package com.citibank.ods.modules.client.bkrportfmgmt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.form.BaseBkrPortfMgmtDetailForm;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BaseBkrPortfMgmtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplBkrPortfMgmtDAO;

/**
 * @author Hamilton Matos
 *  
 */

public abstract class BaseBkrPortfMgmtDetailFnc extends BaseFnc
{

  /**
   * Atualiza o FncVO com as informações do Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBkrPortfMgmtDetailForm detailForm = ( BaseBkrPortfMgmtDetailForm ) form_;
    BaseBkrPortfMgmtDetailFncVO detailFncVO = ( BaseBkrPortfMgmtDetailFncVO ) fncVO_;

    if ( detailForm.getBkrCnpjNbrSrc() != null
         && !detailForm.getBkrCnpjNbrSrc().equals( "" ) )
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrCnpjNbr(
                                                                          detailForm.getBkrCnpjNbrSrc() );
    }
    else
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrCnpjNbr( null );
    }

    if ( detailForm.getBkrNameTextSrc() != null
         && !detailForm.getBkrNameTextSrc().equals( "" ) )
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrNameText(
                                                                           detailForm.getBkrNameTextSrc() );
    }
    else
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrNameText( null );
    }

    if ( detailForm.getCustNbrSrc() != null
         && !detailForm.getCustNbrSrc().equals( "" ) )
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setCustNbr(
                                                                       new BigInteger(
                                                                                       detailForm.getCustNbrSrc() ) );

    }
    else
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setCustNbr( null );
    }

    detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setCustFullNameText(
                                                                              detailForm.getCustFullNameTextSrc() );

    if ( detailForm.getBkrCustNbr() != null
         && !detailForm.getBkrCustNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrCustNbr(
                                                                          new BigInteger(
                                                                                          detailForm.getBkrCustNbr() ) );
    }
    else
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrCustNbr( null );
    }

    if ( detailForm.getSelectedItemsInBrokerGrid() != null )
    {
      detailFncVO.setSelectedItemsInBrokerGrid( new ArrayList() );
      for ( int i = 0; i < detailForm.getSelectedItemsInBrokerGrid().length; i++ )
      {
        detailFncVO.getSelectedItemsInBrokerGrid().add(
                                                        detailForm.getSelectedItemsInBrokerGrid()[ i ] );
      }
    }
    else
    {
      detailFncVO.setSelectedItemsInBrokerGrid( new ArrayList() );
    }

    if ( detailForm.getSelectedItemsInBkrPortfMgmtGrid() != null )
    {
      detailFncVO.setSelectedItemsInBkrPortfMgmtGrid( new ArrayList() );

      for ( int i = 0; i < detailForm.getSelectedItemsInBkrPortfMgmtGrid().length; i++ )
      {
        detailFncVO.getSelectedItemsInBkrPortfMgmtGrid().add(
                                                              detailForm.getSelectedItemsInBkrPortfMgmtGrid()[ i ] );

      }
    }
    else
    {
      detailFncVO.setSelectedItemsInBkrPortfMgmtGrid( new ArrayList() );
    }

    if ( detailForm.getSelectedProdAcctCode() != null
         && !detailForm.getSelectedProdAcctCode().equals( "" ) )
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdAcctCode(
                                                                            new BigInteger(
                                                                                            detailForm.getSelectedProdAcctCode() ) );
    }
    else
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdAcctCode(
                                                                            null );
    }

    if ( detailForm.getSelectedProdUnderAcctCode() != null
         && !detailForm.getSelectedProdUnderAcctCode().equals( "" ) )
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdUnderAcctCode(
                                                                                 new BigInteger(
                                                                                                 detailForm.getSelectedProdUnderAcctCode() ) );
    }
    else
    {
      detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdUnderAcctCode(
                                                                                 null );
    }

  }

  /**
   * Atualiza o Form com as informações do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBkrPortfMgmtDetailFncVO baseBkrPortfMgmtDetailFncVO = ( BaseBkrPortfMgmtDetailFncVO ) fncVO_;
    BaseBkrPortfMgmtDetailForm baseBkrPortfMgmtDetailForm = ( BaseBkrPortfMgmtDetailForm ) form_;

    if ( baseBkrPortfMgmtDetailFncVO.isConfirmAssociationEnabled() == true )
    {
      baseBkrPortfMgmtDetailForm.setConfirmAssociationEnabled( "true" );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setConfirmAssociationEnabled( "false" );
    }

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrCnpjNbr() != null )
    {
      baseBkrPortfMgmtDetailForm.setBkrCnpjNbrSrc( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrCnpjNbr().toString() );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setBkrCnpjNbrSrc( "" );
    }

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrNameText() != null )
    {
      baseBkrPortfMgmtDetailForm.setBkrNameTextSrc( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrNameText().toString() );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setBkrNameTextSrc( "" );
    }

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr() != null )
    {
      baseBkrPortfMgmtDetailForm.setCustNbrSrc( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr().toString() );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setCustNbrSrc( "" );
    }

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustFullNameText() != null )
    {
      baseBkrPortfMgmtDetailForm.setCustFullNameTextSrc( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustFullNameText().toString() );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setCustFullNameTextSrc( "" );
    }

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrCustNbr() != null )
    {
      baseBkrPortfMgmtDetailForm.setBkrCustNbr( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrCustNbr().toString() );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setBkrCustNbr( "" );
    }

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode() != null )
    {
      baseBkrPortfMgmtDetailForm.setSelectedProdAcctCode( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode().toString() );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setSelectedProdAcctCode( "" );
    }

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode() != null )
    {
      baseBkrPortfMgmtDetailForm.setSelectedProdUnderAcctCode( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode().toString() );
    }
    else
    {
      baseBkrPortfMgmtDetailForm.setSelectedProdUnderAcctCode( "" );
    }

    // Popula o grid "Corretoras Associadas a Carteira Administrada".
    baseBkrPortfMgmtDetailForm.setPortfolioResults( baseBkrPortfMgmtDetailFncVO.getPortfolioResults() );
  }

  public void load( BaseBkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO_ )
  {
    //
  }

  protected abstract BaseTplBkrPortfMgmtDAO getDAO();

}