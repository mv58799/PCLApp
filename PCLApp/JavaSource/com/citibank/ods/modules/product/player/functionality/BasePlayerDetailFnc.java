/*
 * Created on Mar 28, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;
import com.citibank.ods.modules.product.player.form.BasePlayerDetailForm;
import com.citibank.ods.modules.product.player.functionality.valueobject.BasePlayerDetailFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
/**
 * @author atilio.l.araujo
 *  
 */
public abstract class BasePlayerDetailFnc extends BaseFnc
{

  protected static final String C_DISPLAY_PLYR_CNPJ_NBR = "CNPJ do Player";

  protected static final String C_DISPLAY_PLYR_NAME = "Nome do Player";

  protected static final String C_DISPLAY_PLYR_ROLE = "Papel do Player";
  
  protected static final String C_DISPLAY_ISSUE = "Mnemônico";

  /**
   * Atualiza o FncVO com as informacoes da Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseTplPlayerRoleEntity baseTplPlayerRoleEntity;

    BasePlayerDetailFncVO basePlayerDetailFncVO = ( BasePlayerDetailFncVO ) fncVO_;
    BasePlayerDetailForm basePlayerDetailForm = ( BasePlayerDetailForm ) form_;
    
	String[] codeArray = null;
	if(basePlayerDetailForm.getSelectedCode()!= null && !basePlayerDetailForm.getSelectedCode().equals("")){
	  codeArray = basePlayerDetailForm.getSelectedCode().split(","); 	
	}

	if(codeArray!= null){
	  basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrCnpjNbr(codeArray[0]);
	}
	else if(basePlayerDetailForm.getPlyrCnpjNbr()!= null && !basePlayerDetailForm.getPlyrCnpjNbr().equals("")){
	  basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrCnpjNbr(basePlayerDetailForm.getPlyrCnpjNbr() );
    }    
    else{
	  basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrCnpjNbr(null);
    }
    
    basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrName(
                                                                          basePlayerDetailForm.getPlyrName() );
    basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrAddrText(
                                                                              basePlayerDetailForm.getPlyrAddrText() );
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    if ( basePlayerDetailForm.getPlyrDueDlgDate() != null
         && !basePlayerDetailForm.getPlyrDueDlgDate().equals( "" ) )
    {
      try
      {
        basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDueDlgDate(
                                                                                    dateFormat.parse( basePlayerDetailForm.getPlyrDueDlgDate() ) );
      }
      catch ( ParseException e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDueDlgDate(
                                                                                  null );
    }
    basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDueDlgExecInd(
                                                                                   basePlayerDetailForm.getPlyrDueDlgExecInd() );

    if ( basePlayerDetailForm.getPlyrDueDlgEndDate() != null
         && !basePlayerDetailForm.getPlyrDueDlgEndDate().equals( "" ) )
    {
      try
      {
        basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDueDlgEndDate(
                                                                                       dateFormat.parse( basePlayerDetailForm.getPlyrDueDlgEndDate() ) );
      }
      catch ( ParseException e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDueDlgEndDate(
                                                                                     null );
    }

    if ( basePlayerDetailForm.getPlyrInvstCmtteApprvDate() != null
         && !basePlayerDetailForm.getPlyrInvstCmtteApprvDate().equals( "" ) )
    {
      try
      {
        basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrInvstCmtteApprvDate(
                                                                                             dateFormat.parse( basePlayerDetailForm.getPlyrInvstCmtteApprvDate() ) );
      }
      catch ( ParseException e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrInvstCmtteApprvDate(
                                                                                           null );
    }

    if ( basePlayerDetailForm.getPlyrDueDlgRnwDate() != null
         && !basePlayerDetailForm.getPlyrDueDlgRnwDate().equals( "" ) )
    {
      try
      {
        basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDueDlgRnwDate(
                                                                                       dateFormat.parse( basePlayerDetailForm.getPlyrDueDlgRnwDate() ) );
      }
      catch ( ParseException e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDueDlgRnwDate(
                                                                                     null );
    }

    basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrApprvRstrnText(
                                                                                    basePlayerDetailForm.getPlyrApprvRstrnText() );
    basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrSuplServText(
                                                                                  basePlayerDetailForm.getPlyrSuplServText() );
    basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrCmntText(
                                                                              basePlayerDetailForm.getPlyrCmntText() );

    BigInteger plyrDvCode = ( basePlayerDetailForm.getPlyrDvCode() != null
                              && basePlayerDetailForm.getPlyrDvCode().length() > 0
                                                                                  ? new BigInteger(
                                                                                                    basePlayerDetailForm.getPlyrDvCode() )
                                                                                  : null );

    basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().setPlyrDvCode(
                                                                            plyrDvCode );

    if ( basePlayerDetailForm.getPlyrRoleNames() != null
         && basePlayerDetailForm.getPlyrRoleNames().length > 1 )
    {
      String[] formPlayerRoleNames = basePlayerDetailForm.getPlyrRoleNames();
      ArrayList playerRoleNames = new ArrayList();

      for ( int i = 0; i < formPlayerRoleNames.length - 1; i++ )
      {
        baseTplPlayerRoleEntity = new BaseTplPlayerRoleEntity();
        baseTplPlayerRoleEntity.getData().setPlyrRoleTypeCode(
                                                               formPlayerRoleNames[ i ] );
        baseTplPlayerRoleEntity.getData().setPlyrCnpjNbr(
                                                          basePlayerDetailForm.getPlyrCnpjNbr() );

        playerRoleNames.add( baseTplPlayerRoleEntity );

      }
      basePlayerDetailFncVO.getBaseTplPlayerEntity().setPlyrRoleNames(
                                                                       playerRoleNames );
    }
    else
    {
      basePlayerDetailFncVO.getBaseTplPlayerEntity().setPlyrRoleNames( null );
    }
    
    //Mnemonicos
    basePlayerDetailFncVO.setIssueShortNameText(basePlayerDetailForm.getIssueShortNameText());
    basePlayerDetailFncVO.setIssueShortNameList(basePlayerDetailForm.getIssueShortNameList());
    if(basePlayerDetailForm.getShortNameIdx() != null){
    	basePlayerDetailFncVO.setShortNameIdx(Integer.parseInt(basePlayerDetailForm.getShortNameIdx()));
    }
  }

  /**
   * Atualiza a Form com as informacoes do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseTplPlayerRoleEntity playerRoleEntity;
    BasePlayerDetailFncVO basePlayerDetailFncVO = ( BasePlayerDetailFncVO ) fncVO_;
    BasePlayerDetailForm basePlayerDetailForm = ( BasePlayerDetailForm ) form_;

    basePlayerDetailForm.setPlyrCnpjNbr( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr() );
    basePlayerDetailForm.setPlyrName( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrName() );
    basePlayerDetailForm.setPlyrAddrText( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrAddrText() );
    basePlayerDetailForm.setPlyrApprvRstrnText( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrApprvRstrnText() );
    basePlayerDetailForm.setPlyrDueDlgExecInd( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgExecInd() );
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    if ( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgDate() != null
         && !basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgDate().equals(
                                                                                                  "" ) )
    {
      try
      {
        basePlayerDetailForm.setPlyrDueDlgDate( dateFormat.format( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgDate() ) );
      }
      catch ( Exception e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailForm.setPlyrDueDlgDate( null );
    }

    if ( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgEndDate() != null
         && !basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgEndDate().equals(
                                                                                                     "" ) )
    {
      try
      {
        basePlayerDetailForm.setPlyrDueDlgEndDate( dateFormat.format( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgEndDate() ) );
      }
      catch ( Exception e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailForm.setPlyrDueDlgEndDate( null );
    }

    if ( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrInvstCmtteApprvDate() != null
         && !basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrInvstCmtteApprvDate().equals(
                                                                                                           "" ) )
    {
      try
      {
        basePlayerDetailForm.setPlyrInvstCmtteApprvDate( dateFormat.format( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrInvstCmtteApprvDate() ) );
      }
      catch ( Exception e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailForm.setPlyrInvstCmtteApprvDate( null );
    }

    if ( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgRnwDate() != null
         && !basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgRnwDate().equals(
                                                                                                     "" ) )
    {
      try
      {
        basePlayerDetailForm.setPlyrDueDlgRnwDate( dateFormat.format( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDueDlgRnwDate() ) );
      }
      catch ( Exception e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailForm.setPlyrDueDlgRnwDate( null );
    }

    basePlayerDetailForm.setPlyrApprvRstrnText( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrApprvRstrnText() );
    basePlayerDetailForm.setPlyrSuplServText( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrSuplServText() );
    basePlayerDetailForm.setPlyrCmntText( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrCmntText() );

    String plyrDvCode = ( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDvCode() != null
                                                                                                          ? basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrDvCode().toString()
                                                                                                          : null );

    basePlayerDetailForm.setPlyrDvCode( plyrDvCode );

    basePlayerDetailForm.setLastUpdUserId( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getLastUpdUserId() );

    if ( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getLastUpdDate() != null
         && !basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getLastUpdDate().equals(
                                                                                               "" ) )
    {
      try
      {
        basePlayerDetailForm.setLastUpdDate( formatDateTime( basePlayerDetailFncVO.getBaseTplPlayerEntity().getData().getLastUpdDate() ) );
      }
      catch ( Exception e_ )
      {
        throw new UnexpectedException("Erro de manipulação de String: " + e_ );
      }
    }
    else
    {
      basePlayerDetailForm.setLastUpdDate( null );
    }

    if ( basePlayerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames() != null
         && !basePlayerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames().isEmpty() )
    {
      ArrayList playerRoleNames = basePlayerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames();
      String[] formPlyrRoleNames = new String[ playerRoleNames.size() ];

      int i = 0;
      for ( Iterator ite = playerRoleNames.iterator(); ite.hasNext(); i++ )
      {
        playerRoleEntity = ( BaseTplPlayerRoleEntity ) ite.next();
        formPlyrRoleNames[ i ] = playerRoleEntity.getData().getPlyrRoleTypeCode();
      }

      basePlayerDetailForm.setPlyrRoleNames( formPlyrRoleNames );
    }
    else
    {
      basePlayerDetailForm.setPlyrRoleNames( null );
    }
    //mnemonicos
    basePlayerDetailForm.setIssueShortNameText(null);
    basePlayerDetailForm.setIssueShortNameList(basePlayerDetailFncVO.getIssueShortNameList());
    basePlayerDetailForm.setLoadIssue(basePlayerDetailFncVO.getLoadIssue());

  }

  public void load( BasePlayerDetailFncVO basePlayerDetailFncVO_ )
  {
    // Recupera as informações do Player
    BaseTplPlayerDAO tplPlayerDAO = this.getDAO();
    BaseTplPlayerEntity tplPlayerEntity = tplPlayerDAO.find( basePlayerDetailFncVO_.getBaseTplPlayerEntity() );

    // Recupera os papéis do player selecionado
    BaseTplPlayerRoleDAO tplPlayerRoleDAO = this.getPlayerRoleDAO();
    ArrayList playerRoleNames = tplPlayerRoleDAO.selectByPk( tplPlayerEntity.getData().getPlyrCnpjNbr() );
    tplPlayerEntity.setPlyrRoleNames( playerRoleNames );

    basePlayerDetailFncVO_.setBaseTplPlayerEntity( tplPlayerEntity );
    basePlayerDetailFncVO_.setLoadIssue("S");
  }

  protected abstract BaseTplPlayerDAO getDAO();

  protected abstract BaseTplPlayerRoleDAO getPlayerRoleDAO();

  /**
   * Realiza o carregamento dos dados de tipo de papel do player
   * @author angelica.almeida
   */
  protected void loadRoleTypeDomain( BasePlayerDetailFncVO playerDetailFncVO_ )
  {
    DataSet resultDomain = ODSConstraintDecoder.decodeRoleType();
    playerDetailFncVO_.setPlayerRoleTypeDomain( resultDomain );
  }

  /**
   * Carrega todos os domínios utilizados pela transação
   */
  protected void loadDomains( BasePlayerDetailFncVO playerDetailFncVO_ )
  {
    this.loadRoleTypeDomain( playerDetailFncVO_ );
  }
  
  /**
   * Converte uma data para o formato de apresentação.
   * 
   * @param date_ - A data a ser convertida.
   * @return String - A data no formato de apresentação.
   */
  protected String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    return dateFormat.format( date_ );
  }
}