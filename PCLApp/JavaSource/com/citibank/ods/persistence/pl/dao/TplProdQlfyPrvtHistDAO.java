/*
 * Created on 19/03/2007
 *
 */
package com.citibank.ods.persistence.pl.dao;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtHistEntity;

/**
 * @author angelica.almeida
 *
 */
public interface TplProdQlfyPrvtHistDAO extends BaseTplProdQlfyPrvtDAO 
{
	
	public DataSet list( BigInteger prodQlfyCode_, String prodQlfyText_,
            Date prodQlfyRefDate_ );

	public TplProdQlfyPrvtHistEntity insert(
                                   TplProdQlfyPrvtHistEntity tplProdQlfyPrvtHistEntity_ );

}
