//------------------------------------------------------------------------
$(document).ready(function() {
    $('#searchCriteriaTable').append(unescape($('#advancedSearchRowTemplate table').html()));

    $('.parameter').live('change', function(event) {
        var target = $(event.target);
        var input = target.parent().parent().find('td').eq(2).find('input');

        input.val("");
        if (target.val() == 'START_DATE' || target.val() == 'STOP_DATE') {
            input.datepicker();
        } else {
            input.datepicker("destroy");
        }
    });

    $('.condition').live('change', function(event) {
        var target = $(event.target);
        var isLogicalOR = (target.val() == 'or');
        target.parent().css('padding-bottom', isLogicalOR ? '35px' : '0px');
    });

    $(".add-more-btn").live("click", function(){
        var template = unescape($('#advancedSearchRowTemplate table').html());
        var html = template;

        var index = $('#searchCriteriaTable tr').length - 1;
        $('#searchCriteriaTable tr').eq(index).find('.right-bg-none').remove();

        $('#searchCriteriaTable').append(html);
    });

    $(".search-link").click(function() {
        var searchCriteriaList = getSearchCriteriaList();
        var data = JSON.stringify({list : searchCriteriaList});

        data = encodeURIComponent(data);
        window.location.href = CONTEXT_PATH + "/search/results?advancedSearch=" + data;
    });
});

//------------------------------------------------------------------------
// Splits the search request on separate words and quoted strings
//------------------------------------------------------------------------
function parseSearchRequest(searchText) {
 var splitRegexp =/\w+|"[^"]+"/g;
 var result = [];
 $.each(searchText.match(splitRegexp), function(index, keyword) {
     keyword = keyword.replace(/"/g, "");
     keyword = keyword.replace(/^\s+/, "");
     keyword = keyword.replace(/\s+$/, "");
     if (keyword.length > 0) {
         result.push(keyword);
     }
 });
 return result;
}

//------------------------------------------------------------------------
// Creates search criteria objects that define parameters for advanced search.
//------------------------------------------------------------------------
function getSearchCriteriaList() {
    var criteriaList = [];
    var criteria = {};
    var addCriteria = false;

    $.each($('#searchCriteriaTable tr'), function(index, row) {
        var parameterName = $(row).find('td').eq(1).find('select').val();
        var value = $(row).find('td').eq(2).find('input').val();

        if (parameterName == 'TT') {
            var keywords = parseSearchRequest(value);
            if (keywords.length > 0) {
                criteria.targetTypes = (criteria.targetTypes || []);
                criteria.targetTypes.push.apply(criteria.targetTypes, keywords);
                addCriteria = true;
            }
        } else if (parameterName == 'T') {
            var keywords = parseSearchRequest(value);
            if (keywords.length > 0) {
                criteria.targets = (criteria.targets || []);
                criteria.targets.push.apply(criteria.targets, keywords);
                addCriteria = true;
            }
        } else if (parameterName == 'M') {
            var keywords = parseSearchRequest(value);
            if (keywords.length > 0) {
                criteria.missions = (criteria.missions || []);
                criteria.missions.push.apply(criteria.missions, keywords);
                addCriteria = true;
            }
        } else if (parameterName == 'IH') {
            var keywords = parseSearchRequest(value);
            if (keywords.length > 0) {
                criteria.instrumentHosts = (criteria.instrumentHosts || []);
                criteria.instrumentHosts.push.apply(criteria.instrumentHosts, keywords);
                addCriteria = true;
            }
        } else if (parameterName == 'I') {
            var keywords = parseSearchRequest(value);
            if (keywords.length > 0) {
                criteria.instruments = (criteria.instruments || []);
                criteria.instruments.push.apply(criteria.instruments, keywords);
                addCriteria = true;
            }
        } else if (parameterName == 'DS_NAME') {
            if (value.length > 0) {
                criteria.name = value;
                addCriteria = true;
            }
        } else if (parameterName == 'DS_ID') {
            if (value.length > 0) {
                var id = parseInt(value);
                if (!isNaN(id)) {
                    criteria.dataSetId = id;
                    addCriteria = true;
                }
            }
        } else if (parameterName == 'START_DATE') {
            if (value.length > 0) {
                criteria.startDate = new Date(value);
                addCriteria = true;
            }
        } else if (parameterName == 'STOP_DATE') {
            if (value.length > 0) {
                criteria.stopDate = new Date(value);
                addCriteria = true;
            }
        }

        if ($(row).find('td').eq(3).find('select').val() == 'or') {
            if (addCriteria) {
                criteriaList.push(criteria);
                criteria = {};
                addCriteria = false;
            }
        }
    });

    // add the last criteria group
    if (addCriteria) {
        criteriaList.push(criteria);
    }
    return criteriaList;
}
