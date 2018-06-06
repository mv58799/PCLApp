<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
             pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK href="<%=request.getContextPath()%>/Common/css/citi.css"
	rel="stylesheet" type="text/css">
	
<script language="javascript">
        function extraActions(action) { 
          document.forms[0].action = "MrDocPrvt.MrDocPrvtList.List.Show";
		  document.forms[0].backURL.disabled = false;           
        }
        
</script>	

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="IpDocPrvt.IpDocTransFinancDetail" />	
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Impressão de	Transação Financeira - IP</TITLE>
</HEAD>

<body>

<html:form action="/IpDocPrvt.IpDocTransFinancDetail.Consult.Show.do">
    <jsp:include page="/View/Util/InitialPrintPage.jsp" flush="true">	
	</jsp:include>
	
	
	
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<html:text property="backURL" value="IpDocPrvt.IpDocTransFinan.Show"
					style="display:none"></html:text>
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Impressão de Transação Financeira - IP</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="3">Dados do Cliente</th>
							</tr>
							<tr class="ODS_Detail_line2" scope="colgroup" colspan="3">
								<td>Nome do Cliente: <bean:write name="IpDocTransFinancDetailForm" property="custFullNameText"></bean:write></td>
								
							</tr>
							<tr class="ODS_Detail_line1" scope="colgroup" colspan="3">
								<TD>Conta Corrente: <bean:write name="IpDocTransFinancDetailForm" property="custCurAcctNbr"></bean:write> </td>
								
							</tr>
							<tr class="ODS_Detail_line1" scope="colgroup" colspan="3">
								<TD>Conta CCI: <bean:write name="IpDocTransFinancDetailForm" property="invstCurAcctNbr"></bean:write> 
								
								</td>
								
							</tr>							
							
							<logic:equal  name="IpDocTransFinancDetailForm" property="trfAcctType" value="1" >
							  <tr class="ODS_Detail_line1">
								<td>Débito em Conta Corrente</td>
							  </tr>
							</logic:equal>
							
							<logic:equal  name="IpDocTransFinancDetailForm" property="trfAcctType" value="2" >
							  <tr class="ODS_Detail_line1">
								<td>Débito em Conta CCI</td>
							  </tr>
							</logic:equal>
							
							<tr>
								<th class="subtitle" scope="colgroup" colspan="3">Dados de Transferência</th>
							<tr class="ODS_Detail_line1">
								<td>Valor: R$ <bean:write name="IpDocTransFinancDetailForm" property="trfAmtNbr" ></bean:write>
								
								 </td>
								
							</tr>
							<tr class="ODS_Detail_line2">
								<td>Tipo Operação: <bean:write name="IpDocTransFinancDetailForm" property="tpOperacao"></bean:write></td>								
							</tr>
							<tr class="ODS_Detail_line1">
								<td>Beneficiário: <bean:write name="IpDocTransFinancDetailForm" property="beneNameText"></bean:write></td>								
							</tr>
							<tr class="ODS_Detail_line2">
								<TD>Banco: <bean:write name="IpDocTransFinancDetailForm" property="bankCode"></bean:write></td>
								
							</tr>
							<tr class="ODS_Detail_line1">
								<td>Agência: <bean:write name="IpDocTransFinancDetailForm" property="brchCode"></bean:write></td>								
							</tr>
							<tr class="ODS_Detail_line2">
								<td>Conta Destino:<bean:write name="IpDocTransFinancDetailForm" property="beneCurAcctNbr"></bean:write></td>								
							</tr>
							
							<logic:equal  name="IpDocTransFinancDetailForm" property="beneAcctTypeCode" value="P" >
							  <tr class="ODS_Detail_line2">
								<td>Crédito em Conta POUPANÇA</td>
							  </tr>							
							</logic:equal>
							
							
							<tr class="ODS_Detail_line2">
								<td>&nbsp;</td>								
							</tr>
							
							<tr class="ODS_Detail_line2">
								<td><html:button property="backBtn" value="Voltar"
									onclick="submitAction('MrDocPrvt.MrDocPrvtList.List');"></html:button></td>								
							</tr>				
						</tbody>
					</table>
					</td>


					<jsp:include page="/View/Util/Footer.jsp" flush="true" />
			</table>
<script language="javascript">
        window.print();
        
</script>			
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />




</html:html>

