/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.general.centrapproval.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.general.centrapproval.form.BaseCentrApprovalListForm;
import com.citibank.ods.modules.general.centrapproval.functionality.valueobject.BaseCentrApprovalListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCentrApprovalMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author Ronaldo Machado (G&P Java Team) 
 */
public abstract class BaseCentrApprovaListFnc extends BaseFnc implements ODSListFnc {
	 
	public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ ){
		
		BaseCentrApprovalListForm baseCentrApprovalListForm = (BaseCentrApprovalListForm) form_;
		BaseCentrApprovalListFncVO baseCentrApprovalListFncVO = (BaseCentrApprovalListFncVO) fncVO_;
		
		if(baseCentrApprovalListForm.getModuleCodeSrc()!= null && !baseCentrApprovalListForm.getModuleCodeSrc().equals("")){
			baseCentrApprovalListFncVO.setModuleCodeSrc(new BigInteger(baseCentrApprovalListForm.getModuleCodeSrc()));
		}
		else{
			baseCentrApprovalListFncVO.setModuleCodeSrc(null);
		}
		
		if(baseCentrApprovalListForm.getLastUpdUserIdSrc()!= null && !baseCentrApprovalListForm.getLastUpdUserIdSrc().equals("")){
			baseCentrApprovalListFncVO.setLastUpdUserIdSrc(baseCentrApprovalListForm.getLastUpdUserIdSrc());
		}
		else{
			baseCentrApprovalListFncVO.setLastUpdUserIdSrc(null);
		}
		
		if(baseCentrApprovalListForm.getModuleProcessText()!= null && !baseCentrApprovalListForm.getModuleProcessText().equals("")){
			baseCentrApprovalListFncVO.setModuleProcessText(baseCentrApprovalListForm.getModuleProcessText()); 
		}
		else{
			baseCentrApprovalListFncVO.setModuleProcessText(null);
		}
		
	}
	
	public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ ){
		
		BaseCentrApprovalListForm baseCentrApprovalListForm = (BaseCentrApprovalListForm) form_;
		BaseCentrApprovalListFncVO baseCentrApprovalListFncVO = (BaseCentrApprovalListFncVO) fncVO_;
		
		/*if(baseCentrApprovalListFncVO.getModuleCodeSrc()!= null){
			baseCentrApprovalListForm.setModuleCodeSrc(baseCentrApprovalListFncVO.getModuleCodeSrc().toString());
		}
		else{
			baseCentrApprovalListForm.setModuleCodeSrc(null);
		}
		
		if(baseCentrApprovalListFncVO.getLastUpdUserIdSrc()!= null && !baseCentrApprovalListFncVO.getLastUpdUserIdSrc().equals("")){
			baseCentrApprovalListForm.setLastUpdUserIdSrc(baseCentrApprovalListFncVO.getLastUpdUserIdSrc());			
		}
		else{
			baseCentrApprovalListForm.setLastUpdUserIdSrc(null);
		}*/
		
		baseCentrApprovalListForm.setResults(baseCentrApprovalListFncVO.getResults());
		
		if(baseCentrApprovalListFncVO.getModuleProcessDomain() != null){
			baseCentrApprovalListForm.setModuleProcessDomain(baseCentrApprovalListFncVO.getModuleProcessDomain());
		}
		else{
			baseCentrApprovalListForm.setModuleProcessDomain(null);
		}
		
	}
	
	
	public void validateList( BaseFncVO fncVO_ )
	{
		// 
	}
	
	public void getDomainLists( BaseCentrApprovalListFncVO baseCentrApprovalListFncVO_, boolean hasAccess5001 )
	{	
		loadCentrApprovalProcessDomain(baseCentrApprovalListFncVO_, hasAccess5001);
	}
	
	private void loadCentrApprovalProcessDomain( BaseCentrApprovalListFncVO baseCentrApprovalListFncVO_, boolean hasAccess5001 )
	{
		//Obtém a instância da Factory
		ODSDAOFactory factory = ODSDAOFactory.getInstance();
		//Obtém a instância do DAO necessário
		TplCentrApprovalMovDAO dao = factory.getTplCentrApprovalMovDAO();
		//Realiza a consulta no DAO
		DataSet result = dao.loadDomain(hasAccess5001);
		//Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
		baseCentrApprovalListFncVO_.setModuleProcessDomain( result );
	}	  

}
