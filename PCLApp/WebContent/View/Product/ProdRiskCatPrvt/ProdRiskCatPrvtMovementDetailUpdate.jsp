
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
				<jsp:param name="pageName" value="ProdRiskCatPrvt.ProdRiskCatPrvtMovementDetail"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Alteração de Categoria de Risco com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="ProdRiskCatPrvt.ProdRiskCatPrvtMovementDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdRiskCatPrvt.ProdRiskCatPrvtMovementDetail.Update.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Alteração de Categoria de Risco com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="16%">Código</TD>
											<TD width="16%" colspan="2">Descrição *</TD>
											<td width="16%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="16%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodRiskCatCode" disabled="true" maxlength="4"></html:text></TD>
											<TD width="16%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_40" property="prodRiskCatText" maxlength="40"></html:text></TD>
											<td width="16%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
										</tr>
										<tr class="fixed">
											<td width="16%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
											<td width="16%">&nbsp;</td>
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
									<TBODY>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="updBtn" value="Confirmar Alteração" onclick="submitAction('update');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</TBODY>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

