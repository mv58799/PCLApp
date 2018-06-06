
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.citibank.ods.modules.product.product.form.ProductDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<script language="javascript">
			function extraActions(action){
				if ( action == 'refresh' ) {
					document.forms[0].action = "Product.ProductDetail.Update.Show"; 
					document.forms[0].backURL.disabled = true; 
					document.forms[0].firstLoaded.value = "true";
				}
				if ( action == 'insertEmissor' ) {
					document.forms[0].action = "Product.ProductDetail.Update.InsertEmissor"; 
					document.forms[0].backURL.disabled = true; 
				}
				if ( action == 'deleteEmissor' ) {
					document.forms[0].action = "Product.ProductDetail.Update.DeleteEmissor"; 
					document.forms[0].backURL.disabled = true; 
				}								
				
			};
		</script>
			<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="Product.ProductDetail"/>		
				<jsp:param name="controlNames" value="'deleteEmissorBtn'" />
				<jsp:param name="fieldsWithMask" value="['prodCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
			</jsp:include>
			<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Inclusão / Alteração de Dados Complementares do Produto  </TITLE>
	</HEAD>

	<body>
		<html:form action="/Product.ProductDetail.Delete.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Produtos"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			
			<html:text property="backURL" value="Product.ProductDetail.Update.Show" style="display:none"></html:text>
			<html:hidden property="findType" value="true"></html:hidden>
			<html:hidden property="noVisibleBox"></html:hidden>
			<html:hidden property="visibleBox"></html:hidden>

			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td width="100%">
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Inclusão / Alteração do Produto - Dados Complementares</th>
								</tr>
							</thead>
							<tr>
								<td>&nbsp;</td>
								<td>
									<table class="ODS_internalWidth" border="0" cellspacing="0">
										<tr><td>&nbsp;</td></tr>
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoBase" class="cursor:pointer;" onclick="hiddenBox(this,'infoBase')">
												Informação Básica
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box de Informação Básica -->
										<tr class="ODS_Detail_Line1" id="infoBase" style="display:none;">
											<td>
												<table>
													<tr>
														<td>Código</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_10" property="prodCode" disabled="true" maxlength="10"></html:text>
														</td>
													</tr>
													<tr>
														<td>Nome MKT</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_20" property="prodName" disabled="true" maxlength="20"></html:text>
														</td>
													</tr>
													<tr>
														<td>Nome</td>
														<td>
															<html:text styleClass="ODS_Text_Field_Size_50" property="prodText" disabled="true" maxlength="50"></html:text>
														</td>
													</tr>
													<tr>
														<td>Segmento Sistema</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_50" property="sysSegCode" disabled="true">
	 															<html:option value=""></html:option>
 																<html:options property="systemSegmentCodeDomain.columnValuesByName(SYS_SEG_CODE)" labelProperty="systemSegmentCodeDomain.columnValuesByName(SYS_SEG_NAME)" />
 															</html:select>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<table width="100%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="25%">Status no Processador</td>
																	<td width="25%">
																		<html:select property="prodStatCode" styleClass="ODS_Select_Field_Size_5" disabled = "true" >
												    	    				<html:option value=""></html:option>
												    	    				<html:options property="loadProdStatCodeDomain.columnValuesByName(PROD_STAT_CODE)" labelProperty="loadProdStatCodeDomain.columnValuesByName(PROD_STAT_TEXT)" />
										     		   					</html:select>
										     		   				</td>
										     		   				<td width="20%">Código Sistema</td>
										     		   				<td>
										     		   					<html:text styleClass="ODS_Text_Field_Size_5" property="sysCode" disabled="true" maxlength="3"></html:text>
										     		   				</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>Família</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_30" property="prodFamlCode" styleId="prodFamlCode" disabled="true">
																<html:option value=""></html:option>
																<html:options property="prodFamlCodeDomain.columnValuesByName(PROD_FAML_CODE)" labelProperty="prodFamlCodeDomain.columnValuesByName(PROD_FAML_NAME)" />
															</html:select>
														</td>	
																								
													</tr>
													<tr>
														<td>Sub-Familia</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_40" property="prodSubFamlCode" styleId="prodSubFamlCode" onchange="submitAction('refresh');" >
																<html:option value=""></html:option>
			 													<html:options property="prodSubFamlCodeDomain.columnValuesByName(PROD_SUB_FAML_CODE)" labelProperty="prodSubFamlCodeDomain.columnValuesByName(PROD_SUB_FAML_NAME)" />
															</html:select>
															<html:hidden property="firstLoaded"></html:hidden>
														</td>
														
													</tr>
													<tr>
														<td>Qualificação</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_40" property="prodQlfyCode">
																<html:option value=""></html:option>
																<html:options property="prodQlfyCodeDomain.columnValuesByName(PROD_QLFY_CODE)" labelProperty="prodQlfyCodeDomain.columnValuesByName(PROD_QLFY_TEXT)" />
															</html:select>
														</td>	
																										
													</tr>
													<tr>
														<td>Agregador</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_40" property="prvtProdAggrCode">
																<html:option value=""></html:option>
																<html:options property="prvtProdAggrCodeDomain.columnValuesByName(PRVT_PROD_AGGR_CODE)" labelProperty="prvtProdAggrCodeDomain.columnValuesByName(PRVT_PROD_AGGR_TEXT)" />
															</html:select>		
														</td>
																
													</tr>
													<tr>
														<td>Categoria de Risco</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_40" property="prodRiskCatCode">
																<html:option value=""></html:option>
																<html:options property="prodRiskCatCodeDomain.columnValuesByName(PROD_INVST_RISK_CODE)" labelProperty="prodRiskCatCodeDomain.columnValuesByName(PROD_INVST_RISK_TEXT)" />
															</html:select>
														</td>
															
													</tr>
													<tr>
														<td>Veículo Legal</td>
														<td>
															<html:select property="prodLegalClassCode" styleClass="ODS_Select_Field_Size_20">
														       	 <html:option value=""></html:option>
														       	 <html:options property="loadProdLegalClassCodeDomain.columnValuesByName(PROD_LEGAL_CLASS_CODE)" labelProperty="loadProdLegalClassCodeDomain.columnValuesByName(PROD_LEGAL_CLASS_TEXT)" />
													        </html:select>
														</td>
														
													</tr>
													<tr>
														<td>Tipo de Ativo</td>
														<td>
															<html:select property="assetTypeCode" styleClass="ODS_Text_Field_Size_70" >
										     					<html:option value=""></html:option>
										     					<html:options property="assetTypeCodeDomain.columnValuesByName(ASSET_TYPE_CODE)" labelProperty="assetTypeCodeDomain.columnValuesByName(ASSET_TEXT)" />
										   					</html:select>
														</td>
													</tr>
													<tr>
														<td>Classificação Interface Global</td>
														<td>
										   					<html:select property="assocClassProdCode" styleClass="ODS_Text_Field_Size_70" >
										     					<html:option value=""></html:option>
										     					<html:options property="assocClassProdCodeDomain.columnValuesByName(ASSOC_CLASS_CODE)" labelProperty="assocClassProdCodeDomain.columnValuesByName(ASSOC_CLASS_TEXT)" />
										   					</html:select>
														</td>
													</tr>
													<tr>
														<td>Enviar Produto para o IA</td>
														<td>
										   					<html:select property="prodSentIaInd" styleClass="ODS_Select_Field_Size_20" >
										     					<html:option value=""></html:option>
										     					<html:option value="S">SIM</html:option>
										     					<html:option value="N">NÃO</html:option>
										   					</html:select>
														</td>
													</tr>
													<tr>
														<td>Classificação Tipo Credito</td>
														<td>
															<html:select property="prodCrTypeClassCode" styleClass="ODS_Select_Field_Size_10">
														       	 <html:option value=""></html:option>
														       	 <html:options property="loadprodCrTypeClassCodeDomain.columnValuesByName(CLASS_TYPE_CREDIT_CODE)" labelProperty="loadprodCrTypeClassCodeDomain.columnValuesByName(CLASS_TYPE_CREDIT_TEXT)" />
													        </html:select>
														</td>
													</tr>
													<tr>
														<td>Número da Conta Produto</td>
														<td colspan="2">
															<html:text styleClass="ODS_Text_Field_Size_15" property="prodAcctCode" disabled="true" maxlength="15"></html:text>
														</td>
													</tr>
													<tr>
														<td>Classificação Onesource</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_40" property="assetClassOnesrc">
																<html:option value=""></html:option>
																<html:options property="assetClassOnesrcDomain.columnValuesByName(prod_onesrc_asset_class_code)" labelProperty="assetClassOnesrcDomain.columnValuesByName(prod_onesrc_asset_class_text)" />
															</html:select>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Fim -->
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoInvest" class="cursor:pointer;" onclick="hiddenBox(this,'infoInvest')">
												Fundos de Investimento
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box de Fundos de Investimento -->
										<tr class="ODS_Detail_Line1" id="infoInvest" style="display: none;">
											<td>
												<table>
													<tr>
														<td>Nome Original do Produto</td>
														<td colspan="3">
															<html:text styleClass="ODS_Text_Field_Size_50" property="prodOrigName" maxlength="50"></html:text>
														</td>
													</tr>
													<tr>
														<td>Data de Aporte</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodEvnConTrbDate" maxlength="10" onkeyup="MaskFieldPress('CHAR','NN/NN/NNNN','left',null)" ></html:text></td>
														<td>Tipo de abertura</td>
														<td>
															<html:select property="prodOpenTypCode" styleClass="ODS_Select_Field_Size_20" >
										     					<html:option value=""></html:option>
										     					<html:option value="C">Constituído no Citibank</html:option>
										     					<html:option value="T">Transferência de outra Instituição</html:option>
										     					<html:option value="S">Cisão</html:option>
										   					</html:select>
										   				</td>
													</tr>
													<tr>
														<td>CNPJ</td>
														<td><html:text styleClass="ODS_Text_Field_Size_15" property="prodCnpjNbr" maxlength="18" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)" ></html:text></td>
														<td>Perfil Fundo</td>
														<td>
															<html:select property="prodFundPrflTyp" styleClass="ODS_Select_Field_Size_20" >
										     					<html:option value=""></html:option>
										     					<html:option value="1">I</html:option>
										     					<html:option value="2">II</html:option>
										     					<html:option value="3">III</html:option>
										     					<html:option value="4">IV</html:option>
										     					<html:option value="5">V</html:option>
										     					<html:option value="6">VI</html:option>
										   					</html:select>
										   				</td>
													</tr>
													<tr>
														<td>Conta CBLC</td>
														<td><html:text styleClass="ODS_Text_Field_Size_15" property="prodBovespaCode" maxlength="20" ></html:text></td>
														<td>ISIN</td>
														<td><html:text styleClass="ODS_Text_Field_Size_15" property="prodIsinCode" maxlength="20" ></html:text></td>
													</tr>
													<tr>
														<td>Conta BMF</td>
														<td><html:text styleClass="ODS_Text_Field_Size_15" property="prodBmfCode" maxlength="20" ></html:text></td>
														<td>Código ANBIMA</td>
														<td><html:text styleClass="ODS_Text_Field_Size_15" property="prodAnbidCode" maxlength="20"></html:text></td>
													</tr>
													<tr>
														<td>Conta Selic</td>
														<td><html:text styleClass="ODS_Text_Field_Size_15" property="prodSelicCode" maxlength="20"></html:text></td>
														<td>Conta Cetip</td>
														<td><html:text styleClass="ODS_Text_Field_Size_15" property="prodCetipCode" maxlength="20"></html:text></td>
													</tr>
													<tr>
														<td>Data do Evento Abertura</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodOpenEvnDate" maxlength="10" onkeyup="MaskFieldPress('CHAR','NN/NN/NNNN','left',null)" ></html:text></td>
														<td>Tipo de encerramento</td>
														<td>
															<html:select property="prodCloseTypCode" styleClass="ODS_Select_Field_Size_20" >
										     					<html:option value=""></html:option>
										     					<html:option value="R">Resgate</html:option>
										     					<html:option value="T">Transferência</html:option>
										     					<html:option value="I">Incorporado</html:option>
										   					</html:select>
										   				</td>
													</tr>
													<tr>
														<td>Taxa de Administração</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodAdminRate" maxlength="10" onkeyup="NumberFieldPress('3','3','FALSE',prodAdminRate)" ></html:text>(%)</td>
														<td>Taxa de Performance</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodPerfmRate" maxlength="10" onkeyup="NumberFieldPress('3','3','FALSE',prodPerfmRate)" ></html:text>(%)</td>
													</tr>
													<tr>
														<td>Taxa de ingresso</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodPrtfinvApplRate" maxlength="10" onkeyup="NumberFieldPress('3','3','FALSE',prodPrtfinvApplRate)" ></html:text>(%)</td>
														<td>Taxa de saída</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodExitRate" maxlength="14" onkeyup="NumberFieldPress('3','5','FALSE',prodExitRate)" ></html:text>(%)</td>
													</tr>
													<tr>
														<td>Tipo cota</td>
														<td>
															<html:select property="prodQuotTypeCode" styleClass="ODS_Select_Field_Size_20" >
										     					<html:option value=""></html:option>
										     					<html:option value="A">Abertura</html:option>
										     					<html:option value="F">Fechamento</html:option>
										   					</html:select>
										   				</td>
														<td>Horário de Movimentação</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodCloseTime" maxlength="5" onkeyup="MaskFieldPress('CHAR','NN:NN','left',null)" ></html:text></td>
													</tr>
													<tr>
														<td>Cotização Aplicação D+</td>
														<td><html:text styleClass="ODS_Text_Field_Size_5" property="prodDepQuotDateType" maxlength="3" onkeyup="MaskFieldPress('CHAR','NNN','left',null)" ></html:text></td>
														<td>Liquidação de Aplicação D+</td>
														<td><html:text styleClass="ODS_Text_Field_Size_5" property="prodApplLiqDateType" maxlength="3" onkeyup="MaskFieldPress('CHAR','NNN','left',null)" ></html:text></td>
													</tr>
													<tr>
														<td>Cotização Resgate D+</td>
														<td><html:text styleClass="ODS_Text_Field_Size_5" property="prodWthdrCrDateType" maxlength="3" onkeyup="MaskFieldPress('CHAR','NNN','left',null)" ></html:text></td>
														<td>Liquidação de Resgate D+</td>
														<td><html:text styleClass="ODS_Text_Field_Size_5" property="prodWthdrLiqDateType" maxlength="3" onkeyup="MaskFieldPress('CHAR','NNN','left',null)" ></html:text></td>
													</tr>
													<tr>
														<td>Aplicação inicial mínima</td>
														<td><html:text styleClass="ODS_Text_Field_Size_20" property="prodMinStaApplAmt" maxlength="20" onkeyup="NumberFieldPressWithTS('15','2',prodMinStaApplAmt)" ></html:text></td>
														<td>Resgate mínimo</td>
														<td><html:text styleClass="ODS_Text_Field_Size_20" property="prodMinWthdrAmt" maxlength="20" onkeyup="NumberFieldPressWithTS('15','2',prodMinWthdrAmt)" ></html:text></td>
													</tr>
													<tr>
														<td>Movimentação Mínima</td>
														<td><html:text styleClass="ODS_Text_Field_Size_20" property="prodMovMinAmt" maxlength="20" onkeyup="NumberFieldPressWithTS('15','2',prodMovMinAmt)" ></html:text></td>
														<td>Valor Permanência</td>
														<td><html:text styleClass="ODS_Text_Field_Size_20" property="prodHoldMinAmt" maxlength="20" onkeyup="NumberFieldPressWithTS('15','2',prodHoldMinAmt)" ></html:text></td>
													</tr>
													<tr>
														<td>Carência</td>
														<td>
															<html:select property="prodGraceInd" styleClass="ODS_Select_Field_Size_20" >
										     					<html:option value=""></html:option>
										     					<html:option value="S">Sim</html:option>
										     					<html:option value="N">Não	</html:option>
										   					</html:select>
										   				</td>
														<td>Data Enc. Balanço</td>
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="prodBalCloseDate" maxlength="10" onkeyup="MaskFieldPress('CHAR','NN/NN','left',null)" ></html:text></td>
													</tr>
													<tr>
														<td>Distribuição CVM</td>
														<td >
															<html:select property="prodCvmDistCode" styleClass="ODS_Select_Field_Size_15" >
										     					<html:option value=""></html:option>
										     					<html:option value="1">Regulamentação 476</html:option>
										     					<html:option value="2">Regulamentação 400</html:option>
										   					</html:select>
										   				</td>
										   				<td>Segmento</td>	
														<td>
															<html:select property="fundDistFormTypeCode">
																<html:option value="">
																</html:option>
																<html:option value="SKY">
																	SKY
																</html:option>
																<html:option value="FWD">
																	Forward
																</html:option>
																<html:option value="GLD">
																	GLD
																</html:option>
																<html:option value="CPC">
																	CPC
																</html:option>
																<html:option value="PVT">
																	Private
																</html:option>
																<html:option value="ALL">
																	ALL
																</html:option>
															</html:select>
														</td>		
													</tr>
													<tr>
														<td>Condomínio</td>
														<td >
															<html:select property="prodQuotCndmCode" styleClass="ODS_Select_Field_Size_15" >
										     					<html:option value=""></html:option>
										     					<html:option value="A">Aberto</html:option>
										     					<html:option value="F">Fechado</html:option>
										   					</html:select>
										   				</td>
										   				<td>
															Prazo
														</td>
														<td>
															<html:text property="termText" maxlength="50"></html:text>
														</td>
														
													</tr>
													<tr>
														<td>Restrito</td>
														<td >
															<html:select property="prodRstrnCode" styleClass="ODS_Select_Field_Size_15" >
										     					<html:option value=""></html:option>
										     					<html:option value="F">Vinculo Familiar</html:option>
										     					<html:option value="S">Grupo Societário</html:option>
										     					<html:option value="E">Grupo Econômico</html:option>
										     					<html:option value="D">Determinam por escrito a condição de solidariedade</html:option>
										   					</html:select>
										   				</td>
										   				<td>
															Data de Início Estratégia 
														</td>
														<td>
															<html:text  maxlength="10" onkeyup="MaskFieldPress('CHAR','NN/NN/NNNN','left',null)"  property="strategyStartDate"></html:text>
														</td>
													</tr>
													<tr>
														<td>Tributação CVM</td>
														<td >
															<html:select property="prodCvmTaxCode" styleClass="ODS_Select_Field_Size_15" >
										     					<html:option value=""></html:option>
										     					<html:option value="C">Curto</html:option>
										     					<html:option value="L">Longo</html:option>
										   					</html:select>
										   				</td>
														<td>
															Data de Encerramento Estratégia 
														</td>
														<td>
															<html:text  maxlength="10" onkeyup="MaskFieldPress('CHAR','NN/NN/NNNN','left',null)"  property="strategyCloseDate"></html:text>
														</td>
														
														
													</tr>
													<tr>
														<td>Forma de Divulgação(Gazeta)</td>
														<td >
															<html:select property="prodGazetaDclrFormCode" styleClass="ODS_Select_Field_Size_15" >
										     					<html:option value=""></html:option>
										     					<html:option value="N">Não divulgar</html:option>
										     					<html:option value="M">Aberto ao Mercado</html:option>
										     					<html:option value="I">Fundo Interno</html:option>
										     					<html:option value="E">Fundos Exclusivos</html:option>
										   					</html:select>
										   				</td>
										   				
														<td>
															Status Aplicação
														</td>
														<td>
															<html:select property="applicationStatCode" styleClass="ODS_Select_Field_Size_15">
																 <html:option value=""></html:option>
														       	 <html:option value="A">Aberto</html:option>
														       	 <html:option value="F">Fechado</html:option>
													        </html:select>
														</td>	
														
													</tr>
													<tr>
														<td>Classificação ANBIMA</td>
														<td >
															<html:select property="anbidFundClassCode" styleClass="ODS_Text_Field_Size_15" >
										     					<html:option value=""></html:option>
										     					<html:options property="anbidFundClassCodeDomain.columnValuesByName(ANBID_FUND_CLASS_CODE)" labelProperty="anbidFundClassCodeDomain.columnValuesByName(ANBID_FUND_CLASS_TEXT)" />
										   					</html:select>
										   				</td>
														<td>
															Status Resgate
														</td>
														<td>
															<html:select property="wthdrStatCode" styleClass="ODS_Select_Field_Size_15">
																 <html:option value=""></html:option>
														       	 <html:option value="A">Aberto</html:option>
														       	 <html:option value="F">Fechado</html:option>
														       	 
													        </html:select>
														</td>
													</tr>
													<tr>
														<td>Divulgar como ANBIMA</td>
														<td>
															<html:select property="prodAnbidDclrCode" styleClass="ODS_Select_Field_Size_15" >
										     					<html:option value=""></html:option>
										     					<html:option value="N">Não Divulgar</html:option>
										     					<html:option value="M">Aberto somente Mercado</html:option>
										     					<html:option value="I">Aberto somente Impressa</html:option>
										     					<html:option value="A">Aberto Mercado/Impressa</html:option>
										   					</html:select>
										   				</td>
										   				<td>
										   					Tipo De Taxa De Performace 
										   				</td>
										   				<td>
										   					<html:text property="perfmRateText" maxlength="250"></html:text>
										   				</td>
													</tr>
													<tr>
														<td>Gestor</td>
														<td>
															<html:select property="prodMgmtCnpjNbr" styleClass="ODS_Select_Field_Size_30">
																<html:option value=""></html:option>
																<html:options property="loadManagerDomain.columnValuesByName(PLYR_CNPJ_NBR)" labelProperty="loadManagerDomain.columnValuesByName(PLYR_NAME)" />
															 </html:select>
														</td>
														<td>
										   					Data De Encerramento/ Transferência 
										   				</td>
										   				<td>
										   					<html:text  maxlength="10" onkeyup="MaskFieldPress('CHAR','NN/NN/NNNN','left',null)"  property="closeDate"></html:text>
										   				</td>
													</tr>
													<tr>
														<td>Administrador</td>
														<td colspan="3">
															<html:select property="prodAdminCnpjNbr" styleClass="ODS_Select_Field_Size_30">
																<html:option value=""></html:option>
																<html:options property="loadAdministratorDomain.columnValuesByName(PLYR_CNPJ_NBR)" labelProperty="loadAdministratorDomain.columnValuesByName(PLYR_NAME)" />
															</html:select>
														</td>
													</tr>
													<tr>
														<td>Custodiante</td>
														<td colspan="3">
															<html:select property="prodCstdyCnpjNbr" styleClass="ODS_Select_Field_Size_30">
																<html:option value=""></html:option>
																<html:options property="loadCustodDomain.columnValuesByName(PLYR_CNPJ_NBR)" labelProperty="loadCustodDomain.columnValuesByName(PLYR_NAME)" />
															</html:select>
														</td>
													</tr>
													<tr>
														<td>Auditor</td>
														<td colspan="3">
															<html:select property="prodAuditCnpjNbr" styleClass="ODS_Select_Field_Size_30">
																<html:option value=""></html:option>
																<html:options property="loadAuditDomain.columnValuesByName(PLYR_CNPJ_NBR)" labelProperty="loadAuditDomain.columnValuesByName(PLYR_NAME)" />
															</html:select>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- Fim -->
										<tr><td>&nbsp;</td></tr>
										<tr class="ODS_line11">
											<td>
												<img src='<%= request.getContextPath() %>/Common/image/plus.gif' id="img_infoRenda" class="cursor:pointer;" onclick="hiddenBox(this,'infoRenda')">
												Renda Fixa
											</td>
										</tr>
										<tr>
					 						<td><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="131%" height="1"></td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<!-- Box de Renda Fixa -->
										<tr class="ODS_Detail_Line1" id="infoRenda" style="display: none;">
											<td>
												<table width="100%">
													<tr>
														<td>Emissor</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_40" property="emissorProdEmissorCode">
																<html:option value=""></html:option>
																<html:options property="prodEmissorsCodeDomain.columnValuesByName(PLYR_CNPJ_NBR)" labelProperty="prodEmissorsCodeDomain.columnValuesByName(PLYR_NAME)" />
															</html:select>																	
														</td>
													</tr>
													<tr>
														<td>Categoria de Risco</td>
														<td>
															<html:select styleClass="ODS_Text_Field_Size_40" property="emissorProdRiskCatCode">
																<html:option value=""></html:option>
																<html:options property="emissorProdRiskCatCodeDomain.columnValuesByName(PROD_INVST_RISK_CODE)" labelProperty="emissorProdRiskCatCodeDomain.columnValuesByName(PROD_INVST_RISK_TEXT)" />
															</html:select>	
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<html:button property="insertEmissorBtn" value="Incluir Emissor" onclick="submitAction('insertEmissor');"></html:button>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<html:hidden property="emissorSeqNbr" value="0" />
															<table border="0" width="100%">
																<tbody>
																	<tr class="ODS_Detail_Line1">
																		<th class="ODS_header" width="10%"></th>
																		<th class="ODS_header" width="50%">Emissor</th>
																		<th class="ODS_header" width="40%" nowrap="nowrap">Categoria de Risco</th>
																	</tr>																									
																	<logic:iterate name="ProductDetailForm" property="listProductPlayerRiskVO" indexId="index" id="emissor">
																		<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
																			<logic:equal name="step" value="0">
																				<tr class="ODS_line1">
																			</logic:equal>
																			<logic:equal name="step" value="1">
																				<tr class="ODS_line2">
																			</logic:equal>
																		</ods:CountStep>
																		<td width="3%">
																			<input class="radio" type="radio"
																			name="selection"
																			onclick="javascript:emissorSeqNbr.value='<%=index%>';disableButtons(false);">
																		</td>
																		<td class="alignLeft"> <bean:write name="emissor" property="data.playerEmissorText" /> </td>
																		<td class="alignLeft"> <bean:write name="emissor" property="data.catRiskText" /> </td>
																		</tr>	
																	</logic:iterate>					
																</tbody>
															</TABLE>
														</td>
													</tr>
													<tr>
														<td>
															<html:button property="deleteEmissorBtn" value="Excluir Emissor" disabled="true" onclick="submitAction('deleteEmissor');"></html:button>
														</td>
													</tr>
												</table>
											</td>											
										</tr>
										<!-- Fim -->
									</table>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3">
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<tr>
												<td width="100%"></td>
												<td><html:button property="alterBtn" value="Confirmar Inclusão/Alteração" onclick="submitAction('update');"></html:button></td>
												<td><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></td>														
												<td><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
											</tr>
										</tbody>
									</table>
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
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
