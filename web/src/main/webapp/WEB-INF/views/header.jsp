<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Survey</title>

<script type="text/javascript" src="<c:url value="/resources/javascript/dojo.xd.js" />" djConfig="parseOnLoad: true"> </script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery-1.7.1.min.js" />"></script>
<script type="text/javascript"src="<c:url value="/resources/javascript/jquery-ui-latest.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.layout-latest.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/javascript/highchart/highcharts.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/highchart/modules/exporting.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.dataTables.js"/>"></script>
<!--  <script type="text/javascript" src="<c:url value="/resources/javascript/themes/dark-blue.js"/>"></script>-->
<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/screen.css"/>" type="text/css" media="screen, projection" />
<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/print.css"/>" type="text/css" media="print" />
<!--[if lt IE 8]>
<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css"/>" type="text/css" media="screen, projection" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/tundra.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/styles/survey-web.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/styles/table.css"/>" type="text/css" media="screen, projection" />
<style type="text/css">
   html, body { width: 100%; height: 100%; margin: 0; }
</style>
  
</head>
<body class="tundra">
   <div dojoType="dijit.layout.BorderContainer" style="width: 100%; height: 100%">
     <div id="header_toolbar" dojoType="dijit.layout.ContentPane" region="top">
         <div id="nav_top"> 
                <div id="app_area">华东理工大学实验室知识管理相关情况调查问卷分析系统</div>
                <div id="navmenu">
                    <ul id="nav">
                       <li><a <c:if test="${sitemap=='main'}" >class="headerDisabledLink"</c:if> <c:if test="${sitemap!='main'}" >class="headerActiveLink"</c:if>  href="<c:url value="/" />">系统介绍</a></li>
                        <li><a class="headerActiveLink"  href="<c:url value="/respondent" />">答卷情况</a></li>
                        <li><a <c:if test="${sitemap=='questions'}" >class="headerDisabledLink"</c:if> <c:if test="${sitemap!='questions'}" >class="headerActiveLink"</c:if>  href="<c:url value="/questionList" />">答卷统计</a></li>
 	                   <li><a <c:if test="${sitemap=='profile'}" >class="headerDisabledLink"</c:if> <c:if test="${sitemap!='profile'}" >class="headerActiveLink"</c:if>  href="<c:url value="/profile" />">偏好设置</a></li>
 	                   <li><a class="headerActiveLink"  href="<c:url value="/analysis" />">答卷分析</a></li>
                    </ul>
                 </div>
                 <div id="other_area"></div>
             </div>
            </div>

     <div id="app_body" dojoType="dijit.layout.ContentPane" region="center">

