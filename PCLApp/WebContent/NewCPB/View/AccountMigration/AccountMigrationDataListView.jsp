<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.form.AccountMigrationDataForm"%>

<html:html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="GENERATOR" content="IBM Software Development Platform">
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="AccountMigration" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'" />
			<jsp:param name="approvalControlNames" value="'','','alterBtn'" />
		</jsp:include>
		<jsp:include page="/NewCPB/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		
		<title>Contas - Consultar Contas Migradas (De-Para)</title>
		
		<script language="javascript">
			function setSelectedKeys(selectRegisterAccountGrbNumber, selectRegisterAccountCustodiaNumber, selectRegisterAccountCustodiaCpfCnpjNumber ) {
				document.forms[0].selectRegisterAccountGrbNumber.value = selectRegisterAccountGrbNumber;
				document.forms[0].selectRegisterAccountCustodiaNumber.value = selectRegisterAccountCustodiaNumber;
				document.forms[0].selectRegisterAccountCustodiaCpfCnpjNumber.value = selectRegisterAccountCustodiaCpfCnpjNumber;
			};


		
		</script>
	</head>
	<body>
		<html:form action="/NEWCPB.AccountMigration.do">
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="EG" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />
			
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               AccountMigrationDataForm formBean = (AccountMigrationDataForm)session.getAttribute("AccountMigrationDataForm");
	         %>
	         
	        <html:hidden property="selectRegisterAccountGrbNumber" />
			<html:hidden property="selectRegisterAccountCustodiaNumber" />
			<html:hidden property="selectRegisterAccountCustodiaCpfCnpjNumber" />
			
			
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Contas - Consultar Contas Migradas (De-Para)</th>
								</tr>
							</thead>	
							<tbody>
								<tr><td colspan="3">&nbsp;</td></tr>
								
								<tr class="ODS_Detail_line1">
									<td colspan="6">										
										<table border="0">
											<tr>
												<TD>Conta Corrente Citibank</TD>
												<TD><html:text property="filterAccountCitiBankNumber" styleClass="ODS_Text_Field_Size_10" style="margin-left: 30px; margin-right: 70px;" onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="15"></html:text></TD>
											</tr>
											
											<tr>
												<TD>Conta Custódia</TD>
												<TD><html:text property="filterAccountCustodiaNumber" styleClass="ODS_Text_Field_Size_10" style="margin-left: 30px; margin-right: 70px;" onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="15"></html:text></TD>
											</tr>
											
											
											<tr>
												<td>CPF/CNPJ</td>
												<td colspan="3">
												
												<html:text property="filterAccountCustodiaCpfCnpjNumber" styleClass="ODS_Text_Field_Size_10" style="margin-left: 30px; margin-right: 70px;" maxlength="18" onblur="completeCpfCnpj(this);"></html:text></td>
											</tr>
											
											<tr>
												<TD>Customer Number</TD>
												<TD><html:text property="filterCustomerNumber" styleClass="ODS_Text_Field_Size_10" style="margin-left: 30px; margin-right: 70px;" onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="11"></html:text></TD>
											</tr>
											
											<tr>
												<TD>Número EM</TD>
												<TD><html:text property="filterEmNumber" styleClass="ODS_Text_Field_Size_20" style="margin-left: 30px; margin-right: 70px;" maxlength="30" onkeyup="toUppercase(this)" onblur="toUppercase(this)"></html:text></TD>
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
						<th class="subtitle" scope="colgroup" colspan="6">Resultado da Consulta</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div class="ODS_DivGridVerticalScroll">
								<table id="gridTable" width="100%" border="0">
									<tbody>
									
										<logic:present name="resultListAccountMigrationData">
											<td>
												<div>
													<display:table name="sessionScope.resultListAccountMigrationData" uid="vo" id="resultRow"
														pagesize="30" export="true" class="listaPaginada"
														style="width:100%" sort="list"
														requestURI="/FrontController/NEWCPB.AccountMigration?method=search">
		
														<bean:define name="resultRow" property="accountGrbNumber" id="selectRegisterAccountGrbNumber" type="java.lang.String"/>
														<bean:define name="resultRow" property="accountCustodiaNumber" id="selectRegisterAccountCustodiaNumber" type="java.lang.String"/>
														<bean:define name="resultRow" property="accountCustodiaCpfCnpjNumber" id="selectRegisterAccountCustodiaCpfCnpjNumber" type="java.lang.String"/>
														
														<% if(user!=null && user.getUserAccess()!=null && (user.getUserAccess().isHasAccessNovoCPBContaInsContasMigradas() || user.getUserAccess().isHasAccessNovoCPBContaConsContasMigradas())){ %>
														<display:column title="" media="html" style="text-align:center;width:2%">
															<a href="javascript:setSelectedKeys('<%=selectRegisterAccountGrbNumber%>' , '<%=selectRegisterAccountCustodiaNumber%>', '<%=selectRegisterAccountCustodiaCpfCnpjNumber%>');submitActionNewCPB('viewUpdate');">
																<img src='<%=request.getContextPath()%>/Common/image/update.gif' alt="" border="0">
															</a>				
														</display:column>
														<%} %>	
														
														
														<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBContaDelContasMigradas()){ %>
														<display:column title="" media="html" style="text-align:center;width:2%">	
															<a href="javascript:setSelectedKeys('<%=selectRegisterAccountGrbNumber%>' , '<%=selectRegisterAccountCustodiaNumber%>', '<%=selectRegisterAccountCustodiaCpfCnpjNumber%>');submitActionNewCPB('confirmDeleteAccountMigrate');">
																<img src='<%=request.getContextPath()%>/Common/image/delete.gif' alt="" border="0">
															</a>
														</display:column>
														<%} %>	
														
		
														<display:column title="Número EM" property="emNumber" sortable="true" style="text-align:center;width:15%"/>
																								
														<display:column title="CPF/CNPJ" property="accountCustodiaCpfCnpjNumber" sortable="true" style="text-align:center;width:10%"/>
														
														<display:column title="Nome do Cliente" property="accountGrbName" sortable="true" style="text-align:center;width:39%"/>
		
														<display:column title="Conta Corrente" property="accountGrbNumber" sortable="true" style="text-align:center;width:12%"/>
														
														<display:column title="Conta Custódia" property="accountCustodiaNumber" sortable="true" style="text-align:center;width:12%"/>
														
														<display:column title="Data Migração" property="migrationDateString" sortable="true" style="text-align:center;width:11%"/>
														
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
			
				<% if(formBean!=null && formBean.isShowConfirmDeletePopup()){ %>
		
				<script type="text/javascript">	
					
					function hidModal(){
						try{
							document.getElementById('modalConfirmDelete').style.display = "none";
						}catch (e){
							console.log(e);
						}
						return false;
					}
				
				</script>
				
				<div id="modalConfirmDelete" class="modal">				
				  <!-- Modal content -->
				  <div class="modal-content" style="width: 35%;">
				    <span class="close" onclick="hidModal()">&times;</span>
				    <p>Deseja excluir a Migração da Conta Corrente Citibank: <%=formBean.getSelectRegisterAccountGrbNumber()%> 
				    e Conta Custodia: <%=formBean.getSelectRegisterAccountCustodiaNumber() %> ?</p>
				    <div>
					    <button type="button" class="modalButton" onclick="javascript:submitActionNewCPB('deleteAccountMigrate');">Sim</button>
						<button type="button" id="buttonNao" class="modalButton" style="float:right" onclick="hidModal();">Não</button>
					</div>				    
				  </div>			
				</div>
			<% } %>
			
		</html:form>		
		<jsp:include page="/View/Util/Footer.jsp" flush="true"/>	
	</body>	
	<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>