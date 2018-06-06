
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>

<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Common/css/citi.css">

<script language=javascript>
	function extraActions(action) {
	};
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="MembershipTerm.MembershipTermDetail" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'approveBtn', 'reproveBtn'" />
	<jsp:param name="searchInputFields" value="" />
	<jsp:param name="mandatoryControlNames" value="" />
	<jsp:param name="mandatoryControlLabels" value="" />
</jsp:include>

<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Termo de adesão</TITLE>
</HEAD>

<body>

	<html:form action="/MembershipTerm.MembershipTermDetail.Insert.Show.do">
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="ProductSheet" />
			<jsp:param name="currentSubSheet" value="Termo de adesão" />
		</jsp:include>

		<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

		<html:text property="backURL" value="MembershipTerm.MembershipTermDetail.Insert.Show" style="display: none;"></html:text>

		<table class="ODS_mainTable" cellspacing="0">
			<tr>
				<td>
					<table class="ODS_internalWidth" border="0">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup">Aprovar/Rejeitar Termo de adesão</th>
							</tr>
							<tr class="ODS_line11" height="25">
								<td>Dados GRB</td>
							</tr>
							<tr>
								<td><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1" width="100%"></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<tr class="ODS_Detail_Line1">
												<td>ACCT NBR:</td>
												<td colspan="2">CLIENT FULL NAME:</td>
												<td>RELTN NBR:</td>
												<td>TIPO:</td>
											</tr>
											<tr class="ODS_Detail_Line1">
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Select_Field_Size_5" property="curAcctNbr"></html:text></td>
												<td colspan="2"><html:text readonly="true" disabled="true" styleClass="ODS_Text_Field_Size_80" property="custFullName"></html:text></td>
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Select_Field_Size_5" property="reltnNbr"></html:text></td>
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Select_Field_Size_1" size="2"  property="custTypeCode"></html:text></td>
											</tr>
											<tr  class="ODS_Detail_Line1">
												<td>CPF:</td>
												<td>OFFICER:</td>
												<td>FUNCIONAL:</td>
												<td>AGÊNCIA:</td>
												<td>ES:</td>
											</tr>
											<tr  class="ODS_Detail_Line1">
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Text_Field_Size_10" style="width:80px;" property="custCpfNbr"></html:text></td>
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Text_Field_Size_70" property="offcrName"></html:text></td>
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Select_Field_Size_5" property="offcrNbr"></html:text></td>
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Text_Field_Size_5" property="portfBrchCode"></html:text></td>
												<td><html:text readonly="true" disabled="true" styleClass="ODS_Text_Field_Size_1" size="2" property="addrStateCode"></html:text></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
						<thead>
							<tr class="ODS_line11" height="25">
								<td>Dados do cadastro</td>
							</tr>
							<tr>
								<td><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1" width="100%"></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<tr valign="top" class="ODS_Detail_Line1">
												<td><html:checkbox styleClass="checkbox" property="trsrySysInd" value="S" disabled="true">REG CTDS</html:checkbox></td>
												<td><html:checkbox styleClass="checkbox" property="dpSysInd" value="S" disabled="true">REG DP</html:checkbox></td>
												<td>&nbsp;</td>
												<td>Segment:</td>
												<td>
													<html:text readonly="true" disabled="true" styleClass="ODS_Text_Field_Size_5" property="segName"></html:text>
												
												</td>
												<td>&nbsp;</td>
												<td>
													<table class="ODS_internalWidth" border="0">
														<tbody>
															<tr class="ODS_Detail_Line1">
																<td>Produto:</td>
																<td>
																	<select id="prodCode" name="prodCode" class="ODS_Select_Field_Size_10" disabled="disabled">
																		<option value="LCA">LCA</option>
																		<option value="LCI">LCI</option>
																	</select>
																</td>
															</tr>
															<tr class="ODS_Detail_Line1">
																<td>Termo:</td>
																<td>
																	<select id="adhTermTypeCode" name="adhTermTypeCode" class="ODS_Select_Field_Size_10" disabled="disabled">
																		<option value="O">Original</option>
																		<option value="C">Cópia</option>
																	</select>
																</td>
															</tr>
															<tr class="ODS_Detail_Line1">
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
														</tbody>
													</table>
												</td>
												<td>
													<table class="ODS_internalWidth" border="0">
														<thead>
															<tr>
																<th style="width: 100px;" class="ODS_header">Produto</th>
																<th style="width: 100px;" class="ODS_header">Termo</th>
															</tr>
														</thead>
														<tbody>
															<logic:iterate id="listaProdutoTermo" property="listaProdutoTermo" name="MembershipTermDetailForm" indexId="index">
															<% String classLine = ""; if((index.intValue() % 2) == 0) classLine="ODS_line2"; else classLine="ODS_line1"; %>
															<tr class="<%=classLine%>">
																<td><bean:write name="listaProdutoTermo" property="prodCode" /></td>
																<td><logic:equal name="listaProdutoTermo" property="adhTermTypeCode" value="O">Original</logic:equal>
																	<logic:equal name="listaProdutoTermo" property="adhTermTypeCode" value="C">Cópia</logic:equal></td>
															</tr>
															</logic:iterate>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>

					<table class="ODS_internalWidth" border="0">
						<tbody>
							<tr>
								<td width="100%"></td>
								<td>
								<logic:equal name="MembershipTermDetailForm" property="canApprove" value="true">
									<html:button property="approveBtn" value="Aprovar" onclick="javascript:submitAction('approve', true);"></html:button>
								</logic:equal>
								</td>
								<td><html:button property="reproveBtn" value="Rejeitar" onclick="javascript:submitAction('reprove', true);"></html:button></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>

		<jsp:include page="/View/Util/Footer.jsp" flush="true"/>

	</html:form>

</body>

<jsp:include page="/View/Util/NoCacheIE.jsp" />

</html:html>