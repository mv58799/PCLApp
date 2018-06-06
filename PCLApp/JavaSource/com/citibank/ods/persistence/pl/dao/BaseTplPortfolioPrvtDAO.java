package com.citibank.ods.persistence.pl.dao;


import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplPortfolioPrvtEntity;

/**
*Esta interface declara os métodos abstratos(Insert, List, Update, Delete,List, find e nstantiateFromResultSet 
*para a tabelaBaseTplPortfolioPrvt, separando o comportamento das operações independente do modo como.
*os dados são acessados(Oracle, SQL, XML, etc).
* @author l.braga
* @date 28/03/2007
*/

public interface BaseTplPortfolioPrvtDAO
{

/**
*Métodos Abstratos
*
*/


public BaseTplPortfolioPrvtEntity find( BaseTplPortfolioPrvtEntity baseBaseTplPortfolioPrvtEntity_ )throws UnexpectedException;
}
