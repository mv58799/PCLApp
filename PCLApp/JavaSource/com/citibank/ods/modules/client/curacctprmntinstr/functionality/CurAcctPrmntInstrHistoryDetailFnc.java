package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.functionality.ODSHistoryDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.TbgPointAcctInvstEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrHistEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrHistEntityVO;
import com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrHistoryDetailForm;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrHistoryDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgPointAcctInvstDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrHistDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.functionality;
 * @version 1.0
 * @author michele.monteiro,20/06/2007
 *  
 */

public class CurAcctPrmntInstrHistoryDetailFnc extends
    BaseCurAcctPrmntInstrDetailFnc implements ODSHistoryDetailFnc
{

  public void loadForConsult( BaseFncVO fncVO_ )
  {

    super.load( fncVO_ );

    CurAcctPrmntInstrHistoryDetailFncVO fncVO = ( CurAcctPrmntInstrHistoryDetailFncVO ) fncVO_;
    TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntity;
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity;

    TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();
    tplIpDocPrvtEntity = new TplIpDocPrvtEntity();

    TplCurAcctPrmntInstrHistDAO curAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrHistDAO();
    ArrayList listAssoc = curAcctPrmntInstrDAO.selectByPK(
                                                           fncVO.getCustNbr(),
                                                           fncVO.getProdAcctCode(),
                                                           fncVO.getProdUnderAcctCode(),
                                                           fncVO.getCurAcctPrmntInstrRefDate() );
    fncVO.setBaseTplIpDocPrvtEntityList( listAssoc );

    if ( listAssoc != null && listAssoc.size() > 0 )
    {
      for ( int i = 0; i < listAssoc.size(); i++ )
      {
        tplCurAcctPrmntInstrHistEntity = ( TplCurAcctPrmntInstrHistEntity ) listAssoc.get( i );

        tplIpDocPrvtEntity.getData().setIpDocCode(

        tplCurAcctPrmntInstrHistEntity.getData().getPrmntInstrCode() );
        tplIpDocPrvtEntity.getData().setCustNbr(
                                                 tplCurAcctPrmntInstrHistEntity.getData().getCustNbr() );

        tplIpDocPrvtEntity = ipDocPrvtDAO.find( tplIpDocPrvtEntity );

        tplCurAcctPrmntInstrHistEntity.getDataIP().setIpDocText(
                                                                 tplIpDocPrvtEntity.getData().getIpDocText() );
        tplCurAcctPrmntInstrHistEntity.getDataIP().setIpInvstCurAcctInd(
                                                                         tplIpDocPrvtEntity.getData().getIpInvstCurAcctInd() );
        if ( tplCurAcctPrmntInstrHistEntity.getDataIP().getIpInvstCurAcctInd().equals(
                                                                                       "S" ) )
        {
          TbgPointAcctInvstDAO tbgPointAcctInvstDAO = ODSDAOFactory.getInstance().getTbgPointAcctInvstDAO();
          TbgPointAcctInvstEntity tbgPointAcctInvstEntity = new TbgPointAcctInvstEntity();

          tbgPointAcctInvstEntity.getData().setCurAcctNbr(
                                                           fncVO.getCurAcctNbr().toString() );
          if ( tbgPointAcctInvstDAO.exists( tbgPointAcctInvstEntity ) )
          {
            tbgPointAcctInvstEntity = tbgPointAcctInvstDAO.find( tbgPointAcctInvstEntity );

            tplCurAcctPrmntInstrHistEntity.getDataPoint().setInvstCurAcctNbr(
                                                                              tbgPointAcctInvstEntity.getData().getInvstCurAcctNbr() );

          }
          else
          {
            tplCurAcctPrmntInstrHistEntity.getDataPoint().setInvstCurAcctNbr(
                                                                              "" );

          }
        }

      }
    }
  }

  public BaseFncVO createFncVO()
  {
    return new CurAcctPrmntInstrHistoryDetailFncVO();
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    CurAcctPrmntInstrHistoryDetailFncVO fncVO = ( CurAcctPrmntInstrHistoryDetailFncVO ) fncVO_;
    CurAcctPrmntInstrHistoryDetailForm form = ( CurAcctPrmntInstrHistoryDetailForm ) form_;

    TplCurAcctPrmntInstrHistEntity curAcctPrmntInstrHistEntity = null;
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

        curAcctPrmntInstrHistEntity = ( TplCurAcctPrmntInstrHistEntity ) ite.next();

        prmntCode = curAcctPrmntInstrHistEntity.getData().getPrmntInstrCode();
        prmntCodeString = prmntCode != null ? prmntCode.toString() : "";
        prmntText = curAcctPrmntInstrHistEntity.getDataIP().getIpDocText();
        prmntIndCCI = ODSConstraintDecoder.decodeIndicator( curAcctPrmntInstrHistEntity.getDataIP().getIpInvstCurAcctInd() );
        invstCur = curAcctPrmntInstrHistEntity.getDataPoint().getInvstCurAcctNbr() != null
                                                                                          ? curAcctPrmntInstrHistEntity.getDataPoint().getInvstCurAcctNbr()
                                                                                          : "";

        row = new String[] { prmntCodeString, prmntText, prmntIndCCI, invstCur };

        showList[ rowIndex ] = row;
      }
      form.setIpDocDomains( showList );

      form.setLastAuthUserId( ( ( TplCurAcctPrmntInstrHistEntityVO ) curAcctPrmntInstrHistEntity.getData() ).getLastAuthUserId() );
      form.setLastUpdUserId( ( ( TplCurAcctPrmntInstrHistEntityVO ) curAcctPrmntInstrHistEntity.getData() ).getLastUpdUserId() );

      if ( ( ( TplCurAcctPrmntInstrHistEntityVO ) curAcctPrmntInstrHistEntity.getData() ).getLastAuthDate() != null )
      {
        form.setLastAuthDate( formatDateTime( ( ( TplCurAcctPrmntInstrHistEntityVO ) curAcctPrmntInstrHistEntity.getData() ).getLastAuthDate() ) );
      }
      if ( ( ( TplCurAcctPrmntInstrHistEntityVO ) curAcctPrmntInstrHistEntity.getData() ).getLastUpdDate() != null )
      {
        form.setLastUpdDate( formatDateTime( ( ( TplCurAcctPrmntInstrHistEntityVO ) curAcctPrmntInstrHistEntity.getData() ).getLastUpdDate() ) );
      }

    }
    else
    {
      form.setIpDocDomains( null );
    }

  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrHistoryDetailFncVO fncVO = ( CurAcctPrmntInstrHistoryDetailFncVO ) fncVO_;
    CurAcctPrmntInstrHistoryDetailForm form = ( CurAcctPrmntInstrHistoryDetailForm ) form_;

    super.updateFncVOFromForm( form_, fncVO_ );

    //Obtem os dados da tela que serão inseridos no grid
    TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntityInsert = new TplCurAcctPrmntInstrHistEntity();
    TplCurAcctPrmntInstrHistEntityVO histEntityVO = ( TplCurAcctPrmntInstrHistEntityVO ) tplCurAcctPrmntInstrHistEntityInsert.getData();

    if ( form.getPrmntInstrCodeSrc() != null
         && form.getPrmntInstrCodeSrc().length() > 0 )
    {
      histEntityVO.setPrmntInstrCode( new BigInteger(
                                                      form.getPrmntInstrCodeSrc() ) );
    }
    if ( form.getPrmntInstrText() != null
         && form.getPrmntInstrText().length() > 0 )
    {
      tplCurAcctPrmntInstrHistEntityInsert.getDataIP().setIpDocText(
                                                                     form.getPrmntInstrText() );
    }
    if ( form.getPrmntInstrInvstCurAcctInd() != null
         && form.getPrmntInstrInvstCurAcctInd().length() > 0 )
    {
      tplCurAcctPrmntInstrHistEntityInsert.getDataIP().setIpInvstCurAcctInd(
                                                                             form.getPrmntInstrInvstCurAcctInd() );
    }

    if ( form.getCustNbrSrc() != null && form.getCustNbrSrc().length() > 0 )
    {
      histEntityVO.setCustNbr( new BigInteger( form.getCustNbrSrc() ) );
    }

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_TIMESTAMP );
    Date curAcctPrmntInstrRefDate;

    try
    {
      curAcctPrmntInstrRefDate = ( form.getCurAcctPrmntInstrRefDate() != null
                                   && form.getCurAcctPrmntInstrRefDate().length() > 0
                                                                                     ? formatter.parse( form.getCurAcctPrmntInstrRefDate() )
                                                                                     : null );

    }
    catch ( ParseException e_ )
    {
      curAcctPrmntInstrRefDate = null;

    }
    fncVO.setCurAcctPrmntInstrRefDate( curAcctPrmntInstrRefDate );

    if ( form.getInvstAcctCurNbr() != null
         && form.getInvstAcctCurNbr().length() > 0 )
    {
      tplCurAcctPrmntInstrHistEntityInsert.getDataPoint().setInvstCurAcctNbr(
                                                                              form.getInvstAcctCurNbr() );
    }
    histEntityVO.setProdAcctCode( new BigInteger( form.getProdAcctCode() ) );
    histEntityVO.setProdUnderAcctCode( new BigInteger(
                                                       form.getProdUnderAcctCode() ) );

    fncVO.setInsertIP( tplCurAcctPrmntInstrHistEntityInsert );

    //Setando as informações do Produto a ser deletado
    String delPrmnt = form.getSelectedIpGrid();
    String delIpDesc = form.getSelectedIpDescGrid();
    String delIndicatorGrid = form.getSelectedIpIndGrid();
    String delInvstCurAcct = form.getSelectedInvstCurAcctGrid();

    if ( !delPrmnt.equals( "" ) && !delInvstCurAcct.equals( "" )
         && !delIndicatorGrid.equals( "" ) && !delIpDesc.equals( "" ) )
    {
      TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntityDelete = new TplCurAcctPrmntInstrHistEntity();
      TplCurAcctPrmntInstrHistEntityVO tplCurAcctPrmntInstrHistEntityVO = ( TplCurAcctPrmntInstrHistEntityVO ) tplCurAcctPrmntInstrHistEntityDelete.getData();

      tplCurAcctPrmntInstrHistEntityVO.setPrmntInstrCode( new BigInteger(
                                                                          form.getPrmntInstrCodeSrc() ) );
      tplCurAcctPrmntInstrHistEntityDelete.getDataIP().setIpDocText( delIpDesc );
      tplCurAcctPrmntInstrHistEntityDelete.getDataIP().setIpInvstCurAcctInd(
                                                                             delIndicatorGrid );
      tplCurAcctPrmntInstrHistEntityDelete.getDataPoint().setInvstCurAcctNbr(
                                                                              delInvstCurAcct );

      fncVO.setDeleteIP( tplCurAcctPrmntInstrHistEntityDelete );

    }

  }

}