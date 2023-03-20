package br.com.soc.sistema.vo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExamesFuncionarioVo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String rowid;
	private FuncionarioVo codFuncionario;
	private ExameVo codExame;
	private LocalDate dataRealizacao;
	
	public ExamesFuncionarioVo() {}
	
	public ExamesFuncionarioVo(String cdFuncionarioExame, FuncionarioVo codFuncionario, ExameVo codExame, LocalDate dataRealizacao) {
		this.rowid = cdFuncionarioExame;
		this.codFuncionario = codFuncionario;
		this.codExame = codExame;
		this.dataRealizacao = dataRealizacao;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public FuncionarioVo getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(FuncionarioVo codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	public ExameVo getCodExame() {
		return codExame;
	}

	public void setCodExame(ExameVo codExame) {
		this.codExame = codExame;
	}

	public LocalDate getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(LocalDate dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}


	@Override
	public String toString() {
		return "ExamesFuncionarioVo [CodFuncionario =" + codFuncionario.getRowid() + ", CodExame =" + codExame.getRowid() + 
				", DataRealizacao ="+ dataRealizacao + "]";
	}
}
