package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.relatorio.RelatorioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioBusiness {
	
	private RelatorioDao dao;
	
	public RelatorioBusiness() {
		this.dao = new RelatorioDao();
	}
	
	public List<RelatorioVo> trazerRelatorio(){
		try {
			return dao.findAllExamesFuncionariosByDate();
		}catch(Exception e) {
			throw new BusinessException("Nao foi possivel localizar os registros");
		}
	}
}
