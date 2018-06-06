package com.citibank.newcpb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;

public class TelephoneVO implements Serializable{	
	

	private static final long serialVersionUID = 1L;
	
	private Long custGfcidNbr; // Numero do GFCID - origem AMC
	private Integer position; // Sequencial do celular
	public String ddd; // Codigo da area do celular (DDD) ou Codigo internacional+codigo da area
	public String number; // Numero do celular 
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

	public Integer getPosition() {
		return position;
	}
	
	public Integer getPositionIndex() {
		if(position!=null && position!=0){
			return position-1;
		}else{
			return 0;
		}		
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
	
	public ArrayList<String> equals(TelephoneVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.custGfcidNbr != null && other.custGfcidNbr != null) {
				if (!this.custGfcidNbr.equals(other.custGfcidNbr)) {
					idDiffList.add("telephoneList["+position+"].ddd");
					idDiffList.add("telephoneList["+position+"].number");
				}
			} else if ((this.custGfcidNbr == null && other.custGfcidNbr != null) || (other.custGfcidNbr == null && this.custGfcidNbr != null)) {
				idDiffList.add("telephoneList["+position+"].ddd");
				idDiffList.add("telephoneList["+position+"].number");
			}
			
			if (this.position != null && other.position != null) {
				if (!this.position.equals(other.position)) {
					idDiffList.add("telephoneList["+position+"].ddd");
					idDiffList.add("telephoneList["+position+"].number");
				}
			} else if ((this.position == null && other.position != null) || (other.position == null && this.position != null)) {
				idDiffList.add("telephoneList["+position+"].ddd");
				idDiffList.add("telephoneList["+position+"].number");
			}
			
			if (this.ddd != null && other.ddd != null) {
				if (!this.ddd.equals(other.ddd)) {
					idDiffList.add("telephoneList["+position+"].ddd");
					idDiffList.add("telephoneList["+position+"].number");
				}
			} else if ((this.ddd == null && other.ddd != null) || (other.ddd == null && this.ddd != null)) {
				idDiffList.add("telephoneList["+position+"].ddd");
				idDiffList.add("telephoneList["+position+"].number");
			}
			
			if (this.number != null && other.number != null) {
				if (!this.number.equals(other.number)) {
					idDiffList.add("telephoneList["+position+"].ddd");
					idDiffList.add("telephoneList["+position+"].number");
				}
			} else if ((this.number == null && other.number != null) || (other.number == null && this.number != null)) {
				idDiffList.add("telephoneList["+position+"].ddd");
				idDiffList.add("telephoneList["+position+"].number");
			}			
		}
		return idDiffList;
	}
}
