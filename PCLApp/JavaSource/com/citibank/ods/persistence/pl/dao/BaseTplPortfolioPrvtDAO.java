package com.citibank.ods.persistence.pl.dao;


import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplPortfolioPrvtEntity;

/**
*Esta interface declara os m�todos abstratos(Insert, List, Update, Delete,List, find e nstantiateFromResultSet 
*para a tabelaBaseTplPortfolioPrvt, separando o comportamento das opera��es independente do modo como.
*os dados s�o acessados(Oracle, SQL, XML, etc).
* @author l.braga
* @date 28/03/2007
*/

public interface BaseTplPortfolioPrvtDAO
{

/**
*M�todos Abstratos
*
*/


public BaseTplPortfolioPrvtEntity find( BaseTplPortfolioPrvtEntity baseBaseTplPortfolioPrvtEntity_ )throws UnexpectedException;
}
