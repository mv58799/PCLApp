package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import java.util.Date;

public interface IFundSubscriptionImportVO {

	public abstract Long getProtocolo();

	public abstract void setProtocolo(Long protocolo);

	public abstract Date getDataDeAbertura();

	public abstract void setDataDeAbertura(Date dataDeAbertura);

	public abstract String getNomeDoCliente();

	public abstract void setNomeDoCliente(String nomeDoCliente);

	public abstract String getAssunto();

	public abstract void setAssunto(String assunto);

	public abstract String getEvento();

	public abstract void setEvento(String evento);

	public abstract Date getDataDeSolucao();

	public abstract void setDataDeSolucao(Date dataDeSolucao);

	public abstract String getFase();

	public abstract void setFase(String fase);

	public abstract Date getDataDaResolucaoDaFase();

	public abstract void setDataDaResolucaoDaFase(Date dataDaResolucaoDaFase);

	public abstract String getComentario();

	public abstract void setComentario(String comentario);

	public abstract Boolean getAttestationDeMismatch();

	public abstract void setAttestationDeMismatch(Boolean attestationDeMismatch);

	public abstract Boolean getBoletimReservaOK();

	public abstract void setBoletimReservaOK(Boolean boletimReservaOK);

	public abstract String getCodigoCorretora();

	public abstract void setCodigoCorretora(String codigoCorretora);

	public abstract String getConhecimentoDoProduto();

	public abstract void setConhecimentoDoProduto(String conhecimentoDoProduto);

	public abstract Long getContaCorrenteDoDebito();

	public abstract void setContaCorrenteDoDebito(Long contaCorrenteDoDebito);

	public abstract Long getCpfDoInvestidor();

	public abstract void setCpfDoInvestidor(Long cpfDoInvestidor);

	public abstract String getFuncionalDoGerente();

	public abstract void setFuncionalDoGerente(String funcionalDoGerente);

	public abstract Long getNivelDeRiscoDoProduto();

	public abstract void setNivelDeRiscoDoProduto(Long nivelDeRiscoDoProduto);

	public abstract String getNomeDoAprovadorDoMismatch();

	public abstract void setNomeDoAprovadorDoMismatch(
			String nomeDoAprovadorDoMismatch);

	public abstract String getNomeDoFundo();

	public abstract void setNomeDoFundo(String nomeDoFundo);

	public abstract String getNomeDoInvestidor();

	public abstract void setNomeDoInvestidor(String nomeDoInvestidor);

	public abstract String getPerfilDoClienteGRB();

	public abstract void setPerfilDoClienteGRB(String perfilDoClienteGRB);

	public abstract Double getQuantoDesteValorNovo();

	public abstract void setQuantoDesteValorNovo(Double quantoDesteValorNovo);

	public abstract Boolean getTermoDeTreinamentoOK();

	public abstract void setTermoDeTreinamentoOK(Boolean termoDeTreinamentoOK);

	public abstract String getTipoDeTransacao();

	public abstract void setTipoDeTransacao(String tipoDeTransacao);

	public abstract Double getValorDoInvestimento();

	public abstract void setValorDoInvestimento(Double valorDoInvestimento);

}