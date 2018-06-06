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
 * @author l.braga,14/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseTplCustomerPrvtEntityVO extends BaseEntityVO
{
  
  // Variável de controle de habilitação de botões de dados complementares
  private BigInteger cmplDataButtonControl;
  
  private BigInteger m_custNbr;
  
  private BigInteger m_custCpfCnpjNbr;
  
  private String m_invstCurAcctNbr;
  
  private BigInteger m_curAcctNbr;

  private String m_custShortNameText;

  private String m_custKeyNameText;

  private String m_custTypeCode;

  private String m_custFullNameText;
  
  private String m_custFullName2Text;
  
  private String m_custFullName3Text;
  
  private String m_custFullName4Text;

  private String m_custActlStatCode;

  private Date m_custStatDate;

  private Date m_custEstabDate;

  private BigInteger m_custEstabTime;

  private String m_custEstabOpId;

  private BigInteger m_custCnrLastMthAmt;

  private BigInteger m_custCnrYtdAmt;

  private BigInteger m_custCnrLastYrAmt;

  private String m_custCnrLastSixMthCode;

  private Date m_lastUpdateDate;

  private BigInteger m_lastUpdateTime;

  private String m_lastUpdateOpId;

  private Date m_apprvDate;

  private BigInteger m_apprvTime;

  private String m_custInputOrigCode;

  private String m_custDupCode;

  private String m_custCorpBkrApplPrflInd;

  private String m_custCorpFundApplPrflInd;

  private BigInteger m_custCetipNbr;

  private BigInteger m_custBmfNbr;

  private BigInteger m_custBovespaNbr;

  private BigInteger m_custBvrjNbr;

  private BigInteger m_custSelicNbr;

  private String m_custMktCatCode;

  private String m_custNoCpfInd;

  private String m_custNatId;

  private Date m_custNatIdApplDate;

  private String m_custNatIdEmitCode;

  private String m_custNatIdEmitName;

  private String m_custNatIdEmitStateCode;

  private String m_custCivilStatCode;

  private String m_custSexCode;

  private Date m_custBirthDate;

  private String m_custBirthCityText;

  private String m_custBirthStateCode;

  private String m_custBirthCntryCode;

  private String m_custProfCode;

  private String m_custEmplInd;

  private BigInteger m_custDepndNbr;

  private Date m_custDepndNbrDate;

  private String m_custOccupCode;

  private String m_custMgmtIncoMinSalCount;

  private Date m_custChkDate;

  private String m_custGrcardInd;

  private BigInteger m_custSocSctyNbr;

  private String m_custCpfOwnInd;

  private String m_custParentLevelInd;

  private String m_custUsaCtznAuthInd;

  private String m_custUsaCtznAuthOpId;

  private String m_custIndivPublicInd;

  private String m_custCitiGrpTieInd;

  private String m_custBirthCntryCoCode;

  private String m_custActyAreaCode;

  private String m_custNoCgcInd;

  private Date m_custFndtnDate;

  private String m_custCoTypeCode;

  private BigInteger m_custGrpCode;

  private BigInteger m_custSubGrpCode;

  private BigInteger m_custIntlNbr;

  private String m_custIrrfExemptInd;

  private String m_recStatCode;
  
  private BigInteger m_reltnNbr;

  
  // Informações referentes a dados complementares de cliente
  private BigInteger m_classCmplcCode;

  private BigInteger m_glbRevenSysOffcrNbr;

  private BigInteger m_offcrNbr;

  private BigInteger m_prvtCustNbr;

  private BigInteger m_prvtKeyNbr;

  private BigInteger m_wealthPotnlCode;

  private Date m_lastAuthDate;

  private Date m_lastUpdDate;

  private String m_emNbr;
  
  private String m_erNbr;

  private String m_lastAuthUserId;

  private String m_lastUpdUserId;

  private String m_mailRecvInd;

  private String m_offclMailRecvInd;
  
  /*
   * Objetivo: Exibição de informação de risco
   * Projeto: RDIP
   * Responsável: Eversystems
   * Data: 05/01/2011
   */
  private String m_rdipDescription;
  
  /**
   * @return Returns apprvDate.
   */
  public Date getApprvDate()
  {
    return m_apprvDate;
  }

  /**
   * @param apprvDate_ Field apprvDate to be setted.
   */
  public void setApprvDate( Date apprvDate_ )
  {
    m_apprvDate = apprvDate_;
  }

  /**
   * @return Returns apprvTime.
   */
  public BigInteger getApprvTime()
  {
    return m_apprvTime;
  }

  /**
   * @param apprvTime_ Field apprvTime to be setted.
   */
  public void setApprvTime( BigInteger apprvTime_ )
  {
    m_apprvTime = apprvTime_;
  }

  /**
   * @return Returns custActlStatCode.
   */
  public String getCustActlStatCode()
  {
    return m_custActlStatCode;
  }

  /**
   * @param custActlStatCode_ Field custActlStatCode to be setted.
   */
  public void setCustActlStatCode( String custActlStatCode_ )
  {
    m_custActlStatCode = custActlStatCode_;
  }

  /**
   * @return Returns custActyAreaCode.
   */
  public String getCustActyAreaCode()
  {
    return m_custActyAreaCode;
  }

  /**
   * @param custActyAreaCode_ Field custActyAreaCode to be setted.
   */
  public void setCustActyAreaCode( String custActyAreaCode_ )
  {
    m_custActyAreaCode = custActyAreaCode_;
  }

  /**
   * @return Returns custBirthCityText.
   */
  public String getCustBirthCityText()
  {
    return m_custBirthCityText;
  }

  /**
   * @param custBirthCityText_ Field custBirthCityText to be setted.
   */
  public void setCustBirthCityText( String custBirthCityText_ )
  {
    m_custBirthCityText = custBirthCityText_;
  }

  /**
   * @return Returns custBirthCntryCoCode.
   */
  public String getCustBirthCntryCoCode()
  {
    return m_custBirthCntryCoCode;
  }

  /**
   * @param custBirthCntryCoCode_ Field custBirthCntryCoCode to be setted.
   */
  public void setCustBirthCntryCoCode( String custBirthCntryCoCode_ )
  {
    m_custBirthCntryCoCode = custBirthCntryCoCode_;
  }

  /**
   * @return Returns custBirthCntryCode.
   */
  public String getCustBirthCntryCode()
  {
    return m_custBirthCntryCode;
  }

  /**
   * @param custBirthCntryCode_ Field custBirthCntryCode to be setted.
   */
  public void setCustBirthCntryCode( String custBirthCntryCode_ )
  {
    m_custBirthCntryCode = custBirthCntryCode_;
  }

  /**
   * @return Returns custBirthDate.
   */
  public Date getCustBirthDate()
  {
    return m_custBirthDate;
  }

  /**
   * @param custBirthDate_ Field custBirthDate to be setted.
   */
  public void setCustBirthDate( Date custBirthDate_ )
  {
    m_custBirthDate = custBirthDate_;
  }

  /**
   * @return Returns custBirthStateCode.
   */
  public String getCustBirthStateCode()
  {
    return m_custBirthStateCode;
  }

  /**
   * @param custBirthStateCode_ Field custBirthStateCode to be setted.
   */
  public void setCustBirthStateCode( String custBirthStateCode_ )
  {
    m_custBirthStateCode = custBirthStateCode_;
  }

  /**
   * @return Returns custBmfNbr.
   */
  public BigInteger getCustBmfNbr()
  {
    return m_custBmfNbr;
  }

  /**
   * @param custBmfNbr_ Field custBmfNbr to be setted.
   */
  public void setCustBmfNbr( BigInteger custBmfNbr_ )
  {
    m_custBmfNbr = custBmfNbr_;
  }

  /**
   * @return Returns custBovespaNbr.
   */
  public BigInteger getCustBovespaNbr()
  {
    return m_custBovespaNbr;
  }

  /**
   * @param custBovespaNbr_ Field custBovespaNbr to be setted.
   */
  public void setCustBovespaNbr( BigInteger custBovespaNbr_ )
  {
    m_custBovespaNbr = custBovespaNbr_;
  }

  /**
   * @return Returns custBvrjNbr.
   */
  public BigInteger getCustBvrjNbr()
  {
    return m_custBvrjNbr;
  }

  /**
   * @param custBvrjNbr_ Field custBvrjNbr to be setted.
   */
  public void setCustBvrjNbr( BigInteger custBvrjNbr_ )
  {
    m_custBvrjNbr = custBvrjNbr_;
  }

  /**
   * @return Returns custCetipNbr.
   */
  public BigInteger getCustCetipNbr()
  {
    return m_custCetipNbr;
  }

  /**
   * @param custCetipNbr_ Field custCetipNbr to be setted.
   */
  public void setCustCetipNbr( BigInteger custCetipNbr_ )
  {
    m_custCetipNbr = custCetipNbr_;
  }

  /**
   * @return Returns custChkDate.
   */
  public Date getCustChkDate()
  {
    return m_custChkDate;
  }

  /**
   * @param custChkDate_ Field custChkDate to be setted.
   */
  public void setCustChkDate( Date custChkDate_ )
  {
    m_custChkDate = custChkDate_;
  }

  /**
   * @return Returns custCitiGrpTieInd.
   */
  public String getCustCitiGrpTieInd()
  {
    return m_custCitiGrpTieInd;
  }

  /**
   * @param custCitiGrpTieInd_ Field custCitiGrpTieInd to be setted.
   */
  public void setCustCitiGrpTieInd( String custCitiGrpTieInd_ )
  {
    m_custCitiGrpTieInd = custCitiGrpTieInd_;
  }

  /**
   * @return Returns custCivilStatCode.
   */
  public String getCustCivilStatCode()
  {
    return m_custCivilStatCode;
  }

  /**
   * @param custCivilStatCode_ Field custCivilStatCode to be setted.
   */
  public void setCustCivilStatCode( String custCivilStatCode_ )
  {
    m_custCivilStatCode = custCivilStatCode_;
  }

  /**
   * @return Returns custCnrLastMthAmt.
   */
  public BigInteger getCustCnrLastMthAmt()
  {
    return m_custCnrLastMthAmt;
  }

  /**
   * @param custCnrLastMthAmt_ Field custCnrLastMthAmt to be setted.
   */
  public void setCustCnrLastMthAmt( BigInteger custCnrLastMthAmt_ )
  {
    m_custCnrLastMthAmt = custCnrLastMthAmt_;
  }

  /**
   * @return Returns custCnrLastSixMthCode.
   */
  public String getCustCnrLastSixMthCode()
  {
    return m_custCnrLastSixMthCode;
  }

  /**
   * @param custCnrLastSixMthCode_ Field custCnrLastSixMthCode to be setted.
   */
  public void setCustCnrLastSixMthCode( String custCnrLastSixMthCode_ )
  {
    m_custCnrLastSixMthCode = custCnrLastSixMthCode_;
  }

  /**
   * @return Returns custCnrLastYrAmt.
   */
  public BigInteger getCustCnrLastYrAmt()
  {
    return m_custCnrLastYrAmt;
  }

  /**
   * @param custCnrLastYrAmt_ Field custCnrLastYrAmt to be setted.
   */
  public void setCustCnrLastYrAmt( BigInteger custCnrLastYrAmt_ )
  {
    m_custCnrLastYrAmt = custCnrLastYrAmt_;
  }

  /**
   * @return Returns custCnrYtdAmt.
   */
  public BigInteger getCustCnrYtdAmt()
  {
    return m_custCnrYtdAmt;
  }

  /**
   * @param custCnrYtdAmt_ Field custCnrYtdAmt to be setted.
   */
  public void setCustCnrYtdAmt( BigInteger custCnrYtdAmt_ )
  {
    m_custCnrYtdAmt = custCnrYtdAmt_;
  }

  /**
   * @return Returns custCorpBkrApplPrflInd.
   */
  public String getCustCorpBkrApplPrflInd()
  {
    return m_custCorpBkrApplPrflInd;
  }

  /**
   * @param custCorpBkrApplPrflInd_ Field custCorpBkrApplPrflInd to be setted.
   */
  public void setCustCorpBkrApplPrflInd( String custCorpBkrApplPrflInd_ )
  {
    m_custCorpBkrApplPrflInd = custCorpBkrApplPrflInd_;
  }

  /**
   * @return Returns custCorpFundApplPrflInd.
   */
  public String getCustCorpFundApplPrflInd()
  {
    return m_custCorpFundApplPrflInd;
  }

  /**
   * @param custCorpFundApplPrflInd_ Field custCorpFundApplPrflInd to be setted.
   */
  public void setCustCorpFundApplPrflInd( String custCorpFundApplPrflInd_ )
  {
    m_custCorpFundApplPrflInd = custCorpFundApplPrflInd_;
  }

  /**
   * @return Returns custCoTypeCode.
   */
  public String getCustCoTypeCode()
  {
    return m_custCoTypeCode;
  }

  /**
   * @param custCoTypeCode_ Field custCoTypeCode to be setted.
   */
  public void setCustCoTypeCode( String custCoTypeCode_ )
  {
    m_custCoTypeCode = custCoTypeCode_;
  }

  /**
   * @return Returns custCpfCnpjNbr.
   */
  public BigInteger getCustCpfCnpjNbr()
  {
    return m_custCpfCnpjNbr;
  }

  /**
   * @param custCpfCnpjNbr_ Field custCpfCnpjNbr to be setted.
   */
  public void setCustCpfCnpjNbr( BigInteger custCpfCnpjNbr_ )
  {
    m_custCpfCnpjNbr = custCpfCnpjNbr_;
  }

  /**
   * @return Returns custCpfOwnInd.
   */
  public String getCustCpfOwnInd()
  {
    return m_custCpfOwnInd;
  }

  /**
   * @param custCpfOwnInd_ Field custCpfOwnInd to be setted.
   */
  public void setCustCpfOwnInd( String custCpfOwnInd_ )
  {
    m_custCpfOwnInd = custCpfOwnInd_;
  }

  /**
   * @return Returns custDepndNbr.
   */
  public BigInteger getCustDepndNbr()
  {
    return m_custDepndNbr;
  }

  /**
   * @param custDepndNbr_ Field custDepndNbr to be setted.
   */
  public void setCustDepndNbr( BigInteger custDepndNbr_ )
  {
    m_custDepndNbr = custDepndNbr_;
  }

  /**
   * @return Returns custDepndNbrDate.
   */
  public Date getCustDepndNbrDate()
  {
    return m_custDepndNbrDate;
  }

  /**
   * @param custDepndNbrDate_ Field custDepndNbrDate to be setted.
   */
  public void setCustDepndNbrDate( Date custDepndNbrDate_ )
  {
    m_custDepndNbrDate = custDepndNbrDate_;
  }

  /**
   * @return Returns custDupCode.
   */
  public String getCustDupCode()
  {
    return m_custDupCode;
  }

  /**
   * @param custDupCode_ Field custDupCode to be setted.
   */
  public void setCustDupCode( String custDupCode_ )
  {
    m_custDupCode = custDupCode_;
  }

  /**
   * @return Returns custEmplInd.
   */
  public String getCustEmplInd()
  {
    return m_custEmplInd;
  }

  /**
   * @param custEmplInd_ Field custEmplInd to be setted.
   */
  public void setCustEmplInd( String custEmplInd_ )
  {
    m_custEmplInd = custEmplInd_;
  }

  /**
   * @return Returns custEstabDate.
   */
  public Date getCustEstabDate()
  {
    return m_custEstabDate;
  }

  /**
   * @param custEstabDate_ Field custEstabDate to be setted.
   */
  public void setCustEstabDate( Date custEstabDate_ )
  {
    m_custEstabDate = custEstabDate_;
  }

  /**
   * @return Returns custEstabOpId.
   */
  public String getCustEstabOpId()
  {
    return m_custEstabOpId;
  }

  /**
   * @param custEstabOpId_ Field custEstabOpId to be setted.
   */
  public void setCustEstabOpId( String custEstabOpId_ )
  {
    m_custEstabOpId = custEstabOpId_;
  }

  /**
   * @return Returns custEstabTime.
   */
  public BigInteger getCustEstabTime()
  {
    return m_custEstabTime;
  }

  /**
   * @param custEstabTime_ Field custEstabTime to be setted.
   */
  public void setCustEstabTime( BigInteger custEstabTime_ )
  {
    m_custEstabTime = custEstabTime_;
  }

  /**
   * @return Returns custFndtnDate.
   */
  public Date getCustFndtnDate()
  {
    return m_custFndtnDate;
  }

  /**
   * @param custFndtnDate_ Field custFndtnDate to be setted.
   */
  public void setCustFndtnDate( Date custFndtnDate_ )
  {
    m_custFndtnDate = custFndtnDate_;
  }

  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }
  /**
	 * @return Returns custFullName2Text.
	 */
	public String getCustFullName2Text()
	{
	  return m_custFullName2Text;
	}

	/**
	 * @param custFullName2Text_ Field custFullName2Text to be setted.
	 */
	public void setCustFullName2Text( String custFullName2Text_ )
	{
	  m_custFullName2Text = custFullName2Text_;
	}
	/**
	   * @return Returns custFullName3Text.
	   */
	  public String getCustFullName3Text()
	  {
		return m_custFullName3Text;
	  }

	  /**
	   * @param custFullName3Text_ Field custFullName3Text to be setted.
	   */
	  public void setCustFullName3Text( String custFullName3Text_ )
	  {
		m_custFullName3Text = custFullName3Text_;
	  }
	/**
	   * @return Returns custFullName4Text.
	   */
	  public String getCustFullName4Text()
	  {
		return m_custFullName4Text;
	  }

	  /**
	   * @param custFullName4Text_ Field custFullName4Text to be setted.
	   */
	  public void setCustFullName4Text( String custFullName4Text_ )
	  {
		m_custFullName4Text = custFullName4Text_;
	  }

  /**
   * @return Returns custGrcardInd.
   */
  public String getCustGrcardInd()
  {
    return m_custGrcardInd;
  }

  /**
   * @param custGrcardInd_ Field custGrcardInd to be setted.
   */
  public void setCustGrcardInd( String custGrcardInd_ )
  {
    m_custGrcardInd = custGrcardInd_;
  }

  /**
   * @return Returns custGrpCode.
   */
  public BigInteger getCustGrpCode()
  {
    return m_custGrpCode;
  }

  /**
   * @param custGrpCode_ Field custGrpCode to be setted.
   */
  public void setCustGrpCode( BigInteger custGrpCode_ )
  {
    m_custGrpCode = custGrpCode_;
  }

  /**
   * @return Returns custIndivPublicInd.
   */
  public String getCustIndivPublicInd()
  {
    return m_custIndivPublicInd;
  }

  /**
   * @param custIndivPublicInd_ Field custIndivPublicInd to be setted.
   */
  public void setCustIndivPublicInd( String custIndivPublicInd_ )
  {
    m_custIndivPublicInd = custIndivPublicInd_;
  }

  /**
   * @return Returns custInputOrigCode.
   */
  public String getCustInputOrigCode()
  {
    return m_custInputOrigCode;
  }

  /**
   * @param custInputOrigCode_ Field custInputOrigCode to be setted.
   */
  public void setCustInputOrigCode( String custInputOrigCode_ )
  {
    m_custInputOrigCode = custInputOrigCode_;
  }

  /**
   * @return Returns custIntlNbr.
   */
  public BigInteger getCustIntlNbr()
  {
    return m_custIntlNbr;
  }

  /**
   * @param custIntlNbr_ Field custIntlNbr to be setted.
   */
  public void setCustIntlNbr( BigInteger custIntlNbr_ )
  {
    m_custIntlNbr = custIntlNbr_;
  }

  /**
   * @return Returns custIrrfExemptInd.
   */
  public String getCustIrrfExemptInd()
  {
    return m_custIrrfExemptInd;
  }

  /**
   * @param custIrrfExemptInd_ Field custIrrfExemptInd to be setted.
   */
  public void setCustIrrfExemptInd( String custIrrfExemptInd_ )
  {
    m_custIrrfExemptInd = custIrrfExemptInd_;
  }

  /**
   * @return Returns custKeyNameText.
   */
  public String getCustKeyNameText()
  {
    return m_custKeyNameText;
  }

  /**
   * @param custKeyNameText_ Field custKeyNameText to be setted.
   */
  public void setCustKeyNameText( String custKeyNameText_ )
  {
    m_custKeyNameText = custKeyNameText_;
  }

  /**
   * @return Returns custMgmtIncoMinSalCount.
   */
  public String getCustMgmtIncoMinSalCount()
  {
    return m_custMgmtIncoMinSalCount;
  }

  /**
   * @param custMgmtIncoMinSalCount_ Field custMgmtIncoMinSalCount to be setted.
   */
  public void setCustMgmtIncoMinSalCount( String custMgmtIncoMinSalCount_ )
  {
    m_custMgmtIncoMinSalCount = custMgmtIncoMinSalCount_;
  }

  /**
   * @return Returns custMktCatCode.
   */
  public String getCustMktCatCode()
  {
    return m_custMktCatCode;
  }

  /**
   * @param custMktCatCode_ Field custMktCatCode to be setted.
   */
  public void setCustMktCatCode( String custMktCatCode_ )
  {
    m_custMktCatCode = custMktCatCode_;
  }

  /**
   * @return Returns custNatId.
   */
  public String getCustNatId()
  {
    return m_custNatId;
  }

  /**
   * @param custNatId_ Field custNatId to be setted.
   */
  public void setCustNatId( String custNatId_ )
  {
    m_custNatId = custNatId_;
  }

  /**
   * @return Returns custNatIdApplDate.
   */
  public Date getCustNatIdApplDate()
  {
    return m_custNatIdApplDate;
  }

  /**
   * @param custNatIdApplDate_ Field custNatIdApplDate to be setted.
   */
  public void setCustNatIdApplDate( Date custNatIdApplDate_ )
  {
    m_custNatIdApplDate = custNatIdApplDate_;
  }

  /**
   * @return Returns custNatIdEmitCode.
   */
  public String getCustNatIdEmitCode()
  {
    return m_custNatIdEmitCode;
  }

  /**
   * @param custNatIdEmitCode_ Field custNatIdEmitCode to be setted.
   */
  public void setCustNatIdEmitCode( String custNatIdEmitCode_ )
  {
    m_custNatIdEmitCode = custNatIdEmitCode_;
  }

  /**
   * @return Returns custNatIdEmitName.
   */
  public String getCustNatIdEmitName()
  {
    return m_custNatIdEmitName;
  }

  /**
   * @param custNatIdEmitName_ Field custNatIdEmitName to be setted.
   */
  public void setCustNatIdEmitName( String custNatIdEmitName_ )
  {
    m_custNatIdEmitName = custNatIdEmitName_;
  }

  /**
   * @return Returns custNatIdEmitStateCode.
   */
  public String getCustNatIdEmitStateCode()
  {
    return m_custNatIdEmitStateCode;
  }

  /**
   * @param custNatIdEmitStateCode_ Field custNatIdEmitStateCode to be setted.
   */
  public void setCustNatIdEmitStateCode( String custNatIdEmitStateCode_ )
  {
    m_custNatIdEmitStateCode = custNatIdEmitStateCode_;
  }

  /**
   * @return Returns custNbr.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_ Field custNbr to be setted.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * @return Returns custNoCgcInd.
   */
  public String getCustNoCgcInd()
  {
    return m_custNoCgcInd;
  }

  /**
   * @param custNoCgcInd_ Field custNoCgcInd to be setted.
   */
  public void setCustNoCgcInd( String custNoCgcInd_ )
  {
    m_custNoCgcInd = custNoCgcInd_;
  }

  /**
   * @return Returns custNoCpfInd.
   */
  public String getCustNoCpfInd()
  {
    return m_custNoCpfInd;
  }

  /**
   * @param custNoCpfInd_ Field custNoCpfInd to be setted.
   */
  public void setCustNoCpfInd( String custNoCpfInd_ )
  {
    m_custNoCpfInd = custNoCpfInd_;
  }

  /**
   * @return Returns custOccupCode.
   */
  public String getCustOccupCode()
  {
    return m_custOccupCode;
  }

  /**
   * @param custOccupCode_ Field custOccupCode to be setted.
   */
  public void setCustOccupCode( String custOccupCode_ )
  {
    m_custOccupCode = custOccupCode_;
  }

  /**
   * @return Returns custParentLevelInd.
   */
  public String getCustParentLevelInd()
  {
    return m_custParentLevelInd;
  }

  /**
   * @param custParentLevelInd_ Field custParentLevelInd to be setted.
   */
  public void setCustParentLevelInd( String custParentLevelInd_ )
  {
    m_custParentLevelInd = custParentLevelInd_;
  }

  /**
   * @return Returns custProfCode.
   */
  public String getCustProfCode()
  {
    return m_custProfCode;
  }

  /**
   * @param custProfCode_ Field custProfCode to be setted.
   */
  public void setCustProfCode( String custProfCode_ )
  {
    m_custProfCode = custProfCode_;
  }

  /**
   * @return Returns custSelicNbr.
   */
  public BigInteger getCustSelicNbr()
  {
    return m_custSelicNbr;
  }

  /**
   * @param custSelicNbr_ Field custSelicNbr to be setted.
   */
  public void setCustSelicNbr( BigInteger custSelicNbr_ )
  {
    m_custSelicNbr = custSelicNbr_;
  }

  /**
   * @return Returns custSexCode.
   */
  public String getCustSexCode()
  {
    return m_custSexCode;
  }

  /**
   * @param custSexCode_ Field custSexCode to be setted.
   */
  public void setCustSexCode( String custSexCode_ )
  {
    m_custSexCode = custSexCode_;
  }

  /**
   * @return Returns custShortNameText.
   */
  public String getCustShortNameText()
  {
    return m_custShortNameText;
  }

  /**
   * @param custShortNameText_ Field custShortNameText to be setted.
   */
  public void setCustShortNameText( String custShortNameText_ )
  {
    m_custShortNameText = custShortNameText_;
  }

  /**
   * @return Returns custSocSctyNbr.
   */
  public BigInteger getCustSocSctyNbr()
  {
    return m_custSocSctyNbr;
  }

  /**
   * @param custSocSctyNbr_ Field custSocSctyNbr to be setted.
   */
  public void setCustSocSctyNbr( BigInteger custSocSctyNbr_ )
  {
    m_custSocSctyNbr = custSocSctyNbr_;
  }

  /**
   * @return Returns custStatDate.
   */
  public Date getCustStatDate()
  {
    return m_custStatDate;
  }

  /**
   * @param custStatDate_ Field custStatDate to be setted.
   */
  public void setCustStatDate( Date custStatDate_ )
  {
    m_custStatDate = custStatDate_;
  }

  /**
   * @return Returns custSubGrpCode.
   */
  public BigInteger getCustSubGrpCode()
  {
    return m_custSubGrpCode;
  }

  /**
   * @param custSubGrpCode_ Field custSubGrpCode to be setted.
   */
  public void setCustSubGrpCode( BigInteger custSubGrpCode_ )
  {
    m_custSubGrpCode = custSubGrpCode_;
  }

  /**
   * @return Returns custTypeCode.
   */
  public String getCustTypeCode()
  {
    return m_custTypeCode;
  }

  /**
   * @param custTypeCode_ Field custTypeCode to be setted.
   */
  public void setCustTypeCode( String custTypeCode_ )
  {
    m_custTypeCode = custTypeCode_;
  }

  /**
   * @return Returns custUsaCtznAuthInd.
   */
  public String getCustUsaCtznAuthInd()
  {
    return m_custUsaCtznAuthInd;
  }

  /**
   * @param custUsaCtznAuthInd_ Field custUsaCtznAuthInd to be setted.
   */
  public void setCustUsaCtznAuthInd( String custUsaCtznAuthInd_ )
  {
    m_custUsaCtznAuthInd = custUsaCtznAuthInd_;
  }

  /**
   * @return Returns custUsaCtznAuthOpId.
   */
  public String getCustUsaCtznAuthOpId()
  {
    return m_custUsaCtznAuthOpId;
  }

  /**
   * @param custUsaCtznAuthOpId_ Field custUsaCtznAuthOpId to be setted.
   */
  public void setCustUsaCtznAuthOpId( String custUsaCtznAuthOpId_ )
  {
    m_custUsaCtznAuthOpId = custUsaCtznAuthOpId_;
  }

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
   * @return Returns lastUpdateTime.
   */
  public BigInteger getLastUpdateTime()
  {
    return m_lastUpdateTime;
  }

  /**
   * @param lastUpdateTime_ Field lastUpdateTime to be setted.
   */
  public void setLastUpdateTime( BigInteger lastUpdateTime_ )
  {
    m_lastUpdateTime = lastUpdateTime_;
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
   * @return Returns classCmplcCode.
   */
  public BigInteger getClassCmplcCode()
  {
    return m_classCmplcCode;
  }
  /**
   * @param classCmplcCode_ Field classCmplcCode to be setted.
   */
  public void setClassCmplcCode( BigInteger classCmplcCode_ )
  {
    m_classCmplcCode = classCmplcCode_;
  }
  /**
   * @return Returns emNbr.
   */
  public String getEmNbr()
  {
    return m_emNbr;
  }
  /**
   * @param emNbr_ Field emNbr to be setted.
   */
  public void setEmNbr( String emNbr_ )
  {
    m_emNbr = emNbr_;
  }
  /**
   * @return Returns erNbr.
   */
  public String getErNbr()
  {
	return m_erNbr;
  }
  /**
   * @param erNbr_ Field erNbr to be setted.
   */
  public void setErNbr( String erNbr_ )
  {
	m_erNbr = erNbr_;
  }  
  /**
   * @return Returns glbRevenSysOffcrNbr.
   */
  public BigInteger getGlbRevenSysOffcrNbr()
  {
    return m_glbRevenSysOffcrNbr;
  }
  /**
   * @param glbRevenSysOffcrNbr_ Field glbRevenSysOffcrNbr to be setted.
   */
  public void setGlbRevenSysOffcrNbr( BigInteger glbRevenSysOffcrNbr_ )
  {
    m_glbRevenSysOffcrNbr = glbRevenSysOffcrNbr_;
  }
  /**
   * @return Returns lastAuthDate.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }
  /**
   * @param lastAuthDate_ Field lastAuthDate to be setted.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }
  /**
   * @return Returns lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }
  /**
   * @param lastAuthUserId_ Field lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }
  /**
   * @return Returns lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }
  /**
   * @param lastUpdDate_ Field lastUpdDate to be setted.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }
  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }
  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }
  /**
   * @return Returns mailRecvInd.
   */
  public String getMailRecvInd()
  {
    return m_mailRecvInd;
  }
  /**
   * @param mailRecvInd_ Field mailRecvInd to be setted.
   */
  public void setMailRecvInd( String mailRecvInd_ )
  {
    m_mailRecvInd = mailRecvInd_;
  }
  /**
   * @return Returns offclMailRecvInd.
   */
  public String getOffclMailRecvInd()
  {
    return m_offclMailRecvInd;
  }
  /**
   * @param offclMailRecvInd_ Field offclMailRecvInd to be setted.
   */
  public void setOffclMailRecvInd( String offclMailRecvInd_ )
  {
    m_offclMailRecvInd = offclMailRecvInd_;
  }
  /**
   * @return Returns offcrNbr.
   */
  public BigInteger getOffcrNbr()
  {
    return m_offcrNbr;
  }
  /**
   * @param offcrNbr_ Field offcrNbr to be setted.
   */
  public void setOffcrNbr( BigInteger offcrNbr_ )
  {
    m_offcrNbr = offcrNbr_;
  }
  /**
   * @return Returns prvtCustNbr.
   */
  public BigInteger getPrvtCustNbr()
  {
    return m_prvtCustNbr;
  }
  /**
   * @param prvtCustNbr_ Field prvtCustNbr to be setted.
   */
  public void setPrvtCustNbr( BigInteger prvtCustNbr_ )
  {
    m_prvtCustNbr = prvtCustNbr_;
  }
  /**
   * @return Returns prvtKeyNbr.
   */
  public BigInteger getPrvtKeyNbr()
  {
    return m_prvtKeyNbr;
  }
  /**
   * @param prvtKeyNbr_ Field prvtKeyNbr to be setted.
   */
  public void setPrvtKeyNbr( BigInteger prvKeyNbr_ )
  {
    m_prvtKeyNbr = prvKeyNbr_;
  }
  /**
   * @return Returns wealthPotnlCode.
   */
  public BigInteger getWealthPotnlCode()
  {
    return m_wealthPotnlCode;
  }
  /**
   * @param wealthPotnlCode_ Field wealthPotnlCode to be setted.
   */
  public void setWealthPotnlCode( BigInteger wealthPotnlCode_ )
  {
    m_wealthPotnlCode = wealthPotnlCode_;
  }

  /**
   * @return Returns cmplDataButtonControl.
   */
  public BigInteger getCmplDataButtonControl()
  {
    return cmplDataButtonControl;
  }
  /**
   * @param cmplDataButtonControl_ Field cmplDataButtonControl to be setted.
   */
  public void setCmplDataButtonControl( BigInteger cmplDataButtonControl_ )
  {
    cmplDataButtonControl = cmplDataButtonControl_;
  }
	/**
	 * @return
	 */
  public BigInteger getReltnNbr() {
	return m_reltnNbr;
  }

	/**
	 * @param integer
	 */
  public void setReltnNbr(BigInteger reltnNbr_) {
	m_reltnNbr = reltnNbr_;
  }

	/**
	 * @return
	 */
	public BigInteger getCurAcctNbr() {
		return m_curAcctNbr;
	}
	
	/**
	 * @return
	 */
	public String getInvstCurAcctNbr() {
		return m_invstCurAcctNbr;
	}
	
	/**
	 * @param integer
	 */
	public void setCurAcctNbr(BigInteger curAcctNbr_) {
		m_curAcctNbr = curAcctNbr_;
	}
	
	/**
	 * @param String
	 */
	public void setInvstCurAcctNbr(String invstCurAcctNbr_) {
		m_invstCurAcctNbr = invstCurAcctNbr_;
	}

	public String getRdipDescription() {
		return m_rdipDescription;
	}

	public void setRdipDescription(String description) {
		m_rdipDescription = description;
	}

}