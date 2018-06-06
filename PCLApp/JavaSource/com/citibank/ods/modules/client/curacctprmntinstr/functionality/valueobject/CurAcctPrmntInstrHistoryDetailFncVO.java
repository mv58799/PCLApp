package com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;

import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrHistEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;

/**
 * @author michele.monteiro
 *  
 */

public class CurAcctPrmntInstrHistoryDetailFncVO extends
    BaseCurAcctPrmntInstrDetailFncVO
{

  private TplCurAcctPrmntInstrHistEntity insertIP = null;

  private TplCurAcctPrmntInstrHistEntity deleteIP = null;

  private TplIpDocPrvtEntity tplIpDocPrvtEntity = null;

  private To3ProductAccountEntity to3ProductAccountEntity = null;

  private TbgPointAcctInvstEntity tbgPointAcctInvstEntity = null;

  /**
   * Data de Referencia do registro no historico
   */
  private Date m_curAcctPrmntInstrRefDate;

  public CurAcctPrmntInstrHistoryDetailFncVO()
  {
    m_baseTplIpDocPrvtEntityList = new ArrayList();
    insertIP = new TplCurAcctPrmntInstrHistEntity();
    deleteIP = new TplCurAcctPrmntInstrHistEntity();
    tplIpDocPrvtEntity = new TplIpDocPrvtEntity();
    tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();
    to3ProductAccountEntity = new To3ProductAccountEntity();
  }

  /**
   * @return Returns insertIP.
   */
  public TplCurAcctPrmntInstrHistEntity getInsertIP()
  {
    return insertIP;
  }

  /**
   * @param insertIP_ Field insertIP to be setted.
   */
  public void setInsertIP( TplCurAcctPrmntInstrHistEntity insertIP_ )
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

  /**
   * @return Returns deleteIP.
   */
  public TplCurAcctPrmntInstrHistEntity getDeleteIP()
  {
    return deleteIP;
  }

  /**
   * @param deleteIP_ Field deleteIP to be setted.
   */
  public void setDeleteIP( TplCurAcctPrmntInstrHistEntity deleteIP_ )
  {
    deleteIP = deleteIP_;
  }

  /**
   * @return Returns curAcctPrmntInstrRefDate.
   */
  public Date getCurAcctPrmntInstrRefDate()
  {
    return m_curAcctPrmntInstrRefDate;
  }

  /**
   * @param curAcctPrmntInstrRefDate_ Field curAcctPrmntInstrRefDate to be
   *          setted.
   */
  public void setCurAcctPrmntInstrRefDate( Date curAcctPrmntInstrRefDate_ )
  {
    m_curAcctPrmntInstrRefDate = curAcctPrmntInstrRefDate_;
  }
}