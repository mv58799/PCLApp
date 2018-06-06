
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>

<%@page import="com.citibank.ods.modules.product.product.form.ProductListForm,java.util.ArrayList"%>



<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		<TITLE> Consulta de Produtos </TITLE>
		
		<script language="javascript">
				function extraActions(action)
				{
					if(action== 'clearPage'){
						document.forms[0].action = "Product.ProductList.List.ClearPage";
						document.forms[0].backURL.disabled = true;	
					}
				};

				function setSelectedKeys(prodCode, sysCode, sysSegCode){
					document.forms[0].selectedProdCode.value = prodCode;
					document.forms[0].selectedSysCode.value = sysCode;
					document.forms[0].selectedSysSegCode.value = sysSegCode;
				};
		 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="Product.ProductList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'detailBtn','deleteBtn','complDataBtn'"/>
			<jsp:param name="searchInputFields" value="'prodCodeSrc'" />
		</jsp:include>

		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	</HEAD>

	<body>
		<html:form action="/Product.ProductList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Produtos"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
		<html:text property="backURL" value="Product.ProductList.List.Show" style="display:none"></html:text>
		<html:hidden property="orderBy" value=""/> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Produtos</th>
								</tr>
							</thead>
							<tr>
								<td >&nbsp;</td>
								<td>
									<table class="ODS_internalWidth" border="0">
										<tbody>
											<tr class="ODS_Detail_Line1">
												<TD width="13%">Código</TD>
												<TD colspan="6">
													<table class="ODS_internalWidth" border="0">
														<tr class="ODS_Detail_Line1">
															<TD><html:text styleClass="ODS_Text_Field_Size_10" property="prodCodeSrc" maxlength="10"></html:text></TD>
															<td>&nbsp;&nbsp;Nome</td>
															<td><html:text styleClass="ODS_Text_Field_Size_40" property="prodNameSrc" maxlength="20"></html:text></td>											
															<TD>&nbsp;</TD>
															<TD>&nbsp;</TD>	
															<TD>&nbsp;</TD>
															<TD>&nbsp;</TD>
															<TD>&nbsp;</TD>
															<TD>&nbsp;</TD>
														</tr>
													</table> 
												</TD>
 											</tr>

											<tr class="ODS_Detail_Line2">
												<td width="13%">Qualificação</td>
												<td width="25%">
													<html:select styleClass="ODS_Text_Field_Size_30" property="prodQlfyCodeSrc">
														<html:option value=""></html:option>
														<html:options property="prodQlfyCodeDomain.columnValuesByName(PROD_QLFY_CODE)" labelProperty="prodQlfyCodeDomain.columnValuesByName(PROD_QLFY_TEXT)" />
													</html:select>
												</td>
												<TD width="13%">&nbsp;&nbsp;Sub-família</TD>
												<td width="25%">
													<html:select styleClass="ODS_Text_Field_Size_30" property="prodSubFamlCodeSrc">
														<html:option value=""></html:option>
														<html:options property="prodSubFamlCodeDomain.columnValuesByName(PROD_SUB_FAML_CODE)" labelProperty="prodSubFamlCodeDomain.columnValuesByName(PROD_SUB_FAML_NAME)" />
													</html:select>
												</td>
												<TD width="7%">&nbsp;</TD>
												<TD width="7%">&nbsp;</TD>
											</tr>

											<tr class="ODS_Detail_Line1">
												<td width="13%">Cat. de Risco</td>
												<td width="25%">
													<html:select styleClass="ODS_Text_Field_Size_30" property="prodRiskCatCodeSrc">
														<html:option value=""></html:option>
														<html:options property="prodRiskCodeDomain.columnValuesByName(PROD_INVST_RISK_CODE)" labelProperty="prodRiskCodeDomain.columnValuesByName(PROD_INVST_RISK_TEXT)" />
													</html:select>
												</td>	
												<TD width="13%">&nbsp;&nbsp;Família</TD>
												<td width="25%"><html:select styleClass="ODS_Text_Field_Size_30" property="prodFamlCodeSrc">
														<html:option value=""></html:option>
														<html:options property="prodFamlCodeDomain.columnValuesByName(PROD_FAML_CODE)" labelProperty="prodFamlCodeDomain.columnValuesByName(PROD_FAML_NAME)" />
													</html:select>
												</td>		
												<TD width="7%">&nbsp;</TD>
												<TD width="7%">&nbsp;</TD>
											</tr>
										</tbody>
									</TABLE>
								</td>
								<td >&nbsp;</td>
							</tr>
						</TABLE>

						<table class="ODS_internalWidth" border="0">
							<tbody>
								<TR>
									<TD width="100%"></TD>
									<TD><html:button property="listBtn" value="Consultar" onclick="submitAction('list');" ></html:button></TD>
									<TD><html:button property="detailBtn" value="Detalhar" onclick="submitAction('detail'); " disabled = "true" ></html:button></TD>
									<TD><html:button property="deleteBtn" value="Excluir" onclick="submitAction('delete');  " disabled ="true" ></html:button></TD>
									<!--<TD><html:button property="complDataBtn" value="Incluir / Alterar" disabled = "true" onclick="submitAction('update');" ></html:button></TD>-->
									<TD><html:button property="clearBtn" value="Limpar" onclick="clearResultSetInServer();submitAction('clearPage');"></html:button></TD>
								</TR>
							</tbody>
						</TABLE>

						<html:hidden property="selectedProdCode" />
						<html:hidden property="selectedSysCode" value="0" />
						<html:hidden property="selectedSysSegCode" value="0" />
		
						<table border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead>
								<tr>
									<td>
										<DIV class=ODS_DivGridVerticalScroll>
											<table id="gridTable" width="1000px" border="0">
												<tbody>
																								
													<%									    
									    				ArrayList listTest = (ArrayList)request.getSession().getAttribute("lista");
													    String sort = (String)request.getParameter("ordenar");
									    
									    				if(listTest == null || sort == null ){
									      					ProductListForm frm = (ProductListForm)request.getSession().getAttribute("ProductListForm");
							                                request.getSession().setAttribute("lista",frm.getListProduct());
		            				                    }
									  				%>
									  				
									  				<logic:present name="lista">
								      <td>
									    <div>
										<display:table name="sessionScope.lista" uid="vo" id="vProd" pagesize="10" export="true" class="listaPaginada"  
 											    requestURI="/FrontController/Product.ProductList.List.Execute?ordenar=1&" style="width:100%" sort="list"  >											
 											    
 																						
											<bean:define name="vProd" property="aprovacao" id="approveFlg" type="java.lang.String"></bean:define>											
											<bean:define name="vProd" property="prodCode" id="selectedProdCode" type="java.lang.String"></bean:define>
											<bean:define name="vProd" property="sysCode"  id="selectedSysCode" type="java.lang.String"></bean:define>
											<bean:define name="vProd" property="sysSegCode" id="selectedSysSegCode" type="java.math.BigInteger"></bean:define>
										
											<display:column title="" media="html"  style="text-align:left;width:2%" >												
												<input type="radio" class="radio" name="selection" onclick="javascript:setSelectedKeys('<%=selectedProdCode%>','<%= selectedSysCode %>', '<%= selectedSysSegCode %>');disableButtons(false);"/>
											</display:column>
											
                                            <display:column title="" media="html"  style="text-align:center;width:2%" >																								
												<logic:equal name="approveFlg" value="0">
												  <a href="javascript:setSelectedKeys('<%= selectedProdCode %>','<%= selectedSysCode %>','<%= selectedSysSegCode %>');submitAction('update');">
												  <img src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0"></a>
											    </logic:equal>		
											</display:column>											
											
											<display:column title="Nome" property="prodName"  sortable="true" style="text-align:left;width:20%" >																								
											</display:column>
											
											<display:column title="Código" property="prodCode" sortable="true" style="text-align:right;width:10%">																																				
											</display:column>
											
											<display:column title="Família" property="prodFamlName" sortable="true" style="text-align:right;width:15%">																																				
											</display:column>
											
											<display:column title="Sub-família" property="prodSubFamlName" sortable="true" style="text-align:right;width:10%">																																				
											</display:column>
											
											<display:column title="Segmento" property="sysSegCode" sortable="true" style="text-align:right;width:05%">																																				
											</display:column>
											
											<display:setProperty name="export.pdf" value="false" />   
											<display:setProperty name="export.csv" value="false" />  
											<display:setProperty name="export.xml" value="false" />
											
										</display:table>	
									    </div>	
								     </td>						
							         </logic:present>
													
													
												</tbody>
											</TABLE>
										</div>
									</td>
								</tr>
							</TABLE>
			
	
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
								</td>
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

