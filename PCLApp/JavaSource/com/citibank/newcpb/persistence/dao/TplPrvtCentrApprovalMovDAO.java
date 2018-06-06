package com.citibank.newcpb.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.newcpb.enums.ScreenNamesEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.vo.RegisterMovPendingToApproveVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtCentrApprovalMovDAO extends BaseOracleDAO {

	public ArrayList<String> loadDomain() {
		
		ManagedRdbConnection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<String> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT DISTINCT '" + ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome() + "' MODULE_PROCESS_TEXT FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER_MOV CUSTOMER_MOV "
			+ "UNION ALL SELECT DISTINCT '" + ScreenNamesEnum.REGISTER_AUTHORIZED.getNome() + "'  MODULE_PROCESS_TEXT FROM " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN_MOV AUTH_PERSN_MOV "
			+ "UNION ALL SELECT DISTINCT '" + ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome() + "' MODULE_PROCESS_TEXT FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL_MOV ACCT_CMPL_MOV "
			+ "UNION ALL SELECT DISTINCT '" + ScreenNamesEnum.ACCOUNT_MIGRATED.getNome() + "' MODULE_PROCESS_TEXT FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT_MOV ACCT_MGRT_MOV "
			+ "UNION ALL SELECT DISTINCT '" + ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome() + "' MODULE_PROCESS_TEXT FROM " + C_PL_SCHEMA + "TPL_PRVT_CPF_STAT_MOV CPF_STAT_MOV "
			+ "UNION ALL SELECT DISTINCT '" + ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome() + "' MODULE_PROCESS_TEXT FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_MOV KE_MOV "
			+ "UNION ALL SELECT DISTINCT '" + ScreenNamesEnum.QUESTIONS_KE_AUTH.getNome() + "' MODULE_PROCESS_TEXT FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_MOV KE_MOV ");

			preparedStatement = connection.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<String>();
				while (rs.next()){
					if(rs.getString("MODULE_PROCESS_TEXT")!=null){
						resultList.add(rs.getString("MODULE_PROCESS_TEXT").trim());
					}
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return resultList;
	}

	public ArrayList<RegisterMovPendingToApproveVO> list(String moduleProcessTextFilter, String userIdFilter) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<RegisterMovPendingToApproveVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			query.append("SELECT PROCESS_CODE, MODULE_PROCESS_TEXT, PROCESS_TEXT, LAST_UPD_USER_ID, LAST_UPD_DATE, REC_STAT_CODE "
			+ " FROM "
			+ "  (SELECT '" + ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome() + "' MODULE_PROCESS_TEXT, "
			+ "    TO_CHAR(CUSTOMER_MOV.CUST_GFCID_NBR) PROCESS_CODE, "
			+ "    CUSTOMER_MOV.CUST_EM_NBR || ' ' || CUST_FULL_NAME PROCESS_TEXT, "
			+ "    CUSTOMER_MOV.LAST_UPD_USER last_upd_user_id, "
			+ "    CUSTOMER_MOV.LAST_UPD_DATE last_upd_date, "
			+ "    CUSTOMER_MOV.REC_STAT_CODE "
			+ "  FROM  " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER_MOV CUSTOMER_MOV "
			+ "  UNION "
			+ " SELECT '" + ScreenNamesEnum.REGISTER_AUTHORIZED.getNome() + "' MODULE_PROCESS_TEXT, "
			+ "     TO_CHAR(AUTH_PERSN_MOV.EM_NBR) PROCESS_CODE, "
			+ "     AUTH_PERSN_MOV.EM_NBR "
			+ "     || ' ' "
			+ "     || AUTH_PERSN_MOV.AUTH_PERSN_NAME PROCESS_TEXT, "
			+ "     AUTH_PERSN_MOV.LAST_UPD_USER_ID last_upd_user_id, "
			+ "     AUTH_PERSN_MOV.LAST_UPD_DATE last_upd_date, "
			+ "     AUTH_PERSN_MOV.REC_STAT_CODE "
			+ "   FROM " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN_MOV AUTH_PERSN_MOV "
			+ "  UNION "
			+ " SELECT '" + ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome() + "' MODULE_PROCESS_TEXT, "
			+ "     TO_CHAR(CPF_STAT_MOV.CPF_UPD_MTH_YR) PROCESS_CODE, "
			+ "     'Mês/Ano  ' "
			+ "     || CPF_STAT_MOV.CPF_UPD_MTH_YR "
			+ "     || '  - Status  ' "
			+ "     || DECODE(CPF_STAT_MOV.CPF_STATUS, 'A', 'Ativo', 'I', 'Inativo') PROCESS_TEXT, "
			+ "     CPF_STAT_MOV.LAST_UPD_USER_ID last_upd_user_id, "
			+ "     CPF_STAT_MOV.LAST_UPD_DATE last_upd_date, "
			+ "     CPF_STAT_MOV.REC_STAT_CODE "
			+ "   FROM " + C_PL_SCHEMA + "TPL_PRVT_CPF_STAT_MOV CPF_STAT_MOV "
			+ "  UNION "
			+ " SELECT '" + ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome() + "' MODULE_PROCESS_TEXT, "
			+ "     TO_CHAR(ACCT_CMPL_MOV.ACCT_NBR) || '&' ||  TO_CHAR(ACCT_CMPL_MOV.CPF_CNPJ_NBR) || '&' ||  TO_CHAR(ACCT_CMPL_MOV.ACCT_SRC_TYPE) PROCESS_CODE, "			
        	+ "     (SELECT DISTINCT CUST.CUST_FULL_NAME FROM PL.TPL_PRVT_CUSTOMER CUST WHERE CUST.CUST_STAT_CODE ='A' AND CUST.CUST_CPF_CNPJ_NBR=ACCT_CMPL_MOV.CPF_CNPJ_NBR  AND ROWNUM=1 )"
        	+ "     || ' ' "
			+ "     || ACCT_CMPL_MOV.ACCT_NBR "
			+ "     || ' ' "
			+ "     || ACCT_CMPL_MOV.CPF_CNPJ_NBR PROCESS_TEXT, "
			+ "     ACCT_CMPL_MOV.LAST_UPD_USER_ID last_upd_user_id, "
			+ "     ACCT_CMPL_MOV.LAST_UPD_DATE last_upd_date, "
			+ "     ACCT_CMPL_MOV.REC_STAT_CODE "
			+ "   FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL_MOV ACCT_CMPL_MOV "
			+ "  UNION "
			+ " SELECT '" + ScreenNamesEnum.ACCOUNT_MIGRATED.getNome() + "' MODULE_PROCESS_TEXT, "
			+ "     TO_CHAR(ACCT_MGRT_MOV.CUR_ACCT_NBR) || '&' ||  TO_CHAR(ACCT_MGRT_MOV.CUST_CPF_CNPJ_NBR) || '&' ||  TO_CHAR(ACCT_MGRT_MOV.NEW_ACCT_NBR) PROCESS_CODE, "
			+ "     (SELECT DISTINCT CUST.CUST_FULL_NAME FROM PL.TPL_PRVT_CUSTOMER CUST WHERE CUST.CUST_STAT_CODE ='A' AND CUST.CUST_CPF_CNPJ_NBR=ACCT_MGRT_MOV.CUST_CPF_CNPJ_NBR AND ROWNUM=1 )"
			+ "     || ' ' "
			+ "     || ACCT_MGRT_MOV.CUR_ACCT_NBR "
			+ "     || ' ' "
			+ "     || ACCT_MGRT_MOV.CUST_CPF_CNPJ_NBR "
			+ "     || ' ' "
			+ "     || ACCT_MGRT_MOV.NEW_ACCT_NBR PROCESS_TEXT, "
			+ "     ACCT_MGRT_MOV.LAST_UPD_USER_ID last_upd_user_id, "
			+ "     ACCT_MGRT_MOV.LAST_UPD_DATE last_upd_date, "
			+ "     ACCT_MGRT_MOV.REC_STAT_CODE "
			+ "   FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_MGRT_MOV ACCT_MGRT_MOV "			
			+ "  UNION "
			+ " SELECT '" + ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome() + "' MODULE_PROCESS_TEXT, "
			+ "     TO_CHAR(KE_MOV.ACCT_NBR) || '&' ||  TO_CHAR(KE_MOV.CUST_CPF_CNPJ_NBR) PROCESS_CODE, "
			+ "     (SELECT DISTINCT CUST.CUST_FULL_NAME FROM " + C_PL_SCHEMA 
			+ "     TPL_PRVT_ACCT_CMPL ACCT_COMPL LEFT JOIN " + C_PL_SCHEMA 
			+ "     TPL_PRVT_CUSTOMER CUST  ON  ACCT_COMPL.CPF_CNPJ_NBR = CUST.CUST_CPF_CNPJ_NBR AND CUST.CUST_STAT_CODE   ='A' WHERE ACCT_COMPL.REC_STAT_CODE <> 'I'  AND ROWNUM=1"
			+ "     AND ACCT_COMPL.ACCT_NBR = KE_MOV.ACCT_NBR)  "
			+ "     || ' ' "
			+ "     || KE_MOV.ACCT_NBR PROCESS_TEXT, "
			+ "     KE_MOV.LAST_UPD_USER last_upd_user_id, "
			+ "     KE_MOV.LAST_UPD_DATE last_upd_date, "
			+ "     KE_MOV.REC_STAT_CODE "
			+ "   FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_MOV KE_MOV "		
			
			+ "	  LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER CUST ON KE_MOV.CUST_CPF_CNPJ_NBR = CUST.CUST_CPF_CNPJ_NBR "
			+ "	  LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL ACCT_COMPL ON CUST.CUST_CPF_CNPJ_NBR = ACCT_COMPL.CPF_CNPJ_NBR "
			+ "	  WHERE KE_MOV.ACCT_NBR = ACCT_COMPL.ACCT_NBR "
			+ "   AND KE_MOV.CUST_CPF_CNPJ_NBR = CUST.CUST_CPF_CNPJ_NBR "

			+ "  UNION "
			+ " SELECT '" + ScreenNamesEnum.QUESTIONS_KE_AUTH.getNome() + "' MODULE_PROCESS_TEXT, "
			+ "     TO_CHAR(KE_MOV.ACCT_NBR) || '&' ||  TO_CHAR(KE_MOV.CUST_CPF_CNPJ_NBR) PROCESS_CODE, "
			+ "     (SELECT DISTINCT AUTH_PERSN.AUTH_PERSN_NAME FROM " + C_PL_SCHEMA 
			+ "     TPL_PRVT_AUTH_PERSN AUTH_PERSN LEFT JOIN " + C_PL_SCHEMA 
			+ "     TPL_PRVT_AUT_PRSN_ACCT PRSN_ACCT  ON AUTH_PERSN.EM_NBR = PRSN_ACCT.EM_NBR WHERE AUTH_PERSN.REC_STAT_CODE <> 'I'  AND ROWNUM=1"
			+ "     AND PRSN_ACCT.ACCT_NBR = KE_MOV.ACCT_NBR)  "
			+ "     || ' ' "
			+ "     || KE_MOV.ACCT_NBR PROCESS_TEXT, "
			+ "     KE_MOV.LAST_UPD_USER last_upd_user_id, "
			+ "     KE_MOV.LAST_UPD_DATE last_upd_date, "
			+ "     KE_MOV.REC_STAT_CODE "
			+ "   FROM " + C_PL_SCHEMA + "TPL_PRVT_ACCT_KE_MOV KE_MOV "
			+ "   LEFT JOIN " + C_PL_SCHEMA + "TPL_PRVT_AUTH_PERSN AUTH_PERSN  ON KE_MOV.CUST_CPF_CNPJ_NBR = AUTH_PERSN.CPF_CNPJ_NBR "
			+ "   LEFT JOIN " + C_PL_SCHEMA + "TPL_PRVT_AUT_PRSN_ACCT PRSN_ACCT ON AUTH_PERSN.EM_NBR = PRSN_ACCT.EM_NBR"
			+ "    WHERE AUTH_PERSN.CPF_CNPJ_NBR = KE_MOV.CUST_CPF_CNPJ_NBR "
			+ "    AND KE_MOV.ACCT_NBR = PRSN_ACCT.ACCT_NBR "
			+ "  ) MOVS");

			String criteria = "";

			if (userIdFilter != null && !userIdFilter.equals("")) {
				criteria = criteria + " UPPER(MOVS.LAST_UPD_USER_ID) LIKE TRIM(?) ";
			}

			if (moduleProcessTextFilter != null && !moduleProcessTextFilter.equals("")) {
				if (criteria.length() > 0) {
					criteria = criteria + "AND MOVS.MODULE_PROCESS_TEXT = ? ";
				} else {
					criteria = criteria + "MOVS.MODULE_PROCESS_TEXT = ? ";
				}
			}

			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + " GROUP BY MODULE_PROCESS_TEXT, PROCESS_CODE, PROCESS_TEXT, last_upd_user_id, last_upd_date, REC_STAT_CODE ORDER BY MOVS.LAST_UPD_DATE DESC";
			} else {
				criteria = " GROUP BY MODULE_PROCESS_TEXT, PROCESS_CODE, PROCESS_TEXT, last_upd_user_id, last_upd_date, REC_STAT_CODE ORDER BY MOVS.LAST_UPD_DATE DESC";
			}

			query.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			if (userIdFilter != null && !userIdFilter.equals("")) {
				preparedStatement.setString(count++, "%" + userIdFilter.toUpperCase() + "%");
			}

			if (moduleProcessTextFilter != null && !moduleProcessTextFilter.equals("")) {
				preparedStatement.setString(count++, moduleProcessTextFilter);
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<RegisterMovPendingToApproveVO>();
				while (rs.next()){
					if(rs.getString("MODULE_PROCESS_TEXT")!=null){
						RegisterMovPendingToApproveVO result = new RegisterMovPendingToApproveVO();
						result.setProcessCode(rs.getString("PROCESS_CODE") != null ? rs.getString("PROCESS_CODE").toString() : null);								
						result.setModuleProcessText(rs.getString("MODULE_PROCESS_TEXT") != null ? rs.getString("MODULE_PROCESS_TEXT").toString() : null);
						result.setProcessText(rs.getString("PROCESS_TEXT") != null ? rs.getString("PROCESS_TEXT").toString() : null);
						result.setLastUpdUserId(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);
												
						if(rs.getTimestamp("LAST_UPD_DATE")!=null){
							result.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE"));
						}
						result.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
						
						resultList.add(result);
					}
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return resultList;
	}
	
	
}
