<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<script language="javascript">
			function extraActions(action){
				if (action =='Officer.OfficerList')
				{
					document.forms[0].action = "Officer.OfficerList.List.Show";
					document.forms[0].backURL.disabled = false;
				}
			}; 
			function enabledInput(status){
				document.forms[0].officerNameTextSrc.disabled = status;
				document.forms[0].selectedOffcrNbr.disabled = !status;
			}
		</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="OfficerCmpl.OfficerCmplHistoryList"/>
				<jsp:param name="gridId" value="gridTable"/>
				<jsp:param name="headerId" value="gridHeader"/>
				<jsp:param name="controlNames" value="'detailBtn'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Consulta Histórico de Banker - Dados Complementares</TITLE>
	</HEAD>
	<body>
		<html:form action="OfficerCmpl.OfficerCmplHistoryList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Banker" />
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>

			<html:text property="backURL" value="OfficerCmpl.OfficerCmplHistoryList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta Histórico de Banker - Dados Complementares</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table>
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">Número do Banker</TD>
											<TD width="13%"><html:text property="officerNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
											<td width="45%"><html:text property="officerNameTextSrc" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></td>
											<TD width="10%"><html:button property="getCustomerNbr" value="Buscar" onclick="submitAction('PreparedSearch.Officer.OfficerList');"></html:button></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="15%">Número Internacional</TD>
											<TD width="13%"><html:text property="offcrIntlNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
											<td colspan="2">Tipo&nbsp;										
												<html:select property="offcrTypeCodeSrc" styleClass="ODS_Select_Field_Size_20">
													<html:option value=""></html:option>
													<html:options property="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_CODE)" labelProperty="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_TEXT)" />
												</html:select>
											</TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">Data de Referência</TD>
											<TD colspan="3"><html:text property="offcrRefDateSrc" styleClass="ODS_Text_Field_Size_10" maxlength="10" onkeydown="maskDate();"></html:text></TD>
										</tr>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
						<html:hidden property="selectedOffcrNbr" value=""/>
						<html:hidden property="selectedRefDate" value=""/>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											 <TD><html:button property="listBtn" value="Consultar" onclick="enabledInput(false);submitAction('list');enabledInput(true);"></html:button></TD>
											 <TD width="100%"><html:button property="detailBtn" disabled="true" value="Detalhar" onclick="submitAction('detail');"></html:button></TD>
											 <TD><html:button property="clearBtn" value="Limpar" onclick="clearAllPage(); disableButtons(true);"></html:button></TD>
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
											<TH class="ODS_header" width="15%">Nome</TH>
											<TH class="ODS_header" width="15%">Número do Banker</TH>
											<TH class="ODS_header" width="15%">Tipo</TH>
											<TH class="ODS_header" width="15%">Número Internacional</TH>
											<TH class="ODS_header" width="15%">Data de Referência</TH>
										</tr>
										<ods:DataSetRows name="OfficerCmplHistoryListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="bigDecimalByName(OFFCR_NBR)" id="selectedOffcrNbr" type="java.math.BigDecimal"></bean:define>
											<bean:define name="resultRow" property="stringByName(OFFCR_REF_DATE)" id="selectedRefDate" type="java.lang.String"></bean:define>
			 									<TD width="3%"><input type="radio" name="selection" onclick="<%="javascript:selectedOffcrNbr.value=" + selectedOffcrNbr + ";selectedRefDate.value='" + selectedRefDate + "';disableButtons(false);"%>"></td>
												<TD width="25%"><a class="ODS_CursorHand" href="#" onclick="<%="javascript:selectedOffcrNbr.value=" + selectedOffcrNbr + ";selectedRefDate.value='" + selectedRefDate + "';submitAction('detail');"%>"><bean:write name="resultRow" property="stringByName(OFFCR_NAME_TEXT)" /></a></td>
												<TD width="15%"><bean:write name="resultRow" property="bigDecimalByName(OFFCR_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
												<TD width="15%"><bean:write name="resultRow" property="stringByName(OFFCR_TYPE_TEXT)"/></td>
												<TD width="15%"><bean:write name="resultRow" property="bigDecimalByName(OFFCR_INTL_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
												<TD width="15%"><bean:write name="resultRow" property="dateByName(OFFCR_REF_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATE_DDMMYYYY%>'/></td>
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
											 <TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
			</table>	
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>