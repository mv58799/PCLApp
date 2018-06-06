package com.citibank.ods.modules.client.erem.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.erem.form.EREMHistoryListForm;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMHistoryListFncVO;
import com.citibank.ods.persistence.pl.dao.TplErEmHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.erem.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 *  
 */

public class EREMHistoryListFnc extends BaseEREMListFnc implements ODSListFnc
{

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    EREMHistoryListFncVO erEmHistoryListFncVO = ( EREMHistoryListFncVO ) fncVO_;
    EREMHistoryListForm erEmListForm = ( EREMHistoryListForm ) form_;
    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    Date erEmRefDate = null;

    try
    {
      erEmRefDate = ( erEmListForm.getEREMRefDateSrc() != null
                      && erEmListForm.getEREMRefDateSrc().length() > 0
                                                                      ? formatter.parse( erEmListForm.getEREMRefDateSrc() )
                                                                      : null );
    }
    catch ( Exception e )
    {

    }
    
    
    
    erEmHistoryListFncVO.setErEmRefDate( erEmRefDate );
    erEmHistoryListFncVO.setErNbrHistSrc( erEmListForm.getErNbrHistSrc() );
    erEmHistoryListFncVO.setEmNbrHistSrc( erEmListForm.getEmNbrHistSrc() );
    erEmHistoryListFncVO.setFromCurrent( erEmListForm.isFromCurrent() );
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    EREMHistoryListFncVO erEmHistoryListFncVO = ( EREMHistoryListFncVO ) fncVO_;
    EREMHistoryListForm erEmListForm = ( EREMHistoryListForm ) form_;

    erEmListForm.setErNbrHistSrc( erEmHistoryListFncVO.getErNbrHistSrc() );
    erEmListForm.setEmNbrHistSrc( erEmHistoryListFncVO.getEmNbrHistSrc() );
    erEmListForm.setFromCurrent( erEmHistoryListFncVO.isFromCurrent() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new EREMHistoryListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    EREMHistoryListFncVO erEmListFncVO = ( EREMHistoryListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplErEmHistDAO tplErEmHistDAO = factory.getTplErEmHistDAO();

    DataSet results = tplErEmHistDAO.list(
                                           erEmListFncVO.getEmNbrHistSrc(),
                                           erEmListFncVO.getErNbrHistSrc(),
                                           ( ( EREMHistoryListFncVO ) erEmListFncVO ).getErEmRefDate(),
                                           erEmListFncVO.getCustNbrSrc(),
                                           erEmListFncVO.getCustFullNameTextSrc(),
                                           erEmListFncVO.getReltnNbrSrc(),
                                           erEmListFncVO.getAcctNbr() );

    erEmListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      erEmListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      erEmListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    EREMHistoryListFncVO erEmListFncVO = ( EREMHistoryListFncVO ) fncVO_;

    if ( erEmListFncVO.isFromSearch() )
    {
      loadCustText( erEmListFncVO );
      loadEmNbr( erEmListFncVO );
      erEmListFncVO.setEmNbrHistSrc( erEmListFncVO.getEmNbrSrc() );
      erEmListFncVO.setFromSearch( false );
    }
    else
    {
      if ( !erEmListFncVO.isFromCurrent() )
      {
        erEmListFncVO.setErNbrHistSrc( null );
        erEmListFncVO.setEmNbrHistSrc( null );
      }

      erEmListFncVO.setSelectedEmNbr( null );
      erEmListFncVO.setSelectedErNbr( null );
      erEmListFncVO.setCustNbrSrc( null );
      erEmListFncVO.setCustFullNameTextSrc( null );
      erEmListFncVO.setResults( null );
      ( ( EREMHistoryListFncVO ) erEmListFncVO ).setErEmRefDate( null );
      erEmListFncVO.setFromCurrent( false );
      erEmListFncVO.setEmNbrSrc( null );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }
}

