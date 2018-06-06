/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplProdQlfyPrvtEntityVO;


/**
 * @author fernando.salgado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdQlfyPrvtEntity extends BaseTplProdQlfyPrvtEntity
{

	 /**
	   * Construtor padrão - sem argumentos 
	   */
	  public TplProdQlfyPrvtEntity()
	  {
	    m_data = new TplProdQlfyPrvtEntityVO();
	  }
	  
	  /**
	   * Construtor - Carrega os atributos com os atributos do movimento
	   */
	  public TplProdQlfyPrvtEntity( TplProdQlfyPrvtMovEntity tplProdQlfyPrvtMovEntity_, 
	  								Date lastAuthDate_, String lastAuthUserId_, 
									String recStatCode_ )
	  {
	  	 m_data = new TplProdQlfyPrvtEntityVO();
	  	 
	  	 m_data.setProdQlfyCode(tplProdQlfyPrvtMovEntity_.getData().getProdQlfyCode());
	  	 m_data.setProdQlfyText(tplProdQlfyPrvtMovEntity_.getData().getProdQlfyText());
	  	 m_data.setLastUpdDate(tplProdQlfyPrvtMovEntity_.getData().getLastUpdDate());
	  	 m_data.setLastUpdUserId(tplProdQlfyPrvtMovEntity_.getData().getLastUpdUserId());
	  	 ((TplProdQlfyPrvtEntityVO)m_data).setLastAuthDate(lastAuthDate_);
	  	 ((TplProdQlfyPrvtEntityVO)m_data).setLastAuthUserId(lastAuthUserId_);
	  	 ((TplProdQlfyPrvtEntityVO)m_data).setRecStatCode(recStatCode_);
	  	
	  }
	  

	
}