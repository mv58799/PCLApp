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
		<TITLE> Consulta de Players </TITLE>
		
		<script>
		function extraActions(action)
			{
				if ( action == 'assocProduct' )
				{
					document.forms[0].action = "ProdPlayerRole.ProdPlayerRoleDetail.Association.do";
					document.forms[0].backURL.disabled = false;	
				}
				else if(action== 'clearPage'){
					document.forms[0].action = "Player.PlayerList.List.ClearPage";
					document.forms[0].backURL.disabled = true;	
				}	
			};
		 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="Player.PlayerList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','deleteBtn','detailBtn','assocProductBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'plyrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['plyrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
			<jsp:param name="searchInputFields" value="'plyrCnpjNbrSrc'" />
		</jsp:include>
		
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
	</HEAD>
	<body>
		<html:form action="/Player.PlayerList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Players"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Player.PlayerList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Players</th>
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
											<TD width="23%">&nbsp;</TD>
										</tr>

									<tr class="ODS_Detail_Line1">
											<TD width="15%">Papel</TD>
											<td width="22%">
												<html:select styleClass="ODS_Text_Field_Size_15" property="plyrRoleTypeCodeSrc">
													<html:option value=""></html:option>
													<html:options property="playerRoleTypeDomain.columnValuesByName(PLYR_ROLE_TYPE_CODE)" labelProperty="playerRoleTypeDomain.columnValuesByName(PLYR_ROLE_TYPE_TEXT)" />
												</html:select>											
											</td>
											<TD width="25%"></TD>
											<TD width="19%"></TD>
											<TD width="10%"></TD>
											<td width="15%"></td>
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
											<TD align="right"><html:button property="insertBtn" value="Inserir" onclick="submitAction('insert');"></html:button></TD>
											<TD><html:button property="detailBtn" value="Detalhar" disabled="true" onclick="submitAction('detail');"></html:button></TD>
											<TD align="right"><html:button property="alterBtn" value="Alterar" disabled="true" onclick="submitAction('update');"></html:button></TD>
											<TD align="right"><html:button property="deleteBtn" value="Excluir" disabled="true" onclick="submitAction('delete');"></html:button></TD>
											<TD align="right"><html:button property="assocProductBtn" value="Associar Produtos" disabled="true" onclick="submitAction('assocProduct');"></html:button></TD>
											<TD align="left" width="44"><html:button property="clearBtn" value="Limpar" onclick="clearResultSetInServer();submitAction('clearPage');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<html:hidden property="selectedPlyrCnpjNbr"/>
					<html:hidden property="selectedPlyrCnpjNbrSrc"/>
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
											<TH class="ODS_header" width="79%">Nome do Player</TH>
											<TH class="ODS_header" width="18%">CNPJ</TH>
										</tr>
										<ods:DataSetRows name="PlayerListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(PLYR_CNPJ_NBR)" id="selectedPlyrCnpjNbr" type="java.lang.String"></bean:define>
			 									<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedPlyrCnpjNbr.value='<%= selectedPlyrCnpjNbr %>';selectedPlyrCnpjNbrSrc.value='<%= selectedPlyrCnpjNbr %>';disableButtons(false);"/></td>
												<TD width="79%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedPlyrCnpjNbr.value='<%= selectedPlyrCnpjNbr %>';submitAction('detail');"><bean:write name="resultRow" property="stringByName(PLYR_NAME)"/></a></td>
												<TD width="18%" align="right"><bean:write name="resultRow" property="stringByName(PLYR_CNPJ_NBR)" formatKey='<%=com.citibank.ods.common.form.BaseForm.C_FORMAT_CNPJ_NBR%>'/></td>
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
