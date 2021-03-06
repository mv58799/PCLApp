package com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;

/**
 * @author michele.monteiro
 *  
 */

public class CurAcctPrmntInstrMovementDetailFncVO extends
    BaseCurAcctPrmntInstrDetailFncVO
{

  private TplCurAcctPrmntInstrMovEntity insertIP = null;

  private TplCurAcctPrmntInstrMovEntity deleteIP = null;

  private TplIpDocPrvtEntity tplIpDocPrvtEntity = null;

  private To3ProductAccountEntity to3ProductAccountEntity = null;

  private TbgPointAcctInvstEntity tbgPointAcctInvstEntity = null;

  public CurAcctPrmntInstrMovementDetailFncVO()
  {
    m_baseTplIpDocPrvtEntityList = new ArrayList();
    insertIP = new TplCurAcctPrmntInstrMovEntity();
    deleteIP = new TplCurAcctPrmntInstrMovEntity();
    tplIpDocPrvtEntity = new TplIpDocPrvtEntity();
    tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();
    to3ProductAccountEntity = new To3ProductAccountEntity();
  }
  
  
  /**
   * @return Returns deleteIP.
   */
  public TplCurAcctPrmntInstrMovEntity getDeleteIP()
  {
    return deleteIP;
  }
  /**
   * @param deleteIP_ Field deleteIP to be setted.
   */
  public void setDeleteIP( TplCurAcctPrmntInstrMovEntity deleteIP_ )
  {
    deleteIP = deleteIP_;
  }
  /**
   * @return Returns insertIP.
   */
  public TplCurAcctPrmntInstrMovEntity getInsertIP()
  {
    return insertIP;
  }
  /**
   * @param insertIP_ Field insertIP to be setted.
   */
  public void setInsertIP( TplCurAcctPrmntInstrMovEntity insertIP_ )
  {
    insertIP = insertIP_;
  }
  /**
   * @return Returns tbgPointAcctInvstEntity.
   */
  public TbgPointAcctInvstEntity getTbgPointAcctInvstEntity()
  {
    return tbgPointAcctInvstEntity;
  }
  /**
   * @param tbgPointAcctInvstEntity_ Field tbgPointAcctInvstEntity to be setted.
   */
  public void setTbgPointAcctInvstEntity(
                                         TbgPointAcctInvstEntity tbgPointAcctInvstEntity_ )
  {
    tbgPointAcctInvstEntity = tbgPointAcctInvstEntity_;
  }
  /**
   * @return Returns to3ProductAccountEntity.
   */
  public To3ProductAccountEntity getTo3ProductAccountEntity()
  {
    return to3ProductAccountEntity;
  }
  /**
   * @param to3ProductAccountEntity_ Field to3ProductAccountEntity to be setted.
   */
  public void setTo3ProductAccountEntity(
                                         To3ProductAccountEntity to3ProductAccountEntity_ )
  {
    to3ProductAccountEntity = to3ProductAccountEntity_;
  }
  /**
   * @return Returns tplIpDocPrvtEntity.
   */
  public TplIpDocPrvtEntity getTplIpDocPrvtEntity()
  {
    return tplIpDocPrvtEntity;
  }
  /**
   * @param tplIpDocPrvtEntity_ Field tplIpDocPrvtEntity to be setted.
   */
  public void setTplIpDocPrvtEntity( TplIpDocPrvtEntity tplIpDocPrvtEntity_ )
  {
    tplIpDocPrvtEntity = tplIpDocPrvtEntity_;
  }
}