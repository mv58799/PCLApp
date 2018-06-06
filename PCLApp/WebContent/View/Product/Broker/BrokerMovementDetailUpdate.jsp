<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions(action){};
	</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="Broker.BrokerMovementDetail"/>
			<jsp:param name="mandatoryControlNames" value="'bkrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['bkrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Alteração de Corretora com Pendência de Aprovação </TITLE>
	</HEAD>

	<body>
		<html:form action="/Broker.BrokerMovementDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Aprovação"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Broker.BrokerMovementList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Alteração de Corretora com Pendência de Aprovação</th>
								</tr>
							</thead>
							<tr>
								<td>&nbsp;</td>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="30%">CNPJ</TD>
											<td colspan="5" width="70%">Razão social da corretora *</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="30%"><html:text styleClass="ODS_Text_Field_Size_15" property="bkrCnpjNbr" maxlength="18"></html:text></TD>
											<td colspan="5" width="70%"><html:text styleClass="ODS_Text_Field_Size_60" property="bkrNameText" maxlength="60"></html:text></td>
										</tr>


										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="24%">Código Mercado Corretora BMF</TD>
											<td colspan="2" width="18%">Código de Mercado da Corretora Bovespa</td>
											<td colspan="2" width="24%">Data Solicitação Aprovação</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrBmfMktCode" maxlength="10"></html:text></TD>
											<td colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrBovespaMktCode" maxlength="10"></html:text></td>
											<td colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrReqDate" maxlength="12" onkeydown="maskDate();"></html:text></td>
										</tr>									

										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Endereço</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6"><html:text styleClass="ODS_Text_Field_Size_100" property="bkrAddrText" maxlength="80"></html:text></TD>
										</tr>

								
										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="24%">Status Aprovação</TD>
											<td colspan="2" width="18%">Data de Aprovação</td>
											<TD colspan="2" width="24%">Data de renovação</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%">
												<html:select styleClass="ODS_Text_Field_Size_15" property="bkrApprvStatCode">
													<html:option value=""></html:option>
													<html:option value="S">Aprovado</html:option>
													<html:option value="N">Não Aprovado</html:option>									
													<html:option value="A">Em Aprovação</html:option>									
												</html:select>
											</TD>
											<td colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrApprvDate" maxlength="12" onkeydown="maskDate();"></html:text></td>
											<TD colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrRnwDate" maxlength="12" onkeydown="maskDate();"></html:text></TD>
										</tr>


										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="24%">Limite Crédito Real Solicitado</TD>
											<td colspan="2" width="18%">Limite Crédito USD Solicitado</td>
											<TD colspan="2" width="24%">Limite Crédito Real Aprovado</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrReqCrLimLcyAmt" maxlength="18" onkeyup="NumberFieldPress('15','2','FALSE',bkrReqCrLimLcyAmt)"></html:text></TD>
											<td colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrReqCrLimDlrAmt" maxlength="18" onkeyup="NumberFieldPress('15','2','FALSE',bkrReqCrLimDlrAmt)"></html:text></td>
											<td colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrApprvCrLimLcyAmt" maxlength="18" onkeyup="NumberFieldPress('15','2','FALSE',bkrApprvCrLimLcyAmt)"></html:text></td>
										</tr>								


										<tr class="ODS_Detail_Line1">
											<td colspan="1" width="24%">Limite de Crédito Dólar Aprovado</td>
											<TD colspan="2" width="18%">Percentual Repasse Bovespa</TD>
											<td colspan="2" width="24%">Percentual Repasse BMF</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrApprvCrLimDlrAmt" maxlength="18" onkeyup="NumberFieldPress('15','2','FALSE',bkrApprvCrLimDlrAmt)"></html:text></TD>
											<TD colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrRbtBovespaRate" maxlength="8" onkeyup="NumberFieldPress('4','3','FALSE',bkrRbtBovespaRate)"></html:text></TD>
											<td colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrRbtBmfRate" maxlength="8" onkeyup="NumberFieldPress('4','3','FALSE',bkrRbtBmfRate)"></html:text></td>
										</tr>


										<tr class="ODS_Detail_Line1">
											<td colspan="6">Serviços Prestados</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="6"><html:text styleClass="ODS_Text_Field_Size_100" property="bkrSuplServText" maxlength="100"></html:text></td>
										</tr>


										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Observação</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_255" property="bkrCommentText"></html:textarea></TD>
										</tr>


										<tr class="ODS_Detail_Line1">
											<td colspan="6">Andamento Processo Aprovação</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_255" property="bkrAuthProcSitText"></html:textarea></td>
										</tr>

									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<TR>
											<TD width="100%"></TD>
											<TD><html:button property="updateBtn" value="Confirmar Alteração" onclick="submitAction('update');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
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
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>
