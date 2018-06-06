package com.citibank.ods.common.form;

import java.io.Serializable;
import java.util.Locale;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.MessageResources;

/**
 * 
 * Classe base para as classes do tipo Form. Esta classe deve ser serialilz�vel
 * porque uma inst�ncia desta pode ser posta em session pelo struts.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class BaseForm extends ActionForm implements Serializable
{

  public static final String C_FORMAT_CNPJ_NBR = "'NN.NNN.NNN/NNNN-NN'";

  public static final String C_FORMAT_CPF_NBR = "'NNN.NNN.NNN-NN'";

  public static final String C_FORMAT_PHONE_NBR = "NNNN-NNNN";

  /**
   * m_backURL - Nome da tela anterior.
   */
  protected String m_backURL = null;

  /**
   * m_currentLocale - Locale utilizado pela sess�o.
   */
  protected Locale m_currentLocale = null;

  /**
   * m_currentMessageResources - Message Resources utilizado pela aplica��o.
   */
  protected MessageResources m_currentMessageResources = null;

  /**
   * m_currentSheet - Aba atualmente selecionada.
   */
  protected String m_currentSheet = null;

  /**
   * m_hasMessage - Verifica��o de mensagens de erros
   */
  protected String m_hasMessages = "";

  /**
   * m_hasMessage - Verifica��o de mensagens de erros
   */
  protected String m_hasErrors = "";

  /**
   * m_currentSubSheet - Submenu selecionado.
   */
  protected String m_currentSubSheet = null;

  /**
   * m_executingList - Flag de verifica��o se o usu�rio clicou no bot�o
   * consultar. 
   */
  protected String m_executingList = "";
  
  protected String m_orderBy = "";
  
  /**
   *  C�digo Id selecionado dinamicamente nas consultas de aprova��o centralizada
   */
  protected String m_selectedCode = "";
  
  /**
   *  C�digo Id do m�dulo selecionado dinamicamente nas consultas de aprova��o centralizada
   */
  protected String m_selectedModuleCode = "";

  /**
   * 
   * Obt�m o Locale utilizado pela sess�o.
   * 
   * @return locale utilizado pela sess�o.
   */
  public Locale getCurrentLocale()
  {
    return m_currentLocale;
  }

  /**
   * 
   * Atribui o valor do Locale utilizado pela sess�o.
   * 
   * @param currentLocale_ - Locale utilizado pela sess�o.
   */
  public void setCurrentLocale( Locale currentLocale_ )
  {
    m_currentLocale = currentLocale_;
  }

  /**
   * 
   * Obt�m o MessageResources utilizado pela aplica��o.
   * 
   * @return MessageResources utilizado pela aplica��o.
   */

  public MessageResources getCurrentMessageResources()
  {
    return m_currentMessageResources;
  }

  /**
   * 
   * Atribui o MessageResources utilizado pela aplica��o.
   * 
   * @param currentMessageResources_ - MessageResources utilizado pela
   *          aplica��o.
   */
  public void setCurrentMessageResources(
                                         MessageResources currentMessageResources_ )
  {
    m_currentMessageResources = currentMessageResources_;
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
   * Obt�m o nome da tela anterior.
   * @return Retorna o nome da tela anterior.
   */
  public String getBackURL()
  {
    return m_backURL;
  }

  /**
   * Atribui valor a propriedade que indica a tela anterior.
   * @param backURL_ Nome da tela anterior.
   */
  public void setBackURL( String backURL_ )
  {
    m_backURL = backURL_;
  }

  /**
   * @return m_hasErrors. Retorna se tem mensagem de erro.
   */
  public String getHasErrors()
  {
    return m_hasErrors;
  }

  /**
   * @param hasErrors_. Seta se tem mensagem de erro.
   */
  public void setHasErrors( String hasErrors_ )
  {
    m_hasErrors = hasErrors_;
  }

  /**
   * @return m_hasMessages. Retorna se tem mensagem.
   */
  public String getHasMessages()
  {
    return m_hasMessages;
  }

  /**
   * @param hasMessages_.Seta se tem mensagem.
   */
  public void setHasMessages( String hasMessages_ )
  {
    m_hasMessages = hasMessages_;
  }

  /**
   * Obt�m o submenu selecionado.
   * @return Retorna o atual submenu.
   */
  public String getCurrentSubSheet()
  {
    return m_currentSubSheet;
  }

  /**
   * Atribui valor a propriedade que indica o submenu.
   * @param currentSubSheet_ Submenu atual.
   */
  public void setCurrentSubSheet( String currentSubSheet_ )
  {
    m_currentSubSheet = currentSubSheet_;
  }
  /**
   * Obt�m sim quando a consulta est� sendo executada.
   * @return Retorna sim ou n�o.
   */  

  public String getExecutingList()
  {
    return m_executingList;
  }
  /**
   * Atribui valor a propriedade que indica a execu��o da consulta.
   * @param executingList_.
   */
  public void setExecutingList( String executingList_ )
  {
    m_executingList = executingList_;
  }
  
  
  public String removeMask( String value_ )
  {
    String withoutLiterals = "";

    if ( value_ != null && !value_.equals( "" ) )
    {

      for ( int i = 0; i < value_.length(); i++ )
      {
        if ( !isConstant( value_.charAt( i ) ) )
        {
          withoutLiterals += value_.charAt( i );
        }
      }
    }
    return withoutLiterals;
  }

  public boolean isConstant( char valor )
  {
    switch ( valor )
    {
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

  /**
   * @return
   */
  public String getSelectedCode() {
	return m_selectedCode;
  }

  /**
   * @param string
   */
  public void setSelectedCode(String m_selectedCode_) {
	m_selectedCode = m_selectedCode_;
  }

  /**
   * @return
   */
  public String getSelectedModuleCode() {
	return m_selectedModuleCode;
  }

  /**
   * @param string
   */
  public void setSelectedModuleCode(String m_selectedModuleCode_) {
	m_selectedModuleCode = m_selectedModuleCode_;
  }

}