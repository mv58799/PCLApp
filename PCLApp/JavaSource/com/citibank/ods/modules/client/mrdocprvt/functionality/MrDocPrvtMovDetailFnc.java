package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplContactCustEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtMovEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;
import com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtMovDetailForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtMovDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de movimento da tabela de Memória de Risco.
 */
public class MrDocPrvtMovDetailFnc extends BaseMrDocPrvtDetailFnc implements
    ODSMovementDetailFnc
{

  private static boolean m_isInsertDeleteContactCustAction;

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    MrDocPrvtMovDetailForm form = ( MrDocPrvtMovDetailForm ) form_;
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;
    
	String[] codeArray = null;
	if(form.getSelectedCode()!= null && !form.getSelectedCode().equals("")){
	  codeArray = form.getSelectedCode().split(","); 	
	}

    // Recupera o código documento MR selecionado no grid de consulta em lista
    BigInteger mrDocCode = ( form.getMrDocCode() != null
                             && form.getMrDocCode().length() > 0
                                                                ? new BigInteger(
                                                                                  form.getMrDocCode() )
                                                                : null );
    if(codeArray!= null){
    	mrDocCode = new BigInteger(codeArray[0]);                                                            
    }

    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
    mrDocPrvtMovEntityVO.setMrDocCode( mrDocCode );
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    MrDocPrvtMovDetailForm form = ( MrDocPrvtMovDetailForm ) form_;
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    String mrDocCode = ( mrDocPrvtMovEntityVO.getMrDocCode() != null
                                                                    ? mrDocPrvtMovEntityVO.getMrDocCode().toString()
                                                                    : null );
    int contactSize = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().size();

    ArrayList opernCodeList = new ArrayList();
    

    ContactCustKey contactCustKey = null;
    TplContactCustEntity contactCustMovEntity = null;
    TplContactCustEntityVO contactCustMovEntityVO =  null;
    HashMap contactCustMovEntities = ( HashMap ) fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities();
    String[] itemsSelectedInForm = new String[ contactCustMovEntities.size() ];
    String[] deletedItemsForm = new String[ contactCustMovEntities.size() ];
    String[] opernCodeArray = new String[ contactCustMovEntities.size() ];

    BaseTplMrDocPrvtEntity mrDocPrvtEntity = ( ( TplMrDocPrvtMovEntity ) fncVO.getTplMrDocPrvtEntity() );
    TplMrDocPrvtMovDAO mrDocPrvtDAO = ( TplMrDocPrvtMovDAO ) getDAO();

    if ( mrDocPrvtDAO.existsMovement( ( TplMrDocPrvtMovEntity ) mrDocPrvtEntity ) )
    {
      mrDocPrvtEntity = ( TplMrDocPrvtMovEntity ) mrDocPrvtDAO.findById( mrDocPrvtEntity );
      HashMap compareContactCust = ( HashMap ) mrDocPrvtEntity.getTplContactCustEntities();

      if ( contactCustMovEntities.size() > 0 )
      {
        Iterator contactCustMovEntitiesIt = contactCustMovEntities.keySet().iterator();
        int count = 0;
        while ( contactCustMovEntitiesIt.hasNext() )
        {
          contactCustKey = ( ContactCustKey ) contactCustMovEntitiesIt.next();
          contactCustMovEntity = ( TplContactCustEntity ) contactCustMovEntities.get( contactCustKey );
          contactCustMovEntityVO  = ((TplContactCustEntityVO)contactCustMovEntity.getData());
          

          if ( compareContactCust.keySet().contains( contactCustKey ) )
          {
            itemsSelectedInForm[ count ] = BaseODSEntity.C_INDICATOR_NO;
          }
          else
          {
            itemsSelectedInForm[ count ] = BaseODSEntity.C_INDICATOR_YES;
          }
          
          if (contactCustMovEntityVO.getOpernCode() != null ){
        	  opernCodeArray[ count ] = ODSConstraintDecoder.decodeOpern( contactCustMovEntityVO.getOpernCode() );
        	  if (contactCustMovEntityVO.getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_DELETE)) {
        		  deletedItemsForm[ count ] = BaseODSEntity.C_INDICATOR_YES;
        	  } else {
        		  deletedItemsForm[ count ] = BaseODSEntity.C_INDICATOR_NO;
        	  } 
          } else {
        	  opernCodeArray[ count ] = ODSConstraintDecoder.decodeOpern( BaseODSEntity.C_OPERN_CODE_INSERT );
       		  deletedItemsForm[ count ] = BaseODSEntity.C_INDICATOR_NO;
          }
          count++;
        }
     
      }
    }


    form.setContactSize( contactSize );
    form.setMrDocCode( mrDocCode );
    form.setOpernCodeArray( opernCodeArray );
    form.setSelectedItemsInGrid( itemsSelectedInForm );
    form.setDeletedItems( deletedItemsForm );
  }

  /**
   * @see com.citibank.ods.modules.client.mrdocprvt.functionality.BaseMrDocPrvtDetailFnc#getDAO()
   */
  protected BaseTplMrDocPrvtDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();
    return mrDocPrvtMovDAO;
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new MrDocPrvtMovDetailFncVO();
  }

  /**
   * Associa um contato a uma memória de risco.
   * 
   * @param BaseFncVO - Objeto com os dados para executar a ação.
   */
  public void insertContactCust( BaseFncVO fncVO_ )
  {
    validateInsertContactCust( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {		
      MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;
	  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();

	  TplContactCustEntity contactCustEntity =  new TplContactCustEntity(new TplContactCustEntity(), BaseODSEntity.C_OPERN_CODE_INSERT);

	  contactCustEntity.getData().setLastAuthDate( new Date() );
	  contactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser() != null ? fncVO.getLoggedUser().getUserID() : "" );
	  contactCustEntity.getData().setLastUpdDate( new Date() );
	  contactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser() != null ? fncVO.getLoggedUser().getUserID() : "" );
	  contactCustEntity.getData().setRecStatCode( "A" );
      
	  //Gera um novo valor para sequence e retorna o mesmo
	  contactCustEntity.getData().setCtcNbr(new BigInteger(tplContactCustDAO.getNextValContactCustNbr().toString()));
	  
	  contactCustEntity.getData().setCustNbr(fncVO.getTo3ProductAccountEntity().getData().getCustNbr());
	  contactCustEntity.getData().setFullNameText(fncVO.getTplContactCustEntity().getData().getFullNameText());
	  contactCustEntity.getData().setFullNameText_2(fncVO.getTplContactCustEntity().getData().getFullNameText_2());
	  contactCustEntity.getData().setFullNameText_3(fncVO.getTplContactCustEntity().getData().getFullNameText_3());	  
	  contactCustEntity.getData().setPhoneNbr(fncVO.getTplContactCustEntity().getData().getPhoneNbr());
	  contactCustEntity.getData().setPhoneDddCode(fncVO.getTplContactCustEntity().getData().getPhoneDddCode());
	  contactCustEntity.getData().setPhoneExtnNbr(fncVO.getTplContactCustEntity().getData().getPhoneExtnNbr());	 
	  

	  tplContactCustDAO.insert( contactCustEntity );

      /*// Busca o contato
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplContactCustDAO contactCustDAO = odsDAOFactory.getTplContactCustDAO();
      contactCustEntity = contactCustDAO.find( contactCustEntity );*/

      // Adiciona contato na memória
      HashMap contactCustEntitiesMap = ( HashMap ) fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities();
      contactCustEntitiesMap.put(
                                  new ContactCustKey(
                                                      contactCustEntity.getData().getCustNbr(),
                                                      contactCustEntity.getData().getCtcNbr() ),
                                  contactCustEntity );

      // Adiciona chave do contato na lista de inseridos
      fncVO.getInsertedContactCust().add(
                                          new ContactCustKey(
                                                              contactCustEntity.getData().getCustNbr(),

                                                              contactCustEntity.getData().getCtcNbr() ) );

      //Cria uma instancia da entity de movimento de callback e adiciona à
      // lista de opern code.
      TplMrDocCallbackMovEntity tplMrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
      TplMrDocCallbackMovEntityVO tplMrDocCallbackMovEntityVO = (TplMrDocCallbackMovEntityVO) tplMrDocCallbackMovEntity.getData();
            
      BaseTplMrDocPrvtEntity tplMrDocPrvtEntity = fncVO.getTplMrDocPrvtEntity();
      TplMrDocPrvtMovEntityVO mrDocEntityVO = ( TplMrDocPrvtMovEntityVO ) tplMrDocPrvtEntity.getData();

      //seta os dados do novo callback
      tplMrDocCallbackMovEntityVO.setCustNbr( contactCustEntity.getData().getCustNbr() );
      tplMrDocCallbackMovEntityVO.setCtcNbr( contactCustEntity.getData().getCtcNbr() );
      tplMrDocCallbackMovEntityVO.setMrDocPrvt(mrDocEntityVO.getMrDocCode() );
      tplMrDocCallbackMovEntityVO.setProdAcctCode(mrDocEntityVO.getProdAcctCode());
      tplMrDocCallbackMovEntityVO.setProdUnderAcctCode(mrDocEntityVO.getProdUnderAcctCode());
      tplMrDocCallbackMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
      tplMrDocCallbackMovEntityVO.setLastUpdDate( new Date() );
      tplMrDocCallbackMovEntityVO.setLastUpdUserId(fncVO.getLoggedUser() != null ? fncVO.getLoggedUser().getUserID() : "" );
      
      fncVO.getCallBackMovList().add( tplMrDocCallbackMovEntity );
      fncVO.getDeletedItems().add( "N" );
      fncVO.setInsertContact( true );  
      
    }
  }

  /**
   * Desassocia um contato a uma memória de risco.
   * 
   * @param BaseFncVO - Objeto com os dados para executar a ação.
   */
  public void deleteContactCust( BaseFncVO fncVO_ )
  {
    validateDeleteContactCust( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;
      HashMap contactCustEntitiesMap = ( HashMap ) fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities();

      // Remove entidade de contato do mapa de contatos associadas
      contactCustEntitiesMap.remove( new ContactCustKey(
                                                         fncVO.getTo3ProductAccountEntity().getData().getCustNbr(),
                                                         fncVO.getAssociatedCtcNbr() ) );

      // Se o contato a ser desassociado está associado somente em memória,
      // apenas remove ele da memória.
      if ( fncVO.getInsertedContactCust().remove(
                                                  new ContactCustKey(
                                                                      fncVO.getTo3ProductAccountEntity().getData().getCustNbr(),
                                                                      fncVO.getAssociatedCtcNbr() ) ) )
      {
        fncVO.getInsertedContactCust().remove(
                                               new ContactCustKey(
                                                                   fncVO.getTo3ProductAccountEntity().getData().getCustNbr(),
                                                                   fncVO.getAssociatedCtcNbr() ) );
      }
      else
      {
        fncVO.getDeletedContactCust().add(
                                           new ContactCustKey(
                                                               fncVO.getTo3ProductAccountEntity().getData().getCustNbr(),
                                                               fncVO.getAssociatedCtcNbr() ) );
      }
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
/*  public void update( BaseFncVO fncVO_ )
  {

    TplMrDocCallbackMovEntity mrDocCallbackMovEntity;
    TplMrDocCallbackMovDAO mrDocCallbackMovDAO;

    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

      Date date = new Date();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
      mrDocPrvtMovEntityVO.setLastUpdDate( date );
      mrDocPrvtMovEntityVO.setLastUpdUserId( fncVO.getLoggedUser().getUserID() );
      mrDocPrvtMovEntityVO.setOpernCode( "A" );

      // Atualiza na tabela TPL_MR_DOC_PRVT_MOV
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = ( TplMrDocPrvtMovDAO ) getDAO();
      mrDocPrvtMovDAO.update( ( TplMrDocPrvtMovEntity ) fncVO.getTplMrDocPrvtEntity() );

      ContactCustKey contactCustKey = null;

      mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();
      mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();

      TplMrDocCallbackDAO tplMrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

      TplMrDocCallbackMovEntityVO tplMrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData();
      tplMrDocCallbackMovEntityVO.setMrDocPrvt( mrDocPrvtMovEntityVO.getMrDocCode() );
      tplMrDocCallbackMovEntityVO.setProdAcctCode( mrDocPrvtMovEntityVO.getProdAcctCode() );
      tplMrDocCallbackMovEntityVO.setProdUnderAcctCode( mrDocPrvtMovEntityVO.getProdUnderAcctCode() );

      //Retira da lista final os ítens que foram inseridos e excluídos na mesma
      // operação.
      int relationList = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().size();
      Iterator resultsIterator;

      for ( int count = 0; count < relationList; count++ )
      {

        resultsIterator = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          contactCustKey = ( ContactCustKey ) resultsIterator.next();

          if ( fncVO.getSelectedItemsInGrid().get( max ).equals( "S" )
               && fncVO.getDeletedItems().get( max ).equals( "S" ) )
          {
            fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().remove(
                                                                                       contactCustKey );
            fncVO.getSelectedItemsInGrid().remove( max );
            fncVO.getDeletedItems().remove( max );
            break;
          }
          else if ( fncVO.getSelectedItemsInGrid().get( max ).equals( "N" )
                    && fncVO.getDeletedItems().get( max ).equals( "N" ) )
          {
            fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().remove(
                                                                                       contactCustKey );
            fncVO.getSelectedItemsInGrid().remove( max );
            fncVO.getDeletedItems().remove( max );
            break;
          }
          max++;
        }
      }

      int count = 0;
      Iterator insertedContactCustIt = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();

      while ( insertedContactCustIt.hasNext() )
      {
        contactCustKey = ( ContactCustKey ) insertedContactCustIt.next();

        mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity(
                                                                ( TplMrDocPrvtMovEntity ) fncVO.getTplMrDocPrvtEntity(),
                                                                contactCustKey,
                                                                BaseODSEntity.C_OPERN_CODE_INSERT );

        if ( fncVO.getSelectedItemsInGrid().get( count ).equals( "S" )
             && fncVO.getDeletedItems().get( count ).equals( "N" ) )
        {
          ( ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
          mrDocCallbackMovEntity.getData().setMrCallbackCode(
                                                              new BigInteger(
                                                                              tplMrDocCallbackDAO.getNextMrCallBackCode().toString() ) );

          mrDocCallbackMovDAO.insert( mrDocCallbackMovEntity );

        }
        else// if ( fncVO.getSelectedItemsInGrid().get( count ).equals( "N" )
              //    && fncVO.getDeletedItems().get( count ).equals( "S" ) )
        {
        	if (mrDocCallbackMovDAO.existsMovement(mrDocCallbackMovEntity)){
        		mrDocCallbackMovDAO.update( mrDocCallbackMovEntity );
        	} else {
        		mrDocCallbackMovDAO.insert( mrDocCallbackMovEntity );
        	}
          
        }

        count++;
      }
    }
  }
*/
  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
	Date date = new Date();
    TplContactCustEntity contactCustEntity;
	TplMrDocPrvtMovDAO mrDocPrvtMovDAO = ( TplMrDocPrvtMovDAO ) getDAO();

    validateApprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

      Date lastAuthDate = new Date();
      String lastAuthUserId = fncVO.getLoggedUser().getUserID();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
      
	  ArrayList listMrMov = mrDocPrvtMovDAO.list(mrDocPrvtMovEntityVO.getProdAcctCode(),mrDocPrvtMovEntityVO.getProdUnderAcctCode());
      Iterator iListMrMov = listMrMov.iterator();
      while(iListMrMov.hasNext()){
		TplMrDocPrvtMovEntity iMovEntity = ( TplMrDocPrvtMovEntity )iListMrMov.next();
		TplMrDocPrvtMovEntityVO iMovEntityVO = (TplMrDocPrvtMovEntityVO) iMovEntity.getData();
		
        //Monta entidade de MR Current a partir de Mov
		TplMrDocPrvtEntity mrDocPrvtEntity = new TplMrDocPrvtEntity( iMovEntity,
																		lastAuthDate,
																		lastAuthUserId );

		// Verifica se MR existe em Current
		TplMrDocPrvtDAO mrDocPrvtDAO = odsDAOFactory.getTplMrDocPrvtDAO();
		if ( mrDocPrvtDAO.existsById( mrDocPrvtEntity ) )
		{
		   // Copia MR de Current para Hist
		   mrDocPrvtDAO.copyFromCurrentToHist( mrDocPrvtEntity, lastAuthDate );

		   // Atualiza MR de Current com os dados de MOV
		   mrDocPrvtDAO.update( mrDocPrvtEntity );
		}
		else
		{
		  //TplMrDocPrvtMovDAO mrDocPrvtMovDAO = ( TplMrDocPrvtMovDAO ) getDAO();
			 // Copia MR de MOV para Current
		   mrDocPrvtMovDAO.copyFromMovToCurrent( iMovEntity,
												   lastAuthDate, lastAuthUserId );
		}

		TplMrDocCallbackMovDAO mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();
		TplMrDocCallbackDAO tplMrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

		TplMrDocCallbackEntity tplMrDocCallbackEntity = new TplMrDocCallbackEntity();
		TplMrDocCallbackEntityVO tplMrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) tplMrDocCallbackEntity.getData();

		tplMrDocCallbackEntityVO.setMrDocCode( iMovEntityVO.getMrDocCode() );
		tplMrDocCallbackEntityVO.setProdAcctCode( iMovEntityVO.getProdAcctCode() );
		tplMrDocCallbackEntityVO.setProdUnderAcctCode( iMovEntityVO.getProdUnderAcctCode() );

		//Inativa todos os callBacks deste MR na Current
		//tplMrDocCallbackDAO.inativateAllMrDocCallBacks( tplMrDocCallbackEntity );

		// Recupera os contatos da movimento
		TplMrDocCallbackMovEntity mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
		TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData();

		mrDocCallbackMovEntityVO.setMrDocPrvt( iMovEntityVO.getMrDocCode() );
		mrDocCallbackMovEntityVO.setProdAcctCode( iMovEntityVO.getProdAcctCode() );
		mrDocCallbackMovEntityVO.setProdUnderAcctCode( iMovEntityVO.getProdUnderAcctCode() );

		//Busca os dados da tabela de movimento e insere na tabela corrente.
		ArrayList insertedContactCust = mrDocCallbackMovDAO.findContactCustByPK(
																					iMovEntityVO.getMrDocCode(),
																					iMovEntityVO.getProdAcctCode(),
																					iMovEntityVO.getProdUnderAcctCode() );
		Iterator insertedContactCustIt = insertedContactCust.iterator();
		while ( insertedContactCustIt.hasNext() )
		{
			 contactCustEntity = ( TplContactCustEntity ) insertedContactCustIt.next();

			 mrDocCallbackMovEntityVO.setCtcNbr( contactCustEntity.getData().getCtcNbr() );
			 mrDocCallbackMovEntityVO.setCustNbr( contactCustEntity.getData().getCustNbr() );

			 // Copia callback de Mov para Current
			 mrDocCallbackMovDAO.copyFromMovToCurrent( mrDocCallbackMovEntity,
													   lastAuthDate, lastAuthUserId );
			 
			 TplContactCustEntityVO tplContactCustEntityVO = (TplContactCustEntityVO) contactCustEntity.getData();
			 if (tplContactCustEntityVO.getOpernCode() != null && tplContactCustEntityVO.getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_DELETE)) {
				 TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
				 contactCustEntity.getData().setLastAuthDate(date);
				 contactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser().getUserID());
				 contactCustEntity.getData().setLastUpdDate(date);
				 contactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser().getUserID());
				 contactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
				 tplContactCustDAO.delete(contactCustEntity);
			 }
		}

		// Deleta Callback da tabela de Movimento
		mrDocCallbackMovDAO.deleteAll( mrDocCallbackMovEntity );

		// Deleta MR da tabela de Movimento
		//TplMrDocPrvtMovDAO mrDocPrvtMovDAO = ( TplMrDocPrvtMovDAO ) getDAO();
		mrDocPrvtMovDAO.delete(iMovEntity);
      	
      }

    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    validateReprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
	  TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
	  TplMrDocPrvtMovDAO mrDocPrvtMovDAO = ( TplMrDocPrvtMovDAO ) getDAO();
	  
	  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
	  
      
	  ArrayList listMrMov = mrDocPrvtMovDAO.list(mrDocPrvtMovEntityVO.getProdAcctCode(),mrDocPrvtMovEntityVO.getProdUnderAcctCode());
	  Iterator iListMrMov = listMrMov.iterator();
	  
	  TplMrDocCallbackMovDAO mrDocCallbackMovDAO = ( TplMrDocCallbackMovDAO ) odsDAOFactory.getTplMrDocCallbackMovDAO();
	  
	  ArrayList listContactCust = mrDocCallbackMovDAO.findContactCustByPK(mrDocPrvtMovEntityVO.getMrDocCode(), mrDocPrvtMovEntityVO.getProdAcctCode(), mrDocPrvtMovEntityVO.getProdUnderAcctCode());
	  Iterator itContactCust = listContactCust.iterator();
	  Date date =  new Date();
	  while (itContactCust.hasNext()) {
		  TplContactCustEntity tplContactCustEntity = (TplContactCustEntity)itContactCust.next();

		//exclusao logica na TPL_CONTACT CUST    	
    	tplContactCustEntity.getData().setLastAuthDate(date);
    	tplContactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser().getUserID());
    	tplContactCustEntity.getData().setLastUpdDate(date);
    	tplContactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser().getUserID());
    	tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
    	tplContactCustDAO.delete(tplContactCustEntity);
      
	  }
      
	  
	  while(iListMrMov.hasNext()){
		TplMrDocPrvtMovEntity iMovEntity = ( TplMrDocPrvtMovEntity )iListMrMov.next();		

		//Deleta callback da tabela TPL_MR_DOC_CALLBACK_MOV
		
		mrDocCallbackMovDAO.deleteAll( new TplMrDocCallbackMovEntity(iMovEntity,
																	 new ContactCustKey(
																							 null,
																							 null ),
																		 "E" ) );
    	
		// Deleta da tabela TPL_MR_DOC_PRVT_MOV		 
		 mrDocPrvtMovDAO.delete(iMovEntity );
	  }
    }  

  }

  /**
   * Valida a associação de contato a uma memória de risco.
   * 
   * @param BaseFncVO - Objeto com os dados para executar a ação.
   */
  public void validateInsertContactCust( BaseFncVO fncVO_ )
  {
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
	if ( fncVO.getTplContactCustEntity().getData().getPhoneDddCode() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
			C_PHONE_DDDCODE_NOT_NULL );
	}
	
	if ( fncVO.getTplContactCustEntity().getData().getPhoneNbr() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
		C_PHONE_NBR_NOT_NULL );
	}
	
	if ( fncVO.getTplContactCustEntity().getData().getFullNameText() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
			C_FULL_NAME_TEXT_NOT_NULL );
	}
    
    /*// Verifica se o contato está cadastrado na base (deve estar cadastrado)
    if ( !fncVO.hasErrors() )
    {
      // Monta entidade com os campos de pesquisa do contato
      TplContactCustEntity contactCustEntity = new TplContactCustEntity();
      contactCustEntity.getData().setCtcNbr( fncVO.getCtcNbr() );
      contactCustEntity.getData().setCustNbr(
                                              fncVO.getTo3ProductAccountEntity().getData().getCustNbr() );

      // Verificar se o contato está cadastrado e ativo.
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplContactCustDAO contactCustDAO = odsDAOFactory.getTplContactCustDAO();
      if ( !contactCustDAO.existsActive( contactCustEntity ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_PK );
      }
    }

    // Verifica se o contato está associado ao cliente (não deve estar
    // associado)
    if ( !fncVO.hasErrors() )
    {
      HashMap contactCustEntitiesMap = ( HashMap ) fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities();

      if ( contactCustEntitiesMap.containsKey( new ContactCustKey(
                                                                   fncVO.getTo3ProductAccountEntity().getData().getCustNbr(),
                                                                   fncVO.getCtcNbr() ) ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_INSERT_CONTACT );
      }
    }*/
  }

  /**
   * Valida a desassociação de contato a uma memória de risco.
   * 
   * @param BaseFncVO - Objeto com os dados para executar a ação.
   */
  public void validateDeleteContactCust( BaseFncVO fncVO_ )
  {
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
    if ( mrDocPrvtMovEntityVO.getMrDocCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }

    if ( fncVO.getCtcNbr() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_CTC_NBR_NOT_NULL );
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
    if ( mrDocPrvtMovEntityVO.getMrDocCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }

    int findItemsDeleted = 0;

    for ( int i = 0; i < fncVO.getDeletedItems().size(); i++ )
    {
      if ( fncVO.getDeletedItems().get( i ).equals( "S" ) )
      {
        findItemsDeleted++;
      }
    }

    if ( !fncVO_.hasErrors() )
    {
      if ( findItemsDeleted == fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().size() )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                         "atualizado", "Callback" );
      }
    }
    // Demais validações
    if ( !fncVO_.hasErrors() )
    {
      // Valida usuário
      if ( !fncVO_.getLoggedUser().getUserID().equals(
                                                       mrDocPrvtMovEntityVO.getLastUpdUserId() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
      }

      // Verifica se OPERN_CODE é diferente de "E" (Toda exclusão é bloqueada
      // para alteração)
      if ( !fncVO_.hasErrors() )
      {
        String opernCode = mrDocPrvtMovEntityVO.getOpernCode();

        if ( opernCode.equals( "E" ) )
        {
          fncVO_.addError( BaseODSFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
        }
      }
    }

  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
    if ( mrDocPrvtMovEntityVO.getMrDocCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }

    // Demais validações
    if ( !fncVO_.hasErrors() )
    {
      // Valida usuário
      if ( fncVO_.getLoggedUser().getUserID().equals(
                                                      mrDocPrvtMovEntityVO.getLastUpdUserId() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
      }
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
    if ( mrDocPrvtMovEntityVO.getMrDocCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtMovEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    // Carrega a tela pela primeira vez
    if ( !fncVO.isFromSearch() && !m_isInsertDeleteContactCustAction )
    {
      loadForDetail( fncVO_ );
      loadOpernCode( fncVO_ );
      super.load( fncVO_ );

      // Reseta os campos
      fncVO.setCtcNbr( null );
      fncVO.setFullNameText( "" );
      fncVO.setAssociatedCtcNbr( null );
      fncVO.resetInsertedContactCust();
      fncVO.resetDeletedContactCust();
	  	  
	  
	  
    }
    // Carrega a tela voltando do search
    else if ( fncVO.isFromSearch() )
    {
      fncVO.setFromSearch( false );

      if ( fncVO.getCtcNbr() != null )
      {
        BaseTplContactCustEntity contactCustEntity = new TplContactCustEntity();
        contactCustEntity.getData().setCtcNbr( fncVO.getCtcNbr() );
        contactCustEntity.getData().setCustNbr(
                                                fncVO.getTo3ProductAccountEntity().getData().getCustNbr() );
        ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
        BaseTplContactCustDAO contactCustDAO = odsDAOFactory.getTplContactCustDAO();
        contactCustEntity = contactCustDAO.find( contactCustEntity );
        fncVO.setFullNameText( contactCustEntity.getData().getFullNameText() );
      }
    }
    
	fncVO.getTplContactCustEntity().getData().setFullNameText("");
	fncVO.getTplContactCustEntity().getData().setFullNameText_2("");
	fncVO.getTplContactCustEntity().getData().setFullNameText_3("");
	fncVO.getTplContactCustEntity().getData().setPhoneDddCode(null);
	fncVO.getTplContactCustEntity().getData().setPhoneNbr(null);
	fncVO.getTplContactCustEntity().getData().setPhoneExtnNbr(null);

    loadDomains( fncVO );
    m_isInsertDeleteContactCustAction = false;
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    loadForDetail( fncVO_ );
    loadOpernCode( fncVO_ );
    super.load( fncVO_ );

  }

  /**
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
  }

  /**
   * Carregamento dos dados para ações: Atualização / Aprovação
   * 
   * @param fncVO_ - Objeto com os dados utilizados para o carregamento.
   */
  private void loadForDetail( BaseFncVO fncVO_ )
  {
    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    if ( !m_isInsertDeleteContactCustAction )
    {

      // Recupera os dados relacionados a memória de risco.
      BaseTplMrDocPrvtDAO mrDocPrvtDAO = getDAO();
      BaseTplMrDocPrvtEntity mrDocPrvtEntity = mrDocPrvtDAO.findById( fncVO.getTplMrDocPrvtEntity() );
      fncVO.setTplMrDocPrvtEntity( mrDocPrvtEntity );

      // Recupera os dados relacionados a Conta de Produto.
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      BaseTo3ProductAccountDAO productAccountDAO = odsDAOFactory.getTo3ProductAccountDAO();
      BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();
      productAccountEntity.getData().setProdAcctCode(
                                                      fncVO.getTplMrDocPrvtEntity().getData().getProdAcctCode() );
      productAccountEntity.getData().setProdUnderAcctCode(
                                                           fncVO.getTplMrDocPrvtEntity().getData().getProdUnderAcctCode() );
      productAccountEntity = productAccountDAO.findByPK( productAccountEntity );
      fncVO.setTo3ProductAccountEntity( productAccountEntity );

    }

    loadDomains( fncVO );
    m_isInsertDeleteContactCustAction = false;
  }

  /**
   * Seta o indicador da ação corrente. True se a ação é de inserção ou remoção
   * de contatos, false caso contrário.
   * 
   * @param insertDeleteContactCustAction - O indicador da ação corrente. True
   *          se a ação é de inserção ou remoção de contatos, false caso
   *          contrário.
   */
  public void setInsertDeleteContactCustAction(
                                               boolean insertDeleteContactCustAction )
  {
    m_isInsertDeleteContactCustAction = insertDeleteContactCustAction;
  }

  private void loadOpernCode( BaseFncVO fncVO_ )
  {

    MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;

    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TplMrDocCallbackMovDAO callbackMovDAO = factory.getTplMrDocCallbackMovDAO();
    TplMrDocCallbackMovEntity tplMrDocCallbackMovEntity;
    TplMrDocPrvtMovEntityVO tplMrDocPrvtMovEntityVO;
    ContactCustKey contactCustKey = null;
    Iterator callBackIt = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();
    fncVO.setCallBackMovList( new ArrayList() );

    while ( callBackIt.hasNext() )
    {
      contactCustKey = ( ContactCustKey ) callBackIt.next();
      tplMrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
      tplMrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
      tplMrDocCallbackMovEntity.getData().setCustNbr(
                                                      contactCustKey.getCustNbr() );
      tplMrDocCallbackMovEntity.getData().setCtcNbr( contactCustKey.getCtcNbr() );
      ( ( TplMrDocCallbackMovEntityVO ) tplMrDocCallbackMovEntity.getData() ).setMrDocPrvt( tplMrDocPrvtMovEntityVO.getMrDocCode() );

      
      fncVO.getCallBackMovList().add(
                                      callbackMovDAO.findCallBack( tplMrDocCallbackMovEntity ) );

    }
  }

  public void update( BaseFncVO fncVO_ )
  {

   
    TplMrDocCallbackMovDAO mrDocCallbackMovDAO;

    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      MrDocPrvtMovDetailFncVO fncVO = ( MrDocPrvtMovDetailFncVO ) fncVO_;
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

      Date date = new Date();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
      mrDocPrvtMovEntityVO.setLastUpdDate( date );
      mrDocPrvtMovEntityVO.setLastUpdUserId( fncVO.getLoggedUser().getUserID() );
      mrDocPrvtMovEntityVO.setOpernCode( "A" );

      // Atualiza na tabela TPL_MR_DOC_PRVT_MOV
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = ( TplMrDocPrvtMovDAO ) getDAO();
      mrDocPrvtMovDAO.update( ( TplMrDocPrvtMovEntity ) fncVO.getTplMrDocPrvtEntity() );

      ContactCustKey contactCustKey = null;

      mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();

      TplMrDocCallbackDAO tplMrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();
      
      TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO(); 


      //Retira da lista final os ítens que foram inseridos e excluídos na mesma
      // operação.
      Iterator resultsIterator;
      resultsIterator = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          contactCustKey = ( ContactCustKey ) resultsIterator.next();
          TplContactCustEntity tplContactCustEntity = (TplContactCustEntity)fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().get(contactCustKey);

          if ( fncVO.getSelectedItemsInGrid().get( max ).equals( "S" )
               && fncVO.getDeletedItems().get( max ).equals( "S" ) )
          {
            fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().remove(
                                                                                       contactCustKey );
            fncVO.getSelectedItemsInGrid().remove( max );
            fncVO.getDeletedItems().remove( max );
            
            //Exclusao logica da TPL_CONTACT_CUST
        	tplContactCustEntity.getData().setLastAuthDate(date);
        	tplContactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser().getUserID());
        	tplContactCustEntity.getData().setLastUpdDate(date);
        	tplContactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser().getUserID());
        	tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
        	tplContactCustDAO.delete(tplContactCustEntity);
            break;
          }
 
          max++;
        }
     

      
      Iterator insertedContactCustIt = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();
      TplMrDocCallbackMovEntity mrDocCallbackMovEntity = null;
      
      int count =0;
      while ( insertedContactCustIt.hasNext() )
      {
    	
        contactCustKey = ( ContactCustKey ) insertedContactCustIt.next();
        TplContactCustEntity tplContactCustEntity = (TplContactCustEntity)fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().get(contactCustKey);

       
        for ( int i = 0; i < fncVO.getCallBackMovList().size(); i++ )
        {    
        	mrDocCallbackMovEntity =  (TplMrDocCallbackMovEntity) fncVO.getCallBackMovList().get(i);
        	if ( (mrDocCallbackMovEntity.getData().getCtcNbr().equals(contactCustKey.getCtcNbr())) &&
        			(mrDocCallbackMovEntity.getData().getCustNbr().equals(contactCustKey.getCustNbr()))) {
        		break;
        		
        	}
        }
        	
   	
        if (mrDocCallbackMovEntity == null || (mrDocCallbackMovEntity != null && (mrDocCallbackMovEntity.getData().getMrCallbackCode() == null))) {
        	mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity(
        			( TplMrDocPrvtMovEntity ) fncVO.getTplMrDocPrvtEntity(),
        			contactCustKey,
        			BaseODSEntity.C_OPERN_CODE_INSERT );
        	mrDocCallbackMovEntity.getData().setMrCallbackCode(
        			new BigInteger(
        					tplMrDocCallbackDAO.getNextMrCallBackCode().toString() ) );

        	mrDocCallbackMovDAO.insert( mrDocCallbackMovEntity );
        } else {
        	if (mrDocCallbackMovDAO.existsMovement(mrDocCallbackMovEntity)){
        		//Exclui registro da MOV, caso era exclusão na MOV e foi retirada a flag de exclusão na tela
        		if (fncVO.getDeletedItems().get( count ).equals( "N" ) && 
        				((TplMrDocCallbackMovEntityVO)mrDocCallbackMovEntity.getData()).getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_DELETE)){
        			mrDocCallbackMovDAO.delete(mrDocCallbackMovEntity);
        		
        		//Exclui registro da MOV, caso era insercao na MOV e a flag de exclusão na tela foi ativada
        		} else if (fncVO.getDeletedItems().get( count ).equals( "S" ) && 
        				((TplMrDocCallbackMovEntityVO)mrDocCallbackMovEntity.getData()).getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_INSERT)){
        			
        			mrDocCallbackMovDAO.delete(mrDocCallbackMovEntity);
                    
        			//Exclusao logica da TPL_CONTACT_CUST
                	tplContactCustEntity.getData().setLastAuthDate(date);
                	tplContactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser().getUserID());
                	tplContactCustEntity.getData().setLastUpdDate(date);
                	tplContactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser().getUserID());
                	tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
                	tplContactCustDAO.delete(tplContactCustEntity);
        		} else  {
        		
        			if ( fncVO.getDeletedItems().get( count ).equals( "S" )){
        				((TplMrDocCallbackMovEntityVO)mrDocCallbackMovEntity.getData()).setOpernCode(BaseODSEntity.C_OPERN_CODE_DELETE);
        			}  else {
        				((TplMrDocCallbackMovEntityVO)mrDocCallbackMovEntity.getData()).setOpernCode(BaseODSEntity.C_OPERN_CODE_INSERT);
        			}

        			mrDocCallbackMovEntity.getData().setLastUpdDate( new Date() );
        			mrDocCallbackMovEntity.getData().setLastUpdUserId(fncVO.getLoggedUser() != null ? fncVO.getLoggedUser().getUserID() : "" );


        			mrDocCallbackMovDAO.update( mrDocCallbackMovEntity );
        		}
        	} 

        }
        count ++;        
      }
    }
  }
}