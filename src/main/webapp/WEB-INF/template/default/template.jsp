<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zero</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/design.css">  
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
   <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Designation', 'Count'],
          ['Software Developer ',     5],
          ['Software QA ',      2],
          ['Web Designer ',    1]
        ]);

        var options = {
          title: 'Employee Designation(Count)',
          colors: ['#CCCCCC', '#999999', '#4C4C4C', '#E6E6E6']
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
        
        
        
        
        
        
      }
    </script>
    
    <script>
    
    
    google.load('visualization', '1', {packages: ['corechart', 'bar']});
    google.setOnLoadCallback(drawBasic);

    function drawBasic() {

          var data1 = google.visualization.arrayToDataTable([
				['Designation', 'Count'],
				['Software Developer ',     5],
				['Software QA ',      2],
				['Web Designer ',    1]
          ]);

          var options = {
            title: 'Employee Designation(Count)',
            colors: ['#999999', '#4C4C4C', '#E6E6E6'],
            chartArea: {width: '50%'},
            hAxis: {
              title: 'Total Count',
              minValue: 0
            },
            vAxis: {
              title: 'Designation'
            }
          };

          var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

          chart.draw(data1, options);
        }
    
    </script>

<style type="text/css">

#footer {
    background-color: #e7e7e7;
   position:fixed;
   left:0px;
   bottom:0px;
   height:30px;
   width:100%;
}
	   
	   * { margin: 0; padding: 0; }
	   p { padding: 10px; }
	   #piechart {  left: 0; top: 0; width: 50%; }
	   #chart_div {  right: 0; top: 0; width: 50%; }
	
	

tbody.list>tr:nth-child(even) {
	background: #CCC
}

tbody.list>tr:nth-child(odd) {
	background: #FFF
}

.lookup {
	margin-left: 5px;
}

.false {
	color: red;
	font-weight: bold;
}

.true {
	color: green;
	font-weight: bold;
}

table td {
	white-space: nowrap;
}

.required {
	color: red;
	font-weight: bold;
}

a.action {
	font-size: smaller;
}

label {
	margin-right: 10px;
}

div.input {
	position: relative;
	height: 100%;
}

span.requiredMessage {
	float: right;
	position: relative;
	font-size: smaller;
	font-weight: normal;
}

div.requiredInput {
	background-color: RED;
	position: absolute;
	left: -4px;
	width: 3px;
	top: 1px;
	bottom: 1px;
}

.sectionHeader {
	text-align: left;
	background-color: #E6E6E6;
	padding: 2px;
	padding-left: 15px;
	padding-right: 15px;
}

li.error, ul>b {
	color: RED;
}

</style>
<tiles:insertAttribute name="headScripts">
	<!-- 	Javascripts -->
</tiles:insertAttribute>
</head>

 
<body>
<div ><tiles:insertAttribute name="header" /></div>  
        <div style="float:left;padding:10px;width:15%;"><tiles:insertAttribute name="menu" /></div>  
        <div style="float:left;padding:10px;width:80%;">  
        <tiles:insertAttribute name="body" /></div>  
        <div style="clear:both;"><tiles:insertAttribute name="footer" /></div>  
	

	<tiles:insertAttribute name="viewScripts">
		<!--  View Javascripts -->
	</tiles:insertAttribute>
	<tiles:insertAttribute name="viewlookups">
		<!--  View Lookups -->
	</tiles:insertAttribute>
</body>
</html>