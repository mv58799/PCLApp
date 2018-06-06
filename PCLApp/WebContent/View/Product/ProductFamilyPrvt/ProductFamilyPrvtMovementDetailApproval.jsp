<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

		<script language="javascript">
			function extraActions(action){
			}; 																		
		</script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="ProductFamilyPrvt.ProductFamilyPrvtMovementDetail"/>
				<jsp:param name="approvalControlNames" value="'approvedBtn','',''"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Detalhe de Família com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProductFamilyPrvt.ProductFamilyPrvtMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProductFamilyPrvt.ProductFamilyPrvtMovementDetail.Approval.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Família com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="12%">Código</TD>
											<TD width="12%">Nome</TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="12%"><html:text property="prodFamlCode" disabled="true" styleClass="ODS_Text_Field_Size_10" maxlength="6"></html:text></TD>
											<TD width="12%"><html:text property="prodFamlName" disabled="true" styleClass="ODS_Text_Field_Size_40" maxlength="40"></html:text></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line1">
											<TD width="12%">Descrição</TD>
											<TD width="12%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD colspan="5"><html:text property="prodFamlText" disabled="true" styleClass="ODS_Text_Field_Size_70" maxlength="70"></html:text></TD>

										</tr>
										<tr class="ODS_Detail_line1">
											<TD colspan="5">
												<table><tr>
													<td width="30%">Usuário de Última Atualização</TD>
											        <TD width="35%">Data/Hora de Última Atualização</TD>
													<td width="30%">Ação</td>
												</tr>
												<tr>
													<td width="30%"><html:text property="lastUpdUserId" disabled="true" styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text></TD>
											        <TD width="35%"><html:text property="lastUpdDate" disabled="true" styleClass="ODS_Text_Field_Size_10" maxlength="10"></html:text></TD>
   													<TD width="30%"><html:text property="opernCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
												</tr>
												</table>
											</TD>
										</tr>
										<tr class="ODS_Detail_line1">
											<td></td>
										</tr>
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
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="approvedBtn" value="Aprovar" onclick="submitAction('approve');"></html:button></TD>
											<TD><html:button property="rejectBtn" value="Reprovar" onclick="submitAction('reprove','', true);"></html:button></TD>
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

<script language="javascript">
	disableApproveButtons("<bean:write name='ProductFamilyPrvtMovementDetailForm' property='lastUpdUserId'/>", "<bean:write name='ProductFamilyPrvtMovementDetailForm' property='opernCode'/>","true");							
</script>