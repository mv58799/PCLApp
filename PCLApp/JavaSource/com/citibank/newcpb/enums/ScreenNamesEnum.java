package com.citibank.newcpb.enums;

public enum ScreenNamesEnum {

	REGISTER_DATA_CUSTOMER("Novo CPB - Cliente - Dados cadastrais"),
	REGISTER_RISK("Novo CPB - EG - Cadastro de Risco"),
	ACCOUNT_ASSOCIATION("Novo CPB - EG - Associação de Conta"),
	REGISTER_AUTHORIZED("Novo CPB - Cliente - Cadastro de Autorizados"),
	ACCOUNT_COMPLEMENTARY_DATA("Novo CPB - Conta - Dados Complementares"),
	ACCOUNT_MIGRATED("Novo CPB - Conta - Migradas (De-Para)"),
	CUSTOMER_DOC_STATUS("Novo CPB - Cliente - Status CPF/CNPJ"),
	DOCUMENTS("Novo CPB - Cliente - Documentos"),
	BANKER_DATA("Novo CPB - Banker"),
	QUESTIONS_KE_CUSTOMER("Novo CPB - Conta - Questionário K&E - Cliente"),
	QUESTIONS_KE_AUTH("Novo CPB - Conta - Questionário K&E - Procurador/Autorizado");

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private ScreenNamesEnum(String nome) {
		this.nome = nome;
	}
	
	public static ScreenNamesEnum fromValue(String value) throws IllegalArgumentException {
        try {
            for (ScreenNamesEnum enumItem : ScreenNamesEnum.values()) {
                if (enumItem.getNome().toUpperCase().equals(value.toUpperCase())) {
                    return enumItem;
                }
            }

            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value type :" + value);
        }
    }
}
