//------------------------------------------------------------------------
var CONTEXT_PATH = '/nasa_pds/pds';

//------------------------------------------------------------------------
var TARGET_TYPE = 0;
var TARGET      = 1;
var MISSION     = 2;
var INSTRUMENT  = 3;
var DATA_SET    = 4;
var DOCUMENT    = 5;

var Types = [TARGET_TYPE, TARGET, MISSION, INSTRUMENT, DATA_SET, DOCUMENT];

//------------------------------------------------------------------------
var EntityNames = {};
EntityNames[TARGET_TYPE] = "Target Type";
EntityNames[TARGET     ] = "Target";
EntityNames[MISSION    ] = "Mission";
EntityNames[INSTRUMENT ] = "Instrument";
EntityNames[DATA_SET   ] = "Data Set";
EntityNames[DOCUMENT   ] = "Document";

//------------------------------------------------------------------------
function getParameterByName(name) {
  name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
  var regexS = "[\\?&]" + name + "=([^&#]*)";
  var regex = new RegExp(regexS);
  var results = regex.exec(window.location.search);

  if (!results)
      return null;

  return decodeURIComponent(results[1].replace(/\+/g, " "));
}

//------------------------------------------------------------------------
String.format = function() {
    var s = arguments[0];
    for (var i = 0; i < arguments.length - 1; i++) {
      var reg = new RegExp("\\{" + i + "\\}", "gm");
      s = s.replace(reg, arguments[i + 1]);
    }
    return s;
};

//------------------------------------------------------------------------
// Navigation state class
//------------------------------------------------------------------------
var NavigationState = function () {
    var self = this;

    $.each(Types, function(index, type) {
        self[type] = {id : 0, entityName : ""};
    });

    // type of the active view
    this.activeViewType = TARGET_TYPE;

    // whether active view is a navigation view or entity info view
    this.entityInfoViewActive = false;

    // tracks current page
    this.currentPage = 1;
};

// Stores selected entity from the current view before navigating to another view.
NavigationState.prototype.push = function(entityId, entityName) {
    this[this.activeViewType].id = entityId;
    this[this.activeViewType].entityName = entityName;
};

// Returns the command object that will be passed to the navigation controller on the server.
NavigationState.prototype.getCommandObject = function () {
    return {
        targetTypeId : this[TARGET_TYPE].id,
        targetId     : this[TARGET     ].id,
        missionId    : this[MISSION    ].id,
        instrumentId : this[INSTRUMENT ].id,
        dataSetId    : this[DATA_SET   ].id,
        documentId   : this[DOCUMENT   ].id,

        pageNumber   : this.currentPage,
        itemsPerPage : getItemsPerPageCount()
    };
};

//------------------------------------------------------------------------
// Search state class
//------------------------------------------------------------------------
SearchState = function() {
    // whether we have search results available
    this.resultsAvailable = false;

    // array of search results for each view type (mission, target, etc.)
    this.resultsViews  = null;

    // type of the selected search result
    this.selectedResultType = -1;

    // text from search input field
    this.searchText = null;

    // entire advanced search request converted to JSON
    this.advancedSearchRequest = null;
};
