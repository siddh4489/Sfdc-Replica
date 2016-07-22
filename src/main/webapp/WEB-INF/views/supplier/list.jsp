<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		
		<!--  <h3>${supplier.describeSObjectResult().getLabelPlural()}</h3> -->
		
		<div align="left" style="margin-bottom: 15px;">
			<spring:url value="/employees/create" var="createUrl" htmlEscape="true" />
			<a href="${createUrl}"><button type="button">Add New</button></a>
		</div>
		<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Employee Detail List</div>
  
		<table class="table dataTable no-footer" >
			<thead>
				<tr>
					<th >Actions</th>
					<c:forEach items="${supplier.getCustomFields()}" var="field">
						<th>${field.getLabel()}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody class="list">
				<c:forEach items="${supplierResords}" var="record">
					<tr>
						<td><!--<spring:url value="/employees/delete/${record.getField('Id')}" var="deleteUrl" htmlEscape="true" />
							<a class="action" href="${deleteUrl}">Delete</a> | -->
							<spring:url value="/employees/details/${record.getField('Id')}" var="detailsUrl" htmlEscape="true" />
							<a class="action" href="${detailsUrl}">Details</a> | 
							<spring:url value="/employees/edit/${record.getField('Id')}" var="editUrl" htmlEscape="true" />
							<a class="action" href="${editUrl}">Edit</a>
						</td>
						<c:forEach items="${supplier.getCustomFields()}" var="field">
							<td >
								<c:choose>
									<c:when test="${field.getType().name() == 'reference'}" >
										<c:set value="${field.getName().concat('_Name') }" var="newField"></c:set>
										${record.getField(newField)}
									</c:when>
									<c:otherwise>
										${record.getField(field.getName())}							
									</c:otherwise>
								</c:choose>
							</td>
						</c:forEach>
<%-- 						<c:forEach items="${supplier.getCustomFields()}" var="field"> --%>
<%-- 							<td style="border-left: solid black 1px;">${record.getField(field.getName())}</td> --%>
<%-- 						</c:forEach> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>		
		
	</tiles:putAttribute>
</tiles:insertDefinition>
