package br.com.carpediem.modelo
{
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Disciplina")]
	public class Disciplina
	{
		public var codigoDisciplina:int;
		public var nome:String;
		public var curso:Curso;
		public var ativo:Boolean;
		
	}
}