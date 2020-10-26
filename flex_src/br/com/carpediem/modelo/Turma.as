package br.com.carpediem.modelo 
{
	import flashx.textLayout.formats.Float;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Turma")]
	public class Turma
	{
		public var codigoTurma:int;
		public var ano:String;
		public var curso:Curso;
		public var professor:Professor;
		public var disciplina:Disciplina;
		public var aluno:ArrayCollection = new ArrayCollection();
		public var ativo:Boolean = true;
		
	}
}