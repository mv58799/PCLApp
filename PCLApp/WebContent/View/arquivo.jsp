<%@ page language="java" %>

<html>

<head>
	<title>PCL</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>

<body style="font-family: verdana; size: 2;">
	<form action="arquivo_gerar.jsp">	
		Arquivo: 
		<select name="fileName" onchange="exibiCampo()">
			<option value="">Selecione um arquivo...</option>
			<option value="log">Log</option>			
		</select>		
		<div id="divData" style="display: none;">
			<br />
			Data(dd/MM/yyyy):
			<input type="text" name="campoData" OnKeyUp="mascaraData(this);" maxlength="10" />
			<br />
		</div>
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
	function mascaraData(campoData){
		var form = document.forms[0];
		var data = campoData.value;
		if (data.length == 2){
			data = data + '/';
			form.campoData.value = data;
			return true;
		}
		if (data.length == 5){
			data = data + '/';
	        form.campoData.value = data;
	        return true;
	    }
	}
	function exibiCampo(){
		var form = document.forms[0];
		if(form.fileName.value == "log"){
			document.getElementById("divData").style.display = "block";
		}
	}
	function gerar(){
		var form = document.forms[0];
		if(form.fileName.selectedIndex > 0){
			if(form.campoData){
				if(form.campoData.value == ""){
					alert("Preencha o campo data!");
					form.campoData.focus();
					return true;
				}
			}
			form.submit();
		} else {
			alert("Selecione um arquivo!");
			form.fileName.focus();
		}
	}
</script>