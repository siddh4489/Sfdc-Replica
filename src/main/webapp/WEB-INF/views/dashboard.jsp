<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div align="center" style="background-color: #e7e7e7;">
			Welcome to Zero...!!!
		</div>
		<body>
		<table>
		<tr><td><div id="piechart" style="width: 500px; height: 300px;"></div></td><td><div id="chart_div" style="width:500px; height: 300px;"></div></td></tr>
		</table>
    
    
  </body>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
