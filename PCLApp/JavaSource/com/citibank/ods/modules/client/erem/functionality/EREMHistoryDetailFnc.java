package com.citibank.ods.modules.client.erem.functionality;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.functionality.ODSHistoryDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.TplErEmHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEmHistEntityVO;
import com.citibank.ods.modules.client.erem.form.EREMHistoryDetailForm;
import com.citibank.ods.modules.client.erem.functionality.valueobject.BaseEREMDetailFncVO;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMHistoryDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.erem.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class EREMHistoryDetailFnc extends BaseEREMDetailFnc implements
    ODSHistoryDetailFnc
{

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    EREMHistoryDetailFncVO erEmHistoryDetailFncVO = ( EREMHistoryDetailFncVO ) fncVO_;
    EREMHistoryDetailForm erEmDetailForm = ( EREMHistoryDetailForm ) form_;
    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       Globals.FuncionalityFormatKeys.C_FORMAT_TIMESTAMP );

    Date erEmRefDate = null;

    try
    {
      erEmRefDate = ( erEmDetailForm.getEREMRefDate() != null
                      && erEmDetailForm.getEREMRefDate().length() > 0
                                                                     ? formatter.parse( erEmDetailForm.getEREMRefDate() )
                                                                     : null );
    }
    catch ( ParseException e )
    {
      throw new UnexpectedException( "Error parsing input field.", e );
    }
    ( ( TplErEmHistEntityVO ) erEmHistoryDetailFncVO.getBaseTplErEmEntity().getData() ).setErEmRefDate( erEmRefDate );
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    EREMHistoryDetailFncVO erEmHistoryDetailFncVO = ( EREMHistoryDetailFncVO ) fncVO_;
    EREMHistoryDetailForm erEmDetailForm = ( EREMHistoryDetailForm ) form_;
    
    erEmDetailForm.setHistoryResults( erEmHistoryDetailFncVO.getHistoryResults() );

    TplErEmHistEntityVO tplErEmHistEntityVO = ( TplErEmHistEntityVO ) erEmHistoryDetailFncVO.getBaseTplErEmEntity().getData();

    String lastAuthDate = ( tplErEmHistEntityVO.getLastAuthDate() != null
                                                                         ? formatDateTime( tplErEmHistEntityVO.getLastAuthDate() )
                                                                         : null );
    erEmDetailForm.setLastAuthDate( lastAuthDate );
  }

  /*
   * Formata a data/hora
   */
  private String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
    return dateFormat.format( date_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new EREMHistoryDetailFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSHistoryDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //carrega os dados de detalhe de histórico
    //load( ( BaseEREMDetailFncVO ) fncVO_ );

    //carrega o grid de detalhe de histórico
    loadHistoryGrid( ( BaseEREMDetailFncVO ) fncVO_ );
  }

  public void loadHistoryGrid( BaseEREMDetailFncVO baseEREMDetailFncVO )
  {
    TplErEmHistDAO tplEREMHistDAO = ( TplErEmHistDAO ) this.getDAO();
    TplErEmHistEntity tplEREMHistEntity = ( TplErEmHistEntity ) baseEREMDetailFncVO.getBaseTplErEmEntity();
    ( ( EREMHistoryDetailFncVO ) baseEREMDetailFncVO ).setHistoryResults( tplEREMHistDAO.listHistory( tplEREMHistEntity ) );
  }

  protected BaseTplErEmDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplErEmHistDAO();
  }

}