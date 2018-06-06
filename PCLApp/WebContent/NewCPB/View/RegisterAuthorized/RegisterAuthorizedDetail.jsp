<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.enums.ScreenNamesEnum"%>
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
		
		<title>Cadastro de Autorizados</title>
		<script language="javascript">
			function setSelectedKeys(acctNbr, authInd) {
				document.forms[0].selectedAcctNbr.value = acctNbr;
				document.forms[0].selectedAuthInd.value = authInd;
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
			<html:text property="idDiffList" disabled="true" style="display:none"></html:text>
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               RegisterAuthorizedForm formBean = (RegisterAuthorizedForm)session.getAttribute("RegisterAuthorizedForm");
	         %>
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<html:hidden property="selectedAcctNbr" value="" />
						<html:hidden property="selectedAuthInd" value="" />
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">									
										<%=formBean.getScreenTitle()%>
									</th>
								</tr>
							</thead>	
							<tbody>
								<tr class="ODS_Detail_line1">
									<td>
										<table width="100%">
											<tr>
												<td width="11%">Número EM</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegister.emNbr" disabled="<%=formBean.isOnlyView() || formBean.isUpdate() %>" maxlength="30" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>												
											</tr>
											<tr>
												<td>CPF/CNPJ</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegister.cpfCnpjNbr"  maxlength="18" onblur="completeCpfCnpj(this);" disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>
											<tr>
												<td>Nome</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_50" property="selectRegister.authPersnName" disabled="<%=formBean.isOnlyView() %>" maxlength="60" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>	
											</tr>
											<tr>
												<td>Documento de Identidade &nbsp;&nbsp; </td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_15" property="selectRegister.docId"  maxlength="20" disabled="<%=formBean.isOnlyView() %>" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													Data de nascimento
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegister.birthDate" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>						
											<tr>
												<td>
													Endereço
												</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_50" property="selectRegister.street"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												
													Bairro
													<html:text styleClass="ODS_Text_Field_Size_30" property="selectRegister.neighborhood" maxlength="80" disabled="<%=formBean.isOnlyView() %>" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>
											</tr>					
											<tr>
												<td>
													Cidade
												</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_40" property="selectRegister.city" maxlength="80" disabled="<%=formBean.isOnlyView() %>" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>	
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 	UF
													<html:text styleClass="ODS_Text_Field_Size_5" property="selectRegister.uf" maxlength="2" disabled="<%=formBean.isOnlyView() %>" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
													&nbsp;&nbsp;
													CEP
													<html:text styleClass="ODS_Text_Field_Size_20" property="selectRegister.zipCode"  maxlength="8" disabled="<%=formBean.isOnlyView() %>" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>	
												</td>
											</tr>					
											<tr>
												<td>Profissão</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_50" property="selectRegister.profText" maxlength="50" disabled="<%=formBean.isOnlyView() %>" onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>
											</tr>
										</table>
										
										<logic:equal name="RegisterAuthorizedForm" property="onlyView" value="false" >
										
											
											<table>
											<thead>
											   <tr>
											      <th class="subtitle" style="font-size: 11px; color: black;" scope="colgroup" colspan="6">Contas Outorgantes</th>
											   </tr>
											</thead>	
												<tr>
													<td>
														<table>
															<tr>
																<td>
																	<table>																		
																		<tr>
																			<td>
																				<table>												
																					<tr>
																					    <td>
																							<html:radio styleClass="radio" property="selectRegister.authInd" value="P" disabled="<%=formBean.isOnlyView() %>"/>									     		   					
															     		   				</td>
															     		   				
															     		   				<td>
															     		   					Procurador
															     		   				</td>
																					</tr>
																					<tr>
																						<td>																											
																							<html:radio styleClass="radio" property="selectRegister.authInd" value="A" disabled="<%=formBean.isOnlyView() %>"/>								     		   					
															     		   				</td>	
															     		   				<td>
															     		   					Pessoa Autorizada
															     		   				</td>
																					</tr>	
																				</table>
																			</td>
																		</tr>	
																		
																		<tr>
																			<td>Data de vigência</td>
																			<td>
																				<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegister.effectiveDate" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>"/>
																				&nbsp;&nbsp;(No caso de prazo indeterminado, não preencher)
																			</td>
																		</tr>
																	</table>															
																</td>
															     <td>
																	<table>
																		<tr>
																			<td style="padding-bottom: 30px;">Observações</td>
																			<td>
																				<html:textarea onkeyup="limitarTamanho(this.value,1000,'sprestante')" 
																				onblur="limitarTamanho(this.value,1000,'sprestante')" 
																				onkeypress="limitarTamanho(this.value,1000,'sprestante')"  property="selectRegister.comments" rows="4" style="width: 300px;" disabled="<%=formBean.isOnlyView() %>"/>
																					<br> <span id="sprestante" style="font-family:Georgia;"></span>																	
																			</td>
																		</tr>																
																	</table>														
																</td>													
															</tr>												
														</table>
													</td>
												</tr>
											</table>
										</logic:equal>
									</td>
								</tr>
								<tr class="ODS_Detail_Line3">
									<td>
										<logic:equal name="RegisterAuthorizedForm" property="onlyView" value="false" >
											<table>	
												<tr>
													<td>Contas Outorgantes&nbsp;&nbsp;&nbsp;&nbsp;</td>
													<td>
														<html:text property="filterAccountList"
															styleClass="ODS_Text_Field_Size_80"
															maxlength="255" 
															disabled="<%=formBean.isOnlyView() %>"/>
															
														<html:button property="approvedBtn" value="&nbsp; Inserir Contas &nbsp;" onclick="submitActionNewCPB('insertAccountList');"/>
													</td>
												</tr>
												<tr>
													<td colspan="2" style="font-size: 9px">(Contas separadas por vírgula)</td>
												</tr>
											</table>
										</logic:equal>
										<table width="100%">																
											 <!--<thead>
												<tr>
													<th class="subtitle" style="font-size: 11px; color: black;" scope="colgroup" colspan="6">Contas Outorgantes</th>
												</tr>
											</thead>  -->
											<tbody>														
												<tr>
													<td>
														<div class="ODS_DivGridVertical">
															<table id="gridTable" width="100%" border="0">
																<tbody>
																	<tr>
																		<td>
																			<div>
																				<display:table name="sessionScope.RegisterAuthorizedForm.selectRegister.accountList" uid="vo" id="resultRow"
																					pagesize="30" export="true" class="listaPaginada"
																					style="width:100%" sort="list"
																					requestURI="/FrontController/NEWCPB.RegisterAuthorized">
																					
																					<logic:equal name="RegisterAuthorizedForm" property="onlyView" value="false" >
																						<display:column title="" media="html" style="text-align:center;width:5%">
																								
																							<bean:define name="resultRow" property="acctNbr" id="acctNbr" type="java.lang.String"></bean:define>
																							<bean:define name="resultRow" property="authInd" id="authInd" type="java.lang.String"></bean:define>													
																								<a href="javascript:setSelectedKeys('<%=acctNbr%>' , '<%=authInd%>' );submitActionNewCPB('deleteAccount');">
																									<img src='<%=request.getContextPath()%>/Common/image/delete.gif' alt="" border="0">
																								</a>																				
																							
																						</display:column>
																					</logic:equal>
																					<display:column title="Cliente da Conta" property="customerName" sortable="true" style="text-align:left;width:35%"/>		
																					<display:column title="EM" property="customerEm" sortable="true" style="text-align:center;width:10%"/>													
																					<display:column title="Número Conta" property="acctNbr" sortable="true" style="text-align:right;width:10%"/>	
																					<display:column title="Tipo" property="agentAuthPersonDesc" sortable="true" style="text-align:center;width:10%"/>	
																					<display:column title="Data de Vigência" property="effectiveDate" sortable="true" style="text-align:center;width:10%"/>	
																					<display:column title="Observações" property="authComment" sortable="true" style="text-align:center;width:25%"/>	
											
																					<display:setProperty name="export.pdf" value="false" />
																					<display:setProperty name="export.csv" value="false" />
																					<display:setProperty name="export.xml" value="false" />		
																				</display:table>
																			</div> 
																		</td>
																	</tr>	
																	<logic:equal name="RegisterAuthorizedForm" property="approve" value="false" >
																		<logic:equal name="RegisterAuthorizedForm" property="onlyView" value="false" >
																		<%formBean.resetRadio();%>
																			<tr><td>&nbsp;</td></tr>
																			<tr>
																				<td colspan="3">
																					<table class="ODS_internalWidth" border="0">
																						<tbody>
																							<tr>
																								<td width="100%"></td>
																								<TD><html:button property="button" value="Salvar" onclick="submitActionNewCPB('save');"/></td>
																								
																								<logic:equal name="RegisterAuthorizedForm" property="fromApprove" value="false" >								
																									<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
																								</logic:equal>	
																								
																								<logic:equal name="RegisterAuthorizedForm" property="fromApprove" value="true" >								
																									<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
																								</logic:equal>	
																							</tr>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																		</logic:equal>
																		
																		<logic:equal name="RegisterAuthorizedForm" property="onlyView" value="true" >
																			
																			<tr><td>&nbsp;</td></tr>
																			<tr>
																				<td colspan="3">
																					<table class="ODS_internalWidth" border="0">
																						<tbody>
																							<tr>
																								<td width="100%"></td>	
																								<td>	
																								<% 	Boolean fromApprove = (Boolean)session.getAttribute("fromApprove");
																								
																									if(fromApprove!=null && fromApprove){%>
																									<html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"/>
																								<%	}else{ %>
																									<html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"/>
																								<%	} 														
																									session.removeAttribute("fromApprove");														
																								%>
																								</td>
																							</tr>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																		</logic:equal>
																	</logic:equal>																				
																</tbody>
															</table>
															<logic:equal name="RegisterAuthorizedForm" property="approve" value="true" >
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
																				<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectRegister.lastUpdUserSafe" disabled="true"/></td>
																				<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectRegister.lastUpdDateFormatedSafe" disabled="true"/></td>
																				<td width="33%"><html:text styleClass="ODS_Text_Field_Size_10" property="selectRegister.recStatCodeText" disabled="true"/></TD>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="3">
																		<table class="ODS_internalWidth" border="0">
																		<bean:define id="lastUpdUserId" name ="RegisterAuthorizedForm" property="selectRegister.lastUpdUserSafe" type="java.lang.String"/>
																			<tbody>
																				<tr>
																					<td width="100%"></td>		
																					<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBAprovRej(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome())){ %>								
																						<logic:equal name="RegisterAuthorizedForm" property="approve" value="true" >
																							<logic:notEqual name="lastUpdUserId" value="<%=userId%>">
																								<td><html:button property="approvedBtn" value="Aprovar" onclick="submitActionNewCPB('approve');"/></td>																																	
																							</logic:notEqual>
																							<td><html:button property="rejectBtn" value="Reprovar" onclick="submitActionNewCPB('reprove');"/></td>	
																						</logic:equal>
																					<% } %>
																					<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></td>
																				</TR>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</logic:equal>
														</div>
													</td>
												</tr>
											</tbody>	
										</table>									
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>		
		</html:form>	
		<jsp:include page="/View/Util/Footer.jsp" flush="true"/>	
	</body>	
	<jsp:include page="/View/Util/NoCacheIE.jsp" />
	<script type="text/javascript">
		
		 var input = document.getElementsByName('idDiffList')[0].value;
				
		if(input){
			var fields = input.substring(1).substring(0, input.length-2).replace(/\s/g, '').split(',');
			
			for (var i = 0; i < fields.length; i++) {
				if(document.getElementsByName('selectRegister.' + fields[i])){
					if(document.getElementsByName('selectRegister.' + fields[i])[0]){
						document.getElementsByName('selectRegister.' + fields[i])[0].classList.add("fieldDiff");
					}
					
					
					if(document.getElementById('selectRegister.' + fields[i])){
						document.getElementById('selectRegister.' + fields[i]).style.border="1px solid red";	
					}
				}
			}
		} 
		
		</script>
</html:html>