<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK href="<%= request.getContextPath() %>/Common/css/citi.css"
	rel="stylesheet" type="text/css">

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName"
		value="KnowledgeExperience.KnowledgeExperienceDetail" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="null" />
	<jsp:param name="searchInputFields" value="'selectedClientNumber','selectedClientNameText'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<script language="javascript">

	function extraActions(action){
	}																	

</script>

<TITLE>Consulta de Question&aacute;rio K&amp;E</TITLE>
</HEAD>

<body>
<html:form
	action="/KnowledgeExperience.KnowledgeExperienceDetail.Consult.Show.do">

	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Clientes" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<html:text property="backURL"
		value="KnowledgeExperience.KnowledgeExperienceList.List.Show"
		style="display:none"></html:text>

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>

			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">
							Detalhe de Question&aacute;rio K&amp;E
						</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr class="ODS_line11">
								<td colspan="3">Dados Gerais:</td>
							</tr>

							<tr>
								<td colspan="4"><img
									src='<%=request.getContextPath()%>/Common/image/20grey1.gif'
									width="131%" height="1"></td>
							</tr>

							<tr>
								<td colspan="4">&nbsp;</td>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">
									Número do Cliente:
								</TD>
								<td colspan="3">
									<bean:write name="KnowledgeExperienceDetailForm" property="selectedClientNumber" />
								</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%">
									Nome do Cliente:
								</td>
								<TD colspan="3">
									<bean:write name="KnowledgeExperienceDetailForm" property="selectedClientNameText" />
								</TD>
							</tr>

						</tbody>
					</TABLE>
					</td>
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
						<th class="subtitle" scope="colgroup" colspan="3">Question&aacute;rio K&amp;E</th>
					</tr>
				</thead>
				<tr>
					<td>

					<DIV class="ODS_DivGridVerticalScroll">
					<table id="gridTable" border="0">
						<tbody>
						
							<tr id="gridHeader" class="fixed">
								<TH class="ODS_header" width="600px">Produto</TH>
								<TH class="ODS_header" width="200px">Resposta</TH>
							</tr>
							
							<ods:DataSetRows name="KnowledgeExperienceDetailForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
								<logic:equal name="step" value="0">
									<tr class="ODS_Line1">
								</logic:equal>
								<logic:equal name="step" value="1">
									<tr class="ODS_Line2">
								</logic:equal>
										<td width="600px">
											<bean:write name="resultRow" property="stringByName(PRODUTO)"/>
										</td>
										<td width="200px">
											<bean:write name="resultRow" property="stringByName(RESPOSTA)"/>
										</td>
									</tr>
							</ods:DataSetRows>
							
						</tbody>
					</TABLE>
					</DIV>
				</tr>
			</TABLE>
			</td>
		</tr>

		<tr> 
			<td>&nbsp;</td>		
			<td>
				<TABLE border="0">
					<TR>
						<td width="100%"></td>
						<td>
							<input type="button" value="Voltar" onclick="submitAction('back');">
						</td>
					</TR>
				</TABLE>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</td>
		</tr>

	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>
