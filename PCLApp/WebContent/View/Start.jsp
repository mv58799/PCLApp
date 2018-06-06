<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
<HEAD>

<TITLE>ODS - FAKE START PAGE </TITLE>

</HEAD>

	<BODY>
		<html:form action="/Start.Show">
			<table class="CI_internalWidth" border="0">
				<tbody>
					<tr class="fixed">
						<TD width="10%">SOEID:</TD>
						<TD width="15%">&nbsp;</TD>
						<TD width="10%">&nbsp;</TD>
						<TD width="15%">&nbsp;</TD>
						<td width="10%">&nbsp;</td>
					</tr>
					<tr class="fixed">
						<td width="10%"><html:text property="soeID" disabled="false"></html:text></td>
						<td width="15%">&nbsp;</td>
						<TD width="10%">&nbsp;</TD>
						<TD width="15%">&nbsp;</TD>
						<TD width="10%">&nbsp;</TD>
					</tr>
				</tbody>
			</table>
			<html:submit>Log On</html:submit>
		</html:form>
	</BODY>
	</html:html>
