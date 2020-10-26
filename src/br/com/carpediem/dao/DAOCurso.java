package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Curso;
import br.com.carpediem.modelo.Disciplina;


public class DAOCurso extends DAO implements IDAO {

	private Connection conn;
	
	public DAOCurso() {
		super();
	}
	
	public Curso getByNome(String nome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, nome, ativo FROM curso WHERE nome = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Curso curso = new Curso();
				curso.setCodigoCurso(rs.getInt("codigo"));
				curso.setNome(rs.getString("nome"));
				curso.setAtivo(rs.getBoolean("ativo"));
				rs.close();
				ps.close();
				return curso;
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
			throw new Exception ("- Erro ao obter Curso por nome!\n" + ex.getMessage()); 
		}

	}
	
	public Curso getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, nome, ativo FROM curso WHERE codigo = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Curso curso = new Curso();
				curso.setCodigoCurso(rs.getInt("codigo"));
				curso.setNome(rs.getString("nome"));
				curso.setAtivo(rs.getBoolean("ativo"));
				rs.close();
				ps.close();
				return curso;
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
			throw new Exception ("- Erro ao obter Curso por código!\n" + ex.getMessage()); 
		}

	}
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT codigo, nome, ativo FROM curso WHERE (nome = ?)");
			ps.setString(1, ((Curso) param).getNome());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Curso curso = new Curso();
				DAOCurso dao = new DAOCurso();
				curso = dao.getByNome(((Curso) param).getNome());
				try {
					conn.setAutoCommit(false);
					PreparedStatement psCurso = conn.prepareStatement("UPDATE curso SET nome = ?, ativo = true WHERE (codigo = ?)");
					
					
					psCurso.setString(1, ((Curso) param).getNome());
					psCurso.setInt(2, curso.getCodigoCurso());
					psCurso.executeUpdate();
					
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar curso\n" + ex.getMessage());
				}
			
			}
			else {
				PreparedStatement psCurso = conn.prepareStatement("INSERT INTO curso (nome, ativo) VALUES (?, ?)");
				
				psCurso.setString(1, ((Curso) param).getNome());
				psCurso.setBoolean(2, ((Curso) param).getAtivo());
				psCurso.executeUpdate();
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Curso\n" + ex.getMessage());
		}

	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();

		try {
			conn.setAutoCommit(false);
			PreparedStatement psCurso = conn.prepareStatement("UPDATE curso SET nome = ? WHERE (codigo = ?)");
			
			
			psCurso.setString(1, ((Curso) param).getNome());
			psCurso.setInt(2, ((Curso) param).getCodigoCurso());
			psCurso.executeUpdate();
			
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar curso\n" + ex.getMessage());
		}

	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		PreparedStatement psIntegridade = conn.prepareStatement("SELECT * FROM turma t, disciplina d WHERE (t.curso_codigo = ? OR d.curso_codigo = ?)");
		psIntegridade.setInt(1, ((Curso) param).getCodigoCurso());
		psIntegridade.setInt(2, ((Curso) param).getCodigoCurso());
		ResultSet rs = psIntegridade.executeQuery();
		if (rs.next())
		{
			throw new Exception ("- Impossível deletar curso. Delete primeiro os dados vinculado a ela (turma, disciplina)");
		}
		else {
			try {
				conn.setAutoCommit(false);
				PreparedStatement psCurso = conn.prepareStatement("UPDATE curso SET ativo = false WHERE (codigo = ?)");
				psCurso.setInt(1, ((Curso) param).getCodigoCurso());
				psCurso.executeUpdate();
			
			
				conn.commit();
				conn.close();
			}
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao deletar Curso\n" + ex.getMessage());
			}
		}

	}

	@Override
	public ArrayList<Curso> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Curso> al = new ArrayList<Curso>();
		Curso curso = new Curso();
		
		try {
			String sql = "SELECT nome FROM curso WHERE ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					curso = getByNome(rs.getString("nome"));
					al.add(curso);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar cursos\n" + ex.getMessage());
			}
		
			return al;

	}

	@Override
	public ArrayList<Curso> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Curso> al = new ArrayList<Curso>();
		Curso curso = new Curso();
		
		try {
			String sql = "SELECT * FROM curso WHERE (ativo = true AND (codigo like '%" + valor + "%' or nome like '%" + valor + "%'))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				curso = getByNome(rs.getString("nome"));
				al.add(curso);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar cursos\n" + ex.getMessage());
			}
		
			return al;

	}

}
