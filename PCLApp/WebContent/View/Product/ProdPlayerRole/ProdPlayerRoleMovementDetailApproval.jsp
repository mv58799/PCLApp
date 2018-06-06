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

		function extraActions( action ){};
		function setSelectedKeys(plyrCnpjNbr, plyrRoleType, prodCode, sysCode, sysSegCode){
			
			document.forms[0].selectedPlyrCnpjNbr.value = plyrCnpjNbr;
			document.forms[0].selectedPlyrRoleTypeCode.value = plyrRoleType;
			document.forms[0].selectedProdCode.value = prodCode;
			document.forms[0].selectedSysCode.value = sysCode;
			document.forms[0].selectedSysSegCode.value = sysSegCode;
		};
	 </script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="ProdPlayerRole.ProdPlayerRoleMovementDetail"/>
		<jsp:param name="approvalControlNames" value="'approvedBtn','',''"/>
		<jsp:param name="fieldsWithMask" value="['plyrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

	<TITLE>Detalhe de Associação de Player x Produto com Pendência de Aprovação</TITLE>
	</HEAD>

	<body>
		<html:form action="/ProdPlayerRole.ProdPlayerRoleMovementDetail.Approval.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Approved"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>			
				<html:text property="backURL" value="ProdPlayerRole.ProdPlayerRoleMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Associação de Player x Produto com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="19%">CNPJ</TD>
											<TD width="58%">Nome do Player</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="19%"><html:text styleClass="ODS_Text_Field_Size_15" property="plyrCnpjNbr" disabled="true" maxlength="18"></html:text></TD>
											<TD width="58%"><html:text styleClass="ODS_Text_Field_Size_60" property="plyrName" disabled="true" maxlength="60"></html:text></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td  width="19%">Usuário Última Atualização</td>
											<td>
												<table width="100%">
													<tr class="ODS_Detail_Line1">
														<td width="34%">Data/Hora Última Atualização</td>
														<td></TD>
													</tr>
												</table>
											</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td  width="19%"><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserId" disabled="true" maxlength="20"></html:text></TD>
											<td>
												<table width="100%">
													<tr class="ODS_Detail_Line2">
														<td width="34%"><html:text styleClass="ODS_Text_Field_Size_15" property="lastUpdDate" disabled="true"> </html:text></td>
														<td></td>
													</tr>
												</table>
											</td>
										</tr>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<html:hidden property="selectedPlyrCnpjNbr" value="" />
					<html:hidden property="selectedPlyrRoleTypeCode" value="" />
					<html:hidden property="selectedProdCode" value="" />
					<html:hidden property="selectedSysCode" value="" />
					<html:hidden property="selectedSysSegCode" value="" />

					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Lista de Produtos</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="fixed">
											<TH class="ODS_header" width="15%">CNPJ Player</TH>
											<TH class="ODS_header" width="15%">Papel do Player</TH>
											<TH class="ODS_header" width="15%">Código do Produto</TH>
											<TH class="ODS_header" width="15%">Código do Sistema</TH>
											<TH class="ODS_header" width="15%">Segmento do Sistema</TH>
											<TH class="ODS_header" width="15%">Descrição do Produto</TH>
											<TH class="ODS_header" width="10%">Ação</TH>
										</tr>
										
										<%int rowIndex = 0;%>
										<logic:notEmpty name="ProdPlayerRoleMovementDetailForm"	property="prodPlayerRoleDomains">
											<logic:iterate name="ProdPlayerRoleMovementDetailForm"
												property="prodPlayerRoleDomains" indexId="index" id="row">
												<%
												ProdPlayerRoleMovementDetailForm prodPlayerRoleMovForm = (ProdPlayerRoleMovementDetailForm) session.getAttribute("ProdPlayerRoleMovementDetailForm");
												String[] resultLine = prodPlayerRoleMovForm.getProdPlayerRoleDomains()[rowIndex++];
												%>
												<ods:CountStep counterName="index" counterStartIndex="0"
													sequenceRestartStep="2" stepIndexName="step">
													<logic:equal name="step" value="0">
														<tr class="ODS_line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_line2">
													</logic:equal>
												</ods:CountStep>

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
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%"></TD>
											<TD><html:button property="approvedBtn" value="Aprovar" onclick="submitAction('approve');"></html:button></TD>
											<TD><html:button property="reproveBtn" value="Reprovar" onclick="submitAction('reprove','', true);"></html:button></TD>
											<td><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp"></jsp:include>
			</table>	

<script>

	disableApproveButtons("<bean:write name='ProdPlayerRoleMovementDetailForm' property='lastUpdUserId'/>","<bean:write name='ProdPlayerRoleMovementDetailForm' property='opernCode'/>",'true');							
</script>		
		</html:form>
	</body>
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
