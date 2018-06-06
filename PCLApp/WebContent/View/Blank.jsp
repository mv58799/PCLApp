
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK href="../theme/Master.css" rel="stylesheet"
	type="text/css">
<TITLE></TITLE>
<script language="javascript">
function openModal(){
window.opener=self; 
window.open("<%= request.getContextPath() %>/FrontController/Disclaimer.Show","Citibank","menubar=0,status=1,resizable=1,width=1024,height=690,left=0,top=0");
window.close(); 
}
</script>
</HEAD>

<BODY onload="openModal();">
</BODY>
</html:html>
