
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
				<jsp:param name="approvalControlNames" value="'approvedBtn','',''"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Detalhe de Sub-Fam�lia com Pend�ncia de Aprova��o</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdSubFamlPrvt.ProdSubFamlPrvtMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprova��o"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdSubFamlPrvt.ProdSubFamlPrvtMovementDetail.Approval.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Sub-Fam�lia com Pend�ncia de Aprova��o</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">C�digo</TD>
											<TD width="12%">Nome</TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodSubFamlCode" disabled="true" maxlength="6"></html:text></TD>
											<TD width="12%"><html:text styleClass="ODS_Text_Field_Size_40" property="prodSubFamlName" disabled="true" maxlength="40"></html:text></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="15%">Descri��o</TD>
											<TD width="12%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="5"><html:text styleClass="ODS_Text_Field_Size_70" property="prodSubFamlText" disabled="true" maxlength="70"></html:text></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="15%" colspan="2">Nome da Familia</TD>
											<TD width="15%">&nbsp;</TD>
											<TD width="10%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD  colspan="5">
												<html:select styleClass="ODS_Select_Field_Size_10" property="prodFamlCode" disabled="true">
													<html:option value=""></html:option>
													<html:options property="prodFamlCodeDomain.columnValuesByName(PROD_FAML_CODE)" labelProperty="prodFamlCodeDomain.columnValuesByName(PROD_FAML_NAME)" />
												</html:select>
											</TD>
										</tr>
										<tr class="ODS_Detail_line1">
											<TD colspan="5">
												<table><tr>
													<td width="30%">Usu�rio de �ltima Atualiza��o</TD>
											        <TD width="35%">Data/Hora de �ltima Atualiza��o</TD>
													<td width="30%">A��o</td>
												</tr>
												<tr>
													<td width="30%"><html:text styleClass="ODS_Text_Field_Size_10" property="lastUpdUserId" disabled="true"></html:text></td>
											        <TD width="35%"><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdDate" disabled="true"></html:text></td>
   													<TD width="30%"><html:text styleClass="ODS_Text_Field_Size_10" property="opernCode" disabled="true"></html:text></td>
												</tr>
												</table>
											</TD>
										</tr>
										<tr class="ODS_Detail_line1">
											<td></td>
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
									<TBODY>
										<TR>
											<td width="100%">&nbsp;</td>
											<TD><html:button property="approvedBtn" value="Aprovar" onclick="submitAction('approve');"></html:button></TD>
											<TD><html:button property="reprovedBtn" value="Reprovar" onclick="submitAction('reprove','', true);"></html:button></TD>
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

<script language="javascript">
	disableApproveButtons("<bean:write name='ProdSubFamlPrvtMovementDetailForm' property='lastUpdUserId'/>","<bean:write name='ProdSubFamlPrvtMovementDetailForm' property='opernCode'/>",'true');							
</script>
