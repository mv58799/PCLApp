package com.citibank.newcpb.security;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.citibank.latam.sgway.util.RecordList;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.security.connector.SecurityGatewayFactory;
import com.citibank.ods.common.security.connector.SecurityGatewayInterface;
import com.citibank.ods.common.user.User;

public class SecurityUtil {
	
	public User initUserNewCPB(ApplicationLogger applicationLogger, User user, String systemID, String isMock) throws Exception {
		applicationLogger.info("##### initUser NEW CPB - BEGIN");

		ArrayList<Integer> functionListFromSG = new ArrayList<Integer>();
		
		InputStream f = null;
		Properties p = new Properties();
		f = this.getClass().getClassLoader().getResourceAsStream("SG.properties");
		p.load(f);

		if (!"true".equals(isMock)) {

			applicationLogger.info("====================== FUNCOES DO USUARIO NEW CPB - SG ON ======================");
			applicationLogger.info("========= USER ID: " + user.getUserID());
			applicationLogger.info("========= USER ipAddress: " + user.getIpAddress());
			applicationLogger.info("========= USER: sessionSpecs: " + user.getSessionSpecs());
			applicationLogger.info("========= USER: systemId: " + systemID);
			applicationLogger.info("========= USER FIRST_NAME: " + user.getFirstName());
			applicationLogger.info("========= USER LAST_NAME: " + user.getLastName());

			if (functionListFromSG != null && functionListFromSG.size() == 0) {

				// Recupera a lista de grupos do usuário
				ArrayList<Integer> functionListFromUser = getFunctionListFromUser(applicationLogger, user, systemID);

				if (functionListFromUser != null) {
					functionListFromSG.addAll(functionListFromUser);
				}
			}

		// Caso o SG esteja "isMock" no propeties, ele seta os perfis abaixo no User.
		} else {

			applicationLogger.info("====================== FUNCOES DO USUARIO - SG OFF ======================");
			applicationLogger.info("========= USER ID: " + user.getUserID());
			applicationLogger.info("========= USER ipAddress: " + user.getIpAddress());
			applicationLogger.info("========= USER: sessionSpecs: " + user.getSessionSpecs());
			applicationLogger.info("========= USER: systemId: " + systemID);
			applicationLogger.info("========= USER FIRST_NAME: " + user.getFirstName());
			applicationLogger.info("========= USER LAST_NAME: " + user.getLastName());

			functionListFromSG.add(new Integer(p.getProperty("MENU_NOVO_CPB").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_BANKER").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_DADOS_CADASTRAIS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_ATUALIZA_DADOS_CADASTRAIS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_DADOS_CADASTRAIS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_CADASTRO_DE_AUTORIZADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_ATUALIZA_CADASTRO_DE_AUTORIZADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_DELETA_CADASTRO_DE_AUTORIZADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_CADASTRO_DE_AUTORIZADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_STATUS_CPF_CNPJ").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_ATUALIZAR_STATUS_CPF_CNPJ").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_DELETA_STATUS_CPF_CNPJ").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_STATUS_CPF_CNPJ").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_DOCUMENTOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_INSERE_DOCUMENTOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_CONSULTA_CADASTRO_DE_RISCO").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_INSERE_RISCO").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_ATUALIZA_RISCO").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_DELETA_RISCO").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_APROVACAO_REJEICAO_RISCO").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_CONSULTA_ASSOCIACAO_DE_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_INSERE_ASSOCIACAO_DE_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_ATUALIZA_ASSOCIACAO_DE_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_DELETA_ASSOCIACAO_DE_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_EG_APROVACAO_REJEICAO_ASSOCIACAO_DE_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_CONSULTA_DADOS_COMPLEMENTARES_DA_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_ATUALIZA_DADOS_COMPLEMENTARES_DA_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_APROVACAO_REJEICAO_DADOS_COMPLEMENTARES_DA_CONTA").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_CONSULTA_CONTAS_MIGRADAS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_INSERE_CONTAS_MIGRADAS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_DELETA_CONTAS_MIGRADAS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_CONTAS_MIGRADAS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_CENTRALIZADA").trim()));			
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_BANKER_CONSULTA_DADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_BANKER_ATUALIZA_DADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_BANKER_INSERE_DADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_BANKER_DELETA_DADOS").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_BANKER_APROVACAO_REJEICAO").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CUSTOMER_CPB").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_CONSULTA_QUESTIONARIO_KE").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_INSERE_ALTERA_QUESTIONARIO_KE").trim()));
			functionListFromSG.add(new Integer(p.getProperty("NOVO_CPB_CONTA_APROVACAO_REJEICAO_QUESTIONARIO_KE").trim()));
			
		
			

			for (Integer item : functionListFromSG) {
				applicationLogger.info("========= FUNCTION ID: " + item);
			}
		}
		
		user.setUserAccess(setAccessInUserByFunctionList(p, functionListFromSG));
		
		applicationLogger.info("##### initUser NEW CPB - END");
		
		return user;
	}
  
	public ArrayList<Integer> getFunctionListFromUser(ApplicationLogger applicationLogger, User user, String systemID) throws Exception {

		Map<Integer, SGModuleBean> userModules = getSystemModulesAndFunctions(applicationLogger, user.getUserID(), user.getIpAddress(), user.getSessionSpecs(), systemID);

		ArrayList<Integer> functions = new ArrayList<Integer>();

		if (userModules != null) {

			for (SGModuleBean module : userModules.values()) {

				if (module.getFunctions() != null) {

					for (SGFunctionBean fun : module.getFunctions().values()) {
						functions.add(fun.getId());						
					}
				}
			}
		}
		return functions;
	}
	
	public Map<Integer, SGModuleBean> getSystemModulesAndFunctions(ApplicationLogger applicationLogger, String userID, String ipAddress, String sessionSpecs, String systemID) throws NumberFormatException, Exception {

		Map<Integer, SGModuleBean> modules = new HashMap<Integer, SGModuleBean>();

		RecordList list;
		SecurityGatewayInterface legacyConnector = SecurityGatewayFactory.getSecurityGatewayLegacyConnector();

		list = legacyConnector.getSystemModulesAndFunctions(userID, ipAddress, sessionSpecs, Integer.parseInt( systemID ));

		if (list != null && list.getRecordCount() > 0) {
			applicationLogger.info("====================== RETORNO DO SG ======================");
			for (int i = 0; i < list.getRecordCount(); i++) {

				SGModuleBean sgModuleBean = (SGModuleBean) modules.get(new Integer(list.getInt(i, "MODULE_ID")));

				if (sgModuleBean == null) {

					sgModuleBean = new SGModuleBean();

					sgModuleBean.setId(new Integer(list.getInt(i, "MODULE_ID")));

					sgModuleBean.setName(list.getString(i, "MODULE_NAME"));

					modules.put(sgModuleBean.getId(), sgModuleBean);

					applicationLogger.info("========= MODULO: ID: " + sgModuleBean.getId() + " NAME: " + sgModuleBean.getName());
				}

				SGFunctionBean function = new SGFunctionBean();
				function.setId(list.getInt(i, "FUNCTION_ID"));
				function.setName(list.getString(i, "FUNCTION_NAME"));

				applicationLogger.info("========= FUNCTION: ID: " + function.getId() + " NAME: " + function.getName());

				sgModuleBean.addFunction(function);
			}
		}
		return modules;
	}
	
	public UserAccessBean setAccessInUserByFunctionList(Properties p, ArrayList<Integer> functionList) {
		
		UserAccessBean userAccessBean = new UserAccessBean();
		
		for (Integer function : functionList ) {
			if (function.equals(new Integer(p.getProperty("MENU_NOVO_CPB").trim()))){
				userAccessBean.setHasAccessMenuNovoCPB(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE").trim()))){
            	userAccessBean.setHasAccessNovoCPBCli(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG").trim()))){
            	userAccessBean.setHasAccessNovoCPBEG(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_BANKER").trim()))){
            	userAccessBean.setHasAccessNovoCPBBanker(Boolean.TRUE);	
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_BANKER_ATUALIZA_DADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBBankerAtual(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_BANKER_DELETA_DADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBBankerDel(Boolean.TRUE); 	
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_BANKER_INSERE_DADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBBankerinsert(Boolean.TRUE);	
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_BANKER_CONSULTA_DADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBBankerCons(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_BANKER_APROVACAO_REJEICAO").trim()))){
            	userAccessBean.setHasAccessNovoCPBBankerAprovRej(Boolean.TRUE);           	
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_DADOS_CADASTRAIS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliConsDadosCad(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_ATUALIZA_DADOS_CADASTRAIS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliAtualDadosCad(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_DADOS_CADASTRAIS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliAprovRejDadosCad(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_CADASTRO_DE_AUTORIZADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliConsCadAut(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_ATUALIZA_CADASTRO_DE_AUTORIZADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliAtualCadAut(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_DELETA_CADASTRO_DE_AUTORIZADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliDelCadAut(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_CADASTRO_DE_AUTORIZADOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliAprovRejCadAut(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_STATUS_CPF_CNPJ").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliConsStatusCPFCNPJ(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_ATUALIZAR_STATUS_CPF_CNPJ").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliAtualrStatusCPFCNPJ(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_DELETA_STATUS_CPF_CNPJ").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliDelStatusCPFCNPJ(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_STATUS_CPF_CNPJ").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliAprovRejStatusCPFCNPJ(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_CONSULTA_DOCUMENTOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliConsDocuments(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_INSERE_DOCUMENTOS").trim()))){
            	userAccessBean.setHasAccessNovoCPBCliInsDocuments(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_CONSULTA_CADASTRO_DE_RISCO").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGConsCadRisco(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_INSERE_RISCO").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGInsRisco(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_ATUALIZA_RISCO").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGAtualRisco(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_DELETA_RISCO").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGDelRisco(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_APROVACAO_REJEICAO_RISCO").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGAprovRejRisco(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_CONSULTA_ASSOCIACAO_DE_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGConsAssoConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_INSERE_ASSOCIACAO_DE_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGInsAssoConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_ATUALIZA_ASSOCIACAO_DE_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGAtualAssoConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_DELETA_ASSOCIACAO_DE_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGDelAssoConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_EG_APROVACAO_REJEICAO_ASSOCIACAO_DE_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBEGAprovRejAssoConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_CONSULTA_DADOS_COMPLEMENTARES_DA_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBContaConsDadosCompdaConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_ATUALIZA_DADOS_COMPLEMENTARES_DA_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBContaAtualDadosCompdaConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_APROVACAO_REJEICAO_DADOS_COMPLEMENTARES_DA_CONTA").trim()))){
            	userAccessBean.setHasAccessNovoCPBContaAprovRejDadosCompdaConta(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_CONSULTA_CONTAS_MIGRADAS").trim()))){
            	userAccessBean.setHasAccessNovoCPBContaConsContasMigradas(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_INSERE_CONTAS_MIGRADAS").trim()))){
            	userAccessBean.setHasAccessNovoCPBContaInsContasMigradas(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_DELETA_CONTAS_MIGRADAS").trim()))){
            	userAccessBean.setHasAccessNovoCPBContaDelContasMigradas(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_REJEICAO_CONTAS_MIGRADAS").trim()))){
            	userAccessBean.setHasAccessNovoCPBContaAprovRejContasMigradas(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CLIENTE_APROVACAO_CENTRALIZADA").trim()))){
            	userAccessBean.setHasAccessNovoCPBAprovacaoCentralizada(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CUSTOMER_CPB").trim()))){
            	userAccessBean.setHasAccessNovoCPBCustomerCPB(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_CONSULTA_QUESTIONARIO_KE").trim()))){
            	userAccessBean.setHasAccessNovoCPBQuestionsKeCons(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_INSERE_ALTERA_QUESTIONARIO_KE").trim()))){
            	userAccessBean.setHasAccessNovoCPBQuestionsKeAtual(Boolean.TRUE);
            }else if (function.equals(new Integer(p.getProperty("NOVO_CPB_CONTA_APROVACAO_REJEICAO_QUESTIONARIO_KE").trim()))){
            	userAccessBean.setHasAccessNovoCPBQuestionsKeAprovRej(Boolean.TRUE);
            }
			
			
        }

		return userAccessBean;
	}
}