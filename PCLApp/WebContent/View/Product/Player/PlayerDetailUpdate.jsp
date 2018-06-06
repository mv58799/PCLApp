
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
	<HEAD><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM Software Development Platform">
		<META http-equiv="Content-Style-Type" content="text/css">

		<LINK href="<%= request.getContextPath() %>/Common/css/citi.css" rel="stylesheet" type="text/css">

	<script language="javascript">
		function extraActions(action){ 
			if ( action == 'insertIssue' ) {
				document.forms[0].action = "Player.PlayerDetail.Update.InsertIssue"; 
				document.forms[0].backURL.disabled = true; 
			}else if ( action == 'deleteIssue' ) {
				document.forms[0].action = "Player.PlayerDetail.Update.DeleteIssue"; 
				document.forms[0].backURL.disabled = true; 
			}
		}
	 </script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="Player.PlayerDetail"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','deleteBtn','deleteMneBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'plyrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['plyrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Alteração de Player</TITLE>
	</HEAD>

	<body>
		<html:form action="/Player.PlayerDetail.Update.Show.do">
			<jsp:include page="/View/Util/InitialPage.jsp" flush="true">
				<jsp:param name="currentSheet" value="ProductSheet"/>
				<jsp:param name="currentSubSheet" value="Players"/>
		    </jsp:include>
			<jsp:include page="/View/Util/MessageControl.jsp" flush="true"></jsp:include>
			<html:text property="backURL" value="Player.PlayerList.List.Show" style="display:none"></html:text> 
			<table class="ODS_mainTable" cellspacing="0">
				<tr>
					<td>
						<table class="ODS_internalWidth" border="0">
							<thead>
								<tr>
									<th class="subtitle" scope="colgroup" colspan="3">Alteração de Player</th>
								</tr>
							</thead>
							<tr>
								<td>&nbsp;</td>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD colspan="1">CNPJ</TD>
											<td colspan="5">Nome do Player *</td>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1"><html:text styleClass="ODS_Text_Field_Size_15" property="plyrCnpjNbr" maxlength="18" disabled="true" onkeyup="MaskFieldPress('CHAR','NN.NNN.NNN/NNNN-NN','left',null)"></html:text></TD>
											<td colspan="5"><html:text styleClass="ODS_Text_Field_Size_60" property="plyrName" maxlength="60"></html:text></td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td colspan="6">Papéis *</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="1" width="24%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="ADM"></html:multibox>Administrador</td>
											<td colspan="1" width="18%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="MNG"></html:multibox>Gestor</td>
											<td width="18%" colspan="1"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="CST"></html:multibox>Custodiante</td>
											<td width="18%" colspan="3"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="ISS"></html:multibox>Emissor</td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td colspan="1" width="24%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="CTL"></html:multibox>Controlador</td>
											<td colspan="1" width="18%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="AUD"></html:multibox>Auditor</td>
											<td width="18%" colspan="4"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="LIQ"></html:multibox>Liquidante</td>
										</tr>
										<html:hidden property="plyrRoleNames" value=""></html:hidden>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6">Endereço</TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6"><html:text styleClass="ODS_Text_Field_Size_80" property="plyrAddrText" maxlength="80"></html:text></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td>Código do Player no Drive</td>
											<TD>Data Due Diligence</TD>
											<td colspan="2" width="16%">Status Due Diligence</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDvCode" disabled="true" maxlength="11"></html:text></td>
											<td><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDueDlgDate" maxlength="10" onkeydown="maskDate();"></html:text></td>
											<td width="16%" colspan="2">
												<html:select styleClass="ODS_Text_Field_Size_15" property="plyrDueDlgExecInd">
													<html:option value=""></html:option>
													<html:option value="S">Concluído</html:option>
													<html:option value="N">Não Concluído</html:option>									
												</html:select>
											</td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td colspan="1" width="24%">Data Conclusão Due Diligence</td>
											<td colspan="1" width="18%">Data Aprovação Comite</td>
											<td colspan="2" width="18%">Data Renovação Due Diligence</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDueDlgEndDate" maxlength="10" onkeydown="maskDate();"></html:text></TD>
											<td colspan="1" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="plyrInvstCmtteApprvDate" maxlength="10" onkeydown="maskDate();"></html:text></td>
											<td colspan="4" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDueDlgRnwDate" maxlength="10" onkeydown="maskDate();"></html:text></td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td colspan="4" width="24%">
												<table>													
													<tr>
														<td colspan="2">Mnemônico</td>
													</tr>
													<tr>														
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="issueShortNameText" style="text-transform: uppercase" maxlength="20"></html:text></td>
														<td><html:button property="insertMneBtn" value="Inserir Mnemônico" onclick="submitAction('insertIssue');"></html:button></td>
													</tr>
													<tr>
														<th colspan="2" class="ODS_header">Mnemônico</th>														
													</tr>
													<logic:iterate name="PlayerDetailForm" property="issueShortNameList" indexId="index" id="item">
														<tr>
															<td colspan="2">
																<table>
																	<tr>
																		<td width="2%">
																			<input class="radio" type="radio" name="selection" style="cursor: pointer;" onclick="javascript:shortNameIdx.value='<%=index%>';disableButtons(false);">
																		</td>
																		<td>
																			<bean:write name="item" property="issueShortName" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</logic:iterate>
													<tr>
														<td colspan="2" align="right">
															<html:button property="deleteMneBtn" value="Excluir Mnemônico" disabled="true" onclick="submitAction('deleteIssue');" style="cursor: pointer;"></html:button>
														</td>
													</tr>
													<html:hidden property="shortNameIdx" value="0" />
												</table>
											</td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Restrição sobre Aprovação</TD>

										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_50" property="plyrApprvRstrnText"></html:textarea></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Serviços Prestados</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_100" property="plyrSuplServText"></html:textarea></td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Observação</TD>											
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_255" property="plyrCmntText"></html:textarea></TD>
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
											<TD width="100%">&nbsp;</TD>
											<TD><html:button property="updateBtn" value="Confirmar Alteração" onclick="submitAction('update');"></html:button></TD>
											<TD><html:button property="clearBtn" value="Limpar" onclick="submitAction('clear');"></html:button></TD>
											<TD><html:button property="backBtn" value="Voltar" onclick="submitAction('back');"></html:button></TD>
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
