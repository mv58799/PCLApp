
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
	<jsp:param name="pageName" value="CustAddress.CustAddressDetail" />
</jsp:include>
<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

<TITLE>Detalhe de Endereço</TITLE>
</HEAD>
<body>
<html:form action="/CustAddress.CustAddressDetail.Consult.Show.do">
	<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
		<jsp:param name="currentSheet" value="CustomerSheet" />
		<jsp:param name="currentSubSheet" value="Customer" />
	</jsp:include>
    <jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>

	<html:text property="backURL" value="CustAddress.CustAddressList.List.Show"	style="display:none"></html:text>
	<table class="ODS_mainTable" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>
			<table class="ODS_internalWidth" border="0">
				<thead>
					<tr height="40">
						<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Endereço</th>
					</tr>
				</thead>
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0" cellspacing="0">
							<tbody>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">Número do Cliente</TD>
									<TD width="15%"></TD>
									<TD width="26%" colspan = "4">Nome do Cliente</TD>
								
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD width="24%"><html:text disabled="true" property="custNbr" styleClass="ODS_Text_Field_Size_10"></html:text></TD>
									<TD width="15%"></TD>
									<TD width="26%" colspan = "4"><html:text disabled="true" property="addrSeqNbr" styleClass="ODS_Text_Field_Size_60"></html:text></TD>
									
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">Sequência do endereço</TD>
									<TD width="15%"></TD>
									<TD width="26%">Tipo de endereço</TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="18%"></TD>
								
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD width="24%"><html:text disabled="true" property="addrSeqNbr" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
									<TD width="15%"></TD>
									<TD width="26%">
											<html:select property="addrTypeCode" styleClass="ODS_Select_Field_Size_15" disabled="true">
											<html:option value=""></html:option>
											<html:options property="addrTypeCodeDomain.columnValuesByName(ADDR_TYPE_CODE)" labelProperty="addrTypeCodeDomain.columnValuesByName(ADDR_TYPE_TEXT)" />
										</html:select>
									</TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">Endereço</TD>
									<TD width="15%"></TD>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="10%"></TD>
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD colspan="4"><html:text styleClass="ODS_Text_Field_Size_70" disabled="true" property="addrNameText"></html:text></TD>
									<TD width="21%">&nbsp;</TD>
									<TD width="18%"></TD>							
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">Bairro</TD>
									<TD width="15%"></TD>
									<TD width="26%">CEP</TD>
									<TD width="14%"></TD>
									<TD width="21%">Cidade</TD>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD width="24%"><html:text disabled="true" property="addrNeighbText" styleClass="ODS_Text_Field_Size_20"></html:text></TD>
									<TD width="15%"></TD>
									<TD width="26%"><html:text styleClass="ODS_Text_Field_Size_5" maxlength="5" disabled="true" property="zipCode">
										</html:text>
										&nbsp;-&nbsp;
										<html:text styleClass="ODS_Text_Field_Size_5" maxlength="3"disabled="true" property="zipExtnCode" ></html:text></TD>
									<TD width="14%"></TD>
									<TD width="21%"><html:text disabled="true" property="addrCityText" styleClass="ODS_Text_Field_Size_25"></html:text></TD>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">Estado</TD>
									<TD width="15%">Cód. do País</TD>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD width="24%"><html:text disabled="true" property="addrStateCode" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
									<TD width="15%"><html:text disabled="true" property="addrCntryCode" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">Caixa Postal</TD>
									<TD width="15%">Mala direta</TD>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD width="24%"><html:text disabled="true" property="mailBoxNbr" styleClass="ODS_Text_Field_Size_5"></html:text></TD>
									<TD width="15%"><html:select property="cellMailInd" styleClass="ODS_Select_Field_Size_5" disabled="true">
											<html:option value=""></html:option>
											<html:options property="cellMailIndDomain.columnValuesByName(INDICATOR_CODE)" labelProperty="cellMailIndDomain.columnValuesByName(INDICATOR_TEXT)" />
										</html:select></TD>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line1">
									<td colspan = "6">
										<table class="ODS_internalWidth" border="0" cellspacing="0">
										<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="10%">Operadora</TD>
											<td width="40%">
											<table class="ODS_internalWidth" border="0" cellspacing="0">
												<tbody>
												<tr class="ODS_Detail_Line1">
														<td width="23%">(DDD)</td>
														<td width="35%">Telefone</td>
														<td width="42%">Ramal</td>
													</tr>
												</tbody>
											</table>
											</td>
											<TD width="50%"><table class="ODS_internalWidth" border="0" cellspacing="0">
												<tbody>
												<tr class="ODS_Detail_Line1">
														<td width="15"></td>
														<TD width="20%"></TD>
														<td width="19%"></td>
														<td width="57%"></td>
													</tr>
												</tbody>
											</table>
											</TD>
											
											</tr>
										</tbody>
										</table>
									</td>
								</tr>
								<tr class="ODS_Detail_Line2">
									<td colspan = "6">
										<table class="ODS_internalWidth" border="0" cellspacing="0">
										<tbody>
										<tr class="ODS_Detail_Line1">
											<TD width="10%"><html:text disabled="true" property="phoneOpCode" maxlength="3" styleClass="ODS_Text_Field_Size_5"></html:text>
											</TD>
											<td width="8"></td>
											<TD width="41%">
												<table class="ODS_internalWidth" border="0" cellspacing="0">
												<tbody>
													<tr class="ODS_Detail_Line1">
														<td width="20%"><html:text disabled="true"
															property="phoneAreaCode" maxlength="4"
															styleClass="ODS_Text_Field_Size_5"></html:text></td>
														<td width="35%"><html:text disabled="true"
															property="phoneNbr" maxlength="9"
															styleClass="ODS_Text_Field_Size_10"></html:text></td>
														<td width="45%"><html:text disabled="true"
															property="phoneExtnNbr" maxlength="4"
															styleClass="ODS_Text_Field_Size_5"></html:text></TD>
													</tr>
												</tbody>
												</table>
											</TD>
											<TD width="50%">
												<table class="ODS_internalWidth" border="0" cellspacing="0">
												<tbody>
													<tr class="ODS_Detail_Line1">
														<td width="3"></td>
														<td width="20%">
														</td>
														<td width="19%">
														</TD>
														<td width="60%">
														</TD>
													</tr>
												</tbody>
												</table>
											</TD>
											
										</tr>
									</tbody>
									</table>
									</td>
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="25%" colspan = "2">
										<table class="ODS_internalWidth" border="0" cellspacing="0">
											<tbody>
											<tr class="ODS_Detail_Line1">
												<td width="28%">(DDD)</td>
												<td width="72%">Fax</td>
											</tr>
											</tbody>
										</table>
									</TD>									
									<TD width="18%" colspan = "2"><table class="ODS_internalWidth" border="0" cellspacing="0">
										<tbody>
										<tr class="ODS_Detail_Line1">
											<td width="19%">(DDD)</td>
											<td width="81%">Telex</td>
										</tr>
										</tbody>
									</table>
									</TD>
									<TD colspan="2"></TD>
																		
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD width="25%" colspan = "2">
										<table class="ODS_internalWidth" border="0" cellspacing="0">
										<tbody>
											<tr class="ODS_Detail_Line1">
												<td width="28%">
													<html:text disabled="true" property="faxAreaCode" maxlength="4" styleClass="ODS_Text_Field_Size_5"></html:text>
												</td>
												<td width="72%">
													<html:text disabled="true" property="faxNbr" maxlength="9" styleClass="ODS_Text_Field_Size_10"></html:text>
												</TD>
											</tr>
										</tbody>
										</table>
									</TD>
									<TD width="20%" colspan = "2">
										<table class="ODS_internalWidth" border="0" cellspacing="0">
										<tbody>
											<tr class="ODS_Detail_Line1">
												<td width="19%">
													<html:text disabled="true" property="telexAreaCode" maxlength="4" styleClass="ODS_Text_Field_Size_5"></html:text>
												</td>
												<td width="81%">
													<html:text disabled="true" property="telexNbr" maxlength="5" styleClass="ODS_Text_Field_Size_5"></html:text>
												</TD>
												</tr>
										</tbody>
										</table>
									</TD>
									<td width="15%">
									</td>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>

								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">Celular</TD>
									<TD width="15%"></TD>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="10%"></TD>
								</tr>
								<tr>
									<td colspan="6">
										<table width="100%">
											<tr id="gridHeader" class="fixed" >										
													<TH class="ODS_header" width="5%">#</TH>										
													<TH class="ODS_header" width="15%">Operadora</TH>										
													<TH class="ODS_header" width="15%">Área</TH>
													<TH class="ODS_header" width="50%">Número do Celular</TH>
													<TH class="ODS_header" width="15%">Envio de Mala Direta</TH>
												</tr>
												<ods:DataSetRows name="CustAddressDetailForm"
													property="custCellResults" dataSetRowName="resultRowCell"
													stepIndexName="step" sequenceRestartStep="2">
													<logic:equal name="step" value="0">
														<tr class="ODS_Line1">
													</logic:equal>
													<logic:equal name="step" value="1">
														<tr class="ODS_Line2">
													</logic:equal>																				
													<TD width="5%"><bean:write name="resultRowCell"
														property="stringByName(CELL_SEQ_NBR)" /></td>
													<TD width="15%"><bean:write name="resultRowCell"
														property="stringByName(CELL_OP_CODE)" /></td>	
													<TD width="15%"><bean:write name="resultRowCell"
														property="stringByName(CELL_AREA_CODE)" /></td>										
													<TD width="50%" class="centralized"><bean:write name="resultRowCell"
														property="stringByName(CELL_PHONE_NBR)" /></td>					
													<TD width="15%" class="alignRight"><bean:write name="resultRowCell"
														property="stringByName(CELL_MAIL_IND)" /></td>					
												</ods:DataSetRows>
										</table>
									</td>
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD colspan="4"></TD>
									<td width="21%"></td>
									<TD width="18%"></TD>
								</tr>
								<tr class="ODS_Detail_Line1">
									<TD width="24%">E-mail</TD>
									<TD width="15%"></TD>
									<TD width="26%"></TD>
									<TD width="14%"></TD>
									<TD width="21%"></TD>
									<TD width="10%"></TD>
								</tr>
 								<tr>
									<td colspan="6">
										<table width="50%">
											<tr id="gridHeader" class="fixed">										
												<TH class="ODS_header" width="5%">#</TH>										
												<TH class="ODS_header" width="75%">E-mail</TH>
												<TH class="ODS_header" width="20%">Envio de Mala Direta</TH>
											</tr>
											<ods:DataSetRows name="CustAddressDetailForm"
												property="custMailResults" dataSetRowName="resultRowMail"
												stepIndexName="step" sequenceRestartStep="2">
												<logic:equal name="step" value="0">
													<tr class="ODS_Line1">
												</logic:equal>
												<logic:equal name="step" value="1">
													<tr class="ODS_Line2">
												</logic:equal>
												<TD width="5%"><bean:write name="resultRowMail"
													property="stringByName(EMAIL_SEQ_NBR)" /></td>										
												<TD width="75%"><bean:write name="resultRowMail"
													property="stringByName(EMAIL_TEXT)" /></td>										
												<TD width="20%" class="alignRight"><bean:write name="resultRowMail"
													property="stringByName(EMAIL_MAIL_IND)" /></td>
											</ods:DataSetRows>
										</table>
									</td>
								</tr>
								<tr class="ODS_Detail_Line1">

								<TD colspan="6">Motivo da troca do CEP pela rotina do correio:</TD>
								</tr>
								<tr class="ODS_Detail_Line2">
									<TD colspan="6"><html:textarea disabled="true" property="zipCodeChangeText" rows="4" cols="100"></html:textarea></TD>
								</tr>
								<tr class="fixed">
									<TD width="24%">&nbsp;</TD>
									<TD width="15%">&nbsp;</TD>
									<TD width="26%">&nbsp;</TD>
									<TD width="14%">&nbsp;</TD>
									<TD width="21%">&nbsp;</TD>
									<TD width="18%">&nbsp;</TD>
								</tr>
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
<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

