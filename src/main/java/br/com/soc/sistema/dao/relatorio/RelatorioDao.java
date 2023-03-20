package br.com.soc.sistema.dao.relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.vo.ExamesFuncionarioVo;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioDao extends Dao{
	
	private FuncionarioDao funcionarioDAO = new FuncionarioDao();
	private ExameDao exameDAO = new ExameDao();
	
	public List<RelatorioVo> findAllExamesFuncionariosByDate(){
		StringBuilder query = new StringBuilder("SELECT rowid id, cd_funcionario codFunc, cd_exame codExame, dt_exame dtExame FROM exame_funcionario ")
								.append("WHERE dt_exame BETWEEN ? AND ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+dtInicial+"%");
			ps.setString(i, "%"+dtFinal+"%");
			
			
			try(ResultSet rs = ps.executeQuery()){
				RelatorioVo vo =  null;
				List<RelatorioVo> examesFuncionario = new ArrayList<>();
				
				while (rs.next()) {
					vo = new RelatorioVo();
					vo.setRowid("id");
					vo.setCodExame(exameDAO.findByCodigo((Integer.parseInt(rs.getString("codExame")))));
					vo.setCodFuncionario(funcionarioDAO.findByCodigo((Integer.parseInt(rs.getString("codFunc")))));
					vo.setDataRealizacao(rs.getTimestamp("dtExame").toLocalDateTime().toLocalDate());
					
					examesFuncionario.add(vo);
				}
				return examesFuncionario;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	
}
