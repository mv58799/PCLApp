package com.citibank.ods.modules.client.relationeg.functionality.valueobject;

/**
 * @author leonardo.nakada
 *  
 */

public class RelationEgMovementDetailFncVO extends BaseRelationEgDetailFncVO
{
  private String m_opernCode;

  public RelationEgMovementDetailFncVO()
  {
    //
  }

  /**
   * @return Returns the opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ The opernCode to set.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}