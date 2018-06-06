package com.citibank.ods.modules.client.portfolioprvt.functionality;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.BasePortfolioPrvtDetailFncVO;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.PortfolioPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplPortfolioPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplPortfolioPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;



//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject; 
 *@version 1.0
 *@author l.braga,02/04/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class PortfolioPrvtDetailFnc extends BasePortfolioPrvtDetailFnc implements ODSDetailFnc
{

  /* 
   * Retorno:  Retorna a instancia do PortfolioPrvtDetailFncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
 
    return new PortfolioPrvtDetailFncVO();
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /*
   * Atualiza o fncFVO com os dados de detalhe.
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    
    loadPortfolioPrvt((PortfolioPrvtDetailFncVO ) fncVO_);
    loadOffNameText(( BasePortfolioPrvtDetailFncVO ) fncVO_);
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }
  /*
   * Cria uma instacia do DAO.
   * @see com.citibank.ods.modules.client.portfolioprvt.functionality.BasePortfolioPrvtDetailFnc#getDAO()
   */
  
  protected BaseTplPortfolioPrvtDAO getDAO()
  {
   ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TplPortfolioPrvtDAO portfolioPrvtDAO = factory.getTplPortfolioPrvtDAO();
    return portfolioPrvtDAO;
  }


}
