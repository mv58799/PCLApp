
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

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
	function extraActions(action){}; 																		
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="EREM.EREMHistoryDetail" />
	<jsp:param name="gridId" value="gridTable"/>
	<jsp:param name="headerId" value="gridHeader"/>
	<jsp:param name="controlNames" value="'detailBtn'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Detalhe Histórico de Associação ER x EM</TITLE>
	</HEAD>

	<body>
		<html:form action="/EREM.EREMHistoryDetail.Consult.Show.do">

	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Clientes" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<html:text property="backURL" value="EREM.EREMHistoryDetail.Consult.Show" style="display:none"></html:text>

			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe Histórico de Associação ER x EM</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD>Número do ER</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD><html:text disabled="true" property="erNbr"></html:text></TD>
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
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Lista de Detalhe Histórico</th>
								</tr>
							</thead>
							<tr>
								<td>
								<div class="ODSdivGrid">
								<table id="gridTable" class="ODS_internalWidth" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">

											<TH class="ODS_header" width="10%">Número do ER</TH>
											<TH class="ODS_header" width="10%">Número do EM</TH>
											<TH class="ODS_header" width="10%">Papel do Cliente no Relacionamento</TH>
											<TH class="ODS_header" width="20%">Nome do Cliente</TH>
											<TH class="ODS_header" width="15%">Usuário da Última Alteração</TH>
											<TH class="ODS_header" width="15%">Data/Hora da Última Alteração</TH>
											<TH class="ODS_header" width="15%">Usuário da Aprovação</TH>
											<TH class="ODS_header" width="15%">Data/Hora da Aprovação</TH>
											<TH class="ODS_header" width="10%">Status</TH>
										</tr>

										<ods:DataSetRows name="EREMHistoryDetailForm" property="historyResults" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
												<TD width="10%" align="left"><bean:write name="resultRow" property="stringByName(ER_NBR)"/></td>
												<TD width="10%" align="left"><bean:write name="resultRow" property="stringByName(EM_NBR)"/></td>
												<TD width="10%" align="left"><bean:write name="resultRow" property="stringByName(ROLE_CUST_TEXT)"/></td>
												<TD width="15%"><bean:write name="resultRow" property="stringByName(CUST_FULL_NAME_TEXT)"/></td>
												<TD width="15%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
												<TD width="15%"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="15%"><bean:write name="resultRow" property="stringByName(LAST_AUTH_USER_ID)"/></td>
												<TD width="15%"><bean:write name="resultRow" property="dateByName(LAST_AUTH_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="10%"><bean:write name="resultRow" property="stringByName(REC_STAT_TEXT)"/></td>
											</tr>
										</ods:DataSetRows>
									</tbody>
								</TABLE>
								</div>
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
											<TD width="100%" align="right"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
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
