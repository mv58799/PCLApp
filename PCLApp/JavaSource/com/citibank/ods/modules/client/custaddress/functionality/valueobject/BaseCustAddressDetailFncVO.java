package com.citibank.ods.modules.client.custaddress.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.bg.BaseTbgCustAddressEntity;
/**
 * @author hamilton.matos
 */

public abstract class BaseCustAddressDetailFncVO extends BaseODSFncVO
{
  /**
   * Entity
   */
  protected BaseTbgCustAddressEntity m_tbgCustAddressEntity;

  // DataSet contendo o tipo do endereço
  private DataSet m_addrTypeCodeDomain;

  // DataSet contendo o dado de indicador de mala direta por email
  private DataSet m_emailMailIndDomain;

  // Indica se cliente receberá mala direta no celular
  private String m_cellMailInd = "";

  // Indica se cliente receberá mala direta no e-mail
  private String m_emailMailInd = "";

  // Tipo de endereço
  private String m_addrTypeCode = "";
  
  private DataSet m_custCellDomain;
  
  private DataSet m_custMailDomain;
  
  private DataSet m_cellMailIndDomain;
  

  /**
   * @return Returns tbgCustAddressEntity.
   */
  public BaseTbgCustAddressEntity getTbgCustAddressEntity()
  {
    return m_tbgCustAddressEntity;
  }

  /**
   * @param tbgCustAddressEntity_ Field tbgCustAddressEntity to be setted.
   */
  public void setTbgCustAddressEntity(
                                      BaseTbgCustAddressEntity tbgCustAddressEntity_ )
  {
    m_tbgCustAddressEntity = tbgCustAddressEntity_;
  }

  /**
   * @return Returns addrTypeCodeDomain.
   */
  public DataSet getAddrTypeCodeDomain()
  {
    return m_addrTypeCodeDomain;
  }

  /**
   * @param addrTypeCodeDomain_ Field addrTypeCodeDomain to be setted.
   */
  public void setAddrTypeCodeDomain( DataSet addrTypeCodeDomain_ )
  {
    m_addrTypeCodeDomain = addrTypeCodeDomain_;
  }

  /**
   * @return Returns emailMailIndDomain.
   */
  public DataSet getEmailMailIndDomain()
  {
    return m_emailMailIndDomain;
  }

  /**
   * @param emailMailIndDomain_ Field emailMailIndDomain to be setted.
   */
  public void setEmailMailIndDomain( DataSet emailMailIndDomain_ )
  {
    m_emailMailIndDomain = emailMailIndDomain_;
  }

  /**
   * @return Returns cellMailInd.
   */
  public String getCellMailInd()
  {
    return m_cellMailInd;
  }

  /**
   * @param cellMailInd_ Field cellMailInd to be setted.
   */
  public void setCellMailInd( String cellMailInd_ )
  {
    m_cellMailInd = cellMailInd_;
  }

  /**
   * @return Returns emailMailInd.
   */
  public String getEmailMailInd()
  {
    return m_emailMailInd;
  }

  /**
   * @param emailMailInd_ Field emailMailInd to be setted.
   */
  public void setEmailMailInd( String emailMailInd_ )
  {
    m_emailMailInd = emailMailInd_;
  }

  /**
   * @return Returns addrTypeCode.
   */
  public String getAddrTypeCode()
  {
    return m_addrTypeCode;
  }

  /**
   * @param addrTypeCode_ Field addrTypeCode to be setted.
   */
  public void setAddrTypeCode( String addrTypeCode_ )
  {
    m_addrTypeCode = addrTypeCode_;
  }
  /**
   * @return Returns custCellDomain.
   */
  public DataSet getCustCellDomain()
  {
    return m_custCellDomain;
  }
  /**
   * @param custCellDomain_ Field custCellDomain to be setted.
   */
  public void setCustCellDomain( DataSet custCellDomain_ )
  {
    m_custCellDomain = custCellDomain_;
  }
  /**
   * @return Returns custMailDomain.
   */
  public DataSet getCustMailDomain()
  {
    return m_custMailDomain;
  }
  /**
   * @param custMailDomain_ Field custMailDomain to be setted.
   */
  public void setCustMailDomain( DataSet custMailDomain_ )
  {
    m_custMailDomain = custMailDomain_;
  }
  
  /**
   * @return Returns cellMailIndDomain.
   */
  public DataSet getCellMailIndDomain()
  {
    return m_cellMailIndDomain;
  }

  /**
   * @param cellMailIndDomain_ Field cellMailIndDomain to be setted.
   */
  public void setCellMailIndDomain( DataSet cellMailIndDomain_ )
  {
    m_cellMailIndDomain = cellMailIndDomain_;
  }
  
}