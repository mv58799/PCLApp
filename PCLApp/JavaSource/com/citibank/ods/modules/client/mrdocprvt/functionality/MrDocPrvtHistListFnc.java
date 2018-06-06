package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtHistListForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtHistListFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de consulta em lista do histórico da tabela
 * de Memória de Risco.
 */
public class MrDocPrvtHistListFnc extends BaseMrDocPrvtListFnc implements
    ODSListFnc
{

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new MrDocPrvtHistListFncVO();
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    // Faz cast para os tipos corretos
    MrDocPrvtHistListFncVO fncVO = ( MrDocPrvtHistListFncVO ) fncVO_;
    MrDocPrvtHistListForm form = ( MrDocPrvtHistListForm ) form_;

    BigInteger mrDocPrvt = ( form.getSelectedMrDocPrvt() != null
                             && form.getSelectedMrDocPrvt().length() > 0
                                                                        ? new BigInteger(
                                                                                          form.getSelectedMrDocPrvt() )

                                                                        : null );
    //Código Documento MR (campo de pesquisa)
    BigInteger mrDocCode = ( form.getMrDocCodeSrc() != null
                             && form.getMrDocCodeSrc().length() > 0
                                                                   ? new BigInteger(
                                                                                     form.getMrDocCodeSrc() )

                                                                   : null );

    BigInteger prodCode = ( form.getSelectedProdAcctCode() != null
                            && form.getSelectedProdAcctCode().length() > 0
                                                                          ? new BigInteger(
                                                                                            form.getSelectedProdAcctCode() )
                                                                          : null );
    BigInteger prodUnderCode = ( form.getSelectedProdUnderAcctCode() != null
                                 && form.getSelectedProdAcctCode().length() > 0
                                                                               ? new BigInteger(
                                                                                                 form.getSelectedProdUnderAcctCode() )
                                                                               : null );

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    try
    {
      fncVO.setMrDocRefDate( formatter.parse( form.getMrDocRefDateSrc() ) );
    }
    catch ( ParseException e_ )
    {
      fncVO.setMrDocRefDate( null );
    }

    fncVO.setMrDocCode( mrDocCode );
    fncVO.setSelectedMrDocPrvt( mrDocPrvt );
    fncVO.setSelectedProdAcctCode( prodCode );
    fncVO.setSelectedProdUnderAcctCode( prodUnderCode );

  }

  public void load( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );
    MrDocPrvtHistListFncVO fncVO = ( MrDocPrvtHistListFncVO ) fncVO_;

    if ( fncVO.getSelectedProdAcctCode() != null )
    {
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

      BaseTo3ProductAccountEntity to3ProductAccountEntity = new To3ProductAccountEntity();
      BaseTo3ProductAccountDAO to3ProductAccountDAO = odsDAOFactory.getTo3ProductAccountDAO();
      to3ProductAccountEntity.getData().setProdAcctCode(
                                                         fncVO.getSelectedProdAcctCode() );
      to3ProductAccountEntity.getData().setProdUnderAcctCode(
                                                              fncVO.getSelectedProdUnderAcctCode() );

      to3ProductAccountEntity = to3ProductAccountDAO.find( to3ProductAccountEntity );

      fncVO.setCustNbr( to3ProductAccountEntity.getData().getCustNbr() );
      fncVO.setCurAcctNbr( to3ProductAccountEntity.getData().getCurAcctNbr() );
    }
    else if ( fncVO.getSelectedMrDocPrvt() != null )
    {

      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      BaseTplMrDocPrvtDAO mrDocPrvtDAO = odsDAOFactory.getTplMrDocPrvtDAO();
      BaseTplMrDocPrvtEntity mrDocPrvtEntity = new TplMrDocPrvtEntity();
      TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity.getData();
      mrDocPrvtEntityVO.setMrDocPrvt( fncVO.getSelectedMrDocPrvt() );
      mrDocPrvtEntityVO.setProdAcctCode( fncVO.getSelectedProdAcctCode() );
      mrDocPrvtEntityVO.setProdUnderAcctCode( fncVO.getSelectedProdUnderAcctCode() );
      mrDocPrvtEntity = mrDocPrvtDAO.find( mrDocPrvtEntity );

      fncVO.setMrDocText( mrDocPrvtEntity.getData().getMrDocText() );
      fncVO.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
      fncVO.setMrInvstCurAcctInd( mrDocPrvtEntity.getData().getMrInvstCurAcctInd() );
    }
    else
    {
      
      //fncVO.setCustNbr( null );
    }
    
    fncVO.setMrInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.mrdocprvt.functionality.BaseMrDocPrvtListFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    // Faz cast para os tipos corretos
    MrDocPrvtHistListFncVO fncVO = ( MrDocPrvtHistListFncVO ) fncVO_;
    MrDocPrvtHistListForm form = ( MrDocPrvtHistListForm ) form_;

    form.setCustNbrSrc( fncVO.getCustNbr() != null
                                                  ? fncVO.getCustNbr().toString()
                                                  : "" );
    form.setCurAcctNbrSrc( fncVO.getCurAcctNbr() != null
                                                        ? fncVO.getCurAcctNbr().toString()
                                                        : "" );
    form.setMrDocCodeSrc( fncVO.getMrDocCode() != null
                                                      ? fncVO.getMrDocCode().toString()
                                                      : "" );
    form.setMrDocTextSrc( fncVO.getMrDocText() != null
                          && !fncVO.getMrDocText().equals( "" )
                                                               ? fncVO.getMrDocText()
                                                               : "" );
    form.setMrInvstCurAcctIndSrc( fncVO.getMrInvstCurAcctInd() != null
                                  && !fncVO.getMrInvstCurAcctInd().equals( "" )
                                                                               ? fncVO.getMrInvstCurAcctInd()
                                                                               : "" );

  }

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    MrDocPrvtHistListFncVO fncVO = ( MrDocPrvtHistListFncVO ) fncVO_;

    // Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplMrDocPrvtHistDAO tplMrDocPrvtHistDAO = factory.getTplMrDocPrvtHistDAO();
    DataSet results = tplMrDocPrvtHistDAO.list(
                                                fncVO.getMrDocCode(),
                                                fncVO.getMrDocText(),
                                                fncVO.getMrInvstCurAcctInd(),
                                                fncVO.getCustNbr(),
                                                fncVO.getCustFullNameText(),
                                                fncVO.getCurAcctNbr(),
                                                ( ( MrDocPrvtHistListFncVO ) fncVO ).getMrDocRefDate() );

    fncVO.setResults( results );

    //  Valida resultado
    if ( results.size() > 0 )
    {
      fncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      fncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

}