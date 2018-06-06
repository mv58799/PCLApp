
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Consulta </TITLE>
	</HEAD>

	<body>
		<html:form action="/ListCustomerBrokerMovementView">
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
									<th class="subtitle" scope="colgroup" colspan="3">Consulta em lista da associação entre o Cliente e as Corretoras com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="CI_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="33%">CNPJ :</TD>
											<TD width="33%">Número Cliente:</TD>
											<TD width="33%">Número Cliente Corretora:</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="33%"><html:text property="jspApplication"></html:text></TD>
											<TD width="33%"><html:text property="jspApplication"></html:text></TD>
											<TD width="33%"><html:text property="jspApplication"></html:text></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="33%">Código Usuário Última Alteração:</TD>
											<TD width="33%">Código Usuário Última Alteração:</TD>
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
											<TD width="100%" align="left"><html:button property="backBtn" value="Voltar" onclick="document.forms[0].action = './index';"></html:button></TD>
											<TD><html:button property="alterBtn" value="Alterar" onclick="document.forms[0].action='./UpdateCustomerBrokerMovementView'; document.forms[0].submit();"></html:button></TD>
											<TD><html:button property="approvalBtn" value="Aprovar/Rejeitar" onclick="document.forms[0].action = './ApprovalCustomerBrokerMovementView'; document.forms[0].submit();"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="document.forms[0].action='';"></html:button></TD>
											<TD><html:button property="listBtn" value="Listar" disabled="true" onclick="document.forms[0].action = './ListCustomerBrokerMovementView'; document.forms[0].submit();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<!-- o grid a seguir não representa o resultado da QUERY, é simplesmente DEMONSTRATIVO -->
				<tr>
					<html:hidden property="selectedCUST_NBR" value="0" />
					<td>&nbsp;</td>
					<td>
						<table class="CI_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta em lista da associação entre o Cliente e as Corretoras com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="CI_internalWidth" border="0">
									<tbody>
										<tr class="fixed">
											<TH class="CI_header">&nbsp;</TH>
											<TH class="CI_header" width="15%">CNPJ</TH>
											<TH class="CI_header" width="15%">Número do Cliente</TH>
											<TH class="CI_header" width="35%">Data/Hora Última Atualização</TH>
											<TH class="CI_header" width="15%">Código Usuário Última Alteração</TH>
											<TH class="CI_header" width="10%">Código Ação Registro</TH>
										</tr>
										<tr class="CI_line1">
											<TD width="3%"><html:radio styleClass="radio" property="radioBtn" value="radioBtn"></html:radio></TD>
											<TD width="15%">1111111111111</TD>
											<TD width="15%">1234567890</TD>
											<TD width="35%">07/03/2007 - 09:50 AM</TD>
											<TD width="15%">1234567890</TD>
											<TD width="10%">I</TD>
										</tr>
										<tr class="CI_line2">
											<TD width="3%"><html:radio styleClass="radio" property="radioBtn" value="radioBtn"></html:radio></TD>
											<TD width="15%">2222222222222</TD>
											<TD width="15%">0987654321</TD>
											<TD width="35%">07/03/2007 - 09:50 AM</TD>
											<TD width="15%">0987654321</TD>
											<TD width="10%">E</TD>
										</tr>
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
