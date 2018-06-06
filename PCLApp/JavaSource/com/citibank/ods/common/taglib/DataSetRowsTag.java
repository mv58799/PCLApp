package com.citibank.ods.common.taglib;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;

/**
 * 
 * Custom Tag utilizada para interação para as linhas de um DataSet.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class DataSetRowsTag extends BaseBodyTag
{
  /**
   * C_DATA_SET_ROW_NAME_DEFAULT - Nome padrão utilizado para publicar uma linha
   * de um DataSet caso um nome não seja definido.
   */
  private static final String C_DATA_SET_ROW_NAME_DEFAULT = "dataSetRow";

  /**
   * C_LOCAL_STRINGS_NAME - nome do MessageResources utilizado por esta custom
   */
  private static final String C_LOCAL_STRINGS_NAME = "com.citibank.ods.common.taglib";

  /**
   * C_MESSAGE_DATASETROWS_INSTANCE - Código da mensagem que indica erro quando
   * o bean referenciado por m_name / m_property não for uma instância de
   * DataSet.
   */
  private static final String C_MESSAGE_DATASETROWS_INSTANCE = "datasetrows.instance";

  /**
   * C_MESSAGE_DATASETROWS_STEPINDEXNAME_EMPTY - Código da mensagem que indica
   * erro quando o m_stepIndexName for informado como "".
   */
  private static final String C_MESSAGE_DATASETROWS_STEPINDEXNAME_EMPTY = "datasetrows.stepIndexName.empty";

  /**
   * C_MESSAGE_DATASETROWS_STEPINDEXNAME_SEQUENCERESTARTSTEP_COMBO_INVALID -
   * Código da mensagem que indica erro quando o m_stepIndexName e
   * m_sequenceRestartStep estiverem preenchidos de maneira inconsistente.
   */
  private static final String C_MESSAGE_DATASETROWS_STEPINDEXNAME_SEQUENCERESTARTSTEP_COMBO_INVALID = "datasetrows.stepIndexName.sequenceRestartStep.combo.invalid";

  /**
   * m_dataSet - DataSet utilzzado para as interações.
   */
  private DataSet m_dataSet;

  /**
   * m_rowIndex - Índice da linha do DataSet que está sendo avaliada.
   */
  private int m_rowIndex = 0;

  /**
   * ms_messages - MessageResources utilizado pela custom tag para tratamento de
   * mensagens de exceção.
   */
  protected static MessageResources ms_messages = MessageResources.getMessageResources( C_LOCAL_STRINGS_NAME );

  /**
   * m_name - Nome do bean que é o DataSet que será percorrido (se m_property
   * não for especificada) ou do bean que possui uma propriedade que é o DataSet
   * que será percorrido (se m_property for especificado).
   */
  private String m_name = null;

  /**
   * m_property - Propriedade do bean especificado por m_name que é o DataSet
   * que será percorrido.
   */
  private String m_property = null;

  /**
   * Nome que será utilizado para publicar uma linha do DataSet.
   */
  private String m_dataSetRowName = null;

  /**
   * m_counterScope - escopo de pesquisa do bean especificado pelo
   * m_counterName. Se não for especificado, serão aplicadas as regras padrão
   * definidas pelo PageContext.findAttribute().
   */
  private String m_scope = null;

  /**
   * m_sequenceRestartStep - indica o número de reinício de contagem de passo.
   * Sempre que o número da linha do DataSet que está sendo percorrida atingir
   * uma valor que é múltiplo deste valor (M_sequenceRestartIndex) a contagem de
   * pessao será reiniciada.
   */
  private int m_sequenceRestartStep = 0;

  /**
   * m_stepIndexName - nome de publicação do Bean que representará o valor do
   * passo atual.
   */
  private String m_stepIndexName = null;

  /**
   * 
   * Getter da Propriedade < <propriedade>>.
   * 
   * @return valor da Propriedade < <propriedade>>.
   */
  public String getDataSetRowName()
  {
    return m_dataSetRowName;
  }

  /**
   * 
   * Setter da propriedade < <propriedade>>.
   * 
   * @param dataSetRowName_ - Valor da propriedade m_dataSetRowName.
   */
  public void setDataSetRowName( String dataSetRowName_ )
  {
    m_dataSetRowName = dataSetRowName_;
  }

  /**
   * 
   * Getter da Propriedade m_name.
   * 
   * @return valor da Propriedade m_name.
   */
  public String getName()
  {
    return m_name;
  }

  /**
   * 
   * Setter da propriedade m_name.
   * 
   * @param dataSetRowName_ - Valor da propriedade m_name.
   */
  public void setName( String name_ )
  {
    m_name = name_;
  }

  /**
   * 
   * Getter da Propriedade m_property.
   * 
   * @return valor da Propriedade m_property.
   */
  public String getProperty()
  {
    return m_property;
  }

  /**
   * 
   * Setter da propriedade m_property.
   * 
   * @param dataSetRowName_ - Valor da propriedade m_property.
   */
  public void setProperty( String property_ )
  {
    m_property = property_;
  }

  /**
   * 
   * Getter da Propriedade m_scope.
   * 
   * @return valor da Propriedade m_scope.
   */
  public String getScope()
  {
    return m_scope;
  }

  /**
   * 
   * Setter da propriedade m_scope.
   * 
   * @param dataSetRowName_ - Valor da propriedade m_scope.
   */
  public void setScope( String scope_ )
  {
    m_scope = scope_;
  }

  /**
   * 
   * Getter da Propriedade m_sequenceRestartStep.
   * 
   * @return valor da Propriedade m_sequenceRestartStep.
   */
  public int getSequenceRestartStep()
  {
    return m_sequenceRestartStep;
  }

  /**
   * 
   * Setter da propriedade m_sequenceRestartStep.
   * 
   * @param dataSetRowName_ - Valor da propriedade m_sequenceRestartStep.
   */
  public void setSequenceRestartStep( int sequenceRestartStep_ )
  {
    m_sequenceRestartStep = sequenceRestartStep_;
  }

  /**
   * 
   * Getter da Propriedade m_stepIndexName.
   * 
   * @return valor da Propriedade m_stepIndexName.
   */
  public String getStepIndexName()
  {
    return m_stepIndexName;
  }

  /**
   * 
   * Setter da propriedade m_stepIndexName.
   * 
   * @param dataSetRowName_ - Valor da propriedade m_stepIndexName.
   */
  public void setStepIndexName( String stepIndexName_ )
  {
    m_stepIndexName = stepIndexName_;
  }

  /*
   * Pesquisa pelo DataSet e, caso exista e possua pelo menos uma linha, publica
   * a primeira linha.
   * @see javax.servlet.jsp.tagext.Tag#doStartTag()
   */
  public int doStartTag() throws JspException
  {
    int tagAction = SKIP_BODY;
    Object dataSet = null;
    //procura pelo bean indicado
    dataSet = TagUtils.getInstance().lookup( pageContext, m_name, m_property,
                                             m_scope );

    if ( dataSet != null )
    {
      // Verifica se o bean obtido é um data set
      if ( !( dataSet instanceof DataSet ) )
      {
        JspException e = new JspException(
                                           ms_messages.getMessage(
                                                                   C_MESSAGE_DATASETROWS_INSTANCE,
                                                                   m_name,
                                                                   m_property ) );
        TagUtils.getInstance().saveException( pageContext, e );
        throw e;
      }
    }

    //Verifica se o stepIndexName e o sequenceRestartStep estão consistentes
    if ( m_stepIndexName != null && "".equals( m_stepIndexName ) )
    {
      JspException e = new JspException(
                                         ms_messages.getMessage( C_MESSAGE_DATASETROWS_STEPINDEXNAME_EMPTY ) );
      TagUtils.getInstance().saveException( pageContext, e );
      throw e;
    }
    if ( ( m_sequenceRestartStep == 0 && m_stepIndexName != null )
         || ( m_sequenceRestartStep > 0 && m_stepIndexName == null ) )
    {
      JspException e = new JspException(
                                         ms_messages.getMessage( C_MESSAGE_DATASETROWS_STEPINDEXNAME_SEQUENCERESTARTSTEP_COMBO_INVALID ) );
      TagUtils.getInstance().saveException( pageContext, e );
      throw e;
    }

    m_dataSet = ( DataSet ) dataSet;
    if ( m_dataSet != null && m_dataSet.size() > 0 )
    {
      if ( m_dataSetRowName == null || "".equals( m_dataSetRowName ) )
      {
        m_dataSetRowName = C_DATA_SET_ROW_NAME_DEFAULT;
      }
      publishData();
      tagAction = EVAL_BODY_BUFFERED;
    }
    return tagAction;
  }

  /*
   * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
   */
  public int doAfterBody() throws JspException
  {
    int tagAction = SKIP_BODY;
    if ( bodyContent != null )
    {
      TagUtils.getInstance().writePrevious( pageContext,
                                            bodyContent.getString() );
      bodyContent.clearBody();
    }

    if ( m_rowIndex < m_dataSet.size() - 1 )
    {
      m_rowIndex = m_rowIndex + 1;
      publishData();
      tagAction = EVAL_BODY_BUFFERED;
    }
    else
    {
      pageContext.removeAttribute( m_dataSetRowName );
      if ( m_stepIndexName != null )
      {
        pageContext.removeAttribute( m_stepIndexName );
      }
    }

    return tagAction;

  }

  /**
   * 
   * Publica a linha corrente do DataSet e, caso esteja especificado, o número
   * do passo determinado pelo número da linha corrente do DataSet.
   *  
   */
  private void publishData()
  {
    DataSetRow dataSetRow = m_dataSet.getRow( m_rowIndex );
    pageContext.setAttribute( m_dataSetRowName, dataSetRow );

    if ( m_stepIndexName != null )
    {
      pageContext.setAttribute(
                                m_stepIndexName,
                                new Integer( m_rowIndex % m_sequenceRestartStep ) );
    }
  }

  /*
   * @see javax.servlet.jsp.tagext.Tag#doEndTag()
   */
  public int doEndTag() throws JspException
  {
    return ( EVAL_PAGE );
  }

  /**
   * Libera os recursos utilizados pela custom tag.
   */
  public void release()
  {
    super.release();

    m_dataSet = null;
    m_dataSetRowName = null;
    m_name = null;
    m_property = null;
    m_rowIndex = 0;
    m_scope = null;
  }
}