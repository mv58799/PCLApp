/*
 * Created on 20/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProdAssetEntityVO;

  /**
   * @author rcoelho
   * @since 20/08/2008
   */
  public class BaseTplProdAssetEntity extends BaseODSEntity
  {
 
  /**
   * Constante do tamanho do código da Sub Classe.
   */
  public static final int C_PROD_SUBASSET_CODE_SIZE = 4;
  
  /**
   * Constante do tamanho da descrição da sub classe.
   */
  public static final int C_PROD_SUBASSET_TEXT_SIZE = 40;

	
  /**
   * Entity VO Qualificação de Produto.
   */
  protected BaseTplProdAssetEntityVO m_data;

  /**
   * @return Retorna o entity VO do agregador de produtos.
   */
  public BaseTplProdAssetEntityVO getData()
  {
	return m_data;
  }
}
