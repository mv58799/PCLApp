package com.citibank.ods.common.functionality.valueobject;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.citibank.ods.common.user.User;
import com.citibank.ods.common.valueobject.BaseVO;

/**
 * 
 * Classe base para as classes do tipo FncVO. Estas classes tem como objetivo
 * manter o estado da funcionalidade, armazenando as informa��es necess�rias
 * para persist�ncia das informa��es e apresenta��o de dados pelo Form.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class BaseFncVO extends BaseVO
{

  /**
   * m_messages - Lista de mensagens do struts para serem publicadas. Estas
   * mensagens s�o registradas pelas classes do tipo Fnc para serem publicadas e
   * apresentadas na tela.
   */
  private ActionMessages m_messages = new ActionMessages();

  /**
   * m_errors - Lista de mensagens de erro do struts para serem publicados.
   * Estas mensagens s�o registradas pelas classes do tipo Fnc para serem
   * publicadas e apresentadas na tela.
   */
  private ActionErrors m_errors = new ActionErrors();

  /**
   * m_currentSheet - Aba atualmente selecionada.
   */
  private String m_currentSheet = null;

  /**
   * m_loggedUser - Objeto contendo os dados do usu�rio logado na sess�o atual.
   */
  private User m_loggedUser = null;

  /**
   * m_currentPage - Nome da p�gina atual.
   */
  private String m_currentPage = null;

  /**
   * Vari�vel de controle do bot�o listar
   */
  private boolean m_executedList = false;

  /**
   * Vari�vel de controle verifica se o consultar est� sendo executado
   */
  private String m_executingList = "";
  
  private String m_orderBy = "";
  
  /**
   * Nome de VO a ser removidos da sessao
   */
  private String m_nameCleanSession = null;

  /**
   * Retorna o objeto contendo os dados do usu�rio logado na sess�o atual.
   * @return Objeto contendo os dados do usu�rio logado na sess�o atual.
   */
  public User getLoggedUser()
  {
    return m_loggedUser;
  }

  /**
   * Atribui um objeto de dados de usu�rio ao FncVO
   * @param loggedUser_ objeto contendo os dados do usu�rio logado na sess�o
   *          atual.
   */
  public void setLoggedUser( User loggedUser_ )
  {
    m_loggedUser = loggedUser_;
  }

  /**
   * 
   * Adiciona uma nova mensagem � lista de mensagens de aviso. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valores definidos em "values_"
   * substituir�o os marcadores {n}, onde n � um n�mero inteiro, e n � a posi��o
   * no array de valores do valor a ser substitu�do.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param values_ - Valores de substitui��o em mensagens din�micas.
   */
  public void addMessage( String textId_, Object[] values_ )
  {
    ActionMessage actionMessage = new ActionMessage( textId_, values_ );
    m_messages.add( textId_, actionMessage );
  }

  /**
   * 
   * Adiciona uma nova mensagem � lista de mensagens de aviso. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * 
   * @param textId_ - C�digo da mensagem.
   */
  public void addMessage( String textId_ )
  {
    addMessage( textId_, null );
  }

  public void addMessage( String textId_, Object value0_ )
  {
    addMessage( textId_, new Object[] { value0_ } );
  }

  /**
   * 
   * Adiciona uma nova mensagem � lista de mensagens de aviso. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valors definidos em "value0_" e
   * "value1_" substituir�o os marcadors {0} e {1}, respectivamente.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param value0_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {0}.
   * @param value1_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {1}.
   */
  public void addMessage( String textId_, Object value0_, Object value1_ )
  {
    addMessage( textId_, new Object[] { value0_, value1_ } );
  }

  /**
   * 
   * Adiciona uma nova mensagem � lista de mensagens de aviso. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valors definidos em "value0_",
   * "value1_" e "value2_" substituir�o os marcadors {0}, {1} e {2},
   * respectivamente.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param value0_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {0}.
   * @param value1_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {1}.
   * @param value2_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {2}.
   */
  public void addMessage( String textId_, Object value0_, Object value1_,
                         Object value2_ )
  {
    addMessage( textId_, new Object[] { value0_, value1_, value2_ } );
  }

  /**
   * 
   * Adiciona uma nova mensagem � lista de mensagens de aviso. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valors definidos em "value0_",
   * "value1_", "value2_" e "value3_" substituir�o os marcadors {0}, {1}, {2} e
   * {3}, respectivamente.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param value0_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {0}.
   * @param value1_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {1}.
   * @param value2_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {2}.
   * @param value3_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {3}.
   */
  public void addMessage( String textId_, Object value0_, Object value1_,
                         Object value2_, Object value3_ )
  {
    addMessage( textId_, new Object[] { value0_, value1_, value2_, value3_ } );
  }

  /**
   * 
   * Adiciona uma nova mensagem de erro � lista de mensagens de erro. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valores definidos em "values_"
   * substituir�o os marcadores {n}, onde n � um n�mero inteiro, e n � a posi��o
   * no array de valores do valor a ser substitu�do.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param values_ - Valores de substitui��o em mensagens din�micas.
   */
  public void addError( String textId_, Object[] values_ )
  {
    ActionMessage actionMessage = new ActionMessage( textId_, values_ );
    m_errors.add( textId_, actionMessage );
  }

  /**
   * 
   * Adiciona uma nova mensagem de erro � lista de mensagens de erro. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * 
   * @param textId_ - C�digo da mensagem.
   */
  public void addError( String textId_ )
  {
    addError( textId_, null );
  }

  /**
   * 
   * Adiciona uma nova mensagem de erro � lista de mensagens de erro. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, o valor definidos em "value0_"
   * substituir� o marcador {0}.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param value0_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {0}.
   */
  public void addError( String textId_, Object value0_ )
  {
    addError( textId_, new Object[] { value0_ } );
  }

  /**
   * 
   * Adiciona uma nova mensagem de erro � lista de mensagens de erro. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valors definidos em "value0_" e
   * "value1_" substituir�o os marcadors {0} e {1}, respectivamente.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param value0_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {0}.
   * @param value1_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {1}.
   */
  public void addError( String textId_, Object value0_, Object value1_ )
  {
    addError( textId_, new Object[] { value0_, value1_ } );
  }

  /**
   * 
   * Adiciona uma nova mensagem de erro � lista de mensagens de erro. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valors definidos em "value0_",
   * "value1_" e "value2_" substituir�o os marcadors {0}, {1} e {2},
   * respectivamente.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param value0_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {0}.
   * @param value1_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {1}.
   * @param value2_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {2}.
   */
  public void addError( String textId_, Object value0_, Object value1_,
                       Object value2_ )
  {
    addError( textId_, new Object[] { value0_, value1_, value2_ } );
  }

  /**
   * 
   * Adiciona uma nova mensagem de erro � lista de mensagens de erro. O texto da
   * mensagem � definido por um identificador (textId_) que ser� traduzido para
   * o texto da mensagem pelo JSP. A rela��o entre o c�digo da mensagem e seu
   * texto est� definido no arquivo de mensagens da aplica��o (o nome do arquivo
   * est� definido na tag "message-resources" do xml de configura��o do struts).
   * <br>
   * Na apresenta��o de mensagens din�micas, os valors definidos em "value0_",
   * "value1_", "value2_" e "value3_" substituir�o os marcadors {0}, {1}, {2} e
   * {3}, respectivamente.
   * 
   * @param textId_ - C�digo da mensagem.
   * @param value0_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {0}.
   * @param value1_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {1}.
   * @param value2_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {2}.
   * @param value3_ - Valor de substitui��o em mensagens din�micas do marcador
   *          {3}.
   */
  public void addError( String textId_, Object value0_, Object value1_,
                       Object value2_, Object value3_ )
  {
    addError( textId_, new Object[] { value0_, value1_, value2_, value3_ } );
  }

  /**
   * 
   * Limpa a lista de mensagens de aviso.
   *  
   */
  public void clearMessages()
  {
    m_messages.clear();
  }

  /**
   * 
   * Limpa a lista de mensagens de erro.
   *  
   */
  public void clearErrors()
  {
    m_errors.clear();
  }

  /**
   * 
   * Obt�m a lista de mensagens de aviso.
   * 
   * @return lista de mensagens de aviso.
   */
  public ActionMessages getMessages()
  {
    return m_messages;
  }

  /**
   * 
   * Obt�m a lista de mensagens de erro.
   * 
   * @return lista de mensagens de erro.
   */
  public ActionErrors getErrors()
  {
    return m_errors;
  }

  /**
   * 
   * Indica se na lista de mensagens de erro existe pelo menos uma mensagem de
   * erro.
   * 
   * @return true - se existe pelo menos um item na lista de mensagens de erro.
   *         false - caso contr�rio.
   */
  public boolean hasErrors()
  {
    boolean hasErrors = false;
    if ( m_errors != null && m_errors.size() > 0 )
    {
      hasErrors = true;
    }
    return hasErrors;
  }

  /**
   * 
   * Indica se na lista de mensagens de aviso existe pelo menos uma mensagem de
   * aviso.
   * 
   * @return true - se existe pelo menos um item na lista de mensagens de aviso.
   *         false - caso contr�rio.
   */
  public boolean hasMessages()
  {
    boolean hasMessages = false;
    if ( m_messages != null && m_messages.size() > 0 )
    {
      hasMessages = true;
    }
    return hasMessages;
  }

  /**
   * Obt�m a aba atual.
   * @return Retorna a aba atual.
   */
  public String getCurrentSheet()
  {
    return m_currentSheet;
  }

  /**
   * Atribui valor a propriedade que indica a aba atual.
   * @param currentSheet_ Aba atual.
   */
  public void setCurrentSheet( String currentSheet_ )
  {
    m_currentSheet = currentSheet_;
  }

  /**
   * @return m_currentPage. Retorna a p�gina atual.
   */
  public String getCurrentPage()
  {
    return m_currentPage;
  }

  /**
   * @param currentPage_.Seta a p�gina atual.
   */
  public void setCurrentPage( String currentPage_ )
  {
    m_currentPage = currentPage_;
  }

  /**
   * @return executedList.Flag de verifica��o se foi efetuada uma consulta.
   */
  public boolean isExecutedList()
  {
    return m_executedList;
  }

  /**
   * @param executedList_.Seta true quando uma consulta � efetuada. Controle de
   *          limpeza de telas
   */
  public void setExecutedList( boolean executedList_ )
  {
    m_executedList = executedList_;
  }

  /**
   * @return executingList. Retorna sim ou n�o.
   */
  public String getExecutingList()
  {
    return m_executingList;
  }

  /**
   * @param executingList_.Seta sim ou n�o referente a execu��o da consulta em
   *          lista.
   */
  public void setExecutingList( String executingList_ )
  {
    m_executingList = executingList_;

  }

  public static final String C_CURRENT_SHEET_CUSTOMER = "CustomerSheet";

  public static final String C_CURRENT_SHEET_PRODUCT = "ProductSheet";

  public static final String C_CURRENT_SHEET_CONTROL = "ControlSheet";

  /**
   * @return
   */
  public String getOrderBy() {
	return m_orderBy;
  }

  /**
   * @param string
   */
  public void setOrderBy(String m_orderBy_) {
	m_orderBy = m_orderBy_;
  }

  public String getNameCleanSession() {
	return m_nameCleanSession;
  	}

  public void setNameCleanSession(String toClean) {
	  m_nameCleanSession = toClean;
  }

  
}