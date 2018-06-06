package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrEntityVO;
import com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrDetailForm;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgPointAcctInvstDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * 
 * @see package com.citibank.ods.modules.client.ipdocprvt.functionality;
 * @version 1.0
 * @author michele.monteiro,04/06/2007
 */

public class CurAcctPrmntInstrDetailFnc extends BaseCurAcctPrmntInstrDetailFnc
    implements ODSDetailFnc
{

  protected BaseTplCurAcctPrmntInstrDAO getDAO()
  {
    ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
    TplCurAcctPrmntInstrDAO tplCurAcctPrmntInstrDAO = odsDAOFactory.getTplCurAcctPrmntInstrDAO();
    return tplCurAcctPrmntInstrDAO;

  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    CurAcctPrmntInstrDetailForm form = ( CurAcctPrmntInstrDetailForm ) form_;

    TplCurAcctPrmntInstrEntity curAcctPrmntInstrEntity;
    TplIpDocPrvtEntity tplIpDocPrvtEntity;

    BigInteger prmntCode;
    String prmntText;
    String prmntIndCCI;
    String prmntCodeString;
    String invstCur;
    String[] row;

    String[][] showList = null;

    ArrayList listCurPrmntInstr = fncVO.getBaseTplIpDocPrvtEntityList();

    if ( listCurPrmntInstr != null && listCurPrmntInstr.size() > 0 )
    {
      showList = new String[ listCurPrmntInstr.size() ][];
      int rowIndex = 0;
      for ( Iterator ite = listCurPrmntInstr.iterator(); ite.hasNext(); rowIndex++ )
      {

        curAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) ite.next();

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

        row = new String[] { prmntCodeString, prmntText, prmntIndCCI, invstCur };

        showList[ rowIndex ] = row;
      }
      form.setIpDocDomains( showList );

    }
    else
    {
      form.setIpDocDomains( null );
    }

  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    CurAcctPrmntInstrDetailForm form = ( CurAcctPrmntInstrDetailForm ) form_;

    super.updateFncVOFromForm( form_, fncVO_ );

    //Obtem os dados da tela que serão inseridos no grid
    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntityInsert = new TplCurAcctPrmntInstrEntity();
    TplCurAcctPrmntInstrEntityVO entityVO = ( TplCurAcctPrmntInstrEntityVO ) tplCurAcctPrmntInstrEntityInsert.getData();

    if ( form.getPrmntInstrCodeSrc() != null
         && form.getPrmntInstrCodeSrc().length() > 0 )
    {
      entityVO.setPrmntInstrCode( new BigInteger( form.getPrmntInstrCodeSrc() ) );
    }
    if ( form.getPrmntInstrText() != null
         && form.getPrmntInstrText().length() > 0 )
    {
      tplCurAcctPrmntInstrEntityInsert.getDataIP().setIpDocText(
                                                                 form.getPrmntInstrText() );
    }
    if ( form.getPrmntInstrInvstCurAcctInd() != null
         && form.getPrmntInstrInvstCurAcctInd().length() > 0 )
    {
      tplCurAcctPrmntInstrEntityInsert.getDataIP().setIpInvstCurAcctInd(
                                                                         form.getPrmntInstrInvstCurAcctInd() );
    }

    if ( form.getCustNbrSrc() != null && form.getCustNbrSrc().length() > 0 )
    {
      entityVO.setCustNbr( new BigInteger( form.getCustNbrSrc() ) );
    }

    if ( form.getInvstAcctCurNbr() != null
         && form.getInvstAcctCurNbr().length() > 0 )
    {
      tplCurAcctPrmntInstrEntityInsert.getDataPoint().setInvstCurAcctNbr(
                                                                          form.getInvstAcctCurNbr() );
    }
    entityVO.setProdAcctCode( new BigInteger( form.getProdAcctCode() ) );
    entityVO.setProdUnderAcctCode( new BigInteger( form.getProdUnderAcctCode() ) );

    fncVO.setInsertIP( tplCurAcctPrmntInstrEntityInsert );

  }

  public BaseFncVO createFncVO()
  {

    return new CurAcctPrmntInstrDetailFncVO();
  }

  public void insertIP( BaseFncVO fncVO_ )
  {

    fncVO_.clearErrors();
    this.validateInsertIP( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
      TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = fncVO.getInsertIP();
      fncVO.getBaseTplIpDocPrvtEntityList().add( fncVO.getInsertIP() );
      fncVO.getSelectedItemsInGrid().add( "S" );
      fncVO.getDeletedItems().add( "N" );
      fncVO.setRelation( true );
    }
  }

  private void validateInsertIP( BaseFncVO fncVO_ )
  {
    TplCurAcctPrmntInstrEntity prmntInstrEntity;

    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrEntityVO prmntInstrEntityVO = ( TplCurAcctPrmntInstrEntityVO ) fncVO.getInsertIP().getData();

    //Validar campos obrigatórios
    if ( fncVO.getPrmntInstrCode() == null
         || fncVO.getPrmntInstrCode().equals( "" ) )
    {
      fncVO_.addError( CurAcctPrmntInstrDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_PRMNT_INSTR_CODE );
    }
    //Verifica se o registro já existe no grid.
    if ( fncVO.getBaseTplIpDocPrvtEntityList() != null
         && !fncVO.getBaseTplIpDocPrvtEntityList().isEmpty() )
    {
      for ( int i = 0; i < fncVO.getBaseTplIpDocPrvtEntityList().size(); i++ )
      {
        prmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) fncVO.getBaseTplIpDocPrvtEntityList().get(
                                                                                                     i );
        if ( prmntInstrEntity.equals( fncVO.getInsertIP() ) )
        {
          fncVO_.addError( CurAcctPrmntInstrDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
        }
      }
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;
    TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO;

    this.validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      CurAcctPrmntInstrDetailFncVO detailFncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
      tplCurAcctPrmntInstrMovDAO = OracleODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();

      ArrayList listIpAssociation = detailFncVO.getBaseTplIpDocPrvtEntityList();

      for ( Iterator ite = listIpAssociation.iterator(); ite.hasNext(); )
      {
        tplCurAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) ite.next();

        tplCurAcctPrmntInstrEntity.getData().setLastUpdDate( new Date() );
        tplCurAcctPrmntInstrEntity.getData().setLastUpdUserId(
                                                               fncVO_.getLoggedUser() != null
                                                                                             ? fncVO_.getLoggedUser().getUserID()
                                                                                             : "" );
        tplCurAcctPrmntInstrMovEntity = new TplCurAcctPrmntInstrMovEntity(
                                                                           tplCurAcctPrmntInstrEntity,
                                                                           TplCurAcctPrmntInstrMovEntity.C_OPERN_CODE_DELETE );

        tplCurAcctPrmntInstrMovDAO.insert( tplCurAcctPrmntInstrMovEntity );

      }
    }
  }

  /**
   * Deleta uma instrução permanente. A instrução permanente é excluída da lista
   * são excluidas na base quando o usuário clica no botão confirmar inserção
   * 
   * @param fncVO_ - Objeto com os dados da crítica e domínio.
   */
  public void deleteIp( BaseFncVO fncVO_ )
  {

    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity;

    fncVO_.clearErrors();
    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;

    //Excluir a linha selecionada
    if ( fncVO.getBaseTplIpDocPrvtEntityList() != null
         && !fncVO.getBaseTplIpDocPrvtEntityList().isEmpty() )
    {
      for ( int i = 0; i < fncVO.getBaseTplIpDocPrvtEntityList().size(); i++ )
      {
        tplCurAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) fncVO.getBaseTplIpDocPrvtEntityList().get(
                                                                                                               i );
        if ( tplCurAcctPrmntInstrEntity.equals( fncVO.getDeleteIP() ) )
        {
          fncVO.getBaseTplIpDocPrvtEntityList().remove( i );
          fncVO.setRelation( true );
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {
    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity;
    TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity = null;
    TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();

    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      CurAcctPrmntInstrDetailFncVO detailFncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
      //Retira da lista final os ítens que foram inseridos e excluídos na mesma
      // operação.
      int relationList = detailFncVO.getBaseTplIpDocPrvtEntityList().size();
      Iterator resultsIterator;

      for ( int count = 0; count < relationList; count++ )
      {

        resultsIterator = detailFncVO.getSelectedItemsInGrid().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          resultsIterator.next();
          if ( detailFncVO.getSelectedItemsInGrid().get( max ).equals( "S" )
               && detailFncVO.getDeletedItems().get( max ).equals( "S" ) )
          {
            detailFncVO.getBaseTplIpDocPrvtEntityList().remove( max );
            detailFncVO.getSelectedItemsInGrid().remove( max );
            detailFncVO.getDeletedItems().remove( max );
            break;
          }
          else if ( detailFncVO.getSelectedItemsInGrid().get( max ).equals( "N" )
                    && detailFncVO.getDeletedItems().get( max ).equals( "N" ) )
          {
            detailFncVO.getBaseTplIpDocPrvtEntityList().remove( max );
            detailFncVO.getSelectedItemsInGrid().remove( max );
            detailFncVO.getDeletedItems().remove( max );
            break;
          }
          max++;

        }
      }
      //Lista de instruções permanente atualizada.
      ArrayList listIpAssociation = detailFncVO.getBaseTplIpDocPrvtEntityList();
      for ( int i = 0; i < listIpAssociation.size(); i++ )
      {
        tplCurAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) listIpAssociation.get( i );
        tplCurAcctPrmntInstrEntity.getData().setLastUpdDate( new Date() );
        tplCurAcctPrmntInstrEntity.getData().setLastUpdUserId(
                                                               fncVO_.getLoggedUser() != null
                                                                                             ? fncVO_.getLoggedUser().getUserID()
                                                                                             : "" );
        if ( detailFncVO.getSelectedItemsInGrid().get( i ).equals( "S" )
             && detailFncVO.getDeletedItems().get( i ).equals( "N" ) )
        {
          tplCurAcctPrmntInstrMovEntity = new TplCurAcctPrmntInstrMovEntity(
                                                                             tplCurAcctPrmntInstrEntity,
                                                                             BaseODSEntity.C_OPERN_CODE_INSERT );
        }
        else if ( detailFncVO.getSelectedItemsInGrid().get( i ).equals( "N" )
                  && detailFncVO.getDeletedItems().get( i ).equals( "S" ) )
        {
          tplCurAcctPrmntInstrMovEntity = new TplCurAcctPrmntInstrMovEntity(
                                                                             tplCurAcctPrmntInstrEntity,
                                                                             BaseODSEntity.C_OPERN_CODE_DELETE );
        }

        tplCurAcctPrmntInstrMovDAO.insert( tplCurAcctPrmntInstrMovEntity );

      }
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );

    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity;
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity;

    TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();
    tplIpDocPrvtEntity = new TplIpDocPrvtEntity();

    TplCurAcctPrmntInstrDAO curAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
    ArrayList listAssoc = curAcctPrmntInstrDAO.selectByPK(
                                                           fncVO.getCustNbr(),
                                                           fncVO.getProdAcctCode(),
                                                           fncVO.getProdUnderAcctCode() );
    fncVO.setBaseTplIpDocPrvtEntityList( listAssoc );

    if ( listAssoc != null && listAssoc.size() > 0 )
    {
      for ( int i = 0; i < listAssoc.size(); i++ )
      {
        tplCurAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) listAssoc.get( i );

        tplIpDocPrvtEntity.getData().setIpDocCode(

        tplCurAcctPrmntInstrEntity.getData().getPrmntInstrCode() );
        tplIpDocPrvtEntity.getData().setCustNbr(
                                                 tplCurAcctPrmntInstrEntity.getData().getCustNbr() );

        tplIpDocPrvtEntity = ipDocPrvtDAO.find( tplIpDocPrvtEntity );

        tplCurAcctPrmntInstrEntity.getDataIP().setIpDocText(
                                                             tplIpDocPrvtEntity.getData().getIpDocText() );
        tplCurAcctPrmntInstrEntity.getDataIP().setIpInvstCurAcctInd(
                                                                     tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd() );
        if ( tplCurAcctPrmntInstrEntity.getDataIP().getIpInvstCurAcctInd().equals(
                                                                                   "S" ) )
        {
          TbgPointAcctInvstDAO tbgPointAcctInvstDAO = ODSDAOFactory.getInstance().getTbgPointAcctInvstDAO();
          TbgPointAcctInvstEntity tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

          tbgPointAcctInvstEntity.getData().setCurAcctNbr(
                                                           fncVO.getCurAcctNbr().toString() );
          if ( tbgPointAcctInvstDAO.exists( tbgPointAcctInvstEntity ) )
          {
            tbgPointAcctInvstEntity = tbgPointAcctInvstDAO.find( tbgPointAcctInvstEntity );

            tplCurAcctPrmntInstrEntity.getDataPoint().setInvstCurAcctNbr(
                                                                          tbgPointAcctInvstEntity.getData().getInvstCurAcctNbr() );

          }
          else
          {
            tplCurAcctPrmntInstrEntity.getDataPoint().setInvstCurAcctNbr( "" );

          }
        }
      }
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );

    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity;
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity;
    TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();

    //Verifica se o registro esta em movimento
    if ( movDAO.exists( fncVO.getProdAcctCode(), fncVO.getProdUnderAcctCode(),
                        fncVO.getCustNbr() ) )
    {
      fncVO_.addError( CurAcctPrmntInstrDetailFncVO.C_ERROR_IN_MOVEMENT );
    }

    if ( !fncVO_.hasErrors() )
    {
      TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();
      tplIpDocPrvtEntity = new TplIpDocPrvtEntity();

      TplCurAcctPrmntInstrDAO curAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
      ArrayList listAssoc = curAcctPrmntInstrDAO.selectByPK(
                                                             fncVO.getCustNbr(),
                                                             fncVO.getProdAcctCode(),
                                                             fncVO.getProdUnderAcctCode() );
      fncVO.setBaseTplIpDocPrvtEntityList( listAssoc );

      if ( listAssoc != null && listAssoc.size() > 0 )
      {
        for ( int i = 0; i < listAssoc.size(); i++ )
        {
          tplCurAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) listAssoc.get( i );

          tplIpDocPrvtEntity.getData().setIpDocCode(

          tplCurAcctPrmntInstrEntity.getData().getPrmntInstrCode() );
          tplIpDocPrvtEntity.getData().setCustNbr(
                                                   tplCurAcctPrmntInstrEntity.getData().getCustNbr() );

          tplIpDocPrvtEntity = ipDocPrvtDAO.find( tplIpDocPrvtEntity );

          tplCurAcctPrmntInstrEntity.getDataIP().setIpDocText(
                                                               tplIpDocPrvtEntity.getData().getIpDocText() );
          tplCurAcctPrmntInstrEntity.getDataIP().setIpInvstCurAcctInd(
                                                                       tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd() );
          if ( tplCurAcctPrmntInstrEntity.getDataIP().getIpInvstCurAcctInd().equals(
                                                                                     "S" ) )
          {
            TbgPointAcctInvstDAO tbgPointAcctInvstDAO = ODSDAOFactory.getInstance().getTbgPointAcctInvstDAO();
            TbgPointAcctInvstEntity tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

            tbgPointAcctInvstEntity.getData().setCurAcctNbr(
                                                             fncVO.getCurAcctNbr().toString() );
            if ( tbgPointAcctInvstDAO.exists( tbgPointAcctInvstEntity ) )
            {
              tbgPointAcctInvstEntity = tbgPointAcctInvstDAO.find( tbgPointAcctInvstEntity );

              tplCurAcctPrmntInstrEntity.getDataPoint().setInvstCurAcctNbr(
                                                                            tbgPointAcctInvstEntity.getData().getInvstCurAcctNbr() );

            }
            else
            {
              tplCurAcctPrmntInstrEntity.getDataPoint().setInvstCurAcctNbr( "" );

            }
          }
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );
    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity;
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity;
    TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();

    //Verifica se o registro esta em movimento
    if ( movDAO.exists( fncVO.getProdAcctCode(), fncVO.getProdUnderAcctCode(),
                        fncVO.getCustNbr() ) )
    {
      fncVO_.addError( CurAcctPrmntInstrDetailFncVO.C_ERROR_IN_MOVEMENT );
    }

    TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();

    if ( !fncVO.isFromSearch() && !fncVO.isRelation() )
    {

      tplIpDocPrvtEntity = new TplIpDocPrvtEntity();

      TplCurAcctPrmntInstrDAO curAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
      ArrayList listAssoc = curAcctPrmntInstrDAO.selectByPK(
                                                             fncVO.getCustNbr(),
                                                             fncVO.getProdAcctCode(),
                                                             fncVO.getProdUnderAcctCode() );
      fncVO.setBaseTplIpDocPrvtEntityList( listAssoc );

      if ( listAssoc != null && listAssoc.size() > 0 )
      {
        for ( int i = 0; i < listAssoc.size(); i++ )
        {
          tplCurAcctPrmntInstrEntity = ( TplCurAcctPrmntInstrEntity ) listAssoc.get( i );

          tplIpDocPrvtEntity.getData().setIpDocCode(

          tplCurAcctPrmntInstrEntity.getData().getPrmntInstrCode() );
          tplIpDocPrvtEntity.getData().setCustNbr(
                                                   tplCurAcctPrmntInstrEntity.getData().getCustNbr() );

          tplIpDocPrvtEntity = ipDocPrvtDAO.find( tplIpDocPrvtEntity );

          tplCurAcctPrmntInstrEntity.getDataIP().setIpDocText(
                                                               tplIpDocPrvtEntity.getData().getIpDocText() );
          tplCurAcctPrmntInstrEntity.getDataIP().setIpInvstCurAcctInd(
                                                                       tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd() );
          if ( tplCurAcctPrmntInstrEntity.getDataIP().getIpInvstCurAcctInd().equals(
                                                                                     "S" ) )
          {
            TbgPointAcctInvstDAO tbgPointAcctInvstDAO = ODSDAOFactory.getInstance().getTbgPointAcctInvstDAO();
            TbgPointAcctInvstEntity tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

            tbgPointAcctInvstEntity.getData().setCurAcctNbr(
                                                             fncVO.getCurAcctNbr().toString() );
            if ( tbgPointAcctInvstDAO.exists( tbgPointAcctInvstEntity ) )
            {
              tbgPointAcctInvstEntity = tbgPointAcctInvstDAO.find( tbgPointAcctInvstEntity );

              tplCurAcctPrmntInstrEntity.getDataPoint().setInvstCurAcctNbr(
                                                                            tbgPointAcctInvstEntity.getData().getInvstCurAcctNbr() );

            }
            else
            {
              tplCurAcctPrmntInstrEntity.getDataPoint().setInvstCurAcctNbr( "" );

            }
          }
        }
      }
    }
    fncVO.setFromSearch( false );
    fncVO.setRelation( false );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();

    if ( movDAO.exists( fncVO.getProdAcctCode(), fncVO.getProdUnderAcctCode(),
                        fncVO.getCustNbr() ) )
    {
      fncVO_.addError( CurAcctPrmntInstrDetailFncVO.C_ERROR_IN_MOVEMENT );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrDetailFncVO fncVO = ( CurAcctPrmntInstrDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
	TplCurAcctPrmntInstrDAO prmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();  

    if ( movDAO.exists( fncVO.getProdAcctCode(), fncVO.getProdUnderAcctCode(),
                        fncVO.getCustNbr() ) )
    {
      fncVO_.addError( CurAcctPrmntInstrDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    //Verifica se ha pelo menos um IP associado
    if ( !fncVO_.hasErrors() )
    {
      int findItemsDeleted = 0;

      for ( int i = 0; i < fncVO.getDeletedItems().size(); i++ )
      {
        if ( fncVO.getDeletedItems().get( i ).equals( "S" ) )
        {
          findItemsDeleted++;

        }
      }
     
      if ( findItemsDeleted == fncVO.getBaseTplIpDocPrvtEntityList().size() )

      {
        fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                         "inserido", "Instrução Permanente" );
      }  
	  
      
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {

  }

}