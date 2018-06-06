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
<script language=javascript>
	function extraActions(action){

		if (action =='CustomerPrvt.CustomerPrvtList')
		{
			document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.Show";
			document.forms[0].backURL.disabled = false;
		}
		else if (action =='Contract.CurAccountList')
		{
			document.forms[0].action = "Contract.CurAccountList.List.Show";
			document.forms[0].backURL.disabled = false;
		}
	};
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="MrDocPrvt.MrDocPrvtHistList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Histórico de Memo de Risco</TITLE>
</HEAD>

<body>
<html:form action="/MrDocPrvt.MrDocPrvtHistList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:hidden property="selectedMrDocCode" />
	<html:hidden property="selectedProdAcctCode" />
	<html:hidden property="selectedProdUnderAcctCode" />
	<html:hidden property="selectedMrDocRefDate" />

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:text property="backURL" value="MrDocPrvt.MrDocPrvtHistList.List.Show" style="display:none"></html:text>
				<td>
					<table class="ODS_internalWidth" border="0">
						<thead><tr><th class="subtitle" scope="colgroup" colspan="5">Consulta de Histórico de Memo de Risco</th></tr></thead>
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="16%">Código</TD>
								<TD width="25%"><html:text property="mrDocCodeSrc"	styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
								<TD align="right" width="15%">Descrição&nbsp;</TD>
								<TD colspan="2" width="44%"><html:text property="mrDocTextSrc"styleClass="ODS_Text_Field_Size_40" maxlength="40"></html:text></TD>
							</tr>
							<TR class="ODS_Detail_line1">
								<TD width="16%">Nro. Cliente</TD>
								<TD colspan="4"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
							</TR>
							<tr class="ODS_Detail_line1">
								<TD width="16%">Nome do Cliente</TD>	
								<TD colspan="4">
									<html:text property="custFullNameTextSrc" styleClass="ODS_Text_Field_Size_60"></html:text>&nbsp;&nbsp;
									<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button>
								</TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="16%">Conta Corrente</TD>
								<TD colspan="4">
									<html:text property="curAcctNbrSrc"	styleClass="ODS_Text_Field_Size_20" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;
									<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.Contract.CurAccountList');"></html:button>
								</TD>
							</tr>	
							<tr class="ODS_Detail_line1">
								<td width="16%">Ind. Conta CCI</td>
								<td width="25%">
									<html:select property="mrInvstCurAcctIndSrc" styleClass="ODS_Select_Field_Size_5">
										<html:option value=""></html:option>
										<html:options property="mrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_CODE)"
											labelProperty="mrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
									</html:select>
								</td>
								<TD  align="right" width="15%">Data de Referência&nbsp;</TD>
								<TD colspan="2" width="44%"><html:text property="mrDocRefDateSrc" styleClass="ODS_Text_Field_Size_10" maxlength="10" onkeydown="maskDate();"></html:text></TD>
							</tr>	
						</tbody>
					</TABLE>

					<table class="ODS_internalWidth" border="0">
						<tbody>
							<TR>
								<TD width="100%"></TD>
								<TD><html:button property="listBtn" value="Consultar"
									onclick="submitAction('list');"></html:button></TD>
								<TD><html:button property="detailBtn" disabled="true"
									value="Detalhar" onclick="submitAction('detail');"></html:button></TD>
								<TD><html:button property="clearBtn" value="Limpar"
									onclick="clearAllPage();"></html:button></TD>
							</TR>
						</tbody>
					</TABLE>
	
					<DIV class="ODS_DivGrid">
						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead>
							<tr>
								<td>
									<table id="gridTable" class="ODS_internalWidth" border="0">
										<tbody>
											<tr id="gridHeader" class="fixed">
												<TH class="ODS_header">&nbsp;</TH>
												<TH class="ODS_header" width="12%">Nome do Cliente</TH>
												<TH class="ODS_header" width="12%">Nro. Cliente</TH>
												<TH class="ODS_header" width="12%">Conta Corrente</TH>
												<TH class="ODS_header" width="12%">Conta CCI</TH>
												<TH class="ODS_header" width="8%">Código</TH>
												<TH class="ODS_header" width="34%">Descrição</TH>
												<TH class="ODS_header" width="14%">Data de Referência</TH>
											</tr>
											<ods:DataSetRows name="MrDocPrvtHistListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
												<logic:equal name="step" value="0">
													<tr class="ODS_line1">
												</logic:equal>
												<logic:equal name="step" value="1">
													<tr class="ODS_line2">
												</logic:equal>
												<bean:define name="resultRow" property="stringByName(PRVT_MR_CODE)" id="selectedMrDocCode" type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(PROD_ACCT_CODE)" id="selectedProdAcctCode" type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(PROD_UNDER_ACCT_CODE)"	id="selectedProdUnderAcctCode" type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(MR_REF_DATE)" id="selectedMrDocRefDate" type="java.lang.String"></bean:define>
												<TD width="3%"><input type="radio" class="radio" name="selection" onclick="<%="javascript:selectedMrDocCode.value=" + selectedMrDocCode + ";selectedProdAcctCode.value="+ selectedProdAcctCode + ";selectedProdUnderAcctCode.value=" + selectedProdUnderAcctCode + ";selectedMrDocRefDate.value='" + selectedMrDocRefDate + "';disableButtons(false);" %>"></td>
												<TD width="12%"><a class="ODS_CursorHand" href="#" onclick="<%="javascript:selectedMrDocCode.value=" + selectedMrDocCode + ";selectedProdAcctCode.value="+ selectedProdAcctCode + ";selectedProdUnderAcctCode.value=" + selectedProdUnderAcctCode + ";selectedMrDocRefDate.value='" + selectedMrDocRefDate + "';submitAction('detail');" %>"><bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)" /></a></td>
												<TD width="12%"><bean:write name="resultRow" property="stringByName(CUST_NBR)" /></td>
												<TD width="12%" class="alignRight"><bean:write name="resultRow"	property="stringByName(CUR_ACCT_NBR)" /></td>
												<TD width="12%" class="alignRight"><bean:write name="resultRow"	property="stringByName(INVST_CUR_ACCT_NBR)" /></td>
												<TD width="8%" class="alignRight"><bean:write name="resultRow" property="stringByName(PRVT_MR_CODE)" /></td>
												<TD width="34%"><bean:write name="resultRow" property="stringByName(PRVT_MR_TEXT)" /></td>
												<TD width="14%" class="centralized"><bean:write	name="resultRow" property="dateByName(MR_REF_DATE)"	formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATE_DDMMYYYY%>' /></td>
											</ods:DataSetRows>
										</table>
									</td>
								</tr>
							</TABLE>
						</DIV>
	
						<TABLE class="ODS_internalWidth" border="0">
							<TBODY>
								<TR>
									<td width="100%"></td>
									<TD><html:button property="backBtn" value="Voltar"	onclick="submitAction('back');"></html:button></TD>
								</TR>
							</TBODY>
						</TABLE>
						<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
