package br.com.carpediem.modelo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Professor")]
	public class Professor extends PessoaFisica
	{
		public var codigoProfessor:int;
		public var disciplinas:ArrayCollection = new ArrayCollection();
		public var instituicoes:ArrayCollection = new ArrayCollection();
		
	}
}