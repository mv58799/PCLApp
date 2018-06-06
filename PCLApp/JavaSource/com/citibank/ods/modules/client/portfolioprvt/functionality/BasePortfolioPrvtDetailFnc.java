package com.citibank.ods.modules.client.portfolioprvt.functionality;

import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplPortfolioPrvtEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.modules.client.portfolioprvt.form.PortfolioPrvtDetailForm;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.BasePortfolioPrvtDetailFncVO;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.PortfolioPrvtDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplPortfolioPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.portfolioprvt.functionality;
 * @version 1.0
 * @author l.braga,31/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BasePortfolioPrvtDetailFnc extends BaseFnc
{

  /*
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    PortfolioPrvtDetailForm detailForm = ( PortfolioPrvtDetailForm ) form_;
    PortfolioPrvtDetailFncVO detailFncVO = ( PortfolioPrvtDetailFncVO ) fncVO_;

    String code = detailForm.getPortfCode();

    detailFncVO.getTplPortfolioPrvtEntity().getData().setPortfCode( code );

  }

  /*
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    PortfolioPrvtDetailForm detailForm = ( PortfolioPrvtDetailForm ) form_;
    PortfolioPrvtDetailFncVO detailFncVO = ( PortfolioPrvtDetailFncVO ) fncVO_;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    String portfBrchCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfBrchCode() != null
                                                                                                       ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfBrchCode().toString()
                                                                                                       : null;

    String portfCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCode() != null
                                                                                               ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCode()
                                                                                               : null;
    String portfCostBusGrpCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostBusGrpCode() != null
                                                                                                                   ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostBusGrpCode().toString()
                                                                                                                   : null;
    String portfCostDivCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostDivCode() != null
                                                                                                             ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostDivCode().toString()
                                                                                                             : null;
    String portfCostPrftyCtrCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostPrftyCtrCode() != null
                                                                                                                       ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostPrftyCtrCode().toString()
                                                                                                                       : null;
    String portfCostRegionCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostRegionCode() != null
                                                                                                                   ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostRegionCode().toString()
                                                                                                                   : null;
    String portfCostRespOffcrCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostRespOffcrCode() != null
                                                                                                                         ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCostRespOffcrCode().toString()
                                                                                                                         : null;
    String portfCservSuplCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCservSuplCode() != null
                                                                                                                 ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfCservSuplCode()
                                                                                                                 : null;
    String portfNameText = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfNameText() != null
                                                                                                       ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfNameText()
                                                                                                       : null;
    String portfNetwkSubGrpCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfNetwkSubGrpCode() != null
                                                                                                                     ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfNetwkSubGrpCode().toString()
                                                                                                                     : null;
    String portfNetwkSubNetwkGrpCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfNetwkSubNetwkGrpCode() != null
                                                                                                                               ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfNetwkSubNetwkGrpCode().toString()
                                                                                                                               : null;
    String portfOffcrNbr = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfOffcrNbr() != null
                                                                                                       ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfOffcrNbr().toString()
                                                                                                       : null;
    String portfOpernType = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfOpernType() != null
                                                                                                         ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfOpernType()
                                                                                                         : null;
    String portfRegionCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfRegionCode() != null
                                                                                                           ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfRegionCode()
                                                                                                           : null;
    String portfSegCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfSegCode() != null
                                                                                                     ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfSegCode()
                                                                                                     : null;
    String portfSegSubCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfSegSubCode() != null
                                                                                                           ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfSegSubCode().toString()
                                                                                                           : null;
    String portfStartDate = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfStartDate() != null
                                                                                                         ? dateFormat.format( detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfStartDate() )
                                                                                                         : null;
    String portfStatCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfStatCode() != null
                                                                                                       ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfStatCode()
                                                                                                       : null;
    String portfUnitCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfUnitCode() != null
                                                                                                       ? detailFncVO.getTplPortfolioPrvtEntity().getData().getPortfUnitCode()
                                                                                                       : null;
    String recStatCode = detailFncVO.getTplPortfolioPrvtEntity().getData().getrecStatCode() != null
                                                                                                   ? detailFncVO.getTplPortfolioPrvtEntity().getData().getrecStatCode()
                                                                                                   : null;
    String offNameText = detailFncVO.getOffNameText() != null
                                                             ? detailFncVO.getOffNameText()
                                                             : null;

    detailForm.setPortfBrchCode( portfBrchCode );
    detailForm.setPortfCode( portfCode );
    detailForm.setPortfCostBusGrpCode( portfCostBusGrpCode );
    detailForm.setPortfCostDivCode( portfCostDivCode );
    detailForm.setPortfCostPrftyCtrCode( portfCostPrftyCtrCode );
    detailForm.setPortfCostRegionCode( portfCostRegionCode );
    detailForm.setPortfCostRespOffcrCode( portfCostRespOffcrCode );
    detailForm.setPortfCservSuplCode( portfCservSuplCode );
    detailForm.setPortfNameText( portfNameText );
    detailForm.setPortfNetwkSubGrpCode( portfNetwkSubGrpCode );
    detailForm.setPortfNetwkSubNetwkGrpCode( portfNetwkSubNetwkGrpCode );
    detailForm.setPortfOffcrNbr( portfOffcrNbr );
    detailForm.setPortfOpernType( portfOpernType );
    detailForm.setPortfRegionCode( portfRegionCode );
    detailForm.setPortfSegCode( portfSegCode );
    detailForm.setPortfSegSubCode( portfSegSubCode );
    detailForm.setPortfStartDate( portfStartDate );
    detailForm.setPortfStatCode( portfStatCode );
    detailForm.setPortfUnitCode( portfUnitCode );
    detailForm.setRecStatCode( recStatCode );
    detailForm.setOffNameText( offNameText );

  }

  public void loadPortfolioPrvt( BasePortfolioPrvtDetailFncVO detailFncVO_ )
  {
    BaseTplPortfolioPrvtEntity portfolioPrvtEntity;

    BaseTplPortfolioPrvtDAO portfolioPrvtDAO = this.getDAO();
    portfolioPrvtEntity = portfolioPrvtDAO.find( detailFncVO_.getTplPortfolioPrvtEntity() );
    detailFncVO_.setTplPortfolioPrvtEntity( portfolioPrvtEntity );
  }

  public void loadOffNameText( BasePortfolioPrvtDetailFncVO detailFncVO_ )
  {
    if ( detailFncVO_.getTplPortfolioPrvtEntity().getData().getPortfOffcrNbr() != null )
    //&&
    // detailFncVO_.getTplPortfolioPrvtEntity().getData().getPortfOffcrNbr().longValue()
    // > 0 )
    {
      TbgOfficerEntity officerEntity = new TbgOfficerEntity();
      officerEntity.getData().setOffcrNbr(
                                           detailFncVO_.getTplPortfolioPrvtEntity().getData().getPortfOffcrNbr() );

      ODSDAOFactory factory = ODSDAOFactory.getInstance();

      TbgOfficerDAO officerDAO = factory.getTbgOfficerDAO();

      if ( officerDAO.exists( officerEntity ) )
      {
        officerEntity = ( TbgOfficerEntity ) officerDAO.find( officerEntity );
        detailFncVO_.setOffNameText( officerEntity.getData().getOffcrNameText() );
      }
      else
      {
        detailFncVO_.setOffNameText( null );
      }
    }
  }

  protected abstract BaseTplPortfolioPrvtDAO getDAO();
}