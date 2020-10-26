package br.com.carpediem.modelo 
{
	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Sala")]
	public class Sala
	{
		public var codigoSala:int;
		public var nome:String;
		public var capacidade:int;
		public var travada:Boolean;
		public var comSenha:Boolean;
		public var professor:Professor;
		public var disciplina:Disciplina;
		public var ativo:Boolean = true;
		public var view:String;
		public var senha:String;
		public var turma:Turma;
		public var log:String;
			
	}
}