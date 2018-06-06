<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Expires" content="0" />
<META http-equiv="Pragma" content="no-cache" />
<META http-equiv="Cache-Control" content="no-cache" />
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet"
	type="text/css">
<TITLE>Private Bank</TITLE>
<script language="javascript">

	// BEGIN: Control to avoid taking back command
	history.go(1);
</script>
</HEAD>
<body>
<html:form action="/ODSMenu.Show.do">
<table class="ODS_mainTable" border="0">
	<tr>
		<td>
		<table class="ODS_internalWidth" border="0">
			<tr>
				<td width="80%"><IMG class="logo"
					src="<%= request.getContextPath() %>/Common/image/cpb_signature_only_grey_red139x17.jpg"></td>
				<td width="20%" align="right" width="139" height="17"><img class="logo"
					src="<%= request.getContextPath() %>/Common/image/citi_2c_gry_pos_rgb120x83.jpg" width="120" height="83"></td>
			</TR>

			<tr class="ODS_Detail_Line2" align="center">
				<td colspan="2">Citibank Private Bank finalizado com sucesso.</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<tr>
		<td>
		<table class="ODS_internalWidth"  border="0">
			<tr>
				<td align="center"><html:button property="FecharBtn" value="Fechar" onclick="javascript:window.close();"></html:button></td>
			</tr>
		</table>
		</td>
	</tr>
	<jsp:include page="/View/Util/Footer.jsp" flush="true" />
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
</html:form>
</table>
</body>
</html:html>
