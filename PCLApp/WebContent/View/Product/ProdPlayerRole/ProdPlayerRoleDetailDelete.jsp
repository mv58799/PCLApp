
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.modules.product.prodplayerrole.form.ProdPlayerRoleDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">

		function extraActions( action )
		{
		};
	 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="ProdPlayerRole.ProdPlayerRoleDetail"/>
				<jsp:param name="gridId" value="gridTable"/>
				<jsp:param name="headerId" value="gridHeader"/>
				<jsp:param name="fieldsWithMask" value="['plyrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
		</jsp:include>
		
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>		

		<TITLE>Exclusão de Associação Player x Produtos</TITLE>
	</HEAD>
	<body>
		<html:form action="/ProdPlayerRole.ProdPlayerRoleDetail.Delete.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Players"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="ProdPlayerRole.ProdPlayerRoleDetail.Delete.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Exclusão de Associação Player x Produtos</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="19%">CNPJ</TD>
											<TD width="58%">Nome do Player</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="19%"><html:text styleClass="ODS_Text_Field_Size_15" property="plyrCnpjNbr" disabled="true" maxlength="18"></html:text></TD>
											<TD width="58%"><html:text styleClass="ODS_Text_Field_Size_60" property="plyrName" disabled="true" maxlength="60"></html:text></TD>
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
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Lista de Produtos</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="fixed">
											<TH class="ODS_header" width="15%">CNPJ Player</TH>
											<TH class="ODS_header" width="15%">Papel do Player</TH>
											<TH class="ODS_header" width="15%">Código do Produto</TH>
											<TH class="ODS_header" width="15%">Código do Sistema</TH>
											<TH class="ODS_header" width="15%">Segmento do Sistema</TH>
											<TH class="ODS_header" width="15%">Descrição do Produto</TH>
										</tr>
										
										<%int rowIndex = 0;%>
										<logic:notEmpty name="ProdPlayerRoleDetailForm"	property="prodPlayerRoleDomains">
											<logic:iterate name="ProdPlayerRoleDetailForm"
												property="prodPlayerRoleDomains" indexId="index" id="row">
												<%
												ProdPlayerRoleDetailForm prodPlayerRoleForm = (ProdPlayerRoleDetailForm) session.getAttribute("ProdPlayerRoleDetailForm");
												String[] resultLine = prodPlayerRoleForm.getProdPlayerRoleDomains()[rowIndex++];
												%>
												<ods:CountStep counterName="index" counterStartIndex="0"
													sequenceRestartStep="2" stepIndexName="step">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2">
													</logic:equal>
												</ods:CountStep>

												<td class="alignLeft"><%=resultLine[0]%></td>
												<td class="alignLeft"><%=resultLine[1]%></td>
												<td class="centralized"><%=resultLine[2]%></td>
												<td class="alignLeft"><%=resultLine[3]%></td>
												<td class="centralized"><%=resultLine[4]%></td>
												<td class="alignLeft"><%=resultLine[5]%></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
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
											<TD><html:button property="deleteBtn" value="Confirmar Exclusão" onclick="submitAction('delete');"></html:button></TD>
											<TD align="left"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>										
										</TR>
									</tbody>
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
