package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.modules.client.curacctprmntinstr.form.BaseCurAcctPrmntInstrDetailForm;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.BaseCurAcctPrmntInstrDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgPointAcctInvstDAO;
import com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/*
 * TODO Retirar do código a descrição do ip em todas as telas da funcionalidade
 */

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.ipdocprvt.functionality;
 * @version 1.0
 * @author michele.monteiro,04/06/2007
 * 
 *  
 */

public class BaseCurAcctPrmntInstrDetailFnc extends BaseFnc
{

  /**
   * Constante do nome da instrução permanente.
   */
  protected final String C_PRMNT_INSTR_CODE = "Número da Instrução Permanente";

  protected final String C_PRMNT_INSTR_TEXT = "Descrição da Instrução Permanente";

  protected final String C_PRMNT_INSTR_INVST_CUR_ACCT_IND = "Indicador de Domínio";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCurAcctPrmntInstrDetailForm form = ( BaseCurAcctPrmntInstrDetailForm ) form_;
    BaseCurAcctPrmntInstrDetailFncVO fncVO = ( BaseCurAcctPrmntInstrDetailFncVO ) fncVO_;

    BigInteger custNbr = ( form.getCustNbrSrc() != null
                           && form.getCustNbrSrc().length() > 0
                                                               ? new BigInteger(
                                                                                 form.getCustNbrSrc() )
                                                               : null );
    BigInteger prodAcctCode = ( form.getProdAcctCode() != null
                                && form.getProdAcctCode().length() > 0
                                                                      ? new BigInteger(
                                                                                        form.getProdAcctCode() )
                                                                      : null );

    BigInteger prodUnderAcctCode = ( form.getProdUnderAcctCode() != null
                                     && form.getProdUnderAcctCode().length() > 0
                                                                                ? new BigInteger(
                                                                                                  form.getProdUnderAcctCode() )
                                                                                : null );
    BigInteger curAcctNbr = ( form.getCurAcctNbr() != null
                              && form.getCurAcctNbr().length() > 0
                                                                  ? new BigInteger(
                                                                                    form.getCurAcctNbr() )
                                                                  : null );
    BigInteger prmntInstrCode = ( form.getPrmntInstrCodeSrc() != null
                                  && form.getPrmntInstrCodeSrc().length() > 0
                                                                             ? new BigInteger(
                                                                                               form.getPrmntInstrCodeSrc() )
                                                                             : null );
    BaseTplCurAcctPrmntInstrEntity curAcctPrmntInstrEntity = new BaseTplCurAcctPrmntInstrEntity();

    if ( !fncVO.isFromSearch() && !fncVO.isRelation())
    {
      if ( form.getSelectedItemsInGrid() != null )
      {
        fncVO.setSelectedItemsInGrid( new ArrayList() );
        for ( int i = 0; i < form.getSelectedItemsInGrid().length; i++ )
        {
          fncVO.getSelectedItemsInGrid().add( form.getSelectedItemsInGrid()[ i ] );

        }

      }
      else
      {
        fncVO.setSelectedItemsInGrid( new ArrayList() );

      }
    }
    if ( !fncVO.isFromSearch() )
    {
      if ( form.getDeletedItems() != null )
      {
        fncVO.setDeletedItems( new ArrayList() );
        for ( int i = 0; i < form.getDeletedItems().length; i++ )
        {
          fncVO.getDeletedItems().add( form.getDeletedItems()[ i ] );

        }

      }
      else
      {
        fncVO.setDeletedItems( new ArrayList() );

      }
    }
    fncVO.setProdAcctCode( prodAcctCode );
    fncVO.setProdUnderAcctCode( prodUnderAcctCode );
    fncVO.setCurAcctNbr( curAcctNbr );
    fncVO.setPrmntInstrCode( prmntInstrCode );
    fncVO.setCustNbr( custNbr );
    fncVO.setClickedSearch( "" );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCurAcctPrmntInstrDetailForm baseCurAcctPrmntInstrDetailForm = ( BaseCurAcctPrmntInstrDetailForm ) form_;
    BaseCurAcctPrmntInstrDetailFncVO baseCurAcctPrmntInstrDetailFncVO = ( BaseCurAcctPrmntInstrDetailFncVO ) fncVO_;

    baseCurAcctPrmntInstrDetailForm.setPrmntInstrInvstCurAcctIndDomain( baseCurAcctPrmntInstrDetailFncVO.getPrmntInstrInvstCurAcctIndDomain() );

    String custNbr = ( baseCurAcctPrmntInstrDetailFncVO.getCustNbr() != null
                                                                            ? baseCurAcctPrmntInstrDetailFncVO.getCustNbr().toString()
                                                                            : null );

    String custFullName = ( baseCurAcctPrmntInstrDetailFncVO.getCustFullName() != null
                            && baseCurAcctPrmntInstrDetailFncVO.getCustFullName().length() > 0
                                                                                              ? baseCurAcctPrmntInstrDetailFncVO.getCustFullName()
                                                                                              : null );
    String prmntText = ( baseCurAcctPrmntInstrDetailFncVO.getPrmntInstrText() != null
                         && baseCurAcctPrmntInstrDetailFncVO.getPrmntInstrText().length() > 0
                                                                                             ? baseCurAcctPrmntInstrDetailFncVO.getPrmntInstrText()
                                                                                             : null );
    String prmntIndCCI = ( baseCurAcctPrmntInstrDetailFncVO.getPrmntInstrInvstCurAcctInd() != null
                           && baseCurAcctPrmntInstrDetailFncVO.getPrmntInstrInvstCurAcctInd().length() > 0
                                                                                                          ? baseCurAcctPrmntInstrDetailFncVO.getPrmntInstrInvstCurAcctInd()
                                                                                                          : null );
    String invstCurAcct = ( baseCurAcctPrmntInstrDetailFncVO.getInvstAcctCurNbr() != null
                            && baseCurAcctPrmntInstrDetailFncVO.getInvstAcctCurNbr().length() > 0
                                                                                                 ? baseCurAcctPrmntInstrDetailFncVO.getInvstAcctCurNbr()
                                                                                                 : null );
    String curAcctNbr = ( baseCurAcctPrmntInstrDetailFncVO.getCurAcctNbr() != null
                                                                                  ? baseCurAcctPrmntInstrDetailFncVO.getCurAcctNbr().toString()
                                                                                  : null );

    if ( baseCurAcctPrmntInstrDetailFncVO.getSelectedItemsInGrid() != null
         && !baseCurAcctPrmntInstrDetailFncVO.getSelectedItemsInGrid().isEmpty() )
    {
      String[] itemsSelectedInForm = new String[ baseCurAcctPrmntInstrDetailFncVO.getSelectedItemsInGrid().size() ];

      for ( int i = 0; i < baseCurAcctPrmntInstrDetailFncVO.getSelectedItemsInGrid().size(); i++ )
      {
        itemsSelectedInForm[ i ] = ( String ) baseCurAcctPrmntInstrDetailFncVO.getSelectedItemsInGrid().get(
                                                                                                             i );
      }

      baseCurAcctPrmntInstrDetailForm.setSelectedItemsInGrid( itemsSelectedInForm );
    }
    else
    {
      String[] itemsLoaded = new String[ baseCurAcctPrmntInstrDetailFncVO.getBaseTplIpDocPrvtEntityList().size() ];

      for ( int i = 0; i < baseCurAcctPrmntInstrDetailFncVO.getBaseTplIpDocPrvtEntityList().size(); i++ )
      {
        itemsLoaded[ i ] = "N";
      }

      baseCurAcctPrmntInstrDetailForm.setSelectedItemsInGrid( itemsLoaded );

    }

    if ( baseCurAcctPrmntInstrDetailFncVO.getDeletedItems() != null
         && !baseCurAcctPrmntInstrDetailFncVO.getDeletedItems().isEmpty() )
    {
      String[] deletedItems = new String[ baseCurAcctPrmntInstrDetailFncVO.getDeletedItems().size() ];

      for ( int i = 0; i < baseCurAcctPrmntInstrDetailFncVO.getDeletedItems().size(); i++ )
      {
        deletedItems[ i ] = ( String ) baseCurAcctPrmntInstrDetailFncVO.getDeletedItems().get(
                                                                                               i );
      }

      baseCurAcctPrmntInstrDetailForm.setDeletedItems( deletedItems );
    }
    else
    {
      String[] deletedItemsLoaded = new String[ baseCurAcctPrmntInstrDetailFncVO.getBaseTplIpDocPrvtEntityList().size() ];

      for ( int i = 0; i < baseCurAcctPrmntInstrDetailFncVO.getBaseTplIpDocPrvtEntityList().size(); i++ )
      {
        deletedItemsLoaded[ i ] = "N";
      }

      baseCurAcctPrmntInstrDetailForm.setDeletedItems( deletedItemsLoaded );

    }

    baseCurAcctPrmntInstrDetailForm.setCustNbrSrc( custNbr );
    baseCurAcctPrmntInstrDetailForm.setCustFullName( custFullName );
    baseCurAcctPrmntInstrDetailForm.setPrmntInstrText( prmntText );
    baseCurAcctPrmntInstrDetailForm.setPrmntInstrInvstCurAcctInd( prmntIndCCI );
    baseCurAcctPrmntInstrDetailForm.setInvstAcctCurNbr( invstCurAcct );
    baseCurAcctPrmntInstrDetailForm.setCurAcctNbr( curAcctNbr );
    baseCurAcctPrmntInstrDetailForm.setClickedSearch( baseCurAcctPrmntInstrDetailFncVO.getClickedSearch() );

  }

  public BaseFncVO createFncVO()
  {
    return new BaseCurAcctPrmntInstrDetailFncVO();
  }

  protected void loadDomains( BaseFncVO fncVO_ )
  {
    BaseCurAcctPrmntInstrDetailFncVO baseCurAcctPrmntInstrDetailFncVO = ( BaseCurAcctPrmntInstrDetailFncVO ) fncVO_;
    baseCurAcctPrmntInstrDetailFncVO.setPrmntInstrInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  public void load( BaseFncVO fncVO_ )
  {

    BaseCurAcctPrmntInstrDetailFncVO fncVO = ( BaseCurAcctPrmntInstrDetailFncVO ) fncVO_;

    BaseTplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
    BaseTo3ProductAccountEntity to3ProductAccountEntity = new To3ProductAccountEntity();

    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    BaseTo3ProductAccountDAO to3ProductAccountDAO = factory.getTo3ProductAccountDAO();

    to3ProductAccountEntity.getData().setProdAcctCode( fncVO.getProdAcctCode() );
    to3ProductAccountEntity.getData().setProdUnderAcctCode(
                                                            fncVO.getProdUnderAcctCode() );
    to3ProductAccountEntity = to3ProductAccountDAO.find( to3ProductAccountEntity );
    fncVO.setCurAcctNbr( to3ProductAccountEntity.getData().getCurAcctNbr() );

    if ( to3ProductAccountEntity.getData().getCustNbr() != null )
    {
      customerPrvtEntity.getData().setCustNbr(
                                               to3ProductAccountEntity.getData().getCustNbr() );
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      BaseTplCustomerPrvtDAO customerPrvtDAO = odsDAOFactory.getTplCustomerPrvtDAO();
      customerPrvtEntity = customerPrvtDAO.find( customerPrvtEntity );
      fncVO.setCustFullName( customerPrvtEntity.getData().getCustFullNameText() );
      fncVO.setCustNbr( customerPrvtEntity.getData().getCustNbr() );

    }
    else
    {
      fncVO.setCustFullName( "" );
      fncVO.setCustNbr( null );
    }
    if ( fncVO.getPrmntInstrCode() != null )
    {

      TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();
      BaseTplIpDocPrvtEntity tplIpDocPrvtEntity = new TplIpDocPrvtEntity();

      tplIpDocPrvtEntity.getData().setIpDocCode( fncVO.getPrmntInstrCode() );
      tplIpDocPrvtEntity.getData().setCustNbr( fncVO.getCustNbr() );

      if ( ipDocPrvtDAO.existsActive( ( TplIpDocPrvtEntity ) tplIpDocPrvtEntity ) )
      {
        tplIpDocPrvtEntity = ipDocPrvtDAO.find( tplIpDocPrvtEntity );
        fncVO.setPrmntInstrText( tplIpDocPrvtEntity.getData().getIpDocText() );
        fncVO.setPrmntInstrInvstCurAcctInd( tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd() );
      }
      else
      {
        fncVO.setPrmntInstrText( "" );
        fncVO.setPrmntInstrInvstCurAcctInd( "" );
      }

      fncVO.setFromSearch( false );

    }
    if (!"".equals(fncVO.getPrmntInstrInvstCurAcctInd()) &&  fncVO.getPrmntInstrInvstCurAcctInd().equals( "S" ) )
    {
      TbgPointAcctInvstDAO tbgPointAcctInvstDAO = ODSDAOFactory.getInstance().getTbgPointAcctInvstDAO();
      TbgPointAcctInvstEntity tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

      tbgPointAcctInvstEntity.getData().setCurAcctNbr(
                                                       fncVO.getCurAcctNbr().toString() );
      if ( tbgPointAcctInvstDAO.exists( tbgPointAcctInvstEntity ) )
      {
        tbgPointAcctInvstEntity = tbgPointAcctInvstDAO.find( tbgPointAcctInvstEntity );
        fncVO.setInvstAcctCurNbr( tbgPointAcctInvstEntity.getData().getInvstCurAcctNbr() );

      }
      else
      {
        fncVO.setInvstAcctCurNbr( "" );

      }
    }
    if ( fncVO.getPrmntInstrCode() == null )
    {
      fncVO.setInvstAcctCurNbr( "" );
      fncVO.setPrmntInstrText( "" );
      fncVO.setPrmntInstrInvstCurAcctInd( "" );

    }
    
    loadDomains( fncVO );

  }

  /**
   * Converte uma data para o formato de apresentação.
   * 
   * @param date_ - A data a ser convertida.
   * @return String - A data no formato de apresentação.
   */
  protected String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    return dateFormat.format( date_ );
  }

}