
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
		};
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="IpDocPrvt.IpDocPrvtHistoryList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn'" />
	<jsp:param name="searchInputFields" value="'custNbrSrc','ipDocCodeSrc','ipDocTextSrc','ipInvstCurAcctIndSrc'"/>
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
<TITLE>Consulta de Histórico de Instrução Permanente</TITLE>
</HEAD>

<body>
<html:form action="/IpDocPrvt.IpDocPrvtHistoryList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>


	<html:hidden property="selectedIpDocRefDate" value="0" />
	<html:hidden property="selectedCustNbr" value="0" />
	<html:hidden property="selectedIpDocCode" value="0" />

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:text property="backURL" value="IpDocPrvt.IpDocPrvtHistoryList.List.Show" style="display:none"></html:text>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Consulta de Histórico de Instrução Permanente</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
								<tr class="ODS_Detail_line1">
									<TD width="23%">Número do Cliente</TD>
									<TD colspan = "4">
										<html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_15" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;
										<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button>
									</TD>
								</tr>
								<tr class="ODS_Detail_line2">
									<TD width="23%">Nome do Cliente</TD>
									<TD colspan = "4">
										<html:text styleClass="ODS_Text_Field_Size_60" property="custFullNameText" maxlength="60"></html:text>&nbsp;&nbsp;
										<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button>
									</TD>
								</tr>
								<tr class="ODS_Detail_line1">
									<TD width="23%">Código</TD>
									<TD width="20%"><html:text property="ipDocCodeSrc" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
									<TD colspan="2" align="right" width="34%">Ind. Conta CCI&nbsp;&nbsp;</TD>
									<TD width="23%">										
										<html:select property="ipInvstCurAcctIndSrc" styleClass="ODS_Select_Field_Size_5" >
											<html:option value=""></html:option>
											<html:options property="ipInvstCurAcctIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="ipInvstCurAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
										</html:select>
									</TD>
								</tr>
								<tr class="ODS_Detail_line2">
									<TD width="23%">Data de Referência</TD>
									<td width="20%"><html:text property="ipDocRefDateSrc" styleClass="ODS_Text_Field_Size_10" maxlength="10" onkeydown="maskDate();" ></html:text></td>
									<TD colspan="3" ></TD>
								</tr>
							</tbody>
						</TABLE>

					<table class="ODS_internalWidth" border="0">
						<tbody>
							<TR>
								<TD width="100%"></TD>
								<TD><html:button property="listBtn" value="Consultar"
									onclick="submitAction('list');"></html:button></TD>
								<TD><html:button property="detailBtn"
									disabled="true" value="Detalhar"
									onclick="document.forms[0].custNbrSrc.disabled='true';submitAction('detail');"></html:button></TD>
								<TD><html:button property="clearBtn" value="Limpar"
									onclick="clearAllPage();"></html:button></TD>
							</TR>
						</tbody>
					</TABLE>

					<table class="ODS_internalWidth" border="0">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
							</tr>
						</thead>
						<tr>
							<td>
							<table id="gridTable" class="ODS_internalWidth" border="0">
								<tbody>
									<tr id="gridHeader" class="fixed">
										<TH class="ODS_header">&nbsp;</TH>
										<TH class="ODS_header" width="35%">Nome do Cliente</TH>
										<TH class="ODS_header" width="24%">Número do Cliente</TH>
										<TH class="ODS_header" width="16%">Código</TH>
										<TH class="ODS_header" width="15%">Data de Referência</TH>
									</tr>

									<ods:DataSetRows name="IpDocPrvtHistoryListForm" property="results"
										dataSetRowName="resultRow" stepIndexName="step"
										sequenceRestartStep="2">
										<logic:equal name="step" value="0">
											<tr class="ODS_line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_line2">
										</logic:equal>
										<bean:define name="resultRow"
											property="stringByName(CUST_NBR)" id="selectedCustNbr"
											type="java.lang.String"></bean:define>
										<bean:define name="resultRow"
											property="stringByName(PRMNT_INSTR_CODE)"
											id="selectedIpDocCode" type="java.lang.String"></bean:define>
										<bean:define name="resultRow"
											property="stringByName(PRMNT_INSTR_REF_DATE)"
											id="selectedIpDocRefDate" type="java.lang.String"></bean:define>
										<TD width="3%"><input type="radio" class="radio"
											name="selection"
											onclick="<%="javascript:selectedCustNbr.value=" + selectedCustNbr + 
															  		  ";selectedIpDocCode.value=" + selectedIpDocCode + 
																	  ";selectedIpDocRefDate.value='" + selectedIpDocRefDate + 
																	  "';disableButtons(false);" %>"></td>
										<TD width="35%"><a class="ODS_CursorHand" href="#" onclick="<%="javascript:selectedCustNbr.value=" + selectedCustNbr + 
															  		  ";selectedIpDocCode.value=" + selectedIpDocCode + 
																	  ";selectedIpDocRefDate.value='" + selectedIpDocRefDate + 
																	  "';document.forms[0].custNbrSrc.disabled='true';submitAction('detail');" %>"><bean:write name="resultRow"
											property="stringByName(CUST_FULL_NAME_TEXT)" /></a></td>
										<TD width="34%" align="right"><bean:write name="resultRow" property="stringByName(CUST_NBR)"
											formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></td>
										<TD width="16%" align="right"><bean:write name="resultRow" property="stringByName(PRMNT_INSTR_CODE)" 
											formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
										<TD width="15%" align="center"><bean:write name="resultRow" property="dateByName(PRMNT_INSTR_REF_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATE_DDMMYYYY %>'/></td>
									</ods:DataSetRows>
								</tbody>
							</table>
							</td>
						</tr>
					</TABLE>

					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%"></td>
								<TD><html:button property="backBtn" value="Voltar"
									onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
				</html:form> <jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</table>
		</td>
	</tr>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
