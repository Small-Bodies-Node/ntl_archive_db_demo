//
// Definitions for schema: http://pds.nasa.gov/
//  file:/D:/topcoder/work/assemblies/pds_projects/web_service/WebContent/wsdl/planetarydatasystem_schema1.xsd
//
//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataSetsInfo
//
function pds_nasa_gov__getDataSetsInfo () {
    this.typeMarker = 'pds_nasa_gov__getDataSetsInfo';
    this._page = null;
    this._restriction = null;
}

//
// accessor is pds_nasa_gov__getDataSetsInfo.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__getDataSetsInfo.prototype.setPage
//
function pds_nasa_gov__getDataSetsInfo_getPage() { return this._page;}

pds_nasa_gov__getDataSetsInfo.prototype.getPage = pds_nasa_gov__getDataSetsInfo_getPage;

function pds_nasa_gov__getDataSetsInfo_setPage(value) { this._page = value;}

pds_nasa_gov__getDataSetsInfo.prototype.setPage = pds_nasa_gov__getDataSetsInfo_setPage;
//
// accessor is pds_nasa_gov__getDataSetsInfo.prototype.getRestriction
// element get for restriction
// - element type is {http://pds.nasa.gov/}restriction
// - optional element
//
// element set for restriction
// setter function is is pds_nasa_gov__getDataSetsInfo.prototype.setRestriction
//
function pds_nasa_gov__getDataSetsInfo_getRestriction() { return this._restriction;}

pds_nasa_gov__getDataSetsInfo.prototype.getRestriction = pds_nasa_gov__getDataSetsInfo_getRestriction;

function pds_nasa_gov__getDataSetsInfo_setRestriction(value) { this._restriction = value;}

pds_nasa_gov__getDataSetsInfo.prototype.setRestriction = pds_nasa_gov__getDataSetsInfo_setRestriction;
//
// Serialize {http://pds.nasa.gov/}getDataSetsInfo
//
function pds_nasa_gov__getDataSetsInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    // block for local variables
    {
     if (this._restriction != null) {
      xml = xml + this._restriction.serialize(cxfjsutils, 'restriction', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataSetsInfo.prototype.serialize = pds_nasa_gov__getDataSetsInfo_serialize;

function pds_nasa_gov__getDataSetsInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataSetsInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restriction');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'restriction')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__restriction_deserialize(cxfjsutils, curElement);
     }
     newobject.setRestriction(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTargetTypesInfoResponse
//
function pds_nasa_gov__getTargetTypesInfoResponse () {
    this.typeMarker = 'pds_nasa_gov__getTargetTypesInfoResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getTargetTypesInfoResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getTargetTypesInfoResponse.prototype.setReturn
//
function pds_nasa_gov__getTargetTypesInfoResponse_getReturn() { return this._return;}

pds_nasa_gov__getTargetTypesInfoResponse.prototype.getReturn = pds_nasa_gov__getTargetTypesInfoResponse_getReturn;

function pds_nasa_gov__getTargetTypesInfoResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getTargetTypesInfoResponse.prototype.setReturn = pds_nasa_gov__getTargetTypesInfoResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getTargetTypesInfoResponse
//
function pds_nasa_gov__getTargetTypesInfoResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTargetTypesInfoResponse.prototype.serialize = pds_nasa_gov__getTargetTypesInfoResponse_serialize;

function pds_nasa_gov__getTargetTypesInfoResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTargetTypesInfoResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}DataSetProcessingException
//
function pds_nasa_gov__DataSetProcessingException () {
    this.typeMarker = 'pds_nasa_gov__DataSetProcessingException';
    this._logged = null;
}

//
// accessor is pds_nasa_gov__DataSetProcessingException.prototype.getLogged
// element get for logged
// - element type is {http://www.w3.org/2001/XMLSchema}boolean
// - required element
// - nillable
//
// element set for logged
// setter function is is pds_nasa_gov__DataSetProcessingException.prototype.setLogged
//
function pds_nasa_gov__DataSetProcessingException_getLogged() { return this._logged;}

pds_nasa_gov__DataSetProcessingException.prototype.getLogged = pds_nasa_gov__DataSetProcessingException_getLogged;

function pds_nasa_gov__DataSetProcessingException_setLogged(value) { this._logged = value;}

pds_nasa_gov__DataSetProcessingException.prototype.setLogged = pds_nasa_gov__DataSetProcessingException_setLogged;
//
// Serialize {http://pds.nasa.gov/}DataSetProcessingException
//
function pds_nasa_gov__DataSetProcessingException_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._logged == null) {
      xml = xml + '<logged xsi:nil=\'true\'/>';
     } else {
      xml = xml + '<logged>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._logged);
      xml = xml + '</logged>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__DataSetProcessingException.prototype.serialize = pds_nasa_gov__DataSetProcessingException_serialize;

function pds_nasa_gov__DataSetProcessingException_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__DataSetProcessingException();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing logged');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = (value == 'true');
    }
    newobject.setLogged(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}metadataObject
//
function pds_nasa_gov__metadataObject () {
    this.typeMarker = 'pds_nasa_gov__metadataObject';
    this._id = 0;
    this._name = null;
    this._children = [];
    this._properties = [];
}

//
// accessor is pds_nasa_gov__metadataObject.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__metadataObject.prototype.setId
//
function pds_nasa_gov__metadataObject_getId() { return this._id;}

pds_nasa_gov__metadataObject.prototype.getId = pds_nasa_gov__metadataObject_getId;

function pds_nasa_gov__metadataObject_setId(value) { this._id = value;}

pds_nasa_gov__metadataObject.prototype.setId = pds_nasa_gov__metadataObject_setId;
//
// accessor is pds_nasa_gov__metadataObject.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__metadataObject.prototype.setName
//
function pds_nasa_gov__metadataObject_getName() { return this._name;}

pds_nasa_gov__metadataObject.prototype.getName = pds_nasa_gov__metadataObject_getName;

function pds_nasa_gov__metadataObject_setName(value) { this._name = value;}

pds_nasa_gov__metadataObject.prototype.setName = pds_nasa_gov__metadataObject_setName;
//
// accessor is pds_nasa_gov__metadataObject.prototype.getChildren
// element get for children
// - element type is {http://pds.nasa.gov/}metadataObject
// - required element
// - array
// - nillable
//
// element set for children
// setter function is is pds_nasa_gov__metadataObject.prototype.setChildren
//
function pds_nasa_gov__metadataObject_getChildren() { return this._children;}

pds_nasa_gov__metadataObject.prototype.getChildren = pds_nasa_gov__metadataObject_getChildren;

function pds_nasa_gov__metadataObject_setChildren(value) { this._children = value;}

pds_nasa_gov__metadataObject.prototype.setChildren = pds_nasa_gov__metadataObject_setChildren;
//
// accessor is pds_nasa_gov__metadataObject.prototype.getProperties
// element get for properties
// - element type is {http://pds.nasa.gov/}property
// - required element
// - array
// - nillable
//
// element set for properties
// setter function is is pds_nasa_gov__metadataObject.prototype.setProperties
//
function pds_nasa_gov__metadataObject_getProperties() { return this._properties;}

pds_nasa_gov__metadataObject.prototype.getProperties = pds_nasa_gov__metadataObject_getProperties;

function pds_nasa_gov__metadataObject_setProperties(value) { this._properties = value;}

pds_nasa_gov__metadataObject.prototype.setProperties = pds_nasa_gov__metadataObject_setProperties;
//
// Serialize {http://pds.nasa.gov/}metadataObject
//
function pds_nasa_gov__metadataObject_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._children != null) {
      for (var ax = 0;ax < this._children.length;ax ++) {
       if (this._children[ax] == null) {
        xml = xml + '<children xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._children[ax].serialize(cxfjsutils, 'children', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._properties != null) {
      for (var ax = 0;ax < this._properties.length;ax ++) {
       if (this._properties[ax] == null) {
        xml = xml + '<properties xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._properties[ax].serialize(cxfjsutils, 'properties', null);
       }
      }
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__metadataObject.prototype.serialize = pds_nasa_gov__metadataObject_serialize;

function pds_nasa_gov__metadataObject_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__metadataObject();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing children');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'children')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__metadataObject_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'children'));
     newobject.setChildren(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing properties');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'properties')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__property_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'properties'));
     newobject.setProperties(item);
     var item = null;
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}pagedResults
//
function pds_nasa_gov__pagedResults () {
    this.typeMarker = 'pds_nasa_gov__pagedResults';
    this._results = [];
    this._total = 0;
}

//
// accessor is pds_nasa_gov__pagedResults.prototype.getResults
// element get for results
// - element type is {http://www.w3.org/2001/XMLSchema}anyType
// - required element
// - array
// - nillable
//
// element set for results
// setter function is is pds_nasa_gov__pagedResults.prototype.setResults
//
function pds_nasa_gov__pagedResults_getResults() { return this._results;}

pds_nasa_gov__pagedResults.prototype.getResults = pds_nasa_gov__pagedResults_getResults;

function pds_nasa_gov__pagedResults_setResults(value) { this._results = value;}

pds_nasa_gov__pagedResults.prototype.setResults = pds_nasa_gov__pagedResults_setResults;
//
// accessor is pds_nasa_gov__pagedResults.prototype.getTotal
// element get for total
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for total
// setter function is is pds_nasa_gov__pagedResults.prototype.setTotal
//
function pds_nasa_gov__pagedResults_getTotal() { return this._total;}

pds_nasa_gov__pagedResults.prototype.getTotal = pds_nasa_gov__pagedResults_getTotal;

function pds_nasa_gov__pagedResults_setTotal(value) { this._total = value;}

pds_nasa_gov__pagedResults.prototype.setTotal = pds_nasa_gov__pagedResults_setTotal;
//
// Serialize {http://pds.nasa.gov/}pagedResults
//
function pds_nasa_gov__pagedResults_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._results != null) {
      for (var ax = 0;ax < this._results.length;ax ++) {
       if (this._results[ax] == null) {
        xml = xml + '<results xsi:nil=\'true\'/>';
       } else {
        var anyHolder = this._results[ax];
        var anySerializer;
        var typeAttr = '';
        if (anyHolder != null) {
         if (!anyHolder.raw) {
          anySerializer = cxfjsutils.interfaceObject.globalElementSerializers[anyHolder.qname];
         }
         if (anyHolder.xsiType) {
          var typePrefix = 'cxfjst1';
          var typeAttr = 'xmlns:' + typePrefix + '=\'' + anyHolder.namespaceURI + '\'';
          typeAttr = typeAttr + ' xsi:type=\'' + typePrefix + ':' + anyHolder.localName + '\'';
         }
         if (anySerializer) {
          xml = xml + this._results[ax].serialize(cxfjsutils, 'results', typeAttr);
         } else {
          xml = xml + '<results ' + typeAttr + '>';
          if (!anyHolder.raw) {
           xml = xml + cxfjsutils.escapeXmlEntities(this._results[ax]);
          } else {
           xml = xml + anyHolder.xml;
          }
          xml = xml + '</results>';
         }
        } else {
         xml = xml + '<results xsi:nil=\'true\'/>';
        }
       }
      }
     }
    }
    // block for local variables
    {
     xml = xml + '<total>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._total);
     xml = xml + '</total>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__pagedResults.prototype.serialize = pds_nasa_gov__pagedResults_serialize;

function pds_nasa_gov__pagedResults_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__pagedResults();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing results');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'results')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = org_apache_cxf_deserialize_anyType(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'results'));
     newobject.setResults(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing total');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setTotal(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataSetRelatedEntititesResponse
//
function pds_nasa_gov__getDataSetRelatedEntititesResponse () {
    this.typeMarker = 'pds_nasa_gov__getDataSetRelatedEntititesResponse';
    this._return = [];
}

//
// accessor is pds_nasa_gov__getDataSetRelatedEntititesResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}entityInfo
// - required element
// - array
//
// element set for return
// setter function is is pds_nasa_gov__getDataSetRelatedEntititesResponse.prototype.setReturn
//
function pds_nasa_gov__getDataSetRelatedEntititesResponse_getReturn() { return this._return;}

pds_nasa_gov__getDataSetRelatedEntititesResponse.prototype.getReturn = pds_nasa_gov__getDataSetRelatedEntititesResponse_getReturn;

function pds_nasa_gov__getDataSetRelatedEntititesResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getDataSetRelatedEntititesResponse.prototype.setReturn = pds_nasa_gov__getDataSetRelatedEntititesResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getDataSetRelatedEntititesResponse
//
function pds_nasa_gov__getDataSetRelatedEntititesResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      for (var ax = 0;ax < this._return.length;ax ++) {
       if (this._return[ax] == null) {
        xml = xml + '<return/>';
       } else {
        xml = xml + this._return[ax].serialize(cxfjsutils, 'return', null);
       }
      }
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataSetRelatedEntititesResponse.prototype.serialize = pds_nasa_gov__getDataSetRelatedEntititesResponse_serialize;

function pds_nasa_gov__getDataSetRelatedEntititesResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataSetRelatedEntititesResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__entityInfo_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return'));
     newobject.setReturn(item);
     var item = null;
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}property
//
function pds_nasa_gov__property () {
    this.typeMarker = 'pds_nasa_gov__property';
    this._id = 0;
    this._name = null;
    this._values = [];
}

//
// accessor is pds_nasa_gov__property.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__property.prototype.setId
//
function pds_nasa_gov__property_getId() { return this._id;}

pds_nasa_gov__property.prototype.getId = pds_nasa_gov__property_getId;

function pds_nasa_gov__property_setId(value) { this._id = value;}

pds_nasa_gov__property.prototype.setId = pds_nasa_gov__property_setId;
//
// accessor is pds_nasa_gov__property.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__property.prototype.setName
//
function pds_nasa_gov__property_getName() { return this._name;}

pds_nasa_gov__property.prototype.getName = pds_nasa_gov__property_getName;

function pds_nasa_gov__property_setName(value) { this._name = value;}

pds_nasa_gov__property.prototype.setName = pds_nasa_gov__property_setName;
//
// accessor is pds_nasa_gov__property.prototype.getValues
// element get for values
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
// - array
// - nillable
//
// element set for values
// setter function is is pds_nasa_gov__property.prototype.setValues
//
function pds_nasa_gov__property_getValues() { return this._values;}

pds_nasa_gov__property.prototype.getValues = pds_nasa_gov__property_getValues;

function pds_nasa_gov__property_setValues(value) { this._values = value;}

pds_nasa_gov__property.prototype.setValues = pds_nasa_gov__property_setValues;
//
// Serialize {http://pds.nasa.gov/}property
//
function pds_nasa_gov__property_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._values != null) {
      for (var ax = 0;ax < this._values.length;ax ++) {
       if (this._values[ax] == null) {
        xml = xml + '<values xsi:nil=\'true\'/>';
       } else {
        xml = xml + '<values>';
        xml = xml + cxfjsutils.escapeXmlEntities(this._values[ax]);
        xml = xml + '</values>';
       }
      }
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__property.prototype.serialize = pds_nasa_gov__property_serialize;

function pds_nasa_gov__property_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__property();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing values');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'values')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       value = cxfjsutils.getNodeText(curElement);
       arrayItem = value;
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'values'));
     newobject.setValues(item);
     var item = null;
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getInstrument
//
function pds_nasa_gov__getInstrument () {
    this.typeMarker = 'pds_nasa_gov__getInstrument';
    this._id = 0;
}

//
// accessor is pds_nasa_gov__getInstrument.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__getInstrument.prototype.setId
//
function pds_nasa_gov__getInstrument_getId() { return this._id;}

pds_nasa_gov__getInstrument.prototype.getId = pds_nasa_gov__getInstrument_getId;

function pds_nasa_gov__getInstrument_setId(value) { this._id = value;}

pds_nasa_gov__getInstrument.prototype.setId = pds_nasa_gov__getInstrument_setId;
//
// Serialize {http://pds.nasa.gov/}getInstrument
//
function pds_nasa_gov__getInstrument_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getInstrument.prototype.serialize = pds_nasa_gov__getInstrument_serialize;

function pds_nasa_gov__getInstrument_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getInstrument();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDocumentsInfo
//
function pds_nasa_gov__getDocumentsInfo () {
    this.typeMarker = 'pds_nasa_gov__getDocumentsInfo';
    this._page = null;
    this._restriction = null;
}

//
// accessor is pds_nasa_gov__getDocumentsInfo.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__getDocumentsInfo.prototype.setPage
//
function pds_nasa_gov__getDocumentsInfo_getPage() { return this._page;}

pds_nasa_gov__getDocumentsInfo.prototype.getPage = pds_nasa_gov__getDocumentsInfo_getPage;

function pds_nasa_gov__getDocumentsInfo_setPage(value) { this._page = value;}

pds_nasa_gov__getDocumentsInfo.prototype.setPage = pds_nasa_gov__getDocumentsInfo_setPage;
//
// accessor is pds_nasa_gov__getDocumentsInfo.prototype.getRestriction
// element get for restriction
// - element type is {http://pds.nasa.gov/}restriction
// - optional element
//
// element set for restriction
// setter function is is pds_nasa_gov__getDocumentsInfo.prototype.setRestriction
//
function pds_nasa_gov__getDocumentsInfo_getRestriction() { return this._restriction;}

pds_nasa_gov__getDocumentsInfo.prototype.getRestriction = pds_nasa_gov__getDocumentsInfo_getRestriction;

function pds_nasa_gov__getDocumentsInfo_setRestriction(value) { this._restriction = value;}

pds_nasa_gov__getDocumentsInfo.prototype.setRestriction = pds_nasa_gov__getDocumentsInfo_setRestriction;
//
// Serialize {http://pds.nasa.gov/}getDocumentsInfo
//
function pds_nasa_gov__getDocumentsInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    // block for local variables
    {
     if (this._restriction != null) {
      xml = xml + this._restriction.serialize(cxfjsutils, 'restriction', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDocumentsInfo.prototype.serialize = pds_nasa_gov__getDocumentsInfo_serialize;

function pds_nasa_gov__getDocumentsInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDocumentsInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restriction');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'restriction')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__restriction_deserialize(cxfjsutils, curElement);
     }
     newobject.setRestriction(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getPreviewImageURLResponse
//
function pds_nasa_gov__getPreviewImageURLResponse () {
    this.typeMarker = 'pds_nasa_gov__getPreviewImageURLResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getPreviewImageURLResponse.prototype.getReturn
// element get for return
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getPreviewImageURLResponse.prototype.setReturn
//
function pds_nasa_gov__getPreviewImageURLResponse_getReturn() { return this._return;}

pds_nasa_gov__getPreviewImageURLResponse.prototype.getReturn = pds_nasa_gov__getPreviewImageURLResponse_getReturn;

function pds_nasa_gov__getPreviewImageURLResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getPreviewImageURLResponse.prototype.setReturn = pds_nasa_gov__getPreviewImageURLResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getPreviewImageURLResponse
//
function pds_nasa_gov__getPreviewImageURLResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + '<return>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._return);
      xml = xml + '</return>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getPreviewImageURLResponse.prototype.serialize = pds_nasa_gov__getPreviewImageURLResponse_serialize;

function pds_nasa_gov__getPreviewImageURLResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getPreviewImageURLResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}namedEntity
//
function pds_nasa_gov__namedEntity () {
    this.typeMarker = 'pds_nasa_gov__namedEntity';
    this._id = 0;
    this._name = null;
}

//
// accessor is pds_nasa_gov__namedEntity.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__namedEntity.prototype.setId
//
function pds_nasa_gov__namedEntity_getId() { return this._id;}

pds_nasa_gov__namedEntity.prototype.getId = pds_nasa_gov__namedEntity_getId;

function pds_nasa_gov__namedEntity_setId(value) { this._id = value;}

pds_nasa_gov__namedEntity.prototype.setId = pds_nasa_gov__namedEntity_setId;
//
// accessor is pds_nasa_gov__namedEntity.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__namedEntity.prototype.setName
//
function pds_nasa_gov__namedEntity_getName() { return this._name;}

pds_nasa_gov__namedEntity.prototype.getName = pds_nasa_gov__namedEntity_getName;

function pds_nasa_gov__namedEntity_setName(value) { this._name = value;}

pds_nasa_gov__namedEntity.prototype.setName = pds_nasa_gov__namedEntity_setName;
//
// Serialize {http://pds.nasa.gov/}namedEntity
//
function pds_nasa_gov__namedEntity_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__namedEntity.prototype.serialize = pds_nasa_gov__namedEntity_serialize;

function pds_nasa_gov__namedEntity_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__namedEntity();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTargetTypesInfo
//
function pds_nasa_gov__getTargetTypesInfo () {
    this.typeMarker = 'pds_nasa_gov__getTargetTypesInfo';
    this._page = null;
}

//
// accessor is pds_nasa_gov__getTargetTypesInfo.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__getTargetTypesInfo.prototype.setPage
//
function pds_nasa_gov__getTargetTypesInfo_getPage() { return this._page;}

pds_nasa_gov__getTargetTypesInfo.prototype.getPage = pds_nasa_gov__getTargetTypesInfo_getPage;

function pds_nasa_gov__getTargetTypesInfo_setPage(value) { this._page = value;}

pds_nasa_gov__getTargetTypesInfo.prototype.setPage = pds_nasa_gov__getTargetTypesInfo_setPage;
//
// Serialize {http://pds.nasa.gov/}getTargetTypesInfo
//
function pds_nasa_gov__getTargetTypesInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTargetTypesInfo.prototype.serialize = pds_nasa_gov__getTargetTypesInfo_serialize;

function pds_nasa_gov__getTargetTypesInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTargetTypesInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}restriction
//
function pds_nasa_gov__restriction () {
    this.typeMarker = 'pds_nasa_gov__restriction';
    this._restrictionEntityClass = '';
    this._restrictionEntityId = 0;
}

//
// accessor is pds_nasa_gov__restriction.prototype.getRestrictionEntityClass
// element get for restrictionEntityClass
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
//
// element set for restrictionEntityClass
// setter function is is pds_nasa_gov__restriction.prototype.setRestrictionEntityClass
//
function pds_nasa_gov__restriction_getRestrictionEntityClass() { return this._restrictionEntityClass;}

pds_nasa_gov__restriction.prototype.getRestrictionEntityClass = pds_nasa_gov__restriction_getRestrictionEntityClass;

function pds_nasa_gov__restriction_setRestrictionEntityClass(value) { this._restrictionEntityClass = value;}

pds_nasa_gov__restriction.prototype.setRestrictionEntityClass = pds_nasa_gov__restriction_setRestrictionEntityClass;
//
// accessor is pds_nasa_gov__restriction.prototype.getRestrictionEntityId
// element get for restrictionEntityId
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for restrictionEntityId
// setter function is is pds_nasa_gov__restriction.prototype.setRestrictionEntityId
//
function pds_nasa_gov__restriction_getRestrictionEntityId() { return this._restrictionEntityId;}

pds_nasa_gov__restriction.prototype.getRestrictionEntityId = pds_nasa_gov__restriction_getRestrictionEntityId;

function pds_nasa_gov__restriction_setRestrictionEntityId(value) { this._restrictionEntityId = value;}

pds_nasa_gov__restriction.prototype.setRestrictionEntityId = pds_nasa_gov__restriction_setRestrictionEntityId;
//
// Serialize {http://pds.nasa.gov/}restriction
//
function pds_nasa_gov__restriction_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<restrictionEntityClass>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._restrictionEntityClass);
     xml = xml + '</restrictionEntityClass>';
    }
    // block for local variables
    {
     xml = xml + '<restrictionEntityId>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._restrictionEntityId);
     xml = xml + '</restrictionEntityId>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__restriction.prototype.serialize = pds_nasa_gov__restriction_serialize;

function pds_nasa_gov__restriction_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__restriction();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restrictionEntityClass');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = value;
    }
    newobject.setRestrictionEntityClass(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restrictionEntityId');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setRestrictionEntityId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getPreviewImageURL
//
function pds_nasa_gov__getPreviewImageURL () {
    this.typeMarker = 'pds_nasa_gov__getPreviewImageURL';
    this._imageFileId = 0;
}

//
// accessor is pds_nasa_gov__getPreviewImageURL.prototype.getImageFileId
// element get for imageFileId
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for imageFileId
// setter function is is pds_nasa_gov__getPreviewImageURL.prototype.setImageFileId
//
function pds_nasa_gov__getPreviewImageURL_getImageFileId() { return this._imageFileId;}

pds_nasa_gov__getPreviewImageURL.prototype.getImageFileId = pds_nasa_gov__getPreviewImageURL_getImageFileId;

function pds_nasa_gov__getPreviewImageURL_setImageFileId(value) { this._imageFileId = value;}

pds_nasa_gov__getPreviewImageURL.prototype.setImageFileId = pds_nasa_gov__getPreviewImageURL_setImageFileId;
//
// Serialize {http://pds.nasa.gov/}getPreviewImageURL
//
function pds_nasa_gov__getPreviewImageURL_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<imageFileId>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._imageFileId);
     xml = xml + '</imageFileId>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getPreviewImageURL.prototype.serialize = pds_nasa_gov__getPreviewImageURL_serialize;

function pds_nasa_gov__getPreviewImageURL_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getPreviewImageURL();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing imageFileId');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setImageFileId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchDataSetsByCriteria
//
function pds_nasa_gov__searchDataSetsByCriteria () {
    this.typeMarker = 'pds_nasa_gov__searchDataSetsByCriteria';
    this._criteria = null;
    this._page = null;
}

//
// accessor is pds_nasa_gov__searchDataSetsByCriteria.prototype.getCriteria
// element get for criteria
// - element type is {http://pds.nasa.gov/}searchCriteria
// - required element
//
// element set for criteria
// setter function is is pds_nasa_gov__searchDataSetsByCriteria.prototype.setCriteria
//
function pds_nasa_gov__searchDataSetsByCriteria_getCriteria() { return this._criteria;}

pds_nasa_gov__searchDataSetsByCriteria.prototype.getCriteria = pds_nasa_gov__searchDataSetsByCriteria_getCriteria;

function pds_nasa_gov__searchDataSetsByCriteria_setCriteria(value) { this._criteria = value;}

pds_nasa_gov__searchDataSetsByCriteria.prototype.setCriteria = pds_nasa_gov__searchDataSetsByCriteria_setCriteria;
//
// accessor is pds_nasa_gov__searchDataSetsByCriteria.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__searchDataSetsByCriteria.prototype.setPage
//
function pds_nasa_gov__searchDataSetsByCriteria_getPage() { return this._page;}

pds_nasa_gov__searchDataSetsByCriteria.prototype.getPage = pds_nasa_gov__searchDataSetsByCriteria_getPage;

function pds_nasa_gov__searchDataSetsByCriteria_setPage(value) { this._page = value;}

pds_nasa_gov__searchDataSetsByCriteria.prototype.setPage = pds_nasa_gov__searchDataSetsByCriteria_setPage;
//
// Serialize {http://pds.nasa.gov/}searchDataSetsByCriteria
//
function pds_nasa_gov__searchDataSetsByCriteria_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + this._criteria.serialize(cxfjsutils, 'criteria', null);
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchDataSetsByCriteria.prototype.serialize = pds_nasa_gov__searchDataSetsByCriteria_serialize;

function pds_nasa_gov__searchDataSetsByCriteria_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchDataSetsByCriteria();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing criteria');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     item = pds_nasa_gov__searchCriteria_deserialize(cxfjsutils, curElement);
    }
    newobject.setCriteria(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}wsDataFile
//
function pds_nasa_gov__wsDataFile () {
    this.typeMarker = 'pds_nasa_gov__wsDataFile';
    this._content = null;
    this._dataHandler = null;
    this._filename = null;
    this._id = 0;
    this._name = null;
}

//
// accessor is pds_nasa_gov__wsDataFile.prototype.getContent
// element get for content
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for content
// setter function is is pds_nasa_gov__wsDataFile.prototype.setContent
//
function pds_nasa_gov__wsDataFile_getContent() { return this._content;}

pds_nasa_gov__wsDataFile.prototype.getContent = pds_nasa_gov__wsDataFile_getContent;

function pds_nasa_gov__wsDataFile_setContent(value) { this._content = value;}

pds_nasa_gov__wsDataFile.prototype.setContent = pds_nasa_gov__wsDataFile_setContent;
//
// accessor is pds_nasa_gov__wsDataFile.prototype.getDataHandler
// element get for dataHandler
// - element type is {http://www.w3.org/2001/XMLSchema}base64Binary
// - optional element
//
// element set for dataHandler
// setter function is is pds_nasa_gov__wsDataFile.prototype.setDataHandler
//
function pds_nasa_gov__wsDataFile_getDataHandler() { return this._dataHandler;}

pds_nasa_gov__wsDataFile.prototype.getDataHandler = pds_nasa_gov__wsDataFile_getDataHandler;

function pds_nasa_gov__wsDataFile_setDataHandler(value) { this._dataHandler = value;}

pds_nasa_gov__wsDataFile.prototype.setDataHandler = pds_nasa_gov__wsDataFile_setDataHandler;
//
// accessor is pds_nasa_gov__wsDataFile.prototype.getFilename
// element get for filename
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for filename
// setter function is is pds_nasa_gov__wsDataFile.prototype.setFilename
//
function pds_nasa_gov__wsDataFile_getFilename() { return this._filename;}

pds_nasa_gov__wsDataFile.prototype.getFilename = pds_nasa_gov__wsDataFile_getFilename;

function pds_nasa_gov__wsDataFile_setFilename(value) { this._filename = value;}

pds_nasa_gov__wsDataFile.prototype.setFilename = pds_nasa_gov__wsDataFile_setFilename;
//
// accessor is pds_nasa_gov__wsDataFile.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__wsDataFile.prototype.setId
//
function pds_nasa_gov__wsDataFile_getId() { return this._id;}

pds_nasa_gov__wsDataFile.prototype.getId = pds_nasa_gov__wsDataFile_getId;

function pds_nasa_gov__wsDataFile_setId(value) { this._id = value;}

pds_nasa_gov__wsDataFile.prototype.setId = pds_nasa_gov__wsDataFile_setId;
//
// accessor is pds_nasa_gov__wsDataFile.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__wsDataFile.prototype.setName
//
function pds_nasa_gov__wsDataFile_getName() { return this._name;}

pds_nasa_gov__wsDataFile.prototype.getName = pds_nasa_gov__wsDataFile_getName;

function pds_nasa_gov__wsDataFile_setName(value) { this._name = value;}

pds_nasa_gov__wsDataFile.prototype.setName = pds_nasa_gov__wsDataFile_setName;
//
// Serialize {http://pds.nasa.gov/}wsDataFile
//
function pds_nasa_gov__wsDataFile_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._content != null) {
      xml = xml + '<content>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._content);
      xml = xml + '</content>';
     }
    }
    // block for local variables
    {
     if (this._dataHandler != null) {
      xml = xml + '<dataHandler>';
      xml = xml + cxfjsutils.packageMtom(this._dataHandler);
      xml = xml + '</dataHandler>';
     }
    }
    // block for local variables
    {
     if (this._filename != null) {
      xml = xml + '<filename>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._filename);
      xml = xml + '</filename>';
     }
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__wsDataFile.prototype.serialize = pds_nasa_gov__wsDataFile_serialize;

function pds_nasa_gov__wsDataFile_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__wsDataFile();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing content');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'content')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setContent(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing dataHandler');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'dataHandler')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = cxfjsutils.deserializeBase64orMom(curElement);
     }
     newobject.setDataHandler(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing filename');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'filename')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setFilename(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getMissionsInfo
//
function pds_nasa_gov__getMissionsInfo () {
    this.typeMarker = 'pds_nasa_gov__getMissionsInfo';
    this._page = null;
    this._restriction = null;
}

//
// accessor is pds_nasa_gov__getMissionsInfo.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__getMissionsInfo.prototype.setPage
//
function pds_nasa_gov__getMissionsInfo_getPage() { return this._page;}

pds_nasa_gov__getMissionsInfo.prototype.getPage = pds_nasa_gov__getMissionsInfo_getPage;

function pds_nasa_gov__getMissionsInfo_setPage(value) { this._page = value;}

pds_nasa_gov__getMissionsInfo.prototype.setPage = pds_nasa_gov__getMissionsInfo_setPage;
//
// accessor is pds_nasa_gov__getMissionsInfo.prototype.getRestriction
// element get for restriction
// - element type is {http://pds.nasa.gov/}restriction
// - optional element
//
// element set for restriction
// setter function is is pds_nasa_gov__getMissionsInfo.prototype.setRestriction
//
function pds_nasa_gov__getMissionsInfo_getRestriction() { return this._restriction;}

pds_nasa_gov__getMissionsInfo.prototype.getRestriction = pds_nasa_gov__getMissionsInfo_getRestriction;

function pds_nasa_gov__getMissionsInfo_setRestriction(value) { this._restriction = value;}

pds_nasa_gov__getMissionsInfo.prototype.setRestriction = pds_nasa_gov__getMissionsInfo_setRestriction;
//
// Serialize {http://pds.nasa.gov/}getMissionsInfo
//
function pds_nasa_gov__getMissionsInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    // block for local variables
    {
     if (this._restriction != null) {
      xml = xml + this._restriction.serialize(cxfjsutils, 'restriction', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getMissionsInfo.prototype.serialize = pds_nasa_gov__getMissionsInfo_serialize;

function pds_nasa_gov__getMissionsInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getMissionsInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restriction');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'restriction')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__restriction_deserialize(cxfjsutils, curElement);
     }
     newobject.setRestriction(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}identifiableEntity
//
function pds_nasa_gov__identifiableEntity () {
    this.typeMarker = 'pds_nasa_gov__identifiableEntity';
    this._id = 0;
}

//
// accessor is pds_nasa_gov__identifiableEntity.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__identifiableEntity.prototype.setId
//
function pds_nasa_gov__identifiableEntity_getId() { return this._id;}

pds_nasa_gov__identifiableEntity.prototype.getId = pds_nasa_gov__identifiableEntity_getId;

function pds_nasa_gov__identifiableEntity_setId(value) { this._id = value;}

pds_nasa_gov__identifiableEntity.prototype.setId = pds_nasa_gov__identifiableEntity_setId;
//
// Serialize {http://pds.nasa.gov/}identifiableEntity
//
function pds_nasa_gov__identifiableEntity_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__identifiableEntity.prototype.serialize = pds_nasa_gov__identifiableEntity_serialize;

function pds_nasa_gov__identifiableEntity_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__identifiableEntity();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getImagesInfo
//
function pds_nasa_gov__getImagesInfo () {
    this.typeMarker = 'pds_nasa_gov__getImagesInfo';
    this._page = null;
    this._restriction = null;
}

//
// accessor is pds_nasa_gov__getImagesInfo.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__getImagesInfo.prototype.setPage
//
function pds_nasa_gov__getImagesInfo_getPage() { return this._page;}

pds_nasa_gov__getImagesInfo.prototype.getPage = pds_nasa_gov__getImagesInfo_getPage;

function pds_nasa_gov__getImagesInfo_setPage(value) { this._page = value;}

pds_nasa_gov__getImagesInfo.prototype.setPage = pds_nasa_gov__getImagesInfo_setPage;
//
// accessor is pds_nasa_gov__getImagesInfo.prototype.getRestriction
// element get for restriction
// - element type is {http://pds.nasa.gov/}restriction
// - optional element
//
// element set for restriction
// setter function is is pds_nasa_gov__getImagesInfo.prototype.setRestriction
//
function pds_nasa_gov__getImagesInfo_getRestriction() { return this._restriction;}

pds_nasa_gov__getImagesInfo.prototype.getRestriction = pds_nasa_gov__getImagesInfo_getRestriction;

function pds_nasa_gov__getImagesInfo_setRestriction(value) { this._restriction = value;}

pds_nasa_gov__getImagesInfo.prototype.setRestriction = pds_nasa_gov__getImagesInfo_setRestriction;
//
// Serialize {http://pds.nasa.gov/}getImagesInfo
//
function pds_nasa_gov__getImagesInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    // block for local variables
    {
     if (this._restriction != null) {
      xml = xml + this._restriction.serialize(cxfjsutils, 'restriction', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getImagesInfo.prototype.serialize = pds_nasa_gov__getImagesInfo_serialize;

function pds_nasa_gov__getImagesInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getImagesInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restriction');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'restriction')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__restriction_deserialize(cxfjsutils, curElement);
     }
     newobject.setRestriction(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getImagesInfoResponse
//
function pds_nasa_gov__getImagesInfoResponse () {
    this.typeMarker = 'pds_nasa_gov__getImagesInfoResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getImagesInfoResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getImagesInfoResponse.prototype.setReturn
//
function pds_nasa_gov__getImagesInfoResponse_getReturn() { return this._return;}

pds_nasa_gov__getImagesInfoResponse.prototype.getReturn = pds_nasa_gov__getImagesInfoResponse_getReturn;

function pds_nasa_gov__getImagesInfoResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getImagesInfoResponse.prototype.setReturn = pds_nasa_gov__getImagesInfoResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getImagesInfoResponse
//
function pds_nasa_gov__getImagesInfoResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getImagesInfoResponse.prototype.serialize = pds_nasa_gov__getImagesInfoResponse_serialize;

function pds_nasa_gov__getImagesInfoResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getImagesInfoResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchDataSetsByCriteriaResponse
//
function pds_nasa_gov__searchDataSetsByCriteriaResponse () {
    this.typeMarker = 'pds_nasa_gov__searchDataSetsByCriteriaResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__searchDataSetsByCriteriaResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__searchDataSetsByCriteriaResponse.prototype.setReturn
//
function pds_nasa_gov__searchDataSetsByCriteriaResponse_getReturn() { return this._return;}

pds_nasa_gov__searchDataSetsByCriteriaResponse.prototype.getReturn = pds_nasa_gov__searchDataSetsByCriteriaResponse_getReturn;

function pds_nasa_gov__searchDataSetsByCriteriaResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__searchDataSetsByCriteriaResponse.prototype.setReturn = pds_nasa_gov__searchDataSetsByCriteriaResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}searchDataSetsByCriteriaResponse
//
function pds_nasa_gov__searchDataSetsByCriteriaResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchDataSetsByCriteriaResponse.prototype.serialize = pds_nasa_gov__searchDataSetsByCriteriaResponse_serialize;

function pds_nasa_gov__searchDataSetsByCriteriaResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchDataSetsByCriteriaResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}page
//
function pds_nasa_gov__page () {
    this.typeMarker = 'pds_nasa_gov__page';
    this._itemsPerPage = 0;
    this._pageNumber = 0;
}

//
// accessor is pds_nasa_gov__page.prototype.getItemsPerPage
// element get for itemsPerPage
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - required element
//
// element set for itemsPerPage
// setter function is is pds_nasa_gov__page.prototype.setItemsPerPage
//
function pds_nasa_gov__page_getItemsPerPage() { return this._itemsPerPage;}

pds_nasa_gov__page.prototype.getItemsPerPage = pds_nasa_gov__page_getItemsPerPage;

function pds_nasa_gov__page_setItemsPerPage(value) { this._itemsPerPage = value;}

pds_nasa_gov__page.prototype.setItemsPerPage = pds_nasa_gov__page_setItemsPerPage;
//
// accessor is pds_nasa_gov__page.prototype.getPageNumber
// element get for pageNumber
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - required element
//
// element set for pageNumber
// setter function is is pds_nasa_gov__page.prototype.setPageNumber
//
function pds_nasa_gov__page_getPageNumber() { return this._pageNumber;}

pds_nasa_gov__page.prototype.getPageNumber = pds_nasa_gov__page_getPageNumber;

function pds_nasa_gov__page_setPageNumber(value) { this._pageNumber = value;}

pds_nasa_gov__page.prototype.setPageNumber = pds_nasa_gov__page_setPageNumber;
//
// Serialize {http://pds.nasa.gov/}page
//
function pds_nasa_gov__page_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<itemsPerPage>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._itemsPerPage);
     xml = xml + '</itemsPerPage>';
    }
    // block for local variables
    {
     xml = xml + '<pageNumber>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._pageNumber);
     xml = xml + '</pageNumber>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__page.prototype.serialize = pds_nasa_gov__page_serialize;

function pds_nasa_gov__page_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__page();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing itemsPerPage');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setItemsPerPage(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing pageNumber');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setPageNumber(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getInstrumentsInfo
//
function pds_nasa_gov__getInstrumentsInfo () {
    this.typeMarker = 'pds_nasa_gov__getInstrumentsInfo';
    this._page = null;
    this._restriction = null;
}

//
// accessor is pds_nasa_gov__getInstrumentsInfo.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__getInstrumentsInfo.prototype.setPage
//
function pds_nasa_gov__getInstrumentsInfo_getPage() { return this._page;}

pds_nasa_gov__getInstrumentsInfo.prototype.getPage = pds_nasa_gov__getInstrumentsInfo_getPage;

function pds_nasa_gov__getInstrumentsInfo_setPage(value) { this._page = value;}

pds_nasa_gov__getInstrumentsInfo.prototype.setPage = pds_nasa_gov__getInstrumentsInfo_setPage;
//
// accessor is pds_nasa_gov__getInstrumentsInfo.prototype.getRestriction
// element get for restriction
// - element type is {http://pds.nasa.gov/}restriction
// - optional element
//
// element set for restriction
// setter function is is pds_nasa_gov__getInstrumentsInfo.prototype.setRestriction
//
function pds_nasa_gov__getInstrumentsInfo_getRestriction() { return this._restriction;}

pds_nasa_gov__getInstrumentsInfo.prototype.getRestriction = pds_nasa_gov__getInstrumentsInfo_getRestriction;

function pds_nasa_gov__getInstrumentsInfo_setRestriction(value) { this._restriction = value;}

pds_nasa_gov__getInstrumentsInfo.prototype.setRestriction = pds_nasa_gov__getInstrumentsInfo_setRestriction;
//
// Serialize {http://pds.nasa.gov/}getInstrumentsInfo
//
function pds_nasa_gov__getInstrumentsInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    // block for local variables
    {
     if (this._restriction != null) {
      xml = xml + this._restriction.serialize(cxfjsutils, 'restriction', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getInstrumentsInfo.prototype.serialize = pds_nasa_gov__getInstrumentsInfo_serialize;

function pds_nasa_gov__getInstrumentsInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getInstrumentsInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restriction');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'restriction')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__restriction_deserialize(cxfjsutils, curElement);
     }
     newobject.setRestriction(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataFile
//
function pds_nasa_gov__getDataFile () {
    this.typeMarker = 'pds_nasa_gov__getDataFile';
    this._id = 0;
}

//
// accessor is pds_nasa_gov__getDataFile.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__getDataFile.prototype.setId
//
function pds_nasa_gov__getDataFile_getId() { return this._id;}

pds_nasa_gov__getDataFile.prototype.getId = pds_nasa_gov__getDataFile_getId;

function pds_nasa_gov__getDataFile_setId(value) { this._id = value;}

pds_nasa_gov__getDataFile.prototype.setId = pds_nasa_gov__getDataFile_setId;
//
// Serialize {http://pds.nasa.gov/}getDataFile
//
function pds_nasa_gov__getDataFile_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataFile.prototype.serialize = pds_nasa_gov__getDataFile_serialize;

function pds_nasa_gov__getDataFile_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataFile();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTarget
//
function pds_nasa_gov__getTarget () {
    this.typeMarker = 'pds_nasa_gov__getTarget';
    this._id = 0;
}

//
// accessor is pds_nasa_gov__getTarget.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__getTarget.prototype.setId
//
function pds_nasa_gov__getTarget_getId() { return this._id;}

pds_nasa_gov__getTarget.prototype.getId = pds_nasa_gov__getTarget_getId;

function pds_nasa_gov__getTarget_setId(value) { this._id = value;}

pds_nasa_gov__getTarget.prototype.setId = pds_nasa_gov__getTarget_setId;
//
// Serialize {http://pds.nasa.gov/}getTarget
//
function pds_nasa_gov__getTarget_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTarget.prototype.serialize = pds_nasa_gov__getTarget_serialize;

function pds_nasa_gov__getTarget_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTarget();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}target
//
function pds_nasa_gov__target () {
    this.typeMarker = 'pds_nasa_gov__target';
    this._id = 0;
    this._name = null;
    this._references = [];
    this._types = [];
}

//
// accessor is pds_nasa_gov__target.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__target.prototype.setId
//
function pds_nasa_gov__target_getId() { return this._id;}

pds_nasa_gov__target.prototype.getId = pds_nasa_gov__target_getId;

function pds_nasa_gov__target_setId(value) { this._id = value;}

pds_nasa_gov__target.prototype.setId = pds_nasa_gov__target_setId;
//
// accessor is pds_nasa_gov__target.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__target.prototype.setName
//
function pds_nasa_gov__target_getName() { return this._name;}

pds_nasa_gov__target.prototype.getName = pds_nasa_gov__target_getName;

function pds_nasa_gov__target_setName(value) { this._name = value;}

pds_nasa_gov__target.prototype.setName = pds_nasa_gov__target_setName;
//
// accessor is pds_nasa_gov__target.prototype.getReferences
// element get for references
// - element type is {http://pds.nasa.gov/}reference
// - required element
// - array
// - nillable
//
// element set for references
// setter function is is pds_nasa_gov__target.prototype.setReferences
//
function pds_nasa_gov__target_getReferences() { return this._references;}

pds_nasa_gov__target.prototype.getReferences = pds_nasa_gov__target_getReferences;

function pds_nasa_gov__target_setReferences(value) { this._references = value;}

pds_nasa_gov__target.prototype.setReferences = pds_nasa_gov__target_setReferences;
//
// accessor is pds_nasa_gov__target.prototype.getTypes
// element get for types
// - element type is {http://pds.nasa.gov/}targetType
// - required element
// - array
// - nillable
//
// element set for types
// setter function is is pds_nasa_gov__target.prototype.setTypes
//
function pds_nasa_gov__target_getTypes() { return this._types;}

pds_nasa_gov__target.prototype.getTypes = pds_nasa_gov__target_getTypes;

function pds_nasa_gov__target_setTypes(value) { this._types = value;}

pds_nasa_gov__target.prototype.setTypes = pds_nasa_gov__target_setTypes;
//
// Serialize {http://pds.nasa.gov/}target
//
function pds_nasa_gov__target_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._references != null) {
      for (var ax = 0;ax < this._references.length;ax ++) {
       if (this._references[ax] == null) {
        xml = xml + '<references xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._references[ax].serialize(cxfjsutils, 'references', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._types != null) {
      for (var ax = 0;ax < this._types.length;ax ++) {
       if (this._types[ax] == null) {
        xml = xml + '<types xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._types[ax].serialize(cxfjsutils, 'types', null);
       }
      }
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__target.prototype.serialize = pds_nasa_gov__target_serialize;

function pds_nasa_gov__target_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__target();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing references');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__reference_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references'));
     newobject.setReferences(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing types');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'types')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__targetType_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'types'));
     newobject.setTypes(item);
     var item = null;
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}volume
//
function pds_nasa_gov__volume () {
    this.typeMarker = 'pds_nasa_gov__volume';
    this._id = 0;
    this._name = null;
    this._description = null;
    this._otherChildren = [];
    this._otherProperties = [];
    this._seriesName = null;
    this._setName = null;
    this._setTextId = null;
    this._textId = null;
}

//
// accessor is pds_nasa_gov__volume.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__volume.prototype.setId
//
function pds_nasa_gov__volume_getId() { return this._id;}

pds_nasa_gov__volume.prototype.getId = pds_nasa_gov__volume_getId;

function pds_nasa_gov__volume_setId(value) { this._id = value;}

pds_nasa_gov__volume.prototype.setId = pds_nasa_gov__volume_setId;
//
// accessor is pds_nasa_gov__volume.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__volume.prototype.setName
//
function pds_nasa_gov__volume_getName() { return this._name;}

pds_nasa_gov__volume.prototype.getName = pds_nasa_gov__volume_getName;

function pds_nasa_gov__volume_setName(value) { this._name = value;}

pds_nasa_gov__volume.prototype.setName = pds_nasa_gov__volume_setName;
//
// accessor is pds_nasa_gov__volume.prototype.getDescription
// element get for description
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for description
// setter function is is pds_nasa_gov__volume.prototype.setDescription
//
function pds_nasa_gov__volume_getDescription() { return this._description;}

pds_nasa_gov__volume.prototype.getDescription = pds_nasa_gov__volume_getDescription;

function pds_nasa_gov__volume_setDescription(value) { this._description = value;}

pds_nasa_gov__volume.prototype.setDescription = pds_nasa_gov__volume_setDescription;
//
// accessor is pds_nasa_gov__volume.prototype.getOtherChildren
// element get for otherChildren
// - element type is {http://pds.nasa.gov/}metadataObject
// - required element
// - array
// - nillable
//
// element set for otherChildren
// setter function is is pds_nasa_gov__volume.prototype.setOtherChildren
//
function pds_nasa_gov__volume_getOtherChildren() { return this._otherChildren;}

pds_nasa_gov__volume.prototype.getOtherChildren = pds_nasa_gov__volume_getOtherChildren;

function pds_nasa_gov__volume_setOtherChildren(value) { this._otherChildren = value;}

pds_nasa_gov__volume.prototype.setOtherChildren = pds_nasa_gov__volume_setOtherChildren;
//
// accessor is pds_nasa_gov__volume.prototype.getOtherProperties
// element get for otherProperties
// - element type is {http://pds.nasa.gov/}property
// - required element
// - array
// - nillable
//
// element set for otherProperties
// setter function is is pds_nasa_gov__volume.prototype.setOtherProperties
//
function pds_nasa_gov__volume_getOtherProperties() { return this._otherProperties;}

pds_nasa_gov__volume.prototype.getOtherProperties = pds_nasa_gov__volume_getOtherProperties;

function pds_nasa_gov__volume_setOtherProperties(value) { this._otherProperties = value;}

pds_nasa_gov__volume.prototype.setOtherProperties = pds_nasa_gov__volume_setOtherProperties;
//
// accessor is pds_nasa_gov__volume.prototype.getSeriesName
// element get for seriesName
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for seriesName
// setter function is is pds_nasa_gov__volume.prototype.setSeriesName
//
function pds_nasa_gov__volume_getSeriesName() { return this._seriesName;}

pds_nasa_gov__volume.prototype.getSeriesName = pds_nasa_gov__volume_getSeriesName;

function pds_nasa_gov__volume_setSeriesName(value) { this._seriesName = value;}

pds_nasa_gov__volume.prototype.setSeriesName = pds_nasa_gov__volume_setSeriesName;
//
// accessor is pds_nasa_gov__volume.prototype.getSetName
// element get for setName
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for setName
// setter function is is pds_nasa_gov__volume.prototype.setSetName
//
function pds_nasa_gov__volume_getSetName() { return this._setName;}

pds_nasa_gov__volume.prototype.getSetName = pds_nasa_gov__volume_getSetName;

function pds_nasa_gov__volume_setSetName(value) { this._setName = value;}

pds_nasa_gov__volume.prototype.setSetName = pds_nasa_gov__volume_setSetName;
//
// accessor is pds_nasa_gov__volume.prototype.getSetTextId
// element get for setTextId
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for setTextId
// setter function is is pds_nasa_gov__volume.prototype.setSetTextId
//
function pds_nasa_gov__volume_getSetTextId() { return this._setTextId;}

pds_nasa_gov__volume.prototype.getSetTextId = pds_nasa_gov__volume_getSetTextId;

function pds_nasa_gov__volume_setSetTextId(value) { this._setTextId = value;}

pds_nasa_gov__volume.prototype.setSetTextId = pds_nasa_gov__volume_setSetTextId;
//
// accessor is pds_nasa_gov__volume.prototype.getTextId
// element get for textId
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for textId
// setter function is is pds_nasa_gov__volume.prototype.setTextId
//
function pds_nasa_gov__volume_getTextId() { return this._textId;}

pds_nasa_gov__volume.prototype.getTextId = pds_nasa_gov__volume_getTextId;

function pds_nasa_gov__volume_setTextId(value) { this._textId = value;}

pds_nasa_gov__volume.prototype.setTextId = pds_nasa_gov__volume_setTextId;
//
// Serialize {http://pds.nasa.gov/}volume
//
function pds_nasa_gov__volume_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._description != null) {
      xml = xml + '<description>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._description);
      xml = xml + '</description>';
     }
    }
    // block for local variables
    {
     if (this._otherChildren != null) {
      for (var ax = 0;ax < this._otherChildren.length;ax ++) {
       if (this._otherChildren[ax] == null) {
        xml = xml + '<otherChildren xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._otherChildren[ax].serialize(cxfjsutils, 'otherChildren', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._otherProperties != null) {
      for (var ax = 0;ax < this._otherProperties.length;ax ++) {
       if (this._otherProperties[ax] == null) {
        xml = xml + '<otherProperties xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._otherProperties[ax].serialize(cxfjsutils, 'otherProperties', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._seriesName != null) {
      xml = xml + '<seriesName>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._seriesName);
      xml = xml + '</seriesName>';
     }
    }
    // block for local variables
    {
     if (this._setName != null) {
      xml = xml + '<setName>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._setName);
      xml = xml + '</setName>';
     }
    }
    // block for local variables
    {
     if (this._setTextId != null) {
      xml = xml + '<setTextId>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._setTextId);
      xml = xml + '</setTextId>';
     }
    }
    // block for local variables
    {
     if (this._textId != null) {
      xml = xml + '<textId>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._textId);
      xml = xml + '</textId>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__volume.prototype.serialize = pds_nasa_gov__volume_serialize;

function pds_nasa_gov__volume_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__volume();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing description');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'description')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setDescription(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing otherChildren');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__metadataObject_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren'));
     newobject.setOtherChildren(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing otherProperties');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherProperties')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__property_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherProperties'));
     newobject.setOtherProperties(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing seriesName');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'seriesName')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setSeriesName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing setName');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'setName')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setSetName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing setTextId');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'setTextId')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setSetTextId(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing textId');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'textId')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setTextId(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataSetResponse
//
function pds_nasa_gov__getDataSetResponse () {
    this.typeMarker = 'pds_nasa_gov__getDataSetResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getDataSetResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}dataSet
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getDataSetResponse.prototype.setReturn
//
function pds_nasa_gov__getDataSetResponse_getReturn() { return this._return;}

pds_nasa_gov__getDataSetResponse.prototype.getReturn = pds_nasa_gov__getDataSetResponse_getReturn;

function pds_nasa_gov__getDataSetResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getDataSetResponse.prototype.setReturn = pds_nasa_gov__getDataSetResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getDataSetResponse
//
function pds_nasa_gov__getDataSetResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataSetResponse.prototype.serialize = pds_nasa_gov__getDataSetResponse_serialize;

function pds_nasa_gov__getDataSetResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataSetResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__dataSet_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getMissionResponse
//
function pds_nasa_gov__getMissionResponse () {
    this.typeMarker = 'pds_nasa_gov__getMissionResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getMissionResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}mission
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getMissionResponse.prototype.setReturn
//
function pds_nasa_gov__getMissionResponse_getReturn() { return this._return;}

pds_nasa_gov__getMissionResponse.prototype.getReturn = pds_nasa_gov__getMissionResponse_getReturn;

function pds_nasa_gov__getMissionResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getMissionResponse.prototype.setReturn = pds_nasa_gov__getMissionResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getMissionResponse
//
function pds_nasa_gov__getMissionResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getMissionResponse.prototype.serialize = pds_nasa_gov__getMissionResponse_serialize;

function pds_nasa_gov__getMissionResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getMissionResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__mission_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getInstrumentResponse
//
function pds_nasa_gov__getInstrumentResponse () {
    this.typeMarker = 'pds_nasa_gov__getInstrumentResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getInstrumentResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}instrument
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getInstrumentResponse.prototype.setReturn
//
function pds_nasa_gov__getInstrumentResponse_getReturn() { return this._return;}

pds_nasa_gov__getInstrumentResponse.prototype.getReturn = pds_nasa_gov__getInstrumentResponse_getReturn;

function pds_nasa_gov__getInstrumentResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getInstrumentResponse.prototype.setReturn = pds_nasa_gov__getInstrumentResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getInstrumentResponse
//
function pds_nasa_gov__getInstrumentResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getInstrumentResponse.prototype.serialize = pds_nasa_gov__getInstrumentResponse_serialize;

function pds_nasa_gov__getInstrumentResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getInstrumentResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__instrument_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataSetsInfoResponse
//
function pds_nasa_gov__getDataSetsInfoResponse () {
    this.typeMarker = 'pds_nasa_gov__getDataSetsInfoResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getDataSetsInfoResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getDataSetsInfoResponse.prototype.setReturn
//
function pds_nasa_gov__getDataSetsInfoResponse_getReturn() { return this._return;}

pds_nasa_gov__getDataSetsInfoResponse.prototype.getReturn = pds_nasa_gov__getDataSetsInfoResponse_getReturn;

function pds_nasa_gov__getDataSetsInfoResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getDataSetsInfoResponse.prototype.setReturn = pds_nasa_gov__getDataSetsInfoResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getDataSetsInfoResponse
//
function pds_nasa_gov__getDataSetsInfoResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataSetsInfoResponse.prototype.serialize = pds_nasa_gov__getDataSetsInfoResponse_serialize;

function pds_nasa_gov__getDataSetsInfoResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataSetsInfoResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchResults
//
function pds_nasa_gov__searchResults () {
    this.typeMarker = 'pds_nasa_gov__searchResults';
    this._dataFiles = null;
    this._datasets = null;
    this._instruments = null;
    this._missions = null;
    this._targetTypes = null;
    this._targets = null;
}

//
// accessor is pds_nasa_gov__searchResults.prototype.getDataFiles
// element get for dataFiles
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for dataFiles
// setter function is is pds_nasa_gov__searchResults.prototype.setDataFiles
//
function pds_nasa_gov__searchResults_getDataFiles() { return this._dataFiles;}

pds_nasa_gov__searchResults.prototype.getDataFiles = pds_nasa_gov__searchResults_getDataFiles;

function pds_nasa_gov__searchResults_setDataFiles(value) { this._dataFiles = value;}

pds_nasa_gov__searchResults.prototype.setDataFiles = pds_nasa_gov__searchResults_setDataFiles;
//
// accessor is pds_nasa_gov__searchResults.prototype.getDatasets
// element get for datasets
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for datasets
// setter function is is pds_nasa_gov__searchResults.prototype.setDatasets
//
function pds_nasa_gov__searchResults_getDatasets() { return this._datasets;}

pds_nasa_gov__searchResults.prototype.getDatasets = pds_nasa_gov__searchResults_getDatasets;

function pds_nasa_gov__searchResults_setDatasets(value) { this._datasets = value;}

pds_nasa_gov__searchResults.prototype.setDatasets = pds_nasa_gov__searchResults_setDatasets;
//
// accessor is pds_nasa_gov__searchResults.prototype.getInstruments
// element get for instruments
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for instruments
// setter function is is pds_nasa_gov__searchResults.prototype.setInstruments
//
function pds_nasa_gov__searchResults_getInstruments() { return this._instruments;}

pds_nasa_gov__searchResults.prototype.getInstruments = pds_nasa_gov__searchResults_getInstruments;

function pds_nasa_gov__searchResults_setInstruments(value) { this._instruments = value;}

pds_nasa_gov__searchResults.prototype.setInstruments = pds_nasa_gov__searchResults_setInstruments;
//
// accessor is pds_nasa_gov__searchResults.prototype.getMissions
// element get for missions
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for missions
// setter function is is pds_nasa_gov__searchResults.prototype.setMissions
//
function pds_nasa_gov__searchResults_getMissions() { return this._missions;}

pds_nasa_gov__searchResults.prototype.getMissions = pds_nasa_gov__searchResults_getMissions;

function pds_nasa_gov__searchResults_setMissions(value) { this._missions = value;}

pds_nasa_gov__searchResults.prototype.setMissions = pds_nasa_gov__searchResults_setMissions;
//
// accessor is pds_nasa_gov__searchResults.prototype.getTargetTypes
// element get for targetTypes
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for targetTypes
// setter function is is pds_nasa_gov__searchResults.prototype.setTargetTypes
//
function pds_nasa_gov__searchResults_getTargetTypes() { return this._targetTypes;}

pds_nasa_gov__searchResults.prototype.getTargetTypes = pds_nasa_gov__searchResults_getTargetTypes;

function pds_nasa_gov__searchResults_setTargetTypes(value) { this._targetTypes = value;}

pds_nasa_gov__searchResults.prototype.setTargetTypes = pds_nasa_gov__searchResults_setTargetTypes;
//
// accessor is pds_nasa_gov__searchResults.prototype.getTargets
// element get for targets
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for targets
// setter function is is pds_nasa_gov__searchResults.prototype.setTargets
//
function pds_nasa_gov__searchResults_getTargets() { return this._targets;}

pds_nasa_gov__searchResults.prototype.getTargets = pds_nasa_gov__searchResults_getTargets;

function pds_nasa_gov__searchResults_setTargets(value) { this._targets = value;}

pds_nasa_gov__searchResults.prototype.setTargets = pds_nasa_gov__searchResults_setTargets;
//
// Serialize {http://pds.nasa.gov/}searchResults
//
function pds_nasa_gov__searchResults_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._dataFiles != null) {
      xml = xml + this._dataFiles.serialize(cxfjsutils, 'dataFiles', null);
     }
    }
    // block for local variables
    {
     if (this._datasets != null) {
      xml = xml + this._datasets.serialize(cxfjsutils, 'datasets', null);
     }
    }
    // block for local variables
    {
     if (this._instruments != null) {
      xml = xml + this._instruments.serialize(cxfjsutils, 'instruments', null);
     }
    }
    // block for local variables
    {
     if (this._missions != null) {
      xml = xml + this._missions.serialize(cxfjsutils, 'missions', null);
     }
    }
    // block for local variables
    {
     if (this._targetTypes != null) {
      xml = xml + this._targetTypes.serialize(cxfjsutils, 'targetTypes', null);
     }
    }
    // block for local variables
    {
     if (this._targets != null) {
      xml = xml + this._targets.serialize(cxfjsutils, 'targets', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchResults.prototype.serialize = pds_nasa_gov__searchResults_serialize;

function pds_nasa_gov__searchResults_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchResults();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing dataFiles');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'dataFiles')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setDataFiles(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing datasets');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'datasets')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setDatasets(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing instruments');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'instruments')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setInstruments(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing missions');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'missions')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setMissions(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing targetTypes');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targetTypes')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setTargetTypes(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing targets');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targets')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setTargets(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}mission
//
function pds_nasa_gov__mission () {
    this.typeMarker = 'pds_nasa_gov__mission';
    this._id = 0;
    this._name = null;
    this._description = null;
    this._endDate = null;
    this._otherChildren = [];
    this._references = [];
    this._startDate = null;
}

//
// accessor is pds_nasa_gov__mission.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__mission.prototype.setId
//
function pds_nasa_gov__mission_getId() { return this._id;}

pds_nasa_gov__mission.prototype.getId = pds_nasa_gov__mission_getId;

function pds_nasa_gov__mission_setId(value) { this._id = value;}

pds_nasa_gov__mission.prototype.setId = pds_nasa_gov__mission_setId;
//
// accessor is pds_nasa_gov__mission.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__mission.prototype.setName
//
function pds_nasa_gov__mission_getName() { return this._name;}

pds_nasa_gov__mission.prototype.getName = pds_nasa_gov__mission_getName;

function pds_nasa_gov__mission_setName(value) { this._name = value;}

pds_nasa_gov__mission.prototype.setName = pds_nasa_gov__mission_setName;
//
// accessor is pds_nasa_gov__mission.prototype.getDescription
// element get for description
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for description
// setter function is is pds_nasa_gov__mission.prototype.setDescription
//
function pds_nasa_gov__mission_getDescription() { return this._description;}

pds_nasa_gov__mission.prototype.getDescription = pds_nasa_gov__mission_getDescription;

function pds_nasa_gov__mission_setDescription(value) { this._description = value;}

pds_nasa_gov__mission.prototype.setDescription = pds_nasa_gov__mission_setDescription;
//
// accessor is pds_nasa_gov__mission.prototype.getEndDate
// element get for endDate
// - element type is {http://www.w3.org/2001/XMLSchema}dateTime
// - optional element
//
// element set for endDate
// setter function is is pds_nasa_gov__mission.prototype.setEndDate
//
function pds_nasa_gov__mission_getEndDate() { return this._endDate;}

pds_nasa_gov__mission.prototype.getEndDate = pds_nasa_gov__mission_getEndDate;

function pds_nasa_gov__mission_setEndDate(value) { this._endDate = value;}

pds_nasa_gov__mission.prototype.setEndDate = pds_nasa_gov__mission_setEndDate;
//
// accessor is pds_nasa_gov__mission.prototype.getOtherChildren
// element get for otherChildren
// - element type is {http://pds.nasa.gov/}metadataObject
// - required element
// - array
// - nillable
//
// element set for otherChildren
// setter function is is pds_nasa_gov__mission.prototype.setOtherChildren
//
function pds_nasa_gov__mission_getOtherChildren() { return this._otherChildren;}

pds_nasa_gov__mission.prototype.getOtherChildren = pds_nasa_gov__mission_getOtherChildren;

function pds_nasa_gov__mission_setOtherChildren(value) { this._otherChildren = value;}

pds_nasa_gov__mission.prototype.setOtherChildren = pds_nasa_gov__mission_setOtherChildren;
//
// accessor is pds_nasa_gov__mission.prototype.getReferences
// element get for references
// - element type is {http://pds.nasa.gov/}reference
// - required element
// - array
// - nillable
//
// element set for references
// setter function is is pds_nasa_gov__mission.prototype.setReferences
//
function pds_nasa_gov__mission_getReferences() { return this._references;}

pds_nasa_gov__mission.prototype.getReferences = pds_nasa_gov__mission_getReferences;

function pds_nasa_gov__mission_setReferences(value) { this._references = value;}

pds_nasa_gov__mission.prototype.setReferences = pds_nasa_gov__mission_setReferences;
//
// accessor is pds_nasa_gov__mission.prototype.getStartDate
// element get for startDate
// - element type is {http://www.w3.org/2001/XMLSchema}dateTime
// - optional element
//
// element set for startDate
// setter function is is pds_nasa_gov__mission.prototype.setStartDate
//
function pds_nasa_gov__mission_getStartDate() { return this._startDate;}

pds_nasa_gov__mission.prototype.getStartDate = pds_nasa_gov__mission_getStartDate;

function pds_nasa_gov__mission_setStartDate(value) { this._startDate = value;}

pds_nasa_gov__mission.prototype.setStartDate = pds_nasa_gov__mission_setStartDate;
//
// Serialize {http://pds.nasa.gov/}mission
//
function pds_nasa_gov__mission_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._description != null) {
      xml = xml + '<description>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._description);
      xml = xml + '</description>';
     }
    }
    // block for local variables
    {
     if (this._endDate != null) {
      xml = xml + '<endDate>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._endDate);
      xml = xml + '</endDate>';
     }
    }
    // block for local variables
    {
     if (this._otherChildren != null) {
      for (var ax = 0;ax < this._otherChildren.length;ax ++) {
       if (this._otherChildren[ax] == null) {
        xml = xml + '<otherChildren xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._otherChildren[ax].serialize(cxfjsutils, 'otherChildren', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._references != null) {
      for (var ax = 0;ax < this._references.length;ax ++) {
       if (this._references[ax] == null) {
        xml = xml + '<references xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._references[ax].serialize(cxfjsutils, 'references', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._startDate != null) {
      xml = xml + '<startDate>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._startDate);
      xml = xml + '</startDate>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__mission.prototype.serialize = pds_nasa_gov__mission_serialize;

function pds_nasa_gov__mission_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__mission();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing description');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'description')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setDescription(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing endDate');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'endDate')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setEndDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing otherChildren');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__metadataObject_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren'));
     newobject.setOtherChildren(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing references');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__reference_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references'));
     newobject.setReferences(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing startDate');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'startDate')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setStartDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getMissionsInfoResponse
//
function pds_nasa_gov__getMissionsInfoResponse () {
    this.typeMarker = 'pds_nasa_gov__getMissionsInfoResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getMissionsInfoResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getMissionsInfoResponse.prototype.setReturn
//
function pds_nasa_gov__getMissionsInfoResponse_getReturn() { return this._return;}

pds_nasa_gov__getMissionsInfoResponse.prototype.getReturn = pds_nasa_gov__getMissionsInfoResponse_getReturn;

function pds_nasa_gov__getMissionsInfoResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getMissionsInfoResponse.prototype.setReturn = pds_nasa_gov__getMissionsInfoResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getMissionsInfoResponse
//
function pds_nasa_gov__getMissionsInfoResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getMissionsInfoResponse.prototype.serialize = pds_nasa_gov__getMissionsInfoResponse_serialize;

function pds_nasa_gov__getMissionsInfoResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getMissionsInfoResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTargetTypeResponse
//
function pds_nasa_gov__getTargetTypeResponse () {
    this.typeMarker = 'pds_nasa_gov__getTargetTypeResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getTargetTypeResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}targetType
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getTargetTypeResponse.prototype.setReturn
//
function pds_nasa_gov__getTargetTypeResponse_getReturn() { return this._return;}

pds_nasa_gov__getTargetTypeResponse.prototype.getReturn = pds_nasa_gov__getTargetTypeResponse_getReturn;

function pds_nasa_gov__getTargetTypeResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getTargetTypeResponse.prototype.setReturn = pds_nasa_gov__getTargetTypeResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getTargetTypeResponse
//
function pds_nasa_gov__getTargetTypeResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTargetTypeResponse.prototype.serialize = pds_nasa_gov__getTargetTypeResponse_serialize;

function pds_nasa_gov__getTargetTypeResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTargetTypeResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__targetType_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchEntitiesByType
//
function pds_nasa_gov__searchEntitiesByType () {
    this.typeMarker = 'pds_nasa_gov__searchEntitiesByType';
    this._entityType = '';
    this._searchText = '';
    this._page = null;
    this._restriction = null;
}

//
// accessor is pds_nasa_gov__searchEntitiesByType.prototype.getEntityType
// element get for entityType
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
//
// element set for entityType
// setter function is is pds_nasa_gov__searchEntitiesByType.prototype.setEntityType
//
function pds_nasa_gov__searchEntitiesByType_getEntityType() { return this._entityType;}

pds_nasa_gov__searchEntitiesByType.prototype.getEntityType = pds_nasa_gov__searchEntitiesByType_getEntityType;

function pds_nasa_gov__searchEntitiesByType_setEntityType(value) { this._entityType = value;}

pds_nasa_gov__searchEntitiesByType.prototype.setEntityType = pds_nasa_gov__searchEntitiesByType_setEntityType;
//
// accessor is pds_nasa_gov__searchEntitiesByType.prototype.getSearchText
// element get for searchText
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
//
// element set for searchText
// setter function is is pds_nasa_gov__searchEntitiesByType.prototype.setSearchText
//
function pds_nasa_gov__searchEntitiesByType_getSearchText() { return this._searchText;}

pds_nasa_gov__searchEntitiesByType.prototype.getSearchText = pds_nasa_gov__searchEntitiesByType_getSearchText;

function pds_nasa_gov__searchEntitiesByType_setSearchText(value) { this._searchText = value;}

pds_nasa_gov__searchEntitiesByType.prototype.setSearchText = pds_nasa_gov__searchEntitiesByType_setSearchText;
//
// accessor is pds_nasa_gov__searchEntitiesByType.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__searchEntitiesByType.prototype.setPage
//
function pds_nasa_gov__searchEntitiesByType_getPage() { return this._page;}

pds_nasa_gov__searchEntitiesByType.prototype.getPage = pds_nasa_gov__searchEntitiesByType_getPage;

function pds_nasa_gov__searchEntitiesByType_setPage(value) { this._page = value;}

pds_nasa_gov__searchEntitiesByType.prototype.setPage = pds_nasa_gov__searchEntitiesByType_setPage;
//
// accessor is pds_nasa_gov__searchEntitiesByType.prototype.getRestriction
// element get for restriction
// - element type is {http://pds.nasa.gov/}restriction
// - optional element
//
// element set for restriction
// setter function is is pds_nasa_gov__searchEntitiesByType.prototype.setRestriction
//
function pds_nasa_gov__searchEntitiesByType_getRestriction() { return this._restriction;}

pds_nasa_gov__searchEntitiesByType.prototype.getRestriction = pds_nasa_gov__searchEntitiesByType_getRestriction;

function pds_nasa_gov__searchEntitiesByType_setRestriction(value) { this._restriction = value;}

pds_nasa_gov__searchEntitiesByType.prototype.setRestriction = pds_nasa_gov__searchEntitiesByType_setRestriction;
//
// Serialize {http://pds.nasa.gov/}searchEntitiesByType
//
function pds_nasa_gov__searchEntitiesByType_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<entityType>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._entityType);
     xml = xml + '</entityType>';
    }
    // block for local variables
    {
     xml = xml + '<searchText>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._searchText);
     xml = xml + '</searchText>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    // block for local variables
    {
     if (this._restriction != null) {
      xml = xml + this._restriction.serialize(cxfjsutils, 'restriction', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchEntitiesByType.prototype.serialize = pds_nasa_gov__searchEntitiesByType_serialize;

function pds_nasa_gov__searchEntitiesByType_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchEntitiesByType();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing entityType');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = value;
    }
    newobject.setEntityType(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing searchText');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = value;
    }
    newobject.setSearchText(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restriction');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'restriction')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__restriction_deserialize(cxfjsutils, curElement);
     }
     newobject.setRestriction(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}instrument
//
function pds_nasa_gov__instrument () {
    this.typeMarker = 'pds_nasa_gov__instrument';
    this._id = 0;
    this._name = null;
    this._description = null;
    this._hosts = [];
    this._otherChildren = [];
    this._references = [];
    this._textId = null;
    this._type = null;
}

//
// accessor is pds_nasa_gov__instrument.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__instrument.prototype.setId
//
function pds_nasa_gov__instrument_getId() { return this._id;}

pds_nasa_gov__instrument.prototype.getId = pds_nasa_gov__instrument_getId;

function pds_nasa_gov__instrument_setId(value) { this._id = value;}

pds_nasa_gov__instrument.prototype.setId = pds_nasa_gov__instrument_setId;
//
// accessor is pds_nasa_gov__instrument.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__instrument.prototype.setName
//
function pds_nasa_gov__instrument_getName() { return this._name;}

pds_nasa_gov__instrument.prototype.getName = pds_nasa_gov__instrument_getName;

function pds_nasa_gov__instrument_setName(value) { this._name = value;}

pds_nasa_gov__instrument.prototype.setName = pds_nasa_gov__instrument_setName;
//
// accessor is pds_nasa_gov__instrument.prototype.getDescription
// element get for description
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for description
// setter function is is pds_nasa_gov__instrument.prototype.setDescription
//
function pds_nasa_gov__instrument_getDescription() { return this._description;}

pds_nasa_gov__instrument.prototype.getDescription = pds_nasa_gov__instrument_getDescription;

function pds_nasa_gov__instrument_setDescription(value) { this._description = value;}

pds_nasa_gov__instrument.prototype.setDescription = pds_nasa_gov__instrument_setDescription;
//
// accessor is pds_nasa_gov__instrument.prototype.getHosts
// element get for hosts
// - element type is {http://pds.nasa.gov/}instrumentHost
// - required element
// - array
// - nillable
//
// element set for hosts
// setter function is is pds_nasa_gov__instrument.prototype.setHosts
//
function pds_nasa_gov__instrument_getHosts() { return this._hosts;}

pds_nasa_gov__instrument.prototype.getHosts = pds_nasa_gov__instrument_getHosts;

function pds_nasa_gov__instrument_setHosts(value) { this._hosts = value;}

pds_nasa_gov__instrument.prototype.setHosts = pds_nasa_gov__instrument_setHosts;
//
// accessor is pds_nasa_gov__instrument.prototype.getOtherChildren
// element get for otherChildren
// - element type is {http://pds.nasa.gov/}metadataObject
// - required element
// - array
// - nillable
//
// element set for otherChildren
// setter function is is pds_nasa_gov__instrument.prototype.setOtherChildren
//
function pds_nasa_gov__instrument_getOtherChildren() { return this._otherChildren;}

pds_nasa_gov__instrument.prototype.getOtherChildren = pds_nasa_gov__instrument_getOtherChildren;

function pds_nasa_gov__instrument_setOtherChildren(value) { this._otherChildren = value;}

pds_nasa_gov__instrument.prototype.setOtherChildren = pds_nasa_gov__instrument_setOtherChildren;
//
// accessor is pds_nasa_gov__instrument.prototype.getReferences
// element get for references
// - element type is {http://pds.nasa.gov/}reference
// - required element
// - array
// - nillable
//
// element set for references
// setter function is is pds_nasa_gov__instrument.prototype.setReferences
//
function pds_nasa_gov__instrument_getReferences() { return this._references;}

pds_nasa_gov__instrument.prototype.getReferences = pds_nasa_gov__instrument_getReferences;

function pds_nasa_gov__instrument_setReferences(value) { this._references = value;}

pds_nasa_gov__instrument.prototype.setReferences = pds_nasa_gov__instrument_setReferences;
//
// accessor is pds_nasa_gov__instrument.prototype.getTextId
// element get for textId
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for textId
// setter function is is pds_nasa_gov__instrument.prototype.setTextId
//
function pds_nasa_gov__instrument_getTextId() { return this._textId;}

pds_nasa_gov__instrument.prototype.getTextId = pds_nasa_gov__instrument_getTextId;

function pds_nasa_gov__instrument_setTextId(value) { this._textId = value;}

pds_nasa_gov__instrument.prototype.setTextId = pds_nasa_gov__instrument_setTextId;
//
// accessor is pds_nasa_gov__instrument.prototype.getType
// element get for type
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for type
// setter function is is pds_nasa_gov__instrument.prototype.setType
//
function pds_nasa_gov__instrument_getType() { return this._type;}

pds_nasa_gov__instrument.prototype.getType = pds_nasa_gov__instrument_getType;

function pds_nasa_gov__instrument_setType(value) { this._type = value;}

pds_nasa_gov__instrument.prototype.setType = pds_nasa_gov__instrument_setType;
//
// Serialize {http://pds.nasa.gov/}instrument
//
function pds_nasa_gov__instrument_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._description != null) {
      xml = xml + '<description>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._description);
      xml = xml + '</description>';
     }
    }
    // block for local variables
    {
     if (this._hosts != null) {
      for (var ax = 0;ax < this._hosts.length;ax ++) {
       if (this._hosts[ax] == null) {
        xml = xml + '<hosts xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._hosts[ax].serialize(cxfjsutils, 'hosts', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._otherChildren != null) {
      for (var ax = 0;ax < this._otherChildren.length;ax ++) {
       if (this._otherChildren[ax] == null) {
        xml = xml + '<otherChildren xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._otherChildren[ax].serialize(cxfjsutils, 'otherChildren', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._references != null) {
      for (var ax = 0;ax < this._references.length;ax ++) {
       if (this._references[ax] == null) {
        xml = xml + '<references xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._references[ax].serialize(cxfjsutils, 'references', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._textId != null) {
      xml = xml + '<textId>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._textId);
      xml = xml + '</textId>';
     }
    }
    // block for local variables
    {
     if (this._type != null) {
      xml = xml + '<type>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._type);
      xml = xml + '</type>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__instrument.prototype.serialize = pds_nasa_gov__instrument_serialize;

function pds_nasa_gov__instrument_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__instrument();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing description');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'description')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setDescription(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing hosts');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'hosts')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__instrumentHost_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'hosts'));
     newobject.setHosts(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing otherChildren');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__metadataObject_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren'));
     newobject.setOtherChildren(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing references');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__reference_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references'));
     newobject.setReferences(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing textId');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'textId')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setTextId(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing type');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'type')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setType(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}entityInfo
//
function pds_nasa_gov__entityInfo () {
    this.typeMarker = 'pds_nasa_gov__entityInfo';
    this._id = 0;
    this._name = null;
}

//
// accessor is pds_nasa_gov__entityInfo.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__entityInfo.prototype.setId
//
function pds_nasa_gov__entityInfo_getId() { return this._id;}

pds_nasa_gov__entityInfo.prototype.getId = pds_nasa_gov__entityInfo_getId;

function pds_nasa_gov__entityInfo_setId(value) { this._id = value;}

pds_nasa_gov__entityInfo.prototype.setId = pds_nasa_gov__entityInfo_setId;
//
// accessor is pds_nasa_gov__entityInfo.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__entityInfo.prototype.setName
//
function pds_nasa_gov__entityInfo_getName() { return this._name;}

pds_nasa_gov__entityInfo.prototype.getName = pds_nasa_gov__entityInfo_getName;

function pds_nasa_gov__entityInfo_setName(value) { this._name = value;}

pds_nasa_gov__entityInfo.prototype.setName = pds_nasa_gov__entityInfo_setName;
//
// Serialize {http://pds.nasa.gov/}entityInfo
//
function pds_nasa_gov__entityInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__entityInfo.prototype.serialize = pds_nasa_gov__entityInfo_serialize;

function pds_nasa_gov__entityInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__entityInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}instrumentHost
//
function pds_nasa_gov__instrumentHost () {
    this.typeMarker = 'pds_nasa_gov__instrumentHost';
    this._id = 0;
    this._name = null;
    this._otherChildren = [];
    this._references = [];
    this._textId = null;
}

//
// accessor is pds_nasa_gov__instrumentHost.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__instrumentHost.prototype.setId
//
function pds_nasa_gov__instrumentHost_getId() { return this._id;}

pds_nasa_gov__instrumentHost.prototype.getId = pds_nasa_gov__instrumentHost_getId;

function pds_nasa_gov__instrumentHost_setId(value) { this._id = value;}

pds_nasa_gov__instrumentHost.prototype.setId = pds_nasa_gov__instrumentHost_setId;
//
// accessor is pds_nasa_gov__instrumentHost.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__instrumentHost.prototype.setName
//
function pds_nasa_gov__instrumentHost_getName() { return this._name;}

pds_nasa_gov__instrumentHost.prototype.getName = pds_nasa_gov__instrumentHost_getName;

function pds_nasa_gov__instrumentHost_setName(value) { this._name = value;}

pds_nasa_gov__instrumentHost.prototype.setName = pds_nasa_gov__instrumentHost_setName;
//
// accessor is pds_nasa_gov__instrumentHost.prototype.getOtherChildren
// element get for otherChildren
// - element type is {http://pds.nasa.gov/}metadataObject
// - required element
// - array
// - nillable
//
// element set for otherChildren
// setter function is is pds_nasa_gov__instrumentHost.prototype.setOtherChildren
//
function pds_nasa_gov__instrumentHost_getOtherChildren() { return this._otherChildren;}

pds_nasa_gov__instrumentHost.prototype.getOtherChildren = pds_nasa_gov__instrumentHost_getOtherChildren;

function pds_nasa_gov__instrumentHost_setOtherChildren(value) { this._otherChildren = value;}

pds_nasa_gov__instrumentHost.prototype.setOtherChildren = pds_nasa_gov__instrumentHost_setOtherChildren;
//
// accessor is pds_nasa_gov__instrumentHost.prototype.getReferences
// element get for references
// - element type is {http://pds.nasa.gov/}reference
// - required element
// - array
// - nillable
//
// element set for references
// setter function is is pds_nasa_gov__instrumentHost.prototype.setReferences
//
function pds_nasa_gov__instrumentHost_getReferences() { return this._references;}

pds_nasa_gov__instrumentHost.prototype.getReferences = pds_nasa_gov__instrumentHost_getReferences;

function pds_nasa_gov__instrumentHost_setReferences(value) { this._references = value;}

pds_nasa_gov__instrumentHost.prototype.setReferences = pds_nasa_gov__instrumentHost_setReferences;
//
// accessor is pds_nasa_gov__instrumentHost.prototype.getTextId
// element get for textId
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for textId
// setter function is is pds_nasa_gov__instrumentHost.prototype.setTextId
//
function pds_nasa_gov__instrumentHost_getTextId() { return this._textId;}

pds_nasa_gov__instrumentHost.prototype.getTextId = pds_nasa_gov__instrumentHost_getTextId;

function pds_nasa_gov__instrumentHost_setTextId(value) { this._textId = value;}

pds_nasa_gov__instrumentHost.prototype.setTextId = pds_nasa_gov__instrumentHost_setTextId;
//
// Serialize {http://pds.nasa.gov/}instrumentHost
//
function pds_nasa_gov__instrumentHost_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._otherChildren != null) {
      for (var ax = 0;ax < this._otherChildren.length;ax ++) {
       if (this._otherChildren[ax] == null) {
        xml = xml + '<otherChildren xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._otherChildren[ax].serialize(cxfjsutils, 'otherChildren', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._references != null) {
      for (var ax = 0;ax < this._references.length;ax ++) {
       if (this._references[ax] == null) {
        xml = xml + '<references xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._references[ax].serialize(cxfjsutils, 'references', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._textId != null) {
      xml = xml + '<textId>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._textId);
      xml = xml + '</textId>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__instrumentHost.prototype.serialize = pds_nasa_gov__instrumentHost_serialize;

function pds_nasa_gov__instrumentHost_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__instrumentHost();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing otherChildren');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__metadataObject_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren'));
     newobject.setOtherChildren(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing references');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__reference_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references'));
     newobject.setReferences(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing textId');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'textId')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setTextId(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}reference
//
function pds_nasa_gov__reference () {
    this.typeMarker = 'pds_nasa_gov__reference';
    this._id = 0;
    this._description = null;
    this._keyTextId = null;
}

//
// accessor is pds_nasa_gov__reference.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__reference.prototype.setId
//
function pds_nasa_gov__reference_getId() { return this._id;}

pds_nasa_gov__reference.prototype.getId = pds_nasa_gov__reference_getId;

function pds_nasa_gov__reference_setId(value) { this._id = value;}

pds_nasa_gov__reference.prototype.setId = pds_nasa_gov__reference_setId;
//
// accessor is pds_nasa_gov__reference.prototype.getDescription
// element get for description
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for description
// setter function is is pds_nasa_gov__reference.prototype.setDescription
//
function pds_nasa_gov__reference_getDescription() { return this._description;}

pds_nasa_gov__reference.prototype.getDescription = pds_nasa_gov__reference_getDescription;

function pds_nasa_gov__reference_setDescription(value) { this._description = value;}

pds_nasa_gov__reference.prototype.setDescription = pds_nasa_gov__reference_setDescription;
//
// accessor is pds_nasa_gov__reference.prototype.getKeyTextId
// element get for keyTextId
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for keyTextId
// setter function is is pds_nasa_gov__reference.prototype.setKeyTextId
//
function pds_nasa_gov__reference_getKeyTextId() { return this._keyTextId;}

pds_nasa_gov__reference.prototype.getKeyTextId = pds_nasa_gov__reference_getKeyTextId;

function pds_nasa_gov__reference_setKeyTextId(value) { this._keyTextId = value;}

pds_nasa_gov__reference.prototype.setKeyTextId = pds_nasa_gov__reference_setKeyTextId;
//
// Serialize {http://pds.nasa.gov/}reference
//
function pds_nasa_gov__reference_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._description != null) {
      xml = xml + '<description>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._description);
      xml = xml + '</description>';
     }
    }
    // block for local variables
    {
     if (this._keyTextId != null) {
      xml = xml + '<keyTextId>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._keyTextId);
      xml = xml + '</keyTextId>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__reference.prototype.serialize = pds_nasa_gov__reference_serialize;

function pds_nasa_gov__reference_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__reference();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing description');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'description')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setDescription(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing keyTextId');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'keyTextId')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setKeyTextId(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataSet
//
function pds_nasa_gov__getDataSet () {
    this.typeMarker = 'pds_nasa_gov__getDataSet';
    this._id = 0;
}

//
// accessor is pds_nasa_gov__getDataSet.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__getDataSet.prototype.setId
//
function pds_nasa_gov__getDataSet_getId() { return this._id;}

pds_nasa_gov__getDataSet.prototype.getId = pds_nasa_gov__getDataSet_getId;

function pds_nasa_gov__getDataSet_setId(value) { this._id = value;}

pds_nasa_gov__getDataSet.prototype.setId = pds_nasa_gov__getDataSet_setId;
//
// Serialize {http://pds.nasa.gov/}getDataSet
//
function pds_nasa_gov__getDataSet_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataSet.prototype.serialize = pds_nasa_gov__getDataSet_serialize;

function pds_nasa_gov__getDataSet_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataSet();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchEntities
//
function pds_nasa_gov__searchEntities () {
    this.typeMarker = 'pds_nasa_gov__searchEntities';
    this._searchText = '';
    this._page = null;
}

//
// accessor is pds_nasa_gov__searchEntities.prototype.getSearchText
// element get for searchText
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
//
// element set for searchText
// setter function is is pds_nasa_gov__searchEntities.prototype.setSearchText
//
function pds_nasa_gov__searchEntities_getSearchText() { return this._searchText;}

pds_nasa_gov__searchEntities.prototype.getSearchText = pds_nasa_gov__searchEntities_getSearchText;

function pds_nasa_gov__searchEntities_setSearchText(value) { this._searchText = value;}

pds_nasa_gov__searchEntities.prototype.setSearchText = pds_nasa_gov__searchEntities_setSearchText;
//
// accessor is pds_nasa_gov__searchEntities.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__searchEntities.prototype.setPage
//
function pds_nasa_gov__searchEntities_getPage() { return this._page;}

pds_nasa_gov__searchEntities.prototype.getPage = pds_nasa_gov__searchEntities_getPage;

function pds_nasa_gov__searchEntities_setPage(value) { this._page = value;}

pds_nasa_gov__searchEntities.prototype.setPage = pds_nasa_gov__searchEntities_setPage;
//
// Serialize {http://pds.nasa.gov/}searchEntities
//
function pds_nasa_gov__searchEntities_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<searchText>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._searchText);
     xml = xml + '</searchText>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchEntities.prototype.serialize = pds_nasa_gov__searchEntities_serialize;

function pds_nasa_gov__searchEntities_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchEntities();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing searchText');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = value;
    }
    newobject.setSearchText(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDocumentsInfoResponse
//
function pds_nasa_gov__getDocumentsInfoResponse () {
    this.typeMarker = 'pds_nasa_gov__getDocumentsInfoResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getDocumentsInfoResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getDocumentsInfoResponse.prototype.setReturn
//
function pds_nasa_gov__getDocumentsInfoResponse_getReturn() { return this._return;}

pds_nasa_gov__getDocumentsInfoResponse.prototype.getReturn = pds_nasa_gov__getDocumentsInfoResponse_getReturn;

function pds_nasa_gov__getDocumentsInfoResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getDocumentsInfoResponse.prototype.setReturn = pds_nasa_gov__getDocumentsInfoResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getDocumentsInfoResponse
//
function pds_nasa_gov__getDocumentsInfoResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDocumentsInfoResponse.prototype.serialize = pds_nasa_gov__getDocumentsInfoResponse_serialize;

function pds_nasa_gov__getDocumentsInfoResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDocumentsInfoResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}targetType
//
function pds_nasa_gov__targetType () {
    this.typeMarker = 'pds_nasa_gov__targetType';
    this._id = 0;
    this._name = null;
}

//
// accessor is pds_nasa_gov__targetType.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__targetType.prototype.setId
//
function pds_nasa_gov__targetType_getId() { return this._id;}

pds_nasa_gov__targetType.prototype.getId = pds_nasa_gov__targetType_getId;

function pds_nasa_gov__targetType_setId(value) { this._id = value;}

pds_nasa_gov__targetType.prototype.setId = pds_nasa_gov__targetType_setId;
//
// accessor is pds_nasa_gov__targetType.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__targetType.prototype.setName
//
function pds_nasa_gov__targetType_getName() { return this._name;}

pds_nasa_gov__targetType.prototype.getName = pds_nasa_gov__targetType_getName;

function pds_nasa_gov__targetType_setName(value) { this._name = value;}

pds_nasa_gov__targetType.prototype.setName = pds_nasa_gov__targetType_setName;
//
// Serialize {http://pds.nasa.gov/}targetType
//
function pds_nasa_gov__targetType_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__targetType.prototype.serialize = pds_nasa_gov__targetType_serialize;

function pds_nasa_gov__targetType_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__targetType();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTargetsInfoResponse
//
function pds_nasa_gov__getTargetsInfoResponse () {
    this.typeMarker = 'pds_nasa_gov__getTargetsInfoResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getTargetsInfoResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getTargetsInfoResponse.prototype.setReturn
//
function pds_nasa_gov__getTargetsInfoResponse_getReturn() { return this._return;}

pds_nasa_gov__getTargetsInfoResponse.prototype.getReturn = pds_nasa_gov__getTargetsInfoResponse_getReturn;

function pds_nasa_gov__getTargetsInfoResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getTargetsInfoResponse.prototype.setReturn = pds_nasa_gov__getTargetsInfoResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getTargetsInfoResponse
//
function pds_nasa_gov__getTargetsInfoResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTargetsInfoResponse.prototype.serialize = pds_nasa_gov__getTargetsInfoResponse_serialize;

function pds_nasa_gov__getTargetsInfoResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTargetsInfoResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getMission
//
function pds_nasa_gov__getMission () {
    this.typeMarker = 'pds_nasa_gov__getMission';
    this._id = 0;
}

//
// accessor is pds_nasa_gov__getMission.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__getMission.prototype.setId
//
function pds_nasa_gov__getMission_getId() { return this._id;}

pds_nasa_gov__getMission.prototype.getId = pds_nasa_gov__getMission_getId;

function pds_nasa_gov__getMission_setId(value) { this._id = value;}

pds_nasa_gov__getMission.prototype.setId = pds_nasa_gov__getMission_setId;
//
// Serialize {http://pds.nasa.gov/}getMission
//
function pds_nasa_gov__getMission_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getMission.prototype.serialize = pds_nasa_gov__getMission_serialize;

function pds_nasa_gov__getMission_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getMission();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTargetType
//
function pds_nasa_gov__getTargetType () {
    this.typeMarker = 'pds_nasa_gov__getTargetType';
    this._id = 0;
}

//
// accessor is pds_nasa_gov__getTargetType.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__getTargetType.prototype.setId
//
function pds_nasa_gov__getTargetType_getId() { return this._id;}

pds_nasa_gov__getTargetType.prototype.getId = pds_nasa_gov__getTargetType_getId;

function pds_nasa_gov__getTargetType_setId(value) { this._id = value;}

pds_nasa_gov__getTargetType.prototype.setId = pds_nasa_gov__getTargetType_setId;
//
// Serialize {http://pds.nasa.gov/}getTargetType
//
function pds_nasa_gov__getTargetType_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTargetType.prototype.serialize = pds_nasa_gov__getTargetType_serialize;

function pds_nasa_gov__getTargetType_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTargetType();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchEntitiesByTypeResponse
//
function pds_nasa_gov__searchEntitiesByTypeResponse () {
    this.typeMarker = 'pds_nasa_gov__searchEntitiesByTypeResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__searchEntitiesByTypeResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__searchEntitiesByTypeResponse.prototype.setReturn
//
function pds_nasa_gov__searchEntitiesByTypeResponse_getReturn() { return this._return;}

pds_nasa_gov__searchEntitiesByTypeResponse.prototype.getReturn = pds_nasa_gov__searchEntitiesByTypeResponse_getReturn;

function pds_nasa_gov__searchEntitiesByTypeResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__searchEntitiesByTypeResponse.prototype.setReturn = pds_nasa_gov__searchEntitiesByTypeResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}searchEntitiesByTypeResponse
//
function pds_nasa_gov__searchEntitiesByTypeResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchEntitiesByTypeResponse.prototype.serialize = pds_nasa_gov__searchEntitiesByTypeResponse_serialize;

function pds_nasa_gov__searchEntitiesByTypeResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchEntitiesByTypeResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataSetRelatedEntitites
//
function pds_nasa_gov__getDataSetRelatedEntitites () {
    this.typeMarker = 'pds_nasa_gov__getDataSetRelatedEntitites';
    this._dataSetId = 0;
    this._relatedEntityType = '';
}

//
// accessor is pds_nasa_gov__getDataSetRelatedEntitites.prototype.getDataSetId
// element get for dataSetId
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for dataSetId
// setter function is is pds_nasa_gov__getDataSetRelatedEntitites.prototype.setDataSetId
//
function pds_nasa_gov__getDataSetRelatedEntitites_getDataSetId() { return this._dataSetId;}

pds_nasa_gov__getDataSetRelatedEntitites.prototype.getDataSetId = pds_nasa_gov__getDataSetRelatedEntitites_getDataSetId;

function pds_nasa_gov__getDataSetRelatedEntitites_setDataSetId(value) { this._dataSetId = value;}

pds_nasa_gov__getDataSetRelatedEntitites.prototype.setDataSetId = pds_nasa_gov__getDataSetRelatedEntitites_setDataSetId;
//
// accessor is pds_nasa_gov__getDataSetRelatedEntitites.prototype.getRelatedEntityType
// element get for relatedEntityType
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
//
// element set for relatedEntityType
// setter function is is pds_nasa_gov__getDataSetRelatedEntitites.prototype.setRelatedEntityType
//
function pds_nasa_gov__getDataSetRelatedEntitites_getRelatedEntityType() { return this._relatedEntityType;}

pds_nasa_gov__getDataSetRelatedEntitites.prototype.getRelatedEntityType = pds_nasa_gov__getDataSetRelatedEntitites_getRelatedEntityType;

function pds_nasa_gov__getDataSetRelatedEntitites_setRelatedEntityType(value) { this._relatedEntityType = value;}

pds_nasa_gov__getDataSetRelatedEntitites.prototype.setRelatedEntityType = pds_nasa_gov__getDataSetRelatedEntitites_setRelatedEntityType;
//
// Serialize {http://pds.nasa.gov/}getDataSetRelatedEntitites
//
function pds_nasa_gov__getDataSetRelatedEntitites_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<dataSetId>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._dataSetId);
     xml = xml + '</dataSetId>';
    }
    // block for local variables
    {
     xml = xml + '<relatedEntityType>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._relatedEntityType);
     xml = xml + '</relatedEntityType>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataSetRelatedEntitites.prototype.serialize = pds_nasa_gov__getDataSetRelatedEntitites_serialize;

function pds_nasa_gov__getDataSetRelatedEntitites_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataSetRelatedEntitites();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing dataSetId');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setDataSetId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing relatedEntityType');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = value;
    }
    newobject.setRelatedEntityType(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchEntitiesResponse
//
function pds_nasa_gov__searchEntitiesResponse () {
    this.typeMarker = 'pds_nasa_gov__searchEntitiesResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__searchEntitiesResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}searchResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__searchEntitiesResponse.prototype.setReturn
//
function pds_nasa_gov__searchEntitiesResponse_getReturn() { return this._return;}

pds_nasa_gov__searchEntitiesResponse.prototype.getReturn = pds_nasa_gov__searchEntitiesResponse_getReturn;

function pds_nasa_gov__searchEntitiesResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__searchEntitiesResponse.prototype.setReturn = pds_nasa_gov__searchEntitiesResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}searchEntitiesResponse
//
function pds_nasa_gov__searchEntitiesResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchEntitiesResponse.prototype.serialize = pds_nasa_gov__searchEntitiesResponse_serialize;

function pds_nasa_gov__searchEntitiesResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchEntitiesResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__searchResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}dataSet
//
function pds_nasa_gov__dataSet () {
    this.typeMarker = 'pds_nasa_gov__dataSet';
    this._id = 0;
    this._name = null;
    this._description = null;
    this._instruments = [];
    this._missions = [];
    this._otherChildren = [];
    this._rating = null;
    this._references = [];
    this._startDate = null;
    this._stopDate = null;
    this._targets = [];
    this._textId = null;
    this._volumes = [];
}

//
// accessor is pds_nasa_gov__dataSet.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for id
// setter function is is pds_nasa_gov__dataSet.prototype.setId
//
function pds_nasa_gov__dataSet_getId() { return this._id;}

pds_nasa_gov__dataSet.prototype.getId = pds_nasa_gov__dataSet_getId;

function pds_nasa_gov__dataSet_setId(value) { this._id = value;}

pds_nasa_gov__dataSet.prototype.setId = pds_nasa_gov__dataSet_setId;
//
// accessor is pds_nasa_gov__dataSet.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is pds_nasa_gov__dataSet.prototype.setName
//
function pds_nasa_gov__dataSet_getName() { return this._name;}

pds_nasa_gov__dataSet.prototype.getName = pds_nasa_gov__dataSet_getName;

function pds_nasa_gov__dataSet_setName(value) { this._name = value;}

pds_nasa_gov__dataSet.prototype.setName = pds_nasa_gov__dataSet_setName;
//
// accessor is pds_nasa_gov__dataSet.prototype.getDescription
// element get for description
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for description
// setter function is is pds_nasa_gov__dataSet.prototype.setDescription
//
function pds_nasa_gov__dataSet_getDescription() { return this._description;}

pds_nasa_gov__dataSet.prototype.getDescription = pds_nasa_gov__dataSet_getDescription;

function pds_nasa_gov__dataSet_setDescription(value) { this._description = value;}

pds_nasa_gov__dataSet.prototype.setDescription = pds_nasa_gov__dataSet_setDescription;
//
// accessor is pds_nasa_gov__dataSet.prototype.getInstruments
// element get for instruments
// - element type is {http://pds.nasa.gov/}instrument
// - required element
// - array
// - nillable
//
// element set for instruments
// setter function is is pds_nasa_gov__dataSet.prototype.setInstruments
//
function pds_nasa_gov__dataSet_getInstruments() { return this._instruments;}

pds_nasa_gov__dataSet.prototype.getInstruments = pds_nasa_gov__dataSet_getInstruments;

function pds_nasa_gov__dataSet_setInstruments(value) { this._instruments = value;}

pds_nasa_gov__dataSet.prototype.setInstruments = pds_nasa_gov__dataSet_setInstruments;
//
// accessor is pds_nasa_gov__dataSet.prototype.getMissions
// element get for missions
// - element type is {http://pds.nasa.gov/}mission
// - required element
// - array
// - nillable
//
// element set for missions
// setter function is is pds_nasa_gov__dataSet.prototype.setMissions
//
function pds_nasa_gov__dataSet_getMissions() { return this._missions;}

pds_nasa_gov__dataSet.prototype.getMissions = pds_nasa_gov__dataSet_getMissions;

function pds_nasa_gov__dataSet_setMissions(value) { this._missions = value;}

pds_nasa_gov__dataSet.prototype.setMissions = pds_nasa_gov__dataSet_setMissions;
//
// accessor is pds_nasa_gov__dataSet.prototype.getOtherChildren
// element get for otherChildren
// - element type is {http://pds.nasa.gov/}metadataObject
// - required element
// - array
// - nillable
//
// element set for otherChildren
// setter function is is pds_nasa_gov__dataSet.prototype.setOtherChildren
//
function pds_nasa_gov__dataSet_getOtherChildren() { return this._otherChildren;}

pds_nasa_gov__dataSet.prototype.getOtherChildren = pds_nasa_gov__dataSet_getOtherChildren;

function pds_nasa_gov__dataSet_setOtherChildren(value) { this._otherChildren = value;}

pds_nasa_gov__dataSet.prototype.setOtherChildren = pds_nasa_gov__dataSet_setOtherChildren;
//
// accessor is pds_nasa_gov__dataSet.prototype.getRating
// element get for rating
// - element type is {http://www.w3.org/2001/XMLSchema}float
// - optional element
//
// element set for rating
// setter function is is pds_nasa_gov__dataSet.prototype.setRating
//
function pds_nasa_gov__dataSet_getRating() { return this._rating;}

pds_nasa_gov__dataSet.prototype.getRating = pds_nasa_gov__dataSet_getRating;

function pds_nasa_gov__dataSet_setRating(value) { this._rating = value;}

pds_nasa_gov__dataSet.prototype.setRating = pds_nasa_gov__dataSet_setRating;
//
// accessor is pds_nasa_gov__dataSet.prototype.getReferences
// element get for references
// - element type is {http://pds.nasa.gov/}reference
// - required element
// - array
// - nillable
//
// element set for references
// setter function is is pds_nasa_gov__dataSet.prototype.setReferences
//
function pds_nasa_gov__dataSet_getReferences() { return this._references;}

pds_nasa_gov__dataSet.prototype.getReferences = pds_nasa_gov__dataSet_getReferences;

function pds_nasa_gov__dataSet_setReferences(value) { this._references = value;}

pds_nasa_gov__dataSet.prototype.setReferences = pds_nasa_gov__dataSet_setReferences;
//
// accessor is pds_nasa_gov__dataSet.prototype.getStartDate
// element get for startDate
// - element type is {http://www.w3.org/2001/XMLSchema}dateTime
// - optional element
//
// element set for startDate
// setter function is is pds_nasa_gov__dataSet.prototype.setStartDate
//
function pds_nasa_gov__dataSet_getStartDate() { return this._startDate;}

pds_nasa_gov__dataSet.prototype.getStartDate = pds_nasa_gov__dataSet_getStartDate;

function pds_nasa_gov__dataSet_setStartDate(value) { this._startDate = value;}

pds_nasa_gov__dataSet.prototype.setStartDate = pds_nasa_gov__dataSet_setStartDate;
//
// accessor is pds_nasa_gov__dataSet.prototype.getStopDate
// element get for stopDate
// - element type is {http://www.w3.org/2001/XMLSchema}dateTime
// - optional element
//
// element set for stopDate
// setter function is is pds_nasa_gov__dataSet.prototype.setStopDate
//
function pds_nasa_gov__dataSet_getStopDate() { return this._stopDate;}

pds_nasa_gov__dataSet.prototype.getStopDate = pds_nasa_gov__dataSet_getStopDate;

function pds_nasa_gov__dataSet_setStopDate(value) { this._stopDate = value;}

pds_nasa_gov__dataSet.prototype.setStopDate = pds_nasa_gov__dataSet_setStopDate;
//
// accessor is pds_nasa_gov__dataSet.prototype.getTargets
// element get for targets
// - element type is {http://pds.nasa.gov/}target
// - required element
// - array
// - nillable
//
// element set for targets
// setter function is is pds_nasa_gov__dataSet.prototype.setTargets
//
function pds_nasa_gov__dataSet_getTargets() { return this._targets;}

pds_nasa_gov__dataSet.prototype.getTargets = pds_nasa_gov__dataSet_getTargets;

function pds_nasa_gov__dataSet_setTargets(value) { this._targets = value;}

pds_nasa_gov__dataSet.prototype.setTargets = pds_nasa_gov__dataSet_setTargets;
//
// accessor is pds_nasa_gov__dataSet.prototype.getTextId
// element get for textId
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for textId
// setter function is is pds_nasa_gov__dataSet.prototype.setTextId
//
function pds_nasa_gov__dataSet_getTextId() { return this._textId;}

pds_nasa_gov__dataSet.prototype.getTextId = pds_nasa_gov__dataSet_getTextId;

function pds_nasa_gov__dataSet_setTextId(value) { this._textId = value;}

pds_nasa_gov__dataSet.prototype.setTextId = pds_nasa_gov__dataSet_setTextId;
//
// accessor is pds_nasa_gov__dataSet.prototype.getVolumes
// element get for volumes
// - element type is {http://pds.nasa.gov/}volume
// - required element
// - array
// - nillable
//
// element set for volumes
// setter function is is pds_nasa_gov__dataSet.prototype.setVolumes
//
function pds_nasa_gov__dataSet_getVolumes() { return this._volumes;}

pds_nasa_gov__dataSet.prototype.getVolumes = pds_nasa_gov__dataSet_getVolumes;

function pds_nasa_gov__dataSet_setVolumes(value) { this._volumes = value;}

pds_nasa_gov__dataSet.prototype.setVolumes = pds_nasa_gov__dataSet_setVolumes;
//
// Serialize {http://pds.nasa.gov/}dataSet
//
function pds_nasa_gov__dataSet_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<id>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._id);
     xml = xml + '</id>';
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    // block for local variables
    {
     if (this._description != null) {
      xml = xml + '<description>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._description);
      xml = xml + '</description>';
     }
    }
    // block for local variables
    {
     if (this._instruments != null) {
      for (var ax = 0;ax < this._instruments.length;ax ++) {
       if (this._instruments[ax] == null) {
        xml = xml + '<instruments xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._instruments[ax].serialize(cxfjsutils, 'instruments', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._missions != null) {
      for (var ax = 0;ax < this._missions.length;ax ++) {
       if (this._missions[ax] == null) {
        xml = xml + '<missions xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._missions[ax].serialize(cxfjsutils, 'missions', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._otherChildren != null) {
      for (var ax = 0;ax < this._otherChildren.length;ax ++) {
       if (this._otherChildren[ax] == null) {
        xml = xml + '<otherChildren xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._otherChildren[ax].serialize(cxfjsutils, 'otherChildren', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._rating != null) {
      xml = xml + '<rating>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._rating);
      xml = xml + '</rating>';
     }
    }
    // block for local variables
    {
     if (this._references != null) {
      for (var ax = 0;ax < this._references.length;ax ++) {
       if (this._references[ax] == null) {
        xml = xml + '<references xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._references[ax].serialize(cxfjsutils, 'references', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._startDate != null) {
      xml = xml + '<startDate>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._startDate);
      xml = xml + '</startDate>';
     }
    }
    // block for local variables
    {
     if (this._stopDate != null) {
      xml = xml + '<stopDate>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._stopDate);
      xml = xml + '</stopDate>';
     }
    }
    // block for local variables
    {
     if (this._targets != null) {
      for (var ax = 0;ax < this._targets.length;ax ++) {
       if (this._targets[ax] == null) {
        xml = xml + '<targets xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._targets[ax].serialize(cxfjsutils, 'targets', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._textId != null) {
      xml = xml + '<textId>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._textId);
      xml = xml + '</textId>';
     }
    }
    // block for local variables
    {
     if (this._volumes != null) {
      for (var ax = 0;ax < this._volumes.length;ax ++) {
       if (this._volumes[ax] == null) {
        xml = xml + '<volumes xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._volumes[ax].serialize(cxfjsutils, 'volumes', null);
       }
      }
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__dataSet.prototype.serialize = pds_nasa_gov__dataSet_serialize;

function pds_nasa_gov__dataSet_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__dataSet();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing id');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing description');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'description')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setDescription(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing instruments');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'instruments')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__instrument_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'instruments'));
     newobject.setInstruments(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing missions');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'missions')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__mission_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'missions'));
     newobject.setMissions(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing otherChildren');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__metadataObject_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'otherChildren'));
     newobject.setOtherChildren(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing rating');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'rating')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = parseFloat(value);
     }
     newobject.setRating(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing references');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__reference_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'references'));
     newobject.setReferences(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing startDate');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'startDate')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setStartDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing stopDate');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'stopDate')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setStopDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing targets');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targets')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__target_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targets'));
     newobject.setTargets(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing textId');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'textId')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setTextId(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing volumes');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'volumes')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = pds_nasa_gov__volume_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'volumes'));
     newobject.setVolumes(item);
     var item = null;
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getInstrumentsInfoResponse
//
function pds_nasa_gov__getInstrumentsInfoResponse () {
    this.typeMarker = 'pds_nasa_gov__getInstrumentsInfoResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getInstrumentsInfoResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}pagedResults
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getInstrumentsInfoResponse.prototype.setReturn
//
function pds_nasa_gov__getInstrumentsInfoResponse_getReturn() { return this._return;}

pds_nasa_gov__getInstrumentsInfoResponse.prototype.getReturn = pds_nasa_gov__getInstrumentsInfoResponse_getReturn;

function pds_nasa_gov__getInstrumentsInfoResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getInstrumentsInfoResponse.prototype.setReturn = pds_nasa_gov__getInstrumentsInfoResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getInstrumentsInfoResponse
//
function pds_nasa_gov__getInstrumentsInfoResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getInstrumentsInfoResponse.prototype.serialize = pds_nasa_gov__getInstrumentsInfoResponse_serialize;

function pds_nasa_gov__getInstrumentsInfoResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getInstrumentsInfoResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__pagedResults_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTargetResponse
//
function pds_nasa_gov__getTargetResponse () {
    this.typeMarker = 'pds_nasa_gov__getTargetResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getTargetResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}target
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getTargetResponse.prototype.setReturn
//
function pds_nasa_gov__getTargetResponse_getReturn() { return this._return;}

pds_nasa_gov__getTargetResponse.prototype.getReturn = pds_nasa_gov__getTargetResponse_getReturn;

function pds_nasa_gov__getTargetResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getTargetResponse.prototype.setReturn = pds_nasa_gov__getTargetResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getTargetResponse
//
function pds_nasa_gov__getTargetResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTargetResponse.prototype.serialize = pds_nasa_gov__getTargetResponse_serialize;

function pds_nasa_gov__getTargetResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTargetResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__target_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}searchCriteria
//
function pds_nasa_gov__searchCriteria () {
    this.typeMarker = 'pds_nasa_gov__searchCriteria';
    this._dataSetId = 0;
    this._instrumentHosts = [];
    this._instruments = [];
    this._missions = [];
    this._startDate = null;
    this._stopDate = null;
    this._targetTypes = [];
    this._targets = [];
}

//
// accessor is pds_nasa_gov__searchCriteria.prototype.getDataSetId
// element get for dataSetId
// - element type is {http://www.w3.org/2001/XMLSchema}long
// - required element
//
// element set for dataSetId
// setter function is is pds_nasa_gov__searchCriteria.prototype.setDataSetId
//
function pds_nasa_gov__searchCriteria_getDataSetId() { return this._dataSetId;}

pds_nasa_gov__searchCriteria.prototype.getDataSetId = pds_nasa_gov__searchCriteria_getDataSetId;

function pds_nasa_gov__searchCriteria_setDataSetId(value) { this._dataSetId = value;}

pds_nasa_gov__searchCriteria.prototype.setDataSetId = pds_nasa_gov__searchCriteria_setDataSetId;
//
// accessor is pds_nasa_gov__searchCriteria.prototype.getInstrumentHosts
// element get for instrumentHosts
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
// - array
// - nillable
//
// element set for instrumentHosts
// setter function is is pds_nasa_gov__searchCriteria.prototype.setInstrumentHosts
//
function pds_nasa_gov__searchCriteria_getInstrumentHosts() { return this._instrumentHosts;}

pds_nasa_gov__searchCriteria.prototype.getInstrumentHosts = pds_nasa_gov__searchCriteria_getInstrumentHosts;

function pds_nasa_gov__searchCriteria_setInstrumentHosts(value) { this._instrumentHosts = value;}

pds_nasa_gov__searchCriteria.prototype.setInstrumentHosts = pds_nasa_gov__searchCriteria_setInstrumentHosts;
//
// accessor is pds_nasa_gov__searchCriteria.prototype.getInstruments
// element get for instruments
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
// - array
// - nillable
//
// element set for instruments
// setter function is is pds_nasa_gov__searchCriteria.prototype.setInstruments
//
function pds_nasa_gov__searchCriteria_getInstruments() { return this._instruments;}

pds_nasa_gov__searchCriteria.prototype.getInstruments = pds_nasa_gov__searchCriteria_getInstruments;

function pds_nasa_gov__searchCriteria_setInstruments(value) { this._instruments = value;}

pds_nasa_gov__searchCriteria.prototype.setInstruments = pds_nasa_gov__searchCriteria_setInstruments;
//
// accessor is pds_nasa_gov__searchCriteria.prototype.getMissions
// element get for missions
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
// - array
// - nillable
//
// element set for missions
// setter function is is pds_nasa_gov__searchCriteria.prototype.setMissions
//
function pds_nasa_gov__searchCriteria_getMissions() { return this._missions;}

pds_nasa_gov__searchCriteria.prototype.getMissions = pds_nasa_gov__searchCriteria_getMissions;

function pds_nasa_gov__searchCriteria_setMissions(value) { this._missions = value;}

pds_nasa_gov__searchCriteria.prototype.setMissions = pds_nasa_gov__searchCriteria_setMissions;
//
// accessor is pds_nasa_gov__searchCriteria.prototype.getStartDate
// element get for startDate
// - element type is {http://www.w3.org/2001/XMLSchema}dateTime
// - optional element
//
// element set for startDate
// setter function is is pds_nasa_gov__searchCriteria.prototype.setStartDate
//
function pds_nasa_gov__searchCriteria_getStartDate() { return this._startDate;}

pds_nasa_gov__searchCriteria.prototype.getStartDate = pds_nasa_gov__searchCriteria_getStartDate;

function pds_nasa_gov__searchCriteria_setStartDate(value) { this._startDate = value;}

pds_nasa_gov__searchCriteria.prototype.setStartDate = pds_nasa_gov__searchCriteria_setStartDate;
//
// accessor is pds_nasa_gov__searchCriteria.prototype.getStopDate
// element get for stopDate
// - element type is {http://www.w3.org/2001/XMLSchema}dateTime
// - optional element
//
// element set for stopDate
// setter function is is pds_nasa_gov__searchCriteria.prototype.setStopDate
//
function pds_nasa_gov__searchCriteria_getStopDate() { return this._stopDate;}

pds_nasa_gov__searchCriteria.prototype.getStopDate = pds_nasa_gov__searchCriteria_getStopDate;

function pds_nasa_gov__searchCriteria_setStopDate(value) { this._stopDate = value;}

pds_nasa_gov__searchCriteria.prototype.setStopDate = pds_nasa_gov__searchCriteria_setStopDate;
//
// accessor is pds_nasa_gov__searchCriteria.prototype.getTargetTypes
// element get for targetTypes
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
// - array
// - nillable
//
// element set for targetTypes
// setter function is is pds_nasa_gov__searchCriteria.prototype.setTargetTypes
//
function pds_nasa_gov__searchCriteria_getTargetTypes() { return this._targetTypes;}

pds_nasa_gov__searchCriteria.prototype.getTargetTypes = pds_nasa_gov__searchCriteria_getTargetTypes;

function pds_nasa_gov__searchCriteria_setTargetTypes(value) { this._targetTypes = value;}

pds_nasa_gov__searchCriteria.prototype.setTargetTypes = pds_nasa_gov__searchCriteria_setTargetTypes;
//
// accessor is pds_nasa_gov__searchCriteria.prototype.getTargets
// element get for targets
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
// - array
// - nillable
//
// element set for targets
// setter function is is pds_nasa_gov__searchCriteria.prototype.setTargets
//
function pds_nasa_gov__searchCriteria_getTargets() { return this._targets;}

pds_nasa_gov__searchCriteria.prototype.getTargets = pds_nasa_gov__searchCriteria_getTargets;

function pds_nasa_gov__searchCriteria_setTargets(value) { this._targets = value;}

pds_nasa_gov__searchCriteria.prototype.setTargets = pds_nasa_gov__searchCriteria_setTargets;
//
// Serialize {http://pds.nasa.gov/}searchCriteria
//
function pds_nasa_gov__searchCriteria_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<dataSetId>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._dataSetId);
     xml = xml + '</dataSetId>';
    }
    // block for local variables
    {
     if (this._instrumentHosts != null) {
      for (var ax = 0;ax < this._instrumentHosts.length;ax ++) {
       if (this._instrumentHosts[ax] == null) {
        xml = xml + '<instrumentHosts xsi:nil=\'true\'/>';
       } else {
        xml = xml + '<instrumentHosts>';
        xml = xml + cxfjsutils.escapeXmlEntities(this._instrumentHosts[ax]);
        xml = xml + '</instrumentHosts>';
       }
      }
     }
    }
    // block for local variables
    {
     if (this._instruments != null) {
      for (var ax = 0;ax < this._instruments.length;ax ++) {
       if (this._instruments[ax] == null) {
        xml = xml + '<instruments xsi:nil=\'true\'/>';
       } else {
        xml = xml + '<instruments>';
        xml = xml + cxfjsutils.escapeXmlEntities(this._instruments[ax]);
        xml = xml + '</instruments>';
       }
      }
     }
    }
    // block for local variables
    {
     if (this._missions != null) {
      for (var ax = 0;ax < this._missions.length;ax ++) {
       if (this._missions[ax] == null) {
        xml = xml + '<missions xsi:nil=\'true\'/>';
       } else {
        xml = xml + '<missions>';
        xml = xml + cxfjsutils.escapeXmlEntities(this._missions[ax]);
        xml = xml + '</missions>';
       }
      }
     }
    }
    // block for local variables
    {
     if (this._startDate != null) {
      xml = xml + '<startDate>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._startDate);
      xml = xml + '</startDate>';
     }
    }
    // block for local variables
    {
     if (this._stopDate != null) {
      xml = xml + '<stopDate>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._stopDate);
      xml = xml + '</stopDate>';
     }
    }
    // block for local variables
    {
     if (this._targetTypes != null) {
      for (var ax = 0;ax < this._targetTypes.length;ax ++) {
       if (this._targetTypes[ax] == null) {
        xml = xml + '<targetTypes xsi:nil=\'true\'/>';
       } else {
        xml = xml + '<targetTypes>';
        xml = xml + cxfjsutils.escapeXmlEntities(this._targetTypes[ax]);
        xml = xml + '</targetTypes>';
       }
      }
     }
    }
    // block for local variables
    {
     if (this._targets != null) {
      for (var ax = 0;ax < this._targets.length;ax ++) {
       if (this._targets[ax] == null) {
        xml = xml + '<targets xsi:nil=\'true\'/>';
       } else {
        xml = xml + '<targets>';
        xml = xml + cxfjsutils.escapeXmlEntities(this._targets[ax]);
        xml = xml + '</targets>';
       }
      }
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__searchCriteria.prototype.serialize = pds_nasa_gov__searchCriteria_serialize;

function pds_nasa_gov__searchCriteria_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__searchCriteria();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing dataSetId');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = parseInt(value);
    }
    newobject.setDataSetId(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing instrumentHosts');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'instrumentHosts')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       value = cxfjsutils.getNodeText(curElement);
       arrayItem = value;
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'instrumentHosts'));
     newobject.setInstrumentHosts(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing instruments');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'instruments')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       value = cxfjsutils.getNodeText(curElement);
       arrayItem = value;
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'instruments'));
     newobject.setInstruments(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing missions');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'missions')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       value = cxfjsutils.getNodeText(curElement);
       arrayItem = value;
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'missions'));
     newobject.setMissions(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing startDate');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'startDate')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setStartDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing stopDate');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'stopDate')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setStopDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing targetTypes');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targetTypes')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       value = cxfjsutils.getNodeText(curElement);
       arrayItem = value;
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targetTypes'));
     newobject.setTargetTypes(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing targets');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targets')) {
     item = [];
     do  {
      var arrayItem;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       value = cxfjsutils.getNodeText(curElement);
       arrayItem = value;
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'targets'));
     newobject.setTargets(item);
     var item = null;
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getDataFileResponse
//
function pds_nasa_gov__getDataFileResponse () {
    this.typeMarker = 'pds_nasa_gov__getDataFileResponse';
    this._return = null;
}

//
// accessor is pds_nasa_gov__getDataFileResponse.prototype.getReturn
// element get for return
// - element type is {http://pds.nasa.gov/}wsDataFile
// - optional element
//
// element set for return
// setter function is is pds_nasa_gov__getDataFileResponse.prototype.setReturn
//
function pds_nasa_gov__getDataFileResponse_getReturn() { return this._return;}

pds_nasa_gov__getDataFileResponse.prototype.getReturn = pds_nasa_gov__getDataFileResponse_getReturn;

function pds_nasa_gov__getDataFileResponse_setReturn(value) { this._return = value;}

pds_nasa_gov__getDataFileResponse.prototype.setReturn = pds_nasa_gov__getDataFileResponse_setReturn;
//
// Serialize {http://pds.nasa.gov/}getDataFileResponse
//
function pds_nasa_gov__getDataFileResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getDataFileResponse.prototype.serialize = pds_nasa_gov__getDataFileResponse_serialize;

function pds_nasa_gov__getDataFileResponse_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getDataFileResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__wsDataFile_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://pds.nasa.gov/}getTargetsInfo
//
function pds_nasa_gov__getTargetsInfo () {
    this.typeMarker = 'pds_nasa_gov__getTargetsInfo';
    this._page = null;
    this._restriction = null;
}

//
// accessor is pds_nasa_gov__getTargetsInfo.prototype.getPage
// element get for page
// - element type is {http://pds.nasa.gov/}page
// - optional element
//
// element set for page
// setter function is is pds_nasa_gov__getTargetsInfo.prototype.setPage
//
function pds_nasa_gov__getTargetsInfo_getPage() { return this._page;}

pds_nasa_gov__getTargetsInfo.prototype.getPage = pds_nasa_gov__getTargetsInfo_getPage;

function pds_nasa_gov__getTargetsInfo_setPage(value) { this._page = value;}

pds_nasa_gov__getTargetsInfo.prototype.setPage = pds_nasa_gov__getTargetsInfo_setPage;
//
// accessor is pds_nasa_gov__getTargetsInfo.prototype.getRestriction
// element get for restriction
// - element type is {http://pds.nasa.gov/}restriction
// - optional element
//
// element set for restriction
// setter function is is pds_nasa_gov__getTargetsInfo.prototype.setRestriction
//
function pds_nasa_gov__getTargetsInfo_getRestriction() { return this._restriction;}

pds_nasa_gov__getTargetsInfo.prototype.getRestriction = pds_nasa_gov__getTargetsInfo_getRestriction;

function pds_nasa_gov__getTargetsInfo_setRestriction(value) { this._restriction = value;}

pds_nasa_gov__getTargetsInfo.prototype.setRestriction = pds_nasa_gov__getTargetsInfo_setRestriction;
//
// Serialize {http://pds.nasa.gov/}getTargetsInfo
//
function pds_nasa_gov__getTargetsInfo_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._page != null) {
      xml = xml + this._page.serialize(cxfjsutils, 'page', null);
     }
    }
    // block for local variables
    {
     if (this._restriction != null) {
      xml = xml + this._restriction.serialize(cxfjsutils, 'restriction', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

pds_nasa_gov__getTargetsInfo.prototype.serialize = pds_nasa_gov__getTargetsInfo_serialize;

function pds_nasa_gov__getTargetsInfo_deserialize (cxfjsutils, element) {
    var newobject = new pds_nasa_gov__getTargetsInfo();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing page');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'page')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__page_deserialize(cxfjsutils, curElement);
     }
     newobject.setPage(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing restriction');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'restriction')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = pds_nasa_gov__restriction_deserialize(cxfjsutils, curElement);
     }
     newobject.setRestriction(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Definitions for schema: null
//  file:/D:/topcoder/work/assemblies/pds_projects/web_service/WebContent/wsdl/planetarydatasystem.wsdl#types1
//
//
// Definitions for service: {http://pds.nasa.gov/}PlanetaryDataSystemService
//

// Javascript for {http://pds.nasa.gov/}PlanetaryDataSystemSEI

function pds_nasa_gov__PlanetaryDataSystemSEI () {
    this.jsutils = new CxfApacheOrgUtil();
    this.jsutils.interfaceObject = this;
    this.synchronous = false;
    this.url = null;
    this.client = null;
    this.response = null;
    this.globalElementSerializers = [];
    this.globalElementDeserializers = [];
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetsInfo'] = pds_nasa_gov__getDataSetsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetsInfo'] = pds_nasa_gov__getDataSetsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetTypesInfoResponse'] = pds_nasa_gov__getTargetTypesInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetTypesInfoResponse'] = pds_nasa_gov__getTargetTypesInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetResponse'] = pds_nasa_gov__getDataSetResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetResponse'] = pds_nasa_gov__getDataSetResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}DataSetProcessingException'] = pds_nasa_gov__DataSetProcessingException_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}DataSetProcessingException'] = pds_nasa_gov__DataSetProcessingException_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMissionResponse'] = pds_nasa_gov__getMissionResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMissionResponse'] = pds_nasa_gov__getMissionResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetsInfoResponse'] = pds_nasa_gov__getDataSetsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetsInfoResponse'] = pds_nasa_gov__getDataSetsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrumentResponse'] = pds_nasa_gov__getInstrumentResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrumentResponse'] = pds_nasa_gov__getInstrumentResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMissionsInfoResponse'] = pds_nasa_gov__getMissionsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMissionsInfoResponse'] = pds_nasa_gov__getMissionsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetTypeResponse'] = pds_nasa_gov__getTargetTypeResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetTypeResponse'] = pds_nasa_gov__getTargetTypeResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntitiesByType'] = pds_nasa_gov__searchEntitiesByType_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntitiesByType'] = pds_nasa_gov__searchEntitiesByType_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetRelatedEntititesResponse'] = pds_nasa_gov__getDataSetRelatedEntititesResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetRelatedEntititesResponse'] = pds_nasa_gov__getDataSetRelatedEntititesResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrument'] = pds_nasa_gov__getInstrument_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrument'] = pds_nasa_gov__getInstrument_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDocumentsInfo'] = pds_nasa_gov__getDocumentsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDocumentsInfo'] = pds_nasa_gov__getDocumentsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getPreviewImageURLResponse'] = pds_nasa_gov__getPreviewImageURLResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getPreviewImageURLResponse'] = pds_nasa_gov__getPreviewImageURLResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSet'] = pds_nasa_gov__getDataSet_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSet'] = pds_nasa_gov__getDataSet_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetTypesInfo'] = pds_nasa_gov__getTargetTypesInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetTypesInfo'] = pds_nasa_gov__getTargetTypesInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchDataSetsByCriteria'] = pds_nasa_gov__searchDataSetsByCriteria_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchDataSetsByCriteria'] = pds_nasa_gov__searchDataSetsByCriteria_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getPreviewImageURL'] = pds_nasa_gov__getPreviewImageURL_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getPreviewImageURL'] = pds_nasa_gov__getPreviewImageURL_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntities'] = pds_nasa_gov__searchEntities_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntities'] = pds_nasa_gov__searchEntities_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMissionsInfo'] = pds_nasa_gov__getMissionsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMissionsInfo'] = pds_nasa_gov__getMissionsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDocumentsInfoResponse'] = pds_nasa_gov__getDocumentsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDocumentsInfoResponse'] = pds_nasa_gov__getDocumentsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getImagesInfo'] = pds_nasa_gov__getImagesInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getImagesInfo'] = pds_nasa_gov__getImagesInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetsInfoResponse'] = pds_nasa_gov__getTargetsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetsInfoResponse'] = pds_nasa_gov__getTargetsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getImagesInfoResponse'] = pds_nasa_gov__getImagesInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getImagesInfoResponse'] = pds_nasa_gov__getImagesInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMission'] = pds_nasa_gov__getMission_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMission'] = pds_nasa_gov__getMission_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchDataSetsByCriteriaResponse'] = pds_nasa_gov__searchDataSetsByCriteriaResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchDataSetsByCriteriaResponse'] = pds_nasa_gov__searchDataSetsByCriteriaResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetType'] = pds_nasa_gov__getTargetType_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetType'] = pds_nasa_gov__getTargetType_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataFile'] = pds_nasa_gov__getDataFile_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataFile'] = pds_nasa_gov__getDataFile_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrumentsInfo'] = pds_nasa_gov__getInstrumentsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrumentsInfo'] = pds_nasa_gov__getInstrumentsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntitiesByTypeResponse'] = pds_nasa_gov__searchEntitiesByTypeResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntitiesByTypeResponse'] = pds_nasa_gov__searchEntitiesByTypeResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetRelatedEntitites'] = pds_nasa_gov__getDataSetRelatedEntitites_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetRelatedEntitites'] = pds_nasa_gov__getDataSetRelatedEntitites_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntitiesResponse'] = pds_nasa_gov__searchEntitiesResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntitiesResponse'] = pds_nasa_gov__searchEntitiesResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrumentsInfoResponse'] = pds_nasa_gov__getInstrumentsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrumentsInfoResponse'] = pds_nasa_gov__getInstrumentsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetResponse'] = pds_nasa_gov__getTargetResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetResponse'] = pds_nasa_gov__getTargetResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataFileResponse'] = pds_nasa_gov__getDataFileResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataFileResponse'] = pds_nasa_gov__getDataFileResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetsInfo'] = pds_nasa_gov__getTargetsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetsInfo'] = pds_nasa_gov__getTargetsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTarget'] = pds_nasa_gov__getTarget_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTarget'] = pds_nasa_gov__getTarget_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetsInfo'] = pds_nasa_gov__getDataSetsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetsInfo'] = pds_nasa_gov__getDataSetsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetTypesInfoResponse'] = pds_nasa_gov__getTargetTypesInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetTypesInfoResponse'] = pds_nasa_gov__getTargetTypesInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}DataSetProcessingException'] = pds_nasa_gov__DataSetProcessingException_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}DataSetProcessingException'] = pds_nasa_gov__DataSetProcessingException_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}metadataObject'] = pds_nasa_gov__metadataObject_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}metadataObject'] = pds_nasa_gov__metadataObject_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}pagedResults'] = pds_nasa_gov__pagedResults_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}pagedResults'] = pds_nasa_gov__pagedResults_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetRelatedEntititesResponse'] = pds_nasa_gov__getDataSetRelatedEntititesResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetRelatedEntititesResponse'] = pds_nasa_gov__getDataSetRelatedEntititesResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}property'] = pds_nasa_gov__property_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}property'] = pds_nasa_gov__property_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrument'] = pds_nasa_gov__getInstrument_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrument'] = pds_nasa_gov__getInstrument_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDocumentsInfo'] = pds_nasa_gov__getDocumentsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDocumentsInfo'] = pds_nasa_gov__getDocumentsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getPreviewImageURLResponse'] = pds_nasa_gov__getPreviewImageURLResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getPreviewImageURLResponse'] = pds_nasa_gov__getPreviewImageURLResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}namedEntity'] = pds_nasa_gov__namedEntity_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}namedEntity'] = pds_nasa_gov__namedEntity_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetTypesInfo'] = pds_nasa_gov__getTargetTypesInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetTypesInfo'] = pds_nasa_gov__getTargetTypesInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}restriction'] = pds_nasa_gov__restriction_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}restriction'] = pds_nasa_gov__restriction_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getPreviewImageURL'] = pds_nasa_gov__getPreviewImageURL_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getPreviewImageURL'] = pds_nasa_gov__getPreviewImageURL_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchDataSetsByCriteria'] = pds_nasa_gov__searchDataSetsByCriteria_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchDataSetsByCriteria'] = pds_nasa_gov__searchDataSetsByCriteria_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}wsDataFile'] = pds_nasa_gov__wsDataFile_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}wsDataFile'] = pds_nasa_gov__wsDataFile_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMissionsInfo'] = pds_nasa_gov__getMissionsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMissionsInfo'] = pds_nasa_gov__getMissionsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}identifiableEntity'] = pds_nasa_gov__identifiableEntity_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}identifiableEntity'] = pds_nasa_gov__identifiableEntity_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getImagesInfo'] = pds_nasa_gov__getImagesInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getImagesInfo'] = pds_nasa_gov__getImagesInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getImagesInfoResponse'] = pds_nasa_gov__getImagesInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getImagesInfoResponse'] = pds_nasa_gov__getImagesInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchDataSetsByCriteriaResponse'] = pds_nasa_gov__searchDataSetsByCriteriaResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchDataSetsByCriteriaResponse'] = pds_nasa_gov__searchDataSetsByCriteriaResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}page'] = pds_nasa_gov__page_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}page'] = pds_nasa_gov__page_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrumentsInfo'] = pds_nasa_gov__getInstrumentsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrumentsInfo'] = pds_nasa_gov__getInstrumentsInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataFile'] = pds_nasa_gov__getDataFile_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataFile'] = pds_nasa_gov__getDataFile_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTarget'] = pds_nasa_gov__getTarget_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTarget'] = pds_nasa_gov__getTarget_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}target'] = pds_nasa_gov__target_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}target'] = pds_nasa_gov__target_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}volume'] = pds_nasa_gov__volume_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}volume'] = pds_nasa_gov__volume_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetResponse'] = pds_nasa_gov__getDataSetResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetResponse'] = pds_nasa_gov__getDataSetResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMissionResponse'] = pds_nasa_gov__getMissionResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMissionResponse'] = pds_nasa_gov__getMissionResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrumentResponse'] = pds_nasa_gov__getInstrumentResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrumentResponse'] = pds_nasa_gov__getInstrumentResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetsInfoResponse'] = pds_nasa_gov__getDataSetsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetsInfoResponse'] = pds_nasa_gov__getDataSetsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchResults'] = pds_nasa_gov__searchResults_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchResults'] = pds_nasa_gov__searchResults_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}mission'] = pds_nasa_gov__mission_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}mission'] = pds_nasa_gov__mission_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMissionsInfoResponse'] = pds_nasa_gov__getMissionsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMissionsInfoResponse'] = pds_nasa_gov__getMissionsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetTypeResponse'] = pds_nasa_gov__getTargetTypeResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetTypeResponse'] = pds_nasa_gov__getTargetTypeResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntitiesByType'] = pds_nasa_gov__searchEntitiesByType_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntitiesByType'] = pds_nasa_gov__searchEntitiesByType_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}instrument'] = pds_nasa_gov__instrument_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}instrument'] = pds_nasa_gov__instrument_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}entityInfo'] = pds_nasa_gov__entityInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}entityInfo'] = pds_nasa_gov__entityInfo_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}instrumentHost'] = pds_nasa_gov__instrumentHost_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}instrumentHost'] = pds_nasa_gov__instrumentHost_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}reference'] = pds_nasa_gov__reference_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}reference'] = pds_nasa_gov__reference_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSet'] = pds_nasa_gov__getDataSet_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSet'] = pds_nasa_gov__getDataSet_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntities'] = pds_nasa_gov__searchEntities_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntities'] = pds_nasa_gov__searchEntities_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDocumentsInfoResponse'] = pds_nasa_gov__getDocumentsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDocumentsInfoResponse'] = pds_nasa_gov__getDocumentsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}targetType'] = pds_nasa_gov__targetType_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}targetType'] = pds_nasa_gov__targetType_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetsInfoResponse'] = pds_nasa_gov__getTargetsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetsInfoResponse'] = pds_nasa_gov__getTargetsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getMission'] = pds_nasa_gov__getMission_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getMission'] = pds_nasa_gov__getMission_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetType'] = pds_nasa_gov__getTargetType_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetType'] = pds_nasa_gov__getTargetType_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntitiesByTypeResponse'] = pds_nasa_gov__searchEntitiesByTypeResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntitiesByTypeResponse'] = pds_nasa_gov__searchEntitiesByTypeResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataSetRelatedEntitites'] = pds_nasa_gov__getDataSetRelatedEntitites_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataSetRelatedEntitites'] = pds_nasa_gov__getDataSetRelatedEntitites_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchEntitiesResponse'] = pds_nasa_gov__searchEntitiesResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchEntitiesResponse'] = pds_nasa_gov__searchEntitiesResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}dataSet'] = pds_nasa_gov__dataSet_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}dataSet'] = pds_nasa_gov__dataSet_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getInstrumentsInfoResponse'] = pds_nasa_gov__getInstrumentsInfoResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getInstrumentsInfoResponse'] = pds_nasa_gov__getInstrumentsInfoResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetResponse'] = pds_nasa_gov__getTargetResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetResponse'] = pds_nasa_gov__getTargetResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}searchCriteria'] = pds_nasa_gov__searchCriteria_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}searchCriteria'] = pds_nasa_gov__searchCriteria_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getDataFileResponse'] = pds_nasa_gov__getDataFileResponse_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getDataFileResponse'] = pds_nasa_gov__getDataFileResponse_deserialize;
    this.globalElementSerializers['{http://pds.nasa.gov/}getTargetsInfo'] = pds_nasa_gov__getTargetsInfo_serialize;
    this.globalElementDeserializers['{http://pds.nasa.gov/}getTargetsInfo'] = pds_nasa_gov__getTargetsInfo_deserialize;
}

function pds_nasa_gov__getTargetsInfo_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getTargetsInfoResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getTargetsInfoResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetsInfo_onsuccess = pds_nasa_gov__getTargetsInfo_op_onsuccess;

function pds_nasa_gov__getTargetsInfo_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetsInfo_onerror = pds_nasa_gov__getTargetsInfo_op_onerror;

//
// Operation {http://pds.nasa.gov/}getTargetsInfo
// Wrapped operation.
// parameter page
// - Object constructor is pds_nasa_gov__page
// parameter restriction
// - Object constructor is pds_nasa_gov__restriction
//
function pds_nasa_gov__getTargetsInfo_op(successCallback, errorCallback, page, restriction) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = page;
    args[1] = restriction;
    xml = this.getTargetsInfo_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getTargetsInfo_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getTargetsInfo_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetsInfo = pds_nasa_gov__getTargetsInfo_op;

function pds_nasa_gov__getTargetsInfo_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getTargetsInfo();
    wrapperObj.setPage(args[0]);
    wrapperObj.setRestriction(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getTargetsInfo', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetsInfo_serializeInput = pds_nasa_gov__getTargetsInfo_serializeInput;

function pds_nasa_gov__getTargetsInfoResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getTargetsInfoResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getPreviewImageURL_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getPreviewImageURLResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getPreviewImageURLResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getPreviewImageURL_onsuccess = pds_nasa_gov__getPreviewImageURL_op_onsuccess;

function pds_nasa_gov__getPreviewImageURL_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getPreviewImageURL_onerror = pds_nasa_gov__getPreviewImageURL_op_onerror;

//
// Operation {http://pds.nasa.gov/}getPreviewImageURL
// Wrapped operation.
// parameter imageFileId
// - simple type {http://www.w3.org/2001/XMLSchema}long//
function pds_nasa_gov__getPreviewImageURL_op(successCallback, errorCallback, imageFileId) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = imageFileId;
    xml = this.getPreviewImageURL_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getPreviewImageURL_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getPreviewImageURL_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getPreviewImageURL = pds_nasa_gov__getPreviewImageURL_op;

function pds_nasa_gov__getPreviewImageURL_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getPreviewImageURL();
    wrapperObj.setImageFileId(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getPreviewImageURL', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getPreviewImageURL_serializeInput = pds_nasa_gov__getPreviewImageURL_serializeInput;

function pds_nasa_gov__getPreviewImageURLResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getPreviewImageURLResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getInstrument_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getInstrumentResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getInstrumentResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrument_onsuccess = pds_nasa_gov__getInstrument_op_onsuccess;

function pds_nasa_gov__getInstrument_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrument_onerror = pds_nasa_gov__getInstrument_op_onerror;

//
// Operation {http://pds.nasa.gov/}getInstrument
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}long//
function pds_nasa_gov__getInstrument_op(successCallback, errorCallback, id) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = id;
    xml = this.getInstrument_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getInstrument_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getInstrument_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrument = pds_nasa_gov__getInstrument_op;

function pds_nasa_gov__getInstrument_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getInstrument();
    wrapperObj.setId(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getInstrument', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrument_serializeInput = pds_nasa_gov__getInstrument_serializeInput;

function pds_nasa_gov__getInstrumentResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getInstrumentResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__searchDataSetsByCriteria_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__searchDataSetsByCriteriaResponse_deserializeResponse');
     responseObject = pds_nasa_gov__searchDataSetsByCriteriaResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchDataSetsByCriteria_onsuccess = pds_nasa_gov__searchDataSetsByCriteria_op_onsuccess;

function pds_nasa_gov__searchDataSetsByCriteria_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchDataSetsByCriteria_onerror = pds_nasa_gov__searchDataSetsByCriteria_op_onerror;

//
// Operation {http://pds.nasa.gov/}searchDataSetsByCriteria
// Wrapped operation.
// parameter criteria
// - Object constructor is pds_nasa_gov__searchCriteria
// parameter page
// - Object constructor is pds_nasa_gov__page
//
function pds_nasa_gov__searchDataSetsByCriteria_op(successCallback, errorCallback, criteria, page) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = criteria;
    args[1] = page;
    xml = this.searchDataSetsByCriteria_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.searchDataSetsByCriteria_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.searchDataSetsByCriteria_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchDataSetsByCriteria = pds_nasa_gov__searchDataSetsByCriteria_op;

function pds_nasa_gov__searchDataSetsByCriteria_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__searchDataSetsByCriteria();
    wrapperObj.setCriteria(args[0]);
    wrapperObj.setPage(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:searchDataSetsByCriteria', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchDataSetsByCriteria_serializeInput = pds_nasa_gov__searchDataSetsByCriteria_serializeInput;

function pds_nasa_gov__searchDataSetsByCriteriaResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__searchDataSetsByCriteriaResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getTarget_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getTargetResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getTargetResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTarget_onsuccess = pds_nasa_gov__getTarget_op_onsuccess;

function pds_nasa_gov__getTarget_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTarget_onerror = pds_nasa_gov__getTarget_op_onerror;

//
// Operation {http://pds.nasa.gov/}getTarget
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}long//
function pds_nasa_gov__getTarget_op(successCallback, errorCallback, id) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = id;
    xml = this.getTarget_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getTarget_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getTarget_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTarget = pds_nasa_gov__getTarget_op;

function pds_nasa_gov__getTarget_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getTarget();
    wrapperObj.setId(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getTarget', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTarget_serializeInput = pds_nasa_gov__getTarget_serializeInput;

function pds_nasa_gov__getTargetResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getTargetResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getTargetTypesInfo_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getTargetTypesInfoResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getTargetTypesInfoResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetTypesInfo_onsuccess = pds_nasa_gov__getTargetTypesInfo_op_onsuccess;

function pds_nasa_gov__getTargetTypesInfo_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetTypesInfo_onerror = pds_nasa_gov__getTargetTypesInfo_op_onerror;

//
// Operation {http://pds.nasa.gov/}getTargetTypesInfo
// Wrapped operation.
// parameter page
// - Object constructor is pds_nasa_gov__page
//
function pds_nasa_gov__getTargetTypesInfo_op(successCallback, errorCallback, page) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = page;
    xml = this.getTargetTypesInfo_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getTargetTypesInfo_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getTargetTypesInfo_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetTypesInfo = pds_nasa_gov__getTargetTypesInfo_op;

function pds_nasa_gov__getTargetTypesInfo_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getTargetTypesInfo();
    wrapperObj.setPage(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getTargetTypesInfo', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetTypesInfo_serializeInput = pds_nasa_gov__getTargetTypesInfo_serializeInput;

function pds_nasa_gov__getTargetTypesInfoResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getTargetTypesInfoResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getMission_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getMissionResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getMissionResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMission_onsuccess = pds_nasa_gov__getMission_op_onsuccess;

function pds_nasa_gov__getMission_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMission_onerror = pds_nasa_gov__getMission_op_onerror;

//
// Operation {http://pds.nasa.gov/}getMission
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}long//
function pds_nasa_gov__getMission_op(successCallback, errorCallback, id) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = id;
    xml = this.getMission_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getMission_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getMission_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMission = pds_nasa_gov__getMission_op;

function pds_nasa_gov__getMission_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getMission();
    wrapperObj.setId(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getMission', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMission_serializeInput = pds_nasa_gov__getMission_serializeInput;

function pds_nasa_gov__getMissionResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getMissionResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getMissionsInfo_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getMissionsInfoResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getMissionsInfoResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMissionsInfo_onsuccess = pds_nasa_gov__getMissionsInfo_op_onsuccess;

function pds_nasa_gov__getMissionsInfo_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMissionsInfo_onerror = pds_nasa_gov__getMissionsInfo_op_onerror;

//
// Operation {http://pds.nasa.gov/}getMissionsInfo
// Wrapped operation.
// parameter page
// - Object constructor is pds_nasa_gov__page
// parameter restriction
// - Object constructor is pds_nasa_gov__restriction
//
function pds_nasa_gov__getMissionsInfo_op(successCallback, errorCallback, page, restriction) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = page;
    args[1] = restriction;
    xml = this.getMissionsInfo_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getMissionsInfo_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getMissionsInfo_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMissionsInfo = pds_nasa_gov__getMissionsInfo_op;

function pds_nasa_gov__getMissionsInfo_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getMissionsInfo();
    wrapperObj.setPage(args[0]);
    wrapperObj.setRestriction(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getMissionsInfo', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getMissionsInfo_serializeInput = pds_nasa_gov__getMissionsInfo_serializeInput;

function pds_nasa_gov__getMissionsInfoResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getMissionsInfoResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getDocumentsInfo_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getDocumentsInfoResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getDocumentsInfoResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDocumentsInfo_onsuccess = pds_nasa_gov__getDocumentsInfo_op_onsuccess;

function pds_nasa_gov__getDocumentsInfo_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDocumentsInfo_onerror = pds_nasa_gov__getDocumentsInfo_op_onerror;

//
// Operation {http://pds.nasa.gov/}getDocumentsInfo
// Wrapped operation.
// parameter page
// - Object constructor is pds_nasa_gov__page
// parameter restriction
// - Object constructor is pds_nasa_gov__restriction
//
function pds_nasa_gov__getDocumentsInfo_op(successCallback, errorCallback, page, restriction) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = page;
    args[1] = restriction;
    xml = this.getDocumentsInfo_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getDocumentsInfo_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getDocumentsInfo_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDocumentsInfo = pds_nasa_gov__getDocumentsInfo_op;

function pds_nasa_gov__getDocumentsInfo_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getDocumentsInfo();
    wrapperObj.setPage(args[0]);
    wrapperObj.setRestriction(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getDocumentsInfo', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDocumentsInfo_serializeInput = pds_nasa_gov__getDocumentsInfo_serializeInput;

function pds_nasa_gov__getDocumentsInfoResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getDocumentsInfoResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getInstrumentsInfo_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getInstrumentsInfoResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getInstrumentsInfoResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrumentsInfo_onsuccess = pds_nasa_gov__getInstrumentsInfo_op_onsuccess;

function pds_nasa_gov__getInstrumentsInfo_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrumentsInfo_onerror = pds_nasa_gov__getInstrumentsInfo_op_onerror;

//
// Operation {http://pds.nasa.gov/}getInstrumentsInfo
// Wrapped operation.
// parameter page
// - Object constructor is pds_nasa_gov__page
// parameter restriction
// - Object constructor is pds_nasa_gov__restriction
//
function pds_nasa_gov__getInstrumentsInfo_op(successCallback, errorCallback, page, restriction) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = page;
    args[1] = restriction;
    xml = this.getInstrumentsInfo_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getInstrumentsInfo_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getInstrumentsInfo_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrumentsInfo = pds_nasa_gov__getInstrumentsInfo_op;

function pds_nasa_gov__getInstrumentsInfo_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getInstrumentsInfo();
    wrapperObj.setPage(args[0]);
    wrapperObj.setRestriction(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getInstrumentsInfo', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getInstrumentsInfo_serializeInput = pds_nasa_gov__getInstrumentsInfo_serializeInput;

function pds_nasa_gov__getInstrumentsInfoResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getInstrumentsInfoResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getImagesInfo_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getImagesInfoResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getImagesInfoResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getImagesInfo_onsuccess = pds_nasa_gov__getImagesInfo_op_onsuccess;

function pds_nasa_gov__getImagesInfo_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getImagesInfo_onerror = pds_nasa_gov__getImagesInfo_op_onerror;

//
// Operation {http://pds.nasa.gov/}getImagesInfo
// Wrapped operation.
// parameter page
// - Object constructor is pds_nasa_gov__page
// parameter restriction
// - Object constructor is pds_nasa_gov__restriction
//
function pds_nasa_gov__getImagesInfo_op(successCallback, errorCallback, page, restriction) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = page;
    args[1] = restriction;
    xml = this.getImagesInfo_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getImagesInfo_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getImagesInfo_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getImagesInfo = pds_nasa_gov__getImagesInfo_op;

function pds_nasa_gov__getImagesInfo_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getImagesInfo();
    wrapperObj.setPage(args[0]);
    wrapperObj.setRestriction(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getImagesInfo', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getImagesInfo_serializeInput = pds_nasa_gov__getImagesInfo_serializeInput;

function pds_nasa_gov__getImagesInfoResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getImagesInfoResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getDataSetRelatedEntitites_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getDataSetRelatedEntititesResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getDataSetRelatedEntititesResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetRelatedEntitites_onsuccess = pds_nasa_gov__getDataSetRelatedEntitites_op_onsuccess;

function pds_nasa_gov__getDataSetRelatedEntitites_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetRelatedEntitites_onerror = pds_nasa_gov__getDataSetRelatedEntitites_op_onerror;

//
// Operation {http://pds.nasa.gov/}getDataSetRelatedEntitites
// Wrapped operation.
// parameter dataSetId
// - simple type {http://www.w3.org/2001/XMLSchema}long// parameter relatedEntityType
// - simple type {http://www.w3.org/2001/XMLSchema}string//
function pds_nasa_gov__getDataSetRelatedEntitites_op(successCallback, errorCallback, dataSetId, relatedEntityType) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = dataSetId;
    args[1] = relatedEntityType;
    xml = this.getDataSetRelatedEntitites_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getDataSetRelatedEntitites_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getDataSetRelatedEntitites_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetRelatedEntitites = pds_nasa_gov__getDataSetRelatedEntitites_op;

function pds_nasa_gov__getDataSetRelatedEntitites_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getDataSetRelatedEntitites();
    wrapperObj.setDataSetId(args[0]);
    wrapperObj.setRelatedEntityType(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getDataSetRelatedEntitites', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetRelatedEntitites_serializeInput = pds_nasa_gov__getDataSetRelatedEntitites_serializeInput;

function pds_nasa_gov__getDataSetRelatedEntititesResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getDataSetRelatedEntititesResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__searchEntitiesByType_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__searchEntitiesByTypeResponse_deserializeResponse');
     responseObject = pds_nasa_gov__searchEntitiesByTypeResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntitiesByType_onsuccess = pds_nasa_gov__searchEntitiesByType_op_onsuccess;

function pds_nasa_gov__searchEntitiesByType_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntitiesByType_onerror = pds_nasa_gov__searchEntitiesByType_op_onerror;

//
// Operation {http://pds.nasa.gov/}searchEntitiesByType
// Wrapped operation.
// parameter entityType
// - simple type {http://www.w3.org/2001/XMLSchema}string// parameter searchText
// - simple type {http://www.w3.org/2001/XMLSchema}string// parameter page
// - Object constructor is pds_nasa_gov__page
// parameter restriction
// - Object constructor is pds_nasa_gov__restriction
//
function pds_nasa_gov__searchEntitiesByType_op(successCallback, errorCallback, entityType, searchText, page, restriction) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(4);
    args[0] = entityType;
    args[1] = searchText;
    args[2] = page;
    args[3] = restriction;
    xml = this.searchEntitiesByType_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.searchEntitiesByType_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.searchEntitiesByType_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntitiesByType = pds_nasa_gov__searchEntitiesByType_op;

function pds_nasa_gov__searchEntitiesByType_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__searchEntitiesByType();
    wrapperObj.setEntityType(args[0]);
    wrapperObj.setSearchText(args[1]);
    wrapperObj.setPage(args[2]);
    wrapperObj.setRestriction(args[3]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:searchEntitiesByType', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntitiesByType_serializeInput = pds_nasa_gov__searchEntitiesByType_serializeInput;

function pds_nasa_gov__searchEntitiesByTypeResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__searchEntitiesByTypeResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getDataSet_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getDataSetResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getDataSetResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSet_onsuccess = pds_nasa_gov__getDataSet_op_onsuccess;

function pds_nasa_gov__getDataSet_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSet_onerror = pds_nasa_gov__getDataSet_op_onerror;

//
// Operation {http://pds.nasa.gov/}getDataSet
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}long//
function pds_nasa_gov__getDataSet_op(successCallback, errorCallback, id) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = id;
    xml = this.getDataSet_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getDataSet_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getDataSet_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSet = pds_nasa_gov__getDataSet_op;

function pds_nasa_gov__getDataSet_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getDataSet();
    wrapperObj.setId(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getDataSet', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSet_serializeInput = pds_nasa_gov__getDataSet_serializeInput;

function pds_nasa_gov__getDataSetResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getDataSetResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getDataSetsInfo_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getDataSetsInfoResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getDataSetsInfoResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetsInfo_onsuccess = pds_nasa_gov__getDataSetsInfo_op_onsuccess;

function pds_nasa_gov__getDataSetsInfo_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetsInfo_onerror = pds_nasa_gov__getDataSetsInfo_op_onerror;

//
// Operation {http://pds.nasa.gov/}getDataSetsInfo
// Wrapped operation.
// parameter page
// - Object constructor is pds_nasa_gov__page
// parameter restriction
// - Object constructor is pds_nasa_gov__restriction
//
function pds_nasa_gov__getDataSetsInfo_op(successCallback, errorCallback, page, restriction) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = page;
    args[1] = restriction;
    xml = this.getDataSetsInfo_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getDataSetsInfo_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getDataSetsInfo_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetsInfo = pds_nasa_gov__getDataSetsInfo_op;

function pds_nasa_gov__getDataSetsInfo_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getDataSetsInfo();
    wrapperObj.setPage(args[0]);
    wrapperObj.setRestriction(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getDataSetsInfo', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataSetsInfo_serializeInput = pds_nasa_gov__getDataSetsInfo_serializeInput;

function pds_nasa_gov__getDataSetsInfoResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getDataSetsInfoResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getDataFile_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getDataFileResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getDataFileResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataFile_onsuccess = pds_nasa_gov__getDataFile_op_onsuccess;

function pds_nasa_gov__getDataFile_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataFile_onerror = pds_nasa_gov__getDataFile_op_onerror;

//
// Operation {http://pds.nasa.gov/}getDataFile
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}long//
function pds_nasa_gov__getDataFile_op(successCallback, errorCallback, id) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = id;
    xml = this.getDataFile_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getDataFile_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getDataFile_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataFile = pds_nasa_gov__getDataFile_op;

function pds_nasa_gov__getDataFile_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getDataFile();
    wrapperObj.setId(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getDataFile', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getDataFile_serializeInput = pds_nasa_gov__getDataFile_serializeInput;

function pds_nasa_gov__getDataFileResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getDataFileResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__getTargetType_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__getTargetTypeResponse_deserializeResponse');
     responseObject = pds_nasa_gov__getTargetTypeResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetType_onsuccess = pds_nasa_gov__getTargetType_op_onsuccess;

function pds_nasa_gov__getTargetType_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetType_onerror = pds_nasa_gov__getTargetType_op_onerror;

//
// Operation {http://pds.nasa.gov/}getTargetType
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}long//
function pds_nasa_gov__getTargetType_op(successCallback, errorCallback, id) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(1);
    args[0] = id;
    xml = this.getTargetType_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getTargetType_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getTargetType_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetType = pds_nasa_gov__getTargetType_op;

function pds_nasa_gov__getTargetType_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__getTargetType();
    wrapperObj.setId(args[0]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getTargetType', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.getTargetType_serializeInput = pds_nasa_gov__getTargetType_serializeInput;

function pds_nasa_gov__getTargetTypeResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__getTargetTypeResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__searchEntities_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling pds_nasa_gov__searchEntitiesResponse_deserializeResponse');
     responseObject = pds_nasa_gov__searchEntitiesResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntities_onsuccess = pds_nasa_gov__searchEntities_op_onsuccess;

function pds_nasa_gov__searchEntities_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     client.user_onerror(httpStatus, httpStatusText);
    }
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntities_onerror = pds_nasa_gov__searchEntities_op_onerror;

//
// Operation {http://pds.nasa.gov/}searchEntities
// Wrapped operation.
// parameter searchText
// - simple type {http://www.w3.org/2001/XMLSchema}string// parameter page
// - Object constructor is pds_nasa_gov__page
//
function pds_nasa_gov__searchEntities_op(successCallback, errorCallback, searchText, page) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(2);
    args[0] = searchText;
    args[1] = page;
    xml = this.searchEntities_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.searchEntities_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.searchEntities_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntities = pds_nasa_gov__searchEntities_op;

function pds_nasa_gov__searchEntities_serializeInput(cxfjsutils, args) {
    var wrapperObj = new pds_nasa_gov__searchEntities();
    wrapperObj.setSearchText(args[0]);
    wrapperObj.setPage(args[1]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://pds.nasa.gov/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:searchEntities', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

pds_nasa_gov__PlanetaryDataSystemSEI.prototype.searchEntities_serializeInput = pds_nasa_gov__searchEntities_serializeInput;

function pds_nasa_gov__searchEntitiesResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = pds_nasa_gov__searchEntitiesResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function pds_nasa_gov__PlanetaryDataSystemSEI_pds_nasa_gov__PlanetaryDataSystemPort () {
  this.url = 'http://localhost:8080/nasa_pds_ws/services/PlanetaryDataSystemPort';
}
pds_nasa_gov__PlanetaryDataSystemSEI_pds_nasa_gov__PlanetaryDataSystemPort.prototype = new pds_nasa_gov__PlanetaryDataSystemSEI;
