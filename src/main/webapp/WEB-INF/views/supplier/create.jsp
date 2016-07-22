<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="zerotags" prefix="ET" %>

<spring:url value="/employees" var="listUrl" htmlEscape="true" />
<spring:url value="/resources/js/popupHandler.js" var="popupUrl" />

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<h3>Add New Employee </h3>
		<form:form class="supplierForm" method="post" modelAttribute="supplier" >
			<c:forEach items="${supplier.getLayouts()}" var="layout">
				<ET:Layout layout="${layout}" layoutType="${supplier.getEditLayoutType()}" model="${supplier}" dataModel="${dataModel}">
					<div align="center">
						<input type="submit" value="Save">
						<input type="submit" value="Cancel" action="${listUrl}">  
						<!--  <a href="${listUrl}"><button type="button">Cancel</button></a>-->
					</div>
				</ET:Layout>
			</c:forEach>
		</form:form>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="viewScripts">
		<script type="text/javascript" src="${popupUrl}"></script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="viewlookups">
		<!--  View Lookups -->
	</tiles:putAttribute>
	
</tiles:insertDefinition>
