<%@ page language="java" %>

<html>

<head>
	<title>PCL</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body style="font-family: verdana; size: 2;">
	<form action="arquivo_all_gerar.jsp">	
		<input type="hidden" name="acao"  value="" />	
	    Diretorio (lista de arquivos): <input type="text" name="dirName" size="200" maxlength="1000" />		
		<input type="button" value="Buscar" onclick="javascript: buscar()" />	
	
		Arquivo (caminho completo e nome): <input type="text" name="fileName" size="200" maxlength="1000" />		
		<input type="button" value="Gerar" onclick="javascript: gerar()" />	
	</form>
	<% if(request.getAttribute("errorMsg1") != null){%>
	<table>
		<tr>
			<td style="color: #FF0000;"><%= request.getAttribute("errorMsg1")%></td>
		</tr>
		<tr>
			<td><%= request.getAttribute("errorMsg2")%></td>
		</tr>
	</table>
	<%} %>
</body>

</html>

<script>

	function buscar(){
		var form = document.forms[0];
		form.acao.value = 'buscar';
	    form.submit();	
	}

	function gerar(){
		var form = document.forms[0];
		form.acao.value = 'gerar';
	    form.submit();	
	}
</script>