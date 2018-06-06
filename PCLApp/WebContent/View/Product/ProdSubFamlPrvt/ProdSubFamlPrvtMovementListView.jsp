
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

		<script language="javascript">
			function extraActions(action){}; 																		
		</script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="ProdSubFamlPrvt.ProdSubFamlPrvtMovementList"/>
				<jsp:param name="gridId" value="gridTable"/>
				<jsp:param name="headerId" value="gridHeader"/>
				<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
				<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
				<jsp:param name="mandatoryControlNames" value="'prodSubFamlCodeSrc'"/>
				<jsp:param name="mandatoryControlLabels" value="'Código da Sub-Família'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Consulta de Sub-Família com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdSubFamlPrvt.ProdSubFamlPrvtMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdSubFamlPrvt.ProdSubFamlPrvtMovementList.List.Show" style="display:none"></html:text>

			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" width="100%" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="5" width="100%">Consulta de Sub-Família com Pendência de Aprovação</th></tr></thead>
							<tbody>
								<tr class="ODS_Detail_line1">
									<TD width="14%">Código</TD>
									<TD width="20%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodSubFamlCodeSrc" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN',null,null)"></html:text></TD>
									<TD width="13%">&nbsp;&nbsp;Nome</TD>
									<TD width="25%"><html:text styleClass="ODS_Text_Field_Size_40" property="prodSubFamlNameSrc" maxlength="40"></html:text></TD>
									<TD width="33%">&nbsp;</TD>
								</tr>
								<tr class="ODS_Detail_line2">
									<TD width="14%">Descrição</TD>
									<TD colspan="4"><html:text styleClass="ODS_Text_Field_Size_70" property="prodSubFamlTextSrc" maxlength="70"></html:text></TD>
								</tr>
								<tr class="ODS_Detail_line1">
									<TD colspan="5">
										<table><tr>
											<td width="35%">Usuário de Última Atualização&nbsp;</td>
											<TD><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserIdSrc" maxlength="20"></html:text></td>
										</tr></table>
									</TD>
								</tr>
							</tbody>
						</TABLE>
	
						<table class="ODS_internalWidth" border="0">
							<TBODY>
								<TR>
									<TD width="100%">&nbsp;</TD>
									<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
									<TD><html:button property="approvedBtn" value="Aprovação" disabled="true" onclick="submitAction('approve');"></html:button></TD>
									<TD><html:button property="alterBtn" value="Alterar" disabled="true" onclick="submitAction('update');"></html:button></TD>
									<TD><html:button property="clearBtn" value="Limpar" onclick="javascript:clearPage();"></html:button></TD>
								</TR>
							</TBODY>
						</TABLE>

						<html:hidden property="selectedProdSubFamlCode" value="0" />

						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead><tr>
							<tr>
								<td>
									<DIV class="ODS_DivGrid">
										<table id="gridTable" border="0" width="1200px">
											<tbody>
												<tr id="gridHeader" class="fixed">
													<TH class="ODS_header">&nbsp;</TH>
													<TH class="ODS_header" width="18%">Nome</TH>
													<TH class="ODS_header" width="30%">Descrição</TH>
													<TH class="ODS_header" width="7%">Código</TH>
													<TH class="ODS_header" width="18%">Nome da Família</TH>
													<TH class="ODS_header" width="10%">Usuário de Última Atualização</TH>
													<TH class="ODS_header" width="8%">Data/Hora de Última Atualização</TH>
													<TH class="ODS_header" width="6%">Ação</TH>
												</tr>
												<ods:DataSetRows name="ProdSubFamlPrvtMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2"> 
													</logic:equal>
													<bean:define name="resultRow" property="stringByName(PROD_SUB_FAML_CODE)" id="selectedProdSubFamlCode" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserIdSrc" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
			 											<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedProdSubFamlCode.value='<%= selectedProdSubFamlCode %>'; disableButtons(false);disableApproveButtons('<%= lastUpdUserIdSrc %>','<%= opernCode %>',true);"/></td>
														<TD width="18%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedProdSubFamlCode.value='<%= selectedProdSubFamlCode %>';submitAction('approve');"><bean:write name="resultRow" property="stringByName(PROD_SUB_FAML_NAME)"/></a></td>
														<TD width="30%"><bean:write name="resultRow" property="stringByName(PROD_SUB_FAML_TEXT)"/></td>
														<TD width="7%" align="right"><bean:write name="resultRow" property="bigDecimalByName(PROD_SUB_FAML_CODE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
														<TD width="18%"><bean:write name="resultRow" property="stringByName(PROD_FAML_NAME)"/></td>
														<TD width="10%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
														<TD width="8%" align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
														<TD width="6%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>
												</ods:DataSetRows>
											</tbody>
										</TABLE>
									</DIV>
								</td>
							</tr>
						</table>

						<table class="ODS_internalWidth" border="0">
							<TBODY>
								<TR>
									<TD width="100%">&nbsp;</TD>
									<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
								</TR>
							</TBODY>
						</TABLE>
						<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
