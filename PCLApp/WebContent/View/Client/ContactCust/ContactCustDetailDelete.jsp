
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

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">

<script language="javascript">
	function extraActions(action){

	}																	
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="ContactCust.ContactCustDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Exclusão de Contato</TITLE>
</HEAD>
<body>
<html:form action="/ContactCust.ContactCustDetail.Delete.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Clientes" />
	</jsp:include>
    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL" value="ContactCust.ContactCustDetail.Consult.Show"	style="display:none"></html:text>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">Exclusão de Contato</th>
					</tr>
				</thead>
				<tr>
					<td>&nbsp;</td>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="15%">Número do Cliente</TD>
								<TD width="15%"></TD>
								<TD width="15%">Nome</TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%"><html:text property="custNbrSrc" disabled="true" styleClass="ODS_Text_Field_Size_10" maxlength="11"></html:text></td>
								<td width="15%"></td>
								<TD width="15%" colspan="4"><html:text property="custFullNameText" disabled="true" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>


							</tr>
								
							<tr class="ODS_Detail_line1">
								<td width="15%">Número do Contato </td>
								<td width="15%"></td>
								<TD width="15%">Nome do Contato </TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%"><html:text property="ctcNbr" disabled="true" styleClass="ODS_Text_Field_Size_5" maxlength="6"></html:text></td>
								<td width="15%"></td>
								<TD width="15%" colspan="4"><html:text
									property="fullNameText" disabled="true" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<td colspan="4">
									<TABLE class="ODS_internalWidth" border="0">
										<tbody>
										<TR class="ODS_Detail_line1">
											<TD width="12%">(DDD)</TD>
											<TD width="13%">Telefone</TD>
											<TD width="10%"></TD>
											<TD width="49">Ramal</TD>
											<TD width="66%"></TD>			
										</TR>
										</tbody>
									</TABLE></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td colspan="4">
									<TABLE class="ODS_internalWidth" border="0">
										<tbody>
											<TR class="ODS_Detail_line2">
											<TD width="10%"><html:text property="phoneDddCode" maxlength="4" styleClass="ODS_Text_Field_Size_5" disabled="true"></html:text></TD>
											<TD width="12%"><html:text property="phoneNbr" maxlength="10" styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></TD>
											<TD width="12%"><html:text property="phoneExtnNbr" maxlength="5" size="5" styleClass="ODS_Text_Field_Size_5" disabled="true"  ></html:text></TD>	
											<TD width="100%"></TD>			
										</TR>
										</tbody>
									</TABLE></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
						</tbody>
					</TABLE>
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%"></td>
								<TD><html:button property="excluir" value="Confirmar Exclusão" onclick="submitAction('delete');"></html:button></TD>
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
