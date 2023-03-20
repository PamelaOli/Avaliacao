package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.examesFuncionario.ExamesFuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExamesFuncionarioFilter;
import br.com.soc.sistema.vo.ExamesFuncionarioVo;


public class ExamesFuncionarioBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExamesFuncionarioDao dao;
	
	public ExamesFuncionarioBusiness() {
		this.dao = new ExamesFuncionarioDao();
	}
	
	public List<ExamesFuncionarioVo> trazerTodosOsExamesFuncionarios(){
		try {
			return dao.findAllExamesFuncionario();
		}catch(Exception e) {
			throw new BusinessException("Nao foi possivel localizar os registros");
		}
	}
	
	public void salvarExamesFuncionario(ExamesFuncionarioVo examesFuncionarioVo) {
		try {
			if(examesFuncionarioVo.getCodExame() == null  || examesFuncionarioVo.getCodFuncionario() == null) {
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			}else{
				dao.insertExamesFuncionario(examesFuncionarioVo);
			}
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}		
	}
	
	public void atualizarExamesFuncionario(ExamesFuncionarioVo examesFuncionarioVo) {
		try {
			if(examesFuncionarioVo.getCodExame() == null && examesFuncionarioVo.getCodFuncionario() == null) {
				throw new IllegalArgumentException("Nome nao pode estar em branco");
			}else{
				dao.updateExamesFuncionario(examesFuncionarioVo);
			}
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a atualizacao do registro");
		}
	}
	
	public ExamesFuncionarioVo excluirExamesFuncionario(String codigo) {
		try {
			String naoDeletado = null;
			if(codigo == naoDeletado) {
				throw new IllegalArgumentException("Erro para excluir");
			}else{
				dao.deleteExamesFuncionario(codigo);
			}
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusao do registro");
		}
		return null;
	}
	
	public ExamesFuncionarioVo buscarPorId(String codigo) {
		try {
			return (ExamesFuncionarioVo) dao.findById(codigo);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public List<ExamesFuncionarioVo> filtrarExamesFuncionario(ExamesFuncionarioFilter filter){
		List<ExamesFuncionarioVo> examesFuncionarios = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case EXAME:
				try {
					examesFuncionarios.addAll(dao.findAllByExame(filter.getValorBusca()));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case FUNCIONARIO:
				examesFuncionarios.addAll(dao.findAllByFuncionario(filter.getValorBusca()));
			break;
		}
		
		return examesFuncionarios;
	}
	
}
