<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrMovementDetailForm"%>

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
					document.forms[0].action = "CurAcctPrmntInstr.CurAcctPrmntInstrMovementDetail.Update.InsertDomain"; 
					document.forms[0].backURL.disabled = true; 
				}
				else if ( action == 'deleteDomain' ) {
					document.forms[0].action = "CurAcctPrmntInstr.CurAcctPrmntInstrMovementDetail.Update.DeleteDomain"; 
					document.forms[0].backURL.disabled = true; 
				}
				else if ( action == 'IpDocPrvt.IpDocPrvtList' ) {
					document.forms[0].action = "IpDocPrvt.IpDocPrvtList.List.Show"; 
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
				}
			}

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
					return isValidSize;
				}
			}
		}
		return isValidSize;
	}

	//Adiciona validação na tela caso da lista de associações não ter ítens selecionados.
	function showErrorMessage(){
		if (!verifyItemsSelected()){
			validationMessageArea.innerHTML = "";
			validationMessageArea.innerHTML += 'Erro: O registro não pode ser alterado pois são obrigatórias uma ou mais associações com "Conta Corrente".<br>';
			validationMessageArea.style.display = "inline";
			tableError.style.display = "inline"
		}
		else {
			submitAction('update');
		}
	}

		 </script>		
	
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="CurAcctPrmntInstr.CurAcctPrmntInstrMovementDetail" />
			<jsp:param name="controlNames" value="'deleteIpBtn'" />

		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Alteração de Associação Conta Corrente x Instrução Permanente com Pendência de Aprovação</TITLE>

	</HEAD>

	<body>
		<html:form action="/CurAcctPrmntInstr.CurAcctPrmntInstrMovementDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
		    </jsp:include>

			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>			
		
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<html:text property="backURL" value="CurAcctPrmntInstr.CurAcctPrmntInstrMovementDetail.Update.Show" style="display:none"></html:text>
					<td>
						<table class="ODS_internalWidth" border="0">
									<thead><tr><th class="subtitle" >Alteração de Associação Conta Corrente X Instrução Permanente com Pendência de Aprovação</th></tr></thead>
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
														<td width="75%" colspan="2">
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
														<td width="25%"><html:text	styleClass="ODS_Text_Field_Size_10" property="prmntInstrCodeSrc" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></td>
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
											<TD width="80%">&nbsp;</TD>
												<TD align="right"><html:button property="insertIpBtn" value="Inserir IP" onclick="submitAction('insertDomain');"></html:button></TD>
										<!-- 	<TD><html:button property="deleteIpBtn" value="Excluir IP" onclick="submitAction('deleteDomain');" disabled="true"></html:button></TD> -->
										</TR>
									</tbody>
								</TABLE>

									<html:hidden property="selectedIpGrid" value="" />
									<html:hidden property="selectedIpDescGrid" value="" />
									<html:hidden property="selectedIpIndGrid" value="" />
									<html:hidden property="selectedInvstCurAcctGrid" value="" />

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
																<TH class="ODS_header" width="10%">Ação</TH>
															</tr>
															<%int rowIndex = 0;%>
															<logic:notEmpty name="CurAcctPrmntInstrMovementDetailForm"	property="ipDocDomains">
																<logic:iterate name="CurAcctPrmntInstrMovementDetailForm" 	property="ipDocDomains" indexId="index" id="row">
																	<%
																		CurAcctPrmntInstrMovementDetailForm curAcctPrmntInstrMovementDetailForm = (CurAcctPrmntInstrMovementDetailForm) session.getAttribute("CurAcctPrmntInstrMovementDetailForm");
																		String[] resultLine = curAcctPrmntInstrMovementDetailForm.getIpDocDomains()[rowIndex++];
																	%>
																	<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
																		<logic:equal name="step" value="0">
																			<tr class="ODS_line1">
																		</logic:equal>
																		<logic:equal name="step" value="1">
																			<tr class="ODS_line2">
																		</logic:equal>
																	</ods:CountStep>
								
																	<bean:define name="CurAcctPrmntInstrMovementDetailForm" property='<%="selectedItemsInGrid["+index+"]"%>' id="selectedItemsInGrid" type="java.lang.String" />
																	<bean:define name="CurAcctPrmntInstrMovementDetailForm" property='<%="deletedItems["+index+"]"%>' id="deletedItems" type="java.lang.String" />
																	<td width="7%" align="center"><input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" disabled="disabled">
																		<input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" style="display:none"></td>									
																	<td width="7%"  align="center"><input type="checkbox"	name=<%="deletedItems["+index+"]"%> value="S" <%=deletedItems.equals("S")?"checked=\"checked\"":""%> class="checkbox" /></td>									
																	<td class="alignLeft"><%=resultLine[0]%></td>
																	<td class="centralized"><%=resultLine[2]%></td>
																	<td class="centralized"><%=resultLine[3]%></td>
																	<td class="centralized"><%=resultLine[4]%></td>
																</logic:iterate>
															</logic:notEmpty>
														</tbody>
													</TABLE>
												</td>
											</tr>
										</TABLE>
									</div>
				
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<TR>
												<TD width="80%">&nbsp;</TD>
												<TD><html:button property="updateBtn" value="Confirmar Alteração" onclick="submitAction('update');"></html:button></TD>
												<TD><html:button property="clearBtn" value="Limpar" onclick="clearAllPage();"></html:button></TD>
												<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>	
											</TR>
										</tbody>
									</TABLE>
								</td>
							</tr>
							<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
						</table>	
					</td>
				</tr>
			</table>	
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
