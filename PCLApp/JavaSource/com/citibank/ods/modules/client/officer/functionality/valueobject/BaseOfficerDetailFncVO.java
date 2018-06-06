package com.citibank.ods.modules.client.officer.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTbgOfficerEntity;
import com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.officer.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 *  
 */

public class BaseOfficerDetailFncVO extends BaseFncVO
{

  protected BaseTbgOfficerEntity m_tbgOfficerEntity;

  protected BaseTplOfficerCmplEntity m_tplOfficerCmplEntity;

  private DataSet OffcrStatCodeDomain;

  private DataSet OffcrCatCodeDomain;

  private DataSet m_offcrTypeCodeDomain;

  private String m_existingData = "";

  /**
   * @return Returns tplOfficerCmplEntity.
   */
  public BaseTbgOfficerEntity getBaseTbgOfficerEntity()
  {
    return m_tbgOfficerEntity;
  }

  /**
   * @param tplOfficerCmplEntity_ Field tplOfficerCmplEntity to be setted.
   */
  public void setBaseTbgOfficerEntity( BaseTbgOfficerEntity m_tbgOfficerEntity_ )
  {
    m_tbgOfficerEntity = m_tbgOfficerEntity_;
  }

  /**
   * @return Returns tbgOfficerEntity.
   */
  public BaseTbgOfficerEntity getTbgOfficerEntity()
  {
    return m_tbgOfficerEntity;
  }

  /**
   * @param tbgOfficerEntity_ Field tbgOfficerEntity to be setted.
   */
  public void setTbgOfficerEntity( BaseTbgOfficerEntity tbgOfficerEntity_ )
  {
    m_tbgOfficerEntity = tbgOfficerEntity_;
  }

  /**
   * @return Returns offcrCatCodeDomain.
   */
  public DataSet getOffcrCatCodeDomain()
  {
    return OffcrCatCodeDomain;
  }

  /**
   * @param offcrCatCodeDomain_ Field offcrCatCodeDomain to be setted.
   */
  public void setOffcrCatCodeDomain( DataSet offcrCatCodeDomain_ )
  {
    OffcrCatCodeDomain = offcrCatCodeDomain_;
  }

  /**
   * @return Returns offcrStatCodeDomain.
   */
  public DataSet getOffcrStatCodeDomain()
  {
    return OffcrStatCodeDomain;
  }

  /**
   * @param offcrStatCodeDomain_ Field offcrStatCodeDomain to be setted.
   */
  public void setOffcrStatCodeDomain( DataSet offcrStatCodeDomain_ )
  {
    OffcrStatCodeDomain = offcrStatCodeDomain_;
  }

  /**
   * @return Returns offcrTypeCodeDomain.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }

  /**
   * @param offcrTypeCodeDomain_ Field offcrTypeCodeDomain to be setted.
   */
  public void setOffcrTypeCodeDomain( DataSet offcrTypeCodeDomain_ )
  {
    m_offcrTypeCodeDomain = offcrTypeCodeDomain_;
  }


  /**
   * @return Returns tplOfficerCmplEntity.
   */
  public BaseTplOfficerCmplEntity getTplOfficerCmplEntity()
  {
    return m_tplOfficerCmplEntity;
  }
  /**
   * @param tplOfficerCmplEntity_ Field tplOfficerCmplEntity to be setted.
   */
  public void setTplOfficerCmplEntity(
                                      BaseTplOfficerCmplEntity tplOfficerCmplEntity_ )
  {
    m_tplOfficerCmplEntity = tplOfficerCmplEntity_;
  }
  /**
   * @return Returns existingData.
   */
  public String getExistingData()
  {
    return m_existingData;
  }

  /**
   * @param existingData_ Field existingData to be setted.
   */
  public void setExistingData( String existingData_ )
  {
    m_existingData = existingData_;
  }
}