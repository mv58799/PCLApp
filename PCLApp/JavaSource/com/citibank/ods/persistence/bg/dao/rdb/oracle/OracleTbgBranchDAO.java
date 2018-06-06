package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.entity.bg.TbgBranchEntity;
import com.citibank.ods.persistence.bg.dao.TbgBranchDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.persistence.bg.dao.rdb.oracle; 
 *@version 1.0
 *@author gerson.a.rodrigues,Apr 18, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class OracleTbgBranchDAO extends BaseOracleTbgBranchDAO implements
TbgBranchDAO
{
  
  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.bg.dao.TbgBankDAO#exists(com.citibank.ods.entity.bg.TbgBankEntity)
   */
  public boolean exists( TbgBranchEntity tbgBranchEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tbgBranchEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

}
