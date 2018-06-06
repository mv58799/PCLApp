package com.citibank.ods.entity.pl;

import java.util.ArrayList;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductEntityVO;

/**
 * @author angelica.almeida
 *  
 */

public class BaseTplProdPlayerRoleEntity extends BaseODSEntity
{
  /**
   * Constante do tamanho do campo Codigo do Produto.
   */
  public static final int C_PROD_CODE_SIZE = 4;

  /**
   * Constante do tamanho do campo Codigo do sistema origem do cadastro do
   * Produto.
   */
  public static final int C_SYS_CODE_SIZE = 3;

  /**
   * Constante do tamanho do campo Codigo da segmentacao do sistema origem do
   * cadastro do Produto.
   */
  public static final int C_SYS_SEG_CODE_SIZE = 2;

  /**
   * Constante do tamanho do campo CNPJ do Player.
   */
  public static final int C_PLYR_CNPJ_NBR_SIZE = 18;

  /**
   * Constante do tamanho do campo Tipo do Papel do Player (Administrador,
   * Gestor, Custodiante, Controlador , Auditor)
   */
  public static final int C_PLYR_ROLE_TYPE_CODE_SIZE = 3;

  /**
   * Constante do tamanho do campo Codigo do Usuario que efetuou a ultima
   * atualizacao registro.
   */
  public static final int C_LAST_UPD_USER_ID = 20;

  /**
   * EntityVO da associação player x produto
   */
  protected BaseTplProdPlayerRoleEntityVO m_data;
  
  /**
   * EntityVO do produto
   */
  protected BaseTplProductEntityVO m_dataProduct;

  /**
   * Lista de Roles
   */
  protected ArrayList prodPlyrRoleNames;

  /**
   * Retorna o EntityVO do Player
   * @return
   */
  public BaseTplProdPlayerRoleEntityVO getData()
  {
    return m_data;
  }

  public BaseTplProdPlayerRoleEntity()
  {
    prodPlyrRoleNames = new ArrayList();
  }

  public BaseTplProductEntityVO getDataProduct()
  {
    return m_dataProduct;
  }
  /**
   * @return Returns plyrRoleNames.
   */
  public ArrayList getProdPlyrRoleNames()
  {
    return prodPlyrRoleNames;
  }

  public boolean equals(
                        BaseTplProdPlayerRoleEntity baseTplProdPlayerRoleEntity_ )
  {
    BaseTplProdPlayerRoleEntityVO baseTplProdPlayerRoleEntityVO = baseTplProdPlayerRoleEntity_.getData();

    if ( m_data.getPlyrCnpjNbr().equals(
                                         baseTplProdPlayerRoleEntityVO.getPlyrCnpjNbr() )
         && m_data.getPlyrRoleTypeCode().equals(
                                                 baseTplProdPlayerRoleEntityVO.getPlyrRoleTypeCode() )
         && m_data.getProdCode().equals(
                                         baseTplProdPlayerRoleEntityVO.getProdCode() )
         && m_data.getSysCode().equals(
                                        baseTplProdPlayerRoleEntityVO.getSysCode() )
         && m_data.getSysSegCode().equals(
                                           baseTplProdPlayerRoleEntityVO.getSysSegCode() ) )
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}