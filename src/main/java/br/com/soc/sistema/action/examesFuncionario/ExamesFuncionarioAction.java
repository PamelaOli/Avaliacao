package br.com.soc.sistema.action.examesFuncionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExamesFuncionarioBusiness;
import br.com.soc.sistema.filter.ExamesFuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExamesFuncionario;
import br.com.soc.sistema.vo.ExamesFuncionarioVo;

public class ExamesFuncionarioAction extends Action{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ExamesFuncionarioVo> examesFuncionario = new ArrayList<>();
	private ExamesFuncionarioBusiness business = new ExamesFuncionarioBusiness();
	private ExamesFuncionarioFilter filtrar = new ExamesFuncionarioFilter();
	private ExamesFuncionarioVo exameFuncionarioVo = new ExamesFuncionarioVo();
	
	public String todos() {
		examesFuncionario.addAll(business.trazerTodosOsExamesFuncionarios());
		
		return SUCCESS;
	}
	
	public String filtrar() {
		if (filtrar.isNullOpcoesCombo()) {
			return REDIRECT;
		} else {
			examesFuncionario = business.filtrarExamesFuncionario(filtrar);
		}
		return SUCCESS;
	}
	
	public String novo() {
		if (exameFuncionarioVo.getCodExame() == null || exameFuncionarioVo.getCodFuncionario() == null) {
			return INPUT;
		} else if (exameFuncionarioVo.getRowid().isEmpty()) {
			business.salvarExamesFuncionario(exameFuncionarioVo);
		} else {
			business.atualizarExamesFuncionario(exameFuncionarioVo);
		}
		return REDIRECT;
	}

	public String atualizar() {
		if (exameFuncionarioVo.getCodExame() == null || exameFuncionarioVo.getCodFuncionario() == null) {
			exameFuncionarioVo = business.buscarPorId(exameFuncionarioVo.getRowid());
			return INPUT;
		} else {
			business.atualizarExamesFuncionario(exameFuncionarioVo);
		}
		return REDIRECT;
	}

	public String excluir() {
		if (exameFuncionarioVo.getRowid() != null) {
			business.excluirExamesFuncionario(exameFuncionarioVo.getRowid());
		}
		return REDIRECT;
	}

	public String buscar() {
		if (exameFuncionarioVo.getRowid().isEmpty()) {
			return REDIRECT;
		} else {
			exameFuncionarioVo = business.buscarPorId(exameFuncionarioVo.getRowid());
		}
		return INPUT;
	}
	
	public List<OpcoesComboBuscarExamesFuncionario> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarExamesFuncionario.values());
	}

	public List<ExamesFuncionarioVo> getExamesFuncionario() {
		return examesFuncionario;
	}

	public void setExamesFuncionario(List<ExamesFuncionarioVo> examesFuncionario) {
		this.examesFuncionario = examesFuncionario;
	}

	public ExamesFuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExamesFuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExamesFuncionarioVo getExameFuncionarioVo() {
		return exameFuncionarioVo;
	}

	public void setExameFuncionarioVo(ExamesFuncionarioVo exameFuncionarioVo) {
		this.exameFuncionarioVo = exameFuncionarioVo;
	}
}
