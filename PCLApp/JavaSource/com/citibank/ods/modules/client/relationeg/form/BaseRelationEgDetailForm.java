package com.citibank.ods.modules.client.relationeg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplRelationEgEntity;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.BaseRelationEgDetailFncVO;
import com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable;

/**
 * @author leonardo.nakada
 *  
 */

public class BaseRelationEgDetailForm extends BaseForm implements
    RelationPrvtSearchable, RelationEgDetailable
{
  public static final int COL_POS_RETLN_NBR = 0;

  public static final int COL_POS_CLIENT_OWNER = 1;

  public static final int COL_POS_EG_NBR = 2;

  public static final int COL_POS_CUST_1 = 3;

  public static final int COL_POS_CUST_2 = 4;

  public static final int COL_POS_OPERN_CODE = 5;

  // Nomero do EG (Entitlement Group)
  private String m_egNbr = "";

  // Nomero do EG (Entitlement Group)
  private String m_selectedEgNbr = "";

  // Nomero do EG (Entitlement Group)
  private String m_selectedEgNbrInGrid = "";

  //Nomero do EG (Entitlement Group)
  private String m_selectedReltnNbrInGrid = "";

  // Data e hora da ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro
  private String m_lastUpdUserId = "";

  // Numero do Relacionamento
  private String m_reltnNbr = "";

  // Numero Sequencial do Relacionamento.
  private String m_reltnSeqNbr = "";

  // Grid de associações
  private String[][] egRelationsGrid = null;

  // Grid de visualização
  private DataSet m_results = null;

  private String m_clickedSearch;

  //Itens selecionados no grid
  private String[] m_selectedItemsInGrid;

  //Itens selecionados no grid para exclusão
  private String[] m_deletedItems;

  // Combo de ER
  private DataSet m_erNbrDomain = null;

  // Número do ER
  private String m_erNbr = "";

  /**
   * @return Returns m_egNbr.
   */
  public String getEgNbr()
  {
    return m_egNbr;
  }

  /**
   * @param egNbr_ Field m_egNbr to be setted.
   */
  public void setEgNbr( String egNbr_ )
  {
    m_egNbr = egNbr_;
  }

  /**
   * @return Returns m_lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field m_lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns m_lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field m_lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns m_reltnSeqNbr.
   */
  public String getReltnSeqNbr()
  {
    return m_reltnSeqNbr;
  }

  /**
   * @param reltnSeqNbr_ Field m_reltnSeqNbr to be setted.
   */
  public void setReltnSeqNbr( String reltnSeqNbr_ )
  {
    m_reltnSeqNbr = reltnSeqNbr_;
  }

  /**
   * @return Returns the egRelationsGrid.
   */
  public String[][] getEgRelationsGrid()
  {
    return egRelationsGrid;
  }

  /**
   * @param egRelationsGrid_ The egRelationsGrid to set.
   */
  public void setEgRelationsGrid( String[][] egRelationsGrid_ )
  {
    egRelationsGrid = egRelationsGrid_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#getSelectedReltnNbr()
   */
  public String getSelectedReltnNbr()
  {
    return null;
  }

  /**
   * @return Returns the selectedEgNbr.
   */
  public String getSelectedEgNbr()
  {
    return null;
  }

  /**
   * @param selectedEgNbr_ The selectedEgNbr to set.
   */
  public void setSelectedEgNbr( String selectedEgNbr_ )
  {
    m_egNbr = selectedEgNbr_;
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

  /**
   * @return Returns the selectedEgNbrInGrid.
   */
  public String getSelectedEgNbrInGrid()
  {
    return m_selectedEgNbrInGrid;
  }

  /**
   * @param selectedEgNbrInGrid_ The selectedEgNbrInGrid to set.
   */
  public void setSelectedEgNbrInGrid( String selectedEgNbrInGrid_ )
  {
    m_selectedEgNbrInGrid = selectedEgNbrInGrid_;
  }

  /**
   * @return Returns the selectedReltnNbrInGrid.
   */
  public String getSelectedReltnNbrInGrid()
  {
    return m_selectedReltnNbrInGrid;
  }

  /**
   * @param selectedReltnNbrInGrid_ The selectedReltnNbrInGrid to set.
   */
  public void setSelectedReltnNbrInGrid( String selectedReltnNbrInGrid_ )
  {
    m_selectedReltnNbrInGrid = selectedReltnNbrInGrid_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#getReltnNbrSrc()
   */
  public String getReltnNbrSrc()
  {
    // 
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#setReltnNbrSrc(java.lang.String)
   */
  public void setReltnNbrSrc( String reltnNbrSrc_ )
  {

  }

  /**
   * @return Returns the reltnNbr.
   */
  public String getReltnNbr()
  {
    return m_reltnNbr;
  }

  /**
   * @param reltnNbr_ The reltnNbr to set.
   */
  public void setReltnNbr( String reltnNbr_ )
  {
    m_reltnNbr = reltnNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#setSelectedReltnNbr(java.lang.String)
   */
  public void setSelectedReltnNbr( String selReltnNbr_ )
  {
    m_reltnNbr = selReltnNbr_;
  }

  /**
   * @return Returns the clickedSearch.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_ The clickedSearch to set.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return Returns selectedItemsInGrid.
   */
  public String[] getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }

  /**
   * @param selectedItemsInGrid_ Field selectedItemsInGrid to be setted.
   */
  public void setSelectedItemsInGrid( String[] selectedItemsInGrid_ )
  {
    m_selectedItemsInGrid = selectedItemsInGrid_;
  }

  /**
   * @return Returns deletedItems.
   */
  public String[] getDeletedItems()
  {
    return m_deletedItems;
  }

  /**
   * @param deletedItems_ Field deletedItems to be setted.
   */
  public void setDeletedItems( String[] deletedItems_ )
  {
    m_deletedItems = deletedItems_;
  }

  /**
   * @return m_erNbrDomain. Retorna o combo populado com os números de ER.
   */
  public DataSet getErNbrDomain()
  {
    return m_erNbrDomain;
  }

  /**
   * @param erNbrDomain_.Popula o dataset com ER's existentes na base.
   */
  public void setErNbrDomain( DataSet erNbrDomain_ )
  {
    m_erNbrDomain = erNbrDomain_;
  }

  /**
   * @return m_erNbr. Retorna o número do ER.
   */
  public String getErNbr()
  {
    return m_erNbr;
  }

  /**
   * @param erNbr_.Seta o número do ER
   */
  public void setErNbr( String erNbr_ )
  {
    m_erNbr = erNbr_;
  }

  /*
   * (non-Javadoc)
   * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
   *      javax.servlet.http.HttpServletRequest)
   */
  public void reset( ActionMapping arg0_, HttpServletRequest arg1_ )
  {
    if ( m_selectedItemsInGrid != null )
    {
      for ( int i = 0; i < m_selectedItemsInGrid.length; i++ )
      {
        m_selectedItemsInGrid[ i ] = "N";
      }
    }

    if ( m_deletedItems != null )
    {
      for ( int j = 0; j < m_deletedItems.length; j++ )
      {
        m_deletedItems[ j ] = "N";
      }
    }

  }

  public String getSelectedErNbr()
  {
    return null;
  }

  public void setSelectedErNbr( String erNbr_ )
  {
    setErNbr( erNbr_ );

  }

  /**
   * Realiza as validações dos campos
   */
  public ActionErrors validate( ActionMapping mapping_, HttpServletRequest req_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseRelationEgDetailFncVO.C_RELTN_NBR_DESCRIPTION,
                                     m_reltnNbr,
                                     BaseTplRelationEgEntity.C_RELTN_NBR_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseRelationEgDetailFncVO.C_EG_NBR_DESCRIPTION,
                                    m_egNbr,
                                    BaseTplRelationEgEntity.C_EG_NBR_SIZE,
                                    errors );

    return errors;
  }
}