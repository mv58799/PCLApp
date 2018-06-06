<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display"%>
<%@ page import="com.citibank.ods.common.user.User"%>
<%@ page import="com.citibank.ods.common.action.BaseAction"%>
<%@ page import="com.citibank.newcpb.enums.ScreenNamesEnum"%>
<%@ page import="com.citibank.newcpb.form.StatusCpfCnpjForm"%>

<html:html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="GENERATOR" content="IBM Software Development Platform">
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link href="<%=request.getContextPath()%>/Common/css/citi.css" rel="stylesheet" type="text/css">
		
		<jsp:include page="/NewCPB/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="StatusCpfCnpj" />
			<jsp:param name="gridId" value="gridTable" />
			<jsp:param name="headerId" value="gridHeader" />
			<jsp:param name="controlNames" value="'alterBtn','approvedBtn'" />
			<jsp:param name="approvalControlNames" value="'','','alterBtn'" />
		</jsp:include>
		<jsp:include page="/NewCPB/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		
		<title>Cliente - Atualização Status CPF/CNPJ</title>
		<script language="javascript">
			function setSelectedKeys(acctNbr) {
				document.forms[0].selectedAcctNbr.value = acctNbr;
			};
		</script>
	</head>
	<body>
		<html:form action="/NEWCPB.StatusCpfCnpj.do">
	
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="NewCPBSheet" />
				<jsp:param name="currentSubSheet" value="Clientes" />
			</jsp:include>
	
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"/>			
			<html:hidden property="clrScreen" value="true" />			
			<html:text property="idDiffList" disabled="true" style="display:none"></html:text>
			<%User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
	               String userId = user != null ? user.getUserID() : null;
	               StatusCpfCnpjForm formBean = (StatusCpfCnpjForm)session.getAttribute("StatusCpfCnpjForm");
	         %>
			<table class="ODS_mainTable" cellspacing="0" width="100%">
				<tr>
					<td>&nbsp;</td>
					<td>
						<html:hidden property="selectedAcctNbr" value="" />
						<table class="ODS_internalWidth" border="0">							
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Cliente - Atualização Status CPF/CNPJ</th>
								</tr>
							</thead>	
							<tbody>
								<tr class="ODS_Detail_line1">
									<td>
										<table width="100%">
											<tr>
												<td>
													Status
													<html:select styleClass="ODS_Select_Field_Size_10" style="margin-left: 10px;" property="selectedRegister.cpfStatus" disabled="<%=formBean.isOnlyView() %>">
														<html:option value=""></html:option>
														<html:optionsCollection  property="statusValues" label="resultDescription" value="resultCode"/>
													</html:select>
												</td>												
											</tr>
											<tr>
												<td>CPF/CNPJ</td>
											</tr>											
											<tr>
												<td style="font-size: 9px">(Digite CPF/CNPJ separadas por vírgula, limitado a 200 itens.)</td>
											</tr>
											<tr style="height:100px">
												<td >
													<html:textarea style="width: 100%; height: 100%;" property="selectedRegister.cpfCnpjNbr"  disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>
											<tr>
												<tr>
												<td>
													Mês/Ano de Atualização
													<html:text property="selectedRegister.cpfUpdMthYr"
														styleClass="ODS_Text_Field_Size_5"
														style="margin-left: 10px;"
														maxlength="7" onkeydown="maskDateMonthYear();" 
														disabled="<%=formBean.isOnlyView() %>"/>
												</td>
											</tr>											
											</tr>
											<tr>
												<td colspan="2" style="font-size: 9px">(Formato MM/YYYY)</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="ODS_Detail_Line3">
									<td>
										<table width="100%">	
											<tbody>														
												<tr>
													<td>
														<div class="ODS_DivGridVertical">
															<table id="gridTable" width="100%" border="0">
																<tbody>
																	<logic:equal name="StatusCpfCnpjForm" property="approve" value="false" >
																		<logic:equal name="StatusCpfCnpjForm" property="onlyView" value="false" >
																			<tr><td>&nbsp;</td></tr>
																			<tr>
																				<td colspan="3">
																					<table class="ODS_internalWidth" border="0">
																						<tbody>
																							<tr>
																								<td width="100%"></td>
																								<TD><html:button property="button" value="Salvar" onclick="submitActionNewCPB('save');"/></td>
																								
																								<logic:equal name="StatusCpfCnpjForm" property="fromApprove" value="false" >								
																									<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"></html:button></TD>
																								</logic:equal>	
																								
																								<logic:equal name="StatusCpfCnpjForm" property="fromApprove" value="true" >								
																									<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></TD>
																								</logic:equal>	
																							</tr>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																		</logic:equal>
																		
																		<logic:equal name="StatusCpfCnpjForm" property="onlyView" value="true" >
																			
																			<tr><td>&nbsp;</td></tr>
																			<tr>
																				<td colspan="3">
																					<table class="ODS_internalWidth" border="0">
																						<tbody>
																							<tr>
																								<td width="100%"></td>	
																								<td>	
																								<% 	Boolean fromApprove = (Boolean)session.getAttribute("fromApprove");
																								
																									if(fromApprove!=null && fromApprove){%>
																									<html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"/>
																								<%	}else{ %>
																									<html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('back');"/>
																								<%	} 														
																									session.removeAttribute("fromApprove");														
																								%>
																								</td>
																							</tr>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																		</logic:equal>
																	</logic:equal>																				
																</tbody>
															</table>
															<logic:equal name="StatusCpfCnpjForm" property="approve" value="true" >
																<tr><td colspan="3">&nbsp;</td></tr>
																<tr>
																	<td colspan="3"><img src='<%= request.getContextPath() %>/Common/image/20grey1.gif' width="100%" height="1"></td>
																</tr>
																<tr><td colspan="3">&nbsp;</td></tr>
																<tr>
																	<td colspan="3">
																		<table width="100%">
																			<tr class="ODS_Detail_Line1">
																				<td width="33%">Usuário de Última Atualização</td>
																				<td width="33%">Data/Hora de Última Atualização</td>
																				<TD width="33%">Ação</TD>
																			</tr>
																			<tr class="ODS_Detail_Line2">
																				<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectedRegister.lastUpdUserIdSafe" disabled="true"/></td>
																				<td width="33%"><html:text styleClass="ODS_Text_Field_Size_20" property="selectedRegister.lastUpdDateFormatedSafe" disabled="true"/></td>
																				<td width="33%"><html:text styleClass="ODS_Text_Field_Size_10" property="selectedRegister.recStatCodeText" disabled="true"/></TD>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="3">
																		<table class="ODS_internalWidth" border="0">
																		<bean:define id="lastUpdUserId" name ="StatusCpfCnpjForm" property="selectedRegister.lastUpdUserIdSafe" type="java.lang.String"/>
																			<tbody>
																				<tr>
																					<td width="100%"></td>		
																					<% if(user!=null && user.getUserAccess()!=null && user.getUserAccess().isHasAccessNovoCPBAprovRej(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome())){ %>								
																						<logic:equal name="StatusCpfCnpjForm" property="approve" value="true" >
																							<logic:notEqual name="lastUpdUserId" value="<%=userId%>">
																								<td><html:button property="approvedBtn" value="Aprovar" onclick="submitActionNewCPB('approve');"/></td>											
																								<td><html:button property="rejectBtn" value="Reprovar" onclick="submitActionNewCPB('reprove');"/></td>										
																							</logic:notEqual>
																						</logic:equal>
																					<% } %>
																					<td><html:button property="backBtn" value="Voltar" onclick="submitActionNewCPB('backApprove');"></html:button></td>
																				</TR>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</logic:equal>
														</div>
													</td>
												</tr>
											</tbody>	
										</table>									
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>		
		</html:form>	
		<jsp:include page="/View/Util/Footer.jsp" flush="true"/>	
	</body>	
	<jsp:include page="/View/Util/NoCacheIE.jsp" />
	<script type="text/javascript">
		
		 var input = document.getElementsByName('idDiffList')[0].value;
				
		if(input){
			var fields = input.substring(1).substring(0, input.length-2).replace(/\s/g, '').split(',');
			
			for (var i = 0; i < fields.length; i++) {
				if(document.getElementsByName('selectRegister.' + fields[i])){
					if(document.getElementsByName('selectRegister.' + fields[i])[0]){
						document.getElementsByName('selectRegister.' + fields[i])[0].classList.add("fieldDiff");
					}
					
					
					if(document.getElementById('selectRegister.' + fields[i])){
						document.getElementById('selectRegister.' + fields[i]).style.border="1px solid red";	
					}
				}
			}
		} 
		
		</script>
</html:html>