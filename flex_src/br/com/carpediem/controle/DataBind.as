package br.com.carpediem.controle
{
	
	import br.com.carpediem.modelo.Administrador;
	import br.com.carpediem.modelo.Aluno;
	import br.com.carpediem.modelo.Aviso;
	import br.com.carpediem.modelo.Boletim;
	import br.com.carpediem.modelo.Curso;
	import br.com.carpediem.modelo.Endereco;
	import br.com.carpediem.modelo.Falta;
	import br.com.carpediem.modelo.Pessoa;
	import br.com.carpediem.modelo.PessoaFisica;
	import br.com.carpediem.modelo.Professor;
	import br.com.carpediem.modelo.Sala;
	import br.com.carpediem.modelo.Turma;
	import br.com.carpediem.modelo.Usuario;
	
	import flash.net.NetStream;
	import flash.net.SharedObject;
	
	import mx.collections.ArrayCollection;
	
	public class DataBind
	{
		private static var _instance:DataBind;
		[Bindable]
		public var IPServidor:String = "localhost";
		[Bindable]
		public var nomeSala:String = "";
		[Bindable]
		public var nomeProfessor:String = "";
		[Bindable]
		public var nomeDisciplina:String = "";
		[Bindable]
		public var turmaSala:Turma = new Turma();
		[Bindable]
		public var listaDeSalas:ArrayCollection = new ArrayCollection();
		[Bindable]
		public var listaItemSalas:ArrayCollection = new ArrayCollection();
		[Bindable]
		public var boasVindas:String;
		[Bindable]
		public var boasVindasData:String;
		[Bindable]
		public var usuarioAtual:Usuario;
		[Bindable]
		public var nivelAcesso:int;
		[Bindable]
		public var nsPub:NetStream; // NetStream responsável pela publicação
		[Bindable]
		public var nsCli:NetStream; // NetStream responsável pela Visualização
		[Bindable]
		public var usuarioPalavra:String;
		[Bindable]
		public var senhaPopUp:String;
		[Bindable]
		public var enderecoPopUp:Endereco = new Endereco();
		[Bindable]
		public var cursoPopUp:Curso = new Curso();
		[Bindable]
		public var faltaPopUp:Falta = new Falta();
		[Bindable]
		public var alunoPopUp:Aluno = new Aluno();
		[Bindable]
		public var pessoaFisicaPopUp:PessoaFisica = new PessoaFisica();
		[Bindable]
		public var mudouCidade:Boolean = false;
		[Bindable]
		public var mudouEstado:Boolean = false;
		[Bindable]
		public var turmaAvisoSelecinada:Turma = new Turma();
		[Bindable]
		public var disciplinasProfessorSelecionado:ArrayCollection = new ArrayCollection();
		[Bindable]
		public var disciplinasAlunoSelecionado:ArrayCollection = new ArrayCollection();
		[Bindable]
		public var faltasBoletimSelecionado:ArrayCollection = new ArrayCollection();
		[Bindable]
		public var enderecoPessoaFisicaSelecionada:Endereco = new Endereco();
		[Bindable]
		public var pessoaFisicaSelecionadaUsuario:PessoaFisica = new PessoaFisica();
		[Bindable]
		public var tabelaDisciplinaProfessorEditavel:Boolean = false;
		[Bindable]
		public var tabelaDisciplinaAlunoEditavel:Boolean = false;
		[Bindable]
		public var tabelaFaltaEditavel:Boolean = false;
		[Bindable]
		public var tabelaUsuarioEditavel:Boolean = false;
		[Bindable]
		public var tabelaDetalheEditavel:Boolean = false;
		[Bindable]
		public var detalheSelecionado:String = "";
		[Bindable]
		public var camposEnderecoEditavel:Boolean = false;
		[Bindable]
		public var usuarioLinhaSelecionada:Usuario;
		[Bindable]
		public var alunoLinhaSelecionada:String = "";
		[Bindable]
		public var boletimLinhaSelecionada:Boletim = new Boletim();
		[Bindable]
		public var pessoaLinhaSelecionada:PessoaFisica= new PessoaFisica();
		[Bindable]
		public var novaSenha:String = "";
		[Bindable]
		public var linhaEditavel:Boolean = false;
		[Bindable]
		public var tolerante:Boolean = false;
		[Bindable]
		public var salaAlterada:Sala = new Sala();
		[Bindable]
		public var avisoDetalhe:String = ""
		
		
		
		public function DataBind()
		{
			
		}
		
		public static function instance():DataBind { 
			if (_instance == null) { 
				_instance = new DataBind(); 
			}
			
			return _instance;
		}
		
		public function cortarMicrofone():void
		{
			nsCli.soundTransform.volume = 0;
		}
	}
}