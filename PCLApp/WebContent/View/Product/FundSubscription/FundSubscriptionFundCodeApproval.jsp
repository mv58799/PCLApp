<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.form.BaseForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> C�digo do Fundo </TITLE>
		
		<script>
		function extraActions(action)
			{
			};
			
			function aprovar(){
				document.forms[0].action = "FundSubscription.FundSubscriptionFundCodeDetail.Approval.Approve";
				document.forms[0].submit();
			}
			
			function reprovar(){
				document.forms[0].action = "FundSubscription.FundSubscriptionFundCodeDetail.Approval.Reprove";
				document.forms[0].submit();
			}
		 </script>

		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/FundSubscription.FundSubscriptionFundCodeDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Termo de Ades�o"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="FundSubscription.FundSubscriptionFundCodeDetail.Approval.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr height="1">
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="2">Aprovar C�digo do Fundo</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ODS_Detail_Line1">
									<TD width="15%">C�digo do fundo</TD>
									<TD width="85%"><html:text styleClass="ODS_Text_Field_Size_40" property="codigo" disabled="true"></html:text></TD>
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="15%">Nome do fundo</TD>
									<TD width="85%"><html:text styleClass="ODS_Text_Field_Size_40" property="nome" disabled="true"></html:text></TD>
								</tr>
								<tr valign="top">
									<td>&nbsp;</td>
									<td>
										<table class="ODS_internalWidth" border="0">
											<tr>
												<td>
												<table class="ODS_internalWidth" border="0">
													<tbody>
														<TR>
															<TD width="100%">&nbsp;</TD>
															<TD>
															<logic:equal name="FundSubscriptionFundCodeDetailApprovalForm" property="canApprove" value="true">
																<html:button property="approvalBtn" value="Aprovar" onclick="aprovar();"></html:button>
															</logic:equal>
															</TD>
															<TD><html:button property="reproveBtn" value="Reprovar" onclick="reprovar();"></html:button></TD>
														</TR>
													</tbody>
												</TABLE>
											</tr>
										</TABLE>
									</td>
								</tr>
							</tbody>
							<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
						</table>	
					</td>
				</tr>
			</table>		
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>



