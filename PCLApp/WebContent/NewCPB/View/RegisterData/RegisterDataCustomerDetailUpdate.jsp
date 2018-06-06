
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.citibank.ods.modules.product.product.form.ProductDetailForm"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.enums.ScreenNamesEnum"%>
<%@ page import="com.citibank.newcpb.form.RegisterDataCustomerForm"%>

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

		<TITLE>Cliente - Atualização de Dados Cadastrais  </TITLE>
	</HEAD>

	<body>
		<html:form action="/NEWCPB.RegisterDataCustomer.do">
		
			
			<html:hidden property="registerConsumer.SOEIDBankerName"></html:hidden>
			
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes" />
		    </jsp:include>
		   <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Product.ProductDetail.Update.Show" style="display:none"></html:text>
			<html:hidden property="findType" value="true"></html:hidden>
			<html:text property="idDiffList" disabled="true" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td width="100%">
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Cliente - Atualização de Dados Cadastrais </th>
								</tr>
							</thead>
							<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
                                  String userId = user != null ? user.getUserID() : null;
                                  RegisterDataCustomerForm formBean = (RegisterDataCustomerForm)session.getAttribute("RegisterDataCustomerForm");
                            %>
                            <tr><td>&nbsp;</td></tr>
							<tr>
								<td>&nbsp;</td>
								<td>
									<table class="ODS_internalWidth" border="0" cellspacing="0">			
										<logic:equal name="RegisterDataCustomerForm" value="F" property="customerType">
											<tr><td>&nbsp;</td></tr>
											<tr class="ODS_line11">
												<td>
													<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoBaseNaturalPerson" class="cursor:pointer;" onclick="hiddenBox(this,'infoBaseNaturalPerson')">
													Dados Pessoais
												</td>
											</tr>
											<tr>
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<!-- Box de Informação Básica de Cliente Pessoa Fisica-->
											
											<tr class="ODS_Detail_Line1" id="infoBaseNaturalPerson" style="display:none;">
												<td>
													<table>
														<tr>
															<td>Tipo de Cliente</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.customerTypeDesc" disabled="true" maxlength="10"></html:text>
															</td>
															
															<td>Status do Cliente</td>
															<td>
																<html:select styleClass="ODS_Select_Field_Size_10" property="registerConsumer.customerStatus" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="customerStatusValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														<tr>
															<td>Nome</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.name" disabled="true" maxlength="80" />
															</td>	
														</tr>
														<tr>
															<td>CPF/CNPJ</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.cpfCnpj"  maxlength="18" onkeyup="toUppercase(this)" onblur="completeCpfCnpj(this);toUppercase(this)" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														<tr>
															<td>Data de nascimento</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_15" property="registerConsumer.birthDate" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
															
															<td style="text-align: right;">Sexo</td>
															<td>
																<html:select styleClass="ODS_Select_Field_Size_10" property="registerConsumer.gender" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="genderTypeValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														<tr>
															<td>Filiação</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_40" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  property="registerConsumer.filiation1" style="width: 300px;"  maxlength="150" disabled="<%=formBean.isOnlyView() %>"/>
																<html:text styleClass="ODS_Text_Field_Size_40" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  property="registerConsumer.filiation2" style="width: 300px;"  maxlength="150" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														<tr>
															<td >Estado Civil</td>
															<td>
																<html:select styleClass="ODS_Text_Field_Size_20" property="registerConsumer.civilState" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="civilStateValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
															
															<td >Nº Dependentes</td>
															<td>
															<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.numberDependents" onkeyup="onlyNumbers(this);" onblur="onlyNumbers(this);" maxlength="2" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														
														<tr>
															<td>Nome do Conjuge</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.spouseName" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														
														<tr>
															<td>Nacionalidade</td>
																<td colspan="3">
																<html:select styleClass="ODS_Text_Field_Size_30" property="registerConsumer.countryBirth" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="countryValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														<tr>
															<td>Naturalidade</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.placeOfBirth" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="60" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
															
															<td style="text-align: right;">UF</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.ufPlaceOfBirth" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="2" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														<tr>
															<td>Documento de Identidade &nbsp;&nbsp; </td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.identityDocument"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="20" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														<tr>  
															<td>Orgão Emissor</td>
															<td colspan="4">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td>
																			<html:select styleClass="ODS_Text_Field_Size_40" property="registerConsumer.emitType" disabled="<%=formBean.isOnlyView() %>">
					 															<html:option value=""></html:option>
				 																<html:optionsCollection  property="emitTypeValues" label="resultDescription" value="resultCode"/>
				 															</html:select>
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">UF &nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.emitDocumentUF"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="2" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
											     		   				
											     		   			<td width="40%" style="text-align: right;">Dt. Emissão &nbsp;&nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.emitDocumentDate"  maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
																	</tr>
																</table>
															</td>
														</tr>
														
														<tr>
															<td>Profissão</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.occupation" onkeyup="toUppercase(this)" onblur="toUppercase(this)" maxlength="50" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														
														<tr>
															<td>Natureza da Ocupação</td>
																<td colspan="3">
																<html:select styleClass="ODS_Text_Field_Size_50" property="registerConsumer.occupationNature" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="occupationNatureValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td>Renda Declarada </td>
															<td colspan="3">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.declaredIncome"  maxlength="15"  onblur="maskValue(this,event,'###.###.###.###',true)" onkeyup="maskValue(this,event,'###.###.###.###',true)" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">Patrimônio Declarado &nbsp;&nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.declaredHeritage"  maxlength="15" onblur="maskValue(this,event,'###.###.###.###',true)" onkeyup="maskValue(this,event,'###.###.###.###',true)" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
																	</tr>
																</table>																
															</td>
														</tr>
														
														<tr>
															<td>Funcionario</td>
															<td>
															
																<div  id="registerConsumer.employeeDiv"  class="borderDivCheckBox" style="width: 100px;">
																	<html:radio styleClass="radio" property="registerConsumer.isEmployee" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																	Sim
																	
																	<html:radio styleClass="radio" property="registerConsumer.isEmployee" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																	Não
																</div>
															</td>
															<td style="text-align: right;">Falecido</td>
															
															<td>	
																<div id="registerConsumer.deceasedDiv" class="borderDivCheckBox" style="width: 100px;">	
																	<html:radio styleClass="radio" property="registerConsumer.isDeceased" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																	Sim
																	
																	<html:radio styleClass="radio" property="registerConsumer.isDeceased" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																	Não
																</div>
															</td>
														</tr>
													
														<tr>
															<td>SOEID Banker</td>
															<td colspan="4">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		
																		<td width="30%">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.SOEIDBankerNumber" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="7" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
																		
											     		   				<td>
																			<html:button property="button" value="Buscar" onclick="searchBankerName();" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																		</td>
											     		   				
												     		   			<td>&nbsp;&nbsp;</td>
												     		   			<td width="50%" style="text-align: left;">												     		   				
												     		   				<div id="bankerNameTd"></div>
												     		   			</td>
	
																	</tr>
																</table>
															</td>	
														</tr>
													
														<tr>
														
															<td>Green Card</td>
															<td colspan="4">
																<table width="100%" cellpadding="0" cellspacing="0">
																	<tr>
																		
																		<td width="19.50%">
																			<div id="registerConsumer.haveGreenCardDiv" class="borderDivCheckBox" style="width: 100px;">
																		
																				<html:radio styleClass="radio" property="registerConsumer.haveGreenCard" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																				Sim
			
																				<html:radio styleClass="radio" property="registerConsumer.haveGreenCard" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																				Não
																			
																			</div>
	
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">Social Security Number &nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.socialSecurityNumber" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="9"  onkeypress="return isOnlyNumbers(event)" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
											     		   	
																	</tr>
																</table>
															</td>
														</tr>
														
														<tr>
														
															<td>Isento de IR</td>
															<td>
																<table  width="30%"  cellpadding="0" cellspacing="0">
																	<tr>
																		
																		<td colspan="4">
																			<div id="registerConsumer.exemptIRDiv" class="borderDivCheckBox" style="width: 100px;">
																				<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																				Sim
																				
																				<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																				Não
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														
														
													
												</table>
											</td>
										</tr>
									</logic:equal>
										
										



										
										<logic:equal name="RegisterDataCustomerForm" value="J" property="customerType">
										
											<tr><td>&nbsp;</td></tr>
											<tr class="ODS_line11">
												<td>
													<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoBaseLegalPerson" class="cursor:pointer;" onclick="hiddenBox(this,'infoBaseLegalPerson')">
													Dados da Empresa
												</td>
											</tr>
											<tr>
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<!-- Box de Informação Básica Empresa -->
											
											<tr class="ODS_Detail_Line1" id="infoBaseLegalPerson" style="display:none;">
												<td>
													<table>
														<tr>
															<td>Tipo de Cliente</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.customerTypeDesc" disabled="true" maxlength="10"></html:text>
															</td>
															
															<td>Status do Cliente</td>
															<td>
																<html:select styleClass="ODS_Select_Field_Size_10" property="registerConsumer.customerStatus" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="customerStatusValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td>Razão Social</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.name" disabled="true" maxlength="20"/>
															</td>
															
														</tr>
														<tr>
															<td>CPF/CNPJ</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.cpfCnpj"  maxlength="18" onblur="completeCpfCnpj(this);" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														
														<tr>
															<td>Atividade Principal</td>
															<td>
																<html:select styleClass="ODS_Text_Field_Size_30" property="registerConsumer.activityMain"  disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="activityMainValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td>NAIC (AMC)</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_60" property="registerConsumer.naicNumber"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  disabled="true" maxlength="50"/>
															</td>
														</tr>
														
														<tr>
															<td>SIC (AMC)</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_60" property="registerConsumer.sicNumber" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  disabled="true"/>
															</td>
														</tr>
														
														<tr>
															<td >Forma de Constituição</td>
															<td>
																<html:select styleClass="ODS_Text_Field_Size_20" property="registerConsumer.constType" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="constTypeValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td>Data da Fundação</td>
															<td colspan="3">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		
																		<td >
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.foundationDate"  maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">Faturam. Médio Mensal (R$ Mil)</td>
											     		   				
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.averageMonthBilling" maxlength="15" onblur="maskValue(this,event,'###.###.###.###',true)" onkeyup="maskValue(this,event,'###.###.###.###',true)" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
																	</tr>
																</table>
															</td>
														</tr>
														
														<tr>
															<td>Pais de Constituição</td>
																<td colspan="3">
																<html:select styleClass="ODS_Text_Field_Size_30" property="registerConsumer.countryConstitution" disabled="<%=formBean.isOnlyView() %>">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="countryValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														
														<tr>
															<td>Nome do Administrador &nbsp;&nbsp;</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.admName" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
															
														</tr>
																											
														
														<tr>
															<td>CPF do Administrador</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.admCpf"  maxlength="18" onblur="completeCpfCnpj(this);" onkeypress="return isOnlyNumbers(event)" disabled="<%=formBean.isOnlyView() %>"/>
															</td>
														</tr>
														
														
														<tr>
															<td>Possui conta Flex DDA?</td>
															<td>
																<div id="registerConsumer.hasFlexAccountDiv" class="borderDivCheckBox" style="width: 100px;">
																	<html:radio styleClass="radio" property="registerConsumer.hasFlexAccount" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																	Sim
																	
																	<html:radio styleClass="radio" property="registerConsumer.hasFlexAccount" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																	Não
																</div>
															</td>
														</tr>
														<tr>
															<td>SOEID Banker</td>
															<td colspan="4">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		
																		<td width="30%">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.SOEIDBankerNumber" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="7" disabled="<%=formBean.isOnlyView() %>"/>
											     		   				</td>
																		
											     		   				<td>
																			<html:button property="button" value="Buscar" onclick="searchBankerName();" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																		</td>
											     		   				
												     		   			<td>&nbsp;&nbsp;</td>
												     		   			<td width="50%" style="text-align: left;">												     		   				
												     		   				<div id="bankerNameTd"></div>
												     		   			</td>
	
																	</tr>
																</table>
															</td>	
														</tr>
														<tr>
															<td>Isento de IR</td>
															<td>
																<div id="registerConsumer.exemptIRDiv" class="borderDivCheckBox" style="width: 100px;">
															
																	<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																	Sim
																	
																	<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																	Não
																</div>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</logic:equal>
										<tr><td>&nbsp;</td></tr>
										<!-- Fim -->
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoInvest" class="cursor:pointer;" onclick="hiddenBox(this,'infoClassCity')">
												Classificação Citi
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box Classificação Citi -->
										
										
										<tr class="ODS_Detail_Line1" id="infoClassCity" style="display:none;">
											<td>
												<table>
												
													<tr>
														<td>Número GFCID</td>
														<td  colspan="4">
															<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.numberGFCID" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  disabled="true" maxlength="20"/>
														</td>
														
													</tr>
													<tr>
														<td>Número EM</td>
														<td colspan="4">
															<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.numberEM" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  disabled="true" maxlength="30"/>
														</td>
														
													</tr>
												
													<tr>
														<td>Número ER</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.er_em.erNbr"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="30" disabled="<%=formBean.isOnlyView() %>"/>
														</td>
														<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
														<td>Papel do Cliente no Relacionamento</td>
														<td>
															<html:select styleClass="ODS_Select_Field_Size_15" property="registerConsumer.er_em.roleCustCode" disabled="<%=formBean.isOnlyView() %>">
	 															<html:option value=""></html:option>
 																<html:optionsCollection  property="customerRoleRelationshipValues" label="resultDescription" value="resultCode"/>
 															</html:select>
														</td>
													</tr>
													
													<tr>
														<td colspan="5">
															<div id="registerConsumer.sensitiveDiv" class="borderDivCheckBox" style="width: 200px;">
																<table width="100%" cellpadding="0" cellspacing="0">																
																	<tr>																	
																		<td width="10%;">
																			<html:radio styleClass="radio" property="registerConsumer.isSensitive" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
																		<td width="10%">
																			Sensitive
											     		   				</td>
																		
											     		   				<td width="10%" style="text-align: center;">
																			<html:radio styleClass="radio" property="registerConsumer.isSensitive" value="false" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																		</td>
																		
																		<td width="70%">
																			Non Sensitive
																		</td>
																	</tr>																
																</table>
															</div>
														</td>
													</tr>
													
													<tr>
														<td colspan="5">													
															<div id="registerConsumer.typeClassDiv" class="borderDivCheckBox"  style="width: 370px;">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="5%;">
																			<html:radio styleClass="radio" property="registerConsumer.typeClass" value="S" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
																		<td width="30%">
																			Senior Public Figure
											     		   				</td>
																		
											     		   				<td width="10%" style="text-align: center;">
																			<html:radio styleClass="radio" property="registerConsumer.typeClass" value="P" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																		</td>
																		
																		<td width="20%">
																			Prominent SPF
																		</td>
																		
																		<td width="10%" style="text-align: center;">
																			<html:radio styleClass="radio" property="registerConsumer.typeClass" value="N" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																		</td>
																		
																		<td width="25%">
																			N/A
																		</td>
																	</tr>
																</table>
															</div> 
														</td>	
													</tr>
													
													<tr>
													
													<td>Derogatory Information (PB&R)&nbsp; </td>		
														<td colspan="5">													
															<div id="registerConsumer.derogatoryInformationDiv" class="borderDivCheckBox" style="width: 100px;">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
											     		   				<td width="10%" style="text-align: center;">
																			<html:radio styleClass="radio" property="registerConsumer.derogatoryInformation" value="true" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																		</td>
																		
																		<td width="10%">
																			Sim
																		</td>
																		
																		<td width="10%" style="text-align: center;">
																			<html:radio styleClass="radio" property="registerConsumer.derogatoryInformation" value="false" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																		</td>
																		
																		<td width="35%">
																			Não
																		</td>																		
																	</tr>
																</table>
															</div>
														</td>														
													</tr>
												</table>
											</td>
										</tr>
										
										<!-- Fim -->
										<tr><td>&nbsp;</td></tr>

										<!-- Box de Endereços -->
										<logic:equal name="RegisterDataCustomerForm" value="F" property="customerType">
											<tr class="ODS_line11">
												<td>
													<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoRenda" class="cursor:pointer;" onclick="hiddenBox(this,'infoAddressNatural')">
													Endereços
												</td>
											</tr>
											<tr>
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<tr class="ODS_Detail_Line1" id="infoAddressNatural" style="display:none;">
												<td>
													<table>
														<tr class="ODS_line11">
															<td style="font-weight: bold;">
																Residencial
															</td>
														</tr>
														<tr>
															<td>
																<table class="borderDivCheckBox" width="100%" cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.residentialAddress.street"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.residentialAddress.neighborhood" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.residentialAddress.city" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>	
																		</td>
																		<td>
																		 	UF
																		</td>	
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.residentialAddress.uf" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="2" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.residentialAddress.zipCode" onkeyup="toUppercase(this)" onblur="toUppercase(this)"   maxlength="8" disabled="<%=formBean.isOnlyView() %>"/>	
																		</td>
																	</tr>
																	<tr>
																		<td colspan="5" width="95%" style="text-align: left;">
																			<div id="registerConsumer.residentialAddress.isCorrespondenceDiv" style="width: 200px;">
																					<html:checkbox property="registerConsumer.residentialAddress.isCorrespondence" disabled="<%=formBean.isOnlyView() %>"/>									     		   					
																					Correspondência													     		   					
												     		   				</div>
											     		   				</td>
																	</tr>
																</table>
															</td>	
														</tr>
													</table>
													
													<table>
														<tr class="ODS_line11">
															<td style="font-weight: bold;">
																Comercial
															</td>
														</tr>
														<tr>
															<td>
																<table class="borderDivCheckBox" width="100%" cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.businessAddress.street" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.businessAddress.neighborhood" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.businessAddress.city" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>	
																		</td>
																		<td>
																		 	UF
																		</td>	
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.businessAddress.uf"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="2" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.businessAddress.zipCode" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="8" disabled="<%=formBean.isOnlyView() %>"/>	
																		</td>
																	</tr>
																	<tr>
											     		   				<td colspan="5" width="95%" style="text-align: left;">
																			<div id="registerConsumer.businessAddress.isCorrespondenceDiv" style="width: 200px;">
																					<html:checkbox property="registerConsumer.businessAddress.isCorrespondence" disabled="<%=formBean.isOnlyView() %>"/>									     		   					
																					Correspondência													     		   					
												     		   				</div>
											     		   				</td>
																	</tr>
																</table>
															</td>	
														</tr>
													</table>
													
													<table>
														<tr class="ODS_line11">
															<td style="font-weight: bold;">
																Outros
															</td>
														</tr>
														<tr>
															<td>
																<table class="borderDivCheckBox" width="100%"  cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.otherAddress.street"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.otherAddress.neighborhood" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.otherAddress.city" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" />	
																		</td>
																		<td>
																		 	UF
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.otherAddress.uf" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="2" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.otherAddress.zipCode"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="8" disabled="<%=formBean.isOnlyView() %>" />	
																		</td>
																	</tr>
																	<tr>
											     		   				<td colspan="5" width="95%" style="text-align: left;">
																			<div id="registerConsumer.otherAddress.isCorrespondenceDiv" style="width: 200px;">
																					<html:checkbox property="registerConsumer.otherAddress.isCorrespondence" disabled="<%=formBean.isOnlyView() %>"/>									     		   					
																					Correspondência													     		   					
												     		   				</div>
											     		   				</td>
																	</tr>
																</table>
															</td>	
														</tr>
													</table>
												</td>
											</tr>
											
											
										</logic:equal>
										
										<logic:equal name="RegisterDataCustomerForm" value="J" property="customerType">										
											<tr class="ODS_line11">
												<td>
													<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoRenda" class="cursor:pointer;" onclick="hiddenBox(this,'infoAddressLegal')">
													Endereços
												</td>
											</tr>
											<tr>
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											
											<tr class="ODS_Detail_Line1" id="infoAddressLegal" style="display:none;">
												<td>
													<table>
														<tr class="ODS_line11">
															<td style="font-weight: bold;">
																Sede Comercial
															</td>
														</tr>
														<tr>
															<td>
																<table class="borderDivCheckBox" width="100%" cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.headOfficeAddress.street" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.headOfficeAddress.neighborhood" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.headOfficeAddress.city" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" />	
																		</td>
																		<td>
																		 	UF
																		</td>	
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.headOfficeAddress.uf" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="2" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.headOfficeAddress.zipCode" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="8" disabled="<%=formBean.isOnlyView() %>" />	
																		</td>
																	</tr>
																	<tr>
																		<td width="5%" style="text-align:center;">
																			<html:checkbox property="registerConsumer.headOfficeAddress.isCorrespondence" disabled="<%=formBean.isOnlyView() %>"/>									     		   					
																		</td>
																		<td colspan="5" width="95%" style="text-align: left;">
																			Correspondência
											     		   				</td>	
																	</tr>
																</table>
															</td>	
														</tr>
													</table>			
													<table>
														<tr class="ODS_line11">
															<td style="font-weight: bold;">
																Outros
															</td>
														</tr>
														<tr>
															<td>
																<table class="borderDivCheckBox" width="100%"  cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.otherAddress.street" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.otherAddress.neighborhood" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.otherAddress.city"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="80" disabled="<%=formBean.isOnlyView() %>" />	
																		</td>
																		<td>
																		 	UF
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.otherAddress.uf"  onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="2" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.otherAddress.zipCode" onkeyup="toUppercase(this)" onblur="toUppercase(this)"  maxlength="8" disabled="<%=formBean.isOnlyView() %>" />	
																		</td>
																	</tr>
																	<tr>
																		<td width="5%" style="text-align:center;">
																			<html:checkbox  property="registerConsumer.otherAddress.isCorrespondence" disabled="<%=formBean.isOnlyView() %>"/>									     		   					
																		</td>
																		<td colspan="5" width="95%" style="text-align: left;">
																			Correspondência
											     		   				</td>
																	</tr>
																</table>
															</td>	
														</tr>
													</table>
												</td>
											</tr>
										</logic:equal>
										

										<tr><td>&nbsp;</td></tr>
										<!-- Fim -->
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoInvest" class="cursor:pointer;" onclick="hiddenBox(this,'infoTelephones')">
												Telefones
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box de Telefones -->
										
										<tr class="ODS_Detail_Line1" id="infoTelephones" style="display:none;">
											<td>
												<table>		
												 
												 	<logic:notEmpty name="RegisterDataCustomerForm" property="registerConsumer.telephoneList">
												 
														<% long telephoneCount = 0;%>
														<logic:iterate name="RegisterDataCustomerForm" property="registerConsumer.telephoneList" id="item" indexId="itemId">
														 	<tr>
																<td><%=telephoneCount+1%></td>
																<td>
																	<html:text styleClass="ODS_Text_Field_Size_5"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" property='<%="registerConsumer.telephoneList["+telephoneCount+"].ddd" %>'   maxlength="5" disabled="<%=formBean.isOnlyView() %>"/>
																</td>
																
																<td>
																	<html:text styleClass="ODS_Text_Field_Size_20"  onkeypress="return isOnlyNumbers(event)" onblur="onlyNumbers(this)" property='<%="registerConsumer.telephoneList["+telephoneCount+"].number" %>'   maxlength="15" disabled="<%=formBean.isOnlyView() %>"/>
																</td>
															</tr>
		        										<% telephoneCount = telephoneCount+1;%>
		    											</logic:iterate>
	    											</logic:notEmpty>
	    											
	    											<logic:empty name="RegisterDataCustomerForm" property="registerConsumer.telephoneList">
 												    	<tr>
	    													<td>Cliente não possui telefones cadastrados.</td>
	    												</tr>
	    											</logic:empty>
	    											

												</table>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Fim -->
										
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoInvest" class="cursor:pointer;" onclick="hiddenBox(this,'infoEmails')">
												E-mails
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										
										
										<tr class="ODS_Detail_Line1" id="infoEmails" style="display:none;">
											<td>
												<table>		
													<logic:notEmpty name="RegisterDataCustomerForm"  property="registerConsumer.mailList">
														<% long mailCount = 0;%>
														<logic:iterate name="RegisterDataCustomerForm" property="registerConsumer.mailList" id="item" indexId="itemId">
														 	<tr>
																<td><%=mailCount+1%></td>
																<td>
																	<html:text styleClass="ODS_Text_Field_Size_50" property='<%="registerConsumer.mailList["+mailCount+"].mail" %>'  onkeyup="toUppercase(this)"  onblur="toUppercase(this)" maxlength="80" disabled="<%=formBean.isOnlyView() %>"/>
																</td>
															</tr>
		        										<% mailCount = mailCount+1;%>
		    											</logic:iterate>
	    											</logic:notEmpty>
	    											<logic:empty name="RegisterDataCustomerForm" property="registerConsumer.mailList">
 												    	<tr>
	    													<td>Cliente não possui emails cadastrados.</td>
	    												</tr>
	    											</logic:empty>
												</table>
											</td>
										</tr>
										<!-- Box de Fundos de Investimento -->
										<tr><td>&nbsp;</td></tr>
										<!-- Fim -->
										
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoInvest" class="cursor:pointer;" onclick="hiddenBox(this,'infoCidadania')">
												Cidadanias
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box de Fundos de Investimento -->
										
										<tr class="ODS_Detail_Line1" id="infoCidadania" style="display:none;">
											<td>
											
											
												<table>		
													<logic:notEmpty name="RegisterDataCustomerForm"  property="registerConsumer.citizenshipList">
														<% long citizemCount = 0;%>
														<logic:iterate name="RegisterDataCustomerForm" property="registerConsumer.citizenshipList" id="item" indexId="itemId">
														 	<tr>
																<td><%=citizemCount+1%></td>
																<td>
																
																	<html:select styleClass="ODS_Text_Field_Size_30" property='<%="registerConsumer.citizenshipList["+citizemCount+"].country" %>' disabled="<%=formBean.isOnlyView() %>">
			 															<html:option value=""></html:option>
		 																<html:optionsCollection  property="countryValues" label="resultDescription" value="resultCode"/>
		 															</html:select>
																</td>
															</tr>
		        										<%citizemCount = citizemCount+1;%>
		    											</logic:iterate>
	    											</logic:notEmpty>
	    											<logic:empty name="RegisterDataCustomerForm" property="registerConsumer.citizenshipList">
 												    	<tr>
	    													<td>Cliente não possui cidadanias cadastradas.</td>
	    												</tr>
	    											</logic:empty>
												</table>

											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Fim -->
										
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoInvest" class="cursor:pointer;" onclick="hiddenBox(this,'infoResidenciaFiscal')">
												Residências Fiscais
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box de Fundos de Investimento -->
										
										<tr class="ODS_Detail_Line1" id="infoResidenciaFiscal" style="display:none;">
											<td>
											
												<table>		
													<logic:notEmpty name="RegisterDataCustomerForm"  property="registerConsumer.fiscalResidenceList">
														<% long fiscalResidenceCount = 0;%>
														<logic:iterate name="RegisterDataCustomerForm" property="registerConsumer.fiscalResidenceList" id="item" indexId="itemId">
														 	<tr>
																<td><%=fiscalResidenceCount+1%></td>
																<td>
																
																	<html:select styleClass="ODS_Text_Field_Size_30" property='<%="registerConsumer.fiscalResidenceList["+fiscalResidenceCount+"].country" %>' disabled="<%=formBean.isOnlyView() %>">
			 															<html:option value=""></html:option>
		 																<html:optionsCollection  property="countryValues" label="resultDescription" value="resultCode"/>
		 															</html:select>
																</td>
															</tr>
		        										<% fiscalResidenceCount = fiscalResidenceCount+1;%>
		    											</logic:iterate>
	    											</logic:notEmpty>
	    											<logic:empty name="RegisterDataCustomerForm" property="registerConsumer.fiscalResidenceList">
 												    	<tr>
	    													<td>Cliente não possui Residências Fiscais cadastradas.</td>
	    												</tr>
	    											</logic:empty>
												</table>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										
										<!-- Fim -->
																				
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoRenda" class="cursor:pointer;" onclick="hiddenBox(this,'infoFatca')">
												FATCA
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										
										<!-- Box FATCA -->
										
										<tr class="ODS_Detail_Line1" id="infoFatca" style="display:none;">										
											<logic:equal name="RegisterDataCustomerForm" value="F" property="customerType">										
												<td>
													<table>
														<tr>
															<td>															
																<table width="100%" border="0" cellpadding="10" cellspacing="10">
																	<tr>																
																		<td colspan="8" style="text-align: center;">
																			<table id="registerConsumer.formTypeDiv" class="borderDivCheckBox" cellpadding="0" cellspacing="0" style="width: 150%;">
																				<tr>																			
																					<td>
																						<html:radio styleClass="radio" property="registerConsumer.formType" onclick="loadFormFatca();" value="0" disabled="<%=formBean.isOnlyView() %>"/>
																					</td>
																					<td>
																						Non US Person sem indício
														     		   				</td>
																					<td>
																						<html:radio styleClass="radio" property="registerConsumer.formType" onclick="loadFormFatca();" value="8" disabled="<%=formBean.isOnlyView() %>"/>
																					</td>
																					<td>
																						Non US Person com indício (W8)
														     		   				</td>
																					
														     		   				<td style="text-align: center;">
																						<html:radio styleClass="radio"  property="registerConsumer.formType" onclick="loadFormFatca();" value="9" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																					</td>
																					
																					<td>
																						US Person (W9)
																					</td>
																				</tr>									
																			</table>
																		</td>
																	</tr>
																</table>
																
																<table id="tabFormAssgnFatcaPF" style="display: none;" border="0" cellpadding="10" cellspacing="10">
																	<tr>
																		<td id="signatureDatew8Label" style="width: 50%;">
																			Data da assinatura  
											     		   				</td>
																		<td>&nbsp;</td>
																		<td id="signatureDatew8Imput">
																			<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.signatureDateFatca" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																		
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																	</tr>
																</table>	
															</td>
														</tr>
													</table>
												</td>
											</logic:equal>
											
											<logic:equal name="RegisterDataCustomerForm" value="J" property="customerType">			
												<td>
													<table>
														<tr>
															<td>
																<table border="0" cellpadding="8" cellspacing="8">
																	<tr>																	
																		<td>Empresa constituída nos EUA?</td>
																		<td>
																			<table width="100%" cellpadding="0" cellspacing="0">
																				<tr>
																					
																					<td>
																						<div id="registerConsumer.custFatcaPjInUsDiv" class="borderDivCheckBox" style="width: 100px;">																				
																							<html:radio styleClass="radio" property="registerConsumer.custFatcaPjInUs" onclick="loadFormW8W9FatcaPJ();"  value="true" disabled="<%=formBean.isOnlyView() %>"/>
																							Sim
						
																							<html:radio styleClass="radio" property="registerConsumer.custFatcaPjInUs" onclick="loadFormW8W9FatcaPJ();"  value="false" disabled="<%=formBean.isOnlyView() %>"/>
																							Não																						
																						</div>			
														     		   				</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
																
																<table border="0" cellpadding="8" cellspacing="8">
																	<tr>																	
																		<td>Instituição Financeira constituída fora dos EUA?</td>
																		<td>
																			<table width="100%" cellpadding="0" cellspacing="0">
																				<tr>
																					
																					<td>
																						<div id="registerConsumer.custFatcaPjOutUsDiv" class="borderDivCheckBox" style="width: 100px;">																				
																							<html:radio styleClass="radio" property="registerConsumer.custFatcaPjOutUs" onclick="loadFormW8W9FatcaPJ();"  value="true" disabled="<%=formBean.isOnlyView() %>"/>
																							Sim
						
																							<html:radio styleClass="radio" property="registerConsumer.custFatcaPjOutUs" onclick="loadFormW8W9FatcaPJ();"  value="false" disabled="<%=formBean.isOnlyView() %>"/>
																							Não																						
																						</div>			
														     		   				</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
																
																<table  border="0" cellpadding="8" cellspacing="8">
																	<tr>																	
																		<td>Empresa não financeira, com donos americanos detentores de mais de 10% do capital social?</td>
																		<td>
																			<table width="100%" cellpadding="0" cellspacing="0">
																				<tr>
																					<td>
																						<div id="registerConsumer.custFatcaPjOwnrUsDiv" class="borderDivCheckBox" style="width: 100px;">																				
																							<html:radio styleClass="radio" property="registerConsumer.custFatcaPjOwnrUs" onclick="loadFormW8W9FatcaPJ();"  value="true" disabled="<%=formBean.isOnlyView() %>"/>
																							Sim
						
																							<html:radio styleClass="radio" property="registerConsumer.custFatcaPjOwnrUs" onclick="loadFormW8W9FatcaPJ();"  value="false" disabled="<%=formBean.isOnlyView() %>"/>
																							Não																						
																						</div>			
														     		   				</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
																
																<table id="tabFormW8W9FatcaPJ" style="display: none;"  border="0" cellpadding="8" cellspacing="8">														
																	<tr>
																		<td>
																			Formulário
																		</td>
																	</tr>
																	<tr>
																		<td style="text-align: center;">
																			<table id="registerConsumer.formTypeDiv" class="borderDivCheckBox" cellpadding="0" cellspacing="0" style="width: 100%;">
																				<tr>
																					<td>&nbsp;</td>
																					<td>
																						<html:radio styleClass="radio" property="registerConsumer.formType" value="8" disabled="<%=formBean.isOnlyView() %>"/>
																					</td>
																					<td>
																						W8
														     		   				</td>
																					<td>&nbsp;&nbsp;</td>
														     		   				<td style="text-align: center;">
																						<html:radio styleClass="radio"  property="registerConsumer.formType" value="9" disabled="<%=formBean.isOnlyView() %>"/>										     		   					
																					</td>																					
																					<td>
																						W9
																					</td>
																					<td>&nbsp;&nbsp;&nbsp;</td>
																				</tr>									
																			</table>
																		</td>
																		
	
																		<td>&nbsp;</td>
																		
	
																		<td id="signatureDatew8Label">
																			Data da assinatura
											     		   				</td>
																		
																		<td id="signatureDatew8Imput">
																			<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.signatureDateFatca" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</logic:equal>

										</tr>
										
										<tr><td>&nbsp;</td></tr>
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoInvest" class="cursor:pointer;" onclick="hiddenBox(this,'infoCrs')">
												CRS
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
										</tr>
										
										<tr><td>&nbsp;</td></tr>
										
										<tr class="ODS_Detail_Line1" id="infoCrs" style="display:none;">
										
											<logic:equal  name="RegisterDataCustomerForm" value="F" property="customerType">
												<td>
													<table style="width: 100%;" border="0">
														<tr>
															<td>
																<table width="400px" border="0" cellpadding="8" cellspacing="8">
																	<tr>																
																		<td style="text-align: center;">
																			<table id="registerConsumer.isCrsTr"  border="0"  class="borderDivCheckBox" cellpadding="0" cellspacing="0" style="width: 100%;">
																				<tr>
																					<td style="width: 20px; text-align:center;">
																						<html:radio styleClass="radio" property="registerConsumer.isCrs" onclick="loadFormCrs();" value="false" disabled="<%=formBean.isOnlyView() %>"/>
																					</td>
																					<td>
																						Sem indício
														     		   				</td>
																					<td style="width: 20px; text-align:center;">
																						<html:radio styleClass="radio" property="registerConsumer.isCrs" onclick="loadFormCrs();" value="true" disabled="<%=formBean.isOnlyView() %>"/>
																					</td>
																					<td>
																						Com indício
														     		   				</td>
																				</tr>									
																			</table>
																		</td>
																	</tr>	
																</table>
															
																<table id="tabFormAssgnCrsPF" style="display: none;" border="0" cellpadding="8" cellspacing="8">
																	<tr>
																		<td style="text-align: center;">
																			<table class="borderDivCheckBox" cellpadding="0"  border="0"  cellspacing="0">
																				<tr>
																					<td>
																						&nbsp; Self Certification &nbsp;&nbsp;&nbsp;
																					</td>
																				</tr>									
																			</table>
																		</td>
																		<td>&nbsp;</td>
	
																		<td id="signatureDateCrsLabel">
																			Data da assinatura
											     		   				</td>								
																		<td id="signatureDateCrsImput">
																			<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.signatureDateCrs" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</logic:equal>
											
											<logic:equal name="RegisterDataCustomerForm" value="J" property="customerType">											
												<td>
													<table>
														<tr>
															<td>
																<table border="0" cellpadding="8" cellspacing="8">
																	<tr>																	
																		<td>Possui domicílios fiscais fora do Brasil?</td>
																		<td>
																			<table border="0" width="100%" cellpadding="0" cellspacing="0">
																				<tr>
																					
																					<td>
																						<div id="registerConsumer.custCrsPjAddrOutUsDiv" class="borderDivCheckBox" style="width: 100px;">																				
																							<html:radio styleClass="radio" property="registerConsumer.custCrsPjAddrOutUs" onclick="loadFormSelfCertCrsPJ();"  value="true" disabled="<%=formBean.isOnlyView() %>"/>
																							Sim
						
																							<html:radio styleClass="radio" property="registerConsumer.custCrsPjAddrOutUs" onclick="loadFormSelfCertCrsPJ();"  value="false" disabled="<%=formBean.isOnlyView() %>"/>
																							Não																						
																						</div>			
														     		   				</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>																
																<table  border="0" cellpadding="8" cellspacing="8">
																	<tr>																	
																		<td>Entidade não financeira passiva - ENF (entidade não financeira cujos resultados sejam preponderantemente relacionados a juros, royaties e/ou aluguéis)?</td>
																		<td>
																			<table width="100%" cellpadding="0" cellspacing="0">
																				<tr>
																					<td>
																						<div id="registerConsumer.custCrsPjEnfLiabDiv" class="borderDivCheckBox" style="width: 100px;">																				
																							<html:radio styleClass="radio" property="registerConsumer.custCrsPjEnfLiab" onclick="loadFormSelfCertCrsPJ();"  value="true" disabled="<%=formBean.isOnlyView() %>"/>
																							Sim
						
																							<html:radio styleClass="radio" property="registerConsumer.custCrsPjEnfLiab"  onclick="loadFormSelfCertCrsPJ();"  value="false" disabled="<%=formBean.isOnlyView() %>"/>
																							Não																						
																						</div>			
														     		   				</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
																
																<table  border="0" cellpadding="8" cellspacing="8">
																	<tr>																	
																		<td>Entidade de investimento (ex: fundo) domiciliada em uma jurisdição não participante do CRS e gerenciada por outra instituição financeira?</td>
																		<td>
																			<table width="100%" cellpadding="0" cellspacing="0">
																				<tr>
																					<td>
																						<div id="registerConsumer.custCrsPjInvstOutDiv" class="borderDivCheckBox" style="width: 100px;">																				
																							<html:radio styleClass="radio" property="registerConsumer.custCrsPjInvstOut" onclick="loadFormSelfCertCrsPJ();"  value="true" disabled="<%=formBean.isOnlyView() %>"/>
																							Sim
						
																							<html:radio styleClass="radio" property="registerConsumer.custCrsPjInvstOut" onclick="loadFormSelfCertCrsPJ();"  value="false" disabled="<%=formBean.isOnlyView() %>"/>
																							Não																						
																						</div>			
														     		   				</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
																
																<table id="tabFormSelfCertCrsPJ" style="display: none;" border="0" cellpadding="8" cellspacing="8">														
																	<tr>
																		<td>
																			Formulário
																		</td>
																	</tr>
																	<tr>																	
																		<td style="text-align: center;">
																			<table class="borderDivCheckBox"  border="0"  cellpadding="0" cellspacing="0" width="100%">
																				<tr>
																					<td>
																						&nbsp; Self Certification &nbsp;&nbsp;&nbsp;
																					</td>
																				</tr>									
																			</table>
																		</td>
																		<td>&nbsp;</td>
	
																		<td id="signatureDateCrsLabel">
																			Data da assinatura
											     		   				</td>
																		
																		<td id="signatureDateCrsImput">
																			<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.signatureDateCrs" maxlength="10" onkeydown="maskDate();" disabled="<%=formBean.isOnlyView() %>" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</logic:equal>
											
											
										</tr>
										<tr><td>&nbsp;</td></tr>
										<tr class="ODS_Detail_Line1">
											<td>
												<table width="200px" cellpadding="0" cellspacing="0">
													<tr>	
														<td style="text-align:left; ">
															<html:checkbox  property="registerConsumer.isAnnualReview" disabled="<%=formBean.isOnlyView() %>"/>									     		   					
														</td>
														<td style="text-align: left; font-weight: bold;">
															Revisão Anual (Reg. 3461)
							     		   				</td>
														
													</tr>
													<tr><td>&nbsp;</td></tr>
																										
												</table>
												
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>	
														<td style="text-align:left; ">
															Data da Última Revisão Anual:	
															<bean:write name="RegisterDataCustomerForm" property="registerConsumer.lastAnnualReviewDateFormatedDDMMYYYY"/>								     		   					
														</td>														
													</tr>
																										
												</table>
												
												
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>	
														<td style="text-align:left; ">
															Data de Inclusão:	
															<bean:write name="RegisterDataCustomerForm" property="registerConsumer.custCreateDateAndUser"/>							     		   					
														</td>														
													</tr>
																										
												</table>
												
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>	
														<td style="text-align:left; ">
															Data da Última Atualização:	
															<bean:write name="RegisterDataCustomerForm" property="registerConsumer.lastAuthDateFormatedDDMMYYYYAndUser"/>									     		   																				
							     		   				</td>
														
													</tr>
																										
												</table>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td>&nbsp;</td></tr>
										
										<tr class="ODS_Detail_Line1">

										

										
									</table>
								</td>
								<td>&nbsp;</td>
								
							</tr>
							
							<logic:equal name="RegisterDataCustomerForm" property="approve" value="false" >
								<logic:equal name="RegisterDataCustomerForm" property="onlyView" value="false" >
									<%formBean.resetCheckBoxs();%>
									<tr><td>&nbsp;</td></tr>
									<tr>
										<td colspan="3">
											<table class="ODS_internalWidth" border="0">
												<tbody>
													<tr>
														<td width="100%"></td>
														<TD>
														<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBCliAtualDadosCad()){ %>
															<html:button property="button" value="Salvar" onclick="submitActionNewCPB('save');"></html:button>
														<% }%>				
														</td>	
														
														<logic:equal name="RegisterDataCustomerForm" property="fromApprove" value="false" >								
															<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
														</logic:equal>	
														
														<logic:equal name="RegisterDataCustomerForm" property="fromApprove" value="true" >								
															<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
														</logic:equal>	
														
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</logic:equal>
								
								<logic:equal name="RegisterDataCustomerForm" property="onlyView" value="true" >
									
									<tr><td>&nbsp;</td></tr>
									<tr>
										<td colspan="3">
											<table class="ODS_internalWidth" border="0">
												<tbody>
													<tr>
														<td width="100%"></td>														
														
														
														<logic:equal name="RegisterDataCustomerForm" property="fromApprove" value="false">								
															<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
														</logic:equal>	
														
														<logic:equal name="RegisterDataCustomerForm" property="fromApprove" value="true">								
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
				<logic:equal name="RegisterDataCustomerForm" property="approve" value="true" >
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
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.lastUpdUserSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.lastUpdDateFormatedSafe" disabled="true"></html:text></td>
									<td width="33%"><html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.recStatCodeText" disabled="true"></html:text></TD>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="ODS_Detail_Line1"><td colspan="3">&nbsp;</td></tr>
					<tr class="ODS_Detail_Line2"><td colspan="3">&nbsp;</td></tr>
					<tr>
						<td colspan="3">
							<table class="ODS_internalWidth" border="0">
							<bean:define id="lastUpdUserId" name ="RegisterDataCustomerForm" property="registerConsumer.lastUpdUserSafe" type="java.lang.String"/>
								<tbody>
									<tr>
										<td width="100%"></td>		
										<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBAprovRej(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome())){ %>								
											<logic:equal name="RegisterDataCustomerForm" property="approve"  value="true" >
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
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
			
		<logic:equal name="RegisterDataCustomerForm" value="F" property="customerType">
			<script>
				loadPageRegisterCustomerPF(<%=formBean.getRegisterConsumer().getFormType() %>);
			</script>
		</logic:equal>
		
		<logic:equal name="RegisterDataCustomerForm" value="J" property="customerType">
			<script>
				loadPageRegisterCustomerPJ(<%=formBean.getRegisterConsumer().getFormType() %>);
			</script>
		</logic:equal>
			

			
		</html:form>

		
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
	
			
		<script type="text/javascript">

		
		 var input = document.getElementsByName('idDiffList')[0].value;
				
		if(input){
			var fields = input.substring(1).substring(0, input.length-2).replace(/\s/g, '').split(',');
			
			for (var i = 0; i < fields.length; i++) {
				if(document.getElementsByName('registerConsumer.' + fields[i])){
					if(document.getElementsByName('registerConsumer.' + fields[i])[0]){
						document.getElementsByName('registerConsumer.' + fields[i])[0].classList.add("fieldDiff");
					}
					
					
					if(document.getElementById('registerConsumer.' + fields[i])){
						document.getElementById('registerConsumer.' + fields[i]).style.border="1px solid red";	
					}
				}
			}
		} 
		
		</script>
</html:html>
