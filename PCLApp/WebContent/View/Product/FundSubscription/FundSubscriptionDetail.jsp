	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.form.BaseForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Subscrição </TITLE>
		
		<script>

		function requestProc(url){
			var xmlHttp;
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xmlHttp.open("GET",url, false);
			xmlHttp.send();

			var xmlDoc = xmlHttp.responseText;
			return xmlDoc;
		}

		function verificar(){
			
			var ret = requestProc("FundSubscription.FundSubscriptionDetail.Consult.Show?enrollment=" + document.forms[0].protocolo.value + "&urlDistin=" + Math.random());
			var o = eval("("+ ret + ")");
			if (o.exists){
				if (o.isMov)
					alert("Protocolo cadastrado aguardando aprovação.");
				else 
					alert("Protocolo indisponível");
			}else {
				alert("Protocolo disponível");
			}
		}
		

		function checkCpfInvestor(){
			
			var ret = requestProc("FundSubscription.FundSubscriptionDetail.Consult.Show?cpf=" + document.forms[0].cpfInvestidor.value + "&urlDistin=" + Math.random());
			var o = eval("("+ ret + ")");
			if (o.exists){
				document.forms[0].nomeInvestidor.value = o.name;
				alert("CPF Ok.");
// 				document.getElementById('textResultCpfCheck').innerHTML = "Ok";
// 				document.getElementById('textResultCpfCheck').style.color = "#00AA00";
			}else {
				alert("CPF Não encontrado")
// 				document.getElementById('textResultCpfCheck').innerHTML = "CPF Não encontrado";
// 				document.getElementById('textResultCpfCheck').style.color = "#FF0000";
			}
		}
		
		function extraActions(action)
			{
			};

		function execute(a){

			if (document.forms[0].operation.value != "Update"){
				if (!document.forms[0].protocolo.value.match(/.*[A-Za-z].*/) ||  !document.forms[0].protocolo.value.match(/.*[0-9].*/)){
					alert("O protocolo deve conter letras e números");
					return;
				}
			}
			document.forms[0].action = "FundSubscription.FundSubscriptionDetail."+ document.forms[0].operation.value +".Execute"
			document.forms[0].submit();
		}
			
		function buscar() {
// 			document.forms[0].action= 'FundSubscription.FundSubscriptionClient.Consult.Show.Clear';
		//	document.forms[0].action= 'FundSubscription.FundSubscriptionDetail.Consult.Search.Insert';
			document.forms[0].action = 'FundSubscription.FundSubscriptionDetail.Insert.Show';
			document.forms[0].isConsultName.value= true;
			document.forms[0].submit();
		}
		function desabilitarCampos(tipo) {
			
			if (document.forms[0].operation.value == "Update"){
				document.forms[0].protocolo.readOnly = "true";
			}
			
			if (tipo.value =='F') {
				document.getElementsByName("codigoCorretora")[0].disabled='';
				
				document.getElementsByName("nomeProduto")[0].style.display = 'none';
				
				document.getElementsByName("nomeFundo")[0].style.display = 'block';
				
				document.getElementById('labFundNameLabel').innerHTML = "Nome do Fundo";
			}
			else {
				document.getElementsByName("codigoCorretora")[0].disabled='true';
				
				document.getElementsByName("nomeProduto")[0].style.display = 'block';
				
				document.getElementsByName("nomeFundo")[0].style.display = 'none';
				
				document.getElementById('labFundNameLabel').innerHTML = "Nome do Produto";
			};
		}
		
		function initFields(){
			desabilitarCampos(document.forms[0].tipoProduto);
			
		}
		function numberField(o){
			var val = o.value.replace(/\D/g,'')
			if (val.length > 2){
				val =val.substring(0, val.length -2) + "."+ val.substring(val.length - 2, val.length);
			}else if (val.length > 0){
				val = "."+ val
			}
			o.value = val;
		}
		 </script>
		 
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body onload="initFields()">
		<html:form action="/FundSubscription.FundSubscriptionDetail.Insert.Execute.do">
		<html:hidden property="operation" />
		<html:hidden property="isConsultName" />
		<html:hidden property="isReturnConsult"/>
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Termo de Adesão"/>
			</jsp:include>
			<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="FundSubscription.FundSubscriptionDetail"/>
			<jsp:param name="controlNames" value="'insertBtn'"/>
			<jsp:param name="fieldsWithMask" value="[]"/>
			<jsp:param name="searchInputFields" value="'detailBtn'" />
		</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="FundSubscriptionImport.FundSubscriptionImportDetail.Consult.Show" style="display:none"></html:text> 
			
			<table class="ODS_mainTable" >
				<tr height="100%" valign="top">
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="5">Inclusão/Alteração</th>
								</tr>
							</thead>
							<tbody>
								<tr class="ODS_Detail_Line1">
									<td>Tipo&nbsp;de&nbsp;produto:</td>
									<td colspan="2">
										<html:select tabindex="1" property="tipoProduto" styleClass="ODS_Select_Field_Size_15" onchange="javascript:desabilitarCampos(this)">
										<html:option value=""></html:option>
										<html:option value="F">Fundos Imobiliários</html:option>
										<html:option value="O">Outros</html:option>
									</html:select>
								</td>
								<td>Código&nbsp;corretora:</td>
								<td><html:text property="codigoCorretora" disabled="true" styleClass="ODS_Select_Field_Size_15"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Nº&nbsp;Protocolo:</td>
								<td width="1"><html:text tabindex="2" property="protocolo"  maxlength="7" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								<td><input type="button" value="Verificar" tabindex="16" onclick="verificar()"></td>
								<td>Conhecimento&nbsp;do&nbsp;produto:</td>
								<td><html:text property="conhecimentoProduto" styleClass="ODS_Select_Field_Size_15" maxlength="50"></html:text>   </td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;de&nbsp;abertura:</td>
								<td colspan="2"><html:text tabindex="3" onkeydown="maskDate();" property="dataAbertura" styleClass="ODS_Text_Field_Size_30"></html:text></td>
								<td>Conta&nbsp;corrente&nbsp;do&nbsp;débito:</td>
								<td><html:text property="ccDebito" tabindex="17" styleClass="ODS_Text_Field_Size_30"  onkeyup="MaskFieldPress('NUMBER','NNNNNNNNN','left',null)" maxlength="9" ></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Nome&nbsp;do&nbsp;cliente:</td>
								<td><html:text tabindex="4" property="nomeCliente" disabled="true" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								<td width="1"><input type="button" value="Buscar" onclick="javascript:buscar()"></td>
								<td>CPF&nbsp;do&nbsp;investidor:</td>
								<td><html:text property="cpfInvestidor" tabindex="18"  onkeyup="MaskFieldPress('CHAR','NNN.NNN.NNN-NN','left',null)" maxlength="15" styleClass="ODS_Text_Field_Size_20" ></html:text> <span style="float: right;" id="textResultCpfCheck"></span> <span style="float: right;"> <input type="button" value="Verificar" onclick="checkCpfInvestor()"></span>   </td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Assunto:</td>
								<td colspan="2"><html:text tabindex="5" maxlength="100" property="assunto" styleClass="ODS_Text_Field_Size_30"></html:text></td>
								<td>Funcional&nbsp;do&nbsp;gerente:</td>
								<td><html:text property="funcionalGerente" readonly="true" tabindex="19" maxlength="40"  styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
								<tr class="ODS_Detail_Line1">
								<td>Evento:</td>
								<td colspan="2"><html:text tabindex="6" maxlength="100" property="evento" styleClass="ODS_Text_Field_Size_30"></html:text></td>
								<td>Nível&nbsp;de&nbsp;risco&nbsp;do&nbsp;produto:</td>
								<td><html:text property="nivelRisco" tabindex="20" onkeyup="MaskFieldPress('NUMBER','NN','left',null)"  maxlength="2" styleClass="ODS_Select_Field_Size_15"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;da&nbsp;solução:</td>
								<td colspan="2"><html:text tabindex="7"  onkeydown="maskDate();" property="dataSolucao" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								<td>Nome&nbsp;do&nbsp;aprovador<br>do&nbsp;mismatch:</td>
								<td><html:text property="nomeAprovador"  tabindex="21"  maxlength="100" styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Fase:</td>
								<td colspan="2"><html:text property="fase" tabindex="8" styleClass="ODS_Text_Field_Size_30" maxlength="40"></html:text></td>
								<td > <label id="labFundNameLabel">Nome&nbsp;do&nbsp;fundo:</label></td>
								<td id="tdFundNameField">
									<html:text property="nomeFundo" tabindex="23"  styleClass="ODS_Text_Field_Size_30"></html:text>
									<html:text property="nomeProduto" tabindex="23"  styleClass="ODS_Text_Field_Size_30"></html:text>
								</td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;de&nbsp;resolução&nbsp;da&nbsp;fase:</td>
								<td colspan="2"><html:text  onkeydown="maskDate();" tabindex="9" property="dataResolucaoFase" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								
								
								<td>Nome&nbsp;do&nbsp;investidor:</td>
								<td><html:text property="nomeInvestidor" readonly="true"  tabindex="25"  maxlength="100" styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Comentário:</td>
								<td colspan="2"><html:text property="comentario" tabindex="10" maxlength="255" styleClass="ODS_Text_Field_Size_30"></html:text></td>

								<td>Perfil&nbsp;do&nbsp;cliente&nbsp;no&nbsp;GRB:</td>
								<td><html:text property="perfilGRB"  readonly="true"  tabindex="26"  styleClass="ODS_Select_Field_Size_15"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Attestaion&nbsp;de&nbsp;Mismatch:</td>
								<td colspan="2">
									<html:select property="atestadoDivergencia" tabindex="11" styleClass="ODS_Select_Field_Size_15">
										<html:option value="S">Sim</html:option>
										<html:option value="N">Não</html:option>
								</html:select>
								</td>
								<td>Tipo&nbsp;de&nbsp;transação:</td>
								<td><html:text property="tipoTransacao" maxlength="50"  tabindex="27"  styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Boletim/Reserva:</td>
								<td colspan="2">
									<html:select property="boletimReserva" tabindex="12" styleClass="ODS_Select_Field_Size_15">
										<html:option value="S">Sim</html:option>
										<html:option value="N">Não</html:option>
									</html:select>
								</td>
								<td>Quanto&nbsp;deste&nbsp;valor&nbsp;é&nbsp;novo:</td>
								<td><html:text property="valorNovo" styleClass="ODS_Text_Field_Size_30"  tabindex="28"   onkeyup="numberField(this)" maxlength="25"></html:text></td>
							</tr>	
							<tr class="ODS_Detail_Line1">
								<td>Termo&nbsp;de&nbsp;treinamento:</td>
								<td colspan="2">
									<html:select property="termoTreinamento" tabindex="13" styleClass="ODS_Select_Field_Size_15">
										<html:option value="S">Sim</html:option>
										<html:option value="N">Não</html:option>
									</html:select>
								</td>
								<td>Valor&nbsp;do&nbsp;investimento&nbsp;R$:</td>
								<td><html:text property="valorInvestimento" styleClass="ODS_Text_Field_Size_30"  tabindex="29"   onkeyup="numberField(this)" maxlength="25"></html:text></td>
							</tr>			
							<tr class="ODS_Detail_Line1">
								<td>Documentação&nbsp;OK:</td>
								<td colspan="2">
									<html:select property="documentacao" tabindex="14" styleClass="ODS_Select_Field_Size_15">
										<html:option value="S">Sim</html:option>
										<html:option value="N">Não</html:option>
									</html:select>
								</td>
							</tr>	
							<tr class="ODS_Detail_Line1">
								<td>OTA:</td>
								<td colspan="2">
									<html:select tabindex="15" property="ota" styleClass="ODS_Select_Field_Size_15">
										<html:option value="S">Sim</html:option>
											<html:option value="N">Não</html:option>
									</html:select>
								</td>
							</tr>
							<tr valign="top" class="ODS_Detail_Line1" >
								<td colspan="5">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<td width="100%"></td>
											<TD>
											<logic:equal name="FundSubscriptionDetailForm" property="blockConfirm" value="false">
												<html:button property="insertBtn"  tabindex="30"  value="Confirmar" onclick="execute('');"></html:button>
											</logic:equal>
											</TD>
											<TD><html:button property="clearBtn" value="Limpar"  tabindex="31"  onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
								</td>
							</tr>																										
							</tbody>
						</table>
						<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
					</td>
				</tr>
			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>



