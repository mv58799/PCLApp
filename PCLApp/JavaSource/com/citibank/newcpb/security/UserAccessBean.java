package com.citibank.newcpb.security;

import org.apache.commons.lang.StringUtils;

import com.citibank.newcpb.enums.ScreenNamesEnum;

public class UserAccessBean {

	private boolean hasAccessMenuNovoCPB;
	private boolean hasAccessNovoCPBCli;
	private boolean hasAccessNovoCPBEG;
	private boolean hasAccessNovoCPBConta;
	private boolean hasAccessNovoCPBBanker;
	private boolean hasAccessNovoCPBBankerCons;
	private boolean hasAccessNovoCPBBankerAtual;
	private boolean hasAccessNovoCPBBankerDel;
	private boolean hasAccessNovoCPBBankerinsert;
	private boolean hasAccessNovoCPBBankerAprovRej;
	private boolean hasAccessNovoCPBCliConsDadosCad;
	private boolean hasAccessNovoCPBCliAtualDadosCad;
	private boolean hasAccessNovoCPBCliAprovRejDadosCad;
	private boolean hasAccessNovoCPBCliConsCadAut;
	private boolean hasAccessNovoCPBCliAtualCadAut;
	private boolean hasAccessNovoCPBCliDelCadAut;
	private boolean hasAccessNovoCPBCliAprovRejCadAut;
	private boolean hasAccessNovoCPBCliConsStatusCPFCNPJ;
	private boolean hasAccessNovoCPBCliAtualrStatusCPFCNPJ;
	private boolean hasAccessNovoCPBCliDelStatusCPFCNPJ;
	private boolean hasAccessNovoCPBCliAprovRejStatusCPFCNPJ;
	private boolean hasAccessNovoCPBCliAprovRejDocuments;
	private boolean hasAccessNovoCPBEGConsCadRisco;
	private boolean hasAccessNovoCPBEGInsRisco;
	private boolean hasAccessNovoCPBEGAtualRisco;
    private boolean hasAccessNovoCPBEGDelRisco;
	private boolean hasAccessNovoCPBEGAprovRejRisco;
	private boolean hasAccessNovoCPBCliConsDocuments;
	private boolean hasAccessNovoCPBCliAtualrDocuments;
	private boolean hasAccessNovoCPBCliDelDocuments;
	private boolean hasAccessNovoCPBCliInsDocuments;
	private boolean hasAccessNovoCPBEGConsAssoConta;
	private boolean hasAccessNovoCPBEGInsAssoConta;
	private boolean hasAccessNovoCPBEGAtualAssoConta;
	private boolean hasAccessNovoCPBEGDelAssoConta;
	private boolean hasAccessNovoCPBEGAprovRejAssoConta;
	private boolean hasAccessNovoCPBContaConsDadosCompdaConta;
	private boolean hasAccessNovoCPBContaAtualDadosCompdaConta;
	private boolean hasAccessNovoCPBContaAprovRejDadosCompdaConta;
	private boolean hasAccessNovoCPBContaConsContasMigradas;
	private boolean hasAccessNovoCPBContaInsContasMigradas;
	private boolean hasAccessNovoCPBContaDelContasMigradas;
	private boolean hasAccessNovoCPBContaAprovRejContasMigradas;
	private boolean hasAccessNovoCPBAprovacaoCentralizada;
	private boolean hasAccessNovoCPBCustomerCPB;
	private boolean hasAccessNovoCPBQuestionsKeCons;
	private boolean hasAccessNovoCPBQuestionsKeAtual;
	private boolean hasAccessNovoCPBQuestionsKeAprovRej;

	public UserAccessBean() {
		this.hasAccessMenuNovoCPB = false;
		this.hasAccessNovoCPBCli = false;
		this.hasAccessNovoCPBEG = false;
		this.hasAccessNovoCPBConta = false;
		this.hasAccessNovoCPBBanker = false;
		this.hasAccessNovoCPBBankerCons = false;
		this.hasAccessNovoCPBBankerAtual = false;
		this.hasAccessNovoCPBBankerDel = false;
		this.hasAccessNovoCPBBankerinsert = false;
		this.hasAccessNovoCPBBankerAprovRej = false;
		this.hasAccessNovoCPBCliConsDadosCad = false;
		this.hasAccessNovoCPBCliAtualDadosCad = false;
		this.hasAccessNovoCPBCliAprovRejDadosCad = false;
		this.hasAccessNovoCPBCliConsCadAut = false;
		this.hasAccessNovoCPBCliAtualCadAut = false;
		this.hasAccessNovoCPBCliDelCadAut = false;
		this.hasAccessNovoCPBCliAprovRejCadAut = false;
		this.hasAccessNovoCPBCliConsStatusCPFCNPJ = false;
		this.hasAccessNovoCPBCliAtualrStatusCPFCNPJ = false;
		this.hasAccessNovoCPBCliDelStatusCPFCNPJ = false;
		this.hasAccessNovoCPBCliAprovRejStatusCPFCNPJ = false;
		this.hasAccessNovoCPBCliConsDocuments = false;
		this.hasAccessNovoCPBCliAtualrDocuments = false;
		this.hasAccessNovoCPBCliDelDocuments = false;
		this.hasAccessNovoCPBCliAprovRejDocuments = false;
		this.hasAccessNovoCPBEGConsCadRisco = false;
		this.hasAccessNovoCPBEGInsRisco = false;
		this.hasAccessNovoCPBEGAtualRisco = false;
		this.hasAccessNovoCPBEGDelRisco = false;
		this.hasAccessNovoCPBEGAprovRejRisco = false;
		this.hasAccessNovoCPBEGConsAssoConta = false;
		this.hasAccessNovoCPBEGInsAssoConta = false;
		this.hasAccessNovoCPBEGAtualAssoConta = false;
		this.hasAccessNovoCPBEGDelAssoConta = false;
		this.hasAccessNovoCPBEGAprovRejAssoConta = false;
		this.hasAccessNovoCPBContaConsDadosCompdaConta = false;
		this.hasAccessNovoCPBContaAtualDadosCompdaConta = false;
		this.hasAccessNovoCPBContaAprovRejDadosCompdaConta = false;
		this.hasAccessNovoCPBContaConsContasMigradas = false;
		this.hasAccessNovoCPBContaInsContasMigradas = false;
		this.hasAccessNovoCPBContaDelContasMigradas = false;
		this.hasAccessNovoCPBContaAprovRejContasMigradas = false;
		this.hasAccessNovoCPBAprovacaoCentralizada = false;
		this.hasAccessNovoCPBCustomerCPB = false;
		this.hasAccessNovoCPBQuestionsKeCons = false;
		this.hasAccessNovoCPBQuestionsKeAtual = false;
		this.hasAccessNovoCPBQuestionsKeAprovRej = false;
	}

	public boolean isHasAccessNovoCPBAprovRej(String selectModuleCode) {
		if(!StringUtils.isBlank(selectModuleCode)){
			if(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAprovRejDadosCad;
			}else if(ScreenNamesEnum.REGISTER_RISK.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBEGAprovRejRisco;
			}else if(ScreenNamesEnum.ACCOUNT_ASSOCIATION.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBEGAprovRejAssoConta;
			}else if(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAprovRejCadAut;
			}else if(ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBContaAprovRejDadosCompdaConta;
			}else if(ScreenNamesEnum.ACCOUNT_MIGRATED.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBContaAprovRejContasMigradas;
			}else if(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAprovRejStatusCPFCNPJ;
			}else if(ScreenNamesEnum.DOCUMENTS.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAprovRejDocuments;
			}else if(ScreenNamesEnum.BANKER_DATA.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBBankerAprovRej;
			}else if(ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBQuestionsKeAprovRej;				
			}else if(ScreenNamesEnum.QUESTIONS_KE_AUTH.getNome().equalsIgnoreCase(selectModuleCode)){
					return hasAccessNovoCPBQuestionsKeAprovRej;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isHasAccessNovoCPBEdit(String selectModuleCode) {
		if(!StringUtils.isBlank(selectModuleCode)){
			if(ScreenNamesEnum.REGISTER_DATA_CUSTOMER.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAtualDadosCad;
			}else if(ScreenNamesEnum.REGISTER_RISK.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBEGAtualRisco;
			}else if(ScreenNamesEnum.ACCOUNT_ASSOCIATION.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBEGAtualAssoConta;
			}else if(ScreenNamesEnum.REGISTER_AUTHORIZED.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAtualCadAut;
			}else if(ScreenNamesEnum.ACCOUNT_COMPLEMENTARY_DATA.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBContaAtualDadosCompdaConta;
			}else if(ScreenNamesEnum.CUSTOMER_DOC_STATUS.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAtualrStatusCPFCNPJ;
			}else if(ScreenNamesEnum.DOCUMENTS.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBCliAtualrDocuments;	
			}else if(ScreenNamesEnum.BANKER_DATA.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBBankerAtual;
			}else if(ScreenNamesEnum.QUESTIONS_KE_CUSTOMER.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBQuestionsKeAtual;
			}else if(ScreenNamesEnum.QUESTIONS_KE_AUTH.getNome().equalsIgnoreCase(selectModuleCode)){
				return hasAccessNovoCPBQuestionsKeAtual;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean isHasAccessMenuNovoCPB() {
		return hasAccessMenuNovoCPB;
	}

	public void setHasAccessMenuNovoCPB(boolean hasAccessMenuNovoCPB) {
		this.hasAccessMenuNovoCPB = hasAccessMenuNovoCPB;
	}

	public boolean isHasAccessNovoCPBCli() {
		return hasAccessNovoCPBCli;
	}

	public void setHasAccessNovoCPBCli(boolean hasAccessNovoCPBCli) {
		this.hasAccessNovoCPBCli = hasAccessNovoCPBCli;
	}

	public boolean isHasAccessNovoCPBEG() {
		return hasAccessNovoCPBEG;
	}

	public void setHasAccessNovoCPBEG(boolean hasAccessNovoCPBEG) {
		this.hasAccessNovoCPBEG = hasAccessNovoCPBEG;
	}

	public boolean isHasAccessNovoCPBConta() {
		return hasAccessNovoCPBConta;
	}

	public void setHasAccessNovoCPBConta(boolean hasAccessNovoCPBConta) {
		this.hasAccessNovoCPBConta = hasAccessNovoCPBConta;
	}

	public boolean isHasAccessNovoCPBCliConsDadosCad() {
		return hasAccessNovoCPBCliConsDadosCad;
	}

	public void setHasAccessNovoCPBCliConsDadosCad(boolean hasAccessNovoCPBCliConsDadosCad) {
		this.hasAccessNovoCPBCliConsDadosCad = hasAccessNovoCPBCliConsDadosCad;
	}

	public boolean isHasAccessNovoCPBCliAtualDadosCad() {
		return hasAccessNovoCPBCliAtualDadosCad;
	}

	public void setHasAccessNovoCPBCliAtualDadosCad(boolean hasAccessNovoCPBCliAtualDadosCad) {
		this.hasAccessNovoCPBCliAtualDadosCad = hasAccessNovoCPBCliAtualDadosCad;
	}
	
	
	public boolean isHasAccessNovoCPBCliAprovRejDadosCad() {
		return hasAccessNovoCPBCliAprovRejDadosCad;
	}

	public void setHasAccessNovoCPBCliAprovRejDadosCad(boolean hasAccessNovoCPBCliAprovRejDadosCad) {
		this.hasAccessNovoCPBCliAprovRejDadosCad = hasAccessNovoCPBCliAprovRejDadosCad;
	}

	public boolean isHasAccessNovoCPBCliConsCadAut() {
		return hasAccessNovoCPBCliConsCadAut;
	}

	public void setHasAccessNovoCPBCliConsCadAut(boolean hasAccessNovoCPBCliConsCadAut) {
		this.hasAccessNovoCPBCliConsCadAut = hasAccessNovoCPBCliConsCadAut;
	}

	public boolean isHasAccessNovoCPBCliAtualCadAut() {
		return hasAccessNovoCPBCliAtualCadAut;
	}

	public void setHasAccessNovoCPBCliAtualCadAut(boolean hasAccessNovoCPBCliAtualCadAut) {
		this.hasAccessNovoCPBCliAtualCadAut = hasAccessNovoCPBCliAtualCadAut;
	}

	public boolean isHasAccessNovoCPBCliDelCadAut() {
		return hasAccessNovoCPBCliDelCadAut;
	}

	public void setHasAccessNovoCPBCliDelCadAut(boolean hasAccessNovoCPBCliDelCadAut) {
		this.hasAccessNovoCPBCliDelCadAut = hasAccessNovoCPBCliDelCadAut;
	}

	public boolean isHasAccessNovoCPBCliAprovRejCadAut() {
		return hasAccessNovoCPBCliAprovRejCadAut;
	}

	public void setHasAccessNovoCPBCliAprovRejCadAut(boolean hasAccessNovoCPBCliAprovRejCadAut) {
		this.hasAccessNovoCPBCliAprovRejCadAut = hasAccessNovoCPBCliAprovRejCadAut;
	}

	public boolean isHasAccessNovoCPBCliConsStatusCPFCNPJ() {
		return hasAccessNovoCPBCliConsStatusCPFCNPJ;
	}

	public void setHasAccessNovoCPBCliConsStatusCPFCNPJ(boolean hasAccessNovoCPBCliConsStatusCPFCNPJ) {
		this.hasAccessNovoCPBCliConsStatusCPFCNPJ = hasAccessNovoCPBCliConsStatusCPFCNPJ;
	}

	public boolean isHasAccessNovoCPBCliAtualrStatusCPFCNPJ() {
		return hasAccessNovoCPBCliAtualrStatusCPFCNPJ;
	}

	public void setHasAccessNovoCPBCliAtualrStatusCPFCNPJ(boolean hasAccessNovoCPBCliAtualrStatusCPFCNPJ) {
		this.hasAccessNovoCPBCliAtualrStatusCPFCNPJ = hasAccessNovoCPBCliAtualrStatusCPFCNPJ;
	}

	public boolean isHasAccessNovoCPBCliDelStatusCPFCNPJ() {
		return hasAccessNovoCPBCliDelStatusCPFCNPJ;
	}

	public void setHasAccessNovoCPBCliDelStatusCPFCNPJ(boolean hasAccessNovoCPBCliDelStatusCPFCNPJ) {
		this.hasAccessNovoCPBCliDelStatusCPFCNPJ = hasAccessNovoCPBCliDelStatusCPFCNPJ;
	}
	
	public boolean isHasAccessNovoCPBCliConsDocuments() {
		return hasAccessNovoCPBCliConsDocuments;
	}

	public void setHasAccessNovoCPBCliConsDocuments(boolean hasAccessNovoCPBCliConsDocuments) {
		this.hasAccessNovoCPBCliConsDocuments = hasAccessNovoCPBCliConsDocuments;
	}
	
	public boolean isHasAccessNovoCPBCliInsDocuments() {
		return hasAccessNovoCPBCliInsDocuments;
	}

	public void setHasAccessNovoCPBCliInsDocuments(boolean hasAccessNovoCPBCliInsDocuments) {
		this.hasAccessNovoCPBCliInsDocuments = hasAccessNovoCPBCliInsDocuments;
	}

	public boolean isHasAccessNovoCPBCliAtualrDocuments() {
		return hasAccessNovoCPBCliAtualrDocuments;
	}

	public void setHasAccessNovoCPBCliAtualrDocuments(boolean hasAccessNovoCPBCliAtualrDocuments) {
		this.hasAccessNovoCPBCliAtualrDocuments = hasAccessNovoCPBCliAtualrDocuments;
	}

	public boolean isHasAccessNovoCPBCliDelDocuments() {
		return hasAccessNovoCPBCliDelDocuments;
	}

	public void setHasAccessNovoCPBCliDelDocuments(boolean hasAccessNovoCPBCliDelDocuments) {
		this.hasAccessNovoCPBCliDelDocuments = hasAccessNovoCPBCliDelDocuments;
	}

    public boolean isHasAccessNovoCPBEGConsCadRisco() {
		return hasAccessNovoCPBEGConsCadRisco;
	}

	public void setHasAccessNovoCPBEGConsCadRisco(boolean hasAccessNovoCPBEGConsCadRisco) {
		this.hasAccessNovoCPBEGConsCadRisco = hasAccessNovoCPBEGConsCadRisco;
	}

	public boolean isHasAccessNovoCPBEGInsRisco() {
		return hasAccessNovoCPBEGInsRisco;
	}

	public void setHasAccessNovoCPBEGInsRisco(boolean hasAccessNovoCPBEGInsRisco) {
		this.hasAccessNovoCPBEGInsRisco = hasAccessNovoCPBEGInsRisco;
	}

	public boolean isHasAccessNovoCPBEGAtualRisco() {
		return hasAccessNovoCPBEGAtualRisco;
	}

	public void setHasAccessNovoCPBEGAtualRisco(boolean hasAccessNovoCPBEGAtualRisco) {
		this.hasAccessNovoCPBEGAtualRisco = hasAccessNovoCPBEGAtualRisco;
	}

	public boolean isHasAccessNovoCPBEGDelRisco() {
		return hasAccessNovoCPBEGDelRisco;
	}

	public void setHasAccessNovoCPBEGDelRisco(boolean hasAccessNovoCPBEGDelRisco) {
		this.hasAccessNovoCPBEGDelRisco = hasAccessNovoCPBEGDelRisco;
	}

	public boolean isHasAccessNovoCPBEGAprovRejRisco() {
		return hasAccessNovoCPBEGAprovRejRisco;
	}

	public void setHasAccessNovoCPBEGAprovRejRisco(boolean hasAccessNovoCPBEGAprovRejRisco) {
		this.hasAccessNovoCPBEGAprovRejRisco = hasAccessNovoCPBEGAprovRejRisco;
	}

	public boolean isHasAccessNovoCPBEGConsAssoConta() {
		return hasAccessNovoCPBEGConsAssoConta;
	}

	public void setHasAccessNovoCPBEGConsAssoConta(boolean hasAccessNovoCPBEGConsAssoConta) {
		this.hasAccessNovoCPBEGConsAssoConta = hasAccessNovoCPBEGConsAssoConta;
	}

	public boolean isHasAccessNovoCPBEGInsAssoConta() {
		return hasAccessNovoCPBEGInsAssoConta;
	}

	public void setHasAccessNovoCPBEGInsAssoConta(boolean hasAccessNovoCPBEGInsAssoConta) {
		this.hasAccessNovoCPBEGInsAssoConta = hasAccessNovoCPBEGInsAssoConta;
	}

	public boolean isHasAccessNovoCPBEGAtualAssoConta() {
		return hasAccessNovoCPBEGAtualAssoConta;
	}

	public void setHasAccessNovoCPBEGAtualAssoConta(boolean hasAccessNovoCPBEGAtualAssoConta) {
		this.hasAccessNovoCPBEGAtualAssoConta = hasAccessNovoCPBEGAtualAssoConta;
	}

	public boolean isHasAccessNovoCPBEGDelAssoConta() {
		return hasAccessNovoCPBEGDelAssoConta;
	}

	public void setHasAccessNovoCPBEGDelAssoConta(boolean hasAccessNovoCPBEGDelAssoConta) {
		this.hasAccessNovoCPBEGDelAssoConta = hasAccessNovoCPBEGDelAssoConta;
	}

	public boolean isHasAccessNovoCPBEGAprovRejAssoConta() {
		return hasAccessNovoCPBEGAprovRejAssoConta;
	}

	public void setHasAccessNovoCPBEGAprovRejAssoConta(boolean hasAccessNovoCPBEGAprovRejAssoConta) {
		this.hasAccessNovoCPBEGAprovRejAssoConta = hasAccessNovoCPBEGAprovRejAssoConta;
	}

	public boolean isHasAccessNovoCPBContaConsDadosCompdaConta() {
		return hasAccessNovoCPBContaConsDadosCompdaConta;
	}

	public void setHasAccessNovoCPBContaConsDadosCompdaConta(boolean hasAccessNovoCPBContaConsDadosCompdaConta) {
		this.hasAccessNovoCPBContaConsDadosCompdaConta = hasAccessNovoCPBContaConsDadosCompdaConta;
	}

	public boolean isHasAccessNovoCPBContaAtualDadosCompdaConta() {
		return hasAccessNovoCPBContaAtualDadosCompdaConta;
	}

	public void setHasAccessNovoCPBContaAtualDadosCompdaConta(boolean hasAccessNovoCPBContaAtualDadosCompdaConta) {
		this.hasAccessNovoCPBContaAtualDadosCompdaConta = hasAccessNovoCPBContaAtualDadosCompdaConta;
	}

	public boolean isHasAccessNovoCPBContaAprovRejDadosCompdaConta() {
		return hasAccessNovoCPBContaAprovRejDadosCompdaConta;
	}

	public void setHasAccessNovoCPBContaAprovRejDadosCompdaConta(boolean hasAccessNovoCPBContaAprovRejDadosCompdaConta) {
		this.hasAccessNovoCPBContaAprovRejDadosCompdaConta = hasAccessNovoCPBContaAprovRejDadosCompdaConta;
	}

	public boolean isHasAccessNovoCPBContaConsContasMigradas() {
		return hasAccessNovoCPBContaConsContasMigradas;
	}

	public void setHasAccessNovoCPBContaConsContasMigradas(boolean hasAccessNovoCPBContaConsContasMigradas) {
		this.hasAccessNovoCPBContaConsContasMigradas = hasAccessNovoCPBContaConsContasMigradas;
	}

	public boolean isHasAccessNovoCPBContaInsContasMigradas() {
		return hasAccessNovoCPBContaInsContasMigradas;
	}

	public void setHasAccessNovoCPBContaInsContasMigradas(boolean hasAccessNovoCPBContaInsContasMigradas) {
		this.hasAccessNovoCPBContaInsContasMigradas = hasAccessNovoCPBContaInsContasMigradas;
	}

	public boolean isHasAccessNovoCPBContaDelContasMigradas() {
		return hasAccessNovoCPBContaDelContasMigradas;
	}

	public void setHasAccessNovoCPBContaDelContasMigradas(boolean hasAccessNovoCPBContaDelContasMigradas) {
		this.hasAccessNovoCPBContaDelContasMigradas = hasAccessNovoCPBContaDelContasMigradas;
	}

	public boolean isHasAccessNovoCPBContaAprovRejContasMigradas() {
		return hasAccessNovoCPBContaAprovRejContasMigradas;
	}

	public void setHasAccessNovoCPBContaAprovRejContasMigradas(boolean hasAccessNovoCPBContaAprovRejContasMigradas) {
		this.hasAccessNovoCPBContaAprovRejContasMigradas = hasAccessNovoCPBContaAprovRejContasMigradas;
	}

	public boolean isHasAccessNovoCPBCliAprovRejStatusCPFCNPJ() {
		return hasAccessNovoCPBCliAprovRejStatusCPFCNPJ;
	}

	public void setHasAccessNovoCPBCliAprovRejStatusCPFCNPJ(boolean hasAccessNovoCPBCliAprovRejStatusCPFCNPJ) {
		this.hasAccessNovoCPBCliAprovRejStatusCPFCNPJ = hasAccessNovoCPBCliAprovRejStatusCPFCNPJ;
	}

	public boolean isHasAccessNovoCPBAprovacaoCentralizada() {
		return hasAccessNovoCPBAprovacaoCentralizada;
	}

	public void setHasAccessNovoCPBAprovacaoCentralizada(boolean hasAccessNovoCPBAprovacaoCentralizada) {
		this.hasAccessNovoCPBAprovacaoCentralizada = hasAccessNovoCPBAprovacaoCentralizada;
	}

	public boolean isHasAccessNovoCPBBanker() {
		return hasAccessNovoCPBBanker;
	}

	public void setHasAccessNovoCPBBanker(boolean hasAccessNovoCPBBanker) {
		this.hasAccessNovoCPBBanker = hasAccessNovoCPBBanker;
	}

	public boolean isHasAccessNovoCPBBankerCons() {
		return hasAccessNovoCPBBankerCons;
	}

	public void setHasAccessNovoCPBBankerCons(boolean hasAccessNovoCPBBankerCons) {
		this.hasAccessNovoCPBBankerCons = hasAccessNovoCPBBankerCons;
	}

	public boolean isHasAccessNovoCPBBankerAtual() {
		return hasAccessNovoCPBBankerAtual;
	}

	public void setHasAccessNovoCPBBankerAtual(boolean hasAccessNovoCPBBankerAtual) {
		this.hasAccessNovoCPBBankerAtual = hasAccessNovoCPBBankerAtual;
	}

	public boolean isHasAccessNovoCPBBankerDel() {
		return hasAccessNovoCPBBankerDel;
	}

	public void setHasAccessNovoCPBBankerDel(boolean hasAccessNovoCPBBankerDel) {
		this.hasAccessNovoCPBBankerDel = hasAccessNovoCPBBankerDel;
	}

	public boolean isHasAccessNovoCPBBankerinsert() {
		return hasAccessNovoCPBBankerinsert;
	}

	public void setHasAccessNovoCPBBankerinsert(boolean hasAccessNovoCPBBankerinsert) {
		this.hasAccessNovoCPBBankerinsert = hasAccessNovoCPBBankerinsert;
	}

	public boolean isHasAccessNovoCPBBankerAprovRej() {
		return hasAccessNovoCPBBankerAprovRej;
	}

	public void setHasAccessNovoCPBBankerAprovRej(
			boolean hasAccessNovoCPBBankerAprovRej) {
		this.hasAccessNovoCPBBankerAprovRej = hasAccessNovoCPBBankerAprovRej;
	}
	public boolean isHasAccessNovoCPBCustomerCPB() {
		return hasAccessNovoCPBCustomerCPB;
	}

	public void setHasAccessNovoCPBCustomerCPB(boolean hasAccessNovoCPBCustomerCPB) {
		this.hasAccessNovoCPBCustomerCPB = hasAccessNovoCPBCustomerCPB;
	}

	public boolean isHasAccessNovoCPBQuestionsKeCons() {
		return hasAccessNovoCPBQuestionsKeCons;
	}

	public void setHasAccessNovoCPBQuestionsKeCons(
			boolean hasAccessNovoCPBQuestionsKeCons) {
		this.hasAccessNovoCPBQuestionsKeCons = hasAccessNovoCPBQuestionsKeCons;
	}

	public boolean isHasAccessNovoCPBQuestionsKeAtual() {
		return hasAccessNovoCPBQuestionsKeAtual;
	}

	public void setHasAccessNovoCPBQuestionsKeAtual(
			boolean hasAccessNovoCPBQuestionsKeAtual) {
		this.hasAccessNovoCPBQuestionsKeAtual = hasAccessNovoCPBQuestionsKeAtual;
	}

	public boolean isHasAccessNovoCPBQuestionsKeAprovRej() {
		return hasAccessNovoCPBQuestionsKeAprovRej;
	}

	public void setHasAccessNovoCPBQuestionsKeAprovRej(
			boolean hasAccessNovoCPBQuestionsKeAprovRej) {
		this.hasAccessNovoCPBQuestionsKeAprovRej = hasAccessNovoCPBQuestionsKeAprovRej;
	}
	
	
}