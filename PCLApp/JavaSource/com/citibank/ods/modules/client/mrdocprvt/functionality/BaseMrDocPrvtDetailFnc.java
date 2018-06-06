package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplMrDocPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;
import com.citibank.ods.modules.client.mrdocprvt.form.BaseMrDocPrvtDetailForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.BaseMrDocPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de Detalhe da tabela de Memória de Risco.
 */
public abstract class BaseMrDocPrvtDetailFnc extends BaseFnc
{
  protected static final String C_MR_DOC_PRVT_NOT_NULL = "Código do Documento MR";

  protected static final String C_PROC_ACCT_CODE_NOT_NULL = "Conta Corrente";

  protected static final String C_PROC_UNDER_ACCT_CODE_NOT_NULL = "Conta Corrente";

  protected static final String C_CTC_NBR_NOT_NULL = "Número do Contato";

  protected static final String C_MR_INVST_CUR_ACCT_NOT_NULL = "Indicador de Conta CCI";
  
  protected static final String C_PHONE_DDDCODE_NOT_NULL = "DDD";
  
  protected static final String C_PHONE_NBR_NOT_NULL = "Telefone";
  
  protected static final String C_FULL_NAME_TEXT_NOT_NULL = "Nome do Contato Principal";

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseMrDocPrvtDetailForm form = ( BaseMrDocPrvtDetailForm ) form_;
    BaseMrDocPrvtDetailFncVO fncVO = ( BaseMrDocPrvtDetailFncVO ) fncVO_;
    
	String[] codeArray = null;
	if(form.getSelectedCode()!= null && !form.getSelectedCode().equals("")){
	  codeArray = form.getSelectedCode().split(","); 	
	}

    // Descrição do documento MR do detalhe (Inserção/Atualização)
    String mrDocText = ( form.getMrDocText() != null
                         && form.getMrDocText().length() > 0
                                                            ? form.getMrDocText()
                                                            : null );

    // Seta o indicador de utilização de conta do detalhe (Inserção/Atualização)
    String mrInvstCurAcctInd = ( form.getMrInvstCurAcctInd() != null
                                 && form.getMrInvstCurAcctInd().length() > 0
                                                                            ? form.getMrInvstCurAcctInd()
                                                                            : null );

    // Código da Conta Produto selecionada no grid de consulta em lista
    // (Detalhe/Atualização) ou na busca do detalhe (Inserção/Atualização)
    BigInteger prodAcctCode = ( form.getProdAcctCode() != null
                                && form.getProdAcctCode().length() > 0
                                                                      ? new BigInteger(
                                                                                        form.getProdAcctCode() )
                                                                      : null );
                                                                      
    if(codeArray!= null){
		prodAcctCode = new BigInteger(codeArray[1]);    	                                                                  
    }

    // Código da Sub-conta Produto selecionada no grid de consulta em lista
    // (Detalhe/Atualização) ou na busca do detalhe (Inserção/Atualização)
    BigInteger prodUnderAcctCode = ( form.getProdUnderAcctCode() != null
                                     && form.getProdUnderAcctCode().length() > 0
                                                                                ? new BigInteger(
                                                                                                  form.getProdUnderAcctCode() )
                                                                                : null );
                                                                                
	if(codeArray!= null){
		prodUnderAcctCode = new BigInteger(codeArray[2]);    	                                                                  
	}                                                                                

    // Seta o número do contato da busca do detalhe
    BigInteger ctcNbr = ( form.getCtcNbrSrc() != null
                          && form.getCtcNbrSrc().length() > 0
                                                             ? new BigInteger(
                                                                               form.getCtcNbrSrc() )
                                                             : null );

    // Seta o número do contato selecionado do grid do detalhe
    BigInteger associatedCtcNbr = ( form.getAssociatedCtcNbr() != null
                                    && form.getAssociatedCtcNbr().length() > 0
                                                                              ? new BigInteger(
                                                                                                form.getAssociatedCtcNbr() )
                                                                              : null );
                                                                              
	BigInteger phoneDddCode = ( form.getPhoneDddCode() != null
										&& form.getPhoneDddCode().length() > 0
																				  ? new BigInteger(
																									form.getPhoneDddCode() )
																				  : null );
	BigInteger phoneNbr = ( form.getPhoneNbr() != null
											&& form.getPhoneNbr().length() > 0
																					  ? new BigInteger(
																										form.getPhoneNbr() )
																					  : null );
																					  
	BigInteger phoneExtnNbr = ( form.getPhoneExtnNbr() != null
												&& form.getPhoneExtnNbr().length() > 0
																						  ? new BigInteger(
																											form.getPhoneExtnNbr() )
																						  : null );
   
	String fullNameText = ( form.getFullNameText() != null
									 && form.getFullNameText().length() > 0
																				? form.getFullNameText()
																				: null );
																				
	String fullNameText_2 = ( form.getFullNameText_2() != null
										 && form.getFullNameText_2().length() > 0 ? form.getFullNameText_2()
																					: null );
	
	String fullNameText_3 = ( form.getFullNameText_3() != null
										 && form.getFullNameText_3().length() > 0 ? form.getFullNameText_3()
																					: null );
																					
																						  																				  
																					  																				  
    																				  
    fncVO.getTplMrDocPrvtEntity().getData().setMrDocText( mrDocText );
    fncVO.getTplMrDocPrvtEntity().getData().setProdAcctCode( prodAcctCode );
    fncVO.getTplMrDocPrvtEntity().getData().setProdUnderAcctCode(
                                                                  prodUnderAcctCode );
    fncVO.getTplMrDocPrvtEntity().getData().setMrInvstCurAcctInd(
                                                                  mrInvstCurAcctInd );
                                                                  
	fncVO.getTplContactCustEntity().getData().setPhoneDddCode(phoneDddCode);
	fncVO.getTplContactCustEntity().getData().setPhoneNbr(phoneNbr);
	fncVO.getTplContactCustEntity().getData().setPhoneExtnNbr(phoneExtnNbr);
	fncVO.getTplContactCustEntity().getData().setFullNameText(fullNameText);
	fncVO.getTplContactCustEntity().getData().setFullNameText_2(fullNameText_2);
	fncVO.getTplContactCustEntity().getData().setFullNameText_3(fullNameText_3);
	
	fncVO.getTo3ProductAccountEntity().getData().setCurAcctNbr(( form.getCurAcctNbrSrc() != null
														&& form.getCurAcctNbrSrc().length() > 0
											 			 ? new BigInteger(
															form.getCurAcctNbrSrc() )
											  				: null )
											  				);
											  				
	fncVO.getTo3ProductAccountEntity().getData().setInvestCurAcctNbr(( form.getInvstCurAcctNbrSrc() != null	? form.getInvstCurAcctNbrSrc(): null ));											  				
                                                                      
    fncVO.setCtcNbr( ctcNbr );
    fncVO.setAssociatedCtcNbr( associatedCtcNbr );
	fncVO.setIsConfirmCancel(form.getIsConfirmCancel());
	fncVO.setMrActive(form.getIsMrActive());

    if ( !fncVO.isFromSearch() )
    {
      if ( form.getDeletedItems() != null )
      {
        fncVO.setDeletedItems( new ArrayList() );
        for ( int j = 0; j < form.getDeletedItems().length; j++ )
        {
          fncVO.getDeletedItems().add( form.getDeletedItems()[ j ] );
        }
      }
      else
      {
        fncVO.setDeletedItems( new ArrayList() );
      }

      if ( form.getSelectedItemsInGrid() != null )
      {
        fncVO.setSelectedItemsInGrid( new ArrayList() );
        for ( int j = 0; j < form.getSelectedItemsInGrid().length; j++ )
        {
          fncVO.getSelectedItemsInGrid().add( form.getSelectedItemsInGrid()[ j ] );
        }
      }
      else
      {
        fncVO.setSelectedItemsInGrid( new ArrayList() );
      }
    }
    //Seta os dados no FncVO
    //fncVO.setInsertContact( false );
    fncVO.setClickedSearch( "" );
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseMrDocPrvtDetailForm form = ( BaseMrDocPrvtDetailForm ) form_;
    BaseMrDocPrvtDetailFncVO fncVO = ( BaseMrDocPrvtDetailFncVO ) fncVO_;

    BaseTplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( BaseTplMrDocPrvtEntityVO ) fncVO.getTplMrDocPrvtEntity().getData();
    To3ProductAccountEntityVO productAccountEntityVO = ( To3ProductAccountEntityVO ) fncVO.getTo3ProductAccountEntity().getData();

    String curAcctNbr = ( productAccountEntityVO.getCurAcctNbr() != null
                                                                        ? productAccountEntityVO.getCurAcctNbr().toString()
                                                                        : null );
                                                                        
    String invstCurAcctNbrSrc = ( productAccountEntityVO.getInvestCurAcctNbr() != null
											? productAccountEntityVO.getInvestCurAcctNbr() : null );                                                                         

    String prodAcctCode = ( mrDocPrvtEntityVO.getProdAcctCode() != null
                                                                       ? mrDocPrvtEntityVO.getProdAcctCode().toString()
                                                                       : null );

    String prodUnderAcctCode = ( mrDocPrvtEntityVO.getProdUnderAcctCode() != null
                                                                                 ? mrDocPrvtEntityVO.getProdUnderAcctCode().toString()
                                                                                 : null );

    String mrDocText = ( mrDocPrvtEntityVO.getMrDocText() != null
                                                                 ? mrDocPrvtEntityVO.getMrDocText()
                                                                 : null );

    String ctcNbr = ( fncVO.getCtcNbr() != null ? fncVO.getCtcNbr().toString()
                                               : null );

    /*String fullNameText = ( fncVO.getFullNameText() != null
                                                           ? fncVO.getFullNameText()
                                                           : null );*/

    String mrInvstCurAcctInd = ( mrDocPrvtEntityVO.getMrInvstCurAcctInd() != null
                                                                                 ? mrDocPrvtEntityVO.getMrInvstCurAcctInd()
                                                                                 : null );

    String lasdUpdDate = ( mrDocPrvtEntityVO.getLastUpdDate() != null
                                                                     ? formatDateTime( mrDocPrvtEntityVO.getLastUpdDate() )
                                                                     : null );

    String lasdUpdUserId = ( mrDocPrvtEntityVO.getLastUpdUserId() != null
                                                                         ? mrDocPrvtEntityVO.getLastUpdUserId()
                                                                         : null );
    String custNbr = ( productAccountEntityVO.getCustNbr() != null
                                                                  ? productAccountEntityVO.getCustNbr().toString()
                                                                  : null );

    String custFullNameText = ( fncVO.getCustFullNameText() != null
                                                                   ? fncVO.getCustFullNameText()
                                                                   : null );

    form.setCurAcctNbrSrc( curAcctNbr );
    form.setInvstCurAcctNbrSrc(invstCurAcctNbrSrc);
    form.setProdAcctCode( prodAcctCode );
    form.setProdUnderAcctCode( prodUnderAcctCode );
    form.setMrDocText( mrDocText );
    form.setCtcNbrSrc( ctcNbr );
    form.setFullNameText( fncVO.getTplContactCustEntity().getData().getFullNameText() );
	form.setFullNameText_2( fncVO.getTplContactCustEntity().getData().getFullNameText_2() );
	form.setFullNameText_3( fncVO.getTplContactCustEntity().getData().getFullNameText_3() );
    form.setMrInvstCurAcctInd( mrInvstCurAcctInd );
    form.setMrInvstCurAcctIndDomain( fncVO.getMrInvstCurAcctIndDomain() );
    form.setClickedSearch( fncVO.getClickedSearch() );
    form.setLastUpdDate( lasdUpdDate );
    form.setLastUpdUserId( lasdUpdUserId );
    form.setCustNbrSrc( custNbr );
    form.setCustFullNameText( custFullNameText );
	
		
	form.setPhoneNbr(( fncVO.getTplContactCustEntity().getData().getPhoneNbr() != null	? fncVO.getTplContactCustEntity().getData().getPhoneNbr().toString()
																								: null ));
	form.setPhoneDddCode(( fncVO.getTplContactCustEntity().getData().getPhoneDddCode() != null	? fncVO.getTplContactCustEntity().getData().getPhoneDddCode().toString()
	                                                          : null ));
	
	form.setPhoneExtnNbr(( fncVO.getTplContactCustEntity().getData().getPhoneExtnNbr() != null	? fncVO.getTplContactCustEntity().getData().getPhoneExtnNbr().toString()
											: null ));

    ContactCustKey contactCustEntityKey = null;
    BaseTplContactCustEntity contactCustEntity = null;

    ArrayList ctcNbrList = new ArrayList();
    ArrayList fullNameTextList = new ArrayList();
	ArrayList fullName_2_TextList = new ArrayList();
	ArrayList fullName_3_TextList = new ArrayList();
    ArrayList phoneDDDList = new ArrayList();
    ArrayList phoneNbrList = new ArrayList();
    ArrayList phoneExtNbrList = new ArrayList();

    HashMap contactCustEntities = ( HashMap ) fncVO.getTplMrDocPrvtEntity().getTplContactCustEntities();

    if ( contactCustEntities.size() > 0 )
    {
      Iterator contactCustEntitiesIt = contactCustEntities.keySet().iterator();

      while ( contactCustEntitiesIt.hasNext() )
      {
        contactCustEntityKey = ( ContactCustKey ) contactCustEntitiesIt.next();
        contactCustEntity = ( BaseTplContactCustEntity ) contactCustEntities.get( contactCustEntityKey );

        if ( contactCustEntity.getData().getFullNameText() != null )
        {
          fullNameTextList.add( contactCustEntity.getData().getFullNameText() );
        }
        else
        {
          fullNameTextList.add( "" );
        }
        
        
		if ( contactCustEntity.getData().getFullNameText_2() != null )
		{
			fullName_2_TextList.add( contactCustEntity.getData().getFullNameText_2() );
		}
		else
		{
			fullName_2_TextList.add( "" );
		}
		
		if ( contactCustEntity.getData().getFullNameText_3() != null )
		{
			fullName_3_TextList.add( contactCustEntity.getData().getFullNameText_3() );
		}
		else
		{
			fullName_3_TextList.add( "" );
		}
        
        
        if ( contactCustEntity.getData().getPhoneDddCode() != null )
        {

          phoneDDDList.add( contactCustEntity.getData().getPhoneDddCode() );
        }
        else
        {
          phoneDDDList.add( "" );
        }
        if ( contactCustEntity.getData().getPhoneNbr() != null )
        {
          phoneNbrList.add( contactCustEntity.getData().getPhoneNbr() );
        }
        else
        {
          phoneNbrList.add( "" );
        }
        if ( contactCustEntity.getData().getPhoneExtnNbr() != null )
        {
          phoneExtNbrList.add( contactCustEntity.getData().getPhoneExtnNbr() );
        }

        else
        {
          phoneExtNbrList.add( "" );
        }

        ctcNbrList.add( contactCustEntity.getData().getCtcNbr().toString() );
      }
    }

    String[] ctcNbrArray = new String[ ctcNbrList.size() ];
    for ( int i = 0; i < ctcNbrList.size(); i++ )
    {
      ctcNbrArray[ i ] = ( String ) ctcNbrList.get( i );
    }

    String[] fullNameTextArray = new String[ fullNameTextList.size() ];
    for ( int i = 0; i < fullNameTextList.size(); i++ )
    {
      fullNameTextArray[ i ] = ( String ) fullNameTextList.get( i );
    }
    
	String[] fullName_2_TextArray = new String[ fullName_2_TextList.size() ];
	for ( int i = 0; i < fullName_2_TextList.size(); i++ )
	{
	  fullName_2_TextArray[ i ] = ( String ) fullName_2_TextList.get( i );
	}
	
	String[] fullName_3_TextArray = new String[ fullName_3_TextList.size() ];
	for ( int i = 0; i < fullName_3_TextList.size(); i++ )
	{
	  fullName_3_TextArray[ i ] = ( String ) fullName_3_TextList.get( i );
	}

    String[] phoneNbrArray = new String[ phoneNbrList.size() ];
    for ( int i = 0; i < phoneNbrList.size(); i++ )
    {
      phoneNbrArray[ i ] = ( String ) phoneNbrList.get( i ).toString();
    }

    String[] phoneExtNbrArray = new String[ phoneExtNbrList.size() ];
    for ( int i = 0; i < phoneExtNbrList.size(); i++ )
    {
      phoneExtNbrArray[ i ] = ( String ) phoneExtNbrList.get( i ).toString();
    }

    String[] phoneDddCodeArray = new String[ phoneDDDList.size() ];
    for ( int i = 0; i < phoneDDDList.size(); i++ )
    {
      phoneDddCodeArray[ i ] = ( String ) phoneDDDList.get( i ).toString();
    }

    form.setCtcNbrArray( ctcNbrArray );
    form.setFullNameTextArray( fullNameTextArray );
    form.setPhoneDddCodeArray( phoneDddCodeArray );
    form.setPhoneExtNbrArray( phoneExtNbrArray );
    form.setPhoneNbrArray( phoneNbrArray );
    form.setFullName_2_TextArray(fullName_2_TextArray);
	form.setFullName_3_TextArray(fullName_3_TextArray);
	form.setMrActive(fncVO.isMrActive());
	form.setIsConfirmCancel(fncVO.isConfirmCancel());

 /*   if ( fncVO.getDeletedItems() != null && !fncVO.getDeletedItems().isEmpty() )
    {
      String[] deletedItemsInFom = new String[ fncVO.getDeletedItems().size() ];

      for ( int i = 0; i < fncVO.getDeletedItems().size(); i++ )
      {
        deletedItemsInFom[ i ] = ( String ) fncVO.getDeletedItems().get( i );
      }

      form.setDeletedItems( deletedItemsInFom );
    }
    else
    {
      String[] deletedItems = new String[ contactCustEntities.size() ];

      for ( int i = 0; i < contactCustEntities.size(); i++ )
      {
        deletedItems[ i ] = "N";
      }

      form.setDeletedItems( deletedItems );
    }*/

  }

  /**
   * Carrega os valores os combobox existentes na tela de detalhe.
   * 
   * @param fncVO_ - Objeto contendo o estado atual da aplicação
   */
  protected void loadDomains( BaseFncVO fncVO_ )
  {
    BaseMrDocPrvtDetailFncVO fncVO = ( BaseMrDocPrvtDetailFncVO ) fncVO_;
    fncVO.setMrInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /**
   * Recupera o BaseTplMrDocPrvtDAO para acessar o banco de dados da tabela de
   * memória de risco.
   * 
   * @return Retorna o BaseTplMrDocPrvtDAO para acessar o banco de dados da
   *         tabela de memória de risco.
   */
  protected abstract BaseTplMrDocPrvtDAO getDAO();

  /**
   * Converte uma data para o formato de apresentação.
   * 
   * @param date_ - A data a ser convertida.
   * @return String - A data no formato de apresentação.
   */
  private String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    return dateFormat.format( date_ );
  }

  public void load( BaseFncVO fncVO_ )
  {
    BaseMrDocPrvtDetailFncVO fncVO = ( BaseMrDocPrvtDetailFncVO ) fncVO_;
    BaseTplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();

    if ( fncVO.getTo3ProductAccountEntity().getData().getCustNbr() != null )
    {
      customerPrvtEntity.getData().setCustNbr(
                                               fncVO.getTo3ProductAccountEntity().getData().getCustNbr() );
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      BaseTplCustomerPrvtDAO customerPrvtDAO = odsDAOFactory.getTplCustomerPrvtDAO();
      customerPrvtEntity = customerPrvtDAO.find( customerPrvtEntity );
      fncVO.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );

    }
    else
    {
      fncVO.setFullNameText( "" );
    }

  }

}