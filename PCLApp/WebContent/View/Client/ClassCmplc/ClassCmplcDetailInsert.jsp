
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">

<script language="javascript">
	function extraActions(action){

	}																	
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="ClassCmplc.ClassCmplcDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Inserção de Classificação Compliance</TITLE>
</HEAD>
<body>
<html:form action="/ClassCmplc.ClassCmplcDetail.Insert.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Parâmetros" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:text property="backURL"
				value="ClassCmplc.ClassCmplcDetail.Insert.Show" style="display:none"></html:text>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Inserção de Classificação Compliance</th>

					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
								<TD colspan="2" width="12%">Código *</TD>
								<TD colspan="2" width="35%">Descrição *</TD>
								<td colspan="2" width="26%">Indicador de Sensitividade</td>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td><html:text property="classCmplcCode" styleClass="ODS_Text_Field_Size_5" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></td>
								<td></td>
								<TD><html:text property="classCmplcText" styleClass="ODS_Text_Field_Size_40" maxlength="40"></html:text></TD>
								<td></td>
								<td>
									<html:select property="sensInd" styleClass="ODS_Select_Field_Size_5" >
									<html:option value=""></html:option>
									<html:options property="sensIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="sensIndDomain.columnValuesByName(INDICATOR_TEXT)" />
									</html:select>
								</td>
								<TD></TD>
							</tr>
							<tr><TD>&nbsp;</TD></tr>
						</tbody>
					</TABLE>
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%">&nbsp;</td>
								<TD width="100%"><html:button property="inserir" value="Confirmar Inserção"
									onclick="submitAction('insert');"></html:button></TD>
								<TD width="100%"><html:button property="limpar" value="Limpar"
									onclick="javascript:reset();"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					</td>
					<td><IMG SRC='<%= request.getContextPath() %>/Common/image/spacer.gif' WIDTH="1"
						HEIGHT="36" /></td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true" />
			</TABLE>
			</td>
		</tr>
	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
