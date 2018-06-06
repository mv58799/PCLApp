package com.citibank.ods.modules.client.officer.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTbgOfficerEntity;
import com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity;
import com.citibank.ods.entity.pl.BaseTplRelationPrvtEntity;
import com.citibank.ods.modules.client.officer.functionality.valueobject.BaseOfficerListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * 
 * @see package com.citibank.ods.modules.client.officer.form;
 * @version 1.0
 * @author l.braga,23/03/2007
 *  
 */

public class BaseOfficerListForm extends BaseForm implements OfficerDetailable,
    OfficerSearchable
{
  // Número do Officer
  private String m_offcrNbrSrc = "";

  // Nome do Officer
  private String m_offcrNameTextSrc = "";

  // Número do officer real quando o Código for de um officer fictício
  private String m_offcrRealNbrSrc = "";

  //Número do officer selecionado no grid.
  private String m_selectedOffcrNbr = "";

  // DataSet como os resultados do banco.
  private DataSet m_results;

  //Número internacional do officer
  private String m_offcrIntlNbrSrc = "";

  //Tipo do officer
  private String m_offcrTypeCodeSrc = "";

  //Número do relacionamento
  private String m_reltnNbrSrc = "";

  //Tipo do Officer - Combo
  private DataSet m_offcrTypeCodeDomain = null;

  //Variável de controle - Verifica se existe ou não dados complementares de
  // banker
  private String m_existingData = "";

  /**
   * @return m_offcrNameTextSrc. Retorna o nome do officer.
   */
  public String getOffcrNameTextSrc()
  {
    return m_offcrNameTextSrc;
  }

  /**
   * @param offcrNameTextSrc_.Seta o nome do officer.
   */
  public void setOffcrNameTextSrc( String offcrNameTextSrc_ )
  {
    m_offcrNameTextSrc = offcrNameTextSrc_;
  }

  /**
   * @return m_offcrNbrSrc. Retorna o número do officer.
   */
  public String getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param offcrNbrSrc_.Seta o número do officer.
   */
  public void setOffcrNbrSrc( String offcrNbrSrc_ )
  {
    m_offcrNbrSrc = offcrNbrSrc_;
  }

  /**
   * @return m_offcrRealNbrSrc. Retorna o número real do officer.
   */
  public String getOffcrRealNbrSrc()
  {
    return m_offcrRealNbrSrc;
  }

  /**
   * @param offcrRealNbrSrc_.Seta o número real do officer.
   */
  public void setOffcrRealNbrSrc( String offcrRealNbrSrc_ )
  {
    m_offcrRealNbrSrc = offcrRealNbrSrc_;
  }

  /**
   * @return m_results. Retorna uma lista com o resultado da pesquisa.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_.Seta o resultado da pesquisa na lista.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * Método da interface OfficerDetailable
   *  
   */
  public String getSelectedOffcrNbr()
  {
    return m_selectedOffcrNbr;
  }

  /**
   * Método da interface OfficerDetailable
   *  
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    m_selectedOffcrNbr = selectedOffcrNbr_;
  }

  /**
   * @return m_offcrIntlNbrSrc.Retorna o número internacional do officer.
   */
  public String getOffcrIntlNbrSrc()
  {
    return m_offcrIntlNbrSrc;
  }

  /**
   * @param offcrIntlNbrSrc_.Seta o número internacional do officer.
   */
  public void setOffcrIntlNbrSrc( String offcrIntlNbrSrc_ )
  {
    m_offcrIntlNbrSrc = offcrIntlNbrSrc_;
  }

  /**
   * @return m_offcrTypeCodeDomain. Retorna uma lista com os tipos de Officer.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }

  /**
   * @param offcrTypeCodeDomain_.Seta na lista os tipos de officer.
   */
  public void setOffcrTypeCodeDomain( DataSet offcrTypeCodeDomain_ )
  {
    m_offcrTypeCodeDomain = offcrTypeCodeDomain_;
  }

  /**
   * @return m_offcrTypeCodeSrc. Retorna o tipo de officer.
   */
  public String getOffcrTypeCodeSrc()
  {
    return m_offcrTypeCodeSrc;
  }

  /**
   * @param offcrTypeCodeSrc_.Seta o tipo de Officer.
   */
  public void setOffcrTypeCodeSrc( String offcrTypeCodeSrc_ )
  {
    m_offcrTypeCodeSrc = offcrTypeCodeSrc_;
  }

  /**
   * @return m_reltnNbrSrc. Retorna o número do relacionamento.
   */
  public String getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }

  /**
   * @param reltnNbrSrc_.Seta o número do relacionamento.
   */
  public void setReltnNbrSrc( String reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
  }

  /**
   * @return m_existingData. Retorna se 0 quando não tem dados complementares e
   *         1 quando tem dados complementares.
   */
  public String getExistingData()
  {
    return m_existingData;
  }

  /**
   * @param existingData_.Seta 0 ou 1 na variável.
   */
  public void setExistingData( String existingData_ )
  {
    m_existingData = existingData_;
  }
  
//INICIO======================Alteraçao G&P	
  //Tag para indicar se o cliente mudou e consequentemente limpar a tela
  private boolean clrScreen;
 
/**
 * @return clrScreen
 */
public boolean getClrScreen() {
	return clrScreen;
}

/**
 * @param clrScreenParm
 */
public void setClrScreen(boolean clrScreenParm) {
	clrScreen = clrScreenParm;
}
//FIM=========================Alteraçao G&P

  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseOfficerListFncVO.C_OFFCR_NBR_SRC_DESCRIPTION,
                                     m_offcrNbrSrc,
                                     BaseTbgOfficerEntity.C_OFFCR_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseOfficerListFncVO.C_OFFCR_NAME_TEXT_SRC_DESCRIPTION,
                                    m_offcrNameTextSrc,
                                    BaseTbgOfficerEntity.C_OFFCR_NAME_TEXT_SRC_SIZE,
                                    errors );

    ODSValidator.validateBigInteger(
                                     BaseOfficerListFncVO.C_OFFCR_REAL_NBR_SRC_DESCRIPTION,
                                     m_offcrRealNbrSrc,
                                     BaseTbgOfficerEntity.C_OFFCR_REAL_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseOfficerListFncVO.C_OFFCR_INTL_DESCRIPTION,
                                     m_offcrIntlNbrSrc,
                                     BaseTplOfficerCmplEntity.C_OFFCR_INTL_NBR_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseOfficerListFncVO.C_RELATION_NBR_DESCRIPTION,
                                     m_reltnNbrSrc,
                                     BaseTplRelationPrvtEntity.C_RELTN_NBR_SRC_SIZE,
                                     errors );

    return errors;
  }

}