package br.com.carpediem.modelo
{
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Aviso")]
	public class Aviso
	{
		public var codigoAviso:int;
		public var descricao:String;
		public var turma:Turma;
		public var ativo: Boolean;
	
		
	}
}