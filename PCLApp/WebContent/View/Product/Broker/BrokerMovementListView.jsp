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
			<jsp:param name="pageName" value="Broker.BrokerMovementList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'bkrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['bkrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Consulta de Corretoras com Pendência de Aprovação </TITLE>
	</HEAD>

	<body>
		<html:form action="/Broker.BrokerMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Broker.BrokerMovementList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Corretoras com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="15%" colspan="2">CNPJ</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_15" property="bkrCnpjNbrSrc" maxlength="18" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)"></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="15%" colspan="2">Razão Social da Corretora</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_60" property="bkrNameTextSrc" maxlength="60"></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="15%" colspan="2">Usuário da Última Atualização</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserIdSrc" maxlength="20"></html:text></TD>
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
											<TD align="right"><html:button property="approvedBtn" value="Aprovação" onclick="submitAction('approve');" disabled="true"></html:button></TD>
											<TD align="left" width="44"><html:button property="alterBtn" value="Alterar" onclick="submitAction('update');" disabled="true"></html:button></TD>
											<TD align="right"><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedBkrCnpjNbr" value="0" />
					<td>&nbsp;</td>
					<td>

					<DIV class="ODS_DivGrid">
						<table border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table id="gridTable" width="1100px" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header">&nbsp;</TH>
											<TH class="ODS_header" width="38%">Razão Social</TH>
											<TH class="ODS_header" width="12%">CNPJ</TH>
											<TH class="ODS_header" width="21%">Usuário da Última Atualização</TH>
											<TH class="ODS_header" width="21%">Data/Hora da Última Atualização</TH>											
											<TH class="ODS_header" width="6%">Ação</TH>
										</tr>
										<ods:DataSetRows name="BrokerMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(BKR_CNPJ_NBR)" id="selectedBkrCnpjNbr" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserIdSrc" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
			 									<TD width="2%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>'; disableButtons(false);disableApproveButtons('<%= lastUpdUserIdSrc %>','<%= opernCode %>',true);"/></td>
												<TD width="38%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedBkrCnpjNbr.value='<%= selectedBkrCnpjNbr %>'; submitAction('approve');"><bean:write name="resultRow" property="stringByName(BKR_NAME_TEXT)"/></a></td>
												<TD width="12%" align="right"><bean:write name="resultRow" property="stringByName(BKR_CNPJ_NBR)"/></td>
												<TD width="21%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
												<TD width="21%" align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="6%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>																																	
										</ods:DataSetRows>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</DIV>
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
