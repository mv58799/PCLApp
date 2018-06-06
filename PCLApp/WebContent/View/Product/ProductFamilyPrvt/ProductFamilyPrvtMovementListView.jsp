<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

		<script language="javascript">
			function extraActions(action){
			}; 																		
		</script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="ProductFamilyPrvt.ProductFamilyPrvtMovementList"/>
				<jsp:param name="gridId" value="gridTable"/>
				<jsp:param name="headerId" value="gridHeader"/>
				<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
				<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Consulta de Família com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProductFamilyPrvt.ProductFamilyPrvtMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProductFamilyPrvt.ProductFamilyPrvtMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Família com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="14%">Código</TD>
											<TD width="20%"><html:text property="prodFamlCodeSrc" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN',null,null)"></html:text></TD>
											<TD width="13%">&nbsp;&nbsp;Nome</TD>
											<TD width="25%"><html:text property="prodFamlNameSrc" styleClass="ODS_Text_Field_Size_40" maxlength="40"></html:text></TD>
											<TD width="33%"></TD>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>	
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="14%">Descrição</TD>
											<TD colspan="5"><html:text property="prodFamlTextSrc" styleClass="ODS_Text_Field_Size_70" maxlength="70"></html:text></TD>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>

										<tr class="ODS_Detail_line1">										
											<TD colspan="5">
												<table><tr>
													<td width="35%">Usuário de Última Atualização&nbsp;</td>
													<TD><html:text property="lastUpdUserIdSrc" styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text></TD>
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
											<TD width="6%"><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											<TD align="right"><html:button property="approvedBtn" value="Aprovação" disabled="true" onclick="submitAction('approve');"></html:button></TD>
											<TD align="right"><html:button property="alterBtn" value="Alterar" disabled="true" onclick="submitAction('update');"></html:button></TD>
											<TD align="left" width="44"><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedProdFamlCode" value="0" />
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
								<div class="ODS_DivGrid">
								<table id="gridTable" class="ODS_internalWidth" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header">&nbsp;</TH>
											<TH class="ODS_header" width="23%">Nome</TH>
											<TH class="ODS_header" width="36%">Descrição</TH>
											<TH class="ODS_header" width="7%">Código</TH>
											<TH class="ODS_header" width="13%">Usuário de Última Atualização</TH>
											<TH class="ODS_header" width="11%">Data/Hora de Última Atualização</TH>
											<TH class="ODS_header" width="7%">Ação</TH>
										</tr>
										<ods:DataSetRows name="ProductFamilyPrvtMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="bigDecimalByName(PROD_FAML_CODE)" id="selectedProdFamlCode" type="java.math.BigDecimal"></bean:define>
											<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserId" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
			 									<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedProdFamlCode.value='<%=selectedProdFamlCode%>';disableButtons(false);disableApproveButtons('<%= lastUpdUserId %>', '<%= opernCode %>','true');"/></td>
												<TD width="23%"><a href="#" onclick="javascript:selectedProdFamlCode.value='<%=selectedProdFamlCode%>';submitAction('approve');"><bean:write name="resultRow" property="stringByName(PROD_FAML_NAME)"/></a></td>
												<TD width="36%"><bean:write name="resultRow" property="stringByName(PROD_FAML_TEXT)"/></td>
												<TD width="7%" align="right"><bean:write name="resultRow" property="bigDecimalByName(PROD_FAML_CODE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
												<TD width="13%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
												<TD width="11%" align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="7%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>
											</tr>
										</ods:DataSetRows>
									</tbody>
									</div>
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
											 <TD align="left"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
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