package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.newcpb.util.FormatUtils;
import com.citibank.newcpb.vo.ErEmVO;
import com.citibank.newcpb.vo.RegisterDataCustomerVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtCustomerDAO extends BaseOracleDAO {

	public ArrayList<RegisterDataCustomerVO> list(String name, String numberEM,String numberGFCID) throws ParseException {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<RegisterDataCustomerVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append("SELECT DISTINCT CUST.CUST_FULL_NAME, CUST.CUST_EM_NBR, CUST.CUST_GFCID_NBR, CUST.CUST_CPF_CNPJ_NBR , CUST.CUST_TYPE_CODE, EMP.EMPL_FULL_NAME "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER CUST LEFT JOIN " + C_PL_SCHEMA + "TPL_PRVT_EMPLOYEE_REG EMP ON TO_CHAR(CUST.CUST_OFFICER_SOEID) = TO_CHAR(EMP.EMPL_SOEID_ID)");
			
			
			String criteria = "";

			if (name != null && !name.equals("")) {
				criteria = criteria + " UPPER(CUST.CUST_FULL_NAME) LIKE TRIM (?) ";
			}

			if (numberEM != null && !numberEM.equals("")) {
				if (criteria.length() > 0) {
					criteria = criteria + "AND UPPER(CUST.CUST_EM_NBR) LIKE TRIM (?) ";
				} else {
					criteria = criteria + "UPPER(CUST.CUST_EM_NBR) LIKE TRIM (?) ";
				}
			}
						
			
			if (numberGFCID != null && !numberGFCID.equals("")) {
				if (criteria.length() > 0) {
					criteria = criteria + "AND UPPER(CUST.CUST_GFCID_NBR) LIKE (?) ";
				} else {
					criteria = criteria + "UPPER(CUST.CUST_GFCID_NBR) LIKE TRIM (?) ";
				}
			}

			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + "ORDER BY CUST.CUST_FULL_NAME";
			} else {
				criteria = " ORDER BY CUST.CUST_FULL_NAME";
			}

			query.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			if (name != null && !name.equals("")) {
				preparedStatement.setString(count++, "%" + name.toUpperCase() + "%");
			}

			if (numberEM != null && !numberEM.equals("")) {
				preparedStatement.setString(count++, "%" + numberEM.toUpperCase() + "%");
			}
			
			if (numberGFCID != null && !numberGFCID.equals("")) {
				preparedStatement.setString(count++, "%" + numberGFCID.toUpperCase() + "%");
			}

			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<RegisterDataCustomerVO>();
				while (rs.next()){
					
					RegisterDataCustomerVO result = new RegisterDataCustomerVO();
					result.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);								
					result.setNumberEM(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
					result.setNumberGFCID(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					result.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					result.setNameBanker(rs.getString("EMPL_FULL_NAME") != null ? rs.getString("EMPL_FULL_NAME").toString() : null);
					result.setCustomerType(rs.getString("CUST_TYPE_CODE") != null ? rs.getString("CUST_TYPE_CODE").toString() : null);
					
					if(!StringUtils.isBlank(result.getCpfCnpj())){
						result.setCpfCnpj(FormatUtils.formatterDoc(result.getCpfCnpj()));
					}
					
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
	
	public RegisterDataCustomerVO update(RegisterDataCustomerVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("UPDATE  " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER ");
			sqlQuery.append("  SET CUST_CPF_CNPJ_NBR         = ? , ");
			sqlQuery.append("  CUST_GFCID_NBR                = ? , ");
			sqlQuery.append("  CUST_FULL_NAME                = ? , ");
			sqlQuery.append("  CUST_EM_NBR                   = ? , ");
			sqlQuery.append("  CUST_TYPE_CODE                = ? , ");
			sqlQuery.append("  CUST_FILIATION                = ? , ");
			sqlQuery.append("  CUST_FILIATION_2              = ? , ");
			sqlQuery.append("  CUST_CIVIL_STAT_CODE          = ? , ");
			sqlQuery.append("  CUST_SPOUSE_NAME              = ? , ");
			sqlQuery.append("  CUST_SEX_CODE                 = ? , ");
			sqlQuery.append("  CUST_BIRTH_DATE               = ? , ");
			sqlQuery.append("  CUST_BIRTH_CNTRY_CODE         = ? , ");
			sqlQuery.append("  CUST_BIRTH_CITY_TEXT          = ? , ");
			sqlQuery.append("  CUST_BIRTH_STATE_CODE         = ? , ");
			sqlQuery.append("  CUST_PROF_TEXT                = ? , ");
			sqlQuery.append("  CUST_OCCUP_CODE               = ? , ");
			sqlQuery.append("  CUST_INCO_AMT                 = ? , ");
			sqlQuery.append("  CUST_PRPTY_AMT                = ? , ");
			sqlQuery.append("  CUST_IRRF_EXEMPT_IND          = ? , ");
			sqlQuery.append("  CUST_EMPL_IND                 = ? , ");
			sqlQuery.append("  CUST_STAT_CODE                = ? , ");
			sqlQuery.append("  CUST_DEATH_IND                = ? , ");
			sqlQuery.append("  CUST_SENS_IND                 = ? , ");
			sqlQuery.append("  CUST_PUBLIC_FIG_TYPE          = ? , ");
			sqlQuery.append("  CUST_DEROGATORY_IND           = ? , ");
			sqlQuery.append("  CUST_DOC_ID                   = ? , ");
			sqlQuery.append("  CUST_DOC_ID_DATE              = ? , ");
			sqlQuery.append("  CUST_DOC_ID_EMIT_STATE_CODE   = ? , ");
			sqlQuery.append("  CUST_DOC_ID_EMIT_TYPE_SEQ     = ? , ");
			sqlQuery.append("  CUST_GRCARD_IND               = ? , ");
			sqlQuery.append("  CUST_SOC_SCTY_NBR             = ? , ");
			sqlQuery.append("  CUST_DEPND_NB                 = ? , ");
			sqlQuery.append("  CUST_CREATE_DATE              = ? , ");
			sqlQuery.append("  CUST_CREATE_USER              = ? , ");
			sqlQuery.append("  LAST_UPD_DATE                 = ? , ");
			sqlQuery.append("  LAST_UPD_USER                 = ? , ");
			sqlQuery.append("  CUST_OFFICER_SOEID            = ? , ");
			sqlQuery.append("  CUST_FATCA_IND                = ? , ");
			sqlQuery.append("  CUST_CRS_IND                  = ? , ");
			sqlQuery.append("  CUST_FATCA_TYPE               = ? , ");
			sqlQuery.append("  CUST_FATCA_W8_DATE            = ? , ");	
			sqlQuery.append("  CUST_CRS_DATE                 = ? , ");
			sqlQuery.append("  CUST_ACTY_AREA_CODE           = ? , ");
			sqlQuery.append("  CUST_NAIC_CODE                = ? , ");
			sqlQuery.append("  CUST_SIC_CODE                 = ? , ");
			sqlQuery.append("  CUST_CO_TYPE_CODE             = ? , ");
			sqlQuery.append("  CUST_FNDTN_DATE               = ? , ");
			sqlQuery.append("  CUST_TOVER_MTH_AMT            = ? , ");
			sqlQuery.append("  CUST_FLEX_DDA_IND             = ? , ");
			sqlQuery.append("  CUST_BIRTH_CNTRY_CO_CODE      = ? , ");
			sqlQuery.append("  CUST_ADM_NAME                 = ? , ");
			sqlQuery.append("  CUST_ADM_CPF                  = ? , ");
			sqlQuery.append("  LAST_AUTH_DATE                = ? , ");
			sqlQuery.append("  LAST_AUTH_USER                = ? , ");		
			sqlQuery.append("  CUST_FATCA_PJ_IN_US           = ? , ");
			sqlQuery.append("  CUST_FATCA_PJ_OUT_US          = ? , ");
			sqlQuery.append("  CUST_FATCA_PJ_OWNR_US         = ? , ");
			sqlQuery.append("  CUST_CRS_PJ_ADDR_OUT_US       = ? , ");
			sqlQuery.append("  CUST_CRS_PJ_ENF_LIAB          = ? , ");
			sqlQuery.append("  CUST_CRS_PJ_INVST_OUT         = ? ");
		
			if (vo.getIsAnnualReview()!=null) {
				if (vo.getIsAnnualReview()) {
					sqlQuery.append(" ,  LAST_UPD_REV_ANNL             = ? ");
				}
			}
			
			sqlQuery.append("WHERE CUST_GFCID_NBR         = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			
			int count = 1;

			if (!StringUtils.isBlank(vo.getCpfCnpj())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getCpfCnpj()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getNumberGFCID())) {
				preparedStatement.setString(count++, vo.getNumberGFCID());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getName())) {
				preparedStatement.setString(count++, vo.getName());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getNumberEM())) {
				preparedStatement.setString(count++, vo.getNumberEM());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCustomerType())) {
				preparedStatement.setString(count++, vo.getCustomerType());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getFiliation1())) {
				preparedStatement.setString(count++, vo.getFiliation1());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getFiliation2())) {
				preparedStatement.setString(count++, vo.getFiliation2());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCivilState())) {
				preparedStatement.setString(count++, vo.getCivilState());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getSpouseName())) {
				preparedStatement.setString(count++, vo.getSpouseName());
			} else {
				preparedStatement.setString(count++, null);
			}
						
			if (!StringUtils.isBlank(vo.getGender())) {
				preparedStatement.setString(count++, vo.getGender());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getBirthDate())) {
				
				Date birthDate =  FormatUtils.formatToDate(vo.getBirthDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(birthDate.getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCountryBirth())) {
			preparedStatement.setString(count++, vo.getCountryBirth());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getPlaceOfBirth())) {
				preparedStatement.setString(count++, vo.getPlaceOfBirth());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getUfPlaceOfBirth())) {
				preparedStatement.setString(count++, vo.getUfPlaceOfBirth());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getOccupation())) {
				preparedStatement.setString(count++, vo.getOccupation());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getOccupationNature())) {
				preparedStatement.setString(count++, vo.getOccupationNature());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getDeclaredIncome())) {
				preparedStatement.setString(count++, FormatUtils.unformatterValue(vo.getDeclaredIncome()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getDeclaredHeritage())) {
				preparedStatement.setString(count++, FormatUtils.unformatterValue(vo.getDeclaredHeritage()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getExemptIR()!=null) {
				if (vo.getExemptIR()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getIsEmployee()!=null) {
				if (vo.getIsEmployee()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCustomerStatus())) {
				preparedStatement.setString(count++, vo.getCustomerStatus());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			
			if (vo.getIsDeceased()!=null) {
				if (vo.getIsDeceased()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getIsSensitive()!=null) {
				if (vo.getIsSensitive()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getTypeClass())) {
				preparedStatement.setString(count++, vo.getTypeClass());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getDerogatoryInformation()!=null) {
				if (vo.getDerogatoryInformation()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getIdentityDocument())) {
				preparedStatement.setString(count++, vo.getIdentityDocument());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getEmitDocumentDate())) {
				
				Date emitDocumentDate =  FormatUtils.formatToDate(vo.getEmitDocumentDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(emitDocumentDate.getTime()));  
			
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getEmitDocumentUF())) {
				preparedStatement.setString(count++, vo.getEmitDocumentUF());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getEmitType())) {
				preparedStatement.setString(count++, vo.getEmitType());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getHaveGreenCard()!=null) {
				if (vo.getHaveGreenCard()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getSocialSecurityNumber())) {
				preparedStatement.setString(count++, vo.getSocialSecurityNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getNumberDependents()!=null) {
				preparedStatement.setInt(count++, vo.getNumberDependents());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCustCreateDate())) { 
				
				Date custCreateDate =  FormatUtils.formatToDate(vo.getCustCreateDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(custCreateDate.getTime()));
	 
				
			} else {
				preparedStatement.setDate(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCustCreateUser())) { 
				preparedStatement.setString(count++, vo.getCustCreateUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastUpdDate()!=null) {
				
 
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastUpdDate().getTime()));
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastUpdUser())) {
				preparedStatement.setString(count++, vo.getLastUpdUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getSOEIDBankerNumber())) {
				preparedStatement.setString(count++, vo.getSOEIDBankerNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			
			if (vo.getFormType()!=null || vo.getCustFatcaPjInUs()!=null || vo.getCustFatcaPjOutUs()!=null || vo.getCustFatcaPjOwnrUs()!=null ) {
				if ((vo.getCustFatcaPjInUs()!=null && vo.getCustFatcaPjInUs())
						|| (vo.getCustFatcaPjOutUs()!=null && vo.getCustFatcaPjOutUs())
							|| (vo.getCustFatcaPjOwnrUs()!=null && vo.getCustFatcaPjOwnrUs())
								|| (vo.getFormType()!=null && vo.getFormType().equals("8"))
									|| (vo.getFormType()!=null && vo.getFormType().equals("9"))) {
					
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getIsCrs()!=null || vo.getCustCrsPjAddrOutUs()!=null || vo.getCustCrsPjEnfLiab()!=null || vo.getCustCrsPjInvstOut()!=null ) {
				if ((vo.getIsCrs()!=null && vo.getIsCrs()) 
						|| (vo.getCustCrsPjAddrOutUs()!=null && vo.getCustCrsPjAddrOutUs())
							|| (vo.getCustCrsPjEnfLiab()!=null && vo.getCustCrsPjEnfLiab())
								|| (vo.getCustCrsPjInvstOut()!=null && vo.getCustCrsPjInvstOut())) {
					
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}		
		
			
			if (!StringUtils.isBlank(vo.getFormType())) { 
				preparedStatement.setString(count++, vo.getFormType());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getSignatureDateFatca())) { 
				
				Date signatureDateFatca =  FormatUtils.formatToDate(vo.getSignatureDateFatca(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(signatureDateFatca.getTime())); 
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getSignatureDateCrs())) { 
				
				Date signatureDateCrs =  FormatUtils.formatToDate(vo.getSignatureDateCrs(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(signatureDateCrs.getTime())); 
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getActivityMain())) {
				preparedStatement.setString(count++, vo.getActivityMain());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getNaicNumber())) {
				preparedStatement.setString(count++, vo.getNaicNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getSicNumber())) {
				preparedStatement.setString(count++, vo.getSicNumber());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getConstType())) {
				preparedStatement.setString(count++, vo.getConstType());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getFoundationDate())) {
				
				Date foundationDate =  FormatUtils.formatToDate(vo.getFoundationDate(),FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
				preparedStatement.setDate(count++, new java.sql.Date(foundationDate.getTime()));
				
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAverageMonthBilling())) {
				preparedStatement.setString(count++, FormatUtils.unformatterValue(vo.getAverageMonthBilling()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getHasFlexAccount()!=null) {
				if (vo.getHasFlexAccount()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCountryConstitution())) {
				preparedStatement.setString(count++, vo.getCountryConstitution());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAdmName())) {
				preparedStatement.setString(count++, vo.getAdmName());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAdmCpf())) {
				preparedStatement.setString(count++, FormatUtils.unformatterDoc(vo.getAdmCpf()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastAuthDate()!=null) {

				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastAuthDate().getTime()));
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastAuthUser())) {
				preparedStatement.setString(count++, vo.getLastAuthUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getCustFatcaPjInUs()!=null) {
				if (vo.getCustFatcaPjInUs()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getCustFatcaPjOutUs()!=null) {
				if (vo.getCustFatcaPjOutUs()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getCustFatcaPjOwnrUs()!=null) {
				if (vo.getCustFatcaPjOwnrUs()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getCustCrsPjAddrOutUs()!=null) {
				if (vo.getCustCrsPjAddrOutUs()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getCustCrsPjEnfLiab()!=null) {
				if (vo.getCustCrsPjEnfLiab()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getCustCrsPjInvstOut()!=null) {
				if (vo.getCustCrsPjInvstOut()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}
				
			if (vo.getIsAnnualReview()!=null) {
				if (vo.getIsAnnualReview()) {
					
					preparedStatement.setTimestamp(count++, new java.sql.Timestamp(new Date().getTime()));

				}
			} 
			
			if (!StringUtils.isBlank(vo.getNumberGFCID())) {
				preparedStatement.setString(count++, vo.getNumberGFCID());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			


			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			preparedStatement.execute();
			

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} catch (ParseException a) {
			throw new UnexpectedException(a.getErrorOffset(), C_ERROR_EXECUTING_STATEMENT, a);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}

	public RegisterDataCustomerVO getRegisterDataCustomer(String numberGFCID) throws ParseException {
		
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		RegisterDataCustomerVO vo = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("SELECT ");
			
			sqlQuery.append("  CUST_FULL_NAME               , ");
			sqlQuery.append("  CUST_CPF_CNPJ_NBR            , ");
			sqlQuery.append("  CUST_GFCID_NBR               , ");
			sqlQuery.append("  CUST_EM_NBR               	, ");
			sqlQuery.append("  CUST_TYPE_CODE               , ");
			sqlQuery.append("  CUST_FILIATION               , ");
			sqlQuery.append("  CUST_FILIATION_2             , ");
			sqlQuery.append("  CUST_CIVIL_STAT_CODE         , ");
			sqlQuery.append("  CUST_SPOUSE_NAME             , ");
			sqlQuery.append("  CUST_SEX_CODE                , ");
			sqlQuery.append("  CUST_BIRTH_DATE              , ");
			sqlQuery.append("  CUST_BIRTH_CITY_TEXT         , ");
			sqlQuery.append("  CUST_BIRTH_STATE_CODE        , ");
			sqlQuery.append("  CUST_BIRTH_CNTRY_CODE        , ");
			sqlQuery.append("  CUST_PROF_TEXT               , ");
			sqlQuery.append("  CUST_OCCUP_CODE              , ");
			sqlQuery.append("  CUST_INCO_AMT                , ");
			sqlQuery.append("  CUST_PRPTY_AMT               , ");
			sqlQuery.append("  CUST_IRRF_EXEMPT_IND         , ");
			sqlQuery.append("  CUST_EMPL_IND                , ");
			sqlQuery.append("  CUST_STAT_CODE               , ");
			sqlQuery.append("  CUST_DEATH_IND               , ");
			sqlQuery.append("  CUST_SENS_IND                , ");
			sqlQuery.append("  CUST_PUBLIC_FIG_TYPE         , ");
			sqlQuery.append("  CUST_DEROGATORY_IND          , ");
			sqlQuery.append("  CUST_DOC_ID                  , ");
			sqlQuery.append("  CUST_DOC_ID_DATE             , ");
			sqlQuery.append("  CUST_DOC_ID_EMIT_STATE_CODE  , ");
			sqlQuery.append("  CUST_DOC_ID_EMIT_TYPE_SEQ    , ");
			sqlQuery.append("  CUST_GRCARD_IND              , ");
			sqlQuery.append("  CUST_SOC_SCTY_NBR            , ");
			sqlQuery.append("  CUST_DEPND_NB                , ");
			sqlQuery.append("  CUST_CREATE_DATE             , ");
			sqlQuery.append("  CUST_CREATE_USER             , ");
			sqlQuery.append("  LAST_UPD_DATE                , ");
			sqlQuery.append("  LAST_UPD_USER                , ");
			sqlQuery.append("  CUST_OFFICER_SOEID           , ");
			sqlQuery.append("  CUST_FATCA_IND               , ");
			sqlQuery.append("  CUST_CRS_IND                 , ");
			sqlQuery.append("  CUST_FATCA_TYPE              , ");
			sqlQuery.append("  CUST_FATCA_W8_DATE           , ");
			sqlQuery.append("  CUST_CRS_DATE                , ");
			sqlQuery.append("  CUST_FATCA_W8_IND            , ");
			sqlQuery.append("  CUST_ACTY_AREA_CODE          , ");
			sqlQuery.append("  CUST_NAIC_CODE               , ");
			sqlQuery.append("  CUST_SIC_CODE                , ");
			sqlQuery.append("  CUST_CO_TYPE_CODE            , ");
			sqlQuery.append("  CUST_FNDTN_DATE              , ");
			sqlQuery.append("  CUST_TOVER_MTH_AMT           , ");
			sqlQuery.append("  CUST_FLEX_DDA_IND            , ");
			sqlQuery.append("  CUST_BIRTH_CNTRY_CO_CODE     , ");
			sqlQuery.append("  CUST_ADM_NAME                , ");
			sqlQuery.append("  CUST_ADM_CPF                 , ");
			sqlQuery.append("  LAST_AUTH_DATE               , ");
			sqlQuery.append("  LAST_AUTH_USER               , ");
			sqlQuery.append("  LAST_UPD_REV_ANNL            , ");
			sqlQuery.append("  REC_STAT_CODE 	            , ");
			sqlQuery.append("  CUST_FATCA_PJ_IN_US          , ");
			sqlQuery.append("  CUST_FATCA_PJ_OUT_US         , ");
			sqlQuery.append("  CUST_FATCA_PJ_OWNR_US        , ");
			sqlQuery.append("  CUST_CRS_PJ_ADDR_OUT_US      , ");
			sqlQuery.append("  CUST_CRS_PJ_ENF_LIAB         , ");
			sqlQuery.append("  CUST_CRS_PJ_INVST_OUT          ");
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER ");
			sqlQuery.append("WHERE CUST_GFCID_NBR          = ?   ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (numberGFCID != null && !numberGFCID.equals("")) {
				preparedStatement.setString(1, numberGFCID.toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				vo = new RegisterDataCustomerVO();
				while (rs.next()){
					
					vo.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);
					vo.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					vo.setNumberGFCID(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					vo.setNumberEM(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
					vo.setCustomerType(rs.getString("CUST_TYPE_CODE") != null ? rs.getString("CUST_TYPE_CODE").toString() : null);
					vo.setFiliation1(rs.getString("CUST_FILIATION") != null ? rs.getString("CUST_FILIATION").toString() : null);
					vo.setFiliation2(rs.getString("CUST_FILIATION_2") != null ? rs.getString("CUST_FILIATION_2").toString() : null);
					vo.setCivilState(rs.getString("CUST_CIVIL_STAT_CODE") != null ? rs.getString("CUST_CIVIL_STAT_CODE").toString() : null);
					vo.setSpouseName(rs.getString("CUST_SPOUSE_NAME") != null ? rs.getString("CUST_SPOUSE_NAME").toString() : null);
					vo.setGender(rs.getString("CUST_SEX_CODE") != null ? rs.getString("CUST_SEX_CODE").toString() : null);
					
					if(rs.getDate("CUST_BIRTH_DATE") != null){						
						Date birthDate = new Date(rs.getDate("CUST_BIRTH_DATE").getTime());
						vo.setBirthDate(FormatUtils.dateToString(birthDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
									
					vo.setPlaceOfBirth(rs.getString("CUST_BIRTH_CITY_TEXT"));
					vo.setUfPlaceOfBirth(rs.getString("CUST_BIRTH_STATE_CODE"));
					vo.setCountryBirth(rs.getString("CUST_BIRTH_CNTRY_CODE"));
					vo.setOccupation(rs.getString("CUST_PROF_TEXT") != null ? rs.getString("CUST_PROF_TEXT").toString() : null);
					vo.setOccupationNature(rs.getString("CUST_OCCUP_CODE") != null ? rs.getString("CUST_OCCUP_CODE").toString() : null);
					vo.setDeclaredIncome(rs.getString("CUST_INCO_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_INCO_AMT").toString()) : null);
					vo.setDeclaredHeritage(rs.getString("CUST_PRPTY_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_PRPTY_AMT").toString()) : null);
					
					if(rs.getString("CUST_IRRF_EXEMPT_IND") != null && !rs.getString("CUST_IRRF_EXEMPT_IND").equals("")){
						if(rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("S")){
							vo.setExemptIR(true);
						}else if (rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("N")){
							vo.setExemptIR(false);
						}
					}
					
					if(rs.getString("CUST_EMPL_IND") != null && !rs.getString("CUST_EMPL_IND").equals("")){
						if(rs.getString("CUST_EMPL_IND").trim().equals("S")){
							vo.setIsEmployee(true);
						}else if (rs.getString("CUST_EMPL_IND").trim().equals("N")){
							vo.setIsEmployee(false);
						}
					}					
					
					vo.setCustomerStatus(rs.getString("CUST_STAT_CODE") != null ? rs.getString("CUST_STAT_CODE").toString() : null);
					
					if(rs.getString("CUST_DEATH_IND") != null && !rs.getString("CUST_DEATH_IND").equals("")){
						if(rs.getString("CUST_DEATH_IND").trim().equals("S")){
							vo.setIsDeceased(true);
						}else if (rs.getString("CUST_DEATH_IND").trim().equals("N")){
							vo.setIsDeceased(false);
						}
					}	
					
					if(rs.getString("CUST_SENS_IND") != null && !rs.getString("CUST_SENS_IND").equals("")){
						if(rs.getString("CUST_SENS_IND").trim().equals("S")){
							vo.setIsSensitive(true);
						}else if (rs.getString("CUST_SENS_IND").trim().equals("N")){
							vo.setIsSensitive(false);
						}
					}

					vo.setTypeClass(rs.getString("CUST_PUBLIC_FIG_TYPE") != null ? rs.getString("CUST_PUBLIC_FIG_TYPE").toString() : null);
					
					if(rs.getString("CUST_DEROGATORY_IND") != null && !rs.getString("CUST_DEROGATORY_IND").equals("")){
						if(rs.getString("CUST_DEROGATORY_IND").trim().equals("S")){
							vo.setDerogatoryInformation(true);
						}else if (rs.getString("CUST_DEROGATORY_IND").trim().equals("N")){
							vo.setDerogatoryInformation(false);
						}
					}
					
					vo.setIdentityDocument(rs.getString("CUST_DOC_ID") != null ? rs.getString("CUST_DOC_ID").toString() : null);
					
					if(rs.getDate("CUST_DOC_ID_DATE") != null){						
						Date docDate = new Date(rs.getDate("CUST_DOC_ID_DATE").getTime());
						vo.setEmitDocumentDate(FormatUtils.dateToString(docDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
										
					vo.setEmitDocumentUF(rs.getString("CUST_DOC_ID_EMIT_STATE_CODE") != null ? rs.getString("CUST_DOC_ID_EMIT_STATE_CODE").toString() : null);
					vo.setEmitType(rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ") != null ? rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ").toString() : null);
					
					if(rs.getString("CUST_GRCARD_IND") != null && !rs.getString("CUST_GRCARD_IND").equals("")){
						if(rs.getString("CUST_GRCARD_IND").trim().equals("S")){
							vo.setHaveGreenCard(true);
						}else if (rs.getString("CUST_GRCARD_IND").trim().equals("N")){
							vo.setHaveGreenCard(false);
						}
					}
					
					vo.setSocialSecurityNumber(rs.getString("CUST_SOC_SCTY_NBR") != null ? rs.getString("CUST_SOC_SCTY_NBR").toString() : null);
					vo.setNumberDependents(rs.getInt("CUST_DEPND_NB"));
					
					if(rs.getDate("CUST_CREATE_DATE") != null){						
						Date createDate = new Date(rs.getDate("CUST_CREATE_DATE").getTime());
						vo.setCustCreateDate(FormatUtils.dateToString(createDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					vo.setCustCreateUser(rs.getString("CUST_CREATE_USER") != null ? rs.getString("CUST_CREATE_USER").toString() : null);

					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						vo.setLastUpdDate(lastUpdateDate);
					}

					vo.setLastUpdUser(rs.getString("LAST_UPD_USER") != null ? rs.getString("LAST_UPD_USER").toString() : null);
					vo.setSOEIDBankerNumber(rs.getString("CUST_OFFICER_SOEID") != null ? rs.getString("CUST_OFFICER_SOEID").toString() : null);
					
					if(rs.getString("CUST_FATCA_IND") != null && !rs.getString("CUST_FATCA_IND").equals("")){
						if(rs.getString("CUST_FATCA_IND").trim().equals("S")){
							vo.setIsFatca(true);
						}else if (rs.getString("CUST_FATCA_IND").trim().equals("N")){
							vo.setIsFatca(false);
						}
					}
					
					if(rs.getString("CUST_CRS_IND") != null && !rs.getString("CUST_CRS_IND").equals("")){
						if(rs.getString("CUST_CRS_IND").trim().equals("S")){
							vo.setIsCrs(true);
						}else if (rs.getString("CUST_CRS_IND").trim().equals("N")){
							vo.setIsCrs(false);
						}
					}
										
					vo.setFormType(rs.getString("CUST_FATCA_TYPE") != null ? rs.getString("CUST_FATCA_TYPE").toString() : null);
					
					if(rs.getDate("CUST_FATCA_W8_DATE") != null){						
						Date fatcaDate = new Date(rs.getDate("CUST_FATCA_W8_DATE").getTime());
						vo.setSignatureDateFatca(FormatUtils.dateToString(fatcaDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					if(rs.getDate("CUST_CRS_DATE") != null){						
						Date crsDate = new Date(rs.getDate("CUST_CRS_DATE").getTime());
						vo.setSignatureDateCrs(FormatUtils.dateToString(crsDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					//vo.set???(rs.getString("CUST_FATCA_W8_IND") != null ? rs.getString("CUST_FATCA_W8_IND").toString() : null);
										
					vo.setActivityMain(rs.getString("CUST_ACTY_AREA_CODE") != null ? rs.getString("CUST_ACTY_AREA_CODE").toString() : null);
					vo.setNaicNumber(rs.getString("CUST_NAIC_CODE") != null ? rs.getString("CUST_NAIC_CODE").toString() : null);
					vo.setSicNumber(rs.getString("CUST_SIC_CODE") != null ? rs.getString("CUST_SIC_CODE").toString() : null);
					vo.setConstType(rs.getString("CUST_CO_TYPE_CODE") != null ? rs.getString("CUST_CO_TYPE_CODE").toString() : null);
					
					if(rs.getDate("CUST_FNDTN_DATE") != null){						
						Date fndtnDate = new Date(rs.getDate("CUST_FNDTN_DATE").getTime());
						vo.setFoundationDate(FormatUtils.dateToString(fndtnDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					vo.setAverageMonthBilling(rs.getString("CUST_TOVER_MTH_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_TOVER_MTH_AMT").toString()) : null);
					
					if(rs.getString("CUST_FLEX_DDA_IND") != null && !rs.getString("CUST_FLEX_DDA_IND").equals("")){
						if(rs.getString("CUST_FLEX_DDA_IND").trim().equals("S")){
							vo.setHasFlexAccount(true);
						}else if (rs.getString("CUST_FLEX_DDA_IND").trim().equals("N")){
							vo.setHasFlexAccount(false);
						}
					}
					
					vo.setCountryConstitution(rs.getString("CUST_BIRTH_CNTRY_CO_CODE") != null ? rs.getString("CUST_BIRTH_CNTRY_CO_CODE").toString() : null);
					vo.setAdmName(rs.getString("CUST_ADM_NAME") != null ? rs.getString("CUST_ADM_NAME").toString() : null);
					vo.setAdmCpf(rs.getString("CUST_ADM_CPF") != null ? rs.getString("CUST_ADM_CPF").toString() : null);
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						vo.setLastAuthDate(lastAuthDate);
					}
										
					vo.setLastAuthUser(rs.getString("LAST_AUTH_USER") != null ? rs.getString("LAST_AUTH_USER").toString() : null);
					
					if(rs.getDate("LAST_UPD_REV_ANNL") != null){
						Date lastAnnualReviewDate = new Date(rs.getDate("LAST_UPD_REV_ANNL").getTime());
						vo.setLastAnnualReviewDate(lastAnnualReviewDate);
						vo.setIsAnnualReview(true);
					}else{
						vo.setIsAnnualReview(false);
					}
					
					vo.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					
					if(rs.getString("CUST_FATCA_PJ_IN_US") != null && !rs.getString("CUST_FATCA_PJ_IN_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("S")){
							vo.setCustFatcaPjInUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("N")){
							vo.setCustFatcaPjInUs(false);
						}
					}
					
					if(rs.getString("CUST_FATCA_PJ_OUT_US") != null && !rs.getString("CUST_FATCA_PJ_OUT_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("S")){
							vo.setCustFatcaPjOutUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("N")){
							vo.setCustFatcaPjOutUs(false);
						}
					}
					
					if(rs.getString("CUST_FATCA_PJ_OWNR_US") != null && !rs.getString("CUST_FATCA_PJ_OWNR_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("S")){
							vo.setCustFatcaPjOwnrUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("N")){
							vo.setCustFatcaPjOwnrUs(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US") != null && !rs.getString("CUST_CRS_PJ_ADDR_OUT_US").equals("")){
						if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("S")){
							vo.setCustCrsPjAddrOutUs(true);
						}else if (rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("N")){
							vo.setCustCrsPjAddrOutUs(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_ENF_LIAB") != null && !rs.getString("CUST_CRS_PJ_ENF_LIAB").equals("")){
						if(rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("S")){
							vo.setCustCrsPjEnfLiab(true);
						}else if (rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("N")){
							vo.setCustCrsPjEnfLiab(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_INVST_OUT") != null && !rs.getString("CUST_CRS_PJ_INVST_OUT").equals("")){
						if(rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("S")){
							vo.setCustCrsPjInvstOut(true);
						}else if (rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("N")){
							vo.setCustCrsPjInvstOut(false);
						}
					}				
					
					vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
		return vo;
		
	}
	
	public ArrayList<RegisterDataCustomerVO> searchCustomerListByER(String numberER) throws ParseException {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ArrayList<RegisterDataCustomerVO> voList = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("SELECT ");			
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FULL_NAME               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_GFCID_NBR               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_EM_NBR                  , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_TYPE_CODE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FILIATION               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FILIATION_2             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CIVIL_STAT_CODE         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SPOUSE_NAME             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SEX_CODE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_DATE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CITY_TEXT         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_STATE_CODE        , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CNTRY_CODE        , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PROF_TEXT               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_OCCUP_CODE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_INCO_AMT                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PRPTY_AMT               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_IRRF_EXEMPT_IND         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_EMPL_IND                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_STAT_CODE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEATH_IND               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SENS_IND                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PUBLIC_FIG_TYPE         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEROGATORY_IND          , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID                  , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_DATE             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_EMIT_STATE_CODE  , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_EMIT_TYPE_SEQ    , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_GRCARD_IND              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SOC_SCTY_NBR            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEPND_NB                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CREATE_DATE             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CREATE_USER             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_DATE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_USER                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_OFFICER_SOEID           , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_IND               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_IND                 , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_TYPE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_W8_DATE           , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_DATE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_W8_IND            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ACTY_AREA_CODE          , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_NAIC_CODE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SIC_CODE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CO_TYPE_CODE            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FNDTN_DATE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_TOVER_MTH_AMT           , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FLEX_DDA_IND            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CNTRY_CO_CODE     , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ADM_NAME                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ADM_CPF                 , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_AUTH_DATE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_AUTH_USER               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_REV_ANNL            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.REC_STAT_CODE 	              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_IN_US          , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_OUT_US         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_OWNR_US        , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_ADDR_OUT_US      , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_ENF_LIAB         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_INVST_OUT        , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.ER_NBR 	                      , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.EM_NBR 	                      , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.LAST_AUTH_DATE 	              , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.LAST_AUTH_USER_ID 	          , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.LAST_UPD_DATE 	              , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.LAST_UPD_USER_ID 	              , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.REC_STAT_CODE 	              , ");
			sqlQuery.append("  TPL_PRVT_ER_EM.ROLE_CUST_CODE 	              , ");
			sqlQuery.append("  TPL_PRVT_DMN_ROLE_CUST.ROLE_CUST_TEXT 	        ");
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER, " + C_PL_SCHEMA + "TPL_PRVT_ER_EM, " + C_PL_SCHEMA + "TPL_PRVT_DMN_ROLE_CUST ");
			sqlQuery.append("WHERE TPL_PRVT_CUSTOMER.CUST_EM_NBR = TPL_PRVT_ER_EM.EM_NBR   ");
			sqlQuery.append("AND TPL_PRVT_DMN_ROLE_CUST.ROLE_CUST_CODE = TPL_PRVT_ER_EM.ROLE_CUST_CODE   ");
			
			if (!StringUtils.isBlank(numberER)) {
				sqlQuery.append("AND TPL_PRVT_ER_EM.ER_NBR = ?   ");
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (!StringUtils.isBlank(numberER)) {
				preparedStatement.setString(1, numberER.toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				voList = new ArrayList<RegisterDataCustomerVO>();
				while (rs.next()){
					RegisterDataCustomerVO vo = new RegisterDataCustomerVO();
					vo.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);
					vo.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					vo.setNumberGFCID(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					vo.setNumberEM(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
					vo.setCustomerType(rs.getString("CUST_TYPE_CODE") != null ? rs.getString("CUST_TYPE_CODE").toString() : null);
					vo.setFiliation1(rs.getString("CUST_FILIATION") != null ? rs.getString("CUST_FILIATION").toString() : null);
					vo.setFiliation2(rs.getString("CUST_FILIATION_2") != null ? rs.getString("CUST_FILIATION_2").toString() : null);
					vo.setCivilState(rs.getString("CUST_CIVIL_STAT_CODE") != null ? rs.getString("CUST_CIVIL_STAT_CODE").toString() : null);
					vo.setSpouseName(rs.getString("CUST_SPOUSE_NAME") != null ? rs.getString("CUST_SPOUSE_NAME").toString() : null);
					vo.setGender(rs.getString("CUST_SEX_CODE") != null ? rs.getString("CUST_SEX_CODE").toString() : null);
					
					if(rs.getDate("CUST_BIRTH_DATE") != null){						
						Date birthDate = new Date(rs.getDate("CUST_BIRTH_DATE").getTime());
						vo.setBirthDate(FormatUtils.dateToString(birthDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
									
					vo.setPlaceOfBirth(rs.getString("CUST_BIRTH_CITY_TEXT"));
					vo.setUfPlaceOfBirth(rs.getString("CUST_BIRTH_STATE_CODE"));
					vo.setCountryBirth(rs.getString("CUST_BIRTH_CNTRY_CODE"));
					vo.setOccupation(rs.getString("CUST_PROF_TEXT") != null ? rs.getString("CUST_PROF_TEXT").toString() : null);
					vo.setOccupationNature(rs.getString("CUST_OCCUP_CODE") != null ? rs.getString("CUST_OCCUP_CODE").toString() : null);
					vo.setDeclaredIncome(rs.getString("CUST_INCO_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_INCO_AMT").toString()) : null);
					vo.setDeclaredHeritage(rs.getString("CUST_PRPTY_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_PRPTY_AMT").toString()) : null);
					
					if(rs.getString("CUST_IRRF_EXEMPT_IND") != null && !rs.getString("CUST_IRRF_EXEMPT_IND").equals("")){
						if(rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("S")){
							vo.setExemptIR(true);
						}else if (rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("N")){
							vo.setExemptIR(false);
						}
					}
					
					if(rs.getString("CUST_EMPL_IND") != null && !rs.getString("CUST_EMPL_IND").equals("")){
						if(rs.getString("CUST_EMPL_IND").trim().equals("S")){
							vo.setIsEmployee(true);
						}else if (rs.getString("CUST_EMPL_IND").trim().equals("N")){
							vo.setIsEmployee(false);
						}
					}					
					
					vo.setCustomerStatus(rs.getString("CUST_STAT_CODE") != null ? rs.getString("CUST_STAT_CODE").toString() : null);
					
					if(rs.getString("CUST_DEATH_IND") != null && !rs.getString("CUST_DEATH_IND").equals("")){
						if(rs.getString("CUST_DEATH_IND").trim().equals("S")){
							vo.setIsDeceased(true);
						}else if (rs.getString("CUST_DEATH_IND").trim().equals("N")){
							vo.setIsDeceased(false);
						}
					}	
					
					if(rs.getString("CUST_SENS_IND") != null && !rs.getString("CUST_SENS_IND").equals("")){
						if(rs.getString("CUST_SENS_IND").trim().equals("S")){
							vo.setIsSensitive(true);
						}else if (rs.getString("CUST_SENS_IND").trim().equals("N")){
							vo.setIsSensitive(false);
						}
					}

					vo.setTypeClass(rs.getString("CUST_PUBLIC_FIG_TYPE") != null ? rs.getString("CUST_PUBLIC_FIG_TYPE").toString() : null);
					
					if(rs.getString("CUST_DEROGATORY_IND") != null && !rs.getString("CUST_DEROGATORY_IND").equals("")){
						if(rs.getString("CUST_DEROGATORY_IND").trim().equals("S")){
							vo.setDerogatoryInformation(true);
						}else if (rs.getString("CUST_DEROGATORY_IND").trim().equals("N")){
							vo.setDerogatoryInformation(false);
						}
					}
					
					vo.setIdentityDocument(rs.getString("CUST_DOC_ID") != null ? rs.getString("CUST_DOC_ID").toString() : null);
					
					if(rs.getDate("CUST_DOC_ID_DATE") != null){						
						Date docDate = new Date(rs.getDate("CUST_DOC_ID_DATE").getTime());
						vo.setEmitDocumentDate(FormatUtils.dateToString(docDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
										
					vo.setEmitDocumentUF(rs.getString("CUST_DOC_ID_EMIT_STATE_CODE") != null ? rs.getString("CUST_DOC_ID_EMIT_STATE_CODE").toString() : null);
					vo.setEmitType(rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ") != null ? rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ").toString() : null);
					
					if(rs.getString("CUST_GRCARD_IND") != null && !rs.getString("CUST_GRCARD_IND").equals("")){
						if(rs.getString("CUST_GRCARD_IND").trim().equals("S")){
							vo.setHaveGreenCard(true);
						}else if (rs.getString("CUST_GRCARD_IND").trim().equals("N")){
							vo.setHaveGreenCard(false);
						}
					}
					
					vo.setSocialSecurityNumber(rs.getString("CUST_SOC_SCTY_NBR") != null ? rs.getString("CUST_SOC_SCTY_NBR").toString() : null);
					vo.setNumberDependents(rs.getInt("CUST_DEPND_NB"));
					
					if(rs.getDate("CUST_CREATE_DATE") != null){						
						Date createDate = new Date(rs.getDate("CUST_CREATE_DATE").getTime());
						vo.setCustCreateDate(FormatUtils.dateToString(createDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					vo.setCustCreateUser(rs.getString("CUST_CREATE_USER") != null ? rs.getString("CUST_CREATE_USER").toString() : null);

					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						vo.setLastUpdDate(lastUpdateDate);
					}

					vo.setLastUpdUser(rs.getString("LAST_UPD_USER") != null ? rs.getString("LAST_UPD_USER").toString() : null);
					vo.setSOEIDBankerNumber(rs.getString("CUST_OFFICER_SOEID") != null ? rs.getString("CUST_OFFICER_SOEID").toString() : null);
					
					if(rs.getString("CUST_FATCA_IND") != null && !rs.getString("CUST_FATCA_IND").equals("")){
						if(rs.getString("CUST_FATCA_IND").trim().equals("S")){
							vo.setIsFatca(true);
						}else if (rs.getString("CUST_FATCA_IND").trim().equals("N")){
							vo.setIsFatca(false);
						}
					}
					
					if(rs.getString("CUST_CRS_IND") != null && !rs.getString("CUST_CRS_IND").equals("")){
						if(rs.getString("CUST_CRS_IND").trim().equals("S")){
							vo.setIsCrs(true);
						}else if (rs.getString("CUST_CRS_IND").trim().equals("N")){
							vo.setIsCrs(false);
						}
					}
										
					vo.setFormType(rs.getString("CUST_FATCA_TYPE") != null ? rs.getString("CUST_FATCA_TYPE").toString() : null);
					
					if(rs.getDate("CUST_FATCA_W8_DATE") != null){						
						Date fatcaDate = new Date(rs.getDate("CUST_FATCA_W8_DATE").getTime());
						vo.setSignatureDateFatca(FormatUtils.dateToString(fatcaDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					if(rs.getDate("CUST_CRS_DATE") != null){						
						Date crsDate = new Date(rs.getDate("CUST_CRS_DATE").getTime());
						vo.setSignatureDateCrs(FormatUtils.dateToString(crsDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
										
					vo.setActivityMain(rs.getString("CUST_ACTY_AREA_CODE") != null ? rs.getString("CUST_ACTY_AREA_CODE").toString() : null);
					vo.setNaicNumber(rs.getString("CUST_NAIC_CODE") != null ? rs.getString("CUST_NAIC_CODE").toString() : null);
					vo.setSicNumber(rs.getString("CUST_SIC_CODE") != null ? rs.getString("CUST_SIC_CODE").toString() : null);
					vo.setConstType(rs.getString("CUST_CO_TYPE_CODE") != null ? rs.getString("CUST_CO_TYPE_CODE").toString() : null);
					
					if(rs.getDate("CUST_FNDTN_DATE") != null){						
						Date fndtnDate = new Date(rs.getDate("CUST_FNDTN_DATE").getTime());
						vo.setFoundationDate(FormatUtils.dateToString(fndtnDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					vo.setAverageMonthBilling(rs.getString("CUST_TOVER_MTH_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_TOVER_MTH_AMT").toString()) : null);
					
					if(rs.getString("CUST_FLEX_DDA_IND") != null && !rs.getString("CUST_FLEX_DDA_IND").equals("")){
						if(rs.getString("CUST_FLEX_DDA_IND").trim().equals("S")){
							vo.setHasFlexAccount(true);
						}else if (rs.getString("CUST_FLEX_DDA_IND").trim().equals("N")){
							vo.setHasFlexAccount(false);
						}
					}
					
					vo.setCountryConstitution(rs.getString("CUST_BIRTH_CNTRY_CO_CODE") != null ? rs.getString("CUST_BIRTH_CNTRY_CO_CODE").toString() : null);
					vo.setAdmName(rs.getString("CUST_ADM_NAME") != null ? rs.getString("CUST_ADM_NAME").toString() : null);
					vo.setAdmCpf(rs.getString("CUST_ADM_CPF") != null ? rs.getString("CUST_ADM_CPF").toString() : null);
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						vo.setLastAuthDate(lastAuthDate);
					}
										
					vo.setLastAuthUser(rs.getString("LAST_AUTH_USER") != null ? rs.getString("LAST_AUTH_USER").toString() : null);
					
					if(rs.getDate("LAST_UPD_REV_ANNL") != null){
						Date lastAnnualReviewDate = new Date(rs.getDate("LAST_UPD_REV_ANNL").getTime());
						vo.setLastAnnualReviewDate(lastAnnualReviewDate);
						vo.setIsAnnualReview(true);
					}else{
						vo.setIsAnnualReview(false);
					}
					
					vo.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					
					if(rs.getString("CUST_FATCA_PJ_IN_US") != null && !rs.getString("CUST_FATCA_PJ_IN_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("S")){
							vo.setCustFatcaPjInUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("N")){
							vo.setCustFatcaPjInUs(false);
						}
					}
					
					if(rs.getString("CUST_FATCA_PJ_OUT_US") != null && !rs.getString("CUST_FATCA_PJ_OUT_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("S")){
							vo.setCustFatcaPjOutUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("N")){
							vo.setCustFatcaPjOutUs(false);
						}
					}
					
					if(rs.getString("CUST_FATCA_PJ_OWNR_US") != null && !rs.getString("CUST_FATCA_PJ_OWNR_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("S")){
							vo.setCustFatcaPjOwnrUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("N")){
							vo.setCustFatcaPjOwnrUs(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US") != null && !rs.getString("CUST_CRS_PJ_ADDR_OUT_US").equals("")){
						if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("S")){
							vo.setCustCrsPjAddrOutUs(true);
						}else if (rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("N")){
							vo.setCustCrsPjAddrOutUs(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_ENF_LIAB") != null && !rs.getString("CUST_CRS_PJ_ENF_LIAB").equals("")){
						if(rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("S")){
							vo.setCustCrsPjEnfLiab(true);
						}else if (rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("N")){
							vo.setCustCrsPjEnfLiab(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_INVST_OUT") != null && !rs.getString("CUST_CRS_PJ_INVST_OUT").equals("")){
						if(rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("S")){
							vo.setCustCrsPjInvstOut(true);
						}else if (rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("N")){
							vo.setCustCrsPjInvstOut(false);
						}
					}				
					vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
					
					ErEmVO eremvo = new ErEmVO();
					eremvo.setErNbr(rs.getString("ER_NBR") != null ? rs.getString("ER_NBR").toString() : null);
					eremvo.setEmNbr(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR").toString() : null);
					eremvo.setLastAuthDate(rs.getDate("LAST_AUTH_DATE") != null ? new Date(rs.getDate("LAST_AUTH_DATE").getTime()) : null);
					eremvo.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);
					eremvo.setLastUpdDate(rs.getDate("LAST_UPD_DATE") != null ? new Date(rs.getDate("LAST_UPD_DATE").getTime()) : null);
					eremvo.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);
					eremvo.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					eremvo.setRoleCustCode(rs.getString("ROLE_CUST_CODE") != null ? rs.getString("ROLE_CUST_CODE").toString() : null);
					eremvo.setRoleCustDesc(rs.getString("ROLE_CUST_TEXT") != null ? rs.getString("ROLE_CUST_TEXT").toString() : null);
					
					vo.setEr_em(eremvo);
					
					voList.add(vo);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}		
		return voList;		
	}
		
	public ArrayList<RegisterDataCustomerVO> listByAccountNumber(String accountNumber) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<RegisterDataCustomerVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append("SELECT CUST.CUST_FULL_NAME, CUST.CUST_EM_NBR, CUST.CUST_GFCID_NBR, CUST.CUST_CPF_CNPJ_NBR , CUST.CUST_ADM_NAME, CUST.CUST_TYPE_CODE "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER CUST "
			+ "LEFT JOIN " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL CMPL "
			+ "ON CUST.CUST_CPF_CNPJ_NBR = CMPL.CPF_CNPJ_NBR ");
			

				
			String criteria = "";

			if (accountNumber != null && !accountNumber.equals("")) {
				criteria = criteria + " UPPER(CMPL.ACCT_NBR) = TRIM (?) ";
			}


			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + "ORDER BY CUST.CUST_FULL_NAME";
			} else {
				criteria = " ORDER BY CUST.CUST_FULL_NAME";
			}

			query.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			if (accountNumber != null && !accountNumber.equals("")) {
				preparedStatement.setString(count++, accountNumber);
			}


			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<RegisterDataCustomerVO>();
				while (rs.next()){
					
					RegisterDataCustomerVO result = new RegisterDataCustomerVO();
					result.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);								
					result.setNumberEM(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
					result.setNumberGFCID(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					result.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					result.setNameBanker(rs.getString("CUST_ADM_NAME") != null ? rs.getString("CUST_ADM_NAME").toString() : null);
					result.setCustomerType(rs.getString("CUST_TYPE_CODE") != null ? rs.getString("CUST_TYPE_CODE").toString() : null);
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
	
	public ArrayList<RegisterDataCustomerVO> listByAccountNumber(String accountNumber, String emNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		ArrayList<RegisterDataCustomerVO> resultList = null;

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append("SELECT CUST.CUST_FULL_NAME, CUST.CUST_EM_NBR, CUST.CUST_GFCID_NBR, CUST.CUST_CPF_CNPJ_NBR , CUST.CUST_ADM_NAME, CUST.CUST_TYPE_CODE "
			+ "FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER CUST "
			+ "LEFT JOIN " + C_PL_SCHEMA + "TPL_PRVT_ACCT_CMPL CMPL "
			+ "ON CUST.CUST_CPF_CNPJ_NBR = CMPL.CPF_CNPJ_NBR ");
			

				
			String criteria = "";

			if (accountNumber != null && !accountNumber.equals("")) {
				criteria = criteria + " UPPER(CMPL.ACCT_NBR) = TRIM (?) ";
			}


			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + "ORDER BY CUST.CUST_FULL_NAME";
			} else {
				criteria = " ORDER BY CUST.CUST_FULL_NAME";
			}

			query.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
			 
			int count = 1;

			if (accountNumber != null && !accountNumber.equals("")) {
				preparedStatement.setString(count++, accountNumber);
			}


			preparedStatement.replaceParametersInQuery(query.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<RegisterDataCustomerVO>();
				while (rs.next()){
					
					RegisterDataCustomerVO result = new RegisterDataCustomerVO();
					result.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);								
					result.setNumberEM(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
					result.setNumberGFCID(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					result.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					result.setNameBanker(rs.getString("CUST_ADM_NAME") != null ? rs.getString("CUST_ADM_NAME").toString() : null);
					result.setCustomerType(rs.getString("CUST_TYPE_CODE") != null ? rs.getString("CUST_TYPE_CODE").toString() : null);
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
	
	public RegisterDataCustomerVO getNameCpfCustomerCitiBankAccount(String accountNumber){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		RegisterDataCustomerVO vo = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			
			sqlQuery.append("SELECT "); 
			sqlQuery.append("  TPL_CUSTOMER_PRVT.CUST_FULL_NAME_TEXT             , ");
			sqlQuery.append("  TPL_CUSTOMER_PRVT.CUST_CPF_CNPJ_NBR                ");				
			sqlQuery.append(" FROM "  + C_PL_SCHEMA + " TPL_CUSTOMER_PRVT        , ");
			sqlQuery.append(C_O3_SCHEMA + " TO3_PRODUCT_ACCOUNT                  ");
			sqlQuery.append(" WHERE " + C_PL_SCHEMA +  " TPL_CUSTOMER_PRVT.CUST_NBR = " + C_O3_SCHEMA + " TO3_PRODUCT_ACCOUNT.CUST_NBR ");
			sqlQuery.append(" AND " + C_O3_SCHEMA +  " TO3_PRODUCT_ACCOUNT.CUR_ACCT_NBR = ? ");
			sqlQuery.append(" AND " + C_O3_SCHEMA +  " TO3_PRODUCT_ACCOUNT.PROD_CODE = '010' ");
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (!StringUtils.isBlank(accountNumber)) {
				preparedStatement.setString(1, accountNumber);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				vo = new RegisterDataCustomerVO();
				while (rs.next()){
					
					vo.setName(rs.getString("CUST_FULL_NAME_TEXT") != null ? rs.getString("CUST_FULL_NAME_TEXT").toString() : null);
					vo.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);

					vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
		return vo;
		
	}
	
	public RegisterDataCustomerVO getNameCpfCustomerCustodiaAccount(String accountNumber){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		RegisterDataCustomerVO vo = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			
			sqlQuery.append("SELECT "); 
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FULL_NAME             ");			
			sqlQuery.append(" FROM "  + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER        , ");
			sqlQuery.append(C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL                  ");
			sqlQuery.append(" WHERE " + C_PL_SCHEMA + " TPL_PRVT_CUSTOMER. CUST_CPF_CNPJ_NBR = " + C_PL_SCHEMA + " TPL_PRVT_ACCT_CMPL.CPF_CNPJ_NBR ");
			sqlQuery.append(" AND " + C_PL_SCHEMA +  " TPL_PRVT_ACCT_CMPL.CUR_ACCT_NBR = ? ");
			
			
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (!StringUtils.isBlank(accountNumber)) {
				preparedStatement.setString(1, accountNumber);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				vo = new RegisterDataCustomerVO();
				while (rs.next()){
					
					vo.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);
					vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
		return vo;
		
	}

	public ArrayList<RegisterDataCustomerVO> searchCustomerListByCpfCnpj(String cpfCnpj) throws ParseException {
			
			ManagedRdbConnection connection = null;
			CitiStatement preparedStatement = null;
			ResultSet rs = null;
			StringBuffer sqlQuery = new StringBuffer();
			ArrayList<RegisterDataCustomerVO> voList = null;
			
			try {
				connection = OracleODSDAOFactory.getConnection();
				
				sqlQuery.append("SELECT ");			
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FULL_NAME               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR            , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_GFCID_NBR               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_EM_NBR                  , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_TYPE_CODE               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FILIATION               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FILIATION_2             , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CIVIL_STAT_CODE         , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SPOUSE_NAME             , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SEX_CODE                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_DATE              , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CITY_TEXT         , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_STATE_CODE        , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CNTRY_CODE        , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PROF_TEXT               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_OCCUP_CODE              , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_INCO_AMT                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PRPTY_AMT               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_IRRF_EXEMPT_IND         , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_EMPL_IND                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_STAT_CODE               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEATH_IND               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SENS_IND                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PUBLIC_FIG_TYPE         , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEROGATORY_IND          , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID                  , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_DATE             , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_EMIT_STATE_CODE  , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_EMIT_TYPE_SEQ    , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_GRCARD_IND              , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SOC_SCTY_NBR            , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEPND_NB                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CREATE_DATE             , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CREATE_USER             , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_DATE                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_USER                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_OFFICER_SOEID           , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_IND               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_IND                 , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_TYPE              , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_W8_DATE           , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_DATE                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_W8_IND            , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ACTY_AREA_CODE          , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_NAIC_CODE               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SIC_CODE                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CO_TYPE_CODE            , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FNDTN_DATE              , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_TOVER_MTH_AMT           , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FLEX_DDA_IND            , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CNTRY_CO_CODE     , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ADM_NAME                , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ADM_CPF                 , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_AUTH_DATE               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_AUTH_USER               , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_REV_ANNL            , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.REC_STAT_CODE 	              , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_IN_US          , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_OUT_US         , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_OWNR_US        , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_ADDR_OUT_US      , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_ENF_LIAB         , ");
				sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_INVST_OUT        , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.ER_NBR 	                      , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.EM_NBR 	                      , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.LAST_AUTH_DATE 	              , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.LAST_AUTH_USER_ID 	          , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.LAST_UPD_DATE 	              , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.LAST_UPD_USER_ID 	              , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.REC_STAT_CODE 	              , ");
				sqlQuery.append("  TPL_PRVT_ER_EM.ROLE_CUST_CODE 	              , ");
				sqlQuery.append("  TPL_PRVT_DMN_ROLE_CUST.ROLE_CUST_TEXT 	        ");
				sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER, " + C_PL_SCHEMA + "TPL_PRVT_ER_EM, " + C_PL_SCHEMA + "TPL_PRVT_DMN_ROLE_CUST ");
				sqlQuery.append("WHERE TPL_PRVT_CUSTOMER.CUST_EM_NBR = TPL_PRVT_ER_EM.EM_NBR   ");
				sqlQuery.append("AND TPL_PRVT_DMN_ROLE_CUST.ROLE_CUST_CODE = TPL_PRVT_ER_EM.ROLE_CUST_CODE   ");
				
				if (!StringUtils.isBlank(cpfCnpj)) {
					sqlQuery.append("AND TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR = ?   ");
				}
				
				preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
				 
			
				if (!StringUtils.isBlank(cpfCnpj)) {
					preparedStatement.setString(1, FormatUtils.unformatterDoc(cpfCnpj).replace(" ", "").trim().toUpperCase());
				}
	
				preparedStatement.replaceParametersInQuery(sqlQuery.toString());
				rs = preparedStatement.executeQuery();
				
				
				if(rs!=null){
					voList = new ArrayList<RegisterDataCustomerVO>();
					while (rs.next()){
						RegisterDataCustomerVO vo = new RegisterDataCustomerVO();
						vo.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);
						vo.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
						vo.setNumberGFCID(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
						vo.setNumberEM(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
						vo.setCustomerType(rs.getString("CUST_TYPE_CODE") != null ? rs.getString("CUST_TYPE_CODE").toString() : null);
						vo.setFiliation1(rs.getString("CUST_FILIATION") != null ? rs.getString("CUST_FILIATION").toString() : null);
						vo.setFiliation2(rs.getString("CUST_FILIATION_2") != null ? rs.getString("CUST_FILIATION_2").toString() : null);
						vo.setCivilState(rs.getString("CUST_CIVIL_STAT_CODE") != null ? rs.getString("CUST_CIVIL_STAT_CODE").toString() : null);
						vo.setSpouseName(rs.getString("CUST_SPOUSE_NAME") != null ? rs.getString("CUST_SPOUSE_NAME").toString() : null);
						vo.setGender(rs.getString("CUST_SEX_CODE") != null ? rs.getString("CUST_SEX_CODE").toString() : null);
						
						if(rs.getDate("CUST_BIRTH_DATE") != null){						
							Date birthDate = new Date(rs.getDate("CUST_BIRTH_DATE").getTime());
							vo.setBirthDate(FormatUtils.dateToString(birthDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
						}
										
						vo.setPlaceOfBirth(rs.getString("CUST_BIRTH_CITY_TEXT"));
						vo.setUfPlaceOfBirth(rs.getString("CUST_BIRTH_STATE_CODE"));
						vo.setCountryBirth(rs.getString("CUST_BIRTH_CNTRY_CODE"));
						vo.setOccupation(rs.getString("CUST_PROF_TEXT") != null ? rs.getString("CUST_PROF_TEXT").toString() : null);
						vo.setOccupationNature(rs.getString("CUST_OCCUP_CODE") != null ? rs.getString("CUST_OCCUP_CODE").toString() : null);						
						vo.setDeclaredIncome(rs.getString("CUST_INCO_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_INCO_AMT").toString()) : null);
						vo.setDeclaredHeritage(rs.getString("CUST_PRPTY_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_PRPTY_AMT").toString()) : null);
						
						if(rs.getString("CUST_IRRF_EXEMPT_IND") != null && !rs.getString("CUST_IRRF_EXEMPT_IND").equals("")){
							if(rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("S")){
								vo.setExemptIR(true);
							}else if (rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("N")){
								vo.setExemptIR(false);
							}
						}
						
						if(rs.getString("CUST_EMPL_IND") != null && !rs.getString("CUST_EMPL_IND").equals("")){
							if(rs.getString("CUST_EMPL_IND").trim().equals("S")){
								vo.setIsEmployee(true);
							}else if (rs.getString("CUST_EMPL_IND").trim().equals("N")){
								vo.setIsEmployee(false);
							}
						}					
						
						vo.setCustomerStatus(rs.getString("CUST_STAT_CODE") != null ? rs.getString("CUST_STAT_CODE").toString() : null);
						
						if(rs.getString("CUST_DEATH_IND") != null && !rs.getString("CUST_DEATH_IND").equals("")){
							if(rs.getString("CUST_DEATH_IND").trim().equals("S")){
								vo.setIsDeceased(true);
							}else if (rs.getString("CUST_DEATH_IND").trim().equals("N")){
								vo.setIsDeceased(false);
							}
						}	
						
						if(rs.getString("CUST_SENS_IND") != null && !rs.getString("CUST_SENS_IND").equals("")){
							if(rs.getString("CUST_SENS_IND").trim().equals("S")){
								vo.setIsSensitive(true);
							}else if (rs.getString("CUST_SENS_IND").trim().equals("N")){
								vo.setIsSensitive(false);
							}
						}
	
						vo.setTypeClass(rs.getString("CUST_PUBLIC_FIG_TYPE") != null ? rs.getString("CUST_PUBLIC_FIG_TYPE").toString() : null);
						
						if(rs.getString("CUST_DEROGATORY_IND") != null && !rs.getString("CUST_DEROGATORY_IND").equals("")){
							if(rs.getString("CUST_DEROGATORY_IND").trim().equals("S")){
								vo.setDerogatoryInformation(true);
							}else if (rs.getString("CUST_DEROGATORY_IND").trim().equals("N")){
								vo.setDerogatoryInformation(false);
							}
						}
						
						vo.setIdentityDocument(rs.getString("CUST_DOC_ID") != null ? rs.getString("CUST_DOC_ID").toString() : null);
						
						if(rs.getDate("CUST_DOC_ID_DATE") != null){						
							Date docDate = new Date(rs.getDate("CUST_DOC_ID_DATE").getTime());
							vo.setEmitDocumentDate(FormatUtils.dateToString(docDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
						}
											
						vo.setEmitDocumentUF(rs.getString("CUST_DOC_ID_EMIT_STATE_CODE") != null ? rs.getString("CUST_DOC_ID_EMIT_STATE_CODE").toString() : null);
						vo.setEmitType(rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ") != null ? rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ").toString() : null);
						
						if(rs.getString("CUST_GRCARD_IND") != null && !rs.getString("CUST_GRCARD_IND").equals("")){
							if(rs.getString("CUST_GRCARD_IND").trim().equals("S")){
								vo.setHaveGreenCard(true);
							}else if (rs.getString("CUST_GRCARD_IND").trim().equals("N")){
								vo.setHaveGreenCard(false);
							}
						}
						
						vo.setSocialSecurityNumber(rs.getString("CUST_SOC_SCTY_NBR") != null ? rs.getString("CUST_SOC_SCTY_NBR").toString() : null);
						vo.setNumberDependents(rs.getInt("CUST_DEPND_NB"));
						
						if(rs.getDate("CUST_CREATE_DATE") != null){						
							Date createDate = new Date(rs.getDate("CUST_CREATE_DATE").getTime());
							vo.setCustCreateDate(FormatUtils.dateToString(createDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
						}
						
						vo.setCustCreateUser(rs.getString("CUST_CREATE_USER") != null ? rs.getString("CUST_CREATE_USER").toString() : null);
	
						if(rs.getDate("LAST_UPD_DATE") != null){						
							Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
							vo.setLastUpdDate(lastUpdateDate);
						}
	
						vo.setLastUpdUser(rs.getString("LAST_UPD_USER") != null ? rs.getString("LAST_UPD_USER").toString() : null);
						vo.setSOEIDBankerNumber(rs.getString("CUST_OFFICER_SOEID") != null ? rs.getString("CUST_OFFICER_SOEID").toString() : null);
						
						if(rs.getString("CUST_FATCA_IND") != null && !rs.getString("CUST_FATCA_IND").equals("")){
							if(rs.getString("CUST_FATCA_IND").trim().equals("S")){
								vo.setIsFatca(true);
							}else if (rs.getString("CUST_FATCA_IND").trim().equals("N")){
								vo.setIsFatca(false);
							}
						}
						
						if(rs.getString("CUST_CRS_IND") != null && !rs.getString("CUST_CRS_IND").equals("")){
							if(rs.getString("CUST_CRS_IND").trim().equals("S")){
								vo.setIsCrs(true);
							}else if (rs.getString("CUST_CRS_IND").trim().equals("N")){
								vo.setIsCrs(false);
							}
						}
											
						vo.setFormType(rs.getString("CUST_FATCA_TYPE") != null ? rs.getString("CUST_FATCA_TYPE").toString() : null);
						
						if(rs.getDate("CUST_FATCA_W8_DATE") != null){						
							Date fatcaDate = new Date(rs.getDate("CUST_FATCA_W8_DATE").getTime());
							vo.setSignatureDateFatca(FormatUtils.dateToString(fatcaDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
						}
						
						if(rs.getDate("CUST_CRS_DATE") != null){						
							Date crsDate = new Date(rs.getDate("CUST_CRS_DATE").getTime());
							vo.setSignatureDateCrs(FormatUtils.dateToString(crsDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
						}
						//vo.set???(rs.getString("CUST_FATCA_W8_IND") != null ? rs.getString("CUST_FATCA_W8_IND").toString() : null);
											
						vo.setActivityMain(rs.getString("CUST_ACTY_AREA_CODE") != null ? rs.getString("CUST_ACTY_AREA_CODE").toString() : null);
						vo.setNaicNumber(rs.getString("CUST_NAIC_CODE") != null ? rs.getString("CUST_NAIC_CODE").toString() : null);
						vo.setSicNumber(rs.getString("CUST_SIC_CODE") != null ? rs.getString("CUST_SIC_CODE").toString() : null);
						vo.setConstType(rs.getString("CUST_CO_TYPE_CODE") != null ? rs.getString("CUST_CO_TYPE_CODE").toString() : null);
						
						if(rs.getDate("CUST_FNDTN_DATE") != null){						
							Date fndtnDate = new Date(rs.getDate("CUST_FNDTN_DATE").getTime());
							vo.setFoundationDate(FormatUtils.dateToString(fndtnDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
						}
						
						vo.setAverageMonthBilling(rs.getString("CUST_TOVER_MTH_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_TOVER_MTH_AMT").toString()) : null);
						
						if(rs.getString("CUST_FLEX_DDA_IND") != null && !rs.getString("CUST_FLEX_DDA_IND").equals("")){
							if(rs.getString("CUST_FLEX_DDA_IND").trim().equals("S")){
								vo.setHasFlexAccount(true);
							}else if (rs.getString("CUST_FLEX_DDA_IND").trim().equals("N")){
								vo.setHasFlexAccount(false);
							}
						}
						
						vo.setCountryConstitution(rs.getString("CUST_BIRTH_CNTRY_CO_CODE") != null ? rs.getString("CUST_BIRTH_CNTRY_CO_CODE").toString() : null);
						vo.setAdmName(rs.getString("CUST_ADM_NAME") != null ? rs.getString("CUST_ADM_NAME").toString() : null);
						vo.setAdmCpf(rs.getString("CUST_ADM_CPF") != null ? rs.getString("CUST_ADM_CPF").toString() : null);
						
						if(rs.getDate("LAST_AUTH_DATE") != null){						
							Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
							vo.setLastAuthDate(lastAuthDate);
						}
											
						vo.setLastAuthUser(rs.getString("LAST_AUTH_USER") != null ? rs.getString("LAST_AUTH_USER").toString() : null);
						
						if(rs.getDate("LAST_UPD_REV_ANNL") != null){
							Date lastAnnualReviewDate = new Date(rs.getDate("LAST_UPD_REV_ANNL").getTime());
							vo.setLastAnnualReviewDate(lastAnnualReviewDate);
							vo.setIsAnnualReview(true);
						}else{
							vo.setIsAnnualReview(false);
						}
						
						vo.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
						
						if(rs.getString("CUST_FATCA_PJ_IN_US") != null && !rs.getString("CUST_FATCA_PJ_IN_US").equals("")){
							if(rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("S")){
								vo.setCustFatcaPjInUs(true);
							}else if (rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("N")){
								vo.setCustFatcaPjInUs(false);
							}
						}
						
						if(rs.getString("CUST_FATCA_PJ_OUT_US") != null && !rs.getString("CUST_FATCA_PJ_OUT_US").equals("")){
							if(rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("S")){
								vo.setCustFatcaPjOutUs(true);
							}else if (rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("N")){
								vo.setCustFatcaPjOutUs(false);
							}
						}
						
						if(rs.getString("CUST_FATCA_PJ_OWNR_US") != null && !rs.getString("CUST_FATCA_PJ_OWNR_US").equals("")){
							if(rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("S")){
								vo.setCustFatcaPjOwnrUs(true);
							}else if (rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("N")){
								vo.setCustFatcaPjOwnrUs(false);
							}
						}
						
						if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US") != null && !rs.getString("CUST_CRS_PJ_ADDR_OUT_US").equals("")){
							if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("S")){
								vo.setCustCrsPjAddrOutUs(true);
							}else if (rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("N")){
								vo.setCustCrsPjAddrOutUs(false);
							}
						}
						
						if(rs.getString("CUST_CRS_PJ_ENF_LIAB") != null && !rs.getString("CUST_CRS_PJ_ENF_LIAB").equals("")){
							if(rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("S")){
								vo.setCustCrsPjEnfLiab(true);
							}else if (rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("N")){
								vo.setCustCrsPjEnfLiab(false);
							}
						}
						
						if(rs.getString("CUST_CRS_PJ_INVST_OUT") != null && !rs.getString("CUST_CRS_PJ_INVST_OUT").equals("")){
							if(rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("S")){
								vo.setCustCrsPjInvstOut(true);
							}else if (rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("N")){
								vo.setCustCrsPjInvstOut(false);
							}
						}				
						vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
						
						ErEmVO eremvo = new ErEmVO();
						eremvo.setErNbr(rs.getString("ER_NBR") != null ? rs.getString("ER_NBR").toString() : null);
						eremvo.setEmNbr(rs.getString("EM_NBR") != null ? rs.getString("EM_NBR").toString() : null);
						eremvo.setLastAuthDate(rs.getDate("LAST_AUTH_DATE") != null ? new Date(rs.getDate("LAST_AUTH_DATE").getTime()) : null);
						eremvo.setLastAuthUser(rs.getString("LAST_AUTH_USER_ID") != null ? rs.getString("LAST_AUTH_USER_ID").toString() : null);
						eremvo.setLastUpdDate(rs.getDate("LAST_UPD_DATE") != null ? new Date(rs.getDate("LAST_UPD_DATE").getTime()) : null);
						eremvo.setLastUpdUser(rs.getString("LAST_UPD_USER_ID") != null ? rs.getString("LAST_UPD_USER_ID").toString() : null);
						eremvo.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
						eremvo.setRoleCustCode(rs.getString("ROLE_CUST_CODE") != null ? rs.getString("ROLE_CUST_CODE").toString() : null);
						eremvo.setRoleCustDesc(rs.getString("ROLE_CUST_TEXT") != null ? rs.getString("ROLE_CUST_TEXT").toString() : null);
						
						vo.setEr_em(eremvo);
						
						voList.add(vo);
					}
				}			
				
				rs.close();
			} catch (SQLException e) {
				throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
			} finally {
				closeStatement(preparedStatement);
				closeConnection(connection);
			}		
			return voList;		
		}

	
	public ArrayList<RegisterDataCustomerVO> searchSimpleCustomerListByCpfCnpj(String cpfCnpj) throws ParseException {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ArrayList<RegisterDataCustomerVO> voList = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append("SELECT ");			
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FULL_NAME               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_GFCID_NBR               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_EM_NBR                  , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_TYPE_CODE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FILIATION               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FILIATION_2             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CIVIL_STAT_CODE         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SPOUSE_NAME             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SEX_CODE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_DATE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CITY_TEXT         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_STATE_CODE        , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CNTRY_CODE        , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PROF_TEXT               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_OCCUP_CODE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_INCO_AMT                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PRPTY_AMT               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_IRRF_EXEMPT_IND         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_EMPL_IND                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_STAT_CODE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEATH_IND               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SENS_IND                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_PUBLIC_FIG_TYPE         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEROGATORY_IND          , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID                  , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_DATE             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_EMIT_STATE_CODE  , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DOC_ID_EMIT_TYPE_SEQ    , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_GRCARD_IND              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SOC_SCTY_NBR            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_DEPND_NB                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CREATE_DATE             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CREATE_USER             , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_DATE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_USER                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_OFFICER_SOEID           , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_IND               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_IND                 , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_TYPE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_W8_DATE           , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_DATE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_W8_IND            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ACTY_AREA_CODE          , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_NAIC_CODE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_SIC_CODE                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CO_TYPE_CODE            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FNDTN_DATE              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_TOVER_MTH_AMT           , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FLEX_DDA_IND            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_BIRTH_CNTRY_CO_CODE     , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ADM_NAME                , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_ADM_CPF                 , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_AUTH_DATE               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_AUTH_USER               , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.LAST_UPD_REV_ANNL            , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.REC_STAT_CODE 	              , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_IN_US          , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_OUT_US         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_FATCA_PJ_OWNR_US        , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_ADDR_OUT_US      , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_ENF_LIAB         , ");
			sqlQuery.append("  TPL_PRVT_CUSTOMER.CUST_CRS_PJ_INVST_OUT          ");
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_CUSTOMER ");
			sqlQuery.append("WHERE 1=1   ");
			
			if (!StringUtils.isBlank(cpfCnpj)) {
				sqlQuery.append("AND TPL_PRVT_CUSTOMER.CUST_CPF_CNPJ_NBR = ?   ");
			}
			
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
		
			if (!StringUtils.isBlank(cpfCnpj)) {
				preparedStatement.setString(1, FormatUtils.unformatterDoc(cpfCnpj).replace(" ", "").trim().toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			
			if(rs!=null){
				voList = new ArrayList<RegisterDataCustomerVO>();
				while (rs.next()){
					RegisterDataCustomerVO vo = new RegisterDataCustomerVO();
					vo.setName(rs.getString("CUST_FULL_NAME") != null ? rs.getString("CUST_FULL_NAME").toString() : null);
					vo.setCpfCnpj(rs.getString("CUST_CPF_CNPJ_NBR") != null ? rs.getString("CUST_CPF_CNPJ_NBR").toString() : null);
					vo.setNumberGFCID(rs.getString("CUST_GFCID_NBR") != null ? rs.getString("CUST_GFCID_NBR").toString() : null);
					vo.setNumberEM(rs.getString("CUST_EM_NBR") != null ? rs.getString("CUST_EM_NBR").toString() : null);
					vo.setCustomerType(rs.getString("CUST_TYPE_CODE") != null ? rs.getString("CUST_TYPE_CODE").toString() : null);
					vo.setFiliation1(rs.getString("CUST_FILIATION") != null ? rs.getString("CUST_FILIATION").toString() : null);
					vo.setFiliation2(rs.getString("CUST_FILIATION_2") != null ? rs.getString("CUST_FILIATION_2").toString() : null);
					vo.setCivilState(rs.getString("CUST_CIVIL_STAT_CODE") != null ? rs.getString("CUST_CIVIL_STAT_CODE").toString() : null);
					vo.setSpouseName(rs.getString("CUST_SPOUSE_NAME") != null ? rs.getString("CUST_SPOUSE_NAME").toString() : null);
					vo.setGender(rs.getString("CUST_SEX_CODE") != null ? rs.getString("CUST_SEX_CODE").toString() : null);
					
					if(rs.getDate("CUST_BIRTH_DATE") != null){						
						Date birthDate = new Date(rs.getDate("CUST_BIRTH_DATE").getTime());
						vo.setBirthDate(FormatUtils.dateToString(birthDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
									
					vo.setPlaceOfBirth(rs.getString("CUST_BIRTH_CITY_TEXT"));
					vo.setUfPlaceOfBirth(rs.getString("CUST_BIRTH_STATE_CODE"));
					vo.setCountryBirth(rs.getString("CUST_BIRTH_CNTRY_CODE"));
					vo.setOccupation(rs.getString("CUST_PROF_TEXT") != null ? rs.getString("CUST_PROF_TEXT").toString() : null);
					vo.setOccupationNature(rs.getString("CUST_OCCUP_CODE") != null ? rs.getString("CUST_OCCUP_CODE").toString() : null);
					vo.setDeclaredIncome(rs.getString("CUST_INCO_AMT") != null ? rs.getString("CUST_INCO_AMT").toString() : null);
					vo.setDeclaredHeritage(rs.getString("CUST_PRPTY_AMT") != null ? rs.getString("CUST_PRPTY_AMT").toString() : null);
					
					if(rs.getString("CUST_IRRF_EXEMPT_IND") != null && !rs.getString("CUST_IRRF_EXEMPT_IND").equals("")){
						if(rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("S")){
							vo.setExemptIR(true);
						}else if (rs.getString("CUST_IRRF_EXEMPT_IND").trim().equals("N")){
							vo.setExemptIR(false);
						}
					}
					
					if(rs.getString("CUST_EMPL_IND") != null && !rs.getString("CUST_EMPL_IND").equals("")){
						if(rs.getString("CUST_EMPL_IND").trim().equals("S")){
							vo.setIsEmployee(true);
						}else if (rs.getString("CUST_EMPL_IND").trim().equals("N")){
							vo.setIsEmployee(false);
						}
					}					
					
					vo.setCustomerStatus(rs.getString("CUST_STAT_CODE") != null ? rs.getString("CUST_STAT_CODE").toString() : null);
					
					if(rs.getString("CUST_DEATH_IND") != null && !rs.getString("CUST_DEATH_IND").equals("")){
						if(rs.getString("CUST_DEATH_IND").trim().equals("S")){
							vo.setIsDeceased(true);
						}else if (rs.getString("CUST_DEATH_IND").trim().equals("N")){
							vo.setIsDeceased(false);
						}
					}	
					
					if(rs.getString("CUST_SENS_IND") != null && !rs.getString("CUST_SENS_IND").equals("")){
						if(rs.getString("CUST_SENS_IND").trim().equals("S")){
							vo.setIsSensitive(true);
						}else if (rs.getString("CUST_SENS_IND").trim().equals("N")){
							vo.setIsSensitive(false);
						}
					}

					vo.setTypeClass(rs.getString("CUST_PUBLIC_FIG_TYPE") != null ? rs.getString("CUST_PUBLIC_FIG_TYPE").toString() : null);
					
					if(rs.getString("CUST_DEROGATORY_IND") != null && !rs.getString("CUST_DEROGATORY_IND").equals("")){
						if(rs.getString("CUST_DEROGATORY_IND").trim().equals("S")){
							vo.setDerogatoryInformation(true);
						}else if (rs.getString("CUST_DEROGATORY_IND").trim().equals("N")){
							vo.setDerogatoryInformation(false);
						}
					}
					
					vo.setIdentityDocument(rs.getString("CUST_DOC_ID") != null ? rs.getString("CUST_DOC_ID").toString() : null);
					
					if(rs.getDate("CUST_DOC_ID_DATE") != null){						
						Date docDate = new Date(rs.getDate("CUST_DOC_ID_DATE").getTime());
						vo.setEmitDocumentDate(FormatUtils.dateToString(docDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
										
					vo.setEmitDocumentUF(rs.getString("CUST_DOC_ID_EMIT_STATE_CODE") != null ? rs.getString("CUST_DOC_ID_EMIT_STATE_CODE").toString() : null);
					vo.setEmitType(rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ") != null ? rs.getString("CUST_DOC_ID_EMIT_TYPE_SEQ").toString() : null);
					
					if(rs.getString("CUST_GRCARD_IND") != null && !rs.getString("CUST_GRCARD_IND").equals("")){
						if(rs.getString("CUST_GRCARD_IND").trim().equals("S")){
							vo.setHaveGreenCard(true);
						}else if (rs.getString("CUST_GRCARD_IND").trim().equals("N")){
							vo.setHaveGreenCard(false);
						}
					}
					
					vo.setSocialSecurityNumber(rs.getString("CUST_SOC_SCTY_NBR") != null ? rs.getString("CUST_SOC_SCTY_NBR").toString() : null);
					vo.setNumberDependents(rs.getInt("CUST_DEPND_NB"));
					
					if(rs.getDate("CUST_CREATE_DATE") != null){						
						Date createDate = new Date(rs.getDate("CUST_CREATE_DATE").getTime());
						vo.setCustCreateDate(FormatUtils.dateToString(createDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					vo.setCustCreateUser(rs.getString("CUST_CREATE_USER") != null ? rs.getString("CUST_CREATE_USER").toString() : null);

					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date lastUpdateDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						vo.setLastUpdDate(lastUpdateDate);
					}

					vo.setLastUpdUser(rs.getString("LAST_UPD_USER") != null ? rs.getString("LAST_UPD_USER").toString() : null);
					vo.setSOEIDBankerNumber(rs.getString("CUST_OFFICER_SOEID") != null ? rs.getString("CUST_OFFICER_SOEID").toString() : null);
					
					if(rs.getString("CUST_FATCA_IND") != null && !rs.getString("CUST_FATCA_IND").equals("")){
						if(rs.getString("CUST_FATCA_IND").trim().equals("S")){
							vo.setIsFatca(true);
						}else if (rs.getString("CUST_FATCA_IND").trim().equals("N")){
							vo.setIsFatca(false);
						}
					}
					
					if(rs.getString("CUST_CRS_IND") != null && !rs.getString("CUST_CRS_IND").equals("")){
						if(rs.getString("CUST_CRS_IND").trim().equals("S")){
							vo.setIsCrs(true);
						}else if (rs.getString("CUST_CRS_IND").trim().equals("N")){
							vo.setIsCrs(false);
						}
					}
										
					vo.setFormType(rs.getString("CUST_FATCA_TYPE") != null ? rs.getString("CUST_FATCA_TYPE").toString() : null);
					
					if(rs.getDate("CUST_FATCA_W8_DATE") != null){						
						Date fatcaDate = new Date(rs.getDate("CUST_FATCA_W8_DATE").getTime());
						vo.setSignatureDateFatca(FormatUtils.dateToString(fatcaDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					if(rs.getDate("CUST_CRS_DATE") != null){						
						Date crsDate = new Date(rs.getDate("CUST_CRS_DATE").getTime());
						vo.setSignatureDateCrs(FormatUtils.dateToString(crsDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
										
					vo.setActivityMain(rs.getString("CUST_ACTY_AREA_CODE") != null ? rs.getString("CUST_ACTY_AREA_CODE").toString() : null);
					vo.setNaicNumber(rs.getString("CUST_NAIC_CODE") != null ? rs.getString("CUST_NAIC_CODE").toString() : null);
					vo.setSicNumber(rs.getString("CUST_SIC_CODE") != null ? rs.getString("CUST_SIC_CODE").toString() : null);
					vo.setConstType(rs.getString("CUST_CO_TYPE_CODE") != null ? rs.getString("CUST_CO_TYPE_CODE").toString() : null);
					
					if(rs.getDate("CUST_FNDTN_DATE") != null){						
						Date fndtnDate = new Date(rs.getDate("CUST_FNDTN_DATE").getTime());
						vo.setFoundationDate(FormatUtils.dateToString(fndtnDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY));
					}
					
					vo.setAverageMonthBilling(rs.getString("CUST_TOVER_MTH_AMT") != null ? FormatUtils.formatterValue(rs.getString("CUST_TOVER_MTH_AMT").toString()) : null);
					
					if(rs.getString("CUST_FLEX_DDA_IND") != null && !rs.getString("CUST_FLEX_DDA_IND").equals("")){
						if(rs.getString("CUST_FLEX_DDA_IND").trim().equals("S")){
							vo.setHasFlexAccount(true);
						}else if (rs.getString("CUST_FLEX_DDA_IND").trim().equals("N")){
							vo.setHasFlexAccount(false);
						}
					}
					
					vo.setCountryConstitution(rs.getString("CUST_BIRTH_CNTRY_CO_CODE") != null ? rs.getString("CUST_BIRTH_CNTRY_CO_CODE").toString() : null);
					vo.setAdmName(rs.getString("CUST_ADM_NAME") != null ? rs.getString("CUST_ADM_NAME").toString() : null);
					vo.setAdmCpf(rs.getString("CUST_ADM_CPF") != null ? rs.getString("CUST_ADM_CPF").toString() : null);
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date lastAuthDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						vo.setLastAuthDate(lastAuthDate);
					}
										
					vo.setLastAuthUser(rs.getString("LAST_AUTH_USER") != null ? rs.getString("LAST_AUTH_USER").toString() : null);
					
					if(rs.getDate("LAST_UPD_REV_ANNL") != null){
						Date lastAnnualReviewDate = new Date(rs.getDate("LAST_UPD_REV_ANNL").getTime());
						vo.setLastAnnualReviewDate(lastAnnualReviewDate);
						vo.setIsAnnualReview(true);
					}else{
						vo.setIsAnnualReview(false);
					}
					
					vo.setRecStatCode(rs.getString("REC_STAT_CODE") != null ? rs.getString("REC_STAT_CODE").toString() : null);
					
					
					if(rs.getString("CUST_FATCA_PJ_IN_US") != null && !rs.getString("CUST_FATCA_PJ_IN_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("S")){
							vo.setCustFatcaPjInUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_IN_US").trim().equals("N")){
							vo.setCustFatcaPjInUs(false);
						}
					}
					
					if(rs.getString("CUST_FATCA_PJ_OUT_US") != null && !rs.getString("CUST_FATCA_PJ_OUT_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("S")){
							vo.setCustFatcaPjOutUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_OUT_US").trim().equals("N")){
							vo.setCustFatcaPjOutUs(false);
						}
					}
					
					if(rs.getString("CUST_FATCA_PJ_OWNR_US") != null && !rs.getString("CUST_FATCA_PJ_OWNR_US").equals("")){
						if(rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("S")){
							vo.setCustFatcaPjOwnrUs(true);
						}else if (rs.getString("CUST_FATCA_PJ_OWNR_US").trim().equals("N")){
							vo.setCustFatcaPjOwnrUs(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US") != null && !rs.getString("CUST_CRS_PJ_ADDR_OUT_US").equals("")){
						if(rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("S")){
							vo.setCustCrsPjAddrOutUs(true);
						}else if (rs.getString("CUST_CRS_PJ_ADDR_OUT_US").trim().equals("N")){
							vo.setCustCrsPjAddrOutUs(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_ENF_LIAB") != null && !rs.getString("CUST_CRS_PJ_ENF_LIAB").equals("")){
						if(rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("S")){
							vo.setCustCrsPjEnfLiab(true);
						}else if (rs.getString("CUST_CRS_PJ_ENF_LIAB").trim().equals("N")){
							vo.setCustCrsPjEnfLiab(false);
						}
					}
					
					if(rs.getString("CUST_CRS_PJ_INVST_OUT") != null && !rs.getString("CUST_CRS_PJ_INVST_OUT").equals("")){
						if(rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("S")){
							vo.setCustCrsPjInvstOut(true);
						}else if (rs.getString("CUST_CRS_PJ_INVST_OUT").trim().equals("N")){
							vo.setCustCrsPjInvstOut(false);
						}
					}				
					vo.setTableOrigin(TableTypeEnum.EFFECTIVE);
					
					voList.add(vo);
				}
			}			
			
			rs.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}		
		return voList;		
	}
}
