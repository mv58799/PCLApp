package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplRelationEgEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgHistEntityVO;

/**
 * Classe que instancia a entidade correspondente a tabela :
 * TplRelationEgHistory
 * @author leonardo.nakada
 * @date 15/04/2007
 */
public class TplRelationEgHistEntity extends BaseTplRelationEgEntity
{

  public TplRelationEgHistEntity()
  {
    m_data = new TplRelationEgHistEntityVO();
  }

  public TplRelationEgHistEntity( TplRelationEgEntity tplRelationEgEntity_,
                                 Date date )
  {
    m_data = new TplRelationEgHistEntityVO();
    TplRelationEgHistEntityVO tplRelationEgHistEntityVO = ( TplRelationEgHistEntityVO ) m_data;
    TplRelationEgEntityVO tplRelationEgEntityVO = ( TplRelationEgEntityVO ) tplRelationEgEntity_.getData();

    tplRelationEgHistEntityVO.setEgNbr( tplRelationEgEntityVO.getEgNbr() );
    tplRelationEgHistEntityVO.setReltnNbr( tplRelationEgEntityVO.getReltnNbr() );
    tplRelationEgHistEntityVO.setReltnEgRefDate( date );
    tplRelationEgHistEntityVO.setLastUpdUserId( tplRelationEgEntityVO.getLastUpdUserId() );
    tplRelationEgHistEntityVO.setLastUpdDate( tplRelationEgEntityVO.getLastUpdDate() );
    tplRelationEgHistEntityVO.setLastAuthUserId( tplRelationEgEntityVO.getLastAuthUserId() );
    tplRelationEgHistEntityVO.setLastAuthDate( tplRelationEgEntityVO.getLastAuthDate() );
    tplRelationEgHistEntityVO.setRecStatCode( tplRelationEgEntityVO.getRecStatCode() );
    //Número do ER
    tplRelationEgHistEntityVO.setErNbr( tplRelationEgEntityVO.getErNbr() );

  }
}