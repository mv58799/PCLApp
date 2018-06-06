package com.citibank.ods.modules.client.classcmplc.form;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplClassCmplcEntity;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.BaseClassCmplcDetailFncVO;

/**
*@author gerson.a.rodrigues
*
*/

public class BaseClassCmplcDetailForm extends BaseForm implements ClassCmplcDetailable
{

  /**
   * @param sensInd_ Field sensInd to be setted.
   */
  public void setSensInd( String sensInd_ )
  {
    m_sensInd = sensInd_;
  }
  // Codigo da Classificacao Compliance.
  private String m_classCmplcCode = "";

  // Descricao do Classificacao Compliance
  private String m_classCmplcText = "";

  // Data e hora da ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario (SOE ID) que  efetuou a ultima atualizacao no registro
  private String m_lastUpdUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao
  private String m_recStatCode = "";

  // Indicador de Sensitivity: indica se a classificacao e considerada Sensitive, assim clientes associados a mesma deverao ter as regras de monitoramento diferenciadas dos demais clientes.
  private String m_sensInd = "";
  
  private DataSet m_sensIndDomain = null;

  /**
  * @return Returns m_classCmplcCode.
  */
  public String getClassCmplcCode()
  {
    return m_classCmplcCode;
  }

  /**
  * @param classCmplcCode_ Field m_classCmplcCode to be setted.
  */
  public void setClassCmplcCode( String classCmplcCode_ )
  {
    m_classCmplcCode = classCmplcCode_;
  }

  /**
  * @return Returns m_classCmplcText.
  */
  public String getClassCmplcText()
  {
    return m_classCmplcText;
  }

  /**
  * @param classCmplcText_ Field m_classCmplcText to be setted.
  */
  public void setClassCmplcText( String classCmplcText_ )
  {
    m_classCmplcText = classCmplcText_;
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
  * @return Returns m_recStatCode.
  */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
  * @param recStatCode_ Field m_recStatCode to be setted.
  */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
  * @return Returns m_sensInd.
  */
  public String getSensInd()
  {
    return m_sensInd;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.form.ClassCmplcDetailable#setSelectedClassCmplcCode(java.math.BigInteger)
   */
  public void setSelectedClassCmplcCode( BigInteger selectedClassCmplc_ )
  {
   this.m_classCmplcCode = selectedClassCmplc_.toString(); 
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.form.ClassCmplcDetailable#getSelectedClassCmplcCode()
   */
  public String getSelectedClassCmplcCode()
  {
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.classcmplc.form.ClassCmplcDetailable#setSelectedClassCmplcCode(java.lang.String)
   */
  public void setSelectedClassCmplcCode( String selectedClassCmplc_ )
  {
    setClassCmplcCode( selectedClassCmplc_ );
  }
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                              HttpServletRequest request_ )
  {
       ActionErrors errors = new ActionErrors();

            ODSValidator.validateBigInteger(
                                    BaseClassCmplcDetailFncVO.C_CLASS_CMPLC_CODE_DESCRIPTION,
                                    m_classCmplcCode,
                                            BaseTplClassCmplcEntity.C_CLASS_CMPLC_CODE_SIZE,
                                    errors );

           ODSValidator.validateMaxLength(
                                              BaseClassCmplcDetailFncVO.C_CLASS_CMPLC_TEXT_DESCRIPTION,
                                    m_classCmplcText,
                                            BaseTplClassCmplcEntity.C_CLASS_CMPLC_TEXT_SIZE,
                                    errors );

           ODSValidator.validateMaxLength(
                                              BaseClassCmplcDetailFncVO.C_SENS_IND_DESCRIPTION,
                                    m_sensInd        ,
                                            BaseTplClassCmplcEntity.C_SENS_IND_SIZE,
                                    errors );


   return errors;
 }




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
}
