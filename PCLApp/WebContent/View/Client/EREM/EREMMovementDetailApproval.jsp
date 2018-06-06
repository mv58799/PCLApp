<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.erem.form.EREMMovementDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions( action )
		{
			
		};
	 </script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="EREM.EREMMovementDetail"/>
		<jsp:param name="approvalControlNames" value="'approvedBtn','',''"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Detalhe de Associação ER x EM com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/EREM.EREMMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<html:text property="backURL" value="EREM.EREMMovementDetail.Approval.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Associação ER x EM com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD>Número do ER:</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD><html:text property="erNbr" styleClass="ODS_Text_Field_Size_30" disabled="true"></html:text></TD>
										</tr>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%"></TD>
											<TD><html:button property="approvedBtn" value="Aprovar" onclick="submitAction('approve');"></html:button></TD>
											<TD><html:button property="rejectBtn" value="Reprovar" onclick="submitAction('reprove', '', true);"></html:button></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">EM's Associados</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="fixed">
											<TH class="ODS_header" width="10%">Número do ER</TH>
											<TH class="ODS_header" width="10%">Número do EM</TH>
											<TH class="ODS_header" width="15%">Papel do Cliente no Relacionamento</TH>
											<TH class="ODS_header" width="25%">Nome do Cliente</TH>
											<TH class="ODS_header" width="15%">Usuário de Última Atualização</TH>
											<TH class="ODS_header" width="15%">Data/Hora de Última Atualização</TH>
											<TH class="ODS_header" width="10%">Ação</TH>
										</tr>

										<%int rowIndex = 0;%>
										<logic:notEmpty name="EREMMovementDetailForm"	property="erEmGrid">
											<logic:iterate name="EREMMovementDetailForm"
												property="erEmGrid" indexId="index" id="row">
												<%
												EREMMovementDetailForm eREMMovementDetailForm = (EREMMovementDetailForm) session.getAttribute("EREMMovementDetailForm");
												String[] resultLine = eREMMovementDetailForm.getErEmGrid()[rowIndex++];
												%>
												<ods:CountStep counterName="index" counterStartIndex="0"
													sequenceRestartStep="2" stepIndexName="step">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2">
													</logic:equal>
												</ods:CountStep>

												<td align="right" width="10%"><%=resultLine[EREMMovementDetailForm.COL_POS_ER_NBR]%></td>
												<td align="right" width="10%"><%=resultLine[EREMMovementDetailForm.COL_POS_EM_NBR]%></td>
												<td align="left" width="15%"><%=resultLine[EREMMovementDetailForm.COL_POS_ROLE_CUST_CODE]%></td>
												<td align="left" width="25%"><%=resultLine[EREMMovementDetailForm.COL_POS_CUST_FULL_NAME]%></td>
												<td align="left" width="15%"><%=resultLine[EREMMovementDetailForm.COL_POS_LAST_UPD_USER_ID]%></td>
												<td align="left" width="15%"><%=resultLine[EREMMovementDetailForm.COL_POS_LAST_UPD_DATE]%></td>
												<td align="left" width="10%"><%=resultLine[EREMMovementDetailForm.COL_POS_OPERN_CODE]%></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
			</table>
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

<script language="javascript">
	
	disableApproveButtons("<bean:write name='EREMMovementDetailForm' property='lastUpdUserId'/>","<bean:write name='EREMMovementDetailForm' property='opernCode'/>",'true');	
						
	function setSelectedKeys(er, em){
		document.forms[0].selectedErNbrInGrid.value = er;
		document.forms[0].selectedEmNbrInGrid.value = em;
	};
</script>	
