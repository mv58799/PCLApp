package com.citibank.ods.modules.product.broker.functionality;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;
import com.citibank.ods.modules.product.broker.form.BaseBrokerDetailForm;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BaseBrokerDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplBrokerDAO;
/**
 * @author Hamilton Matos
 *  
 */
public abstract class BaseBrokerDetailFnc extends BaseFnc
{

  protected static final String C_DISPLAY_BKR_CNPJ_NBR = "CNPJ da Corretora";

  protected static final String C_DISPLAY_BKR_NAME_TEXT = "Razão social da corretora";

  protected static final String C_DISPLAY_BKR_APPRV_STAT_CODE = "Status Aprovação";

  protected static final String C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_1 = "S";

  protected static final String C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_2 = "N";

  protected static final String C_DISPLAY_BKR_APPRV_STAT_CODE_CONSTRAINT_3 = "A";

  /**
   * Atualiza o FncVO com as informacoes da Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBrokerDetailForm detailForm = ( BaseBrokerDetailForm ) form_;
    BaseBrokerDetailFncVO detailFncVO = ( BaseBrokerDetailFncVO ) fncVO_;
    
	String[] codeArray = null;
	if(detailForm.getSelectedCode()!= null && !detailForm.getSelectedCode().equals("")){
	  codeArray = detailForm.getSelectedCode().split(","); 	
	}
    
    if(codeArray != null){
	  detailFncVO.getBaseTplBrokerEntity().getData().setBkrCnpjNbr(codeArray[0]);
    }
    else if ( detailForm.getBkrCnpjNbr() != null
         && !detailForm.getBkrCnpjNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrCnpjNbr(
                                                                    detailForm.getBkrCnpjNbr() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrCnpjNbr( null );
    }

    if ( detailForm.getBkrNameText() != null
         && !detailForm.getBkrNameText().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrNameText(
                                                                     detailForm.getBkrNameText() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrNameText( null );
    }

    if ( detailForm.getBkrAddrText() != null
         && !detailForm.getBkrAddrText().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrAddrText(
                                                                     detailForm.getBkrAddrText() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrAddrText( null );
    }

    if ( detailForm.getBkrBmfMktCode() != null
         && !detailForm.getBkrBmfMktCode().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrBmfMktCode(
                                                                       detailForm.getBkrBmfMktCode() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrBmfMktCode( null );
    }

    if ( detailForm.getBkrBovespaMktCode() != null
         && !detailForm.getBkrBovespaMktCode().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrBovespaMktCode(
                                                                           detailForm.getBkrBovespaMktCode() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrBovespaMktCode( null );
    }

    if ( detailForm.getBkrRbtBmfRate() != null
         && !detailForm.getBkrRbtBmfRate().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrRbtBmfRate(
                                                                       new BigDecimal(
                                                                                       detailForm.getBkrRbtBmfRate() ) );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrRbtBmfRate( null );
    }

    if ( detailForm.getBkrRbtBovespaRate() != null
         && !detailForm.getBkrRbtBovespaRate().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrRbtBovespaRate(
                                                                           new BigDecimal(
                                                                                           detailForm.getBkrRbtBovespaRate() ) );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrRbtBovespaRate( null );
    }

    if ( detailForm.getBkrReqDate() != null
         && !detailForm.getBkrReqDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplBrokerEntity().getData().setBkrReqDate(
                                                                      formatter.parse( detailForm.getBkrReqDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setBkrReqDate( null );
      }
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrReqDate( null );
    }

    if ( detailForm.getBkrRnwDate() != null
         && !detailForm.getBkrRnwDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplBrokerEntity().getData().setBkrRnwDate(
                                                                      formatter.parse( detailForm.getBkrRnwDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setBkrRnwDate( null );
      }
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrRnwDate( null );
    }

    if ( detailForm.getBkrApprvStatCode() != null
         && !detailForm.getBkrApprvStatCode().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvStatCode(
                                                                          detailForm.getBkrApprvStatCode().toUpperCase() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvStatCode( null );
    }

    if ( detailForm.getBkrApprvDate() != null
         && !detailForm.getBkrApprvDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvDate(
                                                                        formatter.parse( detailForm.getBkrApprvDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setBkrApprvDate( null );
      }
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvDate( null );
    }

    if ( detailForm.getBkrAuthProcSitText() != null
         && !detailForm.getBkrAuthProcSitText().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrAuthProcSitText(
                                                                            detailForm.getBkrAuthProcSitText() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrAuthProcSitText(
                                                                            null );
    }

    if ( detailForm.getBkrReqCrLimLcyAmt() != null
         && !detailForm.getBkrReqCrLimLcyAmt().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrReqCrLimLcyAmt(
                                                                           new BigDecimal(
                                                                                           detailForm.getBkrReqCrLimLcyAmt() ) );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrReqCrLimLcyAmt( null );
    }

    if ( detailForm.getBkrApprvCrLimLcyAmt() != null
         && !detailForm.getBkrApprvCrLimLcyAmt().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvCrLimLcyAmt(
                                                                             new BigDecimal(
                                                                                             detailForm.getBkrApprvCrLimLcyAmt() ) );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvCrLimLcyAmt(
                                                                             null );
    }

    if ( detailForm.getBkrReqCrLimDlrAmt() != null
         && !detailForm.getBkrReqCrLimDlrAmt().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrReqCrLimDlrAmt(
                                                                           new BigDecimal(
                                                                                           detailForm.getBkrReqCrLimDlrAmt() ) );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrReqCrLimDlrAmt( null );
    }

    if ( detailForm.getBkrApprvCrLimDlrAmt() != null
         && !detailForm.getBkrApprvCrLimDlrAmt().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvCrLimDlrAmt(
                                                                             new BigDecimal(
                                                                                             detailForm.getBkrApprvCrLimDlrAmt() ) );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrApprvCrLimDlrAmt(
                                                                             null );
    }

    if ( detailForm.getBkrSuplServText() != null
         && !detailForm.getBkrSuplServText().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrSuplServText(
                                                                         detailForm.getBkrSuplServText() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrSuplServText( null );
    }

    if ( detailForm.getBkrCommentText() != null
         && !detailForm.getBkrCommentText().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrCommentText(
                                                                        detailForm.getBkrCommentText() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setBkrCommentText( null );
    }

    if ( detailForm.getLastUpdUserId() != null
         && !detailForm.getLastUpdUserId().equals( "" ) )
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setLastUpdUserId(
                                                                       detailForm.getLastUpdUserId() );
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setLastUpdUserId( null );
    }

    if ( detailForm.getLastUpdDate() != null
         && !detailForm.getLastUpdDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplBrokerEntity().getData().setLastUpdDate(
                                                                       formatter.parse( detailForm.getLastUpdDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setLastUpdDate( null );
      }
    }
    else
    {
      detailFncVO.getBaseTplBrokerEntity().getData().setLastUpdDate( null );
    }

  }

  /**
   * Atualiza a Form com as informacoes do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBrokerDetailFncVO baseBrokerDetailFncVO = ( BaseBrokerDetailFncVO ) fncVO_;
    BaseBrokerDetailForm baseBrokerDetailForm = ( BaseBrokerDetailForm ) form_;

    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrCnpjNbr() != null )
    {
      baseBrokerDetailForm.setBkrCnpjNbr( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrCnpjNbr().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrCnpjNbr( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrNameText() != null )
    {
      baseBrokerDetailForm.setBkrNameText( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrNameText().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrNameText( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrAddrText() != null )
    {
      baseBrokerDetailForm.setBkrAddrText( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrAddrText().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrAddrText( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrBmfMktCode() != null )
    {
      baseBrokerDetailForm.setBkrBmfMktCode( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrBmfMktCode().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrBmfMktCode( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrBovespaMktCode() != null )
    {
      baseBrokerDetailForm.setBkrBovespaMktCode( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrBovespaMktCode().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrBovespaMktCode( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrRbtBmfRate() != null )
    {
      baseBrokerDetailForm.setBkrRbtBmfRate( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrRbtBmfRate().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrRbtBmfRate( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrRbtBovespaRate() != null )
    {
      baseBrokerDetailForm.setBkrRbtBovespaRate( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrRbtBovespaRate().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrRbtBovespaRate( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrReqDate() != null )
    {
      baseBrokerDetailForm.setBkrReqDate( dateFormat.format( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrReqDate() ) );

    }
    else
    {
      baseBrokerDetailForm.setBkrReqDate( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrRnwDate() != null )
    {
      baseBrokerDetailForm.setBkrRnwDate( dateFormat.format( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrRnwDate() ) );

    }
    else
    {
      baseBrokerDetailForm.setBkrRnwDate( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvStatCode() != null )
    {
      baseBrokerDetailForm.setBkrApprvStatCode( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvStatCode().toString().toUpperCase() );
    }
    else
    {
      baseBrokerDetailForm.setBkrApprvStatCode( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvDate() != null )
    {
      baseBrokerDetailForm.setBkrApprvDate( dateFormat.format( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvDate() ) );

    }
    else
    {
      baseBrokerDetailForm.setBkrApprvDate( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrAuthProcSitText() != null )
    {
      baseBrokerDetailForm.setBkrAuthProcSitText( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrAuthProcSitText().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrAuthProcSitText( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrReqCrLimLcyAmt() != null )
    {
      baseBrokerDetailForm.setBkrReqCrLimLcyAmt( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrReqCrLimLcyAmt().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrReqCrLimLcyAmt( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvCrLimLcyAmt() != null )
    {
      baseBrokerDetailForm.setBkrApprvCrLimLcyAmt( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvCrLimLcyAmt().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrApprvCrLimLcyAmt( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrReqCrLimDlrAmt() != null )
    {
      baseBrokerDetailForm.setBkrReqCrLimDlrAmt( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrReqCrLimDlrAmt().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrReqCrLimDlrAmt( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvCrLimDlrAmt() != null )
    {
      baseBrokerDetailForm.setBkrApprvCrLimDlrAmt( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrApprvCrLimDlrAmt().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrApprvCrLimDlrAmt( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrSuplServText() != null )
    {
      baseBrokerDetailForm.setBkrSuplServText( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrSuplServText().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrSuplServText( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrCommentText() != null )
    {
      baseBrokerDetailForm.setBkrCommentText( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getBkrCommentText().toString() );
    }
    else
    {
      baseBrokerDetailForm.setBkrCommentText( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getLastUpdUserId() != null )
    {
      baseBrokerDetailForm.setLastUpdUserId( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getLastUpdUserId().toString() );
    }
    else
    {
      baseBrokerDetailForm.setLastUpdUserId( "" );
    }

    if ( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getLastUpdDate() != null )
    {
      baseBrokerDetailForm.setLastUpdDate( dateFormat.format( baseBrokerDetailFncVO.getBaseTplBrokerEntity().getData().getLastUpdDate() ) );

    }
    else
    {
      baseBrokerDetailForm.setLastUpdDate( "" );
    }

  }

  public void load( BaseBrokerDetailFncVO brokerDetailFncVO_ )
  {
    BaseTplBrokerEntity brokerEntity;

    BaseTplBrokerDAO brokerDAO = this.getDAO();
    brokerEntity = brokerDAO.find( brokerDetailFncVO_.getBaseTplBrokerEntity() );
    brokerDetailFncVO_.setBaseTplBrokerEntity( brokerEntity );

  }

  protected abstract BaseTplBrokerDAO getDAO();

}