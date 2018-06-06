<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.citibank.ods.modules.product.fundsubscription.form.FundSubscriptionListForm"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.form.BaseForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Consulta </TITLE>
		
		<script>
		function extraActions(action)
			{
			
			};
		function edit(protocol){
			document.forms[0].selectedCode.value = "";
			document.forms[0].protocolo.value = protocol;
			document.forms[0].action= 'FundSubscription.FundSubscriptionDetail.Update.Show';
			document.forms[0].submit();
		}
		
		function novo() {
			document.forms[0].action= 'FundSubscription.FundSubscriptionDetail.Insert.Show.Clear';
			document.forms[0].clear.value = true;
			document.forms[0].submit();
		}
		
		function consultar(){
			
			document.forms[0].action= 'FundSubscription.FundSubscriptionList.List.Execute';
			document.forms[0].submit();
		}
		
		function del(){
			document.forms[0].protocolo.value = getCheckedRadioId("selectedFlag");
			document.forms[0].action= 'FundSubscription.FundSubscriptionFundCodeDetail.Delete.Execute';
			document.forms[0].submit();
			
		}
		
		function getCheckedRadioId(name) {
		    var elements = document.getElementsByName(name);

		    for (var i=0, len=elements.length; i<len; ++i)
		        if (elements[i].checked) return elements[i].value;
		}
		
		function limpar() {
			var inputs = document.getElementsByTagName("input");   
			for(var i = 0; i < inputs.length; i++) {
				if (inputs[i].type == "text") {
					inputs[i].value = "";
				}
			}
			
			document.getElementsByName("tipoProduto")[0].value = "";
			
			
			var gridTable = document.getElementById("gridTable");
			var grids = gridTable.rows.length;
			for (var x=grids-1; x>2; x--) {
				gridTable.deleteRow(x);
			}
			
			document.getElementById("encontrados").deleteCell();
		}
		 </script>
			<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="FundSubscription.FundSubscriptionList"/>
				<jsp:param name="gridId" value="gridId"/>
				<jsp:param name="headerId" value="gridHeader"/>
			</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/FundSubscription.FundSubscriptionList.List.Show.do">
		<input type="hidden" name="protocolo" />
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Termo de Adesão"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="FundSubscription.FundSubscriptionConsult.List.Show" style="display:none"></html:text> 
			<html:hidden property="selectedCode"/>
			<html:hidden property="clear" value="false"/>
			<table class="ODS_mainTable" >
				<tr height="100%" valign="top">
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="2">Consulta de Subscrição</th>
								</tr>
							</thead>
							<tbody>
							<tr class="ODS_Detail_Line1">
								<td align="right">Nome&nbsp;do&nbsp;cliente:</td>
								<TD><html:text styleClass="ODS_Text_Field_Size_70" property="nomeCliente"></html:text></TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td align="right">Número da Conta:</td>
								<TD><html:text styleClass="ODS_Text_Field_Size_70" property="numeroConta"></html:text></TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td align="right">Tipo&nbsp;de&nbsp;produto:</td>
								<TD><html:select property="tipoProduto">
										<html:option value=""></html:option>
										<html:option value="F">Fundos Imobiliários</html:option>
										<html:option value="O">Outros</html:option>
									</html:select>
								</TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td align="right">Nome&nbsp;do&nbsp;Fundo/Produto:</td>
								<TD><html:text styleClass="ODS_Text_Field_Size_70" property="nomeProduto"></html:text></TD>
							</tr>
							
							<tr valign="top" class="ODS_Detail_Line1" >
								<td colspan="2">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<td width="100%"></td>
											<TD><html:button property="newBtn" value="Novo" onclick="javascript:novo();"></html:button></TD>
											<TD><html:button property="listBtn" value="Consultar" onclick="consultar();"></html:button></TD>
											<TD><html:button property="deleteBtn" value="Excluir" onclick="del();"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="limpar();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
								</td>
							</tr>
							<tr valign="top" class="ODS_Detail_Line1" >
								<td colspan="2">
											  <logic:notEmpty property="resultado" name="FundSubscriptionListForm">
											  
											  <%
											  FundSubscriptionListForm f = (FundSubscriptionListForm)request.getSession().getAttribute("FundSubscriptionListForm");
											  request.setAttribute("listResultado", f.getResultado());
											  %>
											  <div></div>
											  	<display:table name="listResultado" uid="vProd" id="resultado" pagesize="10" export="false" class="listaPaginada"  
 											    requestURI="/FrontController/FundSubscription.FundSubscriptionList.List.Execute?ordenar=1&" style="width:100%" sort="list" >
												
												<bean:define name="resultado" property="protocolo"  id="protocolo" type="java.lang.String"></bean:define>
												
												  <display:column title="" media="html" >
												  		<input type="radio" name="selectedFlag" value="<%=protocolo %>" />
												  </display:column> 
												  
												  <display:column title="" media="html" >
												  		<a href="javascript:edit('<%=protocolo %>')">
											  				<img src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0">
											  			</a>
												  </display:column>
													<display:column title="Nº&nbsp;Protocolo" media="html" property="protocolo" >
													</display:column>
													<display:column title="Código&nbsp;do&nbsp;Fundo" media="html" property="codigoFundo" >
													</display:column>
													<display:column title="Nome&nbsp;do&nbsp;Fundo/Produto" media="html" property="nomeProduto" >
													</display:column>
													<display:column title="Nome&nbsp;do&nbsp;Cliente" media="html" property="nomeCliente" >
													</display:column>
													<display:column title="Evento" media="html" property="evento" >
													</display:column>
												</display:table> 
											  </logic:notEmpty>
											  </div>
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



