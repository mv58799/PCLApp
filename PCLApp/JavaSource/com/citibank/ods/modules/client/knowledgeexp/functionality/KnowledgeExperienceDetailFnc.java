package com.citibank.ods.modules.client.knowledgeexp.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.knowledgeexp.functionality.valueobject.KnowledgeExperienceDetailFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

public class KnowledgeExperienceDetailFnc extends BaseKnowledgeExperienceDetailFnc{

	public void delete(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void insert(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void loadForConsult(BaseFncVO fncVO_) {
		KnowledgeExperienceDetailFncVO knowledgeExperienceDetailFncVO = ( KnowledgeExperienceDetailFncVO ) fncVO_;
		
		if ( !knowledgeExperienceDetailFncVO.hasErrors() ) {
		  //Obtém a instância da Factory
		  ODSDAOFactory factory = ODSDAOFactory.getInstance();

		  //Obtém a instância do DAO necessário
		  TplCustomerPrvtDAO customerPrvtDAO = factory.getTplCustomerPrvtDAO();

		  DataSet result = customerPrvtDAO.findQuestionary( knowledgeExperienceDetailFncVO.getClientNumber() );					 

		  knowledgeExperienceDetailFncVO.setResults( result );
		  
		  if ( result.size() > 0 ){
			  knowledgeExperienceDetailFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
		  }
		  else{
			  knowledgeExperienceDetailFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
		  }
		}
	}

	public void loadForDelete(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void loadForInsert(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void loadForUpdate(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void update(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void validateDelete(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void validateInsert(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

	public void validateUpdate(BaseFncVO fncVO_) {
		// TODO Auto-generated method stub
	}

}