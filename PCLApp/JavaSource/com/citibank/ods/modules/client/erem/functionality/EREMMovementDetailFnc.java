/*
 * Created on Apr 23, 2007
 *
 */
package com.citibank.ods.modules.client.erem.functionality;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplErEmEntity;
import com.citibank.ods.entity.pl.TplErEmHistEntity;
import com.citibank.ods.entity.pl.TplErEmMovEntity;
import com.citibank.ods.entity.pl.TplRoleCustEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEmEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplErEmMovEntityVO;
import com.citibank.ods.modules.client.erem.form.EREMMovementDetailForm;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmHistDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmMovDAO;
import com.citibank.ods.persistence.pl.dao.TplRoleCustDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 */
public class EREMMovementDetailFnc extends BaseEREMDetailFnc implements
    ODSMovementDetailFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.erem.functionality.BaseEREMDetailFnc#getDAO()
   */
  protected BaseTplErEmDAO getDAO()
  {
    return null;
  }

  /**
   * Realizar a atualização do reristro
   */
  public void update( BaseFncVO fncVO_ )
  {

    TplErEmMovEntity erMovEntity;
    TplErEmMovEntityVO erMovEntityVO;

    this.validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      EREMMovementDetailFncVO erMovDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
      String erNbr = erMovDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();

      TplErEmMovDAO tplRelationErMovDAO = ODSDAOFactory.getInstance().getTplErEmMovDAO();
      tplRelationErMovDAO.deleteRelations( erNbr );

      for ( int j = 0; j < erMovDetailFncVO.getSelectedItemsInGrid().size(); j++ )
      {
        for ( int i = 0; i < erMovDetailFncVO.getBaseTplErEmEntities().size(); i++ )
        {
          if ( erMovDetailFncVO.getDeletedItems().get( j ).equals( "S" ) )
          {
            erMovDetailFncVO.getBaseTplErEmEntities().remove( i );
            break;
          }
        }
      }

      ArrayList list = erMovDetailFncVO.getBaseTplErEmEntities();
      for ( Iterator ite = list.iterator(); ite.hasNext(); )
      {
        erMovEntity = ( TplErEmMovEntity ) ite.next();
        erMovEntityVO = ( TplErEmMovEntityVO ) erMovEntity.getData();
        erMovEntityVO.setLastUpdDate( new Date() );
        erMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );

        if ( erMovEntityVO.getOpernCode() == null )
        {
          erMovEntityVO.setOpernCode( TplErEmMovEntity.C_OPERN_CODE_INSERT );
        }

        tplRelationErMovDAO.insert( erMovEntity );
      }
    }
  }

  /**
   * Realiza a aprovacao das associações EG x Relacionamentos
   */
  public void approve( BaseFncVO fncVO_ )
  {

    TplErEmEntity tplErEmEntity;
    TplErEmHistEntity TplErEmHistEntity;

    this.validateApprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      EREMMovementDetailFncVO erMovDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
      String erNbr = erMovDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();

      TplErEmDAO tplRelationErDAO = ODSDAOFactory.getInstance().getTplErEmDAO();
      TplErEmHistDAO tplRelationErHistDAO = ODSDAOFactory.getInstance().getTplErEmHistDAO();

      // Inseri os dados da tabela de movimento
      ArrayList approveList = erMovDetailFncVO.getBaseTplErEmEntities();
      if ( approveList != null && !approveList.isEmpty() )
      {
          Date date = new Date();

   	    TplErEmMovEntity tplErEmMovEntity;
        TplErEmMovEntityVO tplErEmMovEntityVO;

        Date approveDate = new Date();
        String authUserId = fncVO_.getLoggedUser().getUserID();
        
        List<String> listEmNbr = new ArrayList<String>();
        
        for ( Iterator ite = approveList.iterator(); ite.hasNext(); )
        {

        	tplErEmMovEntity = ( TplErEmMovEntity ) ite.next();
        	tplErEmMovEntityVO = ( TplErEmMovEntityVO ) tplErEmMovEntity.getData();
        	
        	//Verifica se Em já passou pela atualizacao, pois qdo há mais de 1 cliente com o mesmo EM, vem 2 registros no 'approveList' com o mesmo ER x EM
        	if (!listEmNbr.contains(tplErEmMovEntityVO.getEmNbr())){ 

        		// Se existe associações na Current, copiar para histórico
        		if ( tplRelationErDAO.existsRelation( erNbr, tplErEmMovEntityVO.getEmNbr() ) )
        		{
        			ArrayList list = tplRelationErDAO.listAllByErEM(erNbr, tplErEmMovEntityVO.getEmNbr() );
        			if ( list != null && !list.isEmpty() )
        			{
        				if (list.iterator().hasNext()){
        					{
        						tplErEmEntity = ( TplErEmEntity ) list.iterator().next();
        						TplErEmHistEntity = new TplErEmHistEntity( tplErEmEntity, date );

        						tplRelationErHistDAO.insert( TplErEmHistEntity );
        					}
        				}
        			}
        		}

        		tplErEmEntity = new TplErEmEntity(
        				tplErEmMovEntity,
        				authUserId,
        				approveDate,
        				TplErEmEntity.C_REC_STAT_CODE_ACTIVE );
        		if ( TplErEmMovEntity.C_OPERN_CODE_DELETE.equals( tplErEmMovEntityVO.getOpernCode() ) )
        		{
        			( ( TplErEmEntityVO ) tplErEmEntity.getData() ).setRecStatCode( TplErEmEntity.C_REC_STAT_CODE_INACTIVE );
        			tplRelationErDAO.update( tplErEmEntity );

        		}
        		else
        		{
        			if ( tplRelationErDAO.existsRelation( erNbr,
        					tplErEmMovEntityVO.getEmNbr() ) )
        			{
//        				//Verifica se já existe associacao inativa, caso exista deve gravar na tabela de historico antes de atualizar o registro	
//        				if (tplRelationErDAO.existsRelationInactive(erNbr, tplErEmMovEntityVO.getEmNbr() ) ){
//        					TplErEmHistEntity = new TplErEmHistEntity( tplErEmEntity, date );
//        					tplRelationErHistDAO.insert( TplErEmHistEntity );
//        				}

        				tplRelationErDAO.update( tplErEmEntity );
        			}
        			else
        			{
        				tplRelationErDAO.insert( tplErEmEntity );
        			}

        		}

        		listEmNbr.add(tplErEmMovEntityVO.getEmNbr());

        		TplErEmMovDAO tplRelationErMovDAO = ODSDAOFactory.getInstance().getTplErEmMovDAO();
            	tplRelationErMovDAO.deleteRelations( erNbr );
        	}
        }
      }
    }
  }

  /**
   * Realiza a reprovação das associações
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    this.validateReprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      EREMMovementDetailFncVO erMovDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
      String erNbr = erMovDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();

      TplErEmMovDAO tplRelationErMovDAO = ODSDAOFactory.getInstance().getTplErEmMovDAO();
      tplRelationErMovDAO.deleteRelations( erNbr );
    }
  }

  /**
   * Realiza as validações de Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    EREMMovementDetailFncVO erMovDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
    ArrayList list = erMovDetailFncVO.getBaseTplErEmEntities();

    String lastUpdUserId = getLastUpdUserFromList( erMovDetailFncVO );
    //  testar usuário
    if ( erMovDetailFncVO.getLoggedUser() == null
         || !erMovDetailFncVO.getLoggedUser().getUserID().equals( lastUpdUserId ) )
    {
      erMovDetailFncVO.addError( EREMMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }

  }

  /**
   * Realiza as validações da aprovação
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    EREMMovementDetailFncVO erMovDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
    String lastUpdUserId = getLastUpdUserFromList( erMovDetailFncVO );

    // testar usuário
    if ( erMovDetailFncVO.getLoggedUser() == null
         || erMovDetailFncVO.getLoggedUser().getUserID().equals( lastUpdUserId ) )
    {
      erMovDetailFncVO.addError( EREMMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }
  }

  /*
   * Recupera o usuário da última atualização dos reristros
   */
  private String getLastUpdUserFromList(
                                        EREMMovementDetailFncVO erMovDetailFncVO )
  {
    ArrayList list = erMovDetailFncVO.getBaseTplErEmEntities();
    if ( list != null && list.size() > 0 )
    {
      TplErEmMovEntity tplErEmMovEntity = ( TplErEmMovEntity ) list.iterator().next();

      return tplErEmMovEntity.getData().getLastUpdUserId();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carreramentos iniciais para a atualização
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    EREMMovementDetailFncVO erMovDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
    super.loadDomains( erMovDetailFncVO );

    if ( erMovDetailFncVO.isFromSearch() )
    {
      loadEmNbr( erMovDetailFncVO );
      loadCustText( erMovDetailFncVO );
      erMovDetailFncVO.setFromSearch( false );
    }
    else
    {
      String erNbr = erMovDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();
      TplErEmMovDAO tplRelationErMovDAO = ODSDAOFactory.getInstance().getTplErEmMovDAO();

      ArrayList list = tplRelationErMovDAO.listByErNbr( erNbr );
      if ( list != null && list.size() > 0 )
      {
        TplErEmMovEntity relationErMovEntity = ( TplErEmMovEntity ) list.iterator().next();
        TplErEmMovEntityVO erMovEntityVO = ( TplErEmMovEntityVO ) relationErMovEntity.getData();

        erMovDetailFncVO.setCurrentOpernCode( erMovEntityVO.getOpernCode() );
        erMovDetailFncVO.setBaseTplErEmEntities( list );
      }
      erMovDetailFncVO.setCustFullNameText( null );
      erMovDetailFncVO.getBaseTplErEmEntity().getData().setRoleCustCode( null );
      erMovDetailFncVO.getBaseTplErEmEntity().getData().setEmNbr( null );
      erMovDetailFncVO.setCustNbr( null );
    }

  }

  /**
   * Carreramentos iniciais para a aprovação
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    EREMMovementDetailFncVO erMovDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;

    String erNbr = erMovDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();
    TplErEmMovDAO tplRelationErMovDAO = ODSDAOFactory.getInstance().getTplErEmMovDAO();

    ArrayList list = tplRelationErMovDAO.listByErNbr( erNbr );
    if ( list != null )
    {
      erMovDetailFncVO.setBaseTplErEmEntities( list );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Retorna uma instancia de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new EREMMovementDetailFncVO();
  }

  /**
   * Atualiza as informações do Form com os valores vindos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    TplErEmMovEntity erEntity;
    TplErEmMovEntityVO erEntityVO;

    super.updateFormFromFncVO( form_, fncVO_ );

    EREMMovementDetailForm relationErDetailForm = ( EREMMovementDetailForm ) form_;
    EREMMovementDetailFncVO relationErDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;

    String[][] grid = new String[ relationErDetailFncVO.getBaseTplErEmEntities().size() ][ 7 ];
    int indexLinha = 0;
    for ( Iterator ite = relationErDetailFncVO.getBaseTplErEmEntities().iterator(); ite.hasNext(); indexLinha++ )
    {
      erEntity = ( TplErEmMovEntity ) ite.next();
      erEntityVO = ( TplErEmMovEntityVO ) erEntity.getData();

      String strErNbr = "";
      if ( erEntityVO.getErNbr() != null )
      {
        strErNbr = erEntityVO.getErNbr();
      }
      grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_ER_NBR ] = strErNbr;

      String strEmNbr = "";
      if ( erEntityVO.getEmNbr() != null )
      {
        strEmNbr = erEntityVO.getEmNbr();
      }
      grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_EM_NBR ] = strEmNbr;

      if ( erEntity.getRoleCustEntity() != null )
      {
        TplRoleCustEntity tplRoleCustEntity = null;

        tplRoleCustEntity = erEntity.getRoleCustEntity();
        if ( tplRoleCustEntity != null
             && tplRoleCustEntity.getData().getRoleCustText() != null )
        {
          grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_ROLE_CUST_CODE ] = tplRoleCustEntity.getData().getRoleCustText();
        }
        else
        {
          grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_ROLE_CUST_CODE ] = "";
        }
      }

      String strCustFullName = "";
      TplCustomerPrvtEntity tplCustomerPrvtEntity = erEntity.getCustomerPrvtEntity();
      if ( tplCustomerPrvtEntity != null
           && tplCustomerPrvtEntity.getData().getCustFullNameText() != null )
      {
        strCustFullName = tplCustomerPrvtEntity.getData().getCustFullNameText();
      }
      grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_CUST_FULL_NAME ] = strCustFullName;

      String strLastUpdUserId = "";
      if ( erEntityVO.getLastUpdUserId() != null )
      {
        strLastUpdUserId = erEntityVO.getLastUpdUserId();
      }
      grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_LAST_UPD_USER_ID ] = strLastUpdUserId;

      String strLastUpdDate = "";
      if ( erEntityVO.getLastUpdDate() != null )
      {
        SimpleDateFormat sdf = new SimpleDateFormat(
                                                     Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
        strLastUpdDate = sdf.format( erEntityVO.getLastUpdDate() );
      }
      grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_LAST_UPD_DATE ] = strLastUpdDate;

      String strOpernCode = "";
      if ( erEntityVO.getOpernCode() != null )
      {
        strOpernCode = ODSConstraintDecoder.decodeOpern( erEntityVO.getOpernCode() );
      }
      grid[ indexLinha ][ EREMMovementDetailForm.COL_POS_OPERN_CODE ] = strOpernCode;

      relationErDetailForm.setLastUpdUserId( strLastUpdUserId );
    }

    relationErDetailForm.setErEmGrid( grid );

  }

  /*
   * Insere o domínio no Grid
   */
  public void insertDomain( BaseFncVO fncVO_ )
  {
    EREMMovementDetailFncVO detailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
    this.validateInsertDomain( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {
      // Entity to Insert
      TplErEmMovEntity tplErEmEntity = ( TplErEmMovEntity ) detailFncVO.getBaseTplErEmEntity();

      // Cópia
      TplErEmMovEntity cpTplErEmMovEntity = new TplErEmMovEntity();
      cpTplErEmMovEntity.getData().setErNbr( tplErEmEntity.getData().getErNbr() );
      cpTplErEmMovEntity.getData().setEmNbr( tplErEmEntity.getData().getEmNbr() );
      cpTplErEmMovEntity.getData().setRoleCustCode(
                                                    tplErEmEntity.getData().getRoleCustCode() );
      ( ( TplErEmMovEntityVO ) cpTplErEmMovEntity.getData() ).setOpernCode( TplErEmMovEntity.C_OPERN_CODE_INSERT );

      // Recuperando o Role Cust
      TplRoleCustDAO roleCustDAO = ODSDAOFactory.getInstance().getTplRoleCustDAO();
      TplRoleCustEntity tplRoleCustEntity = new TplRoleCustEntity();
      tplRoleCustEntity.getData().setRoleCustCode(
                                                   tplErEmEntity.getData().getRoleCustCode() );
      cpTplErEmMovEntity.setRoleCustEntity( ( TplRoleCustEntity ) roleCustDAO.find( tplRoleCustEntity ) );

      TplCustomerPrvtDAO tplCustomerPrvtDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtDAO();
      TplCustomerPrvtEntity tplCustomerPrvtEntity = new TplCustomerPrvtEntity();
      tplCustomerPrvtEntity.getData().setCustNbr( detailFncVO.getCustNbr() );
      cpTplErEmMovEntity.setCustomerPrvtEntity( ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( tplCustomerPrvtEntity ) );
      detailFncVO.getSelectedItemsInGrid().add( "S" );
      detailFncVO.getDeletedItems().add( "N" );

      detailFncVO.getBaseTplErEmEntities().add( cpTplErEmMovEntity );
    }
  }

  /*
   * Realiza as validações do insert domain
   */
  private void validateInsertDomain( EREMMovementDetailFncVO detailFncVO_ )
  {
    EREMMovementDetailFncVO detailFncVO = ( EREMMovementDetailFncVO ) detailFncVO_;
    TplErEmMovEntityVO erEmEntityVO = ( TplErEmMovEntityVO ) detailFncVO.getBaseTplErEmEntity().getData();

    // Validação de campos obrigatórios

    if ( erEmEntityVO.getErNbr() == null || erEmEntityVO.getErNbr().equals( "" ) )
    {
      detailFncVO.addError( EREMMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                            EREMMovementDetailFncVO.C_ER_NBR_DESCRIPTION );
    }

    if ( erEmEntityVO.getEmNbr() == null || erEmEntityVO.getEmNbr().equals( "" ) )
    {
      detailFncVO.addError( EREMMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                            EREMMovementDetailFncVO.C_EM_NBR_DESCRIPTION );
    }

    if ( erEmEntityVO.getRoleCustCode() == null
         || erEmEntityVO.getRoleCustCode().equals( "" ) )
    {
      detailFncVO.addError(
                            EREMMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                            EREMMovementDetailFncVO.C_ROLE_CUST_CODE_DESCRIPTION );
    }

    if ( !detailFncVO.hasErrors() )
    {
      // Valida se o registro a ser inserido
      // possui o mesmo ER dos outros
      ArrayList list = detailFncVO.getBaseTplErEmEntities();
      if ( !list.isEmpty() )
      {
        TplErEmMovEntity tplErEmEntity = ( TplErEmMovEntity ) list.iterator().next();
        String firstErNbr = tplErEmEntity.getData().getErNbr();

        if ( !erEmEntityVO.getErNbr().equals( firstErNbr ) )
        {
          detailFncVO.addError(
                                EREMMovementDetailFncVO.C_ERROR_DUPLICATE_REG_IN_GRID,
                                EREMMovementDetailFncVO.C_ER_NBR_DESCRIPTION );
        }
      }

      // Verifica se já existe essa associação ER x EM no Grid
      if ( detailFncVO.getBaseTplErEmEntities().contains(
                                                          detailFncVO.getBaseTplErEmEntity() ) )
      {
        detailFncVO.addError( EREMMovementDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
      }
    }

    if ( !detailFncVO.hasErrors() )
    {
      TplCustomerPrvtCmplDAO tplCustomerPrvtCmplDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtCmplDAO();
      if ( !tplCustomerPrvtCmplDAO.existsEmNbr( erEmEntityVO.getEmNbr() ) )
      {
        detailFncVO.addError( EREMMovementDetailFncVO.C_ERROR_REG_NOT_EXISTS,
                              EREMMovementDetailFncVO.C_EM_NBR_DESCRIPTION );
      }
    }
  }

  /*
   * Remove o domínio do Grid
   */
  public void deleteDomain( BaseFncVO fncVO_ )
  {
    EREMMovementDetailFncVO eremMovementDetailFncVO = ( EREMMovementDetailFncVO ) fncVO_;
    ArrayList list = eremMovementDetailFncVO.getBaseTplErEmEntities();

    TplErEmMovEntity tplEREMEntity = new TplErEmMovEntity();
    tplEREMEntity.getData().setErNbr(
                                      eremMovementDetailFncVO.getSelectedErNbrInGrid() );
    tplEREMEntity.getData().setEmNbr(
                                      eremMovementDetailFncVO.getSelectedEmNbrInGrid() );

    list.remove( tplEREMEntity );
  }

}