<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.erem.form.EREMDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions( action ){};
	
		function setSelectedKeys(er, em){
			document.forms[0].selectedErNbrInGrid.value = er;
			document.forms[0].selectedEmNbrInGrid.value = em;
		};
		
				
		function verificaCampo(combo)
		{
			var campo = document.forms[0].reltnEndReasText;

			var vrCombo = combo.options[combo.selectedIndex].text;

			if(vrCombo == 'Outros'){
				campo.disabled = false;
			}	
			else{
				campo.value = "";
				campo.disabled = true;
			}	
		};	
		
	 </script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="EREM.EREMDetail"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Exclusão de Associação ER x EM</TITLE>
	</HEAD>

	<body>
		<html:form action="/EREM.EREMDetail.Delete.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<html:text property="backURL" value="EREM.EREMDetail.Delete.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Exclusão de Associação ER x EM</th>
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
											<TH class="ODS_header" width="15%">Número do ER</TH>
											<TH class="ODS_header" width="15%">Número do EM</TH>
											<TH class="ODS_header" width="20%">Papel do Cliente no Relacionamento</TH>
											<TH class="ODS_header" width="40%">Nome do Cliente</TH>
										</tr>

										<%int rowIndex = 0;%>
										<logic:notEmpty name="EREMDetailForm"	property="erEmGrid">
											<logic:iterate name="EREMDetailForm"
												property="erEmGrid" indexId="index" id="row">
												<%
												EREMDetailForm eREMDetailForm = (EREMDetailForm) session.getAttribute("EREMDetailForm");
												String[] resultLine = eREMDetailForm.getErEmGrid()[rowIndex++];
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

												<td align="right" width="15%"><%=resultLine[EREMDetailForm.COL_POS_ER_NBR]%></td>
												<td align="right" width="15%"><%=resultLine[EREMDetailForm.COL_POS_EM_NBR]%></td>
												<td align="left" width="20%"><%=resultLine[EREMDetailForm.COL_POS_ROLE_CUST_CODE]%></td>
												<td align="left" width="40%"><%=resultLine[EREMDetailForm.COL_POS_CUST_FULL_NAME]%></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</TABLE>
							</tr>
							
							<tr>
							<td>
								<table class="ODS_internalWidth" border="0">
								<tr><th class="subtitle" scope="colgroup" colspan="3">Detalhe de Dados Complementares de ER</th></tr>
									<tbody>
										<td width="34%">&nbsp;</td>
											<TR class="ODS_Detail_Line2">
									<TD><p>Estimativa de Patrimônio</p>
									  <p>
											<html:select property="equityClassCode" styleClass="ODS_Select_Field_Size_20" disabled="true">
												<html:option value=""></html:option>
												<html:options property="equityClassCodeDomain.columnValuesByName(EQUITY_CLASS_CODE)" labelProperty="equityClassCodeDomain.columnValuesByName(EQUITY_CLASS_TEXT)" />
											</html:select>		
									  </p></TD>
									<TD width="65%"><p>Origem
									  </p>
									  <p>
											<html:select property="erReltnTrfInd" styleClass="ODS_Select_Field_Size_10" disabled="true">
												<html:option value=""></html:option>
												<html:options property="erReltnTrfIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="erReltnTrfIndDomain.columnValuesByName(INDICATOR_TEXT)" />
											</html:select>
									  </p></TD>
								  <td width="1%">&nbsp;</td>
								</TR>
								<TR class="ODS_Detail_Line2">
									<TD><p>Motivo Encerramento   
											<html:select property="reltnEndReasCode" styleClass="ODS_Select_Field_Size_20" onchange="verificaCampo(this);">
												<html:option value=""></html:option>
												<html:options property="reltnEndReasCodeDomain.columnValuesByName(RELTN_END_REAS_CODE)" labelProperty="reltnEndReasCodeDomain.columnValuesByName(RELTN_END_REAS_TEXT)" />
											</html:select>
									  </p></TD>
									<TD><p>Motivo Outros
									  </p>
									  <p>
									   <html:textarea property="reltnEndReasText" styleClass="ODS_Text_Field_Size_50"  disabled="true"></html:textarea>&nbsp;&nbsp;
									  </p></TD>
									<TD>&nbsp;</TD>
								</TR>
							   <TR class="ODS_Detail_Line2">
								   <TD colspan="2"><p>&nbsp;&nbsp;&nbsp;</p>
								   <p>&nbsp;</p></TD>
								 <td >&nbsp;</td>
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
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%" align="right"><html:button property="deleteBtn" value="Confirmar Exclusão" onclick="submitAction('delete');"></html:button></TD>
											<td><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
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

