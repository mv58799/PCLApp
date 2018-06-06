<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.form.BaseForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Consulta em lista de Corretoras </TITLE>
		
		<script>
		function extraActions(action)
			{
			};
		 </script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="Broker.BrokerList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'detailBtn','alterBtn','deleteBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'bkrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['bkrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
			<jsp:param name="searchInputFields" value="'bkrCnpjNbrSrc'" />
		</jsp:include>
		
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/Broker.BrokerList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Broker"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Broker.BrokerList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta em lista de Corretora</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">CNPJ</TD>
											<TD width="22%"><html:text styleClass="ODS_Text_Field_Size_15" property="bkrCnpjNbrSrc" maxlength="18" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)"></html:text></TD>
											<TD width="25%"> &nbsp; Razão Social</TD>
											<TD colspan="3" width="35%"><html:text styleClass="ODS_Text_Field_Size_60" property="bkrNameTextSrc" maxlength="60"></html:text></TD>
											<td width="13%">&nbsp;</td>
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
											<TD><html:button property="insertBtn" value="Inserir" onclick="submitAction('insert');"></html:button></TD>
											<TD><html:button property="detailBtn" value="Detalhar" disabled="true" onclick="submitAction('detail');"></html:button></TD>													
											<TD><html:button property="alterBtn" value="Alterar" disabled="true" onclick="submitAction('update');"></html:button></TD>
											<TD><html:button property="deleteBtn" value="Excluir" disabled="true" onclick="submitAction('delete');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedBkrCnpjNbr"/>
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
											<TH class="ODS_header" width="79%">Razão Social</TH>
											<TH class="ODS_header" width="18%">CNPJ</TH>
										</tr>
										<ods:DataSetRows name="BrokerListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(BKR_CNPJ_NBR)" id="selectedBkrCnpjNbr" type="java.lang.String"></bean:define>
			 									<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>';disableButtons(false);"/></td>
												<TD width="79%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>';submitAction('detail');">
													<bean:write name="resultRow" property="stringByName(BKR_NAME_TEXT)"/></a>
												</td>
												<TD width="18%" align="right">
													<bean:write name="resultRow" property="stringByName(BKR_CNPJ_NBR)" formatKey='<%=com.citibank.ods.common.form.BaseForm.C_FORMAT_CNPJ_NBR%>'/>
												</td>
											</ods:DataSetRows>
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



