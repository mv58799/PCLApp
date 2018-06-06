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

		function submitAction(a){
			if (a == 'Reprovar')
				document.forms[0].action="FundSubscription.FundSubscriptionDetail.Approval.Reprove.do"
				
			document.forms[0].submit();
		}
			
		 </script>
		 
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
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
									<th class="subtitle" scope="colgroup" colspan="5">Aprovação</th>
								</tr>
							</thead>
							<tbody>
							<tr class="ODS_Detail_Line1">
								<td>Tipo&nbsp;de&nbsp;produto:</td>
								<td><html:text property="tipoProduto" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"/></td>
								<td>Código&nbsp;corretora:</td>
								<td><html:text property="codigoCorretora" disabled="true" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Nº&nbsp;Protocolo:</td>
								<td><html:text property="protocolo" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>Conhecimento&nbsp;do&nbsp;produto:</td>
								<td><html:text property="conhecimentoProduto" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text>   </td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;de&nbsp;abertura:</td>
								<td><html:text property="dataAbertura" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
								<td>Conta&nbsp;corrente&nbsp;do&nbsp;débito:</td>
								<td><html:text property="ccDebito" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Nome&nbsp;do&nbsp;cliente:</td>
								<td><html:text property="nomeCliente" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>CPF&nbsp;do&nbsp;investidor:</td>
								<td><html:text property="cpfInvestidor" styleClass="ODS_Text_Field_Size_30"></html:text>   </td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Assunto:</td>
								<td><html:text property="assunto" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
								<td>Funcional&nbsp;do&nbsp;gerente:</td>
								<td><html:text property="funcionalGerente" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>
								<tr class="ODS_Detail_Line1">
								<td>Evento:</td>
								<td><html:text property="evento" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
								<td>Nível&nbsp;de&nbsp;risco&nbsp;do&nbsp;produto:</td>
								<td><html:text property="nivelRisco" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;da&nbsp;solução:</td>
								<td><html:text property="dataSolucao" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>Nome&nbsp;do&nbsp;aprovador<br>do&nbsp;mismatch:</td>
								<td><html:text property="nomeAprovador" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Fase:</td>
								<td><html:text property="fase" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
								<td>Código&nbsp;do&nbsp;fundo:</td>
								<td><html:text property="codigoFundo" disabled="true" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Data&nbsp;de&nbsp;resolução&nbsp;da&nbsp;fase:</td>
								<td><html:text property="dataResolucaoFase" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>Nome&nbsp;do&nbsp;fundo:</td>
								<td><html:text property="nomeFundo" disabled="true" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Comentário:</td>
								<td><html:text property="comentario" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
								<td>Nome&nbsp;do&nbsp;Produto:</td>
								<td><html:text property="nomeProduto" disabled="true" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Attestaion&nbsp;de&nbsp;Mismatch:</td>
								<td><html:text property="atestadoDivergencia" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true" /></td>
								<td>Nome&nbsp;do&nbsp;investidor:</td>
								<td><html:text property="nomeInvestidor" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td>Boletim/Reserva:</td>
								<td><html:text property="boletimReserva" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"/></td>
								<td>Perfil&nbsp;do&nbsp;cliente&nbsp;no&nbsp;GRB:</td>
								<td><html:text property="perfilGRB" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
							</tr>	
							<tr class="ODS_Detail_Line1">
								<td>Termo&nbsp;de&nbsp;treinamento:</td>
								<td><html:text property="termoTreinamento" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>Tipo&nbsp;de&nbsp;transação:</td>
								<td><html:text property="tipoTransacao" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>			
							<tr class="ODS_Detail_Line1">
								<td>Documentação&nbsp;OK:</td>
								<td><html:text property="documentacao" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>Quanto&nbsp;deste&nbsp;valor&nbsp;é&nbsp;novo:</td>
								<td><html:text property="valorNovo" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>	
							<tr class="ODS_Detail_Line1">
								<td>OTA:</td>
								<td><html:text property="ota" styleClass="ODS_Select_Field_Size_15" readonly="true" disabled="true"></html:text></td>
								<td>Valor&nbsp;do&nbsp;investimento&nbsp;R$:</td>
								<td><html:text property="valorInvestimento" styleClass="ODS_Text_Field_Size_30" readonly="true" disabled="true"></html:text></td>
							</tr>
							<tr valign="top" class="ODS_Detail_Line1" >
								<td colspan="4">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<td width="100%"></td>
											<TD><html:button property="approvalBtn" value="Aprovar" onclick="submitAction('Aprovar');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Reprovar" onclick="submitAction('Reprovar');"></html:button></TD>
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



