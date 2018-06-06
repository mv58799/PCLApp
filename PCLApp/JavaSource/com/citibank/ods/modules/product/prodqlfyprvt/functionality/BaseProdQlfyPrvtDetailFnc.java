/*
 * Created on Mar 17, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplProdQlfyPrvtEntity;
import com.citibank.ods.modules.product.prodqlfyprvt.form.BaseProdQlfyPrvtDetailForm;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.BaseProdQlfyPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdQlfyPrvtDAO;

/**
 * @author fernando.salgado
 *  
 */
public abstract class BaseProdQlfyPrvtDetailFnc extends BaseFnc
{
  protected static final String C_PROD_QLFY_CODE = "Código da Qualificação";

  protected static final String C_PROD_QLFY_TEXT = "Descrição da Qualificação";

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdQlfyPrvtDetailFncVO baseProdQlfyPrvtDetailFncVO = ( BaseProdQlfyPrvtDetailFncVO ) fncVO_;
    BaseProdQlfyPrvtDetailForm baseProdQlfyPrvtDetailForm = ( BaseProdQlfyPrvtDetailForm ) form_;
    
	String[] codeArray = null;
	if(baseProdQlfyPrvtDetailForm.getSelectedCode()!= null && !baseProdQlfyPrvtDetailForm.getSelectedCode().equals("")){
	  codeArray = baseProdQlfyPrvtDetailForm.getSelectedCode().split(","); 	
	}
	
	if(codeArray!= null){
		baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setProdQlfyCode(new BigInteger(codeArray[0] ) );		
	}
    else if ( baseProdQlfyPrvtDetailForm.getProdQlfyCode() != null
         && !"".equals( baseProdQlfyPrvtDetailForm.getProdQlfyCode() ) )
    {
      baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setProdQlfyCode(
                                                                                            new BigInteger(
                                                                                                            baseProdQlfyPrvtDetailForm.getProdQlfyCode() ) );
    }
    else{
	  baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setProdQlfyCode(null);
    }
    
    baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().setProdQlfyText(
                                                                                          baseProdQlfyPrvtDetailForm.getProdQlfyText() );

  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdQlfyPrvtDetailFncVO baseProdQlfyPrvtDetailFncVO = ( BaseProdQlfyPrvtDetailFncVO ) fncVO_;
    BaseProdQlfyPrvtDetailForm baseProdQlfyPrvtDetailForm = ( BaseProdQlfyPrvtDetailForm ) form_;

    if ( baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyCode() != null )
    {
      baseProdQlfyPrvtDetailForm.setProdQlfyCode( baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyCode().toString() );
    }
    else{
      baseProdQlfyPrvtDetailForm.setProdQlfyCode(null);
    }

    //Setar Descrição
    baseProdQlfyPrvtDetailForm.setProdQlfyText( baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getProdQlfyText() );
    //Setar Usuário
    baseProdQlfyPrvtDetailForm.setLastUpdUserId( baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getLastUpdUserId() );

    //Formatar a data para o Formato DD/MM/AAAA - HH/MM
    SimpleDateFormat formatDate = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
    Date formDate = new Date();
    formDate = baseProdQlfyPrvtDetailFncVO.getBaseTplProdQlfyPrvtEntity().getData().getLastUpdDate();
    if ( formDate != null )
    {
      //Setar Data no formato correto
      String date = formatDate.format( formDate );
      baseProdQlfyPrvtDetailForm.setLastUpdDate( date );
    }
  }

  /**
   * Recupera um elemento de Qualificador de Produto, dado os critérios
   */
  public void loadProdQlfyPrvt( BaseProdQlfyPrvtDetailFncVO detailFncVO_ )
  {
    BaseTplProdQlfyPrvtDAO prodQlfyPrvtDAO = this.getDAO();
    BaseTplProdQlfyPrvtEntity tplProdQlfyPrvtEntity = ( BaseTplProdQlfyPrvtEntity ) prodQlfyPrvtDAO.find( detailFncVO_.getBaseTplProdQlfyPrvtEntity() );
    detailFncVO_.setBaseTplProdQlfyPrvtEntity( tplProdQlfyPrvtEntity );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */

  protected abstract BaseTplProdQlfyPrvtDAO getDAO();

}