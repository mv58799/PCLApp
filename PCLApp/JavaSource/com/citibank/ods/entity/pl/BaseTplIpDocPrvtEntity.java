package com.citibank.ods.entity.pl;

import java.util.ArrayList;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplIpDocPrvtEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela : BaseTplIpDocPrvt
 * @author Hamilton Matos
 * @date 11/04/2007
 */

public class BaseTplIpDocPrvtEntity extends BaseODSEntity
{
  public static final int C_CUST_NBR_SIZE = 11;

  public static final int C_IP_DOC_CODE_SIZE = 6;

  public static final int C_IP_DOC_TEXT_SIZE = 40;

  public static final int C_IP_INVST_CUR_ACCT_IND_SIZE = 1;

  public static final int C_LAST_AUTH_USER_ID_SIZE = 20;

  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

  public static final int C_REC_STAT_CODE_SIZE = 1;

  protected BaseTplIpDocPrvtEntityVO m_data;
  
  protected ArrayList m_baseDocTransferEntities;

  protected ArrayList m_baseIpDocCallbackEntities;

  /**
   * @return Returns baseDocTransferEntities.
   */
  public ArrayList getBaseDocTransferEntities()
  {
    return m_baseDocTransferEntities;
  }

  /**
   * @param baseDocTransferEntities_ Field baseDocTransferEntities to be setted.
   */
  public void setBaseDocTransferEntities( ArrayList baseDocTransferEntities_ )
  {
    m_baseDocTransferEntities = baseDocTransferEntities_;
  }

  /**
   * @return Returns baseIpDocCallbackEntities.
   */
  public ArrayList getBaseIpDocCallbackEntities()
  {
    return m_baseIpDocCallbackEntities;
  }

  /**
   * @param baseIpDocCallbackEntities_ Field baseIpDocCallbackEntities to be
   *          setted.
   */
  public void setBaseIpDocCallbackEntities( ArrayList baseIpDocCallbackEntities_ )
  {
    m_baseIpDocCallbackEntities = baseIpDocCallbackEntities_;
  }

  /**
   * @return Returns data.
   */
  public BaseTplIpDocPrvtEntityVO getData()
  {
    return m_data;
  }

  /**
   * @param data_ Field data to be setted.
   */
  public void setData( BaseTplIpDocPrvtEntityVO data_ )
  {
    m_data = data_;
  }
}