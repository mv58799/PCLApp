<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.citibank.ods.common.security.AuthorizationSG"%>
<%@page import="com.citibank.ods.common.user.User"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Expires" content="0" />
		<META http-equiv="Pragma" content="no-cache" />
		<META http-equiv="Cache-Control" content="no-cache" />
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE>Private Bank - Menu</TITLE>
	<script language="javascript">

		// BEGIN: Controle de back no Browser
		history.go(1);
	// END

	var message="Função desabilitada!";

	// BEGIN: Desabilitando botão direito
	function clickIE4(){
	if (event.button==2){
	event.button=0;
	return false;
	}
	}

	if (document.all&&!document.getElementById){
	document.onmousedown=clickIE4;
	}

	document.oncontextmenu=new Function("return false")
	// END

	// BEGIN: Desabilitando Ctrl N
	document.onkeydown=checkKeys
	function checkKeys()
	{
	if (event.ctrlKey && event.keyCode==78)
	{
	  event.keyCode=0;
	  return false;
	}
	}
	// END

	</script>

	</HEAD>

	<body>
		<form>
			<%	
				User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
				AuthorizationSG sg = new  AuthorizationSG();
				if(sg.hasNEWCPBAccess(user, "/NEWCPB.Menu")){
			%>
				<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
					<jsp:param name="currentSheet" value="NewCPBSheet"/>
				</jsp:include>	
			<%
				}else{
			%>
				<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
					<jsp:param name="currentSheet" value="ProductSheet"/>	
				</jsp:include>
			<%
				}
			%>
			<br><br>
			<html:hidden property="backURL" value="" style="display:none" />

			<jsp:include page="/View/Util/Footer.jsp" flush="true" />
		</form>
	</body>
</html:html>



