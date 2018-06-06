package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.enun.QuestKeTypeEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.vo.QuestionsKeAnswVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtDmnKeProdMovDAO extends BaseOracleDAO {

	public ArrayList<QuestionsKeAnswVO> listQuestionsKeDmn(String acctNbr, String cpfCnpj) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<QuestionsKeAnswVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();						
			query.append(" SELECT PROD_FAML.PROD_FAML_SEQ, "
			+ "   PROD_FAML.PROD_FAML_KE_CODE, "
			+ "   PROD_FAML.PROD_FAML_KE_TEXT, "
			+ "   PROD.PROD_SEQ, "
			+ "   PROD.PROD_KE_CODE, "
			+ "   PROD.PROD_KE_TEXT, "		
			+ "   (select ASNW_CODE FROM " + C_PL_SCHEMA + "  TPL_PRVT_ACCT_KE_ANSW_MOV WHERE PROD.PROD_KE_CODE = PROD_KE_CODE AND PROD_FAML.PROD_FAML_KE_CODE = PROD_FAML_KE_CODE  AND  ACCT_NBR = ' " + acctNbr + "' AND  CUST_CPF_CNPJ_NBR = ' " + cpfCnpj + "' AND QUEST_TYPE_CODE = '" + QuestKeTypeEnum.PROD.getValue() + "') AS ASNW_CODE_PROD, "
			+ "   (select ASNW_CODE FROM " + C_PL_SCHEMA + "  TPL_PRVT_ACCT_KE_ANSW_MOV WHERE PROD.PROD_KE_CODE = PROD_KE_CODE AND PROD_FAML.PROD_FAML_KE_CODE = PROD_FAML_KE_CODE  AND ACCT_NBR = ' " + acctNbr + "' AND  CUST_CPF_CNPJ_NBR = ' " + cpfCnpj + "' AND QUEST_TYPE_CODE = '" + QuestKeTypeEnum.FMA.getValue() + "') AS ASNW_CODE_FMA, "
			+ "   (select ASNW_CODE FROM " + C_PL_SCHEMA + "  TPL_PRVT_ACCT_KE_ANSW_MOV WHERE PROD.PROD_KE_CODE = PROD_KE_CODE AND PROD_FAML.PROD_FAML_KE_CODE = PROD_FAML_KE_CODE AND ACCT_NBR = ' " + acctNbr + "' AND  CUST_CPF_CNPJ_NBR = ' " + cpfCnpj + "' AND QUEST_TYPE_CODE = '" + QuestKeTypeEnum.VMA.getValue() + "') AS ASNW_CODE_VMA "
			+ "   FROM " + C_PL_SCHEMA + " TPL_PRVT_DMN_KE_PROD_FAML PROD_FAML "
			+ "   LEFT JOIN " + C_PL_SCHEMA + " TPL_PRVT_DMN_KE_PROD PROD "
			+ "   ON  PROD_FAML.PROD_FAML_KE_CODE = PROD.PROD_FAML_KE_CODE "
			+ "   ORDER BY PROD_FAML.PROD_FAML_SEQ, PROD.PROD_SEQ " );
						
			
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<QuestionsKeAnswVO>();
				while (rs.next()){
					
					QuestionsKeAnswVO result = new QuestionsKeAnswVO();
					
					result.setProdFamlSeq(rs.getString("PROD_FAML_SEQ") != null ? rs.getString("PROD_FAML_SEQ").toString() : null);
					result.setProdFamlKeCode(rs.getString("PROD_FAML_KE_CODE") != null ? rs.getString("PROD_FAML_KE_CODE").toString() : null);	
					result.setProdFamlKeText(rs.getString("PROD_FAML_KE_TEXT") != null ? rs.getString("PROD_FAML_KE_TEXT").toString() : null);	
					result.setProdSeq(rs.getString("PROD_SEQ") != null ? rs.getString("PROD_SEQ").toString() : null);	
					result.setProdKeCode(rs.getString("PROD_KE_CODE") != null ? rs.getString("PROD_KE_CODE").toString() : null);	
					result.setProdKeText(rs.getString("PROD_KE_TEXT") != null ? rs.getString("PROD_KE_TEXT").toString() : null);	
					result.setProdAnswer(rs.getString("ASNW_CODE_PROD") != null ? rs.getString("ASNW_CODE_PROD").toString() : null);
					result.setFmaAnswer(rs.getString("ASNW_CODE_FMA") != null ? rs.getString("ASNW_CODE_FMA").toString() : null);
					result.setVmaAnswer(rs.getString("ASNW_CODE_VMA") != null ? rs.getString("ASNW_CODE_VMA").toString() : null);
					result.setTableOrigin(TableTypeEnum.MOVEMENT);
					result.setAcctNbr(acctNbr);	
					result.setCpfCnpjNbr(cpfCnpj);
					
					resultList.add(result);
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
