
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Inserção </TITLE>
	</HEAD>

	<body>
		<html:form action="/InsertCustomerBrokerView">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
		    </jsp:include>
			<table class="CI_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="CI_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Inserção da associação entre o cliente e a corretora</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="CI_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="33%">CNPJ :</TD>
											<TD width="33%">Número Cliente:</TD>
											<TD width="33%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="33%"><html:text property="jspApplication"></html:text></TD>
											<TD width="33%"><html:text property="jspApplication"></html:text></TD>
											<TD width="33%"></TD>
										</tr>

										<tr class="fixed">
											<TD width="33%">&nbsp;</TD>
											<TD width="33%">&nbsp;</TD>
											<TD width="33%">&nbsp;</TD>
										</tr>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedCUST_NBR" value="0" />
					<td>&nbsp;</td>
					<td>
						<table class="CI_internalWidth" border="0">
							<tr>
								<td>
								<table class="CI_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%" align="left"><html:button property="backBtn" value="Voltar" onclick="document.forms[0].action = './ListCustomerBrokerView'; document.forms[0].submit();"></html:button></TD>
											<TD><html:button property="insertBtn" value="Inserir" onclick="document.forms[0].action='';"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="document.forms[0].action='';"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
