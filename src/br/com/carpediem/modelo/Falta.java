package br.com.carpediem.modelo;


public class Falta {
	public Integer codigoFalta;
	public Aula aula;
	public Aluno aluno;
	public String justificativa;
	public Integer getCodigoFalta() {
		return codigoFalta;
	}
	public void setCodigoFalta(Integer codigoFalta) {
		this.codigoFalta = codigoFalta;
	}
	public Aula getAula() {
		return aula;
	}
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
}
