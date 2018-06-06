<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<script language="javascript">
			function extraActions(action){
				if ( action == 'refresh' ) {
					document.forms[0].action = "ProdAssetType.ProdAssetTypeMovementDetail.Update.Show"; 
					document.forms[0].backURL.disabled = true; 
					document.forms[0].findType.value = "true";
				}
			};

		</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="ProdAssetType.ProdAssetTypeMovementDetail"/>
			<jsp:param name="mandatoryControlNames" value="'prodAssetTypeCode','prodAssetTypeText'"/>
			<jsp:param name="mandatoryControlLabels" value="'Código do Tipo de Ativo','Descrição do Tipo de Ativo'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Detalhe do Tipo de Ativo com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdAssetType.ProdAssetTypeMovementDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Parâmetros"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdAssetType.ProdAssetTypeMovementDetail.Update.Show" style="display:none"></html:text>
			<html:hidden property="findType"></html:hidden>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe do Tipo de Ativo com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
							    	<table  align="right" class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<td width="10%">Código</td>
											<TD width="52%">Descrição *</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td width="10%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodAssetTypeCode" maxlength="40" disabled="true"></html:text></td>
											<TD width="52%"><html:text styleClass="ODS_Text_Field_Size_50" property="prodAssetTypeText" maxlength="40" disabled="true"></html:text></TD>
										</tr>
										
								</table>
							   </td>	
							 </tr>
									
									
								<tr>
								  <td>
																	
							  <table class="ODS_internalWidth" border="0" cellspacing="0">
																		
										<tr class="ODS_Detail_Line1">
										  <td width="14%">Sub Classe * </td>
										  <td width="16%">&nbsp;&nbsp;Classe</td>
										  <td width="28%">Ordem</td>
										  <td width="25%">&nbsp;</td>
										  <td width="10%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line1">
										  <td width="14%">
										    <html:select disabled="true" styleClass="ODS_Text_Field_Size_40" property="prodSubAssetCode" onchange="submitAction('refresh');">
											  <html:option value=""></html:option>
											  <html:options property="prodSubAssetCodeDomain.columnValuesByName(SUB_ASSET_CLASS_CODE)" labelProperty="prodSubAssetCodeDomain.columnValuesByName(SUB_ASSET_CLASS_TEXT)" />
											</html:select>											      
									      </td>
										  <td width="16%">&nbsp;
										    <html:select styleClass="ODS_Text_Field_Size_40" property="prodAssetCode" disabled="true">
											  <html:option value=""></html:option>
											  <html:options property="prodAssetCodeDomain.columnValuesByName(ASSET_CLASS_CODE)" labelProperty="prodAssetCodeDomain.columnValuesByName(ASSET_CLASS_TEXT)" />
											</html:select></td>
										  <td width="28%"><html:text styleClass="ODS_Text_Field_Size_3" property="assetTypeCustRptOrderNbr" maxlength="3" onkeyup="MaskFieldPress('CHAR','NNNN',null,null)" disabled="true"></html:text></td>
										  <td width="25%">&nbsp;</td>
										  <td width="10%">&nbsp;</td>
										</tr>
										
										<tr class="fixed">
											<td width="14%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
											<td width="28%">&nbsp;</td>
											<td width="25%">&nbsp;</td>
											<td width="10%">&nbsp;</td>
											
										</tr>
									</tbody>
								</TABLE>
								<tr><td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								
							<tr>
					 			<td colspan="3"><img src='/appsl/privatelayer/Common/image/20grey1.gif' width="131%" height="1"></td>
							</tr>
							
								<table class="ODS_internalWidth" border="0" cellspacing="0">
								 <tbody>								
									<tr class="ODS_Detail_line1">
										<TD colspan="5">
									 <table><tr>
										<td width="10%">Usuário de Última Atualização</TD>
										<TD width="10%">Data/Hora de Última Atualização</TD>
										<td width="10%">Ação</td>
									</tr>
									<tr>
										<td width="35%"><html:text property="lastUpdUserId" disabled="true" styleClass="ODS_Text_Field_Size_20" maxlength="20"></html:text></TD>
										<TD width="35%"><html:text property="lastUpdDate" disabled="true" styleClass="ODS_Text_Field_Size_20" maxlength="10"></html:text></TD>
   										<TD width="30%"><html:text property="opernCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
									</tr>
								</table>
							</tr>
						</TABLE>
		

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
											<TD><html:button property="approvedBtn" value="Aprovar" onclick="submitAction('approve');"></html:button></TD>
											<TD><html:button property="rejectBtn" value="Reprovar" onclick="submitAction('reprove','', true);"></html:button></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
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



