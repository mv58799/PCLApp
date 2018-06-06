package com.citibank.newcpb.vo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class AuthorizationPersonAccountVO {	

	private String acctNbr; // Numero Conta
	private String emNbr; // Número do EM do terceiro
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	private String effectiveDate; // Data de Vigencia
//	private Boolean agentFlag; // Indicador de procurados S/N
//	private Boolean authPersnFlag; // Indicador de pessoa autorizada S/N
	private String authInd;
	private String agentAuthPersonDesc;
	private String authComment;   
	
	
	private ArrayList<AcctCmplVO> customerAcctList; // Lista de contas do Clientes associadas a essa Conta.
	
	public Date getLastAuthDate() {
		return lastAuthDate;
	}

	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}

	public String getLastAuthUser() {
		return lastAuthUser;
	}

	public void setLastAuthUser(String lastAuthUser) {
		this.lastAuthUser = lastAuthUser;
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public String getLastUpdUser() {
		return lastUpdUser;
	}

	public void setLastUpdUser(String lastUpdUser) {
		this.lastUpdUser = lastUpdUser;
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

	public String getAcctNbr() {
		return acctNbr;
	}

	public void setAcctNbr(String acctNbr) {
		this.acctNbr = acctNbr;
	}
	
	public String getLastUpdUserSafe() {
		if(lastUpdUser!=null){
			return lastUpdUser;
		}else{
			return "";
		}
	}
	
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			if(recStatCode.equals("U")){
				return "Alteração";
			}else if(recStatCode.equals("A")){
				return "Inclusão";
			}else if(recStatCode.equals("I")){
				return "Exclusão";
			}else{
				return "";
			}			
		}else{
			return "";
		}
	}
	
	public String getLastUpdDateFormatedSafe() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS);
		}else{
			return "";
		}
	}

	public String getEmNbr() {
		return emNbr;
	}

	public void setEmNbr(String emNbr) {
		this.emNbr = emNbr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acctNbr == null) ? 0 : acctNbr.hashCode());
		result = prime * result + ((customerAcctList == null) ? 0 : customerAcctList.hashCode());
		result = prime * result + ((emNbr == null) ? 0 : emNbr.hashCode());
		result = prime * result + ((lastAuthDate == null) ? 0 : lastAuthDate.hashCode());
		result = prime * result + ((lastAuthUser == null) ? 0 : lastAuthUser.hashCode());
		result = prime * result + ((lastUpdDate == null) ? 0 : lastUpdDate.hashCode());
		result = prime * result + ((lastUpdUser == null) ? 0 : lastUpdUser.hashCode());
		result = prime * result + ((recStatCode == null) ? 0 : recStatCode.hashCode());
		result = prime * result + ((tableOrigin == null) ? 0 : tableOrigin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorizationPersonAccountVO other = (AuthorizationPersonAccountVO) obj;
		if (acctNbr == null) {
			if (other.acctNbr != null)
				return false;
		} else if (!acctNbr.equals(other.acctNbr))
			return false;
		if (customerAcctList == null) {
			if (other.customerAcctList != null)
				return false;
		} else if (!customerAcctList.equals(other.customerAcctList))
			return false;
		if (emNbr == null) {
			if (other.emNbr != null)
				return false;
		} else if (!emNbr.equals(other.emNbr))
			return false;
		if (lastAuthDate == null) {
			if (other.lastAuthDate != null)
				return false;
		} else if (!lastAuthDate.equals(other.lastAuthDate))
			return false;
		if (lastAuthUser == null) {
			if (other.lastAuthUser != null)
				return false;
		} else if (!lastAuthUser.equals(other.lastAuthUser))
			return false;
		if (lastUpdDate == null) {
			if (other.lastUpdDate != null)
				return false;
		} else if (!lastUpdDate.equals(other.lastUpdDate))
			return false;
		if (lastUpdUser == null) {
			if (other.lastUpdUser != null)
				return false;
		} else if (!lastUpdUser.equals(other.lastUpdUser))
			return false;
		if (recStatCode == null) {
			if (other.recStatCode != null)
				return false;
		} else if (!recStatCode.equals(other.recStatCode))
			return false;
		if (tableOrigin != other.tableOrigin)
			return false;
		return true;
	}
	
	public String getCustomerName() {
		String text = "";
		if(customerAcctList!=null){
			for (AcctCmplVO vo : customerAcctList) {
				if(!StringUtils.isBlank(text)){
					text += "<p>";
				}
				text += vo.getCustomerName();
			}
		}
		return text;
	}
	
	public String getCustomerEm() {
		String text = "";
		if(customerAcctList!=null){
			for (AcctCmplVO vo : customerAcctList) {
				if(!StringUtils.isBlank(text)){
					text += "<p>";
				}
				text += vo.getEmNbr();
			}
		}
		return text;
	}

	public ArrayList<AcctCmplVO> getCustomerAcctList() {
		return customerAcctList;
	}

	public void setCustomerAcctList(ArrayList<AcctCmplVO> customerAcctList) {
		this.customerAcctList = customerAcctList;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	/**
	 * @return the agentAuthPersonDesc
	 */
	public String getAgentAuthPersonDesc() {
		
		if(getAuthInd()!=null && getAuthInd().trim().equals("P")){
			agentAuthPersonDesc = "Procurador";			
		}else if(getAuthInd()!=null && getAuthInd().trim().equals("A")){
			agentAuthPersonDesc = "Pessoa Autorizada";
		}else{
			agentAuthPersonDesc = "Nenhum";
		}
		return agentAuthPersonDesc;
	}

	/**
	 * @param agentAuthPersonDesc the agentAuthPersonDesc to set
	 */
	public void setAgentAuthPersonDesc(String agentAuthPersonDesc) {
		this.agentAuthPersonDesc = agentAuthPersonDesc;
	}

	/**
	 * @return the authComment
	 */
	public String getAuthComment() {
		return authComment;
	}

	/**
	 * @param authComment the authComment to set
	 */
	public void setAuthComment(String authComment) {
		this.authComment = authComment;
	}

	/**
	 * @return the authInd
	 */
	public String getAuthInd() {
		if(StringUtils.isBlank(authInd)){
			authInd ="X";
		}
		return authInd;
	}

	/**
	 * @param authInd the authInd to set
	 */
	public void setAuthInd(String authInd) {
		this.authInd = authInd;
	}
}
