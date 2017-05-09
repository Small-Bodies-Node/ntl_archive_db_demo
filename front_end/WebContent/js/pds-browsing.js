// global state
var navigationState = new NavigationState();
var searchState = new SearchState();

//------------------------------------------------------------------------
// ViewDesc array defines parameters for each view:
//      urlPart - url suffix used when sending request to navigation controller.
//      builder - function that is used to build the corresponding view (html content).
//------------------------------------------------------------------------
var ViewDesc = {};
ViewDesc[TARGET_TYPE] = {urlPart : "targettypes", builder : createTargetTypesView   };
ViewDesc[TARGET     ] = {urlPart : "targets",     builder : createTargetsView       };
ViewDesc[MISSION    ] = {urlPart : "missions",    builder : createMissionsView      };
ViewDesc[INSTRUMENT ] = {urlPart : "instruments", builder : createInstrumentsView   };
ViewDesc[DATA_SET   ] = {urlPart : "datasets",    builder : createDataSetsView      };
ViewDesc[DOCUMENT   ] = {urlPart : "documents",   builder : createDocumentsView     };

//------------------------------------------------------------------------
// Initialization
//------------------------------------------------------------------------
$(document).ready(function() {
    $('.search-btn').click(doSearch);
    $('#searchResultsCloseBtn').click(handleCloseSearchResults);
    $('#itemsPerPage').change(handleItemsPerPageChange);

    var advancedSearchRequest = getParameterByName('advancedSearch');
    if (advancedSearchRequest != null) {
        doAdvanceSearch(advancedSearchRequest);
    } else {
        loadNavigationView(TARGET_TYPE, true);
    }
});

//------------------------------------------------------------------------
// Shows the given view with the given content
//------------------------------------------------------------------------
function showView(viewType, navigationView) {
    ViewDesc[viewType].builder(navigationView);
    updateNavigationBar();
    updateViewInfo(viewType, navigationView);
    navigationState.activeViewType = viewType;
}

//------------------------------------------------------------------------
// Shows the view of the given type filled with entities data.
//------------------------------------------------------------------------
function loadNavigationView(viewType, repaginate) {
    var url = CONTEXT_PATH + "/navigation/" + ViewDesc[viewType].urlPart;

    $.getJSON(url, navigationState.getCommandObject(), function(navigationView) {
        showView(viewType, navigationView);
        if (repaginate) {
            updatePagination(navigationView.totalEntities);
        }
    });
}

//------------------------------------------------------------------------
// Shows the view of the given type filled with search results data.
//------------------------------------------------------------------------
function loadSearchResultsView(viewType, repaginate) {
    var url, type, data, contentType;

    if (searchState.advancedSearchRequest != null) {
        url = CONTEXT_PATH + "/search/doAdvanced?pageNumber={0}&itemsPerPage={1}&type={2}";
        url = String.format(url, navigationState.currentPage, getItemsPerPageCount(), viewType);
        type = 'POST';
        data = searchState.advancedSearchRequest;
        contentType = 'application/json';
    } else {
        url = CONTEXT_PATH + "/search";
        type = 'GET';
        data = {};
        data.searchText = searchState.searchText;
        data.pageNumber = navigationState.currentPage;
        data.itemsPerPage = getItemsPerPageCount();
        contentType = 'application/x-www-form-urlencoded';
    }

    $.ajax({
        url : url,
        type : type,
        data : data,
        dataType : 'json',
        contentType : contentType,

        success : function (searchResults) {
            var navigationView = getSearchResultsView(searchResults, viewType);
            showView(viewType, navigationView);
            if (repaginate) {
                updatePagination(navigationView.totalEntities);
            }
        }

    });
}

//------------------------------------------------------------------------
// Called when the user clicks in the navigation view on the numbered link.
//------------------------------------------------------------------------
function handleNavigationClick(event) {
    // update navigation state
    navigationState.push(event.data.entity.id, event.data.entity.name);

    // navigate to another view
    navigationState.currentPage = 1;
    loadNavigationView(event.data.viewType, true);
}

//------------------------------------------------------------------------
// Updates navigation bar based on the current navigation state.
//------------------------------------------------------------------------
function updateNavigationBar() {
    $('#navigationBar').empty();

    var active = false;
    var template = unescape($('#navigationBar_tpl ul').html());

    $.each(Types, function(index, type) {
        if (navigationState[type].id != 0) {
            var itemId = 'navBarItem' + index;
            var linkId = 'navBarLink' + index;

            var args = [itemId, linkId, EntityNames[type], navigationState[type].entityName];
            var html = $.validator.format(template, args);

            $('#navigationBar').append(html);
            $('#' + linkId).click({index : index}, handleNavigationBarClick);
            active = true;
        }
    });

    // if navigation history is empty then add a placeholder item
    if (!active && !searchState.resultsAvailable) {
        addNavigationBarPlaceholder();
    }
}

//------------------------------------------------------------------------
// Called when the user clicks on the navigation bar item.
//------------------------------------------------------------------------
function handleNavigationBarClick(event) {
    // remove the selected element from the navigation bar and also
    // all the elements after the selected one.
    for (var k = event.data.index; k < Types.length; k++) {
        navigationState[Types[k]] = {id : 0, entityName : ""};
        $('#navBarItem' + k).remove();
    }

    // if navigation history is empty then add a placeholder item
    if (event.data.index == 0 && !searchState.resultsAvailable) {
        addNavigationBarPlaceholder();
    }

    // load new view according to updated navigation state
    var type = Types[event.data.index];
    if (searchState.resultsAvailable && searchState.selectedResultType == type) {
        var navigationView = searchState.resultsViews[type];
        updatePagination(navigationView.totalEntities);
        showView(type, navigationView);
    } else {
        navigationState.currentPage = 1;
        loadNavigationView(event.data.index, true);
    }

    if (navigationState.entityInfoViewActive) {
        $('#entityInfoView').hide();
        $('#entityInfoView pre').empty();
        $('#navigationView').show();
        $('#paginationControls').show();

        navigationState.entityInfoViewActive = false;
    }
}

//------------------------------------------------------------------------
// Event handler. Called when the user clicks on the Search button on the main browsing page.
//------------------------------------------------------------------------
function doSearch() {
    var url = CONTEXT_PATH + "/search";
    var searchText = $('.input-btn input').val();

    var data = {
            searchText : searchText,
            pageNumber : 1,
            itemsPerPage : getItemsPerPageCount()
    };

    $.getJSON(url, data, function(searchResults) {
        showSearchResultsLinks(searchResults);
    });

    searchState.searchText = searchText;
}

//------------------------------------------------------------------------
// Sends advanced search request to the server and displays returned results
// as a list of items for each view type
//------------------------------------------------------------------------
function doAdvanceSearch(advancedSearchRequest) {
    var url = CONTEXT_PATH + "/search/doAdvanced?pageNumber={0}&itemsPerPage={1}&type={2}";
    url = String.format(url, 1, getItemsPerPageCount(), -1);

    $.ajax({
          url: url,
          type: "POST",
          data: advancedSearchRequest,
          dataType: "json",
          contentType: "application/json",
          success: showSearchResultsLinks
    });

    searchState.advancedSearchRequest = advancedSearchRequest;
}

//------------------------------------------------------------------------
// Shows search results view
//------------------------------------------------------------------------
function showSearchResultsLinks(searchResults) {
    $('#searchResultsList').empty();
    $('#notFoundMessage').hide();

    var template = unescape($('#searchResultTemplate ul').html());
    var found = false;

    $.each(Types, function(index, type) {
        var navigationView = getSearchResultsView(searchResults, type);
        if (navigationView) {
            var id = "searchResultLink" + index;
            var html = $.validator.format(template, id, EntityNames[type], navigationView.totalEntities);
            $('#searchResultsList').append(html);
            $('#' + id).click({type : type, navigationView : navigationView}, handleSearchResultLinkClick);
            found = true;
        }
    });

    $('#searchResults').show();
    $('#navigationBar').empty();
    $('#mainView').hide();

    searchState.resultsAvailable = true;

    searchState.resultsViews = [];
    $.each(Types, function(index, type) {
        searchState.resultsViews[index] = getSearchResultsView(searchResults, type);
    });

    if (!found) {
        $('#notFoundMessage').show();
    }
}

//------------------------------------------------------------------------
// Event handler. Called when the user clicks on the link from the search results list.
//------------------------------------------------------------------------
function handleSearchResultLinkClick(event) {
    navigationState = new NavigationState();

    updatePagination(event.data.navigationView.totalEntities);

    showView(event.data.type, event.data.navigationView);

    searchState.selectedResultType = event.data.type;

    $('#entityInfoView').hide();
    $('#entityInfoView pre').empty();
    $('#navigationView').show();
    $('#paginationControls').show();
    navigationState.entityInfoViewActive = false;

    $('#searchResults').show();
    $('#navigationBar').show();
    $('#mainView').show();
}

//------------------------------------------------------------------------
// Removes search results list and displays main browsing view
//------------------------------------------------------------------------
function handleCloseSearchResults() {
    $('#searchResults').hide();
    $('#searchResultsList').empty();
    $('#notFoundMessage').hide();

    addNavigationBarPlaceholder();

    $('#entityInfoView').hide();
    $('#entityInfoView pre').empty();

    $('#navigationView').show();
    $('#paginationControls').show();
    $('#mainView').show();

    navigationState = new NavigationState();
    searchState = new SearchState();

    loadNavigationView(TARGET_TYPE, true);
}

//------------------------------------------------------------------------
//  Updates pagination controls
//------------------------------------------------------------------------
function updatePagination(totalEntities) {
    var itemsPerPage = getItemsPerPageCount();
    if (itemsPerPage == -1) {
        itemsPerPage = totalEntities;
    }
    $("#pagination").pagination(totalEntities, {
        items_per_page : itemsPerPage,
        num_display_entries : 5,
        callback : function(pageIndex) {
            navigationState.currentPage = (pageIndex + 1);
            if (searchState.resultsAvailable && searchState.selectedResultType == navigationState.activeViewType) {
                loadSearchResultsView(navigationState.activeViewType, false);
            } else {
                loadNavigationView(navigationState.activeViewType, false);
            }
            return false;
        }
    });
}

//------------------------------------------------------------------------
// Updates info message about current view
//------------------------------------------------------------------------
function updateViewInfo(viewType, navigationView) {
    var template = unescape($('#viewInfo_tpl').html());

    var itemsPerPage = getItemsPerPageCount();
    if (itemsPerPage == -1) {
        itemsPerPage = navigationView.totalEntities;
    }

    var from = (navigationState.currentPage - 1) * itemsPerPage + 1; // 1-based
    var to = Math.min(from + itemsPerPage - 1, navigationView.totalEntities);

    var args = [from, to, navigationView.totalEntities, EntityNames[viewType] + 's'];
    var html =  $.validator.format(template, args);
    $('#viewInfo').html(html);
}

//------------------------------------------------------------------------
function addNavigationBarPlaceholder() {
    var template = unescape($('#navigationBar_tpl ul').html());

    var args = ['navBarItem', 'navBarLink', "", "All Target Types"];
    var html = $.validator.format(template, args);

    $('#navigationBar').html(html);
    $('#navigationBar a').remove();
}

//------------------------------------------------------------------------
function handleItemsPerPageChange() {
    navigationState.currentPage = 1;

    if (searchState.resultsAvailable && searchState.selectedResultType == navigationState.activeViewType) {
        loadSearchResultsView(navigationState.activeViewType, true);
    } else {
        loadNavigationView(navigationState.activeViewType, true);
    }
}

//------------------------------------------------------------------------
function getItemsPerPageCount() {
    var itemsPerPage = $('#itemsPerPage').val();
    return (itemsPerPage == 'all') ? -1 : parseInt(itemsPerPage);
}

//------------------------------------------------------------------------
function getSearchResultsView(searchResults, viewType) {
    var navigationView = null;
    if (viewType == TARGET_TYPE) {
        navigationView = searchResults.targetTypes;
    } else if (viewType == TARGET) {
        navigationView = searchResults.targets;
    } else if (viewType == MISSION) {
        navigationView = searchResults.missions;
    } else if (viewType == INSTRUMENT) {
        navigationView = searchResults.instruments;
    } else if (viewType == DATA_SET) {
        navigationView = searchResults.dataSets;
    } else if (viewType == DOCUMENT) {
        navigationView = searchResults.documents;
    }
    return navigationView;
}
