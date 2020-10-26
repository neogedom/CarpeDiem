package br.com.carpediem.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.carpediem.modelo.Sala;
import br.com.carpediem.modelo.Professor;
import br.com.carpediem.modelo.Disciplina;
import br.com.carpediem.modelo.Turma;
import br.com.carpediem.dao.DAOProfessor;
import br.com.carpediem.dao.DAODisciplina;

public class DAOSala extends DAO implements IDAO {

	private Connection conn;
	
	public DAOSala () {
		super();
	}
	
	public Sala getByNome(String nome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, nome, com_senha, travada, professor_codigo, disciplina_codigo, " +
				"turma_codigo, ativo, view, senha, log FROM sala WHERE (nome = ?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Sala sala = new Sala();
				DAOProfessor daop = new DAOProfessor();
				DAODisciplina daod = new DAODisciplina();
				DAOTurma daot = new DAOTurma();
				Professor professor = daop.getByCodigo(rs.getInt("professor_codigo"));
				Disciplina disciplina = daod.getByCodigo(rs.getInt("disciplina_codigo"));
				Turma turma = daot.getByCodigo(rs.getInt("turma_codigo"));
				sala.setcodigoSala(rs.getInt("codigo"));
				sala.setNome(rs.getString("nome"));
				sala.setComSenha(rs.getBoolean("com_senha"));
				sala.setTravada(rs.getBoolean("travada"));
				sala.setProfessor(professor);
				sala.setDisciplina(disciplina);
				sala.setAtivo(rs.getBoolean("ativo"));
				sala.setSenha(rs.getString("senha"));
				sala.setTurma(turma);
				sala.setView(rs.getString("view"));
				sala.setLog(rs.getString("log"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return sala;
			}
			else 
			{
				rs.close();
				ps.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Sala por nome!\n" + ex.getMessage()) ;
		}

	}
	
	public Sala getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, nome, com_senha, travada, professor_codigo, disciplina_codigo, " +
				"turma_codigo, ativo, view, log FROM sala WHERE (codigo = ?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Sala sala = new Sala();
				DAOProfessor daop = new DAOProfessor();
				DAODisciplina daod = new DAODisciplina();
				DAOTurma daot = new DAOTurma();
				Professor professor = daop.getByCodigo(rs.getInt("professor_codigo"));
				Disciplina disciplina = daod.getByCodigo(rs.getInt("disciplina_codigo"));
				Turma turma = daot.getByCodigo(rs.getInt("turma_codigo"));
				sala.setcodigoSala(rs.getInt("codigo"));
				sala.setNome(rs.getString("nome"));
				sala.setComSenha(rs.getBoolean("com_senha"));
				sala.setTravada(rs.getBoolean("travada"));
				sala.setProfessor(professor);
				sala.setDisciplina(disciplina);
				sala.setTurma(turma);
				sala.setAtivo(rs.getBoolean("ativo"));
				sala.setView(rs.getString("view"));
				sala.setLog(rs.getString("log"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return sala;
			}
			else 
			{
				rs.close();
				ps.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Sala por codigo!\n" + ex.getMessage()) ;
		}

	}
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM sala WHERE nome = ?");
			ps.setString(1, ((Sala) param).getNome());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				alterar (((Sala) param));
			
			}
			else {
				ps = conn.prepareStatement("INSERT INTO sala (nome, com_senha, travada, professor_codigo, disciplina_codigo, ativo, view, turma_codigo, senha) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, ((Sala) param).getNome());
				ps.setBoolean(2, ((Sala) param).getComSenha());
				ps.setBoolean(3, ((Sala) param).getTravada());
				ps.setInt(4, ((Sala) param).getProfessor().getCodigoProfessor());
				ps.setInt(5, ((Sala) param).getDisciplina().getCodigoDisciplina());
				ps.setBoolean(6, ((Sala) param).getAtivo());
				ps.setString(7, ((Sala) param).getView());
				ps.setInt(8, ((Sala) param).getTurma().getCodigoTurma());
				ps.setString(9, ((Sala) param).getSenha());
				ps.executeUpdate();
				conn.close();
			}
		} catch (Exception ex)
		{
			throw new Exception ("- Erro ao cadastrar sala\n" + ex.getMessage());
		}

	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement psSala = conn.prepareStatement("UPDATE sala SET ativo = false WHERE (codigo = ?)");
			psSala.setInt(1, ((Sala) param).getCodigoSala());
			psSala.executeUpdate();
			
			
			conn.commit();
			conn.close();
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Sala\n" + ex.getMessage());
		}
	}

	@Override
	public ArrayList<Sala> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT nome FROM sala WHERE ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar salas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> listarPorProfessor(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT nome FROM sala WHERE professor_codigo = (SELECT pr.codigo FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo WHERE pf.nome = ? AND pf.sobrenome = ?) AND  ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, sobrenome);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar salas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> listarPorDisciplina(String disciplina) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT nome FROM sala WHERE disciplina_codigo = (SELECT codigo FROM disciplina  WHERE nome = ? ) AND  ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, disciplina);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar salas por disciplina\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> listarPorTurma(Integer codigoTurma) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT nome FROM sala WHERE turma_codigo = (SELECT codigo FROM turma  WHERE codigo = ?) AND  ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigoTurma);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar salas por turma\n" + ex.getMessage());
			}
		
			return al;
	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("UPDATE sala SET nome = ?, com_senha = ?, travada = ?, professor_codigo = ?, disciplina_codigo = ?, ativo = ?, view = ?, senha = ?  WHERE codigo = ?");
			ps.setString(1, ((Sala) param).getNome());
			ps.setBoolean(2, ((Sala) param).getComSenha());
			ps.setBoolean(3, ((Sala) param).getTravada());
			ps.setInt(4, ((Sala) param).getProfessor().getCodigoProfessor());
			ps.setInt(5, ((Sala) param).getDisciplina().getCodigoDisciplina());
			ps.setBoolean(6, ((Sala) param).getAtivo());
			ps.setString(7, ((Sala) param).getView());
			ps.setString(8, ((Sala) param).getSenha());
			ps.setInt(9, ((Sala) param).getCodigoSala());
			ps.executeUpdate();
			
			conn.commit();
			ps.close();
			conn.close();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar salas\n" + ex.getMessage());
		}
		
	}
	
	public void gravarLog(Sala sala) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("UPDATE sala SET log = ? WHERE codigo = ?");
			ps.setString(1, sala.getLog());
			ps.setInt(2, sala.getCodigoSala());
			ps.executeUpdate();
			
			conn.commit();
			ps.close();
			conn.close();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao gravar log\n" + ex.getMessage());
		}
		
	}

	@Override
	public ArrayList<Sala> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT * FROM sala s WHERE (ativo = true AND (s.codigo like '%" + valor + "%' or s.nome like '%" + valor + "%' or s.professor_codigo IN (SELECT p.codigo FROM professor p INNER JOIN pessoa_fisica pf ON p.pessoa_fisica_codigo = pf.codigo WHERE pf.nome like '%" + valor + "%') or disciplina_codigo IN (SELECT codigo FROM disciplina WHERE nome like '%" + valor + "%')))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar salas\n" + ex.getMessage());
			}
		
			return al;
			
	}
	

	public ArrayList<Sala> buscarSalaT( Boolean travada ) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT * FROM sala s WHERE (ativo = true AND travada = " + travada + ")";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar salas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> buscarSalaT1( String valor, Boolean travada ) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT * FROM sala s WHERE (ativo = true AND travada = " + travada + " AND (s.codigo like '%" + valor + "%' or s.nome like '%" + valor + "%' or s.professor_codigo IN (SELECT p.codigo FROM professor p INNER JOIN pessoa_fisica pf ON p.pessoa_fisica_codigo = pf.codigo WHERE pf.nome like '%" + valor + "%') or disciplina_codigo IN (SELECT codigo FROM disciplina WHERE nome like '%" + valor + "%')))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar salas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> buscarSalaS( Boolean comSenha ) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT * FROM sala s WHERE (ativo = true AND com_senha = " + comSenha + ")";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar salas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> buscarSalaS1( String valor, Boolean comSenha ) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT * FROM sala s WHERE (ativo = true AND com_senha = " + comSenha + " AND (s.codigo like '%" + valor + "%' or s.nome like '%" + valor + "%' or s.professor_codigo IN (SELECT p.codigo FROM professor p INNER JOIN pessoa_fisica pf ON p.pessoa_fisica_codigo = pf.codigo WHERE pf.nome like '%" + valor + "%') or disciplina_codigo IN (SELECT codigo FROM disciplina WHERE nome like '%" + valor + "%')))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar salas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> buscarSala(Boolean comSenha, Boolean travada ) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT * FROM sala s WHERE (ativo = true AND com_senha = " + comSenha + " AND travada = " + travada + ")";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar salas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Sala> buscarSala(String valor, Boolean comSenha, Boolean travada ) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Sala> al = new ArrayList<Sala>();
		Sala sala = new Sala();
		
		try {
			String sql = "SELECT * FROM sala s WHERE (ativo = true AND com_senha = " + comSenha + " AND travada = " + travada + " AND (s.codigo like '%" + valor + "%' or s.nome like '%" + valor + "%' or s.professor_codigo IN (SELECT p.codigo FROM professor p INNER JOIN pessoa_fisica pf ON p.pessoa_fisica_codigo = pf.codigo WHERE pf.nome like '%" + valor + "%') or disciplina_codigo IN (SELECT codigo FROM disciplina WHERE nome like '%" + valor + "%')))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				sala = getByNome(rs.getString("nome"));
				al.add(sala);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar salas\n" + ex.getMessage());
			}
		
			return al;
	}

}
