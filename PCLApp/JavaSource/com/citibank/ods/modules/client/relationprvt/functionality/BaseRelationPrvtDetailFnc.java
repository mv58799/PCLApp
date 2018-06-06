package com.citibank.ods.modules.client.relationprvt.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplRelationPrvtEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplPortfolioPrvtEntity;
import com.citibank.ods.modules.client.relationprvt.form.BaseRelationPrvtDetailForm;
import com.citibank.ods.modules.client.relationprvt.functionality.valueobject.BaseRelationPrvtDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplPortfolioPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.relationprvt.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseRelationPrvtDetailFnc extends BaseFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseRelationPrvtDetailFncVO relationFncVO = ( BaseRelationPrvtDetailFncVO ) fncVO_;
    BaseRelationPrvtDetailForm relationForm = ( BaseRelationPrvtDetailForm ) form_;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    // Atualizando os dados: Form -> FncVO

    String recStatCode = relationForm.getRecStatCode() != null
                         && relationForm.getRecStatCode().length() > 0
                                                                      ? relationForm.getRecStatCode()
                                                                      : null;
    String reltnAcctStmtInd = relationForm.getReltnAcctStmtInd() != null
                              && relationForm.getReltnAcctStmtInd().length() > 0
                                                                                ? relationForm.getReltnAcctStmtInd()
                                                                                : null;
    BigInteger reltnAddrCellCustNbr = relationForm.getReltnAddrCellCustNbr() != null
                                      && relationForm.getReltnAddrCellCustNbr().length() > 0
                                                                                            ? new BigInteger(
                                                                                                              relationForm.getReltnAddrCellCustNbr() )
                                                                                            : null;
    BigInteger reltnAddrCellSeqNbr = relationForm.getReltnAddrCellSeqNbr() != null
                                     && relationForm.getReltnAddrCellSeqNbr().length() > 0
                                                                                          ? new BigInteger(
                                                                                                            relationForm.getReltnAddrCellSeqNbr() )
                                                                                          : null;
    BigInteger reltnAddrEmailCustNbr = relationForm.getReltnAddrEmailCustNbr() != null
                                       && relationForm.getReltnAddrEmailCustNbr().length() > 0
                                                                                              ? new BigInteger(
                                                                                                                relationForm.getReltnAddrEmailCustNbr() )
                                                                                              : null;
    BigInteger reltnAddrEmailSeqNbr = relationForm.getReltnAddrEmailSeqNbr() != null
                                      && relationForm.getReltnAddrEmailSeqNbr().length() > 0
                                                                                            ? new BigInteger(
                                                                                                              relationForm.getReltnAddrEmailSeqNbr() )
                                                                                            : null;
    String reltnCatCode = relationForm.getReltnCatCode() != null
                          && relationForm.getReltnCatCode().length() > 0
                                                                        ? relationForm.getReltnCatCode()
                                                                        : null;
    String reltnClassCode = relationForm.getReltnClassCode() != null
                            && relationForm.getReltnClassCode().length() > 0
                                                                            ? relationForm.getReltnClassCode()
                                                                            : null;
    String reltnClassScoreCode = relationForm.getReltnClassScoreCode() != null
                                 && relationForm.getReltnClassScoreCode().length() > 0
                                                                                      ? relationForm.getReltnClassScoreCode()
                                                                                      : null;
    String reltnCommTypeCode = relationForm.getReltnCommTypeCode() != null
                               && relationForm.getReltnCommTypeCode().length() > 0
                                                                                  ? relationForm.getReltnCommTypeCode()
                                                                                  : null;
    BigInteger reltnCorpBaseNbr = relationForm.getReltnCorpBaseNbr() != null
                                  && relationForm.getReltnCorpBaseNbr().length() > 0
                                                                                    ? new BigInteger(
                                                                                                      relationForm.getReltnCorpBaseNbr() )
                                                                                    : null;
    BigInteger reltnCust1Nbr = relationForm.getReltnCust1Nbr() != null
                               && relationForm.getReltnCust1Nbr().length() > 0
                                                                              ? new BigInteger(
                                                                                                relationForm.getReltnCust1Nbr() )
                                                                              : null;
    BigInteger reltnCust2Nbr = relationForm.getReltnCust2Nbr() != null
                               && relationForm.getReltnCust2Nbr().length() > 0
                                                                              ? new BigInteger(
                                                                                                relationForm.getReltnCust2Nbr() )
                                                                              : null;
    BigInteger reltnCust3Nbr = relationForm.getReltnCust3Nbr() != null
                               && relationForm.getReltnCust3Nbr().length() > 0
                                                                              ? new BigInteger(
                                                                                                relationForm.getReltnCust3Nbr() )
                                                                              : null;
    BigInteger reltnCust4Nbr = relationForm.getReltnCust4Nbr() != null
                               && relationForm.getReltnCust4Nbr().length() > 0
                                                                              ? new BigInteger(
                                                                                                relationForm.getReltnCust4Nbr() )
                                                                              : null;
    BigInteger reltnCust5Nbr = relationForm.getReltnCust5Nbr() != null
                               && relationForm.getReltnCust5Nbr().length() > 0
                                                                              ? new BigInteger(
                                                                                                relationForm.getReltnCust5Nbr() )
                                                                              : null;
    BigInteger reltnCustAddrNbr = relationForm.getReltnCustAddrNbr() != null
                                  && relationForm.getReltnCustAddrNbr().length() > 0
                                                                                    ? new BigInteger(
                                                                                                      relationForm.getReltnCustAddrNbr() )
                                                                                    : null;
    BigInteger reltnCustAddrSeqNbr = relationForm.getReltnCustAddrSeqNbr() != null
                                     && relationForm.getReltnCustAddrSeqNbr().length() > 0
                                                                                          ? new BigInteger(
                                                                                                            relationForm.getReltnCustAddrSeqNbr() )
                                                                                          : null;
    String reltnMfStmtInd = relationForm.getReltnMfStmtInd() != null
                            && relationForm.getReltnMfStmtInd().length() > 0
                                                                            ? relationForm.getReltnMfStmtInd()
                                                                            : null;
    BigInteger reltnNbr = relationForm.getReltnNbr() != null
                          && relationForm.getReltnNbr().length() > 0
                                                                    ? new BigInteger(
                                                                                      relationForm.getReltnNbr() )
                                                                    : null;
    String reltnPortfCode = relationForm.getReltnPortfCode() != null
                            && relationForm.getReltnPortfCode().length() > 0
                                                                            ? relationForm.getReltnPortfCode()
                                                                            : null;
    BigInteger reltnPrmtCode = relationForm.getReltnPrmtCode() != null
                               && relationForm.getReltnPrmtCode().length() > 0
                                                                              ? new BigInteger(
                                                                                                relationForm.getReltnPrmtCode() )
                                                                              : null;
    String reltnRiskLevelCode = relationForm.getReltnRiskLevelCode() != null
                                && relationForm.getReltnRiskLevelCode().length() > 0
                                                                                    ? relationForm.getReltnRiskLevelCode()
                                                                                    : null;
    String reltnSavStmtInd = relationForm.getReltnSavStmtInd() != null
                             && relationForm.getReltnSavStmtInd().length() > 0
                                                                              ? relationForm.getReltnSavStmtInd()
                                                                              : null;
    String reltnSegCode = relationForm.getReltnSegCode() != null
                          && relationForm.getReltnSegCode().length() > 0
                                                                        ? relationForm.getReltnSegCode()
                                                                        : null;
    String reltnSpcfClassServPackInd = relationForm.getReltnSpcfClassServPackInd() != null
                                       && relationForm.getReltnSpcfClassServPackInd().length() > 0
                                                                                                  ? relationForm.getReltnSpcfClassServPackInd()
                                                                                                  : null;
    String reltnStmtOptnCode = relationForm.getReltnStmtOptnCode() != null
                               && relationForm.getReltnStmtOptnCode().length() > 0
                                                                                  ? relationForm.getReltnStmtOptnCode()
                                                                                  : null;
    String reltnTdStmtInd = relationForm.getReltnTdStmtInd() != null
                            && relationForm.getReltnTdStmtInd().length() > 0
                                                                            ? relationForm.getReltnTdStmtInd()
                                                                            : null;
    String reltnTypeCode = relationForm.getReltnTypeCode() != null
                           && relationForm.getReltnTypeCode().length() > 0
                                                                          ? relationForm.getReltnTypeCode()
                                                                          : null;
    String selectedReltnNbr = relationForm.getSelectedReltnNbr() != null
                              && relationForm.getSelectedReltnNbr().length() > 0
                                                                                ? relationForm.getSelectedReltnNbr()
                                                                                : null;

    if ( relationForm.getReltnEstabDate() != null
         && relationForm.getReltnEstabDate().length() != 0 )
    {
      try
      {
        relationFncVO.getTplRelationPrvtEntity().getData().setReltnEstabDate(
                                                                              formatter.parse( relationForm.getReltnEstabDate() ) );
      }
      catch ( Exception e )
      {
        relationFncVO.getTplRelationPrvtEntity().getData().setReltnEstabDate(
                                                                              null );
      }
    }

    relationFncVO.getTplRelationPrvtEntity().getData().setRecStatCode(
                                                                       recStatCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnAcctStmtInd(
                                                                            reltnAcctStmtInd );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnAddrCellCustNbr(
                                                                                reltnAddrCellCustNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnAddrCellSeqNbr(
                                                                               reltnAddrCellSeqNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnAddrEmailCustNbr(
                                                                                 reltnAddrEmailCustNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnAddrEmailSeqNbr(
                                                                                reltnAddrEmailSeqNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCatCode(
                                                                        reltnCatCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnClassCode(
                                                                          reltnClassCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnClassScoreCode(
                                                                               reltnClassScoreCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCommTypeCode(
                                                                             reltnCommTypeCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCorpBaseNbr(
                                                                            reltnCorpBaseNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCust1Nbr(
                                                                         reltnCust1Nbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCust2Nbr(
                                                                         reltnCust2Nbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCust3Nbr(
                                                                         reltnCust3Nbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCust4Nbr(
                                                                         reltnCust4Nbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCust5Nbr(
                                                                         reltnCust5Nbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCustAddrNbr(
                                                                            reltnCustAddrNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnCustAddrSeqNbr(
                                                                               reltnCustAddrSeqNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnMfStmtInd(
                                                                          reltnMfStmtInd );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnNbr( reltnNbr );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnPortfCode(
                                                                          reltnPortfCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnPrmtCode(
                                                                         reltnPrmtCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnRiskLevelCode(
                                                                              reltnRiskLevelCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnSavStmtInd(
                                                                           reltnSavStmtInd );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnSegCode(
                                                                        reltnSegCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnSpcfClassServPackInd(
                                                                                     reltnSpcfClassServPackInd );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnStmtOptnCode(
                                                                             reltnStmtOptnCode );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnTdStmtInd(
                                                                          reltnTdStmtInd );
    relationFncVO.getTplRelationPrvtEntity().getData().setReltnTypeCode(
                                                                         reltnTypeCode );
    relationFncVO.setSelectedReltnNbr( selectedReltnNbr );

    relationFncVO.getTplRelationPrvtEntity().getData().setReltnNbr(
                                                                    new BigInteger(
                                                                                    selectedReltnNbr ) );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseRelationPrvtDetailForm baseRelationPrvtDetailForm = ( BaseRelationPrvtDetailForm ) form_;
    BaseRelationPrvtDetailFncVO baseRelationPrvtDetailFncVO = ( BaseRelationPrvtDetailFncVO ) fncVO_;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        com.citibank.ods.Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    String recStatCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getRecStatCode() != null
                                                                                                                  ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getRecStatCode()
                                                                                                                  : "";
    String reltnAcctStmtInd = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAcctStmtInd() != null
                                                                                                                            ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAcctStmtInd()
                                                                                                                            : "";
    String reltnAddrCellCustNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrCellCustNbr() != null
                                                                                                                                    ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrCellCustNbr().toString()
                                                                                                                                    : "";
    String reltnAddrCellSeqNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrCellSeqNbr() != null
                                                                                                                                  ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrCellSeqNbr().toString()
                                                                                                                                  : "";
    String reltnAddrEmailCustNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrEmailCustNbr() != null
                                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrEmailCustNbr().toString()
                                                                                                                                      : "";
    String reltnAddrEmailSeqNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrEmailSeqNbr() != null
                                                                                                                                    ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnAddrEmailSeqNbr().toString()
                                                                                                                                    : "";
    String reltnCatCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCatCode() != null
                                                                                                                    ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCatCode()
                                                                                                                    : "";
    String reltnClassCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnClassCode() != null
                                                                                                                        ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnClassCode()
                                                                                                                        : "";
    String reltnClassScoreCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnClassScoreCode() != null
                                                                                                                                  ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnClassScoreCode()
                                                                                                                                  : "";
    String reltnCommTypeCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCommTypeCode() != null
                                                                                                                              ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCommTypeCode()
                                                                                                                              : "";
    String reltnCorpBaseNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCorpBaseNbr() != null
                                                                                                                            ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCorpBaseNbr().toString()
                                                                                                                            : "";
    String reltnCust1Nbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust1Nbr() != null
                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust1Nbr().toString()
                                                                                                                      : "";
    String reltnCust2Nbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust2Nbr() != null
                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust2Nbr().toString()
                                                                                                                      : "";
    String reltnCust3Nbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust3Nbr() != null
                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust3Nbr().toString()
                                                                                                                      : "";
    String reltnCust4Nbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust4Nbr() != null
                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust4Nbr().toString()
                                                                                                                      : "";
    String reltnCust5Nbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust5Nbr() != null
                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCust5Nbr().toString()
                                                                                                                      : "";
    String reltnCustAddrNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCustAddrNbr() != null
                                                                                                                            ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCustAddrNbr().toString()
                                                                                                                            : "";
    String reltnCustAddrSeqNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCustAddrSeqNbr() != null
                                                                                                                                  ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnCustAddrSeqNbr().toString()
                                                                                                                                  : "";
    String reltnEstabDate = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnEstabDate() != null
                                                                                                                        ? dateFormat.format( baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnEstabDate() )
                                                                                                                        : "";
    String reltnMfStmtInd = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnMfStmtInd() != null
                                                                                                                        ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnMfStmtInd()
                                                                                                                        : "";
    String reltnNbr = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnNbr() != null
                                                                                                            ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnNbr().toString()
                                                                                                            : "";
    String reltnPortfCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnPortfCode() != null
                                                                                                                        ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnPortfCode()
                                                                                                                        : "";
    String reltnPrmtCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnPrmtCode() != null
                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnPrmtCode().toString()
                                                                                                                      : "";
    String reltnRiskLevelCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnRiskLevelCode() != null
                                                                                                                                ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnRiskLevelCode()
                                                                                                                                : "";
    String reltnSavStmtInd = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnSavStmtInd() != null
                                                                                                                          ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnSavStmtInd()
                                                                                                                          : "";
    String reltnSegCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnSegCode() != null
                                                                                                                    ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnSegCode()
                                                                                                                    : "";
    String reltnSpcfClassServPackInd = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnSpcfClassServPackInd() != null
                                                                                                                                              ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnSpcfClassServPackInd()
                                                                                                                                              : "";
    String reltnStmtOptnCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnStmtOptnCode() != null
                                                                                                                              ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnStmtOptnCode()
                                                                                                                              : "";
    String reltnTdStmtInd = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnTdStmtInd() != null
                                                                                                                        ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnTdStmtInd()
                                                                                                                        : "";
    String reltnTypeCode = baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnTypeCode() != null
                                                                                                                      ? baseRelationPrvtDetailFncVO.getTplRelationPrvtEntity().getData().getReltnTypeCode()
                                                                                                                      : "";

    DataSet reltnPrmtCodeDomain = baseRelationPrvtDetailFncVO.getReltnPrmtCodeDomain();

    baseRelationPrvtDetailForm.setRecStatCode( recStatCode );
    baseRelationPrvtDetailForm.setReltnAcctStmtInd( reltnAcctStmtInd );
    baseRelationPrvtDetailForm.setReltnAddrCellCustNbr( reltnAddrCellCustNbr );
    baseRelationPrvtDetailForm.setReltnAddrCellSeqNbr( reltnAddrCellSeqNbr );
    baseRelationPrvtDetailForm.setReltnAddrEmailCustNbr( reltnAddrEmailCustNbr );
    baseRelationPrvtDetailForm.setReltnAddrEmailSeqNbr( reltnAddrEmailSeqNbr );
    baseRelationPrvtDetailForm.setReltnCatCode( reltnCatCode );
    baseRelationPrvtDetailForm.setReltnClassCode( reltnClassCode );
    baseRelationPrvtDetailForm.setReltnClassScoreCode( reltnClassScoreCode );
    baseRelationPrvtDetailForm.setReltnCommTypeCode( reltnCommTypeCode );
    baseRelationPrvtDetailForm.setReltnCorpBaseNbr( reltnCorpBaseNbr );
    baseRelationPrvtDetailForm.setReltnCust1Nbr( reltnCust1Nbr );
    baseRelationPrvtDetailForm.setReltnCust2Nbr( reltnCust2Nbr );
    baseRelationPrvtDetailForm.setReltnCust3Nbr( reltnCust3Nbr );
    baseRelationPrvtDetailForm.setReltnCust4Nbr( reltnCust4Nbr );
    baseRelationPrvtDetailForm.setReltnCust5Nbr( reltnCust5Nbr );
    baseRelationPrvtDetailForm.setReltnCustAddrNbr( reltnCustAddrNbr );
    baseRelationPrvtDetailForm.setReltnCustAddrSeqNbr( reltnCustAddrSeqNbr );
    baseRelationPrvtDetailForm.setReltnCustAddrSeqNbr( reltnCustAddrSeqNbr );
    baseRelationPrvtDetailForm.setReltnEstabDate( reltnEstabDate );
    baseRelationPrvtDetailForm.setReltnMfStmtInd( reltnMfStmtInd );
    baseRelationPrvtDetailForm.setReltnNbr( reltnNbr );
    baseRelationPrvtDetailForm.setReltnPortfCode( reltnPortfCode );
    baseRelationPrvtDetailForm.setReltnPrmtCode( reltnPrmtCode );
    baseRelationPrvtDetailForm.setReltnRiskLevelCode( reltnRiskLevelCode );
    baseRelationPrvtDetailForm.setReltnSavStmtInd( reltnSavStmtInd );
    baseRelationPrvtDetailForm.setReltnSegCode( reltnSegCode );
    baseRelationPrvtDetailForm.setReltnSpcfClassServPackInd( reltnSpcfClassServPackInd );
    baseRelationPrvtDetailForm.setReltnStmtOptnCode( reltnStmtOptnCode );
    baseRelationPrvtDetailForm.setReltnTdStmtInd( reltnTdStmtInd );
    baseRelationPrvtDetailForm.setReltnTypeCode( reltnTypeCode );
    baseRelationPrvtDetailForm.setReltnPrmtCodeDomain( reltnPrmtCodeDomain );
    baseRelationPrvtDetailForm.setCustFullNameText( baseRelationPrvtDetailFncVO.getCustFullNameText() );
    baseRelationPrvtDetailForm.setCustFullNameText2( baseRelationPrvtDetailFncVO.getCustFullNameText2() );
    baseRelationPrvtDetailForm.setCustFullNameText3( baseRelationPrvtDetailFncVO.getCustFullNameText3() );
    baseRelationPrvtDetailForm.setCustFullNameText4( baseRelationPrvtDetailFncVO.getCustFullNameText4() );
    baseRelationPrvtDetailForm.setCustFullNameText5( baseRelationPrvtDetailFncVO.getCustFullNameText5() );
    baseRelationPrvtDetailForm.setReltnCustAddrNbrCustFullNameText( baseRelationPrvtDetailFncVO.getReltnCustAddrNbrCustFullNameText() );
    baseRelationPrvtDetailForm.setReltnAddrEmailCustNbrCustFullNameText( baseRelationPrvtDetailFncVO.getReltnAddrEmailCustNbrCustFullNameText() );
    baseRelationPrvtDetailForm.setReltnAddrCellCustNbrCustFullNameText( baseRelationPrvtDetailFncVO.getReltnAddrCellCustNbrCustFullNameText() );
    baseRelationPrvtDetailForm.setPortfNameText( baseRelationPrvtDetailFncVO.getPortfNameText() );
    baseRelationPrvtDetailForm.setPortfOffcrNbr( baseRelationPrvtDetailFncVO.getPortfOffcrNbr() != null
                                                                                                       ? ( baseRelationPrvtDetailFncVO.getPortfOffcrNbr() ).toString()
                                                                                                       : "" );
    baseRelationPrvtDetailForm.setOffcrNameText( ( baseRelationPrvtDetailFncVO.getOffcrNameText() ) );
    baseRelationPrvtDetailForm.setReltnSpcfClassServPackIndDomain( baseRelationPrvtDetailFncVO.getReltnSpcfClassServPackIndDomain() );
  }

  public void load( BaseRelationPrvtDetailFncVO fncVO_ )
  {
    BaseTplRelationPrvtEntity tplRelationPrvtEntity = null;

    BaseTplRelationPrvtDAO relationDAO = this.getDAO();
    tplRelationPrvtEntity = relationDAO.find( fncVO_.getTplRelationPrvtEntity() );
    fncVO_.setTplRelationPrvtEntity( tplRelationPrvtEntity );
  }

  public void loadNameText( BaseRelationPrvtDetailFncVO fncVO_ )
  {
    TplCustomerPrvtEntity entity = new TplCustomerPrvtEntity();

    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TplCustomerPrvtDAO customerPrvtDAO = factory.getTplCustomerPrvtDAO();

    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnCust1Nbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnCust1Nbr().longValue() != 0 )
    {

      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnCust1Nbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {
        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setCustFullNameText( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setCustFullNameText( "" );
      }

    }

    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnCust2Nbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnCust2Nbr().longValue() != 0 )
    {
      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnCust2Nbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {
        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setCustFullNameText2( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setCustFullNameText2( "" );
      }

    }
    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnCust3Nbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnCust3Nbr().longValue() != 0 )
    {
      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnCust3Nbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {
        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setCustFullNameText3( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setCustFullNameText3( "" );

      }
    }
    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnCust4Nbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnCust4Nbr().longValue() != 0 )
    {
      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnCust4Nbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {
        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setCustFullNameText4( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setCustFullNameText4( "" );
      }
    }
    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnCust5Nbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnCust5Nbr().longValue() != 0 )
    {
      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnCust5Nbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {

        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setCustFullNameText5( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setCustFullNameText5( "" );
      }

    }
    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnCustAddrNbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnCustAddrNbr().longValue() != 0 )
    {
      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnCustAddrNbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {
        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setReltnCustAddrNbrCustFullNameText( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setReltnCustAddrNbrCustFullNameText( "" );
      }

    }
    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnAddrEmailCustNbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnAddrEmailCustNbr().longValue() != 0 )
    {
      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnAddrEmailCustNbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {
        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setReltnAddrEmailCustNbrCustFullNameText( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setReltnAddrEmailCustNbrCustFullNameText( "" );
      }
    }
    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnAddrCellCustNbr() != null
         && fncVO_.getTplRelationPrvtEntity().getData().getReltnAddrCellCustNbr().longValue() != 0 )
    {
      entity.getData().setCustNbr(
                                   fncVO_.getTplRelationPrvtEntity().getData().getReltnAddrCellCustNbr() );

      if ( customerPrvtDAO.exists( entity ) )
      {
        entity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( entity );
        fncVO_.setReltnAddrCellCustNbrCustFullNameText( entity.getData().getCustFullNameText() );
      }
      else
      {
        fncVO_.setReltnAddrCellCustNbrCustFullNameText( "" );
      }
    }

  }

  public void loadPorfolio( BaseRelationPrvtDetailFncVO fncVO_ )
  {

    if ( fncVO_.getTplRelationPrvtEntity().getData().getReltnPortfCode() != null
         && !fncVO_.getTplRelationPrvtEntity().getData().getReltnPortfCode().equals(
                                                                                     "" ) )
    {

      TplPortfolioPrvtEntity entity = new TplPortfolioPrvtEntity();

      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TplPortfolioPrvtDAO prvtDAO = factory.getTplPortfolioPrvtDAO();

      entity.getData().setPortfCode(
                                     fncVO_.getTplRelationPrvtEntity().getData().getReltnPortfCode() );
      entity = ( TplPortfolioPrvtEntity ) prvtDAO.find( entity );

      fncVO_.setPortfNameText( entity.getData().getPortfNameText() );
      fncVO_.setPortfOffcrNbr( entity.getData().getPortfOffcrNbr() );

    }
  }

  public void loadOfficer( BaseRelationPrvtDetailFncVO fncVO_ )
  {

    if ( fncVO_.getPortfOffcrNbr() != null
         && fncVO_.getPortfOffcrNbr().longValue() != 0 )
    {

      TbgOfficerEntity entity = new TbgOfficerEntity();

      // Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TbgOfficerDAO officerDAO = factory.getTbgOfficerDAO();

      entity.getData().setOffcrNbr( fncVO_.getPortfOffcrNbr() );
      if ( officerDAO.exists( entity ) )
      {
        entity = ( TbgOfficerEntity ) officerDAO.find( entity );
        fncVO_.setOffcrNameText( entity.getData().getOffcrNameText() );
      }
      else
      {
        fncVO_.setOffcrNameText( "" );
      }

    }
    else
    {
      fncVO_.setOffcrNameText( "" );
    }

  }

  public void loadReltnSpcfClassServPackIndDomain(
                                                  BaseRelationPrvtDetailFncVO detailFncVO_ )
  {

    detailFncVO_.setReltnSpcfClassServPackIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  protected abstract BaseTplRelationPrvtDAO getDAO();

}