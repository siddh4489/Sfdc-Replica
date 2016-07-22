<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/employees" var="employeesUrl" htmlEscape="true" />
<spring:url value="/" var="dashboardUrl" htmlEscape="true" />
<spring:url value="/users" var="usersUrl" htmlEscape="true" />
<header class="navbar navbar-default navbar-static-top" role="banner">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="/" class="navbar-brand"> <img src="${pageContext.request.contextPath}/img/L1.png" alt="Mountain View" style="width:150px;height:50px;"></a>
    </div>
    <nav class="collapse navbar-collapse" role="navigation">
      <ul class="nav navbar-nav">
         <li>
          <a href="${dashboardUrl}">Home</a>
        </li>
         <li>
          <a href="${employeesUrl}">Employee</a>
        </li>
        <li>
          <a href="#">Account</a>
        </li>
        <li>
          <a href="#">Contact</a>
        </li>
       
             </ul>
    </nav>
  </div>
</header>

