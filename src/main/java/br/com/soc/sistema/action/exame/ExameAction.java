package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameVo;

public class ExameAction extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();

	public String todos() {
		exames.addAll(business.trazerTodosOsExames());

		return SUCCESS;
	}

	public String filtrar() {
		if (filtrar.isNullOpcoesCombo()) {
			return REDIRECT;
		} else {
			exames = business.filtrarExames(filtrar);
		}
		return SUCCESS;
	}
	
	//um botao para salvar novo e outro para alterar
	public String novo() {
		if (exameVo.getNome() == null) {
			return INPUT;
		} else if (exameVo.getRowid().isEmpty()) {
			business.salvarExame(exameVo);
		} else {
			business.atualizarExame(exameVo);
		}
		return REDIRECT;
	}

	public String buscar() {
		if (exameVo.getRowid() == null) {
			return REDIRECT;
		} else {
			exameVo = business.buscarExamePor(exameVo.getRowid());
		}
		return INPUT;
	}

	public String atualizar() {
		if (exameVo.getNome() == null) {
			exameVo = business.buscarExamePor(exameVo.getRowid());
			return INPUT;
		} else {
			business.atualizarExame(exameVo);
		}
		return REDIRECT;
	}

	public String excluir() {
		if (exameVo.getRowid() != null) {
			business.excluirExames(exameVo.getRowid());
		}
		return REDIRECT;
	}

	public List<OpcoesComboBuscarExames> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}

	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}
}
