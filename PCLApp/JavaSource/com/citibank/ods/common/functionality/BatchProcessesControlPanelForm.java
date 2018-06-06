package com.citibank.ods.common.functionality;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class BatchProcessesControlPanelForm extends ActionForm
{
  public String getTestBatch1ErrorFlag()
  {
    return m_errorFlagsMap.get( "TestBatch1" ).toString();
  }

  public String getTestBatch1ThreadStatus()
  {
    return m_threadStatusMap.get( "TestBatch1" ).toString();
  }

  public String getTestBatch2ErrorFlag()
  {
    return m_errorFlagsMap.get( "TestBatch2" ).toString();
  }

  public String getTestBatch2ThreadStatus()
  {
    return m_threadStatusMap.get( "TestBatch2" ).toString();
  }

  public void setErrorFlagsMap( Map errorFlagsMap_ )
  {
    this.m_errorFlagsMap = errorFlagsMap_;
  }

  public void setThreadStatusMap( Map threadStatusMap_ )
  {
    this.m_threadStatusMap = threadStatusMap_;
  }

  private Map m_threadStatusMap = new HashMap();
  private Map m_errorFlagsMap = new HashMap();
}
