
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
			pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<link rel="stylesheet" type="text/css" href="../Client/Customer/Common/css/citi.css">
		<TITLE></TITLE>
	</HEAD>
	<html:form action="/Application">
		<BODY>
			<table class="CI_mainTable" cellspacing="0">
				<tr><td><br></td></tr>
				<tr>
					<td align="center" class="informative">Deseja realmente fechar esta seção do CI?</td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td align="center" class="fixed"><html:link href="#" onclick="window.self.close() ; window.close();">Sim</html:link>&nbsp;&nbsp;&nbsp;<html:link href="#" onclick="window.close();">Não</html:link></td>
				</tr>
			</table>	
		</BODY>
	</html:form>
</html:html>
