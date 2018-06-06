package com.citibank.ods.modules.client.officercmpl.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.modules.client.officercmpl.form.BaseOfficerCmplDetailForm;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.BaseOfficerCmplDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerTypeDAO;
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
 * </PRE>
 * 
 * Classe base do Fnc de detalhe
 */

public abstract class BaseOfficerCmplDetailFnc extends BaseFnc
{

  protected static final String C_OFFCR_NBR_OBRIGATORIO = "";

  protected static final String C_OFFCR_INTL_NBR_OBRIGATORIO = "";

  protected static final String C_OFFCR_TYPE_CODE_OBRIGATORIO = "";

  /**
   * Atualiza o FncVO com os dados vindos do Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseOfficerCmplDetailForm form = ( BaseOfficerCmplDetailForm ) form_;
    BaseOfficerCmplDetailFncVO fncVO = ( BaseOfficerCmplDetailFncVO ) fncVO_;

    BigInteger offcrIntlNbr = ( form.getOffcrIntlNbr() != null
                                && form.getOffcrIntlNbr().length() > 0
                                                                      ? new BigInteger(
                                                                                        form.getOffcrIntlNbr() )
                                                                      : null );
    BigInteger offcrNbr = ( form.getOffcrNbr() != null
                            && form.getOffcrNbr().length() > 0
                                                              ? new BigInteger(
                                                                                form.getOffcrNbr() )
                                                              : null );
	String[] codeArray = null;
	if(form.getSelectedCode()!= null && !form.getSelectedCode().equals("")){
	  codeArray = form.getSelectedCode().split(","); 	
	}
	
	if(codeArray!= null){
		offcrNbr = new BigInteger(codeArray[0]);                       
	}
                                                              
    String offcrTypeCode = ( form.getOffcrTypeCode() != null
                             && form.getOffcrTypeCode().length() > 0
                                                                    ? form.getOffcrTypeCode()
                                                                    : null );

    String offcrNameText = ( form.getOffcrNameText() != null
                             && form.getOffcrNameText().length() > 0
                                                                    ? form.getOffcrNameText()
                                                                    : null );

    fncVO.getTplOfficerCmplEntity().getData().setOffcrIntlNbr( offcrIntlNbr );
    fncVO.getTplOfficerCmplEntity().getData().setOffcrNbr( offcrNbr );
    fncVO.getTplOfficerCmplEntity().getData().setOffcrTypeCode( offcrTypeCode );
    fncVO.setOffcrNameText( offcrNameText );

    fncVO.setClickedSearch( "" );
  }

  /**
   * Atualiza os dados do Form com os dados vindos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseOfficerCmplDetailForm form = ( BaseOfficerCmplDetailForm ) form_;
    BaseOfficerCmplDetailFncVO fncVO = ( BaseOfficerCmplDetailFncVO ) fncVO_;

    SimpleDateFormat sdf = new SimpleDateFormat(
                                                 Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );

    Date updDate = fncVO.getTplOfficerCmplEntity().getData().getLastUpdDate();
    String lastUpdDate = ( updDate != null ? sdf.format( updDate ) : null );
    String lastUpdUserId = ( fncVO.getTplOfficerCmplEntity().getData().getLastUpdUserId() != null
                                                                                                 ? fncVO.getTplOfficerCmplEntity().getData().getLastUpdUserId().toString()
                                                                                                 : null );
    String offcrIntlNbr = ( fncVO.getTplOfficerCmplEntity().getData().getOffcrIntlNbr() != null
                                                                                               ? fncVO.getTplOfficerCmplEntity().getData().getOffcrIntlNbr().toString()
                                                                                               : null );
    String offcrNbr = ( fncVO.getTplOfficerCmplEntity().getData().getOffcrNbr() != null
                                                                                       ? fncVO.getTplOfficerCmplEntity().getData().getOffcrNbr().toString()
                                                                                       : null );
    String offcrTypeCode = ( fncVO.getTplOfficerCmplEntity().getData().getOffcrTypeCode() != null
                                                                                                 ? fncVO.getTplOfficerCmplEntity().getData().getOffcrTypeCode().toString()
                                                                                                 : null );

    String offcrNameText = ( fncVO.getOffcrNameText() != null
                                                             ? fncVO.getOffcrNameText()
                                                             : null );

    form.setClickedSearch( fncVO.getClickedSearch() );

    form.setLastUpdDate( lastUpdDate );
    form.setLastUpdUserId( lastUpdUserId );
    form.setOffcrIntlNbr( offcrIntlNbr );
    form.setOffcrNbr( offcrNbr );
    form.setOffcrTypeCode( offcrTypeCode );
    form.setOffcrNameText( offcrNameText );

    form.setOffcrTypeCodeDomain( fncVO.getOffcrTypeCodeDomain() );

  }

  /**
   * Realiza o carregamento dos dados do FncVO passado por parâmetro
   * 
   * @param officerCmplDetailFncVO_
   */
  public void loadOfficerCmpl(
                              BaseOfficerCmplDetailFncVO officerCmplDetailFncVO_ )
  {
    BaseTplOfficerCmplEntity officerCmplEntity;

    BaseTplOfficerCmplDAO officerCmplDAO = this.getDAO();
    officerCmplEntity = officerCmplDAO.find( officerCmplDetailFncVO_.getTplOfficerCmplEntity() );
    officerCmplDetailFncVO_.setTplOfficerCmplEntity( officerCmplEntity );
  }

  public void loadOfficerNameText(
                                  BaseOfficerCmplDetailFncVO officerCmplDetailFncVO_ )
  {
    if ( officerCmplDetailFncVO_ != null
         && officerCmplDetailFncVO_.getTplOfficerCmplEntity().getData().getOffcrNbr() != null )
    {
      if ( officerCmplDetailFncVO_.getTplOfficerCmplEntity().getData().getOffcrNbr() != null
           && officerCmplDetailFncVO_.getTplOfficerCmplEntity().getData().getOffcrNbr().intValue() != 0 )
      {
        TbgOfficerEntity officerEntity = new TbgOfficerEntity();
        officerEntity.getData().setOffcrNbr(
                                             officerCmplDetailFncVO_.getTplOfficerCmplEntity().getData().getOffcrNbr() );

        //Obtém a instância da Factory
        ODSDAOFactory factory = ODSDAOFactory.getInstance();
        //Obtém a instância do DAO necessário
        TbgOfficerDAO tbgOfficerDAO = factory.getTbgOfficerDAO();

        //Realiza a consulta no DAO
        officerEntity = ( TbgOfficerEntity ) tbgOfficerDAO.find( officerEntity );

        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        officerCmplDetailFncVO_.setOffcrNameText( officerEntity.getData().getOffcrNameText() );

      }

    }
  }

  protected abstract BaseTplOfficerCmplDAO getDAO();

  /**
   * Carrega os valores existentes de tipos de officers para exibição em combo
   * boxes
   * 
   * @param officerCmplListFncVO_ - Objeto contendo o estado atual da aplicação
   */
  public void loadOfficerTypeDomain(
                                    BaseOfficerCmplDetailFncVO officerCmplListFncVO_ )
  {

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplOfficerTypeDAO officerTypeDAO = factory.getTplOfficerTypeDAO();
    //Realiza a consulta no DAO
    DataSet result = officerTypeDAO.loadOfficerType();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    officerCmplListFncVO_.setOffcrTypeCodeDomain( result );

  }

}