// The PDS object is created in order to get access to PDS services.
var PDS = new PlanetaryDataSystem();

// The first step of using the PDS object is to set web service's URL.
// WEB_SERVICE_URL constant is defined in provided Common.js file
// and should not be modified.
PDS.url = WEB_SERVICE_URL;

// error callback
function onError(error) {
      alert('error ' + error);
}
function addEntitiesInfo(type, entitiesInfo) {
    if (entitiesInfo != null) {
        $.each(entitiesInfo, function(i, info) {
            $('#list').append('<li>[' + type + '] name = ' + info.getName() + ', id = ' + info.getId() + '</li>');
        });
    }
}

// The event handler function for '1' button.
function search1() {
    PDS.searchEntities(onSearch1Success, onError, 'DEEP');
}
function onSearch1Success(response) {
    var result = response.getReturn();
    
    $('#list').empty();
    
    var res = result.getTargetTypes();
    if (res.getTotal() > 0) {
        var targetTypesInfo = res.getResults();
        addEntitiesInfo('target type', targetTypesInfo);
    }
    
    res = result.getTargets();
    if (res.getTotal() > 0) {
        var targetsInfo = res.getResults();
        addEntitiesInfo('target', targetsInfo);
    }
        
    res = result.getMissions();
    if (res.getTotal() > 0) {
        var missionsInfo = res.getResults();
        addEntitiesInfo('mission', missionsInfo);
    }
    
    res = result.getInstruments();
    if (res.getTotal() > 0) {
        var instrumentsInfo = res.getResults();
        addEntitiesInfo('instrument', instrumentsInfo);
    }
    
    res = result.getDatasets();
    if (res.getTotal() > 0) {
        var dataSetsInfo = res.getResults();
        addEntitiesInfo('dataset', dataSetsInfo);
    }
    
    res = result.getDataFiles();
    if (res.getTotal() > 0) {
        var dataFilesInfo = res.getResults();
        addEntitiesInfo('datafile', dataFilesInfo);
    }
}

// The event handler function for '2' button.
function search2() {
    PDS.searchEntitiesByType(onSearch2Success, onError, 'DataSet', 'SOHO');
}
function onSearch2Success(response) {
    var results = response.getReturn().getResults();
    if (results != null && results.length > 0) {
        $('#list').empty();
        addEntitiesInfo('dataset', results);
    }
}

// The event handler function for '3' button.
function search3() {
    var restriction = new Restriction();
    restriction.setRestrictionEntityId(1);
    restriction.setRestrictionEntityClass('Mission');

    PDS.searchEntitiesByType(onSearch3Success, onError, 'DataSet', 'V1.0', null, restriction);
}
function onSearch3Success(response) {
    var results = response.getReturn().getResults();
    if (results != null && results.length > 0) {
        $('#list').empty();
        addEntitiesInfo('dataset', results);
    }
}

// The event handler function for '4' button.
function search4() {
    var criteria = new SearchCriteria();
    criteria.setTargets(['tempel']);
    PDS.searchDataSetsByCriteria(onSearch4Success, onError, criteria);
}
function onSearch4Success(response) {
    var results = response.getReturn().getResults();
    if (results != null && results.length > 0) {
        $('#list').empty();
        addEntitiesInfo('dataset', results);
    }
}

// The event handler function for '5' button.
function search5() {
    var criteria = new SearchCriteria();
    criteria.setStartDate('2000-01-01');
    PDS.searchDataSetsByCriteria(onSearch5Success, onError, criteria);
}
function onSearch5Success(response) {
    var results = response.getReturn().getResults();
    if (results != null && results.length > 0) {
        $('#list').empty();
        addEntitiesInfo('dataset', results);
    }
}
