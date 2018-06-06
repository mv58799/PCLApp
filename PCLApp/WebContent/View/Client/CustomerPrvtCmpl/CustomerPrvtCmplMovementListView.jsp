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
	function extraActions(action)
	{
		if (action =='Officer.OfficerList')
		 {
		  document.forms[0].action = "Officer.OfficerList.List.Show";
		  document.forms[0].backURL.disabled = false;
		 }
		if (action =='CustomerPrvt.CustomerPrvtList')
		 {
		  document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.Show";
		  document.forms[0].backURL.disabled = false;
		 }

		if (action =='update' || action =='approve')
		{
			document.forms[0].custNbrSrc.disabled = true;
		}
	};
</script>


<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="CustomerPrvtCmpl.CustomerPrvtCmplMovementList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'updateBtn','approveBtn'" />
	<jsp:param name="approvalControlNames" value="'','','updateBtn'" />
	<jsp:param name="searchInputFields" value="'custNbrSrc', 'offcrNbrSrc'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Clientes com Pendência de Aprovação - Dados Complementares</TITLE>
</HEAD>
<body>
<html:form action="/CustomerPrvtCmpl.CustomerPrvtCmplMovementList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Aprovação" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:hidden property="selectedCustNbr"/>
			<html:hidden property="selectedOffcrNbr"/>

			<html:text property="backURL" value="CustomerPrvtCmpl.CustomerPrvtCmplMovementList.List.Show" style="display:none"></html:text>
			<td>
				<table class="ODS_internalWidth" border="0">
					<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Consulta de Clientes com Pendência de Aprovação - Dados Complementares</th></tr></thead>
					<tr>
						<td>
							<table class="ODS_internalWidth" border="0">
								<tbody>
									<tr class="ODS_Detail_line1">
										<TD width="17%">Nro. Cliente</TD>
										<TD width="16%" colspan="5"><html:text styleClass="ODS_Text_Field_Size_10" property="custNbrSrc" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;
											<html:button property="getCustomerNbr" value="Buscar"
											onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button></TD>
									</tr>
									<tr class="ODS_Detail_line1">
										<TD width="17%">Nome do Cliente</TD>
										<TD colspan="5"><html:text property="custTextSrc" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
									</tr>
									<tr class="ODS_Detail_Line1">
										<td width="14%">Nro. Banker</td>
										<td colspan="5">
											<table>
												<tr>
												<td width="20%"><html:text styleClass="ODS_Text_Field_Size_5" property="offcrNbrSrc" disabled="true"></html:text></TD>
													<TD align="right" width="23%">Nome do Banker&nbsp;&nbsp;</TD>
													<TD>
														<html:text styleClass="ODS_Text_Field_Size_40"	property="offcrTextSrc" disabled="true" size="60"></html:text>&nbsp;							
														<html:button property="getOFFCR_NBR" value="Buscar"	onclick="submitAction('PreparedSearch.Officer.OfficerList');"></html:button>
													</TD>
												</tr>
											</table>									
										</td>
									</tr>
									<tr class="ODS_Detail_line2">
										<td width="17%">Private Number</td>
										<td width="16%"><html:text styleClass="ODS_Text_Field_Size_10" property="prvtCustNbrSrc" maxlength="8" onkeyup="MaskFieldPress('CHAR','NNNNNNNN','left',null)"></html:text></td>
										<TD width="17%" align="right">Key Private Number&nbsp;&nbsp;</TD>
										<TD width="20%"><html:text styleClass="ODS_Text_Field_Size_10" property="prvtKeyNbrSrc" maxlength="8" onkeyup="MaskFieldPress('CHAR','NNNNNNNN','left',null)"></html:text></TD>
										<TD colspan="2">&nbsp;</TD>
									</tr>

									<tr class="ODS_Detail_Line1">
										<TD width="16%">Número EM</TD>
										<TD width="20%"><html:text styleClass="ODS_Text_Field_Size_30" property="emNbrSrc" maxlength="30"></html:text></TD>
										<TD align="right" width="20%">Status do Cliente&nbsp;&nbsp;</TD>
										<TD colspan="3">
											<html:select property="custPrvtStatCodeSrc"	styleClass="ODS_Select_Field_Size_10">
												<html:option value=""></html:option>
												<html:options
													property="custPrvtStatCodeDomain.columnValuesByName(CUST_PRVT_STAT_CODE)"
													labelProperty="custPrvtStatCodeDomain.columnValuesByName(CUST_PRVT_STAT_TEXT)" />
											</html:select>
										</TD>
									</tr>
									<tr class="ODS_Detail_line1">
										<td width="17%">Potencial Receita</td>
										<td colspan="5">
											<html:select property="wealthPotnlCodeSrc" styleClass="ODS_Select_Field_Size_40">
												<html:option value=""></html:option>
												<html:options property="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_CODE)"
													labelProperty="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_TEXT)" />
											</html:select>
										</td>
									</tr>
									<tr class="ODS_Detail_line1">
										<td width="17%">Classif. Compliance</td>
										 <td width="17%">
											<html:select property="classCmplcCodeSrc" styleClass="ODS_Select_Field_Size_40">
												<html:option value=""></html:option>
												<html:options property="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_CODE)"
													labelProperty="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_TEXT)" />
											</html:select>
										</td>
										<td width="8%">&nbsp;&nbsp;&nbsp;&nbsp;Tipo de Cliente</td>
										<td width="8%">
											<html:select property="prvtCustTypeCodeSrc" styleClass="ODS_Select_Field_Size_20">
												<html:option value=""></html:option>
												<html:options property="prvtCustTypeCodeDomain.columnValuesByName(PRVT_CUST_TYPE_CODE)"
													labelProperty="prvtCustTypeCodeDomain.columnValuesByName(PRVT_CUST_TYPE_TEXT)" />
											</html:select>
										</td>
									</tr>
									<tr class="ODS_Detail_line1">
									    <td colspan="4">
											<table>
												<tr>							
													<TD>Usuário de Última Atualização&nbsp;&nbsp;</TD>
													<td><html:text property="lastUpdUserIdSrc" styleClass="ODS_Text_Field_Size_10" maxlength="20"></html:text></td>
												</tr>
											</table>
										</td>
									</tr>
									</tbody>
								</TABLE>
	
							<TABLE class="ODS_internalWidth" border="0">
								<TBODY>
									<TR>
										<td width="100%"></td>
										<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list')"></html:button></TD>
										<TD><html:button property="approveBtn" value="Aprovação" disabled="true"
											onclick="submitAction('approve');document.forms[0].custNbrSrc.disabled = true;"></html:button></TD>
										<TD><html:button property="updateBtn" disabled="true" value="Alterar"
											onclick="submitAction('update');document.forms[0].custNbrSrc.disabled = true;"></html:button></TD>
										<TD><html:button property="clearBtn" value="Limpar"
											onclick="clearAllPage();"></html:button></TD>
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
										<div class="ODS_DivGrid">
											<table id="gridTable" width="1400px" border="0">
												<tbody>
													<tr id="gridHeader" class="fixed">
														<th class="ODS_header" width="1%"></th>
														<th class="ODS_header" width="20%" height="0">Nome do Cliente</th>
														<th class="ODS_header" width="5%" height="0">Número do Cliente</th>
														<th class="ODS_header" width="15%" height="0">Nome do Banker</th>
														<th class="ODS_header" width="10%" height="0">Número do Banker Global</th>
														<th class="ODS_header" width="5%" height="0">Número do Banker</th>
														<th class="ODS_header" width="5%">Número EM</th>
														<th class="ODS_header" width="8%">Key Private Number</th>
														<th class="ODS_header" width="5%">Private Number</th>
														<th class="ODS_header" width="10%" height="0">Usuário de Última Atualização</th>
														<th class="ODS_header" width="10%" height="0">Data/Hora de Última Atualização</th>
														<th class="ODS_header" width="6%" height="0">Ação</th>
													</tr>

													<ods:DataSetRows name="CustomerPrvtCmplMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
														<logic:equal name="step" value="0">
															<tr class="ODS_line1">
														</logic:equal>
														<logic:equal name="step" value="1">
															<tr class="ODS_line2">
														</logic:equal>
														<bean:define name="resultRow" property="bigDecimalByName(CUST_NBR)" id="CUST_NBR" type="java.math.BigDecimal"></bean:define>
														<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCodeSrc" type="java.lang.String"></bean:define>
														<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserIdSrc" type="java.lang.String"></bean:define>
			 											<TD width="1%"><input type="radio" class="radio" name="selection" 
															onclick="javascript:selectedCustNbr.value='<%= CUST_NBR %>'; disableButtons(false);disableApproveButtons('<%= lastUpdUserIdSrc %>','<%= opernCodeSrc %>',true);"/>
														</td>
														<td class="alignLeft" width="20%" align="left"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedCustNbr.value='<%= CUST_NBR %>'; submitAction('approve');document.forms[0].custNbrSrc.disabled = true;">
															<bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)" /></a>
														</td>
														<td class="alignRight" width="5%" align="right">
															<bean:write name="resultRow" property="bigDecimalByName(CUST_NBR)"
																formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' />
														</td>
														<td class="alignLeft" width="15%" align="left"><bean:write name="resultRow" property="stringByName(OFFCR_NAME_TEXT)" /></td>
														<td class="alignRight" width="10%" align="right"><bean:write name="resultRow" property="stringByName(GLB_REVEN_SYS_OFFCR_NBR)" /></td>
														<td class="alignRight" width="5%" align="right"><bean:write name="resultRow" property="bigDecimalByName(OFFCR_NBR)"
															formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' />
														</td>
														<td class="alignLeft" width="5%" align="left">
															<bean:write name="resultRow" property="stringByName(EM_NBR)" />
														</td>
														<td class="alignRight" width="8%" align="right">
															<bean:write name="resultRow" property="bigDecimalByName(PRVT_KEY_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' />
														</td>
														<td class="alignRight" width="5%" align="right">
															<bean:write name="resultRow" property="bigDecimalByName(PRVT_CUST_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' />
														</td>
														<td class="alignLeft" width="10%" align="left"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)" /></td>
														<TD width="10%">
															<bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/>
														</td>
														<td class="alignLeft" width="6%" align="center">
															<bean:write name="resultRow" property="stringByName(OPERN_TEXT)" />
														</td>
													</ods:DataSetRows>
												</tbody>
											</table>
										</div>
									</table>
									</td>
								</tr>
							</TABLE>

							<TABLE class="ODS_internalWidth" border="0">
								<TBODY>
									<TR>
										<td width="100%"></td>
										<TD><html:button property="backBtn" value="Voltar"	onclick="submitAction('back');"></html:button></TD>
									</TR>
								</TBODY>
							</TABLE>
						</td>
					</tr>
				</table>		
			</html:form> 
			<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
