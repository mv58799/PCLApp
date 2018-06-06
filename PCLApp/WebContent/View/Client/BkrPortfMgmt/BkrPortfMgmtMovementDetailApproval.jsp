<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.bkrportfmgmt.form.BkrPortfMgmtMovementDetailForm"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions(action)
		{
			if (action =='listBkrPortfMgmt')
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtMovementDetail.Approval.ListBkrPortfMgmt";
				document.forms[0].backURL.disabled = false;
			}
		};

		function listBkrPortfMgmtGrid( radioId )
		{	
			if ( radioId.id == 'pmaRadioButton' )
			{
				submitAction('listBkrPortfMgmt');				
			}
		};

		function enableReproveButton()
		{
			document.getElementById( 'reproveBtn' ).disabled = false;
		};

		function enableApproveButton() 
		{	
			<%
				User user = (User)session.getAttribute(BaseAction.C_USER_SESSION_ID);
				String userId = user!=null?user.getUserID():null;
			%>

			var loggedUser = '<%=userId%>';
			var makerUser = document.forms[0].makerUser.value;

			if ( loggedUser != makerUser )
			{
				document.getElementById( 'approvedBtn' ).disabled = false;
				document.forms[0].approveButtonState.value = 'true';
			}

		};

		function enableButtons()
		{	
			var loggedUser = '<%=userId%>';
			var makerUser = document.forms[0].makerUser.value;

			if ( document.forms[0].selectedProdAcctCode.value != '' )
			{
				document.getElementById( 'reproveBtn' ).disabled = false;
			}	

			if ( ( document.forms[0].selectedProdUnderAcctCode.value != '' ) && ( loggedUser != makerUser ) )
			{
				document.getElementById( 'approvedBtn' ).disabled = false;
				document.forms[0].approveButtonState.value = 'true';
			}

		};

	</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="BkrPortfMgmt.BkrPortfMgmtMovementDetail"/>
			<jsp:param name="fieldsWithMask" value="['bkrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Aprovação de Associação de Carteira x Corretora </TITLE>
	</HEAD>

	<body>
		<html:form action="/BkrPortfMgmt.BkrPortfMgmtMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Approved"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="BkrPortfMgmt.BkrPortfMgmtMovementList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="5">Aprovação de Associação de Carteira x Corretora</th>
								</tr>
							</thead>

							<tr class="ODS_Detail_line1">
								<td colspan="5">										
									<table><tr>
										<TD width="12%">Número do Cliente</TD>
										<TD width="68%"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)" disabled="true"></html:text></TD>
									</tr>
									<tr>
										<TD width="12%">Nome do Cliente</TD>
										<TD width="68%"><html:text property="custFullNameTextSrc" styleClass="ODS_Text_Field_Size_60" maxlength="60" disabled="true"></html:text></TD>
									</tr></table>																		
								</td>
			        		</tr>

							<tr class="ODS_line11" height="25"><td colspan="5">Carteiras Administradas</td></tr>

							<tr height="8">
		 						<td colspan="5" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="250%" height="1"></td>
							</tr>

							<tr class="ODS_Detail_Line1">
							<td>

								<html:hidden property="selectedProdAcctCode" />
								<html:hidden property="selectedProdUnderAcctCode" />
								<html:hidden property="confirmAssociationEnabled" />
								<html:hidden property="insertBtnPressed" />
								<html:hidden property="approveButtonState" />
								<html:hidden property="makerUser" />
	
								<table id="gridTable" class="ODS_internalWidth" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="2%">&nbsp;</TH>
											<TH class="ODS_header" width="13%">Conta Corrente	</TH>
											<TH class="ODS_header" width="16%">Conta Investimento</TH>
											<TH class="ODS_header" width="31%">Nome da Carteira</TH>
											<TH class="ODS_header" width="15%">Mnemônico</TH>
											<TH class="ODS_header" width="8%">Produto</TH>
											<TH class="ODS_header" width="15%">Usuário Última Atualização</TH>
										</tr>

										<ods:DataSetRows name="BkrPortfMgmtMovementDetailForm" property="portfolioResults"
											dataSetRowName="resultRow" stepIndexName="step" 
											sequenceRestartStep="2" >
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2">
											</logic:equal>

											<bean:define name="resultRow"
												property="stringByName(LAST_UPD_USER_ID)" id="makerUser"
												type="java.lang.String">
											</bean:define>

											<bean:define name="resultRow"
												property="bigDecimalByName(PROD_ACCT_CODE)" id="selectedProdAcctCode"
												type="java.math.BigDecimal">
											</bean:define>
	
											<bean:define name="resultRow"
												property="bigDecimalByName(PROD_UNDER_ACCT_CODE)" id="selectedProdUnderAcctCode"
												type="java.math.BigDecimal">
											</bean:define>

											<TD width="2%" align="center">
												<input type="radio" class="radio" name="selection" id="pmaRadioButton"

												<logic:equal name="BkrPortfMgmtMovementDetailForm" property="selectedProdAcctCode" value="<%= selectedProdAcctCode.toString() %>">
													<logic:equal name="BkrPortfMgmtMovementDetailForm" property="selectedProdUnderAcctCode" value="<%= selectedProdUnderAcctCode.toString() %>">
														checked="checked" disabled="true"
													</logic:equal>
												</logic:equal> 
									
												onclick="javascript:selectedProdAcctCode.value='<%=selectedProdAcctCode%>';selectedProdUnderAcctCode.value='<%=selectedProdUnderAcctCode%>';makerUser.value='<%=makerUser%>';listBkrPortfMgmtGrid(this);enableReproveButton();enableApproveButton();"/>
											</td>

											<TD width="13%" align="right"><bean:write name="resultRow"
												property="stringByName(CUR_ACCT_NBR)" /></a></td>
	
											<TD width="16%" align="right"><bean:write name="resultRow"
												property="stringByName(INVST_CUR_ACCT_NBR)" /></td>
	
											<TD width="31%" align="left"><bean:write name="resultRow"
												property="stringByName(PORTF_MGMT_PROD_NAME)" /></td>
	
											<TD width="15%" align="left"><bean:write name="resultRow"
												property="stringByName(CUST_MNMC_NAME)"  /></td>
	
											<TD width="8%" align="left"><bean:write name="resultRow"
												property="stringByName(PROD_CODE)"  /></td>

											<TD width="15%" align="left"><bean:write name="resultRow"
												property="stringByName(LAST_UPD_USER_ID)"  /></td>

										</ods:DataSetRows>
	
									</tbody>
								</table>
							</td>

							</tr>

						</TABLE>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>

					<DIV class="ODS_DivGrid">
						<table border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Corretoras Associadas a Carteira Administrada</th>
								</tr>
							</thead>
							<tr>
								<td width="100%">
								<table id="gridTable" width="1300px" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="9%">CNPJ</TH>
											<TH class="ODS_header" width="36%">Razão Social da Corretora</TH>
											<TH class="ODS_header" width="12%">C/C Bovespa</TH>
											<TH class="ODS_header" width="12%">CCI Bovespa</TH>
											<TH class="ODS_header" width="12%">C/C BMF</TH>
											<TH class="ODS_header" width="12%">CCI BMF</TH>
											<TH class="ODS_header" width="7%">Ação</TH>
										</tr>

										<tr class="fixed">
										<%int rowIndexBkrPortfMgmt = 0;%>
										<logic:notEmpty name="BkrPortfMgmtMovementDetailForm"	property="bkrCnpjNbrInBkrPortfMgmtGrid">
											<logic:iterate name="BkrPortfMgmtMovementDetailForm"
												property="bkrCnpjNbrInBkrPortfMgmtGrid" indexId="index" id="row">

												<ods:CountStep counterName="index" counterStartIndex="0"
													sequenceRestartStep="2" stepIndexName="step">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2">
													</logic:equal>
												</ods:CountStep>

												<td class="alignRight" width="9%"><bean:write name="BkrPortfMgmtMovementDetailForm" property='<%="bkrCnpjNbrInBkrPortfMgmtGrid["+index+"]"%>'></bean:write></td>
												<td class="alignLeft" width="36%"><bean:write name="BkrPortfMgmtMovementDetailForm" property='<%="bkrNameTextInBkrPortfMgmtGrid["+index+"]"%>'></bean:write></td>
												<td class="alignRight" width="12%"><bean:write name="BkrPortfMgmtMovementDetailForm" property='<%="bovespaCurAcctNbrInBkrPortfMgmtGrid["+index+"]"%>'></bean:write>
												<td class="alignRight" width="12%"><bean:write name="BkrPortfMgmtMovementDetailForm" property='<%="bovespaInvstAcctNbrInBkrPortfMgmtGrid["+index+"]"%>'></bean:write>
												<td class="alignRight" width="12%"><bean:write name="BkrPortfMgmtMovementDetailForm" property='<%="bmfCurAcctNbrInBkrPortfMgmtGrid["+index+"]"%>'></bean:write>
												<td class="alignRight" width="12%"><bean:write name="BkrPortfMgmtMovementDetailForm" property='<%="bmfInvstAcctNbrInBkrPortfMgmtGrid["+index+"]"%>'></bean:write>
												<td width="7%" align="center"><bean:write name="BkrPortfMgmtMovementDetailForm" property='<%="opernCodeInBkrPortfMgmtGrid["+index+"]"%>'></bean:write>

											</logic:iterate>
										</logic:notEmpty>
										</tr>
									</tbody>
								</table>

							</tr>
						</TABLE>
					</DIV>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%"></TD>
											<TD><html:button property="approvedBtn" value="Aprovar" onclick="submitAction('approve');" disabled="true"></html:button></TD>
											<TD><html:button property="reproveBtn" value="Reprovar" onclick="submitAction('reprove','', true);" disabled="true"></html:button></TD>
											<td><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
<script>enableButtons();</script>
</html:html>