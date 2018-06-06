/*
 * Created on Mar 8, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author leonardo.nakada
 *  
 */
public class BaseTplAggrProdPrvtEntityVO extends BaseEntityVO
{
  /**
   * Codigo do Agrupador de Produtos Private
   */
  private String m_prvtProdAggrCode;

  /**
   * Descricao do agrupador de Produtos Private
   */
  private String m_prvtProdAggrText;

  /**
   * Codigo do usuario que efetuou a ultima atualizacao no registro
   */
  private String m_lastUpdUserID;

  /**
   * Data e hora da ultima atualizaca efetuada pelo usuario
   */
  private Date m_lastUpdDate;

  /**
   * Status Registro - Identifica se o registro esta ativo, inativo ou aguar
   * dando aprovacao
   */
  private String m_recStatCode;

  
  private String m_ixCode;
  
 
  private DataSet m_AggrProdPrvtIxCodeDomain = null;


  
	public DataSet getAggrProdPrvtIxCodeDomain() {
	return m_AggrProdPrvtIxCodeDomain;
}

public void setAggrProdPrvtIxCodeDomain(DataSet m_AggrProdPrvtIxCodeDomain) {
	this.m_AggrProdPrvtIxCodeDomain = m_AggrProdPrvtIxCodeDomain;
}

	public String getIxCode() {
		return m_ixCode;
	}
	
	public void setIxCode(String m_ixCode) {
		this.m_ixCode = m_ixCode;
	}
  
  /**
   * @return Returns the lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ The lastUpdDate to set.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns the lastUpdUserID.
   */
  public String getLastUpdUserID()
  {
    return m_lastUpdUserID;
  }

  /**
   * @param lastUpdUserID_ The lastUpdUserID to set.
   */
  public void setLastUpdUserID( String lastUpdUserID_ )
  {
    m_lastUpdUserID = lastUpdUserID_;
  }

  /**
   * @return Returns the prvtProdAggrCode.
   */
  public String getPrvtProdAggrCode()
  {
    return m_prvtProdAggrCode;
  }

  /**
   * @param prvtProdAggrCode_ The prvtProdAggrCode to set.
   */
  public void setPrvtProdAggrCode( String prvtProdAggrCode_ )
  {
    m_prvtProdAggrCode = prvtProdAggrCode_;
  }

  /**
   * @return Returns the prvtProdAggrText.
   */
  public String getPrvtProdAggrText()
  {
    return m_prvtProdAggrText;
  }

  /**
   * @param prvtProdAggrText_ The prvtProdAggrText to set.
   */
  public void setPrvtProdAggrText( String prvtProdAggrText_ )
  {
    m_prvtProdAggrText = prvtProdAggrText_;
  }

  /**
   * @return Returns the recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ The recStatCode to set.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  
  
}