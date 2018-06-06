<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
	
		<script language="javascript">
			function extraActions( action ){}
		 </script>		
	
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="CurAcctPrmntInstr.CurAcctPrmntInstrDetail" />
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Detalhe de Associação Conta Corrente X Instrução Permanente</TITLE>

	</HEAD>

	<body>
		<html:form action="/CurAcctPrmntInstr.CurAcctPrmntInstrDetail.Consult.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Documentos"/>
		    </jsp:include>

			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>			
		
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<html:text property="backURL" value="CurAcctPrmntInstr.CurAcctPrmntInstrDetail.Consult.Show" style="display:none"></html:text>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr><th class="subtitle" scope="colgroup" colspan="2">Detalhe de Associação Conta Corrente X Instrução Permanente</th></tr>
							</thead>
							<tr>
								<td>
									<table class="ODS_internalWidth"   border="0" cellspacing="0">
										<tbody>
											<tr class="ODS_line11" height="25"><td colspan="3">Conta Corrente:</td></tr>
											<tr>
						 						<td colspan="4"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1"  width="100%"></td>
											</tr>
											<tr class="ODS_Detail_Line1">
												<TD width="25%">Conta Corrente</TD>
												<TD width="30%">Número do Cliente</TD>
												<TD width="45%" colspan="2">Nome do Cliente</TD>
											</tr>
											<tr class="ODS_Detail_Line2">
												<TD width="25%"><html:text styleClass="ODS_Text_Field_Size_15" property="curAcctNbr" disabled="true" maxlength="15"></html:text></TD>
												<TD width="30%"><html:text styleClass="ODS_Text_Field_Size_15" property="custNbrSrc" disabled="true" maxlength="11"></html:text></TD>
												<html:hidden property="custNbrSrc"/>
												<TD width="45%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_60" property="custFullName" disabled="true" maxlength="60"></html:text></TD>
											</tr>
										</tbody>
									</table>

									<table class="ODS_internalWidth" border="0">
										<thead>
											<tr><th class="subtitle" scope="colgroup" colspan="3">Lista de IP's</th></tr>
										</thead>
										<tr>
											<td>
												<table class="ODS_internalWidth" border="0">
													<tbody>
														<tr class="fixed">
															<TH class="ODS_header" width="17%">Código</TH>
															<TH class="ODS_header" width="12%">Indicador CCI</TH>
															<TH class="ODS_header" width="21%">Número Conta CCI</TH>
														</tr>
														<%int rowIndex = 0;%>
														<logic:notEmpty name="CurAcctPrmntInstrDetailForm"	property="ipDocDomains">
															<logic:iterate name="CurAcctPrmntInstrDetailForm" 	property="ipDocDomains" indexId="index" id="row">
																<%
																	CurAcctPrmntInstrDetailForm curAcctDetailForm = (CurAcctPrmntInstrDetailForm) session.getAttribute("CurAcctPrmntInstrDetailForm");
																	String[] resultLine = curAcctDetailForm.getIpDocDomains()[rowIndex++];
																%>
																<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
																	<logic:equal name="step" value="0">
																		<tr class="ODS_line1">
																	</logic:equal>
																	<logic:equal name="step" value="1">
																		<tr class="ODS_line2">
																	</logic:equal>
																</ods:CountStep>
																<td class="centralized"><%=resultLine[0]%></td>
																<td class="centralized"><%=resultLine[2]%></td>
																<td class="centralized"><%=resultLine[3]%></td>
															</logic:iterate>
														</logic:notEmpty>
													</tbody>
												</TABLE>
											</td>
										</tr>
									</TABLE>

									<table class="ODS_internalWidth" border="0">
										<tbody>
											<TR>
												<TD width="100%">&nbsp;</TD>
												<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>	
											</TR>
										</tbody>
									</TABLE>
				
									<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
								</table>	
							</td>
						</tr>
				</table>	
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
