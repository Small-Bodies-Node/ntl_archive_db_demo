// The PDS object is created in order to get access to PDS services.
var PDS = new PlanetaryDataSystem();

// The first step of using the PDS object is to set web service's URL.
// WEB_SERVICE_URL constant is defined in provided Common.js file
// and should not be modified.
PDS.url = WEB_SERVICE_URL;

// The event handler function for '1' button.
function getDataSets1() {
    PDS.getDataSetsInfo(onGetDataSetsSuccess, onError);
}
// The event handler function for '2' button.
function getDataSets2() {
    var page = new Page();
    page.setItemsPerPage(3);
    page.setPageNumber(2);
    PDS.getDataSetsInfo(onGetDataSetsSuccess, onError, page);
}
// The event handler function for '3' button.
function getDataSets3() {
    var restriction = new Restriction();
    restriction.setRestrictionEntityId(3);
    restriction.setRestrictionEntityClass('Mission');
    PDS.getDataSetsInfo(onGetDataSetsSuccess, onError, null, restriction);
}

// This is the function called upon success execution of PDS.getDataSetsInfo function.
function onGetDataSetsSuccess(response) {
    var results = response.getReturn().getResults();

    $('#dataSetsCount').text('datasets count is: ' + results.length);

    // clear list
    $('#dataSetsList').empty(); 
    
    // fill list with missions' names
    $.each(results, function(i, entityInfo) {
        $('#dataSetsList').append('<li>' + entityInfo.getName() + '</li>');
    });
}

// This is the function called upon failure of PDS.getDataSetsInfo method.
function onError(error) {
      alert('error ' + error);
}
