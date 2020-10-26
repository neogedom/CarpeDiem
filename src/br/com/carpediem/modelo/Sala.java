package br.com.carpediem.modelo;

public class Sala {
	
	private Integer codigoSala;
	private String nome;
	private Boolean travada;
	private Boolean comSenha;
	private Professor professor;
	private Disciplina disciplina;
	private Boolean ativo = true;
	private String view;
	private String senha;
	private Turma turma;
	private String log;
	
	
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getCodigoSala() {
		return codigoSala;
	}

	public void setCodigoSala(Integer codigoSala) {
		this.codigoSala = codigoSala;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public void setcodigoSala(Integer codigoSala) {
		this.codigoSala = codigoSala;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Boolean getTravada() {
		return travada;
	}

	public void setTravada(Boolean travada) {
		this.travada = travada;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Boolean getComSenha() {
		return comSenha;
	}
	
	public void setComSenha(Boolean comSenha) {
		this.comSenha = comSenha;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	public Integer getcodigoSala() {
		return codigoSala;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	

}
