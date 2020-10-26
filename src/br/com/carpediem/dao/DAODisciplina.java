package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Curso;
import br.com.carpediem.modelo.Disciplina;





public class DAODisciplina extends DAO implements IDAO {
	
	private Connection conn;
	
	public DAODisciplina() {
		super();
	}
	
	public Disciplina getByNome(String nome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, nome, ativo FROM disciplina WHERE nome = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Disciplina disciplina = new Disciplina();
				disciplina.setCodigo(rs.getInt("codigo"));
				disciplina.setNome(rs.getString("nome"));
				disciplina.setAtivo(rs.getBoolean("ativo"));
				rs.close();
				ps.close();
				return disciplina;
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
			throw new Exception ("- Erro ao obter Disciplina por nome!\n" + ex.getMessage()); 
		}

	}
	
	public Disciplina getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, nome, ativo FROM disciplina WHERE codigo = ?";
		try {
			
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Disciplina disciplina = new Disciplina();
				disciplina.setCodigo(rs.getInt("codigo"));
				disciplina.setNome(rs.getString("nome"));
				disciplina.setAtivo(rs.getBoolean("ativo"));
				rs.close();
				ps.close();
				return disciplina;
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
			throw new Exception ("- Erro ao obter Disciplina por codigo!\n" + ex.getMessage()); 
		}

	}
	
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT codigo, nome, curso_codigo, ativo FROM disciplina WHERE (nome = ?)");
			ps.setString(1, ((Disciplina) param).getNome());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Disciplina disciplina = new Disciplina();
				DAODisciplina dao = new DAODisciplina();
				disciplina = dao.getByNome(((Disciplina) param).getNome());
				Curso curso = new Curso();
				DAOCurso daoc = new DAOCurso ();
				curso = daoc.getByNome(((Disciplina) param).getCurso().getNome());
				try {
					conn.setAutoCommit(false);
					PreparedStatement psDisciplina = conn.prepareStatement("UPDATE disciplina SET nome = ?, curso_codigo = ?, ativo = true WHERE (codigo = ?)");
					
					
					psDisciplina.setString(1, ((Disciplina) param).getNome());
					psDisciplina.setInt(2, curso.getCodigoCurso());
					psDisciplina.setInt(3, disciplina.getCodigoDisciplina());
					psDisciplina.executeUpdate();
					
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar disciplina\n" + ex.getMessage());
				}
			
			}
			else {
				PreparedStatement psDisciplina = conn.prepareStatement("INSERT INTO disciplina (nome, curso_codigo, ativo) VALUES (?, ?, ?)");
				Curso curso = new Curso();
				DAOCurso daoc = new DAOCurso ();
				curso = daoc.getByNome(((Disciplina) param).getCurso().getNome());
				
				psDisciplina.setString(1, ((Disciplina) param).getNome());
				psDisciplina.setInt(2, curso.getCodigoCurso());
				psDisciplina.setBoolean(3, ((Disciplina) param).getAtivo());
				psDisciplina.executeUpdate();
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Disciplina\n" + ex.getMessage());
		}
		
	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		PreparedStatement psIntegridade = conn.prepareStatement("SELECT * FROM turma t, sala s WHERE (t.disciplina_codigo = ? OR s.disciplina_codigo = ?)");
		psIntegridade.setInt(1, ((Disciplina) param).getCodigoDisciplina());
		psIntegridade.setInt(2, ((Disciplina) param).getCodigoDisciplina());
		ResultSet rs = psIntegridade.executeQuery();
		if (rs.next())
		{
			throw new Exception ("- Impossível deletar disciplina. Delete primeiro os dados vinculado a ela (turma, sala)");
		}
		else {
			try {
				conn.setAutoCommit(false);
				PreparedStatement psDisciplina = conn.prepareStatement("UPDATE disciplina SET ativo = false WHERE (codigo = ?)");
				psDisciplina.setInt(1, ((Disciplina) param).getCodigoDisciplina());
				psDisciplina.executeUpdate();
			
			
				conn.commit();
				conn.close();
			}
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao deletar Disciplina\n" + ex.getMessage());
			}
		}
	}

	@Override
	public ArrayList<Disciplina> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		Disciplina disciplina = new Disciplina();
		
		try {
			String sql = "SELECT nome FROM disciplina WHERE ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					disciplina = getByNome(rs.getString("nome"));
					al.add(disciplina);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar disciplinas\n" + ex.getMessage());
			}
		
			return al;

	}
	
	public ArrayList<Disciplina> listarPorCurso(String curso) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		Disciplina disciplina = new Disciplina();
		
		try {
			String sql = "SELECT nome FROM disciplina WHERE (curso_codigo = (SELECT codigo FROM curso WHERE nome = ? ) AND ativo = true)";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, curso);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				disciplina = getByNome(rs.getString("nome"));
				al.add(disciplina);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar disciplinas por curso\n" + ex.getMessage());
			}
		
			return al;
	}

	public ArrayList<Disciplina> listarPorCursos(ArrayList<String> cursos) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		Disciplina disciplina = new Disciplina();
		
		try {
			String sql = "SELECT nome FROM disciplina WHERE ativo = true AND curso_codigo = (SELECT codigo FROM curso WHERE nome = ?)";
			conn.setAutoCommit(false);
			
			for (int cont = 0; cont <= (cursos.size() - 1); cont++)
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, cursos.get(cont));
				ResultSet rs = ps.executeQuery();
				while (rs.next())
				{
					disciplina = getByNome(rs.getString("nome"));
					al.add(disciplina);
				}
			}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar disciplinas\n" + ex.getMessage());
			}
		
			return al;

	}
	
	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();

		try {
			conn.setAutoCommit(false);
			PreparedStatement psDisciplina = conn.prepareStatement("UPDATE disciplina SET nome = ? WHERE (codigo = ?)");
			
			
			psDisciplina.setString(1, ((Disciplina) param).getNome());
			psDisciplina.setInt(2, ((Disciplina) param).getCodigoDisciplina());
			psDisciplina.executeUpdate();
			
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar disciplina\n" + ex.getMessage());
		}
	}

	@Override
	public ArrayList<Disciplina> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Disciplina> al = new ArrayList<Disciplina>();
		Disciplina disciplina = new Disciplina();
		
		try {
			String sql = "SELECT * FROM disciplina WHERE (ativo = true AND (codigo like '%" + valor + "%' or nome like '%" + valor + "%'))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				disciplina = getByNome(rs.getString("nome"));
				al.add(disciplina);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar disciplinas\n" + ex.getMessage());
			}
		
			return al;

	}

	

}
