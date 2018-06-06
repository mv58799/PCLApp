<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>



<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		
		<jsp:include page="/View/General/CentrApproval/CentrApprovalExtraAction.jsp" flush="true"/>
		
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
				<jsp:param name="pageName" value="CentrApproval.CentrApprovalMovementList"/>
				<jsp:param name="gridId" value="gridTable"/>
				<jsp:param name="headerId" value="gridHeader"/>
				<jsp:param name="controlNames" value="'alterBtn','approvedBtn'"/>
				<jsp:param name="approvalControlNames" value="'','','alterBtn'"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Consulta de Aprovação Centralizada</TITLE>
	</HEAD>

	<body>
		<html:form action="/CentrApproval.CentrApprovalMovementList.List.Show.do">
			<%String curSheet = request.getParameter("currentSheet"); %>
			
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">	
			  <jsp:param name="currentSheet" value="<%= curSheet%>"/>
			</jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="CentrApproval.CentrApprovalMovementList.List.Show" style="display:none"></html:text>
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Consulta de Aprova&ccedil;&atilde;o Centralizada</th>
								</tr>
							</thead>
							<tr>
								<td>
								<table class="ODS_internalWidth" border="0">
									<tbody>
										<tr class="ODS_Detail_line1">
											<TD width="6%">Processo</TD>
											<TD width="1781">Usuário de Última Atualização</TD>											
										</tr>
										
										<tr class="ODS_Detail_line2">
											<TD width="6%">
											  <html:select property="moduleProcessText" styleClass="ODS_Select_Field_Size_30">
									            <html:option value=""></html:option>
									            <html:options
										          property="moduleProcessDomain.columnValuesByName(MODULE_PROCESS_TEXT)"
												  labelProperty="moduleProcessDomain.columnValuesByName(MODULE_PROCESS_TEXT)" />
								              </html:select>
											  
											</TD>
											<TD colspan="20"><html:text property="lastUpdUserIdSrc" styleClass="ODS_Text_Field_Size_30" maxlength="50"></html:text></TD>
											
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
											<TD width="100%">&nbsp;</TD>
											<TD width="6%">&nbsp;</TD>
											<TD align="right">&nbsp;</TD>
											<TD align="right"><html:button property="listBtn" value="Consultar" onclick="submitAction('list');"></html:button></TD>
											<TD align="left" width="44"><html:button property="clearBtn" value="Limpar" onclick="clearPage();"></html:button></TD>
										</TR>
									</tbody>
								</TABLE>
							</tr>
						</TABLE>
					</td>
				</tr>

				<tr>
					<html:hidden property="selectedCode" value="" />
					<html:hidden property="selectedModuleCode" value="" />
										
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
								
								<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
                                  String userId = user != null ? user.getUserID() : null;
                                %>		
								
								<DIV class="ODS_DivGridVerticalScroll">								
								<table id="gridTable" width="1200px"  border="0">
									<tbody>
										<tr id="gridHeader" class="fixed">
										    <TH class="ODS_header" width="3%">&nbsp;</TH>
											<TH class="ODS_header" width="3%">&nbsp;</TH>
											<TH class="ODS_header" width="23%">Processo</TH>
											<TH class="ODS_header" width="36%">Descrição do Processo</TH>											
											<TH class="ODS_header" width="13%">Usuário de Última Atualização</TH>
											<TH class="ODS_header" width="11%">Data/Hora de Última Atualização</TH>
											<TH class="ODS_header" width="7%">Ação</TH>
										</tr>
										<ods:DataSetRows name="CentrApprovalMovementListForm" property="results" dataSetRowName="resultRow" stepIndexName="step" sequenceRestartStep="2">
											<logic:equal name="step" value="0">
												<tr class="ODS_line1">
											</logic:equal>
											<logic:equal name="step" value="1">
												<tr class="ODS_line2"> 
											</logic:equal>											
											       
											       <bean:define name="resultRow" property="stringByName(LAST_UPD_USER_ID)" id="lastUpdUserIdSrc" type="java.lang.String"></bean:define>
											       <bean:define name="resultRow" property="stringByName(OPERN_CODE)" id="opernCode" type="java.lang.String"></bean:define>
											       <bean:define name="resultRow" property="stringByName(PROCESS_CODE)" id="selectCode" type="java.lang.String"></bean:define>
											       <bean:define name="resultRow" property="stringByName(MODULE_CODE)" id="selectModuleCode" type="java.lang.String"></bean:define>											       
																						    
												<td width="3%" align="center">
													 <a href="javascript:setKeys('<%=selectCode%>','<%=selectModuleCode%>');disableButtons(false); disableApproveButtons('<%= lastUpdUserIdSrc %>','<%= opernCode %>',true);submitAction('centrApproval');"/>
												      <img  title='Aprovar/Rejeitar' src='<%= request.getContextPath() %>/Common/image/GreenCircle.gif' alt="" border="0"></a>															
												</td>
												
												<logic:equal name="lastUpdUserIdSrc" value="<%=userId%>">
												  
												  <logic:notEqual name="opernCode" value="E">												    
												    <td width="3%" align="center">
													  <a href="javascript:setKeys('<%=selectCode%>','<%=selectModuleCode%>');submitAction('centrUpdate');"/>
												      <img  title='Alteração' src='<%= request.getContextPath() %>/Common/image/update.gif' alt="" border="0"></a>															
												    </td>												    												  
												  </logic:notEqual>
												  
												  <logic:equal name="opernCode" value="E">
												    <td width="3%" align="center">&nbsp;</td>												  
												  </logic:equal>												  
												  
												</logic:equal>
												<logic:notEqual name="lastUpdUserIdSrc" value="<%=userId%>"> 
												  <td width="3%" align="center">&nbsp;</td>												
												</logic:notEqual>
												
												<TD><bean:write name="resultRow" property="StringByName(MODULE_PROCESS_TEXT)"/></td>
											       <TD><bean:write name="resultRow" property="stringByName(PROCESS_TEXT)"/></td>
											       <TD><bean:write name="resultRow" property="stringByName(LAST_UPD_USER_ID)"/></td>
											       <TD align="center"><bean:write name="resultRow" property="dateByName(LAST_UPD_DATE)" formatKey='<%=com.citibank.ods.Globals.FormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM%>'/></td>
											       <TD><bean:write name="resultRow" property="stringByName(OPERN_TEXT)"/></td>
											
											    </tr>
										</ods:DataSetRows>
									</tbody>
									</div>									
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