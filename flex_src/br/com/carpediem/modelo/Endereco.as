package br.com.carpediem.modelo
{
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Endereco")]
	public class Endereco
	{
		public var codigo:int;
		public var rua: String;
		public var bairro:String;
		public var numero:String;
		public var cep:String;
		public var complemento:String;
		public var cidade:Cidade;
		public var ativo:Boolean;
	}
}