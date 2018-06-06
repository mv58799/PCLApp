
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page
        import="com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtDetailForm" %>
<html:html>

<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Common/css/citi.css">

<script language="javascript">
	function extraActions(action){

	}																	
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="IpDocPrvt.IpDocPrvtDetail" />

</jsp:include>
<TITLE>Exclusão de Instrução Permanente</TITLE>
</HEAD>
<body>
<html:form action="/IpDocPrvt.IpDocPrvtDetail.Delete.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Documentos" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
	<html:text property="backURL"
		value="IpDocPrvt.IpDocPrvtList.Consult.Show" style="display:none"></html:text>

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Exclusão de
						Instrução Permanente</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0"
						class="ODS_internalWidth">
						<tbody>
							<TR class="ODS_Detail_Line2">
								<TD width="3%">Conta Corrente</TD>
								<td width="35%">Nome do Cliente</td>
								<td width="35%">CCI</td>
							</TR>
							<TR class="ODS_Detail_Line2">
								<TD width="3%"><html:text property="custCurAcctNbrSrc"
									styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text>
								</TD>

								<TD width="35%"><html:text property="custFullNameText"
									styleClass="ODS_Text_Field_Size_60" disabled="true"></html:text>
								</TD>
								<td colspan="2"><html:text property="invstCurAcctNbrSrc"
									styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text>
								</td>
							</TR>

							<TR class="ODS_Detail_Line2">
								<TD width="3%">Código</TD>
								<td width="35%">&nbsp;</td>
								<td width="35%">&nbsp;</td>
							</TR>
							<TR class="ODS_Detail_Line2">
								<TD width="3%"><html:text property="ipDocCode" disabled="true"
									styleClass="ODS_Text_Field_Size_10"></html:text></TD>
								<TD width="35%">&nbsp;
								</TD>
								<td colspan="2">&nbsp;
								</td>
							</TR>

						</tbody>
					</TABLE>

					<table class="ODS_internalWidth" border="0">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="3">Dados de
								Transferência</th>
							</tr>
						</thead>
						<tbody>
							<tr id="gridHeader" class="fixed">
								<TH class="ODS_header" width="5%">Banco</TH>
								<TH class="ODS_header" width="5%">Agência</TH>
								<TH class="ODS_header" width="15%">Conta Corrente</TH>
								<TH class="ODS_header" width="15%">CPF/CNPJ Benef.</TH>
								<TH class="ODS_header" width="20%">Nome Benef.</TH>
								<%int auxIndex = 0;%>

								<logic:iterate name="IpDocPrvtDetailForm"
									property="domainsAgnBankName" indexId="index" id="baseArray">

									<%IpDocPrvtDetailForm ipDocPrvtDetailForm =
										(IpDocPrvtDetailForm) session.getAttribute("IpDocPrvtDetailForm");
										String[] arrayDocTransferCode = ipDocPrvtDetailForm.getDomainsDocTransferCode();
									    String indexDomainsDocTransferCode = arrayDocTransferCode[auxIndex++];%>
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsAgnBankName[" + index + "]"%>'
										id="domainsAgnBankName" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBrchName[" + index + "]"%>'
										id="domainsBrchName" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsCurAcctCode[" + index + "]"%>'
										id="domainsCurAcctCode" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsTxnTypeCode[" + index + "]"%>'
										id="domainsTxnTypeCode" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsOwnDestAcctInd[" + index + "]"%>'
										id="domainsOwnDestAcctInd" type="java.lang.String" />
										
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsAgnBankCode[" + index + "]"%>'
										id="domainsAgnBankCode" type="java.lang.String" />
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBrchCode[" + index + "]"%>'
										id="domainsBrchCode" type="java.lang.String" />	
										
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBeneCpfCnpjNbr[" + index + "]"%>'
										id="domainsBeneCpfCnpjNbr" type="java.lang.String" />

									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsBeneNameText[" + index + "]"%>'
										id="domainsBeneNameText" type="java.lang.String" />
										

									<ods:CountStep counterName="index" counterStartIndex="0"
										sequenceRestartStep="2" stepIndexName="step">
										<logic:equal name="step" value="0">
											<tr class="ODS_line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_line2">
										</logic:equal>
									</ods:CountStep>

									<td width="5%" align="center"><%=domainsAgnBankCode%></td>
									<td width="5%" align="center"><%=domainsBrchCode%></td>
									<td width="15%" align="center"><%=domainsCurAcctCode%></td>
									<td width="15%" align="center"><%=domainsBeneCpfCnpjNbr%></td>
									<td width="20%" align="left"><%=domainsBeneNameText%></td>
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
								<th class="subtitle" scope="colgroup" colspan="5">Dados de
								Callback</th>
							</tr>
						</thead>

						<tbody>
							<tr id="gridHeader" class="fixed">
								<TH class="ODS_header" width="2%">DDD</TH>
								<TH class="ODS_header" width="10%">Telefone</TH>
								<TH class="ODS_header" width="2%">Ramal</TH>
								<TH class="ODS_header" width="25%">Nome do Contato Principal</TH>
								<TH class="ODS_header" width="25%">Nome do Segundo Contato</TH>
								<TH class="ODS_header" width="25%">Nome do Terceiro Contato</TH>
								<%int auxIndex2 = 0;%>

								<logic:iterate name="IpDocPrvtDetailForm"
									property="domainsFullNameText" indexId="index2" id="baseArray2">

									<%IpDocPrvtDetailForm ipDocPrvtForm =
										(IpDocPrvtDetailForm) session.getAttribute("IpDocPrvtDetailForm");
										String[] arrayCtcNbr = ipDocPrvtForm.getDomainsCtcNbr();
                                        String[] arrayPhone = ipDocPrvtForm.getDomainsPhone();
								        String[] arrayDddPhone = ipDocPrvtForm.getDomainsDDDPhone();
					                    String[] arrayRamalPhone = ipDocPrvtForm.getDomainsRamalPhone();
					              		String[] arrayFullNameText_2 = ipDocPrvtForm.getDomainsFullNameText_2(); 
					              		String[] arrayFullNameText_3 = ipDocPrvtForm.getDomainsFullNameText_3(); 								  
										String indexPhone = arrayPhone[auxIndex2];
								  		String dddPhone = arrayDddPhone[auxIndex2];
								  		String ramalPhone = arrayRamalPhone[auxIndex2];
								  		String fullNameText_2 = arrayFullNameText_2[auxIndex2];
								  		String fullNameText_3 = arrayFullNameText_3[auxIndex2];
								  		String indexCtcNbrArray = arrayCtcNbr[auxIndex2];
										auxIndex2++;
										
										;%>
									<bean:define name="IpDocPrvtDetailForm"
										property='<%="domainsFullNameText[" + index2 + "]"%>'
										id="domainsFullNameText" type="java.lang.String" />
									<ods:CountStep counterName="index2" counterStartIndex="0"
										sequenceRestartStep="2" stepIndexName="step">
										<logic:equal name="step" value="0">
											<tr class="ODS_line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_line2">
										</logic:equal>
									</ods:CountStep>
								    
								    <td width="2%"  class="centralized"><%=dddPhone%></td>
								    <td width="10%" class="centralized"><%=indexPhone%></td>
								    <td width="2%"  class="centralized"><%=ramalPhone%></td>
								    <td width="25%" align="left"><%=domainsFullNameText%></td>
								    <td width="25%" align="left"><%=fullNameText_2%></td>
								    <td width="25%" align="left"><%=fullNameText_3%></td>
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
								<TD><html:button property="deleteBtn" value="Confirmar Exclusão"
									onclick="submitAction('delete');"></html:button></TD>
								<TD><html:button property="backBtn" value="Voltar"
									onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					</td>
				</tr>

				<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</TABLE>
			</td>
		</tr>
	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>

