package com.citibank.ods.modules.client.relationeg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplRelationEgEntity;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.BaseRelationEgListFncVO;
import com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable;

/**
 * @author leonardo.nakada
 *  
 */

public class BaseRelationEgListForm extends BaseForm implements
    RelationPrvtSearchable, RelationEgDetailable
{
  // Nomero do EG (Entitlement Group)
  private String m_egNbrSrc = "";

  // Numero do Relacionamento
  private String m_reltnNbrSrc = "";

  // Resultado da consulta
  private DataSet m_results;

  // clickedSearch
  private String clickedSearch;

  //Número do ER
  private String m_erNbrSrc = "";

  //Combo com os ER da base
  private DataSet m_erNbrDomain;

  /**
   * @return Returns the clickedSearch.
   */
  public String getClickedSearch()
  {
    return clickedSearch;
  }

  /**
   * @param clickedSearch_ The clickedSearch to set.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    clickedSearch = clickedSearch_;
  }

  /**
   * @return Returns the egNbrSrc.
   */
  public String getEgNbrSrc()
  {
    return m_egNbrSrc;
  }

  /**
   * @param egNbrSrc_ The egNbrSrc to set.
   */
  public void setEgNbrSrc( String egNbrSrc_ )
  {
    m_egNbrSrc = egNbrSrc_;
  }

  /**
   * @return Returns the reltnNbrSrc.
   */
  public String getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }

  /**
   * @param reltnNbrSrc_ The reltnNbrSrc to set.
   */
  public void setReltnNbrSrc( String reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
  }

  /**
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#getSelectedReltnNbr()
   */
  public String getSelectedReltnNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#setSelectedReltnNbr(java.lang.String)
   */
  public void setSelectedReltnNbr( String reltnNbr_ )
  {
    this.m_reltnNbrSrc = reltnNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationeg.form.RelationEgDetailable#setSelectedEgNbr(java.lang.String)
   */
  public void setSelectedEgNbr( String egNbr_ )
  {
    this.setEgNbrSrc( egNbr_ );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationeg.form.RelationEgDetailable#getSelectedEgNbr()
   */
  public String getSelectedEgNbr()
  {
    return m_egNbrSrc;
  }

  public DataSet getErNbrDomain()
  {
    return m_erNbrDomain;
  }

  public void setErNbrDomain( DataSet erNbrDomain_ )
  {
    m_erNbrDomain = erNbrDomain_;
  }

  public String getErNbrSrc()
  {
    return m_erNbrSrc;
  }

  public void setErNbrSrc( String erNbrSrc_ )
  {
    m_erNbrSrc = erNbrSrc_;
  }
  
  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationeg.form.RelationEgDetailable#setSelectedEgNbr(java.lang.String)
   */
  public void setSelectedErNbr( String erNbr_ )
  {
    this.setErNbrSrc( erNbr_ );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationeg.form.RelationEgDetailable#getSelectedEgNbr()
   */
  public String getSelectedErNbr()
  {
    return m_erNbrSrc;
  }


  /**
   * Realiza as validações dos campos
   */
  public ActionErrors validate( ActionMapping mapping_, HttpServletRequest req_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseRelationEgListFncVO.C_RELTN_NBR_DESCRIPTION,
                                     m_reltnNbrSrc,
                                     BaseTplRelationEgEntity.C_RELTN_NBR_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseRelationEgListFncVO.C_EG_NBR_DESCRIPTION,
                                    m_egNbrSrc,
                                    BaseTplRelationEgEntity.C_EG_NBR_SIZE,
                                    errors );

    return errors;
  }
}