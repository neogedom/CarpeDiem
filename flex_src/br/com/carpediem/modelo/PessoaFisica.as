package br.com.carpediem.modelo
{
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.PessoaFisica")]
	public class PessoaFisica extends Pessoa
	{
		public var codigoPessoaFisica:int;
		public var nome:String;
		public var sobrenome:String;
		public var cpf:String;
		public var rg:String;
		public var estadoCivil:String;
		public var sexo:String;
		public var dataNascimento:Date;
		public var nivelAcesso:int;
		
		
	
	}
}