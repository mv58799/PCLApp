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

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">
<script language="javascript"src="<%= request.getContextPath() %>/Common/js/dialog_window.js"></script>

		<script language="javascript">
			function extraActions(action){
			};
		</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="OfficerCmpl.OfficerCmplMovementDetail"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>


<TITLE>Alteração de Banker com Pendência de Aprovação- Dados Complementares</TITLE>
</HEAD>

<body>
<html:form action="/OfficerCmpl.OfficerCmplMovementDetail.Update.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet"/>
		<jsp:param name="currentSubSheet" value="Aprovação" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<table class="ODS_mainTable" width="100%" cellpadding="0"cellspacing="0" border="0">
		<tr>
			<html:text property="backURL" value="OfficerCmpl.OfficerCmplMovementDetail.Update.Show" style="display:none"></html:text>	
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Alteração de Banker com Pendência de Aprovação - Dados Complementares</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
								<TD width="20%">Número do Banker</TD>
								<TD width="15%"></TD>
								<TD width="50%">Nome</TD>								
							</tr>
							
							<tr class="ODS_Detail_Line1">
								<TD width="20%"><html:text property="offcrNbr" disabled="true" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>							
								<TD width="15%"></TD>
								<td width="50%"><html:text property="offcrNameText" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></td>
							</tr>
							
							<tr class="ODS_Detail_Line1">
								<TD width="20%">Número Internacional</TD>
								<TD width="15%"></TD>
								<TD width="50%">Tipo *</TD>								
							</tr>
							
							<tr class="ODS_Detail_Line1">
								<TD width="20%"><html:text property="offcrIntlNbr"  styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>							
								<TD width="15%"></TD>
								<td width="50%"><html:select property="offcrTypeCode" styleClass="ODS_Select_Field_Size_20" >
											<html:option value=""></html:option>
											<html:options property="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_CODE)" labelProperty="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_TEXT)" />
											</html:select></td>
							</tr>							
							
							<tr class="ODS_Detail_Line2">
								<td>&nbsp;</td>
							</tr>
						</tbody>
					</TABLE>
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%">&nbsp;</td>
								<TD width="100%"><html:button property="alterar" value="Confirmar Alteração" onclick="submitAction('update');"></html:button></TD>
								<TD width="100%"><html:button property="limpar" value="Limpar" onclick="javascript:clearPage();"></html:button></TD>								
								<TD width="100%"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					
					</td>
					<td><IMG SRC='<%= request.getContextPath() %>/Common/image/spacer.gif' WIDTH="1" HEIGHT="36" /></td>
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