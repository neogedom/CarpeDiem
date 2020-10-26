package br.com.carpediem.modelo
{
	/**
	 * @author Vinicius
	 */
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Falta")]
	public class Falta
	{
		public var codigoFalta:int;
		public var aula:Aula;
		public var aluno:Aluno;
		public var justificativa:String;
	}	
}


