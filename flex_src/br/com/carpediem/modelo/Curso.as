package br.com.carpediem.modelo
{
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Curso")]
	public class Curso
	{
		public var codigoCurso:int;
		public var nome:String;
		public var ativo:Boolean;
	}
}