<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<!-- ----------------------------------------------------------------- -->
<head>
<jsp:include page="includes/htmlhead.jsp"/> 
<title>NASA PDS Archive Data Base Demo: About</title>
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
<li class="current"><a href="about.jsp"><span class="left-button"><span class="right-button"><span class="itext">About Us</span></span></span></a></li>
<li><a href="${ctx}/pds/data"><span class="left-button"><span class="right-button"><span class="itext">Data</span></span></span></a></li>
<li><a href="${ctx}/pds/developer"><span class="left-button"><span class="right-button"><span class="itext">Developer Info</span></span></span></a></li>
<li><a href="${ctx}/pds/related"><span class="left-button"><span class="right-button"><span class="itext">Related Sites</span></span></span></a></li>
<li><a href="${ctx}/pds/contact"><span class="left-button"><span class="right-button"><span class="itext">Contact Us</span></span></span></a></li>
</ul>
</div><!-- end header tab-->
</div><!-- end header -->

<!-- crumbs -->
<div class="crumbs"><a href="${ctx}/pds/home">Home &gt;&gt;</a> About Us</div><!-- end crumbs -->

<!-- wrapper -->
<div class="wrapper">
<!-- top content -->
<div class="top-content">
<h2>About Us</h2>
<div class="text-box">

<div class="photo">
<a href="http://science.nasa.gov/" target="_blank"><img src="${ctx}/images/logo-PSD.png" width="128" alt="PSD logo" /></a>
</div><!-- end photo -->
<div class="texts">
<h3>NASA Planetary Science Division</h3>
<p>The Planetary Science Division is responsible for all NASA projects related to everything in our Solar System that isn't the Sun or the Earth. This includes all non-solar missions that leave low-Earth orbit to study our Moon, comets, asteroids, other moons and planets, and their environments.
<br /><a href="http://science.nasa.gov/">Learn more...</a></p>
</div><!-- end texts -->

<div class="clear"></div>

<div class="photo">
<a href="http://www.nasa.gov/directorates/heo/ntl/" target="_blank"><img src="${ctx}/images/logo-NTL.gif" width="128" alt="NTL logo" /></a>
</div><!-- end photo -->
<div class="texts">
<h3>NASA Tournament Laboratory (NTL)</h3>
<p>The NASA Tournament Lab, a cooperative venture between NASA and Harvard University working through the TopCoder community, sponsors competitions competitions aimed at creating new, innovative, efficient solutions for a wide range of real-world problems experienced by NASA researchers. NTL is part of NASA's Human Exploration and Operations directorate.
<br /><a href="http://www.nasa.gov/directorates/heo/ntl/">Learn more...</a></p>
</div><!-- end texts -->

<div class="clear"></div>

<div class="photo">
<a href="http://www.harvard.edu/" target="_blank"><img src="${ctx}/images/logo-harvard.png" width="128" alt="Harvard logo" /></a>
</div><!-- end photo -->
<div class="texts">
<h3>TopCoder</h3>
<p>TopCoder is an Enterprise Open Innovation developer that leverages the contributions of its nearly half a million members through competitions to design and develop innovative solutions to the problems posed by clients like the NASA Tournament Lab.
<br /><a href="http://www.topcoder.com/">Learn more...</a></p>
</div><!-- end texts -->

<div class="clear"></div>

<div class="photo">
<a href="http://pds.nasa.gov/" target="_blank"><img src="${ctx}/images/logo-pds-wh.jpg" width="128" alt="PDS logo" /></a>
</div><!-- end photo -->
<div class="texts">
<h3>NASA Planetary Data System (PDS)</h3>
<p>The PDS is the ultimate repository of all data produced and collected by NASA missions to targets in our solar system. In addition, the PDS archives include contributions from international mission and ground-based observations. PDS defines format and content standards for the data in its archives, to make these data accessible and usable to the science community at large, and to ensure NASA's return on investment in its Planetary Science program.
<br /><a href="http://pds.nasa.gov/">Learn more...</a></p>
</div><!-- end texts -->

<div class="clear"></div>

<div class="photo">
<a href="http://pdssbn.astro.umd.edu/" target="_blank"><img src="${ctx}/images/logo-sbn.gif" width="128" alt="SBN logo" /></a>
</div><!-- end photo -->
<div class="texts">
<h3>PDS Small Bodies Node (SBN)</h3>
<p>The Small Bodies Node of the PDS specializes in data sets concerned with comets, asteroids, and interplanetary dust. In addition, the SBN curates data from observations of meteors, meteorites, small moons of the outer planets, and dwarf planets.
<br /><a href="http://pdssbn.astro.umd.edu/">Learn more...</a></p>
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
