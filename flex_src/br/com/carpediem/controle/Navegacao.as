package br.com.carpediem.controle
{
	import br.com.carpediem.controle.States;
	import br.com.carpediem.visao.CarpeDiem;
	import br.com.carpediem.visao.componentes.SalaVirtual;
	
	import mx.controls.Alert;
	
	public class Navegacao
	{
		private static var _instance:Navegacao;
		
		public static function instance():Navegacao { 
			if (_instance == null) { 
				_instance = new Navegacao(); 
			}
			
			return _instance;
		}
		public function AcessaLogin():void
		{
			States.instance().currState = States.LOGIN;
		}
		
		public function AcessaTelaInicial():void
		{
			States.instance().currState = States.TELA_INICIAL;
		}
		
		public function AcessaSalaVirtual():void
		{
			States.instance().currState = States.SALA_VIRTUAL;
		}
		
		public function AcessaTelaControle():void
		{
			States.instance().currState = States.TELA_CONTROLE;
		}
		
		public function AcessaQuadroNotas():void
		{
			States.instance().currState = States.QUADRO_NOTAS;
		}
		
		public function AcessaMural():void
		{
			States.instance().currState = States.MURAL;
		}
	}
}