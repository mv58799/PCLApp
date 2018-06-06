<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Expires" content="0" />
<META http-equiv="Pragma" content="no-cache" />
<META http-equiv="Cache-Control" content="no-cache" />
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet"
	type="text/css">
<TITLE>Private Bank</TITLE>
<script language="javascript">

	// BEGIN: Controle de back no Browser
		history.go(1);
	// END

	var message="Fun��o desabilitada!";

	// BEGIN: Desabilitando bot�o direito
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
<html:form action="/ODSMenu.Show.do">
<table class="ODS_mainTable" border="0">
	<tr>
		<td>
		<table class="ODS_internalWidth" border="0">
			<tr>
				<td width="80%"><IMG class="logo"
					src="<%= request.getContextPath() %>/Common/image/cpb_signature_only_grey_red139x17.jpg"></td>
				<td width="20%" align="right" width="139" height="17"><img class="logo"
					src="<%= request.getContextPath() %>/Common/image/citi_2c_gry_pos_rgb120x83.jpg" width="120" height="83"></td>
			</TR>

			<tr class="ODS_Detail_Line2" align="center">
				<td colspan="2">Citigroup Inc. ou qualquer uma de suas subsidi�rias
				e afiliadas (Citigroup) disponibiliza este sistema/aplicativo
				exclusivamente para fins profissionais. 
				De acordo com os termos da pol�tica "Citigroup Electronic
				Communications" e de outras pol�ticas aplic�veis, voc� est� proibido
				de utilizar este 
				sistema para baixar arquivos (download), transmitir ou encaminhar
				imagens ou textos que ameacem a seguran�a de pessoas ou
				propriedades, 
				fazer mal uso de informa��es de propriedade do Citigroup ou
				confidenciais, fazer cal�nias �tnicas, usar apelidos/palavras
				racistas,encaminhar mensagens agressivas, material de sexo
				expl�cito, obscenidades ou qualquer outro conte�do que possa ser
				interpretado como inapropriado, abusivo ou ofensivo <br>
				para outros conforme categorias de prote��o indicadas no C�digo de
				Conduta do Citigroup.Todos os dados, informa��es e comunica��es
				geradas ou recebidas por voc� ou armazenadas neste
				sistema/aplicativo s�o de propriedade exclusiva do Citigroup e podem
				ser interceptadas, armazenadas, acessadas, vistas e usadas pelo
				Citigroup para qualquer finalidade. E-mails enviados por meio deste
				sistema e/ou outros sistemas e aplicativos n�o s�o particulares e
				s�o monitorados pelo Citigroup. O uso deste sistema indica seu
				entendimento quanto ao teor desta mensagem, seu compromisso no uso
				deste sistema de acordo com as pol�ticas do Citigroup e com a
				legisla��o aplic�vel e seu consentimento com o monitoramento, pelo
				Citigroup, dos seus e-mails e quaisquer outras atividades
				desenvolvidas por meio deste sistema.</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<tr>
		<td>
		<table class="ODS_internalWidth"  border="0">
			<tr>
				<td align="center"><html:button property="ConcordoBtn" value="Concordo" onclick="document.forms[0].submit('/ODSMenu');"></html:button></td>
				<td align="center"><html:button property="CancelBtn" value="Cancelar" onclick="document.forms[0].action='./ODSLogoff.Show';document.forms[0].submit();"></html:button></td>
			</tr>
		</table>
		</td>
	</tr>
	<jsp:include page="/View/Util/Footer.jsp" flush="true" />
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
</html:form>
</table>
</body>
</html:html>
