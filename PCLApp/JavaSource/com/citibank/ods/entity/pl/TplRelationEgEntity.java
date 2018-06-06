package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplRelationEgEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgMovEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela : TplRelationEg
 * @author leonardo.nakada
 * @date 15/04/2007
 */

public class TplRelationEgEntity extends BaseTplRelationEgEntity
{
  public static final int C_LAST_AUTH_USER_ID_SIZE = 20;

  public static final int C_REC_STAT_CODE_SIZE = 1;

  public TplRelationEgEntity()
  {
    m_data = new TplRelationEgEntityVO();
  }

  public TplRelationEgEntity( TplRelationEgMovEntity egMovEntity_,
                             String lastAuthUserId_, Date lastAuthDate_,
                             String recStatusCode_ )
  {
    m_data = new TplRelationEgEntityVO();
    TplRelationEgEntityVO tplRelationEgEntityVO = ( TplRelationEgEntityVO ) m_data;

    TplRelationEgMovEntityVO movEntityVO = ( TplRelationEgMovEntityVO ) egMovEntity_.getData();

    tplRelationEgEntityVO.setEgNbr( movEntityVO.getEgNbr() );
    tplRelationEgEntityVO.setReltnNbr( movEntityVO.getReltnNbr() );
    tplRelationEgEntityVO.setLastAuthDate( lastAuthDate_ );
    tplRelationEgEntityVO.setLastAuthUserId( lastAuthUserId_ );
    tplRelationEgEntityVO.setLastUpdDate( movEntityVO.getLastUpdDate() );
    tplRelationEgEntityVO.setLastUpdUserId( movEntityVO.getLastUpdUserId() );
    tplRelationEgEntityVO.setRecStatCode( recStatusCode_ );
    //Número do ER
    tplRelationEgEntityVO.setErNbr( movEntityVO.getErNbr() );

  }

}