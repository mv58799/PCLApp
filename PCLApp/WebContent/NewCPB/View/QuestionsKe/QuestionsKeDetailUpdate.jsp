
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.enums.ScreenNamesEnum"%>
<%@ page import="com.citibank.newcpb.form.QuestionsKeForm"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>


<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		<jsp:include page="/NewCPB/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<script type="text/javascript" src="<%= request.getContextPath() %>/Common/js/jquery.js"></script>

		<TITLE>Questionário K&E</TITLE>
		
		<script language="javascript">
			function setSelectedKeys(selectNumberFile){
				document.forms[0].selectNumberFile.value = selectNumberFile;
			};
	 	</script>
	</HEAD>

	<body>
		<html:form action="/NEWCPB.QuestionsKe.do" enctype="multipart/form-data"  method="POST">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes" />
		    </jsp:include>
		   <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Product.ProductDetail.Update.Show" style="display:none"></html:text>
			<html:hidden property="findType" value="true"></html:hidden>
			
			<table class="ODS_mainTable" cellspacing="0">			
			
				<tr>
					<td width="100%">
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Conta - Incluir / Alterar Questionário K&E </th>
									
								</tr>
							</thead>
							<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
                                  String userId = user != null ? user.getUserID() : null;
                                  QuestionsKeForm formBean = (QuestionsKeForm)session.getAttribute("QuestionsKeForm");
                            %>
                            <tr><td>&nbsp;</td></tr>
							<tr>
								<td>
									<table class="ODS_internalWidth" border="0" cellspacing="0">			
										<tr><td>&nbsp;</td></tr>
										<tr class="ODS_Detail_Line1" id="infoBaseLegalPerson">			
											<td colspan="6">										
												<table>
													<tr>
														<TD>CPF/CNPJ</TD>
														<TD><html:text property="questionsKeVO.acctCmplVO.cpfCnpjNbrFormated" styleClass="ODS_Text_Field_Size_15" style="margin-left: 30px; margin-right: 70px;" 
															onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" maxlength="15" disabled="true"></html:text></TD>
													</tr>
													<tr>
														<TD>Nome do Cliente</TD>
														<TD><html:text property="questionsKeVO.acctCmplVO.customerName" styleClass="ODS_Text_Field_Size_60" style="margin-left: 30px; margin-right: 70px;" 
														  	disabled="true"></html:text></TD>
													</tr>
													<tr >
														<td>Número da Conta</td>
														<td>
															<html:text property="questionsKeVO.acctCmplVO.acctNbr"
																styleClass="ODS_Text_Field_Size_15"
																style="margin-left: 30px; margin-right: 70px;"
																maxlength="18" onblur="completeCpfCnpj(this);"
																disabled="true" />
														</td>
														<TD align="left">&nbsp;Risco</TD>
														<TD><html:text property="questionsKeVO.acctCmplVO.riskLevelCode" styleClass="ODS_Text_Field_Size_5" maxlength="60" 
															onkeyup="toUppercase(this)" onblur="toUppercase(this)" disabled="true"></html:text></TD>
													</tr>																				
													<tr>
														<TD>Nome do Banker</TD>
														<TD><html:text property="questionsKeVO.acctCmplVO.bankerName" styleClass="ODS_Text_Field_Size_60" style="margin-left: 30px; margin-right: 70px;" 
														 maxlength="15" disabled="true"></html:text></TD>
													</tr>
													
													<tr>
														<TD>Data da Última Revisão no IOS</TD>
														<TD><html:text property="questionsKeVO.acctCmplVO.lastIosRevDate" styleClass="ODS_Text_Field_Size_15" style="margin-left: 30px; margin-right: 70px;" 
														maxlength="10"  disabled="true"></html:text></TD>
													</tr>
												</table>																		
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
									</table>
									<table>
						             	<tr>
											<td>
												<DIV class="ODS_DivGridVerticalScroll">
													<table id="gridTable" width="100%" border="0">
														<tbody>
															<display:table name="sessionScope.questionsKeVOList" uid="vo" id="resultRow"  class="listaPaginada"  
																	     style="width:100%">
																	     
															 	<bean:define name="resultRow" property="colorBackground" id="color_background" type="java.lang.String"/>											 
																 <% 
																     String color_background_colun1 = "text-align:left;width:40%;padding: 2px;" + color_background;
																     String color_background_colun2 = "text-align:center;width:20%;padding: 2px;" + color_background;
																 %>
																 <display:column title="Produtos" property="questionName"  style="<%=color_background_colun1%>" >		 																					
																 </display:column>
																	  
																 <display:column title="Conhecimento e Experiência em Produtos" media="html"  style="<%=color_background_colun2%>">																								 	  
																	<logic:equal name="resultRow" value="true" property="needAnswer">																	
																		<html:select styleClass="ODS_Select_Field_Size_10" name="QuestionsKeForm" 
																			property='<%="questionsKeVO.questionsKeAnswVOList[" + (resultRow_rowNum -1) + "].prodAnswer" %>'
																			disabled="<%=formBean.isOnlyView() %>">
																			<html:option value=""></html:option>
																			<html:optionsCollection property="knowledgeExperienceProd" label="resultDescription" value="resultCode"/>
																		</html:select>
																	</logic:equal>
																</display:column>	
																
																<display:column title="Frequência Média ao Ano" media="html"  style="<%=color_background_colun2%>" >																								 	  
																	<logic:equal name="resultRow" value="true" property="needAnswer">	
																		<html:select styleClass="ODS_Select_Field_Size_10" name="QuestionsKeForm" 
																		property='<%="questionsKeVO.questionsKeAnswVOList[" + (resultRow_rowNum -1) + "].fmaAnswer" %>'
																		disabled="<%=formBean.isOnlyView() %>">
																			<html:option value=""></html:option>
																			<html:optionsCollection  property="averageFrequencyByYear" label="resultDescription" value="resultCode"/>
																	</html:select>
																	</logic:equal>
																</display:column>	
																
																<display:column title="Volume Médio ao Ano" media="html"  style="<%=color_background_colun2%>" >																								 	  
																	<logic:equal name="resultRow" value="true" property="needAnswer">									
																		<html:select styleClass="ODS_Select_Field_Size_10" name="QuestionsKeForm" 
																		property='<%="questionsKeVO.questionsKeAnswVOList[" + (resultRow_rowNum -1) + "].vmaAnswer" %>'
																		disabled="<%=formBean.isOnlyView() %>">
																			<html:option value=""></html:option>
																			<html:optionsCollection  property="averageVolumeByYear" label="resultDescription" value="resultCode"/>
																		</html:select>
																	</logic:equal>
																</display:column>		
															</display:table>									
														</tbody>
													</table>
												</DIV>					
											</td>
										</tr>
									</table>
									
							        <table class="ODS_internalWidth" border="0" width="100%">
										<thead>
											<tr>
												<th class="subtitle" scope="colgroup" colspan="3">Arquivos Anexados</th>
												
											</tr>
										</thead>	
										
										<logic:equal name="QuestionsKeForm" property="onlyView"  value="false" >
										<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBQuestionsKeAtual()){ %>
											<tbody>
											<tr>
												<td width="50%"></td>
												<td>
													<table border="0" width="80%" align="center">
														<tr>
															<td >
																<html:file property="file" size="100" styleClass="ODS_Text_Field_Size_60"  onkeypress="return false;" />
															</td>
															
															<td>
																<html:button property="button" value="UpLoad" onclick="submitActionNewCPB('upload');"></html:button>
															</td>
														</tr>
													</table>
												</td>
											</tr>	
											</tbody>
											<%} %>
										</logic:equal>
      								</table>
      								
      								<html:hidden property="selectNumberFile" />	
      								<table>
						             	<tr>
											<td>
												<DIV class="ODS_DivGridVerticalScroll">
													<table id="gridTableFile" width="100%" border="0">
														<tbody>
															<logic:equal name="QuestionsKeForm" property="questionsKeVO.hasQuestionsKeVO"  value="true" >
														
																<display:table name="sessionScope.QuestionsKeForm.questionsKeVO.fileList" uid="voList" id="vCust"  class="listaPaginada"  
																		     style="width:100%">
																		     
																      <bean:define name="vCust" property="fileSeq" id="selectNumberFile" type="java.lang.Integer"></bean:define>
 											 
							 											 <display:column title="" media="html"  style="text-align:center;width:2%" >																									  																							
																				<a href="javascript:setSelectedKeys('<%= selectNumberFile %>');submitDonwload('download');">
																				  <img src='<%= request.getContextPath() %>/Common/image/lupa.gif' alt="" border="0"></a>																			
																		 </display:column>																							    
									 								
										 								<logic:equal name="QuestionsKeForm" property="onlyView"  value="false" >	
										 								<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBQuestionsKeAtual()){ %>
										 									<display:column title="" media="html"  style="text-align:center;width:2%" >																								
																				 <a href="javascript:setSelectedKeys('<%= selectNumberFile %>');submitActionNewCPB('uploadDelete');">
																				  <img src='<%= request.getContextPath() %>/Common/image/delete.gif' alt="" border="0"></a>
																			 </display:column>										 								
										 								<%} %>								 								
										 								</logic:equal>
	
																		 
																																							
																		<display:column title="Nome Arquivo" property="fileName"  sortable="true" style="text-align:left;width:40%" >																								
																		</display:column>
																		
																		<display:column title="Data de Inclusao" property="createDateFormated"  sortable="true" style="text-align:center;width:20%" >																								
																		</display:column>
																		
																		<display:column title="Usuário" property="createUser"  sortable="true" style="text-align:center;width:20%" >																								
																		</display:column>
																		
																		<display:setProperty name="export.pdf" value="false" />   
																		<display:setProperty name="export.csv" value="false" />  
																		<display:setProperty name="export.xml" value="false" />
																</display:table>
															</logic:equal>									
														</tbody>
													</table>
												</DIV>					
											</td>
										</tr>
									</table>
								</td>
								<td>&nbsp;</td>
								

							</tr>
							
							<logic:equal name="QuestionsKeForm" property="approve" value="false" >	
								<logic:equal name="QuestionsKeForm" property="fromApprove" value="false" >								
									<tr>
										<td>									
											<table class="ODS_mainTable" cellspacing="0">									
												<tr class="ODS_Detail_Line1">
													<td>
														<table width="200px" cellpadding="0" cellspacing="0">
															<tr><td>&nbsp;</td></tr>																										
														</table>
														<table width="100%" cellpadding="0" cellspacing="0">
															<tr>	
																<td style="text-align:left; ">
																	Data da Última Atualização:	
																	<bean:write name="QuestionsKeForm" property="questionsKeVO.lastAuthDateFormatedDDMMYYYYAndUser"/>																						     		   																				
									     		   				</td>														
															</tr>																									
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>							
								</logic:equal>							
							</logic:equal>

							


							<logic:equal name="QuestionsKeForm" property="approve" value="false" >										
								<logic:equal name="QuestionsKeForm" property="onlyView" value="false" >
									<tr><td>&nbsp;</td></tr>
									<tr>
										<td colspan="3">
											<table class="ODS_internalWidth" border="0">
												<tbody>
													<tr>
														<td width="100%"></td>
														<TD>
														<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBQuestionsKeAtual()){ %>
															<html:button property="button" value="Salvar" onclick="submitActionNewCPB('save');"></html:button>
														<% }%>				
														</td>	
														
														<logic:equal name="QuestionsKeForm" property="fromApprove" value="false" >								
															<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
														</logic:equal>	
														
														<logic:equal name="QuestionsKeForm" property="fromApprove" value="true" >								
															<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
														</logic:equal>															
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</logic:equal>
								
								<logic:equal name="QuestionsKeForm" property="onlyView" value="true" >									
									<tr><td>&nbsp;</td></tr>
									<tr>
										<td colspan="3">
											<table class="ODS_internalWidth" border="0">
												<tbody>
													<tr>
														<td width="100%"></td>														
														<logic:equal name="QuestionsKeForm" property="fromApprove" value="false">								
															<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
														</logic:equal>	
														
														<logic:equal name="QuestionsKeForm" property="fromApprove" value="true">								
															<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
														</logic:equal>	
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</logic:equal>
							</logic:equal>
						</table>
					</td>
				</tr>
				<logic:equal name="QuestionsKeForm" property="approve" value="true" >
					<tr><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
					</tr>
					<tr><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3">
							<table width="100%">
								<tr class="ODS_Detail_Line1">
									<td width="33%">Usuário de Última Atualização</td>
									<td width="33%">Data/Hora de Última Atualização</td>
									<TD width="33%">Ação</TD>
								</tr>
								<tr class="ODS_Detail_Line2">
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="questionsKeVO.lastUpdUserSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="questionsKeVO.lastUpdDateFormatedSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_10" property="questionsKeVO.recStatCodeText" disabled="true"></html:text></TD>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="ODS_Detail_Line1"><td colspan="3">&nbsp;</td></tr>
					<tr class="ODS_Detail_Line2"><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3">
							<table class="ODS_internalWidth" border="0">
								<tbody>
									<tr>
										<td width="100%"></td>		
										<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBAprovRej(ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome())){ %>								
											<logic:equal name="QuestionsKeForm" property="approve" value="true" >
												<logic:notEqual name="QuestionsKeForm"  property="questionsKeVO.lastUpdUser" value="<%=userId%>">
													<td><html:button property="approvedBtn" value="Aprovar" onclick="submitActionNewCPB('approve');"></html:button></td>																					
												</logic:notEqual>
												<td><html:button property="rejectBtn" value="Reprovar" onclick="submitActionNewCPB('reprove');"></html:button></td>	
											</logic:equal>
										<% } %>
										<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></td>
									</TR>
								</tbody>
							</table>
						</td>
					</tr>
				</logic:equal>
				
				
				
			</table>
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
	
			

</html:html>
