package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplRelationEgEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela : BaseTplRelationEg
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public class BaseTplRelationEgEntity extends BaseODSEntity
{
  public static final int C_RELTN_NBR_SIZE = 11;
  
  public static final int C_EG_NBR_SIZE = 4;
  
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;
  
  private TplRelationPrvtEntity m_relationPrvtEntity;

  /*
   * EntityVO da transação
   */
  protected BaseTplRelationEgEntityVO m_data = null;

  /**
   * Retorna o EntityVO da transação
   */
  public BaseTplRelationEgEntityVO getData()
  {
    return m_data;
  }

  /**
   * @return Returns the relationPrvtEntity.
   */
  public TplRelationPrvtEntity getRelationPrvtEntity()
  {
    return m_relationPrvtEntity;
  }

  /**
   * @param relationPrvtEntity_ The relationPrvtEntity to set.
   */
  public void setRelationPrvtEntity( TplRelationPrvtEntity relationPrvtEntity_ )
  {
    m_relationPrvtEntity = relationPrvtEntity_;
  }
  
  /**
   * Verifica se o objeto é igual ao passado por parâmetro
   * Uma entity de Relacionamento x EG pode ser considerada
   * igual se o EG e o Número de Relacionamento forem o mesmo
   */
  public boolean equals( Object obj_ )
  {
    BaseTplRelationEgEntity baseTplRelationEgEntity = (BaseTplRelationEgEntity) obj_;
    
    boolean isReltnNbrEqual = baseTplRelationEgEntity.getData().getReltnNbr() == this.getData().getReltnNbr() ||
    						  baseTplRelationEgEntity.getData().getReltnNbr().equals(this.getData().getReltnNbr());
    
    boolean isEgNbrEqual = baseTplRelationEgEntity.getData().getEgNbr() == this.getData().getEgNbr() ||
	  					   baseTplRelationEgEntity.getData().getEgNbr().equals(this.getData().getEgNbr());
    
    return isEgNbrEqual && isReltnNbrEqual;
  }
}