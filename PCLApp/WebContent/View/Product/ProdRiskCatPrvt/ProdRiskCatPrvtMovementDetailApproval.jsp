
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<script language="javascript">
			function extraActions(action){
			}; 								
		</script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="ProdRiskCatPrvt.ProdRiskCatPrvtMovementDetail"/>
				<jsp:param name="approvalControlNames" value="'approvedBtn','',''"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Detalhe de Categoria de Risco com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdRiskCatPrvt.ProdRiskCatPrvtMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdRiskCatPrvt.ProdRiskCatPrvtMovementDetail.Approval.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Categoria de Risco com Pendência Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<td colspan="5">
												<table><tr>
													<TD width="15%">Código</TD>
													<TD>Descrição</TD>
												</tr>
												<tr>
													<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodRiskCatCode" disabled="true" maxlength="4"></html:text></TD>
													<TD><html:text styleClass="ODS_Text_Field_Size_40" property="prodRiskCatText" disabled="true" maxlength="40"></html:text></TD>
												</tr></table>
											</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="5"></td>
										</tr>
										<tr class="ODS_Detail_line1">
											<TD colspan="5">
												<table><tr>
													<td width="30%">Usuário de Última Atualização</TD>
											        <TD width="35%">Data/Hora de Última Atualização</TD>
													<td width="30%">Ação</td>
												</tr>
												<tr>
													<td width="30%"><html:text styleClass="ODS_Text_Field_Size_15" property="lastUpdUserId" disabled="true"></html:text></TD>
											        <TD width="35%"><html:text styleClass="ODS_Text_Field_Size_10" property="lastUpdDate" disabled="true"></html:text></TD>
   													<TD width="30%"><html:text styleClass="ODS_Text_Field_Size_5" property="opernCode" disabled="true"></html:text></td>
												</tr>
												</table>
											</TD>
										<tr class="ODS_Detail_line1">
											<TD></TD>
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
									<TBODY>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="approvedBtn" value="Aprovar" onclick="submitAction('approve');"></html:button></TD>
											<TD><html:button property="rejectBtn" value="Reprovar" onclick="submitAction('reprove','', true);"></html:button></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</TBODY>
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
	disableApproveButtons("<bean:write name='ProdRiskCatPrvtMovementDetailForm' property='lastUpdUserId'/>", "<bean:write name='ProdRiskCatPrvtMovementDetailForm' property='opernCode'/>", "true");							
</script>
