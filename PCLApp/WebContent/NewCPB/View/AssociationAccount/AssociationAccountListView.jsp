<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.form.AssociationAccountForm"%>

<html:html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="GENERATOR" content="IBM Software Development Platform">
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="AssociationAccount" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'" />
			<jsp:param name="approvalControlNames" value="'','','alterBtn'" />
		</jsp:include>
		
		<title>Associação conta x EG</title>
		
		
		<script language="javascript">
			function setSelectedKeys(selectRegisterErNbr, selectRegisterEgNbr, selectRegisterAcctNbr ) {
				document.forms[0].selectNumberER.value = selectRegisterErNbr;
				document.forms[0].selectNumberEG.value = selectRegisterEgNbr;
				document.forms[0].selectNumberAccount.value = selectRegisterAcctNbr;
			};

		</script>
	</head>
	<body>
		<html:form action="/NEWCPB.AssociationAccount.do">
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="EG" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />
			
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               AssociationAccountForm formBean = (AssociationAccountForm)session.getAttribute("AssociationAccountForm");
	         %>
			
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consultar Contas x EG</th>
								</tr>
							</thead>	
							<tbody>
								<tr><td colspan="3">&nbsp;</td></tr>
								
								<tr class="ODS_Detail_line1">
									<td colspan="6">										
										<table>
											<tr>
												<TD>Número ER</TD>
												<TD><html:text property="filterNumberER" styleClass="ODS_Text_Field_Size_10" style="margin-left: 30px; margin-right: 70px;" maxlength="30" onkeyup="toUppercase(this)" onblur="toUppercase(this)"></html:text></TD>
											</tr>
											
											<tr>
												<td>Número EG</td>
												<td colspan="3"><html:text property="filterNumberEG" styleClass="ODS_Text_Field_Size_5" style="margin-left: 30px; margin-right: 70px;" maxlength="4" onkeyup="toUppercase(this)" onblur="toUppercase(this)"></html:text></td>
											</tr>
											
											<tr>
												<td>Número da Conta</td>
												<td>
													<html:text property="filterNumberAccount"
														onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)"
														styleClass="ODS_Text_Field_Size_10"
														style="margin-left: 30px; margin-right: 70px;"
														maxlength="15" />
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
	
			
			<html:hidden property="selectNumberER" />
			<html:hidden property="selectNumberEG" />
			<html:hidden property="selectNumberAccount" />
			<html:hidden property="selectRegisterID" />

			<table>
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="4">Resultado da Consulta</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div class="ODS_DivGridVerticalScroll">
								<table id="gridTable" width="100%" border="0">
									<tbody>
									
										<logic:present name="listEM">
											<td>
												<div>
													<display:table name="sessionScope.listEM" uid="vo" id="resultRow"
														pagesize="30" export="true" class="listaPaginada"
														style="width:100%" sort="list"
														requestURI="/FrontController/NEWCPB.AssociationAccount?method=search">
		
														<bean:define name="resultRow" property="erNbr" id="selectRegisterErNbr" type="java.lang.String"/>
														<bean:define name="resultRow" property="egNbr" id="selectRegisterEgNbr" type="java.lang.String"/>
														<bean:define name="resultRow" property="acctNbr" id="selectRegisterAcctNbr" type="java.lang.String"/>
																																					
														<display:column title="Cliente da Conta" property="customerName" sortable="true" style="text-align:left;width:40%"/>
														
														<display:column title="ER" property="erNbr" sortable="true" style="text-align:center;width:20%"/>
		
														<display:column title="EG" property="egNbr" sortable="true" style="text-align:right;width:10%"/>
		
														<display:column title="Número Conta" property="acctNbr" sortable="true" style="text-align:right;width:20%"/>
														
				
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