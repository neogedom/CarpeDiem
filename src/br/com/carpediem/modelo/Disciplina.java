package br.com.carpediem.modelo;

public class Disciplina {
	
	private Integer codigoDisciplina;
	private String nome;
	private Curso curso;
	private Boolean ativo = true;
	
	public Disciplina ()
	{
		
	}
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Integer getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(Integer codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public Disciplina (String nome)
	{
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setCodigo(Integer codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
