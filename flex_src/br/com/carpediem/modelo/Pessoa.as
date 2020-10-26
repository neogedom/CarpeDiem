package br.com.carpediem.modelo
	
{
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Pessoa")]
	public class Pessoa
	{
		public var codigoPessoa:int;
		public var telefone:String;
		public var email:String;
		public var endereco:Endereco;
		public var ativo:Boolean;
		
	}
}