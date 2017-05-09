<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<!-- ----------------------------------------------------------------- -->
<head>
<jsp:include page="includes/htmlhead.jsp"/> 
<title>NASA PDS Archive Data Base Demo: Advance Search</title>
</head>
<!-- ----------------------------------------------------------------- -->

<body>

<jsp:include page="includes/search-templates.jsp"/>

<!-- header -->
<div class="header">
<!-- header top-->
<div class="top">

<h1 class="title-text">NASA PDS Archive Data Base Demo</h1>
</div><!-- end header top-->

<!-- header tab-->
<div class="tab">
<ul>
<li class="current"><a href="${ctx}/pds/home"><span class="left-button"><span class="right-button"><span class="itext">Home</span></span></span></a></li>
<li><a href="${ctx}/pds/about"><span class="left-button"><span class="right-button"><span class="itext">About Us</span></span></span></a></li>
<li><a href="${ctx}/pds/data"><span class="left-button"><span class="right-button"><span class="itext">Data</span></span></span></a></li>
<li><a href="${ctx}/pds/developer"><span class="left-button"><span class="right-button"><span class="itext">Developer Info</span></span></span></a></li>
<li><a href="${ctx}/pds/related"><span class="left-button"><span class="right-button"><span class="itext">Related Sites</span></span></span></a></li>
<li><a href="${ctx}/pds/contact"><span class="left-button"><span class="right-button"><span class="itext">Contact Us</span></span></span></a></li>
</ul>
</div><!-- end header tab-->
</div><!-- end header -->

<!-- crumbs -->
<div class="crumbs">Advanced Search &gt;&gt;</div><!-- end crumbs -->

<!-- wrapper -->
<div class="wrapper">
<!-- heading -->
<div class="heading advance-spacing">
<h2>Advanced Search</h2>
<p>Select the Needed Parameters, you can create as many parameters as needed and generate the results</p>
</div><!-- end heading -->

<!-- data -->
<div class="data-box advance">
<!-- table main -->
<div class="table-main">
<table border="0" cellpadding="0" cellspacing="0" class="table-data">
<colgroup>
<col width="50" />
<col width="235" />
<col width="300" />
<col width="265" />
<col width="115" />
</colgroup>
<tr>
<th>&nbsp;</th>
<th>Select Parameter</th>
<th>Values</th>
<th>Condition</th>
<th class="right-bg-none">Add More</th>
</tr>

<tr>
<td colspan="5" class="content-data">
<table id="searchCriteriaTable" border="0" cellspacing="0" cellpadding="0">
<col width="41" />
<col width="235" />
<col width="300" />
<col width="265" />
<col width="105" />
</table>
</td>
</tr>

</table>
</div>
</div><!-- end data -->
<!-- search btn -->
<div class="search-btn-box">
<a href="javascript:;" class="search-link">
<span class="left-button"><span class="right-button"><span class="itext">SEARCH</span></span></span>
</a>
</div><!-- end search btn -->
</div><!-- end wrapper -->

<!-- footer -->
<div class="footer">
<span class="left">All NASA PDS data are public domain. All code presented is open source.</span>
</div><!-- end footer -->

<div class="footer2">
	<div class="photo">
	<a href="http://www.nasa.gov/" target="_blank"><img src="${ctx}/images/logo-nasa.png" width="100" alt="NASA logo" /></a>
	<a href="http://science.nasa.gov/" target="_blank"><img src="${ctx}/images/logo-PSD.png" width="100" alt="PSD logo" /></a>
	<a href="http://www.harvard.edu/" target="_blank"><img src="${ctx}/images/logo-harvard.png" width="100" alt="Harvard logo" /></a>
	<a href="http://pds.nasa.gov/" target="_blank"><img src="${ctx}/images/logo-pds-wh.jpg" width="100" alt="PDS logo" /></a>
	<a href="http://www.umd.edu/" target="_blank"><img src="${ctx}/images/logo-umd.png" width="100" alt="UMD logo" /></a>
	</div>
This website is currently hosted and maintained by the <a href="http://pdssbn.astro.umd.edu/">Small Bodies Node</a> of the <a href="http://pds.nasa.gov/">Planetary Data System</a>.
</div><!-- end footer2 -->
</body>
</html>
