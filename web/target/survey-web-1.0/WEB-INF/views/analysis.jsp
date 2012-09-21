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
		myLayout.sizePane("west", 300);
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
          <li><a class="headerActiveLink"  href="<c:url value="/respondent" />">答卷情况</a></li>
 	      <li><a class="headerActiveLink"  href="<c:url value="/question" />">答卷统计</a></li>
 	      <li><a class="headerActiveLink"  href="<c:url value="/profile" />">偏好设置</a></li>
 	      <li><a class="headerDisabledLink"  href="<c:url value="/analysis" />">答卷分析</a></li>
     </ul>
  </div>
</div>


</div> 
<form action="<c:url value="/analysis" />" method="post">
<div class="ui-layout-west">
<fieldset><legend>预设的参考点法：(<a href="<c:url value="/profile" />">更改分析方法</a>)</legend>
<c:if test="${preference==null}">目前无预设参考点法，<a href="<c:url value="/profile"/>">请预设</a></c:if>
${preference.name}, epsilon=${preference.parameter} <br/>
   <script type="text/javascript">
		            	var chart;
			            $(document).ready(function() {
				          chart = new Highcharts.Chart({
					      chart: {
						    renderTo: 'chartcontainer_${preference.id}',
						    defaultSeriesType: 'column'
					      },
					      exporting:
					      {
						    enabled:false
					      },
					      title: {
						    text: null
					      },
					      xAxis: {
				            categories:['A','B','C','D','E'],
				            labels:{
					        enabled:false
				            }
					      },
					      yAxis: {
						    min: 0,
						    allowDecimals:false,
						    labels:{
						       enabled:false
					            },
					            
						    title: {
							  text: '',
							  align: 'high'
						    }
					      },
					      plotOptions: {
						    column: {
							  dataLabels: {
								enabled: true
							}
						 },
							series:{
								pointWidth:10,
								minPointLength:2
							}
					    },
					     legend:
					     {
						    enabled:false
					     },
					     credits: {
						   enabled: false
					     },
					     tooltip:{
							    formatter: function()
							    {
								  return this.x+': <b>'+this.y+'</b>';
							    }
						     },
				         series:[{data:[${distribution}]}]
				      },function(chart)
				         {
				        	 var yAxis= chart.yAxis[0];
				        	 yAxis.options.startOntick=false;
				        	 yAxis.options.endOnTick=false;
				         } 
				          
				          );
				          
			       });	
		
		           </script>
		 <div id="chartcontainer_${preference.id}" style="width: 200px; height:100px;overflow:auto; margin: 0 auto" ></div>
</fieldset>


<fieldset><legend>请选择分析范围：</legend>
   <c:forEach var="criterion" items="${criteria}">
   <c:if test="${criterion.id==1}"> <input type="radio" name="criterion" <c:if test="${criterion.id==idCriterion}">checked</c:if> value="${criterion.name}" />${criterion.name}</c:if>
   </c:forEach>
   <hr/>
    <b>状态：</b><br/>
    <c:forEach var="criterion" items="${criteria}">
     <c:if test="${criterion.id>1 && criterion.id<7}"> <input type="radio" name="criterion" <c:if test="${criterion.id==idCriterion}">checked</c:if> value="${criterion.name}" />${criterion.name}<br/></c:if>
   </c:forEach>
    <hr/>
     <b>所在学院：</b><br/>
    <c:forEach var="criterion" items="${criteria}">
      <c:if test="${criterion.id>6}"><input type="radio" name="criterion" <c:if test="${criterion.id==idCriterion}">checked</c:if> value="${criterion.name}" />${criterion.name}<br/></c:if>
   </c:forEach>
</fieldset>
<fieldset><legend>请选择分析结果子集：</legend>
列出排名前<select name="numQuestion">
   <option <c:if test="${numQuestion==5}"> selected </c:if>  value="5">5</option>
   <option <c:if test="${numQuestion==7}"> selected </c:if>  value="7">7</option>
   <option <c:if test="${numQuestion==null || numQuestion==10}"> selected </c:if> value="10">10</option>
   <option <c:if test="${numQuestion==15}"> selected </c:if> value="15">15</option>
   <option <c:if test="${numQuestion==20}"> selected </c:if> value="20">20</option>
</select>个最好、最坏评估结果
</fieldset>
 <button type="submit" name="solve" style="width: 112px;">开始分析</button>
</div> 


<div class="ui-layout-center">
<div class="middle-center" style="height:100%;width:100%;">
 <div class="ui-layout-center">
 <c:if test="${results!=null}" >
 <fieldset><legend>最好结果排名：</legend>
 <script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				//jQuery.noConflict();
				$('#highlight').dataTable( {
					// "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "fnDrawCallback": function ( oSettings ) {
							var that = this;
							/* Need to redo the counters if filtered or sorted */
							if ( oSettings.bSorted || oSettings.bFiltered )
							{
								$("#highlight tr td:first-child").each( function (i) {
									that.fnUpdate( i+1, this.parentNode, 0, false, false );
								} );
							}
						},
						"aoColumnDefs": [
							{ "bSortable": false, "aTargets": [ 0 ] }
						],
						 "iDisplayLength":${numQuestion},
					// "bSort": false,
					 "aaSorting": [[ 4, "desc" ]]
				} );
			} );
		</script>
  <table class="display" id="highlight">
        <thead>
          <tr>
            <th>排名</th>
            <th>问题编号</th>
            <th>问题名称</th>
            <th>问题类型</th>
            <th>得分</th>
            </tr>
        </thead>
        <tbody>
       
        <c:forEach var="result" items="${results}" varStatus="status">
        
        <c:if test="${result.question.questionType!='控制性'}">
            <tr>
                <td>${status.count}</td>
                <td>${result.question.id}</td>
                <td>${result.question.name}</td>
                <td>${result.question.questionType}</td>
                 <td>${result.score}</td>
            </tr>
       </c:if>
        </c:forEach>
        </tbody>
    </table>
 </fieldset>
  <fieldset><legend>最坏结果排名：</legend>
 <script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				//jQuery.noConflict();
				$('#highlight1').dataTable( {
					// "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "fnDrawCallback": function ( oSettings ) {
							var that = this;
							/* Need to redo the counters if filtered or sorted */
							if ( oSettings.bSorted || oSettings.bFiltered )
							{
								$("#highlight1 tr td:first-child").each( function (i) {
									that.fnUpdate( i+1, this.parentNode, 0, false, false );
								} );
							}
						},
						"aoColumnDefs": [
							{ "bSortable": false, "aTargets": [ 0 ] }
						],
						 "iDisplayLength":${numQuestion},
					// "bSort": false,
					 "aaSorting": [[ 4, "asc" ]]
				} );
			} );
		</script>
  <table class="display" id="highlight1">
        <thead>
          <tr>
            <th>排名</th>
            <th>问题编号</th>
            <th>问题名称</th>
            <th>问题类型</th>
            <th>得分</th>
            </tr>
        </thead>
        <tbody>
       
        <c:forEach var="result" items="${results}" varStatus="status">
        
        <c:if test="${result.question.questionType!='控制性'}">
            <tr>
                <td>${status.count}</td>
                <td>${result.question.id}</td>
                <td>${result.question.name}</td>
                <td>${result.question.questionType}</td>
                 <td>${result.score}</td>
            </tr>
       </c:if>
        </c:forEach>
        </tbody>
    </table>
 </fieldset>
 
 
 
 </c:if>
 
 
 
 
 
 
 
 
 </div>
 </div>
</div> 
</form>

<div class="ui-layout-south" id="footer_copyrights">版权 &copy; &nbsp; 。。。</div> 

</body>

</html>

