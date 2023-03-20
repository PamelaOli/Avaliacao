package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarExamesFuncionario;

public class ExamesFuncionarioFilter {
	
	private OpcoesComboBuscarExamesFuncionario opcoesCombo;
	private String valorBusca;
	
	public String getValorBusca() {
		return valorBusca;
	}

	public ExamesFuncionarioFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}
	
	public OpcoesComboBuscarExamesFuncionario getOpcoesCombo() {
		return opcoesCombo;
	}

	public ExamesFuncionarioFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarExamesFuncionario.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static ExamesFuncionarioFilter builder() {
		return new ExamesFuncionarioFilter();
	}
	
}
