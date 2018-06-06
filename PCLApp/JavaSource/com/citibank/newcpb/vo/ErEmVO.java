package com.citibank.newcpb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;

public class ErEmVO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private String erNbr; // Numero do ER
	private String emNbr; // Numero do ER AMC
	private String roleCustCode; // Codigo do Papel do Relaciomento
	private String roleCustDesc; // Codigo do Papel do Relaciomento
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	
	public String getErNbr() {
		return erNbr;
	}

	public void setErNbr(String erNbr) {
		this.erNbr = erNbr;
	}

	public String getEmNbr() {
		return emNbr;
	}

	public void setEmNbr(String emNbr) {
		this.emNbr = emNbr;
	}

	public String getRoleCustCode() {
		return roleCustCode;
	}

	public void setRoleCustCode(String roleCustCode) {
		this.roleCustCode = roleCustCode;
	}

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

	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}

	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}

	public String getRecStatCode() {
		return recStatCode;
	}

	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
	


	public ArrayList<String> equals(ErEmVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.erNbr != null && other.erNbr != null) {
				if (!this.erNbr.equals(other.erNbr)) {
					idDiffList.add("er_em.erNbr");
				}
			} else if ((this.erNbr == null && other.erNbr != null) || (other.erNbr == null && this.erNbr != null)) {
				idDiffList.add("er_em.erNbr");
			}
			
			if (this.emNbr != null && other.emNbr != null) {
				if (!this.emNbr.equals(other.emNbr)) {
					idDiffList.add("er_em.emNbr");
				}
			} else if ((this.emNbr == null && other.emNbr != null) || (other.emNbr == null && this.emNbr != null)) {
				idDiffList.add("er_em.emNbr");
			}
			
			if (this.roleCustCode != null && other.roleCustCode != null) {
				if (!this.roleCustCode.equals(other.roleCustCode)) {
					idDiffList.add("er_em.roleCustCode");
				}
			} else if ((this.roleCustCode == null && other.roleCustCode != null) || (other.roleCustCode == null && this.roleCustCode != null)) {
				idDiffList.add("er_em.roleCustCode");
			}

		}
		return idDiffList;
	}

	public String getRoleCustDesc() {
		return roleCustDesc;
	}

	public void setRoleCustDesc(String roleCustDesc) {
		this.roleCustDesc = roleCustDesc;
	}
}
