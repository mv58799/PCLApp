
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
	<jsp:param name="pageName" value="MembershipTerm.MembershipTermList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'curAcctNbr', 'listBtn', 'clearBtn'" />
	<jsp:param name="searchInputFields" value="'curAcctNbr'" />
	<jsp:param name="mandatoryControlNames" value="'curAcctNbr'"/>
	<jsp:param name="mandatoryControlLabels" value="'Nº CC'"/>
</jsp:include>

<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Termo de adesão</TITLE>
</HEAD>

<body>

	<html:form action="/MembershipTerm.MembershipTermList.List.Show.do">
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="ProductSheet" />
			<jsp:param name="currentSubSheet" value="Termo de adesão" />
		</jsp:include>

		<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

		<html:text property="backURL" value="MembershipTerm.MembershipTermList.List.Show" style="display: none;"></html:text>

		<table class="ODS_mainTable" cellspacing="0">
			<tr>
				<td>
					<table class="ODS_internalWidth" border="0">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup">Cadastrar/Alterar Termo de adesão</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<tr class="ODS_Detail_Line1">
												<td width="15%">Nº CC</td>
												<td width="85%"><html:text styleClass="ODS_Text_Field_Size_20" property="curAcctNbr" maxlength="15" onkeyup="javascript:MaskFieldPress('CHAR', 'NNNNNNNNNNNNNNN', 'left', null);"></html:text></td>
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
								<td><html:button property="listBtn" value="Consultar" onclick="javascript:submitAction('list', true);"></html:button></td>
								<td><html:button property="clearBtn" value="Limpar" onclick="javascript:clearPage();"></html:button></td>
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