package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.TplProductHistEntity;

/**
 * Esta interface declara os m�todos abstratos(Insert, List, Update,
 * Delete,List, find e nstantiateFromResultSet para a tabelaTplProductHist,
 * separando o comportamento das opera��es independente do modo como. os dados
 * s�o acessados(Oracle, SQL, XML, etc).
 * @author leonardo.nakada
 * @date 04/04/2007
 */

public interface TplProductHistDAO extends BaseTplProductDAO
{

  /**
   * M�todos Abstratos
   *  
   */

  public TplProductHistEntity insert( TplProductHistEntity tplProductHistEntity_ )
                                                                  throws UnexpectedException;

  public DataSet list( String prodCode_, String prodFamlCode_,
                      String prodName_, BigInteger prodQlfyCode_,
                      BigInteger prodRiskCatCode_, BigInteger prodSubFamlCode_,
                      Date refDate_

  );

}