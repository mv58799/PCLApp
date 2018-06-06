package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplRelationEgMovEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela :
 * TplRelationEgMovement
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public class TplRelationEgMovEntity extends BaseTplRelationEgEntity
{

  public TplRelationEgMovEntity()
  {
    m_data = new TplRelationEgMovEntityVO();
  }

  public TplRelationEgMovEntity( TplRelationEgEntity egEntity_,
                                String opernCode_ )
  {
    m_data = new TplRelationEgMovEntityVO();

    TplRelationEgMovEntityVO egMovEntityVO = ( TplRelationEgMovEntityVO ) m_data;
    egMovEntityVO.setReltnNbr( egEntity_.getData().getReltnNbr() );
    egMovEntityVO.setEgNbr( egEntity_.getData().getEgNbr() );
    egMovEntityVO.setLastUpdDate( egEntity_.getData().getLastUpdDate() );
    egMovEntityVO.setLastUpdUserId( egEntity_.getData().getLastUpdUserId() );
    egMovEntityVO.setOpernCode( opernCode_ );
    //Número do ER
    egMovEntityVO.setErNbr( egEntity_.getData().getErNbr() );
  }

}