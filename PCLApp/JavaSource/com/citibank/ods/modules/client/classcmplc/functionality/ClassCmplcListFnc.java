package com.citibank.ods.modules.client.classcmplc.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.ClassCmplcListFncVO;
import com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.classcmplc.functionality.valueobject; 
 *@version 1.0
 *@author gerson.a.rodrigues,Mar 13, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class ClassCmplcListFnc extends BaseClassCmplcListFnc implements ODSListFnc
{

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ClassCmplcListFncVO classCmplcListFncVO = ( ClassCmplcListFncVO ) fncVO_;
    
    validateList(fncVO_);
    
    if ( !classCmplcListFncVO.hasErrors() )
    {
      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplClassCmplcDAO classCmplcDAO = factory.getTplClassCmplcDAO();
      // Recupera valores do DAO
      DataSet result = classCmplcDAO.list(
                                               classCmplcListFncVO.getClassCmplcCodeSrc(),
                                               classCmplcListFncVO.getClassCmplcTextSrc(),
                                               classCmplcListFncVO.getSensIndSrc());
      
      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      classCmplcListFncVO.setResults( result );
      if ( result.size() > 0 )
      {
        classCmplcListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      }
      else
      {
        classCmplcListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
      }
    }
    
  }


  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //   
  }
  

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {    
    return new ClassCmplcListFncVO();
  }

}
