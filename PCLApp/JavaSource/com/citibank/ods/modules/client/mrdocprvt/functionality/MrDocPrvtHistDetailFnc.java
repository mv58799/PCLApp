package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.functionality.ODSHistoryDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtHistEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtHistDetailForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.BaseMrDocPrvtDetailFncVO;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtHistDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de histórico da tabela de Memória de Risco.
 */
public class MrDocPrvtHistDetailFnc extends BaseMrDocPrvtDetailFnc implements
    ODSHistoryDetailFnc
{

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    MrDocPrvtHistDetailForm form = ( MrDocPrvtHistDetailForm ) form_;
    MrDocPrvtHistDetailFncVO fncVO = ( MrDocPrvtHistDetailFncVO ) fncVO_;

    TplMrDocPrvtHistEntityVO mrDocPrvtHistEntityVO = ( TplMrDocPrvtHistEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
    To3ProductAccountEntityVO to3ProductAccountEntityVO = ( To3ProductAccountEntityVO ) fncVO.getTo3ProductAccountEntity().getData();

    String mrDocCode = ( mrDocPrvtHistEntityVO.getMrDocCode() != null
                                                                     ? mrDocPrvtHistEntityVO.getMrDocCode().toString()
                                                                     : null );
    String custNbr = ( to3ProductAccountEntityVO.getCustNbr() != null
                                                                     ? to3ProductAccountEntityVO.getCustNbr().toString()
                                                                     : null );
    String curAcctNbr = ( to3ProductAccountEntityVO.getCurAcctNbr() != null
                                                                           ? to3ProductAccountEntityVO.getCurAcctNbr().toString()
                                                                           : null );
    String lastAuthUserId = ( mrDocPrvtHistEntityVO.getLastAuthUserId() != null
                                                                               ? mrDocPrvtHistEntityVO.getLastAuthUserId()
                                                                               : null );
    String lastAuthDate = ( mrDocPrvtHistEntityVO.getLastAuthDate() != null
                                                                           ? formatDateTime( mrDocPrvtHistEntityVO.getLastAuthDate() )
                                                                           : null );
    String mrRefDate = ( mrDocPrvtHistEntityVO.getMrDocRefDate() != null
                                                                        ? formatDateTime( mrDocPrvtHistEntityVO.getMrDocRefDate() )
                                                                        : null );

    form.setCurAcctNbr( curAcctNbr );
    form.setMrDocCode( mrDocCode );
    form.setCustNbr( custNbr );
    form.setLastAuthUserId( lastAuthUserId );
    form.setLastAuthDate( lastAuthDate );
    form.setMrDocRefDate( mrRefDate );
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    MrDocPrvtHistDetailForm form = ( MrDocPrvtHistDetailForm ) form_;
    MrDocPrvtHistDetailFncVO fncVO = ( MrDocPrvtHistDetailFncVO ) fncVO_;

    // Recupera o código documento MR selecionado no grid de consulta em lista
    BigInteger mrDocCode = ( form.getMrDocCode() != null
                             && form.getMrDocCode().length() > 0
                                                                ? new BigInteger(
                                                                                  form.getMrDocCode() )
                                                                : null );
    String lastUserUpd = ( form.getLastUpdUserId() != null
                           && form.getLastUpdUserId().length() > 0
                                                                  ? form.getLastUpdUserId()
                                                                  : null );
    String lastAuthUserId = ( form.getLastAuthUserId() != null
                              && form.getLastAuthUserId().length() > 0
                                                                      ? form.getLastAuthUserId()
                                                                      : null );

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_TIMESTAMP );

    Date mrDocRefDate;
    Date lastUpdDate;
    Date lastAuthDate;

    try
    {
      mrDocRefDate = ( form.getMrDocRefDate() != null
                       && form.getMrDocRefDate().length() > 0
                                                             ? formatter.parse( form.getMrDocRefDate() )
                                                             : null );

    }
    catch ( ParseException e_ )
    {
      mrDocRefDate = null;

    }

    TplMrDocPrvtHistEntityVO mrDocPrvtHistEntityVO = ( TplMrDocPrvtHistEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
    mrDocPrvtHistEntityVO.setMrDocCode( mrDocCode );
    mrDocPrvtHistEntityVO.setMrDocRefDate( mrDocRefDate );
    //    mrDocPrvtHistEntityVO.setLastAuthDate( lastAuthDate );
    mrDocPrvtHistEntityVO.setLastAuthUserId( lastAuthUserId );
    //    mrDocPrvtHistEntityVO.setLastUpdDate( lastUpdDate );
    mrDocPrvtHistEntityVO.setLastUpdUserId( lastUserUpd );
  }

  /**
   * @see com.citibank.ods.modules.client.mrdocprvt.functionality.BaseMrDocPrvtDetailFnc#getDAO()
   */
  protected BaseTplMrDocPrvtDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocPrvtHistDAO mrDocPrvtHistDA = odsDAOFactory.getTplMrDocPrvtHistDAO();
    return mrDocPrvtHistDA;
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new MrDocPrvtHistDetailFncVO();
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSHistoryDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    BaseMrDocPrvtDetailFncVO fncVO = ( BaseMrDocPrvtDetailFncVO ) fncVO_;
    BaseTplMrDocPrvtDAO mrDocPrvtDAO = this.getDAO();
    BaseTplMrDocPrvtEntity tplMrDocPrvtEntity = mrDocPrvtDAO.find( fncVO.getTplMrDocPrvtEntity() );

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    BaseTo3ProductAccountDAO productAccountDAO = odsDAOFactory.getTo3ProductAccountDAO();
    BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();
    productAccountEntity.getData().setProdAcctCode(
                                                    fncVO.getTplMrDocPrvtEntity().getData().getProdAcctCode() );
    productAccountEntity.getData().setProdUnderAcctCode(
                                                         fncVO.getTplMrDocPrvtEntity().getData().getProdUnderAcctCode() );
    productAccountEntity = productAccountDAO.find( productAccountEntity );

    fncVO.setTo3ProductAccountEntity( productAccountEntity );
    fncVO.setTplMrDocPrvtEntity( tplMrDocPrvtEntity );
    super.loadDomains( fncVO );
    super.load( fncVO_ );

  }

  private String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    return dateFormat.format( date_ );
  }

}