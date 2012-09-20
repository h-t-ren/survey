<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/tundra.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/styles/layout-default-latest.css" />" type="text/css" media="screen, projection" />
<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/screen.css"/>" type="text/css" media="screen, projection" />
<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/print.css"/>" type="text/css" media="print" />
<link rel="stylesheet" href="<c:url value="/resources/styles/survey-web.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/styles/left_menu-style.css" />" type="text/css" media="screen" />
<script type="text/javascript" src="<c:url value="/resources/javascript/dojo.xd.js" />" djConfig="parseOnLoad: true"> </script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery-1.4.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.dataTables.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery-ui-latest.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.layout-latest.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/javascript/highchart/highcharts.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/highchart/modules/exporting.js"/>"></script>
<!--[if lt IE 8]>
<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/ie.css"/>" type="text/css" media="screen, projection" />
<![endif]-->

<SCRIPT type="text/javascript">
var myLayout, innerLayout;

$(document).ready(function () {

		myLayout = $('body').layout({

			//	reference only - these options are NOT required because 'true' is the default
				closable:				true	// pane can open & close
			,	resizable:				true	// when open, pane can be resized 
			,	slidable:				true	// when closed, pane can 'slide' open over other panes - closes on mouse-out

			//	some resizing/toggling settings
			,	north__slidable:		false	// OVERRIDE the pane-default of 'slidable=true'
			,	north__spacing_closed:	2		// big resizer-bar when open (zero height)
			,	north__spacing_open:	2		// big resizer-bar when open (zero height)
			,	south__resizable:		false	// OVERRIDE the pane-default of 'resizable=true'
			,	south__spacing_open:	2		// no resizer-bar when open (zero height)
			,	south__spacing_closed:	2		// big resizer-bar when open (zero height)
			//	some pane-size settings
			,	west__minSize:			200
			,   center__onresize:		"innerLayout.resizeAll" 
			});	
		myLayout.sizePane("west", 360);
		myLayout.sizePane("south", 30);
		
		innerLayout = $('div.middle-center').layout({ 	
				    closable:				true	// pane can open & close
				,	resizable:				true	// when open, pane can be resized 
				,	slidable:				true	// when closed, pane can 'slide' open over other panes - closes on mouse-out
                ,   spacing_open:			0  
     			,	spacing_closed:			0
			}); 

});
</SCRIPT>

		
</head>

<body class="tundra">
<div class="ui-layout-north" id="header_toolbar" >
<div id="nav_top">
  <div id="app_area"> 实验室知识管理相关情况调查问卷分析系统</div>
  <div id="other_area"></div>
   <div id="navmenu">
     <ul id="nav">
       <li><a class="headerActiveLink"  href="<c:url value="/" />">系统介绍</a></li>
       <li><a class="headerDisabledLink"  href="<c:url value="/respondent" />">答卷情况</a></li>
       <li><a class="headerActiveLink"  href="<c:url value="/questionList" />">答卷统计</a></li>
 	   <li><a class="headerActiveLink"  href="<c:url value="/profile" />">偏好设置</a></li>
 	   <li><a class="headerActiveLink"  href="<c:url value="/analysis" />">答卷分析</a></li>
     </ul>
  </div>
</div>
</div> 

<div class="ui-layout-west">
<fieldset><legend>答卷者列表：</legend>
 <script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#highlight').dataTable( {
					 "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "bSort": false,
				} );
				$("#highlight tr").each( function (i) {
					if(i==${idQuestion})
						{
						$(this).children('td').css('background-color','#008000');
						}
				} );
				
			} );
		</script>
  <table class="display" id="highlight">
        <thead>
          <tr>
            <th>编号</th>
            <th>学院</th>
            <th>状态</th>
            <th>姓名</th>
            <th>查看</th>
            </tr>
        </thead>
        <tbody>
       
        <c:forEach var="response" items="${responses}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${response.respondent.school}</td>
                <td>${response.respondent.status}</td>
                <td>${response.respondent.name}</td>
                 <td><a href="<c:url value="/respondent/${status.count}" />">查看</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>
</div> 


<div class="ui-layout-center">
<div class="middle-center" style="height:100%;width:100%;">
 <div class="ui-layout-center">
 
 
 <script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#highlight1').dataTable( {
					 "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "bSort": false
				} );
			} );
		</script>
  <table class="display" id="highlight1">
        <thead>
          <tr>
            <th>您的答卷情况</th>
            </tr>
        </thead>
        <tbody>
       
        <c:forEach var="entry" items="${questionMap}">
            <tr>
                <td><b>${entry.key.question.id}）${entry.key.question.name}</b> <br/>
                   <c:forEach var="item" items="${entry.value}">
                     &nbsp;&nbsp;<input type="checkbox" disabled="disabled" <c:if test="${item.selected}"> checked </c:if> />${item.item}<br/>
                   </c:forEach>
                   <c:if test="${entry.key.comment!=null}">
                     <div style="background-color: #008000;">&nbsp;&nbsp;&nbsp;${entry.key.comment}</div> 
                     </c:if>
                 </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
 
 
 
 
 
 
 
 
 
 
 </div>
 </div>
</div> 


<div class="ui-layout-south" id="footer_copyrights">版权 &copy; &nbsp; 。。。</div> 

</body>

</html>

