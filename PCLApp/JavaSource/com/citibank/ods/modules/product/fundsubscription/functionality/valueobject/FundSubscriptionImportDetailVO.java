package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import java.util.Collection;
import java.util.Date;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.broker.functionality.valueobject.CellProperty;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionImportHistoryVO;

public class FundSubscriptionImportDetailVO extends BaseFncVO implements IFundSubscriptionImportVO{
	private static final long serialVersionUID = 1L;
	@CellProperty(value=0, maxLength=5, isMandatory=true, mustMatch="^\\d+$")
	private Long protocolo;
	@CellProperty(value=1)
	private Date dataDeAbertura;
	@CellProperty(2)
	private String nomeDoCliente;
	@CellProperty(3)
	private String assunto;
	@CellProperty(4)
	private String evento;
	@CellProperty(value=5)
	private Date dataDeSolucao;
	@CellProperty(6)
	private String fase;
	@CellProperty(value=7)
	private Date dataDaResolucaoDaFase;
	@CellProperty(8)
	private String comentario;
	@CellProperty(9)
	private Boolean attestationDeMismatch;
	@CellProperty(10)
	private Boolean boletimReservaOK;
	@CellProperty(11)
	private String codigoCorretora;
	@CellProperty(12)
	private String conhecimentoDoProduto;
	@CellProperty(value=13, isMandatory=true)
	private Long contaCorrenteDoDebito;
	@CellProperty(value=14, maxLength=11)
	private Long cpfDoInvestidor;
	@CellProperty(15)
	private String funcionalDoGerente;
	@CellProperty(16)
	private Long nivelDeRiscoDoProduto;
	@CellProperty(17)
	private String nomeDoAprovadorDoMismatch;
	@CellProperty(18)
	private String nomeDoFundo;
	@CellProperty(19)
	private String nomeDoInvestidor;
	@CellProperty(20)
	private String perfilDoClienteGRB;
	@CellProperty(21)
	private Double quantoDesteValorNovo;
	@CellProperty(22)
	private Boolean termoDeTreinamentoOK;
	@CellProperty(23)
	private String tipoDeTransacao;
	@CellProperty(24)
	private Double valorDoInvestimento;
	
	private Collection<FundSubscriptionImportHistoryVO> history;
	public Collection<FundSubscriptionImportHistoryVO> getHistory() {
		return history;
	}
	public void setHistory(Collection<FundSubscriptionImportHistoryVO> history) {
		this.history = history;
	}

	
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getProtocolo()
	 */
	public Long getProtocolo() {
		return protocolo;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setProtocolo(java.lang.Long)
	 */
	public void setProtocolo(Long protocolo) {
		this.protocolo = protocolo;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getDataDeAbertura()
	 */
	public Date getDataDeAbertura() {
		return dataDeAbertura;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setDataDeAbertura(java.util.Date)
	 */
	public void setDataDeAbertura(Date dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getNomeDoCliente()
	 */
	public String getNomeDoCliente() {
		return nomeDoCliente;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setNomeDoCliente(java.lang.String)
	 */
	public void setNomeDoCliente(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getAssunto()
	 */
	public String getAssunto() {
		return assunto;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setAssunto(java.lang.String)
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getEvento()
	 */
	public String getEvento() {
		return evento;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setEvento(java.lang.String)
	 */
	public void setEvento(String evento) {
		this.evento = evento;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getDataDeSolucao()
	 */
	public Date getDataDeSolucao() {
		return dataDeSolucao;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setDataDeSolucao(java.util.Date)
	 */
	public void setDataDeSolucao(Date dataDeSolucao) {
		this.dataDeSolucao = dataDeSolucao;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getFase()
	 */
	public String getFase() {
		return fase;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setFase(java.lang.String)
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getDataDaResolucaoDaFase()
	 */
	public Date getDataDaResolucaoDaFase() {
		return dataDaResolucaoDaFase;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setDataDaResolucaoDaFase(java.util.Date)
	 */
	public void setDataDaResolucaoDaFase(Date dataDaResolucaoDaFase) {
		this.dataDaResolucaoDaFase = dataDaResolucaoDaFase;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getComentario()
	 */
	public String getComentario() {
		return comentario;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setComentario(java.lang.String)
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getAttestationDeMismatch()
	 */
	public Boolean getAttestationDeMismatch() {
		return attestationDeMismatch;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setAttestationDeMismatch(java.lang.Boolean)
	 */
	public void setAttestationDeMismatch(Boolean attestationDeMismatch) {
		this.attestationDeMismatch = attestationDeMismatch;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getBoletimReservaOK()
	 */
	public Boolean getBoletimReservaOK() {
		return boletimReservaOK;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setBoletimReservaOK(java.lang.Boolean)
	 */
	public void setBoletimReservaOK(Boolean boletimReservaOK) {
		this.boletimReservaOK = boletimReservaOK;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getCodigoCorretora()
	 */
	public String getCodigoCorretora() {
		return codigoCorretora;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setCodigoCorretora(java.lang.String)
	 */
	public void setCodigoCorretora(String codigoCorretora) {
		this.codigoCorretora = codigoCorretora;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getConhecimentoDoProduto()
	 */
	public String getConhecimentoDoProduto() {
		return conhecimentoDoProduto;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setConhecimentoDoProduto(java.lang.String)
	 */
	public void setConhecimentoDoProduto(String conhecimentoDoProduto) {
		this.conhecimentoDoProduto = conhecimentoDoProduto;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getContaCorrenteDoDebito()
	 */
	public Long getContaCorrenteDoDebito() {
		return contaCorrenteDoDebito;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setContaCorrenteDoDebito(java.lang.Long)
	 */
	public void setContaCorrenteDoDebito(Long contaCorrenteDoDebito) {
		this.contaCorrenteDoDebito = contaCorrenteDoDebito;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getCpfDoInvestidor()
	 */
	public Long getCpfDoInvestidor() {
		return cpfDoInvestidor;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setCpfDoInvestidor(java.lang.String)
	 */
	public void setCpfDoInvestidor(Long cpfDoInvestidor) {
		this.cpfDoInvestidor = cpfDoInvestidor;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getFuncionalDoGerente()
	 */
	public String getFuncionalDoGerente() {
		return funcionalDoGerente;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setFuncionalDoGerente(java.lang.String)
	 */
	public void setFuncionalDoGerente(String funcionalDoGerente) {
		this.funcionalDoGerente = funcionalDoGerente;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getNivelDeRiscoDoProduto()
	 */
	public Long getNivelDeRiscoDoProduto() {
		return nivelDeRiscoDoProduto;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setNivelDeRiscoDoProduto(java.lang.Long)
	 */
	public void setNivelDeRiscoDoProduto(Long nivelDeRiscoDoProduto) {
		this.nivelDeRiscoDoProduto = nivelDeRiscoDoProduto;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getNomeDoAprovadorDoMismatch()
	 */
	public String getNomeDoAprovadorDoMismatch() {
		return nomeDoAprovadorDoMismatch;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setNomeDoAprovadorDoMismatch(java.lang.String)
	 */
	public void setNomeDoAprovadorDoMismatch(String nomeDoAprovadorDoMismatch) {
		this.nomeDoAprovadorDoMismatch = nomeDoAprovadorDoMismatch;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getNomeDoFundo()
	 */
	public String getNomeDoFundo() {
		return nomeDoFundo;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setNomeDoFundo(java.lang.String)
	 */
	public void setNomeDoFundo(String nomeDoFundo) {
		this.nomeDoFundo = nomeDoFundo;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getNomeDoInvestidor()
	 */
	public String getNomeDoInvestidor() {
		return nomeDoInvestidor;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setNomeDoInvestidor(java.lang.String)
	 */
	public void setNomeDoInvestidor(String nomeDoInvestidor) {
		this.nomeDoInvestidor = nomeDoInvestidor;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getPerfilDoClienteGRB()
	 */
	public String getPerfilDoClienteGRB() {
		return perfilDoClienteGRB;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setPerfilDoClienteGRB(java.lang.String)
	 */
	public void setPerfilDoClienteGRB(String perfilDoClienteGRB) {
		this.perfilDoClienteGRB = perfilDoClienteGRB;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getQuantoDesteValorNovo()
	 */
	public Double getQuantoDesteValorNovo() {
		return quantoDesteValorNovo;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setQuantoDesteValorNovo(java.lang.String)
	 */
	public void setQuantoDesteValorNovo(Double quantoDesteValorNovo) {
		this.quantoDesteValorNovo = quantoDesteValorNovo;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getTermoDeTreinamentoOK()
	 */
	public Boolean getTermoDeTreinamentoOK() {
		return termoDeTreinamentoOK;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setTermoDeTreinamentoOK(java.lang.Boolean)
	 */
	public void setTermoDeTreinamentoOK(Boolean termoDeTreinamentoOK) {
		this.termoDeTreinamentoOK = termoDeTreinamentoOK;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getTipoDeTransacao()
	 */
	public String getTipoDeTransacao() {
		return tipoDeTransacao;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setTipoDeTransacao(java.lang.String)
	 */
	public void setTipoDeTransacao(String tipoDeTransacao) {
		this.tipoDeTransacao = tipoDeTransacao;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#getValorDoInvestimento()
	 */
	public Double getValorDoInvestimento() {
		return valorDoInvestimento;
	}
	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.product.fundsubscription.functionality.valueobject.IFundSubscriptionImportVO#setValorDoInvestimento(java.lang.Double)
	 */
	public void setValorDoInvestimento(Double valorDoInvestimento) {
		this.valorDoInvestimento = valorDoInvestimento;
	}
	@Override
	public String toString() {
		return "ImportFundsSubscriptionVO [protocolo=" + protocolo
				+ ", dataDeAbertura=" + dataDeAbertura + ", nomeDoCliente="
				+ nomeDoCliente + ", assunto=" + assunto + ", evento=" + evento
				+ ", dataDeSolucao=" + dataDeSolucao + ", fase=" + fase
				+ ", dataDaResolucaoDaFase=" + dataDaResolucaoDaFase
				+ ", comentario=" + comentario + ", attestationDeMismatch="
				+ attestationDeMismatch + ", boletimReservaOK="
				+ boletimReservaOK + ", codigoCorretora=" + codigoCorretora
				+ ", conhecimentoDoProduto=" + conhecimentoDoProduto
				+ ", contaCorrenteDoDebito=" + contaCorrenteDoDebito
				+ ", cpfDoInvestidor=" + cpfDoInvestidor
				+ ", funcionalDoGerente=" + funcionalDoGerente
				+ ", nivelDeRiscoDoProduto=" + nivelDeRiscoDoProduto
				+ ", nomeDoAprovadorDoMismatch=" + nomeDoAprovadorDoMismatch
				+ ", nomeDoFundo=" + nomeDoFundo + ", nomeDoInvestidor="
				+ nomeDoInvestidor + ", perfilDoClienteGRB="
				+ perfilDoClienteGRB + ", quantoDesteValorNovo="
				+ quantoDesteValorNovo + ", termoDeTreinamentoOK="
				+ termoDeTreinamentoOK + ", tipoDeTransacao=" + tipoDeTransacao
				+ ", valorDoInvestimento=" + valorDoInvestimento + "]";
	}
	
	

}
