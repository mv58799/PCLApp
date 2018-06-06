package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;	
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.BaseTplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;
import com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplMrDocCallbackMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplMrDocPrvtMovEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;
import com.citibank.ods.modules.client.mrdocprvt.form.MrDocPrvtDetailForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.MrDocPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocCallbackMovDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplMrDocPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de Detalhe da tabela de Memória de Risco.
 */
public class MrDocPrvtDetailFnc extends BaseMrDocPrvtDetailFnc implements
    ODSDetailFnc
{
  private static boolean m_isInsertDeleteContactCustAction;

  private final String C_FROM_SEARCH = "1";

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    MrDocPrvtDetailForm form = ( MrDocPrvtDetailForm ) form_;
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    // Recupera o código documento MR selecionado no grid de consulta em lista
    BigInteger mrDocPrvt = ( form.getMrDocPrvt() != null
                             && form.getMrDocPrvt().length() > 0
                                                                ? new BigInteger(
                                                                                  form.getMrDocPrvt() )
                                                                : null );
    String fromCurAccount = ( form.getFromCurAccount() != null
                              && !form.getFromCurAccount().equals( "" )
                                                                       ? form.getFromCurAccount()
                                                                       : "" );

    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
    mrDocPrvtEntityVO.setMrDocPrvt( mrDocPrvt );
    fncVO.setFromCurAccount( fromCurAccount );
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    MrDocPrvtDetailForm form = ( MrDocPrvtDetailForm ) form_;
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
    To3ProductAccountEntityVO to3ProductAccountEntityVO = ( To3ProductAccountEntityVO ) fncVO.getTo3ProductAccountEntity().getData();

    String mrDocPrvt = ( mrDocPrvtEntityVO.getMrDocPrvt() != null
                                                                 ? mrDocPrvtEntityVO.getMrDocPrvt().toString()
                                                                 : null );
    String custNbr = ( to3ProductAccountEntityVO.getCustNbr() != null
                                                                     ? to3ProductAccountEntityVO.getCustNbr().toString()
                                                                     : null );

    HashMap contactCustEntities = ( HashMap ) fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities();
    
    ContactCustKey contactCustKey = null;
    BaseTplContactCustEntity contactCustMovEntity = null;

    //Cria uma instância da entity especifica
    TplMrDocPrvtEntity mrDocPrvtEntity = ( TplMrDocPrvtEntity ) fncVO.getTplMrDocPrvtEntity();
    TplMrDocPrvtDAO mrDocPrvtDAO = ( TplMrDocPrvtDAO ) getDAO();


      
    if ( contactCustEntities.size() > 0 ) {
      String[] itemsSelectedInForm = new String[ contactCustEntities.size() ];
      String[] itemsDeletedInForm = new String[ contactCustEntities.size() ];
      

      //Verifica se o item existe
	  if ( mrDocPrvtDAO.exists( mrDocPrvtEntity ) )
      {
		mrDocPrvtEntity = ( TplMrDocPrvtEntity ) mrDocPrvtDAO.find( mrDocPrvtEntity );

        HashMap compareContactCust = ( HashMap ) mrDocPrvtEntity.getTplContactCustEntities();

        if ( contactCustEntities.size() > 0 )
        {
          Iterator contactCustMovEntitiesIt = contactCustEntities.keySet().iterator();
          int count = 0;
          while ( contactCustMovEntitiesIt.hasNext() )
          {
        	  contactCustKey = ( ContactCustKey ) contactCustMovEntitiesIt.next();

            if ( compareContactCust.keySet().contains( contactCustKey ) )
            {

              itemsSelectedInForm[ count ] = BaseODSEntity.C_INDICATOR_NO;
            }
            else
            {
              itemsSelectedInForm[ count ] = BaseODSEntity.C_INDICATOR_YES;
            }
            count++;
          }
        }
      }
      else
      {
        for ( int i = 0; i < itemsSelectedInForm.length; i++ )
        {
          itemsSelectedInForm[ i ] = BaseODSEntity.C_INDICATOR_YES;
        }

      }
      
	  for ( int i = 0; i < contactCustEntities.size(); i++ )
	  {
		//itemsSelectedInForm[ i ] = BaseODSEntity.C_INDICATOR_YES;
		itemsDeletedInForm[ i ] = BaseODSEntity.C_INDICATOR_NO;
	  }

      form.setSelectedItemsInGrid( itemsSelectedInForm );
      form.setDeletedItems( itemsDeletedInForm );
    }

    form.setMrDocPrvt( mrDocPrvt );
    form.setCustNbr( custNbr );
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new MrDocPrvtDetailFncVO();
  }

  /**
   * @see com.citibank.ods.modules.client.mrdocprvt.functionality.BaseMrDocPrvtDetailFnc#getDAO()
   */
  protected BaseTplMrDocPrvtDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocPrvtDAO mrDocPrvtDAO = odsDAOFactory.getTplMrDocPrvtDAO();
    return mrDocPrvtDAO;
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
	  MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;	
	  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
		
	  TplContactCustEntity contactCustEntity = new TplContactCustEntity();

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
      

      // Monta entidade com os campos de pesquisa do contato
      /*BaseTplContactCustEntity BaseContactCustEntity = new TplContactCustEntity();
      contactCustEntity.getData().setCtcNbr( fncVO.getCtcNbr() );
      contactCustEntity.getData().setCustNbr(
                                              fncVO.getTo3ProductAccountEntity().getData().getCustNbr() );

      // Busca o contato
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
      fncVO.getDeletedItems().add( BaseODSEntity.C_INDICATOR_NO );
      fncVO.setInsertContact( true );
	  //fncVO.setFromSearch(true);
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
      MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
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

  public void Prepareinsert( BaseFncVO fncVO_ ){
	
	TplMrDocCallbackMovEntity mrDocCallbackMovEntity;
	TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO;
	ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
	TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();
	TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
	
	MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

	TplMrDocCallbackDAO tplMrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

	Date date = new Date();
	TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
	mrDocPrvtEntityVO.setLastUpdDate( date );
	mrDocPrvtEntityVO.setLastUpdUserId( fncVO.getLoggedUser().getUserID() );
	mrDocPrvtEntityVO.setRecStatCode( "A" );
	
	//Busca o novo valor para a sequence
	mrDocPrvtEntityVO.setMrDocPrvt(mrDocPrvtMovDAO.getNextVal());

	// Monta entidade com os valores a serem inseridos na tabela
	// TPL_MR_DOC_PRVT_MOV
	TplMrDocPrvtMovEntity mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
	TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();
	mrDocPrvtMovEntityVO.setMrDocCode(mrDocPrvtEntityVO.getMrDocPrvt()  );
	mrDocPrvtMovEntityVO.setMrDocText( mrDocPrvtEntityVO.getMrDocText() );
	mrDocPrvtMovEntityVO.setMrInvstCurAcctInd( mrDocPrvtEntityVO.getMrInvstCurAcctInd() );
	mrDocPrvtMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
	mrDocPrvtMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
	mrDocPrvtMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
	mrDocPrvtMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
	mrDocPrvtMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

	// Insere na tabela TPL_MR_DOC_PRVT_MOV
	
	mrDocPrvtMovDAO.insert( mrDocPrvtMovEntity );
	ContactCustKey contactCustKey = null;

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
		  TplContactCustEntity tplContactCustEntity = (TplContactCustEntity)fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().get(contactCustKey);
		  
			  if ( fncVO.getSelectedItemsInGrid().get( max ).equals( BaseODSEntity.C_INDICATOR_YES )
			   && fncVO.getDeletedItems().get( max ).equals( BaseODSEntity.C_INDICATOR_YES ) )
			  {
				fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().remove(
																								   contactCustKey );
				fncVO.getSelectedItemsInGrid().remove( max );
				fncVO.getDeletedItems().remove( max );
					
				//exclusao logica na TPL_CONTACT CUST    	
			    tplContactCustEntity.getData().setLastAuthDate(date);
			    tplContactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser().getUserID());
			    tplContactCustEntity.getData().setLastUpdDate(date);
			    tplContactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser().getUserID());
			    tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
			    tplContactCustDAO.delete(tplContactCustEntity);
			      
				break;
			  }
			  max ++;
	  }
	   relationList = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().size();
	}
	
	
	//Varre a lista de contatos inseridos ao memo de risco.
	Iterator insertedContactCustIt = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();
	while ( insertedContactCustIt.hasNext() )
	{
		contactCustKey = ( ContactCustKey ) insertedContactCustIt.next();

			// Monta entidade com os valores a serem inseridos na tabela
			// TPL_MR_DOC_CALLBACK_MOV
		mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
		mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData();
		mrDocCallbackMovEntityVO.setMrDocPrvt( mrDocPrvtEntityVO.getMrDocPrvt() );
	    mrDocCallbackMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
		mrDocCallbackMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

		mrDocCallbackMovEntityVO.setMrCallbackCode( new BigInteger(
																				tplMrDocCallbackDAO.getNextMrCallBackCode().toString() ) );
		mrDocCallbackMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
		mrDocCallbackMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
		mrDocCallbackMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
		mrDocCallbackMovEntityVO.setCustNbr( contactCustKey.getCustNbr() );
		mrDocCallbackMovEntityVO.setCtcNbr( contactCustKey.getCtcNbr() );

				// Insere relacionamento na tabela TPL_MR_DOC_CALLBACK_MOV
		TplMrDocCallbackMovDAO mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();
		mrDocCallbackMovDAO.insert( mrDocCallbackMovEntity );
	  	
	}
  }

  	
  
  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {    
	ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
	MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    { 
	  
	  TplMrDocPrvtEntity mrDocEntity = ( TplMrDocPrvtEntity ) fncVO.getTplMrDocPrvtEntity();
	  TplMrDocPrvtDAO dao = odsDAOFactory.getTplMrDocPrvtDAO();
	  
	  try{
		mrDocEntity = ( TplMrDocPrvtEntity )dao.findActive(mrDocEntity);
	  }
	  catch( NoRowsReturnedException exception ){
		mrDocEntity = null;
	  }	  
	 
	  TplMrDocPrvtEntityVO currentVo;	 
	  if(mrDocEntity != null){
		currentVo = ( TplMrDocPrvtEntityVO ) mrDocEntity.getData();
	  }
	  else{
		currentVo = null;
	  }	  

	  if ( currentVo!= null && currentVo.getRecStatCode().equals("A") )
	  {
		
		fncVO.setMrActive(true);
		if(fncVO.isConfirmCancel()){
			fncVO.setIsConfirmCancel(false);
			//			
			this.PrepareDelete(fncVO,mrDocEntity);
			this.Prepareinsert(fncVO);
			fncVO.setMrActive(false);
			fncVO.resetInsertedContactCust();
			fncVO.resetDeletedContactCust();
			fncVO.setSelectedItemsInGrid(new ArrayList());
			fncVO.setDeletedItems(new ArrayList());
			fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().clear();		
		}
				
	  }
	  else{
		this.Prepareinsert(fncVO);
		fncVO.resetInsertedContactCust();
		fncVO.resetDeletedContactCust();
		fncVO.setSelectedItemsInGrid(new ArrayList());
		fncVO.setDeletedItems(new ArrayList());
		fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().clear();
	  }
    }  
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    //Cria instância da Entity e do EntityVO específicos
    TplMrDocCallbackMovEntity mrDocCallbackMovEntity;
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO;

    //Validações de atualização
    validateUpdate( fncVO_ );

    //Se não houver erros inseridos pela validação o Update é executado.
    if ( !fncVO_.hasErrors() )
    {
      MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplMrDocCallbackDAO tplMrDocCallbackDAO = odsDAOFactory.getTplMrDocCallbackDAO();

      Date date = new Date();
      TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
      mrDocPrvtEntityVO.setLastUpdDate( date );
      mrDocPrvtEntityVO.setLastUpdUserId( fncVO.getLoggedUser().getUserID() );
      mrDocPrvtEntityVO.setRecStatCode( "A" );

      // Monta entidade com os valores a serem inseridos na tabela
      // TPL_MR_DOC_PRVT_MOV
      TplMrDocPrvtMovEntity mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();
      mrDocPrvtMovEntityVO.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
      mrDocPrvtMovEntityVO.setMrDocText( mrDocPrvtEntityVO.getMrDocText() );
      mrDocPrvtMovEntityVO.setMrInvstCurAcctInd( mrDocPrvtEntityVO.getMrInvstCurAcctInd() );
      mrDocPrvtMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
      mrDocPrvtMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
      mrDocPrvtMovEntityVO.setOpernCode( "A" );
      mrDocPrvtMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
      mrDocPrvtMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

      // Insere na tabela TPL_MR_DOC_PRVT_MOV
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();
      mrDocPrvtMovDAO.insert( mrDocPrvtMovEntity );

      ContactCustKey contactCustKey = null;

      //Retira da lista final os ítens que foram inseridos e excluídos na mesma
      // operação.
      int relationList = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().size();
      Iterator resultsIterator;

      for ( int count = 0; count < relationList; count++ )
      {

        resultsIterator = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          contactCustKey = ( ContactCustKey ) resultsIterator.next();

          if ( fncVO.getSelectedItemsInGrid().get( max ).equals( BaseODSEntity.C_INDICATOR_NO )
                    && fncVO.getDeletedItems().get( max ).equals( BaseODSEntity.C_INDICATOR_NO ) )
          {
            fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().remove(
                                                                                       contactCustKey );
            fncVO.getSelectedItemsInGrid().remove( max );
            fncVO.getDeletedItems().remove( max );
            break;
          }
          max++;          
        }
        relationList = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().size();
      }
      
      TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
      
      Iterator insertedContactCustIt = fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().keySet().iterator();
      int count = 0;
      while ( insertedContactCustIt.hasNext() )
      {
        contactCustKey = ( ContactCustKey ) insertedContactCustIt.next();

        // Monta entidade com os valores a serem inseridos na tabela
        // TPL_MR_DOC_CALLBACK_MOV
        mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
        mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData();
        mrDocCallbackMovEntityVO.setMrDocPrvt( mrDocPrvtEntityVO.getMrDocPrvt() );
        mrDocCallbackMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
        mrDocCallbackMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );
        mrDocCallbackMovEntityVO.setMrCallbackCode( new BigInteger(
                                                                    tplMrDocCallbackDAO.getNextMrCallBackCode().toString() ) );
        mrDocCallbackMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
        mrDocCallbackMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
        mrDocCallbackMovEntityVO.setCustNbr( contactCustKey.getCustNbr() );
        mrDocCallbackMovEntityVO.setCtcNbr( contactCustKey.getCtcNbr() );
        
        TplMrDocCallbackMovDAO mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();
                
        if ( fncVO.getSelectedItemsInGrid().get( count ).equals( BaseODSEntity.C_INDICATOR_YES )
                && fncVO.getDeletedItems().get( count ).equals( BaseODSEntity.C_INDICATOR_YES ) )
        {
        //exclusao logica na TPL_CONTACT CUST
        	TplContactCustEntity tplContactCustEntity =  new TplContactCustEntity();
        	tplContactCustEntity.getData().setCtcNbr(contactCustKey.getCtcNbr());
        	tplContactCustEntity.getData().setCustNbr(contactCustKey.getCustNbr());
        	tplContactCustEntity.getData().setLastAuthDate(date);
        	tplContactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser().getUserID());
        	tplContactCustEntity.getData().setLastUpdDate(date);
        	tplContactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser().getUserID());
        	tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
        	tplContactCustDAO.delete(tplContactCustEntity);
        } else {
  
        	//Verifica qual ação está sendo executada para o callBack e seta o
        	// opernCode da ação(I-Inserçãp e E-Exclusão)
        	if ( fncVO.getSelectedItemsInGrid().get( count ).equals( BaseODSEntity.C_INDICATOR_YES )
        			&& fncVO.getDeletedItems().get( count ).equals( BaseODSEntity.C_INDICATOR_NO ) )
        	{
        		mrDocCallbackMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
        	}
        	else if ( fncVO.getSelectedItemsInGrid().get( count ).equals( BaseODSEntity.C_INDICATOR_NO )
        			&& fncVO.getDeletedItems().get( count ).equals( BaseODSEntity.C_INDICATOR_YES ) )
        	{
        		mrDocCallbackMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );
        	}
        	// Insere relacionamento na tabela TPL_MR_DOC_CALLBACK_MOV 
        	mrDocCallbackMovDAO.insert( mrDocCallbackMovEntity );        	
        }
        count++;
      }
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
    TplMrDocCallbackMovEntity mrDocCallbackMovEntity;
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO;

    validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

      Date date = new Date();
      TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
      mrDocPrvtEntityVO.setLastUpdDate( date );
      mrDocPrvtEntityVO.setLastUpdUserId( fncVO.getLoggedUser().getUserID() );
      mrDocPrvtEntityVO.setRecStatCode( BaseODSEntity.C_REC_STAT_CODE_ACTIVE);

      // Monta entidade com os valores a serem inseridos na tabela
      // TPL_MR_DOC_PRVT_MOV
      TplMrDocPrvtMovEntity mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();
      mrDocPrvtMovEntityVO.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
      mrDocPrvtMovEntityVO.setMrDocText( mrDocPrvtEntityVO.getMrDocText() );
      mrDocPrvtMovEntityVO.setMrInvstCurAcctInd( mrDocPrvtEntityVO.getMrInvstCurAcctInd() );
      mrDocPrvtMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
      mrDocPrvtMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
      mrDocPrvtMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );
      mrDocPrvtMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
      mrDocPrvtMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

      // Insere na tabela TPL_MR_DOC_PRVT_MOV
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();
      mrDocPrvtMovDAO.insert( mrDocPrvtMovEntity );

      // Exclui logicamente os contatos associados ao MR deletado
      ContactCustKey contactCustKey = null;
      HashMap contactCustEntities = ( HashMap ) fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities();
      if ( contactCustEntities.size() > 0 )
      {
        Iterator contactCustEntitiesIt = contactCustEntities.keySet().iterator();

        while ( contactCustEntitiesIt.hasNext() )
        {
          contactCustKey = ( ContactCustKey ) contactCustEntitiesIt.next();

          // Monta entidade com os valores a serem inseridos na tabela
          // TPL_MR_DOC_CALLBACK_MOV
          mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
          mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData();
          mrDocCallbackMovEntityVO.setMrDocPrvt( mrDocPrvtEntityVO.getMrDocPrvt() );
          mrDocCallbackMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
          mrDocCallbackMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );
          mrDocCallbackMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
          mrDocCallbackMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
          mrDocCallbackMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );
          mrDocCallbackMovEntityVO.setCustNbr( contactCustKey.getCustNbr() );
          mrDocCallbackMovEntityVO.setCtcNbr( contactCustKey.getCtcNbr() );
          mrDocCallbackMovEntityVO.setMrCallbackCode( new BigInteger( "1" ) );

          // Insere relacionamento na tabela TPL_MR_DOC_CALLBACK_MOV
          TplMrDocCallbackMovDAO mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();
          mrDocCallbackMovDAO.insert( mrDocCallbackMovEntity );
        }
      }
    }
  }
  
  public void PrepareDelete( BaseFncVO fncVO_,TplMrDocPrvtEntity entity_ )
	{
	  TplMrDocCallbackMovEntity mrDocCallbackMovEntity;
	  TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO;

	  //validateDelete( fncVO_ );
	  if ( !fncVO_.hasErrors() )
	  {
		MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
		ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();

		Date date = new Date();
		TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) entity_.getData();
		mrDocPrvtEntityVO.setLastUpdDate( date );
		mrDocPrvtEntityVO.setLastUpdUserId( fncVO.getLoggedUser().getUserID() );
		mrDocPrvtEntityVO.setRecStatCode( "A" );

		// Monta entidade com os valores a serem inseridos na tabela
		// TPL_MR_DOC_PRVT_MOV
		TplMrDocPrvtMovEntity mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
		TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();
		mrDocPrvtMovEntityVO.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
		mrDocPrvtMovEntityVO.setMrDocText( mrDocPrvtEntityVO.getMrDocText() );
		mrDocPrvtMovEntityVO.setMrInvstCurAcctInd( mrDocPrvtEntityVO.getMrInvstCurAcctInd() );
		mrDocPrvtMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
		mrDocPrvtMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
		mrDocPrvtMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );
		mrDocPrvtMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
		mrDocPrvtMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

		// Insere na tabela TPL_MR_DOC_PRVT_MOV
		TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();
		mrDocPrvtMovDAO.insert( mrDocPrvtMovEntity );

		// Exclui logicamente os contatos associados ao MR deletado
		ContactCustKey contactCustKey = null;
		HashMap contactCustEntities = ( HashMap ) entity_.getTplContactCustEntities();
		if ( contactCustEntities.size() > 0 )
		{
		  Iterator contactCustEntitiesIt = contactCustEntities.keySet().iterator();

		  while ( contactCustEntitiesIt.hasNext() )
		  {
			contactCustKey = ( ContactCustKey ) contactCustEntitiesIt.next();

			// Monta entidade com os valores a serem inseridos na tabela
			// TPL_MR_DOC_CALLBACK_MOV
			mrDocCallbackMovEntity = new TplMrDocCallbackMovEntity();
			mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity.getData();
			mrDocCallbackMovEntityVO.setMrDocPrvt( mrDocPrvtEntityVO.getMrDocPrvt() );
			mrDocCallbackMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
			mrDocCallbackMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );
			mrDocCallbackMovEntityVO.setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
			mrDocCallbackMovEntityVO.setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
			mrDocCallbackMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );
			mrDocCallbackMovEntityVO.setCustNbr( contactCustKey.getCustNbr() );
			mrDocCallbackMovEntityVO.setCtcNbr( contactCustKey.getCtcNbr() );
			mrDocCallbackMovEntityVO.setMrCallbackCode( new BigInteger( "1" ) );

			// Insere relacionamento na tabela TPL_MR_DOC_CALLBACK_MOV
			TplMrDocCallbackMovDAO mrDocCallbackMovDAO = odsDAOFactory.getTplMrDocCallbackMovDAO();
			mrDocCallbackMovDAO.insert( mrDocCallbackMovEntity );
		  }
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
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
	fncVO.setMrActive(false);

    //TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

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
    
    
    /*if ( mrDocPrvtEntityVO.getMrDocPrvt() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }*/

    /*if ( mrDocPrvtEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }*/

    /*if ( fncVO.getCtcNbr() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_CTC_NBR_NOT_NULL );
    }*/

    // Verifica se o contato está cadastrado na base (deve estar cadastrado)
    /*if ( !fncVO.hasErrors() )
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
    }*/

    // Verifica se o contato está associado ao cliente (não deve estar
    // associado)
    /*if ( !fncVO.hasErrors() )
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
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
    if ( mrDocPrvtEntityVO.getMrDocPrvt() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdUnderAcctCode() == null )
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
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar campos obrigatórios
    /*if ( mrDocPrvtEntityVO.getMrDocPrvt() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }*/

    if ( mrDocPrvtEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }

    // Verifica se MR já está cadastrado e ativo
    /*if ( !fncVO_.hasErrors() )
    {
      TplMrDocPrvtDAO mrDocPrvtDAO = ( TplMrDocPrvtDAO ) getDAO();
      if ( mrDocPrvtDAO.existsActive( fncVO.getTplMrDocPrvtEntity() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_MR_DUPLICATE );
      }
    }*/
    //Verifica se existe items selecionados para exclusão se existir verifica
    // se o tamanho da lista de ítens exclúidos e do mesmo tamanho da lista
    // final
    if ( !fncVO_.hasErrors() )
    {
      int findItemsDeleted = 0;
      for ( int i = 0; i < fncVO.getDeletedItems().size(); i++ )
      {
        if ( fncVO.getDeletedItems().get( i ).equals( BaseODSEntity.C_INDICATOR_YES ) )
        {
          findItemsDeleted++;

        }
      }

      if ( findItemsDeleted == fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().size() )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                         "inserido", "Callback" );
      }
    }
    // Verifica se existe a chave em movimento
    if ( !fncVO_.hasErrors() )
    {
      // Monta TplMrDocPrvtMovEntity com as PK para fazer a verificação
      TplMrDocPrvtMovEntity mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();

      //Popula o entityVO com as chaves da tabel de Memo de
      // Risco(PL.TPL_MR_PRVT)
      //mrDocPrvtMovEntityVO.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
      mrDocPrvtMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
      mrDocPrvtMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );
      //Cria uma instância do ODSDAOFactory para acessar o DAO de Memo de Risco
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();
      //Verifica se existe registro com a mesma primary key na tabela de
      // movimento.
      if ( mrDocPrvtMovDAO.existsMovement( mrDocPrvtMovEntity ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
    if ( mrDocPrvtEntityVO.getMrDocPrvt() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }

    // Verifica se não está cadastrado ou não está ativo
    if ( !fncVO_.hasErrors() )
    {
      TplMrDocPrvtDAO mrDocPrvtDAO = ( TplMrDocPrvtDAO ) getDAO();
      if ( !mrDocPrvtDAO.existsActive( fncVO.getTplMrDocPrvtEntity() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_PK_NOT_FOUND );
      }
    }

    //Verifica se existe items selecionados para exclusão se existir verifica
    // se o tamanho da lista de ítens exclúidos e do mesmo tamanho da lista
    // final
    if ( !fncVO_.hasErrors() )
    {
      int findItemsDeleted = 0;
      for ( int i = 0; i < fncVO.getDeletedItems().size(); i++ )
      {
        if ( fncVO.getDeletedItems().get( i ).equals( BaseODSEntity.C_INDICATOR_YES ) )
        {
          findItemsDeleted++;

        }
      }

      if ( findItemsDeleted == fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities().size() )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                         "inserido", "Callback" );
      }
    }

    // Verifica se existe a chave em movimento
    if ( !fncVO_.hasErrors() )
    {
      // Monta TplMrDocPrvtMovEntity com as PK para fazer a verificação
      TplMrDocPrvtMovEntity mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();

      //Popula o EntityVO com as chaves da tabela de Memo de Risco
      // (PL.TPL_MR_PRVT)
      mrDocPrvtMovEntityVO.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
      mrDocPrvtMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
      mrDocPrvtMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

      //Cria uma instância do ODSDAOFactory para acessar o DAO de Memo de Risco
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();

      //Verifica se existe registro com a mesma primary key na tabela de
      // movimento.
      if ( mrDocPrvtMovDAO.existsMovement( mrDocPrvtMovEntity ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();

    // Validar Campos Obrigatórios
    if ( mrDocPrvtEntityVO.getMrDocPrvt() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_MR_DOC_PRVT_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_ACCT_CODE_NOT_NULL );
    }

    if ( mrDocPrvtEntityVO.getProdUnderAcctCode() == null )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PROC_UNDER_ACCT_CODE_NOT_NULL );
    }

    // Verifica se não está cadastrado ou não está ativo
    if ( !fncVO_.hasErrors() )
    {
      TplMrDocPrvtDAO mrDocPrvtDAO = ( TplMrDocPrvtDAO ) getDAO();
      if ( !mrDocPrvtDAO.existsById( fncVO.getTplMrDocPrvtEntity() ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_PK_NOT_FOUND );
      }
    }

    // Verifica se existe a chave em movimento
    if ( !fncVO_.hasErrors() )
    {
      // Monta TplMrDocPrvtMovEntity com as PK para fazer a verificação
      TplMrDocPrvtMovEntity mrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
      TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity.getData();
      //Popula o EntityVO com as chaves da tabela de Memo de Risco
      // (PL.TPL_MR_PRVT)
      mrDocPrvtMovEntityVO.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
      mrDocPrvtMovEntityVO.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
      mrDocPrvtMovEntityVO.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

      //Cria uma instância do ODSDAOFactory para acessar o DAO de Memo de Risco
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();

      //Verifica se existe registro com a mesma primary key na tabela de
      // movimento.
      if ( mrDocPrvtMovDAO.existsMovement( mrDocPrvtMovEntity ) )
      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
	m_isInsertDeleteContactCustAction = false;
    loadForDetail( fncVO_ );
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;
    
	if(fncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr() == null && 
	   fncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr().equals("")){
		
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
						 "Conta Corrente ou Conta CCI");
	}
	
	if(!fncVO.hasErrors()){
		if (!fncVO.isInsertContact() && !fncVO.isMrActive() ){
				fncVO.resetInsertedContactCust();
				fncVO.resetDeletedContactCust();
				fncVO.setTplMrDocPrvtEntity( new TplMrDocPrvtEntity() );
				fncVO.setTplContactCustEntity(new TplContactCustEntity());		
				fncVO.setDeletedItems( new ArrayList() );
				fncVO.setSelectedItemsInGrid( new ArrayList() );				
		
				ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
				To3ProductAccountDAO productAccountDAO = odsDAOFactory.getTo3ProductAccountDAO();
				BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();		
				
				productAccountEntity.getData().setCurAcctNbr(fncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr());
			    productAccountEntity.getData().setInvestCurAcctNbr((fncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr()));
				
			    try{
                    //Recupera achave da conta produto através da conta corrente ou conta CCI.
					productAccountEntity = productAccountDAO.findByCurAcct(productAccountEntity);
				}
				catch( NoRowsReturnedException exception ){
					productAccountEntity = null;
				}
				
				if(productAccountEntity == null){
					fncVO_.addError(BaseODSFncVO.C_ERROR_REG_NOT_EXISTS,"Conta Corrente ou Conta CCI");					  
				}
				
			    if(!fncVO.hasErrors()){
					fncVO.getTplMrDocPrvtEntity().getData().setProdAcctCode(productAccountEntity.getData().getProdAcctCode());
					fncVO.getTplMrDocPrvtEntity().getData().setProdUnderAcctCode(productAccountEntity.getData().getProdUnderAcctCode());
		
					//Recupera os dados relacionados a Conta de Produto.
					productAccountEntity.getData().setProdAcctCode(fncVO.getTplMrDocPrvtEntity().getData().getProdAcctCode() );
					productAccountEntity.getData().setProdUnderAcctCode(fncVO.getTplMrDocPrvtEntity().getData().getProdUnderAcctCode() );
					productAccountEntity = productAccountDAO.findByPK( productAccountEntity );
					fncVO.setTo3ProductAccountEntity( productAccountEntity );
					super.load( fncVO_ );
			    }			
		
			}
			else{			
				//Limpa os dados do callback	     	
				fncVO.getTplContactCustEntity().getData().setFullNameText(null);
				fncVO.getTplContactCustEntity().getData().setFullNameText_2(null);
				fncVO.getTplContactCustEntity().getData().setFullNameText_3(null);
				fncVO.getTplContactCustEntity().getData().setPhoneNbr(null);
				fncVO.getTplContactCustEntity().getData().setPhoneDddCode(null);
				fncVO.getTplContactCustEntity().getData().setPhoneDddCode(null);
				fncVO.getTplContactCustEntity().getData().setPhoneExtnNbr(null);
				fncVO.setInsertContact(false);	
			}
		
			
			
		
	}
	
	
    // Carrega a tela pela primeira vez
    /*if ( !fncVO.isFromSearch() && !m_isInsertDeleteContactCustAction
         && !fncVO.getFromCurAccount().equals( C_FROM_SEARCH ) )
    {
      // Reseta os campos da tela
      fncVO.setCtcNbr( null );
      fncVO.setFullNameText( "" );
      fncVO.setCustFullNameText( "" );
      fncVO.setAssociatedCtcNbr( null );
      fncVO.resetInsertedContactCust();
      fncVO.resetDeletedContactCust();
      fncVO.setTplMrDocPrvtEntity( new TplMrDocPrvtEntity() );
      fncVO.setTo3ProductAccountEntity( new To3ProductAccountEntity() );
      fncVO.setDeletedItems( new ArrayList() );
      fncVO.setSelectedItemsInGrid( new ArrayList() );

      // Seta o Código Documento MR
      TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
      TplMrDocPrvtDAO mrDocPrvtDAO = ( TplMrDocPrvtDAO ) getDAO();
      mrDocPrvtEntityVO.setMrDocPrvt( new BigInteger(
                                                      mrDocPrvtDAO.getNextMrDocCode().toString() ) );
    }
    // Carrega a tela voltando do search
    else if ( fncVO.isFromSearch() )
    {
      fncVO.setFromSearch( false );

      if ( fncVO.getTplMrDocPrvtEntity().getData().getProdAcctCode() != null
           && fncVO.getTplMrDocPrvtEntity().getData().getProdUnderAcctCode() != null )
      {
        // Recupera os dados relacionados a Conta de Produto.
        ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
        BaseTo3ProductAccountDAO productAccountDAO = odsDAOFactory.getTo3ProductAccountDAO();
        BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();
        productAccountEntity.getData().setProdAcctCode(
                                                        fncVO.getTplMrDocPrvtEntity().getData().getProdAcctCode() );
        productAccountEntity.getData().setProdUnderAcctCode(
                                                             fncVO.getTplMrDocPrvtEntity().getData().getProdUnderAcctCode() );
        productAccountEntity = productAccountDAO.find( productAccountEntity );
        fncVO.setTo3ProductAccountEntity( productAccountEntity );
      }

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
    loadDomains( fncVO );
    super.load( fncVO_ );
    m_isInsertDeleteContactCustAction = false;*/
    
    
    
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {

    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    // Verifica se MR não existe em movimento
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
    TplMrDocPrvtMovEntity tplMrDocPrvtMovEntity = new TplMrDocPrvtMovEntity();
    ( ( TplMrDocPrvtMovEntityVO ) tplMrDocPrvtMovEntity.getData() ).setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
    tplMrDocPrvtMovEntity.getData().setProdAcctCode(
                                                     mrDocPrvtEntityVO.getProdAcctCode() );
    tplMrDocPrvtMovEntity.getData().setProdUnderAcctCode(
                                                          mrDocPrvtEntityVO.getProdUnderAcctCode() );

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();

    //Verifica se o resgitro existe em movimento
    if ( mrDocPrvtMovDAO.existsMovement( tplMrDocPrvtMovEntity ) )
    {
      fncVO.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
    }

    // Carrega a tela pela primeira vez
    if ( !fncVO.isFromSearch() && !m_isInsertDeleteContactCustAction )
    {
      loadForDetail( fncVO_ );

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
        BaseTplContactCustDAO contactCustDAO = odsDAOFactory.getTplContactCustDAO();
        contactCustEntity = contactCustDAO.find( contactCustEntity );
        fncVO.setFullNameText( contactCustEntity.getData().getFullNameText() );

        loadDomains( fncVO );
        m_isInsertDeleteContactCustAction = false;
      }
    } else {
    	m_isInsertDeleteContactCustAction = false;
    }
  }

  /**
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

    // Verifica se MR não existe em movimento
    // TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO
    // ) fncVO.getTplMrDocPrvtEntity().getData();

    TplMrDocPrvtMovEntity mrDocPrvtEntity = new TplMrDocPrvtMovEntity();
    TplMrDocPrvtMovEntityVO tplMrDocPrvtEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtEntity.getData();

    tplMrDocPrvtEntityVO.setMrDocCode( ( ( TplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData() ).getMrDocPrvt() );
    tplMrDocPrvtEntityVO.setProdAcctCode( fncVO.getTplMrDocPrvtEntity().getData().getProdAcctCode() );
    tplMrDocPrvtEntityVO.setProdUnderAcctCode( fncVO.getTplMrDocPrvtEntity().getData().getProdUnderAcctCode() );

    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplMrDocPrvtMovDAO mrDocPrvtMovDAO = odsDAOFactory.getTplMrDocPrvtMovDAO();

    if ( mrDocPrvtEntity.getData().getProdAcctCode() != null
         && mrDocPrvtEntity.getData().getProdUnderAcctCode() != null )
    {
      if ( mrDocPrvtMovDAO.existsMovement( mrDocPrvtEntity ) )
      {
        fncVO.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
      }
    }

    loadForDetail( fncVO_ );
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

  /**
   * Carregamento dos dados para ações: Consulta e Atualização.
   * 
   * @param fncVO_ - Objeto com os dados utilizados para o carregamento.
   */
  private void loadForDetail( BaseFncVO fncVO_ )
  {
    MrDocPrvtDetailFncVO fncVO = ( MrDocPrvtDetailFncVO ) fncVO_;

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

      super.load( fncVO_ );
    }

    loadDomains( fncVO );
    m_isInsertDeleteContactCustAction = false;
  }
}