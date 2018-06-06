<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.bkrportfmgmt.form.BkrPortfMgmtDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		

	<script language="javascript">
		function extraActions( action )
		{
			if ( action == 'insertDomain' ) 
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtDetail.Insert.InsertDomain"; 
				document.forms[0].backURL.disabled = true; 
			}
			else if ( action == 'deleteDomain' ) 
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtDetail.Insert.DeleteDomain"; 
				document.forms[0].backURL.disabled = true; 
			}
			else if (action =='listBroker')
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtDetail.Insert.List";
				document.forms[0].backURL.disabled = false;
			}
			else if (action =='listBkrPortfMgmt')
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtDetail.Insert.ListBkrPortfMgmt";
				document.forms[0].backURL.disabled = false;
			}
			else if (action =='updateBkrPortfMgmt')
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtDetail.Insert.Update";
				document.forms[0].insertBtnPressed.value = 'true';
				document.forms[0].backURL.disabled = false;
			}	
			else if(action== 'clearPage')
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtDetail.Insert.ClearPage";
				document.forms[0].backURL.disabled = false;	
			}
		};

		function switchCheckBoxState( checkBox, checkBoxIndex )
		{
			var deleteCheckBox = 'deleteCheckBox[' + checkBoxIndex + ']';
			var updateCheckBox = 'updateCheckBox[' + checkBoxIndex + ']';
		
			if( ( checkBox.id == deleteCheckBox && document.getElementById( deleteCheckBox ).checked == true ) || ( checkBox.id == updateCheckBox && document.getElementById( updateCheckBox ).checked == false ) )	
			{	
				document.getElementById( updateCheckBox ).checked = false;	
				document.getElementById( updateCheckBox ).value = 'N';

				document.getElementById( deleteCheckBox ).checked = true;	
				document.getElementById( deleteCheckBox ).value = 'S';
			}
			else if( ( checkBox.id == deleteCheckBox && document.getElementById( deleteCheckBox ).checked == false ) || ( checkBox.id == updateCheckBox && document.getElementById( updateCheckBox ).checked == true ) )	
			{	
				document.getElementById( updateCheckBox ).checked = true;	
				document.getElementById( updateCheckBox ).value = 'S';

				document.getElementById( deleteCheckBox ).checked = false;	
				document.getElementById( deleteCheckBox ).value = 'N';
			}
		};

		function listBkrPortfMgmtGrid( event )
		{	
			submitAction('listBkrPortfMgmt');				
		};

		function enableAddBrokerButton() 
		{
			if ( document.forms[0].selectedProdAcctCode.value != '' && document.forms[0].hasErrors.value != 'true' ) 
			{
				document.getElementById( 'addBrokerBtn' ).disabled = false;
			}
		};

		function enableInsertButton()
		{
			enableAddBrokerButton();
			validateCheckBoxes();

			insertButtonControl = document.getElementById( 'insertBtn' );
			listBrokerButtonControl = document.getElementById( 'listBrokerBtn' );

			if ( insertButtonControl && listBrokerButtonControl )
			{
				if ( document.forms[0].selectedProdAcctCode.value != '' )
				{
					insertButtonControl.disabled = false;
				}
	
				if ( document.forms[0].confirmAssociationEnabled.value == 'false' )
				{
					insertButtonControl.disabled = true;
					listBrokerButtonControl.disabled = true;
				}
			}
		};

		function validateCheckBoxes()
		{
			var addBrokerButtonControl = document.getElementById( 'addBrokerBtn' );		
			var checkBoxes = document.getElementById( 'selectedItemsInBrokerGrid' );		
			var brokerListSize = document.forms[0].brokerListSize.value;

			addBrokerButtonControl.disabled = true;

			for ( var i = 0; i < brokerListSize; i++)
			{
				var checkBoxElement = document.getElementById( 'selectedItemsInBrokerGrid[' + i + ']' );
	
				if( checkBoxElement.checked == true )
				{
					addBrokerButtonControl.disabled = false;
					break;
				}
			}
		};

	</script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="BkrPortfMgmt.BkrPortfMgmtDetail"/>
		<jsp:param name="controlNames" value="'addBrokerBtn'"/>
		<jsp:param name="fieldsWithMask" value="['bkrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
		<jsp:param name="searchInputFields" value="'bkrCnpjNbrSrc'" />
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE> Associação Corretora x Carteira </TITLE>

	</HEAD>

	<body>
		<html:form action="/BkrPortfMgmt.BkrPortfMgmtDetail.Insert.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Relacionamentos" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<html:text property="backURL" value="CustomerPrvt.CustomerPrvtList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="5">Associação Corretora x Carteira</th>
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
								<html:hidden property="hasErrors" />
								<html:hidden property="brokerListSize" />

								<table id="gridTable" border="0">

									<tbody>

										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="3%">&nbsp;</TH>
											<TH class="ODS_header" width="14%">Conta Corrente</TH>
											<TH class="ODS_header" width="17%">Conta Investimento</TH>
											<TH class="ODS_header" width="38%">Nome da Carteira</TH>
											<TH class="ODS_header" width="19%">Mnemônico</TH>
											<TH class="ODS_header" width="9%">Produto</TH>
										</tr>

										<ods:DataSetRows name="BkrPortfMgmtDetailForm" property="portfolioResults"
											dataSetRowName="resultRow" stepIndexName="step" 
											sequenceRestartStep="2" >
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2">
											</logic:equal>

											<bean:define name="resultRow"
												property="bigDecimalByName(PROD_ACCT_CODE)" id="selectedProdAcctCode"
												type="java.math.BigDecimal">
											</bean:define>
	
											<bean:define name="resultRow"
												property="bigDecimalByName(PROD_UNDER_ACCT_CODE)" id="selectedProdUnderAcctCode"
												type="java.math.BigDecimal">
											</bean:define>

											<TD width="3%" align="center">
												<input type="radio" class="radio" name="selection" id="pmaRadioButton"

												<logic:equal name="BkrPortfMgmtDetailForm" property="selectedProdAcctCode" value="<%= selectedProdAcctCode.toString() %>">
													<logic:equal name="BkrPortfMgmtDetailForm" property="selectedProdUnderAcctCode" value="<%= selectedProdUnderAcctCode.toString() %>">
														checked="checked" disabled="true"
													</logic:equal>
												</logic:equal> 
									
												onclick="javascript:selectedProdAcctCode.value='<%=selectedProdAcctCode%>';selectedProdUnderAcctCode.value='<%=selectedProdUnderAcctCode%>';listBkrPortfMgmtGrid(event);"/>
											</td>

											<TD width="14%" align="right"><bean:write name="resultRow"
												property="stringByName(CUR_ACCT_NBR)" /></td>
	
											<TD width="17%" align="right"><bean:write name="resultRow"
												property="stringByName(INVST_CUR_ACCT_NBR)" /></td>
	
											<TD width="38%" align="left"><bean:write name="resultRow"
												property="stringByName(PORTF_MGMT_PROD_NAME)" /></td>
	
											<TD width="19%" align="left"><bean:write name="resultRow"
												property="stringByName(CUST_MNMC_NAME)"  /></td>
	
											<TD width="9%" align="left"><bean:write name="resultRow"
												property="stringByName(PROD_CODE)"  /></td>

										</ods:DataSetRows>
	
									</tbody>
								</table>
							</td>

							</tr>

						</TABLE>
					</td>
				</tr>

			<ods:AccessGrantedTag m_SGFunction="/BkrPortfMgmt.BkrPortfMgmtDetail.Insert.Update">

				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">

							<tr class="ODS_line11" height="25"><td colspan="5">Consulta de Corretora</td></tr>

							<tr height="8">
		 						<td colspan="5" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="250%" height="1"></td>
							</tr>

							<tr class="ODS_Detail_Line1">
								<td colspan="5">										
									<table><tr>
										<TD width="12%">CNPJ da Corretora</TD>
										<TD width="68%"><html:text property="bkrCnpjNbrSrc" styleClass="ODS_Text_Field_Size_15" maxlength="18" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)"></html:text></TD>
									</tr>
									<tr>									
										<TD width="12%">Razão Social</TD>
										<TD width="68%"><html:text property="bkrNameTextSrc" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
									</tr></table>	
								</td>
							</tr>
						</TABLE>
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
											<TD><html:button property="listBrokerBtn" value="Consultar" onclick="submitAction('listBroker');enableAddBrokerButton();"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="submitAction('clearPage');"></html:button></TD>					
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>


				<tr>
					<td>&nbsp;</td>
					<td>
						<table border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
								</tr>
							</thead>
							<tr>
								<td width = "100%">
								<DIV class="ODS_DivGridVerticalScroll">
								<table id="gridTable" width="1200px" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="2%">&nbsp;</TH>
											<TH class="ODS_header" width="9%">CNPJ</TH>
											<TH class="ODS_header" width="38%">Razão Social da Corretora</TH>
											<TH class="ODS_header" width="51%">Endereço da Corretora</TH>
										</tr>

										<tr class="fixed">
										<%int rowIndex = 0;%>
										<logic:notEmpty name="BkrPortfMgmtDetailForm"	property="brokerGrid">
											<logic:iterate name="BkrPortfMgmtDetailForm"
												property="brokerGrid" indexId="index" id="row">
												<%
												BkrPortfMgmtDetailForm bkrPortfMgmtDetailForm = (BkrPortfMgmtDetailForm) session.getAttribute("BkrPortfMgmtDetailForm");
												String[] resultLine = bkrPortfMgmtDetailForm.getBrokerGrid()[rowIndex++];
												%>
												<ods:CountStep counterName="index" counterStartIndex="0"
													sequenceRestartStep="2" stepIndexName="step">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2">
													</logic:equal>
												</ods:CountStep>

												<bean:define name="BkrPortfMgmtDetailForm" property='<%="selectedItemsInBrokerGrid["+index+"]"%>' id="selectedItemsInBrokerGrid" type="java.lang.String" />

												<td width="2%" align="center">

													<input type="checkbox" class="checkbox" name=<%="selectedItemsInBrokerGrid["+index+"]"%> value="S" <%=selectedItemsInBrokerGrid.equals("S")?"checked=\"checked\"":""%> onclick="javascript:enableAddBrokerButton();validateCheckBoxes();" />

												</td>
												<td class="alignRight" width="9%"><%=resultLine[BkrPortfMgmtDetailForm.COL_POS_BKR_CNPJ_NBR]%></td>
												<td class="alignLeft" width="38%"><%=resultLine[BkrPortfMgmtDetailForm.COL_POS_BKR_NAME_TEXT]%></td>
												<td class="alignLeft" width="51%"><%=resultLine[BkrPortfMgmtDetailForm.COL_POS_BKR_ADDR_TEXT]%></td>
											</logic:iterate>
										</logic:notEmpty>
										</tr>
									</tbody>
								</table>
							</DIV>
							</tr>
						</TABLE>
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
											<TD><html:button property="addBrokerBtn" value="Inserir Corretoras" onclick="submitAction('insertDomain');" disabled="true"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

			</ods:AccessGrantedTag>

				<tr>
					<td>&nbsp;</td>
					<td>
						<table border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Corretoras Associadas a Carteira Administrada</th>
								</tr>
							</thead>
							<tr>
								<td width="100%">
								<DIV class="ODS_DivGridVerticalScroll">
								<table id="gridTable" width="1400px" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="4%">Excluir</TH>
											<TH class="ODS_header" width="5%">Atualizar</TH>
											<TH class="ODS_header" width="8%">CNPJ</TH>
											<TH class="ODS_header" width="35%">Razão Social da Corretora</TH>
											<TH class="ODS_header" width="12%">C/C Bovespa</TH>
											<TH class="ODS_header" width="12%">CCI Bovespa</TH>
											<TH class="ODS_header" width="12%">C/C BMF</TH>
											<TH class="ODS_header" width="12%">CCI BMF</TH>
										</tr>

										<logic:equal name="BkrPortfMgmtDetailForm" property="confirmAssociationEnabled" value="true">
										<tr class="fixed">
										<%int rowIndexBkrPortfMgmt = 0;%>
										<logic:notEmpty name="BkrPortfMgmtDetailForm"	property="bkrCnpjNbrInBkrPortfMgmtGrid">
											<logic:iterate name="BkrPortfMgmtDetailForm"
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

												<bean:define name="BkrPortfMgmtDetailForm" property='<%="updateSelectedItemsInBkrPortfMgmtGrid["+index+"]"%>' id="updateSelectedItemsInBkrPortfMgmtGrid" type="java.lang.String" />
												<bean:define name="BkrPortfMgmtDetailForm" property='<%="deleteSelectedItemsInBkrPortfMgmtGrid["+index+"]"%>' id="deleteSelectedItemsInBkrPortfMgmtGrid" type="java.lang.String" />

												<td width="4%" align="center">
													<input type="checkbox" id='<%="deleteCheckBox["+index+"]"%>' class="checkbox" name=<%="deleteSelectedItemsInBkrPortfMgmtGrid["+index+"]"%> value="S" <%=deleteSelectedItemsInBkrPortfMgmtGrid.equals("S")?"checked=\"checked\"":""%> onclick="switchCheckBoxState(this, '<%=index%>');"/> 													
												</td>

												<td width="5%" align="center">
													<input type="checkbox" id='<%="updateCheckBox["+index+"]"%>' class="checkbox" name=<%="updateSelectedItemsInBkrPortfMgmtGrid["+index+"]"%> value="S" <%=updateSelectedItemsInBkrPortfMgmtGrid.equals("S")?"checked=\"checked\"":""%> onclick="switchCheckBoxState(this, '<%=index%>');"/> 
												</td>

												<td class="alignRight" width="8%"><bean:write name="BkrPortfMgmtDetailForm" property='<%="bkrCnpjNbrInBkrPortfMgmtGrid["+index+"]"%>'></bean:write></td>
												<td class="alignLeft" width="35%"><bean:write name="BkrPortfMgmtDetailForm" property='<%="bkrNameTextInBkrPortfMgmtGrid["+index+"]"%>'></bean:write></td>
												<td width="12%" align="center"><html:text property='<%="bovespaCurAcctNbrInBkrPortfMgmtGrid["+index+"]"%>' styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text>
												<td width="12%" align="center"><html:text property='<%="bovespaInvstAcctNbrInBkrPortfMgmtGrid["+index+"]"%>' styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text></td>
												<td width="12%" align="center"><html:text property='<%="bmfCurAcctNbrInBkrPortfMgmtGrid["+index+"]"%>' styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text></td>
												<td width="12%" align="center"><html:text property='<%="bmfInvstAcctNbrInBkrPortfMgmtGrid["+index+"]"%>' styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text></td>

											</logic:iterate>
										</logic:notEmpty>
										</tr>
										</logic:equal> 
									</tbody>
								</table>
							</DIV>
							</tr>
						</TABLE>
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
												<ods:AccessGrantedTag m_SGFunction="/BkrPortfMgmt.BkrPortfMgmtDetail.Insert.Update">
													<TD><html:button property="insertBtn" value="Confirmar Associações" onclick="submitAction('updateBkrPortfMgmt');" disabled="true"></html:button></TD>
												</ods:AccessGrantedTag>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
			</table>	
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>		
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
	<script>enableInsertButton();</script>
</html:html>