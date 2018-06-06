package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtListForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de consulta em lista da tabela de Memória de
 * Risco.
 */
public class MrDocPrvtListFnc extends BaseMrDocPrvtListFnc implements
    ODSListFnc
{
  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new MrDocPrvtListFncVO();
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
      MrDocPrvtListFncVO fncVO = ( MrDocPrvtListFncVO ) fncVO_;
      // Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      // Obtém a instância do DAO necessário
      TplMrDocPrvtDAO mrDocPrvtDAO = factory.getTplMrDocPrvtDAO();
      // Recupera valores do DAO
      DataSet result = mrDocPrvtDAO.list( fncVO.getCustFullNameText(),
	                                      fncVO.getInvstCurAcctNbr(),
		                                  fncVO.getCurAcctNbr());

      // Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      fncVO.setResults( result );

      if ( result.size() > 0 )
      {
        fncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      }
      else
      {
        fncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
      }
    }
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    // Faz cast para os tipos corretos
    MrDocPrvtListFncVO fncVO = ( MrDocPrvtListFncVO ) fncVO_;
    MrDocPrvtListForm form = ( MrDocPrvtListForm ) form_;

    //Código Documento MR (campo de pesquisa)
    BigInteger mrDocPrvt = ( form.getMrDocPrvtSrc() != null
                             && form.getMrDocPrvtSrc().length() > 0
                                                                   ? new BigInteger(
                                                                                     form.getMrDocPrvtSrc() )
                                                                   : null );
    fncVO.setMrDocPrvt( mrDocPrvt );

  }

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );
    MrDocPrvtListFncVO fncVO = ( MrDocPrvtListFncVO ) fncVO_;
    fncVO.setMrInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }
}