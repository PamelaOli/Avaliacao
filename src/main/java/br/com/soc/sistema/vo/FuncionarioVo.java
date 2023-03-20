package br.com.soc.sistema.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FuncionarioVo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String rowid;
	private String nome;
	
	public FuncionarioVo() {}

	public FuncionarioVo(String rowid, String nome) {
		this.rowid = rowid;
		this.nome = nome;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "FuncionarioVo [rowid=" + rowid + ", nome=" + nome + "]";
	}
}
