package com.citibank.newcpb.vo;

import java.io.InputStream;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class KeCustFileVO {	
	
	private Integer fileSeq;
	private String acctNbr;
	private String cpfCnpjNbr;
	private String fileName;
	private byte[] file;
	private InputStream fileI;
	private Date createDate;
	private String createUser;
	private String recStatCode;
	private String createDateString;
	private TableTypeEnum tableOrigin;
	
	public Integer getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(Integer fileSeq) {
		this.fileSeq = fileSeq;
	}
	public String getAcctNbr() {
		return acctNbr;
	}
	public void setAcctNbr(String acctNbr) {
		this.acctNbr = acctNbr;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getRecStatCode() {
		return recStatCode;
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}
	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}
	public String getCreateDateFormated() {
		if(createDate!=null){
			return FormatUtils.dateToString(createDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
		}else{
			return "";
		}
	}
	/**
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}
	/**
	 * @return the createDateString
	 */
	public String getCreateDateString() {
		return createDateString;
	}
	/**
	 * @param createDateString the createDateString to set
	 */
	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}
	/**
	 * @return the fileI
	 */
	public InputStream getFileI() {
		return fileI;
	}
	/**
	 * @param fileI the fileI to set
	 */
	public void setFileI(InputStream fileI) {
		this.fileI = fileI;
	}
	/**
	 * @return the cpfCnpjNbr
	 */
	public String getCpfCnpjNbr() {
		return cpfCnpjNbr;
	}
	/**
	 * @param cpfCnpjNbr the cpfCnpjNbr to set
	 */
	public void setCpfCnpjNbr(String cpfCnpjNbr) {
		this.cpfCnpjNbr = cpfCnpjNbr;
	}

}
