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
			<jsp:param name="pageName" value="Player.PlayerMovementList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'plyrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['plyrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Consulta de Players com Pendência de Aprovação </TITLE>
	</HEAD>

	<body>
		<html:form action="/Player.PlayerMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Approved"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Player.PlayerMovementList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Players com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">CNPJ</TD>
											<TD width="22%"><html:text styleClass="ODS_Text_Field_Size_15" property="plyrCnpjNbrSrc" maxlength="18" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)"></html:text></TD>
											<TD width="25%"> &nbsp; Nome do Player</TD>
											<TD width="15%" colspan="3"><html:text styleClass="ODS_Text_Field_Size_60" property="plyrNameSrc" maxlength="60"></html:text></TD>
										</tr>
									<tr class="ODS_Detail_Line1">
											<TD width="15%">Papel</TD>
											<td width="22%">
												<html:select styleClass="ODS_Text_Field_Size_15" property="plyrRoleTypeCodeSrc">
													<html:option value=""></html:option>
													<html:options property="playerRoleTypeDomain.columnValuesByName(PLYR_ROLE_TYPE_CODE)" labelProperty="playerRoleTypeDomain.columnValuesByName(PLYR_ROLE_TYPE_TEXT)" />
										
												</html:select>											
											</td>
											<TD colspan="4">
												<table>
													<tr>
														<td>&nbsp;Usuário de Última Atualização</TD>
														<TD>&nbsp;<html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserIdSrc" maxlength="20"></html:text></TD>
													</tr>
												</table>
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
											<TD align="right"><html:button property="approvedBtn" value="Aprovação" onclick="submitAction('approve');" disabled="true"></html:button></TD>
											<TD align="left" width="44"><html:button property="alterBtn"
									value="Alterar" onclick="submitAction('update');"
									disabled="true"></html:button></TD>
											<TD align="right"><html:button property="clearBtn"
									value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedPlyrCnpjNbr" value="0" />
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
											<TH class="ODS_header" width="50%">Nome do Player</TH>
											<TH class="ODS_header" width="11%">CNPJ</TH>
											<TH class="ODS_header" width="16%">Usuário de Última Atualização</TH>
											<TH class="ODS_header" width="12%">Data/Hora de Última Atualização</TH>											
											<TH class="ODS_header" width="7%">Ação</TH>
										</tr>
										<ods:DataSetRows name="PlayerMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(PLYR_CNPJ_NBR)" id="selectedPlyrCnpjNbr" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserIdSrc" type="java.lang.String"></bean:define>
											<bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
			 									<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedPlyrCnpjNbr.value='<%= selectedPlyrCnpjNbr %>'; disableButtons(false);disableApproveButtons('<%= lastUpdUserIdSrc %>','<%= opernCode %>',true);"/></td>
												<TD width="50%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedPlyrCnpjNbr.value='<%= selectedPlyrCnpjNbr %>'; submitAction('approve');"><bean:write name="resultRow" property="stringByName(PLYR_NAME)"/></a></td>
												<TD width="11%" align="right"><bean:write name="resultRow" property="stringByName(PLYR_CNPJ_NBR)"/></td>
												<TD width="16%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
												<TD width="12%" align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="7%"><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>																																	
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
