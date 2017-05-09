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

// helper function
function getReferencesHtml(references) {
    if (references == null || references.length == 0) {
        return null;
    }
    var items = '';
    $.each(references, function(i, reference) {
        items += '<li>' +  reference.getKeyTextId() + '</li>';
    });
    return '<ul>' + items + '</ul>';
}

// helper function
function getEntitiesNames(entities) {
    if (entities == null || entities.length == 0) {
        return null;
    }
    var names = '';
    $.each(entities, function(i, entity) {
        if (i > 0) {
            names += ', ';
        }
        names = entity.getName();
    });
    return names;
}

//-------- TargetType ---------
var targetTypeId = 1;
function getTargetType() {
    PDS.getTargetType(onGetTargetTypeSuccess, onError, targetTypeId);
}

function onGetTargetTypeSuccess(response) {
    var targetType = response.getReturn();

    $('#label').text('TargetType with id ' + targetTypeId);

    // clear list
    $('#list').empty();

    // fill list with TargetType properties
    $('#list').append('<li> id: ' + targetType.getId() + '</li>');
    $('#list').append('<li> name: ' + targetType.getName() + '</li>');
}

//-------- Target ---------
var targetId = 1;
function getTarget() {
    PDS.getTarget(onGetTargetSuccess, onError, targetId);
}

function onGetTargetSuccess(response) {
    var target = response.getReturn();

    $('#label').text('Target with id ' + targetId);

    // clear list
    $('#list').empty();

    // fill list with Target properties
    $('#list').append('<li> id: ' + target.getId() + '</li>');
    $('#list').append('<li> name: ' + target.getName() + '</li>');
    var types = '';
    $.each(target.getTypes(), function(i, type) {
        if (i > 0) {
            types += ', ';
        }
        types += type.getName();
    });
    $('#list').append('<li> type(s): ' + types + '</li>');
}

//-------- Mission ---------
var missionId = 2;
function getMission() {
    PDS.getMission(onGetMissionSuccess, onError, missionId);
}

function onGetMissionSuccess(response) {
    var mission = response.getReturn();
    
    $('#label').text('Mission with id ' + missionId);

    // clear list
    $('#list').empty();

    // fill list with Mission properties
    $('#list').append('<li> id: ' + mission.getId() + '</li>');
    $('#list').append('<li> name: ' + mission.getName() + '</li>');
    $('#list').append('<li> start date: ' + mission.getStartDate() + '</li>');
    $('#list').append('<li> end date: ' + mission.getEndDate() + '</li>');
    
    var referencesHtml = getReferencesHtml(mission.getReferences());
    if (referencesHtml != null) {
        $('#list').append('<li> references:' + referencesHtml + '</li>');
    }
    
    $('#list').append('<li> description: </br><pre>' + mission.getDescription() + '</pre></li>');
}

//-------- Instrument ---------
var instrumentId = 10;
function getInstrument() {
    PDS.getInstrument(onGetInstrumentSuccess, onError, instrumentId);
}

function onGetInstrumentSuccess(response) {
    var instrument = response.getReturn();
    
    $('#label').text('Instrument with id ' + instrumentId);

    // clear list
    $('#list').empty();

    // fill list with Instrument properties
    $('#list').append('<li> id: ' + instrument.getId() + '</li>');
    $('#list').append('<li> name: ' + instrument.getName() + '</li>');
    $('#list').append('<li> type: ' + instrument.getType() + '</li>');
    $('#list').append('<li> textId: ' + instrument.getTextId() + '</li>');

    var referencesHtml = getReferencesHtml(instrument.getReferences());
    if (referencesHtml != null) {
        $('#list').append('<li> references:' + referencesHtml + '</li>');
    }

    if (instrument.getHosts() != null) {
        var hosts = '';
        $.each(instrument.getHosts(), function(i, host) {
            if (i > 0) {
                hosts += ', ';
            }
            hosts += host.getName();
        });
        $('#list').append('<li> host(s): ' + hosts + '</li>');
    }
    
    $('#list').append('<li> description: </br><pre>' + instrument.getDescription() + '</pre></li>');
}

//-------- DataSet ---------
var dataSetId = 1;
function getDataSet() {
    PDS.getDataSet(onGetDataSetSuccess, onError, dataSetId);
}

function onGetDataSetSuccess(response) {
    var dataSet = response.getReturn();
    
    $('#label').text('DataSet with id ' + dataSetId);

    // clear list
    $('#list').empty();

    // fill list with DataSet properties
    $('#list').append('<li> id: ' + dataSet.getId() + '</li>');
    $('#list').append('<li> name: ' + dataSet.getName() + '</li>');
    $('#list').append('<li> textId: ' + dataSet.getTextId() + '</li>');
    $('#list').append('<li> start date: ' + dataSet.getStartDate() + '</li>');
    $('#list').append('<li> end date: ' + dataSet.getStopDate() + '</li>');

    var referencesHtml = getReferencesHtml(dataSet.getReferences());
    if (referencesHtml != null) {
        $('#list').append('<li> references:' + referencesHtml + '</li>');
    }
    
    var volumes = getEntitiesNames(dataSet.getVolumes());
    if (volumes != null) {
        $('#list').append('<li> volume(s): ' + volumes + '</li>');
    }
    var targets = getEntitiesNames(dataSet.getTargets());
    if (targets != null) {
        $('#list').append('<li> target(s): ' + targets + '</li>');
    }
    var missions = getEntitiesNames(dataSet.getMissions());
    if (missions != null) {
        $('#list').append('<li> mission(s): ' + missions + '</li>');
    }
    var instruments = getEntitiesNames(dataSet.getInstruments());
    if (instruments != null) {
        $('#list').append('<li> instrument(s): ' + instruments + '</li>');
    }

    $('#list').append('<li> description: </br><pre>' + dataSet.getDescription() + '</pre></li>');
}

//-------- DataFile ---------
var dataFileId = 1;
function getDataFile() {
    PDS.getDataFile(onGetDataFileSuccess, onError, dataFileId);
}

function onGetDataFileSuccess(response) {
    var dataFile = response.getReturn();

    $('#label').text('DataFile with id ' + dataFileId);

    // clear list
    $('#list').empty();

    // fill list with Mission properties
    $('#list').append('<li> id: ' + dataFile.getId() + '</li>');
    $('#list').append('<li> name: ' + dataFile.getName() + '</li>');
    if (dataFile.getContent() != null) {
        $('#list').append('<li> content: </br><pre>' + dataFile.getContent() + '</pre></li>');
    } else {
        $('#list').append('<li> binary file: can not show file content</li>');
    }
}
