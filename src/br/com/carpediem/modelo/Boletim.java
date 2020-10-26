package br.com.carpediem.modelo;

import java.util.ArrayList;



public class Boletim {
	private Integer codigoBoletim;
	private Aluno aluno;
	private Turma turma;
	private Float VA1;
	private Float VA2;
	private Float VA3;
	private Float VA4;
	private Float VA5;
	private String status;
	private ArrayList<Falta> faltas; 
	private Integer quantidadeFalta;
	
	public Integer getCodigoBoletim() {
		return codigoBoletim;
	}
	public void setCodigoBoletim(Integer codigoBoletim) {
		this.codigoBoletim = codigoBoletim;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Float getVA1() {
		return VA1;
	}
	public void setVA1(Float vA1) {
		VA1 = vA1;
	}
	public Float getVA2() {
		return VA2;
	}
	public void setVA2(Float vA2) {
		VA2 = vA2;
	}
	public Float getVA3() {
		return VA3;
	}
	public void setVA3(Float vA3) {
		VA3 = vA3;
	}
	public Float getVA4() {
		return VA4;
	}
	public void setVA4(Float vA4) {
		VA4 = vA4;
	}
	public Float getVA5() {
		return VA5;
	}
	public void setVA5(Float vA5) {
		VA5 = vA5;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<Falta> getFaltas() {
		return faltas;
	}
	public void setFaltas(ArrayList<Falta> faltas) {
		this.faltas = faltas;
	}
	public Integer getQuantidadeFalta() {
		return quantidadeFalta;
	}
	public void setQuantidadeFalta(Integer quantidadeFalta) {
		this.quantidadeFalta = quantidadeFalta;
	}
	
}
