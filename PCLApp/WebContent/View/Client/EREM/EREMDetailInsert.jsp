<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.erem.form.EREMDetailForm"%>

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
				document.forms[0].action = "EREM.EREMDetail.Insert.InsertDomain"; 
				document.forms[0].backURL.disabled = true; 
			}
			else if ( action == 'deleteDomain' ) {
				document.forms[0].action = "EREM.EREMDetail.Insert.DeleteDomain"; 
				document.forms[0].backURL.disabled = true; 
			}
			else if (action =='CustomerPrvt.CustomerPrvtList')
			{
				document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.Show";
				document.forms[0].backURL.disabled = false;
			}
		};

		function setSelectedKeys(er, em){
			document.forms[0].selectedErNbrInGrid.value = er;
			document.forms[0].selectedEmNbrInGrid.value = em;
		};

		function enableFields()
		{
			document.forms[0].custFullNameText.disabled = false;
			document.forms[0].custNbr.disabled = false;
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
			validationMessageArea.innerHTML += 'Erro: O registro não pode ser inserido pois são obrigatórias uma ou mais associações com "ER".<br>';
			validationMessageArea.style.display = "inline";
			tableError.style.display = "inline"
		}
		else {
			submitAction('insert');
		}
	}
	 </script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="EREM.EREMDetail"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Inserção de Associação ER x EM</TITLE>
	</HEAD>

	<body>
		<html:form action="/EREM.EREMDetail.Insert.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="EREM.EREMDetail.Insert.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="4">Inserção de Associação ER x EM</th></tr></thead>
								<tbody>
									<tr class="ODS_Detail_Line1">
										<TD colspan="4">Número do ER *</TD>

									</tr>
									<tr class="ODS_Detail_Line2">
										<TD colspan="4"><html:text property="erNbr" styleClass="ODS_Text_Field_Size_30" maxlength="30"></html:text></TD>
									</tr>
									<tr><td width="100%">&nbsp;</td></tr>
									<tr class="ODS_line11"><td colspan="4">Dados do EM:</td></tr>
									<tr>
				 						<td colspan="4"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
									</tr>
									<tr><td width="100%">&nbsp;</td></tr>
									<tr class="ODS_Detail_Line1">
										<TD width="24%">Papel do Cliente no Relacionamento *</TD>											
										<TD width="20%">Número do EM *</TD>
										<td width="36%">&nbsp;</td>
										<td width="20%">&nbsp;</td>
									</tr>
									<tr class="ODS_Detail_Line2">
										<TD width="24%">
											<html:select property="roleCustCode" styleClass="ODS_Select_Field_Size_10">
												<html:option value=""></html:option>
												<html:options property="roleCustCodeDomain.columnValuesByName(ROLE_CUST_CODE)" labelProperty="roleCustCodeDomain.columnValuesByName(ROLE_CUST_TEXT)" />
											</html:select>
										</TD>
										<TD colspan="2"><html:text property="emNbr" styleClass="ODS_Text_Field_Size_30" maxlength="30"></html:text>&nbsp;&nbsp;</TD>
										<td>&nbsp;</td>
									</tr>
									<tr class="ODS_Detail_Line1">
										<TD width="24%">Número do Cliente</TD>
										<TD colspan="2">Nome do Cliente</TD>
										<td>&nbsp;</td>
									<tr>
									<tr class="ODS_Detail_Line2">
										<TD width="24%"><html:text property="custNbr" styleClass="ODS_Text_Field_Size_15" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;<html:button property="getBtn" value="Buscar" onclick="enableFields();submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList.Insert');"></html:button></TD>
										<TD colspan="2"><html:text property="custFullNameText" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
										<td>&nbsp;</td>
									</tr>
								</tbody>
							</TABLE>
							<table class="ODS_internalWidth" border="0">
								<tbody>
									<TR>
										<TD width="100%"></TD>
										<TD><html:button property="insertEMBtn" value="Inserir EM" onclick="enableFields();submitAction('insertDomain');"></html:button></TD>
									</TR>
								</tbody>
							</TABLE>
							<html:hidden property="selectedErNbrInGrid" value="0" />
							<html:hidden property="selectedEmNbrInGrid" value="0" />
	
							<table class="ODS_internalWidth" border="0">
								<thead><tr><th class="subtitle" scope="colgroup" colspan="6">EM's Associados</th></tr></thead>
									<tbody>
										<tr class="fixed">
											<TH class="ODS_header" width="7%">Inserir</TH>
											<TH class="ODS_header" width="7%">Excluir</TH>
											<TH class="ODS_header" width="20%">Número do ER</TH>
											<TH class="ODS_header" width="20%">Número do EM</TH>
											<TH class="ODS_header" width="20%">Papel do Cliente no Relacionamento</TH>
											<TH class="ODS_header" width="20%">Nome do Cliente</TH>
										</tr>

										<%int rowIndex = 0;%>
										<logic:notEmpty name="EREMDetailForm"	property="erEmGrid">
											<logic:iterate name="EREMDetailForm"
												property="erEmGrid" indexId="index" id="row">
												<%
												EREMDetailForm erEmDetailForm = (EREMDetailForm) session.getAttribute("EREMDetailForm");
												String[] resultLine = erEmDetailForm.getErEmGrid()[rowIndex++];
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

												<bean:define name="EREMDetailForm" property='<%="selectedItemsInGrid["+index+"]"%>' id="selectedItemsInGrid" type="java.lang.String" />
												<bean:define name="EREMDetailForm" property='<%="deletedItems["+index+"]"%>' id="deletedItems" type="java.lang.String" />
												<td width="7%" align="center"><input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" disabled="disabled">
													<input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" style="display:none"></td>									
												<td width="7%" align="center"><input type="checkbox"	name=<%="deletedItems["+index+"]"%> value="S" <%=deletedItems.equals("S")?"checked=\"checked\"":""%> class="checkbox" /></td>									
												<td width="20%" align="right"><%=resultLine[EREMDetailForm.COL_POS_ER_NBR]%></td>
												<td width="20%" align="right"><%=resultLine[EREMDetailForm.COL_POS_EM_NBR]%></td>
												<td width="20%" align="left"><%=resultLine[EREMDetailForm.COL_POS_ROLE_CUST_CODE]%></td>
												<td width="20%" align="left"><%=resultLine[EREMDetailForm.COL_POS_CUST_FULL_NAME]%></td>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</TABLE>

								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="insertBtn" value="Confirmar Inserção" onclick="submitAction('insert');enableFields();"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="javascript:clearAllPage();"></html:button></TD>
											<TD align="left"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>										
										</TR>
									</tbody>
								</TABLE>
							</td>
						</tr>
						<jsp:include page="/View/Util/Footer.jsp" flush="true"/>			
					</table>
				</html:form>
			</body>
			<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

