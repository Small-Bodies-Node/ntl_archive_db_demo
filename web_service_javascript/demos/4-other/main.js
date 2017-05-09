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
function addEntitiesInfo(type, entitiesInfo) {
    if (entitiesInfo != null) {
        $.each(entitiesInfo, function(i, info) {
            $('#list').append('<li>[' + type + '] name = ' + info.getName() + ', id = ' + info.getId() + '</li>');
        });
    }
}

// The event handler function for '1' button.
function getRelatedEntities1() {
    PDS.getDataSetRelatedEntitites(onSuccess1, onError, 2, 'Mission');
}
function onSuccess1(response) {
    var results = response.getReturn();

    // clear list
    $('#list').empty(); 
    
    addEntitiesInfo('mission', results);
}

// The event handler function for '2' button.
function getRelatedEntities2() {
    PDS.getDataSetRelatedEntitites(onSuccess2, onError, 10, 'Instrument');
}
function onSuccess2(response) {
    var results = response.getReturn();

    // clear list
    $('#list').empty(); 
    
    addEntitiesInfo('instrument', results);
}

// The event handler function for '3' button.
function getPreviewImageURL1() {
    PDS.getPreviewImageURL(onSuccess3, onError, 2276);
}
function onSuccess3(response) {
    var url = response.getReturn();

    // clear list
    $('#list').empty(); 
    $('#list').append('<li>' + url + '</li>');
}
