package br.com.carpediem.controle;

import br.com.carpediem.dao.*;
import br.com.carpediem.modelo.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class FlexRemoto {
	
	public Turma getTurmaPorSala (Integer codigoSala) throws Exception
	{
		DAOTurma daou = new DAOTurma();
		Turma turma = new Turma();
		try{
			 turma = daou.getPorSala(codigoSala);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao obter turma pelo FlexRemoto\n" + ex.getMessage());
		}
		return turma;
			
	}
	
	public Turma getTurma (String ano, String curso, String nome, String sobrenome, String disciplina) throws Exception
	{
		DAOTurma daou = new DAOTurma();
		Turma turma = new Turma();
		try{
			 turma = daou.getByNome(ano, curso, nome, sobrenome, disciplina);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao obter turma pelo FlexRemoto\n" + ex.getMessage());
		}
		return turma;
			
	}
	
	public Endereco getEnderecoPorPessoa (Integer codigoPessoa) throws Exception
	{
		DAOPessoa daou = new DAOPessoa();
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		try{
			 pessoa = daou.getByCodigo(codigoPessoa);
			 endereco = pessoa.getEndereco();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de endereço por pessoa pelo FlexRemoto\n" + ex.getMessage());
		}
		return endereco;
			
	}
	
	public PessoaFisica getPessoaFisicaUsuario (Integer codigoPessoaUsuario) throws Exception
	{
		DAOUsuario daou = new DAOUsuario();
		Usuario usuario = new Usuario();
		PessoaFisica pessoaFisica = new PessoaFisica();
		try{
			 usuario = daou.getByCodigo(codigoPessoaUsuario);
			 pessoaFisica = usuario.getPessoaFisica();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir pessoa física por usuário pelo FlexRemoto\n" + ex.getMessage());
		}
		return pessoaFisica;
			
	}
	
	public Sala getSalaByNome (String nomeSala) throws Exception
	{
		DAOSala daos = new DAOSala();
		Sala sala = new Sala();
		try{
			 sala = daos.getByNome(nomeSala);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir sala por nome pelo FlexRemoto\n" + ex.getMessage());
		}
		return sala;
			
	}
	
	public ArrayList<PessoaFisica> listaPessoaFisica () throws Exception
	{
		DAOPessoaFisica daop = new DAOPessoaFisica();
		ArrayList <PessoaFisica> al = new ArrayList<PessoaFisica>();
		try {
			 al = daop.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de pessoas físicas pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<PessoaFisica> listaPessoasFisicasSemUsuario () throws Exception
	{
		DAOPessoaFisica daop = new DAOPessoaFisica();
		ArrayList <PessoaFisica> al = new ArrayList<PessoaFisica>();
		try {
			 al = daop.listaPessoaFisicaSemUsuario();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de pessoas físicas pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Administrador> listaAdministradores () throws Exception
	{
		DAOAdministrador daop = new DAOAdministrador();
		ArrayList <Administrador> al = new ArrayList<Administrador>();
		try {
			 al = daop.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de administradores pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Professor> listaProfessores () throws Exception
	{
		DAOProfessor daop = new DAOProfessor();
		ArrayList <Professor> al = new ArrayList<Professor>();
		try {
			 al = daop.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de professores pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Professor> listaProfessorPorCurso (String curso) throws Exception
	{
		DAOProfessor daop = new DAOProfessor();
		ArrayList <Professor> al = new ArrayList<Professor>();
		try {
			 al = daop.listarPorCurso(curso);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de professores por curso pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Professor> listaProfessorPorCursoFrequencia (String curso) throws Exception
	{
		DAOProfessor daop = new DAOProfessor();
		ArrayList <Professor> al = new ArrayList<Professor>();
		try {
			 al = daop.listarPorCurso(curso);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de professores por curso pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Professor> listaProfessorPorDisciplina (String disciplina) throws Exception
	{
		DAOProfessor daop = new DAOProfessor();
		ArrayList <Professor> al = new ArrayList<Professor>();
		try {
			 al = daop.listarPorDisciplina(disciplina);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de professores por disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> listaAlunos () throws Exception
	{
		DAOAluno daoal = new DAOAluno();
		ArrayList <Aluno> al  = new ArrayList<Aluno>();
		try {
			 al = daoal.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de professores pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Usuario> listaUsuarios () throws Exception
	{
		DAOUsuario daou = new DAOUsuario();
		ArrayList<Usuario> al = new ArrayList<Usuario>();
		try {
			 al = daou.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de usuarios pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aviso> listaAvisos () throws Exception
	{
		DAOAviso daoav = new DAOAviso();
		ArrayList<Aviso> al = new ArrayList<Aviso>();
		try {
			 al = daoav.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de avisos pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<PessoaFisica> listaPessoasFisicas () throws Exception
	{
		DAOPessoaFisica daopf = new DAOPessoaFisica();
		ArrayList<PessoaFisica> al = new ArrayList<PessoaFisica>();
		try {
			 al = daopf.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de pessoas físicas pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Disciplina> listaDisciplinas() throws Exception
	{
		DAODisciplina daod = new DAODisciplina();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		try {
			 al = daod.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de disciplinas pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;	
	}
	
	public ArrayList<Disciplina> listaDisciplinaPorCursos(ArrayList<String> cursos) throws Exception
	{
		DAODisciplina daod = new DAODisciplina();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		try {
			 al = daod.listarPorCursos(cursos);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de disciplinas por cursos pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;	
	}
	
	public ArrayList<Disciplina> listaDisciplinaPorCurso(String curso) throws Exception
	{
		DAODisciplina daod = new DAODisciplina();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		try {
			 al = daod.listarPorCurso(curso);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de disciplinas por cursos pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;	
	}

	
	public ArrayList<Disciplina> listaDisciplinasConsulta() throws Exception
	{
		DAODisciplina daod = new DAODisciplina();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		try {
			 al = daod.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de disciplinas pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;	
	}
	public ArrayList<Disciplina> listaDisciplinaProfessores (String nome, String sobrenome) throws Exception
	{
		DAOProfessor daop = new DAOProfessor();
		ArrayList<Disciplina> ds = new ArrayList<Disciplina>();
		try {
			Professor pr = daop.getByNome(nome, sobrenome);
			ds = pr.getDisciplinas();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de disciplina dos professores pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return ds;
		
	}
	
	public ArrayList<Disciplina> listaDisciplinaProfessoresFrequencia (String nome, String sobrenome) throws Exception
	{
		DAOProfessor daop = new DAOProfessor();
		ArrayList<Disciplina> ds = new ArrayList<Disciplina>();
		try {
			Professor pr = daop.getByNome(nome, sobrenome);
			ds = pr.getDisciplinas();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de disciplina dos professores pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return ds;
		
	}
	
	
	public void gravaSalaBanco (Sala sala) throws Exception
	{
		DAOSala daos = new DAOSala();
		try {
			daos.cadastrar(sala);
		}
		catch (Exception ex){
			throw new Exception ("- Erro ao cadastrar sala pelo FlexRemoto\n" + ex.getMessage());

		}
	}
	
	public void computaFalta (Falta falta) throws Exception
	{
		DAOFalta daof = new DAOFalta();
		try {
			daof.cadastrar(falta);
		}
		catch (Exception ex){
			throw new Exception ("- Erro ao computar falta pelo FlexRemoto\n" + ex.getMessage());

		}
	}
	
	public ArrayList<Sala> listaSalas () throws Exception
	{
		DAOSala daos = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daos.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de salas pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> listaSalaPorProfessor (String nome, String sobrenome) throws Exception
	{
		DAOSala daos = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daos.listarPorProfessor(nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de salas por professor pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> listaSalaPorDisciplina (String disciplina) throws Exception
	{
		DAOSala daos = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daos.listarPorDisciplina(disciplina);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de salas por disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	public ArrayList<Sala> listaSalaPorTurma (Integer codigoTurma) throws Exception
	{
		DAOSala daos = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daos.listarPorTurma(codigoTurma);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de salas por professor pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	public String getProfessorSala(String sala) throws Exception
	{
		DAOSala daos = new DAOSala();
		String nome = "";
		try {
			
			Sala s = daos.getByNome(sala); 
			nome = s.getProfessor().getNome(); 
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de nomes de Professor por sala pelo FlexRemoto\n" + ex.getMessage());
		}
		return nome;
	}
	
	public Usuario getUsuario (String nomeUsuario) throws Exception
	{
		DAOUsuario daou = new DAOUsuario();
		Usuario usuario = new Usuario();
		try{
			 usuario = daou.getByNome(nomeUsuario);
			 if (usuario == null)
			 {
				 throw new Exception ("Usuário ou senha incorretos!");
			 }
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de usuarios pelo FlexRemoto\n" + ex.getMessage());
		}
		return usuario;
			
	}
	
	public boolean cadastraAdministrador (Administrador administrador) throws Exception 
	{
		DAOAdministrador daoa = new DAOAdministrador();
		try {
			daoa.cadastrar(administrador);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Administrador\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraAula(Aula aula) throws Exception 
	{
		DAOAula daoa = new DAOAula();
		try {
			daoa.cadastrar(aula);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Aula\n" + ex.getMessage());
		}
		return true;
	}
	
	
	public boolean cadastraProfessor (Professor professor) throws Exception 
	{
		DAOProfessor daopr = new DAOProfessor();
		try {
			daopr.cadastrar(professor);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Professor\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraAluno (Aluno aluno) throws Exception 
	{
		DAOAluno daoal = new DAOAluno();
		try {
			daoal.cadastrar(aluno);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Aluno\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraEndereco (Endereco endereco) throws Exception 
	{
		DAOEndereco daoen = new DAOEndereco();
		try {
			daoen.cadastrar(endereco);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Endereço\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraUsuario (Usuario usuario) throws Exception 
	{
		DAOUsuario daoen = new DAOUsuario();
		try {
			daoen.cadastrar(usuario);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Usuário\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraDisciplina (Disciplina disciplina) throws Exception 
	{
		DAODisciplina daoen = new DAODisciplina();
		try {
			daoen.cadastrar(disciplina);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Disciplina\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraCurso (Curso curso) throws Exception 
	{
		DAOCurso daoen = new DAOCurso();
		try {
			daoen.cadastrar(curso);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Curso\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraTurma (Turma turma) throws Exception 
	{
		DAOTurma daot = new DAOTurma();
		try {
			daot.cadastrar(turma);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Turma\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean cadastraAviso (Aviso aviso) throws Exception 
	{
		DAOAviso daoav = new DAOAviso();
		try {
			daoav.cadastrar(aviso);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar Aviso\n" + ex.getMessage());
		}
		return true;
	}
	
	
	
	public ArrayList<Estado> listaEstados () throws Exception
	{
		DAOEstado daoe = new DAOEstado();
		ArrayList<Estado> al = new ArrayList<Estado>();
		try {
			 al = daoe.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de estados pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<String> listaCidades (String estado) throws Exception
	{
		DAOCidade daoc = new DAOCidade();
		ArrayList <String> alCidades = new ArrayList <String>();
		try {
			alCidades = daoc.listarPorEstado(estado);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de cidades pelo FlexRemoto\n" + ex.getMessage());
		}
		return alCidades;
			
	}
	
	public ArrayList<Endereco> listaEnderecos () throws Exception
	{
		DAOEndereco daoe = new DAOEndereco();
		ArrayList<Endereco> al = new ArrayList<Endereco>();
		try {
			 al = daoe.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de endereços pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}

	
	public ArrayList<Curso> listaCursos () throws Exception
	{
		DAOCurso daoi = new DAOCurso();
		ArrayList<Curso> al = new ArrayList<Curso>();
		try {
			 al = daoi.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de cursos pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Turma> listaTurmas () throws Exception
	{
		DAOTurma daot = new DAOTurma();
		ArrayList<Turma> al = new ArrayList<Turma>();
		try {
			 al = daot.listar();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de turmas pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Turma> listaTurmaPorDisciplina (String disciplina_nome, String professor_nome, String professor_sobrenome) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		ArrayList<Turma> al = new ArrayList<Turma>();
		try {
			 al = daot.listarPorDisciplina(disciplina_nome, professor_nome, professor_sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de turmas por disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Turma> listaTurmaPorDisciplina (String disciplina_nome) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		ArrayList<Turma> al = new ArrayList<Turma>();
		try {
			 al = daot.listarPorDisciplina(disciplina_nome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de turmas por disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Boletim> listaBoletim (Integer turma) throws Exception
	{
		DAOBoletim daob = new DAOBoletim();
		ArrayList<Boletim> al = new ArrayList<Boletim>();
		try {
			 al = daob.listar(turma);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de boletim pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Turma> listaTurmasPorCurso (String curso) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		ArrayList <Turma> al = new ArrayList<Turma>();
		try {
			 al = daot.listarPorCurso(curso);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de turmas por curso pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Turma> listaTurmaPorAluno (String nome, String sobrenome) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		ArrayList <Turma> al = new ArrayList<Turma>();
		try {
			 al = daot.listarPorAluno(nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de turmas por aluno pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Turma> listaTurmaPorProfessor (String nome, String sobrenome) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		ArrayList <Turma> al = new ArrayList<Turma>();
		try {
			 al = daot.listarPorProfessor(nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de turmas por professor pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aula> listaAulasPorTurma (Integer codigoTurma) throws Exception
	{
		DAOAula daot = new DAOAula();
		ArrayList <Aula> al = new ArrayList<Aula>();
		try {
			 al = daot.listarPorTurma(codigoTurma);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de aulas por turma pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> listaAlunosPorTurma (Integer codigoTurma) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		Turma turma = new Turma();
		ArrayList <Aluno> al = new ArrayList<Aluno>();
		try {
			 turma = daot.getByCodigo(codigoTurma);
			 al = turma.getAluno();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de alunos por turma pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> listaAlunosPorTurmaRendimento (Integer codigoTurma) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		Turma turma = new Turma();
		ArrayList <Aluno> al = new ArrayList<Aluno>();
		try {
			 turma = daot.getByCodigo(codigoTurma);
			 al = turma.getAluno();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de alunos por turma pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> listaAlunoPorCurso (String curso) throws Exception
	{
		DAOAluno daoal = new DAOAluno();
		ArrayList <Aluno> al = new ArrayList<Aluno>();
		try {
			 al = daoal.listarPorCurso(curso);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de alunos por curso pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> listaAlunoPorProfessor (String nome, String sobrenome) throws Exception
	{
		DAOAluno daoal = new DAOAluno();
		ArrayList <Aluno> al = new ArrayList<Aluno>();
		try {
			 al = daoal.listarPorProfessor(nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de alunos por curso pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> listaAlunosPorDisciplina (String disciplina) throws Exception
	{
		DAOAluno daoal = new DAOAluno();
		ArrayList <Aluno> al = new ArrayList<Aluno>();
		try {
			 al = daoal.listarPorDisciplina(disciplina);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de alunos por disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> listaAlunosPorTurma (String ano, String curso, String professor_nome, String professor_sobrenome, String disciplina) throws Exception
	{
		DAOTurma daot = new DAOTurma();
		Turma turma = new Turma();
		ArrayList <Aluno> al = new ArrayList<Aluno>();
		try {
			 turma = daot.getByNome(ano, curso, professor_nome, professor_sobrenome, disciplina);
			 al = turma.getAluno();
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de alunos por turma pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltaBoletim (Integer codigoBoletim) throws Exception
	{
		DAOFalta daof = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daof.listarPorBoletim(codigoBoletim);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por turma pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorTurma (Integer codigoTurma) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorTurma(codigoTurma);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por turma pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorCurso (String curso) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorCurso(curso);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por curso pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorData (Date de, Date ate) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorData(de, ate);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por data pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorDataAluno (Date de, Date ate, String nome, String sobrenome) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorDataAluno(de, ate, nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por data pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorDataProfessor (Date de, Date ate, String nome, String sobrenome) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorDataProfessor(de, ate, nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por data pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorDataDisciplina (Date de, Date ate, String disciplina) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorDataDisciplina(de, ate, disciplina);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por data pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorProfessor (String nome, String sobrenome) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorProfessor(nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por professor pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorAluno (String nome, String sobrenome) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorAluno(nome, sobrenome);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por aluno pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Falta> listaFaltasPorDisciplina (String disciplina) throws Exception
	{
		DAOFalta daob = new DAOFalta();
		ArrayList <Falta> al = new ArrayList<Falta>();
		try {
			 al = daob.listarPorDisciplina(disciplina);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de faltas por professor pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Professor> buscaProfessor (String valor) throws Exception
	{
		DAOProfessor daop = new DAOProfessor();
		ArrayList<Professor> al = new ArrayList<Professor>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar professores pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Curso> buscaCurso (String valor) throws Exception
	{
		DAOCurso daop = new DAOCurso();
		ArrayList<Curso> al = new ArrayList<Curso>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar cursos pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Administrador> buscaAdministrador (String valor) throws Exception
	{
		DAOAdministrador daop = new DAOAdministrador();
		ArrayList<Administrador> al = new ArrayList<Administrador>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar administradores pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aluno> buscaAluno (String valor) throws Exception
	{
		DAOAluno daop = new DAOAluno();
		ArrayList<Aluno> al = new ArrayList<Aluno>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar alunos pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	
	public ArrayList<Endereco> buscaEndereco (String valor) throws Exception
	{
		DAOEndereco daop = new DAOEndereco();
		ArrayList<Endereco> al = new ArrayList<Endereco>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar endereço pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Usuario> buscaUsuario (String valor) throws Exception
	{
		DAOUsuario daop = new DAOUsuario();
		ArrayList<Usuario> al = new ArrayList<Usuario>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar usuário pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Disciplina> buscaDisciplina (String valor) throws Exception
	{
		DAODisciplina daop = new DAODisciplina();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> buscaSala (Boolean comSenha, Boolean travada ) throws Exception
	{
		DAOSala daop = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daop.buscarSala(comSenha, travada);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar sala pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> buscaSala (String valor) throws Exception
	{
		DAOSala daop = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar sala pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> buscaSalaT (Boolean travada ) throws Exception
	{
		DAOSala daop = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daop.buscarSalaT(travada);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar sala pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> buscaSalaT1 (String valor, Boolean travada ) throws Exception
	{
		DAOSala daop = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daop.buscarSalaT1(valor, travada);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar sala pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> buscaSalaS (Boolean comSenha ) throws Exception
	{
		DAOSala daop = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daop.buscarSalaS(comSenha);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar sala pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> buscaSalaS1 (String valor, Boolean comSenha ) throws Exception
	{
		DAOSala daop = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daop.buscarSalaT1(valor, comSenha);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar sala pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Sala> buscaSala (String valor, Boolean comSenha, Boolean travada ) throws Exception
	{
		DAOSala daop = new DAOSala();
		ArrayList<Sala> al = new ArrayList<Sala>();
		try {
			 al = daop.buscarSala(valor, comSenha, travada);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar sala pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public ArrayList<Aviso> buscaAviso (String valor) throws Exception
	{
		DAOAviso daop = new DAOAviso();
		ArrayList<Aviso> al = new ArrayList<Aviso>();
		try {
			 al = daop.buscar(valor);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao buscar aviso pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return al;
			
	}
	
	public Sala alteraSala (Sala sala) throws Exception
	{
		DAOSala daos = new DAOSala();
		try{
			 daos.alterar(sala);
		}
		catch (Exception ex) {
			throw new Exception ("- Erro ao transferir listagem de usuarios pelo FlexRemoto\n" + ex.getMessage());
		}
		return sala;
	}
	
	public boolean gravarLog (Sala sala) throws Exception
	{
		DAOSala daos = new DAOSala();
		try {
			daos.gravarLog(sala);
		}
		catch (Exception ex) {
			
			throw new Exception ("- Erro ao gravar log pelo FlexRemoto\n" + ex.getMessage());
		}
		
		return true;
	}
	
	public boolean alteraProfessor (Professor professor) throws Exception 
	{
		DAOProfessor daopr = new DAOProfessor();
		try {
			daopr.alterar(professor);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Professor pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean alteraAdministrador (Administrador administrador) throws Exception 
	{
		DAOAdministrador daopr = new DAOAdministrador();
		try {
			daopr.alterar(administrador);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Administrador pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean alteraAluno (Aluno aluno) throws Exception 
	{
		DAOAluno daopr = new DAOAluno();
		try {
			daopr.alterar(aluno);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Aluno pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	
	public boolean alteraEndereco (Endereco endereco) throws Exception 
	{
		DAOEndereco daopr = new DAOEndereco();
		try {
			daopr.alterar(endereco);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Endereço pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean alteraUsuario (Usuario usuario) throws Exception 
	{
		DAOUsuario daopr = new DAOUsuario();
		try {
			daopr.alterar(usuario);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Usuário pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean aAvisoplina (Disciplina disciplina) throws Exception 
	{
		DAODisciplina daopr = new DAODisciplina();
		try {
			daopr.alterar(disciplina);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean alteraBoletim (Boletim boletim) throws Exception 
	{
		DAOBoletim daob = new DAOBoletim();
		try {
			daob.alterar(boletim);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Boletim\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean alteraAviso (Aviso aviso) throws Exception 
	{
		DAOAviso daoa = new DAOAviso();
		try {
			daoa.alterar(aviso);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Aviso\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean alteraCurso (Curso curso) throws Exception 
	{
		DAOCurso daoa = new DAOCurso();
		try {
			daoa.alterar(curso);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Curso\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean alteraDisciplina (Disciplina disciplina) throws Exception 
	{
		DAODisciplina daoa = new DAODisciplina();
		try {
			daoa.alterar(disciplina);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao alterar Disciplina\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaProfessor (Professor professor) throws Exception 
	{
		DAOProfessor daopr = new DAOProfessor();
		try {
			daopr.deletar(professor);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Professor pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaAdministrador (Administrador administrador) throws Exception 
	{
		DAOAdministrador daopr = new DAOAdministrador();
		try {
			daopr.deletar(administrador);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Administrador pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaAluno (Aluno aluno) throws Exception 
	{
		DAOAluno daopr = new DAOAluno();
		try {
			daopr.deletar(aluno);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Aluno pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	
	public boolean deletaEndereco (Endereco endereco) throws Exception 
	{
		DAOEndereco daopr = new DAOEndereco();
		try {
			daopr.deletar(endereco);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Endereço pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaUsuario (Usuario usuario) throws Exception 
	{
		DAOUsuario daopr = new DAOUsuario();
		try {
			daopr.deletar(usuario);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Usuário pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaDisciplina (Disciplina disciplina) throws Exception 
	{
		DAODisciplina daopr = new DAODisciplina();
		try {
			daopr.deletar(disciplina);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Disciplina pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaCurso (Curso curso) throws Exception 
	{
		DAOCurso daopr = new DAOCurso();
		try {
			daopr.deletar(curso);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Curso pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaSala (Sala sala) throws Exception 
	{
		DAOSala daopr = new DAOSala();
		try {
			daopr.deletar(sala);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Sala pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public boolean deletaAviso (Aviso aviso) throws Exception 
	{
		DAOAviso daopr = new DAOAviso();
		try {
			daopr.deletar(aviso);
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Aviso pelo FlexRemoto\n" + ex.getMessage());
		}
		return true;
	}
	
	public String criaRelatorio () throws Exception 
	{
		String reportXML = "";
		HashMap<String, Object> hm = new HashMap<>();
		/*hm.put("titulo", "falta");
		hm.put("tipo_filtro", "turma_codigo");
		hm.put("filtro",1);*/
		
		try {
			if (JasperReportsFacade.getReportPageCount("D:/Meus Documentos/TCC/Projeto/CarpeDiem/CarpeDiem/WEB-INF/reports/teste.jasper", hm, DAO.getConnection()) > 0)
			{
				reportXML = JasperReportsFacade.exportToXMLReport("D:/Meus Documentos/TCC/Projeto/CarpeDiem/CarpeDiem/WEB-INF/reports/teste.jasper", hm, DAO.getConnection());
				
			}
			else
			{
				throw new Exception ();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception ("- Erro ao gerar Relatório\n" + e.getMessage());
		}
		
		return reportXML;
	}
	
	public Boletim montaRendimento (int codigoTurma, int codigoAluno) throws Exception
	{
		DAOBoletim daob = new DAOBoletim ();
		Boletim b = new Boletim();
		try {
			b = daob.getByNome(codigoTurma, codigoAluno);
		}
		catch(Exception e) {
			throw new Exception ("- Erro ao gerar Rendimento\n" + e.getMessage());
		}
		
		return b;
	}
}
