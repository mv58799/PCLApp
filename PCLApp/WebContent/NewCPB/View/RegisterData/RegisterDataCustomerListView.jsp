<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.form.RegisterDataCustomerForm"%>


<html:html>
	<HEAD>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="RegisterDataCustomer"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>
		<TITLE>Cadastro Cliente</TITLE>
		
		
			<script language="javascript">

				function setSelectedKeys(customeGFCID){
					document.forms[0].selectCustomeGFCID.value = customeGFCID;
				};
		 </script>
	</HEAD>
	<body>
	
	
	<html:form action="/NEWCPB.RegisterDataCustomer.do">
	
		<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
			<jsp:param name="currentSheet" value="NewCPBSheet"/>
			<jsp:param name="currentSubSheet" value="Clientes" />
	    </jsp:include>
	    
	    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
		<html:hidden property="clrScreen" value="true"/>
		
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               RegisterDataCustomerForm formBean = (RegisterDataCustomerForm)session.getAttribute("RegisterDataCustomerForm");
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
									Dados Cadastrais</th>
							</tr>
						</thead>
						<tbody>
							<tr><td colspan="3">&nbsp;</td></tr>
							<tr class="ODS_Detail_line1">
								<td colspan="6">										
									<table>
										<tr>
											<TD>Número EM</TD>
											<TD><html:text property="numberEM" styleClass="ODS_Text_Field_Size_15"  maxlength="30" onkeyup="toUppercase(this)" onblur="toUppercase(this)"></html:text></TD>
											<TD align="left">&nbsp;Nome</TD>
											<TD><html:text property="name" styleClass="ODS_Text_Field_Size_60" maxlength="60" onkeyup="toUppercase(this)" onblur="toUppercase(this)"></html:text></TD>
										</tr>
										
										<tr>
											<td>Número GFCID</td>
											<td colspan="3"><html:text property="numberGFCID" styleClass="ODS_Text_Field_Size_15"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="11"></html:text></td>
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
			</table>
			
						<html:hidden property="selectCustomeGFCID" />
						
						
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
				 											     style="width:100%" sort="list" requestURI="/FrontController/NEWCPB.RegisterDataCustomer?method=search" >
				 											     
				 											 <bean:define name="vCust" property="numberGFCID" id="selectCustomeGFCID" type="java.lang.String"></bean:define>
				 											 
				 											 <% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBCliConsDadosCad()){ %>
				 											 <display:column title="" media="html"  style="text-align:center;width:1%" >																								
															
																  <a href="javascript:setSelectedKeys('<%= selectCustomeGFCID %>');submitActionNewCPB('view');">
																  <img src='<%= request.getContextPath() %>/Common/image/lupa.gif' alt="" border="0"></a>
															   	
															</display:column>	
															<%} %>
				 											    
				 											<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBCliAtualDadosCad()){ %>
				                                            <display:column title="" media="html"  style="text-align:center;width:1%" >																								
																 <a href="javascript:setSelectedKeys('<%= selectCustomeGFCID %>');submitActionNewCPB('viewEdit');">
																  <img src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0"></a>
															   	
															</display:column>	
															<%} %>
																									
											
															<display:column title="Nome" property="name"  sortable="true" style="text-align:left;width:10%" >																								
															</display:column>
															
															<display:column title="Número EM" property="numberEM" sortable="true" style="text-align:right;width:5%">																				
															</display:column>
															
															<display:column title="Número GFCID" property="numberGFCID" sortable="true" style="text-align:right;width:5%">																																				
															</display:column>
															
															<display:column title="CPF / CNPJ" property="cpfCnpj" sortable="true" style="text-align:right;width:5%">																																				
															</display:column>
															
															<display:column title="Nome do Banker" property="nameBanker" sortable="true" style="text-align:left;width:10%">																																				
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