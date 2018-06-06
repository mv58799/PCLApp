package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplRelationPrvtEntityVO extends BaseEntityVO
{

  private BigInteger m_reltnNbr;

  private String m_reltnClassCode;

  private String m_reltnTypeCode;

  private String m_reltnCatCode;

  private String m_reltnSegCode;

  private BigInteger m_reltnCustAddrNbr;

  private BigInteger m_reltnCustAddrSeqNbr;

  private Date m_reltnEstabDate;

  private BigInteger m_reltnPrmtCode;

  private String m_reltnPortfCode;

  private String m_reltnStmtOptnCode;

  private String m_reltnClassScoreCode;

  private String m_reltnMfStmtInd;

  private String m_reltnSavStmtInd;

  private String m_reltnAcctStmtInd;

  private String m_reltnTdStmtInd;

  private BigInteger m_reltnCorpBaseNbr;

  private String m_reltnSpcfClassServPackInd;

  private String m_reltnRiskLevelCode;

  private BigInteger m_reltnAddrEmailCustNbr;

  private BigInteger m_reltnAddrEmailSeqNbr;

  private BigInteger m_reltnAddrCellCustNbr;

  private BigInteger m_reltnAddrCellSeqNbr;

  private String m_reltnCommTypeCode;

  private BigInteger m_reltnCust1Nbr;

  private BigInteger m_reltnCust2Nbr;

  private BigInteger m_reltnCust3Nbr;

  private BigInteger m_reltnCust4Nbr;

  private BigInteger m_reltnCust5Nbr;

  private String m_lastUpdateOpId;

  private Date m_lastUpdateDate;

  private String m_recStatCode;

  /**
   * @return Returns lastUpdateDate.
   */
  public Date getLastUpdateDate()
  {
    return m_lastUpdateDate;
  }

  /**
   * @param lastUpdateDate_ Field lastUpdateDate to be setted.
   */
  public void setLastUpdateDate( Date lastUpdateDate_ )
  {
    m_lastUpdateDate = lastUpdateDate_;
  }

  /**
   * @return Returns lastUpdateOpId.
   */
  public String getLastUpdateOpId()
  {
    return m_lastUpdateOpId;
  }

  /**
   * @param lastUpdateOpId_ Field lastUpdateOpId to be setted.
   */
  public void setLastUpdateOpId( String lastUpdateOpId_ )
  {
    m_lastUpdateOpId = lastUpdateOpId_;
  }

  /**
   * @return Returns recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * @return Returns reltnAcctStmtInd.
   */
  public String getReltnAcctStmtInd()
  {
    return m_reltnAcctStmtInd;
  }

  /**
   * @param reltnAcctStmtInd_ Field reltnAcctStmtInd to be setted.
   */
  public void setReltnAcctStmtInd( String reltnAcctStmtInd_ )
  {
    m_reltnAcctStmtInd = reltnAcctStmtInd_;
  }

  /**
   * @return Returns reltnAddrCellCustNbr.
   */
  public BigInteger getReltnAddrCellCustNbr()
  {
    return m_reltnAddrCellCustNbr;
  }

  /**
   * @param reltnAddrCellCustNbr_ Field reltnAddrCellCustNbr to be setted.
   */
  public void setReltnAddrCellCustNbr( BigInteger reltnAddrCellCustNbr_ )
  {
    m_reltnAddrCellCustNbr = reltnAddrCellCustNbr_;
  }

  /**
   * @return Returns reltnAddrCellSeqNbr.
   */
  public BigInteger getReltnAddrCellSeqNbr()
  {
    return m_reltnAddrCellSeqNbr;
  }

  /**
   * @param reltnAddrCellSeqNbr_ Field reltnAddrCellSeqNbr to be setted.
   */
  public void setReltnAddrCellSeqNbr( BigInteger reltnAddrCellSeqNbr_ )
  {
    m_reltnAddrCellSeqNbr = reltnAddrCellSeqNbr_;
  }

  /**
   * @return Returns reltnAddrEmailCustNbr.
   */
  public BigInteger getReltnAddrEmailCustNbr()
  {
    return m_reltnAddrEmailCustNbr;
  }

  /**
   * @param reltnAddrEmailCustNbr_ Field reltnAddrEmailCustNbr to be setted.
   */
  public void setReltnAddrEmailCustNbr( BigInteger reltnAddrEmailCustNbr_ )
  {
    m_reltnAddrEmailCustNbr = reltnAddrEmailCustNbr_;
  }

  /**
   * @return Returns reltnAddrEmailSeqNbr.
   */
  public BigInteger getReltnAddrEmailSeqNbr()
  {
    return m_reltnAddrEmailSeqNbr;
  }

  /**
   * @param reltnAddrEmailSeqNbr_ Field reltnAddrEmailSeqNbr to be setted.
   */
  public void setReltnAddrEmailSeqNbr( BigInteger reltnAddrEmailSeqNbr_ )
  {
    m_reltnAddrEmailSeqNbr = reltnAddrEmailSeqNbr_;
  }

  /**
   * @return Returns reltnCatCode.
   */
  public String getReltnCatCode()
  {
    return m_reltnCatCode;
  }

  /**
   * @param reltnCatCode_ Field reltnCatCode to be setted.
   */
  public void setReltnCatCode( String reltnCatCode_ )
  {
    m_reltnCatCode = reltnCatCode_;
  }

  /**
   * @return Returns reltnClassCode.
   */
  public String getReltnClassCode()
  {
    return m_reltnClassCode;
  }

  /**
   * @param reltnClassCode_ Field reltnClassCode to be setted.
   */
  public void setReltnClassCode( String reltnClassCode_ )
  {
    m_reltnClassCode = reltnClassCode_;
  }

  /**
   * @return Returns reltnClassScoreCode.
   */
  public String getReltnClassScoreCode()
  {
    return m_reltnClassScoreCode;
  }

  /**
   * @param reltnClassScoreCode_ Field reltnClassScoreCode to be setted.
   */
  public void setReltnClassScoreCode( String reltnClassScoreCode_ )
  {
    m_reltnClassScoreCode = reltnClassScoreCode_;
  }

  /**
   * @return Returns reltnCommTypeCode.
   */
  public String getReltnCommTypeCode()
  {
    return m_reltnCommTypeCode;
  }

  /**
   * @param reltnCommTypeCode_ Field reltnCommTypeCode to be setted.
   */
  public void setReltnCommTypeCode( String reltnCommTypeCode_ )
  {
    m_reltnCommTypeCode = reltnCommTypeCode_;
  }

  /**
   * @return Returns reltnCorpBaseNbr.
   */
  public BigInteger getReltnCorpBaseNbr()
  {
    return m_reltnCorpBaseNbr;
  }

  /**
   * @param reltnCorpBaseNbr_ Field reltnCorpBaseNbr to be setted.
   */
  public void setReltnCorpBaseNbr( BigInteger reltnCorpBaseNbr_ )
  {
    m_reltnCorpBaseNbr = reltnCorpBaseNbr_;
  }

  /**
   * @return Returns reltnCust1Nbr.
   */
  public BigInteger getReltnCust1Nbr()
  {
    return m_reltnCust1Nbr;
  }

  /**
   * @param reltnCust1Nbr_ Field reltnCust1Nbr to be setted.
   */
  public void setReltnCust1Nbr( BigInteger reltnCust1Nbr_ )
  {
    m_reltnCust1Nbr = reltnCust1Nbr_;
  }

  /**
   * @return Returns reltnCust2Nbr.
   */
  public BigInteger getReltnCust2Nbr()
  {
    return m_reltnCust2Nbr;
  }

  /**
   * @param reltnCust2Nbr_ Field reltnCust2Nbr to be setted.
   */
  public void setReltnCust2Nbr( BigInteger reltnCust2Nbr_ )
  {
    m_reltnCust2Nbr = reltnCust2Nbr_;
  }

  /**
   * @return Returns reltnCust3Nbr.
   */
  public BigInteger getReltnCust3Nbr()
  {
    return m_reltnCust3Nbr;
  }

  /**
   * @param reltnCust3Nbr_ Field reltnCust3Nbr to be setted.
   */
  public void setReltnCust3Nbr( BigInteger reltnCust3Nbr_ )
  {
    m_reltnCust3Nbr = reltnCust3Nbr_;
  }

  /**
   * @return Returns reltnCust4Nbr.
   */
  public BigInteger getReltnCust4Nbr()
  {
    return m_reltnCust4Nbr;
  }

  /**
   * @param reltnCust4Nbr_ Field reltnCust4Nbr to be setted.
   */
  public void setReltnCust4Nbr( BigInteger reltnCust4Nbr_ )
  {
    m_reltnCust4Nbr = reltnCust4Nbr_;
  }

  /**
   * @return Returns reltnCust5Nbr.
   */
  public BigInteger getReltnCust5Nbr()
  {
    return m_reltnCust5Nbr;
  }

  /**
   * @param reltnCust5Nbr_ Field reltnCust5Nbr to be setted.
   */
  public void setReltnCust5Nbr( BigInteger reltnCust5Nbr_ )
  {
    m_reltnCust5Nbr = reltnCust5Nbr_;
  }

  /**
   * @return Returns reltnCustAddrNbr.
   */
  public BigInteger getReltnCustAddrNbr()
  {
    return m_reltnCustAddrNbr;
  }

  /**
   * @param reltnCustAddrNbr_ Field reltnCustAddrNbr to be setted.
   */
  public void setReltnCustAddrNbr( BigInteger reltnCustAddrNbr_ )
  {
    m_reltnCustAddrNbr = reltnCustAddrNbr_;
  }

  /**
   * @return Returns reltnCustAddrSeqNbr.
   */
  public BigInteger getReltnCustAddrSeqNbr()
  {
    return m_reltnCustAddrSeqNbr;
  }

  /**
   * @param reltnCustAddrSeqNbr_ Field reltnCustAddrSeqNbr to be setted.
   */
  public void setReltnCustAddrSeqNbr( BigInteger reltnCustAddrSeqNbr_ )
  {
    m_reltnCustAddrSeqNbr = reltnCustAddrSeqNbr_;
  }

  /**
   * @return Returns reltnEstabDate.
   */
  public Date getReltnEstabDate()
  {
    return m_reltnEstabDate;
  }

  /**
   * @param reltnEstabDate_ Field reltnEstabDate to be setted.
   */
  public void setReltnEstabDate( Date reltnEstabDate_ )
  {
    m_reltnEstabDate = reltnEstabDate_;
  }

  /**
   * @return Returns reltnMfStmtInd.
   */
  public String getReltnMfStmtInd()
  {
    return m_reltnMfStmtInd;
  }

  /**
   * @param reltnMfStmtInd_ Field reltnMfStmtInd to be setted.
   */
  public void setReltnMfStmtInd( String reltnMfStmtInd_ )
  {
    m_reltnMfStmtInd = reltnMfStmtInd_;
  }

  /**
   * @return Returns reltnNbr.
   */
  public BigInteger getReltnNbr()
  {
    return m_reltnNbr;
  }

  /**
   * @param reltnNbr_ Field reltnNbr to be setted.
   */
  public void setReltnNbr( BigInteger reltnNbr_ )
  {
    m_reltnNbr = reltnNbr_;
  }

  /**
   * @return Returns reltnPortfCode.
   */
  public String getReltnPortfCode()
  {
    return m_reltnPortfCode;
  }

  /**
   * @param reltnPortfCode_ Field reltnPortfCode to be setted.
   */
  public void setReltnPortfCode( String reltnPortfCode_ )
  {
    m_reltnPortfCode = reltnPortfCode_;
  }

  /**
   * @return Returns reltnPrmtCode.
   */
  public BigInteger getReltnPrmtCode()
  {
    return m_reltnPrmtCode;
  }

  /**
   * @param reltnPrmtCode_ Field reltnPrmtCode to be setted.
   */
  public void setReltnPrmtCode( BigInteger reltnPrmtCode_ )
  {
    m_reltnPrmtCode = reltnPrmtCode_;
  }

  /**
   * @return Returns reltnRiskLevelCode.
   */
  public String getReltnRiskLevelCode()
  {
    return m_reltnRiskLevelCode;
  }

  /**
   * @param reltnRiskLevelCode_ Field reltnRiskLevelCode to be setted.
   */
  public void setReltnRiskLevelCode( String reltnRiskLevelCode_ )
  {
    m_reltnRiskLevelCode = reltnRiskLevelCode_;
  }

  /**
   * @return Returns reltnSavStmtInd.
   */
  public String getReltnSavStmtInd()
  {
    return m_reltnSavStmtInd;
  }

  /**
   * @param reltnSavStmtInd_ Field reltnSavStmtInd to be setted.
   */
  public void setReltnSavStmtInd( String reltnSavStmtInd_ )
  {
    m_reltnSavStmtInd = reltnSavStmtInd_;
  }

  /**
   * @return Returns reltnSegCode.
   */
  public String getReltnSegCode()
  {
    return m_reltnSegCode;
  }

  /**
   * @param reltnSegCode_ Field reltnSegCode to be setted.
   */
  public void setReltnSegCode( String reltnSegCode_ )
  {
    m_reltnSegCode = reltnSegCode_;
  }

  /**
   * @return Returns reltnSpcfClassServPackInd.
   */
  public String getReltnSpcfClassServPackInd()
  {
    return m_reltnSpcfClassServPackInd;
  }

  /**
   * @param reltnSpcfClassServPackInd_ Field reltnSpcfClassServPackInd to be
   *          setted.
   */
  public void setReltnSpcfClassServPackInd( String reltnSpcfClassServPackInd_ )
  {
    m_reltnSpcfClassServPackInd = reltnSpcfClassServPackInd_;
  }

  /**
   * @return Returns reltnStmtOptnCode.
   */
  public String getReltnStmtOptnCode()
  {
    return m_reltnStmtOptnCode;
  }

  /**
   * @param reltnStmtOptnCode_ Field reltnStmtOptnCode to be setted.
   */
  public void setReltnStmtOptnCode( String reltnStmtOptnCode_ )
  {
    m_reltnStmtOptnCode = reltnStmtOptnCode_;
  }

  /**
   * @return Returns reltnTdStmtInd.
   */
  public String getReltnTdStmtInd()
  {
    return m_reltnTdStmtInd;
  }

  /**
   * @param reltnTdStmtInd_ Field reltnTdStmtInd to be setted.
   */
  public void setReltnTdStmtInd( String reltnTdStmtInd_ )
  {
    m_reltnTdStmtInd = reltnTdStmtInd_;
  }

  /**
   * @return Returns reltnTypeCode.
   */
  public String getReltnTypeCode()
  {
    return m_reltnTypeCode;
  }

  /**
   * @param reltnTypeCode_ Field reltnTypeCode to be setted.
   */
  public void setReltnTypeCode( String reltnTypeCode_ )
  {
    m_reltnTypeCode = reltnTypeCode_;
  }
}