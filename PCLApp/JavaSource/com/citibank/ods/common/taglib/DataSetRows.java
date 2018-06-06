/*
 * Created on Jan 12, 2007
 *
 */
package com.citibank.ods.common.taglib;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;

/**
 * @author User
 *  
 */
public class DataSetRows extends BaseBodyTag
{
  private static final String C_DATA_SET_ROW_NAME_DEFAULT = "dataSetRow";

  private static final String C_LOCAL_STRINGS_NAME = "com.citibank.ods.common.taglib";

  private static final String C_MESSAGE_DATASETROWS_INSTANCE = "datasetrows.instance";

  private static final String C_MESSAGE_DATASETROWS_STEPINDEXNAME_EMPTY = "datasetrows.stepIndexName.empty";

  private static final String C_MESSAGE_DATASETROWS_STEPINDEXNAME_SEQUENCERESTARTSTEP_COMBO_INVALID = "datasetrows.stepIndexName.sequenceRestartStep.combo.invalid";

  private DataSet m_dataSet;

  private int m_rowIndex = 0;

  protected static MessageResources ms_messages = MessageResources.getMessageResources( C_LOCAL_STRINGS_NAME );

  private String m_name = null;

  private String m_property = null;

  private String m_dataSetRowName = null;

  private String m_scope = null;

  private int m_sequenceRestartStep = 0;

  private String m_stepIndexName = null;

  public String getDataSetRowName()
  {
    return m_dataSetRowName;
  }

  public void setDataSetRowName( String dataSetRowName_ )
  {
    m_dataSetRowName = dataSetRowName_;
  }

  public String getName()
  {
    return m_name;
  }

  public void setName( String name_ )
  {
    m_name = name_;
  }

  public String getProperty()
  {
    return m_property;
  }

  public void setProperty( String property_ )
  {
    m_property = property_;
  }

  public String getScope()
  {
    return m_scope;
  }

  public void setScope( String scope_ )
  {
    m_scope = scope_;
  }

  public int getSequenceRestartStep()
  {
    return m_sequenceRestartStep;
  }

  public void setSequenceRestartStep( int sequenceRestartStep_ )
  {
    m_sequenceRestartStep = sequenceRestartStep_;
  }

  public String getStepIndexName()
  {
    return m_stepIndexName;
  }

  public void setStepIndexName( String stepIndexName_ )
  {
    m_stepIndexName = stepIndexName_;
  }

  public int doStartTag() throws JspException
  {
    int tagAction = SKIP_BODY;
    Object dataSet = null;
    //procura pelo bean indicado
    dataSet = TagUtils.getInstance().lookup( pageContext, m_name, m_property,
                                             m_scope );

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

  public int doEndTag() throws JspException
  {
    return ( EVAL_PAGE );
  }

  /**
   * Release all allocated resources.
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