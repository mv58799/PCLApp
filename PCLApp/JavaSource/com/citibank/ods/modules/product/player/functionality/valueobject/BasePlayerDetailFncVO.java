/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality.valueobject;

import java.util.List;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;

/**
 * @author angelica.almeida
 *  
 */
public class BasePlayerDetailFncVO extends BaseODSFncVO
{
  
  public static final String C_PLYR_CNPJ_NBR_DESCRIPTION = "CNPJ";

  public static final String C_PLYR_NAME_DESCRIPTION = "Razão Social Player";
  
  public static final String C_PLYR_ROLE_TYPE_CODE_DESCRIPTION = "Papel do Player";

  public static final String C_PLYR_ADDR_TEXT_DESCRIPTION = "Endereço";

  public static final String C_PLYR_DUE_DLG_DATE_DESCRIPTION = "Data Due Diligence";

  public static final String C_PLYR_DUE_DLG_EXEC_IND_DESCRIPTION = "Indicador de Execução Due Diligence";

  public static final String C_PLYR_DUE_DLG_END_DATE_DESCRIPTION = "Data Conclusão Due Diligence";

  public static final String C_PLYR_DUE_DLG_RNW_DATE_DESCRIPTION = "Data Renovação Due Diligence";

  public static final String C_PLYR_INVST_CMTTE_APPRV_DATE_DESCRIPTION = "Data Aprovação Comite";

  public static final String C_PLYR_APPRV_RSTRN_TEXT_DESCRIPTION = "Restrição sobre Aprovação";

  public static final String C_PLYR_SUPL_SERV_TEXT_DESCRIPTION = "Serviços Prestados";

  public static final String C_PLYR_CMNT_TEXT_DESCRIPTION = "Observação";
  /**
   * Entity do Player
   */
  protected BaseTplPlayerEntity m_baseTplPlayerEntity;

  /**
   * Domínio de tipo de papel do player
   */
  private DataSet m_playerRoleTypeDomain;
  
  // Mnemonicos
  private String issueShortNameText;
  private Integer shortNameIdx;
  private List<ShortNameVO> issueShortNameList;
  private String loadIssue = "S";
  

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
	
	public Integer getShortNameIdx() {
		return shortNameIdx;
	}

	public void setShortNameIdx(Integer shortNameIdx) {
		this.shortNameIdx = shortNameIdx;
	}

/**
   * @return Returns baseTplPlayerEntity.
   */
  public BaseTplPlayerEntity getBaseTplPlayerEntity()
  {
    return m_baseTplPlayerEntity;
  }

  /**
   * @param baseTplPlayerEntity_ Field baseTplPlayerEntity to be setted.
   */
  public void setBaseTplPlayerEntity( BaseTplPlayerEntity baseTplPlayerEntity_ )
  {
    m_baseTplPlayerEntity = baseTplPlayerEntity_;
  }
  
  /**
   * @return Returns playerRoleTypeDomain.
   */
  public DataSet getPlayerRoleTypeDomain()
  {
    return m_playerRoleTypeDomain;
  }
  
  /**
   * @param playerRoleTypeDomain__ Field playerRoleTypeDomain to be setted.
   */
  public void setPlayerRoleTypeDomain( DataSet playerRoleTypeDomain_ )
  {
    m_playerRoleTypeDomain = playerRoleTypeDomain_;
  }
  
	public String getLoadIssue() {
		return loadIssue;
	}
	
	public void setLoadIssue(String loadIssue) {
		this.loadIssue = loadIssue;
	}

}