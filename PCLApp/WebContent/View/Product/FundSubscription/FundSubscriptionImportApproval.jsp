<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.form.BaseForm"%>
<%@ page import="com.citibank.ods.modules.product.fundsubscription.form.FundSubscriptionImportApprovalForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Aprovação de Importação de Subscrição </TITLE>
		
		<script>
		function aprovar() {
			document.forms[0].action = "FundSubscription.FundSubscriptionDetail.Approval.Approve";
			document.forms[0].submit();
		}
		
		function reprovar() {
			document.forms[0].action = "FundSubscription.FundSubscriptionDetail.Approval.Reprove";
			document.forms[0].submit();
		}
		 </script>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/FundSubscription.FundSubscriptionDetail.Approval.do"  enctype="multipart/form-data">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="CentrApproval.CentrApprovalMovementList.List" style="display:none"></html:text> 
			<table class="ODS_mainTable" >
				<tr height="100%" valign="top">
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup">Aprovação de Importação de Subscrição</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td>
									<table width = "100%">
										<tr>
											<td >
											<table id="gridTable" border="0" width = "100%">
												<thead>
													<tr class="fixed">
														<TH class="ODS_header" width="15%">Tipo&nbsp;de&nbsp;produto</TH>
														<TH class="ODS_header" width="10%">Código</TH>
														<TH class="ODS_header" width="15%">Data&nbsp;da&nbsp;Importação</TH>
														<TH class="ODS_header" width="30%">Nome&nbsp;do&nbsp;Arquivo</TH>
														<TH class="ODS_header" width="20%">Quantidade&nbsp;de&nbsp;Registros</TH>
													</tr>
												</thead>
												<tbody>
													<tr class="ODS_line1">
														<bean:define id="form" name="FundSubscriptionDetailApprovalForm" type="com.citibank.ods.modules.product.fundsubscription.form.FundSubscriptionDetailApprovalForm" />
														<TD><bean:write name ="form" property="tipoProduto" /></TD>
														<TD><bean:write name ="form" property="codigo" /></TD>
														<TD><bean:write name ="form" property="dataImportacao" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></TD>
														<TD><bean:write name ="form" property="nomeArquivo" /></TD>
														<TD align="center"><bean:write name="form" property="registros" /></TD>
													</tr>
												</tbody>
											</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr valign="top" class="ODS_Detail_Line1" >
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD>
												<logic:equal name="FundSubscriptionDetailApprovalForm" property="canApprove" value="true">
													<html:button property="approvalBtn" value="Aprovar" onclick="javascript:aprovar();"></html:button>
												</logic:equal>
											</TD>
											<TD><html:button property="reproveBtn" value="Reprovar" onclick="javascript:reprovar();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
								</td>
							</tr>
							</tbody>
						</table>
						<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
					</td>
				</tr>
			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>



