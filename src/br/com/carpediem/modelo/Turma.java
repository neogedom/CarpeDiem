package br.com.carpediem.modelo;

import java.util.ArrayList;

public class Turma {
	private Integer codigoTurma;
	private String ano;
	private Curso curso;
	private Professor professor;
	private Disciplina disciplina;
	private ArrayList<Aluno> aluno;
	private String status;
	private Boolean ativo;

	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public ArrayList<Aluno> getAluno() {
		return aluno;
	}
	public void setAluno(ArrayList<Aluno> aluno) {
		this.aluno = aluno;
	}
	public Integer getCodigoTurma() {
		return codigoTurma;
	}
	public void setCodigoTurma(Integer codigoTurma) {
		this.codigoTurma = codigoTurma;
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
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
