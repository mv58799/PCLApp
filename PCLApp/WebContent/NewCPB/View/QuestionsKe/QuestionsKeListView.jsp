<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.form.QuestionsKeForm"%>
<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="QuestionsKe"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>
		<TITLE>Questionário K&E</TITLE>
				
		<script language="javascript">
		function setSelectedKeys(numberAccount, numberCpfCnpj){
			document.forms[0].selectNumberAccount.value = numberAccount;
			document.forms[0].selectCpfCnpj.value = numberCpfCnpj;
		};
	 	</script>
	</HEAD>
	<body>	
	
	<html:form action="/NEWCPB.QuestionsKe.do">	
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="NewCPBSheet"/>
			<jsp:param name="currentSubSheet" value="Conta" />
	    </jsp:include>	    
	    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
		<html:hidden property="clrScreen" value="true"/>		
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               QuestionsKeForm formBean = (QuestionsKeForm)session.getAttribute("QuestionsKeForm");
	         %>
		<table class="ODS_mainTable" cellspacing="0" width="100%">
			<tr>
				<td>&nbsp;</td>
				<td>
					<html:text property="backURL" value="CustomerPrvt.CustomerPrvtList.List.Show" style="display:none"></html:text> 		
					<table class="ODS_internalWidth">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="3">
									Conta - Consultar Questionário K&E
								</th>
							</tr>
						</thead>
						<tbody>
							<tr><td colspan="3">&nbsp;</td></tr>
							<tr class="ODS_Detail_line1">
								<td colspan="6">										
									<table>
										<tr>
											<TD>Número da Conta</TD>
											<TD><html:text property="filterNumberAccount" styleClass="ODS_Text_Field_Size_15" style="margin-left: 30px; margin-right: 70px;" onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="15"></html:text></TD>
										</tr>
										<tr>
											<td>CPF/CNPJ</td>
											<td>
												<html:text property="filterCpfCnpj"
													styleClass="ODS_Text_Field_Size_15"
													style="margin-left: 30px; margin-right: 70px;"
													maxlength="18" onblur="completeCpfCnpj(this);" />
											</td>
											<TD align="left">&nbsp;Nome</TD>
											<TD><html:text property="filterName" styleClass="ODS_Text_Field_Size_60" maxlength="60" onkeyup="toUppercase(this)" onblur="toUppercase(this)"></html:text></TD>
										</tr>																				
										<tr>
											<td>Número EM</td>
											<td>
												<html:text property="filterNumberEM"
													styleClass="ODS_Text_Field_Size_15"
													style="margin-left: 30px; margin-right: 70px;"
													maxlength="30" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
											</td>
										</tr>										
										<tr>
											<td>Número GFCID</td>
											<td>
												<html:text property="filterNumberGFCID"
													styleClass="ODS_Text_Field_Size_15"
													style="margin-left: 30px; margin-right: 70px;"
													onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="11"/>
											</td>
										</tr>
									</table>																		
								</td>
							</tr>
						</tbody>
					</table>	
					
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%"></td>
								<TD><html:button property="button" value="Consultar" onclick="submitActionNewCPB('search');"></html:button></TD>
							</TR>
						</TBODY>	
					</TABLE>					
				</td>
			</tr>	
		</table>
			
		<html:hidden property="selectNumberAccount" />	
		<html:hidden property="selectCpfCnpj" />		
		<table>
			<thead><tr><th class="subtitle" scope="colgroup" colspan="6">Resultado da Consulta</th></tr></thead>
            	<tr>
				<td>
					<DIV class="ODS_DivGridVerticalScroll">
						<table id="gridTable" width="100%" border="0">
							<tbody>
								<logic:present name="lista">
								      <td>
								    	<div>
											<display:table name="sessionScope.lista" uid="vo" id="vCust" pagesize="30" export="true" class="listaPaginada"  
	 											     style="width:100%" sort="list" requestURI="/FrontController/NEWCPB.QuestionsKe?method=search" >
	 											     
	 											 <bean:define name="vCust" property="acctNbr" id="selectNumberAccount" type="java.lang.String"></bean:define>
	 											 <bean:define name="vCust" property="cpfCnpjNbr" id="selectCpfCnpj" type="java.lang.String"></bean:define>
	 											 
	 											 <% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBQuestionsKeCons()){ %>
	 											 <display:column title="" media="html"  style="text-align:center;width:1%" >																									  
													<logic:equal name="vCust" value="true" property="hasQuestionsKe">				
														<a href="javascript:setSelectedKeys('<%= selectNumberAccount %>' , '<%= selectCpfCnpj %>');submitActionNewCPB('view');">
														  <img src='<%= request.getContextPath() %>/Common/image/lupa.gif' alt="" border="0"></a>
													</logic:equal>
												 </display:column>	
												 <%} %>
	 											    
	 											 <% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBQuestionsKeAtual()){ %>
	                                             <display:column title="" media="html"  style="text-align:center;width:1%" >																								
													 <a href="javascript:setSelectedKeys('<%= selectNumberAccount %>' ,'<%= selectCpfCnpj %>');submitActionNewCPB('viewEdit');">
													  <img src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0"></a>
											   	
												 </display:column>	
												 <%} %>
																																	
												<display:column title="Nome" property="customerName"  sortable="true" style="text-align:left;width:15%" >																								
												</display:column>
												
												<display:column title="Número EM" property="emNbr" sortable="true" style="text-align:right;width:5%">																				
												</display:column>
												
												<display:column title="Número GFCID" property="gfcid" sortable="true" style="text-align:right;width:5%">																																				
												</display:column>
												
												<display:column title="CPF / CNPJ" property="cpfCnpjNbrFormated" sortable="true" style="text-align:right;width:7%">																																				
												</display:column>
												
												<display:column title="Número da Conta" property="acctNbr" sortable="true" style="text-align:left;width:10%">																																				
												</display:column>
												
												<display:column title="Risco" property="riskLevelCode" sortable="true" style="text-align:center;width:5%">																																				
												</display:column>
												
												<display:column title="Possui Questionário" property="hasQuestionsKeString" sortable="true" style="text-align:center;width:8%">																																				
												</display:column>
												
												<display:setProperty name="export.pdf" value="false" />   
												<display:setProperty name="export.csv" value="false" />  
												<display:setProperty name="export.xml" value="false" />
											</display:table>	
								    	</div>	
								     </td>						
						         </logic:present>								
							</tbody>
						</table>
					</DIV>					
				</td>
			</tr>
		</table>
	</html:form> 				
	<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>