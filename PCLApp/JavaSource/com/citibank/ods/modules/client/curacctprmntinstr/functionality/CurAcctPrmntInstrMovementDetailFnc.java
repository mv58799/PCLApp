package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrHistEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleEntity;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrMovEntityVO;
import com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrMovementDetailForm;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrDetailFncVO;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrMovementDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgPointAcctInvstDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrHistDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.functionality;
 * @version 1.0
 * @author michele.monteiro,19/06/2007
 *  
 */

public class CurAcctPrmntInstrMovementDetailFnc extends
    BaseCurAcctPrmntInstrDetailFnc implements ODSMovementDetailFnc
{
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
    CurAcctPrmntInstrMovementDetailForm form = ( CurAcctPrmntInstrMovementDetailForm ) form_;

    super.updateFormFromFncVO( form_, fncVO_ );

    TplCurAcctPrmntInstrMovEntity curAcctPrmntInstrEntity = null;
    TplIpDocPrvtEntity tplIpDocPrvtEntity;

    BigInteger prmntCode;
    String prmntText;
    String prmntIndCCI;
    String prmntCodeString;
    String invstCur;
    String opernCode;
    String[] row;

    String[][] showList = null;

    ArrayList listCurPrmntInstr = fncVO.getBaseTplIpDocPrvtEntityList();

    if ( listCurPrmntInstr != null && listCurPrmntInstr.size() > 0 )
    {
      showList = new String[ listCurPrmntInstr.size() ][];
      int rowIndex = 0;
      for ( Iterator ite = listCurPrmntInstr.iterator(); ite.hasNext(); rowIndex++ )
      {

        curAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrMovEntity ) ite.next();

        prmntCode = curAcctPrmntInstrEntity.getData().getPrmntInstrCode();
        prmntCodeString = prmntCode != null ? prmntCode.toString() : "";
        prmntText = curAcctPrmntInstrEntity.getDataIP().getIpDocText();
        prmntIndCCI = ( curAcctPrmntInstrEntity.getDataIP().getIpInvstCurAcctInd() != null && !curAcctPrmntInstrEntity.getDataIP().getIpInvstCurAcctInd().equals(
                                                                                                                                                                  "" ) )
                                                                                                                                                                        ? ODSConstraintDecoder.decodeIndicator( curAcctPrmntInstrEntity.getDataIP().getIpInvstCurAcctInd() )
                                                                                                                                                                        : "";
        invstCur = curAcctPrmntInstrEntity.getDataPoint().getInvstCurAcctNbr() != null
                                                                                      ? curAcctPrmntInstrEntity.getDataPoint().getInvstCurAcctNbr()
                                                                                      : "";
        opernCode = ODSConstraintDecoder.decodeOpern( ( ( TplCurAcctPrmntInstrMovEntityVO ) curAcctPrmntInstrEntity.getData() ).getOpernCode() );

        row = new String[] { prmntCodeString, prmntText, prmntIndCCI, invstCur,
                            opernCode };

        showList[ rowIndex ] = row;
      }
      form.setIpDocDomains( showList );
      if ( curAcctPrmntInstrEntity.getData().getLastUpdUserId() != null
           && curAcctPrmntInstrEntity.getData().getLastUpdUserId().length() > 0 )
      {
        form.setLastUpdUserId( curAcctPrmntInstrEntity.getData().getLastUpdUserId() );
      }

      if ( curAcctPrmntInstrEntity.getData().getLastUpdDate() != null )
      {
        form.setLastUpdDate( formatDateTime( curAcctPrmntInstrEntity.getData().getLastUpdDate() ) );
      }

      String strOpernCode = ( ODSConstraintDecoder.decodeOpern( ( ( TplCurAcctPrmntInstrMovEntityVO ) curAcctPrmntInstrEntity.getData() ).getOpernCode() ) );
      if ( strOpernCode != null )
      {
        form.setOpernCode( strOpernCode );
      }

    }
    else
    {
      form.setIpDocDomains( null );
    }

  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
    CurAcctPrmntInstrMovementDetailForm form = ( CurAcctPrmntInstrMovementDetailForm ) form_;

    super.updateFncVOFromForm( form_, fncVO_ );

    //Obtem os dados da tela que serão inseridos no grid
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntityInsert = new TplCurAcctPrmntInstrMovEntity();
    TplCurAcctPrmntInstrMovEntityVO movEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntityInsert.getData();

    if ( form.getPrmntInstrCodeSrc() != null
         && form.getPrmntInstrCodeSrc().length() > 0 )
    {
      movEntityVO.setPrmntInstrCode( new BigInteger(
                                                     form.getPrmntInstrCodeSrc() ) );
    }
    if ( form.getPrmntInstrText() != null
         && form.getPrmntInstrText().length() > 0 )
    {
      tplCurAcctPrmntInstrMovEntityInsert.getDataIP().setIpDocText(
                                                                    form.getPrmntInstrText() );
    }
    if ( form.getPrmntInstrInvstCurAcctInd() != null
         && form.getPrmntInstrInvstCurAcctInd().length() > 0 )
    {
      tplCurAcctPrmntInstrMovEntityInsert.getDataIP().setIpInvstCurAcctInd(
                                                                            form.getPrmntInstrInvstCurAcctInd() );
    }

    if ( form.getCustNbrSrc() != null && form.getCustNbrSrc().length() > 0 )
    {
      movEntityVO.setCustNbr( new BigInteger( form.getCustNbrSrc() ) );
    }

    if ( form.getInvstAcctCurNbr() != null
         && form.getInvstAcctCurNbr().length() > 0 )
    {
      tplCurAcctPrmntInstrMovEntityInsert.getDataPoint().setInvstCurAcctNbr(
                                                                             form.getInvstAcctCurNbr() );
    }
    if ( form.getLastUpdUserId() != null
         && form.getLastUpdUserId().length() > 0 )
    {
      movEntityVO.setLastUpdUserId( form.getLastUpdUserId() );

    }

    movEntityVO.setProdAcctCode( new BigInteger( form.getProdAcctCode() ) );
    movEntityVO.setProdUnderAcctCode( new BigInteger(
                                                      form.getProdUnderAcctCode() ) );

    fncVO.setInsertIP( tplCurAcctPrmntInstrMovEntityInsert );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {

    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = null;
    TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntity;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;

    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;

      TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
      TplCurAcctPrmntInstrDAO curAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
      TplCurAcctPrmntInstrHistDAO histDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrHistDAO();

      ArrayList listCurAcctPrmntMov = fncVO.getBaseTplIpDocPrvtEntityList();
      //Inseri os registros da tabela corrente na tabela de histórico
      ArrayList listCurAcctPrmntCurrent = curAcctPrmntInstrDAO.selectByPK(
                                                                           fncVO.getCustNbr(),
                                                                           fncVO.getProdAcctCode(),
                                                                           fncVO.getProdUnderAcctCode() );
      if ( listCurAcctPrmntCurrent != null
           && listCurAcctPrmntCurrent.size() > 0 )
      {
        for ( int i = 0; i < listCurAcctPrmntCurrent.size(); i++ )
        {
          tplCurAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) listCurAcctPrmntCurrent.get( i );
          tplCurAcctPrmntInstrHistEntity = new TplCurAcctPrmntInstrHistEntity(
                                                                               tplCurAcctPrmntInstrEntity,
                                                                               new Date() );
          histDAO.insert( tplCurAcctPrmntInstrHistEntity );
        }
      }

      for ( int i = 0; i < listCurAcctPrmntMov.size(); i++ )
      {
        tplCurAcctPrmntInstrMovEntity = ( TplCurAcctPrmntInstrMovEntity ) listCurAcctPrmntMov.get( i );

        tplCurAcctPrmntInstrEntity = new TplCurAcctPrmntInstrEntity(
                                                                     tplCurAcctPrmntInstrMovEntity,
                                                                     new Date(),
                                                                     fncVO_.getLoggedUser().getUserID(),
                                                                     TplCurAcctPrmntInstrEntity.C_REC_STAT_CODE_ACTIVE );

        if ( !( ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity.getData() ).getOpernCode().equals(
                                                                                                                     TplCurAcctPrmntInstrMovEntity.C_OPERN_CODE_DELETE ) )
        {
          if ( curAcctPrmntInstrDAO.exists( tplCurAcctPrmntInstrEntity ) )
          {
            curAcctPrmntInstrDAO.update( tplCurAcctPrmntInstrEntity );
          }
          else
          {
            curAcctPrmntInstrDAO.insert( tplCurAcctPrmntInstrEntity );
          }
        }
        else
        {
          ( ( TplCurAcctPrmntInstrEntityVO ) tplCurAcctPrmntInstrEntity.getData() ).setRecStatCode( TplCurAcctPrmntInstrEntity.C_REC_STAT_CODE_INACTIVE );
          curAcctPrmntInstrDAO.update( tplCurAcctPrmntInstrEntity );
        }

      }
      // Remove o movimento
      movDAO.delete( fncVO.getCustNbr(), fncVO.getProdAcctCode(),
                     fncVO.getProdUnderAcctCode() );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
    ArrayList listAssoc = movDAO.selectByPK( fncVO.getCustNbr(),
                                             fncVO.getProdAcctCode(),
                                             fncVO.getProdUnderAcctCode() );
    fncVO.setBaseTplIpDocPrvtEntityList( listAssoc );

    TplIpDocPrvtDAO tplIpDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();

    if ( listAssoc != null && listAssoc.size() > 0 )
    {
      for ( int i = 0; i < listAssoc.size(); i++ )
      {
        tplCurAcctPrmntInstrMovEntity = ( TplCurAcctPrmntInstrMovEntity ) listAssoc.get( i );
        tplIpDocPrvtEntity = new TplIpDocPrvtEntity();
        tplIpDocPrvtEntity.getData().setCustNbr(
                                                 tplCurAcctPrmntInstrMovEntity.getData().getCustNbr() );
        tplIpDocPrvtEntity.getData().setIpDocCode(
                                                   tplCurAcctPrmntInstrMovEntity.getData().getPrmntInstrCode() );

        tplIpDocPrvtEntity = tplIpDocPrvtDAO.find( tplIpDocPrvtEntity );

        tplCurAcctPrmntInstrMovEntity.getDataIP().setIpDocText(
                                                                tplIpDocPrvtEntity.getData().getIpDocText() != null
                                                                                                                   ? tplIpDocPrvtEntity.getData().getIpDocText()
                                                                                                                   : "" );
        tplCurAcctPrmntInstrMovEntity.getDataIP().setIpInvstCurAcctInd(
                                                                        tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd() != null
                                                                                                                                   ? tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd()
                                                                                                                                   : "" );
        if ( tplCurAcctPrmntInstrMovEntity.getDataIP().getIpInvstCurAcctInd().equals(
                                                                                      "S" ) )
        {
          TbgPointAcctInvstDAO tbgPointAcctInvstDAO = ODSDAOFactory.getInstance().getTbgPointAcctInvstDAO();
          TbgPointAcctInvstEntity tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

          tbgPointAcctInvstEntity.getData().setCurAcctNbr(
                                                           fncVO.getCurAcctNbr().toString() );
          if ( tbgPointAcctInvstDAO.exists( tbgPointAcctInvstEntity ) )
          {
            tbgPointAcctInvstEntity = tbgPointAcctInvstDAO.find( tbgPointAcctInvstEntity );

            tplCurAcctPrmntInstrMovEntity.getDataPoint().setInvstCurAcctNbr(
                                                                             tbgPointAcctInvstEntity.getData().getInvstCurAcctNbr() );
          }
          else
          {
            tplCurAcctPrmntInstrMovEntity.getDataPoint().setInvstCurAcctNbr( "" );

          }
        }

      }
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {

    super.load( fncVO_ );
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;

    if ( !fncVO.isFromSearch() && !fncVO.isRelation() )
    {
      fncVO.getInsertIP().getData().setCustNbr( null );

      TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
      ArrayList listAssoc = movDAO.selectByPK( fncVO.getCustNbr(),
                                               fncVO.getProdAcctCode(),
                                               fncVO.getProdUnderAcctCode() );
      fncVO.setBaseTplIpDocPrvtEntityList( listAssoc );

      TplIpDocPrvtDAO tplIpDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();

      if ( listAssoc != null && listAssoc.size() > 0 )
      {
        for ( int i = 0; i < listAssoc.size(); i++ )
        {
          tplCurAcctPrmntInstrMovEntity = ( TplCurAcctPrmntInstrMovEntity ) listAssoc.get( i );
          tplIpDocPrvtEntity = new TplIpDocPrvtEntity();
          tplIpDocPrvtEntity.getData().setCustNbr(
                                                   tplCurAcctPrmntInstrMovEntity.getData().getCustNbr() );
          tplIpDocPrvtEntity.getData().setIpDocCode(
                                                     tplCurAcctPrmntInstrMovEntity.getData().getPrmntInstrCode() );

          tplIpDocPrvtEntity = tplIpDocPrvtDAO.find( tplIpDocPrvtEntity );
          tplCurAcctPrmntInstrMovEntity.getDataIP().setIpDocText(
                                                                  tplIpDocPrvtEntity.getData().getIpDocText() != null
                                                                                                                     ? tplIpDocPrvtEntity.getData().getIpDocText()
                                                                                                                     : "" );
          tplCurAcctPrmntInstrMovEntity.getDataIP().setIpInvstCurAcctInd(
                                                                          tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd() != null
                                                                                                                                     ? tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd()
                                                                                                                                     : "" );
          if ( tplCurAcctPrmntInstrMovEntity.getDataIP().getIpInvstCurAcctInd().equals(
                                                                                        "S" ) )
          {
            TbgPointAcctInvstDAO tbgPointAcctInvstDAO = ODSDAOFactory.getInstance().getTbgPointAcctInvstDAO();
            TbgPointAcctInvstEntity tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

            tbgPointAcctInvstEntity.getData().setCurAcctNbr(
                                                             fncVO.getCurAcctNbr().toString() );

            if ( tbgPointAcctInvstDAO.exists( tbgPointAcctInvstEntity ) )
            {
              tbgPointAcctInvstEntity = tbgPointAcctInvstDAO.find( tbgPointAcctInvstEntity );

              tplCurAcctPrmntInstrMovEntity.getDataPoint().setInvstCurAcctNbr(
                                                                               tbgPointAcctInvstEntity.getData().getInvstCurAcctNbr() );

            }
            else
            {
              tplCurAcctPrmntInstrMovEntity.getDataPoint().setInvstCurAcctNbr(
                                                                               "" );

            }

          }
        }
      }

    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    if ( !fncVO_.hasErrors() )
    {
      CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
      TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();

      BigInteger custNbr = fncVO.getCustNbr();
      BigInteger prodCode = fncVO.getProdAcctCode();
      BigInteger prodUnderCode = fncVO.getProdUnderAcctCode();

      if ( custNbr != null && prodCode != null && prodUnderCode != null )
      {
        //Exclui todas as associações relacionadas a conta corrente
        tplCurAcctPrmntInstrMovDAO.delete( custNbr, prodCode, prodUnderCode );
      }
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    TplCurAcctPrmntInstrMovEntityVO tplCurAcctPrmntInstrMovEntityVO;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity_;

    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
      TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
      movDAO.delete( fncVO.getCustNbr(), fncVO.getProdAcctCode(),
                     fncVO.getProdUnderAcctCode() );

      for ( int j = 0; j < fncVO.getSelectedItemsInGrid().size(); j++ )
      {
        for ( int i = 0; i < fncVO.getBaseTplIpDocPrvtEntityList().size(); i++ )
        {
          if ( fncVO.getDeletedItems().get( j ).equals( "S" ) )
          {
            fncVO.getBaseTplIpDocPrvtEntityList().remove( i );
            break;
          }
        }
      }

      ArrayList listCurAcctPrmntMov = fncVO.getBaseTplIpDocPrvtEntityList();

      for ( int i = 0; i < listCurAcctPrmntMov.size(); i++ )
      {
        tplCurAcctPrmntInstrMovEntity_ = ( TplCurAcctPrmntInstrMovEntity ) listCurAcctPrmntMov.get( i );
        tplCurAcctPrmntInstrMovEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity_.getData();

        tplCurAcctPrmntInstrMovEntityVO.setLastUpdDate( new Date() );
        tplCurAcctPrmntInstrMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
        if ( tplCurAcctPrmntInstrMovEntityVO.getOpernCode() == null )
        {
          tplCurAcctPrmntInstrMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
        }

        movDAO.insert( tplCurAcctPrmntInstrMovEntity_ );
      }

    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity = ( TplCurAcctPrmntInstrMovEntity ) fncVO.getBaseTplIpDocPrvtEntityList().get(
                                                                                                                                               0 );
    TplCurAcctPrmntInstrMovEntityVO tplCurAcctPrmntInstrMovEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity.getData();

    // Testar usuário
    if ( fncVO.getLoggedUser() == null
         || fncVO.getLoggedUser().getUserID().equals(
                                                      tplCurAcctPrmntInstrMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( CurAcctPrmntInstrMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity = ( TplCurAcctPrmntInstrMovEntity ) fncVO.getBaseTplIpDocPrvtEntityList().get(
                                                                                                                                               0 );
    TplCurAcctPrmntInstrMovEntityVO tplCurAcctPrmntInstrMovEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity.getData();

    // Testar usuário
    if ( fncVO.getLoggedUser() == null
         || !fncVO.getLoggedUser().getUserID().equals(
                                                       tplCurAcctPrmntInstrMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( CurAcctPrmntInstrMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }

  }

  public BaseFncVO createFncVO()
  {

    return new CurAcctPrmntInstrMovementDetailFncVO();
  }

  /**
   * Insere um elemento na lista
   * @param fncVO_
   */
  public void insertIP( BaseFncVO fncVO_ )
  {
    fncVO_.clearErrors();
    this.validateInsertIP( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
      ( ( TplCurAcctPrmntInstrMovEntityVO ) fncVO.getInsertIP().getData() ).setOpernCode( TplProdPlayerRoleEntity.C_OPERN_CODE_INSERT );
      fncVO.getBaseTplIpDocPrvtEntityList().add( fncVO.getInsertIP() );
      fncVO.getSelectedItemsInGrid().add( "S" );
      fncVO.getDeletedItems().add( "N" );
      fncVO.setRelation( true );
    }

  }

  /**
   * Remove um elemento na lista
   * @param fncVO_
   */
  public void deleteIP( BaseFncVO fncVO_ )
  {
    fncVO_.clearErrors();
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;
    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;

    //Excluir a linha selecionada
    if ( fncVO.getBaseTplIpDocPrvtEntityList() != null
         && !fncVO.getBaseTplIpDocPrvtEntityList().isEmpty() )
    {
      for ( int i = 0; i < fncVO.getBaseTplIpDocPrvtEntityList().size(); i++ )
      {
        tplCurAcctPrmntInstrMovEntity = ( TplCurAcctPrmntInstrMovEntity ) fncVO.getBaseTplIpDocPrvtEntityList().get(
                                                                                                                     i );
        if ( tplCurAcctPrmntInstrMovEntity.equals( fncVO.getDeleteIP() ) )
        {
          fncVO.getBaseTplIpDocPrvtEntityList().remove( i );
          fncVO.setRelation( true );
        }
      }
    }
  }

  private void validateInsertIP( BaseFncVO fncVO_ )
  {
    TplCurAcctPrmntInstrMovEntity prmntInstrEntity;

    CurAcctPrmntInstrMovementDetailFncVO fncVO = ( CurAcctPrmntInstrMovementDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrMovEntityVO prmntInstrEntityVO = ( TplCurAcctPrmntInstrMovEntityVO ) fncVO.getInsertIP().getData();

    //Validar campos obrigatórios
    if ( fncVO.getPrmntInstrCode() == null
         || fncVO.getPrmntInstrCode().equals( "" ) )
    {
      fncVO_.addError(
                       CurAcctPrmntInstrMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PRMNT_INSTR_CODE );
    }

    //Verifica se o registro já existe no grid.
    if ( fncVO.getBaseTplIpDocPrvtEntityList() != null
         && !fncVO.getBaseTplIpDocPrvtEntityList().isEmpty() )
    {
      for ( int i = 0; i < fncVO.getBaseTplIpDocPrvtEntityList().size(); i++ )
      {
        prmntInstrEntity = ( TplCurAcctPrmntInstrMovEntity ) fncVO.getBaseTplIpDocPrvtEntityList().get(
                                                                                                        i );
        if ( prmntInstrEntity.equals( fncVO.getInsertIP() ) )
        {
          fncVO_.addError( CurAcctPrmntInstrDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
        }
      }
    }

  }

}