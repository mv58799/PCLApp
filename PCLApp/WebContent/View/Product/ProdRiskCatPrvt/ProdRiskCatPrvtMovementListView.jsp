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
				<jsp:param name="pageName" value="ProdRiskCatPrvt.ProdRiskCatPrvtMovementList"/>
				<jsp:param name="gridId" value="gridTable"/>
				<jsp:param name="headerId" value="gridHeader"/>
				<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
				<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>

		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE> Consulta de Categoria de Risco com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="ProdRiskCatPrvt.ProdRiskCatPrvtMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:hidden property="selectedProdRiskCatCode" value="0"/>
			<html:text property="backURL" value="ProdRiskCatPrvt.ProdRiskCatPrvtMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Categoria de Risco com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD colspan="2">
												<table><tr>
												<td width="8%">Código</td>
												<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodRiskCatCodeSrc" maxlength="4" onkeyup="MaskFieldPress('CHAR','NNNN',null,null)"></html:text></TD>
												<TD width="10%">&nbsp;&nbsp;Descrição</TD>
												<TD><html:text styleClass="ODS_Text_Field_Size_40" property="prodRiskCatTextSrc" maxlength="40"></html:text></TD>	
												</tr></table>		
											</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="2">
												<table><tr>
													<TD width="25%">Usuário da Última Atualização</TD>
													<TD><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserIdSrc" maxlength="20"></html:text></TD>
												</tr></table>
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
								<table id="gridTable" class="ODS_internalWidth"  border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header">&nbsp;</TH>
											<TH class="ODS_header" width="43%">Descrição</TH>
											<TH class="ODS_header" width="7%">Código</TH>
											<TH class="ODS_header" width="20%">Usuário da Última Atualização</TH>
											<TH class="ODS_header" width="17%">Data/Hora da Última Atualização</TH>
											<TH class="ODS_header" width="10%">Ação</TH>
										</tr>

										<ods:DataSetRows name="ProdRiskCatPrvtMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="bigDecimalByName(PROD_INVST_RISK_CODE)" id="RISK_CODE" type="java.math.BigDecimal"></bean:define>
											<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserId" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
			 									<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedProdRiskCatCode.value='<%=RISK_CODE%>';disableButtons(false);disableApproveButtons('<%= lastUpdUserId %>','<%= opernCode %>','true');"/></td>
												<TD width="43%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedProdRiskCatCode.value='<%=RISK_CODE%>';submitAction('approve');" ><bean:write name="resultRow" property="stringByName(PROD_INVST_RISK_TEXT)"/></a></td>
												<TD width="7%"><bean:write name="resultRow" property="bigDecimalByName(PROD_INVST_RISK_CODE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER%>'/></td>
												<TD width="20%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
												<TD width="17%"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="10%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>
											</tr>
										</ods:DataSetRows>
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
