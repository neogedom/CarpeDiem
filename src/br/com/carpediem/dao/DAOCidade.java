package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Cidade;
import br.com.carpediem.modelo.Estado;

public class DAOCidade extends DAO implements IDAO {

	private Connection conn;
	
	public DAOCidade () {
		super();
	}
	
	public Cidade getByNome(String nome_cidade, String sgl_estado) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT c.cod_cidade, c.nom_cidade, c.cod_estado FROM cidade c WHERE (c.nom_cidade = ? AND c.cod_estado = (SELECT e.cod_estado FROM estado e WHERE (e.sgl_estado = ?)))";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome_cidade);
			ps.setString(2, sgl_estado);	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Cidade cidade = new Cidade();
				DAOEstado daoe = new DAOEstado();
				Estado estado = daoe.getByCodigo(rs.getInt("cod_estado"));
				cidade.setCodigo(rs.getInt("cod_cidade"));
				cidade.setNome(rs.getString("nom_cidade"));
				cidade.setEstado(estado);
				
				rs.close();
				ps.close();
				conn.close();
				
				return cidade;
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
			throw new Exception ("- Erro ao obter Cidade por nome!\n" + ex.getMessage()) ;
		}

	}
	
	public Cidade getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT cod_cidade, cod_estado, nom_cidade FROM cidade WHERE (cod_cidade = ?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Cidade cidade = new Cidade();
				DAOEstado daoe = new DAOEstado();
				Estado estado = daoe.getByCodigo(rs.getInt("cod_estado"));
				cidade.setCodigo(rs.getInt("cod_cidade"));
				cidade.setNome(rs.getString("nom_cidade"));
				cidade.setEstado(estado);
				
				rs.close();
				ps.close();
				conn.close();
				
				return cidade;
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
			throw new Exception ("- Erro ao obter Cidade por código!\n" + ex.getMessage()) ;
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
	public ArrayList<Cidade> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Cidade> al = new ArrayList<Cidade>();
		Cidade cidade = new Cidade();
		
		try {
			String sql = "SELECT cod_cidade FROM cidade";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				cidade = getByCodigo(rs.getInt("cod_cidade"));
				al.add(cidade);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar cidades\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<String> listarPorEstado(String uf) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<String> al = new ArrayList<String>();
		Cidade cidade = new Cidade();
		
		try {
			String sql = "SELECT cod_cidade FROM cidade c, estado e WHERE ( e.sgl_estado = ? AND e.cod_estado = c.cod_estado)";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uf);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				cidade = getByCodigo(rs.getInt("cod_cidade"));
				al.add(cidade.getNome());
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar cidades por estado\n" + ex.getMessage());
			}
		
			return al;
	}

	@Override
	public ArrayList<Cidade> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
