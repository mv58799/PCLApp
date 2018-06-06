package com.citibank.ods.modules.client.customerprvt.functionality;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.entity.pl.TplErEmEntity;
import com.citibank.ods.modules.client.customerprvt.form.BaseCustomerPrvtDetailForm;
import com.citibank.ods.modules.client.customerprvt.functionality.valueobject.BaseCustomerPrvtDetailFncVO;
import com.citibank.ods.modules.client.customerprvt.functionality.valueobject.CustomerPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;


//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.customerPrvt.functionality; 
 *@version 1.0
 *@author l.braga,14/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class CustomerPrvtDetailFnc extends BaseCustomerPrvtDetailFnc implements ODSDetailFnc
{

  /* 
   * Cria uma Instacia do DAO
   * @see com.citibank.ods.modules.client.customerprvt.functionality.BaseCustomerPrvtDetailFnc#getDAO()
   */
  protected BaseTplCustomerPrvtDAO getDAO()
  {
   ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TplCustomerPrvtDAO customerPrvtDAO = factory.getTplCustomerPrvtDAO();
    return customerPrvtDAO;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return new CustomerPrvtDetailFncVO();
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
  
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
	  super.updateFormFromFncVO(form_,fncVO_);
	  
	  BaseCustomerPrvtDetailForm form = ( BaseCustomerPrvtDetailForm ) form_;
	  BaseCustomerPrvtDetailFncVO fncVO = ( BaseCustomerPrvtDetailFncVO ) fncVO_;
	  
    //Atualização do grid de ER Relacionados
	ArrayList domainsEmNbr = new ArrayList();
	ArrayList domainsErNbr = new ArrayList();

	ArrayList erEmEntities = fncVO.getErEmEntities();
	Iterator erEmEntitiesIt = erEmEntities.iterator();

	while ( erEmEntitiesIt.hasNext() )
	{

		TplErEmEntity emEntity = ( TplErEmEntity ) erEmEntitiesIt.next();

	  if ( emEntity.getData().getErNbr() != null )
	  {
		domainsErNbr.add( emEntity.getData().getErNbr() );
	  }
	  else
	  {
		domainsErNbr.add( "" );
	  }

	  if ( emEntity.getData().getEmNbr() != null )
	  {
		domainsEmNbr.add( emEntity.getData().getEmNbr() );
	  }
	  else
	  {
		domainsEmNbr.add( "" );
	  }
	}

	String[] domainsErNbrArray = new String[ domainsErNbr.size() ];
	String[] domainsEmNbrArray = new String[ domainsEmNbr.size() ];
	
	//	Fazendo a verredura de campos pelo atributo chave, atribuindo os
	// valores
	// aos vetores
	for (int i = 0; i < domainsEmNbr.size(); i++) {
	  domainsEmNbrArray[i] = domainsEmNbr.get(i).toString();
	  domainsErNbrArray[i] = domainsErNbr.get(i).toString();	
	}
	
	form.setDomainsEmNbr(domainsEmNbrArray);
	form.setDomainsErNbr(domainsErNbrArray);
	  
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
   * Metodo que inicializa tela de detalhe carregando os atributos.
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    load((CustomerPrvtDetailFncVO)fncVO_);
    loadDomains((CustomerPrvtDetailFncVO)fncVO_);
    loadErEm((CustomerPrvtDetailFncVO)fncVO_); 
    
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

}
