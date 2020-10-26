package br.com.carpediem.modelo;
import java.util.Date;

public class Aula {
	
	private Integer codigoAula;
	private Turma turma;
	private Date data;
	private String horaInicial;
	private String horaFinal;
	
	public Integer getCodigoAula() {
		return codigoAula;
	}

	public void setCodigoAula(Integer codigoAula) {
		this.codigoAula = codigoAula;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	
	public Date getDate() {
		return data;
	}
	
	public String getHoraFinal() {
		return horaFinal;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	
	
	
	
}
