package br.com.carpediem.modelo
{
	public class Atividade
	{
		private var codigo:int;
		private var descricao:String;
		private var horario:String;
		private var pessoa:Pessoa;
		
		public function Atividade()
		{
		}
		
		public function getCodigo():int
		{
			return codigo;
		}
		
		public function getDescricao():String
		{
			return descricao;
		}
		
		public function getHorario():String
		{
			return horario;
		}
		
		public function getPessoa():int
		{
			return pessoa;
		}
		
	}
}