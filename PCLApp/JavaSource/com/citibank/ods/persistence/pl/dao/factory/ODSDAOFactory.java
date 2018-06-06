package com.citibank.ods.persistence.pl.dao.factory;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.factory.BaseDAOFactory;
import com.citibank.ods.persistence.bg.dao.TbgBankDAO;
import com.citibank.ods.persistence.bg.dao.TbgBranchDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustCellDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustMailDAO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.bg.dao.TbgPointAcctInvstDAO;
import com.citibank.ods.persistence.pl.dao.*;

/**
 * Title: Classe de métodos abstratos DAO do sistema ODS
 * @author ralf.davi.filho
 * @version 1.0
 */
public abstract class ODSDAOFactory extends BaseDAOFactory
{

  /**
   * Comment for <code>C_DAO_FACTORY_SYSTEM</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private static final String C_DAO_FACTORY_SYSTEM = "ods";

  /**
   * Comment for <code>ms_instance</code>: DAO Factory singleton instance
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  protected static ODSDAOFactory ms_instance;

  /**
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public static void bootstrap()
  {
    if ( ms_instance != null )
      throw new UnexpectedException(
                                     "bootstrap cannot be invoke more than once." );
    ms_instance = ( ODSDAOFactory ) BaseDAOFactory.createDAOFactory( C_DAO_FACTORY_SYSTEM );
  }

  /**
   * 
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public ODSDAOFactory()
  {
    if ( ms_instance != null )
    {
      throw new UnexpectedException( "This constructor cannot be called twice" );
    }
  }

  /**
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public static final ODSDAOFactory getInstance()
  {
    return ms_instance;
  }

  /**
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public abstract TplOfficerTypeDAO getTplOfficerTypeDAO();

  public abstract TplOfficerCmplDAO getTplOfficerCmplDAO();

  public abstract TplOfficerCmplMovDAO getTplOfficerCmplMovDAO();

  public abstract TplOfficerCmplHistDAO getTplOfficerCmplHistDAO();

  public abstract TplCustomerPrvtCmplDAO getTplCustomerPrvtCmplDAO();

  public abstract TplCustomerPrvtCmplMovDAO getTplCustomerPrvtCmplMovDAO();

  public abstract TplCustomerPrvtCmplHistDAO getTplCustomerPrvtCmplHistDAO();

  public abstract TplAggrProdPrvtDAO getTplAggrProdPrvtDAO();

  public abstract TplAggrProdPrvtHistDAO getTplAggrProdPrvtHistDAO();

  public abstract TplClassCmplcDAO getTplClassCmplcDAO();
  
  public abstract TplCustomerPrvtTypeDAO getTplCustomerPrvtTypeDAO();

  public abstract TplProdRiskCatPrvtDAO getTplProdRiskCatPrvtDAO();

  public abstract TplProdRiskCatPrvtMovDAO getTplProdRiskCatPrvtMovDAO();

  public abstract TplProdRiskCatPrvtHistDAO getTplProdRiskCatPrvtHistDAO();

  public abstract TplProductDAO getTplProductDAO();
  
  public abstract TplProductMovDAO getTplProductMovDAO();

  public abstract TplProductHistDAO getTplProductHistDAO();
  
  public abstract TplProductCorpDAO getTplProductCorpDAO();
  
  public abstract TplProductCorpMovDAO getTplProductCorpMovDAO();
  
  public abstract TplProductCorpHistDAO getTplProductCorpHistDAO();

  public abstract TplDocTransferDAO getTplDocTransferDAO();

  public abstract TplDocTransferMovDAO getTplDocTransferMovDAO();

  public abstract TplDocTransferHistDAO getTplDocTransferHistDAO();

  public abstract TplIpDocCallbackDAO getTplIpDocCallbackDAO();

  public abstract TplIpDocCallbackMovDAO getTplIpDocCallbackMovDAO();

  public abstract TplIpDocCallbackHistDAO getTplIpDocCallbackHistDAO();

  public abstract TplIpDocPrvtMovDAO getTplIpDocPrvtMovDAO();

  public abstract TplIpDocPrvtHistDAO getTplIpDocPrvtHistDAO();

  /**
   * Recupera DAO para acessar tabela de Processos de Carga.
   * 
   * @return TplLoadProcExecRequestDAO - DAO para acessar tabela de Processos de
   *         Carga.
   */
  public abstract TplLoadProcessDAO getTplLoadProcessDAO();

  /**
   * Recupera DAO para acessar tabela de Execução de Processos de Carga.
   * 
   * @return TplLoadProcExecRequestDAO - DAO para acessar tabela de Execução de
   *         Processos de Carga.
   */
  public abstract TplLoadProcExecDAO getTplLoadProcExecDAO();

  public abstract TplPotentialWealthDAO getTplPotentialWealthDAO();

  public abstract TplCustomerPrvtDAO getTplCustomerPrvtDAO();
  
  public abstract TplProdSubAssetMovDAO getTplProdSubAssetMovDAO();
  
  public abstract TplProdSubAssetHistDAO getTplProdSubAssetHistDAO();
  
  public abstract TplProdSubAssetDAO getTplProdSubAssetDAO();
  
  public abstract TplProdAssetTypeMovDAO getTplProdAssetTypeMovDAO();
  
  public abstract TplProdAssetTypeHistDAO getTplProdAssetTypeHistDAO();
  
  public abstract TplProdAssetTypeDAO getTplProdAssetTypeDAO();
  
  public abstract TplProdAssetMovDAO getTplProdAssetMovDAO();
  
  public abstract TplProdAssetHistDAO getTplProdAssetHistDAO();
  
  public abstract TplProdAssetDAO getTplProdAssetDAO();  

  public abstract TplProdQlfyPrvtMovDAO getTplProdQlfyPrvtMovDAO();

  public abstract TplProdQlfyPrvtHistDAO getTplProdQlfyPrvtHistDAO();

  public abstract TplProdQlfyPrvtDAO getTplProdQlfyPrvtDAO();

  public abstract TplProductFamilyPrvtDAO getTplProductFamilyPrvtDAO();

  public abstract TplProductFamilyPrvtMovDAO getTplProductFamilyPrvtMovDAO();

  public abstract TplProductFamilyPrvtHistDAO getTplProductFamilyPrvtHistDAO();

  public abstract TplProdSubFamlPrvtDAO getTplProductSubFamilyPrvtDAO();

  public abstract TplProdSubFamlPrvtMovDAO getTplProductSubFamilyPrvtMovDAO();

  public abstract TplProdSubFamlPrvtHistDAO getTplProductSubFamilyPrvtHistDAO();

  public abstract TplPlayerDAO getTplPlayerDAO();

  public abstract TplPlayerMovDAO getTplPlayerMovDAO();

  public abstract TplPlayerHistDAO getTplPlayerHistDAO();

  public abstract TplErDAO getTplErDAO();

  public abstract TplErMovDAO getTplErMovDAO();

  public abstract TplErHistDAO getTplErHistDAO();
  
  public abstract TplErEmDAO getTplErEmDAO();

  public abstract TplErEmMovDAO getTplErEmMovDAO();

  public abstract TplErEmHistDAO getTplErEmHistDAO();

  public abstract TplPlayerRoleDAO getTplPlayerRoleDAO();

  public abstract TplPlayerRoleHistDAO getTplPlayerRoleHistDAO();

  public abstract TplPlayerRoleMovDAO getTplPlayerRoleMovDAO();

  public abstract TplProdPlayerRoleDAO getTplProdPlayerRoleDAO();

  public abstract TplProdPlayerRoleMovDAO getTplProdPlayerRoleMovDAO();

  public abstract TplProdPlayerRoleHistDAO getTplProdPlayerRoleHistDAO();

  public abstract TbgSegmentDAO getTbgSegmentDAO();

  public abstract TbgOfficerDAO getTbgOfficerDAO();

  public abstract TbgCustAddressDAO getTbgCustAddressDAO();

  public abstract TbgCustCellDAO getTbgCustCellDAO();

  public abstract TbgCustMailDAO getTbgCustMailDAO();

  public abstract TbgBranchDAO getTbgBranchDAO();

  public abstract TbgBankDAO getTbgBankDAO();

  /**
   * Recupera DAO para acessar tabela de Interface de Entrada de Colunas.
   * 
   * @return TplEntryInterColumnDAO - DAO para acessar tabela de Interface de
   *         Entrada de Colunas.
   */
  public abstract TplEntryInterColumnDAO getTplEntryInterColumnDAO();

  /**
   * Recupera DAO para acessar tabela de Interface de Entrada.
   * 
   * @return TplEntryInterDAO - DAO para acessar tabela de Interface de Entrada.
   */
  public abstract TplEntryInterDAO getTplEntryInterDAO();

  /**
   * Recupera DAO para acessar tabela de Requerimeno de Execução de Processos de
   * Carga.
   * 
   * @return TplLoadProcExecRequestDAO - DAO para acessar tabela de Requerimeno
   *         de Execução de Processos de Carga.
   */
  public abstract TplLoadProcExecRequestDAO getTplLoadProcExecRequestDAO();

  /**
   * Recupera DAO para acessar tabela de Críticas Lógicas.
   * 
   * @return TplLoadProcExecRequestDAO - DAO para acessar tabela de Críticas
   *         Lógicas.
   */
  public abstract TplLogicCritDAO getTplLogicCritDAO();

  /**
   * Recupera DAO para acessar tabela de domínios de críticas lógicas.
   * 
   * @return TplLoadProcExecRequestDAO - DAO para acessar tabela de domínios de
   *         críticas lógicas.
   */
  public abstract TplLogicCritDomainDAO getTplLogicCritDomainDAO();

  /**
   * Recupera DAO para acessar tabela de contas de produtos.
   * 
   * @return To3ProductAccountDAO - DAO para acessar tabela de contas de
   *         produtos.
   */
  public abstract To3ProductAccountDAO getTo3ProductAccountDAO();

  /**
   * Recupera DAO para acessar tabela de histórico de contas de produtos.
   * 
   * @return To3ProductAccountDAO - DAO para acessar tabela de histótico contas
   *         de produtos.
   */
  public abstract To3ProductAccountHistDAO getTo3ProductAccountHistDAO();

  public abstract TplContactCustDAO getTplContactCustDAO();

  public abstract TplRelationPrvtDAO getTplRelationPrvtDAO();

  public abstract TplPortfolioPrvtDAO getTplPortfolioPrvtDAO();

  public abstract TplRelationEgDAO getTplRelationEgDAO();

  public abstract TplRelationEgMovDAO getTplRelationEgMovDAO();

  public abstract TplRelationEgHistDAO getTplRelationEgHistDAO();

  public abstract TplIpDocPrvtDAO getTplIpDocPrvtDAO();
  
  public abstract TplIpDocTransFinancDAO getTplIpDocTransFinancDAO(); 

  /**
   * Recupera DAO para acessar tabela de memória de risco.
   * 
   * @return TplMrDocPrvtDAO - DAO para acessar tabela de memória de risco.
   */
  public abstract TplMrDocPrvtDAO getTplMrDocPrvtDAO();

  /**
   * Recupera DAO para acessar tabela de histórico de memória de risco.
   * 
   * @return TplMrDocPrvtHistDAO - DAO para acessar tabela de histórico de
   *         memória de risco.
   */
  public abstract TplMrDocPrvtHistDAO getTplMrDocPrvtHistDAO();

  /**
   * Recupera DAO para acessar tabela de movimento de memória de risco.
   * 
   * @return TplMrDocPrvtMovDAO - DAO para acessar tabela de movimento de
   *         memória de risco.
   */
  public abstract TplMrDocPrvtMovDAO getTplMrDocPrvtMovDAO();

  /**
   * Recupera DAO para acessar tabela de telefones de confirmação de memória de
   * risco.
   * 
   * @return TplMrDocCallbackDAO - DAO para acessar tabela de telefones de
   *         confirmaçã de memória de risco.
   */
  public abstract TplMrDocCallbackDAO getTplMrDocCallbackDAO();

  /**
   * Recupera DAO para acessar tabela de histórico de telefones de confirmação
   * de memória de risco.
   * 
   * @return TplMrDocCallbackHistDAO - DAO para acessar tabela de histórico de
   *         telefones de confirmação de memória de risco.
   */
  public abstract TplMrDocCallbackHistDAO getTplMrDocCallbackHistDAO();

  /**
   * Recupera DAO para acessar tabela de movimento de telefones de confirmação
   * de memória de risco.
   * 
   * @return TplMrDocCallbackMovDAO - DAO para acessar tabela de movimento de
   *         telefones de confirmação de memória de risco.
   */
  public abstract TplMrDocCallbackMovDAO getTplMrDocCallbackMovDAO();

  public abstract TplRoleCustDAO getTplRoleCustDAO();

  /**
   * Recupera DAO para acessar tabela de sigla do sistema
   * 
   * @return TbgSystemDAO - DAO para acessar tabela de sigla do sistema para
   *         popular o combo sigla do sistema na tela de contrato.
   */
  public abstract TbgSystemDAO getTbgSystemDAO();

  /**
   * Recupera DAO para acessar tabela de segmento do sistema
   * 
   * @return TbgSystemSegmentDAO - DAO para acessar tabela de segmento do
   *         sistema para popular o combo sigla do sistema na tela de contrato.
   */
  public abstract TbgSystemSegmentDAO getTbgSystemSegmentDAO();

  /**
   * Recupera DAO para acessar tabela de de movimento de contratos
   * 
   * @return To3ProductAccountMovDAO - DAO para acessar tabela de movimento de
   *         contrato.
   */
  public abstract To3ProductAccountMovDAO getTo3ProductAccountMovDAO();

  /**
   * Recupera DAO para acessar tabela associação de conta corrente à IP
   * 
   * @return TplCurAcctPrmntInstrDAO - DAO para acessar tabela de associação de
   *         conta corrente à IP.
   */
  public abstract TplCurAcctPrmntInstrDAO getTplCurAcctPrmntInstrDAO();

  /**
   * Recupera DAO para acessar tabela associação de conta corrente à IP
   * 
   * @return TplCurAcctPrmntInstrMovDAO - DAO para acessar tabela de movimento
   *         associação de conta corrente à IP.
   */
  public abstract TplCurAcctPrmntInstrMovDAO getTplCurAcctPrmntInstrMovDAO();

  /**
   * Recupera DAO para acessar tabela associação de conta corrente à IP
   * 
   * @return TplCurAcctPrmntInstrHistDAO - DAO para acessar tabela de histórico
   *         associação de conta corrente à IP.
   */
  public abstract TplCurAcctPrmntInstrHistDAO getTplCurAcctPrmntInstrHistDAO();

  public abstract TbgPointAcctInvstDAO getTbgPointAcctInvstDAO();

  /**
   * Recupera DAO para acessar tabela de corretora
   * 
   * @return TplBrokerDAO - DAO para acessar tabela de corretoras
   */
  public abstract TplBrokerDAO getTplBrokerDAO();

  /**
   * Recupera DAO para acessar tabela de movimento corretora
   * 
   * @return TplBrokerMovDAO - DAO para acessar tabela de movimento de
   *         corretoras
   */
  public abstract TplBrokerMovDAO getTplBrokerMovDAO();

  /**
   * Recupera DAO para acessar tabela de histórico corretora
   * 
   * @return TplBrokerHistDAO - DAO para acessar tabela de histórico corretoras
   */
  public abstract TplBrokerHistDAO getTplBrokerHistDAO();

  /**
   * Recupera DAO para acessar tabela de associação corretora x carteira
   * 
   * @return TplBrokerMovDAO - DAO para acessar tabela de movimento de
   *         associação corretora x carteira
   */
  public abstract TplBkrPortfMgmtDAO getTplBkrPortfMgmtDAO();

  /**
   * Recupera DAO para acessar tabela de movimento associação corretora x
   * carteira
   * 
   * @return TplBrokerMovDAO - DAO para acessar tabela de movimento de
   *         associação corretora x carteira
   */
  public abstract TplBkrPortfMgmtMovDAO getTplBkrPortfMgmtMovDAO();

  /**
   * Recupera DAO para acessar tabela de histórico de associação corretora x
   * carteira
   * 
   * @return TplBrokerHistDAO - DAO para acessar tabela de histórico de
   *         associação corretora x carteira
   */
  public abstract TplBkrPortfMgmtHistDAO getTplBkrPortfMgmtHistDAO();
  
  /**
  * Recupera DAO para acessar tabelas de movimento centralizadas
  * 
  * @return TplCentrApprovalMovDAO
  */
  public abstract TplCentrApprovalMovDAO getTplCentrApprovalMovDAO();
  
  /**
  * Recupera DAO para acessar a tabela TPL_REASON_END_RELATION
  * 
  * @return TplReasonEndRelationDAO
  */
  public abstract TplReasonEndRelationDAO getTplReasonEndRelationDAO();
  
  /**
  * Recupera DAO para acessar a tabela TPL_RELTN_CLASS_EQUITY
  * 
  * @return TplReltnClassEquityDAO
  */
  public abstract TplReltnClassEquityDAO getTplReltnClassEquityDAO();
  
  public abstract TplAssocClassProdRdipDAO getTplAssocClassProdRdipDAO();
  
  public abstract TplShortNamePlayerMovDAO getTplShortNamePlayerMovDAO();
  
  
  public abstract TplRiskFamilyProdPlayerDAO getTplRiskFamilyProdPlayerDAO();
  
  public abstract TplRiskFamilyProdPlayerMovDAO getTplRiskFamilyProdPlayerMovDAO();
  
  public abstract TplRiskFamilyProdPlayerHistDAO getTplRiskFamilyProdPlayerHistDAO();
  
  public abstract TplShortNamePlayerHistDAO getTplShortNamePlayerHistDAO();
  
  public abstract TplShortNamePlayerDAO getTplShortNamePlayerDAO(); 
  //Fase 3 
  public abstract TbgClassTypeFundAnbidDAO getTbgClassTypeFundAnbidDAO();
  
  public abstract TplAssetClassOnesrcDao getTplAssetClassOnesrcDao();
}