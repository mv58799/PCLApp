/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl;

import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplPlayerEntityVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

/**
 * @author angelica.almeida
 *  
 */
public class BaseTplPlayerEntity extends BaseODSEntity
{
  /**
   * Constante do tamanho do campo CNPJ do player.
   */
  public static final int C_PLYR_CNPJ_NBR_SIZE = 18;

  /**
   * Constante do tamanho do campo Nome do player.
   */
  public static final int C_PLYR_NAME_SIZE = 60;

  /**
   * Constante do tamanho do campo Endereço do player.
   */
  public static final int C_PLYR_ADDR_TEXT_SIZE = 80;

  /**
   * Constante do tamanho do campo Restrições sobre aprovação.
   */
  public static final int C_PLYR_APPRV_RSTRN_TEXT_SIZE = 50;

  /**
   * Constante do tamanho do campo Serviçoes prestados pelo player.
   */
  public static final int C_PLYR_SUPL_SERV_TEXT_SIZE = 100;

  /**
   * Constante do tamanho do campo Observação.
   */
  public static final int C_PLYR_CMNT_TEXT_SIZE = 255;

  /**
   * EntityVO do player
   */
  protected BaseTplPlayerEntityVO m_data;

  /**
   * Lista de Roles
   */
  protected ArrayList plyrRoleNames;
  
  /**
   * Lista de Mnemonicos
   */
  protected List<ShortNameVO> plyrIssueNames;
  
  /**
   * Retorna o EntityVO do Player
   * @return
   */  
  public BaseTplPlayerEntityVO getData()
  {
    return m_data;
  }

  public BaseTplPlayerEntity()
  {
    plyrRoleNames = new ArrayList(); 
  }
 
  /**
   * @return Returns plyrRoleNames.
   */
  public ArrayList getPlyrRoleNames()
  {
    return plyrRoleNames;
  }
  
  public void setPlyrRoleNames( ArrayList plyrRoleNames_ )
  {
    plyrRoleNames = plyrRoleNames_;
  }

   public List<ShortNameVO> getPlyrIssueNames() {
	 return plyrIssueNames;
   }

   public void setPlyrIssueNames(List<ShortNameVO> plyrIssueNames) {
	 this.plyrIssueNames = plyrIssueNames;
   }
}