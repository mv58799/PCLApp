
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.citibank.ods.modules.client.relationeg.form.RelationEgDetailForm"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">
		
		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions( action )
		{
		};
	 </script>		

	<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
		<jsp:param name="pageName" value="RelationEg.RelationEgDetail"/>
	</jsp:include>
	<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>

		<TITLE>Detalhe de Associação Relacionamento x EG</TITLE>
	</HEAD>

	<body>
		<html:form action="/RelationEg.RelationEgDetail.Consult.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="CustomerSheet"/>
				<jsp:param name="currentSubSheet" value="Relacionamentos" />
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>			
			<html:text property="backURL" value="RelationEg.RelationEgDetail.Consult.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Datalhe de Associação Relacionamento x EG</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<td rowspan="2" colspan="2">
												<table>
													<tr>
														<TD width="27%">ER *</TD>
														<TD width="18%">EG *</TD>
													</tr>
													<tr>
														<td width="27%">
															<html:select styleClass="ODS_Text_Field_Size_30" property="erNbr" disabled="true">
															<html:option value=""></html:option>
																	<html:options property="erNbrDomain.columnValuesByName(ER_NBR)" labelProperty="erNbrDomain.columnValuesByName(ER_NBR)" />
														</html:select></TD>
														<TD width="18%"><html:text property="egNbr" styleClass="ODS_Text_Field_Size_5" maxlength="4" disabled="true"></html:text></TD>
													</tr>
												</table>
											</td>
											<td width="45%" rowspan="2">&nbsp;</td>
										</tr>
										<tr></tr>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>

						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Relacionamentos Associados</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="fixed">
											<TH class="ODS_header" width="22%">Cliente Owner</TH>
											<TH class="ODS_header" width="22%">Cliente Agregado 1</TH>
											<TH class="ODS_header" width="22%">Cliente Agregado 2</TH>
											<TH class="ODS_header" width="10%">EG</TH>
											<TH class="ODS_header" width="22%">Nro. Relacionamento</TH>
										</tr>

										<%int rowIndex = 0;%>
										<logic:notEmpty name="RelationEgDetailForm"	property="egRelationsGrid">
											<logic:iterate name="RelationEgDetailForm"
												property="egRelationsGrid" indexId="index" id="row">
												<%
												RelationEgDetailForm relationEgDetailForm = (RelationEgDetailForm) session.getAttribute("RelationEgDetailForm");
												String[] resultLine = relationEgDetailForm.getEgRelationsGrid()[rowIndex++];
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

												<td class="alignLeft" width="22%"><%=resultLine[RelationEgDetailForm.COL_POS_CLIENT_OWNER]%></td>
												<td class="alignLeft" width="22%"><%=resultLine[RelationEgDetailForm.COL_POS_CUST_1]%></td>
												<td class="alignLeft" width="22%"><%=resultLine[RelationEgDetailForm.COL_POS_CUST_2]%></td>
												<td class="alignRight" width="10%"><%=resultLine[RelationEgDetailForm.COL_POS_EG_NBR]%></td>
												<td class="alignRight" width="22%"><%=resultLine[RelationEgDetailForm.COL_POS_RETLN_NBR]%></td>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
		
						<table class="ODS_internalWidth" border="0">
							<tbody>
								<TR>
									<TD width="100%"></TD>
									<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
								</TR>
							</tbody>
						</TABLE>
					</td>
				</tr>
			</table>						
			<jsp:include page="/View/Util/Footer.jsp" flush="true"/>		
		</html:form>
	</body>
	<jsp:include page="/View/Util/NoCacheIE.jsp"/>
</html:html>

