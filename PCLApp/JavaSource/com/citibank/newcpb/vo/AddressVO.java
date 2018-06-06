package com.citibank.newcpb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.newcpb.enums.TableTypeEnum;

public class AddressVO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private Long custGfcidNbr; // Numero do GFCID - origem AMC
	private Integer addrSeqNbr; // Sequencial do endereço
	private String addrTypeCode; // Indicador do tipo de endereco. R(Residencial) / C (Comercial)/ O (Outros]0
	private String street; // Endereço 
	private String neighborhood; // Bairro
	private String city; // Cidade
	private String uf; // Codigo do Estado
	private String addrCntryCode; // Codigo do Pais
	private String zipCode; // CEP
	private Boolean isCorrespondence; // indicador de Endereco de correspondencia S/N
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

	public Integer getAddrSeqNbr() {
		return addrSeqNbr;
	}

	public void setAddrSeqNbr(Integer addrSeqNbr) {
		this.addrSeqNbr = addrSeqNbr;
	}

	public String getAddrTypeCode() {
		return addrTypeCode;
	}

	public void setAddrTypeCode(String addrTypeCode) {
		this.addrTypeCode = addrTypeCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getAddrCntryCode() {
		return addrCntryCode;
	}

	public void setAddrCntryCode(String addrCntryCode) {
		this.addrCntryCode = addrCntryCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	

	public Boolean getIsCorrespondence() {
		return isCorrespondence;
	}

	public void setIsCorrespondence(Boolean isCorrespondence) {
		this.isCorrespondence = isCorrespondence;
	}

	public ArrayList<String> equals(AddressVO other,  String type) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
						
			
			if (this.street != null && other.street != null) {
				if (!this.street.equals(other.street)) {
					idDiffList.add(type+"street");
				}
			} else if ((this.street == null && other.street != null) || (other.street == null && this.street != null)) {
				idDiffList.add(type+"street");
			}
			
			if (this.neighborhood != null && other.neighborhood != null) {
				if (!this.neighborhood.equals(other.neighborhood)) {
					idDiffList.add(type+"neighborhood");
				}
			} else if ((this.neighborhood == null && other.neighborhood != null) || (other.neighborhood == null && this.neighborhood != null)) {
				idDiffList.add(type+"neighborhood");
			}
			
			if (this.city != null && other.city != null) {
				if (!this.city.equals(other.city)) {
					idDiffList.add(type+"city");
				}
			} else if ((this.city == null && other.city != null) || (other.city == null && this.city != null)) {
				idDiffList.add(type+"city");
			}
			
			if (this.uf != null && other.uf != null) {
				if (!this.uf.equals(other.uf)) {
					idDiffList.add(type+"uf");
				}
			} else if ((this.uf == null && other.uf != null) || (other.uf == null && this.uf != null)) {
				idDiffList.add(type+"uf");
			}
			
			
			if (this.zipCode != null && other.zipCode != null) {
				if (!this.zipCode.equals(other.zipCode)) {
					idDiffList.add(type+"zipCode");
				}
			} else if ((this.zipCode == null && other.zipCode != null) || (other.zipCode == null && this.zipCode != null)) {
				idDiffList.add(type+"zipCode");
			}
			
			if (this.isCorrespondence != null && other.isCorrespondence != null) {
				if (!this.isCorrespondence.equals(other.isCorrespondence)) {
					idDiffList.add(type+"isCorrespondenceDiv");
				}
			} else if ((this.isCorrespondence == null && other.isCorrespondence != null) || (other.isCorrespondence == null && this.isCorrespondence != null)) {
				idDiffList.add(type+"isCorrespondenceDiv");
			}
			
		}else{
			idDiffList.add(type+"street");
			idDiffList.add(type+"neighborhood");
			idDiffList.add(type+"city");
			idDiffList.add(type+"uf");
			idDiffList.add(type+"zipCode");
			idDiffList.add(type+"isCorrespondenceDiv");
		}
		return idDiffList;
	}
}
