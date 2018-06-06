<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.citibank.ods.modules.client.ipdocprvt.form.IpDocTransFinancDetailForm" %>

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
        }
        
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="IpDocPrvt.IpDocTransFinancDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
<TITLE>Transação Financeira</TITLE>
</HEAD>

<body>
<html:form action="/IpDocPrvt.IpDocTransFinancDetail.Insert.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp">
		<jsp:param name="currentSheet" value="CustomerSheet" />
	</jsp:include>
	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>


	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<html:text property="backURL"
					value="IpDocPrvt.IpDocTransFinancDetail.Insert.Show"
					style="display:none"></html:text>
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Instrução
						Permanente - Transação Financeira</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_line1">
								<TD width="35%">Nome do Cliente</TD>
								<TD width="14%">Conta Corrente</TD>
								<td width="4%">&nbsp;</td>
								<td width="14%">CCI</td>
								<TD width="33%">&nbsp;</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="35%"><html:text property="custFullNameText"
									styleClass="ODS_Text_Field_Size_60" disabled="true"></html:text></TD>
								<td width="14%"><html:text property="custCurAcctNbr"
									styleClass="ODS_Text_Field_Size_20" disabled="true"></html:text></td>
								<td width="4%"><html:radio styleClass="radio" 
								                property="trfAcctType" value="1" >
								               </html:radio> 
								</td>
								<td width="14%"><html:text property="invstCurAcctNbr"
									styleClass="ODS_Text_Field_Size_10" disabled="true"></html:text>
								</td>
								<TD width="33%"><html:radio styleClass="radio" property="trfAcctType" value="2" >
								               </html:radio>
								 </TD>
							</tr>
						</tbody>
					</table>
					</td>
				</tr>
				<thead>
					<tr>
						<th class="subtitle" scope="colgroup" colspan="3">Dados do
						Beneficiário</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>
							<tr class="ODS_Detail_Line2">
								<td colspan="6" rowspan="2">
								<table width="685">
									<tr>
										<td width="9%">Banco</td>
										<td width="10%">Agência</td>
										<td width="22%">Conta Corrente&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td width="40%">CPF/CNPJ</td>
										<td width="19%">&nbsp;</td>
									</tr>
									<tr>
										<td width="9%"><html:text styleClass="ODS_Text_Field_Size_5"
											property="bankCode" maxlength="3"
											onkeyup="MaskFieldPress('CHAR','NNN','left',null)"
											disabled="true"></html:text></TD>
										<td width="10%"><html:text styleClass="ODS_Text_Field_Size_5"
											property="brchCode" maxlength="4"
											onkeyup="MaskFieldPress('CHAR','NNNN','left',null)"
											disabled="true"></html:text>
										<td width="22%"><html:text styleClass="ODS_Text_Field_Size_15"
											property="beneCurAcctNbr" maxlength="15"
											onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"
											disabled="true"></html:text>
										<td width="40%"><html:text styleClass="ODS_Text_Field_Size_15"
											property="beneCpfCnpjNbr" maxlength="14"
											onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"
											disabled="true"></html:text>
										<td width="19%">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr class="ODS_Detail_Line2"></tr>

							<tr class="ODS_Detail_Line2">
								<td width="33%">Nome</td>
								<td width="17%">Valor *</td>
								<td width="7%">PA *</td>
								<td width="9%">Data/Hora *</td>								
							</tr>
							<tr class="ODS_Detail_Line1">
								<td><html:text property="beneNameText"
									styleClass="ODS_Text_Field_Size_60" maxlength="60"
									disabled="true"></html:text></td>
								<td width="17%"><html:text styleClass="ODS_Text_Field_Size_15"
									property="trfAmtNbr" maxlength="18" onkeyup="NumberFieldPress('15','2','FALSE',trfAmtNbr)">
								</html:text></td>


								<td width="7%"><html:text styleClass="ODS_Text_Field_Size_55"
									property="chnnlAttdText" maxlength="40"  >									
									</html:text>  
								</td>
								<td width="9%"><html:text styleClass="ODS_Text_Field_Size_7"
									property="trfDate" maxlength="16" onkeypress="formataDataHora(this);">


								</html:text></td>
								
							</tr>

						</tbody>
					</table>
					</td>
				</tr>

				<tr>
					<td>

					<table class="ODS_internalWidth" border="0">
						<thead>
							<tr>
								<th class="subtitle" scope="colgroup" colspan="10">Dados de
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

								<logic:iterate name="IpDocTransFinancDetailForm"
									property="domainsFullNameText" indexId="index2" id="baseArray2">
									<%IpDocTransFinancDetailForm ipDocPrvtForm =
	(IpDocTransFinancDetailForm) session.getAttribute(
		"IpDocTransFinancDetailForm");
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
auxIndex2++;%>

									<bean:define name="IpDocTransFinancDetailForm"
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
									<td width="2%" class="centralized"><%=dddPhone%></td>
									<td width="10%" class="centralized"><%=indexPhone%></td>
									<td width="2%" class="centralized"><%=ramalPhone%></td>
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
					<table class="ODS_internalWidth" border="0">
						<TBODY>
							<TR>
								<TD width="100%"></TD>
								<TD><html:button property="insertBtn"
									value="Confirmar Transferência"
									onclick="submitAction('insert');"></html:button></TD>
								<TD><html:button property="clearBtn" value="Limpar"
									onclick="clearAllPage();"></html:button></TD>
								<TD><html:button property="backBtn" value="Voltar"
									onclick="submitAction('back');"></html:button></TD>
							</TR>
						</TBODY>
					</TABLE>
					</td>
				</tr>
			</TABLE>
			</td>
		</tr>
		<jsp:include page="/View/Util/Footer.jsp" flush="true" />
	</table>
</html:form>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>

