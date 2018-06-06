package com.citibank.ods.persistence.bg.dao.rdb.oracle;

import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.entity.bg.TbgBankEntity;
import com.citibank.ods.persistence.bg.dao.TbgBankDAO;


//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.bg.dao.rdb.oracle;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 18, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTbgBankDAO extends BaseOracleTbgBankDAO implements
    TbgBankDAO
{


  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.bg.dao.TbgBankDAO#exists(com.citibank.ods.entity.bg.TbgBankEntity)
   */
  public boolean exists( TbgBankEntity tbgBankEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tbgBankEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }
}