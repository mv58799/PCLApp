<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page
	import="com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtDetailForm"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK href="<%= request.getContextPath() %>/Common/css/citi.css"
	rel="stylesheet" type="text/css">
<script language="javascript">

        function extraActions(action) {

            if (action == 'insertDocTransfer') {
                document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Update.InsertDocTransfer";
                document.forms[0].backURL.disabled = true;
            }
            else if (action == 'insertCallback') {
                document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Update.InsertCallback";
                document.forms[0].backURL.disabled = true;
            }
            else if (action == 'deleteDocTransfer') {
                document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Update.DeleteDocTransfer";
                document.forms[0].backURL.disabled = true;
            }
            else if (action == 'deleteCallback') {
                document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Update.DeleteCallback";
                document.forms[0].backURL.disabled = true;
            }
			else if (action =='Contract.CurAccountList')
			{
				document.forms[0].action = "Contract.CurAccountList.List.Show";
				document.forms[0].backURL.disabled = false; 
			}
			else if (action =='ContactCust.ContactCustList')
			{
				document.forms[0].action = "ContactCust.ContactCustList.List.Show";
				document.forms[0].backURL.disabled = false; 
			}
        }
	function verifyCallBackSelected()
	{
		var isValidSize = false;

		var controlsCallBack = document.getElementsByName('selectedCallBackInGrid');

		for (i = 0; i < controlsCallBack.length; i++)
		{
			if (controlsCallBack != null)
			{
				if (controlsCallBack[i].checked == true)
				{
					isValidSize = true;
					break;
				}
			}
		}
	
		return isValidSize;
	}

	function verifyDocTransferSelected()
	{
		var isValidSize = false;
		var controlsDocTransfer = document.getElementsByName('selectedDocTransferInGrid');

		for (j = 0; j < controlsDocTransfer.length; j++)
		{
			if (controlsDocTransfer != null)
			{
				if (controlsDocTransfer[j].checked == true)
				{
					isValidSize = true;
					break;
				}
			}
		}
		return isValidSize;
	}

	//Adiciona validação na tela caso da lista de associações não ter ítens selecionados.
	function showErrorMessage(){
		if (!verifyCallBackSelected() ||!verifyDocTransferSelected() ){
			validationMessageArea.innerHTML = "";
			validationMessageArea.innerHTML += 'Erro: O registro não pode ser alterado pois são obrigatórias uma ou mais associações com "Instrução Permanente".<br>';
			validationMessageArea.style.display = "inline";
			tableError.style.display = "inline"
		}
		else {
			submitAction('update');
			
		}
	}



    </script>
	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="IpDocPrvt.IpDocPrvtDetail" />
	</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
<TITLE>Alteração de Instrução Permanente</TITLE>
</HEAD>

<body>
<html:form action="/IpDocPrvt.IpDocPrvtDetail.Update.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:text property="backURL"
		value="IpDocPrvt.IpDocPrvtDetail.Update.Show" style="display:none"></html:text>

	<html:hidden property="selectedDocTransferCode" />
	<html:hidden property="selectedCtcNbr" />
	<html:hidden property="actionPerformed" />

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Alteração de
						Instrução Permanente</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="25%">Número do Cliente</TD>
								<TD colspan="2">Nome do Cliente</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="25%"><html:text property="custNbrSrc" disabled="true" styleClass="ODS_Text_Field_Size_15"></html:text></td>
								<html:hidden property="custNbrSrc"></html:hidden>
								<TD colspan="2"><html:text property="custFullNameText" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="25%">Conta Corrente</TD>
								<TD colspan="2">Conta CCI</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="25%"><html:text property="custCurAcctNbrSrc" styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text>
								</TD>
								<td colspan="2"><html:text property="invstCurAcctNbrSrc" styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text>
								</td>
							</tr>							
							<tr class="ODS_Detail_line1">
								<td colspan="3">Código</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<td colspan="3"><html:text property="ipDocCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></td>
							</tr>
						</tbody>
					</table>
					<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Dados de Transferência</th></tr></thead>
					<tr>
						<td>
						<table class="ODS_internalWidth" border="0" cellspacing="0">
							<tbody>
								<tr class="ODS_Detail_Line2">
									<td colspan="6" rowspan="2">
									<table>
										<tr>
										<td width="13%">Banco *</td>
										<td width="13%">Agência *</td>
										<td width="17%">Conta Corrente *</td>
										<td width="17%">CPF/CNPJ Benefici&aacute;rio *</td>
										<td width="21%">Nome do Benefici&aacute;rio *</td>
										<td width="17%"></td>
									</tr>
									<tr>
										<td width="13%"><html:text styleClass="ODS_Text_Field_Size_5"
											property="agnBankCode"  maxlength="3" onkeyup="MaskFieldPress('CHAR','NNN','left',null)"></html:text></TD>
										<td width="13%"><html:text styleClass="ODS_Text_Field_Size_5"
											property="brchCode"  maxlength="4" onkeyup="MaskFieldPress('CHAR','NNNN','left',null)"></html:text></TD>
										 <td width="17%"><html:text styleClass="ODS_Text_Field_Size_15" 
										 	property="curAcctNbr" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text></td>
										<td width="17%"><html:text styleClass="ODS_Text_Field_Size_15"
											property="beneCpfCnpjNbr" maxlength="14"
											onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text></td>
										<td width="21%"><html:text property="beneNameText"
											styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></td>
										<td width="17%"></TD>
									</tr>
								</table>
								</td>
							</tr>
						</tbody>
					</table>
					</td>
				</tr>
<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_Line2">
								<td colspan="6" rowspan="2">
								<table width="685">
									<tr class="ODS_Detail_line1">
										<td width="10%">Tipo de Conta *</TD>
										<td width="60%">Conta Destino de Mesma Titularidade *</td>										
									</tr>
									<tr class="ODS_Detail_line2">
										<td width="10%"><html:select property="beneAcctTypeCode"
											styleClass="ODS_Select_Field_Size_7">
											<html:option value=""></html:option>
											<html:options
												property="beneAcctTypeCodeIndDomain.columnValuesByName(ACCT_TYPE_CODE)"
												labelProperty="beneAcctTypeCodeIndDomain.columnValuesByName(ACCT_TYPE_TEXT)" />
										</html:select></td>
										<td width="60%"><html:select property="beneMainDestAcctInd"
											styleClass="ODS_Select_Field_Size_5">
											<html:option value=""></html:option>
											<html:options
												property="beneMainDestAcctIndDomain.columnValuesByName(INDICATOR_CODE)"
												labelProperty="beneMainDestAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
										</html:select></td>									
										
								</table>
								</td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>&nbsp;</td>
							</tr>
							<tr class="ODS_Detail_Line1" align="right">
								<td colspan="5"><html:button property="insertDocTransferBtn"
									value="Inserir Transferência"
									onclick="javascript:document.forms[0].actionPerformed.value='insertDocTransfer';submitAction('insertDocTransfer');"></html:button></td>
							 </tr>
													
						</tbody>						
					</table>
				  </td>
				</tr>				
				<tr>
					<td>
					<table id="gridTable" class="ODS_internalWidth" border="0">
						<tbody>
								<TH class="ODS_header" width="7%">Inserir</TH>
								<TH class="ODS_header" width="7%">Excluir</TH>
								<TH class="ODS_header" width="5%">Banco</TH>
								<TH class="ODS_header" width="5%">Agência</TH>
								<TH class="ODS_header" width="10%">Conta Corrente</TH>
								<TH class="ODS_header" width="10%">CPF/CNPJ Benef.</TH>
								<TH class="ODS_header" width="30%">Nome Benef.</TH>
								<%
    int auxIndex = 0;
    %>

								<logic:iterate name="IpDocPrvtDetailForm"
									property="domainsAgnBankName" indexId="index" id="baseArray">

									<%IpDocPrvtDetailForm ipDocPrvtDetailForm = ( IpDocPrvtDetailForm ) session.getAttribute( "IpDocPrvtDetailForm" );
    String[] arrayDocTransferCode = ipDocPrvtDetailForm.getDomainsDocTransferCode();
    String indexDomainsDocTransferCode = arrayDocTransferCode[ auxIndex++ ];
    %>

									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsAgnBankName[" + index + "]"%>'
										id="domainsAgnBankName" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsAgnBankCode[" + index + "]"%>'
										id="domainsAgnBankCode" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBrchName[" + index + "]"%>'
										id="domainsBrchName" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBrchCode[" + index + "]"%>'
										id="domainsBrchCode" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsCurAcctCode[" + index + "]"%>'
										id="domainsCurAcctCode" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsTxnTypeCode[" + index + "]"%>'
										id="domainsTxnTypeCode" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsOwnDestAcctInd[" + index + "]"%>'
										id="domainsOwnDestAcctInd" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBeneCpfCnpjNbr[" + index + "]"%>'
										id="domainsBeneCpfCnpjNbr" type="java.lang.String" />

									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBeneNameText[" + index + "]"%>'
										id="domainsBeneNameText" type="java.lang.String" />

									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBeneMainDestAcctInd[" + index + "]"%>'
										id="domainsBeneMainDestAcctInd" type="java.lang.String" />

									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBeneAcctTypeCode[" + index + "]"%>'
										id="domainsBeneAcctTypeCode" type="java.lang.String" />
										

									<ods:CountStep counterName="index" counterStartIndex="0"
										sequenceRestartStep="2" stepIndexName="step">
										<logic:equal name="step" value="0">
											<tr class="ODS_line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_line2">
										</logic:equal>
									</ods:CountStep>
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="selectedDocTransferInGrid[" + index + "]"%>'
										id="selectedDocTransferInGrid" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="deletedDocTransfer[" + index + "]"%>'
										id="deletedDocTransfer" type="java.lang.String" />
									<td width="7%" align="center"><input type="checkbox"
										name=<%="selectedDocTransferInGrid[" + index + "]"%> value="S"
										<%=selectedDocTransferInGrid.equals("S") ? "checked=\"checked\"" : ""%>
										class="checkbox" disabled="disabled"> <input type="checkbox"
										name=<%="selectedDocTransferInGrid[" + index + "]"%> value="S"
										<%=selectedDocTransferInGrid.equals("S") ? "checked=\"checked\"" : ""%>
										class="checkbox" style="display: none"></td>
									<td width="7%" align="center"><input type="checkbox"
										name=<%="deletedDocTransfer[" + index + "]"%> value="S"
										<%=deletedDocTransfer.equals("S") ? "checked=\"checked\"" : ""%>
										class="checkbox" /></td>
									<td width="5%"  class="centralized"><%=domainsAgnBankCode%></td>
									<td width="5%"  class="centralized"><%=domainsBrchCode%></td>
									<td width="15%" class="centralized"><%=domainsCurAcctCode%></td>
									<td width="15%" class="centralized"><%=domainsBeneCpfCnpjNbr%></td>
									<td width="20%" align="left"><%=domainsBeneNameText%></td>
								</logic:iterate>
							</TR>
						</TBODY>
					</TABLE>
					</td>

				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Dados de
						Callback</th>
					</tr>
				</thead>
											
							<tr class="ODS_Detail_Line1">
								<td width="5%">DDD *</TD>
								<td width="10%">Telefone *</TD>
								<td width="85%">Ramal</TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td width="5%"><html:text property="phoneDddCode" maxlength="4"
									styleClass="ODS_Text_Field_Size_5"
									onkeyup="MaskFieldPress('CHAR','NNNN','left',null)"></html:text></td>
								<td width="10%"><html:text property="phoneNbr" maxlength="10"
									styleClass="ODS_Text_Field_Size_10"
									onkeyup="MaskFieldPress('CHAR','NNNNNNNNNN','left',null)"></html:text></td>
								<td width="85%"><html:text property="phoneExtnNbr" maxlength="5"
									styleClass="ODS_Text_Field_Size_5"
									onkeyup="MaskFieldPress('CHAR','NNNNN','left',null)"></html:text></td>

							</tr>
						</tbody>
					</table>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>	

							<tr class="ODS_Detail_Line1">
								<td colspan="4">Nome do Contato Principal *</td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td colspan="4"><html:text property="fullNameText"
									styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td colspan="4">Nome do Segundo Contato</td>
							</tr>

							<tr class="ODS_Detail_Line2">
								<td colspan="4"><html:text property="fullNameText_2"
									styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td colspan="4">Nome do Terceiro Contato</td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td colspan="4"><html:text property="fullNameText_3"
									styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line3">
								<TD width="53%"></TD>
								<TD><html:button
									property="insertCallbackBtn" value="Inserir Contato"
									onclick="javascript:document.forms[0].actionPerformed.value='insertCallback';submitAction('insertCallback', true);">
								</html:button></TD>
							</tr>
						</tbody>
					</table>
				  </td>
				</tr>
				<tr>
					<td>
					<table id="gridTable" class="ODS_internalWidth" border="0">
						<tbody>
							<tr id="gridHeader" class="fixed">
								<TH class="ODS_header" width="2%">Inserir</TH>
								<TH class="ODS_header" width="2%">Excluir</TH>
								<TH class="ODS_header" width="2%">DDD</TH>
								<TH class="ODS_header" width="10%">Telefone</TH>
								<TH class="ODS_header" width="2%">Ramal</TH>
								<TH class="ODS_header" width="25%">Nome do Contato Principal</TH>
								<TH class="ODS_header" width="25%">Nome do Segundo Contato</TH>
								<TH class="ODS_header" width="25%">Nome do Terceiro Contato</TH>							
							</tr>
							<%int auxIndex2 = 0;%>
							<logic:iterate name="IpDocPrvtDetailForm"
								property="domainsFullNameText" indexId="index2" id="baseArray2">
								<%IpDocPrvtDetailForm ipDocPrvtForm =
	                              (IpDocPrvtDetailForm) session.getAttribute("IpDocPrvtDetailForm");
                                    String[] arrayCtcNbr = ipDocPrvtForm.getDomainsCtcNbr();
                                    String[] arrayPhone = ipDocPrvtForm.getDomainsPhone();
								    String[] arrayDddPhone = ipDocPrvtForm.getDomainsDDDPhone();
					                String[] arrayRamalPhone = ipDocPrvtForm.getDomainsRamalPhone();
					                String[] arrayFullNameText_2 = ipDocPrvtForm.getDomainsFullNameText_2(); 
					                String[] arrayFullNameText_3 = ipDocPrvtForm.getDomainsFullNameText_3(); 
								    String indexCtcNbrArray = arrayCtcNbr[auxIndex2];
								    String indexPhone = arrayPhone[auxIndex2];
								    String dddPhone = arrayDddPhone[auxIndex2];
								    String ramalPhone = arrayRamalPhone[auxIndex2];
								    String fullNameText_2 = arrayFullNameText_2[auxIndex2];
								    String fullNameText_3 = arrayFullNameText_3[auxIndex2];
								    auxIndex2++;%>
								<bean:define name="IpDocPrvtDetailForm"
									property='<%="domainsFullNameText[" + index2 + "]"%>'
									id="domainsFullNameText" type="java.lang.String" />
								
								<ods:CountStep counterName="index2" counterStartIndex="0"
									sequenceRestartStep="2" stepIndexName="step">
									<logic:equal name="step" value="0">
										<tr class="ODS_line1">
									</logic:equal>
									<logic:equal name="step" value="1">
										<tr class="ODS_line2">
									</logic:equal>
								</ods:CountStep>
								<bean:define name="IpDocPrvtDetailForm"
									property='<%="selectedCallBackInGrid[" + index2 + "]"%>'
									id="selectedCallBackInGrid" type="java.lang.String" />
								<bean:define name="IpDocPrvtDetailForm"
									property='<%="deletedCallBack[" + index2 + "]"%>'
									id="deletedCallBack" type="java.lang.String" />
								<td width="2%" align="center"><input type="checkbox"
									name=<%="selectedCallBackInGrid[" + index2 + "]"%> value="S"
									<%=selectedCallBackInGrid.equals("S") ? "checked=\"checked\"" : ""%>
									class="checkbox" disabled="disabled"> <input type="checkbox"
									name=<%="selectedCallBackInGrid[" + index2 + "]"%> value="S"
									<%=selectedCallBackInGrid.equals("S") ? "checked=\"checked\"" : ""%>
									class="checkbox" style="display: none"></td>
								<td width="2%" align="center"><input type="checkbox"
									name=<%="deletedCallBack[" + index2 + "]"%> value="S"
									<%=deletedCallBack.equals("S") ? "checked=\"checked\"" : ""%>
									class="checkbox" /></td>

								<td width="2%"  class="centralized"><%=dddPhone%></td>
								<td width="10%" class="centralized"><%=indexPhone%></td>
								<td width="2%"  class="centralized"><%=ramalPhone%></td>
								<td width="25%" align="left"><%=domainsFullNameText%></td>
								<td width="25%" align="left"><%=fullNameText_2%></td>
								<td width="25%" align="left"><%=fullNameText_3%></td>
							</logic:iterate>
						</tbody>
					</TABLE>
					</td>
				<tr>

				<tr>
					<td>
					<table class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<TD width="100%"></TD>
								<TD><html:button property="updateBtn"
									value="Confirmar Alteração"
									onclick="javascript:document.forms[0].actionPerformed.value='updateIpDocPrvt';submitAction('update');"></html:button></TD>
								<TD><html:button property="clearBtn" value="Limpar"
									onclick="clearPage();"></html:button></TD>
								<TD><html:button property="backBtn" value="Voltar"
									onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<jsp:include page="/View/Util/Footer.jsp" flush="true" />
	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>

