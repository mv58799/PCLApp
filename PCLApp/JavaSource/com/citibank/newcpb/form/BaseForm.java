package com.citibank.newcpb.form;

import java.io.Serializable;
import java.util.Locale;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.citibank.ods.common.user.User;

public class BaseForm extends ActionForm implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	public static final String C_MESSAGE_SUCESS = "SUCESS";	
	public static final String C_ERROR_EMPTY_FILTER = "ERROR_EMPTY_FILTER";
	public static final String C_ERROR_REGISTER_BLOCK = "ERROR_REGISTER_BLOCK";
	public static final String C_ERROR = "ERROR";
	public static final String C_NO_REGISTER_FOUND = "NO_REGISTER_FOUND";	  
	public static final String C_ERROR_GENERIC = "ERROR_GENERIC";
	public static final String C_FORMAT_CNPJ_NBR = "'NN.NNN.NNN/NNNN-NN'";
	public static final String C_FORMAT_CPF_NBR = "'NNN.NNN.NNN-NN'";
	public static final String C_FORMAT_PHONE_NBR = "NNNN-NNNN";
	
	public static final String C_ERROR_EMPTY_DATE_SIGNATURE_W8 = "ERROR_EMPTY_DATE_SIGNATURE_W8";
	public static final String C_ERROR_EMPTY_CUSTOMER_TYPE = "ERROR_EMPTY_CUSTOMER_TYPE";
	public static final String C_ERROR_EMPTY_CUSTOMER_STATUS = "ERROR_EMPTY_CUSTOMER_STATUS";
	public static final String C_ERROR_EMPTY_CUSTOMER_NAME = "ERROR_EMPTY_CUSTOMER_NAME";
	public static final String C_ERROR_EMPTY_CUSTOMER_CPF = "ERROR_EMPTY_CUSTOMER_CPF";
	public static final String C_ERROR_EMPTY_CUSTOMER_CNPJ = "ERROR_EMPTY_CUSTOMER_CNPJ";
	public static final String C_ERROR_EMPTY_CUSTOMER_GFCID = "ERROR_EMPTY_CUSTOMER_GFCID";
	public static final String C_ERROR_EMPTY_CUSTOMER_EM = "ERROR_EMPTY_CUSTOMER_EM";
	public static final String C_ERROR_EMPTY_CUSTOMER_ER = "ERROR_EMPTY_CUSTOMER_ER";
	public static final String C_ERROR_EMPTY_CUSTOMER_ROLE = "ERROR_EMPTY_CUSTOMER_ROLE";
	
	public static final String C_ERROR_NO_REGISTER_ACCOUNT_ASSOCIATION = "ERROR_NO_REGISTER_ACCOUNT_ASSOCIATION";
	
	
	
	public static final String C_ERROR_INVALID_DATE_BIRTH = "ERROR_INVALID_DATE_BIRTH";
	public static final String C_ERROR_INVALID_DATE_DOC_EMIT = "ERROR_INVALID_DATE_DOC_EMIT";
	public static final String C_ERROR_INVALID_DATE_SIGNATURE_W8 = "ERROR_INVALID_DATE_SIGNATURE_W8";
	public static final String C_ERROR_INVALID_DATE_SIGNATURE_CRS = "ERROR_INVALID_DATE_SIGNATURE_CRS";
	public static final String C_ERROR_INVALID_DATE_FUND = "ERROR_INVALID_DATE_FUND";
	public static final String C_ERROR_INVALID_CPF_ADM = "ERROR_INVALID_CPF_ADM";
	public static final String C_ERROR_INVALID_CPF_CUSTOMER = "ERROR_INVALID_CPF_CUSTOMER";
	public static final String C_ERROR_INVALID_CNPJ_CUSTOMER = "ERROR_INVALID_CNPJ_CUSTOMER";

	public static final String C_ERROR_ER_NOT_EXIST = "ERROR_ER_NOT_EXIST";	
	public static final String C_ERROR_EMPTY_EG = "ERROR_EMPTY_EG";
	public static final String C_ERROR_EMPTY_ER = "ERROR_EMPTY_ER";
	public static final String C_ERROR_EMPTY_RISK_RISK = "ERROR_EMPTY_RISK_RISK";
	public static final String C_ERROR_EMPTY_RISK_DATE_IOS = "ERROR_EMPTY_RISK_DATE_IOS";
	public static final String C_ERROR_EMPTY_ACCOUNT_NUMBER = "ERROR_EMPTY_ACCOUNT_NUMBER";
	public static final String C_ERROR_EMPTY_EMPLOYEE_SOEID = "ERROR_EMPTY_EMPLOYEE_SOEID";
	
	
	public static final String C_ERROR_MAX_LENGTH_ER = "ERROR_MAX_LENGTH_ER";
	public static final String C_ERROR_MAX_LENGTH_EG = "ERROR_MAX_LENGTH_EG";
	public static final String C_ERROR_MAX_LENGTH_ACCOUNT_NUMBER = "ERROR_MAX_LENGTH_ACCOUNT_NUMBER";
	public static final String C_ERROR_ACCOUNT_NUMBER_INVALID = "ERROR_ACCOUNT_NUMBER_INVALID";
	public static final String C_ERROR_MAX_LENGTH_EMPLOYEE_SOEID = "ERROR_MAX_LENGTH_EMPLOYEE_SOEID";
	
	public static final String C_ERROR_NUMBER_EG_NON_FIND_NUMBER_ER = "ERROR_NUMBER_EG_NON_FIND_NUMBER_ER";
	public static final String C_ERROR_NUMBER_ACCT_NON_FIND = "ERROR_NUMBER_ACCT_NON_FIND";
	public static final String C_ERROR_NUMBER_ACCT_ALREADY_ASSOCIATION = "ERROR_NUMBER_ACCT_ALREADY_ASSOCIATION";
	
	public static final String C_REGISTER_NOT_FOUND = "REGISTER_NOT_FOUND";

	public static final String C_ERROR_ONLY_SAME_EG_ASSOCIATION = "ERROR_ONLY_SAME_EG_ASSOCIATION";
	public static final String C_ERROR_NUMBER_ACCT_ALREADY_ASSOCIATION_IN_SESSION = "ERROR_NUMBER_ACCT_ALREADY_ASSOCIATION_IN_SESSION";	
	public static final String C_ERROR_EMPTY = "ERROR_EMPTY";	
	public static final String C_ERROR_DELETE_EG = "ERROR_DELETE_EG";
	public static final String C_CANNOT_UPDATE_REGISTER_IN_MOVEMENT = "CANNOT_UPDATE_REGISTER_IN_MOVEMENT";
	public static final String CANNOT_DELETE_REGISTER_IN_MOVEMENT = "CANNOT_DELETE_REGISTER_IN_MOVEMENT";
	
	public static final String C_ERROR_AUTH_ACCOUNT_NUMBER_INVALID_LIST = "ERROR_AUTH_ACCOUNT_NUMBER_INVALID_LIST";	
	public static final String C_ERROR_AUTH_EMPTY_ACCOUNT_NUMBER_OUT = "ERROR_AUTH_EMPTY_ACCOUNT_NUMBER_OUT";
	public static final String C_ERROR_AUTH_EMPTY_EM = "ERROR_AUTH_EMPTY_EM";
	public static final String C_ERROR_AUTH_EMPTY_ACCOUNT_LIST = "ERROR_AUTH_EMPTY_ACCOUNT_LIST";
	public static final String C_ERROR_AUTH_EMPTY_CPF_CNPJ = "ERROR_AUTH_EMPTY_CPF_CNPJ";
	public static final String C_ERROR_AUTH_EMPTY_NAME = "ERROR_AUTH_EMPTY_NAME";
	public static final String C_ERROR_AUTH_INVALID_CPF_CNPJ = "ERROR_AUTH_INVALID_CPF_CNPJ";
	public static final String C_ERROR_AUTH_EMPTY_DATE_BIRTH = "ERROR_AUTH_EMPTY_DATE_BIRTH";
	public static final String C_ERROR_AUTH_ACCOUNT_NUMBER_ALREADY = "ERROR_AUTH_ACCOUNT_NUMBER_ALREADY";
	public static final String C_ERROR_AUTH_INVALID_EM = "ERROR_AUTH_INVALID_EM";
	
	public static final String C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_STATUS = "ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_STATUS";
	public static final String C_ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_LIST = "ERROR_STATUS_CPF_CNPJ_EMPTY_CPF_LIST";
	public static final String C_ERROR_STATUS_CPF_CNPJ_EMPTY_MONTH_YEAR = "ERROR_STATUS_CPF_CNPJ_EMPTY_MONTH_YEAR";
	public static final String C_ERROR_STATUS_CPF_CNPJ_LIST_MAX = "ERROR_STATUS_CPF_CNPJ_LIST_MAX";
	public static final String C_ERROR_STATUS_CPF_CNPJ_INVALID_MONTH_YEAR = "ERROR_STATUS_CPF_CNPJ_INVALID_MONTH_YEAR";
	public static final String C_CANNOT_UPDATE_REGISTER_IN_MOVEMENT_STATUS_CPF = "CANNOT_UPDATE_REGISTER_IN_MOVEMENT_STATUS_CPF";	
	public static final String C_ERROR_STATUS_CPF_CNPJ_INVALID = "ERROR_STATUS_CPF_CNPJ_INVALID";
	
	public static final String C_ERROR_ACCOUNT_COMPL_EMPTY_ACCT_NBR = "ERROR_ACCOUNT_COMPL_EMPTY_ACCT_NBR";
	public static final String C_ERROR_ACCOUNT_COMPL_EMPTY_TYPE = "ERROR_ACCOUNT_COMPL_EMPTY_TYPE";
	public static final String C_ERROR_ACCOUNT_COMPL_EMPTY_CPF_CNPJ = "ERROR_ACCOUNT_COMPL_EMPTY_CPF_CNPJ";
	public static final String C_ERROR_ACCOUNT_INVALID_DATE_OPEN_ACCOUNT = "ERROR_ACCOUNT_INVALID_DATE_OPEN_ACCOUNT";
	public static final String C_ERROR_ACCOUNT_INVALID_DATE_END_ACCOUNT = "ERROR_ACCOUNT_INVALID_DATE_END_ACCOUNT";
	public static final String C_ERROR_ACCOUNT_INVALID_DATE_ASS_ACCOUNT = "ERROR_ACCOUNT_INVALID_DATE_ASS_ACCOUNT";
	public static final String C_ERROR_ACCOUNT_INVALID_DATE_IOS_LAST_REV = "ERROR_ACCOUNT_INVALID_DATE_IOS_LAST_REV";
	
	public static final String C_ERROR_EMPTY_DATE_MGRT_ACCOUNT = "ERROR_EMPTY_DATE_MGRT_ACCOUNT";
	public static final String C_ERROR_INVALID_DATE_MGRT_ACCOUNT = "ERROR_INVALID_DATE_MGRT_ACCOUNT";
	public static final String C_ERROR_EMPTY_ACCOUNT_CITIBANK_MGRT_ACCOUNT = "ERROR_EMPTY_ACCOUNT_CITIBANK_MGRT_ACCOUNT";
	public static final String C_ERROR_EMPTY_ACCOUNT_CUSTODIA_MGRT_ACCOUNT = "ERROR_EMPTY_ACCOUNT_CUSTODIA_MGRT_ACCOUNT";
	public static final String C_ERROR_ALREADY_ACCOUNT_CITIBANK_MGRT = "ERROR_ALREADY_ACCOUNT_CITIBANK_MGRT";
	public static final String C_ERROR_ALREADY_ACCOUNT_CUSTODIA_MGRT = "ERROR_ALREADY_ACCOUNT_CUSTODIA_MGRT";
	
	public static final String C_ERROR_CPF_ACCOUNT_GRB_DIFFERENT_CPF_ACCOUNT_CUSTODIA = "ERROR_CPF_ACCOUNT_GRB_DIFFERENT_CPF_ACCOUNT_CUSTODIA";
	public static final String C_ERROR_CPF_ACCOUNT_CUSTODIA_NON_EXIST = "ERROR_CPF_ACCOUNT_CUSTODIA_NON_EXIST";
	
	public static final String C_ERROR_ACCOUNT_CUSTODIA_NON_EXIST = "ERROR_ACCOUNT_CUSTODIA_NON_EXIST";
	public static final String C_ERROR_ACCOUNT_CITIBANK_NON_EXIST = "ERROR_ACCOUNT_CITIBANK_NON_EXIST";
	
	public static final String C_ERROR_ALREADY_SAME_EMPLOYEE_SOEID = "ERROR_ALREADY_SAME_EMPLOYEE_SOEID";
	public static final String C_ERROR_EMPLOYEE_SOEID_NON_EXIST = "ERROR_EMPLOYEE_SOEID_NON_EXIST";
	public static final String C_ERROR_SUPERVISOR_SOEID_NON_EXIST = "ERROR_SUPERVISOR_SOEID_NON_EXIST";
	public static final String C_ERROR_ASSOCIATE_SOEID_NON_EXIST = "ERROR_ASSOCIATE_SOEID_NON_EXIST";
	public static final String C_ERROR_SAME_USER = "ERROR_SAME_USER";
	

	public static final String C_ERROR_QUESTION_KE_QTD_MAX = "ERROR_QUESTION_KE_QTD_MAX";
	public static final String C_ERROR_QUESTION_KE_LIMIT_SIZE = "ERROR_QUESTION_KE_LIMIT_SIZE";
	public static final String C_ERROR_QUESTION_KE_EMPTY = "ERROR_QUESTION_KE_EMPTY";


	
	private User m_loggedUser = null;

	/**
	 * m_backURL - Nome da tel
	public static final String CANNOT_DELETE_REGISTER_IN_MOVEMENT = "CANNOT_DELETE_REGISTER_IN_MOVEMENT";	
a anterior.
	 */
	protected String m_backURL = null;

	/**
	 * m_currentLocale - Locale utilizado pela sessão.
	 */
	protected Locale m_currentLocale = null;

	/**
	 * m_currentMessageResources - Message Resources utilizado pela aplicação.
	 */
	protected MessageResources m_currentMessageResources = null;

	/**
	 * m_currentSheet - Aba atualmente selecionada.
	 */
	protected String m_currentSheet = null;

	/**
	 * m_hasMessage - Verificação de mensagens de erros
	 */
	protected String m_hasMessages = "";

	/**
	 * m_hasMessage - Verificação de mensagens de erros
	 */
	protected String m_hasErrors = "";

	/**
	 * m_currentSubSheet - Submenu selecionado.
	 */
	protected String m_currentSubSheet = null;

	/**
	 * m_executingList - Flag de verificação se o usuário clicou no botão
	 * consultar.
	 */
	protected String m_executingList = "";

	protected String m_orderBy = "";

	/**
	 * Código Id selecionado dinamicamente nas consultas de aprovação
	 * centralizada
	 */
	protected String m_selectedCode = "";

	/**
	 * Código Id do módulo selecionado dinamicamente nas consultas de aprovação
	 * centralizada
	 */
	protected String m_selectedModuleCode = "";

	public Locale getCurrentLocale() {
		return m_currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale_) {
		m_currentLocale = currentLocale_;
	}

	public MessageResources getCurrentMessageResources() {
		return m_currentMessageResources;
	}

	public void setCurrentMessageResources(MessageResources currentMessageResources_) {
		m_currentMessageResources = currentMessageResources_;
	}

	public String getCurrentSheet() {
		return m_currentSheet;
	}

	public void setCurrentSheet(String currentSheet_) {
		m_currentSheet = currentSheet_;
	}

	public String getBackURL() {
		return m_backURL;
	}

	public void setBackURL(String backURL_) {
		m_backURL = backURL_;
	}

	public String getHasErrors() {
		return m_hasErrors;
	}

	public void setHasErrors(String hasErrors_) {
		m_hasErrors = hasErrors_;
	}

	public String getHasMessages() {
		return m_hasMessages;
	}

	public void setHasMessages(String hasMessages_) {
		m_hasMessages = hasMessages_;
	}

	public String getCurrentSubSheet() {
		return m_currentSubSheet;
	}

	public void setCurrentSubSheet(String currentSubSheet_) {
		m_currentSubSheet = currentSubSheet_;
	}

	public String getExecutingList() {
		return m_executingList;
	}

	public void setExecutingList(String executingList_) {
		m_executingList = executingList_;
	}

	public String removeMask(String value_) {
		String withoutLiterals = "";

		if (value_ != null && !value_.equals("")) {

			for (int i = 0; i < value_.length(); i++) {
				if (!isConstant(value_.charAt(i))) {
					withoutLiterals += value_.charAt(i);
				}
			}
		}
		return withoutLiterals;
	}

	public boolean isConstant(char valor) {
		switch (valor) {
		case '/':
			return true;
		case '.':
			return true;
		case ',':
			return true;
		case '-':
			return true;
		case '(':
			return true;
		case ')':
			return true;
		case ':':
			return true;
		default:
			return false;
		}
	}

	public String getOrderBy() {
		return m_orderBy;
	}

	public void setOrderBy(String m_orderBy_) {
		m_orderBy = m_orderBy_;
	}

	public String getSelectedCode() {
		return m_selectedCode;
	}

	public void setSelectedCode(String m_selectedCode_) {
		m_selectedCode = m_selectedCode_;
	}

	public String getSelectedModuleCode() {
		return m_selectedModuleCode;
	}

	public void setSelectedModuleCode(String m_selectedModuleCode_) {
		m_selectedModuleCode = m_selectedModuleCode_;
	}

	public User getLoggedUser() {
		return m_loggedUser;
	}

	public void setLoggedUser(User m_loggedUser) {
		this.m_loggedUser = m_loggedUser;
	}

	// ATRIBUTOS DO VO - INICIO
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * m_messages - Lista de mensagens do struts para serem publicadas. Estas
	 * mensagens são registradas pelas classes do tipo Fnc para serem publicadas
	 * e apresentadas na tela.
	 */
	private ActionMessages m_messages = new ActionMessages();

	/**
	 * m_errors - Lista de mensagens de erro do struts para serem publicados.
	 * Estas mensagens são registradas pelas classes do tipo Fnc para serem
	 * publicadas e apresentadas na tela.
	 */
	private ActionErrors m_errors = new ActionErrors();

	/**
	 * m_currentPage - Nome da página atual.
	 */
	private String m_currentPage = null;

	/**
	 * Variável de controle do botão listar
	 */
	private boolean m_executedList = false;

	/**
	 * Nome de VO a ser removidos da sessao
	 */
	private String m_nameCleanSession = null;

	/**
	 * 
	 * Adiciona uma nova mensagem à lista de mensagens de aviso. O texto da
	 * mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valores definidos em "values_"
	 * substituirão os marcadores {n}, onde n é um número inteiro, e n é a
	 * posição no array de valores do valor a ser substituído.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param values_
	 *            - Valores de substituição em mensagens dinâmicas.
	 */
	public void addMessage(String textId_, Object[] values_) {
		ActionMessage actionMessage = new ActionMessage(textId_, values_);
		m_messages.add(textId_, actionMessage);
	}

	/**
	 * 
	 * Adiciona uma nova mensagem à lista de mensagens de aviso. O texto da
	 * mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 */
	public void addMessage(String textId_) {
		addMessage(textId_, null);
	}

	public void addMessage(String textId_, Object value0_) {
		addMessage(textId_, new Object[] { value0_ });
	}

	/**
	 * 
	 * Adiciona uma nova mensagem à lista de mensagens de aviso. O texto da
	 * mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valors definidos em "value0_"
	 * e "value1_" substituirão os marcadors {0} e {1}, respectivamente.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param value0_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {0}.
	 * @param value1_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {1}.
	 */
	public void addMessage(String textId_, Object value0_, Object value1_) {
		addMessage(textId_, new Object[] { value0_, value1_ });
	}

	/**
	 * 
	 * Adiciona uma nova mensagem à lista de mensagens de aviso. O texto da
	 * mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valors definidos em "value0_",
	 * "value1_" e "value2_" substituirão os marcadors {0}, {1} e {2},
	 * respectivamente.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param value0_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {0}.
	 * @param value1_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {1}.
	 * @param value2_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {2}.
	 */
	public void addMessage(String textId_, Object value0_, Object value1_, Object value2_) {
		addMessage(textId_, new Object[] { value0_, value1_, value2_ });
	}

	/**
	 * 
	 * Adiciona uma nova mensagem à lista de mensagens de aviso. O texto da
	 * mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valors definidos em "value0_",
	 * "value1_", "value2_" e "value3_" substituirão os marcadors {0}, {1}, {2}
	 * e {3}, respectivamente.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param value0_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {0}.
	 * @param value1_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {1}.
	 * @param value2_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {2}.
	 * @param value3_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {3}.
	 */
	public void addMessage(String textId_, Object value0_, Object value1_, Object value2_, Object value3_) {
		addMessage(textId_, new Object[] { value0_, value1_, value2_, value3_ });
	}

	/**
	 * 
	 * Adiciona uma nova mensagem de erro à lista de mensagens de erro. O texto
	 * da mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valores definidos em "values_"
	 * substituirão os marcadores {n}, onde n é um número inteiro, e n é a
	 * posição no array de valores do valor a ser substituído.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param values_
	 *            - Valores de substituição em mensagens dinâmicas.
	 */
	public void addError(String textId_, Object[] values_) {
		ActionMessage actionMessage = new ActionMessage(textId_, values_);
		m_errors.add(textId_, actionMessage);
	}

	/**
	 * 
	 * Adiciona uma nova mensagem de erro à lista de mensagens de erro. O texto
	 * da mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 */
	public void addError(String textId_) {
		addError(textId_, null);
	}
	
	

	/**
	 * 
	 * Adiciona uma nova mensagem de erro à lista de mensagens de erro. O texto
	 * da mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, o valor definidos em "value0_"
	 * substituirá o marcador {0}.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param value0_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {0}.
	 */
	public void addError(String textId_, Object value0_) {
		addError(textId_, new Object[] { value0_ });
	}

	/**
	 * 
	 * Adiciona uma nova mensagem de erro à lista de mensagens de erro. O texto
	 * da mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valors definidos em "value0_"
	 * e "value1_" substituirão os marcadors {0} e {1}, respectivamente.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param value0_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {0}.
	 * @param value1_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {1}.
	 */
	public void addError(String textId_, Object value0_, Object value1_) {
		addError(textId_, new Object[] { value0_, value1_ });
	}

	/**
	 * 
	 * Adiciona uma nova mensagem de erro à lista de mensagens de erro. O texto
	 * da mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valors definidos em "value0_",
	 * "value1_" e "value2_" substituirão os marcadors {0}, {1} e {2},
	 * respectivamente.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param value0_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {0}.
	 * @param value1_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {1}.
	 * @param value2_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {2}.
	 */
	public void addError(String textId_, Object value0_, Object value1_, Object value2_) {
		addError(textId_, new Object[] { value0_, value1_, value2_ });
	}

	/**
	 * 
	 * Adiciona uma nova mensagem de erro à lista de mensagens de erro. O texto
	 * da mensagem é definido por um identificador (textId_) que será traduzido
	 * para o texto da mensagem pelo JSP. A relação entre o código da mensagem e
	 * seu texto está definido no arquivo de mensagens da aplicação (o nome do
	 * arquivo está definido na tag "message-resources" do xml de configuração
	 * do struts). <br>
	 * Na apresentação de mensagens dinâmicas, os valors definidos em "value0_",
	 * "value1_", "value2_" e "value3_" substituirão os marcadors {0}, {1}, {2}
	 * e {3}, respectivamente.
	 * 
	 * @param textId_
	 *            - Código da mensagem.
	 * @param value0_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {0}.
	 * @param value1_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {1}.
	 * @param value2_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {2}.
	 * @param value3_
	 *            - Valor de substituição em mensagens dinâmicas do marcador
	 *            {3}.
	 */
	public void addError(String textId_, Object value0_, Object value1_, Object value2_, Object value3_) {
		addError(textId_, new Object[] { value0_, value1_, value2_, value3_ });
	}

	/**
	 * 
	 * Limpa a lista de mensagens de aviso.
	 * 
	 */
	public void clearMessages() {
		m_messages.clear();
	}

	/**
	 * 
	 * Limpa a lista de mensagens de erro.
	 * 
	 */
	public void clearErrors() {
		m_errors.clear();
	}

	/**
	 * 
	 * Obtém a lista de mensagens de aviso.
	 * 
	 * @return lista de mensagens de aviso.
	 */
	public ActionMessages getMessages() {
		return m_messages;
	}

	/**
	 * 
	 * Obtém a lista de mensagens de erro.
	 * 
	 * @return lista de mensagens de erro.
	 */
	public ActionErrors getErrors() {
		return m_errors;
	}

	/**
	 * 
	 * Indica se na lista de mensagens de erro existe pelo menos uma mensagem de
	 * erro.
	 * 
	 * @return true - se existe pelo menos um item na lista de mensagens de
	 *         erro. false - caso contrário.
	 */
	public boolean hasErrors() {
		boolean hasErrors = false;
		if (m_errors != null && m_errors.size() > 0) {
			hasErrors = true;
		}
		return hasErrors;
	}

	/**
	 * 
	 * Indica se na lista de mensagens de aviso existe pelo menos uma mensagem
	 * de aviso.
	 * 
	 * @return true - se existe pelo menos um item na lista de mensagens de
	 *         aviso. false - caso contrário.
	 */
	public boolean hasMessages() {
		boolean hasMessages = false;
		if (m_messages != null && m_messages.size() > 0) {
			hasMessages = true;
		}
		return hasMessages;
	}

	/**
	 * @return m_currentPage. Retorna a página atual.
	 */
	public String getCurrentPage() {
		return m_currentPage;
	}

	/**
	 * @param currentPage_.Seta a página atual.
	 */
	public void setCurrentPage(String currentPage_) {
		m_currentPage = currentPage_;
	}

	/**
	 * @return executedList.Flag de verificação se foi efetuada uma consulta.
	 */
	public boolean isExecutedList() {
		return m_executedList;
	}

	/**
	 * @param executedList_.Seta true quando uma consulta é efetuada. Controle de limpeza de telas
	 */
	public void setExecutedList(boolean executedList_) {
		m_executedList = executedList_;
	}

	public static final String C_CURRENT_SHEET_CUSTOMER = "CustomerSheet";

	public static final String C_CURRENT_SHEET_PRODUCT = "ProductSheet";

	public static final String C_CURRENT_SHEET_CONTROL = "ControlSheet";

	public String getNameCleanSession() {
		return m_nameCleanSession;
	}

	public void setNameCleanSession(String toClean) {
		m_nameCleanSession = toClean;
	}

}