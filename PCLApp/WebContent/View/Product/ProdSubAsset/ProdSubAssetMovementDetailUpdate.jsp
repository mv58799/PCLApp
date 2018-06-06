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
			}; 																		
		</script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="ProdSubAsset.ProdSubAssetMovementDetail"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Alteração de Sub Classe de Ativo com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdSubAsset.ProdSubAssetMovementDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Parâmetros"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdSubAsset.ProdSubAssetMovementDetail.Update.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Alteração de Sub Classe de Ativo com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>

								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="16%">Código</TD>
											<TD colspan="2" width="21%">Descrição *</TD>
											<td width="40%">Ordem *</td>
											<td width="16%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="16%"><html:text styleClass="ODS_Text_Field_Size_5" disabled="true" property="prodSubAssetCode" maxlength="4"></html:text></TD>
											<TD colspan="2" width="21%"><html:text styleClass="ODS_Text_Field_Size_50" property="prodSubAssetText" maxlength="40"></html:text></TD>
											<td width="40%"><html:text styleClass="ODS_Text_Field_Size_3" property="subAssetClassRptOrderNbr" maxlength="3" onkeyup="MaskFieldPress('CHAR','NNNN',null,null)"></html:text></td>
											<td width="16%">&nbsp;</td>
										</tr>
									</tbody>
								</TABLE>
								
									<table class="ODS_internalWidth" border="0" cellspacing="0">
									  <tbody>
									  <tr>
									      <td>&nbsp;</td>
									  </tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="2" width="21%">Nome da Classe *</TD>
											<TD>&nbsp;</TD>
											<TD width="40%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
										  <td width="50%">
										    <html:select styleClass="ODS_Text_Field_Size_20" property="prodAssetCode">
												<html:option value=""></html:option>
												<html:options property="prodAssetCodeDomain.columnValuesByName(ASSET_CLASS_CODE)" labelProperty="prodAssetCodeDomain.columnValuesByName(ASSET_CLASS_TEXT)" />
											</html:select>
										 </td>
									   </tr>
										<tr class="fixed">
											<td width="16%">&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td width="40%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
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
											<TD><html:button property="updBtn" value="Confirmar Alteração" onclick="submitAction('update');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
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