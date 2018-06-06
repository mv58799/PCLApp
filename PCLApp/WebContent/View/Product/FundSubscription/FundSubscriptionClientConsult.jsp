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
		<TITLE> Busca de cliente </TITLE>
		
		<script>
		function extraActions(action)
			{
			};
			
			function submitAction(act){
				var val = document.forms[0].acctNbr.value.replace(/\D/g,'');
				if (val == null || val.length == 0)
					{
						alert("Conta inválida");
					}else {
						document.forms[0].action=  'FundSubscription.FundSubscriptionDetail.'+ document.forms[0].operation.value +'.Execute';
						document.forms[0].isReturnConsult.value= true;
						document.forms[0].submit();
				}
			}
			function back(){
				document.forms[0].action= 'FundSubscription.FundSubscriptionDetail.'+ document.forms[0].operation.value +'.Show';
				document.forms[0].submit();
			}
		 </script>

		
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/FundSubscription.FundSubscriptionClient.Consult.Show.Clear.do">
			<html:hidden property="operation" />
			<html:hidden property="isReturnConsult"/>
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Termo de Adesão"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="FundSubscription.FundSubscriptionDetail.Insert.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0" height="100%">
				<tr height="1">
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup">Buscar cliente</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">Nº CC</TD>
											<TD width="85%"><html:text styleClass="ODS_Text_Field_Size_20" property="acctNbr" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNNN','left',null)"></html:text></TD>
										</tr>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
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
											<TD><html:button property="detailBtn" value="Consultar" onclick="submitAction('Detail', true);"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="back()"></html:button></TD>
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



