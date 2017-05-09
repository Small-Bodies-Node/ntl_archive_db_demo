<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<!-- ----------------------------------------------------------------- -->
<head>
<jsp:include page="includes/htmlhead.jsp"/> 
<title>NASA PDS Archive Data Base Demo: Data</title>
</head>
<!-- ----------------------------------------------------------------- -->

<body>
<!-- header -->
<div class="header">
<!-- header top-->
<div class="top">

<h1 class="title-text">NASA PDS Archive Data Base Demo</h1>
</div><!-- end header top-->

<!-- header tab-->
<div class="tab">
<ul>
<li><a href="${ctx}/pds/home"><span class="left-button"><span class="right-button"><span class="itext">Home</span></span></span></a></li>
<li><a href="${ctx}/pds/about"><span class="left-button"><span class="right-button"><span class="itext">About Us</span></span></span></a></li>
<li class="current"><a href="data.jsp"><span class="left-button"><span class="right-button"><span class="itext">Data</span></span></span></a></li>
<li><a href="${ctx}/pds/developer"><span class="left-button"><span class="right-button"><span class="itext">Developer Info</span></span></span></a></li>
<li><a href="${ctx}/pds/related"><span class="left-button"><span class="right-button"><span class="itext">Related Sites</span></span></span></a></li>
<li><a href="${ctx}/pds/contact"><span class="left-button"><span class="right-button"><span class="itext">Contact Us</span></span></span></a></li>
</ul>
</div><!-- end header tab-->
</div><!-- end header -->

<!-- crumbs -->
<div class="crumbs"><a href="${ctx}/pds/home">Home &gt;&gt;</a> Data</div><!-- end crumbs -->

<!-- wrapper -->
<div class="wrapper">
<!-- top content -->
<div class="top-content">
<h2>Data in the PDS Archive Data Base Demo</h2>
<div class="text-box">

<div class="texts">

	This database contains metadata for most of the comet holdings of the Small Bodies Node.
	The metadata include descriptions of missions, spacecraft, and instruments as well as the
  descriptions of the data sets themselves.  Additional data sets will be added periodically 
  as new data are incorporated into the archive.
<br />
<br />
	The full data sets can all be found in the <a href="http://pdssbn.astro.umd.edu/data_sb/index.shtml">
Small Bodies Data Archives</a>.
<br />
<br />
	The last data set update was on 1 June, 2013.

</div>

</div>
</div><!-- end top content -->

<!-- search box -->
<div class="search-box">
<!-- quick search -->
<div class="dl-box">
<dl class="quick-search dl-border-right">
<dt>Quick Search</dt>
<dd>Begin your search from the Target Types, Start browsing the Planetary Data Set.</dd>
</dl>
<span>
<a href="browse" class="green-link">Click here</a>
</span>
</div><!-- end quick search -->

<!-- advanced search -->
<div class="dl-box">
<dl class="advanced-search dl-border-right">
<dt>Advanced Search</dt>
<dd>Advanced Search is for experienced users with detailed knowledge of PDS mission data and science.</dd>
</dl>
<span>
<a href="search/advanced" class="green-link">Click here</a>
</span>
</div><!-- end advanced search -->

</div><!-- end search box -->
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
