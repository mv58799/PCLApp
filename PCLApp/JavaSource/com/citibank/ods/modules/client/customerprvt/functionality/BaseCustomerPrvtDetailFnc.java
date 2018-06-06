package com.citibank.ods.modules.client.customerprvt.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.customerprvt.form.BaseCustomerPrvtDetailForm;
import com.citibank.ods.modules.client.customerprvt.functionality.valueobject.BaseCustomerPrvtDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplPotentialWealthDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.customerPrvt.functionality;
 * @version 1.0
 * @author l.braga,14/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseCustomerPrvtDetailFnc extends BaseFnc
{

  public static final String C_EXISTING_DATA_CMPL = "1";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCustomerPrvtDetailForm form = ( BaseCustomerPrvtDetailForm ) form_;
    BaseCustomerPrvtDetailFncVO fncVO = ( BaseCustomerPrvtDetailFncVO ) fncVO_;

    BigInteger custNbr = ( form.getCustNbr() != null
                           && form.getCustNbr().length() > 0
                                                            ? new BigInteger(
                                                                              form.getCustNbr() )
                                                            : null );

    TplCustomerPrvtEntity tplCustomerPrvtEntity = new TplCustomerPrvtEntity();

    TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity = new TplCustomerPrvtCmplEntity();

    fncVO.setTplCustomerPrvtEntity( tplCustomerPrvtEntity );

    fncVO.setTplCustomerPrvtCmplEntity( tplCustomerPrvtCmplEntity );

    fncVO.getTplCustomerPrvtEntity().getData().setCustNbr( custNbr );

    // Atualizando os dados: Form -> FncVO
    fncVO.getTplCustomerPrvtCmplEntity().getData().setCustNbr(
                                                               toBigInteger(
                                                                             form,
                                                                             form.getCustNbr(),
                                                                             Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    fncVO.getTplCustomerPrvtCmplEntity().getData().setClassCmplcCode(
                                                                      toBigInteger(
                                                                                    form,
                                                                                    form.getClassCmplcCode(),
                                                                                    Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
                                                                                    
	fncVO.getTplCustomerPrvtCmplEntity().getData().setPrvtCustTypeCode( 
																	  toBigInteger(
																	                form,
																	                form.getPrvtCustTypeCode(),
																	                Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
    
    fncVO.getTplCustomerPrvtCmplEntity().getData().setOffcrNbr(
                                                                toBigInteger(
                                                                              form,
                                                                              form.getOffcrNbrSrc(),
                                                                              Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    fncVO.getTplCustomerPrvtCmplEntity().getData().setPrvtKeyNbr(
                                                                        toBigInteger(
                                                                                      form,
                                                                                      form.getPrvtKeyNbr(),
                                                                                      Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    fncVO.getTplCustomerPrvtCmplEntity().getData().setPrvtCustNbr(
                                                                   toBigInteger(
                                                                                 form,
                                                                                 form.getPrvtCustNbr(),
                                                                                 Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    fncVO.getTplCustomerPrvtCmplEntity().getData().setEmNbr( form.getEmNbr() );
    
	fncVO.getTplCustomerPrvtCmplEntity().getData().setErNbr( form.getErNbr() );
    
	fncVO.getTplCustomerPrvtCmplEntity().getData().setWealthPotnlCode(
                                                                       toBigInteger(
                                                                                     form,
                                                                                     form.getWealthPotnlCode(),
                                                                                     Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    fncVO.setClickedSearch( "" );

    if ( form.getMailRecvInd() != null
         && form.getMailRecvInd().equals(
                                          Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM ) )
    {
      fncVO.getTplCustomerPrvtCmplEntity().getData().setMailRecvInd(
                                                                     Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM );
    }
    else
    {
      fncVO.getTplCustomerPrvtCmplEntity().getData().setMailRecvInd(
                                                                     Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO );
    }

    if ( form.getOffclMailRecvInd() != null
         && form.getOffclMailRecvInd().equals(
                                               Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM ) )
    {
      fncVO.getTplCustomerPrvtCmplEntity().getData().setOffclMailRecvInd(
                                                                          Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM );
    }
    else
    {
      fncVO.getTplCustomerPrvtCmplEntity().getData().setOffclMailRecvInd(
                                                                          Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO );
    }

    fncVO.getTplCustomerPrvtCmplEntity().getData().setGlbRevenSysOffcrNbr(
                                                                           toBigInteger(
                                                                                         form,
                                                                                         form.getGlbRevenSysOffcrNbr(),
                                                                                         Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    fncVO.setCmplDataButtonControl( form.getCmplDataButtonControl() );
    String custPrvtStatCode = form.getCustPrvtStatCode() != null
                                                                ? form.getCustPrvtStatCode()
                                                                : "";
    fncVO.getTplCustomerPrvtCmplEntity().getData().setCustPrvtStatCode(
                                                                        custPrvtStatCode );
    
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCustomerPrvtDetailForm form = ( BaseCustomerPrvtDetailForm ) form_;
    BaseCustomerPrvtDetailFncVO fncVO = ( BaseCustomerPrvtDetailFncVO ) fncVO_;
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    String custNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNbr() != null
                                                                                      ? fncVO.getTplCustomerPrvtEntity().getData().getCustNbr().toString()
                                                                                      : null );
    String custCpfCnpjNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCpfCnpjNbr() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustCpfCnpjNbr().toString()
                                                                                                    : null );
    String custShortNameText = ( fncVO.getTplCustomerPrvtEntity().getData().getCustShortNameText() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustShortNameText().toString()
                                                                                                          : null );
    String custKeyNameText = ( fncVO.getTplCustomerPrvtEntity().getData().getCustKeyNameText() != null
                                                                                                      ? fncVO.getTplCustomerPrvtEntity().getData().getCustKeyNameText().toString()
                                                                                                      : null );
    String custTypeCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustTypeCode() != null
                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustTypeCode().toString()
                                                                                                : null );
    String custFullNameText = ( fncVO.getTplCustomerPrvtEntity().getData().getCustFullNameText() != null
                                                                                                        ? fncVO.getTplCustomerPrvtEntity().getData().getCustFullNameText().toString()
                                                                                                        : null );
    String custActlStatCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustActlStatCode() != null
                                                                                                        ? fncVO.getTplCustomerPrvtEntity().getData().getCustActlStatCode().toString()
                                                                                                        : null );

    String custStatDate = ( fncVO.getTplCustomerPrvtEntity().getData().getCustStatDate() != null
                                                                                                ? dateFormat.format( fncVO.getTplCustomerPrvtEntity().getData().getCustStatDate() )
                                                                                                : null );

    String custEstabDate = ( fncVO.getTplCustomerPrvtEntity().getData().getCustEstabDate() != null
                                                                                                  ? dateFormat.format( fncVO.getTplCustomerPrvtEntity().getData().getCustEstabDate() )
                                                                                                  : null );
    String custEstabTime = ( fncVO.getTplCustomerPrvtEntity().getData().getCustEstabTime() != null
                                                                                                  ? fncVO.getTplCustomerPrvtEntity().getData().getCustEstabTime().toString()
                                                                                                  : null );
    String custEstabOpId = ( fncVO.getTplCustomerPrvtEntity().getData().getCustEstabOpId() != null
                                                                                                  ? fncVO.getTplCustomerPrvtEntity().getData().getCustEstabOpId().toString()
                                                                                                  : null );
    String custCnrLastMthAmt = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCnrLastMthAmt() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustCnrLastMthAmt().toString()
                                                                                                          : null );
    String custCnrYtdAmt = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCnrYtdAmt() != null
                                                                                                  ? fncVO.getTplCustomerPrvtEntity().getData().getCustCnrYtdAmt().toString()
                                                                                                  : null );
    String custCnrLastYrAmt = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCnrLastYrAmt() != null
                                                                                                        ? fncVO.getTplCustomerPrvtEntity().getData().getCustCnrLastYrAmt().toString()
                                                                                                        : null );
    String custCnrLastSixMthCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCnrLastSixMthCode() != null
                                                                                                                  ? fncVO.getTplCustomerPrvtEntity().getData().getCustCnrLastSixMthCode().toString()
                                                                                                                  : null );
    String lastUpdateDate = ( fncVO.getTplCustomerPrvtEntity().getData().getLastUpdateDate() != null
                                                                                                    ? formatDateTime( fncVO.getTplCustomerPrvtEntity().getData().getLastUpdateDate() )
                                                                                                    : null );
    String lastUpdateTime = ( fncVO.getTplCustomerPrvtEntity().getData().getLastUpdateTime() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getLastUpdateTime().toString()
                                                                                                    : null );
    String lastUpdateOpId = ( fncVO.getTplCustomerPrvtEntity().getData().getLastUpdateOpId() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getLastUpdateOpId().toString()
                                                                                                    : null );
    String apprvDate = ( fncVO.getTplCustomerPrvtEntity().getData().getApprvDate() != null
                                                                                          ? formatDateTime( fncVO.getTplCustomerPrvtEntity().getData().getApprvDate() )
                                                                                          : null );
    String apprvTime = ( fncVO.getTplCustomerPrvtEntity().getData().getApprvTime() != null
                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getApprvTime().toString()
                                                                                          : null );
    String custInputOrigCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustInputOrigCode() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustInputOrigCode().toString()
                                                                                                          : null );
    String custDupCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustDupCode() != null
                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getCustDupCode().toString()
                                                                                              : null );
    String custCorpBkrApplPrflInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCorpBkrApplPrflInd() != null
                                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustCorpBkrApplPrflInd().toString()
                                                                                                                    : null );
    String custCorpFundApplPrflInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCorpFundApplPrflInd() != null
                                                                                                                      ? fncVO.getTplCustomerPrvtEntity().getData().getCustCorpFundApplPrflInd().toString()
                                                                                                                      : null );
    String custCetipNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCetipNbr() != null
                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustCetipNbr().toString()
                                                                                                : null );
    String custBmfNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBmfNbr() != null
                                                                                            ? fncVO.getTplCustomerPrvtEntity().getData().getCustBmfNbr().toString()
                                                                                            : null );
    String custBovespaNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBovespaNbr() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustBovespaNbr().toString()
                                                                                                    : null );
    String custBvrjNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBvrjNbr() != null
                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getCustBvrjNbr().toString()
                                                                                              : null );
    String custSelicNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustSelicNbr() != null
                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustSelicNbr().toString()
                                                                                                : null );
    String custMktCatCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustMktCatCode() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustMktCatCode().toString()
                                                                                                    : null );
    String custNoCpfInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNoCpfInd() != null
                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustNoCpfInd().toString()
                                                                                                : null );
    String custNatId = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNatId() != null
                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustNatId().toString()
                                                                                          : null );
    String custNatIdApplDate = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdApplDate() != null
                                                                                                          ? dateFormat.format( fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdApplDate() )
                                                                                                          : null );
    String custNatIdEmitCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdEmitCode() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdEmitCode().toString()
                                                                                                          : null );
    String custNatIdEmitName = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdEmitName() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdEmitName().toString()
                                                                                                          : null );
    String custNatIdEmitStateCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdEmitStateCode() != null
                                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustNatIdEmitStateCode().toString()
                                                                                                                    : null );
    String custCivilStatCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCivilStatCode() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustCivilStatCode().toString()
                                                                                                          : null );
    String custSexCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustSexCode() != null
                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getCustSexCode().toString()
                                                                                              : null );
    String custBirthDate = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBirthDate() != null
                                                                                                  ? dateFormat.format( fncVO.getTplCustomerPrvtEntity().getData().getCustBirthDate() )
                                                                                                  : null );
    String custBirthCityText = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBirthCityText() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustBirthCityText().toString()
                                                                                                          : null );
    String custBirthStateCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBirthStateCode() != null
                                                                                                            ? fncVO.getTplCustomerPrvtEntity().getData().getCustBirthStateCode().toString()
                                                                                                            : null );
    String custBirthCntryCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBirthCntryCode() != null
                                                                                                            ? fncVO.getTplCustomerPrvtEntity().getData().getCustBirthCntryCode().toString()
                                                                                                            : null );
    String custProfCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustProfCode() != null
                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustProfCode().toString()
                                                                                                : null );
    String custEmplInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustEmplInd() != null
                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getCustEmplInd().toString()
                                                                                              : null );
    String custDepndNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustDepndNbr() != null
                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustDepndNbr().toString()
                                                                                                : null );
    String custDepndNbrDate = ( fncVO.getTplCustomerPrvtEntity().getData().getCustDepndNbrDate() != null
                                                                                                        ? dateFormat.format( fncVO.getTplCustomerPrvtEntity().getData().getCustDepndNbrDate() )
                                                                                                        : null );
    String custOccupCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustOccupCode() != null
                                                                                                  ? fncVO.getTplCustomerPrvtEntity().getData().getCustOccupCode().toString()
                                                                                                  : null );
    String custMgmtIncoMinSalCount = ( fncVO.getTplCustomerPrvtEntity().getData().getCustMgmtIncoMinSalCount() != null
                                                                                                                      ? fncVO.getTplCustomerPrvtEntity().getData().getCustMgmtIncoMinSalCount().toString()
                                                                                                                      : null );
    String custChkDate = ( fncVO.getTplCustomerPrvtEntity().getData().getCustChkDate() != null
                                                                                              ? dateFormat.format( fncVO.getTplCustomerPrvtEntity().getData().getCustChkDate() )
                                                                                              : null );
    String custGrcardInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustGrcardInd() != null
                                                                                                  ? fncVO.getTplCustomerPrvtEntity().getData().getCustGrcardInd().toString()
                                                                                                  : null );
    String custSocSctyNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustSocSctyNbr() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustSocSctyNbr().toString()
                                                                                                    : null );
    String custCpfOwnInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCpfOwnInd() != null
                                                                                                  ? fncVO.getTplCustomerPrvtEntity().getData().getCustCpfOwnInd().toString()
                                                                                                  : null );
    String custParentLevelInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustParentLevelInd() != null
                                                                                                            ? fncVO.getTplCustomerPrvtEntity().getData().getCustParentLevelInd().toString()
                                                                                                            : null );
    String custUsaCtznAuthInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustUsaCtznAuthInd() != null
                                                                                                            ? fncVO.getTplCustomerPrvtEntity().getData().getCustUsaCtznAuthInd().toString()
                                                                                                            : null );
    String custUsaCtznAuthOpId = ( fncVO.getTplCustomerPrvtEntity().getData().getCustUsaCtznAuthOpId() != null
                                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getCustUsaCtznAuthOpId().toString()
                                                                                                              : null );
    String custIndivPublicInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustIndivPublicInd() != null
                                                                                                            ? fncVO.getTplCustomerPrvtEntity().getData().getCustIndivPublicInd().toString()
                                                                                                            : null );
    String custCitiGrpTieInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCitiGrpTieInd() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustCitiGrpTieInd().toString()
                                                                                                          : null );
    String custBirthCntryCoCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustBirthCntryCoCode() != null
                                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustBirthCntryCoCode().toString()
                                                                                                                : null );
    String custActyAreaCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustActyAreaCode() != null
                                                                                                        ? fncVO.getTplCustomerPrvtEntity().getData().getCustActyAreaCode().toString()
                                                                                                        : null );
    String custNoCgcInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustNoCgcInd() != null
                                                                                                ? fncVO.getTplCustomerPrvtEntity().getData().getCustNoCgcInd().toString()
                                                                                                : null );
    String custFndtnDate = ( fncVO.getTplCustomerPrvtEntity().getData().getCustFndtnDate() != null
                                                                                                  ? dateFormat.format( fncVO.getTplCustomerPrvtEntity().getData().getCustFndtnDate() )
                                                                                                  : null );
    String custCoTypeCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustCoTypeCode() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustCoTypeCode().toString()
                                                                                                    : null );
    String custGrpCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustGrpCode() != null
                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getCustGrpCode().toString()
                                                                                              : null );
    String custSubGrpCode = ( fncVO.getTplCustomerPrvtEntity().getData().getCustSubGrpCode() != null
                                                                                                    ? fncVO.getTplCustomerPrvtEntity().getData().getCustSubGrpCode().toString()
                                                                                                    : null );
    String custIntlNbr = ( fncVO.getTplCustomerPrvtEntity().getData().getCustIntlNbr() != null
                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getCustIntlNbr().toString()
                                                                                              : null );
    String custIrrfExemptInd = ( fncVO.getTplCustomerPrvtEntity().getData().getCustIrrfExemptInd() != null
                                                                                                          ? fncVO.getTplCustomerPrvtEntity().getData().getCustIrrfExemptInd().toString()
                                                                                                          : null );
    String recStatCode = ( fncVO.getTplCustomerPrvtEntity().getData().getRecStatCode() != null
                                                                                              ? fncVO.getTplCustomerPrvtEntity().getData().getRecStatCode().toString()
                                                                                              : null );
    DataSet custSexCodeDomain = fncVO.getCustSexCodeDomain() != null
                                                                    ? fncVO.getCustSexCodeDomain()
                                                                    : null;

    DataSet custCivilStatCodeDomain = fncVO.getCustCivilStatCodeDomain() != null
                                                                                ? fncVO.getCustCivilStatCodeDomain()
                                                                                : null;

    DataSet custMktCatCodeDomain = fncVO.getCustMktCatCodeDomain() != null
                                                                          ? fncVO.getCustCivilStatCodeDomain()
                                                                          : null;
    DataSet custDupCodeDomain = fncVO.getCustDupCodeDomain() != null
                                                                    ? fncVO.getCustMktCatCodeDomain()
                                                                    : null;
    DataSet custTypeCodeDomain = fncVO.getCustTypeCodeDomain() != null
                                                                      ? fncVO.getCustTypeCodeDomain()
                                                                      : null;
    DataSet custCitiGrpTieIndDomain = fncVO.getCustCitiGrpTieIndDomain() != null
                                                                                ? fncVO.getCustCitiGrpTieIndDomain()
                                                                                : null;
    DataSet custCorpBkrApplPrflIndDomain = fncVO.getCustCorpBkrApplPrflIndDomain() != null
                                                                                          ? fncVO.getCustCorpBkrApplPrflIndDomain()
                                                                                          : null;
    DataSet custCorpFundApplPrflIndDomain = fncVO.getCustCorpFundApplPrflIndDomain() != null
                                                                                            ? fncVO.getCustCorpFundApplPrflIndDomain()
                                                                                            : null;
    DataSet custCpfOwnIndDomain = fncVO.getCustCpfOwnIndDomain() != null
                                                                        ? fncVO.getCustCpfOwnIndDomain()
                                                                        : null;
    DataSet custEmplIndDomain = fncVO.getCustEmplIndDomain() != null
                                                                    ? fncVO.getCustEmplIndDomain()
                                                                    : null;
    DataSet custGrcardIndDomain = fncVO.getCustGrcardIndDomain() != null
                                                                        ? fncVO.getCustGrcardIndDomain()
                                                                        : null;
    DataSet custIndivPublicIndDomain = fncVO.getCustIndivPublicIndDomain() != null
                                                                                  ? fncVO.getCustIndivPublicIndDomain()
                                                                                  : null;
    DataSet custIrrfExemptIndDomain = fncVO.getCustIrrfExemptIndDomain() != null
                                                                                ? fncVO.getCustIrrfExemptIndDomain()
                                                                                : null;
    DataSet custNoCgcIndDomain = fncVO.getCustNoCgcIndDomain() != null
                                                                      ? fncVO.getCustNoCgcIndDomain()
                                                                      : null;
    DataSet custNoCpfIndDomain = fncVO.getCustNoCpfIndDomain() != null
                                                                      ? fncVO.getCustNoCpfIndDomain()
                                                                      : null;
    DataSet custParentLevelIndDomain = fncVO.getCustParentLevelIndDomain() != null
                                                                                  ? fncVO.getCustParentLevelIndDomain()
                                                                                  : null;
    DataSet custUsaCtznAuthIndDomain = fncVO.getCustUsaCtznAuthIndDomain() != null
                                                                                  ? fncVO.getCustUsaCtznAuthIndDomain()
                                                                                  : null;

 
    /* 
     * RDIP - Inclusão do descritivo de risco 
     * Data: 05/01/2011
     * Responsável: Eversystems
     */                                                                                  
    String rdipDescription = (fncVO.getTplCustomerPrvtEntity().getData().getRdipDescription() != null) ? 
    		fncVO.getTplCustomerPrvtEntity().getData().getRdipDescription():"";
                                                                                  
    form.setCustNbr( custNbr );
    form.setCustCpfCnpjNbr( custCpfCnpjNbr );
    form.setCustShortNameText( custShortNameText );
    form.setApprvDate( apprvDate );
    form.setApprvTime( apprvTime );
    form.setCustActlStatCode( custActlStatCode );
    form.setCustActyAreaCode( custActyAreaCode );
    form.setCustBirthCityText( custBirthCityText );
    form.setCustBirthCntryCode( custBirthCntryCode );
    form.setCustBirthCntryCoCode( custBirthCntryCoCode );
    form.setCustBirthDate( custBirthDate );
    form.setCustBirthStateCode( custBirthStateCode );
    form.setCustBmfNbr( custBmfNbr );
    form.setCustBovespaNbr( custBovespaNbr );
    form.setCustBvrjNbr( custBvrjNbr );
    form.setCustCetipNbr( custCetipNbr );
    form.setCustChkDate( custChkDate );
    form.setCustCitiGrpTieInd( custCitiGrpTieInd );
    form.setCustCivilStatCode( custCivilStatCode );
    form.setCustCnrLastMthAmt( custCnrLastMthAmt );
    form.setCustCnrLastSixMthCode( custCnrLastSixMthCode );
    form.setCustCnrLastYrAmt( custCnrLastYrAmt );
    form.setCustCnrYtdAmt( custCnrYtdAmt );
    form.setCustCorpBkrApplPrflInd( custCorpBkrApplPrflInd );
    form.setCustCorpFundApplPrflInd( custCorpFundApplPrflInd );
    form.setCustCoTypeCode( custCoTypeCode );
    form.setCustCpfOwnInd( custCpfOwnInd );
    form.setCustDepndNbr( custDepndNbr );
    form.setCustDepndNbrDate( custDepndNbrDate );
    form.setCustDupCode( custDupCode );
    form.setCustEmplInd( custEmplInd );
    form.setCustEstabDate( custEstabDate );
    form.setCustEstabOpId( custEstabOpId );
    form.setCustEstabTime( custEstabTime );
    form.setCustFndtnDate( custFndtnDate );
    form.setCustFullNameText( custFullNameText );
    form.setCustGrcardInd( custGrcardInd );
    form.setCustGrpCode( custGrpCode );
    form.setCustIndivPublicInd( custIndivPublicInd );
    form.setCustInputOrigCode( custInputOrigCode );
    form.setCustIntlNbr( custIntlNbr );
    form.setCustIrrfExemptInd( custIrrfExemptInd );
    form.setCustKeyNameText( custKeyNameText );
    form.setCustMgmtIncoMinSalCount( custMgmtIncoMinSalCount );
    form.setCustMktCatCode( custMktCatCode );
    form.setCustNatId( custNatId );
    form.setCustNatIdApplDate( custNatIdApplDate );
    form.setCustNatIdEmitCode( custNatIdEmitCode );
    form.setCustNatIdEmitName( custNatIdEmitName );
    form.setCustNatIdEmitStateCode( custNatIdEmitStateCode );
    form.setCustNoCgcInd( custNoCgcInd );
    form.setCustNoCpfInd( custNoCpfInd );
    form.setCustOccupCode( custOccupCode );
    form.setCustParentLevelInd( custParentLevelInd );
    form.setCustProfCode( custProfCode );
    form.setCustSelicNbr( custSelicNbr );
    form.setCustSexCode( custSexCode );
    form.setCustSocSctyNbr( custSocSctyNbr );
    form.setCustStatDate( custStatDate );
    form.setCustSubGrpCode( custSubGrpCode );
    form.setCustTypeCode( custTypeCode );
    form.setCustUsaCtznAuthInd( custUsaCtznAuthInd );
    form.setCustUsaCtznAuthOpId( custUsaCtznAuthOpId );
    form.setLastUpdateDate( lastUpdateDate );
    form.setLastUpdateOpId( lastUpdateOpId );
    form.setLastUpdateTime( lastUpdateTime );
    form.setRecStatCode( recStatCode );
    form.setCustSexCodeDomain( custSexCodeDomain );
    form.setCustCivilStatCodeDomain( custCivilStatCodeDomain );
    form.setCustMktCatCodeDomain( custMktCatCodeDomain );
    form.setCustDupCodeDomain( custDupCodeDomain );
    form.setCustTypeCodeDomain( custTypeCodeDomain );
    form.setCustCitiGrpTieIndDomain( custCitiGrpTieIndDomain );
    form.setCustCorpBkrApplPrflIndDomain( custCorpBkrApplPrflIndDomain );
    form.setCustCorpFundApplPrflIndDomain( custCorpFundApplPrflIndDomain );
    form.setCustCpfOwnIndDomain( custCpfOwnIndDomain );
    form.setCustEmplIndDomain( custEmplIndDomain );
    form.setCustGrcardIndDomain( custGrcardIndDomain );
    form.setCustIndivPublicIndDomain( custIndivPublicIndDomain );
    form.setCustIrrfExemptIndDomain( custIrrfExemptIndDomain );
    form.setCustNoCgcIndDomain( custNoCgcIndDomain );
    form.setCustNoCpfIndDomain( custNoCpfIndDomain );
    form.setCustParentLevelIndDomain( custParentLevelIndDomain );
    form.setCustUsaCtznAuthIndDomain( custUsaCtznAuthIndDomain );
    form.setCustPrvtStatCodeDomain( fncVO.getCustPrvtStatCodeDomain() );
    form.setRdipDescription(rdipDescription);

    form.setClassCmplcCode( fncVO.getTplCustomerPrvtCmplEntity().getData().getClassCmplcCode() != null
                                                                                                      ? fncVO.getTplCustomerPrvtCmplEntity().getData().getClassCmplcCode().toString()
                                                                                                      : "" );
	form.setPrvtCustTypeCode( fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtCustTypeCode() != null
																									  ? fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtCustTypeCode().toString()
																									  : "" );
	form.setCustNbrSrc( fncVO.getTplCustomerPrvtCmplEntity().getData().getCustNbr() != null
                                                                                           ? fncVO.getTplCustomerPrvtCmplEntity().getData().getCustNbr().toString()
                                                                                           : "" );
    form.setEmNbr( fncVO.getTplCustomerPrvtCmplEntity().getData().getEmNbr() != null
                                                                                    ? fncVO.getTplCustomerPrvtCmplEntity().getData().getEmNbr()
                                                                                    : "" );
	form.setErNbr( fncVO.getTplCustomerPrvtCmplEntity().getData().getErNbr() != null
																					? fncVO.getTplCustomerPrvtCmplEntity().getData().getErNbr()
																					: "" );

	form.setOffcrNbrSrc( fncVO.getTplCustomerPrvtCmplEntity().getData().getOffcrNbr() != null
                                                                                             ? fncVO.getTplCustomerPrvtCmplEntity().getData().getOffcrNbr().toString()
                                                                                             : "" );
    form.setPrvtKeyNbr( fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtKeyNbr() != null
                              && fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtKeyNbr().longValue() > 0
                                                                                                                     ? fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtKeyNbr().toString()
                                                                                                                     : "" );
    form.setPrvtCustNbr( fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtCustNbr() != null
                         && fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtCustNbr().longValue() > 0
                                                                                                           ? fncVO.getTplCustomerPrvtCmplEntity().getData().getPrvtCustNbr().toString()
                                                                                                           : "" );
    form.setWealthPotnlCode( fncVO.getTplCustomerPrvtCmplEntity().getData().getWealthPotnlCode() != null
                                                                                                        ? fncVO.getTplCustomerPrvtCmplEntity().getData().getWealthPotnlCode().toString()
                                                                                                        : "" );
    form.setMailRecvInd( fncVO.getTplCustomerPrvtCmplEntity().getData().getMailRecvInd() != null
                                                                                                ? fncVO.getTplCustomerPrvtCmplEntity().getData().getMailRecvInd()
                                                                                                : "" );
    form.setOffclMailRecvInd( fncVO.getTplCustomerPrvtCmplEntity().getData().getOffclMailRecvInd() != null
                                                                                                          ? fncVO.getTplCustomerPrvtCmplEntity().getData().getOffclMailRecvInd()
                                                                                                          : "" );
    
    form.setGlbRevenSysOffcrNbr( fncVO.getTplCustomerPrvtCmplEntity().getData().getGlbRevenSysOffcrNbr() != null
                                 && fncVO.getTplCustomerPrvtCmplEntity().getData().getGlbRevenSysOffcrNbr().longValue() > 0
                                                                                                          ? fncVO.getTplCustomerPrvtCmplEntity().getData().getGlbRevenSysOffcrNbr().toString()
	                                                                                                      : "" );
    form.setClassCmplcCodeDomain( fncVO.getClassCmplcCodeDomain() );
    form.setPrvtCustTypeCodeDomain( fncVO.getPrvtCustTypeCodeDomain());
    form.setWealthPotnlCodeDomain( fncVO.getWealthPotnlCodeDomain() );
    form.setOffclMailRecvIndDomain( fncVO.getOffclMailRecvIndDomain() );
    form.setMailRecvIndDomain( fncVO.getMailRecvIndDomain() );

    if ( fncVO.getTplCustomerPrvtCmplEntity().getData().getCustNbr() != null
         && fncVO.getTplCustomerPrvtCmplEntity().getData().getCustNbr().longValue() > 0 )
    {
      form.setCustText( fncVO.getCustText() );
    }
    else
    {
      form.setCustText( "" );
    }

    if ( fncVO.getTplCustomerPrvtCmplEntity().getData().getOffcrNbr() != null
         && fncVO.getTplCustomerPrvtCmplEntity().getData().getOffcrNbr().intValue() > 0 )
    {
      form.setOffcrText( fncVO.getOffcrText() );
    }
    else
    {
      form.setOffcrText( "" );
    }
    String custPrvtStatCode = fncVO.getTplCustomerPrvtCmplEntity().getData().getCustPrvtStatCode() != null
                                                                                                          ? fncVO.getTplCustomerPrvtCmplEntity().getData().getCustPrvtStatCode()
                                                                                                          : "";

    form.setClickedSearch( fncVO.getClickedSearch() );
    form.setCustPrvtStatCode( custPrvtStatCode );

  }

  /*
   * Metodo que carrega os Combos da Tela...
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public void load( BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    BaseTplCustomerPrvtEntity customerPrvtEntity = null;

    BaseCustomerPrvtDetailFncVO detailFncVO = ( BaseCustomerPrvtDetailFncVO ) customerPrvtDetailFncVO_;

    BaseTplCustomerPrvtDAO customerPrvtDAO = this.getDAO();
    customerPrvtEntity = customerPrvtDAO.find( detailFncVO.getTplCustomerPrvtEntity() );

    detailFncVO.setTplCustomerPrvtEntity( customerPrvtEntity );
    if ( detailFncVO.getCmplDataButtonControl().equals( C_EXISTING_DATA_CMPL ) )
    {
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplCustomerPrvtCmplDAO tplCustomerPrvtCmplDAO = odsDAOFactory.getTplCustomerPrvtCmplDAO();
      detailFncVO.getTplCustomerPrvtCmplEntity().getData().setCustNbr(
                                                                       detailFncVO.getTplCustomerPrvtEntity().getData().getCustNbr() );

      detailFncVO.setTplCustomerPrvtCmplEntity( tplCustomerPrvtCmplDAO.find( detailFncVO.getTplCustomerPrvtCmplEntity() ) );

    }
  }

  protected void loadDomains(
                             BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    loadCustCitiGrpTieIndDomain( customerPrvtDetailFncVO_ );
    loadCustCorpBkrApplPrflIndDomain( customerPrvtDetailFncVO_ );
    loadCustCorpFundApplPrflIndDomain( customerPrvtDetailFncVO_ );
    loadCustCpfOwnIndDomain( customerPrvtDetailFncVO_ );
    loadCustEmplIndDomain( customerPrvtDetailFncVO_ );
    loadCustGrcardIndDomain( customerPrvtDetailFncVO_ );
    loadCustIndivPublicIndDomain( customerPrvtDetailFncVO_ );
    loadCustIrrfExemptIndDomain( customerPrvtDetailFncVO_ );
    loadCustNoCgcIndDomain( customerPrvtDetailFncVO_ );
    loadCustNoCpfIndDomain( customerPrvtDetailFncVO_ );
    loadCustParentLevelIndDomain( customerPrvtDetailFncVO_ );
    loadCustUsaCtznAuthIndDomain( customerPrvtDetailFncVO_ );
    loadClassCmplcDomain( customerPrvtDetailFncVO_ );
    loadPrvtCustTypeDomain( customerPrvtDetailFncVO_);
    loadWealthPotnlDomain( customerPrvtDetailFncVO_ );
    loadCustText( customerPrvtDetailFncVO_ );
    loadOffcrText( customerPrvtDetailFncVO_ );
    loadMailRecvIndDomain( customerPrvtDetailFncVO_ );
    loadOffclMailRecvIndDomain( customerPrvtDetailFncVO_ );
    loadCustPrvtStatCode( customerPrvtDetailFncVO_ );
  }
  
  public void loadErEm( BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {
	if  (customerPrvtDetailFncVO_.getTplCustomerPrvtEntity().getData().getEmNbr()!= null)
		{
		  ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
		  TplErEmDAO erEmDAO = odsDAOFactory.getTplErEmDAO();

		  customerPrvtDetailFncVO_.setErEmEntities( erEmDAO.listByErNbr(customerPrvtDetailFncVO_.getTplCustomerPrvtEntity().getData().getErNbr(),
		                                                                customerPrvtDetailFncVO_.getTplCustomerPrvtEntity().getData().getEmNbr() ) );

		}
 	
  }

  private void loadCustCitiGrpTieIndDomain(
                                           BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustCitiGrpTieIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustCorpBkrApplPrflIndDomain(
                                                BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustCorpBkrApplPrflIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustCorpFundApplPrflIndDomain(
                                                 BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustCorpFundApplPrflIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustCpfOwnIndDomain(
                                       BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustCpfOwnIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustEmplIndDomain(
                                     BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustEmplIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustGrcardIndDomain(
                                       BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustGrcardIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustIndivPublicIndDomain(
                                            BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustIndivPublicIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustIrrfExemptIndDomain(
                                           BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustIrrfExemptIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustNoCgcIndDomain(
                                      BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustNoCgcIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustNoCpfIndDomain(
                                      BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustNoCpfIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustParentLevelIndDomain(
                                            BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustParentLevelIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustUsaCtznAuthIndDomain(
                                            BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustUsaCtznAuthIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadCustPrvtStatCode(
                                    BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setCustPrvtStatCodeDomain( ODSConstraintDecoder.decodeCustomerStatCode() );
  }

  private void loadClassCmplcDomain( BaseCustomerPrvtDetailFncVO customerFncVO_ )
  {
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplClassCmplcDAO classCmplcDAO = factory.getTplClassCmplcDAO();
    //Realiza a consulta no DAO
    DataSet result = classCmplcDAO.loadDomain();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    customerFncVO_.setClassCmplcCodeDomain( result );

  }
  
  private void loadPrvtCustTypeDomain( BaseCustomerPrvtDetailFncVO customerFncVO_ )
  {
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplCustomerPrvtTypeDAO customerPrvtTypeDAO = factory.getTplCustomerPrvtTypeDAO();
    //Realiza a consulta no DAO
    DataSet result = customerPrvtTypeDAO.loadDomain();
   //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    customerFncVO_.setPrvtCustTypeCodeDomain( result );

  }
 
  private void loadWealthPotnlDomain( BaseCustomerPrvtDetailFncVO customerFncVO_ )
  {

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplPotentialWealthDAO potentialWealthDAO = factory.getTplPotentialWealthDAO();
    //Realiza a consulta no DAO
    DataSet result = potentialWealthDAO.loadDomain();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    customerFncVO_.setWealthPotnlCodeDomain( result );

  }

  public void loadCustText( BaseCustomerPrvtDetailFncVO customerFncVO_ )
  {
    if ( customerFncVO_.getTplCustomerPrvtCmplEntity().getData().getCustNbr() != null
         && customerFncVO_.getTplCustomerPrvtCmplEntity().getData().getCustNbr().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               customerFncVO_.getTplCustomerPrvtCmplEntity().getData().getCustNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      customerFncVO_.setCustText( customerPrvtEntity.getData().getCustFullNameText() );
    }
  }

  public void loadOffcrText( BaseCustomerPrvtDetailFncVO customerFncVO_ )
  {
    if ( customerFncVO_.getTplCustomerPrvtCmplEntity().getData().getOffcrNbr() != null
         && customerFncVO_.getTplCustomerPrvtCmplEntity().getData().getOffcrNbr().intValue() > 0 )
    {
      TbgOfficerEntity officerEntity = new TbgOfficerEntity();
      officerEntity.getData().setOffcrNbr(
                                           customerFncVO_.getTplCustomerPrvtCmplEntity().getData().getOffcrNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TbgOfficerDAO tbgOfficerDAO = factory.getTbgOfficerDAO();

      //Realiza a consulta no DAO
      officerEntity = ( TbgOfficerEntity ) tbgOfficerDAO.find( officerEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      customerFncVO_.setOffcrText( officerEntity.getData().getOffcrNameText() );
    }
  }

  private void loadMailRecvIndDomain(
                                     BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setMailRecvIndDomain( ODSConstraintDecoder.decodeIndicador() );

  }

  private void loadOffclMailRecvIndDomain(
                                          BaseCustomerPrvtDetailFncVO customerPrvtDetailFncVO_ )
  {

    customerPrvtDetailFncVO_.setOffclMailRecvIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /**
   * Converte uma data para o formato de apresentação.
   * 
   * @param date_ - A data a ser convertida - TimeStamp
   * @return String - A data no formato de apresentação.
   */
  private String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    return dateFormat.format( date_ );
  }

  protected abstract BaseTplCustomerPrvtDAO getDAO();

}