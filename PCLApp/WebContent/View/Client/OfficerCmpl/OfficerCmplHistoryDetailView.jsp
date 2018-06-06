<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
<HEAD>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM Software Development Platform">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Common/css/citi.css">
	<script language="javascript"src="<%= request.getContextPath() %>/Common/js/dialog_window.js"></script>
	
	<script language="javascript">
		function extraActions(action){
		}; 																		
	</script>

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="OfficerCmpl.OfficerCmplHistoryDetail"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
	
	<TITLE>Detalhe de Histórico de Banker - Dados Complementares</TITLE>
</HEAD>
<body>
<html:form action="/OfficerCmpl.OfficerCmplHistoryDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet"/>
		<jsp:param name="currentSubSheet" value="Banker" />
	</jsp:include>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<html:text property="backURL" value="OfficerCmplHistoryDetailForm" style="display:none"></html:text>	
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Histórico de Banker - Dados Complementares</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
								<TD width="25%">Número do Banker</TD>
								<TD width="50%">Nome</TD>
								<TD width="25%">&nbsp;</TD>
							</tr>

							<tr class="ODS_Detail_Line2">
								<td width="25%"><html:text property="offcrNbr" styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></td>
								<TD width="50%"><html:text property="offcrNameText" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>								
								<TD width="25%">&nbsp;</TD>
							</tr>

							<tr class="ODS_Detail_Line2">
								<TD width="25%">Tipo</TD>
								<td width="50%">Número Internacional</td>
								<TD width="25%">&nbsp;</TD>
							</tr>

							<tr class="ODS_Detail_Line2">
								<TD width="25%">&nbsp;<html:select property="offcrTypeCode" disabled="true" styleClass="ODS_Select_Field_Size_20">
													<html:option value=""></html:option>
													<html:options property="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_CODE)" labelProperty="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_TEXT)" />
												</html:select></TD>
								<TD width="50%">&nbsp;&nbsp;<html:text property="offcrIntlNbr" styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></TD>
								<TD width="25%">&nbsp;</TD>
							</tr>



							<tr class="ODS_Detail_Line1">
								<TD width="25%">Usuário da Última Atualização</TD>
								<TD width="50%">&nbsp;Data/Hora da Última Atualização</TD>
								<td width="25%">&nbsp;&nbsp;Indicador Status Registro</td>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="25%"><html:text property="lastUpdUserId" styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
								<TD width="50%">&nbsp;<html:text property="lastUpdDate" styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text></TD>
								<TD width="25%">&nbsp;&nbsp;<html:text property="recStatCode" styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_Line1">
								<TD width="25%">Usuário da Aprovação</TD>
								<TD width="50%">&nbsp;Data/Hora da Aprovação</TD>
								<TD width="25%">&nbsp;&nbsp;Data de Referência</TD>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="25%"><html:text property="lastAuthUserId" styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
								<TD width="50%">&nbsp;<html:text property="lastAuthDate" styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text></TD>
								<td width="25%">&nbsp;&nbsp;<html:text property="offcrRefDate" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td width="25%">&nbsp;</td>
							</tr>
						</tbody>
					</TABLE>
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%">&nbsp;</td>
								<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					</td>
					<td width="15">&nbsp;</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true" />
			</TABLE>
		</td>
	</tr>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>