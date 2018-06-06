
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
	
		<script language="javascript">
	
			function extraActions( action )
			{
				if ( action == 'IpDocPrvt.IpDocPrvtList' ) {
					document.forms[0].action = "IpDocPrvt.IpDocPrvtList.List.Show"; 
					document.forms[0].backURL.disabled = false; 
				}
				else if ( action == 'Customer.CustomerPrvtList' ) {
					document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.Show"; 
					document.forms[0].backURL.disabled = false; 
				}
				else if ( action == 'Contract.CurAccountList' ) {
					document.forms[0].action = "Contract.CurAccountList.List.Show"; 
					document.forms[0].backURL.disabled = false; 
				}
			};
	
		 </script>		
	
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="CurAcctPrmntInstr.CurAcctPrmntInstrHistoryList" />
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'detailBtn'" />
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Consulta de Hist�rico de Associa��o Conta Corrente X Instru��o Permanente</TITLE>

	</HEAD>

	<body>
		<html:form action="/CurAcctPrmntInstr.CurAcctPrmntInstrHistoryList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes"/>
		    </jsp:include>

			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>		

			<html:hidden property="selectedCustNbrHist" value=""/>	
			<html:hidden property="selectedProdUnderAcctCode" value=""/>
			<html:hidden property="selectedProdAcctCode" value=""/>
			<html:hidden property="selectedPrmntInstrCodeHist" value=""/>
			<html:hidden property="selectedCurAcctPrmntInstrRefDate" value=""/>

			<html:text property="backURL" value="CurAcctPrmntInstr.CurAcctPrmntInstrHistoryList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr><th class="subtitle" scope="colgroup" colspan="5">Consulta de Hist�rico de Associa��o Conta Corrente X Instru��o Permanente</th></tr>
							</thead>
							<tr>
								<td>
									<table class="ODS_internalWidth" border="0" cellspacing="0">
										<tbody>
											<tr class="ODS_line11" height="25"><td colspan="5">Conta Corrente:</td></tr>
											<tr>
						 						<td colspan="5"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1" width="100%"></td>
											</tr>
											<tr class="ODS_Detail_Line1">
												<TD width="15%">Conta</TD>
												<TD colspan="4"><html:text styleClass="ODS_Text_Field_Size_15" property="curAcctNbrSrc" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getCustomerBtn" value="Buscar" onclick="submitAction('PreparedSearch.Contract.CurAccountList');"></html:button></TD>
											</tr>

											<tr class="ODS_Detail_Line2">
												<TD width="15%">Nro. Cliente</TD>
												<TD colspan="4"><html:text styleClass="ODS_Text_Field_Size_15" property="custNbrHistSrc"  maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getCurAccountBtn" value="Buscar" onclick="submitAction('PreparedSearch.Customer.CustomerPrvtList');"></html:button></TD>
											</tr>

											<tr class="ODS_Detail_Line1">
												<TD width="15%">Nome do Cliente</TD>
												<TD colspan="4"><html:text styleClass="ODS_Text_Field_Size_60" property="custFullNameSrc" maxlength="60"></html:text></TD>
											</tr>
									
											<tr class="ODS_line11" height="25"><td colspan="5">Instru��o Permanente:</td></tr>
											<tr>
						 						<td colspan="5"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' height="1" width="100%"></td>
											</tr>
										
											<tr class="ODS_Detail_Line1">
												<td colspan="5">
													<TABLE>
														<tr>
															<td width="7%">C�digo </td>
															<td width="25%"><html:text	styleClass="ODS_Text_Field_Size_10" property="prmntInstrCodeHistSrc" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.IpDocPrvt.IpDocPrvtList');"></html:button></td>
															<td width="20%" align="right">&nbsp;Ind. Conta CCI</td>
															<td width="16%">
																<html:select property="prmntInstrInvstCurAcctIndSrc" styleClass="ODS_Select_Field_Size_5">
																	<html:option value=""></html:option>
																	<html:options property="prmntInstrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="prmntInstrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
																</html:select>
															</td>
															<TD width="18%" align="right">&nbsp;Data de Refer�ncia</TD>
															<TD width="20%"><html:text property="curAcctPrmntInstrRefDate" styleClass="ODS_Text_Field_Size_10" maxlength="10" onkeydown="maskDate();"/></TD>
														<tr>
													</TABLE>
												</td>
											</tr>
										</tbody>
									</TABLE>

									<table class="ODS_internalWidth" border="0">
										<tbody>
											<TR>
												<TD width="100%">&nbsp;</TD>
												<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
												<TD><html:button property="detailBtn" value="Detalhar" onclick="submitAction('detail');" disabled="true"></html:button></TD>
												<TD><html:button property="clearBtn" value="Limpar" onclick="clearAllPage();"></html:button></TD>
											</TR>
										</tbody>
									</TABLE>

									<DIV class="ODS_DivGrid">
										<table class="ODS_internalWidth" border="0">
											<thead>
												<tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr>
											</thead>
								
											<tr>
												<td width="100%">
													<table id="gridTable" class="ODS_internalWidth" border="0">
														<tbody>
														<tr class="fixed" id="gridHeader">
															<TH class="ODS_header">&nbsp;</TH>
															<TH class="ODS_header" width="37%">Nome do Cliente</TH>
															<TH class="ODS_header" width="10%">Data de Refer�ncia</TH>
															<TH class="ODS_header" width="10%">N�mero do Cliente</TH>
															<TH class="ODS_header" width="10%">Conta Corrente</TH>
															<TH class="ODS_header" width="10%">Ind. Conta CCI</TH>
															<TH class="ODS_header" width="10%">Conta CCI</TH>
															<TH class="ODS_header" width="10%">C�digo</TH>
														</tr>
															<ods:DataSetRows name="CurAcctPrmntInstrHistoryListForm" property="results" dataSetRowName="resultRow"	stepIndexName="step" sequenceRestartStep="2">
															<logic:equal name="step" value="0">
																<tr class="ODS_Line1">
															</logic:equal>
															<logic:equal name="step" value="1">
																<tr class="ODS_Line2">
															</logic:equal>
															<bean:define name="resultRow" property="stringByName(CUST_NBR)" id="selectedCustNbrHist" type="java.lang.String"></bean:define>
															<bean:define name="resultRow" property="stringByName(PROD_ACCT_CODE)" id="selectedProdAcctCode" type="java.lang.String"></bean:define>
															<bean:define name="resultRow" property="stringByName(PROD_UNDER_ACCT_CODE)"	id="selectedProdUnderAcctCode" type="java.lang.String"></bean:define>												
															<bean:define name="resultRow" property="stringByName(PRMNT_INSTR_CODE)"	id="selectedPrmntInstrCodeHist" type="java.lang.String"></bean:define>
															<bean:define name="resultRow" property="stringByName(CUR_ACCT_PRMNT_INSTR_REF_DATE)"id="selectedCurAcctPrmntInstrRefDate" type="java.lang.String"></bean:define>
							
															<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedCustNbrHist.value='<%= selectedCustNbrHist %>'; selectedProdAcctCode.value='<%= selectedProdAcctCode %>'; selectedProdUnderAcctCode.value='<%= selectedProdUnderAcctCode %>'; selectedPrmntInstrCodeHist.value='<%= selectedPrmntInstrCodeHist %>';selectedCurAcctPrmntInstrRefDate.value='<%=selectedCurAcctPrmntInstrRefDate%>';disableButtons(false);"></td>
															<TD width="37%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedCustNbrHist.value='<%=selectedCustNbrHist%>';selectedProdAcctCode.value='<%=selectedProdAcctCode%>'; selectedProdUnderAcctCode.value= '<%=selectedProdUnderAcctCode%>';selectedPrmntInstrCodeHist.value='<%=selectedPrmntInstrCodeHist%>';selectedCurAcctPrmntInstrRefDate.value='<%=selectedCurAcctPrmntInstrRefDate%>';submitAction('detail');">
																<bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)" /></td>
															<TD width="10%"  class="centralized"><bean:write name="resultRow"	property="dateByName(CUR_ACCT_PRMNT_INSTR_REF_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATE_DDMMYYYY%>' /></td>
															<TD width="10%"  class="alignRight"><bean:write name="resultRow"	property="stringByName(CUST_NBR)" /></td>
															<TD width="10%"  class="alignRight"><bean:write name="resultRow" property="stringByName(CUR_ACCT_NBR)" /></td>
															<TD width="10%" class="centralized"><bean:write name="resultRow"	property="stringByName(INDICATOR_TEXT)" /></td>
															<TD width="10%"  class="alignRight"><bean:write name="resultRow"	property="stringByName(INVST_CUR_ACCT_NBR)" /></td>
															<TD width="10%"  class="alignRight"><bean:write name="resultRow" property="stringByName(PRMNT_INSTR_CODE)" /></td>
														</ods:DataSetRows>
											</tbody>
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

						<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
					</table>	
				</td>
			</tr>
		</table>	
	</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
