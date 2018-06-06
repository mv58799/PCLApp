
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

		<script language="javascript">
			function extraActions(action){
			}; 																		
		</script>

		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="ProdSubFamlPrvt.ProdSubFamlPrvtMovementDetail"/>
				<jsp:param name="mandatoryControlNames" value="'prodSubFamlCode'"/>
				<jsp:param name="mandatoryControlLabels" value="'Código da Sub-Família'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Alteração de Sub-Família com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdSubFamlPrvt.ProdSubFamlPrvtMovementDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdSubFamlPrvt.ProdSubFamlPrvtMovementDetail.Update.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Alteração de Sub-Família com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">Código</TD>
											<TD width="12%">Nome *</TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodSubFamlCode" disabled="true" maxlength="6"></html:text></TD>
											<TD width="12%"><html:text styleClass="ODS_Text_Field_Size_40" property="prodSubFamlName" maxlength="40"></html:text></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">Descrição</TD>
											<TD width="10%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="5"><html:text styleClass="ODS_Text_Field_Size_70" property="prodSubFamlText" maxlength="70"></html:text></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="15%" colspan="2">Nome da Familia *</TD>
											<TD width="15%">&nbsp;</TD>
											<TD width="10%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD  colspan="5">
												<html:select property="prodFamlCode">
													<html:option value=""></html:option>
													<html:options property="prodFamlCodeDomain.columnValuesByName(PROD_FAML_CODE)" labelProperty="prodFamlCodeDomain.columnValuesByName(PROD_FAML_NAME)" />
												</html:select>
											</TD>
										</tr>
										<tr class="fixed">
											<TD width="15%"></TD>
											<TD width="15%">&nbsp;</TD>
											<TD width="10%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedCUST_NBR" value="0" />
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<TBODY>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="updBtn" value="Confirmar Alteração" onclick="submitAction('update');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage()"></html:button></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</TBODY>
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
