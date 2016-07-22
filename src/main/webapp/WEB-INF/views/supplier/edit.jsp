<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="zerotags" prefix="ET" %>

<spring:url value="/employees" var="listUrl" htmlEscape="true" />
<spring:url value="/suppliers/edit" var="updateUrl" htmlEscape="true" />

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<h3>Edit Employee Details</h3>
		<form:form class="supplierForm" method="post" modelAttribute="supplier" >
			<c:forEach items="${supplier.getLayouts()}" var="layout">
				<ET:Layout layout="${layout}" layoutType="${supplier.getEditLayoutType()}" model="${supplier}" dataModel="${dataModel}">
					<div align="center">
						<input type="submit" value="Update"> 
						<input type="submit" value="Cancel" action="${listUrl}"> 
						<!-- <a href="${listUrl}"><button type="button">Cancel</button></a> -->
					</div>
				</ET:Layout>
			</c:forEach>
		</form:form>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
