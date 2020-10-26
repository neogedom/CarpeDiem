package br.com.carpediem.modelo;

public class Aviso {
	
	private Integer codigoAviso;
	private String descricao;
	private Turma turma;
	private Boolean ativo;
	
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}



	public void setCodigoAviso(Integer codigoAviso) {
		this.codigoAviso = codigoAviso;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getCodigoAviso() {
		return codigoAviso;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
	

}
