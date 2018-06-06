package com.citibank.ods.modules.client.officercmpl.functionality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.functionality.ODSHistoryDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplHistoryEntityVO;
import com.citibank.ods.modules.client.officercmpl.form.OfficerCmplHistoryDetailForm;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplHistoryDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.officercmpl.functionality;
 * @version 1.0
 * @author fabio.luppi.gil,Mar 5, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * Classe Fnc de detalhe do Histórico dos dados complementares de Officer
 * 
 * </PRE>
 */

public class OfficerCmplHistoryDetailFnc extends BaseOfficerCmplDetailFnc
    implements ODSHistoryDetailFnc
{
  /**
   * Recupera uma instancia do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new OfficerCmplHistoryDetailFncVO();
  }

  /**
   * Atualiza os dados do Form com os dados vindos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    OfficerCmplHistoryDetailForm form = ( OfficerCmplHistoryDetailForm ) form_;
    OfficerCmplHistoryDetailFncVO fncVO = ( OfficerCmplHistoryDetailFncVO ) fncVO_;
    TplOfficerCmplHistoryEntityVO historyEntityVO = ( TplOfficerCmplHistoryEntityVO ) fncVO.getTplOfficerCmplEntity().getData();

    String recStatCode = "";
    if ( historyEntityVO.getRecStatCode() != null
         && !historyEntityVO.getRecStatCode().equals( "" ) )
    {
      recStatCode = ODSConstraintDecoder.decodeRecStatus( historyEntityVO.getRecStatCode() );
    }
    form.setRecStatCode( recStatCode );

    String lastAuthDate = "";
    if ( historyEntityVO.getLastAuthDate() != null
         && !historyEntityVO.getLastAuthDate().equals( "" ) )
    {
      SimpleDateFormat sdf = new SimpleDateFormat(
                                                   Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
      lastAuthDate = sdf.format( historyEntityVO.getLastAuthDate() );
    }
    form.setLastAuthDate( lastAuthDate );

    form.setLastAuthUserId( historyEntityVO.getLastAuthUserId() );

    String refDate = "";
    if ( historyEntityVO.getOffcrRefDate() != null
         && !historyEntityVO.getOffcrRefDate().equals( "" ) )
    {
      SimpleDateFormat sdf = new SimpleDateFormat(
                                                   Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      refDate = sdf.format( historyEntityVO.getOffcrRefDate() );
    }
    form.setOffcrRefDate( refDate );
  }

  /**
   * Atualiza o FncVO com os dados vindos do Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    OfficerCmplHistoryDetailForm form = ( OfficerCmplHistoryDetailForm ) form_;
    OfficerCmplHistoryDetailFncVO fncVO = ( OfficerCmplHistoryDetailFncVO ) fncVO_;
    Date offcrRefDate = null;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       Globals.FuncionalityFormatKeys.C_FORMAT_TIMESTAMP );
    try
    {
      offcrRefDate = ( form.getOffcrRefDate() != null
                       && form.getOffcrRefDate().length() > 0
                                                             ? formatter.parse( form.getOffcrRefDate() )
                                                             : null );
    }
    catch ( ParseException e )
    {
      throw new UnexpectedException( "Error parsing input field.", e );
    }

    ( ( TplOfficerCmplHistoryEntityVO ) fncVO.getTplOfficerCmplEntity().getData() ).setOffcrRefDate( offcrRefDate );

  }

  /**
   * Recupera a instância de um DAO
   */
  protected BaseTplOfficerCmplDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplOfficerCmplHistDAO tplOfficerCmplHistDAO = odsDAOFactory.getTplOfficerCmplHistDAO();
    return tplOfficerCmplHistDAO;
  }

  /**
   * "[Method description]"
   * 
   * @param
   * @return
   * @exception
   * @see
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    super.loadOfficerTypeDomain( ( OfficerCmplHistoryDetailFncVO ) fncVO_ );
    super.loadOfficerCmpl( ( OfficerCmplHistoryDetailFncVO ) fncVO_ );
    super.loadOfficerNameText( ( OfficerCmplHistoryDetailFncVO ) fncVO_ );
  }
}