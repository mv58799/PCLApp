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
			<jsp:param name="pageName" value="CurAcctPrmntInstr.CurAcctPrmntInstrMovementList" />
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Consulta de Associa��o Conta Corrente X Instru��o Permanente com Pend�ncia de Aprova��o</TITLE>

	</HEAD>

	<body>
		<html:form action="/CurAcctPrmntInstr.CurAcctPrmntInstrMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes"/>
		    </jsp:include>

			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>		

			<html:hidden property="selectedCustNbr" value=""/>	
			<html:hidden property="selectedProdUnderAcctCode" value=""/>
			<html:hidden property="selectedProdAcctCode" value=""/>
			<html:hidden property="selectedPrmntInstrCode" value=""/>

			<html:text property="backURL" value="CurAcctPrmntInstr.CurAcctPrmntInstrMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr><th class="subtitle" scope="colgroup" colspan="3">Consulta de Associa��o Conta Corrente X Instru��o Permanente com Pend�ncia de Aprova��o</th></tr>
							</thead>
							<tr>
								<td>
									<table class="ODS_internalWidth" border="0" cellspacing="0">
										<tbody>
											<tr class="ODS_line11" height="25"><td colspan="3">Conta Corrente:</td></tr>
											<tr>
						 						<td colspan="4"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1" width="100%"></td>
											</tr>
											<tr class="ODS_Detail_Line1">
												<TD width="15%">Conta</TD>
												<TD colspan="3"><html:text styleClass="ODS_Text_Field_Size_15" property="curAcctNbrSrc" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getCustomerBtn" value="Buscar" onclick="submitAction('PreparedSearch.Contract.CurAccountList');"></html:button></TD>
											</tr>

											<tr class="ODS_Detail_Line2">
												<TD width="15%">Nro. Cliente</TD>
												<TD colspan="3"><html:text styleClass="ODS_Text_Field_Size_15" property="custNbrSrc"  maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getCurAccountBtn" value="Buscar" onclick="submitAction('PreparedSearch.Customer.CustomerPrvtList');"></html:button></TD>
											</tr>

											<tr class="ODS_Detail_Line1">
												<TD width="15%">Nome do Cliente</TD>
												<TD colspan="3"><html:text styleClass="ODS_Text_Field_Size_60" property="custFullNameSrc" maxlength="60"></html:text></TD>
											</tr>
									
											<tr class="ODS_line11" height="25"><td colspan="3">Instru��o Permanente:</td></tr>
											<tr>
						 						<td colspan="4"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' height="1" width="100%"></td>
											</tr>
										
											<tr class="ODS_Detail_Line1">
												<td width="15%">C�digo </td>
												<td width="28%"><html:text	styleClass="ODS_Text_Field_Size_10" property="prmntInstrCodeSrc" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.IpDocPrvt.IpDocPrvtList');"></html:button></td>
												<td width="15%" align="right">&nbsp;&nbsp;&nbsp;Ind. Conta CCI</td>
												<td width="42%">
													<html:select property="prmntInstrInvstCurAcctIndSrc" styleClass="ODS_Select_Field_Size_5">
														<html:option value=""></html:option>
														<html:options property="prmntInstrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="prmntInstrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
													</html:select>
												</td>
											</tr>
											<tr class="ODS_Detail_Line2">
												<td colspan="4">
													<table width="100%">
														<tr>
															<TD width="23%">Usu�rio de �ltima Atualiza��o</TD>
															<TD width="48%"><html:text property="lastUpdUserIdSrc" styleClass="ODS_Text_Field_Size_20" maxlength="20"/></TD>
															<td width="29%">&nbsp;</td>
														</tr>
													</table>
												</td>
											</tr>
										</tbody>
									</TABLE>

									<table class="ODS_internalWidth" border="0">
										<tbody>
											<TR>
												<TD width="100%">&nbsp;</TD>
												<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
												<TD><html:button property="approvedBtn"  value="Aprova��o" onclick="submitAction('approve');" disabled="true"></html:button></TD>
												<TD><html:button property="alterBtn" value="Alterar" onclick="submitAction('update');" disabled="true"></html:button></TD>
												<TD><html:button property="clearBtn" value="Limpar" onclick="clearAllPage();"></html:button></TD>
											</TR>
										</tbody>
									</TABLE>

									<table class="ODS_internalWidth" border="0">
										<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead>
									</table>

									<DIV class="ODS_DivGrid">
										<table id="gridTable" class="ODS_internalWidth" border="0">
											<tbody>
												<tr class="fixed" id="gridHeader">
													<TH class="ODS_header">&nbsp;</TH>
													<TH class="ODS_header" width="27%">Nome do Cliente</TH>
													<TH class="ODS_header" width="10%">N�mero do Cliente</TH>
													<TH class="ODS_header" width="10%">Conta Corrente</TH>
													<TH class="ODS_header" width="10%">Ind. Conta CCI</TH>
													<TH class="ODS_header" width="10%">Conta CCI</TH>
													<TH class="ODS_header" width="10%">C�digo</TH>
													<TH class="ODS_header" width="10%">Usu�rio de �ltima Atualiza��o</TH>
													<TH class="ODS_header" width="10%">A��o</TH>
												</tr>
												
												<ods:DataSetRows name="CurAcctPrmntInstrMovementListForm" property="results" dataSetRowName="resultRow"	stepIndexName="step" sequenceRestartStep="2">
													<logic:equal name="step" value="0">
														<tr class="ODS_Line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_Line2">
													</logic:equal>
													<bean:define name="resultRow" property="stringByName(CUST_NBR)" id="selectedCustNbr" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(PROD_ACCT_CODE)" id="selectedProdAcctCode" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(PROD_UNDER_ACCT_CODE)"	id="selectedProdUnderAcctCode" type="java.lang.String"></bean:define>												
													<bean:define name="resultRow" property="stringByName(PRMNT_INSTR_CODE)"	id="selectedPrmntInstrCode" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserId" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
					
													<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedCustNbr.value='<%= selectedCustNbr %>'; selectedProdAcctCode.value='<%= selectedProdAcctCode %>'; selectedProdUnderAcctCode.value='<%= selectedProdUnderAcctCode %>'; selectedPrmntInstrCode.value='<%= selectedPrmntInstrCode %>';disableButtons(false);disableApproveButtons('<%=lastUpdUserId%>','<%= opernCode%>','true');"></td>
													<TD width="27%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedCustNbr.value='<%=selectedCustNbr%>';selectedProdAcctCode.value='<%=selectedProdAcctCode%>'; selectedProdUnderAcctCode.value= '<%=selectedProdUnderAcctCode%>';selectedPrmntInstrCode.value='<%=selectedPrmntInstrCode%>';submitAction('approve');">
														<bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)" /></td>
													<TD width="10%"  class="alignRight"><bean:write name="resultRow"	property="stringByName(CUST_NBR)" /></td>
													<TD width="10%"  class="alignRight"><bean:write name="resultRow" property="stringByName(CUR_ACCT_NBR)" /></td>
													<TD width="10%" class="centralized"><bean:write name="resultRow" property="stringByName(INDICATOR_TEXT)" /></td>
													<TD width="10%"  class="alignRight"><bean:write name="resultRow"	property="stringByName(INVST_CUR_ACCT_NBR)" /></td>
													<TD width="10%"  class="alignRight"><bean:write name="resultRow" property="stringByName(PRMNT_INSTR_CODE)" /></td>
													<TD width="10%" class="alignRight"><bean:write name="resultRow"	property="stringByName(LAST_UPD_USER_ID)" /></td>
													<TD width="10%" class="alignRight"><bean:write name="resultRow"	property="stringByName(OPERN_TEXT)" /></td>
												</ods:DataSetRows>
											</tbody>
										</table>
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
