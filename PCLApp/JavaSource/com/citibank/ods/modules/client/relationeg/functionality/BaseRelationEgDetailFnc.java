/*
 * Created on Apr 15, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.relationeg.functionality;

import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplRelationEgEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplRelationPrvtEntity;
import com.citibank.ods.modules.client.relationeg.form.BaseRelationEgDetailForm;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.BaseRelationEgDetailFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseRelationEgDetailFnc extends BaseFnc
{

  /**
   * Atualiza as informações do FncVO com os dados vindos da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseRelationEgDetailFncVO relationEgDetailFncVO = ( BaseRelationEgDetailFncVO ) fncVO_;
    BaseRelationEgDetailForm relationEgDetailForm = ( BaseRelationEgDetailForm ) form_;
    
	String[] codeArray = null;
	if(relationEgDetailForm.getSelectedCode()!= null && !relationEgDetailForm.getSelectedCode().equals("")){
	  codeArray = relationEgDetailForm.getSelectedCode().split(","); 	
	}

    //Número do ER
    relationEgDetailFncVO.setErNbr( relationEgDetailForm.getErNbr() != null
                                    && !relationEgDetailForm.getErNbr().equals(
                                                                                "" )
                                                                                    ? relationEgDetailForm.getErNbr()
                                                                                    : "" );
                                                                                    
    if(codeArray!= null){
	  relationEgDetailFncVO.setErNbr(codeArray[1]);    	                                                                                    
    }
    relationEgDetailFncVO.setSelectedEgNbrInGrid( relationEgDetailForm.getSelectedEgNbrInGrid() );

    String selectedReltnNbr = relationEgDetailForm.getSelectedReltnNbrInGrid();
    if ( selectedReltnNbr != null && !selectedReltnNbr.equals( "" ) )
    {
      relationEgDetailFncVO.setSelectedReltnNbrInGrid( new BigInteger(
                                                                       selectedReltnNbr ) );
    }

    String egNbr = relationEgDetailForm.getEgNbr();
    relationEgDetailFncVO.setEgNbr( egNbr );
    
	if(codeArray!= null){
	  relationEgDetailFncVO.setEgNbr(codeArray[0]);    	                                                                                    
	}    

    String reltnNbr = relationEgDetailForm.getReltnNbr();
    if ( reltnNbr != null && !reltnNbr.equals( "" ) )
    {
      relationEgDetailFncVO.setReltnNbr( new BigInteger( reltnNbr ) );
    }
    else
    {
      relationEgDetailFncVO.setReltnNbr( null );
    }
    BaseTplRelationEgEntity relationEgEntity = new BaseTplRelationEgEntity();

    //Manipulação dos arrays de string para os itens inseridos e excluídos do
    // grid
    if ( !relationEgDetailFncVO.isFromSearch() )
    {
      if ( relationEgDetailForm.getSelectedItemsInGrid() != null )
      {
        relationEgDetailFncVO.setSelectedItemsInGrid( new ArrayList() );
        for ( int i = 0; i < relationEgDetailForm.getSelectedItemsInGrid().length; i++ )
        {
          relationEgDetailFncVO.getSelectedItemsInGrid().add(
                                                              relationEgDetailForm.getSelectedItemsInGrid()[ i ] );

        }

      }
      else
      {
        relationEgDetailFncVO.setSelectedItemsInGrid( new ArrayList() );

      }

      if ( relationEgDetailForm.getDeletedItems() != null )
      {
        relationEgDetailFncVO.setDeletedItems( new ArrayList() );
        for ( int j = 0; j < relationEgDetailForm.getDeletedItems().length; j++ )
        {
          relationEgDetailFncVO.getDeletedItems().add(
                                                       relationEgDetailForm.getDeletedItems()[ j ] );

        }

      }
      else
      {
        relationEgDetailFncVO.setDeletedItems( new ArrayList() );
      }

    }
    relationEgDetailFncVO.setClickedSearch( "" );
  }

  /**
   * Atualiza os dados da Form com os atributos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseRelationEgDetailFncVO relationEgDetailFncVO = ( BaseRelationEgDetailFncVO ) fncVO_;
    BaseRelationEgDetailForm relationEgDetailForm = ( BaseRelationEgDetailForm ) form_;

    //Número do Eg
    relationEgDetailForm.setEgNbr( relationEgDetailFncVO.getEgNbr() );

    //Número do ER
    relationEgDetailForm.setErNbr( relationEgDetailFncVO.getErNbr() != null
                                   && !relationEgDetailFncVO.getErNbr().equals(
                                                                                "" )
                                                                                    ? relationEgDetailFncVO.getErNbr()
                                                                                    : "" );
    //Número do relacionamento
    if ( relationEgDetailFncVO.getReltnNbr() != null )
    {
      relationEgDetailForm.setReltnSeqNbr( relationEgDetailFncVO.getReltnNbr().toString() );
      relationEgDetailForm.setReltnNbr( relationEgDetailFncVO.getReltnNbr().toString() );
    }
    else
    {
      relationEgDetailForm.setReltnSeqNbr( "" );
      relationEgDetailForm.setReltnNbr( "" );
    }

    //Manipulação dos arrays de string para os itens inseridos e excluídos do
    // grid
    if ( relationEgDetailFncVO.getSelectedItemsInGrid() != null
         && !relationEgDetailFncVO.getSelectedItemsInGrid().isEmpty() )
    {
      String[] itemsSelectedInForm = new String[ relationEgDetailFncVO.getSelectedItemsInGrid().size() ];

      for ( int i = 0; i < relationEgDetailFncVO.getSelectedItemsInGrid().size(); i++ )
      {
        itemsSelectedInForm[ i ] = ( String ) relationEgDetailFncVO.getSelectedItemsInGrid().get(
                                                                                                  i );
      }

      relationEgDetailForm.setSelectedItemsInGrid( itemsSelectedInForm );
    }
    else
    {
      String[] itemsLoaded = new String[ relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size() ];

      for ( int i = 0; i < relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size(); i++ )
      {
        itemsLoaded[ i ] = "N";
      }

      relationEgDetailForm.setSelectedItemsInGrid( itemsLoaded );

    }

    if ( relationEgDetailFncVO.getDeletedItems() != null
         && !relationEgDetailFncVO.getDeletedItems().isEmpty() )
    {
      String[] deletedItemsInList = new String[ relationEgDetailFncVO.getDeletedItems().size() ];

      for ( int i = 0; i < relationEgDetailFncVO.getDeletedItems().size(); i++ )
      {
        deletedItemsInList[ i ] = ( String ) relationEgDetailFncVO.getDeletedItems().get(
                                                                                          i );
      }

      relationEgDetailForm.setDeletedItems( deletedItemsInList );
    }
    else
    {
      String[] deletedItems = new String[ relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size() ];

      for ( int i = 0; i < relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size(); i++ )
      {
        deletedItems[ i ] = "N";
      }

      relationEgDetailForm.setDeletedItems( deletedItems );

    }

    //Carrega o combo com os números de ER existentes
    relationEgDetailForm.setErNbrDomain( relationEgDetailFncVO.getErNbrDomain() );
    relationEgDetailForm.setResults( relationEgDetailFncVO.getResults() );
    relationEgDetailForm.setClickedSearch( relationEgDetailFncVO.getClickedSearch() );
  }

  /*
   * Recupera um TplRelationPrvtEntity
   */
  protected TplRelationPrvtEntity findRelationByPk( BigInteger reltnNbr )
  {
    TplRelationPrvtEntity relationPrvtEntity = new TplRelationPrvtEntity();
    relationPrvtEntity.getData().setReltnNbr( reltnNbr );
    try
    {
      TplRelationPrvtDAO relationPrvtDAO = ODSDAOFactory.getInstance().getTplRelationPrvtDAO();
      relationPrvtEntity = ( TplRelationPrvtEntity ) relationPrvtDAO.find( relationPrvtEntity );
    }
    catch ( NoRowsReturnedException e )
    {
      relationPrvtEntity = null;
    }

    return relationPrvtEntity;
  }

  /*
   * Recupera os Customers associado
   */
  protected void findCustomers( TplRelationPrvtEntity relationPrvtEntity )
  {
    TplCustomerPrvtDAO tplCustomerPrvtDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtDAO();
    TplCustomerPrvtEntity custEntity = new TplCustomerPrvtEntity();

    if ( relationPrvtEntity.getData().getReltnCust1Nbr() != null )
    {
      custEntity.getData().setCustNbr(
                                       relationPrvtEntity.getData().getReltnCust1Nbr() );
      try
      {
        TplCustomerPrvtEntity cust1 = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( custEntity );
        relationPrvtEntity.setCustomerPrvtCmplEntity1( cust1 );
      }
      catch ( NoRowsReturnedException e )
      {
      }
    }

    if ( relationPrvtEntity.getData().getReltnCust2Nbr() != null )
    {
      custEntity.getData().setCustNbr(
                                       relationPrvtEntity.getData().getReltnCust2Nbr() );
      try
      {
        TplCustomerPrvtEntity cust2 = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( custEntity );
        relationPrvtEntity.setCustomerPrvtCmplEntity2( cust2 );
      }
      catch ( NoRowsReturnedException e )
      {
      }
    }

    if ( relationPrvtEntity.getData().getReltnCust3Nbr() != null )
    {
      custEntity.getData().setCustNbr(
                                       relationPrvtEntity.getData().getReltnCust3Nbr() );
      try
      {
        TplCustomerPrvtEntity cust3 = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( custEntity );
        relationPrvtEntity.setCustomerPrvtCmplEntity3( cust3 );
      }
      catch ( NoRowsReturnedException e )
      {
      }
    }

    if ( relationPrvtEntity.getData().getReltnCust4Nbr() != null )
    {
      custEntity.getData().setCustNbr(
                                       relationPrvtEntity.getData().getReltnCust4Nbr() );
      try
      {
        TplCustomerPrvtEntity cust4 = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( custEntity );
        relationPrvtEntity.setCustomerPrvtCmplEntity4( cust4 );
      }
      catch ( NoRowsReturnedException e )
      {
      }
    }

    if ( relationPrvtEntity.getData().getReltnCust5Nbr() != null )
    {
      custEntity.getData().setCustNbr(
                                       relationPrvtEntity.getData().getReltnCust5Nbr() );
      try
      {
        TplCustomerPrvtEntity cust5 = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( custEntity );
        relationPrvtEntity.setCustomerPrvtCmplEntity5( cust5 );
      }
      catch ( NoRowsReturnedException e )
      {
      }
    }
  }

  protected void loadErNbr( BaseFncVO fncVO_ )
  {
    BaseRelationEgDetailFncVO relationEgDetailFncVO = ( BaseRelationEgDetailFncVO ) fncVO_;
    //Cria uma instacia do DAO de ERxEM
    TplErEmDAO tplErEmDAO = ODSDAOFactory.getInstance().getTplErEmDAO();
    relationEgDetailFncVO.setErNbrDomain( tplErEmDAO.loadErNbr() );
  }

}