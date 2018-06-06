 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<script language="javascript">
			function extraActions(action){};
		 </script>
		
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="ProdQlfyPrvt.ProdQlfyPrvtMovementList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'','prodQlfyCodeSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'','Código da Qualificação'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Consulta de Qualificação com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdQlfyPrvt.ProdQlfyPrvtMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:hidden property="selectedProdQlfyVode" value="0"/>
			<html:text property="backURL" value="ProdQlfyPrvt.ProdQlfyPrvtMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Qualificação com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">

											<TD width="10%">Código</TD>
											<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_5" property="prodQlfyCodeSrc" maxlength="4" onkeyup="MaskFieldPress('CHAR','NNNN',null,null)"></html:text></TD>
											<TD width="16%">&nbsp;&nbsp;Descrição</TD>
											<TD width="23%"><html:text styleClass="ODS_Text_Field_Size_50" property="prodQlfyTextSrc" maxlength="40"></html:text></TD>
											<TD width="40%">&nbsp;</TD>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD colspan="5">
												<table><tr>
													<td width="35%">Usuário de Última Atualização&nbsp;</td>
													<TD><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserIdSrc" maxlength="20"></html:text></TD>
												</tr></table>
											</TD>	
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
											<TD width="100%">&nbsp;</TD>
											 <TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											 <TD><html:button property="approvedBtn" value="Aprovação" disabled="true"  onclick="submitAction('approve');"></html:button></TD>
											 <TD><html:button property="alterBtn" value="Alterar" disabled="true" onclick="submitAction('update');"></html:button></TD>
											 <TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedProdQlfyCode" value="0" />
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table id="gridTable" class="ODS_internalWidth" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header">&nbsp;</TH>
											<TH class="ODS_header" width="42%">Descrição</TH>
											<TH class="ODS_header" width="7%">Código</TH>
											<TH class="ODS_header" width="20%">Usuário de Última Atualização</TH>
											<TH class="ODS_header" width="17%">Data/Hora de Última Atualização</TH>
											<TH class="ODS_header" width="10%">Ação</TH>
										</tr>
										<ods:DataSetRows name="ProdQlfyPrvtMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(PROD_QLFY_CODE)" id="selectedProdQlfyCode" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserId" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
			 									<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedProdQlfyCode.value='<%= selectedProdQlfyCode %>'; disableButtons(false);disableApproveButtons('<%= lastUpdUserId %>','<%= opernCode %>',true);"/></td>
												<TD width="42%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedProdQlfyCode.value='<%= selectedProdQlfyCode %>';submitAction('approve');"><bean:write name="resultRow" property="stringByName(PROD_QLFY_TEXT)"/></a></td>
												<TD width="7%" align="right"><bean:write name="resultRow" property="bigDecimalByName(PROD_QLFY_CODE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
												<TD width="20%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
												<TD width="17%" align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="10%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>
											</tr>

										</ods:DataSetRows>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>

						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
    										 <TD width="100%">&nbsp;</TD>
											 <TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
			</table>			
		</html:form>
	</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>



