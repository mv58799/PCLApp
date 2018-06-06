<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Common/css/citi.css">
		<script language="javascript" src="<%= request.getContextPath() %>/Common/js/dialog_window.js"></script>
		<script language="javascript">

		function submitAction( action )
		{
			if ( action == 'officer' )
			{
				document.forms[0].action = "./ListOfficerView";
				document.forms[0].backURL.disabled = false;
			}
			else if ( action == 'customer' )
			{
				document.forms[0].action = "./ListCustomerView";
				document.forms[0].backURL.disabled = false;
			}
			document.forms[0].submit();
		}
	 </script>

	<TITLE></TITLE>
	</HEAD>
	<body>
		<html:form action="/Menu"><table class="CI_mainTable" cellspacing="0"><tr>
		<DIV STYLE="overflow; width:0px; height:0px; display:none;" id="teste"><html:text property="backURL" value="Menu"></html:text></DIV> 
			<td>
				<TABLE border="0">
					<TBODY>
						<TR>
							<TD width="100%"><html:button property="voltar" value="Customer" onclick="submitAction('customer');"></html:button></TD>
							<TD width="100%"><html:button property="limpar" value="Officer" onclick="submitAction('officer');"></html:button></TD>
						</TR>
					</TBODY>
				</TABLE>
			</td>
		</html:form>
	</body> 
</html:html>