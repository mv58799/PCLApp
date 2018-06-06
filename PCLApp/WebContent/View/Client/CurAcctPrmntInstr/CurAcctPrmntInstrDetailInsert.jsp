<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
	
		<script language="javascript">
	
			function extraActions( action )
			{
				if ( action == 'insertDomain' ) {
					document.forms[0].action = "CurAcctPrmntInstr.CurAcctPrmntInstrDetail.Insert.InsertDomain"; 
					document.forms[0].backURL.disabled = true; 
				}
				else if ( action == 'deleteDomain' ) {
					document.forms[0].action = "CurAcctPrmntInstr.CurAcctPrmntInstrDetail.Insert.DeleteDomain"; 
					document.forms[0].backURL.disabled = true; 
				}
				else if ( action == 'IpDocPrvt.IpDocPrvtList' ) {
					document.forms[0].action = "IpDocPrvt.IpDocPrvtList.List.Execute"; 
					document.forms[0].backURL.disabled = false; 
					document.forms[0].fromCurAcct.value = "true";
				}
				else if ( action == 'detailIpDoc' ) {
					document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Consult.Show"; 
					document.forms[0].backURL.disabled = false; 
				}
			};
	
			function setSelectedKeys(ipCode, ipDesc, ipInd, invstCurAcct){
				document.forms[0].selectedIpGrid.value = ipCode;
				document.forms[0].selectedIpDescGrid.value = ipDesc;
				document.forms[0].selectedIpIndGrid.value = ipInd;
				document.forms[0].selectedInvstCurAcctGrid.value = invstCurAcct;
			};
	
			function setIpCodeValue(){
				if (document.forms[0].prmntInstrCodeSrc.value != ''){
					document.forms[0].ipDocCodeSrc.value = document.forms[0].prmntInstrCodeSrc.value;
					alert(document.forms[0].ipDocCodeSrc.value)
				}
			};

			function clearFields(){
				document.forms[0].prmntInstrCodeSrc.value = "";
				document.forms[0].prmntInstrInvstCurAcctInd.value = "";
				document.forms[0].invstAcctCurNbr.value = "";
			};


	//Verifica se tem ítens selecionados no grid e retorna true ou false.
	function verifyItemsSelected()
	{
		var isValidSize = false;

		var controls = document.getElementsByName('selectedItemsInGrid');

		for (i = 0; i < controls.length; i++)
		{
			if (controls != null)
			{
				if (controls[i].checked == true)
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
		if (!verifyItemsSelected()){
			validationMessageArea.innerHTML = "";
			validationMessageArea.innerHTML += 'Erro: O registro não pode ser associado pois são obrigatórias uma ou mais associações com "Conta Corrente".<br>';
			validationMessageArea.style.display = "inline";
			tableError.style.display = "inline"
		}
		else {
			submitAction('insert');
		}
	}
		 </script>		
	
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="CurAcctPrmntInstr.CurAcctPrmntInstrDetail" />
			<jsp:param name="controlNames" value="'deleteIpBtn'" />
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Associação Conta Corrente X Instrução Permanente</TITLE>

	</HEAD>

	<body>
		<html:form action="/CurAcctPrmntInstr.CurAcctPrmntInstrDetail.Insert.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes"/>
		    </jsp:include>

			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>	

			<html:hidden property="selectedIpGrid" value="" />
			<html:hidden property="selectedIpDescGrid" value="" />
			<html:hidden property="selectedIpIndGrid" value="" />
			<html:hidden property="selectedInvstCurAcctGrid" value="" />
			<html:hidden property="selectedIpDocCode" value="" />
			<html:hidden property="fromCurAcct"/>
		
		
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<html:text property="backURL" value="CurAcctPrmntInstr.CurAcctPrmntInstrDetail.Insert.Show" style="display:none"></html:text>
					<td>
						<table class="ODS_internalWidth" border="0">
									<thead><tr><th class="subtitle">Associação Conta Corrente X Instrução Permanente</th></tr></thead>
							<tr>
								<td>
									<table class="ODS_internalWidth"   border="0" cellspacing="0">
										<tbody>
											<tr class="ODS_line11" height="25"><td colspan="3">Conta Corrente:</td></tr>
											<tr>
						 						<td colspan="3"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td>
											</tr>
											<TR>
												<TD>
													<TABLE>
														<tr class="ODS_Detail_Line1">
															<TD width="25%">Conta Corrente</TD>
															<TD width="19%">Número do Cliente</TD>
															<TD width="52%">Nome do Cliente</TD>
														</tr>
														<tr class="ODS_Detail_Line2">
															<TD width="25%"><html:text styleClass="ODS_Text_Field_Size_15" property="curAcctNbr" disabled="true" maxlength="15"></html:text></TD>
															<TD width="19%"><html:text styleClass="ODS_Text_Field_Size_15" property="custNbrSrc" disabled="true" maxlength="11"></html:text></TD>
															<html:hidden property="custNbrSrc"/>
															<TD  width="52%"><html:text styleClass="ODS_Text_Field_Size_60" property="custFullName" disabled="true" maxlength="60"></html:text></TD>
														</tr>
													</TABLE>
												</TD>
											</TR>
			
											<tr class="ODS_line11" height="25"><td colspan="3">Instrução Permanente:</td></tr>
											<tr>
						 						<td colspan="3"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' height="1"  width="100%"></td>
											</tr>
											
											<tr class="ODS_Detail_Line1">
											<td>
												<table>
													<tr class="ODS_Detail_Line1">
														<td width="25%">Código </td>
														<td width="75%"  colspan="2">
															<table>
																<tr>
																	<td width="40%">Ind.Conta CCI</td>
																	<td width="10%">&nbsp;</td>
																	<td width="50%">Conta Investimento</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr class="ODS_Detail_Line1">
														<td width="25%"><html:text	styleClass="ODS_Text_Field_Size_10" property="prmntInstrCodeSrc" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)" disabled="true"></html:text></td>
														<html:hidden property="ipDocCodeSrc"/>
														<td width="75%" colspan="2">
															<table>
																<tr>
																	<td width="40%"><html:select property="prmntInstrInvstCurAcctInd" styleClass="ODS_Select_Field_Size_5"  disabled="true">
																		<html:option value=""></html:option>
																		<html:options property="prmntInstrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="prmntInstrInvstCurAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
																	</html:select></td>
																	<td width="10%">&nbsp;</td>
																	<td width="50%"><html:text styleClass="ODS_Text_Field_Size_15" property="invstAcctCurNbr" disabled="true" maxlength="40"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar IP" onclick="javascript:setIpCodeValue(); submitAction('PreparedSearch.IpDocPrvt.IpDocPrvtList');"></html:button></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
						
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="insertIpBtn" value="Inserir IP" onclick="submitAction('insertDomain');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>

									<div class="ODS_DivGrid">
										<table class="ODS_internalWidth" border="0">
											<thead>
												<tr><th class="subtitle" scope="colgroup" colspan="3">Lista de IP's</th></tr>
											</thead>
											<tr>
												<td>
													<table class="ODS_internalWidth" border="0">
														<tbody>
															<tr class="fixed">
																<TH class="ODS_header" width="7%">Inserir</TH>
																<TH class="ODS_header" width="7%">Excluir</TH>
																<TH class="ODS_header" width="14%">Código</TH>
																<TH class="ODS_header" width="12%">Indicador CCI</TH>
																<TH class="ODS_header" width="21%">Número Conta CCI</TH>
															</tr>
															<%int rowIndex = 0;%>
															<logic:notEmpty name="CurAcctPrmntInstrDetailForm"	property="ipDocDomains">
																<logic:iterate name="CurAcctPrmntInstrDetailForm" 	property="ipDocDomains" indexId="index" id="row">
																	<%
																		CurAcctPrmntInstrDetailForm curAcctDetailForm = (CurAcctPrmntInstrDetailForm) session.getAttribute("CurAcctPrmntInstrDetailForm");
																		String[] resultLine = curAcctDetailForm.getIpDocDomains()[rowIndex++];
																	%>
																	<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
																		<logic:equal name="step" value="0">
																			<tr class="ODS_line1">
																		</logic:equal>
																		<logic:equal name="step" value="1">
																			<tr class="ODS_line2">
																		</logic:equal>
																	</ods:CountStep>
								
																	<bean:define name="CurAcctPrmntInstrDetailForm" property='<%="selectedItemsInGrid["+index+"]"%>' id="selectedItemsInGrid" type="java.lang.String" />
																	<bean:define name="CurAcctPrmntInstrDetailForm" property='<%="deletedItems["+index+"]"%>' id="deletedItems" type="java.lang.String" />
																	<td width="7%" align="center"><input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" disabled="disabled">
																		<input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" style="display:none"></td>									
																	<td width="7%" align="center"><input type="checkbox"	name=<%="deletedItems["+index+"]"%> value="S" <%=deletedItems.equals("S")?"checked=\"checked\"":""%> class="checkbox" /></td>									
																	<td class="alignLeft"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedIpDocCode.value='<%=resultLine[0]%>';submitAction('detailIpDoc');"><%=resultLine[0]%></a></td>
																	<td class="centralized"><%=resultLine[2]%></td>
																	<td class="centralized"><%=resultLine[3]%></td>
																</logic:iterate>
															</logic:notEmpty>
														</tbody>
													</table>
												</td>
											</tr>
										</TABLE>
									</DIV>
								
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<TR>
												<TD width="80%">&nbsp;</TD>
												<TD><html:button property="insertBtn" value="Confirmar Associação" onclick="submitAction('insert');"></html:button></TD>
												<TD><html:button property="clearBtn" value="Limpar" onclick="clearFields();"></html:button></TD>
												<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>	
											</TR>
										</tbody>
									</TABLE>
								</td>
							</tr>
						</table>	
					<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
				</table>	
			</html:form>
		</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
