package br.com.carpediem.dao;

import br.com.carpediem.modelo.Endereco;
import br.com.carpediem.modelo.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOPessoa extends DAO implements IDAO {
	
	private Connection conn;
	
	public DAOPessoa () {
		super();
	}
	
	public Pessoa getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, telefone, email, endereco_codigo, ativo FROM pessoa WHERE (codigo = ?)";

		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Pessoa pessoa = new Pessoa();
				Endereco endereco = new Endereco();
				DAOEndereco dao = new DAOEndereco();
				endereco = dao.getByCodigo(rs.getInt("endereco_codigo"));
				
				pessoa.setCodigoPessoa(rs.getInt("codigo"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoa.setEndereco(endereco);
				pessoa.setAtivo(rs.getBoolean("ativo"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return pessoa;
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
			throw new Exception ("- Erro ao obter Pessoa por codigo!\n" + ex.getMessage()) ;
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
	public ArrayList<Pessoa> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
