<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page
	import="com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtHistDetailForm"%>
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
	function extraActions(action){}																	
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="MrDocPrvt.MrDocPrvtHistDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Histórico de Memo de Risco</TITLE>
</HEAD>
<body>

<html:form action="/MrDocPrvt.MrDocPrvtHistDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<table class="ODS_mainTable" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<html:text property="backURL" value="MrDocPrvt.MrDocPrvtHistDetail.Consult.Show" style="display:none"></html:text>
			<td>
				<table class="ODS_internalWidth" border="0">
					<thead><tr><th class="subtitle" scope="colgroup" colspan="3" height="52">Detalhe de Histórico de Memo de Risco</th></tr></thead>
					<TBODY>
						<TR class="ODS_Detail_Line2">
							<TD colspan="4" >Conta Corrente</TD>
						</TR>
						<TR class="ODS_Detail_Line2">
							<TD colspan="4"><html:text property="curAcctNbrSrc"	styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></TD>
						</TR>
						<TR class="ODS_Detail_Line2">
							<td width="25%">Número do Cliente</td>
							<td colspan="3">Nome do Cliente</td>
						</TR>
						<TR class="ODS_Detail_Line2">
							<TD width="25%"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text></TD>
							<td colspan="3"><html:text property="custFullNameText" styleClass="ODS_Text_Field_Size_60" disabled="true"></html:text></td>
						</TR>
						<TR class="ODS_Detail_Line2">
							<td width="25%">Código do MR</td>
							<td width="47%">Descrição do MR</td>
							<td  colspan="2" width="28%">Ind. Conta CCI</td>
						</TR>
						<TR class="ODS_Detail_Line2">
							<td width="25%"><html:text property="mrDocCode"	styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></td>
							<td width="47%"><html:text property="mrDocText"	styleClass="ODS_Text_Field_Size_40" disabled="true"></html:text></td>
							<td width="28%" colspan="2">
								<html:select property="mrInvstCurAcctInd" styleClass="ODS_Select_Field_Size_5" disabled="true">
									<html:option value=""></html:option>
									<html:options
										property="mrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_CODE)"
										labelProperty="mrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
								</html:select>
							</td>
						</TR>
						<TR class="ODS_Detail_Line2">
							<td width="25%">Data de Referência</td>
							<td width="47%">Data/Hora da Última Alteração</td>
							<TD colspan="2" width="28%">Usuário da Última Alteração</TD>
						</TR>
						<TR class="ODS_Detail_Line2">
							<td width="25%"><html:text property="mrDocRefDate" styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
							<td width="47%"><html:text property="lastUpdUserId"	styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
							<td width="28%" colspan="2"><html:text property="lastUpdDate" styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
						</TR>
						<TR class="ODS_Detail_Line2">
							<td width="25%">Usuário da Última Autorização</td>
							<td width="47%">Data/Hora da Última Autorização</td>
							<TD colspan="2" width="28%"></TD>
						</TR>
						<TR class="ODS_Detail_Line2">
							<td width="25%"><html:text property="lastAuthUserId" styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
							<td width="47%"><html:text property="lastAuthDate"	styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
							<td width="28%" colspan="2"></td>
						</TR>
					</TBODY>
				</table>

				<table class="ODS_internalWidth" border="0">
					<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Dados do Callback</th></tr></thead>
					<tr>
						<td width="100%">
							<table class="ODS_internalWidth" border="0" id="grid">
								<tbody>
									<tr class="fixed">
										<TH class="ODS_header" width="50%">Nome do Contato</TH>
										<TH class="ODS_header" width="15%">DDD</TH>
										<TH class="ODS_header" width="20%">Telefone</TH>
										<TH class="ODS_header" width="15%">Ramal</TH>
									</tr>
									<%int auxIndex = 0;%>
									<logic:iterate name="MrDocPrvtHistDetailForm" property="fullNameTextArray" indexId="index" id="baseArray">

									<%MrDocPrvtHistDetailForm mrDocPrvtHistForm = ( MrDocPrvtHistDetailForm ) session.getAttribute( "MrDocPrvtHistDetailForm" );
									    String[] arrayCtcNbr = mrDocPrvtHistForm.getCtcNbrArray();

									    String indexCtcNbrArray = arrayCtcNbr[ auxIndex++ ];
								    %>
									<bean:define name="MrDocPrvtHistDetailForm"	property='<%="fullNameTextArray[" + index + "]"%>' id="fullNameTextArray" type="java.lang.String" />
									<bean:define name="MrDocPrvtHistDetailForm"	property='<%="phoneDddCodeArray[" + index + "]"%>' id="phoneDddCodeArray" type="java.lang.String" />
									<bean:define name="MrDocPrvtHistDetailForm"	property='<%="phoneNbrArray[" + index + "]"%>' id="phoneNbrArray" type="java.lang.String" />
									<bean:define name="MrDocPrvtHistDetailForm"	property='<%="phoneExtNbrArray[" + index + "]"%>' id="phoneExtNbrArray" type="java.lang.String" />

									<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
										<logic:equal name="step" value="0">
											<tr class="ODS_line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_line2">
										</logic:equal>
									</ods:CountStep>
									<td width="50%" align="center"><%=fullNameTextArray%></td>
									<td width="15%" align="center"><%=phoneDddCodeArray%></td>
									<td width="20%" align="center"><%=phoneNbrArray%></td>
									<td width="15%" align="center"><%=phoneExtNbrArray%></td>
								</logic:iterate>
							</tbody>
						</table>
					</td>
				</tr>
			</TABLE>

			<TABLE class="ODS_internalWidth" border="0">
				<TBODY>
					<TR>
						<TD width="100%"></TD>
						<TD><html:button property="backBtn" value="Voltar"	onclick="submitAction('back');"></html:button></TD>
					</TR>
				</TBODY>
			</TABLE>
			<jsp:include page="/View/Util/Footer.jsp" flush="true" />
		</td>
	</tr>
</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>


