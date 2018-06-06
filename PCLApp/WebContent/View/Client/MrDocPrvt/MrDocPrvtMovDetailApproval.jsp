<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page
	import="com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtMovDetailForm"%>


<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK href="<%=request.getContextPath()%>/Common/css/citi.css"
	rel="stylesheet" type="text/css">

<script language="javascript">
	function extraActions(action){}; 								
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="MrDocPrvt.MrDocPrvtMovDetail" />
	<jsp:param name="approvalControlNames" value="'approvedBtn','',''" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Memo de Risco com Pendência de Aprovação</TITLE>
</HEAD>

<body>
<html:form action="/MrDocPrvt.MrDocPrvtMovDetail.Approval.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Aprovação" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL"
		value="MrDocPrvt.MrDocPrvtMovDetail.Approval.Show"
		style="display:none"></html:text>

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Memo
						de Risco com Pendência de Aprovação</th>
					</tr>
				</thead>
				<tbody>
					<TR class="ODS_Detail_Line2">
						<TD>Número do Cliente</TD>
						<TD width="293">Nome do Cliente</TD>
					</TR>
					<TR class="ODS_Detail_Line2">
						<TD><html:text property="custNbrSrc"
							styleClass="ODS_Text_Field_Size_15" disabled="true"></html:text></TD>
						<td width="293"><html:text property="custFullNameText"
							styleClass="ODS_Text_Field_Size_60" disabled="true"></html:text></td>
					</TR>
					<TR class="ODS_Detail_Line2">
						<td width="25%">Conta Corrente</td>
						<td colspan="3">Conta CCI</td>
					</TR>
					<TR class="ODS_Detail_Line2">
						<TD width="25%"><html:text property="curAcctNbrSrc"
							styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></TD>
						<td colspan="3"><html:text property="invstCurAcctNbrSrc"
							styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></td>
					</TR>
					<TR class="ODS_Detail_Line2">
						<td width="25%">Código do MR</td>
						<td width="68%"></td>
						<TD colspan="2" width="28%"></TD>
					</TR>
					
					<TR class="ODS_Detail_Line2">
						<td width="25%"><html:text property="mrDocCode"
							styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text></td>
						<td width="68%"></td>
						<TD colspan="2" width="28%"></TD>
					</TR>
					<TR class="ODS_Detail_Line2">
						<td width="25%">Usuário da Última Atualização</td>
						<td colspan="2">Data/Hora da Última Atualização</td>
					</TR>
					<tr class="ODS_Detail_Line2">
						<td width="25%"><html:text styleClass="ODS_Text_Field_Size_20"
							property="lastUpdUserId" disabled="true"></html:text></td>
						<td colspan="2"><html:text styleClass="ODS_Text_Field_Size_20"
							property="lastUpdDate" disabled="true"></html:text></td>
					</TR>
				</tbody>
			</TABLE>

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
						<TH class="ODS_header" width="10%">Ação</TH>

						<%int auxIndex = 0;%>
						<logic:iterate name="MrDocPrvtMovDetailForm"
							property="fullNameTextArray" indexId="index" id="baseArray">

							<%MrDocPrvtMovDetailForm mrDocPrvtForm =
                	            (MrDocPrvtMovDetailForm) session.getAttribute("MrDocPrvtMovDetailForm");
                              String[] arrayCtcNbr = mrDocPrvtForm.getCtcNbrArray();
                              String indexCtcNbrArray = arrayCtcNbr[auxIndex++];%>
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="fullNameTextArray[" + index + "]"%>'
								id="fullNameTextArray" type="java.lang.String" />
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="fullName_2_TextArray[" + index + "]"%>'
								id="fullName_2_TextArray" type="java.lang.String" />
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="fullName_3_TextArray[" + index + "]"%>'
								id="fullName_3_TextArray" type="java.lang.String" />
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="phoneDddCodeArray[" + index + "]"%>'
								id="phoneDddCodeArray" type="java.lang.String" />
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="phoneNbrArray[" + index + "]"%>'
								id="phoneNbrArray" type="java.lang.String" />
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="phoneExtNbrArray[" + index + "]"%>'
								id="phoneExtNbrArray" type="java.lang.String" />
							<ods:CountStep counterName="index" counterStartIndex="0"
								sequenceRestartStep="2" stepIndexName="step">
								<logic:equal name="step" value="0">
									<tr class="ODS_line1">
								</logic:equal>
								<logic:equal name="step" value="1">
									<tr class="ODS_line2">
								</logic:equal>
							</ods:CountStep>
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="selectedItemsInGrid[" + index + "]"%>'
								id="selectedItemsInGrid" type="java.lang.String" />
							<bean:define name="MrDocPrvtMovDetailForm"
								property='<%="deletedItems[" + index + "]"%>' id="deletedItems"
								type="java.lang.String" />
								
						<bean:define name="MrDocPrvtMovDetailForm"
										property='<%="opernCodeArray[" + index + "]"%>'
										id="opernCodeArray" type="java.lang.String" />

							<td width="2%" align="center"><%=phoneDddCodeArray%></td>
							<td width="10%" align="center"><%=phoneNbrArray%></td>
							<td width="2%" align="center"><%=phoneExtNbrArray%></td>
							<td width="25%" align="center"><%=fullNameTextArray%></td>
							<td width="25%" align="center"><%=fullName_2_TextArray%></td>
							<td width="25%" align="center"><%=fullName_3_TextArray%></td>
							<td width="10%" align="center"><%=opernCodeArray%></td>

						</logic:iterate>
					</tr>
				</tbody>
			</table>
			</DIV>

			<table class="ODS_internalWidth" border="0">
				<TBODY>
					<TR>
						<TD width="100%"></TD>
						<TD><html:button property="approvedBtn" value="Aprovar"
							onclick="submitAction('approve');"></html:button></TD>
						<TD><html:button property="rejectBtn" value="Reprovar"
							onclick="submitAction('reprove','', true);"></html:button></TD>
						<TD><html:button property="backBtn" value="Voltar"
							onclick="submitAction('back');"></html:button></TD>
					</TR>
				</TBODY>
			</TABLE>
			</td>
		</tr>
		<jsp:include page="/View/Util/Footer.jsp" flush="true" />
	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>

<script language="javascript">
	disableApproveButtons("<bean:write name='MrDocPrvtMovDetailForm' property='lastUpdUserId'/>", "<bean:write name='MrDocPrvtMovDetailForm' property='opernCode'/>", "true");							
</script>