package com.citibank.ods.modules.client.relationprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplRelationPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.relationprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseRelationPrvtDetailFncVO extends BaseODSFncVO
{

  protected BaseTplRelationPrvtEntity m_tplRelationPrvtEntity;

  private DataSet m_reltnPrmtCodeDomain;

  private String selectedReltnNbr = "";
  
  // Nome do cliente no Primeiro relacionamento.
  private String m_custFullNameText = "";
  
  // Nome do cliente no Segundo relacionamento.
  private String m_custFullNameText2 = "";
  
  // Nome do cliente no Terceiro relacionamento.
  private String m_custFullNameText3 = "";
  
  // Nome do cliente no Quarto relacionamento.
  private String m_custFullNameText4 = "";
  
  // Nome do cliente no Quinto relacionamento.
  private String m_custFullNameText5 = "";
  
  private String reltnAddrCellCustNbr_custFullNameText  = "";
  
  private String reltnAddrEmailCustNbr_custFullNameText = "";
  
  private String reltnCustAddrNbr_custFullNameText = "";
  
  // Descrição da carteira.
  private String m_portfNameText = "";
  
  // Officer da carteira.
  private BigInteger m_portfOffcrNbr = null;
  
  // Nome do Officer
  private String m_offcrNameText = "";
  
  private DataSet m_reltnSpcfClassServPackIndDomain ;

  /**
   * @return Returns reltnSpcfClassServPackIndDomain.
   */
  public DataSet getReltnSpcfClassServPackIndDomain()
  {
    return m_reltnSpcfClassServPackIndDomain;
  }
  /**
   * @param reltnSpcfClassServPackIndDomain_ Field reltnSpcfClassServPackIndDomain to be setted.
   */
  public void setReltnSpcfClassServPackIndDomain(
                                                 DataSet reltnSpcfClassServPackIndDomain_ )
  {
    m_reltnSpcfClassServPackIndDomain = reltnSpcfClassServPackIndDomain_;
  }
  /**
   * @return Returns offcrNameText.
   */
  public String getOffcrNameText()
  {
    return m_offcrNameText;
  }
  /**
   * @param offcrNameText_ Field offcrNameText to be setted.
   */
  public void setOffcrNameText( String offcrNameText_ )
  {
    m_offcrNameText = offcrNameText_;
  }
  /**
   * @return Returns portfNameText.
   */
  public String getPortfNameText()
  {
    return m_portfNameText;
  }
  /**
   * @param portfNameText_ Field portfNameText to be setted.
   */
  public void setPortfNameText( String portfNameText_ )
  {
    m_portfNameText = portfNameText_;
  }

  /**
   * @return Returns portfOffcrNbr.
   */
  public BigInteger getPortfOffcrNbr()
  {
    return m_portfOffcrNbr;
  }
  /**
   * @param portfOffcrNbr_ Field portfOffcrNbr to be setted.
   */
  public void setPortfOffcrNbr( BigInteger portfOffcrNbr_ )
  {
    m_portfOffcrNbr = portfOffcrNbr_;
  }
  /**
   * @return Returns reltnAddrCellCustNbr_custFullNameText.
   */
  public String getReltnAddrCellCustNbrCustFullNameText()
  {
    return reltnAddrCellCustNbr_custFullNameText;
  }
  /**
   * @param reltnAddrCellCustNbr_custFullNameText_ Field reltnAddrCellCustNbr_custFullNameText to be setted.
   */
  public void setReltnAddrCellCustNbrCustFullNameText(
                                                       String reltnAddrCellCustNbr_custFullNameText_ )
  {
    reltnAddrCellCustNbr_custFullNameText = reltnAddrCellCustNbr_custFullNameText_;
  }
  /**
   * @return Returns reltnAddrEmailCustNbr_custFullNameText.
   */
  public String getReltnAddrEmailCustNbrCustFullNameText()
  {
    return reltnAddrEmailCustNbr_custFullNameText;
  }
  /**
   * @param reltnAddrEmailCustNbr_custFullNameText_ Field reltnAddrEmailCustNbr_custFullNameText to be setted.
   */
  public void setReltnAddrEmailCustNbrCustFullNameText(
                                                        String reltnAddrEmailCustNbr_custFullNameText_ )
  {
    reltnAddrEmailCustNbr_custFullNameText = reltnAddrEmailCustNbr_custFullNameText_;
  }
  /**
   * @return Returns reltnCustAddrNbr_custFullNameText.
   */
  public String getReltnCustAddrNbrCustFullNameText()
  {
    return reltnCustAddrNbr_custFullNameText;
  }
  /**
   * @param reltnCustAddrNbr_custFullNameText_ Field reltnCustAddrNbr_custFullNameText to be setted.
   */
  public void setReltnCustAddrNbrCustFullNameText(
                                                   String reltnCustAddrNbr_custFullNameText_ )
  {
    reltnCustAddrNbr_custFullNameText = reltnCustAddrNbr_custFullNameText_;
  }
  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }
  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }

  /**
   * @return Returns custFullNameText5.
   */
  public String getCustFullNameText5()
  {
    return m_custFullNameText5;
  }
  /**
   * @param custFullNameText5_ Field custFullNameText5 to be setted.
   */
  public void setCustFullNameText5( String custFullNameText5_ )
  {
    m_custFullNameText5 = custFullNameText5_;
  }
  /**
   * @return Returns custFullNameText2.
   */
  public String getCustFullNameText2()
  {
    return m_custFullNameText2;
  }
  /**
   * @param custFullNameText2_ Field custFullNameText2 to be setted.
   */
  public void setCustFullNameText2( String custFullNameText2_ )
  {
    m_custFullNameText2 = custFullNameText2_;
  }
  /**
   * @return Returns custFullNameText3.
   */
  public String getCustFullNameText3()
  {
    return m_custFullNameText3;
  }
  /**
   * @param custFullNameText3_ Field custFullNameText3 to be setted.
   */
  public void setCustFullNameText3( String custFullNameText3_ )
  {
    m_custFullNameText3 = custFullNameText3_;
  }
  /**
   * @return Returns custFullNameText4.
   */
  public String getCustFullNameText4()
  {
    return m_custFullNameText4;
  }
  /**
   * @param custFullNameText4_ Field custFullNameText4 to be setted.
   */
  public void setCustFullNameText4( String custFullNameText4_ )
  {
    m_custFullNameText4 = custFullNameText4_;
  }
  /**
   * @return Returns selectedReltnNbr.
   */
  public String getSelectedReltnNbr()
  {
    return selectedReltnNbr;
  }

  /**
   * @param selectedReltnNbr_ Field selectedReltnNbr to be setted.
   */
  public void setSelectedReltnNbr( String selectedReltnNbr_ )
  {
    selectedReltnNbr = selectedReltnNbr_;
  }

  /**
   * @return Returns reltnPrmtCodeDomain.
   */
  public DataSet getReltnPrmtCodeDomain()
  {
    return m_reltnPrmtCodeDomain;
  }

  /**
   * @param reltnPrmtCodeDomain_ Field reltnPrmtCodeDomain to be setted.
   */
  public void setReltnPrmtCodeDomain( DataSet reltnPrmtCodeDomain_ )
  {
    m_reltnPrmtCodeDomain = reltnPrmtCodeDomain_;
  }

  /**
   * @param tplRelationPrvtEntity_ Field tplRelationPrvtEntity to be setted.
   */
  public void setTplRelationPrvtEntity(
                                       BaseTplRelationPrvtEntity tplRelationPrvtEntity_ )
  {
    m_tplRelationPrvtEntity = tplRelationPrvtEntity_;
  }

  /**
   * @return Returns tplRelationPrvtEntity.
   */
  public BaseTplRelationPrvtEntity getTplRelationPrvtEntity()
  {
    return m_tplRelationPrvtEntity;
  }

}