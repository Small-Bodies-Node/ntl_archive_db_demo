<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<!-- ----------------------------------------------------------------- -->
<head>
<jsp:include page="includes/htmlhead.jsp"/> 
<title>NASA PDS Archive Data Base Demo</title>
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
<li class="current"><a href="home.jsp"><span class="left-button"><span class="right-button"><span class="itext">Home</span></span></span></a></li>
<li><a href="${ctx}/pds/about"><span class="left-button"><span class="right-button"><span class="itext">About Us</span></span></span></a></li>
<li><a href="${ctx}/pds/data"><span class="left-button"><span class="right-button"><span class="itext">Data</span></span></span></a></li>
<li><a href="${ctx}/pds/developer"><span class="left-button"><span class="right-button"><span class="itext">Developer Info</span></span></span></a></li>
<li><a href="${ctx}/pds/related"><span class="left-button"><span class="right-button"><span class="itext">Related Sites</span></span></span></a></li>
<li><a href="${ctx}/pds/contact"><span class="left-button"><span class="right-button"><span class="itext">Contact Us</span></span></span></a></li>
</ul>
</div><!-- end header tab-->
</div><!-- end header -->

<!-- crumbs -->
<div class="crumbs">Home &gt;&gt;</div><!-- end crumbs -->

<!-- wrapper -->
<div class="wrapper">
<!-- top content -->
<div class="top-content">
<h2>Welcome to the PDS Archive Data Base Demo</h2>
	<div class="text-box">
		<div class="texts">
		The Planetary Data System (PDS) Small Bodies Node (SBN) Archive Data Base Demo presented here integrates observations of comets from the SBN data archives into a single data base with an API designed to accommodate application development. The database, API development, and prototype search below were all designed and coded as part of a series of challenges posed by the NASA Tournament Lab. The API underlying the search is open source, and available for download from the &quot;<a href="developer.jsp">Developer Info</a>&quot; page. The search itself was developed as a rapid prototype, and gives just a small taste of the possibilities open to developers interested in using PDS data in apps for scientists, amateurs, teachers, and the general public.
		<br /><br />All our archive data is public domain, as is all PDS data. We encourage developers to make use of these archives - the direct and enduring legacy of NASA's planetary science missions - in new and exciting ways. If you'd like to know more about our archives, please visit the <a href="http://pdssbn.astro.umd.edu">SBN home page</a> or the <a href="pds.nasa.gov">PDS home page</a>.
		<br /><br />And if you do use this database and/or API in your development, please let us know! And please also give us a shout-out in whatever credits you might include in your app.
</div><!-- end texts -->
	</div><!-- end text-box -->

<h2>Data Base status</h2>
	<div class="text-box">
		<div class="texts">
		The database was loaded with the contents of over 200 SBN comet data sets. The last data set update was on 1 June, 2013.
		</div><!-- end texts -->
	</div><!-- end text-box -->

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
