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
	href="<%=request.getContextPath()%>/Common/css/citi.css">

<script language="javascript">
	function extraActions(action){

	}																	
</script>

<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
	<jsp:param name="pageName" value="CustomerPrvt.CustomerPrvtDetail" />
	<jsp:param name="fieldsWithMask"
		value="['custCpfCnpjNbr','CPF/CNPJ',null,null,null]" />
</jsp:include>

<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Cliente Private</TITLE>

</HEAD>

<body>

<html:form action="/CustomerPrvt.CustomerPrvtDetail.Consult.Show.do">

	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Clientes" />
	</jsp:include>

	<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL"
		value="CustomerPrvt.CustomerPrvtDetail.Consult.Show"
		style="display:none"></html:text>

	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe de
						Cliente Private</th>
					</tr>
				</thead>
				<tr>
					<td>
					<table class="ODS_internalWidth" border="0" cellspacing="0">
						<tbody>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr class="ODS_line11">
								<td colspan="3">Dados Gerais:</td>
							</tr>

							<tr>
								<td colspan="4"><img
									src='<%=request.getContextPath()%>/Common/image/20grey1.gif'
									width="131%" height="1"></td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Número do Cliente</TD>
								<td colspan="3">Nome</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%"><html:text property="custNbr" disabled="true"
									styleClass="ODS_Text_Field_Size_10"></html:text></td>
								<TD colspan="3"><html:text property="custFullNameText"
									disabled="true" styleClass="ODS_Text_Field_Size_50"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Número Internacional</TD>
								<TD colspan="2">Nome Abreviado</TD>
								<TD width="15%">Key Name</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="15%"><html:text property="custIntlNbr"
									disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
								<TD colspan="2"><html:text property="custShortNameText"
									disabled="true" styleClass="ODS_Text_Field_Size_30"></html:text></TD>
								<TD width="15%"><html:text property="custKeyNameText"
									disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
							</tr>


							<tr class="ODS_Detail_line1">
								<TD width="25%">Data Nascimento</TD>
								<TD width="25%">Naturalidade</TD>
								<TD width="18%">Estado Natal</TD>
								<TD width="32%">Código de nacionalidade</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="15%"><html:text property="custBirthDate"
									disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
								<TD width="15%"><html:text property="custBirthCityText"
									disabled="true" styleClass="ODS_Text_Field_Size_20"></html:text></TD>
								<TD width="10%"><html:text property="custBirthStateCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="15%"><html:text property="custBirthCntryCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Sexo</TD>
								<TD width="15%">Estado Civil</TD>
								<TD width="10%"></TD>
								<td width="15%">&nbsp;</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%"><html:text property="custSexCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></td>
								<td width="15%"><html:text property="custCivilStatCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></td>
								<td width="10%"></td>
								<td width="15%">&nbsp;</td>
							</tr>

							<tr class="ODS_Detail_line1">
								<td colspan="4">
								<table width="100%">
									<tr>
										<TD width="20%">Número de Identidade</TD>
										<TD width="22%">Orgão Emissor</TD>
										<TD width="15%">Data Emissão</TD>
										<TD width="12%">Cód. Emissor</TD>
										<TD width="15%">Estado</TD>
									</tr>
								</table>
								</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<td colspan="4">
								<table width="100%">
									<tr>
										<TD width="20%"><html:text property="custNatId"
											disabled="true" styleClass="ODS_Text_Field_Size_20"></html:text></TD>
										<TD width="22%"><html:text property="custNatIdEmitName"
											disabled="true" styleClass="ODS_Text_Field_Size_15"></html:text></TD>
										<TD width="15%"><html:text property="custNatIdApplDate"
											disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
										<TD width="12%"><html:text property="custNatIdEmitCode"
											disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
										<TD width="15%"><html:text property="custNatIdEmitStateCode"
											disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
									</tr>
								</table>
								</td>
							</tr>

							<tr class="ODS_Detail_line1">
								<td colspan="4">
								<table width="100%">
									<tr>
										<TD width="20%">CPF/CNPJ</TD>
										<TD width="22%">Ind. Isenção CPF</TD>
										<TD width="15%">CPF Próprio</TD>
										<TD width="27%">Ind. Grau de Parentesco</TD>
									</tr>
								</table>
								</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<td colspan="4">
								<table width="100%">
									<tr>
										<TD width="20%"><html:text property="custCpfCnpjNbr"
											disabled="true" styleClass="ODS_Text_Field_Size_15"></html:text>
										</TD>
										<TD width="22%"><html:select property="custNoCpfInd"
											styleClass="ODS_Select_Field_Size_5" disabled="true">
											<html:option value=""></html:option>
											<html:options
												property="custNoCpfIndDomain.columnValuesByName(INDICATOR_CODE)"
												labelProperty="custNoCpfIndDomain.columnValuesByName(INDICATOR_TEXT)" />
										</html:select></TD>
										<TD width="15%"><html:select property="custCpfOwnInd"
											styleClass="ODS_Select_Field_Size_5" disabled="true">
											<html:option value=""></html:option>
											<html:options
												property="custCpfOwnIndDomain.columnValuesByName(INDICATOR_CODE)"
												labelProperty="custCpfOwnIndDomain.columnValuesByName(INDICATOR_TEXT)" />
										</html:select></TD>
										<TD width="27%"><html:select property="custParentLevelInd"
											styleClass="ODS_Select_Field_Size_5" disabled="true">
											<html:option value=""></html:option>
											<html:options
												property="custParentLevelIndDomain.columnValuesByName(INDICATOR_CODE)"
												labelProperty="custParentLevelIndDomain.columnValuesByName(INDICATOR_TEXT)" />
										</html:select></TD>
									</tr>
								</table>
								</td>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Green Card</TD>
								<TD width="15%" colspan="3">Nro. Green Card ou Social</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="15%"><html:select property="custGrcardInd"
									styleClass="ODS_Select_Field_Size_5" disabled="true">
									<html:option value=""></html:option>
									<html:options
										property="custGrcardIndDomain.columnValuesByName(INDICATOR_CODE)"
										labelProperty="custGrcardIndDomain.columnValuesByName(INDICATOR_TEXT)" />
								</html:select></TD>
								<TD width="15%" colspan="3"><html:text property="custSocSctyNbr"
									disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Nro. Dependentes</TD>
								<TD width="15%" colspan="2">Data de Alteração do Nro. de
								Dependentes</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="15%"><html:text property="custDepndNbr"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="15%" colspan="2"><html:text
									property="custDepndNbrDate" disabled="true"
									styleClass="ODS_Text_Field_Size_10"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Cód. Tipo Cliente</TD>
								<TD width="15%">Cód. Duplicação do Cliente</TD>
								<td colspan="2">Funcionário</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="15%"><html:text property="custTypeCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="15%"><html:text property="custDupCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD colspan="2"><html:select property="custEmplInd"
									styleClass="ODS_Select_Field_Size_5" disabled="true">
									<html:option value=""></html:option>
									<html:options
										property="custEmplIndDomain.columnValuesByName(INDICATOR_CODE)"
										labelProperty="custEmplIndDomain.columnValuesByName(INDICATOR_TEXT)" />
								</html:select></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Código Profissão</TD>
								<TD width="15%">Quantidade de Salários</TD>
								<TD colspan="2">Data de Verificação da Renda</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%"><html:text property="custProfCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></td>
								<td width="15%"><html:text property="custMgmtIncoMinSalCount"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></td>
								<TD colspan="2"><html:text property="custChkDate"
									disabled="true" styleClass="ODS_Text_Field_Size_15"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Ligado ao Grupo Citibank</TD>
								<TD width="15%">Data do Ingresso do Cliente</TD>
								<TD width="10%">Status do Cliente</TD>
								<td width="15%">Data do Status do Cliente</td>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="15%"><html:select property="custCitiGrpTieInd"
									styleClass="ODS_Select_Field_Size_5" disabled="true">
									<html:option value=""></html:option>
									<html:options
										property="custCitiGrpTieIndDomain.columnValuesByName(INDICATOR_CODE)"
										labelProperty="custCitiGrpTieIndDomain.columnValuesByName(INDICATOR_TEXT)" />
								</html:select></TD>
								<TD width="15%"><html:text property="custEstabDate"
									disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
								<TD width="10%"><html:text property="custActlStatCode"
									disabled="true"></html:text></TD>
								<TD width="15%"><html:text property="custStatDate"
									disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Cód. Constituição da Empresa</TD>
								<TD width="15%">Data de Constituição da Empresa</TD>
								<TD width="10%">Cód. Ocupacional</TD>
								<TD width="15%">Cód. Nacionalidade da Empresa</TD>
							</tr>
							<tr class="ODS_Detail_line2">
								<TD width="15%"><html:text property="custCoTypeCode"
									disabled="true"></html:text></TD>
								<TD width="15%"><html:text property="custFndtnDate"
									disabled="true" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
								<TD width="10%"><html:text property="custOccupCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="15%"><html:text property="custBirthCntryCoCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
							</tr>

							<tr class="ODS_Detail_line1">
								<TD width="15%">Cód. Grupo</TD>
								<TD width="15%">Cód. Subgrupo</TD>
								<TD width="10%">Ramo de Atividade</TD>
								<TD width="15%">Risco do Cliente</TD>							
							</tr>
							<tr class="ODS_Detail_line2">
								<td width="15%"><html:text property="custGrpCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></td>
								<TD width="15%"><html:text property="custSubGrpCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="10%"><html:text property="custActyAreaCode"
									disabled="true" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
								<TD width="15%">
									<html:text property="rdipDescription"
									disabled="true" styleClass="ODS_Text_Field_Size_40"></html:text>
								</TD>
									
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<logic:equal name="CustomerPrvtDetailForm"
								property="cmplDataButtonControl" value="1">

								<tr class="ODS_line11">
									<td colspan="3">Dados Complementares:</td>
								</tr>

								<tr>
									<td colspan="4"><img
										src='<%=request.getContextPath()%>/Common/image/20grey1.gif'
										width="131%" height="1"></td>
								</tr>

								<tr class="ODS_Detail_line1">
									<TD width="15%">Private Number</TD>
									<TD width="15%">Key Private Number</TD>
									<td>Número EM</td>

								</tr>
								<tr class="ODS_Detail_line2">
									<td width="15%"><html:text styleClass="ODS_Text_Field_Size_10"
										property="prvtCustNbr" disabled="true"></html:text></td>
									<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_10"
										property="prvtKeyNbr" disabled="true"></html:text></TD>
									<TD><html:text styleClass="ODS_Text_Field_Size_30"
										property="emNbr" disabled="true"></html:text></TD>

								</tr>

								<tr class="ODS_Detail_line1">
									<TD width="15%">Número do Banker</TD>
									<td colspan="3" width="75%" rowspan="2">
									<table>
										<tr>
											<TD width="50%">Nome do Banker</TD>
											<TD width="25%">Status do Cliente</TD>
										</tr>
										<tr>
											<TD width="50%"><html:text
												styleClass="ODS_Text_Field_Size_40" property="offcrText"
												disabled="true" size="60"></html:text></TD>
											<TD width="25%"><html:select property="custPrvtStatCode"
												styleClass="ODS_Select_Field_Size_10" disabled="true">
												<html:option value=""></html:option>
												<html:options
													property="custPrvtStatCodeDomain.columnValuesByName(CUST_PRVT_STAT_CODE)"
													labelProperty="custPrvtStatCodeDomain.columnValuesByName(CUST_PRVT_STAT_TEXT)" />
											</html:select></TD>
										</tr>
									</table>
									</td>
								</tr>
								<tr class="ODS_Detail_line2">
									<TD width="15%"><html:text styleClass="ODS_Text_Field_Size_5"
										property="offcrNbrSrc" disabled="true"></html:text></TD>
									<td colspan="2" width="75%"></td>
								</tr>

								<tr class="ODS_Detail_line1">
									<TD width="15%">Número do Banker (Sistema Global)</TD>
									<TD width="15%">Envio de Correspondência do Logotipo</TD>
									<td colspan="2">Envio de Mala Direta</td>
								</tr>
								<tr class="ODS_Detail_line2">
									<td width="15%"><html:text styleClass="ODS_Text_Field_Size_5"
										property="glbRevenSysOffcrNbr" disabled="true"></html:text></td>
									<TD width="15%"><html:select property="offclMailRecvInd"
										styleClass="ODS_Select_Field_Size_5" disabled="true">
										<html:option value=""></html:option>
										<html:options
											property="offclMailRecvIndDomain.columnValuesByName(INDICATOR_CODE)"
											labelProperty="offclMailRecvIndDomain.columnValuesByName(INDICATOR_TEXT)" />
									</html:select></TD>
									<TD colspan="2"><html:select property="mailRecvInd"
										styleClass="ODS_Select_Field_Size_5" disabled="true">
										<html:option value=""></html:option>
										<html:options
											property="mailRecvIndDomain.columnValuesByName(INDICATOR_CODE)"
											labelProperty="mailRecvIndDomain.columnValuesByName(INDICATOR_TEXT)" />
									</html:select></TD>
								</tr>

								<tr class="ODS_Detail_line1">
									<TD width="15%">Potencial Receita</TD>
									<td width="8%">Tipo de Cliente</td>

								</tr>
								<tr class="ODS_Detail_line2">
									<TD width="15%"><html:select property="wealthPotnlCode"
										styleClass="ODS_Select_Field_Size_40" disabled="true">
										<html:option value=""></html:option>
										<html:options
											property="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_CODE)"
											labelProperty="wealthPotnlCodeDomain.columnValuesByName(WEALTH_POTNL_TEXT)" />
									</html:select></TD>

									<td width="8%"><html:select property="prvtCustTypeCode"
										styleClass="ODS_Select_Field_Size_15" disabled="true">
										<html:option value=""></html:option>
										<html:options
											property="prvtCustTypeCodeDomain.columnValuesByName(PRVT_CUST_TYPE_CODE)"
											labelProperty="prvtCustTypeCodeDomain.columnValuesByName(PRVT_CUST_TYPE_TEXT)" />
									</html:select></td>


								</tr>

								<tr class="ODS_Detail_line1">
									<TD width="15%" colspan="4">Classificação Compliance</TD>
								</tr>
								<tr class="ODS_Detail_line2">
									<TD width="15%" colspan="4"><html:select
										property="classCmplcCode"
										styleClass="ODS_Select_Field_Size_40" disabled="true">
										<html:option value=""></html:option>
										<html:options
											property="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_CODE)"
											labelProperty="classCmplcCodeDomain.columnValuesByName(CLASS_CMPLC_TEXT)" />
									</html:select></TD>
								</tr>
												<tr>
					<td>

					<table class="ODS_internalWidth" border="0">
						<thead>
						 <tr>
							<th class="subtitle" scope="colgroup" colspan="10">Lista de ER's</th>
						 </tr>
						</thead>
						<tbody>
							<tr id="gridHeader" class="fixed">
								<TH class="ODS_header" width="25%">ER</TH>
								<TH class="ODS_header" width="25%">EM</TH>
							</tr>
							<%int auxIndex2 = 0;%>
							<tr class="ODS_line1">

								<logic:iterate name="CustomerPrvtDetailForm"
									property="domainsErNbr" indexId="index" id="baseArray">

									<bean:define name="CustomerPrvtDetailForm"
										property='<%="domainsErNbr[" + index + "]"%>'
										id="domainsErNbr" type="java.lang.String" />
									<bean:define name="CustomerPrvtDetailForm"
										property='<%="domainsEmNbr[" + index + "]"%>'
										id="domainsEmNbr" type="java.lang.String" />
										
									<ods:CountStep counterName="index" counterStartIndex="0"
										sequenceRestartStep="2" stepIndexName="step">
										<logic:equal name="step" value="0">
											<tr class="ODS_line1">
										</logic:equal>
										<logic:equal name="step" value="1">
											<tr class="ODS_line2">
										</logic:equal>
									</ods:CountStep>		
										
									<td width="25%" class="centralized"><%=domainsErNbr%></td>
									<td width="25%" class="centralized"><%=domainsEmNbr%></td>
								</logic:iterate>
							</tr>
						</tbody>
					</table>

					</td>
				</tr>
				<TD>&nbsp;</TD>

							</logic:equal>

						</tbody>
					</TABLE>

				<TABLE class="ODS_internalWidth" border="0">
					<TBODY>
						<TR>
							<td width="100%"></td>
							<TD><html:button property="backBtn" value="Voltar"
								onclick="submitAction('back');"></html:button></TD>
						</TR>
					</TBODY>
				</TABLE>
				<jsp:include page="/View/Util/Footer.jsp" flush="true"></jsp:include>
			</TABLE>
			</html:form></td>
		</tr>
	</table>
</body>
<jsp:include page="/View/Util/NoCacheIE.jsp" />
</html:html>
