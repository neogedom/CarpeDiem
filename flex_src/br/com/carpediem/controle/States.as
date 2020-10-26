package br.com.carpediem.controle
{
	public class States
	{
		private static var _instance:States; 
		public static const LOGIN:String = "Login"; 
		public static const TELA_INICIAL:String = "TelaInicial"; 
		public static const SALA_VIRTUAL:String = "SalaVirtual"; 
		public static const TELA_CONTROLE:String = "TelaControle";
		public static const MURAL:String = "Mural";
		public static const QUADRO_NOTAS:String = "QuadroDeNotas";
		public static const VIEW_ALUNO:String = "ViewAluno";
		public static const VIEW_PROFESSOR:String = "ViewProfessor";
		public static const START_STATE:String = LOGIN;
		public static const DATE_FIELD:String = "DateField"; 
		
		[Bindable]
		public var currState:String = "";
		
		public function States()
		{
		}
		
		public static function instance():States { 
			if (_instance == null) { 
				_instance = new States(); 
			}
			
			return _instance;
			}
		}
		
		
}