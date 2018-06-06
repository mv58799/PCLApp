/*
 * Created on Apr 15, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.relationeg.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.relationeg.form.BaseRelationEgListForm;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.BaseRelationEgListFncVO;
import com.citibank.ods.persistence.pl.dao.TplErEmDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseRelationEgListFnc extends BaseFnc
{

  /**
   * Atualiza as informações do FncVO com o Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseRelationEgListFncVO baseRelationEgListFncVO = ( BaseRelationEgListFncVO ) fncVO_;
    BaseRelationEgListForm baseRelationEgListForm = ( BaseRelationEgListForm ) form_;

    baseRelationEgListFncVO.setEgNbr( baseRelationEgListForm.getEgNbrSrc() );

    if ( baseRelationEgListForm.getReltnNbrSrc() != null
         && !baseRelationEgListForm.getReltnNbrSrc().equals( "" ) )
    {
      baseRelationEgListFncVO.setReltnNbr( new BigInteger(
                                                           baseRelationEgListForm.getReltnNbrSrc() ) );
    }
    else
    {
      baseRelationEgListFncVO.setReltnNbr( null );
    }

    baseRelationEgListFncVO.setClickedSearch( "" );

    //Número do ER
    baseRelationEgListFncVO.setErNbrSrc( baseRelationEgListForm.getErNbrSrc() != null
                                         && !baseRelationEgListForm.getErNbrSrc().equals(
                                                                                          "" )
                                                                                              ? baseRelationEgListForm.getErNbrSrc()
                                                                                              : "" );
  }

  /**
   * Atualiza as informações do Form com o FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseRelationEgListFncVO baseRelationEgListFncVO = ( BaseRelationEgListFncVO ) fncVO_;
    BaseRelationEgListForm baseRelationEgListForm = ( BaseRelationEgListForm ) form_;

    baseRelationEgListForm.setResults( baseRelationEgListFncVO.getResults() );

    baseRelationEgListForm.setClickedSearch( baseRelationEgListFncVO.getClickedSearch() );
    //Carrega o combo com o número do ER
    baseRelationEgListForm.setErNbrDomain( baseRelationEgListFncVO.getErNbrDomain() );
    //Número do ER
    baseRelationEgListForm.setErNbrSrc( baseRelationEgListFncVO.getErNbrSrc() != null
                                        && !baseRelationEgListFncVO.getErNbrSrc().equals(
                                                                                          "" )
                                                                                              ? baseRelationEgListFncVO.getErNbrSrc()
                                                                                              : "" );
  }

  /**
   * Carregamento inicial da tela de consulta em lista
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseRelationEgListFncVO baseRelationEgListFncVO = ( BaseRelationEgListFncVO ) fncVO_;
    baseRelationEgListFncVO.setResults( null );
    loadErNbr( baseRelationEgListFncVO );

  }

  public void loadErNbr( BaseFncVO fncVO_ )
  {
    BaseRelationEgListFncVO baseRelationEgListFncVO = ( BaseRelationEgListFncVO ) fncVO_;
    //Cria uma instacia do DAO de ERxEM
    TplErEmDAO tplErEmDAO = ODSDAOFactory.getInstance().getTplErEmDAO();
    baseRelationEgListFncVO.setErNbrDomain( tplErEmDAO.loadErNbr() );
  }
}