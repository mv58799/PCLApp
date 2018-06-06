<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.citibank.ods.common.action.BaseAction"%>
<%@page import="com.citibank.ods.common.user.User"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.form.BaseForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Código do Fundo </TITLE>
		
		<script>
		function extraActions(action)
			{
			};
			
		function alterar(cd, name) {
			document.forms[0].codigo.value = cd;
			document.forms[0].nome.value = name;
			document.forms[0].action= 'FundSubscription.FundSubscriptionFundCodeDetail.Update.Show';
			document.forms[0].submit();
		}
		
		function search(){
			document.forms[0].action= 'FundSubscription.FundSubscriptionFundCodeList.List.Execute';
			document.forms[0].submit();
		}
		
		function limpar() {
			var inputs = document.getElementsByTagName("input");   
			for(i = 0; i < inputs.length; i++) {
				if (inputs[i].type == "text") {
					inputs[i].value = "";
				}
			}
			
			
			
			var gridTable = document.getElementById("gridTable");
			var grids = gridTable.rows.length;
			for (var x=grids-1; x>2; x--) {
				gridTable.deleteRow(x);
			}
			
			document.getElementById("encontrados").deleteCell();
		}
		 </script>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/FundSubscription.FundSubscriptionFundCodeList.List.Show.Clear.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Termo de Adesão"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="FundSubscription.FundSubscriptionFundCode.List.Show.Clear" style="display:none"></html:text> 
			<input type="hidden" name="codigo">
			<input type="hidden" name="nome">
			<table class="ODS_mainTable" >
				<tr height="100%" valign="top">
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="2">Atualizar Código de Fundo</th>
								</tr>
							</thead>
							<tbody>
							<tr class="ODS_Detail_Line1">
								<td width="1">Nome&nbsp;o&nbsp;do&nbsp;fundo:</td>
								<TD><html:text styleClass="ODS_Text_Field_Size_70" property="nomeFundo"></html:text></TD>
							</tr>
							<tr valign="top" class="ODS_Detail_Line1" >
								<td></td>
								<td>
								<html:radio property="fundosComCodigo" value="true">Incluir fundos com código</html:radio><br>
								<html:radio property="fundosComCodigo" value="false">Somente fundos sem código</html:radio>
							</td>
							<tr valign="top" class="ODS_Detail_Line1" >
								<td colspan="2">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="listBtn" value="Consultar" onclick="javascript:search();"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="javascript:limpar();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table class="ODS_internalWidth" border="0" id ="gridTable">
										<thead>
											<tr>
												<th class="subtitle" scope="colgroup" colspan="3">Resultado da consulta</th>
											</tr>
										</thead>
									<tbody>
											<tr class="ODS_Detail_Line1" id="encontrados">
												<td colspan="3">
													<logic:notEmpty property="resultado" name="FundSubscriptionFundCodeListForm">
													<bean:size property="resultado" name="FundSubscriptionFundCodeListForm" id="itensEncontrados"/>
													<%=itensEncontrados %> Itens Encontrado(s)
													</logic:notEmpty>
											  	</td>
											  </tr>
											  <tr id="gridHeader" class="fixed">
														<th class="ODS_header" width="30px">&nbsp;</th>
														<th class="ODS_header" width="30%">Código</th>
														<th class="ODS_header" width="70%">Nome&nbsp;do&nbsp;Fundo</th>
											  </tr>
											  <logic:notEmpty property="resultado" name="FundSubscriptionFundCodeListForm">
											  <logic:iterate id="resultado" property="resultado" name="FundSubscriptionFundCodeListForm" indexId="index">
												<% String classLine="";
												   if((index.intValue() % 2)==0) 
												      	classLine="ODS_line2";
											      	else
											      		classLine="ODS_line1";
												%>
												<tr id="gridId" class="<%=classLine %>">
														<TD><a href="javascript:alterar('<bean:write name="resultado" property="codigo"/>', '<bean:write name="resultado" property="nome" />')">
												  			<img src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0">
												  			</a>
												  		</TD>
														<TD><bean:write name="resultado" property="codigo"/></TD>
														<TD><bean:write name="resultado" property="nome" /></TD>
													</tr>
												</logic:iterate>
											  </logic:notEmpty>
										</tbody>										
									</table>
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



