package br.com.soc.sistema.action.relatorio;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.RelatorioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioAction extends Action{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<RelatorioVo> relatorio = new ArrayList<>();
	private RelatorioBusiness business = new RelatorioBusiness();
		
	public String mostrarRelatorio() {
		relatorio.addAll(business.trazerRelatorio());
		
		return SUCCESS;
	}
}
