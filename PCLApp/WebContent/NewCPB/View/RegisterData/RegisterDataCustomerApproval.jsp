
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Common/css/citi.css">

<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="RegisterDataCustomerApproval" />
	<jsp:param name="approvalControlNames" value="'approveBtn','',''" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Cliente - Atualização de Dados Cadastrais</TITLE>
</HEAD>

<body>
	<TABLE class="ODS_internalWidth" border="0">
		<TBODY>
			<TR>
				<TD width="100%">&nbsp;</TD>
				<TD width="100%"><html:button property="approveBtn"
						value="Aprovar" onclick="submitActionNewCPB('approve');"></html:button></TD>
				<TD width="100%"><html:button property="reproveBtn"
						value="Reprovar" onclick="submitActionNewCPB('reprove');"></html:button></TD>
				<TD width="100%">
				<TD><html:button property="backBtn" value="Voltar"
						onclick="submitActionNewCPB('search');"></html:button></TD>
			</TR>
		</TBODY>
	</TABLE>
	<html:form action="/CentralApproval.do">
		
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet"/>
				<jsp:param name="currentSubSheet" value="Clientes" />
		    </jsp:include>
		   
			<html:text property="backURL" value="Product.ProductDetail.Update.Show" style="display:none"></html:text>
			<html:hidden property="findType" value="true"></html:hidden>

			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td width="100%">
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Cliente - Atualização de Dados Cadastrais </th>
								</tr>
							</thead>
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
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<!-- Box de Informação Básica de Cliente Pessoa Fisica-->
											
											<tr class="ODS_Detail_Line1" id="infoBaseNaturalPerson" style="display:none;">
												<td>
													<table>
														<tr>
															<td>Tipo de Cliente</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.customerType" disabled="true" maxlength="10"></html:text>
															</td>
															
															<td>Status do Cliente</td>
															<td>
																<html:select styleClass="ODS_Select_Field_Size_10" property="registerConsumer.customerStatus">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="customerStatusValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														<tr>
															<td>Nome</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.name" disabled="true" maxlength="20"></html:text>
															</td>	
														</tr>
														<tr>
															<td>CPF/CNPJ</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.cpfCnpj"  maxlength="50"></html:text>
															</td>
														</tr>
														<tr>
															<td>Data de nascimento</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_15" property="registerConsumer.birthDate" maxlength="50"></html:text>
															</td>
															
															<td style="text-align: right;">Sexo</td>
															<td>
																<html:select styleClass="ODS_Select_Field_Size_10" property="registerConsumer.gender">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="genderTypeValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														<tr>
															<td>Filiação</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_80" property="registerConsumer.filiation"  maxlength="50"></html:text>
															</td>
														</tr>	
														<tr>
															<td >Estado Civil</td>
															<td>
																<html:select styleClass="ODS_Text_Field_Size_20" property="registerConsumer.civilState">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="civilStateValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
															
															<td >N Dependentes</td>
															<td>
															<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.numberDependents" maxlength="50"></html:text>
															</td>
														</tr>
														
														<tr>
															<td>Nome do Conjuge</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.spouseName" maxlength="50"></html:text>
															</td>
														</tr>
														
														<tr>
															<td>Nacionalidade</td>
																<td colspan="3">
																<html:select styleClass="ODS_Text_Field_Size_30" property="registerConsumer.countryBirth">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="countryValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														<tr>
															<td>Naturalidade</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.placeOfBirth" maxlength="50"></html:text>
															</td>
															
															<td style="text-align: right;">UF</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.ufPlaceOfBirth" maxlength="50"></html:text>
															</td>
														</tr>
														<tr>
															<td>Documento de Identidade &nbsp;&nbsp; </td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.identityDocument"  maxlength="50"></html:text>
															</td>
														</tr>
														<tr>
															<td colspan="4">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="40%">Orgão Emissor</td>
																		<td>
																			<html:select styleClass="ODS_Text_Field_Size_30" property="registerConsumer.emitType">
					 															<html:option value=""></html:option>
				 																<html:optionsCollection  property="emitTypeValues" label="resultDescription" value="resultCode"/>
				 															</html:select>
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">UF &nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.emitDocumentUF"  maxlength="50"></html:text>
											     		   				</td>
											     		   				
											     		   			<td width="40%" style="text-align: right;">Dt. Emissão &nbsp;&nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.emitDocumentDate"  maxlength="50"></html:text>
											     		   				</td>
																	</tr>
																</table>
															</td>
														</tr>
														
														<tr>
															<td>Profissão</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.occupation" maxlength="50"></html:text>
															</td>
														</tr>
														
														<tr>
															<td>Natureza da Ocupação</td>
																<td colspan="3">
																<html:select styleClass="ODS_Text_Field_Size_30" property="registerConsumer.occupationNature">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="occupationNatureValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td colspan="3">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="28%">Renda Declarada </td>
																		<td >
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.declaredIncome"  maxlength="50"></html:text>
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">Patrimônio Declarado &nbsp;&nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.declaredHeritage"  maxlength="50"></html:text>
											     		   				</td>
																	</tr>
																</table>																
															</td>
														</tr>
														
														<tr>
															<td>Funcionario</td>
															<td>
																<html:radio styleClass="radio" property="registerConsumer.isEmployee" value="true"></html:radio>
																Sim
																
																<html:radio styleClass="radio" property="registerConsumer.isEmployee" value="false"></html:radio>
																Não
															</td>
															<td>Falecido</td>
															
															<td>		
																<html:radio styleClass="radio" property="registerConsumer.isDeceased" value="true"></html:radio>
																Sim
																
																<html:radio styleClass="radio" property="registerConsumer.isDeceased" value="false"></html:radio>
																Não
															</td>
														</tr>
													
													
														<tr>
													
															<td colspan="4">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="19%">SOEID Banker</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.SOEIDBankerNumber" maxlength="50"></html:text>
											     		   				</td>
																		
											     		   				<td>
																			<html:button property="button" value="Buscar" onclick="submitActionNewCPB('search');"></html:button>										     		   					
																		</td>
											     		   				
												     		   			<td>&nbsp;&nbsp;</td>
												     		   			<td width="40%" style="text-align: left;">xxxxxxxxxxxxxxxxxxxxxxxxxxx</td>
	
																	</tr>
																</table>
															</td>
														</tr>
													
														<tr>
															<td colspan="4">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="20%">Green Card</td>
																		<td>
																			<html:radio styleClass="radio" property="registerConsumer.haveGreenCard" value="true"></html:radio>
																			Sim
		
																			<html:radio styleClass="radio" property="registerConsumer.haveGreenCard" value="false"></html:radio>
																			Não
	
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">Social Security Number &nbsp;</td>
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.socialSecurityNumber" maxlength="50"></html:text>
											     		   				</td>
											     		   	
																	</tr>
																</table>
															</td>
														</tr>
													
														<tr>
															<td>Isento de IR</td>
															<td>
															
																<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="true"></html:radio>
																Sim
																
																<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="false"></html:radio>
																Não
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
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<!-- Box de Informação Básica Empresa -->
											
											<tr class="ODS_Detail_Line1" id="infoBaseLegalPerson" style="display:none;">
												<td>
													<table>
														<tr>
															<td>Tipo de Cliente</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.customerType" disabled="true" maxlength="10"></html:text>
															</td>
															
															<td>Status do Cliente</td>
															<td>
																<html:select styleClass="ODS_Select_Field_Size_10" property="registerConsumer.customerStatus">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="customerStatusValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td>Razão Social</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.name" disabled="true" maxlength="20"></html:text>
															</td>
															
														</tr>
														<tr>
															<td>CPF/CNPJ</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.cpfCnpj"  maxlength="50"></html:text>
															</td>
														</tr>
														
														<tr>
															<td>Atividade Principal</td>
															<td>
																<html:select styleClass="ODS_Text_Field_Size_20" property="registerConsumer.activityMain" >
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="activityMainValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td>NAIC (AMC)</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_60" property="registerConsumer.naicNumber"  disabled="true" maxlength="50"></html:text>
															</td>
														</tr>
														
														<tr>
															<td>SIC (AMC)</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_60" property="registerConsumer.sicNumber" disabled="true"  maxlength="50"></html:text>
															</td>
														</tr>
														
														<tr>
															<td >Forma de Constituição</td>
															<td>
																<html:select styleClass="ODS_Text_Field_Size_20" property="registerConsumer.constType">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="constTypeValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														<tr>
															<td colspan="3">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="26%">Data da Fundação</td>
																		<td >
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.foundationDate"  maxlength="50"></html:text>
											     		   				</td>
											     		   				
											     		   				<td width="40%" style="text-align: right;">Faturam. Médio Mensal (R$ Mil)</td>
											     		   				
											     		   				<td>
											     		   					<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.averageMonthBilling"  maxlength="50"></html:text>
											     		   				</td>
																	</tr>
																</table>
															</td>
														</tr>
														
														<tr>
															<td>Pais de Constituição</td>
																<td colspan="3">
																<html:select styleClass="ODS_Text_Field_Size_30" property="registerConsumer.countryBirth">
		 															<html:option value=""></html:option>
	 																<html:optionsCollection  property="countryValues" label="resultDescription" value="resultCode"/>
	 															</html:select>
															</td>
														</tr>
														
														
														<tr>
															<td>Nome do Administrador &nbsp;&nbsp;</td>
															<td>
																<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.admName" maxlength="50"></html:text>
															</td>
															
														</tr>
																											
														
														<tr>
															<td>CPF do Administrador</td>
															<td colspan="3">
																<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.admCpf"  maxlength="50"></html:text>
															</td>
														</tr>
														
														
														<tr>
															<td>Possui conta Flex DDA?</td>
															<td>
																<html:radio styleClass="radio" property="registerConsumer.hasFlexAccount" value="true"></html:radio>
																Sim
																
																<html:radio styleClass="radio" property="registerConsumer.hasFlexAccount" value="false"></html:radio>
																Não
															</td>
														</tr>
														<tr>
															<td colspan="4">
																<table width="100%" border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="19%">SOEID Banker</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.SOEIDBankerNumber" maxlength="50"></html:text>
											     		   				</td>
																		
											     		   				<td>
																			<html:button property="button" value="Buscar" onclick="submitActionNewCPB('search');"></html:button>										     		   					
																		</td>
											     		   				
												     		   			<td>&nbsp;&nbsp;</td>
												     		   			<td width="40%" style="text-align: left;">xxxxxxxxxxxxxxxxxxxxxxxxxxx</td>
																	</tr>
																</table>
															</td>	
														</tr>
														<tr>
															<td>Isento de IR</td>
															<td>
																<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="true"></html:radio>
																Sim
																
																<html:radio styleClass="radio" property="registerConsumer.exemptIR" value="false"></html:radio>
																Não
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
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box Classificação Citi -->
										
										<tr class="ODS_Detail_Line1" id="infoClassCity" style="display:none;">
											<td>
												<table>
												
													<tr>
														<td>Número GFCID</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.numberGFCID" disabled="true" maxlength="20"></html:text>
														</td>
														
													</tr>
													<tr>
														<td>Número EM</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.numberEM" disabled="true" maxlength="20"></html:text>
														</td>
														
													</tr>
													<tr>
														<td>Número ER</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.numberER"  maxlength="10"></html:text>
														</td>
														
														<td>Papel do Cliente no Relacionamento</td>
														<td>
															<html:select styleClass="ODS_Select_Field_Size_10" property="registerConsumer.customerRoleRelationship">
	 															<html:option value=""></html:option>
 																<html:optionsCollection  property="customerStatusValues" label="resultDescription" value="resultCode"/>
 															</html:select>
														</td>
													</tr>
													
													<tr>
														<td colspan="2">
															<table width="100%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="10%;" style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.isSensitive" value="true"></html:radio>
																	</td>
																	<td width="10%">
																		Sensitive
										     		   				</td>
																	
										     		   				<td width="10%" style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.isSensitive" value="false"></html:radio>										     		   					
																	</td>
																	
																	<td width="70%">
																		Non Sensitive
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													
													<tr>
														<td colspan="2">
															<table width="100%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="10%;" style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.typeClass" value="SPF"></html:radio>
																	</td>
																	<td width="30%">
																		Senior Public Figure
										     		   				</td>
																	
										     		   				<td width="10%" style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.typeClass" value="PRO"></html:radio>										     		   					
																	</td>
																	
																	<td width="20%">
																		Prominent SPF
																	</td>
																	
																	<td width="10%" style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.typeClass" value="NA"></html:radio>										     		   					
																	</td>
																	
																	<td width="20%">
																		N/A
																	</td>
																</tr>
															</table>
														</td>	
													</tr>
													
													<tr>
														<td colspan="2">
															<table width="100%" border="0" cellpadding="0" cellspacing="0">
																<tr>

																	<td width="40%">
																		Derogatory Information (BP&R)
										     		   				</td>
																	
										     		   				<td width="10%" style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.derogatoryInformation" value="true"></html:radio>										     		   					
																	</td>
																	
																	<td width="10%">
																		Sim
																	</td>
																	
																	<td width="10%" style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.derogatoryInformation" value="true"></html:radio>										     		   					
																	</td>
																	
																	<td width="30%">
																		Não
																	</td>
																</tr>
															</table>
														</td>
														
													</tr>
													
													<tr>
														<td>Razão Social</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.name" disabled="true" maxlength="20"></html:text>
														</td>
														
													</tr>
													<tr>
														<td>CPF/CNPJ</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.cpfCnpj"  maxlength="50"></html:text>
														</td>
													</tr>
													
													<tr>
														<td>Atividade Principal</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_20" property="registerConsumer.activityMain" >
	 															<html:option value=""></html:option>
 																<html:optionsCollection  property="activityMainValues" label="resultDescription" value="resultCode"/>
 															</html:select>
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
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
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
																<table width="100%" cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.residentialAddress.street"  maxlength="20"></html:text>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.residentialAddress.neighborhood" maxlength="20"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.residentialAddress.city" maxlength="20"></html:text>	
																		</td>
																		<td>
																		 	UF
																		</td>	
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.residentialAddress.uf" maxlength="20"></html:text>
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.residentialAddress.zipCode"  maxlength="20"></html:text>	
																		</td>
																	</tr>
																	<tr>
																		<td width="5%" style="text-align:center;">
																			<html:checkbox  property="registerConsumer.residentialAddress.isCorrespondence"></html:checkbox>									     		   					
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
																Comercial
															</td>
														</tr>
														<tr>
															<td>
																<table width="100%" cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.businessAddress.street" maxlength="20"></html:text>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.businessAddress.neighborhood" maxlength="20"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.businessAddress.city" maxlength="20"></html:text>	
																		</td>
																		<td>
																		 	UF
																		</td>	
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.businessAddress.uf"  maxlength="20"></html:text>
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.businessAddress.zipCode" maxlength="20"></html:text>	
																		</td>
																	</tr>
																	<tr>
																		<td width="5%" style="text-align:center;">
																			<html:checkbox  property="registerConsumer.businessAddress.isCorrespondence"></html:checkbox>									     		   					
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
																<table width="100%"  cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.otherAddress.street"  maxlength="20"></html:text>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.otherAddress.neighborhood" maxlength="20"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.otherAddress.city" maxlength="20"></html:text>	
																		</td>
																		<td>
																		 	UF
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.otherAddress.uf" maxlength="20"></html:text>
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.otherAddress.zipCode"  maxlength="20"></html:text>	
																		</td>
																	</tr>
																	<tr>
																		<td width="5%" style="text-align:center;">
																			<html:checkbox  property="registerConsumer.otherAddress.isCorrespondence"></html:checkbox>									     		   					
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
										
										<logic:equal name="RegisterDataCustomerForm" value="J" property="customerType">										
											<tr class="ODS_line11">
												<td>
													<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoRenda" class="cursor:pointer;" onclick="hiddenBox(this,'infoAddressLegal')">
													Endereços
												</td>
											</tr>
											<tr>
						 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
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
																<table width="100%" cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.headOfficeAddress.street" maxlength="20"></html:text>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.headOfficeAddress.neighborhood" maxlength="20"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.headOfficeAddress.city" maxlength="20"></html:text>	
																		</td>
																		<td>
																		 	UF
																		</td>	
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.headOfficeAddress.uf" maxlength="20"></html:text>
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.headOfficeAddress.zipCode" maxlength="20"></html:text>	
																		</td>
																	</tr>
																	<tr>
																		<td width="5%" style="text-align:center;">
																			<html:checkbox  property="registerConsumer.headOfficeAddress.isCorrespondence"></html:checkbox>									     		   					
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
																<table width="100%"  cellpadding="10" cellspacing="10">
																	<tr>
																		<td>
																			Endereço
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_50" property="registerConsumer.otherAddress.street" maxlength="20"></html:text>
																		</td>
			
																		<td colspan="2" style="text-align: right;">
																			Bairro
																		</td>
																		<td colspan="2">
																			<html:text styleClass="ODS_Text_Field_Size_30" property="registerConsumer.otherAddress.neighborhood" maxlength="20"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			Cidade
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_40" property="registerConsumer.otherAddress.city"  maxlength="20"></html:text>	
																		</td>
																		<td>
																		 	UF
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_5" property="registerConsumer.otherAddress.uf"  maxlength="20"></html:text>
																		</td>
																		<td>
																			CEP
																		</td>
																		<td>
																			<html:text styleClass="ODS_Text_Field_Size_20" property="registerConsumer.otherAddress.zipCode" maxlength="20"></html:text>	
																		</td>
																	</tr>
																	<tr>
																		<td width="5%" style="text-align:center;">
																			<html:checkbox  property="registerConsumer.otherAddress.isCorrespondence"></html:checkbox>									     		   					
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
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
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
																	<html:text styleClass="ODS_Text_Field_Size_5" property='<%="registerConsumer.telephoneList["+telephoneCount+"].ddd" %>'   maxlength="2"></html:text>
																</td>
																
																<td>
																	<html:text styleClass="ODS_Text_Field_Size_20" property='<%="registerConsumer.telephoneList["+telephoneCount+"].number" %>'   maxlength="9"></html:text>
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
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
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
																	<html:text styleClass="ODS_Text_Field_Size_50" property='<%="registerConsumer.mailList["+mailCount+"].mail" %>'   maxlength="2"></html:text>
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
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
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
																
																	<html:select styleClass="ODS_Text_Field_Size_30" property='<%="registerConsumer.citizenshipList["+citizemCount+"].country" %>'>
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
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
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
																
																	<html:select styleClass="ODS_Text_Field_Size_30" property='<%="registerConsumer.fiscalResidenceList["+fiscalResidenceCount+"].country" %>'>
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
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoRenda" class="cursor:pointer;" onclick="hiddenBox(this,'infoFatcaCrs')">
												FATCA / CRS
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box FATCA / CRS -->
										
										<tr class="ODS_Detail_Line1" id="infoFatcaCrs" style="display:none;">
											<td>
												<table>
													<tr>
														<td>
															<table width="100%" cellpadding="0" cellspacing="0">
															
																<tr>	
																	<td width="5%" style="text-align:center;">
																		<html:checkbox  property="registerConsumer.isFatca"></html:checkbox>									     		   					
																	</td>
																	<td colspan="5" width="95%" style="text-align: left;">
																		FATCA
										     		   				</td>
																</tr>
																
																<tr>	
																	<td width="5%" style="text-align:center;">
																		<html:checkbox  property="registerConsumer.isCrs"></html:checkbox>									     		   					
																	</td>
																	<td colspan="5" width="95%" style="text-align: left;">
																		CRS
										     		   				</td>
																	
																</tr>
															</table>
														
														
															<table width="100%" border="0" cellpadding="10" cellspacing="10">
														
																<tr>
																	<td colspan="8">
																		Formulário
																	</td>
																</tr>
																<tr>
																	<td style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.formType" value="W8"></html:radio>
																	</td>
																	<td>
																		W8
										     		   				</td>
																	
										     		   				<td style="text-align: center;">
																		<html:radio styleClass="radio" property="registerConsumer.formType" value="W9"></html:radio>										     		   					
																	</td>
																	
																	<td>
																		W9
																	</td>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td>
																		Data da assinatura W8
										     		   				</td>
																	
																	<td>
																		<html:text styleClass="ODS_Text_Field_Size_10" property="registerConsumer.signatureDatew8" maxlength="20"></html:text>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr class="ODS_Detail_Line1">
											<td>
												<table width="100%" cellpadding="1" cellspacing="0">
													<tr>	
														<td width="5%" style="text-align:left; ">
															<html:checkbox  property="numberGFCID"></html:checkbox>									     		   					
														</td>
														<td colspan="5" width="95%" style="text-align: left; font-weight: bold;">
															Revisão Anual (Reg. 3461)
							     		   				</td>
														
													</tr>
													
												</table>
											</td>
										</tr>
										
									</table>
								</td>
								<td>&nbsp;</td>
								
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td colspan="3">
									<TABLE class="ODS_internalWidth" border="0">
										<TBODY>
											<TR>
												<TD width="100%">&nbsp;</TD>
												<TD width="100%"><html:button property="approveBtn"
														value="Aprovar" onclick="submitActionNewCPB('approve');"></html:button></TD>
												<TD width="100%"><html:button property="reproveBtn"
														value="Reprovar" onclick="submitActionNewCPB('reprove');"></html:button></TD>
												<TD width="100%">
												<TD><html:button property="backBtn" value="Voltar"
														onclick="submitActionNewCPB('search');"></html:button></TD>
											</TR>
										</TBODY>
									</TABLE>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
		</html:form>
		<script>
			loadHiddenBox(document.getElementsByName("visibleBox")[0],document.getElementsByName("noVisibleBox")[0]);
		</script>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>