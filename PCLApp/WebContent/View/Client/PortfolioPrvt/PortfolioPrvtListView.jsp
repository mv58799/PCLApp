
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

<script language="javascript">
	function extraActions(action){
	
		if (action =='Officer.OfficerList')
		 {
		  document.forms[0].action = "Officer.OfficerList.List.Show";
		  document.forms[0].backURL.disabled = false;
		 }
	}	
	
	function disabledInputFields()
	{
		document.forms[0].offcrNameTextSrc.disabled = false;
	}																
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="PortfolioPrvt.PortfolioPrvtList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Carteiras</TITLE>
</HEAD>
<body>
<html:form action="/PortfolioPrvt.PortfolioPrvtList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Banker" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:hidden property="selectedPortfCode" value="" />

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:text property="backURL"
				value="PortfolioPrvt.PortfolioPrvtList.List.Show" style="display:none"></html:text>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Consulta de Carteira</th>
					</tr>
				</thead>
			</table>

			<table class="ODS_internalWidth" border="0">
				<tbody>
					<tr class="ODS_Detail_Line1">						
						<TD width="12%">Código</TD>
						<TD width="25%"><html:text property="portfCodeSrc" styleClass="ODS_Text_Field_Size_10" maxlength="8"></html:text></TD>
						<TD  width="20%" align="right">Descrição&nbsp;&nbsp;</TD>
						<TD width="42%"><html:text property="portfNameTextSrc" styleClass="ODS_Text_Field_Size_40" maxlength="40"></html:text></TD>
					</tr>
					<tr class="ODS_Detail_Line1">
						<td width="12%">Nro. Banker&nbsp;&nbsp;</TD>
						<TD width="25%"><html:text property="offcrNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text>&nbsp;&nbsp;
							<html:button property="searchOfficer" value="Buscar" onclick="submitAction('PreparedSearch.Officer.OfficerList');"></html:button></TD>
						<td width="20%" align="right">Nome do Banker&nbsp;&nbsp;</TD>
						<td width="42%"><html:text property="offcrNameTextSrc" styleClass="ODS_Text_Field_Size_40"  maxlength="40"></html:text></td>
					</tr>
				</tbody>
			</TABLE>
					
			<TABLE class="ODS_internalWidth" border="0">
				<TBODY>
					<TR>
						<TD width="100%"> &nbsp;</TD>
						<TD><html:button  property="clearBtn" value="Consultar"
							onclick="disabledInputFields();submitAction('list')"></html:button></TD>
						<TD><html:button property="detailBtn"
							disabled="true" value="Detalhar"
							onclick="submitAction('detail');"></html:button></TD>
						<TD><html:button property="clearBtn" value="Limpar"
							onclick="clearAllPage();"></html:button></TD>

					</TR>
				</TBODY>
			</TABLE>
			
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3" width="838">Resultado da Consulta</th>
					</tr>
				</thead>
			</table>

			<table id="gridTable" class="ODS_internalWidth" border="0">
				<tbody>
					<tr id="gridHeader" class="fixed">
						<th class="ODS_header" width="3%"></th>
						<th class="ODS_header" width="35%">Descrição</th>
						<th class="ODS_header" width="14%">Código</th>
						<th class="ODS_header" width="35%">Nome do Banker</th>
						<th class="ODS_header" width="13%">Nro.Banker</th>
					</tr>
	
					<ods:DataSetRows name="PortfolioPrvtListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
						<logic:equal name="step" value="0">
							<tr class="ODS_line1">
						</logic:equal>
						<logic:equal name="step" value="1">
							<tr class="ODS_line2"> 
						</logic:equal>
						<bean:define name="resultRow" property="stringByName(PORTF_CODE)" id="selectedPortfCode" type="java.lang.String"></bean:define>
							<td class="centralized" width="3%" align="center">
								<input type="radio" class="radio" name="selection" onclick="<%="javascript:selectedPortfCode.value='" + selectedPortfCode + "';disableButtons(false);"%>">
							</td>
							<td  width="35%"><a class="ODS_CursorHand" href="#" onclick="<%="javascript:selectedPortfCode.value='" + selectedPortfCode + "';submitAction('detail');"%>"><bean:write name="resultRow" property="stringByName(PORTF_NAME_TEXT)"/></a></td>
							<td  width="14%" align="right"><bean:write name="resultRow" property="stringByName(PORTF_CODE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
							<td  width="35%"><bean:write name="resultRow" property="stringByName(OFFCR_NAME_TEXT)"/></td>									
							<td  width="13%" align="right"><bean:write name="resultRow" property="stringByName(PORTF_OFFCR_NBR)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
													
					</ods:DataSetRows>
				</tbody>
			</table>
						
			<TABLE class="ODS_internalWidth" border="0">
				<TBODY>
					<TR>
						<td width="100%"></td>
						<TD><html:button property="backBtn" value="Voltar"
							onclick="submitAction('back');"></html:button></TD>
					</TR>
				</TBODY>
			</TABLE>
		</td>
	</tr>
</table>
</html:form> <jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
	
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
