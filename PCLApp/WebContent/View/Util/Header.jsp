
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Common/css/citi.css">
		<TITLE></TITLE>
	</HEAD>

	<BODY>
		<table class="CI_mainTable" cellspacing="0"  width="100%">
				<TR>
					<td colspan="2"><IMG border="0" src="<%= request.getContextPath() %>/Common/image/h_wave.gif" width="100%" height="36"></td>
				</TR>
				<tr>
					<td width="55%">
						<table class="CI_internalWidth" cellspacing="0"  border="0">
							<tr> 
								<td width="93%"><IMG src="<%= request.getContextPath() %>/Common/image/logo.gif" alt="citigroup"></td>
							</tr>
						</TABLE>
					</td>
					<td width="55%">
						<table class="CI_internalWidth" cellspacing="0" border="0">
							<tr>
								<td width="100%">&nbsp;</td>
							</tr>
							<tr>
								<td width="100%" align="right" class="CI_divWelcomeUser"><span >Boa Tarde, USUÁRIO - 05/01/2006 17:00:00&nbsp;</span><html:link href="../Client/Customer/Util/FindProposal"><span class="CI_darkBlueBold">Início</span></html:link>&nbsp;<html:link href="../Client/Customer/Util/SystemExit" onclick="window.modal(this.href,'width=300,height=200,toolbars=0,resizable=0');return false;"><span class="CI_darkBlueBold">Encerrar</span></html:link></td>

							</tr>
							<tr>
								<td align="right" width="100%" class="CI_divLocalization"><span class="redMessage">*Protótipo*&nbsp;</span>v1.0.0.0&nbsp;</td>								
							</tr>
						</table>
				</td>
			</tr>
		</table>
	</BODY>
</html:html>
