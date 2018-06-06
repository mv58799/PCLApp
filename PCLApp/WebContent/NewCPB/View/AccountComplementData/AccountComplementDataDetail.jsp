<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.enums.ScreenNamesEnum"%>
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
		
		<title>Conta - Alterar Dados Complementares</title>
	</head>
	<body>
		<html:form action="/NEWCPB.AccountComplementData.do">
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="Clientes" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />			
			<html:text property="idDiffList" disabled="true" style="display:none"></html:text>
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               AccountComplementDataForm formBean = (AccountComplementDataForm)session.getAttribute("AccountComplementDataForm");
	         %>
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">									
										<%=formBean.getScreenTitle()%>
									</th>
								</tr>
							</thead>
							
							</table>
							<table>	
							<tbody>
								<tr class="ODS_Detail_line1">
								
								
									<td>
										<table>
											<tr>
												<td>Número da Conta</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_15" property="selectRegisterAccountComplement.acctNbr" disabled="true"/>
												</td>												
											</tr>
											<tr>
												<td>CPF/CNPJ</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_15" property="selectRegisterAccountComplement.cpfCnpjNbr"  maxlength="18" onblur="completeCpfCnpj(this);" disabled="true"/>
												</td>
											</tr>
											<tr>
												<td>Data da Assinatura do Contrato</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.contractSignatureDate" disabled="<%=formBean.isOnlyView() %>" onkeydown="maskDate();" maxlength="10" />
												</td>	
											</tr>
											<tr>
												<td>Data de Encerramento da Conta&nbsp;&nbsp;&nbsp; </td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.accountClosingDate"  onkeydown="maskDate();" maxlength="10" disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>						
											<tr>
												<td>
													Código CETIP
												</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.cetipNumber"  maxlength="10"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>					
											<tr>
												<td>
													Código Bovespa
												</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.bovespaNumber" maxlength="10"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" disabled="<%=formBean.isOnlyView() %>"/>	
												</td>
											</tr>					
											<tr>
												<td>Código BMF</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.bmfNumber" maxlength="10"   onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>
											
											<tr>
									
												<td>Possui procurador/autorizado?</td>
												<td colspan="4">
													<table width="100%" cellpadding="0" cellspacing="0">
														<tr>
															
															<td width="19.50%">
																<div id="selectRegisterAccountComplement.hasAngent" class="borderDivCheckBox" style="width: 100px;">
															
																	<html:radio styleClass="radio" property="selectRegisterAccountComplement.hasAngent" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																	Sim

																	<html:radio styleClass="radio" property="selectRegisterAccountComplement.hasAngent" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																	Não
																
																</div>

								     		   				</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>Risco</td>
												<td>
													<html:text property="selectRegisterAccountComplement.riskLevelCode"
														styleClass="ODS_Select_Field_Size_2"
														maxlength="1" 
														disabled="<%=formBean.isOnlyView() %>"
														onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
												</td>
											</tr>								
											<tr>
												<TD>Número ER (já existente)</TD>
												<td style="display: inline-flex;">
													<html:text property="selectRegisterAccountComplement.erNbr"
														styleClass="ODS_Text_Field_Size_10"
														maxlength="30" 
														disabled="<%=formBean.isOnlyView() || formBean.isEdit() %>"
														onkeyup="toUppercase(this)" onblur="toUppercase(this)"/>
														
														<logic:equal name="AccountComplementDataForm" property="onlyView" value="false" >
															<logic:equal name="AccountComplementDataForm" property="updateFromApprove" value="false" >
																<html:button property="button" value="Consultar" onclick="submitActionNewCPB('searchER');"/>
															</logic:equal>
														</logic:equal>
												</td>
											</tr>
											<tr>
												<TD>Número EG</TD>
														<td style="display: inline-flex;">
															<html:text property="selectRegisterAccountComplement.egNbr"
																	styleClass="ODS_Text_Field_Size_5"
																	maxlength="4" 
																	disabled="<%=formBean.isOnlyView() || formBean.isEdit()  %>"/>
														</td>
													</tr>
										</table>
									</td>
									<td width="50px">
									
									</td>
									
									<td>
										<table>
											<tr>
												<td>Origem</td>
												
												<td>
													
													<html:select styleClass="ODS_Select_Field_Size_15" property="selectRegisterAccountComplement.accountType" disabled="true">
															<html:option value=""></html:option>
															<html:optionsCollection  property="accountTypeValues" label="resultDescription" value="resultCode"/>
													</html:select>
												</td>
																								
											</tr>
											<tr>
												<td></td>
												<td>
												</td>
											</tr>
											<tr>
												<td>Data da Abertura da Conta&nbsp;&nbsp;&nbsp; </td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.accountOpenDate" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>"/>
												</td>	
											</tr>
											<tr>
												<td>Encerramento por:</td>
												<td>
												
													<html:select styleClass="ODS_Select_Field_Size_30" property="selectRegisterAccountComplement.accountClosingReason"  disabled="<%=formBean.isOnlyView() %>" >
															<html:option value=""></html:option>  
															<html:optionsCollection  property="accountClosingReasonValues" label="resultDescription" value="resultCode"/>
													</html:select>
												
												</td>
											</tr>						
											<tr>
												<td>
													Código SELIC
												</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.selicNumber" maxlength="10"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>					
											<tr>
												<td>
													Código BVRJ
												</td>
												<td>
													<html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.bvrjNumber" maxlength="10"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" disabled="<%=formBean.isOnlyView() %>"/>	
												</td>
											</tr>					
											<tr>
												<td></td>
												<td>
												</td>
											</tr>
																<tr>
												<td>Data da Última Revisão no IOS</td>
												<td>
													<html:text property="selectRegisterAccountComplement.lastIosRevDate"
														styleClass="ODS_Select_Field_Size_10"
														maxlength="10" 
														onkeydown="maskDate();" 
														disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>											
																			<tr>
												<td>Tipo de Conta </td>
												<td>
													<html:select styleClass="ODS_Select_Field_Size_30" property="selectRegisterAccountComplement.accountTypeRDIP" disabled="<%=formBean.isOnlyView() %>">
															<html:option value=""></html:option>
															<html:optionsCollection  property="accountTypeRDIPValues" label="resultDescription" value="resultCode"/>
													</html:select>
												</td>
											</tr>											
<tr>
												<td></td>
												<td>
												</td>
											</tr>
											<tr>
												<td></td>
												<td>
												</td>
											</tr>

										</table>
									</td>
								</tr>
							</tbody>
						</table>	
						<table>
					<% if(formBean!=null && formBean.getSelectRegister().getErNbr()!=null && formBean.getSelectRegister().getCustomerList()!=null && !formBean.getSelectRegister().getCustomerList().isEmpty()){ %>
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="6">EM´s Associados ao <%=formBean.getSelectRegister().getErNbr() %></th>
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
																<display:table name="sessionScope.AccountComplementDataForm.selectRegister.customerList" uid="vo" id="customerRow"
																	pagesize="30" export="false" class="listaPaginada"
																	style="width:100%" sort="list"
																	requestURI="/FrontController/NEWCPB.RegisterDataRisk?method=search">
																	
																	<display:column title="Número EM" property="numberEM" sortable="true" style="text-align:center;width:20%"/>		
																	<display:column title="Nome" property="name" sortable="true" style="text-align:left;width:50%"/>		
																	<display:column title="Papel no Relacionamento" property="er_em.roleCustDesc" sortable="true" style="text-align:center;width:30%"/>													
							
																</display:table>
															</div>
														</td>
													</tr>	
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<% } %>
				</table>
				
					</td>
					
					
				<logic:equal name="AccountComplementDataForm" property="approve" value="false" >
					<logic:equal name="AccountComplementDataForm" property="onlyView" value="false" >
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="3">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr>
											<td width="100%"></td>
											<TD>
											<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBContaAtualDadosCompdaConta()){ %>
												<html:button property="button" value="Salvar" onclick="submitActionNewCPB('save');"></html:button>
											<% }%>				
											</td>	
											
											<logic:equal name="AccountComplementDataForm" property="fromApprove" value="false" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
											</logic:equal>	
											
											<logic:equal name="AccountComplementDataForm" property="fromApprove" value="true" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
											</logic:equal>	
											
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</logic:equal>
					
					<logic:equal name="AccountComplementDataForm" property="onlyView" value="true" >
						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="3">
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr>
											<td width="100%"></td>														
											
											
											<logic:equal name="AccountComplementDataForm" property="fromApprove" value="false" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
											</logic:equal>	
											
											<logic:equal name="AccountComplementDataForm" property="fromApprove" value="true" >								
												<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
											</logic:equal>	
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</logic:equal>
				</logic:equal>
				</tr>
				
				<logic:equal name="AccountComplementDataForm" property="approve" value="true" >
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
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectRegisterAccountComplement.lastUpdUserSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectRegisterAccountComplement.lastUpdDateFormatedSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_10" property="selectRegisterAccountComplement.recStatCodeText" disabled="true"></html:text></TD>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="ODS_Detail_Line1"><td colspan="3">&nbsp;</td></tr>
					<tr class="ODS_Detail_Line2"><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3">
							<table class="ODS_internalWidth" border="0">
							<bean:define id="lastUpdUserId" name ="AccountComplementDataForm" property="selectRegisterAccountComplement.lastUpdUserSafe" type="java.lang.String"/>
								<tbody>
									<tr>
										<td width="100%"></td>		
										<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBAprovRej(ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome())){ %>								
											<logic:equal name="AccountComplementDataForm" property="approve" value="true" >
												<logic:notEqual name="lastUpdUserId" value="<%=userId%>">
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
		</html:form>	
		<jsp:include page="/View/Util/Footer.jsp" flush="true"/>	
	</body>	
	<jsp:include page="/View/Util/NoCacheIE.jsp" />
	<script type="text/javascript">
		
		var input = document.getElementsByName('idDiffList')[0].value;
				
		if(input){
			var fields = input.substring(1).substring(0, input.length-2).replace(/\s/g, '').split(',');
			
			for (var i = 0; i < fields.length; i++) {
				if(document.getElementsByName('selectRegisterAccountComplement.' + fields[i])){
					if(document.getElementsByName('selectRegisterAccountComplement.' + fields[i])[0]){
						document.getElementsByName('selectRegisterAccountComplement.' + fields[i])[0].classList.add("fieldDiff");
					}
					
					
					if(document.getElementById('selectRegisterAccountComplement.' + fields[i])){
						document.getElementById('selectRegisterAccountComplement.' + fields[i]).style.border="1px solid red";	
					}
				}
			}
		} 
		
		</script>
</html:html>