
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
	function extraActions(action){
		if ( action == 'deleteFromList' ){
			document.forms[0].action = "ClassCmplc.ClassCmplcDetail.Delete.Execute";
			document.forms[0].backURL.disabled = true;	
		}
	};
</script>


<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="ClassCmplc.ClassCmplcList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'deleteBtn'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Classificação Compliance</TITLE>
</HEAD>
<body>
<html:form action="/ClassCmplc.ClassCmplcList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Parâmetros" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<html:hidden property="selectedClassCmplcCode" value="0" />
			<html:text property="backURL"
				value="ClassCmplc.ClassCmplcList.List.Show" style="display:none"></html:text>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">Consulta de Classificação Compliance</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
								<TD  colspan="4">
									<table><tr>
										<td>Código&nbsp;&nbsp;</TD>
										<TD><html:text property="classCmplcCodeSrc"
											styleClass="ODS_Text_Field_Size_5" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></TD>
										<td>&nbsp;&nbsp;Descrição&nbsp;&nbsp;</td>
										<td><html:text property="classCmplcTextSrc"
											styleClass="ODS_Text_Field_Size_40" maxlength="40"></html:text></td>
										<td>&nbsp;&nbsp;Indicador de Sensitividade&nbsp;&nbsp;</td>
										<td><html:select property="sensIndSrc" styleClass="ODS_Select_Field_Size_5" >
											<html:option value=""></html:option>
											<html:options property="sensIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="sensIndDomain.columnValuesByName(INDICATOR_TEXT)" />
										</html:select></td>
									</tr></table>
								</TD>						
							</tr>
							<tr><TD>&nbsp;</TD></tr>
						</tbody>
					</TABLE>


					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<TD width="100%">&nbsp;</TD>
								<TD width="100%"><html:button property="clearBtn" value="Consultar"
									onclick="submitAction('list')"></html:button></TD>
								<TD width="100%"><html:button property="deleteBtn"
									disabled="true" value="Excluir"
									onclick="submitAction('deleteFromList', '', true);"></html:button></TD>
								<TD width="100%"><html:button property="clearBtn" value="Limpar"
									onclick="clearPage()"></html:button></TD>
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

							<table id="gridTable" class="ODS_internalWidth" border="0">
								<TBODY>
									<tr id="gridHeader" class="fixed">
										<TH class="ODS_header" width="3%">&nbsp;</TH>
										<th class="ODS_header" width="48%">Descrição</th>
										<th class="ODS_header" width="27%">Código</th>
										<th class="ODS_header" width="22%">Indicador de Sensitividade</th>
									</tr>

									<ods:DataSetRows name="ClassCmplcListForm" property="results"
										dataSetRowName="resultRow" stepIndexName="step"
										sequenceRestartStep="2">
										<logic:equal name="step" value="0">
											<tr class="ODS_Line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_Line2">
										</logic:equal>
										<bean:define name="resultRow"
											property="bigDecimalByName(CLASS_CMPLC_CODE)"
											id="selectedClassCmplcCode" type="java.math.BigDecimal"></bean:define>
										<td class="centralized" width="3%" align="center"><input
											type="radio" name="selection" class="radio"
											onclick="<%="javascript:selectedClassCmplcCode.value=" + selectedClassCmplcCode + ";disableButtons(false);"%>">
										</td>
										<td width="48%" align="left"><bean:write
											name="resultRow" property="stringByName(CLASS_CMPLC_TEXT)" /></td>
										<td width="27%" align="right"><bean:write
											name="resultRow"
											property="bigDecimalByName(CLASS_CMPLC_CODE)"
											formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>' /></td>
										<td width="22%" align="center"><bean:write
											name="resultRow" property="stringByName(SENS_IND)" /></td>
									</ods:DataSetRows>

								</tbody>
							</table>
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
