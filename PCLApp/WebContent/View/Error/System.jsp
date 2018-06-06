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
	<logic:equal parameter="currentSheet" value="ControlSheet">
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="ControlSheet"/>
			<jsp:param name="currentSubSheet" value="Processo"/>
	   	</jsp:include>
	</logic:equal>
	<logic:equal parameter="currentSheet" value="CustomerSheet">
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="CustomerSheet"/>
			<jsp:param name="currentSubSheet" value="Cliente"/>
	   	</jsp:include>
	</logic:equal>
	<logic:equal parameter="currentSheet" value="ProductSheet">
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="ProductSheet"/>
			<jsp:param name="currentSubSheet" value="Produto"/>
	   	</jsp:include>
	</logic:equal>
	<logic:equal parameter="currentSheet" value="NewCPBSheet">
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="NewCPBSheet"/>
			<jsp:param name="currentSubSheet" value="Aprovação"/>
	   	</jsp:include>
	</logic:equal>

	<html:form action="/ODSMenu.Show.do">
		<table class="ODS_mainTable" border="0">
			<tr>
				<td>
					<table class="ODS_internalWidth" border="0">
						<tr class="ODS_Detail_Line2" align="center">
							<td colspan="2"><br>Erro de execução: Detalhes no arquivo de log.<br><br></td>
						</tr>
						<tr><td></td></tr>
					</table>
				</td>
			</tr>
			<jsp:include page="/View/Util/Footer.jsp" flush="true" />
		</table>
	</html:form>
</body>
</html:html>
