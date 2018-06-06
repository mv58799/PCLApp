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
			<jsp:param name="pageName" value="ProductFamilyPrvt.ProductFamilyPrvtDetail"/>
			<jsp:param name="mandatoryControlNames" value="'prodFamlCode','prodFamlName'"/>
			<jsp:param name="mandatoryControlLabels" value="'Código da Família de Produtos','Nome da Família de Produtos'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Inserção de Família</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProductFamilyPrvt.ProductFamilyPrvtDetail.Insert.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Parâmetros"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProductFamilyPrvt.ProductFamilyPrvtDetail.Insert.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Inserção de Família</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="12%">Código *</TD>
											<TD width="12%">Nome *</TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="12%"><html:text property="prodFamlCode" styleClass="ODS_Text_Field_Size_10" maxlength="6" onkeyup="MaskFieldPress('CHAR','NNNNNN',null,null)"></html:text></TD>
											<TD width="12%"><html:text property="prodFamlName" styleClass="ODS_Text_Field_Size_40" maxlength="40"></html:text></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line1">
											<TD width="12%">Descrição</TD>
											<TD width="10%"></TD>
											<td width="5%">&nbsp;</td>
											<TD width="25%"></TD>
											<td width="5%">&nbsp;</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD colspan="5"><html:text property="prodFamlText" styleClass="ODS_Text_Field_Size_70" maxlength="70"></html:text></TD>
										</tr>
										<tr class="ODS_Detail_line1">
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
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="insertBtn" value="Confirmar Inserção" onclick="submitAction('insert',true);"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
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