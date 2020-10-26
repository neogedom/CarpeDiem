package br.com.carpediem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Endereco;
import br.com.carpediem.modelo.PessoaFisica;


public class DAOPessoaFisica extends DAO implements IDAO {
	private Connection conn;
	
	public DAOPessoaFisica () {
		super();
	}


	public PessoaFisica getByNome (String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT pf.codigo as codigo_pessoa_fisica, p.codigo as codigo_pessoa, pf.nome, pf.sobrenome, pf.cpf, pf.rg," +
				"pf.estado_civil, pf.sexo, pf.data_nascimento, p.telefone, p.email, pf.nivel_acesso, p.ativo FROM pessoa p, " +
				"pessoa_fisica pf WHERE (pf.codigo = p.codigo and pf.nome = ?)";


		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				PessoaFisica pessoaFisica = new PessoaFisica();
				pessoaFisica.setCodigoPessoaFisica(rs.getInt("codigo_pessoa_fisica"));
				pessoaFisica.setCodigoPessoa(rs.getInt("codigo_pessoa"));
				pessoaFisica.setNome(rs.getString("nome"));
				pessoaFisica.setSobrenome(rs.getString("sobrenome"));
				pessoaFisica.setCpf(rs.getString("cpf"));
				pessoaFisica.setRg(rs.getString("rg"));
				pessoaFisica.setSexo(rs.getString("sexo"));
				pessoaFisica.setEstadoCivil(rs.getString("estado_civil"));
				pessoaFisica.setDataNascimento(rs.getDate("data_nascimento"));
				pessoaFisica.setEmail(rs.getString("email"));
				pessoaFisica.setTelefone(rs.getString("telefone"));
				pessoaFisica.setNivelAcesso(rs.getInt("nivel_acesso"));
				pessoaFisica.setAtivo(rs.getBoolean("ativo"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return pessoaFisica;
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
			throw new Exception ("- Erro ao obter Pessoa Física por codigo!\n" + ex.getMessage()) ;
		}
	}
	
	public PessoaFisica getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT pf.codigo as codigo_pessoa_fisica, p.codigo as codigo_pessoa, pf.nome, pf.sobrenome, pf.cpf, pf.rg," +
				"pf.estado_civil, pf.sexo, pf.data_nascimento, p.telefone, p.email, pf.nivel_acesso, p.ativo FROM pessoa p, " +
				"pessoa_fisica pf WHERE (pf.codigo = p.codigo and pf.codigo = ?)";


		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				PessoaFisica pessoaFisica = new PessoaFisica();
				pessoaFisica.setCodigoPessoaFisica(rs.getInt("codigo_pessoa_fisica"));
				pessoaFisica.setCodigoPessoa(rs.getInt("codigo_pessoa"));
				pessoaFisica.setNome(rs.getString("nome"));
				pessoaFisica.setSobrenome(rs.getString("sobrenome"));
				pessoaFisica.setCpf(rs.getString("cpf"));
				pessoaFisica.setRg(rs.getString("rg"));
				pessoaFisica.setSexo(rs.getString("sexo"));
				pessoaFisica.setEstadoCivil(rs.getString("estado_civil"));
				pessoaFisica.setDataNascimento(rs.getDate("data_nascimento"));
				pessoaFisica.setEmail(rs.getString("email"));
				pessoaFisica.setTelefone(rs.getString("telefone"));
				pessoaFisica.setNivelAcesso(rs.getInt("nivel_acesso"));
				pessoaFisica.setAtivo(rs.getBoolean("ativo"));
				
				rs.close();
				ps.close();
				conn.close();
				
				return pessoaFisica;
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
			throw new Exception ("- Erro ao obter Pessoa Física por codigo!\n" + ex.getMessage()) ;
		}
	}
		
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT en.codigo as codigo_endereco, en.rua, en.numero, en.bairro, en.cep, en.complemento, en.cidade_codigo, en.ativo FROM endereco en WHERE (en.rua = ? AND en.numero = ? AND en.bairro = ? AND en.cep = ?)");
			ps.setString(1, ((Endereco) param).getRua());
			ps.setString(2, ((Endereco) param).getNumero());
			ps.setString(3, ((Endereco) param).getBairro());
			ps.setString(4, ((Endereco) param).getCep());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				alterar (((Endereco) param));
			
			}
			else {
				PreparedStatement psCidade = conn.prepareStatement("SELECT cod_cidade FROM cidade WHERE (nom_cidade = ? AND cod_estado = (SELECT cod_estado FROM estado WHERE (sgl_estado = ?)))");
				PreparedStatement psEndereco = conn.prepareStatement("INSERT INTO endereco (rua, numero, bairro, cep, complemento, cidade_codigo, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)");
				
				psCidade.setString(1, ((Endereco) param).getCidade().getNome());
				psCidade.setString(1, ((Endereco) param).getCidade().getEstado().getUf());
				rs = psCidade.executeQuery();
				
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

	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<PessoaFisica> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<PessoaFisica> al = new ArrayList<PessoaFisica>();
		PessoaFisica pessoaFisica = new PessoaFisica ();
		
		try {
			String sql = "SELECT nome, sobrenome FROM pessoa_fisica pf INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE (p.ativo = true)";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				pessoaFisica = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(pessoaFisica);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar pessoas físicas\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<PessoaFisica> listaPessoaFisicaSemUsuario() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<PessoaFisica> al = new ArrayList<PessoaFisica>();
		PessoaFisica pessoaFisica = new PessoaFisica ();
		
		try {
			String sql = "SELECT nome, sobrenome FROM pessoa_fisica pf INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo  WHERE (p.ativo = true AND pf.codigo NOT IN (SELECT pessoa_fisica_codigo FROM usuario) )";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				pessoaFisica = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(pessoaFisica);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar pessoas físicas sem usuário\n" + ex.getMessage());
			}
		
			return al;
	}


	@Override
	public ArrayList<PessoaFisica> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
