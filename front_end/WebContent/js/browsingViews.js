function updateViewHeader(mainColgroup, heading, innerColgroup) {
    $('#main-colgroup').empty();
    $('#main-colgroup').append($('#' + mainColgroup + ' colgroup').html());

    $('#main-heading').empty();
    $('#main-heading').append($('#' + heading + ' tr').html());

    $('#navigationView').empty();
    $('#navigationView').append($('#' + innerColgroup + ' colgroup').html());
}

//------------------------------------------------------------------------
// Target Types view
//------------------------------------------------------------------------
function createTargetTypesView(navigationView) {
    var primaryEntities = navigationView.entities;
    var childrenStats = navigationView.childrenStats;

    updateViewHeader('targetTypes-mainColgroup', 'targetTypes-mainHeading', 'targetTypes-innerColgroup');

    $.each(primaryEntities, function(index, entity) {
        var stats = childrenStats[index];

        var targetsId = "targets" + index;
        var missionId = "missions" + index;
        var instrumentsId = "instruments" + index;
        var datasetsId = "datasets" + index;
        var documentsId = "documents" + index;

        var template = unescape($('#targetTypes-row table tbody').html());
        var html = $.validator.format(template, entity.name,
                targetsId    , stats.targetsCount,
                missionId    , stats.missionsCount,
                instrumentsId, stats.instrumentsCount,
                datasetsId   , stats.dataSetsCount,
                documentsId  , stats.documentsCount);

        $('#navigationView').append(html);

        if (stats.targetsCount == 0) {
            $('#' + targetsId).replaceWith('0');
        }
        if (stats.missionsCount == 0) {
            $('#' + missionId).replaceWith('0');
        }
        if (stats.instrumentsCount == 0) {
            $('#' + instrumentsId).replaceWith('0');
        }
        if (stats.dataSetsCount == 0) {
            $('#' + datasetsId).replaceWith('0');
        }
        if (stats.documentsCount == 0) {
            $('#' + documentsId).replaceWith('0');
        }

        $('#' + targetsId).click    ({entity : entity, viewType : TARGET    }, handleNavigationClick);
        $('#' + missionId).click    ({entity : entity, viewType : MISSION   }, handleNavigationClick);
        $('#' + instrumentsId).click({entity : entity, viewType : INSTRUMENT}, handleNavigationClick);
        $('#' + datasetsId).click   ({entity : entity, viewType : DATA_SET  }, handleNavigationClick);
        $('#' + documentsId).click  ({entity : entity, viewType : DOCUMENT  }, handleNavigationClick);
    });
}

//------------------------------------------------------------------------
// Targets view
//------------------------------------------------------------------------
function createTargetsView(navigationView) {
    var primaryEntities = navigationView.entities;
    var childrenStats = navigationView.childrenStats;

    updateViewHeader('targets-mainColgroup', 'targets-mainHeading', 'targets-innerColgroup');

    $.each(primaryEntities, function(index, entity) {
        var stats = childrenStats[index];

        var missionId = "missions" + index;
        var instrumentsId = "instruments" + index;
        var datasetsId = "datasets" + index;
        var documentsId = "documents" + index;

        var template = unescape($('#targets-row tbody').html());
        var html = $.validator.format(template, entity.name,
                missionId    , stats.missionsCount,
                instrumentsId, stats.instrumentsCount,
                datasetsId   , stats.dataSetsCount,
                documentsId  , stats.documentsCount);

        $('#navigationView').append(html);

        $('#' + missionId).click    ({entity : entity, viewType : MISSION   }, handleNavigationClick);
        $('#' + instrumentsId).click({entity : entity, viewType : INSTRUMENT}, handleNavigationClick);
        $('#' + datasetsId).click   ({entity : entity, viewType : DATA_SET  }, handleNavigationClick);
        $('#' + documentsId).click  ({entity : entity, viewType : DOCUMENT  }, handleNavigationClick);
    });
}

//------------------------------------------------------------------------
// Missions view
//------------------------------------------------------------------------
function createMissionsView(navigationView) {
    var primaryEntities = navigationView.entities;
    var childrenStats = navigationView.childrenStats;

    updateViewHeader('missions-mainColgroup', 'missions-mainHeading', 'missions-innerColgroup');

    $.each(primaryEntities, function(index, entity) {
        var stats = childrenStats[index];

        var missionId = "missions" + index;
        var instrumentsId = "instruments" + index;
        var datasetsId = "datasets" + index;
        var documentsId = "documents" + index;

        var template = unescape($('#missions-row tbody').html());
        var html = $.validator.format(template,
                missionId    , entity.name,
                instrumentsId, stats.instrumentsCount,
                datasetsId   , stats.dataSetsCount,
                documentsId  , stats.documentsCount);

        $('#navigationView').append(html);

        $('#' + missionId).click    ({entity : entity, viewType : MISSION   }, showMission);
        $('#' + instrumentsId).click({entity : entity, viewType : INSTRUMENT}, handleNavigationClick);
        $('#' + datasetsId).click   ({entity : entity, viewType : DATA_SET  }, handleNavigationClick);
        $('#' + documentsId).click  ({entity : entity, viewType : DOCUMENT  }, handleNavigationClick);
    });
}

function showMission(event) {
    var data = event.data;

    // update navigation state
    navigationState.push(data.entity.id, data.entity.name);
    navigationState.entityInfoViewActive = true;

    var url = CONTEXT_PATH + "/info/mission";
    $.getJSON(url, {missionId : data.entity.id}, function(mission) {
        updateNavigationBar();
        $('#navigationView').hide();
        $('#paginationControls').hide();

        $('#entityInfoView').show();
        $('#entityInfoView pre').text(mission.description);
    });
}

//------------------------------------------------------------------------
// Instruments view
//------------------------------------------------------------------------
function createInstrumentsView(navigationView) {
    var primaryEntities = navigationView.entities;
    var childrenStats = navigationView.childrenStats;

    updateViewHeader('instruments-mainColgroup', 'instruments-mainHeading', 'instruments-innerColgroup');

    $.each(primaryEntities, function(index, entity) {
        var stats = childrenStats[index];

        var instrumentsId = "instruments" + index;
        var datasetsId = "datasets" + index;
        var documentsId = "documents" + index;

        var template = unescape($('#instruments-row tbody').html());
        var html = $.validator.format(template,
                instrumentsId, entity.name,
                datasetsId   , stats.dataSetsCount,
                documentsId  , stats.documentsCount);

        $('#navigationView').append(html);

        $('#' + instrumentsId).click({entity : entity, viewType : INSTRUMENT}, showInstrument);
        $('#' + datasetsId).click   ({entity : entity, viewType : DATA_SET  }, handleNavigationClick);
        $('#' + documentsId).click  ({entity : entity, viewType : DOCUMENT  }, handleNavigationClick);
    });
}

function showInstrument(event) {
    var data = event.data;

    // update navigation state
    navigationState.push(data.entity.id, data.entity.name);
    navigationState.entityInfoViewActive = true;

    var url = CONTEXT_PATH + "/info/instrument";
    $.getJSON(url, {instrumentId : data.entity.id}, function(instrument) {
        updateNavigationBar();
        $('#navigationView').hide();
        $('#paginationControls').hide();

        $('#entityInfoView').show();
        $('#entityInfoView pre').text(instrument.description);
    });
}

//------------------------------------------------------------------------
// DataSets view
//------------------------------------------------------------------------
function createDataSetsView(navigationView) {
    var primaryEntities = navigationView.entities;
    var childrenStats = navigationView.childrenStats;

    updateViewHeader('datasets-mainColgroup', 'datasets-mainHeading', 'datasets-innerColgroup');

    $.each(primaryEntities, function(index, entity) {
        var stats = childrenStats[index];

        var datasetsId = "datasets" + index;
        var documentsId = "documents" + index;

        var template = unescape($('#datasets-row tbody').html());
        var html = $.validator.format(template,
                datasetsId   , entity.name,
                documentsId  , stats.documentsCount);

        $('#navigationView').append(html);

        var eventData = {entity : entity, viewType : DATA_SET};
        $('#' + datasetsId).click(eventData, showDataSet);

        eventData = {entity : entity, viewType : DOCUMENT};
        $('#' + documentsId).click (eventData, handleNavigationClick);
    });
}

function showDataSet(event) {
    var data = event.data;

    // update navigation state
    navigationState.push(data.entity.id, data.entity.name);
    navigationState.entityInfoViewActive = true;

    var url = CONTEXT_PATH + "/info/dataset";
    $.getJSON(url, {dataSetId : data.entity.id}, function(dataSet) {
        updateNavigationBar();
        $('#navigationView').hide();
        $('#paginationControls').hide();

        $('#entityInfoView').show();
        $('#entityInfoView pre').text(dataSet.description);
    });
}

//------------------------------------------------------------------------
//  Documents view
//------------------------------------------------------------------------
function createDocumentsView(navigationView) {
    var primaryEntities = navigationView.entities;

    updateViewHeader('documents-mainColgroup', 'documents-mainHeading', 'documents-innerColgroup');

    $.each(primaryEntities, function(index, entity) {
        var documentsId = "documents" + index;

        var template = unescape($('#documents-row tbody').html());
        var html = $.validator.format(template,
                documentsId  , entity.name);

        $('#navigationView').append(html);

        var eventData = {entity : entity, viewType : DOCUMENT};

        $('#' + documentsId).click(eventData, showDocument);
    });
}

function showDocument(event) {
    var data = event.data;
    var url = CONTEXT_PATH + "/info/document";

    $.getJSON(url, {documentId : data.entity.id}, function(dataFile) {
        if (dataFile.content) {
            $('#entityInfoView').show();

            // update navigation state
            navigationState.push(data.entity.id, data.entity.name);
            navigationState.entityInfoViewActive = true;

            updateNavigationBar();
            $('#navigationView').hide();
            $('#paginationControls').hide();

            $('#entityInfoView pre').text(dataFile.content);
        } else {
            location.href = "info/downloadDocument?documentId=" + data.entity.id;
        }
    });
}
