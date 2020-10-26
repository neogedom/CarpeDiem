package br.com.carpediem.modelo;
import java.util.ArrayList;

public class Professor extends PessoaFisica {
	
	private Integer codigoProfessor;
	private ArrayList <Disciplina> disciplinas;
	private ArrayList <Curso> cursos;
	private Integer nivelAcesso = 2;


	public Integer getNivelAcesso()
	{
		return nivelAcesso;
	}
	
	public Integer getCodigoProfessor() {
		return codigoProfessor;
	}

	public void setCodigoProfessor(Integer codigoProfessor) {
		this.codigoProfessor = codigoProfessor;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public void setNivelAcesso(Integer nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public ArrayList <Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList <Curso> cursos) {
		this.cursos = cursos;
	}
	
	
}
