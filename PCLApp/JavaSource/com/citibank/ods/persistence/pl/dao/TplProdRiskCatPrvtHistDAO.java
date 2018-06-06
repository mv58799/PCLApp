/*
 * Created on Mar 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtHistEntity;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 *
 * *** 20110321 ***
 * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
 * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
 * inserção e alteração serão removidas)
 */
public interface TplProdRiskCatPrvtHistDAO extends BaseTplProdRiskCatPrvtDAO
{
//  public DataSet list( BigInteger prodRiskCatCode_, String prodRiskCatText_,
//                      Date prodRiskCatRefDate_ );
//
//  public TplProdRiskCatPrvtHistEntity insert(
//                                             TplProdRiskCatPrvtHistEntity tplProdRiskCatPrvtHistEntity_ );
}