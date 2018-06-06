
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
	<jsp:param name="pageName" value="Officer.OfficerDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Banker </TITLE>
</HEAD>
<body>
<html:form action="/Officer.OfficerDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Banker" />
	</jsp:include>
    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL" value="Officer.OfficerDetail.Consult.Show"	style="display:none"></html:text>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Detalhe de Banker</th></tr></thead>
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tbody>
								<tr class="ODS_line11" height="25"><td colspan="3">Dados Cadastrais:</td></tr>
								<tr>
			 						<td colspan="3"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td>
								</tr>
								<tr class="ODS_Detail_line1">
									<TD width="8%">Número do Banker</TD>
									<TD width="17%">Nome</TD>
									<td width="15%">Categoria</td>
								</tr>
								<tr class="ODS_Detail_line2">
									<td><html:text property="offcrNbr" disabled="true" styleClass="ODS_Text_Field_Size_10" ></html:text></td>
									<TD><html:text property="offcrNameText" disabled="true" styleClass="ODS_Text_Field_Size_40"></html:text></TD>
									<TD><html:text property="offcrCatCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
								</tr>
								<tr class="ODS_Detail_line1">
									<TD>Canal de Venda </TD>
									<TD>Data Inicial de Trabalho</TD>
									<TD>Número Real</TD>
								</tr>
								<tr class="ODS_Detail_line2">
									<td><html:text property="offcrChnnlCode" disabled="true" styleClass="ODS_Text_Field_Size_10" ></html:text></td>
									<TD><html:text property="offcrStartDate" disabled="true" styleClass="ODS_Text_Field_Size_15"></html:text></TD>
									<TD><html:text property="offcrRealNbr" disabled="true" styleClass="ODS_Text_Field_Size_10" ></html:text></TD>
								</tr>
								<tr class="ODS_Detail_line1">
									<TD colspan="3">Email</TD>
								</tr>
								<tr class="ODS_Detail_line2">
									<td colspan="3"><html:text property="offcrEmailName" disabled="true" styleClass="ODS_Text_Field_Size_40"></html:text></td>
								</tr>
								<logic:equal name="OfficerDetailForm" property="existingData" value="1">
									<tr class="ODS_line11" height="25"><td colspan="3">Dados Complementares:</td></tr>
									<tr>
				 						<td colspan="3"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td>
									</tr>
									<tr class="ODS_Detail_Line1">
										<TD width="33%" colspan="2">Número Internacional</TD>
										<TD width="67%">Tipo</TD>								
									</tr>
									
									<tr class="ODS_Detail_Line1">
										<TD width="33%" colspan="2"><html:text property="offcrIntlNbr" disabled="true" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>							
										<td width="67%">
											<html:select property="offcrTypeCode" styleClass="ODS_Select_Field_Size_20" disabled="true" >
												<html:option value=""></html:option>
												<html:options property="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_CODE)" labelProperty="offcrTypeCodeDomain.columnValuesByName(OFFCR_TYPE_TEXT)" />
											</html:select>
										</td>
									</tr>	
								</logic:equal>
							</tbody>
						</TABLE>

		
						<table class="ODS_internalWidth" border="0">
							<tbody>
								<TR>
									<TD width="100%"></TD>
									<td><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
								</TR>
							</tbody>
						</TABLE>
						<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
					</TABLE>
				</td>
			</tr>
		</table>
	</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

