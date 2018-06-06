<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page
	import="com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtDetailForm"%>


<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">
<script language="javascript">
	function extraActions(action){}																	
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="MrDocPrvt.MrDocPrvtDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Memo de Risco</TITLE>
</HEAD>
<body>

<html:form action="/MrDocPrvt.MrDocPrvtDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<table class="ODS_mainTable" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>&nbsp;</td>
			<html:text property="backURL"	value="MrDocPrvt.MrDocPrvtDetail.Consult.Show" style="display:none"></html:text>
			<td>
				<table class="ODS_internalWidth" border="0">
					<thead><tr><th class="subtitle" scope="colgroup" colspan="3" height="52">Detalhe de Memo de Risco</th></tr></thead>
					<tr>
						<td>
						<TABLE class="ODS_internalWidth" border="0">
							<TBODY>
								<TR class="ODS_Detail_Line2">
									<TD >Número do Cliente</TD>
									<TD width="293">Nome do Cliente</TD>
								</TR>
								<TR class="ODS_Detail_Line2">
									<TD ><html:text property="custNbrSrc"
									styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text></TD>
									<td width="293"><html:text property="custFullNameText" styleClass="ODS_Text_Field_Size_60" disabled="true"></html:text></td>
								</TR>
								<TR class="ODS_Detail_Line2">
									<td width="25%">Conta Corrente</td>
									<td colspan="3">Conta CCI</td>
								</TR>
								<TR class="ODS_Detail_Line2">
									<TD width="25%"><html:text property="curAcctNbrSrc"
									styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></TD>
									<td colspan="3"><html:text property="invstCurAcctNbrSrc"	styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></td>
								</TR>
								<TR class="ODS_Detail_Line2">
									<td width="25%">Código do MR</td>
									<td width="68%"></td>
									<TD colspan="2" width="28%"></TD>
								</TR>
								<TR class="ODS_Detail_Line2">
									<td width="25%"><html:text property="mrDocPrvt"	styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></td>
									<td width="68%"></td>
									<TD colspan="2" width="28%">
										
									</TD>
								</TR>
							</TBODY>
						</table>
	
						<DIV class="ODS_DivGridVerticalScroll">
								<table class="ODS_internalWidth" border="0" id="grid">
									<TBODY>
										<tr class="fixed">											
											<TH class="ODS_header" width="2%">DDD</TH>
											<TH class="ODS_header" width="10%">Telefone</TH>
											<TH class="ODS_header" width="2%">Ramal</TH>
											<TH class="ODS_header" width="25%">Nome do Contato Principal</TH>
											<TH class="ODS_header" width="25%">Nome do Segundo Contato</TH>
											<TH class="ODS_header" width="25%">Nome do Terceiro Contato</TH>
											
											<%  int auxIndex = 0; %>
											<logic:iterate name="MrDocPrvtDetailForm" property="fullNameTextArray" indexId="index" id="baseArray">

												<%MrDocPrvtDetailForm mrDocPrvtForm = ( MrDocPrvtDetailForm ) session.getAttribute( "MrDocPrvtDetailForm" );
												    String[] arrayCtcNbr = mrDocPrvtForm.getCtcNbrArray();
												    String indexCtcNbrArray = arrayCtcNbr[ auxIndex++ ];
											    %>
											<bean:define name="MrDocPrvtDetailForm"	property='<%="fullNameTextArray[" + index + "]"%>' id="fullNameTextArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtDetailForm"	property='<%="fullName_2_TextArray[" + index + "]"%>' id="fullName_2_TextArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtDetailForm"	property='<%="fullName_3_TextArray[" + index + "]"%>' id="fullName_3_TextArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtDetailForm"	property='<%="phoneDddCodeArray[" + index + "]"%>' id="phoneDddCodeArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtDetailForm"	property='<%="phoneNbrArray[" + index + "]"%>' id="phoneNbrArray" type="java.lang.String" />
											<bean:define name="MrDocPrvtDetailForm"	property='<%="phoneExtNbrArray[" + index + "]"%>' id="phoneExtNbrArray" type="java.lang.String" />
												<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2">
													</logic:equal>
												</ods:CountStep>
												<bean:define name="MrDocPrvtDetailForm" property='<%="selectedItemsInGrid["+index+"]"%>' id="selectedItemsInGrid" type="java.lang.String" />
												<bean:define name="MrDocPrvtDetailForm" property='<%="deletedItems["+index+"]"%>' id="deletedItems" type="java.lang.String" />
												
												<td width="2%" align="center"><%=phoneDddCodeArray%></td>
												<td width="10%" align="center"><%=phoneNbrArray%></td>
												<td width="2%" align="center"><%=phoneExtNbrArray%></td>
												<td width="25%" align="center"><%=fullNameTextArray%></td>
												<td width="25%" align="center"><%=fullName_2_TextArray%></td>
												<td width="25%" align="center"><%=fullName_3_TextArray%></td>	
												
											</logic:iterate>
										</tr>
									</tbody>
								</table>
								</DIV>
						
						<TABLE class="ODS_internalWidth" border="0">
							<TBODY>
								<TR>
									<TD width="100%"></TD>
									<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
								</TR>
							</TBODY>
						</TABLE>
						<jsp:include page="/View/Util/Footer.jsp" flush="true" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

