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

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Common/css/citi.css">
<script language="javascript"src="<%= request.getContextPath() %>/Common/js/dialog_window.js"></script>

	<script language="javascript">
		function extraActions(action){
			if (action == 'search'){
				document.forms[0].action = "OfficerCmpl.OfficerCmplDetail.Insert.Search.Officer";
			}
			else if (action =='Officer.OfficerList')
			{
				document.forms[0].action = "Officer.OfficerList.List.Show";
				document.forms[0].backURL.disabled = false;
			}
		}; 			
														
	</script>
	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="OfficerCmpl.OfficerCmplDetail"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	<TITLE>Inserção / Alteração de Banker - Dados Complementares</TITLE>
</HEAD>

<body>
	<html:form action="/OfficerCmpl.OfficerCmplDetail.Insert.Show.do">
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="CustomerSheet"/>
			<jsp:param name="currentSubSheet" value="Banker" />
		</jsp:include>
		<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
		<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>

		<table class="ODS_mainTable" width="100%" cellpadding="0"cellspacing="0" border="0">
			<tr>
				<td>&nbsp;</td>
				<html:text property="backURL" value="OfficerCmpl.OfficerCmplDetail.Insert.Show" style="display:none"></html:text>	
				<td>
					<table class="ODS_internalWidth" border="0">
						<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Inserção / Alteração de Banker - Dados Complementares</th></tr></thead>
						<tbody>
							<tr class="ODS_line11" height="25"><td colspan="3">Dados Cadastrais:</td></tr>
							<tr><td colspan="3"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td></tr>
	
							<tr class="ODS_Detail_Line1">
								<TD width="25%">Número do Banker</TD>
								<TD width="75%" colspan="2">Nome do Banker</TD>
							</tr>
							<tr class="ODS_Detail_Line2">
								<TD width="25%"><html:text property="offcrNbr" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
								<td width="75%" colspan="2"><html:text property="offcrNameText" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></td>
							</tr>
					
							<tr class="ODS_line11" height="25"><td colspan="3">Dados Complementares:</td></tr>
								<tr><td colspan="3"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td></tr>

							<tr class="ODS_Detail_Line2">
								<TD width="25%">Número Internacional</TD>
								<TD width="75%" colspan="2">Tipo *</TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<TD width="25%"><html:text property="offcrIntlNbr" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
								<TD width="75%" colspan="2">
									<html:select property="offcrTypeCode" styleClass="ODS_Select_Field_Size_20">
										<html:option value=""></html:option>
										<html:options property="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_CODE)" labelProperty="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_TEXT)" />
									</html:select>
								</TD>	
							</tr>
						</tbody>
					</TABLE>

					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%">&nbsp;</td>
								<TD><html:button property="inserir" value="Confirmar Alteração" onclick="submitAction('insert');"></html:button></TD>
								<TD><html:button property="limpar" value="Limpar" onclick="javascript:clearAllPage();"></html:button></TD>								
								<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
				</td>
				<td><IMG SRC='<%= request.getContextPath() %>/Common/image/spacer.gif' WIDTH="1" HEIGHT="36" /></td>
				<jsp:include page="/View/Util/Footer.jsp" flush="true" />
			</tr>
		</table>
	</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>