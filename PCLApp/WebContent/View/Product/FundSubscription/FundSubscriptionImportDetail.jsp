<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.citibank.ods.modules.product.fundsubscription.form.FundSubscriptionImportDetailForm"%>
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
		<TITLE> Importação </TITLE>
		
		<script>
		function extraActions(action)
			{
			};
			
		 </script>
		 <jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="FundSubscriptionImport.FundSubscriptionImportDetail"/>
				<jsp:param name="mandatoryControlNames" value="'file'"/>
				<jsp:param name="mandatoryControlLabels" value="'Selecione o arquivo com a base de protocolos'"/>
				<jsp:param name="fieldsWithMask" value="[]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/FundSubscriptionImport.FundSubscriptionImportDetail.Insert.Execute.do"  enctype="multipart/form-data">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Subscrição"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="FundSubscriptionImport.FundSubscriptionImportDetail.Insert.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" >
				<tr height="100%" valign="top">
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="2">Importação</th>
								</tr>
							</thead>
							<tbody>
							<tr class="ODS_Detail_Line1">
								<td align="right">Selecione&nbsp;o&nbsp;arquivo&nbsp;com&nbsp;a&nbsp;base&nbsp;de&nbsp;protocolos:</td>
								<TD><html:file styleClass="ODS_Text_Field_Size_70" property="file"></html:file></TD>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td align="right">Tipo&nbsp;de&nbsp;produto:</td>
								<TD>
									<html:select property="productType">
										<html:option value="F">Fundos Imobiliários</html:option>
										<html:option value="O">Outros</html:option>
									</html:select>
									
								</TD>
							</tr>
							
							<tr valign="top" class="ODS_Detail_Line1" >
								<td colspan="2">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="importBtn" value="Importar" onclick="submitAction('Import', true, true);"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table border="0">
										<thead>
											<tr>
												<th class="subtitle" scope="colgroup" colspan="3">Histórico de Importação</th>
											</tr>
										</thead>
										<tr>
											<td width = "100%">
											<DIV class="ODS_DivGridVerticalScroll">
											
											<table id="gridTable" border="0">
												<thead>
													<tr id="gridHeader" class="fixed">
														<TH class="ODS_header" width="15%">Tipo&nbsp;de&nbsp;produto</TH>
														<TH class="ODS_header" width="10%">Código</TH>
														<TH class="ODS_header" width="15%">Data&nbsp;da&nbsp;Importação</TH>
														<TH class="ODS_header" width="30%">Nome&nbsp;do&nbsp;Arquivo</TH>
														<TH class="ODS_header" width="20%">Quantidade&nbsp;de&nbsp;Registros</TH>
													</tr>
												</thead>
												<tbody>
												
												
												<logic:notEmpty property="history"  scope="session"  name="FundSubscriptionImportDetailForm">
												<logic:iterate id="history" scope="session" property="history" name="FundSubscriptionImportDetailForm" indexId="index">
												<% String classLine="";
												   if((index.intValue() % 2)==0) 
												      	classLine="ODS_line2";
											      	else
											      		classLine="ODS_line1";
												%>
												<tr id="gridHeader" class="<%=classLine %>">
														<TD><bean:write name="history" property="tipoProduto" /></TD>
														<TD><bean:write name="history" property="codigo" /></TD>
														<TD><bean:write name="history" property="dataImportacao" /></TD>
														<TD><bean:write name="history" property="nomeArquivo" /></TD>
														<TD align="center"><bean:write name="history" property="registros" /></TD>
													</tr>
												</logic:iterate>
												</logic:notEmpty>
												</tbody>
											</table>
											</DIV>
											</td>
										</tr>
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



