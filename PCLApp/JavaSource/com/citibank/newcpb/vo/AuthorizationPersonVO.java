package com.citibank.newcpb.vo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.TableTypeEnum;
import com.citibank.newcpb.util.FormatUtils;

public class AuthorizationPersonVO {
	
	private String emNbr; // Número do EM da pessoa autorizada
	private String authInd;
	private String authPersnName; // Nome da pessoa autorizada
	private String birthDate; // Data de nascimento
	private String comments; // Comentarios adicionais
	private String cpfCnpjNbr; // CPF / CNPJ
	private String docId; // Documento de identidade
	private String profText; // Profissão do terceiro
	private String street; // Endereço 
	private String neighborhood; // Bairro
	private String city; // Cidade
	private String uf; // Codigo do Estado
	private String addrCntryCode; // Codigo do Pais
	private String zipCode; // CEP
	private String effectiveDate; // Data de Vigencia
	private Date lastAuthDate; // Data e hora da última autorizacao
	private String lastAuthUser; // Usuario que realizou a ultima autorizacao
	private Date lastUpdDate; // Data e hora da última atualização
	private String lastUpdUser; // Usuario que realizou a ultima atualizacao
	private String recStatCode; // Status do Registro
	private TableTypeEnum tableOrigin; // Tipo da Tabela de Origem (CAD/MOV/HIST)
	
	private ArrayList<AuthorizationPersonAccountVO> accountList;
	private ArrayList<AuthorizationPersonAccountVO> accountListRemoved;	
	
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
	
	public ArrayList<String> equals(AuthorizationPersonVO other) {

		ArrayList<String> idDiffList = new ArrayList<String>();
		
		if (other != null){
			
			if (this.emNbr != null && other.emNbr != null) {
				if (!this.emNbr.equals(other.emNbr)) {
					idDiffList.add("emNbr");
				}
			} else if ((this.emNbr == null && other.emNbr != null) || (other.emNbr == null && this.emNbr != null)) {
				idDiffList.add("emNbr");
			}
			
			if (this.authInd != null && other.authInd != null) {
				if (!this.authInd.equals(other.authInd)) {
					idDiffList.add("authIndDiv");
				}
			} else if ((this.authInd == null && other.authInd != null) || (other.authInd == null && this.authInd != null)) {
				idDiffList.add("authIndDiv");
			}
						
			if (this.authPersnName != null && other.authPersnName != null) {
				if (!this.authPersnName.equals(other.authPersnName)) {
					idDiffList.add("authPersnName");
				}
			} else if ((this.authPersnName == null && other.authPersnName != null) || (other.authPersnName == null && this.authPersnName != null)) {
				idDiffList.add("authPersnName");
			}
			
			if (this.birthDate != null && other.birthDate != null) {
				if (!this.birthDate.equals(other.birthDate)) {
					idDiffList.add("birthDate");
				}
			} else if ((this.birthDate == null && other.birthDate != null) || (other.birthDate == null && this.birthDate != null)) {
				idDiffList.add("birthDate");
			}
			
			if (this.comments != null && other.comments != null) {
				if (!this.comments.equals(other.comments)) {
					idDiffList.add("comments");
				}
			} else if ((this.comments == null && other.comments != null) || (other.comments == null && this.comments != null)) {
				idDiffList.add("comments");
			}
			
			if (this.cpfCnpjNbr != null && other.cpfCnpjNbr != null) {
				if (!this.cpfCnpjNbr.equals(other.cpfCnpjNbr)) {
					idDiffList.add("cpfCnpjNbr");
				}
			} else if ((this.cpfCnpjNbr == null && other.cpfCnpjNbr != null) || (other.cpfCnpjNbr == null && this.cpfCnpjNbr != null)) {
				idDiffList.add("cpfCnpjNbr");
			}
			
			if (this.docId != null && other.docId != null) {
				if (!this.docId.equals(other.docId)) {
					idDiffList.add("docId");
				}
			} else if ((this.docId == null && other.docId != null) || (other.docId == null && this.docId != null)) {
				idDiffList.add("docId");
			}
			
			if (this.profText != null && other.profText != null) {
				if (!this.profText.equals(other.profText)) {
					idDiffList.add("profText");
				}
			} else if ((this.profText == null && other.profText != null) || (other.profText == null && this.profText != null)) {
				idDiffList.add("profText");
			}
			
			if (this.street != null && other.street != null) {
				if (!this.street.equals(other.street)) {
					idDiffList.add("street");
				}
			} else if ((this.street == null && other.street != null) || (other.street == null && this.street != null)) {
				idDiffList.add("street");
			}
			
			if (this.neighborhood != null && other.neighborhood != null) {
				if (!this.neighborhood.equals(other.neighborhood)) {
					idDiffList.add("neighborhood");
				}
			} else if ((this.neighborhood == null && other.neighborhood != null) || (other.neighborhood == null && this.neighborhood != null)) {
				idDiffList.add("neighborhood");
			}
			
			if (this.city != null && other.city != null) {
				if (!this.city.equals(other.city)) {
					idDiffList.add("city");
				}
			} else if ((this.city == null && other.city != null) || (other.city == null && this.city != null)) {
				idDiffList.add("city");
			}
			
			if (this.uf != null && other.uf != null) {
				if (!this.uf.equals(other.uf)) {
					idDiffList.add("uf");
				}
			} else if ((this.uf == null && other.uf != null) || (other.uf == null && this.uf != null)) {
				idDiffList.add("uf");
			}
			
			if (this.addrCntryCode != null && other.addrCntryCode != null) {
				if (!this.addrCntryCode.equals(other.addrCntryCode)) {
					idDiffList.add("addrCntryCode");
				}
			} else if ((this.addrCntryCode == null && other.addrCntryCode != null) || (other.addrCntryCode == null && this.addrCntryCode != null)) {
				idDiffList.add("addrCntryCode");
			}
			
			if (this.zipCode != null && other.zipCode != null) {
				if (!this.zipCode.equals(other.zipCode)) {
					idDiffList.add("zipCode");
				}
			} else if ((this.zipCode == null && other.zipCode != null) || (other.zipCode == null && this.zipCode != null)) {
				idDiffList.add("zipCode");
			}
			
		}else{
			idDiffList.add("emNbr");
			idDiffList.add("agentFlagDiv");
			idDiffList.add("authPersnFlagDiv");
			idDiffList.add("authPersnName");
			idDiffList.add("birthDate");
			idDiffList.add("comments");
			idDiffList.add("cpfCnpjNbr");
			idDiffList.add("docId");
			idDiffList.add("profText");
			idDiffList.add("street");
			idDiffList.add("neighborhood");
			idDiffList.add("city");
			idDiffList.add("uf");
			idDiffList.add("addrCntryCode");
			idDiffList.add("zipCode");
		}
		
		return idDiffList;
	}

	public String getEmNbr() {
		return emNbr;
	}

	public void setEmNbr(String emNbr) {
		this.emNbr = emNbr;
	}

	public String getAuthPersnName() {
		return authPersnName;
	}

	public void setAuthPersnName(String authPersnName) {
		this.authPersnName = authPersnName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCpfCnpjNbr() {
		return cpfCnpjNbr;
	}

	public void setCpfCnpjNbr(String cpfCnpjNbr) {
		this.cpfCnpjNbr = cpfCnpjNbr;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getProfText() {
		return profText;
	}

	public void setProfText(String profText) {
		this.profText = profText;
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

	public TableTypeEnum getTableOrigin() {
		return tableOrigin;
	}

	public void setTableOrigin(TableTypeEnum tableOrigin) {
		this.tableOrigin = tableOrigin;
	}

	public ArrayList<AuthorizationPersonAccountVO> getAccountList() {
		if(accountList==null){
			accountList = new ArrayList<AuthorizationPersonAccountVO>();
		}
		return accountList;
	}

	public void setAccountList(ArrayList<AuthorizationPersonAccountVO> accountList) {
		this.accountList = accountList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountList == null) ? 0 : accountList.hashCode());
		result = prime * result + ((accountListRemoved == null) ? 0 : accountListRemoved.hashCode());
		result = prime * result + ((addrCntryCode == null) ? 0 : addrCntryCode.hashCode());
		result = prime * result + ((authInd == null) ? 0 : authInd.hashCode());
		result = prime * result + ((authPersnName == null) ? 0 : authPersnName.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((cpfCnpjNbr == null) ? 0 : cpfCnpjNbr.hashCode());
		result = prime * result + ((docId == null) ? 0 : docId.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((emNbr == null) ? 0 : emNbr.hashCode());
		result = prime * result + ((lastAuthDate == null) ? 0 : lastAuthDate.hashCode());
		result = prime * result + ((lastAuthUser == null) ? 0 : lastAuthUser.hashCode());
		result = prime * result + ((lastUpdDate == null) ? 0 : lastUpdDate.hashCode());
		result = prime * result + ((lastUpdUser == null) ? 0 : lastUpdUser.hashCode());
		result = prime * result + ((neighborhood == null) ? 0 : neighborhood.hashCode());
		result = prime * result + ((profText == null) ? 0 : profText.hashCode());
		result = prime * result + ((recStatCode == null) ? 0 : recStatCode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((tableOrigin == null) ? 0 : tableOrigin.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		AuthorizationPersonVO other = (AuthorizationPersonVO) obj;
		if (accountList == null) {
			if (other.accountList != null)
				return false;
		} else if (!accountList.equals(other.accountList))
			return false;
		if (accountListRemoved == null) {
			if (other.accountListRemoved != null)
				return false;
		} else if (!accountListRemoved.equals(other.accountListRemoved))
			return false;
		if (addrCntryCode == null) {
			if (other.addrCntryCode != null)
				return false;
		} else if (!addrCntryCode.equals(other.addrCntryCode))
			return false;
		if (authInd == null) {
			if (other.authInd != null)
				return false;
		} else if (!authInd.equals(other.authInd))
			return false;
		if (authPersnName == null) {
			if (other.authPersnName != null)
				return false;
		} else if (!authPersnName.equals(other.authPersnName))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (cpfCnpjNbr == null) {
			if (other.cpfCnpjNbr != null)
				return false;
		} else if (!cpfCnpjNbr.equals(other.cpfCnpjNbr))
			return false;
		if (docId == null) {
			if (other.docId != null)
				return false;
		} else if (!docId.equals(other.docId))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
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
		if (neighborhood == null) {
			if (other.neighborhood != null)
				return false;
		} else if (!neighborhood.equals(other.neighborhood))
			return false;
		if (profText == null) {
			if (other.profText != null)
				return false;
		} else if (!profText.equals(other.profText))
			return false;
		if (recStatCode == null) {
			if (other.recStatCode != null)
				return false;
		} else if (!recStatCode.equals(other.recStatCode))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (tableOrigin != other.tableOrigin)
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
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

	public ArrayList<AuthorizationPersonAccountVO> getAccountListRemoved() {
		if(accountListRemoved==null){
			accountListRemoved = new ArrayList<AuthorizationPersonAccountVO>();
		}
		return accountListRemoved;
	}

	public void setAccountListRemoved(ArrayList<AuthorizationPersonAccountVO> accountListRemoved) {
		this.accountListRemoved = accountListRemoved;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
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
