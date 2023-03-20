package br.com.soc.sistema.dao.examesFuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.vo.ExamesFuncionarioVo;

public class ExamesFuncionarioDao extends Dao{
	private FuncionarioDao funcionarioDAO = new FuncionarioDao();
	private ExameDao exameDAO = new ExameDao();

	
	public void insertExamesFuncionario(ExamesFuncionarioVo examesFuncionarioVo){
		StringBuilder query = new StringBuilder("INSERT INTO exame_funcionario (cd_funcionario, cd_exame) values (?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setObject(i++, examesFuncionarioVo.getCodExame());
			ps.setObject(i++, examesFuncionarioVo.getCodFuncionario());
			ps.setTimestamp(i++, Timestamp.valueOf(examesFuncionarioVo.getDataRealizacao().atStartOfDay()));
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ExamesFuncionarioVo> findAllExamesFuncionario(){
		StringBuilder query = new StringBuilder("SELECT rowid id, cd_funcionario codFuncionario, cd_exame codExame, dt_exame dtExame FROM exame_funcionario");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExamesFuncionarioVo vo =  null;
			List<ExamesFuncionarioVo> examesFuncionario = new ArrayList<>();
			while (rs.next()) {
				vo = new ExamesFuncionarioVo();
				vo.setRowid(rs.getString("id"));
				vo.setCodExame(exameDAO.findByCodigo((Integer.parseInt(rs.getString("codExame")))));
				vo.setCodFuncionario(funcionarioDAO.findByCodigo((Integer.parseInt(rs.getString("codFuncionario")))));
				vo.setDataRealizacao(rs.getTimestamp("dtExame").toLocalDateTime().toLocalDate());
				
				
				
				examesFuncionario.add(vo);
			}
			return examesFuncionario;
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	

	
	public List<ExamesFuncionarioVo> findAllByFuncionario(String codFuncionario){
		StringBuilder query = new StringBuilder("select exame_funcionario.rowid id, exame_funcionario.cd_funcionario codFuncionario,exame_funcionario.cd_exame codExame, exame_funcionario.dt_exame dtExame from exame_funcionario ")
	            .append("join funcionario on funcionario.rowid = exame_funcionario.cd_funcionario join exame on exame.rowid = exame_funcionario.cd_exame where lower(funcionario.nm_funcionario) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			
			ps.setString(1, "%"+codFuncionario+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExamesFuncionarioVo vo =  null;
				List<ExamesFuncionarioVo> examesFuncionario = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExamesFuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodExame(exameDAO.findByCodigo((Integer.parseInt(rs.getString("codExame")))));
					vo.setCodFuncionario(funcionarioDAO.findByCodigo((Integer.parseInt(rs.getString("codFuncionario")))));
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
	
	public List<ExamesFuncionarioVo> findAllByExame(String codExame){
		StringBuilder query = new StringBuilder("select exame_funcionario.rowid id, exame_funcionario.cd_funcionario codFuncionario,exame_funcionario.cd_exame codExame, exame_funcionario.dt_exame dtExame from exame_funcionario ")
		        .append("join exame on exame.rowid = exame_funcionario.cd_exame where lower(exame.nm_exame) like lower(?)");
								
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			ps.setString(1, "%"+codExame+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExamesFuncionarioVo vo =  null;
				List<ExamesFuncionarioVo> examesFuncionario = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExamesFuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setCodExame(exameDAO.findByCodigo((Integer.parseInt(rs.getString("codExame")))));
					vo.setCodFuncionario(funcionarioDAO.findByCodigo((Integer.parseInt(rs.getString("codFuncionario")))));
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
	
	public ExamesFuncionarioVo findById(String cdExameFuncionario){
        StringBuilder query = new StringBuilder("SELECT rowid id, cd_funcionario codFunc, cd_exame codExame, dt_exame dtExame FROM exame_funcionario ")
                .append("WHERE rowid = ?");
        ExamesFuncionarioVo exameFuncVO = new ExamesFuncionarioVo();

        try(Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())){
            int i = 1;
            ps.setString(i, "%"+cdExameFuncionario+"%");

            try(ResultSet rs = ps.executeQuery()){
                ExamesFuncionarioVo vo =  null;
                List<ExamesFuncionarioVo> examesFuncionario = new ArrayList<>();

                while (rs.next()) {
                    exameFuncVO.setRowid("id");
                    exameFuncVO.setCodExame(exameDAO.findByCodigo((Integer.parseInt(rs.getString("codExame")))));
                    exameFuncVO.setCodFuncionario(funcionarioDAO.findByCodigo((Integer.parseInt(rs.getString("codFunc")))));
                    exameFuncVO.setDataRealizacao(rs.getTimestamp("dtExame").toLocalDateTime().toLocalDate());

                    examesFuncionario.add(vo);
                }
                return exameFuncVO;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return exameFuncVO;
    }
	
//	public List<ExamesFuncionarioVo> findAllExamesFuncionariosByDate(String dtInicial, String dtFinal){
//		StringBuilder query = new StringBuilder("SELECT rowid id, cd_funcionario codFunc, cd_exame codExame, dt_exame dtExame FROM exame_funcionario ")
//								.append("WHERE dt_exame BETWEEN ? AND ?");
//		
//		try(Connection con = getConexao();
//			PreparedStatement ps = con.prepareStatement(query.toString())){
//			int i = 1;
//			
//			ps.setString(i, "%"+dtInicial+"%");
//			ps.setString(i, "%"+dtFinal+"%");
//			
//			
//			try(ResultSet rs = ps.executeQuery()){
//				ExamesFuncionarioVo vo =  null;
//				List<ExamesFuncionarioVo> examesFuncionario = new ArrayList<>();
//				
//				while (rs.next()) {
//					vo = new ExamesFuncionarioVo();
//					vo.setRowid("id");
//					vo.setCodExame(exameDAO.findByCodigo((Integer.parseInt(rs.getString("codExame")))));
//					vo.setCodFuncionario(funcionarioDAO.findByCodigo((Integer.parseInt(rs.getString("codFunc")))));
//					vo.setDataRealizacao(rs.getTimestamp("dtExame").toLocalDateTime().toLocalDate());
//					
//					examesFuncionario.add(vo);
//				}
//				return examesFuncionario;
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}		
//		return Collections.emptyList();
//	}
	
	
	public void updateExamesFuncionario(ExamesFuncionarioVo examesFuncionarioVo) {
		StringBuilder query = new StringBuilder("UPDATE exame_funcionario SET cd_funcionario = ? AND cd_exame = ? AND dt_exame = ? "
				+ "WHERE rowid = ?");
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			ps.setObject(1, examesFuncionarioVo.getCodExame());
			ps.setObject(2, examesFuncionarioVo.getCodFuncionario());
			ps.setTimestamp(2, Timestamp.valueOf( examesFuncionarioVo.getDataRealizacao().atStartOfDay()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteExamesFuncionario(String codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM exame_funcionario WHERE rowid = ?");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			ps.setString(1, codigo);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
