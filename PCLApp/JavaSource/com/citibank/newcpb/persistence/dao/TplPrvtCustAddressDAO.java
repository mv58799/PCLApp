package com.citibank.newcpb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.vo.AddressVO;
import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class TplPrvtCustAddressDAO extends BaseOracleDAO {
	
	public void deleteByGFCID(String custGfcidNbr) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_CUST_ADDRESS WHERE CUST_GFCID_NBR = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));	

			if (!StringUtils.isBlank(custGfcidNbr)) {
				preparedStatement.setString(1, custGfcidNbr);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	public AddressVO insert(AddressVO vo) {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer sqlQuery = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			sqlQuery.append("INSERT INTO " + C_PL_SCHEMA + "TPL_PRVT_CUST_ADDRESS ( ");
			sqlQuery.append("    CUST_GFCID_NBR, ");
			sqlQuery.append("    ADDR_SEQ_NBR, ");
			sqlQuery.append("    ADDR_TYPE_CODE, ");
			sqlQuery.append("    ADDR_NAME_TEXT, ");
			sqlQuery.append("    ADDR_NEIGHB_TEXT, ");
			sqlQuery.append("    ADDR_CITY_TEXT, ");
			sqlQuery.append("    ADDR_STATE_CODE, ");
			sqlQuery.append("    ADDR_CNTRY_CODE, ");
			sqlQuery.append("    ZIP_CODE, ");
			sqlQuery.append("    ADDR_MAIL_IND, ");
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER ");
			sqlQuery.append("  ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));

			int count = 1;

			if (vo.getCustGfcidNbr() != null) {
				preparedStatement.setLong(count++, vo.getCustGfcidNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			if (vo.getAddrSeqNbr() != null) {
				preparedStatement.setLong(count++, vo.getAddrSeqNbr());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getAddrTypeCode())) {
				preparedStatement.setString(count++, vo.getAddrTypeCode());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(vo.getStreet())) {
				preparedStatement.setString(count++, vo.getStreet());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getNeighborhood())) {
				preparedStatement.setString(count++, vo.getNeighborhood());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getCity())) {
				preparedStatement.setString(count++, vo.getCity());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getUf())) {
				preparedStatement.setString(count++, vo.getUf());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getAddrCntryCode())) {
				preparedStatement.setString(count++, vo.getAddrCntryCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getZipCode())) {
				preparedStatement.setString(count++, vo.getZipCode());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getIsCorrespondence()!=null) {
				if (vo.getIsCorrespondence()) {
					preparedStatement.setString(count++, "S");
				}else{
					preparedStatement.setString(count++, "N");
				}
			} else {
				preparedStatement.setString(count++, null);
			}	
				
			
			if (vo.getLastAuthDate() != null) {
				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastAuthDate().getTime())); 

			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastAuthUser())) {
				preparedStatement.setString(count++, vo.getLastAuthUser());
			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (vo.getLastUpdDate() != null) {

				preparedStatement.setTimestamp(count++, new java.sql.Timestamp(vo.getLastUpdDate().getTime())); 

			} else {
				preparedStatement.setString(count++, null);
			}
			
			if (!StringUtils.isBlank(vo.getLastUpdUser())) {
				preparedStatement.setString(count++, vo.getLastUpdUser());
			} else {
				preparedStatement.setString(count++, null);
			}

			
			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		return vo;
	}

	public ArrayList<AddressVO> list(String custGfcidNbr){
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuffer sqlQuery = new StringBuffer();
		ArrayList<AddressVO> resultList = null;
		
		try {
			connection = OracleODSDAOFactory.getConnection();
			
			sqlQuery.append(" SELECT  ");
			sqlQuery.append("    CUST_GFCID_NBR, ");
			sqlQuery.append("    ADDR_SEQ_NBR, ");
			sqlQuery.append("    ADDR_TYPE_CODE, ");
			sqlQuery.append("    ADDR_NAME_TEXT, ");
			sqlQuery.append("    ADDR_NEIGHB_TEXT, ");
			sqlQuery.append("    ADDR_CITY_TEXT, ");
			sqlQuery.append("    ADDR_STATE_CODE, ");
			sqlQuery.append("    ADDR_CNTRY_CODE, ");
			sqlQuery.append("    ZIP_CODE, ");
			sqlQuery.append("    ADDR_MAIL_IND, ");
			sqlQuery.append("    LAST_AUTH_DATE, ");
			sqlQuery.append("    LAST_AUTH_USER, ");
			sqlQuery.append("    LAST_UPD_DATE, ");
			sqlQuery.append("    LAST_UPD_USER ");	
			sqlQuery.append(" FROM " + C_PL_SCHEMA + "TPL_PRVT_CUST_ADDRESS ");

			String criteria = "";
						
			if (custGfcidNbr != null && !custGfcidNbr.equals("")) {
				if (criteria.length() > 0) {
					criteria = criteria + "AND UPPER(CUST_GFCID_NBR) = (?) ";
				} else {
					criteria = criteria + "UPPER(CUST_GFCID_NBR) = TRIM (?) ";
				}
			}

			if (criteria.length() > 0) {
				criteria = " WHERE " + criteria + "ORDER BY ADDR_SEQ_NBR";
			} else {
				criteria = " ORDER BY ADDR_SEQ_NBR";
			}

			sqlQuery.append(criteria);
			preparedStatement = new CitiStatement(connection.prepareStatement(sqlQuery.toString()));
			 
			int count = 1;

			if (custGfcidNbr != null && !custGfcidNbr.equals("")) {
				preparedStatement.setString(count++, custGfcidNbr.toUpperCase());
			}

			preparedStatement.replaceParametersInQuery(sqlQuery.toString());
			rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				resultList = new ArrayList<AddressVO>();
				while (rs.next()){
					
					AddressVO result = new AddressVO();
					result.setCustGfcidNbr(rs.getLong("CUST_GFCID_NBR"));					
					result.setAddrSeqNbr(rs.getInt("ADDR_SEQ_NBR"));
					result.setAddrTypeCode(rs.getString("ADDR_TYPE_CODE"));
					result.setStreet(rs.getString("ADDR_NAME_TEXT"));
					result.setNeighborhood(rs.getString("ADDR_NEIGHB_TEXT"));
					result.setCity(rs.getString("ADDR_CITY_TEXT"));
					result.setUf(rs.getString("ADDR_STATE_CODE"));
					result.setAddrCntryCode(rs.getString("ADDR_CNTRY_CODE"));
					result.setZipCode(rs.getString("ZIP_CODE"));
					
					
					if(rs.getString("ADDR_MAIL_IND") != null && !rs.getString("ADDR_MAIL_IND").equals("")){
						if(rs.getString("ADDR_MAIL_IND").trim().equals("S")){
							result.setIsCorrespondence(true);
						}else if (rs.getString("ADDR_MAIL_IND").trim().equals("N")){
							result.setIsCorrespondence(false);
						}
					}
					
					if(rs.getDate("LAST_AUTH_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_AUTH_DATE").getTime());
						result.setLastAuthDate(docDate);
					}
					
					result.setLastAuthUser(rs.getString("LAST_AUTH_USER") != null ? rs.getString("LAST_AUTH_USER").toString() : null);
					
					if(rs.getDate("LAST_UPD_DATE") != null){						
						Date docDate = new Date(rs.getDate("LAST_UPD_DATE").getTime());
						result.setLastUpdDate(docDate);
					}

					result.setLastUpdUser(rs.getString("LAST_UPD_USER") != null ? rs.getString("LAST_UPD_USER").toString() : null);
					result.setTableOrigin(TableTypeEnum.EFFECTIVE);
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
	
	public void deleteByKey(Long custGfcidNbr, Integer addrSeqNbr, String addrTypeCode) {
		
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();

			query.append(" DELETE FROM " + C_PL_SCHEMA + "TPL_PRVT_CUST_ADDRESS WHERE CUST_GFCID_NBR = ? AND ADDR_SEQ_NBR = ? AND ADDR_TYPE_CODE = ? ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			if (custGfcidNbr!=null) {
				preparedStatement.setString(1, custGfcidNbr.toString());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (addrSeqNbr!=null) {
				preparedStatement.setString(2, addrSeqNbr.toString());
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}
			
			if (!StringUtils.isBlank(addrTypeCode)) {
				preparedStatement.setString(3, addrTypeCode);
			} else {
				throw new UnexpectedException(C_ERROR_EXECUTING_STATEMENT, null);
			}

			
			preparedStatement.replaceParametersInQuery(query.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}
}
