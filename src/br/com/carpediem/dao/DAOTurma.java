package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Aluno;
import br.com.carpediem.modelo.Curso;
import br.com.carpediem.modelo.Disciplina;
import br.com.carpediem.modelo.Professor;
import br.com.carpediem.modelo.Turma;

public class DAOTurma extends DAO implements IDAO {

private Connection conn;
	
	public DAOTurma() {
		super();
	}
	
	public Turma getByNome(String ano, String curso, String professor_nome, String professor_sobrenome, String disciplina) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlTurma = "SELECT * FROM turma t WHERE (t.ano = ? AND t.curso_codigo = (SELECT c.codigo FROM curso c WHERE c.nome = ?) AND professor_codigo = (SELECT pr.codigo FROM professor pr INNER JOIN pessoa_fisica pf ON pf.codigo = pr.pessoa_fisica_codigo WHERE pf.nome = ? AND pf.sobrenome = ?) AND disciplina_codigo = (SELECT d.codigo FROM disciplina d WHERE d.nome = ?))";
		
		String sqlAlunos = "SELECT al.codigo as codigo_aluno, pf.codigo as codigo_pessoa_fisica, p.codigo as codigo_pessoa, pf.nome as aluno_nome, pf.sobrenome as aluno_sobrenome, pf.cpf, pf.rg, pf.sexo, pf.data_nascimento,  pf.estado_civil, p.email, p.telefone FROM aluno al, turma t, aluno_turma at, pessoa_fisica pf, pessoa p WHERE (at.ativo = true AND at.aluno_codigo = al.codigo and at.turma_codigo = t.codigo and al.pessoa_fisica_codigo = pf.codigo and pf.pessoa_codigo = p.codigo and t.ano = ? and t.curso_codigo = (SELECT c.codigo FROM curso c WHERE c.nome = ?) AND t.professor_codigo = (SELECT pr.codigo FROM professor pr INNER JOIN pessoa_fisica pf ON pf.codigo = pr.pessoa_fisica_codigo WHERE pf.nome = ? AND pf.sobrenome = ?) AND disciplina_codigo = (SELECT d.codigo FROM disciplina d WHERE d.nome = ?))";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psTurma = conn.prepareStatement(sqlTurma);
			psTurma.setString(1, ano);
			psTurma.setString(2, curso);
			psTurma.setString(3, professor_nome);
			psTurma.setString(4, professor_sobrenome);
			psTurma.setString(5, disciplina);
			ResultSet rsTurma = psTurma.executeQuery();
			
			PreparedStatement psAlunos = conn.prepareStatement(sqlAlunos);
			psAlunos.setString(1, ano);
			psAlunos.setString(2, curso);
			psAlunos.setString(3, professor_nome);
			psAlunos.setString(4, professor_sobrenome);
			psAlunos.setString(5, disciplina);
			ResultSet rsAlunos = psAlunos.executeQuery();

			if (rsTurma.next())
			{
				Turma turma = new Turma();
				Curso curso_ = new Curso();
				DAOCurso daoc = new DAOCurso();
				curso_ = daoc.getByNome(curso);
				Professor professor_ = new Professor();
				DAOProfessor daopr = new DAOProfessor();
				professor_ = daopr.getByNome(professor_nome, professor_sobrenome);
				Disciplina disciplina_ = new Disciplina();
				DAODisciplina daod = new DAODisciplina();
				disciplina_ = daod.getByNome(disciplina);
				turma.setCodigoTurma(rsTurma.getInt("codigo"));
				turma.setAno(rsTurma.getString("ano"));
				turma.setCurso(curso_);
				turma.setProfessor(professor_);
				turma.setDisciplina(disciplina_);
				turma.setAtivo(rsTurma.getBoolean("ativo"));
				
				
				ArrayList<Aluno> al = new ArrayList<Aluno>();
				while (rsAlunos.next())
				{
					Aluno aluno = new Aluno();
					DAOAluno daoal = new DAOAluno();
					aluno = daoal.getByCodigo(rsAlunos.getInt("codigo_aluno"));
					al.add(aluno);
					
				}
				turma.setAluno(al);
				
				rsTurma.close();
				psTurma.close();
				rsAlunos.close();
				psAlunos.close();
				return turma;
			}
			else 
			{
				rsTurma.close();
				psTurma.close();
				rsAlunos.close();
				psAlunos.close();
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Turma por nome!\n" + ex.getMessage()); 
		}
	}
	
	public Turma getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlTurma = "SELECT * FROM turma  WHERE (codigo = ?)";
		
		String sqlAlunos = "SELECT al.codigo as codigo_aluno, pf.codigo as codigo_pessoa_fisica, p.codigo as codigo_pessoa, pf.nome as aluno_nome, pf.sobrenome as aluno_sobrenome, pf.cpf, pf.rg, pf.sexo, pf.data_nascimento,  pf.estado_civil, p.email, p.telefone FROM aluno al, turma t, aluno_turma at, pessoa_fisica pf, pessoa p WHERE (at.aluno_codigo = al.codigo and at.turma_codigo = t.codigo and al.pessoa_fisica_codigo = pf.codigo and pf.pessoa_codigo = p.codigo and at.ativo = true and t.codigo = ?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psTurma = conn.prepareStatement(sqlTurma);
			psTurma.setInt(1, codigo);
			ResultSet rsTurma = psTurma.executeQuery();
			
			PreparedStatement psAlunos = conn.prepareStatement(sqlAlunos);
			psAlunos.setInt(1, codigo);
			ResultSet rsAlunos = psAlunos.executeQuery();

			if (rsTurma.next())
			{
				Turma turma = new Turma();
				Curso curso_ = new Curso();
				DAOCurso daoc = new DAOCurso();
				curso_ = daoc.getByCodigo(rsTurma.getInt("curso_codigo"));
				Professor professor_ = new Professor();
				DAOProfessor daopr = new DAOProfessor();
				professor_ = daopr.getByCodigo(rsTurma.getInt("professor_codigo"));
				Disciplina disciplina_ = new Disciplina();
				DAODisciplina daod = new DAODisciplina();
				disciplina_ = daod.getByCodigo(rsTurma.getInt("disciplina_codigo"));
				turma.setCodigoTurma(rsTurma.getInt("codigo"));
				turma.setAno(rsTurma.getString("ano"));
				turma.setCurso(curso_);
				turma.setProfessor(professor_);
				turma.setDisciplina(disciplina_);
				turma.setAtivo(rsTurma.getBoolean("ativo"));
				ArrayList<Aluno> al = new ArrayList<Aluno>();
				
				Aluno aluno = new Aluno();
				while (rsAlunos.next())
				{
					
					DAOAluno daoal = new DAOAluno();
					aluno = daoal.getByCodigo(rsAlunos.getInt("codigo_aluno"));
					al.add(aluno);
					
				}
				turma.setAluno(al);
				
				rsTurma.close();
				psTurma.close();
				rsAlunos.close();
				psAlunos.close();
				return turma;
			}
			else 
			{
				rsTurma.close();
				psTurma.close();
				rsAlunos.close();
				psAlunos.close();
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Turma por código!\n" + ex.getMessage()); 
		}
	}
	
	public Turma getPorSala(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlTurma = "SELECT * FROM turma t WHERE (t.codigo = (SELECT s.turma_codigo FROM sala s WHERE s.codigo = ?))";
		
		String sqlAlunos = "SELECT DISTINCT al.codigo as codigo_aluno, pf.codigo as codigo_pessoa_fisica, p.codigo as codigo_pessoa, pf.nome as aluno_nome, pf.sobrenome as aluno_sobrenome, pf.cpf, pf.rg, pf.sexo, pf.data_nascimento,  pf.estado_civil, p.email, p.telefone FROM aluno al, turma t, aluno_turma at, pessoa_fisica pf, pessoa p, sala s WHERE (at.aluno_codigo = al.codigo and at.turma_codigo = (SELECT t.codigo FROM turma t WHERE (t.codigo = (SELECT s.turma_codigo FROM sala s WHERE s.codigo = ?))) and al.pessoa_fisica_codigo = pf.codigo and pf.pessoa_codigo = p.codigo and at.ativo = true and t.codigo = (SELECT t.codigo FROM turma t WHERE (t.codigo = (SELECT s.turma_codigo FROM sala s WHERE s.codigo = ?))))";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psTurma = conn.prepareStatement(sqlTurma);
			psTurma.setInt(1, codigo);
			ResultSet rsTurma = psTurma.executeQuery();
			
			PreparedStatement psAlunos = conn.prepareStatement(sqlAlunos);
			psAlunos.setInt(1, codigo);
			psAlunos.setInt(2, codigo);
			ResultSet rsAlunos = psAlunos.executeQuery();

			if (rsTurma.next())
			{
				Turma turma = new Turma();
				Curso curso_ = new Curso();
				DAOCurso daoc = new DAOCurso();
				curso_ = daoc.getByCodigo(rsTurma.getInt("curso_codigo"));
				Professor professor_ = new Professor();
				DAOProfessor daopr = new DAOProfessor();
				professor_ = daopr.getByCodigo(rsTurma.getInt("professor_codigo"));
				Disciplina disciplina_ = new Disciplina();
				DAODisciplina daod = new DAODisciplina();
				disciplina_ = daod.getByCodigo(rsTurma.getInt("disciplina_codigo"));
				turma.setCodigoTurma(rsTurma.getInt("codigo"));
				turma.setAno(rsTurma.getString("ano"));
				turma.setCurso(curso_);
				turma.setProfessor(professor_);
				turma.setDisciplina(disciplina_);
				turma.setAtivo(rsTurma.getBoolean("ativo"));
				ArrayList<Aluno> al = new ArrayList<Aluno>();
				
				Aluno aluno = new Aluno();
				while (rsAlunos.next())
				{
					
					DAOAluno daoal = new DAOAluno();
					aluno = daoal.getByCodigo(rsAlunos.getInt("codigo_aluno"));
					al.add(aluno);
					
				}
				turma.setAluno(al);
				
				rsTurma.close();
				psTurma.close();
				rsAlunos.close();
				psAlunos.close();
				return turma;
			}
			else 
			{
				rsTurma.close();
				psTurma.close();
				rsAlunos.close();
				psAlunos.close();
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Turma por código!\n" + ex.getMessage()); 
		}
	}
		
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM turma t WHERE (ano = ? AND curso_codigo = (SELECT c.codigo FROM curso c WHERE c.nome = ?) AND professor_codigo = (SELECT pr.codigo FROM professor pr INNER JOIN pessoa_fisica pf ON pf.codigo = pr.pessoa_fisica_codigo WHERE pf.nome = ? AND pf.sobrenome = ?) AND disciplina_codigo = (SELECT d.codigo FROM disciplina d WHERE d.nome = ?))");
			ps.setString(1, ((Turma) param).getAno());
			ps.setString(2, ((Turma) param).getCurso().getNome());
			ps.setString(3, ((Turma) param).getProfessor().getNome());
			ps.setString(4, ((Turma) param).getProfessor().getSobrenome());
			ps.setString(5, ((Turma) param).getDisciplina().getNome());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Turma turma = new Turma();
				DAOTurma dao = new DAOTurma();
				turma = dao.getByNome(((Turma) param).getAno(), ((Turma) param).getCurso().getNome(), ((Turma) param).getProfessor().getNome(), ((Turma) param).getProfessor().getSobrenome(), ((Turma) param).getDisciplina().getNome());
				Curso curso = new Curso();
				DAOCurso daoc = new DAOCurso();
				curso = daoc.getByNome(((Turma) param).getCurso().getNome());
				Professor professor = new Professor();
				DAOProfessor daopr = new DAOProfessor();
				professor = daopr.getByNome(((Turma) param).getProfessor().getNome(), ((Turma) param).getProfessor().getSobrenome());
				Disciplina disciplina = new Disciplina();
				DAODisciplina daod = new DAODisciplina();
				disciplina = daod.getByNome(((Turma) param).getDisciplina().getNome());
				
				try {
					conn.setAutoCommit(false);
					PreparedStatement psTurma = conn.prepareStatement("UPDATE turma SET ano = ?, curso_codigo = ?, professor_codigo = ?, disciplina_codigo = ?,  ativo = true WHERE (codigo = ?)");
					PreparedStatement psAlunoTurmaDelete = conn.prepareStatement("UPDATE aluno_turma SET ativo = false WHERE (turma_codigo = ? )");
					PreparedStatement psAlunoTurma = conn.prepareStatement("UPDATE aluno_turma SET ativo = true WHERE (turma_codigo = ? AND aluno_codigo = ?)");
					PreparedStatement psAlunoSelect = conn.prepareStatement("SELECT al.codigo FROM aluno al INNER JOIN pessoa_fisica pf ON pf.codigo = al.pessoa_fisica_codigo  WHERE pf.nome = ? AND pf.sobrenome = ?");
					
					psTurma.setString(1, ((Turma) param).getAno());
					psTurma.setInt(2, curso.getCodigoCurso());
					psTurma.setInt(3, professor.getCodigoProfessor());
					psTurma.setInt(4, disciplina.getCodigoDisciplina());
					psTurma.setInt(5, turma.getCodigoTurma());
					
					psTurma.executeUpdate();
					
					psAlunoTurmaDelete.setInt(1, turma.getCodigoTurma());
					psAlunoTurmaDelete.executeUpdate();
					
					ResultSet rsExiste;
					
					for (int cont = 0; cont <= (((Turma) param).getAluno().size() - 1); cont++)
					{

						psAlunoTurma.setInt(1, turma.getCodigoTurma());
						
						psAlunoSelect.setString(1, ((Turma) param).getAluno().get(cont).getNome());
						psAlunoSelect.setString(2, ((Turma) param).getAluno().get(cont).getSobrenome());
						rsExiste = psAlunoSelect.executeQuery();
						rsExiste.next();
						psAlunoTurma.setInt(2, rsExiste.getInt("codigo"));
						
						psAlunoTurma.execute();
						
					}
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar turma\n" + ex.getMessage());
				}
			
			}
			else {
				PreparedStatement psTurma = conn.prepareStatement("INSERT INTO turma (ano, curso_codigo, professor_codigo, disciplina_codigo, ativo) VALUES (?, ?, ?, ?, ?)");
				PreparedStatement psAlunos = conn.prepareStatement("INSERT INTO aluno_turma (turma_codigo, aluno_codigo) VALUES ((SELECT MAX(t.codigo) FROM turma t), ?)");
				PreparedStatement psAlunoSelect = conn.prepareStatement("SELECT al.codigo FROM aluno al INNER JOIN pessoa_fisica pf ON pf.codigo = al.pessoa_fisica_codigo  WHERE pf.nome = ? AND pf.sobrenome = ?");
				
				Curso curso = new Curso();
				DAOCurso daoc = new DAOCurso();
				curso = daoc.getByNome(((Turma) param).getCurso().getNome());
				Professor professor = new Professor();
				DAOProfessor daopr = new DAOProfessor();
				professor = daopr.getByNome(((Turma) param).getProfessor().getNome(), ((Turma) param).getProfessor().getSobrenome());
				Disciplina disciplina = new Disciplina();
				DAODisciplina daod = new DAODisciplina();
				disciplina = daod.getByNome(((Turma) param).getDisciplina().getNome());
				
				
				psTurma.setString(1, ((Turma) param).getAno());
				psTurma.setInt(2, curso.getCodigoCurso());
				psTurma.setInt(3, professor.getCodigoProfessor());
				psTurma.setInt(4, disciplina.getCodigoDisciplina());
				psTurma.setBoolean(5, ((Turma) param).getAtivo());
				psTurma.executeUpdate();
				
				
				for (int cont = 0; cont <= (((Turma) param).getAluno().size() - 1); cont++)
				{
					psAlunoSelect.setString(1, ((Turma) param).getAluno().get(cont).getNome());
					psAlunoSelect.setString(2, ((Turma) param).getAluno().get(cont).getSobrenome());
					rs = psAlunoSelect.executeQuery();
					rs.next();
					psAlunos.setInt(1, rs.getInt("codigo"));
					psAlunos.executeUpdate();
				}
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Turma\n" + ex.getMessage());
		}


	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Turma> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Turma> al = new ArrayList<Turma>();
		Turma turma = new Turma();
		
		try {
			String sql = "SELECT * FROM turma WHERE ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			
		
				while (rs.next())
				{
					Curso curso = new Curso();
					DAOCurso daoc = new DAOCurso();
					curso = daoc.getByCodigo(rs.getInt("curso_codigo"));
					Professor professor = new Professor();
					DAOProfessor daop = new DAOProfessor();
					professor = daop.getByCodigo(rs.getInt("professor_codigo"));
					Disciplina disciplina = new Disciplina();
					DAODisciplina daod = new DAODisciplina();
					disciplina = daod.getByCodigo(rs.getInt("disciplina_codigo"));
					
					turma = getByNome(rs.getString("ano"), curso.getNome(), professor.getNome(), professor.getSobrenome(), disciplina.getNome());
					al.add(turma);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar turmas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Turma> listarPorDisciplina(String disciplina_nome, String professor_nome, String professor_sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Turma> al = new ArrayList<Turma>();
		Turma turma = new Turma();
		
		try {
			String sql = "SELECT * FROM turma WHERE disciplina_codigo = (SELECT codigo FROM disciplina WHERE nome = ?) AND professor_codigo = (SELECT pr.codigo FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo WHERE pf.nome = ? AND pf.sobrenome = ?) AND ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, disciplina_nome);
			ps.setString(2, professor_nome);
			ps.setString(3, professor_sobrenome);
			ResultSet rs = ps.executeQuery();
		
			Curso curso = new Curso();
			DAOCurso daoc = new DAOCurso();
			Professor professor = new Professor();
			DAOProfessor daop = new DAOProfessor();
			Disciplina disciplina = new Disciplina();
			DAODisciplina daod = new DAODisciplina();
				while (rs.next())
				{
					
					curso = daoc.getByCodigo(rs.getInt("curso_codigo"));
					
					professor = daop.getByCodigo(rs.getInt("professor_codigo"));
					
					disciplina = daod.getByCodigo(rs.getInt("disciplina_codigo"));
					
					turma = getByNome(rs.getString("ano"), curso.getNome(), professor.getNome(), professor.getSobrenome(), disciplina.getNome());
					al.add(turma);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar turmas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Turma> listarPorDisciplina(String disciplina_nome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Turma> al = new ArrayList<Turma>();
		Turma turma = new Turma();
		
		try {
			String sql = "SELECT * FROM turma WHERE disciplina_codigo = (SELECT codigo FROM disciplina WHERE nome = ?) AND ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, disciplina_nome);
			ResultSet rs = ps.executeQuery();
		
			Curso curso = new Curso();
			DAOCurso daoc = new DAOCurso();
			Professor professor = new Professor();
			DAOProfessor daop = new DAOProfessor();
			Disciplina disciplina = new Disciplina();
			DAODisciplina daod = new DAODisciplina();
				while (rs.next())
				{
					
					curso = daoc.getByCodigo(rs.getInt("curso_codigo"));
					
					professor = daop.getByCodigo(rs.getInt("professor_codigo"));
					
					disciplina = daod.getByCodigo(rs.getInt("disciplina_codigo"));
					
					turma = getByNome(rs.getString("ano"), curso.getNome(), professor.getNome(), professor.getSobrenome(), disciplina.getNome());
					al.add(turma);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar turmas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Turma> listarPorCurso(String curso) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Turma> al = new ArrayList<Turma>();
		Turma turma = new Turma();
		
		try {
			String sql = "SELECT t.ano, c.nome as curso_nome, pf.nome as professor_nome, pf.sobrenome, d.nome as disciplina_nome FROM turma t INNER JOIN curso c ON t.curso_codigo = c.codigo INNER JOIN disciplina d ON t.disciplina_codigo = d.codigo INNER JOIN professor pr ON t.professor_codigo = pr.codigo  INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo WHERE (t.ativo = true AND t.curso_codigo = (SELECT codigo FROM curso WHERE (nome = ?)))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, curso);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				turma = getByNome(rs.getString("ano"), rs.getString("curso_nome"), rs.getString("professor_nome"), rs.getString("sobrenome"), rs.getString("disciplina_nome"));
				al.add(turma);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar turmas por curso\n" + ex.getMessage());
			}
		
			return al;

		}
	
	public ArrayList<Turma> listarPorAluno(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Turma> al = new ArrayList<Turma>();
		Turma turma = new Turma();
		
		try {
			String sql = "SELECT ano, c.nome as curso_nome, d.nome as disciplina_nome, pf.nome as professor_nome, pf.sobrenome  FROM turma t INNER JOIN curso c ON t.curso_codigo = c.codigo INNER JOIN disciplina d ON t.disciplina_codigo = d.codigo INNER JOIN professor pr ON t.professor_codigo = pr.codigo INNER JOIN pessoa_fisica pf ON pf.codigo = pr.pessoa_fisica_codigo INNER JOIN aluno_turma atur ON atur.turma_codigo = t.codigo WHERE (atur.aluno_codigo = (SELECT al.codigo FROM aluno al INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo WHERE pf.nome = ? AND pf.sobrenome = ?))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, sobrenome);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				turma = getByNome(rs.getString("ano"), rs.getString("curso_nome"), rs.getString("professor_nome"), rs.getString("sobrenome"), rs.getString("disciplina_nome"));
				al.add(turma);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar turmas por aluno\n" + ex.getMessage());
			}
		
			return al;

		}
	
	public ArrayList<Turma> listarPorProfessor(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Turma> al = new ArrayList<Turma>();
		Turma turma = new Turma();
		
		try {
			String sql = "SELECT ano, c.nome as curso_nome, d.nome as disciplina_nome, pf.nome as professor_nome, pf.sobrenome  FROM turma t INNER JOIN curso c ON t.curso_codigo = c.codigo INNER JOIN disciplina d ON t.disciplina_codigo = d.codigo INNER JOIN professor pr ON t.professor_codigo = pr.codigo INNER JOIN pessoa_fisica pf ON pf.codigo = pr.pessoa_fisica_codigo WHERE (t.professor_codigo = (SELECT pr.codigo FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo WHERE pf.nome = ? AND pf.sobrenome = ?))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, sobrenome);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				turma = getByNome(rs.getString("ano"), rs.getString("curso_nome"), rs.getString("professor_nome"), rs.getString("sobrenome"), rs.getString("disciplina_nome"));
				al.add(turma);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar turmas por aluno\n" + ex.getMessage());
			}
		
			return al;

		}

	@Override
	public ArrayList<Turma> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
