<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>

<%@page import="com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtListForm,java.util.ArrayList,com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtEntityVO"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">
<script language=javascript>
	function extraActions(action)
	{
	
		if ( action == 'custAddress' )
		{
			document.forms[0].action = "CustAddress.CustAddressList.List.Execute";
			document.forms[0].backURL.disabled= false;
			document.forms[0].custNbrSrc.disabled = true;
		}

		else if (action =='Officer.OfficerList')
		 {
		  document.forms[0].action = "Officer.OfficerList.List.Show";
		  document.forms[0].backURL.disabled = false;
		 }

		else if (action =='deleteCmpl')
		 {
		  document.forms[0].action = "CustomerPrvtCmpl.CustomerPrvtCmplDetail.Delete.Show";
		  document.forms[0].backURL.disabled = false;
		 }

		else if (action =='insertCmpl')
		 {
			  document.forms[0].action = "CustomerPrvtCmpl.CustomerPrvtCmplDetail.Insert.Show";
			  document.forms[0].backURL.disabled = false;
		 }

		else if (action == 'contact')
		{
			document.forms[0].action = "ContactCust.ContactCustList.List.Execute";
			document.forms[0].backURL.disabled= false;
		}

		else if(action== 'clearPage'){
			document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.ClearPage";
			document.forms[0].backURL.disabled = true;	
		}

		else if(action== 'bkrPortfMgmt'){
			document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtDetail.Insert.Show";
			document.forms[0].backURL.disabled = false;	
		}

		else if(action== 'approveBkrPortfMgmt'){
			document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtMovementList.List.Show";
			document.forms[0].backURL.disabled = false;	
		}

		if ( action =='insertCmpl' || action =='deleteCmpl' || action =='detail' || action =='update' || action =='delete' || action =='contact'|| action =='custAddress' )
		{
			document.forms[0].custNbrSrc.disabled = true;
		} 
		
		else if (action =='list')
		{
			document.forms[0].selectedCustNbr.value=document.forms[0].custNbrSrc.value;
			document.forms[0].selectedCustNbrList.value=document.forms[0].custNbrSrc.value;
		}

	};

	function hasCmplData(control)
	{
		var hasCmpl = '1';

		if ( control == hasCmpl )
		{
		  //document.forms[0].insertBtn.disabled = false;
		  document.forms[0].deleteBtn.disabled = false;
		} else 
			{
			  //document.forms[0].insertBtn.disabled = false;
			  document.forms[0].deleteBtn.disabled = true;
			}
	};
	
	function setKeys(custNbr, cmpl){
		
		document.forms[0].selectedCustNbr.value = custNbr;
		document.forms[0].selectedCustNbrList.value = custNbr;
		document.forms[0].cmplDataButtonControl.value = cmpl;	
		submitAction('insertCmpl');
		hasCmplData(cmpl);		
	};	
	
</script>


<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="CustomerPrvt.CustomerPrvtList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn', 'contactBtn', 'contactDetailBtn', 'custAddressBtn', 'bkrPortfMgmtBtn'" />
    <jsp:param name="searchInputFields" value="'custNbrSrc','custFullNameTextSrc', 'custCpfCnpjNbrSrc', 'curAcctNbrSrc', 'reltnNbrSrc'" />
	<jsp:param name="fieldsWithMask" value="['custCpfCnpjNbrSrc','CPF/CNPJ',null,null,null]"/>
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de Cliente Private</TITLE>
</HEAD>
<body>
<html:form action="/CustomerPrvt.CustomerPrvtList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet"/>
		<jsp:param name="currentSubSheet" value="Clientes" />
    </jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:hidden property="clrScreen" value="true"/>
	<table class="ODS_mainTable" cellspacing="0" width="100%">
		<tr>
			<td>&nbsp;</td>
			<td>
				<html:hidden property="selectedCustNbr"/>
				<html:hidden property="cmplDataButtonControl" value=""/>
				<html:hidden property="selectedCustNbrList"/>
				<html:hidden property="orderBy" value=""/> 
				<html:hidden property="reltnNbr"/>
				
				

				<html:text property="backURL" value="CustomerPrvt.CustomerPrvtList.List.Show" style="display:none"></html:text> 
								
	
				<table class="ODS_internalWidth" border="0">
					<thead><tr height="40"><th class="subtitle" scope="colgroup" colspan="6">Consulta de Cliente Private</th></tr></thead>
					<tbody>
						<tr class="ODS_line11" height="25"><td colspan="6">Dados Cadastrais:</td></tr>
						<tr><td colspan="6"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1" width="100%"></td></tr>

						<tr class="ODS_Detail_line1">
							<td colspan="6">										
								<table>
									<tr>
										<TD width="16%">Nro. Cliente</TD>
										<TD width="20%"><html:text property="custNbrSrc" styleClass="ODS_Text_Field_Size_10" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
										<TD width="12%" align="right">Nome&nbsp;&nbsp;</TD>
										<TD width="55%"><html:text property="custFullNameTextSrc" styleClass="ODS_Text_Field_Size_60" maxlength="60"></html:text></TD>
									</tr>
								</table>																		
							</td>
						</tr>
						<tr class="ODS_Detail_line2">
							<TD colspan = "6">
								<table>
									<tr>
										<td width="23%">CPF / CNPJ</td>
										<td><html:text property="custCpfCnpjNbrSrc" styleClass="ODS_Text_Field_Size_15" maxlength="18" onblur="javascript:maskCpfCnpj();"></html:text></td>
										<td align="right" width="25%">Conta</TD>
										<TD width="17%"><html:text property="curAcctNbrSrc"
											styleClass="ODS_Text_Field_Size_10" maxlength="15"
											onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text></TD>
										<TD align="right" width="70%">Nro. Relacionamento</TD>
										<TD width="10%"><html:text property="reltnNbrSrc"
											styleClass="ODS_Text_Field_Size_10" maxlength="11"
											onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
									</tr>
								</table>	
							</TD>
						</tr>
				
						<tr class="ODS_line11" height="25"><td colspan="6">Dados Complementares:</td></tr>
						<tr><td colspan="6"><img src='<%= request.getContextPath()%>/Common/image/20grey1.gif' height="1" width="100%"></td></tr>

						<tr class="ODS_Detail_line2">
							<td width="16%">Nro. Banker</td>
							<td width="20%"><html:text styleClass="ODS_Text_Field_Size_5" property="offcrNbrSrc" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></td>
							<td align="right" width="20%">Nome do Banker&nbsp;&nbsp;</TD>
							<TD align="right" colspan = "3">
								<html:text styleClass="ODS_Text_Field_Size_30" property="offcrTextSrc" disabled="true" size="60"></html:text>
								<html:button property="getOFFCR_NBR" value="Buscar"	onclick="submitAction('PreparedSearch.Officer.OfficerList');"></html:button>
							</TD>
						</tr>

						<tr class="ODS_Detail_Line1">
							<TD width="16%">Número EM</TD>
							<TD width="20%"><html:text styleClass="ODS_Text_Field_Size_30" property="emNbrSrc" maxlength="30"></html:text></TD>
							<TD align="right" width="20%">Status do Cliente&nbsp;&nbsp;</TD>
							<TD colspan="3">
								<html:select property="custPrvtStatCodeSrc"	styleClass="ODS_Select_Field_Size_10">
									<html:option value=""></html:option>
									<html:options
										property="custPrvtStatCodeDomain.columnValuesByName(CUST_PRVT_STAT_CODE)"
										labelProperty="custPrvtStatCodeDomain.columnValuesByName(CUST_PRVT_STAT_TEXT)" />
								</html:select>
							</TD>
						</tr>

						<tr class="ODS_Detail_line2">
							<td width="16%">Private Number</td>
							<td width="20%"><html:text styleClass="ODS_Text_Field_Size_10" property="prvtCustNbrSrc" maxlength="8" onkeyup="MaskFieldPress('CHAR','NNNNNNNN','left',null)"></html:text></td>
							<td align="right" width="20%">Key Private Number&nbsp;&nbsp;</TD>
							<TD colspan = "3">
								<html:text styleClass="ODS_Text_Field_Size_10" property="prvtKeyNbrSrc" maxlength="8" onkeyup="MaskFieldPress('CHAR','NNNNNNNN','left',null)"></html:text>
							</TD>
						</tr>

						<tr class="ODS_Detail_line1">
							<td width="16%">Potencial Receita</td>
							<td width="16%"><html:select property="wealthPotnlCodeSrc" styleClass="ODS_Select_Field_Size_40">
										<html:option value=""></html:option>
										<html:options	property="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_CODE)"
											labelProperty="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_TEXT)" />
									       </html:select></td>
							<td align="right" width="8%"> Tipo de Cliente&nbsp;</td>
							<td width="8%"><html:select property="prvtCustTypeCodeSrc" styleClass="ODS_Select_Field_Size_20">
										<html:option value=""></html:option>
										<html:options
											property="prvtCustTypeCodeDomain.columnValuesByName(PRVT_CUST_TYPE_CODE)"
											labelProperty="prvtCustTypeCodeDomain.columnValuesByName(PRVT_CUST_TYPE_TEXT)" />
									</html:select></td>
							</tr>
							
							<tr class="ODS_Detail_line2">
								<td width="16%">Classif. Compliance</td>
								<td colspan="5">
									<html:select property="classCmplcCodeSrc" styleClass="ODS_Select_Field_Size_40">
										<html:option value=""></html:option>
										<html:options
											property="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_CODE)"
											labelProperty="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_TEXT)" />
									</html:select>
								</td>
							</tr>
							
						</tbody>
					</TABLE>			
		
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<TD width="80%">&nbsp;</TD>									
								<td><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></td>
								<td><html:button property="detailBtn" disabled="true" value="Detalhar" onclick="submitAction('detail');"></html:button></td>
								<!--<td><html:button property="insertBtn" disabled="true" value="Alterar / Inserir" onclick="submitAction('insertCmpl');"></html:button></td>-->
								<td><html:button property="deleteBtn" disabled="true" value="Excluir Dados Cmpl." onclick="submitAction('deleteCmpl');"></html:button></td>
								<td><html:button property="clearBtn" value="Limpar"	onclick="clearResultSetInServer();submitAction('clearPage');"></html:button></td>
							</TR>
						</TBODY>
					</TABLE>						
					
					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<TD width="100%">&nbsp;</TD>
								<td><html:button property="custAddressBtn"	disabled="true" value="Endereços" onclick="submitAction('custAddress');"></html:button></td>
								<td><html:button property="contactBtn" disabled="true" value="Gerenciar Contatos" onclick="submitAction('contact');"></html:button></td>
								<ods:AccessGrantedTag m_SGFunction="/BkrPortfMgmt.BkrPortfMgmtDetail.Insert.Show"><td><html:button property="bkrPortfMgmtBtn" disabled="true" value="Associar Corretora" onclick="submitAction('bkrPortfMgmt');"></html:button></td></ods:AccessGrantedTag>
							</TR>
						</TBODY>
					</TABLE>
					
					<table>
						<thead><tr><th class="subtitle" scope="colgroup" colspan="6">Resultado da Consulta</th></tr></thead>
						<logic:equal name="CustomerPrvtListForm" property="flagAllData" value="N" >
								<tr class="ODS_Detail_Line2">
									<td style="color:red">Devido ao volume esta
									pesquisa foi limitada a 2.000 linhas.<br>
								    Favor, utilizar os filtros a
									fim de especificar a sua consulta. </td>
								</tr>
						</logic:equal>
						
		             	<tr>
							<td>					
								<DIV class="ODS_DivGridVerticalScroll">
									<table id="gridTable" width="1200px" border="0">
										<tbody>
										
										<%									    
									    ArrayList listTest = (ArrayList)request.getSession().getAttribute("lista");
									    String sort = (String)request.getParameter("ordenar");
									    
									    if(listTest == null || sort == null ){
									      CustomerPrvtListForm frm = (CustomerPrvtListForm)request.getSession().getAttribute("CustomerPrvtListForm");
		                                  request.getSession().setAttribute("lista",frm.getCustList());
		                                }
									  %>
										
									  <logic:present name="lista">
								      <td>
									    <div>
										<display:table name="sessionScope.lista" uid="vo" id="vCust" pagesize="10" export="true" class="listaPaginada"  
 											    requestURI="/FrontController/CustomerPrvt.CustomerPrvtList.List.Execute?ordenar=1&" style="width:100%" sort="list"  >											
 											<bean:define name="vCust" property="aprovacao" id="approveFlg" type="java.lang.String"></bean:define>    
 											<bean:define name="vCust" property="custNbr" id="selectedCustNbr" type="java.lang.Integer"></bean:define>
											<bean:define name="vCust" property="cmplDataButtonControl" id="cmplDataButtonControl" type="java.lang.Integer"></bean:define>    
											<bean:define name="vCust" property="reltnNbr" id="selectedReltnNbr" type="java.math.BigInteger"></bean:define>
											
											
											<display:column title="" media="html"  style="text-align:left;width:2%" >												
												<input type="radio" class="radio" name="selection" onclick="javascript:reltnNbr.value='<%=selectedReltnNbr%>';selectedCustNbr.value='<%=selectedCustNbr%>';selectedCustNbrList.value='<%=selectedCustNbr%>';cmplDataButtonControl.value='<%=cmplDataButtonControl%>';disableButtons(false); hasCmplData('<%=cmplDataButtonControl%>'); ">												
											</display:column>
											
											<display:column title="" media="html"  style="text-align:center;width:2%" >
											<logic:equal name="approveFlg" value="0">																								
												<a href="javascript:setKeys('<%=selectedCustNbr%>','<%=cmplDataButtonControl%>');disableButtons(false);"/>
												<img src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0"></a>		
											</logic:equal>	
											</display:column>											
											
											<display:column title="Nome" property="custFullNameText"  sortable="true" style="text-align:left;width:20%" >																								
											</display:column>
											
											<display:column title="Nro do Cliente" property="custNbr" sortable="true" style="text-align:right;width:05%">																				
											</display:column>
											
											<display:column title="CPF / CNPJ" property="custCpfCnpjNbr" sortable="true" style="text-align:right;width:10%">																																				
											</display:column>
											
											<display:column title="Nro Relacionamento" sortable="true" style="text-align:right;width:15%">																																				
											  <logic:notEqual name="vCust" property="reltnNbr" value="0">
											    <bean:write name="vCust" property="reltnNbr" />	  
											  </logic:notEqual>		
											</display:column>
											
											<display:column title="Conta" property="curAcctNbr" sortable="true" style="text-align:right;width:10%">																																				
											</display:column>
											
											<display:column title="Nome do Banker" property="offcrNameText" sortable="true" style="text-align:left;width:20%">																																				
											</display:column>
											
											<display:column title="Private Number" property="prvtCustNbr" sortable="true" style="text-align:right;width:10%">																																				
											</display:column>
											
											<display:column title="Key Private Number" property="prvtPrincCustNbr"  sortable="true" style="text-align:right;width:10%">																																				
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
					</TABLE>

					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%"></td>
								<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>	
					</TABLE>
				</html:form> 
				<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</td>
		</tr>
	</table>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>