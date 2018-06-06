package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.bg.valueobject.TbgSegmentEntityVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.valueobject.TbgOfficerEntityVO;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCustomerPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplTermAdhesionEntityVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

public class OracleTplTermAdhesionDAO extends BaseOracleTplTermAdhesionDAO {

	public List<TplTermAdhesionEntityVO> findByCurAcctNbr(Long curAcctNbr, TableTypeEnum tableType) {
		List<TplTermAdhesionEntityVO> result = new ArrayList<TplTermAdhesionEntityVO>();

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append("	TERM.PROD_ACCT_CODE ");
			sql.append("	, TERM.PROD_UNDER_ACCT_CODE ");
			sql.append("	, TERM.PROD_CODE ");
			sql.append("	, TERM.SYS_CODE ");
			sql.append("	, TERM.SYS_SEG_CODE ");
			sql.append("	, TERM.TRSRY_SYS_IND ");
			sql.append("	, TERM.DP_SYS_IND ");
			sql.append("	, TERM.ADH_TERM_TYPE_CODE ");
			sql.append("	, TERM.LAST_APPRV_DATE ");
			sql.append("	, TERM.LAST_APPRV_USER_ID ");
			sql.append("	, TERM.LAST_UPD_DATE ");
			sql.append("	, TERM.LAST_UPD_USER_ID ");
			sql.append(!TableTypeEnum.EFFECTIVE.equals(tableType) ? "	, TERM.OPERN_TYPE_CODE " : "");
			sql.append(" from ");
			sql.append("	PL.TPL_TERM_ADHESION").append(tableType.getSufixo()).append(" TERM ");

			sql.append(" inner join ");
			sql.append("	O3.TO3_PRODUCT_ACCOUNT PROD_ACCT ");
			sql.append(" on ");
			sql.append("	TERM.PROD_ACCT_CODE = PROD_ACCT.PROD_ACCT_CODE and ");
			sql.append("	TERM.PROD_UNDER_ACCT_CODE = PROD_ACCT.PROD_UNDER_ACCT_CODE ");

			sql.append(" inner join ");
			sql.append("	PL.TPL_PRODUCT PROD ");
			sql.append(" on ");
			sql.append("	TERM.PROD_CODE = PROD.PROD_CODE and ");
			sql.append("	TERM.SYS_CODE = PROD.SYS_CODE and ");
			sql.append("	TERM.SYS_SEG_CODE = PROD.SYS_SEG_CODE ");

			sql.append(" where ");
			sql.append("	PROD_ACCT.CUR_ACCT_NBR = ? and ");
			sql.append("	PROD_ACCT.PROD_CODE = '010' and ");
			sql.append("	PROD_ACCT.SYS_CODE = 'DA' and ");
			sql.append("	PROD_ACCT.SYS_SEG_CODE = 1 and ");
			sql.append("	PROD.PROD_CODE IN ('LCA', 'LCI') and ");
			sql.append("	PROD.SYS_CODE = 'OD' and ");
			sql.append("	PROD.SYS_SEG_CODE = 2 ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, curAcctNbr);
			log(sql.toString(), curAcctNbr);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				TplTermAdhesionEntityVO vo = getTermAdhesion(rs, tableType);

				result.add(vo);
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public TplTermAdhesionEntityVO findByKey(Long prodAcctCode, Long prodUnderAcctCode, String prodCode, String sysCode, Integer sysSegCode, TableTypeEnum tableType) {
		TplTermAdhesionEntityVO result = null;

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append("	PROD_ACCT_CODE ");
			sql.append("	, PROD_UNDER_ACCT_CODE ");
			sql.append("	, PROD_CODE ");
			sql.append("	, SYS_CODE ");
			sql.append("	, SYS_SEG_CODE ");
			sql.append("	, TRSRY_SYS_IND ");
			sql.append("	, DP_SYS_IND ");
			sql.append("	, ADH_TERM_TYPE_CODE ");
			sql.append("	, LAST_APPRV_DATE ");
			sql.append("	, LAST_APPRV_USER_ID ");
			sql.append("	, LAST_UPD_DATE ");
			sql.append("	, LAST_UPD_USER_ID ");
			sql.append(!TableTypeEnum.EFFECTIVE.equals(tableType) ? "	, OPERN_TYPE_CODE " : "");
			sql.append(" from ");
			sql.append("	PL.TPL_TERM_ADHESION").append(tableType.getSufixo());
			sql.append(" where ");
			sql.append("	PROD_ACCT_CODE = ? and ");
			sql.append("	PROD_UNDER_ACCT_CODE = ? and ");
			sql.append("	PROD_CODE = ? and ");
			sql.append("	SYS_CODE = ? and ");
			sql.append("	SYS_SEG_CODE = ? ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, prodAcctCode);
			stmt.setLong(2, prodUnderAcctCode);
			stmt.setString(3, prodCode);
			stmt.setString(4, sysCode);
			stmt.setInt(5, sysSegCode);

			log(sql.toString(), prodAcctCode,prodUnderAcctCode,prodCode, sysCode, sysSegCode  );
			
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = getTermAdhesion(rs, tableType);
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public List<TplTermAdhesionEntityVO> findByProdAcctCodeAndProdUnderAcctCode(Long prodAcctCode, Long prodUnderAcctCode, TableTypeEnum tableType) {
		List<TplTermAdhesionEntityVO> result = new ArrayList<TplTermAdhesionEntityVO>();

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append("	PROD_ACCT_CODE ");
			sql.append("	, PROD_UNDER_ACCT_CODE ");
			sql.append("	, PROD_CODE ");
			sql.append("	, SYS_CODE ");
			sql.append("	, SYS_SEG_CODE ");
			sql.append("	, TRSRY_SYS_IND ");
			sql.append("	, DP_SYS_IND ");
			sql.append("	, ADH_TERM_TYPE_CODE ");
			sql.append("	, LAST_APPRV_DATE ");
			sql.append("	, LAST_APPRV_USER_ID ");
			sql.append("	, LAST_UPD_DATE ");
			sql.append("	, LAST_UPD_USER_ID ");
			sql.append(!TableTypeEnum.EFFECTIVE.equals(tableType) ? "	, OPERN_TYPE_CODE " : "");
			sql.append(" from ");
			sql.append("	PL.TPL_TERM_ADHESION").append(tableType.getSufixo());
			sql.append(" where ");
			sql.append("	PROD_ACCT_CODE = ? and ");
			sql.append("	PROD_UNDER_ACCT_CODE = ? and ");
			sql.append("	PROD_CODE IN ('LCA', 'LCI') and ");
			sql.append("	SYS_CODE = 'OD' and ");
			sql.append("	SYS_SEG_CODE = 2 ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, prodAcctCode);
			stmt.setLong(2, prodUnderAcctCode);
			log(sql.toString(),prodAcctCode , prodUnderAcctCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				TplTermAdhesionEntityVO vo = getTermAdhesion(rs, tableType);

				result.add(vo);
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public void insert(TplTermAdhesionEntityVO vo, TableTypeEnum tableType) {

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" insert into PL.TPL_TERM_ADHESION").append(tableType.getSufixo());
			sql.append("	(PROD_ACCT_CODE ");
			sql.append("	, PROD_UNDER_ACCT_CODE ");
			sql.append("	, PROD_CODE ");
			sql.append("	, SYS_CODE ");
			sql.append("	, SYS_SEG_CODE ");
			sql.append("	, TRSRY_SYS_IND ");
			sql.append("	, DP_SYS_IND ");
			sql.append("	, ADH_TERM_TYPE_CODE ");
			sql.append("	, LAST_APPRV_DATE ");
			sql.append("	, LAST_APPRV_USER_ID ");
			sql.append("	, LAST_UPD_DATE ");
			sql.append("	, LAST_UPD_USER_ID ");
			sql.append(!TableTypeEnum.EFFECTIVE.equals(tableType) ? "	, OPERN_TYPE_CODE " : "");
			sql.append("	) values (?,?,?,?,?,?,?,?,?,?,?,?").append(!TableTypeEnum.EFFECTIVE.equals(tableType) ? ",?" : "").append(")");

			int index = 0;

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(++index, vo.getProdAcctCode());
			stmt.setLong(++index, vo.getProdUnderAcctCode());
			stmt.setString(++index, vo.getProdCode());
			stmt.setString(++index, vo.getSysCode());
			stmt.setInt(++index, vo.getSysSegCode());
			stmt.setString(++index, vo.getTrsrySysInd());
			stmt.setString(++index, vo.getDpSysInd());
			stmt.setString(++index, vo.getAdhTermTypeCode());
			
			if (vo.getLastApprvDate() == null) {
				stmt.setNull(++index, Types.DATE);
			} else {
				stmt.setTimestamp(++index, new java.sql.Timestamp(vo.getLastApprvDate().getTime()));
			}

			if (vo.getLastApprvUserId() == null) {
				stmt.setNull(++index, Types.VARCHAR);
			} else {
				stmt.setString(++index, vo.getLastApprvUserId());
			}

			stmt.setTimestamp(++index, new java.sql.Timestamp(vo.getLastUpdDate().getTime()));
			stmt.setString(++index, vo.getLastUpdUserId());

			if (!TableTypeEnum.EFFECTIVE.equals(tableType)) {
				stmt.setString(++index, vo.getOpernTypeCode());
			}
			log(sql.toString());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeStatement(stmt);
			closeConnection(connection);
		}

	}

	public void update(TplTermAdhesionEntityVO vo, TableTypeEnum tableType) {

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" update PL.TPL_TERM_ADHESION").append(tableType.getSufixo());
			sql.append(" set ");
			sql.append("	TRSRY_SYS_IND = ? ");
			sql.append("	, DP_SYS_IND = ? ");
			sql.append("	, ADH_TERM_TYPE_CODE = ? ");
			sql.append("	, LAST_APPRV_DATE = ? ");
			sql.append("	, LAST_APPRV_USER_ID = ? ");
			sql.append("	, LAST_UPD_DATE = ? ");
			sql.append("	, LAST_UPD_USER_ID = ? ");
			sql.append(!TableTypeEnum.EFFECTIVE.equals(tableType) ? "	, OPERN_TYPE_CODE = ? " : "");
			sql.append(" where ");
			sql.append("	PROD_ACCT_CODE = ? and ");
			sql.append("	PROD_UNDER_ACCT_CODE = ? and ");
			sql.append("	PROD_CODE = ? and ");
			sql.append("	SYS_CODE = ? and ");
			sql.append("	SYS_SEG_CODE = ? ");

			int index = 0;

			stmt = connection.prepareStatement(sql.toString());
			stmt.setString(++index, vo.getTrsrySysInd());
			stmt.setString(++index, vo.getDpSysInd());
			stmt.setString(++index, vo.getAdhTermTypeCode());

			if (vo.getLastApprvDate() == null) {
				stmt.setNull(++index, Types.DATE);
			} else {
				stmt.setTimestamp(++index, new java.sql.Timestamp(vo.getLastApprvDate().getTime()));
			}

			if (vo.getLastApprvUserId() == null) {
				stmt.setNull(++index, Types.VARCHAR);
			} else {
				stmt.setString(++index, vo.getLastApprvUserId());
			}

			stmt.setTimestamp(++index, new java.sql.Timestamp(vo.getLastUpdDate().getTime()));
			stmt.setString(++index, vo.getLastUpdUserId());
			if (!TableTypeEnum.EFFECTIVE.equals(tableType)) {
				stmt.setString(++index, vo.getOpernTypeCode());
			}
			stmt.setLong(++index, vo.getProdAcctCode());
			stmt.setLong(++index, vo.getProdUnderAcctCode());
			stmt.setString(++index, vo.getProdCode());
			stmt.setString(++index, vo.getSysCode());
			stmt.setInt(++index, vo.getSysSegCode());

			log(sql.toString());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeStatement(stmt);
			closeConnection(connection);
		}

	}

	public void delete(Long prodAcctCode, Long prodUnderAcctCode, String prodCode, String sysCode, Integer sysSegCode, TableTypeEnum tableType) {

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" delete from PL.TPL_TERM_ADHESION").append(tableType.getSufixo());
			sql.append(" where PROD_ACCT_CODE = ? and PROD_UNDER_ACCT_CODE = ? and PROD_CODE = ? and SYS_CODE = ? and SYS_SEG_CODE = ? ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, prodAcctCode);
			stmt.setLong(2, prodUnderAcctCode);
			stmt.setString(3, prodCode);
			stmt.setString(4, sysCode);
			stmt.setInt(5, sysSegCode);

			log(sql.toString(),prodAcctCode, prodUnderAcctCode, prodCode , sysCode, sysSegCode);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeStatement(stmt);
			closeConnection(connection);
		}

	}

	private TplTermAdhesionEntityVO getTermAdhesion(ResultSet rs, TableTypeEnum tableType) throws SQLException {
		TplTermAdhesionEntityVO result = new TplTermAdhesionEntityVO();

		result.setProdAcctCode(rs.getLong("PROD_ACCT_CODE"));
		result.setProdUnderAcctCode(rs.getLong("PROD_UNDER_ACCT_CODE"));
		result.setProdCode(rs.getString("PROD_CODE"));
		result.setTrsrySysInd(rs.getString("TRSRY_SYS_IND"));
		result.setDpSysInd(rs.getString("DP_SYS_IND"));
		result.setAdhTermTypeCode(rs.getString("ADH_TERM_TYPE_CODE"));
		result.setLastApprvDate(rs.getTimestamp("LAST_APPRV_DATE"));
		result.setLastApprvUserId(rs.getString("LAST_APPRV_USER_ID"));
		result.setLastUpdDate(rs.getTimestamp("LAST_UPD_DATE"));
		result.setLastUpdUserId(rs.getString("LAST_UPD_USER_ID"));
		if (!TableTypeEnum.EFFECTIVE.equals(tableType)) {
			result.setOpernTypeCode(rs.getString("OPERN_TYPE_CODE"));
		}

		return result;
	}

	public List<TplProductEntityVO> getProductList() {
		List<TplProductEntityVO> result = new ArrayList<TplProductEntityVO>();

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select PROD_CODE, SYS_CODE, SYS_SEG_CODE, PROD_NAME ");
			sql.append(" from PL.TPL_PRODUCT ");
			sql.append(" where ");
			sql.append("	PROD_CODE IN ('LCA','LCI') and ");
			sql.append("	SYS_CODE = 'OD' and ");
			sql.append("	SYS_SEG_CODE = 2 ");

			stmt = connection.prepareStatement(sql.toString());
			log(sql.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				TplProductEntityVO vo = new TplProductEntityVO();
				vo.setProdCode(rs.getString("PROD_CODE"));
				vo.setSysCode(rs.getString("SYS_CODE"));
				vo.setSysSegCode(BigInteger.valueOf(rs.getInt("SYS_SEG_CODE")));
				vo.setProdName(rs.getString("PROD_NAME"));

				result.add(vo);
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public TplCustomerPrvtEntityVO getCustomer(Long custNbr) {
		TplCustomerPrvtEntityVO result = null;

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select CUST_NBR, CUST_CPF_CNPJ_NBR, CUST_TYPE_CODE, CUST_FULL_NAME_TEXT ");
			sql.append(" from PL.TPL_CUSTOMER_PRVT where CUST_NBR = ? ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, custNbr);

			log(sql.toString(), custNbr);
			
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = new TplCustomerPrvtEntityVO();
				result.setCustNbr(BigInteger.valueOf(rs.getLong("CUST_NBR")));
				result.setCustCpfCnpjNbr(BigInteger.valueOf(rs.getLong("CUST_CPF_CNPJ_NBR")));
				result.setCustTypeCode(rs.getString("CUST_TYPE_CODE"));
				result.setCustFullNameText(rs.getString("CUST_FULL_NAME_TEXT"));
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public TbgOfficerEntityVO getOfficer(Long reltnNbr) {
		TbgOfficerEntityVO result = null;

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select O.OFFCR_NBR, O.OFFCR_NAME_TEXT ");
			sql.append(" from ");
			sql.append("	BG.TBG_OFFICER O ");
			sql.append(" inner join ");
			sql.append("	PL.TPL_PORTFOLIO_PRVT P ");
			sql.append(" on ");
			sql.append("	O.OFFCR_NBR = P.PORTF_OFFCR_NBR ");
			sql.append(" inner join ");
			sql.append("	PL.TPL_RELATION_PRVT R ");
			sql.append(" on ");
			sql.append("	R.RELTN_PORTF_CODE = P.PORTF_CODE ");
			sql.append(" where ");
			sql.append("	R.RELTN_NBR = ? ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, reltnNbr);
			log(sql.toString(), reltnNbr);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = new TbgOfficerEntityVO();
				result.setOffcrNbr(BigInteger.valueOf(rs.getInt("OFFCR_NBR")));
				result.setOffcrNameText(rs.getString("OFFCR_NAME_TEXT"));
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public Integer getPortfBrchCode(Long reltnNbr) {
		Integer result = null;

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select P.PORTF_BRCH_CODE ");
			sql.append(" from ");
			sql.append("	BG.TBG_OFFICER O ");
			sql.append(" inner join ");
			sql.append("	PL.TPL_PORTFOLIO_PRVT P ");
			sql.append(" on ");
			sql.append("	O.OFFCR_NBR = P.PORTF_OFFCR_NBR ");
			sql.append(" inner join ");
			sql.append("	PL.TPL_RELATION_PRVT R ");
			sql.append(" on ");
			sql.append("	R.RELTN_PORTF_CODE = P.PORTF_CODE ");
			sql.append(" where ");
			sql.append("	R.RELTN_NBR = ? ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, reltnNbr);
			log(sql.toString(), reltnNbr);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("PORTF_BRCH_CODE");
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public String getAddrStateCode(Long custNbr) {
		String result = null;

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select ADDR_STATE_CODE ");
			sql.append(" from ");
			sql.append("	BG.TBG_CUST_ADDRESS ");
			sql.append(" where ");
			sql.append("	CUST_NBR = ? ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, custNbr);
			log(sql.toString(), custNbr);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getString("ADDR_STATE_CODE");
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public BaseTo3ProductAccountEntity getProductAccountByCurAcctNbr(Long curAcctNbr) {
		BaseTo3ProductAccountEntity result = null;

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select PA.PROD_ACCT_CODE, PA.PROD_UNDER_ACCT_CODE, PA.CUST_NBR, PA.RELTN_NBR, PA.CUR_ACCT_NBR, PA.PROD_CODE, PA.SYS_CODE, PA.SYS_SEG_CODE, RP.RELTN_SEG_CODE ");
			sql.append(" from ");
			sql.append("	O3.TO3_PRODUCT_ACCOUNT PA");
			sql.append(" LEFT JOIN pl.tpl_relation_prvt RP ON PA.RELTN_NBR = RP.RELTN_NBR ");
			sql.append(" where ");
			sql.append("	PA.CUR_ACCT_NBR = ? and ");
			sql.append("	PA.PROD_CODE = '010' and ");
			sql.append("	PA.SYS_CODE = 'DA' and ");
			sql.append("	PA.SYS_SEG_CODE = 1 ");

			log(sql.toString(), curAcctNbr);
			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, curAcctNbr);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = getProductAccount(rs);
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	public BaseTo3ProductAccountEntity getProductAccountByProdAcctCodeAndProdUnderAcctCode(Long prodAcctCode, Long prodUnderAcctCode) {
		BaseTo3ProductAccountEntity result = null;

		ManagedRdbConnection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = OracleODSDAOFactory.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select PA.PROD_ACCT_CODE, PA.PROD_UNDER_ACCT_CODE, PA.CUST_NBR, PA.RELTN_NBR, PA.CUR_ACCT_NBR, PA.PROD_CODE, PA.SYS_CODE, PA.SYS_SEG_CODE, RP.RELTN_SEG_CODE ");
			sql.append(" from ");
			sql.append("	O3.TO3_PRODUCT_ACCOUNT PA");
			sql.append("	LEFT JOIN pl.tpl_relation_prvt RP ON PA.RELTN_NBR = RP.RELTN_NBR");
			sql.append(" where ");
			sql.append("	PA.PROD_ACCT_CODE = ? and ");
			sql.append("	PA.PROD_UNDER_ACCT_CODE = ? and ");
			sql.append("	PA.PROD_CODE = '010' and ");
			sql.append("	PA.SYS_CODE = 'DA' and ");
			sql.append("	PA.SYS_SEG_CODE = 1 ");

			stmt = connection.prepareStatement(sql.toString());
			stmt.setLong(1, prodAcctCode);
			stmt.setLong(2, prodUnderAcctCode);

			
			log(sql.toString(), prodAcctCode, prodUnderAcctCode);
			rs = stmt.executeQuery();

			if (rs.next()) {
				result = getProductAccount(rs);
			}

		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(connection);
		}

		return result;
	}

	private BaseTo3ProductAccountEntity getProductAccount(ResultSet rs) throws SQLException {
		BaseTo3ProductAccountEntity result = new To3ProductAccountEntity();

		To3ProductAccountEntityVO data = (To3ProductAccountEntityVO) result.getData();
		data.setProdAcctCode(new BigInteger(rs.getString("PROD_ACCT_CODE")));
		data.setProdUnderAcctCode(new BigInteger(rs.getString("PROD_UNDER_ACCT_CODE")));
		data.setCustNbr(new BigInteger(rs.getString("CUST_NBR")));
		data.setReltnNbr(new BigInteger(rs.getString("RELTN_NBR")));
		data.setCurAcctNbr(new BigInteger(rs.getString("CUR_ACCT_NBR")));
		data.setProdCode(rs.getString("PROD_CODE"));
		data.setSysCode(rs.getString("SYS_CODE"));
		data.setSysSegCode(new BigInteger(rs.getString("SYS_SEG_CODE")));
		data.setSegCode(rs.getString("RELTN_SEG_CODE"));
		return result;
	}
	public static void log(String str, Object... var){
		if (str==null)
		return;
		
		try {
			str = str.replaceAll("?", "%s");
			
			ApplicationLogger.getInstance().info(String.format(str, var));
		}catch(Exception e ){
			ApplicationLogger.getInstance().info(str);
		}
	}
}
