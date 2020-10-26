package br.com.carpediem.modelo;


public class Aluno extends PessoaFisica{
	
	private Integer codigoAluno;
	private String matricula;
	private Curso curso;

	public String getMatricula() {
		return matricula;
	}
	
	public Integer getCodigoAluno() {
		return codigoAluno;
	}

	public void setCodigoAluno(Integer codigoAluno) {
		this.codigoAluno = codigoAluno;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	

}
