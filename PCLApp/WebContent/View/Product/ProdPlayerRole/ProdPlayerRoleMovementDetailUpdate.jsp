
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.product.prodplayerrole.form.ProdPlayerRoleMovementDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
	<script language="javascript">

		function extraActions( action )
		{
			if ( action == 'insertDomain' ) {
				document.forms[0].action = "ProdPlayerRole.ProdPlayerRoleMovementDetail.Update.InsertDomain"; 
				document.forms[0].backURL.disabled = false; 
			}
			else if ( action == 'deleteDomain' ) {
				document.forms[0].action = "ProdPlayerRole.ProdPlayerRoleMovementDetail.Update.DeleteDomain"; 
				document.forms[0].backURL.disabled = false; 
			}
			else if ( action == 'Product.ProductList' ) {
				document.forms[0].action = "ProdPlayerRole.ProdPlayerRoleMovementDetail.Update.ListProduct"; 
				document.forms[0].backURL.disabled = false; 
			}

			else if ( action == 'clearPage' ) {
				document.forms[0].action = "ProdPlayerRole.ProdPlayerRoleMovementDetail.Update.ClearPage"; 
				document.forms[0].backURL.disabled = false; 
			}
		};

		function clearProduct()
		{
			document.forms[0].sysSegCodeSrc.value = "";
			document.forms[0].sysCodeSrc.value = "";
			document.forms[0].prodCodeSrc.value = "";
		}

		function setSelectedKeys(plyrCnpjNbr, plyrRoleType, prodCode, sysCode, sysSegCode){
			
			document.forms[0].selectedPlyrCnpjNbr.value = plyrCnpjNbr;
			document.forms[0].selectedPlyrRoleTypeCode.value = plyrRoleType;
			document.forms[0].selectedProdCodeGrid.value = prodCode;
			document.forms[0].selectedSysCodeGrid.value = sysCode;
			document.forms[0].selectedSysSegCodeGrid.value = sysSegCode;
		};

		//Verifica se tem ítens selecionados no grid e retorna true ou false.
		function verifyItemsSelected()
		{
			var isValidSize = false;
	
			var controls = document.getElementsByName('selectedItemsInGrid');
	
			for (i = 0; i < controls.length; i++)
			{
				if (controls != null)
				{
					if (controls[i].checked == true)
					{
						isValidSize = true;
					}
				}
			}
			return isValidSize;
		}
	
		//Adiciona validação na tela caso da lista de associações não ter ítens selecionados.
		function showErrorMessage(){
			if (!verifyItemsSelected()){
				validationMessageArea.innerHTML = "";
				validationMessageArea.innerHTML += 'Erro: O registro não pode ser alterado pois são obrigatórias uma ou mais associações com "Player".<br>';
				validationMessageArea.style.display = "inline";
				tableError.style.display = "inline"
			}
			else {
				submitAction('update');
			}
		}
	 </script>		
	 <jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	 	<jsp:param name="pageName" value="ProdPlayerRole.ProdPlayerRoleMovementDetail"/>
		<jsp:param name="fieldsWithMask" value="['plyrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
     </jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
	<TITLE>Alteração de Associação de Player x Produto com Pendência de Aprovação</TITLE>
	</HEAD>
	<body>
		<html:form action="/ProdPlayerRole.ProdPlayerRoleMovementDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Approved"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>						
			<html:text property="backURL" value="ProdPlayerRole.ProdPlayerRoleMovementDetail.Update.Show" style="display:none"></html:text>		    
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Alteração de Associação de Player x Produto com Pendência de Aprovação</th></tr></thead>
								<tbody>
									<tr class="ODS_line11" height="25"><td colspan="3">Player:</td></tr>
									<tr><td colspan="3" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td></tr>
									<tr class="ODS_Detail_Line1">
										<TD width="19%">CNPJ</TD>
										<TD width="58%">Nome do Player</TD>
										<TD width="23%">&nbsp;</TD>
									</tr>
									<tr class="ODS_Detail_Line2">
										<TD width="19%"><html:text styleClass="ODS_Text_Field_Size_15" property="plyrCnpjNbr" disabled="true" maxlength="18"></html:text></TD>
										<TD width="58%"><html:text styleClass="ODS_Text_Field_Size_60" property="plyrName" disabled="true" maxlength="60"></html:text></TD>
										<TD width="23%">&nbsp;</TD>
									</tr>
									<tr class="ODS_line11" height="25"><td colspan="3">Produto:</td></tr>
									<tr><td colspan="3" width="100%"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td></tr>
									<tr class="ODS_Detail_Line1">
										<td colspan="3" rowspan="2">
											<table>
												<tr>
													<td width="17%">Código
														<html:text styleClass="ODS_Text_Field_Size_10" property="prodCode" maxlength="10"></html:text>
													</td>	
													<td width="25%">Nome do Produto
														<html:text styleClass="ODS_Text_Field_Size_20" property="prodName" maxlength="20"></html:text>
													</td>
													<td width="15%" valign="bottom">
														<html:button property="getProduct" value="Consultar Produto" onclick="submitAction('Product.ProductList');"></html:button>
													</td>
													<td width="33%">&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr></tr>
									<tr class="ODS_Detail_Line1">
										<td rowspan="2" colspan="3">
											<table>
												<tr>
													<td width="24%">Papel do Player *</td>
												</tr>
												<tr>
													<td>
														<html:select styleClass="ODS_Text_Field_Size_15" property="plyrRoleTypeCode">
															<html:option value=""></html:option>
															<html:options property="prodPlayerRoleTypes.columnValuesByName(PLYR_ROLE_TYPE_CODE)" labelProperty="prodPlayerRoleTypes.columnValuesByName(PLYR_ROLE_TYPE_TEXT)" />
														</html:select>
													</td>
												</tr>
											</table>
										</td>	
									</tr>
									<tr></tr>
								</tbody>
							</TABLE>
						</td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td>
							<table class="ODS_internalWidth" border="0" cellspacing="0">
								<thead><tr><th class="subtitle" scope="colgroup" colspan="3" width="100%">Lista de Produtos</th></tr></thead>
									<tr>
										<td colspan="3">								
											<div class="ODS_DivGridVertical">
												<table class="ODS_internalWidth" border="0">
													<tbody>
														<tr class="fixed">
															<TH class="ODS_header" width="10%">Inserir</TH>
															<TH class="ODS_header" width="15%">Código do Produto</TH>
															<TH class="ODS_header" width="20%">Código do Sistema</TH>
															<TH class="ODS_header" width="20%">Segmento do Sistema</TH>
															<TH class="ODS_header" width="35%">Descrição do Produto</TH>
														</tr>
														<%int auxIndex = 0;%>
														<logic:notEmpty name="ProdPlayerRoleMovementDetailForm"	property="listProduct">
															<logic:iterate name="ProdPlayerRoleMovementDetailForm"	property="listProduct" indexId="index2" id="row">
																<%
																	ProdPlayerRoleMovementDetailForm prodPlayerRoleMovementDetailForm = (ProdPlayerRoleMovementDetailForm) session.getAttribute("ProdPlayerRoleMovementDetailForm");
																	String[] resultList = prodPlayerRoleMovementDetailForm.getListProduct()[auxIndex++];
																%>
																<ods:CountStep counterName="index2" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
																	<logic:equal name="step" value="0">
																		<tr class="ODS_line1">
																	</logic:equal>
																	<logic:equal name="step" value="1">
																		<tr class="ODS_line2">
																	</logic:equal>
																</ods:CountStep>
																<bean:define name="ProdPlayerRoleMovementDetailForm" property='<%="selectedProduct["+index2+"]"%>' id="selectedProduct" type="java.lang.String" />
																<td width="10%" align="center"><input type="checkbox" name=<%="selectedProduct["+index2+"]"%> value="S" <%=selectedProduct.equals("S")?"checked=\"checked\"":""%> class="checkbox" ></td>
																<td width="15%" align="center"><%=resultList[0]%></td>
																<td width="20%" align="center"><%=resultList[1]%></td>
																<td width="20%" align="center"><%=resultList[2]%></td>
																<td width="35%" align="left"><%=resultList[3]%></td>
															</logic:iterate>
														</logic:notEmpty>
													</tbody>
												</TABLE>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td>&nbsp;</td>
							<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%"></TD>
											<TD><html:button property="insertProduct" value="Adicionar Produtos" onclick="submitAction('insertDomain');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
				
								<html:hidden property="selectedPlyrCnpjNbr" value="" />
								<html:hidden property="selectedPlyrRoleTypeCode" value="" />
								<html:hidden property="selectedProdCodeGrid" value="" />
								<html:hidden property="selectedSysCodeGrid" value="" />
								<html:hidden property="selectedSysSegCodeGrid" value="" />

								<table class="ODS_internalWidth" border="0">
									<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Produtos Associados<th></tr></thead>
									<tr>
										<td>
											<table class="ODS_internalWidth" border="0">
												<tbody>
													<tr class="fixed">
														<TH class="ODS_header" width="7%">Inserir</TH>
														<TH class="ODS_header" width="7%">Excluir</TH>
														<TH class="ODS_header" width="15%">CNPJ Player</TH>
														<TH class="ODS_header" width="15%">Papel do Player</TH>
														<TH class="ODS_header" width="10%">Código do Produto</TH>
														<TH class="ODS_header" width="15%">Código do Sistema</TH>
														<TH class="ODS_header" width="15%">Segmento do Sistema</TH>
														<TH class="ODS_header" width="15%">Descrição do Produto</TH>
														<TH class="ODS_header" width="10%">Ação</TH>
													<%int rowIndex = 0;%>
													<logic:notEmpty name="ProdPlayerRoleMovementDetailForm"	property="prodPlayerRoleDomains">
														<logic:iterate name="ProdPlayerRoleMovementDetailForm"	property="prodPlayerRoleDomains" indexId="index" id="row">
															<%
																ProdPlayerRoleMovementDetailForm prodPlayerRoleForm = (ProdPlayerRoleMovementDetailForm) session.getAttribute("ProdPlayerRoleMovementDetailForm");
																String[] resultLine = prodPlayerRoleForm.getProdPlayerRoleDomains()[rowIndex++];
															%>
															<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
																<logic:equal name="step" value="0">
																	<tr class="ODS_line1">
																</logic:equal>
																<logic:equal name="step" value="1">
																	<tr class="ODS_line2">
																</logic:equal>
															</ods:CountStep>
			
															<bean:define name="ProdPlayerRoleMovementDetailForm" property='<%="selectedItemsInGrid["+index+"]"%>' id="selectedItemsInGrid" type="java.lang.String" />
															<bean:define name="ProdPlayerRoleMovementDetailForm" property='<%="deletedItems["+index+"]"%>' id="deletedItems" type="java.lang.String" />
															<td width="7%" align="center"><input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" disabled="disabled">
																<input type="checkbox"	name=<%="selectedItemsInGrid["+index+"]"%> value="S" <%=selectedItemsInGrid.equals("S")?"checked=\"checked\"":""%> class="checkbox" style="display:none"></td>									
															<td width="7%" align="center"><input type="checkbox"	name=<%="deletedItems["+index+"]"%> value="S" <%=deletedItems.equals("S")?"checked=\"checked\"":""%> class="checkbox" /></td>									
															<td class="alignLeft"><%=resultLine[0]%></td>
															<td class="alignLeft"><%=resultLine[1]%></td>
															<td class="centralized"><%=resultLine[2]%></td>
															<td class="alignLeft"><%=resultLine[3]%></td>
															<td class="centralized"><%=resultLine[4]%></td>
															<td class="alignLeft"><%=resultLine[5]%></td>
															<td class="alignLeft"><%=resultLine[6]%></td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</tbody>
											</TABLE>
										</tr>
									</TABLE>
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td>
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<TR>
												<TD width="100%" align="left">&nbsp;</TD>
												<TD><html:button property="updateBtn" value="Alterar" onclick="submitAction('update');"></html:button></TD>
												<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();submitAction('clearPage')"></html:button></TD>
												<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>																				
											</TR>
										</tbody>
									</TABLE>
								</td>
							</tr>
						<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
					</table>
				</html:form>
			</body>
		<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
