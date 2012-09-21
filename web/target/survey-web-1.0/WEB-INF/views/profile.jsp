<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<style type="text/css">
#containerx{width:800px;border:1px solid #ccc;}
#containerx .box1{width:380px;float: left;}
#containerx .box2{margin-left: 40px;width:380px;float:left;}
</style>
	<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#highlight').dataTable( {
					 "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "bSort": false
				} );
				$('#highlight1').dataTable( {
					 "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "bSort": false
				} );
			} );
		</script>
<form action="<c:url value="/profile" />" method="post">
<fieldset><legend>请选择您的偏好：</legend>	
<div id="containerx">
<div  class="box1">
 <table class="display" id="highlight">
 <thead><tr><th style="text-align: center;">参考点法</th></tr></thead>
        <tbody>
        <c:forEach var="preference" items="${profile.preference}" varStatus="status">
        <c:if test="${preference.method=='BASIC_RFP'}">
            <tr>
                <td><input type="radio" name="idPref" <c:if test="${preference.selected}">checked</c:if> value="${preference.id}" />${preference.name}, 
                 epsilon=${preference.parameter} <br/>
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
					    	 /*
					    	 labelFormatter:function()
					    	 {
					    		 return 'fitness:';
					    	 }
					       */
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
				         series:[{data:[${distributions[status.index]}]}]
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
		           </td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
<div  class="box2">
 <table class="display" id="highlight1">
  <thead><tr><th style="text-align: center;">高级参考点法</th></tr></thead>
        <tbody>
        <c:forEach var="preference" items="${profile.preference}" varStatus="status">
         <c:if test="${preference.method=='ENHANCED_RFP'}">
            <tr>
                <td><input type="radio" name="idPref" <c:if test="${preference.selected}">checked</c:if> value="${preference.id}" />${preference.name}, 
                 epsilon=${preference.parameter} <br/>
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
					    	 /*
					    	 labelFormatter:function()
					    	 {
					    		 return 'fitness:';
					    	 }
					       */
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
				         series:[{data:[${distributions[status.index]}]}]
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
		           </td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>	
</fieldset>
<button type="submit" name="save" style="width: 112px;">保存</button>
</form>
<%@ include file="footer.jsp" %>