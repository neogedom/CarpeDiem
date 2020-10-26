package br.com.carpediem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.PessoaFisica;
import br.com.carpediem.modelo.Usuario;

public class DAOUsuario extends DAO implements IDAO {
	
	private Connection conn;
	
	public DAOUsuario () {
		super();
	}
	
	public Usuario getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, usuario, senha, pessoa_fisica_codigo, ativo FROM usuario WHERE (codigo = ?)";

		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Usuario usuario = new Usuario();
				DAOPessoaFisica daop = new DAOPessoaFisica();
				PessoaFisica pessoaFisica = daop.getByCodigo(rs.getInt("pessoa_fisica_codigo"));
				usuario.setCodigoUsuario(rs.getInt("codigo"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPessoaFisica(pessoaFisica);
				usuario.setAtivo(rs.getBoolean("ativo"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return usuario;
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
			throw new Exception ("- Erro ao obter Usuario por código!\n" + ex.getMessage()) ;
		}

	}
	
	public Usuario getByNome(String nome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT codigo, usuario, senha, pessoa_fisica_codigo, ativo FROM usuario WHERE (usuario = ?)";

		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				Usuario usuario = new Usuario();
				DAOPessoaFisica daop = new DAOPessoaFisica();
				PessoaFisica pessoaFisica = daop.getByCodigo(rs.getInt("pessoa_fisica_codigo"));
				usuario.setCodigoUsuario(rs.getInt("codigo"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPessoaFisica(pessoaFisica);
				usuario.setAtivo(rs.getBoolean("ativo"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return usuario;
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
			throw new Exception ("- Erro ao obter Usuario por nome!\n" + ex.getMessage()) ;
		}

	}

	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT codigo, usuario, senha, pessoa_fisica_codigo, ativo FROM usuario WHERE (usuario = ?)");
			ps.setString(1, ((Usuario) param).getUsuario());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				DAOUsuario dao = new DAOUsuario ();
				Usuario usuario = new Usuario ();
				usuario = dao.getByNome(((Usuario) param).getUsuario());
				DAOPessoaFisica daop = new DAOPessoaFisica();
				PessoaFisica pessoaFisica = new PessoaFisica();
				pessoaFisica = daop.getByNome(((Usuario) param).getPessoaFisica().getNome(), ((Usuario) param).getPessoaFisica().getSobrenome());
				try {
					conn.setAutoCommit(false);
					PreparedStatement psUsuario = conn.prepareStatement("UPDATE usuario SET usuario = ?, senha = ?, pessoa_fisica_codigo = ?, ativo = true WHERE (codigo = ?)");
					
					psUsuario.setString(1, ((Usuario) param).getUsuario());
					psUsuario.setString(2, ((Usuario) param).getSenha());
					psUsuario.setInt(3, pessoaFisica.getCodigoPessoaFisica());
					psUsuario.setInt(4, usuario.getCodigoUsuario());
					psUsuario.executeUpdate();
					
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar usuário\n" + ex.getMessage());
				}
			}
			else {
				PessoaFisica pessoaFisica = new PessoaFisica();
				DAOPessoaFisica daopf = new DAOPessoaFisica();
				pessoaFisica = daopf.getByNome(((Usuario) param).getPessoaFisica().getNome(), ((Usuario) param).getPessoaFisica().getSobrenome());
				PreparedStatement psUsuario = conn.prepareStatement("INSERT INTO usuario (usuario, senha, pessoa_fisica_codigo, ativo) VALUES (?, ?, ?, ?)");
				
				psUsuario.setString(1, ((Usuario) param).getUsuario());
				psUsuario.setString(2, ((Usuario) param).getSenha());
				psUsuario.setInt(3, pessoaFisica.getCodigoPessoaFisica());
				psUsuario.setBoolean(4, ((Usuario) param).getAtivo());
				psUsuario.executeUpdate();
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Usuário\n" + ex.getMessage());
		}
	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement psUsuario = conn.prepareStatement("UPDATE usuario SET usuario = ?, senha = ?, pessoa_fisica_codigo = ? WHERE (codigo = ?)");
			
			psUsuario.setString(1, ((Usuario) param).getUsuario());
			psUsuario.setString(2, ((Usuario) param).getSenha());
			psUsuario.setInt(3, ((Usuario) param).getPessoaFisica().getCodigoPessoaFisica());
			psUsuario.setInt(4, ((Usuario) param).getCodigoUsuario());
			psUsuario.executeUpdate();
			
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar usuário\n" + ex.getMessage());
		}
	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
				conn = super.getConnection();
				try {
					conn.setAutoCommit(false);
					PreparedStatement psUsuario = conn.prepareStatement("UPDATE usuario SET ativo = false WHERE (codigo = ?)");
					psUsuario.setInt(1, ((Usuario) param).getCodigoUsuario());
					psUsuario.executeUpdate();
					
					
					conn.commit();
					conn.close();
				}
				catch (Exception ex)
				{
					throw new Exception ("- Erro ao deletar Usuário\n" + ex.getMessage());
				}

	}

	@Override
	public ArrayList<Usuario> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Usuario> al = new ArrayList<Usuario>();
		Usuario usuario = new Usuario();
		
		try {
			String sql = "SELECT usuario FROM usuario WHERE ativo = true";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					usuario = getByNome(rs.getString("usuario"));
					al.add(usuario);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar usuarios\n" + ex.getMessage());
			}
		
			return al;
	}

	@Override
	public ArrayList<Usuario> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Usuario> al = new ArrayList<Usuario>();
		Usuario usuario = new Usuario();
		
		try {
			String sql = "SELECT * FROM usuario u INNER JOIN pessoa_fisica pf ON u.pessoa_fisica_codigo = pf.codigo WHERE (ativo = true AND (u.codigo like '%" + valor + "%' or u.usuario like '%" + valor + "%' or pessoa_fisica_codigo in (SELECT codigo FROM pessoa_fisica WHERE nome like '%" + valor + "%' or sobrenome like '%" + valor + "%')))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				usuario = getByNome(rs.getString("usuario"));
				al.add(usuario);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar usuários\n" + ex.getMessage());
			}
		
			return al;
	}

}
