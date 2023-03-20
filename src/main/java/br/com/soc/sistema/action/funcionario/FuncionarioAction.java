package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();

	public String todos() {
		funcionarios.addAll(business.trazerTodosOsFuncionarios());

		return SUCCESS;
	}

	public String filtrar() {
		if (filtrar.isNullOpcoesCombo()) {
			return REDIRECT;
		} else {
			funcionarios = business.filtrarFuncionarios(filtrar);
		}
		return SUCCESS;
	}

	//um botao para salvar novo e outro para alterar
	public String novo() {
		if (funcionarioVo.getNome() == null) {
			return INPUT;
		} else if (funcionarioVo.getRowid().isEmpty()) {
			business.salvarFuncionario(funcionarioVo);
		} else {
			business.atualizarFuncionario(funcionarioVo);
		}
		return REDIRECT;
	}

	public String buscar() {
		if (funcionarioVo.getRowid() == null) {
			return REDIRECT;
		} else {
			funcionarioVo = business.buscarFuncionarioPor(funcionarioVo.getRowid());
		}
		return INPUT;
	}

	public String atualizar() {
		if (funcionarioVo.getNome() == null) {
			funcionarioVo = business.buscarFuncionarioPor(funcionarioVo.getRowid());
			return INPUT;
		} else {
			business.atualizarFuncionario(funcionarioVo);
		}
		return REDIRECT;
	}

	public String excluir() {
		if(funcionarioVo.getRowid() != null) {
			business.excluirFuncionarios(funcionarioVo.getRowid());
		}
		return REDIRECT;
	}

	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}
}
