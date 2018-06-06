package com.citibank.ods.modules.client.knowledgeexp.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.BusinessConstants;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.KnowledgeExperienceListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

public class KnowledgeExperienceListFnc extends BaseKnowledgeExperienceListFnc {

	@Override
	public BaseFncVO createFncVO() {
		return new KnowledgeExperienceListFncVO();
	}

	public void list(BaseFncVO fncVO_){
		KnowledgeExperienceListFncVO knowledgeExperienceListFncVO = ( KnowledgeExperienceListFncVO ) fncVO_;
		
		if ( !knowledgeExperienceListFncVO.hasErrors() ) {
		  //Obt�m a inst�ncia da Factory
		  ODSDAOFactory factory = ODSDAOFactory.getInstance();

		  //Obt�m a inst�ncia do DAO necess�rio
		  TplCustomerPrvtDAO customerPrvtDAO = factory.getTplCustomerPrvtDAO();

		  DataSet result = customerPrvtDAO.list(	knowledgeExperienceListFncVO.getClientNumber(), 
				  									knowledgeExperienceListFncVO.getClientNameText() );					 

		  knowledgeExperienceListFncVO.setResults( result );
		  
		  if ( result.size() > 0 ){
			  if (result.size() >= BusinessConstants.KNOW_EXP_EXCEEDED_LIMIT ){
				  knowledgeExperienceListFncVO.addMessage( BaseODSFncVO.C_EXCEEDED_LIMIT );
			  }else{
				  knowledgeExperienceListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
			  }
		  }else{
			  knowledgeExperienceListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
		  }
		  
		}
	}
	
}
