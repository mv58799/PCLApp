
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
		function extraActions(action){};
	</script>
		<jsp:include page="/View/Util/JavascriptScreenControl.jsp" flush="true">
			<jsp:param name="pageName" value="Player.PlayerDetail"/>
			<jsp:param name="gridId" value="gridTable"/>
			<jsp:param name="headerId" value="gridHeader"/>
			<jsp:param name="controlNames" value="'alterBtn','deleteBtn'"/>
			<jsp:param name="mandatoryControlNames" value="'plyrCnpjNbrSrc'"/>
			<jsp:param name="mandatoryControlLabels" value="'CNPJ'"/>
			<jsp:param name="fieldsWithMask" value="['plyrCnpjNbr','CHAR','NN.NNN.NNN/NNNN-NN','left',null],['plyrName','CHAR','^^/^^^','left',null]"/>
		</jsp:include>
		<jsp:include page="/View/Util/JavascriptMasks.jsp" flush="true"></jsp:include>
		<TITLE>Detalhe de Player</TITLE>
	</HEAD>

	<body>
		<html:form action="/Player.PlayerDetail.Consult.Show.do">
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
									<th class="subtitle" scope="colgroup" colspan="3">Detalhe de Player</th>
								</tr>
							</thead>
							<tr>
								<td>&nbsp;</td>
								<td>
								<table class="ODS_internalWidth" border="0" cellspacing="0">
									<tbody>
										<tr class="ODS_Detail_Line1">
											<TD colspan="1">CNPJ</TD>
											<td colspan="5">Nome do Player</td>
										<tr class="ODS_Detail_Line2">
											<TD colspan="1"><html:text styleClass="ODS_Text_Field_Size_15" property="plyrCnpjNbr" maxlength="18" disabled="true"></html:text></TD>
											<td colspan="5"><html:text styleClass="ODS_Text_Field_Size_60" property="plyrName" maxlength="60" disabled="true"></html:text></td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td colspan="6">Papéis</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="1" width="24%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="ADM" disabled="true"></html:multibox>Administrador</td>
											<td colspan="1" width="18%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="MNG" disabled="true"></html:multibox>Gestor</td>
											<td width="18%" colspan="1"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="CST" disabled="true"></html:multibox>Custodiante</td>
											<td width="18%" colspan="3"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="ISS" disabled="true"></html:multibox>Emissor</td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td colspan="1" width="24%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="CTL" disabled="true"></html:multibox>Controlador</td>
											<td colspan="1" width="18%"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="AUD" disabled="true"></html:multibox>Auditor</td>
											<td width="18%" colspan="4"><html:multibox styleClass="checkBox" property="plyrRoleNames" value="LIQ" disabled="true"></html:multibox>Liquidante</td>
										</tr>
										<html:hidden property="plyrRoleNames" value=""></html:hidden>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6">Endereço</TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6"><html:text styleClass="ODS_Text_Field_Size_80" property="plyrAddrText" maxlength="80" disabled="true"></html:text></TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td>Código do Player no Drive</td>
											<TD>Data Due Diligence</TD>
											<td colspan="2" width="16%">Status Due Diligence</td>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDvCode" disabled="true" maxlength="11"></html:text></td>
											<td><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDueDlgDate" disabled="true" maxlength="10"></html:text></td>
											<td width="16%" colspan="2">
												<html:select styleClass="ODS_Text_Field_Size_15" property="plyrDueDlgExecInd" disabled="true">
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
											<TD colspan="1" width="24%"><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDueDlgEndDate" disabled="true" maxlength="10"></html:text></TD>
											<td colspan="1" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="plyrInvstCmtteApprvDate" disabled="true" maxlength="10"></html:text></td>
											<td colspan="4" width="18%"><html:text styleClass="ODS_Text_Field_Size_10" property="plyrDueDlgRnwDate" disabled="true" maxlength="10"></html:text></td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<td colspan="4" width="24%">
												<table width="20%">		
													<tr>
														<td colspan="2">Mnemônico</td>
													</tr>
													<tr>														
														<td><html:text styleClass="ODS_Text_Field_Size_10" property="issueShortNameText" disabled="true"></html:text></td>
														<td><html:button property="insertMneBtn" value="Inserir Mnemônico" disabled="true"></html:button></td>
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
																			<input class="radio" type="radio" name="selection" disabled="true">
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
											<TD colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_50" property="plyrApprvRstrnText" disabled="true"></html:textarea></TD>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Serviços Prestados</TD>
										</tr>
										<tr class="ODS_Detail_Line2">
											<td colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_100" property="plyrSuplServText" disabled="true"></html:textarea></td>
										</tr>
										<tr class="ODS_Detail_Line1">
											<TD colspan="6">Observação</TD>											
										</tr>
										<tr class="ODS_Detail_Line2">
											<TD colspan="6"><html:textarea styleClass="ODS_Text_Field_Size_255" property="plyrCmntText" disabled="true"></html:textarea></TD>
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
