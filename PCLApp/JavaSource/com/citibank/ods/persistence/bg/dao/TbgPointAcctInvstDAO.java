package com.citibank.ods.persistence.bg.dao;

import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @see package com.citibank.ods.persistence.bg.dao;
 * @version 1.0
 * @author michele.monteiro,19/06/2007
 *  
 */

public interface TbgPointAcctInvstDAO extends BaseTbgPointAcctInvstDAO
{

  public TbgPointAcctInvstEntity find(
                                      TbgPointAcctInvstEntity tbgPointAcctInvstEntity_ );

  public boolean exists( TbgPointAcctInvstEntity tbgPointAcctInvstEntity_ );
}