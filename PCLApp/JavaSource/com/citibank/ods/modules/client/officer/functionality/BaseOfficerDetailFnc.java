package com.citibank.ods.modules.client.officer.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTbgOfficerEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.entity.pl.TplOfficerCmplEntity;
import com.citibank.ods.modules.client.officer.form.BaseOfficerDetailForm;
import com.citibank.ods.modules.client.officer.functionality.valueobject.BaseOfficerDetailFncVO;
import com.citibank.ods.modules.client.officer.functionality.valueobject.OfficerDetailFncVO;
import com.citibank.ods.persistence.bg.dao.BaseTbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.officer.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 *  
 */

public abstract class BaseOfficerDetailFnc extends BaseFnc
{

  public static final String C_EXISTING_DATA_CMPL = "1";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseOfficerDetailFncVO officerFncVO = ( BaseOfficerDetailFncVO ) fncVO_;
    BaseOfficerDetailForm officerForm = ( BaseOfficerDetailForm ) form_;

    TbgOfficerEntity tbgOfficerEntity = new TbgOfficerEntity();
    TplOfficerCmplEntity officerCmplEntity = new TplOfficerCmplEntity();

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    officerFncVO.setBaseTbgOfficerEntity( tbgOfficerEntity );
    officerFncVO.setTplOfficerCmplEntity( officerCmplEntity );

    // Atualizando os dados: Form -> FncVO
    String offcrCatCode = ( officerForm.getOffcrCatCode() != null
                            && officerForm.getOffcrCatCode().length() > 0
                                                                         ? officerForm.getOffcrCatCode()
                                                                         : null );
    BigInteger offcrChnnlCode = ( officerForm.getOffcrChnnlCode() != null
                                  && officerForm.getOffcrChnnlCode().length() > 0
                                                                                 ? new BigInteger(
                                                                                                   officerForm.getOffcrChnnlCode() )
                                                                                 : null );
    String offcrEmailName = ( officerForm.getOffcrEmailName() != null
                              && officerForm.getOffcrEmailName().length() > 0
                                                                             ? officerForm.getOffcrEmailName()
                                                                             : null );
    String offcrNameText = ( officerForm.getOffcrNameText() != null
                             && officerForm.getOffcrNameText().length() > 0
                                                                           ? officerForm.getOffcrNameText()
                                                                           : null );
    BigInteger offcrNbr = ( officerForm.getOffcrNbr() != null
                            && officerForm.getOffcrNbr().length() > 0
                                                                     ? new BigInteger(
                                                                                       officerForm.getOffcrNbr() )
                                                                     : null );
    String offcrNcknName = ( officerForm.getOffcrNcknName() != null
                             && officerForm.getOffcrNcknName().length() > 0
                                                                           ? officerForm.getOffcrNcknName()
                                                                           : null );
    BigInteger offcrPhoneOpCode = ( officerForm.getOffcrPhoneOpCode() != null
                                    && officerForm.getOffcrPhoneOpCode().length() > 0
                                                                                     ? new BigInteger(
                                                                                                       officerForm.getOffcrPhoneOpCode() )
                                                                                     : null );
    BigInteger offcrPhoneSecAreaCode = ( officerForm.getOffcrPhoneSecAreaCode() != null
                                         && officerForm.getOffcrPhoneSecAreaCode().length() > 0
                                                                                               ? new BigInteger(
                                                                                                                 officerForm.getOffcrPhoneSecAreaCode() )
                                                                                               : null );
    BigInteger offcrPhoneSecExtnNbr = ( officerForm.getOffcrPhoneSecExtnNbr() != null
                                        && officerForm.getOffcrPhoneSecExtnNbr().length() > 0
                                                                                             ? new BigInteger(
                                                                                                               officerForm.getOffcrPhoneSecExtnNbr() )
                                                                                             : null );
    BigInteger offcrPhoneSecNbr = ( officerForm.getOffcrPhoneSecNbr() != null
                                    && officerForm.getOffcrPhoneSecNbr().length() > 0
                                                                                     ? new BigInteger(
                                                                                                       officerForm.getOffcrPhoneSecNbr() )
                                                                                     : null );
    BigInteger offcrPhoneSecOpCode = ( officerForm.getOffcrPhoneSecOpCode() != null
                                       && officerForm.getOffcrPhoneSecOpCode().length() > 0
                                                                                           ? new BigInteger(
                                                                                                             officerForm.getOffcrPhoneSecOpCode() )
                                                                                           : null );
    BigInteger offcrRealNbr = ( officerForm.getOffcrRealNbr() != null
                                && officerForm.getOffcrRealNbr().length() > 0
                                                                             ? new BigInteger(
                                                                                               officerForm.getOffcrRealNbr() )
                                                                             : null );
    BigInteger offcrRemoteCode = ( officerForm.getOffcrRemoteCode() != null
                                   && officerForm.getOffcrRemoteCode().length() > 0
                                                                                   ? new BigInteger(
                                                                                                     officerForm.getOffcrRemoteCode() )
                                                                                   : null );
    BigInteger offcrSecCode = ( officerForm.getOffcrSecCode() != null
                                && officerForm.getOffcrSecCode().length() > 0
                                                                             ? new BigInteger(
                                                                                               officerForm.getOffcrSecCode() )
                                                                             : null );

    String offcrStatCode = ( officerForm.getOffcrStatCode() != null
                             && officerForm.getOffcrStatCode().length() > 0
                                                                           ? officerForm.getOffcrStatCode()
                                                                           : null );
    String offcrTypeCode = ( officerForm.getOffcrTypeCode() != null
                             && officerForm.getOffcrTypeCode().length() > 0
                                                                           ? officerForm.getOffcrTypeCode()
                                                                           : "" );
    BigInteger offcrIntlNbr = ( officerForm.getOffcrIntlNbr() != null
                                && officerForm.getOffcrIntlNbr().length() > 0
                                                                             ? new BigInteger(
                                                                                               officerForm.getOffcrIntlNbr() )
                                                                             : null );

    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrNbr( offcrNbr );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrNameText(
                                                                       offcrNameText );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrCatCode(
                                                                      offcrCatCode );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrChnnlCode(
                                                                        offcrChnnlCode );

    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrStatCode(
                                                                       offcrStatCode );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrEmailName(
                                                                        offcrEmailName );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrRealNbr(
                                                                      offcrRealNbr );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrPhoneOpCode(
                                                                          offcrPhoneOpCode );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrPhoneSecAreaCode(
                                                                               offcrPhoneSecAreaCode );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrPhoneSecOpCode(
                                                                             offcrPhoneSecOpCode );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrPhoneSecNbr(
                                                                          offcrPhoneSecNbr );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrPhoneSecExtnNbr(
                                                                              offcrPhoneSecExtnNbr );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrSecCode(
                                                                      offcrSecCode );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrRemoteCode(
                                                                         offcrRemoteCode );
    officerFncVO.getBaseTbgOfficerEntity().getData().setOffcrNcknName(
                                                                       offcrNcknName );
    officerFncVO.setOffcrTypeCodeDomain( officerForm.getOffcrTypeCodeDomain() );
    officerFncVO.getTplOfficerCmplEntity().getData().setOffcrIntlNbr(
                                                                      offcrIntlNbr );
    officerFncVO.getTplOfficerCmplEntity().getData().setOffcrTypeCode(
                                                                       offcrTypeCode );

    officerFncVO.setExistingData( officerForm.getExistingData() );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseOfficerDetailForm form = ( BaseOfficerDetailForm ) form_;
    BaseOfficerDetailFncVO fncVO = ( BaseOfficerDetailFncVO ) fncVO_;
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    String offcrCatCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrCatCode() != null
                                                                                               ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrCatCode().toString()
                                                                                               : "" );
    String offcrChnnlCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrChnnlCode() != null
                                                                                                   ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrChnnlCode().toString()
                                                                                                   : "" );
    String offcrEmailName = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrEmailName() != null
                                                                                                   ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrEmailName().toString()
                                                                                                   : "" );
    String offcrNameText = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrNameText() != null
                                                                                                 ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrNameText().toString()
                                                                                                 : "" );
    String offcrNbr = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrNbr() != null
                                                                                       ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrNbr().toString()
                                                                                       : "" );
    String offcrNcknName = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrNcknName() != null
                                                                                                 ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrNcknName().toString()
                                                                                                 : "" );
    String offcrPhoneOpCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneOpCode() != null
                                                                                                       ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneOpCode().toString()
                                                                                                       : "" );
    String offcrPhoneSecAreaCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecAreaCode() != null
                                                                                                                 ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecAreaCode().toString()
                                                                                                                 : "" );
    String offcrPhoneSecExtnNbr = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecExtnNbr() != null
                                                                                                               ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecExtnNbr().toString()
                                                                                                               : "" );
    String offcrPhoneSecNbr = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecNbr() != null
                                                                                                       ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecNbr().toString()
                                                                                                       : "" );
    String offcrPhoneSecOpCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecOpCode() != null
                                                                                                             ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrPhoneSecOpCode().toString()
                                                                                                             : "" );
    String offcrRealNbr = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrRealNbr() != null
                                                                                               ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrRealNbr().toString()
                                                                                               : "" );
    String offcrRemoteCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrRemoteCode() != null
                                                                                                     ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrRemoteCode().toString()
                                                                                                     : "" );
    String offcrSecCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrSecCode() != null
                                                                                               ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrSecCode().toString()
                                                                                               : "" );
    String offcrStartDate = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrStartDate() != null
                                                                                                   ? dateFormat.format( fncVO.getBaseTbgOfficerEntity().getData().getOffcrStartDate() )
                                                                                                   : "" );
    String offcrStatCode = ( fncVO.getBaseTbgOfficerEntity().getData().getOffcrStatCode() != null
                                                                                                 ? fncVO.getBaseTbgOfficerEntity().getData().getOffcrStatCode().toString()
                                                                                                 : "" );
    DataSet offcrStatCodeDomain = ( fncVO.getOffcrStatCodeDomain() != null
                                                                          ? fncVO.getOffcrStatCodeDomain()
                                                                          : null );
    DataSet offcrCatCodeDomain = ( fncVO.getOffcrCatCodeDomain() != null
                                                                        ? fncVO.getOffcrCatCodeDomain()
                                                                        : null );
    String offcrTypeCode = ( fncVO.getTplOfficerCmplEntity().getData().getOffcrTypeCode() != null
                             && !fncVO.getTplOfficerCmplEntity().getData().getOffcrTypeCode().equals(
                                                                                                      "" )
                                                                                                          ? fncVO.getTplOfficerCmplEntity().getData().getOffcrTypeCode()
                                                                                                          : "" );
    String offcrIntlNbr = ( fncVO.getTplOfficerCmplEntity().getData().getOffcrIntlNbr() != null
                                                                                               ? fncVO.getTplOfficerCmplEntity().getData().getOffcrIntlNbr().toString()
                                                                                               : "" );

    form.setOffcrCatCode( offcrCatCode );
    form.setOffcrChnnlCode( offcrChnnlCode );
    form.setOffcrEmailName( offcrEmailName );
    form.setOffcrNameText( offcrNameText );
    form.setOffcrNbr( offcrNbr );
    form.setOffcrNcknName( offcrNcknName );
    form.setOffcrPhoneOpCode( offcrPhoneOpCode );
    form.setOffcrPhoneSecAreaCode( offcrPhoneSecAreaCode );
    form.setOffcrPhoneSecExtnNbr( offcrPhoneSecExtnNbr );
    form.setOffcrPhoneSecNbr( offcrPhoneSecNbr );
    form.setOffcrPhoneSecOpCode( offcrPhoneSecOpCode );
    form.setOffcrRealNbr( offcrRealNbr );
    form.setOffcrRemoteCode( offcrRemoteCode );
    form.setOffcrSecCode( offcrSecCode );
    form.setOffcrStartDate( offcrStartDate );
    form.setOffcrStatCode( offcrStatCode );
    form.setOffcrStatCodeDomain( offcrStatCodeDomain );
    form.setOffcrCatCodeDomain( offcrCatCodeDomain );
    form.setOffcrTypeCodeDomain( fncVO.getOffcrTypeCodeDomain() );
    form.setExistingData( fncVO.getExistingData() );
    form.setOffcrIntlNbr( offcrIntlNbr );
    form.setOffcrTypeCode( offcrTypeCode );

  }

  public void load( BaseOfficerDetailFncVO fncVO_ )
  {

    BaseTbgOfficerEntity tbgOfficerEntity = null;
    BaseOfficerDetailFncVO detailFncVO = ( BaseOfficerDetailFncVO ) fncVO_;

    BaseTbgOfficerDAO officerDAO = this.getDAO();
    tbgOfficerEntity = officerDAO.find( detailFncVO.getBaseTbgOfficerEntity() );
    detailFncVO.setBaseTbgOfficerEntity( tbgOfficerEntity );

    if ( detailFncVO.getExistingData().equals( C_EXISTING_DATA_CMPL ) )
    {
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplOfficerCmplDAO tplOfficerCmplDAO = odsDAOFactory.getTplOfficerCmplDAO();
      detailFncVO.getTplOfficerCmplEntity().getData().setOffcrNbr(
                                                                   detailFncVO.getBaseTbgOfficerEntity().getData().getOffcrNbr() );
      detailFncVO.setTplOfficerCmplEntity( tplOfficerCmplDAO.find( detailFncVO.getTplOfficerCmplEntity() ) );

    }

  }

  protected abstract BaseTbgOfficerDAO getDAO();

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new OfficerDetailFncVO();
  }

}