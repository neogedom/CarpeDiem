package br.com.carpediem.modelo;
import java.util.Date;

public class PessoaFisica extends Pessoa {
	private Integer codigoPessoaFisica;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String rg;
	private String estadoCivil;
	private String sexo;
	private Date dataNascimento;
	private Integer nivelAcesso;
	
	public Integer getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(Integer nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public Integer getCodigoPessoaFisica() {
		return codigoPessoaFisica;
	}

	public void setCodigoPessoaFisica(Integer codigoPessoaFisica) {
		this.codigoPessoaFisica = codigoPessoaFisica;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	

}
