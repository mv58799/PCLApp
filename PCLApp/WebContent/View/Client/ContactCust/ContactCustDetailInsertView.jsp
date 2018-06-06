
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
	<jsp:param name="pageName" value="ContactCust.ContactCustDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Inser��o de Contato</TITLE>
</HEAD>
<body>
<html:form action="/ContactCust.ContactCustDetail.Insert.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Customer" />
	</jsp:include>
    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL" value="ContactCust.ContactCustList.Consult.Show"	style="display:none"></html:text>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">Inser��o de Contato</th>
					</tr>
				</thead>
				<tr>
					<td>&nbsp;</td>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="15%">N�mero do Cliente</TD>
								<TD width="15%"></TD>
								<TD width="15%">Nome</TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" disabled="true" maxlength="11"></html:text></td>
								<td width="15%"></td>
								<TD width="15%" colspan="4"><html:text property="custFullNameText" styleClass="ODS_Text_Field_Size_60" disabled="true" maxlength="60"></html:text></TD>


							</tr>
								
							<tr class="ODS_Detail_line1">
								<!--<td width="15%">N�mero do Contato </td>
								<td width="15%"></td>-->
								<TD width="15%">Nome do Contato </TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<!--<td width="15%"><html:text property="ctcNbr" styleClass="ODS_Text_Field_Size_5" maxlength="6" disabled="true" onkeyup="MaskFieldPress('CHAR','NNNNNN','left',null)"></html:text></td>
								<td width="15%"></td>-->
								<TD width="15%" colspan="4"><html:text
									property="fullNameText" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<td colspan="4">
									<TABLE class="ODS_internalWidth" border="0">
										<tbody>
										<TR class="ODS_Detail_line1">
											<TD width="12%">(DDD)</TD>
											<TD width="13%">Telefone</TD>
											<TD width="7%"></TD>	
											<TD width="11%">Ramal</TD>	
											<TD width="100%"></TD>			
										</TR>
										</tbody>
									</TABLE></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td colspan="4">
									<TABLE class="ODS_internalWidth" border="0">
										<tbody>
											<TR class="ODS_Detail_line2">
											<TD width="10%"><html:text property="phoneDddCode" maxlength="4" styleClass="ODS_Text_Field_Size_5" onkeyup="MaskFieldPress('CHAR','NNNN','left',null)"></html:text></TD>
											<TD width="19%"><html:text property="phoneNbr" maxlength="10" styleClass="ODS_Text_Field_Size_10" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNN','left',null)"></html:text></TD>
											<TD width="10%"><html:text property="phoneExtnNbr" maxlength="5" styleClass="ODS_Text_Field_Size_5" onkeyup="MaskFieldPress('CHAR','NNNNN','left',null)"></html:text></TD>	
											<TD width="100%"></TD>			
										</TR>
										</tbody>
									</TABLE></TD>
								<TD width="15%"></TD>
								<TD width="15%"></TD>
							</tr>
						</tbody>
					</TABLE>
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%">&nbsp;</td>
								<TD width="100%"><html:button property="insertBtn" value="Confirmar Inser��o" onclick="submitAction('insert');"></html:button></TD>
								<TD width="100%"><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>								
								<TD width="100%"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					</td>
					<td><IMG SRC='<%= request.getContextPath() %>/Common/image/spacer.gif' WIDTH="1" HEIGHT="36" /></td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true" />
			</TABLE>
			</td>
		</tr>
	</table>
	</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>