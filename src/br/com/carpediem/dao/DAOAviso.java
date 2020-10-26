package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.carpediem.modelo.Aviso;
import br.com.carpediem.modelo.Turma;


public class DAOAviso extends DAO implements IDAO {

	private Connection conn;
	
	public DAOAviso() {
		super();
	}
	
	public Aviso getByNome(String nome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM aviso WHERE descricao = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Aviso aviso = new Aviso();
				Turma turma = new Turma();
				DAOTurma daot = new DAOTurma();
				turma = daot.getByCodigo(rs.getInt("turma_codigo"));
				
				aviso.setCodigoAviso(rs.getInt("codigo"));
				aviso.setTurma(turma);
				aviso.setDescricao(rs.getString("descricao"));
				aviso.setAtivo(rs.getBoolean("ativo"));
				rs.close();
				ps.close();
				return aviso;
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
			throw new Exception ("- Erro ao obter Aviso por nome!\n" + ex.getMessage()); 
		}

	}
	
	public Aviso getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM aviso WHERE codigo = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Aviso aviso = new Aviso();
				Turma turma = new Turma();
				DAOTurma daot = new DAOTurma();
				turma = daot.getByCodigo(rs.getInt("turma_codigo"));
				
				aviso.setCodigoAviso(rs.getInt("codigo"));
				aviso.setTurma(turma);
				aviso.setDescricao(rs.getString("descricao"));
				aviso.setAtivo(rs.getBoolean("ativo"));
				rs.close();
				ps.close();
				return aviso;
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
			throw new Exception ("- Erro ao obter Aviso por código!\n" + ex.getMessage()); 
		}

	}
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT codigo, descricao, turma_codigo, ativo FROM aviso WHERE (descricao = ?)");
			ps.setString(1, ((Aviso) param).getDescricao());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Aviso aviso = new Aviso();
				DAOAviso dao = new DAOAviso();
				aviso = dao.getByNome(((Aviso) param).getDescricao());
				try {
					conn.setAutoCommit(false);
					PreparedStatement psAviso = conn.prepareStatement("UPDATE aviso SET descricao = ?, turma_codigo = ?, ativo = true WHERE (codigo = ?)");
					
					
					psAviso.setString(1, ((Aviso) param).getDescricao());
					psAviso.setInt(2, aviso.getCodigoAviso());
					psAviso.executeUpdate();
					
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar aviso\n" + ex.getMessage());
				}
			
			}
			else {
				Turma turma = new Turma ();
				DAOTurma daot = new DAOTurma();
				turma = daot.getByCodigo(((Aviso) param).getTurma().getCodigoTurma());
				PreparedStatement psAviso = conn.prepareStatement("insert into aviso (descricao, turma_codigo, ativo) values (?, ?, ?)");
				
				psAviso.setString(1, ((Aviso) param).getDescricao());
				psAviso.setInt(2, turma.getCodigoTurma());
				psAviso.setBoolean(3, ((Aviso) param).getAtivo());
				psAviso.executeUpdate();
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Aviso\n" + ex.getMessage());
		}

	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		Aviso aviso = new Aviso();
		DAOAviso dao = new DAOAviso();
		aviso = dao.getByCodigo(((Aviso) param).getCodigoAviso());
	
		try {
			conn.setAutoCommit(false);
			PreparedStatement psAviso = conn.prepareStatement("UPDATE aviso SET descricao = ?, turma_codigo = ?, ativo = true WHERE (codigo = ?)");
			
			
			psAviso.setString(1, ((Aviso) param).getDescricao());
			psAviso.setInt(2,((Aviso) param).getTurma().getCodigoTurma());
			psAviso.setInt(3, aviso.getCodigoAviso());
			psAviso.executeUpdate();
			
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar aviso\n" + ex.getMessage());
		}
	

	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement psSala = conn.prepareStatement("UPDATE aviso SET ativo = false WHERE (codigo = ?)");
			psSala.setInt(1, ((Aviso) param).getCodigoAviso());
			psSala.executeUpdate();
			
			
			conn.commit();
			conn.close();
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Aviso\n" + ex.getMessage());
		}
	}

	@Override
	public ArrayList<Aviso> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aviso> al = new ArrayList<Aviso>();
		Aviso aviso = new Aviso();
		
		try {
			String sql = "SELECT descricao FROM aviso WHERE ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					aviso = getByNome(rs.getString("descricao"));
					al.add(aviso);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar avisos\n" + ex.getMessage());
			}
		
			return al;

	}

	@Override
	public ArrayList<Aviso> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aviso> al = new ArrayList<Aviso>();
		Aviso aviso = new Aviso();
		
		try {
			String sql = "SELECT * FROM aviso av INNER JOIN turma t ON t.codigo = av.turma_codigo INNER JOIN professor pr ON t.professor_codigo = pr.codigo INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo INNER JOIN disciplina ds ON t.disciplina_codigo = ds.codigo WHERE (av.ativo = true AND (av.codigo like '%" + valor + "%' or pf.nome like '%" + valor + "%' or pf.sobrenome like '%" + valor + "%' or av.descricao like '%" + valor + "%' or ds.nome like '%" + valor + "%' ))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				aviso = getByNome(rs.getString("descricao"));
				al.add(aviso);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar avisos\n" + ex.getMessage());
			}
		
			return al;
	}

}
