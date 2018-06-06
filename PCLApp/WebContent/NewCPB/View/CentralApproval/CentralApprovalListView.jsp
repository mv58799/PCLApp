<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.newcpb.form.CentralApprovalForm"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="java.util.ArrayList"%>


<html:html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM Software Development Platform">
<meta http-equiv="Content-Style-Type" content="text/css">

<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%= request.getContextPath() %>/Common/js/centralApproval.js"></script>

<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="CentralApproval"/>
		<jsp:param name="gridId" value="gridTable"/>
		<jsp:param name="headerId" value="gridHeader"/>
		<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
		<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
</jsp:include>

<title>Consulta de Aprovação Centralizada</title>
</head>
<body>
	<html:form action="/NEWCPB.CentralApproval.do">
		<%
			String curSheet = request.getParameter("currentSheet");
		%>
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="NewCPBSheet" />
		</jsp:include>
		<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
		<html:text property="backURL" value="CentralApproval" style="display:none"></html:text>
		<table class="ODS_mainTable" cellspacing="0">
			<tr>
				<td>&nbsp;</td>
				<td>
					<table class="ODS_internalWidth" border="0">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="3">
									Consulta de Aprova&ccedil;&atilde;o Centralizada</th>
							</tr>
						</thead>
						<tr>
							<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<td width="6%">Processo</td>
											<td width="1781">Usuário de Última Atualização</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<td width="6%"><html:select
													property="moduleProcessTextFilter"
													styleClass="ODS_Select_Field_Size_30">
													<html:option value=""></html:option>
													<html:options property="moduleProcessDomainList" />
												</html:select></td>
											<td colspan="20"><html:text property="userIdFilter"
													styleClass="ODS_Text_Field_Size_30" maxlength="50" /></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
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
										<tr>
											<td width="100%">&nbsp;</td>
											<td width="6%">&nbsp;</td>
											<td align="right">&nbsp;</td>
											<td align="right">
												<html:button property="listBtn" value="Consultar" onclick="submitActionNewCPB('search');"></html:button>
											</td>
											<td align="left" width="44">
												<html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button>
											</td>
										</tr>
									</tbody>
								</table>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<html:hidden property="selectedCode" value="" />
				<html:hidden property="selectedModuleCode" value="" />
				<td>&nbsp;</td>
				<td>

					<table border="0">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="3">
									Resultado da Consulta</th>
							</tr>
						</thead>
						<tr>
							<td>
							
								<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
                                  String userId = user != null ? user.getUserID() : null; %>
                                
								<div class=ODS_DivGridVerticalScroll>
									<table id="gridTable" width="100%" border="0">
										<tbody>

											<%											
												CentralApprovalForm frm = (CentralApprovalForm) request.getSession().getAttribute("CentralApprovalForm");
												ArrayList listTest = (ArrayList) request.getSession().getAttribute("lista");
														String sort = (String) request.getParameter("ordenar");

														if (listTest == null || sort == null) {															
															request.getSession().setAttribute("lista", frm.getResultList());
														}
														
												if(frm.getResultList()!=null && !frm.getResultList().isEmpty()){														
											%>
												<logic:present name="lista">
													<td>
														<div>
															<display:table name="sessionScope.lista" uid="vo"
																id="resultRow" pagesize="30" export="true"
																class="listaPaginada"
																style="width:100%" sort="list"
																requestURI="/FrontController/NEWCPB.CentralApproval?method=search">
																
																	<bean:define id="lastUpdUserId" name ="resultRow" property="lastUpdUserIdSafe" type="java.lang.String"/>
																	<bean:define id="opernCode" name ="resultRow" property="recStatCodeSafe" type="java.lang.String"/>
																	<bean:define id="processCode" name ="resultRow" property="processCodeSafe" type="java.lang.String"/>
																	<bean:define id="moduleProcessText" name ="resultRow" property="moduleProcessTextSafe" type="java.lang.String"/>
																	
																	<display:column title="" media="html"  style="text-align:center;width:3%" >
																		<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBAprovRej(moduleProcessText)){ %>
																			<a href="javascript:setKeys('<%=processCode%>','<%=moduleProcessText%>');disableButtons(false);submitActionNewCPB('approveReject');"/>
																				<img  title='Aprovar/Rejeitar' src='<%= request.getContextPath() %>/Common/image/GreenCircle.gif' alt="" border="0">
																			</a>
																		<% } %>
																	</display:column>
																	
																	<logic:equal name="lastUpdUserId" value="<%=userId%>">
																	  
																	  <logic:notEqual name="opernCode" value="I">											    
																	    <display:column title="" media="html"  style="text-align:center;width:3%" >	
																		  	<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBEdit(moduleProcessText)){ %>
																			  <a href="javascript:setKeys('<%=processCode%>','<%=moduleProcessText%>');submitActionNewCPB('viewEdit');"/>
																		      <img  title='Alteração' src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0">
																		      </a>															
																	    	<% } %>
																	    </display:column>												    												  
																	  </logic:notEqual>
																	  
																	  <logic:equal name="opernCode" value="I">
																	    <display:column title="" media="html"  style="text-align:center;width:3%" >	
																	    </display:column>											  
																	  </logic:equal>												  
																	  
																	</logic:equal>
																	<logic:notEqual name="lastUpdUserId" value="<%=userId%>"> 
																	  <display:column title="" media="html"  style="text-align:center;width:3%" >	
																	    </display:column>										
																	</logic:notEqual>
		
																	<display:column title="Processo" property="moduleProcessText"
																		style="text-align:left;width:20%">
																	</display:column>
		
																	<display:column title="Descrição do Processo" property="processText"
																		style="text-align:left;width:18%">
																	</display:column>
		
																	<display:column title="Última Atualização" property="lastUpdUserId"
																		style="text-align:left;width:7%">
																	</display:column>
		
																	<display:column title="Data/Hora de Última Atualização" property="lastUpdDateFormated" 
																		style="text-align:center;width:10%">
																	</display:column>
		
																	<display:column title="Ação" property="recStatCodeText"
																		style="text-align:left;width:05%">
																	</display:column>
		
																	<display:setProperty name="export.pdf" value="false" />
																	<display:setProperty name="export.csv" value="false" />
																	<display:setProperty name="export.xml" value="false" />
															</display:table>
														</div>
													</td>
												</logic:present>
											<% }%>

										</tbody>
									</TABLE>
								</div>
							</td>
						</tr>
					</TABLE>

				</td>
			</tr>
			<jsp:include page="/View/Util/Footer.jsp" flush="true" />
		</table>
	</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>