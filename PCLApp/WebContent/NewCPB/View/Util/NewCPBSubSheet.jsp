
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ods.tld" prefix="ods"%>
<%@page import="com.citibank.ods.common.security.AuthorizationSG"%>
<%@page import="com.citibank.ods.common.action.BaseAction"%>
<%@page import="com.citibank.ods.common.user.User"%>


	<%	
		User user = (User) session.getAttribute(BaseAction.C_USER_SESSION_ID);
		AuthorizationSG sg = new  AuthorizationSG();
		boolean hasPreviousMenu = false;
	%>

	<script type="text/javascript">
	 	var id_menu_NovoCPB =
	 	[
			<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.Customer")){
				hasPreviousMenu = true;%>
				[null, 'Cliente', null, '_id24:frmMenu', null
				 	<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.RegisterDataCustomer")){%>
				 		,[null, 'Dados cadastrais', './NEWCPB.RegisterDataCustomer?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]					
				 		,
				 	<%}%>
				 	[null, 'Cadastro de Autorizados', null, '_id24:frmMenu', null
						<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.RegisterAuthorizedSearch")){%>
							, [null, 'Consultar', './NEWCPB.RegisterAuthorized?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
						<%} if(sg.hasNEWCPBAccess(user, "/NEWCPB.RegisterAuthorizedInsert")){%>	
							, [null, 'Inserir', './NEWCPB.RegisterAuthorized?currentSheet=NewCPBSheet&method=viewInsert&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
						<%}%>
					],
					[null, 'Status CPF/CNPJ', null, '_id24:frmMenu', null
						<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.StatusCpfCnpjSearch")){%>
							, [null, 'Consultar', './NEWCPB.StatusCpfCnpj?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
						<%} if(sg.hasNEWCPBAccess(user, "/NEWCPB.StatusCpfCnpjUpdate")){%>	
							, [null, 'Atualizar', './NEWCPB.StatusCpfCnpj?currentSheet=NewCPBSheet&method=viewUpdate&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
						<%}%>
					],
					[null, 'Documentos', null, '_id24:frmMenu', null
						<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.DocumentsSearch")){%>
							, [null, 'Consultar', './NEWCPB.Documents?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
						<%} if(sg.hasNEWCPBAccess(user, "/NEWCPB.DocumentsInsert")){%>	
							, [null, 'Inserir', './NEWCPB.Documents?currentSheet=NewCPBSheet&method=viewInsert&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
						<%}%>
					]
				]

				
			<%} if(sg.hasNEWCPBAccess(user, "/NEWCPB.Account")){				
				if(hasPreviousMenu){%>
				,
				_cmSplit,
				<%}  hasPreviousMenu = true;%>
				[null, 'Conta', null, '_id24:frmMenu', null	
						,[null, 'Dados Complementares', './NEWCPB.AccountComplementData?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
														
						<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.AssociationAccountSearch")){%>
						,[null, 'Consulta Contas x EG', './NEWCPB.AssociationAccount?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
						<%}%>
						
						,[null, 'Questionário K&E', null, '_id24:frmMenu', null
							<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.AssociationAccountSearch")){%>
								, [null, 'Cliente', './NEWCPB.QuestionsKe?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
							<%} if(sg.hasNEWCPBAccess(user, "/NEWCPB.AssociationAccountSearch")){%>	
								,[null, 'Procurador/Autorizado', './NEWCPB.QuestionsKeAuthorized?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
							<%}%>
						]

						,[null, 'Migradas (De-Para) ', null, '_id24:frmMenu', null
							<%if(sg.hasNEWCPBAccess(user, "/NEWCPB.AccountMigrationSearch")){%>
								, [null, 'Consultar', './NEWCPB.AccountMigration?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
							<%} if(sg.hasNEWCPBAccess(user, "/NEWCPB.AccountMigrationInsert")){%>	
								, [null, 'Inserir', './NEWCPB.AccountMigration?currentSheet=NewCPBSheet&method=viewInsert&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
							<%}%>
						]
				] 


			
			<%} if(sg.hasNEWCPBAccess(user, "/NEWCPB.CentralApproval")){	
				
				if(hasPreviousMenu){%>
				,
				_cmSplit,
				<%}  hasPreviousMenu = true;%>
				[null, 'Aprovação', null, '_id24:frmMenu', null	
						,[null, 'Aprovação centralizada', './NEWCPB.CentralApproval?currentSheet=NewCPBSheet&urlDistinguisher=' + '<%= new java.util.Date().getTime()%>', '_self', null]
				]
				
			<% }%>
		];
	</script><div id="id_menu_NovoCPB"></div>

	<script type="text/javascript">	cmDraw ('id_menu_NovoCPB', id_menu_NovoCPB, 'hbr', cmThemeOffice, 'ODS_ThemeOffice');</script>
