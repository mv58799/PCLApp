package com.citibank.ods.common.taglib;

import java.math.BigInteger;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

/**
 * 
 * Custom Tag utilizada para contagem de passo a partir de um contador.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class CountStepTag extends BaseBodyTag
{

  /**
   * m_counterName - nome do bean que é um tipo numérico inteiro (se
   * m_counterProperty não for informado) ou bem que possui uma propriedade que
   * é do um tipo numérico inteiro (se m_counterProperty for informado) que
   * representa o contador que será utilizado como base para o cálculo do passo.
   */
  private String m_counterName = null;

  /**
   * m_counterProperty - propriedade do bean especifcado por m_counterName que é
   * um tipo numérico que representa o contador que será utilizado como base
   * para o cálculo do passo. Se esta propriedade não for esepecificada, o
   * próprio bean encontrado será considerado como o contador base para o
   * cálculo do passo.
   */
  private String m_counterProperty = null;

  /**
   * m_counterScope - escopo de pesquisa do bean especificado pelo
   * m_counterName. Se não for especificado, serão aplicadas as regras padrão
   * definidas pelo PageContext.findAttribute().
   */
  private String m_counterScope = null;

  /**
   * m_counterStartIndex - Valor do contador que será considerado como o
   * primeiro valor para a contagem de passo. Se não for especificado, será
   * ssumido 0.
   */
  private int m_counterStartIndex = 0;

  /**
   * m_sequenceRestartStep - Valor mínimo do contador que define o reinício da
   * contagem de passo.
   */
  private int m_sequenceRestartStep = 0;

  /**
   * m_stepIndexName - nome de publicação do Bean que representará o valor do
   * passo atual.
   */
  private String m_stepIndexName = null;

  /**
   * C_LOCAL_STRINGS_NAME - nome do MessageResources utilizado por esta custom
   * tag para tratamento de mensagens.
   */
  private static final String C_LOCAL_STRINGS_NAME = "com.citibank.ods.common.taglib";

  /**
   * C_MESSAGE_COUNTERSTEPTAG_COUNTER_INSTANCE - Código da mensagem que indica
   * erro quando o contador referenciado por m_counterName / m_counterProperty
   * não for uma instância de um tipo esperado.
   */
  private static final String C_MESSAGE_COUNTERSTEPTAG_COUNTER_INSTANCE = "countsteptag.counter.instance";

  /**
   * ms_messages - MessageResources utilizado pela custom tag para tratamento de
   * mensagens de exceção.
   */
  protected static MessageResources ms_messages = MessageResources.getMessageResources( C_LOCAL_STRINGS_NAME );

  /**
   * m_counterValue - valor atual do contador interno de passo. O valor do
   * primeiro passo é sempre 0.
   */
  private int m_counterValue = 0;

  /*
   * Encontra o contador, identifica e publica o valor atual do passo em que se
   * encontra o contador.
   * @see javax.servlet.jsp.tagext.Tag#doStartTag()
   */
  public int doStartTag() throws JspException
  {
    Object counter = null;
    //procura pelo bean indicado
    counter = TagUtils.getInstance().lookup( pageContext, m_counterName,
                                             m_counterProperty, m_counterScope );

    // Verifica se o bean obtido é um tipo esperado
    if ( !( counter instanceof Integer ) && !( counter instanceof Byte )
         && !( counter instanceof Long ) && !( counter instanceof Short )
         && !( counter instanceof BigInteger ) )
    {
      JspException e = new JspException(
                                         ms_messages.getMessage(
                                                                 C_MESSAGE_COUNTERSTEPTAG_COUNTER_INSTANCE,
                                                                 m_counterName,
                                                                 m_counterProperty ) );
      TagUtils.getInstance().saveException( pageContext, e );
      throw e;
    }

    m_counterValue = ( ( Number ) counter ).intValue();

    publishData();
    return EVAL_BODY_BUFFERED;
  }

  /*
   * @see javax.servlet.jsp.tagext.Tag#doEndTag()
   */
  public int doEndTag() throws JspException
  {
    return EVAL_PAGE;
  }

  /*
   * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
   */
  public int doAfterBody() throws JspException
  {
    if ( bodyContent != null )
    {
      TagUtils.getInstance().writePrevious( pageContext,
                                            bodyContent.getString() );
      bodyContent.clearBody();
    }

    return SKIP_BODY;
  }

  /**
   * 
   * Faz a publicação do valor do passo utilizando o nome informado para
   * m_stepIndexName.
   *  
   */
  private void publishData()
  {
    pageContext.setAttribute(
                              m_stepIndexName,
                              new Integer(
                                           ( m_counterValue - m_counterStartIndex )
                                                                                      % m_sequenceRestartStep ) );
  }

  /**
   * 
   * Getter da propriedade m_counterName.
   * 
   * @return valor da propriedade m_counterName.
   */
  public String getCounterName()
  {
    return m_counterName;
  }

  /**
   * 
   * Setter da propriedade m_counterName.
   * 
   * @param counterName_ - valor da propriedade m_counterName.
   */
  public void setCounterName( String counterName_ )
  {
    m_counterName = counterName_;
  }

  /**
   * 
   * Getter da propriedade m_counterProperty.
   * 
   * @return m_counterProperty.
   */
  public String getCounterProperty()
  {
    return m_counterProperty;
  }

  /**
   * 
   * Setter da propriedade m_counterProperty.
   * 
   * 
   * @param counterProperty_ - valor da propriedade m_counterProperty.
   */
  public void setCounterProperty( String counterProperty_ )
  {
    m_counterProperty = counterProperty_;
  }

  public String getCounterScope()
  {
    return m_counterScope;
  }

  /**
   * 
   * Setter da propriedade m_counterScope.
   * 
   * @param counterScope_ - valor da propriedade m_counterScope.
   */
  public void setCounterScope( String counterScope_ )
  {
    m_counterScope = counterScope_;
  }

  /**
   * 
   * Getter da propriedade m_counterStartIndex
   * 
   * @return valor da propriedade m_counterStartIndex.
   */
  public int getCounterStartIndex()
  {
    return m_counterStartIndex;
  }

  /**
   * 
   * Setter da propriedade m_counterStartIndex.
   * 
   * @param counterStartIndex_ - valor da propriedade m_counterStartIndex.
   */
  public void setCounterStartIndex( int counterStartIndex_ )
  {
    m_counterStartIndex = counterStartIndex_;
  }

  /**
   * 
   * Getter da propriedade m_sequenceRestartStep.
   * 
   * @return valor da propriedade m_sequenceRestartStep.
   */
  public int getSequenceRestartStep()
  {
    return m_sequenceRestartStep;
  }

  /**
   * 
   * Setter da propriedade m_sequenceRestartStep.
   * 
   * @param sequenceRestartStep_ - valor da propriedade m_sequenceRestartStep.
   */
  public void setSequenceRestartStep( int sequenceRestartStep_ )
  {
    m_sequenceRestartStep = sequenceRestartStep_;
  }

  /**
   * 
   * Getter da propriedade m_stepIndexName.
   * 
   * @return valor da propriedade m_stepIndexName.
   */
  public String getStepIndexName()
  {
    return m_stepIndexName;
  }

  /**
   * 
   * Setter da propriedade m_stepIndexName.
   * 
   * @param stepIndexName_ - valor da propriedade m_stepIndexName.
   */
  public void setStepIndexName( String stepIndexName_ )
  {
    m_stepIndexName = stepIndexName_;
  }
}