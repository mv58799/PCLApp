<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<script language="javascript">
			function extraActions(action){};
		</script>
			<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="Product.ProductMovementDetail"/>
			</jsp:include>
			<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Consulta Detalhada de Produto Dados Complementares com Pendência de Aprovação </TITLE>
	</HEAD>

	<body>
		<html:form action="/Product.ProductMovementDetail.Consult.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Product.ProductMovementDetail.Consult.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta Detalhada de Produto Dados Complementares com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td >&nbsp;</td>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<td width="33%">Código Sistema:</td>
											<TD width="5"></TD>
											<td width="36%">Segmento Sistema:</td>
											<TD width="5"></TD>
											<td width="27%">Código Sistema Processador:</td>
											<TD></TD>
										</tr>						

										<tr class="ODS_Detail_Line2">
											<td width="33%">
												<html:text styleClass="ODS_Text_Field_Size_5" property="sysCode" disabled="true"></html:text>
											</td>
											<TD width="5"></TD>
											<td width="36%">
												<html:select styleClass="ODS_Text_Field_Size_5" property="sysSegCode" disabled="true">
													<html:option value="SistemaOrigem"></html:option>
													<html:option value="SistemaOrigem1">Sistema Origem 1</html:option>
													<html:option value="SistemaOrigem2">Sistema Origem 2</html:option>
													<html:option value="SistemaOrigem3">Sistema Origem 3</html:option>
												</html:select>
											</td>
											<TD width="5"></TD>
											<td width="27%">
												<html:text styleClass="ODS_Text_Field_Size_5" property="prodProcSysCode" disabled="true"></html:text>
											</td>
											<TD></TD>
										</tr>				

										<tr class="ODS_Detail_Line1">
											<TD width="33%">Segmento Sistema Processador:</TD>
											<TD width="5"></TD>
											<td width="36%">Familia:</td>
											<TD width="5"></TD>
											<td width="27%">Código:</td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_5" property="prodProcSysSegCode" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:select styleClass="ODS_Text_Field_Size_30" property="prodFamlCode" disabled="true">
													<html:option value=""></html:option>
													<html:options property="prodFamlCodeDomain.columnValuesByName(PROD_FAML_CODE)" labelProperty="prodFamlCodeDomain.columnValuesByName(PROD_FAML_NAME)" />
												</html:select></td>
											<TD width="5"></TD>
											<td width="27%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodCode" disabled="true"></html:text></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="33%">Nome Produto:</TD>
											<TD width="5"></TD>
											<TD width="36%">Descrição do Produto:</TD>
											<TD width="5"></TD>
											<TD width="27%"></TD>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="prodName" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_40" property="prodText" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<TD width="27%"></TD>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">Data Criação Produto:</td>
											<TD width="5"></TD>
											<td width="36%">Data Início Operação:</td>
											<TD width="5"></TD>
											<td width="27%"></td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodCreateDate" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodOpernStaDate" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="27%"></td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td width="33%">Sub-Familia:</td>
											<TD width="5"></TD>
											<td width="36%">Qualificação Produto:</td>
											<TD width="5"></TD>
											<td width="27%">Agregador Produto:</td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td width="33%">
												<html:select styleClass="ODS_Text_Field_Size_10" property="prodSubFamlCode">
													<html:option value="prodQlfyCode"></html:option>
													<html:option value="prodQlfyCode1">Código Sub-Familia 1</html:option>
													<html:option value="prodQlfyCode2">Código Sub-Familia 2</html:option>
													<html:option value="prodQlfyCode3">Código Sub-Familia 3</html:option>
												</html:select>
											</td>
											<TD width="5"></TD>
											<td width="36%">
												<html:select styleClass="ODS_Text_Field_Size_40" property="prodQlfyCode" disabled="true">
													<html:option value="prodQlfyCode"></html:option>
													<html:option value="prodQlfyCode1">Cod. Qual. Prod. 1</html:option>
													<html:option value="prodQlfyCode2">Cod. Qual. Prod. 2</html:option>
													<html:option value="prodQlfyCode3">Cod. Qual. Prod. 3</html:option>
												</html:select>
											</td>			
											<TD width="5"></TD>								
											<td width="27%">
												<html:select styleClass="ODS_Text_Field_Size_20" property="prvtProdAggrCode" disabled="true">
													<html:option value="CodAgregProd"></html:option>
													<html:option value="CodAgregProd1">Cód. Agreg. Prod. 1</html:option>
													<html:option value="CodAgregProd2">Cód. Agreg. Prod. 2</html:option>
													<html:option value="CodAgregProd3">Cód. Agreg. Prod. 3</html:option>
												</html:select>
											</td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td width="33%">Classificação Tipo Credito:</td>
											<TD width="5"></TD>
											<td width="36%">Categoria de Risco:</td>
											<TD width="5"></TD>
											<td width="27%">Código Moeda:</td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
                                            <td width="33%">
												<html:text styleClass="ODS_Text_Field_Size_5" property="prodCrTypeClassCode" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%">
												<html:select styleClass="ODS_Text_Field_Size_40" property="prodRiskCatCode" disabled="true">
													<html:option value="CodCategRisco"></html:option>
													<html:option value="CodCategRisco1">Cod. Categ. Risco 1</html:option>
													<html:option value="CodCategRisco2">Cod. Categ. Risco 2</html:option>
													<html:option value="CodCategRisco3">Cod. Categ. Risco 3</html:option>
												</html:select>
											</td>
											<TD width="5"></TD>
											<td width="27%"><html:text styleClass="ODS_Text_Field_Size_5" property="prodCcyCode" disabled="true"></html:text></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">Indicador de Contrato Sob Política:</td>
											<TD width="5"></TD>
											<td width="36%"></td>
											<TD width="5"></TD>
											<td width="27%"></td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:checkbox style="border: none" property="citiGrpTieReltnPlcyInd" disabled="true">23A</html:checkbox></td>
											<TD width="5"></TD>
											<td width="36%"><html:checkbox style="border: none" property="citiGrpTieRstrnPlcyInd" disabled="true">23B</html:checkbox></td>
											<TD width="5"></TD>
											<td width="27%"></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">Código Selic:</td>
											<TD width="5"></TD>
											<td width="36%">Codigo Bovespa:</td>
											<TD width="5"></TD>
											<td width="27%">Código BMF:</td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodSelicCode" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodBovespaCode" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="27%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodBmfCode" disabled="true"></html:text></td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td width="33%">Codigo ISO:</td>
											<TD width="5"></TD>
											<td width="36%">Código Anbid:</td>
											<TD width="5"></TD>
											<td width="27%">Codigo Cetip:</td>
											<TD></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_5" property="prodIsoCode" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodAnbidCode" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="27%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodCetipCode" disabled="true"></html:text></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">Administrador:</td>
											<TD width="5"></TD>
											<td width="36%">Custodiante:</td>
											<TD width="5"></TD>
											<td width="27%">Auditor:</td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line2">	
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_5" property="prodAdminCnpjNbr" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodCstdyCnpjNbr" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="27%"><html:text styleClass="ODS_Text_Field_Size_15" property="prodAuditCnpjNbr" disabled="true"></html:text></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">CNPJ Controlador:</td>
											<TD width="5"></TD>
											<td width="36%">CNPJ Gestor:</td>
											<TD width="5"></TD>
											<td width="27%"></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line2">
											<td width="33%">
												<html:text styleClass="ODS_Text_Field_Size_15" property="prodCtlCnpjNbr" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%">
												<html:text styleClass="ODS_Text_Field_Size_15" property="prodMgmtCnpjNbr" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="27%"></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">Codigo Usuario Aprovação:</td>
											<TD width="5"></TD>
											<td width="36%">Data Aprovação:</td>
											<TD width="5"></TD>
											<td width="27%">Data Autorização:</td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="lastAuthUserId" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_10" property="prodApprvDate" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="27%"><html:text styleClass="ODS_Text_Field_Size_10" property="lastAuthDate" disabled="true"></html:text></td>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">Codigo Usuario Ultima Alteração:</td>
											<TD width="5"></TD>
											<td width="36%">Data Ultima Alteração:</td>
											<TD width="5"></TD>
											<TD width="27%"></TD>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserId" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_10" property="lastUpdDate" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<TD width="27%"></TD>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<td width="33%">Status Produto:</td>
											<TD width="5"></TD>
											<td width="36%">Status Registro:</td>
											<TD width="5"></TD>
											<TD width="27%"></TD>
											<TD></TD>
										</tr>

										<tr class="ODS_Detail_Line2">
											<td width="33%"><html:text styleClass="ODS_Text_Field_Size_5" property="prodStatCode" disabled="true"></html:text></td>
											<TD width="5"></TD>
											<td width="36%"><html:text styleClass="ODS_Text_Field_Size_5" property="recStatCode" disabled="true"></html:text></td>											
											<TD width="5"></TD>
											<TD width="27%"></TD>
											<TD></TD>
										</tr>
									</tbody>
								</TABLE>
								</td>
								<td >&nbsp;</td>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%"></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
			</table>	
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>		
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
