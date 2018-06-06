package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtMovListForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtMovListFncVO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de consulta em lista do movimento da tabela
 * de Memória de Risco.
 */
public class MrDocPrvtMovListFnc extends BaseMrDocPrvtListFnc implements
    ODSListFnc
{
  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new MrDocPrvtMovListFncVO();
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    // Faz cast para os tipos corretos
    MrDocPrvtMovListFncVO fncVO = ( MrDocPrvtMovListFncVO ) fncVO_;
    MrDocPrvtMovListForm form = ( MrDocPrvtMovListForm ) form_;

    //Código Documento MR (campo de pesquisa)
    BigInteger mrDocCode = ( form.getMrDocCodeSrc() != null
                             && form.getMrDocCodeSrc().length() > 0
                                                                   ? new BigInteger(
                                                                                     form.getMrDocCodeSrc() )
                                                                   : null );

    String lastUpdUserId = ( form.getLastUpdUserIdSrc() != null
                             && form.getLastUpdUserIdSrc().length() > 0
                                                                       ? form.getLastUpdUserIdSrc()
                                                                       : null );

    Date lastUpdDate = null;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    try
    {
      lastUpdDate = ( form.getLastUpdDateSrc() != null
                      && form.getLastUpdDateSrc().length() > 0
                                                              ? formatter.parse( form.getLastUpdDateSrc() )
                                                              : null );
    }
    catch ( ParseException e )
    {
      lastUpdDate = null;
    }

    fncVO.setMrDocCode( mrDocCode );
    fncVO.setLastUpdUserId( lastUpdUserId );
    fncVO.setLastUpdDate( lastUpdDate );
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    validateList( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      //Os campos critérios de pesquisa
      updateSearchFields( fncVO_ );
      //Cria uma instancia do fncVO específico.
      MrDocPrvtMovListFncVO fncVO = ( MrDocPrvtMovListFncVO ) fncVO_;
      // Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      // Obtém a instância do DAO necessário
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = factory.getTplMrDocPrvtMovDAO();
      // Recupera valores do DAO
      DataSet results = mrDocPrvtMovDAO.list( fncVO.getMrDocCode(),
                                              fncVO.getMrDocText(),
                                              fncVO.getMrInvstCurAcctInd(),
                                              fncVO.getCustNbr(),
                                              fncVO.getCustFullNameText(),
                                              fncVO.getCurAcctNbr(),
                                              fncVO.getLastUpdUserId(),
                                              fncVO.getLastUpdDate(),
                                              null,
                                              null );

      // Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      fncVO.setResults( results );

      if ( results.size() > 0 )
      {
        fncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      }
      else
      {
        fncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
      }
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );
    MrDocPrvtMovListFncVO fncVO = ( MrDocPrvtMovListFncVO ) fncVO_;
    fncVO.setMrInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

}