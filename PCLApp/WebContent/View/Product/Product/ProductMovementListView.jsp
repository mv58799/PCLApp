

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
		<TITLE> Consulta de Produtos com Pendência de Aprovação </TITLE>
		
		<script language="javascript">
				function extraActions(action){};
				
				function setSelectedKeys(prodCode, sysCode, sysSegCode , prodName, prodText)
				{
					document.forms[0].selectedProdCode.value = prodCode;
					document.forms[0].selectedSysCode.value = sysCode;
					document.forms[0].selectedSysSegCode.value = sysSegCode;
				};
		 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="Product.ProductMovementList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/Product.ProductMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
		<html:text property="backURL" value="Product.ProductMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="6">Consulta de Produtos com Pendência de Aprovação</th></tr></thead>
								<tbody>
									<tr class="ODS_Detail_Line1">
										<TD width="13%">Código</TD>
										<TD colspan="5">
										<table class="ODS_internalWidth" border="0">
											<tr class="ODS_Detail_Line1">
												<TD><html:text styleClass="ODS_Text_Field_Size_10" property="prodCodeSrc" maxlength="10"></html:text></TD>
												<td>&nbsp;&nbsp;Nome</td>
												<td><html:text styleClass="ODS_Text_Field_Size_40" property="prodNameSrc" maxlength="20"></html:text></td>											
														<TD>&nbsp;</TD>
														<TD>&nbsp;</TD>	
														<TD>&nbsp;</TD>
														<TD>&nbsp;</TD>
														<TD>&nbsp;</TD>
												</tr>
											</table>
										 </TD>
									</tr>
									<tr class="ODS_Detail_Line2">
										<td width="13%">Qualificação</td>
										<td width="25%">
											<html:select styleClass="ODS_Text_Field_Size_30" property="prodQlfyCodeSrc">
												<html:option value=""></html:option>
												<html:options property="prodQlfyCodeDomain.columnValuesByName(PROD_QLFY_CODE)" labelProperty="prodQlfyCodeDomain.columnValuesByName(PROD_QLFY_TEXT)" />
											</html:select>
										</td>
										<TD width="13%">&nbsp;&nbsp;Sub-família</TD>
										<td width="25%">
											<html:select styleClass="ODS_Text_Field_Size_30" property="prodSubFamlCodeSrc">
												<html:option value=""></html:option>
												<html:options property="prodSubFamlCodeDomain.columnValuesByName(PROD_SUB_FAML_CODE)" labelProperty="prodSubFamlCodeDomain.columnValuesByName(PROD_SUB_FAML_NAME)" />
											</html:select>
										</td>
										<TD width="7%">&nbsp;</TD>
										<TD width="7%">&nbsp;</TD>
									</tr>
									<tr class="ODS_Detail_Line1">
										<td width="13%">Cat. de Risco</td>
										<td width="25%">
											<html:select styleClass="ODS_Text_Field_Size_30" property="prodRiskCatCodeSrc">
												<html:option value=""></html:option>
												<html:options property="prodRiskCodeDomain.columnValuesByName(PROD_INVST_RISK_CODEPROD_INVST_RISK_CODEPROD_INVST_RISK_CODEPROD_INVST_RISK_CODEPROD_INVST_RISK_CODEPROD_INVST_RISK_CODE)" labelProperty="prodRiskCodeDomain.columnValuesByName(PROD_INVST_RISK_TEXT)" />
											</html:select>
										</td>	
										<TD width="13%">&nbsp;&nbsp;Família</TD>
										<td width="25%">
												<html:select styleClass="ODS_Text_Field_Size_30" property="prodFamlCodeSrc">
													<html:option value=""></html:option>
													<html:options property="prodFamlCodeDomain.columnValuesByName(PROD_FAML_CODE)" labelProperty="prodFamlCodeDomain.columnValuesByName(PROD_FAML_NAME)" />
												</html:select>
										</td>		
										<TD width="7%">&nbsp;</TD>
										<TD width="7%">&nbsp;</TD>
									</tr>
									<tr class="ODS_Detail_Line1">
										<TD colspan="6">
											<table width="100%">
												<tr>
													<td width="23%">Usuário de Última Atualização</TD>
													<TD><html:text property="lastUpdUserIdSrc" maxlength="20"></html:text></TD>
												<tr>
											</table>
										</TD>
									</tr>
								</tbody>
							</table>
	
							<table class="ODS_internalWidth" border="0">
								<tbody>
									<TR>
										<TD width="100%"></TD>
										<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
										<TD><html:button property="approvedBtn" value="Aprovação" onclick="submitAction('approve');" disabled="true"></html:button></TD>
										<TD><html:button property="alterBtn" value="Alterar" onclick="submitAction('update');" disabled="true"></html:button></TD>
										<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
									</TR>
								</tbody>
							</TABLE>

							<html:hidden property="selectedProdCode" value="0" />
							<html:hidden property="selectedSysCode" value="0" />
							<html:hidden property="selectedSysSegCode" value="0" />

							<table border="0">
								<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead>
								<tr>
									<td>
									<DIV class=ODS_DivGrid>
										<table id="gridTable" width="1200px" border="0">
												<tbody>
													<tr id="gridHeader" class="fixed">
														<TH class="ODS_header" width="2%" >&nbsp;</TH>
														<TH class="ODS_header" width="20%">Nome</TH>
														<TH class="ODS_header" width="20%">Descrição</TH>
														<TH class="ODS_header" width="5%">Código</TH>
														<TH class="ODS_header" width="11%">Família</TH>
														<TH class="ODS_header" width="11%">Sub-família</TH>
														<TH class="ODS_header" width="5%">Segmento</TH>
														<TH class="ODS_header" width="10%">Usuário de Última Atualização</TH>
														<TH class="ODS_header" width="11%">Data/Hora de Última Atualização</TH>
														<TH class="ODS_header" width="5%">Ação</TH>
												</tr>
												<ods:DataSetRows name="ProductMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2"> 
													</logic:equal>
													<bean:define name="resultRow" property="stringByName(PROD_CODE)" id="selectedProdCode" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(SYS_CODE)"  id="selectedSysCode" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="bigDecimalByName(SYS_SEG_CODE)" id="selectedSysSegCode" type="java.math.BigDecimal"></bean:define>
													<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserIdSrc" type="java.lang.String"></bean:define>
													<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>

				 									<TD width="2%"><input type="radio" class="radio" name="selection" onclick="javascript:setSelectedKeys('<%= selectedProdCode %>', '<%= selectedSysCode %>', '<%= selectedSysSegCode %>' ); disableButtons(false); disableApproveButtons('<%= lastUpdUserIdSrc %>','<%= opernCode %>',true);"/></td>
													<TD width="20%"><a class="ODS_CursorHand" href="#" onclick="javascript:setSelectedKeys('<%= selectedProdCode %>', '<%= selectedSysCode %>', '<%= selectedSysSegCode %>' );submitAction('approve'); " ><bean:write name="resultRow" property="StringByName(PROD_NAME)"/></a></td>
													<TD width="20%"><bean:write name="resultRow" property="StringByName(PROD_TEXT)"/></td>
													<TD width="5%" align="right"><bean:write name="resultRow" property="StringByName(PROD_CODE)"/></td>
													<TD width="11%"><bean:write name="resultRow" property="stringByName(PROD_FAML_NAME)"/></td>
													<TD width="11%"><bean:write name="resultRow" property="stringByName(PROD_SUB_FAML_NAME)"/></td>
													<TD width="5%" align= "right"><bean:write name="resultRow" property="bigDecimalByName(SYS_SEG_CODE)"/></td>
													<TD width="10%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
													<TD width="11%" align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
													<TD width="5%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>
												</ods:DataSetRows>
											</tbody>
										</TABLE>
									</DIV>
								</td>
							</tr>
						</TABLE>

						<table class="ODS_internalWidth" border="0">
							<tbody>
								<TR>
									<TD width="100%"></TD>
									<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
								</TR>
							</tbody>
						</TABLE>
					</td>
				</tr>
			</table>
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>