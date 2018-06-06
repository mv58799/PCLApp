/*
 * Created on Apr 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.relationeg.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.relationeg.form.RelationEgListForm;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgListFncVO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RelationEgListFnc extends BaseRelationEgListFnc implements
    ODSListFnc
{
	
	public static final int MAXIMUM_REGISTERS = 50;

  /**
   * Executa a consulta em Lista do sub-módulo
   */
  public void list( BaseFncVO fncVO_ )
  {
    RelationEgListFncVO egListFncVO = ( RelationEgListFncVO ) fncVO_;

    TplRelationEgDAO tplRelationEgDAO = ODSDAOFactory.getInstance().getTplRelationEgDAO();
    DataSet results = tplRelationEgDAO.list( egListFncVO.getReltnNbr(),
                                             egListFncVO.getEgNbr(),
                                             egListFncVO.getErNbrSrc() );

    if ( results.size() > 0 )
    {
      egListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      
      if(results.size()>MAXIMUM_REGISTERS){
    	  egListFncVO.setFlagAllData("N");
    	  results = results.newDataSetByRange(1, MAXIMUM_REGISTERS);
      }
      else{
    	  egListFncVO.setFlagAllData("S");
      }
    }
    else
    {
      egListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
    
    egListFncVO.setResults( results );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new RelationEgListFncVO();
  }
  
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    RelationEgListFncVO fncVO = ( RelationEgListFncVO ) fncVO_;
    RelationEgListForm form = ( RelationEgListForm ) form_;

    String flagAllData = ( fncVO.getFlagAllData() != null
                             && !"".equals( fncVO.getFlagAllData() )
                                                                      ? fncVO.getFlagAllData()
                                                                      : "" );
    //Atualiza o form com a flag de que retornou todos os dados da base
    form.setFlagAllData( flagAllData );
  }
}