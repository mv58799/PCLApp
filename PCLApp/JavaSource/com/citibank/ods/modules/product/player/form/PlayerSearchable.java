package com.citibank.ods.modules.product.player.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.product.player.form;
 * @version 1.0
 * @author angelica.almeida,27/05/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface PlayerSearchable
{

  /**
   * @return SelectedPlyrCnpjNbr. Retorna o valor selecionado no grid.
   */
  public String getSelectedPlyrCnpjNbrSrc();

  /**
   * @param plyrCnpjNbr. Seta o cnpj do player.
   */
  public void setSelectedPlyrCnpjNbrSrc( String plyrCnpjNbr_ );

  public String getPlyrCnpjNbrSrc();

  public void setPlyrCnpjNbrSrc( String plyrCnpjNbrSrc_ );

}