package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Administrador;
import br.com.carpediem.modelo.Endereco;



public class DAOAdministrador extends DAO implements IDAO {

	private Connection conn;
	
	public DAOAdministrador () {
		super();
	}
	
	public Administrador getByNome(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlAdministrador = "SELECT p.codigo as codigo_pessoa, pf.codigo as codigo_pessoa_fisica, a.codigo as codigo_administrador, " +
				"pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.nivel_acesso, p.email, p.telefone, p.endereco_codigo, p.ativo FROM " +
				"administrador a INNER JOIN pessoa_fisica pf ON pf.codigo = a.pessoa_fisica_codigo " +
				"INNER JOIN pessoa p ON p.codigo = pf.pessoa_codigo WHERE pf.nome = ? AND pf.sobrenome = ?";


		try {
			conn.setAutoCommit(false);
			PreparedStatement psAdministrador = conn.prepareStatement(sqlAdministrador);
			psAdministrador.setString(1, nome);
			psAdministrador.setString(2, sobrenome);
			ResultSet rsAdministrador = psAdministrador.executeQuery();
			
			if (rsAdministrador.next()) 
			{
				Administrador administrador = new Administrador();
				administrador.setCodigoAdministrador(rsAdministrador.getInt("codigo_administrador"));
				administrador.setCodigoPessoaFisica(rsAdministrador.getInt("codigo_pessoa_fisica"));
				administrador.setCodigoPessoa(rsAdministrador.getInt("codigo_pessoa"));
				administrador.setNome(rsAdministrador.getString("nome"));
				administrador.setSobrenome(rsAdministrador.getString("sobrenome"));
				administrador.setCpf(rsAdministrador.getString("cpf"));
				administrador.setRg(rsAdministrador.getString("rg"));
				administrador.setEstadoCivil(rsAdministrador.getString("estado_civil"));
				administrador.setSexo(rsAdministrador.getString("sexo"));
				administrador.setDataNascimento(rsAdministrador.getDate("data_nascimento"));
				administrador.setTelefone(rsAdministrador.getString("telefone"));
				administrador.setEmail(rsAdministrador.getString("email"));
				administrador.setAtivo(rsAdministrador.getBoolean("ativo"));
				
				rsAdministrador.close();
				psAdministrador.close();
				conn.close();
				
				return administrador;
			}
			else 
			{
				rsAdministrador.close();
				psAdministrador.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Administrador por nome!\n" + ex.getMessage()) ;
		}

	}
	
	public Administrador getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlAdministrador = "SELECT p.codigo as codigo_pessoa, pf.codigo as codigo_pessoa_fisica, a.codigo as codigo_administrador, " +
				"pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.nivel_acesso, p.email, p.telefone, p.ativo FROM " +
				"administrador a INNER JOIN pessoa_fisica pf ON pf.codigo = a.pessoa_fisica_codigo " +
				"INNER JOIN pessoa p ON p.codigo = pf.pessoa_codigo WHERE a.codigo = ?";


		try {
			conn.setAutoCommit(false);
			PreparedStatement psAdministrador = conn.prepareStatement(sqlAdministrador);
			psAdministrador.setInt(1, codigo);
			ResultSet rsAdministrador = psAdministrador.executeQuery();
			
			if (rsAdministrador.next()) 
			{
				Administrador administrador = new Administrador();
				administrador.setCodigoAdministrador(rsAdministrador.getInt("codigo_administrador"));
				administrador.setCodigoPessoaFisica(rsAdministrador.getInt("codigo_pessoa_fisica"));
				administrador.setCodigoPessoa(rsAdministrador.getInt("codigo_pessoa"));
				administrador.setNome(rsAdministrador.getString("nome"));
				administrador.setSobrenome(rsAdministrador.getString("sobrenome"));
				administrador.setCpf(rsAdministrador.getString("cpf"));
				administrador.setRg(rsAdministrador.getString("rg"));
				administrador.setEstadoCivil(rsAdministrador.getString("estado_civil"));
				administrador.setSexo(rsAdministrador.getString("sexo"));
				administrador.setDataNascimento(rsAdministrador.getDate("data_nascimento"));
				administrador.setTelefone(rsAdministrador.getString("telefone"));
				administrador.setEmail(rsAdministrador.getString("email"));
				administrador.setAtivo(rsAdministrador.getBoolean("ativo"));
				
				rsAdministrador.close();
				psAdministrador.close();
				conn.close();
				
				return administrador;
			}
			else 
			{
				rsAdministrador.close();
				psAdministrador.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Administrador por código!\n" + ex.getMessage()) ;
		}

	}
	
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT a.codigo as codigo_administrador, pf.codigo as codigo_pessoa_fisica, p.codigo as codigo_pessoa, pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.pessoa_codigo, pf.nivel_acesso FROM administrador a, pessoa_fisica pf, pessoa p WHERE (pf.nome = ? and pf.sobrenome = ? and a.pessoa_fisica_codigo = pf.codigo)");
			ps.setString(1, ((Administrador) param).getNome());
			ps.setString(2, ((Administrador) param).getSobrenome());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				
				Administrador administrador = new Administrador();
				DAOAdministrador dao = new DAOAdministrador();
				administrador = dao.getByNome(((Administrador) param).getNome(), ((Administrador) param).getSobrenome());
				DAOEndereco daoe = new DAOEndereco();
				Endereco endereco = new Endereco();
				endereco = daoe.get(((Administrador) param).getEndereco().getRua(), ((Administrador) param).getEndereco().getBairro(), ((Administrador) param).getEndereco().getNumero(), ((Administrador) param).getEndereco().getCep());
				
				try {
					PreparedStatement psPessoa = conn.prepareStatement("UPDATE pessoa SET email = ?, telefone = ?, endereco_codigo = ?, ativo = true WHERE (codigo = ?)");
					PreparedStatement psPessoaFisica = conn.prepareStatement("UPDATE pessoa_fisica SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, estado_civil = ?, sexo = ?, data_nascimento = ?  WHERE (codigo = ?)");
					
							
					psPessoa.setString(1, ((Administrador) param).getEmail());
					psPessoa.setString(2, ((Administrador) param).getTelefone());
					psPessoa.setInt(3, endereco.getCodigo());
					psPessoa.setInt(4, administrador.getCodigoPessoa());
					psPessoa.executeUpdate();
					
					psPessoaFisica.setString(1, ((Administrador) param).getNome());
					psPessoaFisica.setString(2, ((Administrador) param).getSobrenome());
					psPessoaFisica.setString(3, ((Administrador) param).getCpf());
					psPessoaFisica.setString(4, ((Administrador) param).getRg());
					psPessoaFisica.setString(5, ((Administrador) param).getEstadoCivil());
					psPessoaFisica.setString(6, ((Administrador) param).getSexo());
					psPessoaFisica.setDate(7,  new Date(((Administrador) param).getDataNascimento().getTime()));
					psPessoaFisica.setInt(8, (administrador.getCodigoPessoaFisica()));
					psPessoaFisica.executeUpdate();
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar administrador\n" + ex.getMessage());
				}
			
			}
			else {
				PreparedStatement psPessoa = conn.prepareStatement("INSERT INTO pessoa (telefone, email, endereco_codigo, ativo) VALUES (?, ?, ?, ?)");
				PreparedStatement psPessoaFisica = conn.prepareStatement("INSERT INTO pessoa_fisica (nome, sobrenome, cpf, rg, estado_civil, sexo, data_nascimento, pessoa_codigo, nivel_acesso) VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT MAX(codigo) FROM pessoa), ?)");
				PreparedStatement psAdministrador = conn.prepareStatement("INSERT INTO administrador ( pessoa_fisica_codigo) VALUES ((SELECT MAX(codigo) FROM pessoa_fisica))");
				PreparedStatement psEndereco = conn.prepareStatement("SELECT codigo FROM endereco WHERE (rua = ? AND numero = ? AND bairro = ? AND complemento = ? AND cidade_codigo = (SELECT cod_cidade FROM cidade WHERE (nom_cidade = ? AND cod_estado = (SELECT cod_estado FROM estado WHERE (sgl_estado = ?)))) AND cep = ?)");
				
				psEndereco.setString(1, ((Administrador) param).getEndereco().getRua());
				psEndereco.setString(2, ((Administrador) param).getEndereco().getNumero());
				psEndereco.setString(3, ((Administrador) param).getEndereco().getBairro());
				psEndereco.setString(4, ((Administrador) param).getEndereco().getComplemento());
				psEndereco.setString(5, ((Administrador) param).getEndereco().getCidade().getNome());
				psEndereco.setString(6, ((Administrador) param).getEndereco().getCidade().getEstado().getUf());
				psEndereco.setString(7, ((Administrador) param).getEndereco().getCep());
				rs = psEndereco.executeQuery();
				rs.next();
				
				psPessoa.setString(1, ((Administrador) param).getTelefone());
				psPessoa.setString(2, ((Administrador) param).getEmail());
				psPessoa.setInt(3, (rs.getInt("codigo")));
				psPessoa.setBoolean(4, true);
				psPessoa.executeUpdate();
				
				psPessoaFisica.setString(1, ((Administrador) param).getNome());
				psPessoaFisica.setString(2, ((Administrador) param).getSobrenome());
				psPessoaFisica.setString(3, ((Administrador) param).getCpf());
				psPessoaFisica.setString(4, ((Administrador) param).getRg());
				psPessoaFisica.setString(5, ((Administrador) param).getEstadoCivil());
				psPessoaFisica.setString(6, ((Administrador) param).getSexo());
				psPessoaFisica.setDate(7,  new Date(((Administrador) param).getDataNascimento().getTime()));
				psPessoaFisica.setInt(8,((Administrador) param).getNivelAcesso());
				psPessoaFisica.executeUpdate();
				
				psAdministrador.executeUpdate();
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Administrador\n" + ex.getMessage());
		}


	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		Administrador administrador = new Administrador();
		DAOAdministrador dao = new DAOAdministrador();
		administrador = dao.getByCodigo(((Administrador) param).getCodigoAdministrador());
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psPessoa = conn.prepareStatement("UPDATE pessoa SET email = ?, telefone = ?, endereco_codigo = ? WHERE (codigo = ?)");
			PreparedStatement psPessoaFisica = conn.prepareStatement("UPDATE pessoa_fisica SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, estado_civil = ?, sexo = ?, data_nascimento = ?  WHERE (codigo = ?)");
			
			psPessoa.setString(1, ((Administrador) param).getEmail());
			psPessoa.setString(2, ((Administrador) param).getTelefone());
			psPessoa.setInt(3, ((Administrador) param).getEndereco().getCodigo());
			psPessoa.setInt(4, administrador.getCodigoPessoa());
			psPessoa.executeUpdate();
			
			psPessoaFisica.setString(1, ((Administrador) param).getNome());
			psPessoaFisica.setString(2, ((Administrador) param).getSobrenome());
			psPessoaFisica.setString(3, ((Administrador) param).getCpf());
			psPessoaFisica.setString(4, ((Administrador) param).getRg());
			psPessoaFisica.setString(5, ((Administrador) param).getEstadoCivil());
			psPessoaFisica.setString(6, ((Administrador) param).getSexo());
			psPessoaFisica.setDate(7,  new Date(((Administrador) param).getDataNascimento().getTime()));
			psPessoaFisica.setInt(8, (administrador.getCodigoPessoaFisica()));
			psPessoaFisica.executeUpdate();
			
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar administrador\n" + ex.getMessage());
		}
	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement psIntegridade = conn.prepareStatement("SELECT * FROM usuario WHERE (pessoa_fisica_codigo = ?)");
			psIntegridade.setInt(1, ((Administrador) param).getCodigoPessoaFisica());
			ResultSet rs = psIntegridade.executeQuery();
			if (rs.next())
			{
				throw new Exception ("- Impossível deletar administrador. Delete primeiro o usuário vinculado a ele");
			}
			else {
				Administrador administrador = new Administrador();
				DAOAdministrador dao = new DAOAdministrador();
				administrador = dao.getByCodigo(((Administrador) param).getCodigoAdministrador());
				PreparedStatement ps = conn.prepareStatement("UPDATE pessoa SET ativo = false WHERE (codigo = ?)");
				PreparedStatement psUsuario = conn.prepareStatement("UPDATE usuario SET ativo = false WHERE (pessoa_fisica_codigo = ?)");
				ps.setInt(1, administrador.getCodigoPessoa());
				ps.executeUpdate();
			
				psUsuario.setInt(1, ((Administrador) param).getCodigoPessoaFisica());
				psUsuario.executeUpdate();
			
			
				conn.commit();
				conn.close();
			}
		}
		catch (Exception ex)
		{
			throw new Exception ("- Erro ao deletar Administrador\n" + ex.getMessage());
		}

	}

	@Override
	public ArrayList<Administrador> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Administrador> al = new ArrayList<Administrador>();
		Administrador administrador = new Administrador();
		
		try {
			String sql = "SELECT pf.nome, pf.sobrenome FROM administrador a INNER JOIN pessoa_fisica pf ON a.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE (p.ativo = true)";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				administrador = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(administrador);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar administradores\n" + ex.getMessage());
			}
		
			return al;

	}

	@Override
	public ArrayList<Administrador> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Administrador> al = new ArrayList<Administrador>();
		Administrador administrador = new Administrador();
		
		try {
			String sql = "SELECT * FROM administrador a INNER JOIN pessoa_fisica pf ON a.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE (ativo = true AND (a.codigo like '%" + valor + "%' or pf.nome like '%" + valor + "%' or pf.sobrenome like '%" + valor + "%' or pf.cpf like '%" + valor + "%' or pf.rg like '%" + valor + "%' or pf.estado_civil like '%" + valor + "%' or pf.sexo like '%" + valor + "%' or pf.data_nascimento like '%" + valor + "%' or p.telefone like '%" + valor + "%' or p.email like '%" + valor + "%'))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				administrador = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(administrador);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar administradores\n" + ex.getMessage());
			}
		
			return al;
	}

}
