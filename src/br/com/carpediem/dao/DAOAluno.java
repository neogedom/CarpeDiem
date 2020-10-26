package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.carpediem.modelo.Aluno;
import br.com.carpediem.modelo.Endereco;
import br.com.carpediem.modelo.Professor;


public class DAOAluno extends DAO implements IDAO {

	private Connection conn;
	
	public DAOAluno () {
		super();
	}
	
	public Aluno getByNome(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlAluno = "SELECT p.codigo as codigo_pessoa, pf.codigo as codigo_pessoa_fisica, al.codigo as codigo_aluno, " +
				"al.matricula, pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.nivel_acesso, p.email, p.telefone, p.endereco_codigo, p.ativo FROM " +
				"aluno al INNER JOIN pessoa_fisica pf ON pf.codigo = al.pessoa_fisica_codigo " +
				"INNER JOIN pessoa p ON p.codigo = pf.pessoa_codigo WHERE pf.nome = ?";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psAluno = conn.prepareStatement(sqlAluno);
			psAluno.setString(1, nome);
			ResultSet rsAluno = psAluno.executeQuery();
			if (rsAluno.next()) 
			{
				Aluno aluno = new Aluno();
				Endereco endereco = new Endereco();
				DAOEndereco daoe = new DAOEndereco();
				aluno.setCodigoAluno(rsAluno.getInt("codigo_aluno"));
				aluno.setCodigoPessoaFisica(rsAluno.getInt("codigo_pessoa_fisica"));
				aluno.setCodigoPessoa(rsAluno.getInt("codigo_pessoa"));
				aluno.setNome(rsAluno.getString("nome"));
				aluno.setMatricula(rsAluno.getString("matricula"));
				aluno.setSobrenome(rsAluno.getString("sobrenome"));
				aluno.setCpf(rsAluno.getString("cpf"));
				aluno.setRg(rsAluno.getString("rg"));
				aluno.setEstadoCivil(rsAluno.getString("estado_civil"));
				aluno.setSexo(rsAluno.getString("sexo"));
				aluno.setDataNascimento(rsAluno.getDate("data_nascimento"));
				aluno.setTelefone(rsAluno.getString("telefone"));
				aluno.setEmail(rsAluno.getString("email"));
				endereco = daoe.getByCodigo(rsAluno.getInt("endereco_codigo"));
				aluno.setEndereco(endereco);
				aluno.setAtivo(rsAluno.getBoolean("ativo"));
		
				rsAluno.close();
				psAluno.close();
				conn.close();
				
				return aluno;
			}
			else 
			{
				rsAluno.close();
				psAluno.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Aluno por nome!\n" + ex.getMessage()) ;
		}

	}
	
	public Aluno getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlAluno = "SELECT p.codigo as codigo_pessoa, pf.codigo as codigo_pessoa_fisica, al.codigo as codigo_aluno, " +
				"al.matricula, pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.nivel_acesso, p.email, p.telefone, p.endereco_codigo, p.ativo FROM " +
				"aluno al INNER JOIN pessoa_fisica pf ON pf.codigo = al.pessoa_fisica_codigo " +
				"INNER JOIN pessoa p ON p.codigo = pf.pessoa_codigo WHERE al.codigo = ?";

		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psAluno = conn.prepareStatement(sqlAluno);
			psAluno.setInt(1, codigo);
			ResultSet rsAluno = psAluno.executeQuery();
			if (rsAluno.next()) 
			{
				Aluno aluno = new Aluno();
				Endereco endereco = new Endereco();
				DAOEndereco daoe = new DAOEndereco();
				aluno.setCodigoAluno(rsAluno.getInt("codigo_aluno"));
				aluno.setCodigoPessoaFisica(rsAluno.getInt("codigo_pessoa_fisica"));
				aluno.setCodigoPessoa(rsAluno.getInt("codigo_pessoa"));
				aluno.setNome(rsAluno.getString("nome"));
				aluno.setMatricula(rsAluno.getString("matricula"));
				aluno.setSobrenome(rsAluno.getString("sobrenome"));
				aluno.setCpf(rsAluno.getString("cpf"));
				aluno.setRg(rsAluno.getString("rg"));
				aluno.setEstadoCivil(rsAluno.getString("estado_civil"));
				aluno.setSexo(rsAluno.getString("sexo"));
				aluno.setDataNascimento(rsAluno.getDate("data_nascimento"));
				aluno.setTelefone(rsAluno.getString("telefone"));
				aluno.setEmail(rsAluno.getString("email"));
				endereco = daoe.getByCodigo(rsAluno.getInt("endereco_codigo"));
				aluno.setEndereco(endereco);
				aluno.setAtivo(rsAluno.getBoolean("ativo"));
			
				rsAluno.close();
				psAluno.close();
				conn.close();
				
				return aluno;
			}
			else 
			{
				rsAluno.close();
				psAluno.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Aluno por nome!\n" + ex.getMessage()) ;
		}

	}
	
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT al.codigo as codigo_aluno, pf.codigo as codigo_pessoa_fisica, pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.pessoa_codigo, pf.nivel_acesso FROM aluno al, pessoa_fisica pf, pessoa p WHERE (((pf.nome = ? and pf.sobrenome = ?) or pf.cpf = ?) and al.pessoa_fisica_codigo = pf.codigo and p.ativo = false)");
			ps.setString(1, ((Aluno) param).getNome());
			ps.setString(2, ((Aluno) param).getSobrenome());
			ps.setString(3, ((Aluno) param).getCpf());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Aluno aluno = new Aluno();
				DAOAluno dao = new DAOAluno();
				aluno = dao.getByNome(((Aluno) param).getNome(), ((Aluno) param).getSobrenome());
				DAOEndereco daoe = new DAOEndereco();
				Endereco endereco = new Endereco();
				endereco = daoe.get(((Aluno) param).getEndereco().getRua(), ((Aluno) param).getEndereco().getBairro(), ((Aluno) param).getEndereco().getNumero(), ((Aluno) param).getEndereco().getCep());
				
				try {
					PreparedStatement psPessoa = conn.prepareStatement("UPDATE pessoa SET email = ?, telefone = ?, endereco_codigo = ?, ativo = true WHERE (codigo = ?)");
					PreparedStatement psPessoaFisica = conn.prepareStatement("UPDATE pessoa_fisica SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, estado_civil = ?, sexo = ?, data_nascimento = ?  WHERE (codigo = ?)");
					PreparedStatement psAluno = conn.prepareStatement("UPDATE aluno SET matricula = ? WHERE (codigo = ?)");
					
					psPessoa.setString(1, ((Aluno) param).getEmail());
					psPessoa.setString(2, ((Aluno) param).getTelefone());
					psPessoa.setInt(3, endereco.getCodigo());
					psPessoa.setInt(4, aluno.getCodigoPessoa());
					psPessoa.executeUpdate();
					
					psPessoaFisica.setString(1, ((Aluno) param).getNome());
					psPessoaFisica.setString(2, ((Aluno) param).getSobrenome());
					psPessoaFisica.setString(3, ((Aluno) param).getCpf());
					psPessoaFisica.setString(4, ((Aluno) param).getRg());
					psPessoaFisica.setString(5, ((Aluno) param).getEstadoCivil());
					psPessoaFisica.setString(6, ((Aluno) param).getSexo());
					psPessoaFisica.setDate(7,  new Date(((Aluno) param).getDataNascimento().getTime()));
					psPessoaFisica.setInt(8, (aluno.getCodigoPessoaFisica()));
					psPessoaFisica.executeUpdate();
					
					psAluno.setString(1, ((Aluno) param).getMatricula());
					psAluno.setInt(2, aluno.getCodigoAluno());
					psAluno.executeUpdate();
					
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar aluno\n" + ex.getMessage());
				}
			
			}
			else
			{
				PreparedStatement psPessoa = conn.prepareStatement("INSERT INTO pessoa (telefone, email, endereco_codigo, ativo) VALUES (?, ?, ?, ?)");
				PreparedStatement psPessoaFisica = conn.prepareStatement("INSERT INTO pessoa_fisica (nome, sobrenome, cpf, rg, estado_civil, sexo, data_nascimento, pessoa_codigo, nivel_acesso) VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT MAX(codigo) FROM pessoa), ?)");
				PreparedStatement psAluno = conn.prepareStatement("INSERT INTO aluno ( pessoa_fisica_codigo, matricula) VALUES ((SELECT MAX(codigo) FROM pessoa_fisica), ?)");
				PreparedStatement psEndereco = conn.prepareStatement("SELECT codigo FROM endereco WHERE (rua = ? AND numero = ? AND bairro = ? AND complemento = ? AND cidade_codigo = (SELECT cod_cidade FROM cidade WHERE (nom_cidade = ? AND cod_estado = (SELECT cod_estado FROM estado WHERE (sgl_estado = ?)))) AND cep = ?)");
				
				psEndereco.setString(1, ((Aluno) param).getEndereco().getRua());
				psEndereco.setString(2, ((Aluno) param).getEndereco().getNumero());
				psEndereco.setString(3, ((Aluno) param).getEndereco().getBairro());
				psEndereco.setString(4, ((Aluno) param).getEndereco().getComplemento());
				psEndereco.setString(5, ((Aluno) param).getEndereco().getCidade().getNome());
				psEndereco.setString(6, ((Aluno) param).getEndereco().getCidade().getEstado().getUf());
				psEndereco.setString(7, ((Aluno) param).getEndereco().getCep());
				rs = psEndereco.executeQuery();
				rs.next();
				
				psPessoa.setString(1, ((Aluno) param).getTelefone());
				psPessoa.setString(2, ((Aluno) param).getEmail());
				psPessoa.setInt(3, rs.getInt("codigo"));
				psPessoa.setBoolean(4, true);
				psPessoa.executeUpdate();
				
				psPessoaFisica.setString(1, ((Aluno) param).getNome());
				psPessoaFisica.setString(2, ((Aluno) param).getSobrenome());
				psPessoaFisica.setString(3, ((Aluno) param).getCpf());
				psPessoaFisica.setString(4, ((Aluno) param).getRg());
				psPessoaFisica.setString(5, ((Aluno) param).getEstadoCivil());
				psPessoaFisica.setString(6, ((Aluno) param).getSexo());
				psPessoaFisica.setDate(7,  new Date(((Aluno) param).getDataNascimento().getTime()));
				psPessoaFisica.setInt(8,((Aluno) param).getNivelAcesso());
				psPessoaFisica.executeUpdate();
				
				psAluno.setString(1, ((Aluno) param).getMatricula());
				psAluno.executeUpdate();
			
				}
				
				conn.commit();
				conn.close();
				
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Aluno\n" + ex.getMessage());
		}

	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		Aluno aluno = new Aluno();
		DAOAluno dao = new DAOAluno();
		aluno = dao.getByCodigo(((Aluno) param).getCodigoAluno());
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psPessoa = conn.prepareStatement("UPDATE pessoa SET email = ?, telefone = ?, endereco_codigo = ? WHERE (codigo = ?)");
			PreparedStatement psPessoaFisica = conn.prepareStatement("UPDATE pessoa_fisica SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, estado_civil = ?, sexo = ?, data_nascimento = ?  WHERE (codigo = ?)");
			PreparedStatement psAluno = conn.prepareStatement("UPDATE aluno SET matricula = ? WHERE (codigo = ?)");
			
			psPessoa.setString(1, ((Aluno) param).getEmail());
			psPessoa.setString(2, ((Aluno) param).getTelefone());
			psPessoa.setInt(3, ((Aluno) param).getEndereco().getCodigo());
			psPessoa.setInt(4, aluno.getCodigoPessoa());
			psPessoa.executeUpdate();
			
			psPessoaFisica.setString(1, ((Aluno) param).getNome());
			psPessoaFisica.setString(2, ((Aluno) param).getSobrenome());
			psPessoaFisica.setString(3, ((Aluno) param).getCpf());
			psPessoaFisica.setString(4, ((Aluno) param).getRg());
			psPessoaFisica.setString(5, ((Aluno) param).getEstadoCivil());
			psPessoaFisica.setString(6, ((Aluno) param).getSexo());
			psPessoaFisica.setDate(7,  new Date(((Aluno) param).getDataNascimento().getTime()));
			psPessoaFisica.setInt(8, (aluno.getCodigoPessoaFisica()));
			psPessoaFisica.executeUpdate();
			
			psAluno.setString(1, ((Aluno) param).getMatricula());
			psAluno.setInt(2, aluno.getCodigoAluno());
			psAluno.executeUpdate();
			
			
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar aluno\n" + ex.getMessage());
		}
	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		PreparedStatement psIntegridade= conn.prepareStatement("SELECT * FROM usuario u WHERE (u.pessoa_fisica_codigo = ?)");
		psIntegridade.setInt(1, ((Aluno) param).getCodigoPessoaFisica());
		ResultSet rs = psIntegridade.executeQuery();
		if (rs.next())
		{
			throw new Exception ("- Impossível deletar aluno. Delete primeiro o usuário vinculado a ele.");
		}
		else {
			try {
				conn.setAutoCommit(false);
				Aluno aluno = new Aluno();
				DAOAluno dao = new DAOAluno();
				aluno = dao.getByCodigo(((Aluno) param).getCodigoAluno());
				PreparedStatement ps = conn.prepareStatement("UPDATE pessoa SET ativo = false WHERE (codigo = ?)");
				PreparedStatement psBoletim = conn.prepareStatement("DELETE aluno_turma WHERE (codigo_aluno = ?)");
				ps.setInt(1, aluno.getCodigoPessoa());
				ps.executeUpdate();
				psBoletim.executeUpdate();
			
				conn.commit();
				conn.close();
			}
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao deletar Aluno\n" + ex.getMessage());
			}
		}
	}

	@Override
	public ArrayList<Aluno> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aluno> al = new ArrayList<Aluno>();
		Aluno aluno = new Aluno();
		
		try {
			String sql = "SELECT pf.nome, pf.sobrenome FROM aluno al INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE (p.ativo = true)";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				aluno = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(aluno);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar alunos\n" + ex.getMessage());
			}
		
			return al;

	}
	
	public ArrayList<Aluno> listarPorCurso(String curso) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aluno> al = new ArrayList<Aluno>();
		Aluno aluno = new Aluno();
		
		try {
			String sql = "SELECT pf.nome, pf.sobrenome FROM aluno al INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo INNER JOIN aluno_curso ac ON al.codigo = ac.aluno_codigo WHERE (p.ativo = true AND ac.curso_codigo = (SELECT codigo FROM curso WHERE (nome = ?)))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, curso);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				aluno = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(aluno);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar alunos por curso\n" + ex.getMessage());
			}
		
			return al;

		}
	
	public ArrayList<Aluno> listarPorProfessor(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aluno> al = new ArrayList<Aluno>();
		Aluno aluno = new Aluno();
		
		try {
			String sql = "SELECT nome, sobrenome FROM aluno al INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo WHERE al.codigo IN (  SELECT atur.aluno_codigo FROM aluno_turma atur WHERE atur.turma_codigo IN (SELECT t.codigo FROM turma t WHERE t.professor_codigo = (SELECT pr.codigo FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE nome = ? AND sobrenome = ? AND p.ativo = true)))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, sobrenome);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				aluno = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(aluno);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar alunos por professor\n" + ex.getMessage());
			}
		
			return al;

		}
	
	public ArrayList<Aluno> listarPorDisciplina(String disciplina) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aluno> al = new ArrayList<Aluno>();
		Aluno aluno = new Aluno();
		
		try {
			String sql = "SELECT nome, sobrenome FROM aluno al INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo WHERE al.codigo IN (  SELECT atur.aluno_codigo FROM aluno_turma atur WHERE atur.turma_codigo IN (SELECT t.codigo FROM turma t WHERE t.disciplina_codigo = (SELECT codigo FROM disciplina  WHERE nome = ? AND ativo = true)))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, disciplina);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				aluno = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(aluno);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar alunos por disciplina\n" + ex.getMessage());
			}
		
			return al;

		}

	@Override
	public ArrayList<Aluno> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aluno> al = new ArrayList<Aluno>();
		Aluno aluno = new Aluno();
		
		try {
			String sql = "SELECT * FROM aluno al INNER JOIN pessoa_fisica pf ON al.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE (ativo = true AND (al.codigo like '%" + valor + "%' or pf.nome like '%" + valor + "%' or pf.sobrenome like '%" + valor + "%' or pf.cpf like '%" + valor + "%' or pf.rg like '%" + valor + "%' or pf.estado_civil like '%" + valor + "%' or pf.sexo like '%" + valor + "%' or pf.data_nascimento like '%" + valor + "%' or p.telefone like '%" + valor + "%' or p.email like '%" + valor + "%'))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				aluno = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(aluno);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar alunos\n" + ex.getMessage());
			}
		
			return al;
	}
	
}
