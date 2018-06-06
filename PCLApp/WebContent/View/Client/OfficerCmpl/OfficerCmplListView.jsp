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

<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet"
	type="text/css">
<script language="javascript">
			function extraActions(action){
				if ( action == 'listHistory' ){
					document.forms[0].action = "OfficerCmpl.OfficerCmplHistoryList.List.Show";
				}
				else if (action =='Officer.OfficerList')
				{
					document.forms[0].action = "Officer.OfficerList.List.Show";
					document.forms[0].backURL.disabled = false;
				}
			}; 																		
		</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">	
	<jsp:param name="pageName" value="OfficerCmpl.OfficerCmplList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'alterBtn','deleteBtn','detailBtn'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Banker - Dados Complementares</TITLE>
</HEAD>
<body>
<html:form action="OfficerCmpl.OfficerCmplList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Banker" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:text property="backURL"
		value="OfficerCmpl.OfficerCmplList.List.Show" style="display:none"></html:text>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Consulta de Banker - Dados Complementares</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
								<TD width="18%">Número do Banker</TD>
								<TD width="15%"><html:text property="officerNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
								<td width="50%"><html:text property="officerNameTextSrc" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></td>
								<TD width="5%"><html:button property="getCustomerNbr" value="Buscar" onclick="submitAction('PreparedSearch.Officer.OfficerList');"></html:button></TD>
								<TD width="5%"></TD>
							</tr>

							<tr class="ODS_Detail_Line2">
								<TD colspan="5">
									<table><tr>
										<td>Número Internacional&nbsp;&nbsp;</TD>
										<TD><html:text property="offcrIntlNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
										<td>&nbsp;&nbsp;Tipo&nbsp;&nbsp;</td>
										<TD>
											<html:select property="offcrTypeCodeSrc" styleClass="ODS_Select_Field_Size_20">
											<html:option value=""></html:option>
											<html:options property="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_CODE)" labelProperty="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_TEXT)" />
											</html:select>
										</TD>
									</tr></table>
								</TD>
							</tr>

							<tr class="ODS_Detail_Line2">
								<td>&nbsp;</td>
							</tr>
						</tbody>
					</TABLE>
				</tr>
			</TABLE>
			</td>
		</tr>
		<tr>
			<html:hidden property="selectedOffcrNbr" value="" />
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0">
						<tbody>
							<TR>
								<TD width="100%">&nbsp;</TD>
								<TD><html:button property="listBtn" value="Consultar"
									onclick="selectedOffcrNbr.disabled=true; submitAction('list'); "></html:button></TD>
								<TD width="100%"><html:button property="insertBtn"
									value="Inserir" onclick="submitAction('insert');"></html:button></TD>
								<TD width="100%"><html:button property="alterBtn"
									disabled="true" value="Alterar"
									onclick="submitAction('update');"></html:button></TD>
								<TD width="100%"><html:button property="deleteBtn"
									disabled="true" value="Excluir"
									onclick="submitAction('delete');"></html:button></TD>
								<TD width="100%"><html:button property="detailBtn"
									disabled="true" value="Detalhar"
									onclick="submitAction('detail');"></html:button></TD>
								<TD width="100%"><html:button property="histBtn"
									value="Histórico" onclick="submitAction('listHistory');"></html:button></TD>
								<TD><html:button property="clearBtn" value="Limpar"
									onclick="clearAllPage(); officerNameTextSrc.value='';"></html:button></TD>
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
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table id="gridTable" class="ODS_internalWidth" border="0">
						<tbody>
							<tr id="gridHeader" class="fixed">
								<TH class="ODS_header" width="3%">&nbsp;</TH>
								<TH class="ODS_header" width="25%">Nome</TH>
								<TH class="ODS_header" width="15%">Número do Banker</TH>
								<TH class="ODS_header" width="15%">Tipo</TH>
								<TH class="ODS_header" width="15%">Número Internacional</TH>
							</tr>
							<ods:DataSetRows name="OfficerCmplListForm" property="results"
								dataSetRowName="resultRow" stepIndexName="step"
								sequenceRestartStep="2">
								<logic:equal name="step" value="0">
									<tr class="ODS_line1">
								</logic:equal>
								<logic:equal name="step" value="1">
									<tr class="ODS_line2">
								</logic:equal>
								<bean:define name="resultRow"
									property="bigDecimalByName(OFFCR_NBR)" id="selectedOffcrNbr"
									type="java.math.BigDecimal"></bean:define>
								<TD width="3%"><input type="radio" class="radio"
									name="selection"
									onclick="<%="javascript:selectedOffcrNbr.value=" + selectedOffcrNbr + "; disableButtons(false);"%>"></td>
								<TD width="25%"><a class="ODS_CursorHand" href="#" onclick="<%="javascript:selectedOffcrNbr.value=" + selectedOffcrNbr + "; submitAction('detail');"%>"><bean:write name="resultRow"
									property="stringByName(OFFCR_NAME_TEXT)" /></a></td>
								<TD width="15%"><bean:write name="resultRow"
									property="bigDecimalByName(OFFCR_NBR)"
									formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></td>
								<TD width="15%"><bean:write name="resultRow"
									property="stringByName(OFFCR_TYPE_TEXT)" /></td>
								<TD width="15%"><bean:write name="resultRow"
									property="bigDecimalByName(OFFCR_INTL_NBR)"
									formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></td>
								</tr>
							</ods:DataSetRows>
						</tbody>
					</TABLE>
				</tr>
			</TABLE>
			<table class="ODS_internalWidth" border="0">
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0">
						<tbody>
							<TR>
								<TD width="100%">&nbsp;</TD>
								<TD align="left"><html:button property="backBtn" value="Voltar"
									onclick="submitAction('back');"></html:button></TD>
							</TR>
						</tbody>
					</TABLE>
				</tr>
			</TABLE>
			</td>
		</tr>
		<jsp:include page="/View/Util/Footer.jsp" flush="true" />
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
