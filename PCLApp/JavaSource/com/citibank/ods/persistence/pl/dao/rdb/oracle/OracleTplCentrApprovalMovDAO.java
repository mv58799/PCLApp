/*
 * Created on 08/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.TplCentrApprovalMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;


/**
 * @author citi
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OracleTplCentrApprovalMovDAO extends BaseOracleDAO  implements TplCentrApprovalMovDAO {
		
	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplCentrApprovalMovDAO#list(java.math.BigInteger, java.lang.String, java.lang.String, java.lang.String)
	 */
	public DataSet list(String m_moduleProcessText_, String lastUpdUserId_, boolean hasAccess5001) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try
		{
		  connection = OracleODSDAOFactory.getConnection();		  
		  query.append(" SELECT MODULE_CODE, ");
		  query.append("   PROCESS_CODE, ");
		  query.append("   MODULE_PROCESS_TEXT, ");
		  query.append("   PROCESS_TEXT, ");
		  query.append("   last_upd_user_id, ");
		  query.append("   last_upd_date, ");
		  query.append("   DECODE(opern_code, 'A', 'Alteração', 'I', 'Inclusão', 'E', 'Exclusão') opern_text, ");
		  query.append("   opern_code ");
		  query.append(" FROM ");
		  query.append("   (SELECT 'Produto - Classe de Ativo' MODULE_PROCESS_TEXT, ");
		  query.append("     'ProdAsset.ProdAssetMovementDetail' MODULE_CODE, ");
		  query.append("     lpad(asse_mov.asset_class_code, 8, '0') ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     asse_mov.asset_class_text PROCESS_TEXT, ");
		  query.append("     asse_mov.asset_class_last_upd_user_id last_upd_user_id, ");
		  query.append("     asse_mov.asset_class_last_upd_date last_upd_date, ");
		  query.append("     asse_mov.asset_class_opern_code opern_code ");
		  query.append("   FROM pl.tpl_asset_class_mov asse_mov ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Sub Classe de Ativo' MODULE_PROCESS_TEXT, ");
		  query.append("     'ProdSubAsset.ProdSubAssetMovementDetail' MODULE_CODE, ");
		  query.append("     lpad(sub_asse_mov.sub_asset_class_code, 8, '0') ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     sub_asse_mov.sub_asset_class_text PROCESS_TEXT, ");
		  query.append("     sub_asse_mov.sub_asset_last_upd_user_id last_upd_user_id, ");
		  query.append("     sub_asse_mov.sub_asset_last_upd_date last_upd_date, ");
		  query.append("     sub_asse_mov.sub_asset_opern_code opern_code ");
		  query.append("   FROM pl.tpl_sub_asset_class_mov sub_asse_mov ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Tipo de Ativo' MODULE_PROCESS_TEXT, ");
		  query.append("     'ProdAssetType.ProdAssetTypeMovementDetail' MODULE_CODE, ");
		  query.append("     lpad(type_mov.asset_type_code, 8, '0') ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     type_mov.asset_type_text PROCESS_TEXT, ");
		  query.append("     type_mov.asset_type_last_upd_user_id last_upd_user_id, ");
		  query.append("     type_mov.asset_type_last_upd_date last_upd_date, ");
		  query.append("     type_mov.asset_type_opern_code opern_code ");
		  query.append("   FROM pl.tpl_asset_type_mov type_mov ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Família' MODULE_PROCESS_TEXT, ");
		  query.append("     'ProductFamilyPrvt.ProductFamilyPrvtMovementDetail' MODULE_CODE, ");
		  query.append("     lpad(FAM_MOV.PROD_FAML_CODE, 8, '0') ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     FAM_MOV.PROD_FAML_TEXT PROCESS_TEXT, ");
		  query.append("     FAM_MOV.LAST_UPD_USER_ID last_upd_user_id, ");
		  query.append("     FAM_MOV.LAST_UPD_DATE LAST_UPD_DATE, ");
		  query.append("     FAM_MOV.OPERN_CODE OPERN_CODE ");
		  query.append("   FROM PL.TPL_PRODUCT_FAMILY_PRVT_MOV FAM_MOV ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Sub-Família' MODULE_PROCESS_TEXT, ");
		  query.append("     'ProdSubFamlPrvt.ProdSubFamlPrvtMovementDetail' MODULE_CODE, ");
		  query.append("     lpad(sub.PROD_SUB_FAML_CODE, 8, '0') ");
		  query.append("     || ',' ");
		  query.append("     || lpad(sub.prod_faml_code, 8, '0') PROCESS_CODE, ");
		  query.append("     sub.PROD_SUB_FAML_TEXT PROCESS_TEXT, ");
		  query.append("     sub.LAST_UPD_USER_ID last_upd_user_id, ");
		  query.append("     sub.LAST_UPD_DATE LAST_UPD_DATE, ");
		  query.append("     sub.OPERN_CODE OPERN_CODE ");
		  query.append("   FROM PL.TPL_PROD_SUB_FAML_PRVT_MOV sub ");
		  query.append("   LEFT JOIN PL.TPL_PRODUCT_FAMILY_PRVT prod ");
		  query.append("   ON sub.PROD_FAML_CODE = prod.PROD_FAML_CODE ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Produtos' MODULE_PROCESS_TEXT, ");
		  query.append("     'Product.ProductMovementDetail' MODULE_CODE, ");
		  query.append("     PROD.PROD_CODE ");
		  query.append("     || ',' ");
		  query.append("     || PROD.SYS_CODE ");
		  query.append("     || ',' ");
		  query.append("     || PROD.SYS_SEG_CODE PROCESS_CODE, ");
		  query.append("     PROD.PROD_TEXT PROCESS_TEXT, ");
		  query.append("     PROD.LAST_UPD_USER_ID, ");
		  query.append("     PROD.LAST_UPD_DATE, ");
		  query.append("     PROD.OPERN_CODE ");
		  query.append("   FROM PL.TPL_PRODUCT_MOV PROD, ");
		  query.append("     PL.TPL_PRODUCT_SUB_FAMILY_PRVT SUB, ");
		  query.append("     PL.TPL_PRODUCT_FAMILY_PRVT PROD_FAML ");
		  query.append("   WHERE PROD.PROD_SUB_FAML_CODE = SUB.PROD_SUB_FAML_CODE(+) ");
		  query.append("   AND SUB.PROD_FAML_CODE        = PROD_FAML.PROD_FAML_CODE(+) ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT DISTINCT 'Produto - Player' MODULE_PROCESS_TEXT, ");
		  query.append("     'Player.PlayerMovementDetail' MODULE_CODE, ");
		  query.append("     PL.TPL_PLAYER_MOV.PLYR_CNPJ_NBR ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     PL.TPL_PLAYER_MOV.PLYR_ADDR_TEXT PROCESS_TEXT, ");
		  query.append("     PL.TPL_PLAYER_MOV.LAST_UPD_USER_ID, ");
		  query.append("     PL.TPL_PLAYER_MOV.LAST_UPD_DATE, ");
		  query.append("     PL.TPL_PLAYER_MOV.OPERN_CODE ");
		  query.append("   FROM PL.TPL_PLAYER_MOV, ");
		  query.append("     PL.TPL_PLAYER_ROLE_MOV ");
		  query.append("   WHERE PL.TPL_PLAYER_MOV.PLYR_CNPJ_NBR = PL.TPL_PLAYER_ROLE_MOV.PLYR_CNPJ_NBR ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Qualificação' MODULE_PROCESS_TEXT, ");
		  query.append("     'ProdQlfyPrvt.ProdQlfyPrvtMovementDetail' MODULE_CODE, ");
		  query.append("     PROD_QLFY_CODE ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     PROD_QLFY_TEXT PROCESS_TEXT, ");
		  query.append("     LAST_UPD_USER_ID, ");
		  query.append("     LAST_UPD_DATE, ");
		  query.append("     OPERN_CODE ");
		  query.append("   FROM PL.TPL_PROD_QLFY_PRVT_MOV ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Subscrição - Fundos Imobiliários/Outros' MODULE_PROCESS_TEXT, ");
		  query.append("     'FundSubscription.FundSubscriptionDetail' MODULE_CODE, ");
		  query.append("     TO_CHAR(ENROLLMENT_NBR) ");
		  query.append("     || ',M' PROCESS_CODE, ");
		  query.append("     COMMENT_TEXT PROCESS_TEXT, ");
		  query.append("     LAST_UPD_USER_ID, ");
		  query.append("     LAST_UPD_DATE, ");
		  query.append("     OPERN_TYPE_CODE AS OPERN_CODE ");
		  query.append("   FROM PL.TPL_DATA_SUBSCPT_MOV ");
		  query.append("   WHERE FILE_IMPORT_CODE IS NULL ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Importação Subscrição - Fundos Imobiliários/Outros' MODULE_PROCESS_TEXT, ");
		  query.append("     'FundSubscription.FundSubscriptionDetail' MODULE_CODE, ");
		  query.append("     TO_CHAR(FILE_IMPORT_CODE) ");
		  query.append("     || ',I' PROCESS_CODE, ");
		  query.append("     IMPORT_FILE_NAME_TEXT ");
		  query.append("     || ' Data importação : ' ");
		  query.append("     || TO_CHAR(FILE_IMPORT_DATE, 'DD/MM/YYYY HH24:MI:SS' ) PROCESS_TEXT, ");
		  query.append("     LAST_UPD_USER_ID, ");
		  query.append("     LAST_UPD_DATE, ");
		  query.append("     OPERN_TYPE_CODE AS OPERN_CODE ");
		  query.append("   FROM PL.TPL_INFO_SUBSCPT_IMP_MOV ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Subscrição - Código do Fundo' MODULE_PROCESS_TEXT, ");
		  query.append("     'FundSubscription.FundSubscriptionFundCodeDetail' MODULE_CODE, ");
		  query.append("     FUND_NAME_TEXT PROCESS_CODE, ");
		  query.append("     FUND_NAME_TEXT PROCESS_TEXT, ");
		  query.append("     LAST_UPD_USER_ID, ");
		  query.append("     LAST_UPD_DATE, ");
		  query.append("     OPERN_TYPE_CODE AS OPERN_CODE ");
		  query.append("   FROM PL.TPL_FUND_MORT_MOV ");
		  query.append("   WHERE OPERN_TYPE_CODE = 'A' ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT DISTINCT 'Termo de adesão' MODULE_PROCESS_TEXT, ");
		  query.append("     'MembershipTerm.MembershipTermDetail' MODULE_CODE, ");
		  query.append("     PROD_ACCT_CODE ");
		  query.append("     || ',' ");
		  query.append("     || PROD_UNDER_ACCT_CODE PROCESS_CODE, ");
		  query.append("     'Termo de adesão' PROCESS_TEXT, ");
		  query.append("     LAST_UPD_USER_ID, ");
		  query.append("     TO_DATE(TO_CHAR( LAST_UPD_DATE, 'DD/MM/YYYY' ), 'DD/MM/YYYY') AS LAST_UPD_DATE , ");
		  query.append("     'A' OPERN_CODE ");
		  query.append("   FROM PL.TPL_TERM_ADHESION_MOV ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Corretoras' MODULE_PROCESS_TEXT, ");
		  query.append("     'Broker.BrokerMovementDetail' MODULE_CODE, ");
		  query.append("     BKR_CNPJ_NBR ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     BKR_NAME_TEXT PROCESS_TEXT, ");
		  query.append("     LAST_UPD_USER_ID, ");
		  query.append("     LAST_UPD_DATE, ");
		  query.append("     OPERN_CODE ");
		  query.append("   FROM PL.TPL_BROKER_MOV ");
		  query.append("   UNION ALL ");
		  query.append("   SELECT 'Produto - Player x Produtos' MODULE_PROCESS_TEXT, ");
		  query.append("     'ProdPlayerRole.ProdPlayerRoleMovementDetail' MODULE_CODE, ");
		  query.append("     PROD_PLAYER.PLYR_CNPJ_NBR ");
		  query.append("     || ',' PROCESS_CODE, ");
		  query.append("     PLAYER.PLYR_NAME PROCESS_TEXT, ");
		  query.append("     PROD_PLAYER.LAST_UPD_USER_ID, ");
		  query.append("     PROD_PLAYER.Last_Upd_Date, ");
		  query.append("     PROD_PLAYER.Opern_Code ");
		  query.append("   FROM PL.TPL_PROD_PLAYER_ROLE_MOV PROD_PLAYER, ");
		  query.append("     PL.TPL_PLAYER PLAYER ");
		  query.append("   WHERE PROD_PLAYER.PLYR_CNPJ_NBR = PLAYER.PLYR_CNPJ_NBR ");
		  if(hasAccess5001){
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Controle - Contratos' MODULE_PROCESS_TEXT, ");
			  query.append("     'Contract.ProductAccountMovementDetail' MODULE_CODE, ");
			  query.append("     ACCT.PROD_ACCT_CODE ");
			  query.append("     || ',' ");
			  query.append("     || ACCT.PROD_UNDER_ACCT_CODE PROCESS_CODE, ");
			  query.append("     PROD.PROD_NAME PROCESS_TEXT, ");
			  query.append("     ACCT.LAST_UPD_USER_ID, ");
			  query.append("     ACCT.LAST_UPD_DATE, ");
			  query.append("     ACCT.OPERN_CODE ");
			  query.append("   FROM O3.TO3_PRODUCT_ACCOUNT_MOV ACCT ");
			  query.append("   LEFT JOIN PL.TPL_PRODUCT PROD ");
			  query.append("   ON ACCT.PROD_CODE     = PROD.PROD_CODE ");
			  query.append("   AND ACCT.SYS_SEG_CODE = PROD.SYS_SEG_CODE ");
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Cliente - ER x EM' MODULE_PROCESS_TEXT, ");
			  query.append("     'EREM.EREMMovementDetail' MODULE_CODE, ");
			  query.append("     PL.TPL_ER_EM_MOV.ER_NBR ");
			  query.append("     || ',' ");
			  query.append("     || PL.TPL_ER_EM_MOV.EM_NBR PROCESS_CODE, ");
			  query.append("     PL.TPL_ROLE_CUST.ROLE_CUST_TEXT PROCESS_TEXT, ");
			  query.append("     PL.TPL_ER_EM_MOV.LAST_UPD_USER_ID, ");
			  query.append("     PL.TPL_ER_EM_MOV.LAST_UPD_DATE, ");
			  query.append("     PL.TPL_ER_EM_MOV.OPERN_CODE ");
			  query.append("   FROM PL.TPL_ER_EM_MOV, ");
			  query.append("     PL.TPL_CUSTOMER_PRVT_CMPL, ");
			  query.append("     PL.TPL_CUSTOMER_PRVT, ");
			  query.append("     PL.TPL_ROLE_CUST ");
			  query.append("   WHERE PL.TPL_ER_EM_MOV.EM_NBR          = PL.TPL_CUSTOMER_PRVT_CMPL.EM_NBR ");
			  query.append("   AND PL.TPL_CUSTOMER_PRVT_CMPL.CUST_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
			  query.append("   AND PL.TPL_ROLE_CUST.ROLE_CUST_CODE    = PL.TPL_ER_EM_MOV.ROLE_CUST_CODE ");
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Cliente - Relacionamento x EG' MODULE_PROCESS_TEXT, ");
			  query.append("     'RelationEg.RelationEgMovementDetail' MODULE_CODE, ");
			  query.append("     PL.TPL_RELATION_EG_MOV.EG_NBR ");
			  query.append("     ||',' ");
			  query.append("     ||PL.TPL_RELATION_EG_MOV.ER_NBR PROCESS_CODE, ");
			  query.append("     '' PROCESS_TEXT, ");
			  query.append("     PL.TPL_RELATION_EG_MOV.LAST_UPD_USER_ID, ");
			  query.append("     PL.TPL_RELATION_EG_MOV.LAST_UPD_DATE, ");
			  query.append("     PL.TPL_RELATION_EG_MOV.OPERN_CODE ");
			  query.append("   FROM PL.TPL_RELATION_EG_MOV, ");
			  query.append("     (SELECT REL3.RELTN_NBR, ");
			  query.append("       REL3.RELTN_CUST_1_NBR, ");
			  query.append("       REL3.RELTN_CUST_2_NBR, ");
			  query.append("       REL3.RELTN_CUST_3_NBR, ");
			  query.append("       REL3.RELTN_CUST_4_NBR, ");
			  query.append("       REL3.RELTN_CUST_5_NBR, ");
			  query.append("       REL3.CUST_SHORT_NAME_TEXT1, ");
			  query.append("       REL3.CUST_SHORT_NAME_TEXT2, ");
			  query.append("       PL.TPL_CUSTOMER_PRVT.CUST_SHORT_NAME_TEXT CUST_SHORT_NAME_TEXT3 ");
			  query.append("     FROM ");
			  query.append("       (SELECT REL2.RELTN_NBR, ");
			  query.append("         REL2.RELTN_CUST_1_NBR, ");
			  query.append("         REL2.RELTN_CUST_2_NBR, ");
			  query.append("         REL2.RELTN_CUST_3_NBR, ");
			  query.append("         REL2.RELTN_CUST_4_NBR, ");
			  query.append("         REL2.RELTN_CUST_5_NBR, ");
			  query.append("         REL2.CUST_SHORT_NAME_TEXT1, ");
			  query.append("         PL.TPL_CUSTOMER_PRVT.CUST_SHORT_NAME_TEXT CUST_SHORT_NAME_TEXT2 ");
			  query.append("       FROM ");
			  query.append("         (SELECT PL.TPL_RELATION_PRVT.RELTN_NBR, ");
			  query.append("           PL.TPL_RELATION_PRVT.RELTN_CUST_1_NBR, ");
			  query.append("           PL.TPL_RELATION_PRVT.RELTN_CUST_2_NBR, ");
			  query.append("           PL.TPL_RELATION_PRVT.RELTN_CUST_3_NBR, ");
			  query.append("           PL.TPL_RELATION_PRVT.RELTN_CUST_4_NBR, ");
			  query.append("           PL.TPL_RELATION_PRVT.RELTN_CUST_5_NBR, ");
			  query.append("           PL.TPL_CUSTOMER_PRVT.CUST_SHORT_NAME_TEXT CUST_SHORT_NAME_TEXT1 ");
			  query.append("         FROM PL.TPL_RELATION_PRVT ");
			  query.append("         LEFT JOIN PL.TPL_CUSTOMER_PRVT ");
			  query.append("         ON PL.TPL_RELATION_PRVT.RELTN_CUST_1_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
			  query.append("         ) REL2 ");
			  query.append("       LEFT JOIN PL.TPL_CUSTOMER_PRVT ");
			  query.append("       ON REL2.RELTN_CUST_2_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
			  query.append("       ) REL3 ");
			  query.append("     LEFT JOIN PL.TPL_CUSTOMER_PRVT ");
			  query.append("     ON REL3.RELTN_CUST_3_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
			  query.append("     ) CUSTS_RELTN ");
			  query.append("   WHERE CUSTS_RELTN.RELTN_NBR = PL.TPL_RELATION_EG_MOV.RELTN_NBR ");
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Cliente - Banker Dados Compl' MODULE_PROCESS_TEXT, ");
			  query.append("     'OfficerCmpl.OfficerCmplMovementDetail' MODULE_CODE, ");
			  query.append("     PL.TPL_OFFICER_CMPL_MOV.OFFCR_NBR ");
			  query.append("     ||',' PROCESS_CODE, ");
			  query.append("     BG.TBG_OFFICER.OFFCR_NAME_TEXT PROCESS_TEXT, ");
			  query.append("     PL.TPL_OFFICER_CMPL_MOV.LAST_UPD_USER_ID, ");
			  query.append("     PL.TPL_OFFICER_CMPL_MOV.LAST_UPD_DATE, ");
			  query.append("     PL.TPL_OFFICER_CMPL_MOV.OPERN_CODE ");
			  query.append("   FROM PL.TPL_OFFICER_CMPL_MOV, ");
			  query.append("     PL.TPL_OFFICER_TYPE, ");
			  query.append("     BG.TBG_OFFICER ");
			  query.append("   WHERE PL.TPL_OFFICER_CMPL_MOV.OFFCR_TYPE_CODE = PL.TPL_OFFICER_TYPE.OFFCR_TYPE_CODE ");
			  query.append("   AND PL.TPL_OFFICER_CMPL_MOV.OFFCR_NBR         = BG.TBG_OFFICER.OFFCR_NBR ");
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Cliente - Cliente Dados Compl' MODULE_PROCESS_TEXT, ");
			  query.append("     'CustomerPrvtCmpl.CustomerPrvtCmplMovementDetail' MODULE_CODE, ");
			  query.append("     CUSTMOV.CUST_NBR ");
			  query.append("     ||',' PROCESS_CODE, ");
			  query.append("     CUST.CUST_FULL_NAME_TEXT PROCESS_TEXT, ");
			  query.append("     CUSTMOV.LAST_UPD_USER_ID, ");
			  query.append("     CUSTMOV.LAST_UPD_DATE, ");
			  query.append("     CUSTMOV.OPERN_CODE ");
			  query.append("   FROM PL.TPL_CUSTOMER_PRVT_CMPL_MOV CUSTMOV ");
			  query.append("   INNER JOIN PL.TPL_CUSTOMER_PRVT CUST ");
			  query.append("   ON CUSTMOV.CUST_NBR = CUST.CUST_NBR ");
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Cliente - Instrução Permanente' MODULE_PROCESS_TEXT, ");
			  query.append("     'IpDocPrvt.IpDocPrvtMovementDetail' MODULE_CODE, ");
			  query.append("     iPDocPrvtMov.PRMNT_INSTR_CODE ");
			  query.append("     ||',' ");
			  query.append("     ||iPDocPrvtMov.CUST_NBR PROCESS_CODE, ");
			  query.append("     iPDocPrvtMov.PRMNT_INSTR_TEXT PROCESS_TEXT, ");
			  query.append("     iPDocPrvtMov.LAST_UPD_USER_ID, ");
			  query.append("     iPDocPrvtMov.LAST_UPD_DATE, ");
			  query.append("     iPDocPrvtMov.OPERN_CODE ");
			  query.append("   FROM PL.TPL_PRMNT_INSTR_PRVT_MOV iPDocPrvtMov, ");
			  query.append("     PL.TPL_CUSTOMER_PRVT customerPrvt ");
			  query.append("   WHERE customerPrvt.CUST_NBR = iPDocPrvtMov.CUST_NBR ");
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Cliente - Memo de Risco' MODULE_PROCESS_TEXT, ");
			  query.append("     'MrDocPrvt.MrDocPrvtMovDetail' MODULE_CODE, ");
			  query.append("     MR.PRVT_MR_CODE ");
			  query.append("     ||',' ");
			  query.append("     ||MR.PROD_ACCT_CODE ");
			  query.append("     ||',' ");
			  query.append("     || MR.PROD_UNDER_ACCT_CODE PROCESS_CODE, ");
			  query.append("     MR.PRVT_MR_TEXT PROCESS_TEXT, ");
			  query.append("     MR.LAST_UPD_USER_ID, ");
			  query.append("     MR.LAST_UPD_DATE, ");
			  query.append("     MR.OPERN_CODE ");
			  query.append("   FROM PL.TPL_MR_PRVT_MOV MR, ");
			  query.append("     O3.TO3_PRODUCT_ACCOUNT PROD ");
			  query.append("   WHERE MR.PROD_ACCT_CODE     = PROD.PROD_ACCT_CODE ");
			  query.append("   AND MR.PROD_UNDER_ACCT_CODE = PROD.PROD_UNDER_ACCT_CODE ");
			  query.append("   AND TRIM(PROD_CODE)         = '010' ");
			  query.append("   AND PROD.SYS_SEG_CODE       = 1 ");
			  query.append("   UNION ALL ");
			  query.append("   SELECT 'Cliente - ER' MODULE_PROCESS_TEXT, ");
			  query.append("     'ER.ERMovementDetail' MODULE_CODE, ");
			  query.append("     ER_MOV.ER_NBR ");
			  query.append("     ||',' PROCESS_CODE, ");
			  query.append("     ER_MOV.ER_NBR PROCESS_TEXT, ");
			  query.append("     ER_MOV.LAST_UPD_USER_ID, ");
			  query.append("     ER_MOV.LAST_UPD_DATE, ");
			  query.append("     ER_MOV.OPERN_CODE ");
			  query.append("   FROM PL.TPL_ER_MOV ER_MOV ");
		  }
		  
		  query.append("   ) movs ");
		  
		  String criteria = "";		  

		  if ( lastUpdUserId_ != null && !lastUpdUserId_.equals(""))
		  {
			criteria = criteria + " UPPER(movs.last_upd_user_id) like TRIM(?) ";
		  }
		  
		  if(m_moduleProcessText_!= null && !m_moduleProcessText_.equals("")){
			if(criteria.length() > 0){
				criteria = criteria + "and movs.MODULE_PROCESS_TEXT = ? ";
			}
			else{
				criteria = criteria + "movs.MODULE_PROCESS_TEXT = ? ";	
			}			
		  }
			
		  if(criteria.length() > 0){
			criteria = " WHERE " + criteria + "order by movs.last_upd_date desc";
		  }
		  else{
			criteria = " order by movs.last_upd_date desc";
		  }		  
		  
		  query.append( criteria );

		  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
		  int count = 1;

		  
		  if ( lastUpdUserId_ != null && !lastUpdUserId_.equals(""))
		  {
			preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
		  }
		  
		  if ( m_moduleProcessText_ != null && !m_moduleProcessText_.equals(""))
		  {
			preparedStatement.setString( count++, m_moduleProcessText_);
		  }
		  
		  preparedStatement.replaceParametersInQuery(query.toString());
		  resultSet = preparedStatement.executeQuery();

		  rsds = new ResultSetDataSet( resultSet );
		  resultSet.close();
		}
		catch ( SQLException e )
		{
		  throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
		}
		finally
		{
		  closeStatement( preparedStatement );
		  closeConnection( connection );
		}
		return rsds;

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.TplCentrApprovalMovDAO#loadDomain()
	 */
	public DataSet loadDomain(boolean hasAccess5001) {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append(" SELECT DISTINCT 'Produto - Classe de Ativo' MODULE_PROCESS_TEXT ");
			query.append(" FROM pl.tpl_asset_class_mov asse_mov ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Sub Classe de Ativo' MODULE_PROCESS_TEXT ");
			query.append(" FROM pl.tpl_sub_asset_class_mov sub_asse_mov ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Tipo de Ativo' MODULE_PROCESS_TEXT ");
			query.append(" FROM pl.tpl_asset_type_mov type_mov ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Subscrição - Fundos Imobiliários/Outros' MODULE_PROCESS_TEXT ");
			query.append(" FROM pl.tpl_data_subscpt_mov subt_mov ");
			query.append(" WHERE subt_mov.FILE_IMPORT_CODE IS NULL ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Importação Subscrição - Fundos Imobiliários/Outros' MODULE_PROCESS_TEXT ");
			query.append(" FROM pl.TPL_INFO_SUBSCPT_IMP_MOV imp_subt_mov ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Subscrição - Código do Fundo' MODULE_PROCESS_TEXT ");
			query.append(" FROM pl.TPL_FUND_MORT_MOV cod_subt_mov ");
			query.append(" WHERE OPERN_TYPE_CODE = 'A' ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Termo de adesão' MODULE_PROCESS_TEXT ");
			query.append(" FROM pl.TPL_TERM_ADHESION_MOV term_mov ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Família' MODULE_PROCESS_TEXT ");
			query.append(" FROM PL.TPL_PRODUCT_FAMILY_PRVT_MOV FAM_MOV ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Sub-Família' MODULE_PROCESS_TEXT ");
			query.append(" FROM PL.TPL_PROD_SUB_FAML_PRVT_MOV sub ");
			query.append(" LEFT JOIN PL.TPL_PRODUCT_FAMILY_PRVT prod ");
			query.append(" ON sub.PROD_FAML_CODE = prod.PROD_FAML_CODE ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Produtos' MODULE_PROCESS_TEXT ");
			query.append(" FROM PL.TPL_PRODUCT_MOV PROD, ");
			query.append("   PL.TPL_PRODUCT_SUB_FAMILY_PRVT SUB, ");
			query.append("   PL.TPL_PRODUCT_FAMILY_PRVT PROD_FAML ");
			query.append(" WHERE PROD.PROD_SUB_FAML_CODE = SUB.PROD_SUB_FAML_CODE(+) ");
			query.append(" AND SUB.PROD_FAML_CODE        = PROD_FAML.PROD_FAML_CODE(+) ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Player' MODULE_PROCESS_TEXT ");
			query.append(" FROM PL.TPL_PLAYER_MOV, ");
			query.append("   PL.TPL_PLAYER_ROLE_MOV ");
			query.append(" WHERE PL.TPL_PLAYER_MOV.PLYR_CNPJ_NBR = PL.TPL_PLAYER_ROLE_MOV.PLYR_CNPJ_NBR ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Qualificação' MODULE_PROCESS_TEXT ");
			query.append(" FROM PL.TPL_PROD_QLFY_PRVT_MOV ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Corretoras' MODULE_PROCESS_TEXT ");
			query.append(" FROM PL.TPL_BROKER_MOV ");
			query.append(" UNION ALL ");
			query.append(" SELECT DISTINCT 'Produto - Player x Produtos' MODULE_PROCESS_TEXT ");
			query.append(" FROM PL.TPL_PROD_PLAYER_ROLE_MOV PROD_PLAYER, ");
			query.append("   PL.TPL_PLAYER PLAYER ");
			query.append(" WHERE PROD_PLAYER.PLYR_CNPJ_NBR = PLAYER.PLYR_CNPJ_NBR ");
			if(hasAccess5001){
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Controle - Contratos' MODULE_PROCESS_TEXT ");
				query.append(" FROM O3.TO3_PRODUCT_ACCOUNT_MOV ACCT ");
				query.append(" LEFT JOIN PL.TPL_PRODUCT PROD ");
				query.append(" ON ACCT.PROD_CODE     = PROD.PROD_CODE ");
				query.append(" AND ACCT.SYS_SEG_CODE = PROD.SYS_SEG_CODE ");
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Cliente - ER' MODULE_PROCESS_TEXT FROM pl.tpl_er_mov er_mov ");
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Cliente - ER x EM' MODULE_PROCESS_TEXT ");
				query.append(" FROM PL.TPL_ER_EM_MOV, ");
				query.append("   PL.TPL_CUSTOMER_PRVT_CMPL, ");
				query.append("   PL.TPL_CUSTOMER_PRVT, ");
				query.append("   PL.TPL_ROLE_CUST ");
				query.append(" WHERE PL.TPL_ER_EM_MOV.EM_NBR          = PL.TPL_CUSTOMER_PRVT_CMPL.EM_NBR ");
				query.append(" AND PL.TPL_CUSTOMER_PRVT_CMPL.CUST_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
				query.append(" AND PL.TPL_ROLE_CUST.ROLE_CUST_CODE    = PL.TPL_ER_EM_MOV.ROLE_CUST_CODE ");
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Cliente - Relacionamento x EG' MODULE_PROCESS_TEXT ");
				query.append(" FROM PL.TPL_RELATION_EG_MOV, ");
				query.append("   (SELECT REL3.RELTN_NBR, ");
				query.append("     REL3.RELTN_CUST_1_NBR, ");
				query.append("     REL3.RELTN_CUST_2_NBR, ");
				query.append("     REL3.RELTN_CUST_3_NBR, ");
				query.append("     REL3.RELTN_CUST_4_NBR, ");
				query.append("     REL3.RELTN_CUST_5_NBR, ");
				query.append("     REL3.CUST_SHORT_NAME_TEXT1, ");
				query.append("     REL3.CUST_SHORT_NAME_TEXT2, ");
				query.append("     PL.TPL_CUSTOMER_PRVT.CUST_SHORT_NAME_TEXT CUST_SHORT_NAME_TEXT3 ");
				query.append("   FROM ");
				query.append("     (SELECT REL2.RELTN_NBR, ");
				query.append("       REL2.RELTN_CUST_1_NBR, ");
				query.append("       REL2.RELTN_CUST_2_NBR, ");
				query.append("       REL2.RELTN_CUST_3_NBR, ");
				query.append("       REL2.RELTN_CUST_4_NBR, ");
				query.append("       REL2.RELTN_CUST_5_NBR, ");
				query.append("       REL2.CUST_SHORT_NAME_TEXT1, ");
				query.append("       PL.TPL_CUSTOMER_PRVT.CUST_SHORT_NAME_TEXT CUST_SHORT_NAME_TEXT2 ");
				query.append("     FROM ");
				query.append("       (SELECT PL.TPL_RELATION_PRVT.RELTN_NBR, ");
				query.append("         PL.TPL_RELATION_PRVT.RELTN_CUST_1_NBR, ");
				query.append("         PL.TPL_RELATION_PRVT.RELTN_CUST_2_NBR, ");
				query.append("         PL.TPL_RELATION_PRVT.RELTN_CUST_3_NBR, ");
				query.append("         PL.TPL_RELATION_PRVT.RELTN_CUST_4_NBR, ");
				query.append("         PL.TPL_RELATION_PRVT.RELTN_CUST_5_NBR, ");
				query.append("         PL.TPL_CUSTOMER_PRVT.CUST_SHORT_NAME_TEXT CUST_SHORT_NAME_TEXT1 ");
				query.append("       FROM PL.TPL_RELATION_PRVT ");
				query.append("       LEFT JOIN PL.TPL_CUSTOMER_PRVT ");
				query.append("       ON PL.TPL_RELATION_PRVT.RELTN_CUST_1_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
				query.append("       ) REL2 ");
				query.append("     LEFT JOIN PL.TPL_CUSTOMER_PRVT ");
				query.append("     ON REL2.RELTN_CUST_2_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
				query.append("     ) REL3 ");
				query.append("   LEFT JOIN PL.TPL_CUSTOMER_PRVT ");
				query.append("   ON REL3.RELTN_CUST_3_NBR = PL.TPL_CUSTOMER_PRVT.CUST_NBR ");
				query.append("   ) CUSTS_RELTN ");
				query.append(" WHERE CUSTS_RELTN.RELTN_NBR = PL.TPL_RELATION_EG_MOV.RELTN_NBR ");
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Cliente - Banker Dados Compl' MODULE_PROCESS_TEXT ");
				query.append(" FROM PL.TPL_OFFICER_CMPL_MOV, ");
				query.append("   PL.TPL_OFFICER_TYPE, ");
				query.append("   BG.TBG_OFFICER ");
				query.append(" WHERE PL.TPL_OFFICER_CMPL_MOV.OFFCR_TYPE_CODE = PL.TPL_OFFICER_TYPE.OFFCR_TYPE_CODE ");
				query.append(" AND PL.TPL_OFFICER_CMPL_MOV.OFFCR_NBR         = BG.TBG_OFFICER.OFFCR_NBR ");
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Cliente - Cliente Dados Compl' MODULE_PROCESS_TEXT ");
				query.append(" FROM PL.TPL_CUSTOMER_PRVT_CMPL_MOV CUSTMOV ");
				query.append(" INNER JOIN PL.TPL_CUSTOMER_PRVT CUST ");
				query.append(" ON CUSTMOV.CUST_NBR = CUST.CUST_NBR ");
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Cliente - Instrução Permanente' MODULE_PROCESS_TEXT ");
				query.append(" FROM PL.TPL_PRMNT_INSTR_PRVT_MOV iPDocPrvtMov, ");
				query.append("   PL.TPL_CUSTOMER_PRVT customerPrvt ");
				query.append(" WHERE customerPrvt.CUST_NBR = iPDocPrvtMov.CUST_NBR ");
				query.append(" UNION ALL ");
				query.append(" SELECT DISTINCT 'Cliente - Memo de Risco' MODULE_PROCESS_TEXT ");
				query.append(" FROM PL.TPL_MR_PRVT_MOV MR, ");
				query.append("   O3.TO3_PRODUCT_ACCOUNT PROD ");
				query.append(" WHERE MR.PROD_ACCT_CODE     = PROD.PROD_ACCT_CODE ");
				query.append(" AND MR.PROD_UNDER_ACCT_CODE = PROD.PROD_UNDER_ACCT_CODE ");
				query.append(" AND TRIM(PROD_CODE)         = '010' ");
				query.append(" AND PROD.SYS_SEG_CODE       = 1 ");
			}
			
			
			preparedStatement =	new CitiStatement(connection.prepareStatement(query.toString()));
			
			preparedStatement.replaceParametersInQuery(query.toString());
			resultSet = preparedStatement.executeQuery();

			rsds = new ResultSetDataSet(resultSet);
			resultSet.close();
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				C_ERROR_EXECUTING_STATEMENT,
				e);
		}
		 finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return rsds;
	}
	
	
}
