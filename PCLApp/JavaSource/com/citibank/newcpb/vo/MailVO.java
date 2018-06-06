package com.citibank.newcpb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;

public class MailVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long custGfcidNbr; // Numero do GFCID - origem AMC
	private Integer position; // Sequencial do email
	private String mail; // Email do cliente
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	
	public ArrayList<String> equals(MailVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.custGfcidNbr != null && other.custGfcidNbr != null) {
				if (!this.custGfcidNbr.equals(other.custGfcidNbr)) {
					idDiffList.add("mail");
				}
			} else if ((this.custGfcidNbr == null && other.custGfcidNbr != null) || (other.custGfcidNbr == null && this.custGfcidNbr != null)) {
				idDiffList.add("mail");
			}
			
			if (this.position != null && other.position != null) {
				if (!this.position.equals(other.position)) {
					idDiffList.add("mail");
				}
			} else if ((this.position == null && other.position != null) || (other.position == null && this.position != null)) {
				idDiffList.add("mail");
			}
			
			if (this.mail != null && other.mail != null) {
				if (!this.mail.equals(other.mail)) {
					idDiffList.add("mail");
				}
			} else if ((this.mail == null && other.mail != null) || (other.mail == null && this.mail != null)) {
				idDiffList.add("mail");
			}
		}
		return idDiffList;
	}
	
}
