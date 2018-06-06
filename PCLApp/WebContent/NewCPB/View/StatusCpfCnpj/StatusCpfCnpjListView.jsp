<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.form.StatusCpfCnpjForm"%>

<html:html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="GENERATOR" content="IBM Software Development Platform">
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="StatusCpfCnpj" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'" />
			<jsp:param name="approvalControlNames" value="'','','alterBtn'" />
		</jsp:include>
		<jsp:include page="/NewCPB/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		
		<title>Cliente - Consulta Status CPF/CNPJ</title>	
		<script language="javascript">
			function setSelectedKeys(cpfCnpj) {
				document.forms[0].selectedCpfCnpj.value = cpfCnpj;
			};
		</script>
	</head>
	<body>
		<html:form action="/NEWCPB.StatusCpfCnpj.do">
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="Clientes" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               StatusCpfCnpjForm formBean = (StatusCpfCnpjForm)session.getAttribute("StatusCpfCnpjForm");
	         %>
	         
	         <html:hidden property="selectedCpfCnpj" value="" />
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Cliente - Consulta Status CPF/CNPJ</th>
								</tr>
							</thead>	
							<tbody>
								<tr class="ODS_Detail_line1">
									<td colspan="6">
										<table>
											<tr>
												<td>Número EM</td>
												<td>
													<html:text property="filterNumberEM"
														styleClass="ODS_Text_Field_Size_10"
														style="margin-left: 15px;"
														maxlength="30" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>
											</tr>
											<tr>
												<td>CPF/CNPJ</td>
												<td>
													<html:text property="filterCpfCnpj"
														styleClass="ODS_Text_Field_Size_10"
														style="margin-left: 15px;"
														maxlength="18" onblur="completeCpfCnpj(this);"/>
												</td>
											</tr>
											<tr>
												<td>Nome</td>
												<td>
													<html:text property="filterName"
														styleClass="ODS_Text_Field_Size_60"
														style="margin-left: 15px;"
														maxlength="60" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>
											</tr>
											<tr>
												<td>Status</td>
												<td>
													<html:select styleClass="ODS_Select_Field_Size_5" property="filterStatus"
														style="margin-left: 15px;">
														<html:option value=""></html:option>
														<html:optionsCollection  property="statusValues" label="resultDescription" value="resultCode"/>
													</html:select>
												</td>
											</tr>
											<tr>
												<td>Mês/Ano de Atualização</td>
												<td>
													<html:text property="filterMonthYear"
														styleClass="ODS_Text_Field_Size_5"
														style="margin-left: 15px;"
														maxlength="7" onkeydown="maskDateMonthYear();" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
	
						<table class="ODS_internalWidth" border="0">
							<tbody>
								<tr>
									<td width="100%"></td>
									<td><html:button property="button" value="Consultar"
											onclick="submitActionNewCPB('search');"></html:button></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>			
			<table>
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="6">Resultado da Consulta</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div class="ODS_DivGridVerticalScroll">
								<table id="gridTable" width="100%" border="0">
									<tbody>
										<tr>
											<td>
												<div>
													<% if(formBean!=null && formBean.getResultList()!=null && !formBean.getResultList().isEmpty()){ %>
													<display:table name="sessionScope.StatusCpfCnpjForm.resultList" uid="vo" id="resultRow"
														pagesize="30" export="true" class="listaPaginada"
														style="width:100%" sort="list"
														requestURI="/FrontController/NEWCPB.StatusCpfCnpj?method=search">
														
														<display:column title="Nome do Cliente" property="customer.name" sortable="true" style="text-align:left;width:40%"/>		
														<display:column title="CPF/CNPJ" property="cpfCnpjNbrFormated" sortable="true" style="text-align:right;width:20%"/>														
														<display:column title="Mês/Ano da Última Atualização" property="cpfUpdMthYrText" sortable="true" style="text-align:right;width:20%"/>
														<display:column title="Status" property="cpfStatusText" sortable="true" style="text-align:right;width:20%"/>
				
														<display:setProperty name="export.pdf" value="false" />
														<display:setProperty name="export.csv" value="false" />
														<display:setProperty name="export.xml" value="false" />		
													</display:table>
													<%} %>
												</div>
											</td>	
										</tr>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</html:form>		
		<jsp:include page="/View/Util/Footer.jsp" flush="true"/>	
	</body>	
	<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>