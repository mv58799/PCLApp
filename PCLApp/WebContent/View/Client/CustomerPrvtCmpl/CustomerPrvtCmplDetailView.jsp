
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">

<script language="javascript">
	function extraActions(action){

	}																	
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="CustomerPrvtCmpl.CustomerPrvtCmplDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe do Cliente - Dados Complementares</TITLE>
</HEAD>

<body>
<html:form
	action="/CustomerPrvtCmpl.CustomerPrvtCmplDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Clientes" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td><html:text property="backURL"
				value="CustomerPrvtCmpl.CustomerPrvtCmplDetail.Consult.Show"
				style="display:none"></html:text>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe do Cliente - Dados Complementares</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_Line1">
								<TD width="19%">N�mero do Cliente</TD>
								<td width="2%"></td>
								<TD width="33%">Nome do Cliente</TD>
								<td width="2%"></td>
								<td width="19%"></td>
								<td width="6%"></td>
							</tr>
							<tr class="ODS_Detail_Line2">
								<TD width="16%"><html:text styleClass="ODS_Text_Field_Size_10" property="custNbrSrc" disabled="true"></html:text></TD>
								<td width="2%"></td>
								<TD colspan="4"><html:text styleClass="ODS_Text_Field_Size_60" property="custText"
									disabled="true"></html:text></TD>
								<td width="6%"></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td width="19%">Private Number</td>
								<td width="2%"></td>
								<td colspan="4" rowspan="2">
									<table><tr>
										<td width="50%">Key Private Number</td>
										<TD>N�mero EM</TD>
									</tr>
									<tr>
										<TD width="50%"><html:text styleClass="ODS_Text_Field_Size_10" property="prvtKeyNbr" disabled="true"></html:text></TD>
										<td><html:text styleClass="ODS_Text_Field_Size_30" property="emNbr" disabled="true"></html:text></td>
									</tr></table>
								</td>
							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="19%" colspan = "2"><html:text styleClass="ODS_Text_Field_Size_10" property="prvtCustNbr"
									disabled="true"></html:text></td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td colspan="6">
									<table width="100%">
										<tr>
											<td width="50%">Potencial Receita</td>
											<td width="50%">Classifica��o Compliance</td>
										</tr>
										<tr>
											<td width="50%"><html:select property="wealthPotnlCode" styleClass="ODS_Select_Field_Size_40"	disabled="true">
																<html:option value=""></html:option>
																<html:options
																	property="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_CODE)"
																	labelProperty="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_TEXT)" />
															</html:select>
											</td>
											<td width="50%"><html:select property="classCmplcCode" styleClass="ODS_Select_Field_Size_40" disabled="true">
																<html:option value=""></html:option>
																<html:options
																	property="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_CODE)"
																	labelProperty="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_TEXT)" />
															</html:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr class="ODS_Detail_Line1">
								<td width="19%">N�mero do Banker</td>
								<TD width="2%"></TD>
								<td colspan = "2">Nome do Banker</TD>
								<TD width="16%"></TD>
								<TD width="16%"></TD>

							</tr>
							<tr class="ODS_Detail_Line2">
								<td width="16%"><html:text styleClass="ODS_Text_Field_Size_5" property="offcrNbrSrc" disabled="true"></html:text></TD>
								<td width="2%"></td>								
								<TD colspan = "2"><html:text styleClass="ODS_Text_Field_Size_40" property="offcrText" disabled="true" size = "60"></html:text></TD>
								<td width="16%"></td>
								<TD width="16%"></TD>
								
							</tr>
							<tr class="ODS_Detail_Line1">
								<td colspan="6">
										<table><tr>
											<TD width="30%">N�mero do Banker (Sistema Global)&nbsp;</TD>
											<TD width="30%">Envio de Correspond�ncia do Logotipo&nbsp;</TD>
											<TD width="30%">Envio de Mala Direta&nbsp;</TD>
										</tr>
										<tr>
											<td width="30%"><html:text styleClass="ODS_Text_Field_Size_5" property="glbRevenSysOffcrNbr" disabled="true"></html:text></TD>
											<td width="30%">
												<html:select property="offclMailRecvInd" styleClass="ODS_Select_Field_Size_5" disabled="true">
													<html:option value=""></html:option>
													<html:options property="offclMailRecvIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="offclMailRecvIndDomain.columnValuesByName(INDICATOR_TEXT)" />
												</html:select>
											</td>
											<td width="30%">
												<html:select property="mailRecvInd" styleClass="ODS_Select_Field_Size_5" disabled="true">
													<html:option value=""></html:option>
													<html:options property="mailRecvIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="mailRecvIndDomain.columnValuesByName(INDICATOR_TEXT)" />
												</html:select>
											</td>
										</tr></table>
								</td>
							</tr>
							<tr class="ODS_Detail_Line2">		
								<td width="16%"></td>				
							</tr>

						</tbody>
					</TABLE>
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%"></td>
								<TD><html:button property="backBtn" value="Voltar"
									onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</TABLE>
			</html:form></td>
		</tr>
	</table>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
