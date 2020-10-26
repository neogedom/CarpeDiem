package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Cidade;
import br.com.carpediem.modelo.Endereco;
import br.com.carpediem.modelo.Estado;


public class DAOEndereco extends DAO implements IDAO {

	private Connection conn;
	
	public DAOEndereco () {
		super();
	}
	
	public Endereco get(String rua, String bairro, String numero, String cep) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, rua, numero, bairro, complemento, cep, c.cod_cidade, est.cod_estado, ativo FROM endereco en, estado est, cidade c WHERE (rua = ? AND bairro = ? AND numero = ? AND cep = ? AND en.cidade_codigo = c.cod_cidade AND c.cod_estado = est.cod_estado)";

		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rua);
			ps.setString(2, bairro);
			ps.setString(3, numero);
			ps.setString(4, cep);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				DAOEstado daoe = new DAOEstado();
				DAOCidade daoc = new DAOCidade();
				Endereco endereco = new Endereco();
				Estado estado = daoe.getByCodigo(rs.getInt("cod_estado"));
				Cidade cidade = daoc.getByCodigo(rs.getInt("cod_cidade"));
				endereco.setCodigo(rs.getInt("codigo"));
				endereco.setRua(rs.getString("rua"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(cidade);
				endereco.getCidade().setEstado(estado);
				endereco.setAtivo(rs.getBoolean("ativo"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return endereco;
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
			throw new Exception ("- Erro ao obter Endereço!\n" + ex.getMessage()) ;
		}

	}
	
	public Endereco getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, rua, numero, bairro, complemento, cep, c.cod_cidade, est.cod_estado, ativo FROM " +
				"endereco en INNER JOIN cidade c ON en.cidade_codigo = c.cod_cidade INNER JOIN estado est ON c.cod_estado = est.cod_estado" +
				" WHERE (en.codigo = ?)";

		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				DAOEstado daoe = new DAOEstado();
				DAOCidade daoc = new DAOCidade();
				Endereco endereco = new Endereco();
				Estado estado = daoe.getByCodigo(rs.getInt("cod_estado"));
				Cidade cidade = daoc.getByCodigo(rs.getInt("cod_cidade"));
				endereco.setCodigo(rs.getInt("codigo"));
				endereco.setRua(rs.getString("rua"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(cidade);
				endereco.getCidade().setEstado(estado);
				endereco.setAtivo(rs.getBoolean("ativo"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return endereco;
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
			throw new Exception ("- Erro ao obter Endereço!\n" + ex.getMessage()) ;
		}

	}
	
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT en.codigo as codigo_endereco, en.rua, en.numero, en.bairro, en.cep, en.complemento, en.cidade_codigo, en.ativo FROM endereco en WHERE (en.rua = ? AND en.numero = ? AND en.bairro = ? AND en.cep = ?)");
			ps.setString(1, ((Endereco) param).getRua());
			ps.setString(2, ((Endereco) param).getNumero());
			ps.setString(3, ((Endereco) param).getBairro());
			ps.setString(4, ((Endereco) param).getCep());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Cidade cidade = new Cidade();
				DAOCidade daoc = new DAOCidade();
				cidade = daoc.getByNome(((Endereco) param).getCidade().getNome(), ((Endereco) param).getCidade().getEstado().getUf());
				Endereco endereco = new Endereco();
				DAOEndereco dao = new DAOEndereco();
				endereco = dao.get(rs.getString("rua"), rs.getString("bairro"), rs.getString("numero"), rs.getString("cep"));
				try {
					PreparedStatement psEndereco = conn.prepareStatement("UPDATE endereco SET rua = ?, numero = ?, bairro = ?, cep = ?, complemento = ?, cidade_codigo = ?, ativo = true WHERE (codigo = ?)");
					
					
					psEndereco.setString(1, ((Endereco) param).getRua());
					psEndereco.setString(2, ((Endereco) param).getNumero());
					psEndereco.setString(3, ((Endereco) param).getBairro());
					psEndereco.setString(4, ((Endereco) param).getCep());
					psEndereco.setString(5, ((Endereco) param).getComplemento());
					psEndereco.setInt(6, cidade.getCodigo());
					psEndereco.setInt(7, endereco.getCodigo());
					psEndereco.executeUpdate();
				}
				catch (Exception ex)
				{
					throw new Exception ("- Erro ao cadastrar Endereço\n" + ex.getMessage());
				}
				
			}
			else
			{
				conn.setAutoCommit(false);
				PreparedStatement psCidade = conn.prepareStatement("SELECT cod_cidade FROM cidade WHERE (nom_cidade = ? AND cod_estado = (SELECT cod_estado FROM estado WHERE (sgl_estado = ?)))");
				PreparedStatement psEndereco = conn.prepareStatement("INSERT INTO endereco (rua, numero, bairro, cep, complemento, cidade_codigo, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)");
				
				psCidade.setString(1, ((Endereco) param).getCidade().getNome());
				psCidade.setString(2, ((Endereco) param).getCidade().getEstado().getUf());
				rs = psCidade.executeQuery();
				rs.next();
				
				psEndereco.setString(1, ((Endereco) param).getRua());
				psEndereco.setString(2, ((Endereco) param).getNumero());
				psEndereco.setString(3, ((Endereco) param).getBairro());
				psEndereco.setString(4, ((Endereco) param).getCep());
				psEndereco.setString(5, ((Endereco) param).getComplemento());
				psEndereco.setInt(6, rs.getInt("cod_cidade"));
				psEndereco.setBoolean(7, true);
				psEndereco.executeUpdate();
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Endereço\n" + ex.getMessage());
		}
	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		Cidade cidade = new Cidade();
		DAOCidade daoc = new DAOCidade();
		cidade = daoc.getByNome(((Endereco) param).getCidade().getNome(), ((Endereco) param).getCidade().getEstado().getUf());
		
		try {
			PreparedStatement psEndereco = conn.prepareStatement("UPDATE endereco SET rua = ?, numero = ?, bairro = ?, cep = ?, complemento = ?, cidade_codigo = ? WHERE (codigo = ?)");

			psEndereco.setString(1, ((Endereco) param).getRua());
			psEndereco.setString(2, ((Endereco) param).getNumero());
			psEndereco.setString(3, ((Endereco) param).getBairro());
			psEndereco.setString(4, ((Endereco) param).getCep());
			psEndereco.setString(5, ((Endereco) param).getComplemento());
			psEndereco.setInt(6, cidade.getCodigo());
			psEndereco.setInt(7, ((Endereco) param).getCodigo());
			psEndereco.executeUpdate();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar endereço\n" + ex.getMessage());
		}
	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement psPessoa = conn.prepareStatement("SELECT * FROM pessoa WHERE (endereco_codigo = ?)");
			psPessoa.setInt(1, ((Endereco) param).getCodigo());
			ResultSet rs = psPessoa.executeQuery();
			if (rs.next())
			{
				throw new Exception ("- Impossível deletar endereço. Delete primeiro as pessoas vinculadas a ele");
			}
			else 
			{
			PreparedStatement ps = conn.prepareStatement("UPDATE endereco SET ativo = false WHERE (codigo = ?)");
			ps.setInt(1, ((Endereco) param).getCodigo());
			ps.executeUpdate();
			conn.commit();
			}
			
			conn.close();
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Endereco\n" + ex.getMessage());
		}
	}

	@Override
	public ArrayList <Endereco> listar() throws Exception {
		// TODO Auto-generated method stub
			conn = super.getConnection();
			ArrayList<Endereco> al = new ArrayList<Endereco>();
			Endereco endereco = new Endereco();
			
			try {
				String sql = "SELECT rua, bairro, numero, cep FROM endereco WHERE ativo = true";
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
			
				while (rs.next())
				{
					endereco = get(rs.getString("rua"), rs.getString("bairro"), rs.getString("numero"), rs.getString("cep"));
					al.add(endereco);
				}
			
				}
		
				catch (Exception ex)
				{
					throw new Exception ("- Erro ao listar endereços\n" + ex.getMessage());
				}
			
				return al;
	}

	@Override
	public ArrayList<Endereco> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Endereco> al = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();
		
		try {
			String sql = "SELECT * FROM endereco en INNER JOIN cidade c ON en.cidade_codigo = c.cod_cidade INNER JOIN estado est ON est.cod_estado = c.cod_estado WHERE (ativo = true AND (en.codigo like '%" + valor + "%' or en.rua like '%" + valor + "%' or en.bairro like '%" + valor + "%' or en.cep like '%" + valor + "%' or en.numero like '%" + valor + "%' or en.complemento like '%" + valor + "%' or c.cod_cidade in (SELECT cod_cidade FROM cidade WHERE (nom_cidade like '%" + valor + "%' or cod_estado in (SELECT cod_estado FROM estado WHERE nom_estado like '%" + valor + "%' or sgl_estado like '%" + valor + "%')))))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				endereco = get(rs.getString("rua"), rs.getString("bairro"), rs.getString("numero"), rs.getString("cep"));
				al.add(endereco);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar enderecos\n" + ex.getMessage());
			}
		
			return al;
	}

}
