<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Common/css/citi.css">
		<script language="javascript" src="<%= request.getContextPath() %>/Common/js/dialog_window.js"></script>
		<TITLE></TITLE>
	</HEAD>
	<body>
		<html:form action="/Start.Show.do">

		<html:messages message="false" id="msg">
			<bean:write name="msg"/>
		</html:messages>

		<html:messages message="true" id="msg">
			<bean:write name="msg"/>
		</html:messages>

		</html:form>
	</body> 
</html:html>