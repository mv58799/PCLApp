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
			<jsp:param name="pageName" value="Broker.BrokerDetail"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','deleteBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'bkrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['bkrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null],['plyrName','CHAR','^^/^^^','left',null]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE> Detalhe de Corretoras </TITLE>
	</HEAD>

	<body>
		<html:form action="/Broker.BrokerDetail.Consult.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Broker"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Broker.BrokerList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Corretora</th>
								</tr>
							</thead>
							<tr>
								<td>&nbsp;</td>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="30%">CNPJ</TD>
											<td colspan="5" width="70%">Raz�o social da corretora</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="30%"><html:text styleClass="ODS_Text_Field_Size_15" property="bkrCnpjNbr" maxlength="18" disabled="true"></html:text></TD>
											<td colspan="5" width="70%"><html:text styleClass="ODS_Text_Field_Size_60" property="bkrNameText" maxlength="60" disabled="true"></html:text></td>
										</tr>

										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="24%">C�digo Mercado Corretora BMF</TD>
											<td colspan="2" width="18%">C�digo de Mercado da Corretora Bovespa</td>
											<td colspan="2" width="24%">Data Solicita��o Aprova��o</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrBmfMktCode" maxlength="10" disabled="true"></html:text></TD>
											<td colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrBovespaMktCode" maxlength="10" disabled="true"></html:text></td>
											<td colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrReqDate" maxlength="10" disabled="true"></html:text></td>
										</tr>									

										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Endere�o</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6"><html:text styleClass="ODS_Text_Field_Size_100" property="bkrAddrText" maxlength="80" disabled="true"></html:text></TD>
										</tr>

								
										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="24%">Status Aprova��o</TD>
											<td colspan="2" width="18%">Data de Aprova��o</td>
											<TD colspan="2" width="24%">Data de renova��o</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%">
												<html:select styleClass="ODS_Text_Field_Size_15" property="bkrApprvStatCode" disabled="true">
													<html:option value=""></html:option>
													<html:option value="S">Aprovado</html:option>
													<html:option value="N">N�o Aprovado</html:option>									
													<html:option value="A">Em Aprova��o</html:option>									
												</html:select>
											</TD>
											<td colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrApprvDate" maxlength="10" disabled="true"></html:text></td>
											<TD colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrRnwDate" maxlength="10" disabled="true"></html:text></TD>
										</tr>


										<tr class="ODS_Detail_Line1">
											<TD colspan="1" width="24%">Limite Cr�dito Real Solicitado</TD>
											<td colspan="2" width="18%">Limite Cr�dito USD Solicitado</td>
											<TD colspan="2" width="24%">Limite Cr�dito Real Aprovado</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrReqCrLimLcyAmt" maxlength="20" disabled="true"></html:text></TD>
											<td colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrReqCrLimDlrAmt" maxlength="20" disabled="true"></html:text></td>
											<td colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrApprvCrLimDlrAmt" maxlength="20" disabled="true"></html:text></td>
										</tr>								


										<tr class="ODS_Detail_Line1">
											<td colspan="1" width="24%">Limite de Cr�dito D�lar Aprovado</td>
											<TD colspan="2" width="18%">Percentual Repasse Bovespa</TD>
											<td colspan="2" width="24%">Percentual Repasse BMF</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_20" property="bkrApprvCrLimLcyAmt" maxlength="20" disabled="true"></html:text></TD>
											<TD colspan="2" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrRbtBovespaRate" maxlength="10" disabled="true"></html:text></TD>
											<td colspan="2" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="bkrRbtBmfRate" maxlength="10" disabled="true"></html:text></td>
										</tr>


										<tr class="ODS_Detail_Line1">
											<td colspan="6">Servi�os Prestados</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="6"><html:text styleClass="ODS_Text_Field_Size_100" property="bkrSuplServText" maxlength="100" disabled="true"></html:text></td>
										</tr>


										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Observa��o</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_255" property="bkrCommentText" disabled="true"></html:textarea></TD>
										</tr>


										<tr class="ODS_Detail_Line1">
											<td colspan="6">Andamento Processo Aprova��o</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_255" property="bkrAuthProcSitText" disabled="true"></html:textarea></td>
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
