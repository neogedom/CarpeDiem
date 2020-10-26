package br.com.carpediem.modelo
{
	import br.com.carpediem.modelo.PessoaFisica;
	
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Usuario")]
	public class Usuario
	{
		public var codigoUsuario:int;
		public var usuario:String;
		public var senha:String;
		public var pessoaFisica:PessoaFisica;
		public var ativo:Boolean;
	}
		
}