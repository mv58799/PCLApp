
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

	}																	
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="PortfolioPrvt.PortfolioPrvtDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Carteira</TITLE>
</HEAD>
<body>
<html:form action="/PortfolioPrvt.PortfolioPrvtDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Banker" />
	</jsp:include>
    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL" value="PortfolioPrvt.PortfolioPrvtDetail.Consult.Show" style="display:none"></html:text>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Carteira</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
								<TD width="25%">Codigo</TD>
								<td width="7%"></td>
								<TD width="25%" colspan="4">Descrição</TD>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="25%"><html:text property="portfCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></td>
								<td width="7%"></td>
								<TD width="25%" colspan="4"><html:text property="portfNameText" disabled="true" styleClass="ODS_Text_Field_Size_40"></html:text></TD>

							</tr>
							<tr class="ODS_Detail_Line1">
								<td width="25%">Número do Banker</td>
								<td width="7%"></td>
								<TD width="25%">Nome do Banker</TD>
								<td width="7%"></td>
								<TD width="25%"></TD>
								<TD width="7%"></TD>
							</tr>
							<tr class="ODS_Detail_Line2">
								<TD width="25%"><html:text property="portfOffcrNbr" disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<td width="7%"></td>
								<TD width="25%" colspan="4"><html:text property="offNameText" disabled="true" styleClass="ODS_Text_Field_Size_40"></html:text></TD>
								
							</tr>								
							<tr class="ODS_Detail_Line1">
								<td width="16%" colspan="2">Data de Cadastramento</td>
								<TD width="25%">Status</TD>
								<td width="7%"></td>
								<TD width="25%">Setor</TD>
								<td width="7%"></td>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="25%"><html:text property="portfStartDate" disabled="true" styleClass="ODS_Text_Field_Size_15" ></html:text></td>
								<td width="7%"></td>
								<TD width="25%"><html:text property="portfStatCode" disabled="true" styleClass="ODS_Text_Field_Size_5" ></html:text></TD>
								<td width="7%"></td>
								<TD width="25%"><html:text property="portfUnitCode" disabled="true" styleClass="ODS_Text_Field_Size_5" ></html:text></TD>
								<TD width="7%"></TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td width="15%">Território</td>
								<TD width="7%"></TD>
								<TD width="15%">Centro de Custo</TD>
								<TD width="7%"></TD>
								<TD width="16%" colspan="2">Grupo Negócio - Centro Custo</TD>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="15%"><html:text property="portfRegionCode" disabled="true" styleClass="ODS_Text_Field_Size_5" ></html:text></td>
								<td width="7%"></td>
								<TD width="15%"><html:text property="portfCostDivCode" disabled="true" styleClass="ODS_Text_Field_Size_5" ></html:text></TD>
								<td width="7%"></td>
								<TD width="16%"><html:text property="portfCostBusGrpCode" disabled="true"  styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="16%"></TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<TD width="16%" colspan="2">Código Região - Centro Custo</TD>
								<td width="16%" colspan="2">Código Rentabilidade - Centro Custo</td>
								<TD width="15%">Banker - Centro Custo</TD>
								<td width="7%"></td>

							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="15%"><html:text property="portfCostRegionCode" disabled="true" styleClass="ODS_Text_Field_Size_5" ></html:text></td>
								<td width="5%"></td>
								<TD width="15%"> <html:text property="portfCostPrftyCtrCode" disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<td width="5%"></td>
								<TD width="15%"><html:text property="portfCostRespOffcrCode" disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="15%"></TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td width="25%">Código Filial</td>
								<td width="7%"></td>
								<TD width="25%">Tipo Operações</TD>
								<td width="7%"></td>
								<td width="25%"></td>
								<td width="7%"></td>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="25%"><html:text property="portfBrchCode" disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></td>
								<td width="7%"></td>
								<td width="25%"><html:text property="portfOpernType" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></td>
								<TD width="7%"></TD>
								<td width="25%"></td>
								<TD width="7%"></TD>
							</tr>
							</tbody>
						</TABLE>
						<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%"></td>
								<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</TABLE>
			</html:form></td>
		</tr>
	</table>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>