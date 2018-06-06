package com.citibank.ods.modules.product.player.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.valueobject.TplShortNamePlayerVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.BasePlayerDetailFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;

/**
 * @author atilio.l.araujo
 *  
 */

public class BasePlayerDetailForm extends BaseForm implements PlayerDetailable
{

  // Data e Hora da Ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do Usuario que efetuou a ultima atualizacao registro.
  private String m_lastUpdUserId = "";

  // Endereco do Player
  private String m_plyrAddrText = "";

  // Restricoes sobre aprovacao
  private String m_plyrApprvRstrnText = "";

  // Campo para Observacao
  private String m_plyrCmntText = "";

  // CNPJ do Player.
  private String m_plyrCnpjNbr = "";

  // Data do due diligence: visita tecnica ate a empresa alvo, avaliando
  // condicoes fisicas, tecnicas e de negocio.
  private String m_plyrDueDlgDate = "";

  // Data de conclusao do due dilligence
  private String m_plyrDueDlgEndDate = "";

  // Indica se o due dilligence foi ou nao executado.
  private String m_plyrDueDlgExecInd = "";

  // Data de renovacao do due dilligence
  private String m_plyrDueDlgRnwDate = "";

  // Data de aprovacao no comite de Investimento do Private
  private String m_plyrInvstCmtteApprvDate = "";

  // Nome do Player
  private String m_plyrName = "";

  // Servicos Prestados pelo Player
  private String m_plyrSuplServText = "";

  //Codigo do Player no Drive
  private String m_plyrDvCode = "";

  // Papéis do Player escolhidos no momento da inserção.
  private String[] m_plyrRoleNames;
  
  
  // Mnemonicos
  private String issueShortNameText;
  private String shortNameIdx;
  private List<ShortNameVO> issueShortNameList = null;  
  private String loadIssue = "S";
  
  public BasePlayerDetailForm(){
	  issueShortNameList = new ArrayList<ShortNameVO>();
  }
/**
   * @return Returns m_lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field m_lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns m_lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field m_lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns m_plyrAddrText.
   */
  public String getPlyrAddrText()
  {
    return m_plyrAddrText;
  }

  /**
   * @param plyrAddrText_ Field m_plyrAddrText to be setted.
   */
  public void setPlyrAddrText( String plyrAddrText_ )
  {
    m_plyrAddrText = plyrAddrText_;
  }

  /**
   * @return Returns m_plyrApprvRstrnText.
   */
  public String getPlyrApprvRstrnText()
  {
    return m_plyrApprvRstrnText;
  }

  /**
   * @param plyrApprvRstrnText_ Field m_plyrApprvRstrnText to be setted.
   */
  public void setPlyrApprvRstrnText( String plyrApprvRstrnText_ )
  {
    m_plyrApprvRstrnText = plyrApprvRstrnText_;
  }

  /**
   * @return Returns m_plyrCmntText.
   */
  public String getPlyrCmntText()
  {
    return m_plyrCmntText;
  }

  /**
   * @param plyrCmntText_ Field m_plyrCmntText to be setted.
   */
  public void setPlyrCmntText( String plyrCmntText_ )
  {
    m_plyrCmntText = plyrCmntText_;
  }

  /**
   * @return Returns m_plyrCnpjNbr.
   */
  public String getPlyrCnpjNbr()
  {
    return m_plyrCnpjNbr;
  }

  /**
   * @param plyrCnpjNbr_ Field m_plyrCnpjNbr to be setted.
   */
  public void setPlyrCnpjNbr( String plyrCnpjNbr_ )
  {
    m_plyrCnpjNbr = removeMask( plyrCnpjNbr_ );
  }

  /**
   * @return Returns m_plyrDueDlgDate.
   */
  public String getPlyrDueDlgDate()
  {
    return m_plyrDueDlgDate;
  }

  /**
   * @param plyrDueDlgDate_ Field m_plyrDueDlgDate to be setted.
   */
  public void setPlyrDueDlgDate( String plyrDueDlgDate_ )
  {
    m_plyrDueDlgDate = plyrDueDlgDate_;
  }

  /**
   * @return Returns m_plyrDueDlgEndDate.
   */
  public String getPlyrDueDlgEndDate()
  {
    return m_plyrDueDlgEndDate;
  }

  /**
   * @param plyrDueDlgEndDate_ Field m_plyrDueDlgEndDate to be setted.
   */
  public void setPlyrDueDlgEndDate( String plyrDueDlgEndDate_ )
  {
    m_plyrDueDlgEndDate = plyrDueDlgEndDate_;
  }

  /**
   * @return Returns m_plyrDueDlgExecInd.
   */
  public String getPlyrDueDlgExecInd()
  {
    return m_plyrDueDlgExecInd;
  }

  /**
   * @param plyrDueDlgExecInd_ Field m_plyrDueDlgExecInd to be setted.
   */
  public void setPlyrDueDlgExecInd( String plyrDueDlgExecInd_ )
  {
    m_plyrDueDlgExecInd = plyrDueDlgExecInd_;
  }

  /**
   * @return Returns m_plyrDueDlgRnwDate.
   */
  public String getPlyrDueDlgRnwDate()
  {
    return m_plyrDueDlgRnwDate;
  }

  /**
   * @param plyrDueDlgRnwDate_ Field m_plyrDueDlgRnwDate to be setted.
   */
  public void setPlyrDueDlgRnwDate( String plyrDueDlgRnwDate_ )
  {
    m_plyrDueDlgRnwDate = plyrDueDlgRnwDate_;
  }

  /**
   * @return Returns m_plyrInvstCmtteApprvDate.
   */
  public String getPlyrInvstCmtteApprvDate()
  {
    return m_plyrInvstCmtteApprvDate;
  }

  /**
   * @param plyrInvstCmtteApprvDate_ Field m_plyrInvstCmtteApprvDate to be
   *          setted.
   */
  public void setPlyrInvstCmtteApprvDate( String plyrInvstCmtteApprvDate_ )
  {
    m_plyrInvstCmtteApprvDate = plyrInvstCmtteApprvDate_;
  }

  /**
   * @return Returns m_plyrName.
   */
  public String getPlyrName()
  {
    return m_plyrName;
  }

  /**
   * @param plyrName_ Field m_plyrName to be setted.
   */
  public void setPlyrName( String plyrName_ )
  {
    m_plyrName = plyrName_;
  }

  /**
   * @return Returns m_plyrSuplServText.
   */
  public String getPlyrSuplServText()
  {
    return m_plyrSuplServText;
  }

  /**
   * @param plyrSuplServText_ Field m_plyrSuplServText to be setted.
   */
  public void setPlyrSuplServText( String plyrSuplServText_ )
  {
    m_plyrSuplServText = plyrSuplServText_;
  }

  /**
   * @return Returns plyrRoleNames.
   */
  public String[] getPlyrRoleNames()
  {
    return m_plyrRoleNames;
  }

  /**
   * @param plyrRoleNames_ Field plyrRoleNames to be setted.
   */
  public void setPlyrRoleNames( String[] plyrRoleNames_ )
  {
    m_plyrRoleNames = plyrRoleNames_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerDetailable#getSelectedPlyrCnpjNbr()
   */
  public String getSelectedPlyrCnpjNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerDetailable#setSelectedPlyrCnpjNbr(java.lang.String)
   */
  public void setSelectedPlyrCnpjNbr( String setSelectedPlyrCnpjNbr_ )
  {
    m_plyrCnpjNbr = setSelectedPlyrCnpjNbr_;
  }

  public String getPlyrDvCode()
  {
    return m_plyrDvCode;
  }

  public void setPlyrDvCode( String plyrDvCode_ )
  {
    m_plyrDvCode = plyrDvCode_;
  }

  public List<ShortNameVO> getIssueShortNameList() {
	if(issueShortNameList == null){
		issueShortNameList = new ArrayList<ShortNameVO>();
	}  
	return issueShortNameList;
  }

  public void setIssueShortNameList(List<ShortNameVO> issueShortName) {
	this.issueShortNameList = issueShortName;
  }
  
  public String getIssueShortNameText() {
	return issueShortNameText;
  }

  public void setIssueShortNameText(String issueShortNameText) {
	this.issueShortNameText = issueShortNameText;
  }
  public String getShortNameIdx() {
	return shortNameIdx;
  }

  public void setShortNameIdx(String shortNameIdx) {
	this.shortNameIdx = shortNameIdx;
  }
  
	public String getLoadIssue() {
		return loadIssue;
	}
	
	public void setLoadIssue(String loadIssue) {
		this.loadIssue = loadIssue;
	}
  
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_CNPJ_NBR_DESCRIPTION,
                                    m_plyrCnpjNbr,
                                    BaseTplPlayerEntity.C_PLYR_CNPJ_NBR_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_NAME_DESCRIPTION,
                                    m_plyrName,
                                    BaseTplPlayerEntity.C_PLYR_NAME_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_ADDR_TEXT_DESCRIPTION,
                                    m_plyrAddrText,
                                    BaseTplPlayerEntity.C_PLYR_ADDR_TEXT_SIZE,
                                    errors );

    ODSValidator.validateDate(
                               BasePlayerDetailFncVO.C_PLYR_DUE_DLG_DATE_DESCRIPTION,
                               m_plyrDueDlgDate, errors );

    ODSValidator.validateDate(
                               BasePlayerDetailFncVO.C_PLYR_DUE_DLG_END_DATE_DESCRIPTION,
                               m_plyrDueDlgEndDate, errors );

    ODSValidator.validateDate(
                               BasePlayerDetailFncVO.C_PLYR_INVST_CMTTE_APPRV_DATE_DESCRIPTION,
                               m_plyrInvstCmtteApprvDate, errors );

    ODSValidator.validateDate(
                               BasePlayerDetailFncVO.C_PLYR_DUE_DLG_RNW_DATE_DESCRIPTION,
                               m_plyrDueDlgRnwDate, errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_APPRV_RSTRN_TEXT_DESCRIPTION,
                                    m_plyrApprvRstrnText,
                                    BaseTplPlayerEntity.C_PLYR_APPRV_RSTRN_TEXT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_SUPL_SERV_TEXT_DESCRIPTION,
                                    m_plyrSuplServText,
                                    BaseTplPlayerEntity.C_PLYR_SUPL_SERV_TEXT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_CMNT_TEXT_DESCRIPTION,
                                    m_plyrCmntText,
                                    BaseTplPlayerEntity.C_PLYR_CMNT_TEXT_SIZE,
                                    errors );
    return errors;
  }
}