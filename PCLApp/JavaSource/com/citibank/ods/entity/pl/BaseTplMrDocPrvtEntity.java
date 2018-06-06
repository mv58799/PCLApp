package com.citibank.ods.entity.pl;

import java.util.Map;

import com.citibank.ods.common.entity.BaseEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplMrDocPrvtEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de Memória de Risco.
 */
public class BaseTplMrDocPrvtEntity extends BaseEntity
{

  // O entity VO de Memória de Risco.
  protected BaseTplMrDocPrvtEntityVO m_data = null;

  // Mapa das entidades de Contatos do Cliente
  protected Map m_tplContactCustEntities = null;
  
  //Tamanho do campo número do documento MR.
  public static final int C_MR_DOC_PRVT_SIZE = 6;
  
  //Tamanho do campo número do documento MR.
  public static final int C_MR_DOC_CODE_SIZE = 6;
  
  //Tamanho do campo número do cliente.
  public static final int C_CUST_NBR_SIZE = 11;
  
  //Tamanho do campo descrição do documento MR.
  public static final int C_MR_DOC_TEXT_SIZE = 40;
  
  //Tamanho do campo conta corrente.
  public static final int C_CUR_ACCT_NBR_SIZE = 15;
  
  //Tamanho do campo Número do contato
  public static final int C_CTC_NBR_SIZE=6;
  
  //Tamanho do campo usuário da última atualização
  public static final int C_LAST_UPD_USER_ID =20;
  

  /**
   * Recupera o entity VO de Memória de Risco.
   * 
   * @return BaseTplMrDocPrvtEntityVO - O entity VO de Memória de Risco.
   */
  public BaseTplMrDocPrvtEntityVO getData()
  {
    return m_data;
  }

  /**
   * Recupera o mapa das entidades de Contatos do Cliente
   * 
   * @return Map - O mapa das entidades de Contatos do Cliente
   */
  public Map getTplContactCustEntities()
  {
    return m_tplContactCustEntities;
  }

  /**
   * Seta o mapa das entidades de Contatos do Cliente
   * 
   * @param tplContactCustEntities_ - O mapa das entidades de Contatos do
   *          Cliente
   */
  public void setTplContactCustEntities( Map tplContactCustEntities_ )
  {
    m_tplContactCustEntities = tplContactCustEntities_;
  }
}