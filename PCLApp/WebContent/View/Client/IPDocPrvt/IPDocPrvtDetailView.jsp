
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page  import="com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtDetailForm" %>
<%@ page  import="com.citibank.ods.common.util.ODSConstraintDecoder" %>
<html:html>

<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/Common/css/citi.css">

<script language="javascript">
	function extraActions(action){

	}																	
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="IpDocPrvt.IpDocPrvtDetail" />

</jsp:include>
<TITLE>Detalhe de Instrução Permanente</TITLE>
</HEAD>
<body>
<html:form action="/IpDocPrvt.IpDocPrvtDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<html:text property="backURL" value="IpDocPrvt.IpDocPrvtList.Consult.Show"style="display:none"></html:text>

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Instrução Permanente</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="30%">Número do Cliente</TD>
								<TD colspan="2">Nome do Cliente</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="30%"><html:text property="custNbrSrc" disabled="true" styleClass="ODS_Text_Field_Size_15"></html:text></td>
								<TD colspan="2" ><html:text property="custFullNameText" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
							</tr>
							<tr class="ODS_Detail_line1">
								<td width="30%">Código </td>
								<TD width="40%">Ind. Conta CCI</TD>
								<TD width="30%">&nbsp;</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="30%"><html:text property="ipDocCode" disabled="true" 
									styleClass="ODS_Text_Field_Size_10"></html:text></td>
								<TD width="40%">
									<html:select property="ipInvstCurAcctInd" styleClass="ODS_Select_Field_Size_5" disabled="true">
										<html:option value=""></html:option>
										<html:options property="ipInvstCurAcctIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="ipInvstCurAcctIndDomain.columnValuesByName(INDICATOR_TEXT)" />
									</html:select>
								</TD>
								<td width="30%">&nbsp;</td>
							</tr>
						</tbody>
					</table>
		
					<table class="ODS_internalWidth" border="0">
					<thead>
						<tr>
							<th class="subtitle" scope="colgroup" colspan="3">Dados de Transferência</th>
						</tr>
					</thead>
			        <tbody>
		    	        <tr id="gridHeader" class="fixed">
		            	    <TH class="ODS_header" width="7%">Banco</TH>
		                	<TH class="ODS_header" width="8%">Agência</TH>
			                <TH class="ODS_header" width="17%">Conta Corrente</TH>
			                <TH class="ODS_header" width="16%">Tipo de Transação</TH>
			                <TH class="ODS_header" width="25%">Mesma Titularidade</TH>
							<%
	       						int auxIndex = 0;
	                         %>
    	
		                    <logic:iterate name="IpDocPrvtDetailForm"  property="domainsAgnBankName" indexId="index" id="baseArray">

        	                <% IpDocPrvtDetailForm ipDocPrvtDetailForm = (IpDocPrvtDetailForm) session.getAttribute("IpDocPrvtDetailForm");
								String[] arrayDocTransferCode = ipDocPrvtDetailForm.getDomainsDocTransferCode();
								String indexDomainsDocTransferCode = arrayDocTransferCode[auxIndex++];
							%>

            	             <bean:define name="IpDocPrvtDetailForm" property='<%="domainsAgnBankName[" + index + "]"%>'id="domainsAgnBankName" type="java.lang.String"/>
                    	     <bean:define name="IpDocPrvtDetailForm" property='<%="domainsBrchName[" + index + "]"%>' id="domainsBrchName" type="java.lang.String"/>
	                         <bean:define name="IpDocPrvtDetailForm" property='<%="domainsCurAcctCode[" + index + "]"%>'id="domainsCurAcctCode" type="java.lang.String"/>
                             <bean:define name="IpDocPrvtDetailForm" property='<%="domainsTxnTypeCode[" + index + "]"%>'id="domainsTxnTypeCode" type="java.lang.String"/>
                             <bean:define name="IpDocPrvtDetailForm" property='<%="domainsOwnDestAcctInd[" + index + "]"%>'id="domainsOwnDestAcctInd" type="java.lang.String"/>
	
							<ods:CountStep counterName="index" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
		                        <logic:equal name="step" value="0">
        			                <tr class="ODS_line1">
                    	        </logic:equal>
                           		<logic:equal name="step" value="1">
                        			<tr class="ODS_line2">
	                            </logic:equal>
                            </ods:CountStep>
                            <td width="30%" class="centralized"><%=domainsAgnBankName%></td>
                            <td width="20%" class="centralized"><%=domainsBrchName%></td>
                            <td width="20%" class="centralized"><%=domainsCurAcctCode%></td>
            		        <td width="15%" class="centralized"><%=com.citibank.ods.common.util.ODSConstraintDecoder.decodeTransaction(domainsTxnTypeCode)%></td>
            		        <td width="15%" class="centralized"><%=com.citibank.ods.common.util.ODSConstraintDecoder.decodeIndicator(domainsOwnDestAcctInd)%></td>
						</logic:iterate>
                     </TR>
                </TBODY>
			</table>
		</td>
	</tr>

	<tr>
		<td>
			<table class="ODS_internalWidth" border="0">
				
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Dados de Callback</th>
					</tr>
				</thead>

		        <tbody>
		            <tr id="gridHeader" class="fixed">
		                <TH class="ODS_header" width="20%">Código do Contato</TH>
		                <TH class="ODS_header" width="80%">Nome do Contato</TH>
						<%
							int auxIndex2 = 0;
					    %>
						
						<logic:iterate name="IpDocPrvtDetailForm" property="domainsFullNameText" indexId="index2" id="baseArray2">

							<%IpDocPrvtDetailForm ipDocPrvtForm = ( IpDocPrvtDetailForm ) session.getAttribute( "IpDocPrvtDetailForm" );
								String[] arrayCtcNbr = ipDocPrvtForm.getDomainsCtcNbr();
							    String indexCtcNbrArray = arrayCtcNbr[ auxIndex2++ ];
						    %>
							<bean:define name="IpDocPrvtDetailForm" property='<%="domainsFullNameText[" + index2 + "]"%>'
								id="domainsFullNameText" type="java.lang.String" />
								<ods:CountStep counterName="index2" counterStartIndex="0" sequenceRestartStep="2" stepIndexName="step">
									<logic:equal name="step" value="0">
										<tr class="ODS_line1">
									</logic:equal>
									<logic:equal name="step" value="1">
										<tr class="ODS_line2">
									</logic:equal>
								</ods:CountStep>
								<td width="20%" class="centralized"><%=indexCtcNbrArray%></td>
								<td width="80%" class="centralized"><%=domainsFullNameText%></td>
							</logic:iterate>
		            </tbody>
				</table>
			</td>
		</tr>

		<tr>
			<td>
				<TABLE class="ODS_internalWidth" border="0">
					<TBODY>
						<TR>
							<td width="100%"></td>
							<TD><html:button property="backBtn" value="Voltar"	onclick="submitAction('back');"></html:button></TD>
						</TR>
					</TBODY>
				</TABLE>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</TABLE>
		</html:form>
	</td>
	</tr>
	</table>
</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
