package com.citibank.newcpb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;

public class CustomerCountryVO implements Serializable{	
	

	private static final long serialVersionUID = 1L;
	
	private Long custGfcidNbr; // Numero do GFCID - origem AMC
	private String registerType; // Indicador do tipo de cadastro. R (Residencia Fiscal) / C (Cidadania)
	private Integer position; // Sequencial do pais
	private String country; // Codigo do pais
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	
	public Long getCustGfcidNbr() {
		return custGfcidNbr;
	}
	public void setCustGfcidNbr(Long custGfcidNbr) {
		this.custGfcidNbr = custGfcidNbr;
	}
	public String getRegisterType() {
		return registerType;
	}
	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}
	public Integer getPositionIndex() {
		if(position!=null && position!=0){
			return position-1;
		}else{
			return 0;
		}		
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getRecStatCode() {
		return recStatCode;
	}
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}
	
	public ArrayList<String> equals(CustomerCountryVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.custGfcidNbr != null && other.custGfcidNbr != null) {
				if (!this.custGfcidNbr.equals(other.custGfcidNbr)) {
					idDiffList.add("country");
				}
			} else if ((this.custGfcidNbr == null && other.custGfcidNbr != null) || (other.custGfcidNbr == null && this.custGfcidNbr != null)) {
				idDiffList.add("country");
			}
			
			if (this.position != null && other.position != null) {
				if (!this.position.equals(other.position)) {
					idDiffList.add("country");
				}
			} else if ((this.position == null && other.position != null) || (other.position == null && this.position != null)) {
				idDiffList.add("country");
			}
			
			if (this.registerType != null && other.registerType != null) {
				if (!this.registerType.equals(other.registerType)) {
					idDiffList.add("country");
				}
			} else if ((this.registerType == null && other.registerType != null) || (other.registerType == null && this.registerType != null)) {
				idDiffList.add("country");
			}
			
			if (this.country != null && other.country != null) {
				if (!this.country.equals(other.country)) {
					idDiffList.add("country");
				}
			} else if ((this.country == null && other.country != null) || (other.country == null && this.country != null)) {
				idDiffList.add("country");
			}			
		}
		return idDiffList;
	}
	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}
	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}
}
