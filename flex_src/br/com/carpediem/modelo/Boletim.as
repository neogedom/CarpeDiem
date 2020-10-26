package br.com.carpediem.modelo
{
	import mx.collections.ArrayCollection;

	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Boletim")]
	public class Boletim
	{
		public var codigoBoletim:int;
		public var aluno:Aluno;
		public var turma:Turma;
		public var VA1:Number;
		public var VA2:Number;
		public var VA3:Number;
		public var VA4:Number;
		public var VA5:Number;
		public var status:String;
		public var faltas:ArrayCollection = new ArrayCollection();
		public var quantidadeFalta:int;
		public var porcentagem:String
		
		
	}
}