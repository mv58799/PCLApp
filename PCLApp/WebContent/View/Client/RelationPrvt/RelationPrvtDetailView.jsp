
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

<script language="javascript">
	function extraActions(action){

	}																	
</script>
<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="RelationPrvt.RelationPrvtDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Relacionamentos Private</TITLE>
</HEAD>
<body>
<html:form action="/RelationPrvt.RelationPrvtDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Relacionamentos" />
	</jsp:include>
    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL" value="RelationPrvt.RelationPrvtDetail.Consult.Show" style="display:none"></html:text>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Relacionamentos Private</th>
					</tr>
				</thead>
					<tr>
						<td>
						<table class="ODS_internalWidth" border="0" cellspacing="0">
							<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Número do Relacionamento</TD>
											<TD width="17%" colspan="2">Classe de Relacionamento</TD>
											<TD width="16%">Tipo de Relacionamento</TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2"><html:text property="reltnNbr" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD width="17%" colspan="2"><html:text property="reltnClassCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD width="16%"><html:text property="reltnTypeCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Categoria do Relacionamento</TD>
											<TD width="17%" colspan="2">Segmento</TD>
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2"><html:text property="reltnCatCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD width="17%" colspan="2"><html:text property="reltnSegCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Número do Cliente Endereçador</TD>
											<TD width="17%" colspan="2">Nome do Cliente</TD>
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2"><html:text property="reltnCustAddrNbr" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD colspan="4"><html:text  property="reltnCustAddrNbrCustFullNameText" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
											
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Sequência do Endereço</TD>
											<TD width="17%" colspan="2">Data do Estabelecimento do Relacionamento</TD>
											<TD width="16%">Origem do Cliente</TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2" ><html:text property="reltnCustAddrSeqNbr" disabled="true"></html:text></TD>
											
											<TD width="17%" colspan="2"><html:text property="reltnEstabDate" disabled="true" styleClass="ODS_Text_Field_Size_20"></html:text></TD>
											<TD width="16%">
												<html:select property="reltnPrmtCode" disabled="true">
												<html:option value=""></html:option>
													<html:options property="reltnPrmtCodeDomain.columnValuesByName(RELTN_PRMT_CODE)" labelProperty="reltnPrmtCodeDomain.columnValuesByName(RELTN_PRMT_TEXT)" />
												</html:select>
											</TD>
											<TD width="16%"></TD>
										</tr>
										
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Código da Carteira</TD>
											
											<TD width="17%" colspan="2">Descrição da Carteira</TD>
											
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2"><html:text property="reltnPortfCode" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD colspan="4"><html:text  property="portfNameText" disabled="true" styleClass="ODS_Text_Field_Size_40"></html:text></TD>
											
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Banker da Carteira</TD>
											
											<TD width="17%" colspan="2">Nome do Banker</TD>
										
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2"><html:text property="portfOffcrNbr" disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
											
											<TD colspan="4"><html:text property="offcrNameText" disabled="true" styleClass="ODS_Text_Field_Size_40"></html:text></TD>
											
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Tipo de Recebimento de Extrato do CitiStatement</TD>
											<TD width="17%" colspan="2">Faixa de Pontuação</TD>
											
											<TD width="16%"></TD>
											<TD width="16%"></TD>
											
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2"><html:text property="reltnStmtOptnCode" disabled="true"></html:text></TD>
											
											<TD width="17%" colspan="2"><html:text property="reltnClassScoreCode" disabled="true"></html:text></TD>
											
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD colspan="4">Indicador de Recebimento de Extrato Isolado de:</TD>
											
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" >
												<logic:equal property="reltnMfStmtInd" value="S" name="RelationPrvtDetailForm">
													<input type="checkbox" name="reltnMfStmtInd" style="border:none" checked disabled="disabled" />
												</logic:equal>
												<logic:notEqual name="RelationPrvtDetailForm" property = "reltnMfStmtInd" value="S">
													<html:checkbox style="border:none" property="reltnMfStmtInd" disabled="true" value = "false"></html:checkbox> 
												</logic:notEqual>	
												Multifundos
											</TD>
											<TD width="17%" >
												<logic:equal property="reltnSavStmtInd" value="S" name="RelationPrvtDetailForm">
													<input type="checkbox" name="reltnSavStmtInd" style="border:none" checked disabled="disabled" />
												</logic:equal>
												<logic:notEqual name="RelationPrvtDetailForm" property = "reltnSavStmtInd" value="S">
													<html:checkbox style="border:none" property="reltnSavStmtInd" disabled="true" value = "false"></html:checkbox> 
												</logic:notEqual>	
												Poupança
											</TD>
											<TD width="17%" >
												<logic:equal property="reltnAcctStmtInd" value="S" name="RelationPrvtDetailForm">
													<input type="checkbox" name="reltnAcctStmtInd" style="border:none" checked disabled="disabled" />
												</logic:equal>
												<logic:notEqual name="RelationPrvtDetailForm" property = "reltnAcctStmtInd" value="S">
													<html:checkbox style="border:none" property="reltnAcctStmtInd" disabled="true" value = "false"></html:checkbox> 
												</logic:notEqual>	
												Conta Corrente&nbsp;&nbsp;&nbsp;&nbsp;
											</TD>
											<TD width="17%" >
												<logic:equal property="reltnTdStmtInd" value="S" name="RelationPrvtDetailForm">
													<input type="checkbox" name="reltnTdStmtInd" style="border:none" checked disabled="disabled" />
												</logic:equal>
												<logic:notEqual name="RelationPrvtDetailForm" property = "reltnTdStmtInd" value="S">
													<html:checkbox style="border:none" property="reltnTdStmtInd" disabled="true" value = "false"></html:checkbox> 
												</logic:notEqual>	
												TD
											</TD>
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Numero do Cliente Endereçador para Email</TD>
											

										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%"><html:text property="reltnAddrEmailCustNbr"  disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											<TD colspan="5"><html:text property="reltnAddrEmailCustNbrCustFullNameText"  disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
											
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Numero do Cliente Endereçador para Celular</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%"><html:text property="reltnAddrCellCustNbr"  disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											<TD colspan="5"><html:text property="reltnAddrCellCustNbrCustFullNameText"  disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
											
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" colspan="2">Numero Cliente Base</TD>
											
											<TD width="17%" colspan="2">Pacote da classe</TD>
											<TD width="16%" colspan="2">Nível de Risco</TD>

										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" colspan="2"><html:text property="reltnCorpBaseNbr" disabled="true"></html:text></TD>
											<td width="17%" colspan="2">
												<html:select property="reltnSpcfClassServPackInd" styleClass="ODS_Select_Field_Size_5" disabled="true">
													<html:option value=""></html:option>
													<html:options property="reltnSpcfClassServPackIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="reltnSpcfClassServPackIndDomain.columnValuesByName(INDICATOR_TEXT)" />
												</html:select>
											</td>
											<TD width="16%"><html:text property="reltnRiskLevelCode" disabled="true"></html:text></TD>
											
											
											<TD width="16%"></TD>
										</tr>


										<tr class="ODS_Detail_Line1">
											<TD width="17%" >Número Owner</TD>
											
											<TD width="17%" colspan="3">Nome Owner</TD>
										
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" ><html:text property="reltnCust1Nbr" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD colspan="5"><html:text property="custFullNameText" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
											
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" >Número Agregado 1</TD>
											
											<TD width="17%" colspan="3">Nome Agregado 1</TD>
											
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" ><html:text property="reltnCust2Nbr" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD colspan="5"><html:text property="custFullNameText2" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
											
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%" >Número Agregado 2</TD>
											
											<TD width="17%" colspan="3">Nome Agregado 2</TD>
											
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%" ><html:text property="reltnCust3Nbr" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD width="17%" colspan="5"><html:text property="custFullNameText3" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
										
										</tr>										
										<tr class="ODS_Detail_Line1">
											<TD width="17%" >Número Agregado 3</TD>
											 
											<TD width="17%" colspan="3">Nome Agregado 3</TD>
											
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%"><html:text property="reltnCust4Nbr" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD colspan="5"><html:text property="custFullNameText4" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>

										</tr>
										<tr class="ODS_Detail_Line1">
											<TD width="17%">Número Agregado 4</TD>
											
											<TD width="17%" colspan="3">Nome Agregado 4</TD>
										
											<TD width="16%"></TD>
											<TD width="16%"></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD width="17%"><html:text property="reltnCust5Nbr" disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
											
											<TD colspan="5"><html:text property="custFullNameText5" disabled="true" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
											
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
										<TR>
											<TD width="100%" align="right"><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
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
