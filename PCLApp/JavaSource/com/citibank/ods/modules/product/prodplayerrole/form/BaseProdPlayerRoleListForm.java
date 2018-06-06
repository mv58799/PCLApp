package com.citibank.ods.modules.product.prodplayerrole.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.modules.product.player.form.PlayerSearchable;
import com.citibank.ods.modules.product.player.functionality.valueobject.BasePlayerDetailFncVO;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductDetailFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.product.playerproduct.form;
 * @version 1.0
 * @author atilio.l.araujo,Apr 4, 2007
 *  
 */

public class BaseProdPlayerRoleListForm extends BaseForm implements
    ProdPlayerRoleDetailable, PlayerSearchable
{
  //CNPJ do Player.
  private String m_plyrCnpjNbrSrc = "";

  //Nome do Player.
  private String m_plyrNameSrc = "";

  // Tipo do Papel do Player (Administrador, Gestor, Custodiante, Controlador,
  // Auditor)
  private String m_plyrRoleTypeCode = "";

  // Codigo do Produto.
  private String m_prodCodeSrc = "";

  // Codigo do sistema origem do cadastro do Produto.
  private String m_sysCodeSrc = "";

  // Codigo da segmentacao do sistema origem do cadastro do Produto.
  private String m_sysSegCodeSrc = "";

  // Nome do Produto
  private String m_prodNameSrc = "";

  //Resultado da Consulta
  private DataSet results;

  private String m_clickedSearch;

  /*
   * Domínio de tipo de papel do player
   */
  private DataSet m_prodPlayerRoleDomain;

  /*
   * Nome do papel do Player.
   */
  private String m_plyrRoleNameSrc;

  /*
   * Último usuário alteração
   */
  private String m_lastUpdUserIdSrc;

  //Seleção da lista
  private String m_selectedPlyrCnpjNbr;

  /**
   * @return Returns prodCodeSrc.
   */
  public String getProdCodeSrc()
  {
    return m_prodCodeSrc;
  }

  /**
   * @param prodCodeSrc_ Field prodCodeSrc to be setted.
   */
  public void setProdCodeSrc( String prodCodeSrc_ )
  {
    m_prodCodeSrc = prodCodeSrc_;
  }

  /**
   * @return Returns sysCodeSrc.
   */
  public String getSysCodeSrc()
  {
    return m_sysCodeSrc;
  }

  /**
   * @param sysCodeSrc_ Field sysCodeSrc to be setted.
   */
  public void setSysCodeSrc( String sysCodeSrc_ )
  {
    m_sysCodeSrc = sysCodeSrc_;
  }

  /**
   * @return Returns sysSegCodeSrc.
   */
  public String getSysSegCodeSrc()
  {
    return m_sysSegCodeSrc;
  }

  /**
   * @param sysSegCodeSrc_ Field sysSegCodeSrc to be setted.
   */
  public void setSysSegCodeSrc( String sysSegCodeSrc_ )
  {
    m_sysSegCodeSrc = sysSegCodeSrc_;
  }

  /**
   * @return Returns prodPlayerRoleDomain.
   */
  public DataSet getProdPlayerRoleDomain()
  {
    return m_prodPlayerRoleDomain;
  }

  /**
   * @param prodPlayerRoleDomain_ Field prodPlayerRoleDomain to be setted.
   */
  public void setProdPlayerRoleDomain( DataSet prodPlayerRoleDomain_ )
  {
    m_prodPlayerRoleDomain = prodPlayerRoleDomain_;
  }

  /**
   * @return Returns plyrRoleNameSrc.
   */
  public String getPlyrRoleNameSrc()
  {
    return m_plyrRoleNameSrc;
  }

  /**
   * @param plyrRoleNameSrc_ Field plyrRoleNameSrc to be setted.
   */
  public void setPlyrRoleNameSrc( String plyrRoleNameSrc_ )
  {
    m_plyrRoleNameSrc = plyrRoleNameSrc_;
  }

  /**
   * @return Returns plyrCnpjNbrSrc.
   */
  public String getPlyrCnpjNbrSrc()
  {
    return m_plyrCnpjNbrSrc;
  }

  /**
   * @param plyrCnpjNbrSrc_ Field plyrCnpjNbrSrc to be setted.
   */
  public void setPlyrCnpjNbrSrc( String plyrCnpjNbrSrc_ )
  {
    m_plyrCnpjNbrSrc = removeMask( plyrCnpjNbrSrc_ );
  }

  public String getPlyrNameSrc()
  {
    return m_plyrNameSrc;
  }

  public void setPlyrNameSrc( String plyrNameSrc_ )
  {
    m_plyrNameSrc = plyrNameSrc_;
  }

  /**
   * @return Returns plyrRoleTypeCode.
   */
  public String getPlyrRoleTypeCode()
  {
    return m_plyrRoleTypeCode;
  }

  /**
   * @param plyrRoleTypeCode_ Field plyrRoleTypeCode to be setted.
   */
  public void setPlyrRoleTypeCode( String plyrRoleTypeCode_ )
  {
    m_plyrRoleTypeCode = plyrRoleTypeCode_;
  }

  /**
   * @return Returns prodNameSrc.
   */
  public String getProdNameSrc()
  {
    return m_prodNameSrc;
  }

  /**
   * @param prodNameSrc_ Field prodNameSrc to be setted.
   */
  public void setProdNameSrc( String prodNameSrc_ )
  {
    m_prodNameSrc = prodNameSrc_;
  }

  /**
   * @return Returns results.
   */
  public DataSet getResults()
  {
    return results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
    results = results_;
  }

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  public String getSelectedPlyrCnpjNbr()
  {
    return m_selectedPlyrCnpjNbr;
  }

  public void setSelectedPlyrCnpjNbr( String selectedPlyrCnpjNbr_ )
  {
    m_selectedPlyrCnpjNbr = selectedPlyrCnpjNbr_;

  }

  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerSearchable#getSelectedPlyrCnpjNbrSrc()
   */
  public String getSelectedPlyrCnpjNbrSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerSearchable#setSelectedPlyrCnpjNbrSrc(java.lang.String)
   */
  public void setSelectedPlyrCnpjNbrSrc( String plyrCnpjNbr_ )
  {
    setPlyrCnpjNbrSrc( plyrCnpjNbr_ );
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_CODE_DESCRIPTION,
                                    m_prodCodeSrc,
                                    BaseTplProductEntity.C_PROD_CODE_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_SYS_CODE_DESCRIPTION,
                                    m_sysCodeSrc,
                                    BaseTplProductEntity.C_SYS_CODE_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_CNPJ_NBR_DESCRIPTION,
                                    m_plyrCnpjNbrSrc,
                                    BaseTplPlayerEntity.C_PLYR_CNPJ_NBR_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_NAME_DESCRIPTION,
                                    m_prodNameSrc,
                                    BaseTplProductEntity.C_PROD_NAME_SIZE,
                                    errors );

    return errors;
  }

}