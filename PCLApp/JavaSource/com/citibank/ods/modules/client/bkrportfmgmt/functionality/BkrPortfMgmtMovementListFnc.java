package com.citibank.ods.modules.client.bkrportfmgmt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.form.BkrPortfMgmtMovementListForm;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author hamilton matos
 *  
 */

public class BkrPortfMgmtMovementListFnc extends BaseBkrPortfMgmtListFnc
    implements ODSListFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    BkrPortfMgmtMovementListForm baseBkrPortfMgmtListForm = ( BkrPortfMgmtMovementListForm ) form_;
    BkrPortfMgmtMovementListFncVO baseBkrPortfMgmtListFncVO = ( BkrPortfMgmtMovementListFncVO ) fncVO_;

    String lastUpdUserIdSrc = ( baseBkrPortfMgmtListForm.getLastUpdUserIdSrc() != null
                                && baseBkrPortfMgmtListForm.getLastUpdUserIdSrc().length() > 0
                                                                                              ? baseBkrPortfMgmtListForm.getLastUpdUserIdSrc()
                                                                                              : null );
    baseBkrPortfMgmtListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

    if ( baseBkrPortfMgmtListForm.getCurAcctNbrSrc() != null
         && !baseBkrPortfMgmtListForm.getCurAcctNbrSrc().equals( "" ) )
    {
      baseBkrPortfMgmtListFncVO.setCurAcctNbrSrc( new BigInteger(
                                                                  baseBkrPortfMgmtListForm.getCurAcctNbrSrc() ) );
    }
    else
    {
      baseBkrPortfMgmtListFncVO.setCurAcctNbrSrc( null );
    }

    if ( baseBkrPortfMgmtListForm.getCustMnmcNameSrc() != null
         && !baseBkrPortfMgmtListForm.getCustMnmcNameSrc().equals( "" ) )
    {
      baseBkrPortfMgmtListFncVO.setCustMnmcNameSrc( baseBkrPortfMgmtListForm.getCustMnmcNameSrc() );
    }
    else
    {
      baseBkrPortfMgmtListFncVO.setCustMnmcNameSrc( null );
    }

    if ( baseBkrPortfMgmtListForm.getPortfMgmtProdNameSrc() != null
         && !baseBkrPortfMgmtListForm.getPortfMgmtProdNameSrc().equals( "" ) )
    {
      baseBkrPortfMgmtListFncVO.setPortfMgmtProdNameSrc( baseBkrPortfMgmtListForm.getPortfMgmtProdNameSrc() );
    }
    else
    {
      baseBkrPortfMgmtListFncVO.setPortfMgmtProdNameSrc( null );
    }

    if ( baseBkrPortfMgmtListForm.getProdCodeSrc() != null
         && !baseBkrPortfMgmtListForm.getProdCodeSrc().equals( "" ) )
    {
      baseBkrPortfMgmtListFncVO.setProdCodeSrc( baseBkrPortfMgmtListForm.getProdCodeSrc() );
    }
    else
    {
      baseBkrPortfMgmtListFncVO.setProdCodeSrc( null );
    }

  }

  /**
   * Atualiza os atributos do Form com os atributos vindos da FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    if ( fncVO_.isExecutedList() )
    {
      this.list( fncVO_ );
    }

    super.updateFormFromFncVO( form_, fncVO_ );

  }

  /**
   * Recupera uma lista de corretoras com os critérios especificados
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementListFncVO bkrPortfMgmtMovementListFncVO = ( BkrPortfMgmtMovementListFncVO ) fncVO_;

    TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();
    DataSet results = tplBkrPortfMgmtMovDAO.list(
                                                  bkrPortfMgmtMovementListFncVO.getCustNbrSrc(),
                                                  bkrPortfMgmtMovementListFncVO.getCustFullNameTextSrc(),
                                                  bkrPortfMgmtMovementListFncVO.getCurAcctNbrSrc(),
                                                  bkrPortfMgmtMovementListFncVO.getCustMnmcNameSrc(),
                                                  bkrPortfMgmtMovementListFncVO.getPortfMgmtProdNameSrc(),
                                                  bkrPortfMgmtMovementListFncVO.getProdCodeSrc(),
                                                  bkrPortfMgmtMovementListFncVO.getLastUpdUserIdSrc(),
                                                  bkrPortfMgmtMovementListFncVO.getLoggedUser().getUserID() );

    bkrPortfMgmtMovementListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      bkrPortfMgmtMovementListFncVO.clearMessages();
      bkrPortfMgmtMovementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      bkrPortfMgmtMovementListFncVO.clearMessages();
      bkrPortfMgmtMovementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /**
   * Carregameto inicial - consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementListFncVO bkrPortfMgmtMovementListFncVO = ( BkrPortfMgmtMovementListFncVO ) fncVO_;

    bkrPortfMgmtMovementListFncVO.setCustNbrSrc( null );
    bkrPortfMgmtMovementListFncVO.setCustFullNameTextSrc( null );
    bkrPortfMgmtMovementListFncVO.setBkrCnpjNbrSrc( null );
    bkrPortfMgmtMovementListFncVO.setBkrNameTextSrc( null );
    bkrPortfMgmtMovementListFncVO.setResults( null );

  }

  /**
   * Validação da consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

  public BaseFncVO createFncVO()
  {
    return new BkrPortfMgmtMovementListFncVO();
  }
}