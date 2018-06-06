
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
	function extraActions(action){};

	function disabledField(){
		document.forms[0].selectedCustNbr.disabled="true";
	}


</script>


<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="ContactCust.ContactCustList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn', 'alterBtn', 'deleteBtn'" />
	<jsp:param name="fieldsWithMask" value="['custCpfCnpjNbr','CPF/CNPJ',null,null,null]"/>
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Contatos</TITLE>
</HEAD>
<body>
<html:form action="/ContactCust.ContactCustList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet"/>
		<jsp:param name="currentSubSheet" value="Clientes" />
    </jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:hidden property="selectedCustNbr"/>
			<html:hidden property="selectedCtcNbr"/>
			<html:hidden property="selectedCtcNbrSrc"/>			

			<html:hidden property="custNbrSrc"/>			

			<html:text property="backURL" value="ContactCust.ContactCustList.List.Show" style="display:none"></html:text> 
			<td>
			<table class="ODS_internalWidth" border="0">
					<thead>
						<tr height="40">
							<th class="subtitle" scope="colgroup" colspan="3">Consulta de Contatos</th>
						</tr>
					</thead>
					<tr>
					<td width="10"> &nbsp;</td>
                    <td>
						<table class="ODS_internalWidth" border="0">
								<tbody>
									<tr><td>&nbsp;</td></tr>
									<tr class="ODS_line11"><td colspan="3">Dados do Cliente</td></tr>
									<tr>
			 						<td colspan="6"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
									</tr>
									<tr><td>&nbsp;</td></tr>
									<tr class="ODS_Detail_line1">
										<TD width="16%">Número do Cliente</TD>
										<TD width="15%"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" disabled="true"></html:text></TD>
										<TD width="7%">Nome</TD>
										<td colspan="3" width="458"><html:text property="custFullNameText" styleClass="ODS_Text_Field_Size_60"  disabled="true" maxlength="60"></html:text></td>
										
									</tr>
									<tr class="ODS_Detail_line2">
										<TD width="16%">CPF / CNPJ</TD>
										<TD width="15%"><html:text property="custCpfCnpjNbr" styleClass="ODS_Text_Field_Size_15"  disabled="true" maxlength="18"></html:text></TD>
										<TD width="7%"></TD>
										<TD></TD>
										<TD></TD>
										<TD></TD>
										
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr class="ODS_line11"><td colspan="3">Dados do Contato</td></tr>
								<tr>
					 			<td colspan="6"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
							 	</tr>
								<tr><td>&nbsp;</td></tr>
								<TR class="ODS_Detail_line1">
									<TD width="16%">Número do Contato</TD>
									<TD width="15%"><html:text property="ctcNbrSrc" styleClass="ODS_Text_Field_Size_5" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
									<TD colspan="4"></TD>
								</TR>
								<TR class="ODS_Detail_line2">
									<TD width="7%">Nome</TD>
									<TD colspan="4"><html:text property="fullNameTextSrc"
										styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
									<TD></TD>	
								</TR>	
							</TBODY>
						</TABLE>
						<TABLE class="ODS_internalWidth" border="0">
							<TBODY>
								<TR>
									<TD width="100%"> &nbsp;</TD>
									<TD><html:button property="listBtn" value="Consultar"
										onclick="javascript:disabledField();submitAction('list');"></html:button></TD>
									<TD width="100%"><html:button property="insertBtn"
										value="Inserir" onclick="submitAction('insert');"></html:button></TD>
									<TD width="100%"><html:button property="detailBtn"
										disabled="true" value="Detalhar"
										onclick="submitAction('detail');"></html:button></TD>
									<TD width="100%"><html:button property="alterBtn"
										disabled="true" value="Alterar"
										onclick="submitAction('update');"></html:button></TD>
									<TD width="100%"><html:button property="deleteBtn"
										disabled="true" value="Excluir"
										onclick="submitAction('delete');"></html:button></TD>
									<TD width="100%"><html:button property="clearBtn" value="Limpar"
										onclick="clearPage();"></html:button></TD>
								</TR>
							</TBODY>
						</TABLE>
			
							<table class="ODS_internalWidth" border="0">
								<thead>
									<tr>
										<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
									</tr>
								</thead>
								<tr>
									<td>
										<TABLE id="gridTable" class="ODS_internalWidth" border="0">
											<TBODY>
											<TR id="gridHeader" class="fixed">
												<TH class="ODS_header" width="3%"></TH>
												<TH class="ODS_header" width="57%">Nome do Contato</TH>	
												<TH class="ODS_header" width="16%">Número do Contato</TH>
												<TH class="ODS_header" width="3%">DDD</TH>
												<TH class="ODS_header" width="17%">Número do Telefone</TH>
												<TH class="ODS_header" width="4%">Ramal</TH>
											</TR>
			
												<ods:DataSetRows name="ContactCustListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
													<logic:equal name="step" value="0">
														<TR class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<TR class="ODS_line2"> 
													</logic:equal>
													<bean:define name="resultRow" property="stringByName(CUST_NBR)" id="selectedCustNbr" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(CTC_NBR)" id="selectedCtcNbr" type="java.lang.String"></bean:define>
														<td class="centralized" width="3%" align="center">
															<input type="radio" class="radio" name="selection" onclick="<%="selectedCtcNbr.value=" + selectedCtcNbr + ";selectedCtcNbrSrc.value=" + selectedCtcNbr + ";selectedCustNbr.value=" + selectedCustNbr + ";disableButtons(false);"%>"></td>
														<TD width="57%" align="left"><a class="ODS_CursorHand" href="#" onclick="<%="selectedCtcNbr.value=" + selectedCtcNbr + ";selectedCtcNbrSrc.value=" + selectedCtcNbr + ";selectedCustNbr.value=" + selectedCustNbr + ";submitAction('detail');"%>"><bean:write name="resultRow" property="stringByName(FULL_NAME_TEXT)" /></a></TD>
														<TD width="16%" align="right"><bean:write name="resultRow" property="bigDecimalByName(CTC_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></TD>
														<TD width="3%" align="right"><bean:write name="resultRow" property="bigDecimalByName(PHONE_DDD_CODE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></TD>
														<TD width="17%" align="right"><bean:write name="resultRow" property="bigDecimalByName(PHONE_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></TD>
														<TD width="4%" align="right"><bean:write name="resultRow" property="bigDecimalByName(PHONE_EXTN_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></TD>																			
												</ods:DataSetRows>
											</TBODY>
										</TABLE>
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
					</html:form> <jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</table>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>