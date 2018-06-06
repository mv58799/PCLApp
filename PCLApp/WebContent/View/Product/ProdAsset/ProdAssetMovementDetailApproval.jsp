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
				<jsp:param name="pageName" value="ProdAsset.ProdAssetMovementDetail"/>
				<jsp:param name="approvalControlNames" value="'approvedBtn','',''"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Detalhe de Classe de Ativo com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdAsset.ProdAssetMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdAsset.ProdAssetMovementDetail.Approval.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Classe de Ativo com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="12%">Código&nbsp;</TD>
											<TD width="15%">Descrição&nbsp;</TD>
											<td width="15%">Ordem</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="12%"><html:text property="prodAssetCode" disabled="true" styleClass="ODS_Text_Field_Size_10" maxlength="6"></html:text>&nbsp;</TD>
											<TD width="15"><html:text property="prodAssetText" disabled="true" styleClass="ODS_Text_Field_Size_30" maxlength="30"></html:text>&nbsp;</TD>
											<TD width="15%"><html:text property="assetClassCustRptOrderNbr" disabled="true" styleClass="ODS_Text_Field_Size_10" maxlength="6"></html:text></TD>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
						
										<tr><td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
					 						<td colspan="3"><img src='/appsl/privatelayer/Common/image/20grey1.gif' width="131%" height="1"></td>
										</tr>
										</tbody>
								</TABLE>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD colspan="5">
												<table><tr>
													<td width="24%">Usuário de Última Atualização&nbsp;</TD>
											        <TD width="32%">Data/Hora de Última Atualização&nbsp;</TD>
													<td width="44%">Ação</td>
												</tr>
												<tr>
													<td width="24%"><html:text property="lastUpdUserId" disabled="true" styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text>&nbsp;</TD>
											        <TD width="32%"><html:text property="lastUpdDate" disabled="true" styleClass="ODS_Text_Field_Size_30" maxlength="30"></html:text>&nbsp;</TD>
   													<TD width="44%"><html:text property="opernCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
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
	disableApproveButtons("<bean:write name='ProdAssetMovementDetailForm' property='lastUpdUserId'/>", "<bean:write name='ProdAssetMovementDetailForm' property='opernCode'/>","true");							
</script>