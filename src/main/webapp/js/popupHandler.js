/**
 * 
 */

var companyPopup;
function openPopup(url) {
	
	var width = 600;
	var height = 500;
	var left = (screen.width / 2) - (width / 2);
	var top = (screen.height / 2) - (height / 2);
	
	popupWindow = window.open(url, "Lookup",
			"scrollbars=yes, resizable=no, width="+width+", height="+height+",top="+top+",left="+left+"");
}