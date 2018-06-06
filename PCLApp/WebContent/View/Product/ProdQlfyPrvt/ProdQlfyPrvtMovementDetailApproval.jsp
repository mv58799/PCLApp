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
			function extraActions(action){};
		 </script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="ProdQlfyPrvt.ProdQlfyPrvtMovementDetail"/>
			<jsp:param name="approvalControlNames" value="'approvedBtn','reprovedBtn',''"/>
			<jsp:param name="mandatoryControlNames" value="'','prodQlfyCodeSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'','Código da Qualificação'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Detalhe de Qualificação com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdQlfyPrvt.ProdQlfyPrvtMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdQlfyPrvt.ProdQlfyPrvtMovementDetail.Approval.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Qualificação com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="12%">Código</TD>
											<TD width="12%">Descrição</TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="12%"><html:text styleClass="ODS_Text_Field_Size_5" property="prodQlfyCode" disabled="true" maxlength="4"></html:text></TD>
											<TD width="12%"><html:text styleClass="ODS_Text_Field_Size_50" property="prodQlfyText" disabled="true" maxlength="40"></html:text></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line1">
											<TD colspan="5">
												<table><tr>
													<td width="30%">Usuário de Última Atualização</TD>
											        <TD width="35%">Data/Hora de Última Atualização</TD>
													<td width="30%">Ação</td>
												</tr>
												<tr>
													<td width="30%"><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserId" disabled="true"></html:text></TD>
											        <TD width="35%"><html:text styleClass="ODS_Text_Field_Size_10" property="lastUpdDate" disabled="true"></html:text></TD>
   													<TD width="30%"><html:text styleClass="ODS_Text_Field_Size_10" property="opernCode" disabled="true"></html:text></td>
												</tr>
												</table>
											</TD>
										</tr>
										<tr class="ODS_Detail_line2">
											<td></td>
										</tr>
									</tbody>
								</TABLE>
								</td>
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
	disableApproveButtons("<bean:write name='ProdQlfyPrvtMovementDetailForm' property='lastUpdUserId'/>","<bean:write name='ProdQlfyPrvtMovementDetailForm' property='opernCode'/>",'true');							
</script>