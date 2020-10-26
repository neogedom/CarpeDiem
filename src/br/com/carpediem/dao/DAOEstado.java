package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Estado;
import br.com.carpediem.modelo.Sala;

public class DAOEstado extends DAO implements IDAO {

	private Connection conn;
	
	public DAOEstado() {
		super();
	}
	
	public Estado getByNome(String nome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT cod_estado, sgl_estado, nom_estado FROM estado WHERE (sgl_estado = ?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Estado estado = new Estado();
				estado.setCodigo(rs.getInt("cod_estado"));
				estado.setNome(rs.getString("nom_estado"));
				estado.setUf(rs.getString("sgl_estado"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return estado;
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
			throw new Exception ("- Erro ao obter Estado por nome!\n" + ex.getMessage()) ;
		}

	}
	
	public Estado getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT cod_estado, sgl_estado, nom_estado FROM estado WHERE (cod_estado = ?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Estado estado = new Estado();
				estado.setCodigo(rs.getInt("cod_estado"));
				estado.setNome(rs.getString("nom_estado"));
				estado.setUf(rs.getString("sgl_estado"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return estado;
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
			throw new Exception ("- Erro ao obter Estado por nome!\n" + ex.getMessage()) ;
		}
	}
	
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub

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
	public ArrayList<Estado> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Estado> al = new ArrayList<Estado>();
		Estado estado = new Estado();
		
		try {
			String sql = "SELECT sgl_estado FROM estado";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				estado = getByNome(rs.getString("sgl_estado"));
				al.add(estado);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar estados\n" + ex.getMessage());
			}
		
			return al;
	}

	@Override
	public ArrayList buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
