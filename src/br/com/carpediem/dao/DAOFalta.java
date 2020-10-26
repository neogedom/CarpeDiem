package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Aluno;
import br.com.carpediem.modelo.Aula;
import br.com.carpediem.modelo.Curso;
import br.com.carpediem.modelo.Falta;


public class DAOFalta extends DAO implements IDAO {

private Connection conn;
	
	public DAOFalta() {
		super();
	}
	
	public Falta getByNome(Integer aluno_codigo, Integer aula_codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM falta WHERE aluno_codigo = ? AND aula_codigo = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aluno_codigo);
			ps.setInt(2, aula_codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Falta falta = new Falta();
				Aluno aluno = new Aluno();
				DAOAluno daoal = new DAOAluno();
				aluno = daoal.getByCodigo(aluno_codigo);
				Aula aula = new Aula();
				DAOAula daoau = new DAOAula();
				aula = daoau.getByCodigo(aula_codigo);
				
				falta.setCodigoFalta(rs.getInt("codigo"));
				falta.setJustificativa(rs.getString("justificativa"));
				falta.setAluno(aluno);
				falta.setAula(aula);
				rs.close();
				ps.close();
				
				return falta;
			}
			else 
			{
				rs.close();
				ps.close();
				return null;
			}
	
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Falta por nome!\n" + ex.getMessage()); 
		}

	}
	
	public Falta getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM falta WHERE codigo = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Falta falta = new Falta();
				Aluno aluno = new Aluno();
				DAOAluno daoal = new DAOAluno();
				aluno = daoal.getByCodigo(rs.getInt("aluno_codigo"));
				Aula aula = new Aula();
				DAOAula daoau = new DAOAula();
				aula = daoau.getByCodigo(rs.getInt("aula_codigo"));
				
				falta.setCodigoFalta(rs.getInt("codigo"));
				falta.setJustificativa(rs.getString("justificativa"));
				falta.setAluno(aluno);
				falta.setAula(aula);
				rs.close();
				ps.close();
				
				return falta;
			}
			else 
			{
				rs.close();
				ps.close();
				return null;
			}
	
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Falta por nome!\n" + ex.getMessage()); 
		}
	}
	
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
				conn.setAutoCommit(false);
				Aula aula = new Aula();
				DAOAula dao = new DAOAula();
				aula = dao.getByNome(((Falta) param).getAula().getHoraInicial(), ((Falta) param).getAula().getHoraFinal(), ((Falta) param).getAula().getTurma().getCodigoTurma());
				Aluno aluno = new Aluno();
				DAOAluno daoal = new DAOAluno();
				aluno = daoal.getByNome(((Falta) param).getAluno().getNome(), ((Falta) param).getAluno().getSobrenome());
				
				PreparedStatement psFalta = conn.prepareStatement("INSERT INTO falta (aluno_codigo, aula_codigo) VALUES (?, ?)");
				
				psFalta.setInt(1, aluno.getCodigoAluno());
				psFalta.setInt(2, aula.getCodigoAula());
				psFalta.executeUpdate();
				
				
				conn.commit();
				conn.close();
			} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Falta\n" + ex.getMessage());
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
	public ArrayList<Falta> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Falta> listarPorTurma(Integer codigoTurma) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo WHERE (a.turma_codigo = ?)";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigoTurma);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por turma\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Falta> listarPorBoletim(Integer codigoBoletim) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f, aluno_turma atu, aula a WHERE (atu.codigo = ? AND f.aula_codigo = a.codigo AND atu.aluno_codigo = f.aluno_codigo AND atu.turma_codigo = a.turma_codigo)";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigoBoletim);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por turma\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Falta> listarPorCurso(String curso) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo INNER JOIN aluno al ON f.aluno_codigo = al.codigo INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo INNER JOIN turma t ON a.turma_codigo = t.codigo INNER JOIN curso c ON t.curso_codigo = c.codigo WHERE (c.nome = ?) ORDER BY aluno_codigo";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, curso);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por curso\n" + ex.getMessage());
			}
		
			return al;
	}

	@Override
	public ArrayList<Falta> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Falta> listarPorData(java.util.Date de, java.util.Date ate) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo WHERE (a.DATA between ? AND ?) ORDER BY aluno_codigo";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new Date (de.getTime()));
			ps.setDate(2, new Date (ate.getTime()));
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por curso\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Falta> listarPorDataAluno(java.util.Date de, java.util.Date ate, String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo INNER JOIN aluno al ON f.aluno_codigo = al.codigo INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo WHERE ((a.DATA between ? AND ?) AND pf.nome = ? AND pf.sobrenome = ?) ORDER BY aluno_codigo";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new Date (de.getTime()));
			ps.setDate(2, new Date (ate.getTime()));
			ps.setString(3, nome);
			ps.setString(4, sobrenome);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por data\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Falta> listarPorDataProfessor(java.util.Date de, java.util.Date ate, String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo INNER JOIN turma t ON a.turma_codigo = t.codigo INNER JOIN professor pr ON t.professor_codigo = pr.codigo INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo WHERE ((a.DATA between ? AND ?) AND pf.nome = ? AND pf.sobrenome = ?) ORDER BY professor_codigo";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new Date (de.getTime()));
			ps.setDate(2, new Date (ate.getTime()));
			ps.setString(3, nome);
			ps.setString(4, sobrenome);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por data\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Falta> listarPorDataDisciplina(java.util.Date de, java.util.Date ate, String disciplina) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo INNER JOIN turma t ON a.turma_codigo = t.codigo INNER JOIN disciplina d ON t.disciplina_codigo = d.codigo  WHERE ((a.DATA between ? AND ?) AND d.nome = ? ) ORDER BY disciplina_codigo";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new Date (de.getTime()));
			ps.setDate(2, new Date (ate.getTime()));
			ps.setString(3, disciplina);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por data\n" + ex.getMessage());
			}
		
			return al;
	}
	
	
	public ArrayList<Falta> listarPorProfessor(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo INNER JOIN turma t ON a.turma_codigo = t.codigo INNER JOIN professor pr ON t.professor_codigo = pr.codigo INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo WHERE (pf.nome = ? AND pf.sobrenome = ?) ORDER BY aluno_codigo";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, sobrenome);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por professor\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Falta> listarPorAluno(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aluno al ON f.aluno_codigo = al.codigo INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo WHERE (pf.nome = ? AND pf.sobrenome = ?)";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, sobrenome);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por professor\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Falta> listarPorDisciplina(String disciplina) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Falta> al = new ArrayList<Falta>();
		Falta falta = new Falta();
		
		try {
			String sql = "SELECT * FROM falta f INNER JOIN aula a ON f.aula_codigo = a.codigo INNER JOIN turma t ON a.turma_codigo = t.codigo INNER JOIN disciplina d ON t.disciplina_codigo = d.codigo WHERE (d.nome = ?) ORDER BY aluno_codigo";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, disciplina);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					falta = getByNome(rs.getInt("aluno_codigo"), rs.getInt("aula_codigo"));
					al.add(falta);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar faltas por disciplina\n" + ex.getMessage());
			}
		
			return al;
	}
	
	
	
}
