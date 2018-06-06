<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.form.AccountComplementDataForm"%>

<html:html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="GENERATOR" content="IBM Software Development Platform">
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="AccountComplementData" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'" />
			<jsp:param name="approvalControlNames" value="'','','alterBtn'" />
		</jsp:include>
		<jsp:include page="/NewCPB/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		
		<title>Conta - Dados Complementares</title>
		
		<script language="javascript">
			function setSelectedKeys(selectRegisterAcctNbr, selectRegisterCpfCnpjNbr, selectRegisterAccountType ) {
				document.forms[0].selectRegisterAcctNbr.value = selectRegisterAcctNbr;
				document.forms[0].selectRegisterCpfCnpjNbr.value = selectRegisterCpfCnpjNbr;
				document.forms[0].selectRegisterAccountType.value = selectRegisterAccountType;
			};


		
		</script>
	</head>
	<body>
		<html:form action="/NEWCPB.AccountComplementData.do">
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="EG" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />
			
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               AccountComplementDataForm formBean = (AccountComplementDataForm)session.getAttribute("AccountComplementDataForm");
	         %>
			
			<html:hidden property="selectRegisterAcctNbr" />
			<html:hidden property="selectRegisterCpfCnpjNbr" />
			<html:hidden property="selectRegisterAccountType" />
			
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Conta - Dados Complementares</th>
								</tr>
							</thead>	
							<tbody>
								<tr><td colspan="3">&nbsp;</td></tr>
								
								<tr class="ODS_Detail_line1">
									<td colspan="6">										
										<table border="0">
											<tr>
												<TD>Número da Conta</TD>
												<TD><html:text property="filterNumberAccount" styleClass="ODS_Text_Field_Size_10" style="margin-left: 30px; margin-right: 70px;" onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="15"></html:text></TD>
											</tr>
											
											<tr>
												<td>CPF/CNPJ</td>
												<td colspan="3">
												
												<html:text property="filterNumberCpfCnpj" styleClass="ODS_Text_Field_Size_10" style="margin-left: 30px; margin-right: 70px;" maxlength="18" onblur="completeCpfCnpj(this);"></html:text></td>
											</tr>
											
											<tr>
												<td>Origem da Conta</td>
												<td>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<html:select styleClass="ODS_Select_Field_Size_10" property="filterAccountType" disabled="<%=formBean.isOnlyView() %>">
															<html:option value=""></html:option>
															<html:optionsCollection  property="accountTypeValues" label="resultDescription" value="resultCode"/>
													</html:select>
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
											onclick="submitActionNewCPB('search');"></html:button>		
									</td>
									<td align="left" width="44">
												<html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>


			<table>
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="7">Resultado da Consulta</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div class="ODS_DivGridVerticalScroll">
								<table id="gridTable" width="100%" border="0">
									<tbody>
									
										<logic:present name="resultListAccountComplementData">
											<td>
												<div>
													<display:table name="sessionScope.resultListAccountComplementData" uid="vo" id="resultRow"
														pagesize="30" export="true" class="listaPaginada"
														style="width:100%" sort="list"
														requestURI="/FrontController/NEWCPB.AccountComplementData?method=search">
		
														<bean:define name="resultRow" property="acctNbr" id="selectRegisterAcctNbr" type="java.lang.String"/>
														<bean:define name="resultRow" property="cpfCnpjNbr" id="selectRegisterCpfCnpjNbr" type="java.lang.String"/>
														<bean:define name="resultRow" property="accountType" id="selectRegisterAccountType" type="java.lang.String"/>
														
														<% if(user!=null && user.getUserAccess()!=null && (user.getUserAccess().isHasAccessNovoCPBContaAtualDadosCompdaConta()) ){ %>
														<display:column title="" media="html" style="text-align:center;width:2%">
															<a href="javascript:setSelectedKeys('<%=selectRegisterAcctNbr%>' , '<%=selectRegisterCpfCnpjNbr%>', '<%=selectRegisterAccountType%>');submitActionNewCPB('openAccountComplementUpdateView');">
																<img src='<%=request.getContextPath()%>/Common/image/update.gif' alt="" border="0">
															</a>		
														</display:column>
														<%} %>
														<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBContaConsDadosCompdaConta() ){ %>
														<display:column title="" media="html" style="text-align:center;width:2%">
															<a href="javascript:setSelectedKeys('<%=selectRegisterAcctNbr%>' , '<%=selectRegisterCpfCnpjNbr%>', '<%=selectRegisterAccountType%>');submitActionNewCPB('openAccountComplementView');">
																<img src='<%=request.getContextPath()%>/Common/image/lupa.gif' alt="" border="0">
															</a>		
														</display:column>
														<%} %>
		
														<display:column title="Número da Conta" property="acctNbr" sortable="true" style="text-align:center;width:15%"/>
														
														<display:column title="Origem" property="accountTypeDesc" sortable="true" style="text-align:center;width:10%"/>
		
														<display:column title="CPF/CNPJ" property="cpfCnpjNbr" sortable="true" style="text-align:center;width:20%"/>
		
														<display:column title="Data Ass. do Contrato" property="contractSignatureDate" sortable="true" style="text-align:center;width:20%"/>
														
														<display:column title="Possui Procurador?" property="hasAngentString" sortable="true" style="text-align:center;width:15%"/>
														
														<display:column title="Risco" property="riskLevelCode" sortable="true" style="text-align:center;width:10%"/>
														
														<display:setProperty name="export.pdf" value="false" />
														<display:setProperty name="export.csv" value="false" />
														<display:setProperty name="export.xml" value="false" />		
													</display:table>
												</div>
											</td>
										</logic:present>		
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