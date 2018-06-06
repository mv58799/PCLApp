/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;
import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplErEntity;
import com.citibank.ods.modules.client.er.form.BaseERDetailForm;
import com.citibank.ods.modules.client.er.functionality.valueobject.BaseERDetailFncVO;
import com.citibank.ods.modules.client.er.functionality.valueobject.ERDetailFncVO;
import com.citibank.ods.persistence.pl.dao.TplReasonEndRelationDAO;
import com.citibank.ods.persistence.pl.dao.TplReltnClassEquityDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class BaseERDetailFnc extends BaseFnc
{
	protected static final String C_ER_NBR = "Número de ER";

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm, com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) 

	{
	  BaseERDetailFncVO baseErDetailFncVO = ( BaseERDetailFncVO ) fncVO_;
	  BaseERDetailForm baseErDetailForm = ( BaseERDetailForm ) form_;	  
	  
	  String erNbr;    
	  String[] codeArray = null;
	  if(baseErDetailForm.getSelectedCode()!= null && !baseErDetailForm.getSelectedCode().equals("")){
		codeArray = baseErDetailForm.getSelectedCode().split(","); 	
	  }
	
	  if(codeArray != null){
		  erNbr = codeArray[0]; 	
	  } 
	  else if(baseErDetailForm.getErNbr() != null && baseErDetailForm.getErNbr().length() > 0){
		  erNbr = baseErDetailForm.getErNbr();
	  }
	  else{
		  erNbr = null;
	  }
	    
	  baseErDetailFncVO.getBaseTplErEntity().getData().setErNbr(erNbr);

	  String erReltnTrfInd = ( baseErDetailForm.getErReltnTrfInd() != null
							  && baseErDetailForm.getErReltnTrfInd().length() > 0
																				  ? baseErDetailForm.getErReltnTrfInd()
																				  : null );

	  BigInteger reltnEndReasCode = ( baseErDetailForm.getReltnEndReasCode() != null
							 && baseErDetailForm.getReltnEndReasCode().length() > 0
																			? new BigInteger(
																							  baseErDetailForm.getReltnEndReasCode() )
																			: null );
	  String reltnEndReasText = ( baseErDetailForm.getReltnEndReasText() != null
							  && !baseErDetailForm.getReltnEndReasText().equals(
																				   "" )
																					   ? baseErDetailForm.getReltnEndReasText()
																					   : "" );
	  BigInteger equityClassCode = ( baseErDetailForm.getEquityClassCode() != null
							 && baseErDetailForm.getEquityClassCode().length() > 0
																			  ? new BigInteger(
																								baseErDetailForm.getEquityClassCode() )
																			  : null );
	  
	  
	  baseErDetailFncVO.getBaseTplErEntity().getData().setErNbr(erNbr);
	  baseErDetailFncVO.getBaseTplErEntity().getData().setErReltnTrfInd(erReltnTrfInd);
	  baseErDetailFncVO.getBaseTplErEntity().getData().setReltnEndReasCode(reltnEndReasCode);
	  baseErDetailFncVO.getBaseTplErEntity().getData().setReltnEndReasText(reltnEndReasText);
	  baseErDetailFncVO.getBaseTplErEntity().getData().setEquityClassCode(equityClassCode);
	  															  
	}	
		
		
	

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm, com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) 
	{
	  BaseERDetailFncVO baseErDetailFncVO = ( BaseERDetailFncVO ) fncVO_;
	  BaseERDetailForm baseErDetailForm = ( BaseERDetailForm ) form_;

	  String erNbr = ( baseErDetailFncVO.getBaseTplErEntity().getData().getErNbr() != null
																						  ? baseErDetailFncVO.getBaseTplErEntity().getData().getErNbr().toString()
																						  : null );

	  String erReltnTrfInd = ( baseErDetailFncVO.getBaseTplErEntity().getData().getErReltnTrfInd() != null
						       																               ? baseErDetailFncVO.getBaseTplErEntity().getData().getErReltnTrfInd().toString()
																					                       : null);
																					
	  String reltnEndReasCode = ( baseErDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasCode() != null
	                                                                                                             ? baseErDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasCode().toString()
																				                                 : null );
																				        
	  String reltnEndReasText = ( baseErDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasText() != null
						                                                                                    	 ? baseErDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasText().toString()
																					                             : null );
																					
	  String equityClassCode = ( baseErDetailFncVO.getBaseTplErEntity().getData().getEquityClassCode() != null
																						                       ? baseErDetailFncVO.getBaseTplErEntity().getData().getEquityClassCode().toString()
																						                       : null );	  
	  
      //Formatar a data para o Formato DD/MM/AAAA - HH/MM
	  SimpleDateFormat formatDate = new SimpleDateFormat(Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
	  Date formDate = new Date();
	  formDate = baseErDetailFncVO.getBaseTplErEntity().getData().getLastUpdDate();
	  if ( formDate != null )
	  {
		//Setar Data no formato correto
		String date = formatDate.format( formDate );
		baseErDetailForm.setLastUpdDate( date );
	  }																							  
																									  
	  String lastUpdUserId = ( baseErDetailFncVO.getBaseTplErEntity().getData().getLastUpdUserId() != null
																										  ? baseErDetailFncVO.getBaseTplErEntity().getData().getLastUpdUserId().toString()
																										  : null );	  
                                                                                                    		               
	  String recStatCode = ( baseErDetailFncVO.getBaseTplErEntity().getData().getRecStatCode() != null
																											  ? baseErDetailFncVO.getBaseTplErEntity().getData().getRecStatCode().toString()
																											  : null );
																											
	  baseErDetailForm.setErNbr( erNbr );
	  baseErDetailForm.setErReltnTrfInd( erReltnTrfInd );
	  baseErDetailForm.setErReltnTrfIndDomain(baseErDetailFncVO.getErReltnTrfIndDomain());	  
	  baseErDetailForm.setReltnEndReasCode( reltnEndReasCode );
	  baseErDetailForm.setReltnEndReasCodeDomain(baseErDetailFncVO.getReltnEndReasCodeDomain());
	  baseErDetailForm.setReltnEndReasText( reltnEndReasText );
	  baseErDetailForm.setEquityClassCode( equityClassCode );
	  baseErDetailForm.setEquityClassCodeDomain(baseErDetailFncVO.getEquityClassCodeDomain());
	  baseErDetailForm.setLastUpdUserId( lastUpdUserId );	  
	  baseErDetailForm.setRecStatCode( recStatCode );		
		
	}
	/*
	 * Formata a data/hora
	 */
	private String formatDateTime( Date date_ )
	{
	  DateFormat dateFormat = new SimpleDateFormat(
													Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
	  return dateFormat.format( date_ );
	}


	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */
	public BaseFncVO createFncVO() {		
		return null;
	}
	
	private void loadErReltnTrfIndDomain(BaseERDetailFncVO baseERDetailFncVO )
    {
	  baseERDetailFncVO.setErReltnTrfIndDomain( ODSConstraintDecoder.decodeOriginCode() );
	}
		
	private void loadReltnEndReasCodeDomain(BaseERDetailFncVO baseERDetailFncVO )
	{
		TplReasonEndRelationDAO dao = ODSDAOFactory.getInstance().getTplReasonEndRelationDAO();
		DataSet resultDomain = dao.loadDomain();
		baseERDetailFncVO.setReltnEndReasCodeDomain( resultDomain );
	}
	
	private void loadEquityClassCodeDomain(BaseERDetailFncVO baseERDetailFncVO )
	{
		TplReltnClassEquityDAO dao = ODSDAOFactory.getInstance().getTplReltnClassEquityDAO();
		DataSet resultDomain = dao.loadDomain();
		baseERDetailFncVO.setEquityClassCodeDomain( resultDomain );
	}
	
	public void loadDomains(BaseERDetailFncVO baseERDetailFncVO ){
		this.loadErReltnTrfIndDomain(baseERDetailFncVO);
		this.loadReltnEndReasCodeDomain(baseERDetailFncVO);
		this.loadEquityClassCodeDomain(baseERDetailFncVO);

	}


}
