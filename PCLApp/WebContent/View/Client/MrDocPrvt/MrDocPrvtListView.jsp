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
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">
<script language=javascript>

	function extraActions(action){

		if (action =='CustomerPrvt.CustomerPrvtList')
		{
			document.forms[0].action = "CustomerPrvt.CustomerPrvtList.List.Show";
			document.forms[0].backURL.disabled = false;
		}
		else if (action =='MrDocPrvt.MrDocPrvtInsert')
		{
			document.forms[0].action = "MrDocPrvt.MrDocPrvtDetail.Insert.Show";			
			document.forms[0].backURL.disabled = false;
		}
		else if (action =='MrDocPrvt.MrDocPrvtConsulta')
		{
			document.forms[0].action = "MrDocPrvt.MrDocPrvtDetail.Consult.Show";			
			document.forms[0].backURL.disabled = false;
		}
		else if (action =='MrDocPrvt.MrDocPrvtDelete')
		{
			document.forms[0].action = "MrDocPrvt.MrDocPrvtDetail.Delete.Show";			
			document.forms[0].backURL.disabled = false;
		}
		else if (action =='IpDocPrvt.IpDocPrvtInsert')
		{
			document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Insert.Show";			
			document.forms[0].custCurAcctNbrSrc.value = document.forms[0].curAcctNbrSrc.value;
			document.forms[0].backURL.disabled = false;
		}
		else if(action == 'IpDocPrvt.IpDocPrvtDelete'){
		    document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Delete.Show";
		    document.forms[0].backURL.disabled = false;
		}
		else if(action == 'IpDocPrvt.IpDocPrvtDetail'){
		    document.forms[0].action = "IpDocPrvt.IpDocTransFinancDetail.Insert.Show";
		    document.forms[0].backURL.disabled = false;
		} 
		else if ( action == 'MrDocPrvt.MrDocPrvtUpdate' ) {
		    document.forms[0].action = "MrDocPrvt.MrDocPrvtDetail.Update.Show";
		    document.forms[0].backURL.disabled = false;		
		} 
		else if ( action == 'IpDocPrvt.IpDocPrvtUpdate' ) {
		    document.forms[0].action = "IpDocPrvt.IpDocPrvtDetail.Update.Show";
		    document.forms[0].backURL.disabled = false;
		}

	};
	
	function fillFieds(selectedModuleCode,selectedModuleSubCode, selectedTipo, selectedProdAcctCode,selectedProdUnderAcctCode){
	 	if(selectedTipo == 'MR'){
	    document.forms[0].selectedProdAcctCode.value = selectedProdAcctCode;
	    document.forms[0].selectedProdUnderAcctCode.value = selectedProdUnderAcctCode;
	    document.forms[0].selectedMrDocPrvt.value = selectedModuleCode;
	  }
	  else if(selectedTipo == 'IP'){
	    document.forms[0].ipDocCode.value = selectedModuleCode;
	    document.forms[0].docTransferCode.value = selectedModuleSubCode;
	    document.forms[0].selectedMrDocPrvt.value = '';
	  }
	  disableButtons(false);
	}
	
	function Detail(){
	  if(document.forms[0].selectedMrDocPrvt.value != ''){
	    submitAction('MrDocPrvt.MrDocPrvtConsulta');
	  }
	  else {
	    submitAction('IpDocPrvt.IpDocPrvtDetail');
	  }
	};
	
	function Delete(){
	   if(document.forms[0].selectedMrDocPrvt.value != ''){
	    submitAction('MrDocPrvt.MrDocPrvtDelete');
	  }
	  else {
	    submitAction('IpDocPrvt.IpDocPrvtDelete');
	  }
	};
	
	function Alter(){
	   if(document.forms[0].selectedMrDocPrvt.value != ''){
	    submitAction('MrDocPrvt.MrDocPrvtUpdate');
	  }
	  else {
	    submitAction('IpDocPrvt.IpDocPrvtUpdate');
	  }
	};

</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="MrDocPrvt.MrDocPrvtList" />
	<jsp:param name="gridId" value="gridTable" />
	<jsp:param name="headerId" value="gridHeader" />
	<jsp:param name="controlNames" value="'detailBtn', 'updateBtn', 'deleteBtn'" />
	    <jsp:param name="searchInputFields" value="'curAcctNbrSrc', 'custNbrSrc'" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Consulta de MR/IP</TITLE>
</HEAD>

<body>
<html:form action="/MrDocPrvt.MrDocPrvtList.List.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<jsp:include page="/View/Util/SubmitSearch.jsp" flush="true"></jsp:include>
	<html:hidden property="selectedMrDocPrvt"/>
	<html:hidden property="selectedProdAcctCode" />
	<html:hidden property="selectedProdUnderAcctCode" />
	<html:hidden property="reltnNbr"/>

	<table class="ODS_mainTable" cellspacing="0" width="100%">
		<tr>
			<td>&nbsp;</td>
			<html:text property="backURL" value="MrDocPrvt.MrDocPrvtList.List.Show" style="display:none"></html:text>
			<html:text property="custCurAcctNbrSrc"	style="display:none"></html:text>
			<html:text property="ipDocCode"	style="display:none"></html:text>
			<html:text property="docTransferCode"	style="display:none"></html:text>
			
				<td>
					<table class="ODS_internalWidth" border="0">
						<thead><tr><th class="subtitle" scope="colgroup" colspan="5">Consulta de MR/IP</th></tr></thead>
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="6%">Nome do Cliente</TD>	
								<TD colspan="4">
									<html:text property="custFullNameTextSrc" styleClass="ODS_Text_Field_Size_60"></html:text>&nbsp;
									<html:button property="getBtn" value="Buscar" onclick="submitAction('PreparedSearch.CustomerPrvt.CustomerPrvtList');"></html:button>
								</TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="6%">Nome do Co-Titular</TD>	
								<TD colspan="4">
									<html:text property="custFullName2TextSrc" styleClass="ODS_Text_Field_Size_60"></html:text>&nbsp;
								</TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="6%">Nome do Co-Titular 2</TD>	
								<TD colspan="4">
									<html:text property="custFullName3TextSrc" styleClass="ODS_Text_Field_Size_60"></html:text>&nbsp;
								</TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="6%">Nome do Co-Titular 3</TD>	
								<TD colspan="4">
									<html:text property="custFullName4TextSrc" styleClass="ODS_Text_Field_Size_60"></html:text>&nbsp;
								</TD>
								
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="6%">CCI</TD>
								<TD width="25%" colspan="4">
									<html:text property="invstCurAcctNbrSrc"	styleClass="ODS_Text_Field_Size_16" maxlength="11" ></html:text>&nbsp;&nbsp;
								</TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<TD width="6%">Conta Corrente</TD>
								<TD width="25%" colspan="4">
									<html:text property="curAcctNbrSrc"	styleClass="ODS_Text_Field_Size_20" maxlength="15" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text>&nbsp;&nbsp;
								</TD>
							</tr>
						</tbody>
					</TABLE>

					<table class="ODS_internalWidth" border="0">
						<tbody>
							<TR>
								<TD width="70%" align="right"></TD>
								<TD><html:button property="listBtn" value="Consultar"	onclick="submitAction('list');"></html:button></TD>
								<TD><html:button property="insertMRBtn" value="Inserir MR" onclick="submitAction('MrDocPrvt.MrDocPrvtInsert');"></html:button></TD>
								<TD><html:button property="insertIPBtn" value="Inserir IP" onclick="submitAction('IpDocPrvt.IpDocPrvtInsert');"></html:button></TD>
								<TD><html:button property="detailBtn" value="Detalhar" onclick="Detail();" disabled="true"></html:button></TD>
								<TD><html:button property="updateBtn" value="Alterar" onclick="Alter();" disabled="true"></html:button></TD>																						
								<TD><html:button property="deleteBtn" value="Excluir" onclick="Delete();" disabled="true"></html:button></TD>								
								<TD><html:button property="clearBtn" value="Limpar" onclick="clearAllPage();"></html:button></TD>
							</TR>
						</tbody>
					</TABLE>
				
					
						<table class="ODS_internalWidth" border="0">
							<thead><tr><th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th></tr></thead>
							  <tr>
								<td>
								   <DIV class="ODS_DivGridVerticalScroll" >
								     <table id="gridTable" width="200%"  border="0">
										<tbody>
											<tr id="gridHeader" class="fixed">
												<TH class="ODS_header" width="3%">&nbsp;</TH>
												<TH class="ODS_header" width="4%">Tipo</TH>
												<TH class="ODS_header" width="6%">Banco </TH>
												<TH class="ODS_header" width="6%">Agência</TH>
												<TH class="ODS_header" width="9%">Conta Corrente</TH>
												<TH class="ODS_header" width="14%">Beneficiário</TH>
												<TH class="ODS_header" width="9%">CNPJ/CPF</TH>
												<TH class="ODS_header" width="12%">Data de Alteração</TH>
												<TH class="ODS_header" width="11%">Usuário da Alteração </TH>
												<TH class="ODS_header" width="12%">Data de Autorização</TH>
												<TH class="ODS_header" width="11%">Usuário de Autorização</TH>
											</tr>
											<ods:DataSetRows name="MrDocPrvtListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
												<logic:equal name="step" value="0">
													<tr class="ODS_line1">
												</logic:equal>
												<logic:equal name="step" value="1">
													<tr class="ODS_line2">
												</logic:equal>
												<bean:define name="resultRow" property="stringByName(MODULE_CODE)" id="selectedModuleCode" type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(MODULE_SUB_CODE)" id="selectedModuleSubCode" type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(TIPO)" id="selectedTipo" type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(PROD_ACCT_CODE)" id="selectedProdAcctCode"	type="java.lang.String"></bean:define>
												<bean:define name="resultRow" property="stringByName(PROD_UNDER_ACCT_CODE)" id="selectedProdUnderAcctCode" type="java.lang.String"></bean:define>
												
												<TD width="3%"><input type="radio" class="radio" name="selection" onclick="javascript:fillFieds('<%= selectedModuleCode %>','<%= selectedModuleSubCode %>','<%= selectedTipo %>', '<%= selectedProdAcctCode %>','<%= selectedProdUnderAcctCode %>');"/></td>												
												<TD align="center"><bean:write name="resultRow" property="stringByName(TIPO)" /></td>
												<TD align="center"><bean:write name="resultRow" property="stringByName(BANK_NBR)"/></td>
												<TD align="center"><bean:write name="resultRow" property="stringByName(AGN_NBR)"/></td>
												<TD align="center"><bean:write name="resultRow" property="stringByName(CUR_ACCT_NBR_BENE)" /></td>
												<TD align="left"><bean:write name="resultRow" property="stringByName(BENE_NAME_TEXT)" /></td>
												<TD align="center"><bean:write name="resultRow" property="stringByName(BENE_CPF_CNPJ_NBR)"/></td>
												<TD align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD align="center"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)" /></td>
												<TD align="center"><bean:write name="resultRow" property="dateByName(LAST_AUTH_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>' /></td>
												<TD align="center"><bean:write name="resultRow" property="stringByName(LAST_AUTH_USER_ID)" /></td>
											</ods:DataSetRows>
										 </tbody>
									 </table>
								</td>
							</tr>
						</TABLE>
					</DIV>

					<TABLE class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<td width="100%"></td>
								<TD><html:button property="backBtn" value="Voltar"	onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					</html:form> <jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
				</td>
			</tr>
		</table>
	</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
