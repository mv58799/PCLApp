<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.form.RegisterAuthorizedForm"%>

<html:html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="GENERATOR" content="IBM Software Development Platform">
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="RegisterAuthorized" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'" />
			<jsp:param name="approvalControlNames" value="'','','alterBtn'" />
		</jsp:include>
		<jsp:include page="/NewCPB/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		
		<title>Cliente - Consulta Cadastro de Autorizados</title>	
		<script language="javascript">
			function setSelectedKeys(emNbr) {
				document.forms[0].selectedEmNbr.value = emNbr;
			};
		</script>
	</head>
	<body>
		<html:form action="/NEWCPB.RegisterAuthorized.do">
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="Clientes" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               RegisterAuthorizedForm formBean = (RegisterAuthorizedForm)session.getAttribute("RegisterAuthorizedForm");
	         %>
	         
	         <html:hidden property="selectedEmNbr" value="" />
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Cliente - Consulta Cadastro de Autorizados</th>
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
														style="margin-left: 30px; margin-right: 70px;"
														maxlength="30" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>
											</tr>
											<tr>
												<td>CPF/CNPJ</td>
												<td>
													<html:text property="filterCpfCnpj"
														styleClass="ODS_Text_Field_Size_10"
														style="margin-left: 30px; margin-right: 70px;"
														maxlength="18" onblur="completeCpfCnpj(this);" />
												</td>
											</tr>
											<tr>
												<td>Nome</td>
												<td>
													<html:text property="filterName"
														styleClass="ODS_Text_Field_Size_60"
														style="margin-left: 30px; margin-right: 70px;"
														maxlength="60" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
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
													<display:table name="sessionScope.RegisterAuthorizedForm.resultList" uid="vo" id="resultRow"
														pagesize="30" export="true" class="listaPaginada"
														style="width:100%" sort="list"
														requestURI="/FrontController/NEWCPB.RegisterAuthorized?method=search">
		
														<bean:define id="emNbr" name ="resultRow" property="emNbr" type="java.lang.String"/>
														
														<display:column title="" media="html" style="text-align:center;width:2%">
															<a href="javascript:setSelectedKeys('<%=emNbr%>');submitActionNewCPB('viewEdit');">
																<img src='<%=request.getContextPath()%>/Common/image/update.gif' alt="" border="0">
															</a>
														</display:column>
		
														<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBCliDelCadAut()){ %>
														<display:column title="" media="html" style="text-align:center;width:2%">
																<a href="javascript:setSelectedKeys('<%=emNbr%>');submitActionNewCPB('delete');">
																	<img src='<%=request.getContextPath()%>/Common/image/delete.gif' alt="" border="0">
																</a>	
														</display:column>
														<%} %>
														
														<display:column title="Número EM" property="emNbr" sortable="true" style="text-align:left;width:25%"/>
		
														<display:column title="CPF/CNPJ" property="cpfCnpjNbr" sortable="true" style="text-align:right;width:25%"/>
														
														<display:column title="Nome" property="authPersnName" sortable="true" style="text-align:right;width:46%"/>
				
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
				    <p>Deseja excluir o Cadastro de Terceiros do EM <%=formBean.getSelectedEmNbr()%> ?</p>
				    <div>
					    <button type="button" class="modalButton" onclick="javascript:setSelectedKeys('<%=formBean.getSelectedEmNbr()%>');submitActionNewCPB('deleteConfirm');">Sim</button>
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