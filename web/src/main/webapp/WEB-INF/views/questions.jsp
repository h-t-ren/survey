<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

	<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#highlight').dataTable( {
					 "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "bSort": false
				} );
			} );
		</script>
  <table class="display" id="highlight">
        <thead>
          <tr>
            <th>问题列表</th>
            </tr>
        </thead>
        <tbody>
       
        <c:forEach var="question" items="${questions.question}">
            <tr>
                <td><b>${question.id}）${question.name}</b> <br/>
                   <c:forEach var="item" items="${question.item}">
                     &nbsp;&nbsp;<input type="checkbox" disabled="disabled"/>${item}<br>
                   </c:forEach>
                 </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


<%@ include file="footer.jsp" %>