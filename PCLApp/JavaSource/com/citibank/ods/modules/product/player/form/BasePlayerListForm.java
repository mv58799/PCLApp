/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.modules.product.player.functionality.valueobject.BasePlayerListFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

/**
 * @author atilio.l.araujo
 *  
 */
public class BasePlayerListForm extends BaseForm implements PlayerDetailable,
    PlayerSearchable
{
  //CNPJ do Player
  private String m_plyrCnpjNbrSrc;

  // Razão Social do Player
  private String m_plyrNameSrc;

  // Papel do Player
  private String m_plyrRoleTypeCodeSrc;

  //Resultado da Consulta
  private DataSet m_results;

  //Seleção da lista
  private String m_selectedPlyrCnpjNbr;

  //Seleção da lista - Implementação botão buscar;
  private String m_selectedPlyrCnpjNbrSrc;

  /*
   * Domínio de tipo de papel do player
   */
  private DataSet m_playerRoleTypeDomain;
  
  // Mnemonicos
  private String issueShortNameText;
  private String shortNameIdx;
  private List<ShortNameVO> issueShortNameList;
  
  public BasePlayerListForm(){
	  issueShortNameList = new ArrayList<ShortNameVO>();
  }

/**
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns the plyrCnpjNbrSrc.
   */
  public String getPlyrCnpjNbrSrc()
  {
    return m_plyrCnpjNbrSrc;
  }

  /**
   * @param plyrCnpjNbrSrc_ The plyrCnpjNbrSrc to set.
   */
  public void setPlyrCnpjNbrSrc( String plyrCnpjNbrSrc_ )
  {

    m_plyrCnpjNbrSrc = removeMask( plyrCnpjNbrSrc_ );
  }

  /**
   * @return Returns the plyrNameSrc.
   */
  public String getPlyrNameSrc()
  {
    return m_plyrNameSrc;
  }

  /**
   * @param plyrNameSrc_ The plyrNameSrc to set.
   */
  public void setPlyrNameSrc( String plyrNameSrc_ )
  {
    m_plyrNameSrc = plyrNameSrc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerDetailable#getSelectedPlyrCnpjNbr()
   */
  public String getSelectedPlyrCnpjNbr()
  {
    return m_selectedPlyrCnpjNbr;
  }

  public String getPlyrRoleTypeCodeSrc()
  {
    return m_plyrRoleTypeCodeSrc;
  }

  public void setPlyrRoleTypeCodeSrc( String plyrRoleTypeCodeSrc_ )
  {
    m_plyrRoleTypeCodeSrc = plyrRoleTypeCodeSrc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerDetailable#setSelectedPlyrCnpjNbr(java.lang.String)
   */
  public void setSelectedPlyrCnpjNbr( String setSelectedPlyrCnpjNbr_ )
  {
    m_selectedPlyrCnpjNbr = setSelectedPlyrCnpjNbr_;
  }

  /**
   * @return m_selectedPlyrCnpjNbrSrc. Retorna o número do cnpj.
   */
  public String getSelectedPlyrCnpjNbrSrc()
  {
    return m_selectedPlyrCnpjNbrSrc;
  }

  /**
   * @param selectedPlyrCnpjNbrSrc_.Seta o número do cnpj
   */
  public void setSelectedPlyrCnpjNbrSrc( String selectedPlyrCnpjNbrSrc_ )
  {
    m_selectedPlyrCnpjNbrSrc = selectedPlyrCnpjNbrSrc_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BasePlayerListFncVO.C_PLYR_CNPJ_NBR_DESCRIPTION,
                                    m_plyrCnpjNbrSrc,
                                    BaseTplPlayerEntity.C_PLYR_CNPJ_NBR_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerListFncVO.C_PLYR_NAME_DESCRIPTION,
                                    m_plyrNameSrc,
                                    BaseTplPlayerEntity.C_PLYR_NAME_SIZE,
                                    errors );

    return errors;
  }

  //Retorna domínio de tipo de papel do player
  public DataSet getPlayerRoleTypeDomain()
  {
    return m_playerRoleTypeDomain;
  }

  //Seta domínio de tipo de papel do player
  public void setPlayerRoleTypeDomain( DataSet playerRoleTypeDomain_ )
  {
    m_playerRoleTypeDomain = playerRoleTypeDomain_;
  }
  
   public List<ShortNameVO> getIssueShortNameList() {
		return issueShortNameList;
   }

	public void setIssueShortNameList(List<ShortNameVO> issueShortNameList) {
		this.issueShortNameList = issueShortNameList;
	}

	public String getIssueShortNameText() {
		return issueShortNameText;
	}

	public void setIssueShortNameText(String issueShortNameText) {
		this.issueShortNameText = issueShortNameText;
	}

	public String getShortNameIdx() {
		return shortNameIdx;
	}

	public void setShortNameIdx(String shortNameIdx) {
		this.shortNameIdx = shortNameIdx;
	}

}