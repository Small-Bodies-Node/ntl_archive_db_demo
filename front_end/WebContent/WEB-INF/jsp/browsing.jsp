<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<!-- ----------------------------------------------------------------- -->
<head>
<jsp:include page="includes/htmlhead.jsp"/>
<script src="${pageContext.request.contextPath}/js/browsingViews.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/pds-browsing.js" type="text/javascript"></script>
<title>NASA PDS Archive Data Base Demo: Browsing</title>
</head>
<!-- ----------------------------------------------------------------- -->

<body>

<jsp:include page="includes/search-templates.jsp"/>
<jsp:include page="includes/browsing-templates.jsp"/>

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
<div class="crumbs">Browsing by Target Types &gt;&gt;</div><!-- end crumbs -->

<!-- wrapper -->
<div class="wrapper">
<!-- heading -->
<div class="heading heading-bottom">
<h2>Browsing by Target Types</h2>
<p>
 Below is the list of Target Types, Click on any to access the specific Targets, You can also find the white spot and Correlated 
 Results by selecting White Spot and Correlated Results Respectively
 </p>
</div><!-- end heading -->
<div class="container">
<!-- sidebar -->
<div class="sidebar hide">
<h2 class="search-h2">Search</h2>
<div class="search-main box">
<!-- search dl -->
<dl class="search-dl">
<dt>Recent Searches</dt>
 <!-- 
 <dd><a href="browsing-data-sets-level.html">Spectrometer</a>(11)</dd>
<dd><a href="browsing-data-sets-level.html">Plasma Analyzer</a>(1)</dd>
<dd><a href="browsing-data-sets-level.html">Camera</a>(5)</dd>
<dd><a href="browsing-data-sets-level.html">Altimeter</a>(11)</dd>
<dd><a href="browsing-data-sets-level.html">Radio Science</a>(3)</dd>
<dd><a href="browsing-data-sets-level.html">Other</a>(8)</dd>
 -->
<dd><h1>WIP</h1></dd>
</dl>
<!-- search dl -->
<dl class="search-dl">
<dt>Saved Searches</dt>
<!-- 
 <dd><a href="browsing-missions-level.html">My search mission</a>(101)</dd>
<dd><a href="browsing-missions-level.html">My search</a>(1100)</dd>
<dd><a href="browsing-missions-level.html">My result</a>(1000)</dd>
<dd><a href="browsing-instruments-level.html">My instruments</a>(11)</dd>
<dd><a href="browsing-missions-level.html">My work</a>(1100)</dd>
<dd><a href="browsing-missions-level.html">My achivement</a>(101)</dd>
 -->
<dd><h1>WIP</h1></dd>
</dl>

</div>
</div><!-- end sidebar -->

<!-- main content -->
<div class="main-content">

<!-- ul sub tab -->
<ul class="sub-tab">
<li class="current"><a href="javascript:;">Search Criteria</a></li>

<!-- We haven't made final decision about these tabs 
 <li><a href="javascript:;">White spots</a></li>
<li class="size"><a href="javascript:;">Search Correlation</a></li>
 -->

</ul>
<!-- contents -->
<div class="contents">
<!-- sub tab content -->
<div class="sub-tab-content">
<div class="advanced-search">
<span><a href="${ctx}/pds/search/advanced/">Advanced Search</a></span>
</div>
<h3>Enter Search Criteria:</h3>
<div class="input-btn">
<div class="inputs">
<span class="left">
<span class="right">
<input name="" type="text" class="blue-reset"/>
</span>
</span>
</div>
</div>

<a href="javascript:;" class="search-btn">
<span class="left-button"><span class="right-button"><span class="itext">Search</span></span></span>
</a>
<p class="data-p-text">Modify your search by selecting the appropriate Quick search or enter a criteria in the below Search Textbox</p>
</div>

<!-- Search results -->
<div id="searchResults" class="sub-tab-content hide">
<ul class="data-bar">
<li>
<span><em>Search results</em><a id="searchResultsCloseBtn" href="javascript:;" title="close"></a></span>
</li>
</ul>
<ul id ="searchResultsList">
</ul>

<!-- in case nothing was found -->
<span id="notFoundMessage" class="hide" style="font-size:130%;padding-left:20px;">
Your search did not match any documents
</span>
</div>

<!-- Navigation bar -->
<ul id="navigationBar" class="data-bar">
</ul>

<!-- data -->
<div id="mainView" class="data-box data-spacing">
<!-- top page text -->

<div id="viewInfo" class="top-page-text">
</div>

<div class="table-main">

<table border="0" cellpadding="0" cellspacing="0" class="table-data">
<colgroup id="main-colgroup">
</colgroup>

<tr id="main-heading">
</tr>

<tr>
 <td colspan="6" class="content-data">

<!-- info about selected entity -->
<div id="entityInfoView" class="hide">
<pre></pre>
</div>

 <!-- main navigation view -->
 <table id="navigationView" border="0" cellspacing="0" cellpadding="0">
 </table>

<!-- pagination controls -->
<div id="paginationControls" style="border-top:1px solid #9A9A9A;padding-top:15px;padding-bottom:10px;">
<span class="left">
Show:
<select id="itemsPerPage" name="">
<option value="5 ">5</option>
<option value="10" selected="selected">10</option>
<option value="15">15</option>
<option value="20">20</option>
<option value="50">50</option>
<option value="all">All</option>
</select>
</span>

<div id="pagination" class="pagination" style="float:right;">
</div>
</div>

</td>
</tr>
</table>
</div>
</div><!-- end data -->
</div><!-- end contents -->
</div><!-- end main content -->

</div>
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
