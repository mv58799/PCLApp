/*
 * Created on Apr 3, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.util.ArrayList;

/**
 * @author acacio.domingos
 *  
 */

public interface BaseTplProdPlayerRoleDAO
{
  public ArrayList selectByPlyr( String plyrCnpjNbr_);
}