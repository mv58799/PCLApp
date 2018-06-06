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

		function extraActions( action )
		{
			if (action =='Player.PlayerList')
			 {
			  document.forms[0].action = "Player.PlayerList.List.Show";
			  document.forms[0].backURL.disabled = false;
			 }
		};
	 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="ProdPlayerRole.ProdPlayerRoleList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>		
			<jsp:param name="controlNames" value="'detailBtn','deleteBtn'"/>
			<jsp:param name="fieldsWithMask" value="['plyrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>	
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Consulta de Associação Player x Produtos </TITLE>
	</HEAD>
	<body>
		<html:form action="/ProdPlayerRole.ProdPlayerRoleList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Players"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>		
			<html:text property="backURL" value="ProdPlayerRole.ProdPlayerRoleList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Associação Player x Produtos</th>
								</tr>
							</thead>
							<tr>
								<td >&nbsp;</td>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_line11" height="25"><td colspan="3">Player:</td></tr>
										<tr height="8">
					 						<td colspan="3" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="250%" height="1"></td>
										</tr>
										<tr class="ODS_Detail_Line1" height="30">
											<TD width="17%">CNPJ</TD>
											<TD colspan="2"><html:text styleClass="ODS_Text_Field_Size_15" property="plyrCnpjNbrSrc" maxlength="18" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)"></html:text>&nbsp;&nbsp;
												<html:button property="getPlayer" value="Buscar" onclick="submitAction('PreparedSearch.Player.PlayerList');"></html:button>
											</TD>
											<TD colspan="3">Papel&nbsp;&nbsp;
												<html:select styleClass="ODS_Select_Field_Size_15" property="plyrRoleNameSrc">
																<html:option value=""></html:option>
																<html:options property="prodPlayerRoleDomain.columnValuesByName(PLYR_ROLE_TYPE_CODE)" labelProperty="prodPlayerRoleDomain.columnValuesByName(PLYR_ROLE_TYPE_TEXT)" />
															</html:select>
											</td>
										</tr>
										<tr class="ODS_Detail_Line1" height="30">
											<TD width="17%">Nome do Player</TD>
											<TD colspan="5"><html:text styleClass="ODS_Text_Field_Size_60" property="plyrNameSrc" maxlength="60"></html:text></TD>
										</tr>
										<tr class="ODS_line11" height="25"><td colspan="3">Produto:</td></tr>
										<tr height="8">
					 						<td colspan="3" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="250%" height="1"></td>
										</tr>
										<TR class="ODS_Detail_Line1" height="30">
											<TD width="17%">Código</TD>
											<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodCodeSrc" maxlength="10"></html:text></TD>
											<td width="10%">&nbsp;&nbsp;Nome</td>
											<td width="15%"><html:text styleClass="ODS_Text_Field_Size_20" property="prodNameSrc" maxlength="20"></html:text></td>
											<TD width="20%">&nbsp;&nbsp;Código do Sistema</TD>
											<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_5" property="sysCodeSrc" maxlength="3"></html:text></TD>
										</TR>
										<TR class="ODS_Detail_Line2" height="30">
											<TD width="17%">Seg. do Sistema</TD>
											<TD colspan="5"><html:text styleClass="ODS_Text_Field_Size_15" property="sysSegCodeSrc" maxlength="2" onkeyup="MaskFieldPress('CHAR','NN','left',null)"></html:text></TD>
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
											<TD width="100%">&nbsp;</TD>
											<TD width="6%"><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											<TD><html:button property="detailBtn" value="Detalhar" onclick="submitAction('detail'); " disabled = "true"></html:button></TD>
											<TD><html:button property="deleteBtn" value="Excluir" onclick="submitAction('delete'); " disabled = "true"></html:button></TD>
											<TD align="left" width="44"><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>					
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<html:hidden property="selectedPlyrCnpjNbr" />
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
											<TH class="ODS_header" width="2%">&nbsp;</TH>
											<TH class="ODS_header" width="51%">Nome do Player</TH>
											<TH class="ODS_header" width="22%">CNPJ</TH>
											<TH class="ODS_header" width="25%">Quantidade Produto</TH>	
										</tr>
										<ods:DataSetRows name="ProdPlayerRoleListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(PLYR_CNPJ_NBR)" id="selectedPlyrCnpjNbr" type="java.lang.String"></bean:define>
			 										<TD width="2%"><input type="radio" class="radio" name="selection" onclick="javascript:selectedPlyrCnpjNbr.value='<%= selectedPlyrCnpjNbr %>'; disableButtons(false);"/></td>
													<TD width="51%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedPlyrCnpjNbr.value='<%= selectedPlyrCnpjNbr %>';submitAction('detail');"><bean:write name="resultRow" property="stringByName(PLYR_NAME)"/></a></td>
													<TD width="22%" align="right"><bean:write name="resultRow" property="stringByName(PLYR_CNPJ_NBR)"/></td>
													<TD width="25%" align="right"><bean:write name="resultRow" property="stringByName(QTDE_PROD)"/></td>
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
										<TR class="fixed">
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
