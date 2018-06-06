<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" 	href="<%= request.getContextPath() %>/Common/css/citi.css">
<script language="javascript" src="<%= request.getContextPath() %>/Common/js/dialog_window.js"></script>

<BODY>

<script language="javascript">


	function onlyNumber(keyEvt){
	
		if(typeof(keyEvt) == 'undefined')
	
		var keyEvt = window.event;
	
		var code = keyEvt.charCode;
		var key = keyEvt.keyCode;
		
		if (navigator.appName=='Netscape'){
			if  (code >= 48 && code <= 57){
				return true; 
			}else if ( code == 0 && (key ==8 || key==9 )){
				return true;
			}else if ( code == 0 && key==46){
				return true;
			}else{ 
				return false; } 
		
		}else if (navigator.appName=='Microsoft Internet Explorer')	{
			if  (key >= 48 && key <= 57){
				return true; 
			}else if  (key ==8 || key==9 ){
				return true;
			}else{
				return false;}
		}
		
	}

</script>
</BODY>
</html:html>
