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
		function extraActions(action){};
	 </script>
	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="AggrProdPrvt.AggrProdPrvtHistoryList"/>
		<jsp:param name="gridId" value="gridTable"/>
		<jsp:param name="headerId" value="gridHeader"/>
	</jsp:include>

	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>


		<TITLE> Consulta Histórico de Agregador</TITLE>
	</HEAD>

	<body>
		<html:form action="/AggrProdPrvt.AggrProdPrvtHistoryList.List.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Parâmetros"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="AggrProdPrvt.AggrProdPrvtList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta Histórico de Agregador</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<td colspan="5">
												<table>
													<tr class="ODS_Detail_line1">
														<td width="10%">Código</td>
														<TD width="15%"><html:text property="prvtProdAggrCodeHistSrc"
															styleClass="ODS_Text_Field_Size_10" maxlength="5"></html:text></TD>
														<TD width="14%">&nbsp;Descrição</TD>
														<TD width="23%"><html:text property="prvtProdAggrTextSrc"
															styleClass="ODS_Text_Field_Size_40" maxlength="20"></html:text></TD>
														<TD width="18%">&nbsp;Data de Referência</TD>
														<TD><html:text property="prvtProdAggrRefDate"
															styleClass="ODS_Text_Field_Size_10" onkeydown="maskDate();" maxlength="10"></html:text></TD>
							                 		</tr>
												</table>												
											</td>
										</tr>
										<tr class="ODS_Detail_line2">
											<TD width="18%">&nbsp;</TD>
											<TD width="20%">&nbsp;</TD>
											<TD width="13%">&nbsp;</TD>
											<TD width="25%">&nbsp;</TD>						
											<TD width="24%">&nbsp;</TD>
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
											<TD align="left" width="44"><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
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

											<TH class="ODS_header" width="30%">Descrição</TH>
											<TH class="ODS_header" width="10%">Código</TH>
											<TH class="ODS_header" width="15%">Data de Referência</TH>
											<TH class="ODS_header" width="20%">Usuário da Última Atualização</TH>
											<TH class="ODS_header" width="15%">Data/Hora da Última Atualização</TH>
											<TH class="ODS_header" width="10%">Status</TH>										
										</tr>
										<ods:DataSetRows name="AggrProdPrvtHistoryListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>
											<bean:define name="resultRow" property="stringByName(PRVT_PROD_AGGR_CODE)" id="PRVT_PROD_AGGR_CODE" type="java.lang.String"></bean:define>
												<TD width="30%"><bean:write name="resultRow" property="stringByName(PRVT_PROD_AGGR_TEXT)"/></td>
			 									<TD width="10%" align="right"><bean:write name="resultRow" property="stringByName(PRVT_PROD_AGGR_CODE)"/></td>	
												<TD width="15%" align="center"><bean:write name="resultRow" property="dateByName(PRVT_PROD_AGGR_REF_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>										
												<TD width="20%"><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
												<TD width="15%"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
												<TD width="10%" align="center"><bean:write name="resultRow" property="stringByName(REC_STAT_TEXT)"/></td>
											</tr>
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
										<TR class="fixed">
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
