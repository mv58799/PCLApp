/*
 * Created on Mar 2, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.classcmplc.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplClassCmplcEntity;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.BaseClassCmplcListFncVO;

/**
 * @author bruno.zanetti
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class BaseClassCmplcListForm extends BaseForm implements
    ClassCmplcDetailable
{

  //Codigo da Classificacao Compliance.
  private String m_classCmplcCodeSrc = "";

  // Descricao do Classificacao Compliance
  private String m_classCmplcTextSrc = "";

  // Data e hora da ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDateSrc = "";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro
  private String m_lastUpdUserIdSrc = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCodeSrc = "";

  // Indicador de Sensitivity: indica se a classificacao e considerada
  // Sensitive, assim clientes associados a mesma deverao ter as regras de
  // monitoramento diferenciadas dos demais clientes.
  private String m_sensIndSrc = "";

  private String m_selectedClassCmplcCode = null;
  
  private DataSet m_sensIndDomain = null ;

  /**
   * @return Returns sensIndDomain.
   */
  public DataSet getSensIndDomain()
  {
    return m_sensIndDomain;
  }
  /**
   * @param sensIndDomain_ Field sensIndDomain to be setted.
   */
  public void setSensIndDomain( DataSet sensIndDomain_ )
  {
    m_sensIndDomain = sensIndDomain_;
  }
  /**
   * Comment for <code>m_results</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_results = null;

  /**
   * @return Returns classCmplcCodeSrc.
   */
  public String getClassCmplcCodeSrc()
  {
    return m_classCmplcCodeSrc;
  }

  /**
   * @param classCmplcCodeSrc_ Field classCmplcCodeSrc to be setted.
   */
  public void setClassCmplcCodeSrc( String classCmplcCodeSrc_ )
  {
    m_classCmplcCodeSrc = classCmplcCodeSrc_;
  }

  /**
   * @return Returns classCmplcTextSrc.
   */
  public String getClassCmplcTextSrc()
  {
    return m_classCmplcTextSrc;
  }

  /**
   * @param classCmplcTextSrc_ Field classCmplcTextSrc to be setted.
   */
  public void setClassCmplcTextSrc( String classCmplcTextSrc_ )
  {
    m_classCmplcTextSrc = classCmplcTextSrc_;
  }

  /**
   * @return Returns lastUpdDateSrc.
   */
  public String getLastUpdDateSrc()
  {
    return m_lastUpdDateSrc;
  }

  /**
   * @param lastUpdDateSrc_ Field lastUpdDateSrc to be setted.
   */
  public void setLastUpdDateSrc( String lastUpdDateSrc_ )
  {
    m_lastUpdDateSrc = lastUpdDateSrc_;
  }

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * @return Returns recStatCodeSrc.
   */
  public String getRecStatCodeSrc()
  {
    return m_recStatCodeSrc;
  }

  /**
   * @param recStatCodeSrc_ Field recStatCodeSrc to be setted.
   */
  public void setRecStatCodeSrc( String recStatCodeSrc_ )
  {
    m_recStatCodeSrc = recStatCodeSrc_;
  }

  /**
   * @return Returns sensIndSrc.
   */
  public String getSensIndSrc()
  {
    return m_sensIndSrc;
  }

  /**
   * @param sensIndSrc_ Field sensIndSrc to be setted.
   */
  public void setSensIndSrc( String sensIndSrc_ )
  {
    m_sensIndSrc = sensIndSrc_;
  }

  /**
   * @return Returns results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.form.ClassCmplcDetailable#getSelectedClassCmplcCode()
   */
  public String getSelectedClassCmplcCode()
  {
    return m_selectedClassCmplcCode;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.form.ClassCmplcDetailable#setSelectedClassCmplcCode(java.lang.String)
   */
  public void setSelectedClassCmplcCode( String selectedClassCmplc_ )
  {
    m_selectedClassCmplcCode = selectedClassCmplc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.form.ClassCmplcDetailable#getClassCmplcCode()
   */
  public String getClassCmplcCode()
  {
    return m_selectedClassCmplcCode;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.form.ClassCmplcDetailable#setClassCmplcCode(java.lang.String)
   */
  public void setClassCmplcCode( String selectedClassCmplc_ )
  {
    m_selectedClassCmplcCode = selectedClassCmplc_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseClassCmplcListFncVO.C_CLASS_CMPLC_CODE_SRC_DESCRIPTION,
                                     m_classCmplcCodeSrc,
                                     BaseTplClassCmplcEntity.C_CLASS_CMPLC_CODE_SRC_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseClassCmplcListFncVO.C_CLASS_CMPLC_TEXT_SRC_DESCRIPTION,
                                    m_classCmplcTextSrc,
                                    BaseTplClassCmplcEntity.C_CLASS_CMPLC_TEXT_SRC_SIZE,
                                    errors );

    return errors;
  }

}