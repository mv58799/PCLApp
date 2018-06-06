
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
	};
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="CustAddress.CustAddressList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn'" />
	<jsp:param name="fieldsWithMask" value="['custCpfCnpjNbrSrc','CPF/CNPJ',null,null,null]"/>
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Endereços</TITLE>
</HEAD>

<body>
<html:form action="/CustAddress.CustAddressList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Customer" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<html:hidden property="selectedCustNbr" />
	<html:hidden property="selectedAddrSeqNbr" value="0" />
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>

			<html:text property="backURL"
				value="CustAddress.CustAddressList.List.Show" style="display:none"></html:text>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead> 
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Consulta de Endereços</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="16%">Número do Cliente</TD>
								<TD width="11%"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text>&nbsp;&nbsp;
								
								<TD width="170"></TD>
								<TD width="9%">CPF/CNPJ</TD>
								<TD width="47%"><html:text property="custCpfCnpjNbrSrc" styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text></TD>
								<TD></TD>
								
							</tr>
							<tr class="ODS_Detail_line2">
								
								<TD width="16%">Nome Cliente ou Empresa</TD>
								<TD colspan = "4"><html:text property="custFullNameTextSrc" styleClass="ODS_Text_Field_Size_60" disabled="true"></html:text></TD>
								<TD></TD>
								<TD></TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="16%">&nbsp;</TD>
								<TD width="11%"></TD>
								<TD width="15%"></TD>
								<TD width="99"></TD>
								<TD width="540"></TD>
								<TD></TD>
							</tr>
						</tbody>
					</TABLE>

					<table class="ODS_internalWidth" border="0">
						<tbody>
							<TR>
								<TD width="100%"></TD>
								<TD width="100%">&nbsp;</TD>
								<TD><html:button property="listBtn" value="Consultar"
									onclick="submitAction('list');"></html:button></TD>
								<TD width="100%"><html:button property="detailBtn"
									disabled="true" value="Detalhar"
									onclick="submitAction('detail');"></html:button></TD>
								<TD><html:button property="clearBtn" value="Limpar"
									onclick="clearPage();"></html:button></TD>
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
										<TH class="ODS_header" width="29%">Endereço</TH>
										<TH class="ODS_header" width="10%">Cidade</TH>
										<TH class="ODS_header" width="2%">Estado</TH>
										<TH class="ODS_header" width="10%">CEP</TH>
									</tr>

									<ods:DataSetRows name="CustAddressListForm" property="results"
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
											property="stringByName(ADDR_SEQ_NBR)"
											id="selectedAddrSeqNbr" type="java.lang.String"></bean:define>
										<TD width="3%"><input type="radio" class="radio"
											name="selection"
											onclick="<%="javascript:selectedCustNbr.value=" + selectedCustNbr + 
															  		  ";selectedAddrSeqNbr.value=" + selectedAddrSeqNbr + 
																	  ";disableButtons(false);" %>"></td>
										<TD width="29%" align="left">

										<a class="ODS_CursorHand" href="#" onclick="<%="javascript:selectedCustNbr.value=" + selectedCustNbr + ";selectedAddrSeqNbr.value=" + selectedAddrSeqNbr + ";submitAction('detail');"%>">
										<bean:write name="resultRow"
											property="stringByName(ADDR_NAME_TEXT)" /></a></td>

										<TD width="10%" align="left"><bean:write name="resultRow"
											property="stringByName(ADDR_CITY_TEXT)" /></td>
										<TD width="2%" align="center"><bean:write name="resultRow"
											property="stringByName(ADDR_STATE_CODE)" /></td>
										<TD width="10%" align="center"><bean:write name="resultRow"
											property="stringByName(ZIP_CODE)"  /> - 
											<bean:write name="resultRow"
											property="stringByName(ZIP_EXTN_CODE)"  /></td>
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
				</html:form> 
				<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</table>
		</td>
	</tr>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
