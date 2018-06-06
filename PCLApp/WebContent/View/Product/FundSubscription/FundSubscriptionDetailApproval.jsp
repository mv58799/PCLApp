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
		<TITLE> Aprovação </TITLE>
		
		<script>
		function desabilitarCampos(tipo) {
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

		function extraActions(action)
			{
			};

		function aprovar(a){
			document.forms[0].submit();
		}
			
		function reprovar(a){
			document.forms[0].action='FundSubscription.FundSubscriptionDetail.Approval.Reprove';
			document.forms[0].submit();
		}
			
		 </script>

		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body onload="desabilitarCampos(document.forms[0].tipoProduto)">
		<html:form action="/FundSubscription.FundSubscriptionDetail.Approval.Approve.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Termo de Adesão"/>
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
								<td>
									<html:select disabled="true" tabindex="1" property="tipoProduto" styleClass="ODS_Select_Field_Size_15" onchange="javascript:desabilitarCampos(this)">
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
								<td width="1"><html:text readonly="true"  tabindex="2" property="protocolo"  maxlength="7" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								<td>Conhecimento&nbsp;do&nbsp;produto:</td>
								<td><html:text readonly="true"  property="conhecimentoProduto" styleClass="ODS_Select_Field_Size_15" maxlength="50"></html:text>   </td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;de&nbsp;abertura:</td>
								<td colspan="1"><html:text readonly="true"  tabindex="3" onkeydown="maskDate();" property="dataAbertura" styleClass="ODS_Text_Field_Size_30"></html:text></td>
								<td>Conta&nbsp;corrente&nbsp;do&nbsp;débito:</td>
								<td><html:text readonly="true"  property="ccDebito" tabindex="17" styleClass="ODS_Text_Field_Size_30"  onkeyup="MaskFieldPress('NUMBER','NNNNNNNNN','left',null)" maxlength="9" ></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Nome&nbsp;do&nbsp;cliente:</td>
								<td><html:text readonly="true"  tabindex="4" property="nomeCliente" disabled="true" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								<td>CPF&nbsp;do&nbsp;investidor:</td>
								<td><html:text readonly="true"  property="cpfInvestidor" tabindex="18"  onkeyup="MaskFieldPress('CHAR','NNN.NNN.NNN-NN','left',null)" maxlength="15" styleClass="ODS_Text_Field_Size_20" ></html:text>   </td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Assunto:</td>
								<td colspan="1"><html:text readonly="true"  tabindex="5" maxlength="100" property="assunto" styleClass="ODS_Text_Field_Size_30"></html:text></td>
								<td>Funcional&nbsp;do&nbsp;gerente:</td>
								<td><html:text readonly="true"  property="funcionalGerente" readonly="true" tabindex="19" maxlength="40"  styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
								<tr class="ODS_Detail_Line1">
								<td>Evento:</td>
								<td colspan="1"><html:text readonly="true"  tabindex="6" maxlength="100" property="evento" styleClass="ODS_Text_Field_Size_30"></html:text></td>
								<td>Nível&nbsp;de&nbsp;risco&nbsp;do&nbsp;produto:</td>
								<td><html:text readonly="true"  property="nivelRisco" tabindex="20" onkeyup="MaskFieldPress('NUMBER','NN','left',null)"  maxlength="2" styleClass="ODS_Select_Field_Size_15"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;da&nbsp;solução:</td>
								<td colspan="1"><html:text readonly="true"  tabindex="7"  onkeydown="maskDate();" property="dataSolucao" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								<td>Nome&nbsp;do&nbsp;aprovador<br>do&nbsp;mismatch:</td>
								<td><html:text readonly="true"  property="nomeAprovador"  tabindex="21"  maxlength="100" styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Fase:</td>
								<td colspan="1"><html:text readonly="true"  property="fase" tabindex="8" styleClass="ODS_Text_Field_Size_30" maxlength="40"></html:text></td>
								<td > <label id="labFundNameLabel">Nome&nbsp;do&nbsp;fundo:</label></td>
								<td id="tdFundNameField">
									<html:text readonly="true"  property="nomeFundo" tabindex="23"  styleClass="ODS_Text_Field_Size_30"></html:text>
									<html:text readonly="true"  property="nomeProduto" tabindex="23"  styleClass="ODS_Text_Field_Size_30"></html:text>
								</td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;de&nbsp;resolução&nbsp;da&nbsp;fase:</td>
								<td colspan="1"><html:text readonly="true"   onkeydown="maskDate();" tabindex="9" property="dataResolucaoFase" styleClass="ODS_Select_Field_Size_15"></html:text></td>
								
								
								<td>Nome&nbsp;do&nbsp;investidor:</td>
								<td><html:text readonly="true"  property="nomeInvestidor" readonly="true"  tabindex="25"  maxlength="100" styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Comentário:</td>
								<td colspan="1"><html:text readonly="true"  property="comentario" tabindex="10" maxlength="255" styleClass="ODS_Text_Field_Size_30"></html:text></td>

								<td>Perfil&nbsp;do&nbsp;cliente&nbsp;no&nbsp;GRB:</td>
								<td><html:text readonly="true"  property="perfilGRB"  readonly="true"  tabindex="26"  styleClass="ODS_Select_Field_Size_15"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Attestaion&nbsp;de&nbsp;Mismatch:</td>
								<td colspan="1">
									<html:text property="atestadoDivergencia" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true" />
								</td>
								<td>Tipo&nbsp;de&nbsp;transação:</td>
								<td><html:text readonly="true"  property="tipoTransacao" maxlength="50"  tabindex="27"  styleClass="ODS_Text_Field_Size_30"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Boletim/Reserva:</td>
								<td colspan="1">
									<html:text property="boletimReserva" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"/>
								</td>
								<td>Quanto&nbsp;deste&nbsp;valor&nbsp;é&nbsp;novo:</td>
								<td><html:text readonly="true"  property="valorNovo" styleClass="ODS_Text_Field_Size_30"  tabindex="28"   onkeyup="numberField(this)" maxlength="25"></html:text></td>
							</tr>	
							<tr class="ODS_Detail_Line1">
								<td>Termo&nbsp;de&nbsp;treinamento:</td>
								<td><html:text property="termoTreinamento" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>Valor&nbsp;do&nbsp;investimento&nbsp;R$:</td>
								<td><html:text readonly="true"  property="valorInvestimento" styleClass="ODS_Text_Field_Size_30"  tabindex="29"   onkeyup="numberField(this)" maxlength="25"></html:text></td>
							</tr>			
							<tr class="ODS_Detail_Line1">
								<td>Documentação&nbsp;OK:</td>
								<td><html:text property="documentacao" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
							</tr>	
							<tr class="ODS_Detail_Line1">
								<td>OTA:</td>
								<td><html:text property="ota" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr valign="top" class="ODS_Detail_Line1" >
								<td colspan="4">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<td width="100%"></td>
											<TD>
												<logic:equal name="FundSubscriptionDetailApprovalForm" property="canApprove" value="true">
													<html:button property="approvalBtn" value="Aprovar" onclick="javascript:aprovar();"></html:button>
												</logic:equal>
											</TD>
											<TD><html:button property="clearBtn" value="Reprovar" onclick="javascript:reprovar();"></html:button></TD>
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



