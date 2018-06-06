package com.citibank.newcpb.vo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class DocumentsVO {
	
	private String emNbr; // Número do EM
	private String cpfCnpjNbr; // Numero do CPF CNPJ 
	private String name; //nome
	private String numberEM; // Numero do EM
	private String type; // Tipo do documento
	private String documentsTitulo; // Titulo
	private String documentsDate; // Data
	private String hasDoc;
	private String cpfUpdMthYr; // Mês e Ano da ultima atualizacao do Status do CPF
	private Date lastAuthDate; // Data e hora da última autorizacao	
	private String lastAuthUserId; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUserId; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	
	private RegisterDataCustomerVO customer;
	
	public String getRecStatCodeText() {
		if(recStatCode!=null){
			if(recStatCode.equals("A")){
				return "Alteração";
			}else{
				return "";
			}			
		}else{
			return "";
		}
	}
	
	public ArrayList<String> equals(DocumentsVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.cpfCnpjNbr != null && other.cpfCnpjNbr != null) {
				if (!this.cpfCnpjNbr.equals(other.cpfCnpjNbr)) {
					idDiffList.add("cpfCnpjNbr");
				}
			} else if ((this.cpfCnpjNbr == null && other.cpfCnpjNbr != null) || (other.cpfCnpjNbr == null && this.cpfCnpjNbr != null)) {
				idDiffList.add("cpfCnpjNbr");
			}
			
			if (this.documentsTitulo != null && other.documentsTitulo != null) {
				if (!this.documentsTitulo.equals(other.documentsTitulo)) {
					idDiffList.add("cpfStatus");
				}
			} else if ((this.documentsTitulo == null && other.documentsTitulo != null) || (other.documentsTitulo == null && this.documentsTitulo != null)) {
				idDiffList.add("cpfStatus");
			}
			
			if (this.cpfUpdMthYr != null && other.cpfUpdMthYr != null) {
				if (!this.cpfUpdMthYr.equals(other.cpfUpdMthYr)) {
					idDiffList.add("cpfUpdMthYr");
				}
			} else if ((this.cpfUpdMthYr == null && other.cpfUpdMthYr != null) || (other.cpfUpdMthYr == null && this.cpfUpdMthYr != null)) {
				idDiffList.add("cpfUpdMthYr");
			}
			
		}else{
			idDiffList.add("cpfCnpjNbr");
			idDiffList.add("cpfStatus");
			idDiffList.add("cpfUpdMthYr");
		}
		
		return idDiffList;
	}

	public String getCpfCnpjNbr() {
		return cpfCnpjNbr;
	}
	
	public String getCpfCnpjNbrFormated() {
		try {
			return FormatUtils.formatterDoc(cpfCnpjNbr);
		} catch (ParseException e) {
			return "";
		}
	}

	public void setCpfCnpjNbr(String cpfCnpjNbr) {
		this.cpfCnpjNbr = cpfCnpjNbr;
	}

	public Date getLastAuthDate() {
		return lastAuthDate;
	}

	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}
	
	public String getEmNbr() {
		return emNbr;
	}

	public void setEmNbr(String emNbr) {
		this.emNbr = emNbr;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHasDoc() {
		return hasDoc;
	}

	public void setHasDoc(String hasDoc) {
		this.hasDoc = hasDoc;
	}
	
	
	public String getNumberEM() {
		return numberEM;
	}

	public void setNumberEM(String numberEM) {
		this.numberEM = numberEM;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getDocumentsDate() {
		return documentsDate;
	}

	public void setDocumentsDate(String documentsDate) {
		this.documentsDate = documentsDate;
	}	
	
	
	public String getLastUpdDateFormatedSafe() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY_HHMMSS);
		}else{
			return "";
		}
	}
	
	public String getLastUpdDateFormatedDDMMYYYY() {
		if(lastUpdDate!=null){
			return FormatUtils.dateToString(lastUpdDate, FormatUtils.C_FORMAT_DATE_DD_MM_YYYY);
		}else{
			return "";
		}
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
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

	public String getDocumentsTitulo() {
		return documentsTitulo;
	}
	
	public String getDocumentsTituloText() {
		if(!StringUtils.isBlank(documentsTitulo)){
			if(documentsTitulo.equalsIgnoreCase("D")){
				return "Doc";
			}else if(documentsTitulo.equalsIgnoreCase("M")){
				return "Man";
			}
		    }else if(documentsTitulo.equalsIgnoreCase("R")){
			   return "Rev";
		    }
		
		return documentsTitulo;
	}

	public void setDocumentsTitulo(String documentsTitulo) {
		this.documentsTitulo = documentsTitulo;
	}

	public String getCpfUpdMthYrText() {
		if(!StringUtils.isBlank(cpfUpdMthYr) && cpfUpdMthYr.length()>2){
			StringBuilder cpfUpdMthYrFormated = new StringBuilder(cpfUpdMthYr);
			cpfUpdMthYrFormated.insert(2, '/');
			return cpfUpdMthYrFormated.toString();
		}else{
			return "";
		}
	}
	
	public String getCpfUpdMthYr() {
		return cpfUpdMthYr;
	}

	public void setCpfUpdMthYr(String cpfUpdMthYr) {
		this.cpfUpdMthYr = cpfUpdMthYr;
	}

	public String getLastAuthUserId() {
		return lastAuthUserId;
	}

	public void setLastAuthUserId(String lastAuthUserId) {
		this.lastAuthUserId = lastAuthUserId;
	}
	
	public String getLastUpdUserIdSafe() {
		if(lastUpdUserId!=null){
			return lastUpdUserId;
		}else{
			return "";
		}
	}

	public String getLastUpdUserId() {
		return lastUpdUserId;
	}

	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}

	public RegisterDataCustomerVO getCustomer() {
		return customer;
	}

	public void setCustomer(RegisterDataCustomerVO customer) {
		this.customer = customer;
	}
}
