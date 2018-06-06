<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page
	import="com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtMovDetailForm"%>


<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Common/css/citi.css">
<script language="javascript">
	function extraActions(action){

	    if ( action == 'insertDomain' ) {
			document.forms[0].action = "MrDocPrvt.MrDocPrvtMovDetail.Update.InsertDomain"; 
			document.forms[0].backURL.disabled = true; 
		}
	    else if ( action == 'deleteDomain' ) {
			document.forms[0].action = "MrDocPrvt.MrDocPrvtMovDetail.Update.DeleteDomain"; 
			document.forms[0].backURL.disabled = true; 
		}
	    else if ( action == 'ContactCust.ContactCustList' ) {
			document.forms[0].action = "ContactCust.ContactCustList.List.Show"; 
			document.forms[0].backURL.disabled = false; 
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
			validationMessageArea.innerHTML += 'Erro: O registro não pode ser alterado pois são obrigatórias uma ou mais associações com "Memo de Risco".<br>';
			validationMessageArea.style.display = "inline";
			tableError.style.display = "inline"
		}
		else {
			submitAction('update');
		}
	}																
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="MrDocPrvt.MrDocPrvtMovDetail" />
	<jsp:param name="controlNames" value="'deleteContactBtn'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Alteração de Memo de Risco com Pendência de Aprovação</TITLE>
</HEAD>
<body>

<html:form action="/MrDocPrvt.MrDocPrvtMovDetail.Update.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Aprovação" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:hidden property="associatedCtcNbr" />

	<table class="ODS_mainTable" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<html:text property="backURL"
				value="MrDocPrvt.MrDocPrvtMovDetail.Update.Show"
				style="display:none"></html:text>
			<td width="100%">
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3" height="52">Alteração
						de Memo de Risco com Pendência de Aprovação</th>
					</tr>
				</thead>
				<TBODY>
					<td>&nbsp;</td>
					<TR class="ODS_Detail_Line2">
						<TD width="3%">Conta Corrente</TD>
						<td width="35%">Nome do Cliente</td>
						<td width="35%">CCI</td>
					</TR>
					<TR class="ODS_Detail_Line2">
						<TD width="3%"><html:text property="curAcctNbrSrc"
							styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text>
						</TD>

						<TD width="35%"><html:text property="custFullNameText"
							styleClass="ODS_Text_Field_Size_60" disabled="true"></html:text>
						</TD>
						<td colspan="2"><html:text property="invstCurAcctNbrSrc"
							styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text>
						</td>
					</TR>


				</TBODY>
			</table>

			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Dados de
						Callback</th>
					</tr>
				</thead>
				<tr>
					<td width="100%">
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
							<tr class="ODS_Detail_Line1">
								<td width="5%">DDD *</TD>
								<td width="10%">Telefone *</TD>
								<td width="85%">Ramal</TD>
							</tr>
								
							<tr class="ODS_Detail_Line1">
								<td width="3%"><html:text property="phoneDddCode" maxlength="4"
									styleClass="ODS_Text_Field_Size_5"
									onkeyup="MaskFieldPress('CHAR','NNNN','left',null)"></html:text></td>
								<td width="3%"><html:text property="phoneNbr" maxlength="10"
									styleClass="ODS_Text_Field_Size_10"
									onkeyup="MaskFieldPress('CHAR','NNNNNNNNNN','left',null)"></html:text></td>
								<td width="15%"><html:text property="phoneExtnNbr" maxlength="5"
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
							<tr class="ODS_Detail_Line1">
							<TD width="58%"></TD>
							<TD><html:button property="insertContactBtn"
								value="Inserir Contato" onclick="submitAction('insertDomain');"></html:button></TD>
							</tr>


						</tbody>

					</table>
					<DIV class="ODS_DivGridVerticalScroll">
								<table class="ODS_internalWidth" border="0" id="grid">
									<TBODY>
										<tr class="fixed">
											<TH class="ODS_header" width="7%">Inserir</TH>
											<TH class="ODS_header" width="7%">Excluir</TH>
											<TH class="ODS_header" width="2%">DDD</TH>
											<TH class="ODS_header" width="10%">Telefone</TH>
											<TH class="ODS_header" width="2%">Ramal</TH>
											<TH class="ODS_header" width="25%">Nome do Contato Principal</TH>
											<TH class="ODS_header" width="20%">Nome do Segundo Contato</TH>
											<TH class="ODS_header" width="20%">Nome do Terceiro Contato</TH>
											<TH class="ODS_header" width="10%">Ação</TH>
											
											<%  int auxIndex = 0; %>
											<logic:iterate name="MrDocPrvtMovDetailForm" property="fullNameTextArray" indexId="index" id="baseArray">

												<%MrDocPrvtMovDetailForm mrDocPrvtForm = ( MrDocPrvtMovDetailForm ) session.getAttribute( "MrDocPrvtMovDetailForm" );
												    String[] arrayCtcNbr = mrDocPrvtForm.getCtcNbrArray();
												    String indexCtcNbrArray = arrayCtcNbr[ auxIndex++ ];
											    %>
											<bean:define name="MrDocPrvtMovDetailForm"	property='<%="fullNameTextArray[" + index + "]"%>' id="fullNameTextArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtMovDetailForm"	property='<%="fullName_2_TextArray[" + index + "]"%>' id="fullName_2_TextArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtMovDetailForm"	property='<%="fullName_3_TextArray[" + index + "]"%>' id="fullName_3_TextArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtMovDetailForm"	property='<%="phoneDddCodeArray[" + index + "]"%>' id="phoneDddCodeArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtMovDetailForm"	property='<%="phoneNbrArray[" + index + "]"%>' id="phoneNbrArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtMovDetailForm"	property='<%="phoneExtNbrArray[" + index + "]"%>' id="phoneExtNbrArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtMovDetailForm"	property='<%="opernCodeArray[" + index + "]"%>' id="opernCodeArray" type="java.lang.String" />
												<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2">
													</logic:equal>
												</ods:CountStep>
												<bean:define name="MrDocPrvtMovDetailForm" property='<%="selectedItemsInGrid["+index+"]"%>' id="selectedItemsInGrid" type="java.lang.String" />
												<bean:define name="MrDocPrvtMovDetailForm" property='<%="deletedItems["+index+"]"%>' id="deletedItems" type="java.lang.String" />
												<td width="7%" align="center"><input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" disabled="disabled">
													<input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" style="display:none"></td>									
												<td width="7%" align="center"><input type="checkbox"	name=<%="deletedItems["+index+"]"%> value="S" <%=deletedItems.equals("S")?"checked=\"checked\"":""%> class="checkbox" /></td>									
												<td width="2%" align="center"><%=phoneDddCodeArray%></td>
												<td width="10%" align="center"><%=phoneNbrArray%></td>
												<td width="2%" align="center"><%=phoneExtNbrArray%></td>
												<td width="25%" align="center"><%=fullNameTextArray%></td>
												<td width="20%" align="center"><%=fullName_2_TextArray%></td>
												<td width="20%" align="center"><%=fullName_3_TextArray%></td>	
												<td width="10%" align="center"><%=opernCodeArray%></td>
												
											</logic:iterate>
										</tr>
									</tbody>
								</table>
								</DIV>
										</td>
				</tr>
			</TABLE>

			<TABLE class="ODS_internalWidth" border="0">
				<TBODY>
					<tr><td>&nbsp;</td></tr>
					<TR>
						<TD width="100%"></TD>
						<TD><html:button property="alterBtn" value="Alterar"
							onclick="submitAction('update');"></html:button></TD>
						<TD><html:button property="clearBtn" value="Limpar"
							onclick="clearPage();"></html:button></TD>
						<TD><html:button property="backBtn" value="Voltar"
							onclick="submitAction('back');"></html:button></TD>
					</TR>
				</TBODY>
			</TABLE>

			<jsp:include page="/View/Util/Footer.jsp" flush="true" /></td>
		</tr>
	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>




