package br.com.carpediem.modelo;

public class Usuario {
	private Integer codigoUsuario;
	private String usuario;
	private String senha;
	private PessoaFisica pessoaFisica;
	private Boolean ativo;
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}
	
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	
	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}
	
}
