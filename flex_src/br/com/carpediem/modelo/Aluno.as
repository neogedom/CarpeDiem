package br.com.carpediem.modelo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Aluno")]
	public class Aluno extends PessoaFisica
	{
		public var codigoAluno:int;
		public var matricula:String;
		public var disciplinas:ArrayCollection = new ArrayCollection();
		
	}
}