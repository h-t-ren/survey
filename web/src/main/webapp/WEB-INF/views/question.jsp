<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			,	west__minSize:			350
			,   center__onresize:		"innerLayout.resizeAll" 
			});	
		myLayout.sizePane("west", 550);
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
  <div id="app_area">实验室知识管理相关情况调查问卷分析系统</div>
  <div id="other_area"></div>
   <div id="navmenu">
     <ul id="nav">
          <li><a class="headerActiveLink"  href="<c:url value="/" />">系统介绍</a></li>
          <li><a class="headerActiveLink"  href="<c:url value="/respondent" />">答卷情况</a></li>
 	      <li><a class="headerDisabledLink"  href="<c:url value="/question" />">答卷统计</a></li>
 	      <li><a class="headerActiveLink"  href="<c:url value="/profile" />">偏好设置</a></li>
 	      <li><a class="headerActiveLink"  href="<c:url value="/analysis" />">答卷分析</a></li>
     </ul>
  </div>
</div>


</div> 
<div class="ui-layout-west">
<fieldset><legend>请点击问题查看统计结果：</legend>
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
            <th>问题描述</th>
            <th>查看</th>
            </tr>
        </thead>
        <tbody>
       
        <c:forEach var="question" items="${questions.question}" varStatus="status">
            <tr>
                <td>${question.id}</td>
                <td>
                <c:if test="${fn:length(question.name)>32}">
                    ${fn:substring(question.name,0,32)}...
                </c:if>
                 <c:if test="${fn:length(question.name)<33}">
                    ${question.name}
                </c:if>
                </td>
                <td><a href="<c:url value="/question/${question.id}" />"><img src="<c:url value="/resources/images/crud/view.png" />"></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>
</div> 


<div class="ui-layout-center">
<div class="middle-center" style="height:100%;width:100%;">
 <div class="ui-layout-center">

 <c:if test="${idQuestion!=1100}">
    <table class="display">
        <thead>
          <tr>
            <th>问题描述</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><b>${question.id}）${question.name}</b> <br/>
                   <c:forEach var="item" items="${question.item}" varStatus="status">
                      &nbsp;&nbsp;第 ${status.count}选项：&nbsp;${item}<br>
                   </c:forEach>
                 </td>
            </tr>
        </tbody>
    </table>
 <fieldset>
 <script type="text/javascript">
  var chart;
    $(document).ready(function()
    {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'column'
            },
            title: {
                text: '统计结果与参考点比较柱状图'
            },
            xAxis: {
                categories: [${categories}]
            },
            yAxis: {
                min: 0,
                title: {
                    text: '百分比:%'
                }
            },
            legend: {
                layout: 'vertical',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 80,
                y: 40,
                floating: true,
                shadow: true
            },
            tooltip: {
                formatter: function() {
                	var yValue
                	if(this.y==0.5)
                	    yValue = 0;
                	else
                		yValue = this.y
                    return ''+
                        this.x +': '+ yValue+' %';
                }
            },
		     credits: {
			   enabled: false
		     },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
                series: [${series}]
        });
    });
</script>
<form action="<c:url value="/question/${question.id}" />" method="post">
<div id="container" style="min-width:400px;width:800px; height: 400px;float:left; margin: 0 auto"></div>
<fieldset style="float:left;width:100%; margin: 0 auto"><legend>请选择不同分组，在进行对比分析：：</legend>
<div style="float:left;width:100%; margin: 0 auto">
<c:forEach var="criterion" items="${criteria}">
<c:set var="contains" value="false" />
<c:forEach var="selectedC" items="${selectedCriteria}">
  <c:if test="${selectedC eq criterion.name}">
    <c:set var="contains" value="true" />
  </c:if>
</c:forEach>
 <input type="checkbox" name="criteria" <c:if test="${contains}">checked</c:if> value="${criterion.name}" />${criterion.name}&nbsp;
</c:forEach>
</div>
 <div style="float:left; margin: 0 auto"> <button type="submit" name="analysis" style="width: 112px;">对比分析</button></div>
 </fieldset>
 </form>
</fieldset>
   <c:if test="${comments!=null&& !empty comments}">
	<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#highlight2').dataTable( {
					 "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "bSort": false
				} );
			} );
		</script>
  <table class="display" id="highlight2" style="float: left;">
        <thead>
          <tr>
            <th>意见和建议: </th>
            </tr>
        </thead>
        <tbody>
       
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>${comment}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>

</c:if>




 </div>
 </div>
</div> 


<div class="ui-layout-south" id="footer_copyrights"> 版权 &copy; 任宏涛  </div> 

</body>

</html>

