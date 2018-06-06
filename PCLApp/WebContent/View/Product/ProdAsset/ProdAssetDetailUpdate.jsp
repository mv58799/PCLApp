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
			function extraActions(action){};
		 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="ProdAsset.ProdAssetDetail"/>
			<jsp:param name="mandatoryControlNames" value="'prodAssetCode','prodAssetText'"/>
			<jsp:param name="mandatoryControlLabels" value="'C�digo da Sub Classe de Ativo','Descri��o da Sub Classe de Ativo'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Altera��o de Sub Classe</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdAsset.ProdAssetDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Par�metros"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdAsset.ProdAssetDetail.Update.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Altera��o de Classe de Ativo</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="16%">C�digo</TD>
											<TD colspan="2" width="17%">Descri��o *</TD>
											<td width="50%">Ordem</td>
											<td width="16%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="16%"><html:text styleClass="ODS_Text_Field_Size_5" disabled="true" property="prodAssetCode" maxlength="4"></html:text></TD>
											<TD colspan="2" width="17%"><html:text styleClass="ODS_Text_Field_Size_50" property="prodAssetText" maxlength="40"></html:text></TD>
											<td width="50%"><html:text styleClass="ODS_Text_Field_Size_3" property="assetClassCustRptOrderNbr" maxlength="3" onkeyup="MaskFieldPress('CHAR','NNNN',null,null)"></html:text></td>
											<td width="16%">&nbsp;</td>
										</tr>
										
										<tr class="fixed">
											<td width="16%">&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td width="50%">&nbsp;</td>
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
											<TD><html:button property="updBtn" value="Confirmar Altera��o" onclick="submitAction('update',true);"></html:button></TD>
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



