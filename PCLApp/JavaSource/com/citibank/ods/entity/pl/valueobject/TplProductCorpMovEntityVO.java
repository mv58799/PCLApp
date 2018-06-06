package com.citibank.ods.entity.pl.valueobject;


public class TplProductCorpMovEntityVO  extends BaseTplProductCorpEntityVO{

	  /**
	   * Codigo da Operacao realizada no registro: inclusao, alteracao, exclusao
	   */
	  private String m_opernCode;
	  
	  public TplProductCorpMovEntityVO(){}  
	  
	  /**
	   * @return Returns the opernCode.
	   */
	  public String getOpernCode()
	  {
	    return m_opernCode;
	  }

	  /**
	   * @param opernCode_ The opernCode to set.
	   */
	  public void setOpernCode( String opernCode_ )
	  {
	    m_opernCode = opernCode_;
	  }	  
}
