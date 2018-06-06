<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">

		function extraActions( action )
		{
			if ( action == 'insertDomain' ) 
			{
				document.forms[0].action = "BkrPortfMgmt.BkrPortfMgmtMovementDetail.Update.InsertDomain"; 
				document.forms[0].backURL.disabled = true; 
			}

		};

		function enableAlterButton()
		{
			if ( document.forms[0].countUsr.value > 0 )
			{
				document.getElementById( 'alterBtn' ).disabled = false;
			}
			else
			{
				document.getElementById( 'alterBtn' ).disabled = true;
			}
		};

		function setUpdate()
		{
			document.forms[0].isUpdate.value = 'true';
			document.forms[0].isApprove.value = 'false';
			submitAction('update');
		};

		function setApprove()
		{
			document.forms[0].isApprove.value = 'true';
			document.forms[0].isUpdate.value = 'false';
			submitAction('approve');
		};

	 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="BkrPortfMgmt.BkrPortfMgmtMovementList"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'approvedBtn'"/>
			<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
			<jsp:param name="fieldsWithMask" value="['bkrCnpjNbrSrc','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>			
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Consulta de Associação de Carteira x Corretora com Pendência de Aprovação </TITLE>
	</HEAD>

	<body>
		<html:form action="/BkrPortfMgmt.BkrPortfMgmtMovementList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Approved"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>	
			<html:text property="backURL" value="BkrPortfMgmt.BkrPortfMgmtMovementList.List.Show" style="display:none"></html:text>			
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Associação de Carteira x Corretora com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>

										<tr class="ODS_Detail_Line1">
											<TD width="10%" colspan="2">Número do Cliente</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_15" property="custNbrSrc" maxlength="11" onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNN','left',null)"></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="10%" colspan="2">Nome do Cliente</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_60" property="custFullNameTextSrc" maxlength="60"></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="10%" colspan="2">Mnemônico do Cliente</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_60" property="custMnmcNameSrc" maxlength="20"></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="10%" colspan="2">Nome da Carteira</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_60" property="portfMgmtProdNameSrc" maxlength="40"></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="10%" colspan="2">Conta Corrente</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_15" property="curAcctNbrSrc" maxlength="15"  onkeyup="MaskFieldPress('CHAR','NNNNNNNNNNNNNNN','left',null)"></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="10%" colspan="2">Código do Produto</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_15" property="prodCodeSrc" maxlength="10" ></html:text></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD width="10%" colspan="2">Usuário da Última Atualização</TD>
											<TD width="50%" colspan="2"><html:text styleClass="ODS_Text_Field_Size_20" property="lastUpdUserIdSrc" maxlength="20" ></html:text></TD>
										</tr>

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
										<TR class="fixed">
											<TD width="100%">&nbsp;</TD>
											<TD width="6%"><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											<TD align="right"><html:button property="approvedBtn" value="Aprovação" onclick="setApprove();" disabled="true"></html:button></TD>
											<TD align="right"><html:button property="alterBtn" value="Alterar" onclick="setUpdate();" disabled="true"></html:button></TD>
											<TD align="left" width="44"><html:button property="clearBtn" value="Limpar" onclick="clearAllPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<html:hidden property="selectedBkrCnpjNbr"/>
					<html:hidden property="selectedCustNbr"/>
					<html:hidden property="countUsr"/>
					<html:hidden property="isUpdate" />
					<html:hidden property="isApprove" />
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Resultado da Consulta</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table id="gridTable" class="ODS_internalWidth" border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
											<TH class="ODS_header" width="2%">&nbsp;</TH>
											<TH class="ODS_header" width="53%">Nome</TH>
											<TH class="ODS_header" width="13%">CPF/CNPJ</TH>
											<TH class="ODS_header" width="32%">Quantidade de carteiras em aprovação</TH>
										</tr>
											<ods:DataSetRows name="BkrPortfMgmtMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">				
												<logic:equal name="step" value="0">
													<tr class="ODS_line1">
												</logic:equal>
												<logic:equal name="step" value="1">
													<tr class="ODS_line2"> 
												</logic:equal>
												<bean:define name="resultRow" 
													property="stringByName(COUNT_USR)" id="countUsr" type="java.lang.String">
												</bean:define>
													<bean:define name="resultRow" 
													property="stringByName(CUST_NBR)" id="selectedCustNbr" type="java.lang.String">
												</bean:define>
			 											<TD width="2%"><input type="radio" class="radio" name="selection" id="pmaRadioButton" onclick="javascript:selectedCustNbr.value='<%= selectedCustNbr %>'; countUsr.value='<%= countUsr %>';enableAlterButton();disableButtons(false);"/></td>
														<TD width="53%"><a class="ODS_CursorHand" href="#" onclick="javascript:selectedCustNbr.value='<%= selectedCustNbr %>'; setApprove();"><bean:write name="resultRow" property="stringByName(CUST_NAME)"/></a></td>
														<TD width="13%" align="right"><bean:write name="resultRow" property="stringByName(CPF_CNPJ_NBR)"/></td>
														<TD width="32%" align="right"><bean:write name="resultRow" property="stringByName(QTY_PORTF_MGMT)"/></td>
											</ods:DataSetRows>
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
											<TD width="100%">&nbsp;</TD>
											<TD align="left"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>										
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"/>
			</table>			
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>