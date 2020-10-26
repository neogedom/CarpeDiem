package br.com.carpediem.modelo
{
	import mx.collections.ArrayCollection;

	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Aula")]
	public class Aula
	{
		public var codigoAula:int;
		public var turma:Turma;
		public var data:Date;
		public var horaFinal:String;
		public var horaInicial:String;
		
		
	}
}