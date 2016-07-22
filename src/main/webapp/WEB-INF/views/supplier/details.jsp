<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="zerotags" prefix="ET" %>
<spring:url value="/employees" var="listUrl" htmlEscape="true" />
<spring:url value="/suppliers/edit/${sObjectRecord.getField('Id')}" var="editUrl" htmlEscape="true" />
<spring:url value="/suppliers/delete" var="deleteUrl" htmlEscape="true" />

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<spring:url value="/employees" var="listUrl" htmlEscape="true" />
		<a class="action" href="${listUrl}"><img src="${pageContext.request.contextPath}/img/Back_Arrow.png" alt="Mountain View" style="width:30px;height:20px;">Back</a>
	
		<h3>Employee Details</h3>
			<c:forEach items="${supplier.getLayouts()}" var="layout">
				<ET:Layout layout="${layout}" layoutType="${supplier.getDetailsLayoutType()}" model="${supplier}" dataModel="${dataModel}">
			   </ET:Layout>
			</c:forEach>
	</tiles:putAttribute>
</tiles:insertDefinition>
