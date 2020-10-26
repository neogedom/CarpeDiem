package br.com.carpediem.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Disciplina;
import br.com.carpediem.modelo.Endereco;
import br.com.carpediem.modelo.Professor;

public class DAOProfessor extends DAO implements IDAO {
	
	private Connection conn;
	
	public DAOProfessor () {
		super();
	}
	
	public Professor getByNome(String nome, String sobrenome) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlProfessor = "SELECT p.codigo as codigo_pessoa, pf.codigo as codigo_pessoa_fisica, pr.codigo as codigo_professor, " +
				"pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.nivel_acesso, p.email, p.telefone, p.endereco_codigo, p.ativo FROM " +
				"professor pr INNER JOIN pessoa_fisica pf ON pf.codigo = pr.pessoa_fisica_codigo " +
				"INNER JOIN pessoa p ON p.codigo = pf.pessoa_codigo WHERE pf.nome = ? and pf.sobrenome = ?";


		String sqlDisciplinas = "SELECT d.codigo, d.nome, d.ativo FROM disciplina d, professor pr, " +
				"professor_disciplina pd, pessoa_fisica pf WHERE " +
				"(pd.disciplina_codigo = d.codigo and pd.professor_codigo = pr.codigo and " +
				"pr.pessoa_fisica_codigo = pf.codigo and pf.nome = ? and pf.sobrenome = ?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psProfessor = conn.prepareStatement(sqlProfessor);
			psProfessor.setString(1, nome);
			psProfessor.setString(2, sobrenome);
			ResultSet rsProfessor = psProfessor.executeQuery();
			PreparedStatement psDisciplinas = conn.prepareStatement(sqlDisciplinas);
			psDisciplinas.setString(1, nome);
			psDisciplinas.setString(2, sobrenome);
			ResultSet rsDisciplinas = psDisciplinas.executeQuery();
			if (rsProfessor.next()) 
			{
				Professor professor = new Professor();
				Endereco endereco = new Endereco();
				DAOEndereco daoe = new DAOEndereco();
				professor.setCodigoProfessor(rsProfessor.getInt("codigo_professor"));
				professor.setCodigoPessoaFisica(rsProfessor.getInt("codigo_pessoa_fisica"));
				professor.setCodigoPessoa(rsProfessor.getInt("codigo_pessoa"));
				professor.setNome(rsProfessor.getString("nome"));
				professor.setSobrenome(rsProfessor.getString("sobrenome"));
				professor.setCpf(rsProfessor.getString("cpf"));
				professor.setRg(rsProfessor.getString("rg"));
				professor.setEstadoCivil(rsProfessor.getString("estado_civil"));
				professor.setSexo(rsProfessor.getString("sexo"));
				professor.setDataNascimento(rsProfessor.getDate("data_nascimento"));
				professor.setTelefone(rsProfessor.getString("telefone"));
				professor.setEmail(rsProfessor.getString("email"));
				endereco = daoe.getByCodigo(rsProfessor.getInt("endereco_codigo"));
				professor.setEndereco(endereco);
				professor.setAtivo(rsProfessor.getBoolean("ativo"));
				ArrayList<Disciplina> al = new ArrayList<Disciplina>();
				while (rsDisciplinas.next())
				{
					Disciplina disciplina = new Disciplina();
					disciplina.setCodigo(rsDisciplinas.getInt("codigo"));
					disciplina.setNome(rsDisciplinas.getString("nome"));
					disciplina.setAtivo(rsDisciplinas.getBoolean("ativo"));
					al.add(disciplina);
					
				}
				professor.setDisciplinas(al);
				rsProfessor.close();
				rsDisciplinas.close();
				psProfessor.close();
				psDisciplinas.close();
				conn.close();
				
				return professor;
			}
			else 
			{
				rsProfessor.close();
				rsDisciplinas.close();
				psProfessor.close();
				psDisciplinas.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Professor por nome!\n" + ex.getMessage()) ;
		}

	}
	
	public Professor getByCodigo (Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sqlProfessor = "SELECT p.codigo as codigo_pessoa, pf.codigo as codigo_pessoa_fisica, pr.codigo as codigo_professor, " +
				"pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.nivel_acesso, p.email, p.telefone, p.endereco_codigo, p.ativo FROM " +
				"professor pr INNER JOIN pessoa_fisica pf ON pf.codigo = pr.pessoa_fisica_codigo " +
				"INNER JOIN pessoa p ON p.codigo = pf.pessoa_codigo WHERE pr.codigo = ?";


		String sqlDisciplinas = "SELECT d.codigo, d.nome, d.ativo FROM disciplina d, professor pr, " +
				"professor_disciplina pd, pessoa_fisica pf WHERE " +
				"(pd.disciplina_codigo = d.codigo and pd.professor_codigo = pr.codigo and " +
				"pr.pessoa_fisica_codigo = pf.codigo and pf.codigo = ?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement psProfessor = conn.prepareStatement(sqlProfessor);
			psProfessor.setInt(1, codigo);
			ResultSet rsProfessor = psProfessor.executeQuery();
			PreparedStatement psDisciplinas = conn.prepareStatement(sqlDisciplinas);
			psDisciplinas.setInt(1, codigo);
			ResultSet rsDisciplinas = psDisciplinas.executeQuery();
			if (rsProfessor.next()) 
			{
				Professor professor = new Professor();
				Endereco endereco = new Endereco();
				DAOEndereco daoe = new DAOEndereco();
				professor.setCodigoProfessor(rsProfessor.getInt("codigo_professor"));
				professor.setCodigoPessoaFisica(rsProfessor.getInt("codigo_pessoa_fisica"));
				professor.setCodigoPessoa(rsProfessor.getInt("codigo_pessoa"));
				professor.setNome(rsProfessor.getString("nome"));
				professor.setSobrenome(rsProfessor.getString("sobrenome"));
				professor.setCpf(rsProfessor.getString("cpf"));
				professor.setRg(rsProfessor.getString("rg"));
				professor.setEstadoCivil(rsProfessor.getString("estado_civil"));
				professor.setSexo(rsProfessor.getString("sexo"));
				professor.setDataNascimento(rsProfessor.getDate("data_nascimento"));
				professor.setTelefone(rsProfessor.getString("telefone"));
				professor.setEmail(rsProfessor.getString("email"));
				endereco = daoe.getByCodigo(rsProfessor.getInt("endereco_codigo"));
				professor.setEndereco(endereco);
				professor.setAtivo(rsProfessor.getBoolean("ativo"));
				ArrayList<Disciplina> al = new ArrayList<Disciplina>();
				while (rsDisciplinas.next())
				{
					Disciplina disciplina = new Disciplina();
					disciplina.setCodigo(rsDisciplinas.getInt("codigo"));
					disciplina.setNome(rsDisciplinas.getString("nome"));
					disciplina.setAtivo(rsDisciplinas.getBoolean("ativo"));
					al.add(disciplina);
					
				}
				professor.setDisciplinas(al);
				rsProfessor.close();
				rsDisciplinas.close();
				psProfessor.close();
				psDisciplinas.close();
				conn.close();
				
				return professor;
			}
			else 
			{
				rsProfessor.close();
				rsDisciplinas.close();
				psProfessor.close();
				psDisciplinas.close();
				conn.close();
				
				return null;
			}
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception ("- Erro ao obter Professor por nome!\n" + ex.getMessage()) ;
		}

	}
	
	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT pr.codigo as codigo_professor, pf.codigo as codigo_pessoa_fisica, pf.nome, pf.sobrenome, pf.cpf, pf.rg, pf.estado_civil, pf.sexo, pf.data_nascimento, pf.pessoa_codigo, pf.nivel_acesso FROM professor pr, pessoa_fisica pf WHERE (pf.nome = ? and pr.pessoa_fisica_codigo = pf.codigo)");
			ps.setString(1, ((Professor) param).getNome());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Professor professor = new Professor();
				DAOProfessor dao = new DAOProfessor();
				professor = dao.getByNome(((Professor) param).getNome(), ((Professor) param).getSobrenome());
				DAOEndereco daoe = new DAOEndereco();
				Endereco endereco = new Endereco();
				endereco = daoe.get(((Professor) param).getEndereco().getRua(), ((Professor) param).getEndereco().getBairro(), ((Professor) param).getEndereco().getNumero(), ((Professor) param).getEndereco().getCep());
				
				try {
					PreparedStatement psPessoa = conn.prepareStatement("UPDATE pessoa SET email = ?, telefone = ?, endereco_codigo = ?, ativo = true WHERE (codigo = ?)");
					PreparedStatement psPessoaFisica = conn.prepareStatement("UPDATE pessoa_fisica SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, estado_civil = ?, sexo = ?, data_nascimento = ?  WHERE (codigo = ?)");
					PreparedStatement psDisciplinaSelect = conn.prepareStatement("SELECT codigo FROM disciplina WHERE nome = ?");
					PreparedStatement psProfessorDisciplina = conn.prepareStatement("INSERT INTO professor_disciplina (professor_codigo, disciplina_codigo) VALUES (?, ?)");
					PreparedStatement psProfessorDisciplinaDelete = conn.prepareStatement("DELETE FROM professor_disciplina WHERE professor_codigo = ?");
							
					psPessoa.setString(1, ((Professor) param).getEmail());
					psPessoa.setString(2, ((Professor) param).getTelefone());
					psPessoa.setInt(3, endereco.getCodigo());
					psPessoa.setInt(4, professor.getCodigoPessoa());
					psPessoa.executeUpdate();
					
					psPessoaFisica.setString(1, ((Professor) param).getNome());
					psPessoaFisica.setString(2, ((Professor) param).getSobrenome());
					psPessoaFisica.setString(3, ((Professor) param).getCpf());
					psPessoaFisica.setString(4, ((Professor) param).getRg());
					psPessoaFisica.setString(5, ((Professor) param).getEstadoCivil());
					psPessoaFisica.setString(6, ((Professor) param).getSexo());
					psPessoaFisica.setDate(7,  new Date(((Professor) param).getDataNascimento().getTime()));
					psPessoaFisica.setInt(8, (professor.getCodigoPessoaFisica()));
					psPessoaFisica.executeUpdate();
					
					psProfessorDisciplinaDelete.setInt(1, professor.getCodigoProfessor());
					psProfessorDisciplinaDelete.executeUpdate();
					
					ResultSet rsExiste;
					for (int cont = 0; cont <= (((Professor) param).getDisciplinas().size() - 1); cont++)
					{
						psProfessorDisciplina.setInt(1, professor.getCodigoProfessor());
						
						psDisciplinaSelect.setString(1, ((Professor) param).getDisciplinas().get(cont).getNome());
						rsExiste = psDisciplinaSelect.executeQuery();
						rsExiste.next();
						psProfessorDisciplina.setInt(2, rsExiste.getInt("codigo"));
						
						psProfessorDisciplina.execute();
						
					}
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar professor\n" + ex.getMessage());
				}
			
			}
			else {
				PreparedStatement psPessoa = conn.prepareStatement("INSERT INTO pessoa (telefone, email, endereco_codigo, ativo) VALUES (?, ?, ?, ?)");
				PreparedStatement psPessoaFisica = conn.prepareStatement("INSERT INTO pessoa_fisica (nome, sobrenome, cpf, rg, estado_civil, sexo, data_nascimento, pessoa_codigo, nivel_acesso) VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT MAX(codigo) FROM pessoa), ?)");
				PreparedStatement psProfessor = conn.prepareStatement("INSERT INTO professor ( pessoa_fisica_codigo) VALUES ((SELECT MAX(codigo) FROM pessoa_fisica))");
				PreparedStatement psEndereco = conn.prepareStatement("SELECT codigo FROM endereco WHERE (rua = ? AND numero = ? AND bairro = ? AND complemento = ? AND cidade_codigo = (SELECT cod_cidade FROM cidade WHERE (nom_cidade = ? AND cod_estado = (SELECT cod_estado FROM estado WHERE (sgl_estado = ?)))) AND cep = ?)");
				PreparedStatement psProfessorDisciplina = conn.prepareStatement("INSERT INTO professor_disciplina (professor_codigo, disciplina_codigo) VALUES ((SELECT MAX(codigo) FROM professor), ?)");

				psEndereco.setString(1, ((Professor) param).getEndereco().getRua());
				psEndereco.setString(2, ((Professor) param).getEndereco().getNumero());
				psEndereco.setString(3, ((Professor) param).getEndereco().getBairro());
				psEndereco.setString(4, ((Professor) param).getEndereco().getComplemento());
				psEndereco.setString(5, ((Professor) param).getEndereco().getCidade().getNome());
				psEndereco.setString(6, ((Professor) param).getEndereco().getCidade().getEstado().getUf());
				psEndereco.setString(7, ((Professor) param).getEndereco().getCep());
				rs = psEndereco.executeQuery();
				rs.next();
				
				psPessoa.setString(1, ((Professor) param).getTelefone());
				psPessoa.setString(2, ((Professor) param).getEmail());
				psPessoa.setInt(3, rs.getInt("codigo"));
				psPessoa.setBoolean(4, true);
				psPessoa.executeUpdate();
				
				psPessoaFisica.setString(1, ((Professor) param).getNome());
				psPessoaFisica.setString(2, ((Professor) param).getSobrenome());
				psPessoaFisica.setString(3, ((Professor) param).getCpf());
				psPessoaFisica.setString(4, ((Professor) param).getRg());
				psPessoaFisica.setString(5, ((Professor) param).getEstadoCivil());
				psPessoaFisica.setString(6, ((Professor) param).getSexo());
				psPessoaFisica.setDate(7,  new Date(((Professor) param).getDataNascimento().getTime()));
				psPessoaFisica.setInt(8,((Professor) param).getNivelAcesso());
				psPessoaFisica.executeUpdate();
				
				psProfessor.executeUpdate();
				
			
				Disciplina disciplina = new Disciplina();
				DAODisciplina daod = new DAODisciplina();
				for (int cont = 0; cont <= (((Professor) param).getDisciplinas().size() - 1); cont++)
				{
					disciplina = daod.getByNome( ((Professor) param).getDisciplinas().get(cont).getNome());
					psProfessorDisciplina.setInt(1, disciplina.getCodigoDisciplina());
					
					psProfessorDisciplina.execute();
					
				}
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Professor\n" + ex.getMessage());
		}


	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		PreparedStatement psIntegridade= conn.prepareStatement("SELECT * FROM usuario u, turma t, sala s WHERE (u.pessoa_fisica_codigo = ? OR t.professor_codigo = ? OR s.professor_codigo = ?)");
		psIntegridade.setInt(1, ((Professor) param).getCodigoPessoaFisica());
		psIntegridade.setInt(2, ((Professor) param).getCodigoProfessor());
		psIntegridade.setInt(3, ((Professor) param).getCodigoProfessor());
		ResultSet rs = psIntegridade.executeQuery();
		if (rs.next())
		{
			throw new Exception ("- Impossível deletar professor. Delete primeiro os dados vinculado a ele (usuário, turma, sala)");
		}
		else {
			try {
				conn.setAutoCommit(false);
				Professor professor = new Professor();
				DAOProfessor dao = new DAOProfessor();
				professor = dao.getByCodigo(((Professor) param).getCodigoProfessor());
				PreparedStatement ps = conn.prepareStatement("UPDATE pessoa SET ativo = false WHERE (codigo = ?)");
				ps.setInt(1, professor.getCodigoPessoa());
				ps.executeUpdate();
				
				conn.commit();
				conn.close();
			}
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao deletar Professor\n" + ex.getMessage());
			}
		}
	}

	@Override
	public ArrayList<Professor> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Professor> al = new ArrayList<Professor>();
		Professor professor = new Professor();
		
		try {
			String sql = "SELECT pf.nome, pf.sobrenome FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE (p.ativo = true)";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				professor = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(professor);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar professores\n" + ex.getMessage());
			}
		
			return al;

		}
	
	public ArrayList<Professor> listarPorCurso(String curso) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Professor> al = new ArrayList<Professor>();
		Professor professor = new Professor();
		
		try {
			String sql = "SELECT pf.nome, pf.sobrenome FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo INNER JOIN professor_curso pc ON pr.codigo = pc.professor_codigo WHERE (p.ativo = true AND pc.curso_codigo = (SELECT codigo FROM curso WHERE (nome = ?)))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, curso);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				professor = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(professor);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar professores por curso\n" + ex.getMessage());
			}
		
			return al;

		}
	
	public ArrayList<Professor> listarPorDisciplina(String disciplina) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Professor> al = new ArrayList<Professor>();
		Professor professor = new Professor();
		
		try {
			String sql = "SELECT pf.nome, pf.sobrenome FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo INNER JOIN professor_disciplina pd ON pr.codigo = pd.professor_codigo WHERE (p.ativo = true AND pd.disciplina_codigo = (SELECT codigo FROM disciplina WHERE (nome = ?)))";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, disciplina);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				professor = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(professor);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar professores por disciplina\n" + ex.getMessage());
			}
		
			return al;

		}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		Professor professor = new Professor();
		DAOProfessor dao = new DAOProfessor();
		professor = dao.getByCodigo(((Professor) param).getCodigoProfessor());
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement psPessoa = conn.prepareStatement("UPDATE pessoa SET email = ?, telefone = ?, endereco_codigo = ? WHERE (codigo = ?)");
			PreparedStatement psPessoaFisica = conn.prepareStatement("UPDATE pessoa_fisica SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, estado_civil = ?, sexo = ?, data_nascimento = ?  WHERE (codigo = ?)");
			PreparedStatement psProfessorDisciplina = conn.prepareStatement("INSERT INTO professor_disciplina VALUES (?, ?)");
			PreparedStatement psProfessorDisciplinaDelete = conn.prepareStatement("DELETE FROM professor_disciplina WHERE professor_codigo = ?");
				
			psPessoa.setString(1, ((Professor) param).getEmail());
			psPessoa.setString(2, ((Professor) param).getTelefone());
			psPessoa.setInt(3, ((Professor) param).getEndereco().getCodigo());
			psPessoa.setInt(4, professor.getCodigoPessoa());
			psPessoa.executeUpdate();
			
			psPessoaFisica.setString(1, ((Professor) param).getNome()); 
			psPessoaFisica.setString(2, ((Professor) param).getSobrenome());
			psPessoaFisica.setString(3, ((Professor) param).getCpf());
			psPessoaFisica.setString(4, ((Professor) param).getRg());
			psPessoaFisica.setString(5, ((Professor) param).getEstadoCivil());
			psPessoaFisica.setString(6, ((Professor) param).getSexo());
			psPessoaFisica.setDate(7,  new Date(((Professor) param).getDataNascimento().getTime()));
			psPessoaFisica.setInt(8, (professor.getCodigoPessoaFisica()));
			psPessoaFisica.executeUpdate();
			
			psProfessorDisciplinaDelete.setInt(1, ((Professor) param).getCodigoProfessor());
			psProfessorDisciplinaDelete.executeUpdate();
			
			Disciplina disciplina = new Disciplina();
			DAODisciplina daod = new DAODisciplina();
			for (int cont = 0; cont <= (((Professor) param).getDisciplinas().size() - 1); cont++)
			{
				psProfessorDisciplina.setInt(1, ((Professor) param).getCodigoProfessor());
				
				disciplina = daod.getByNome( ((Professor) param).getDisciplinas().get(cont).getNome());
				psProfessorDisciplina.setInt(2, disciplina.getCodigoDisciplina());
				
				psProfessorDisciplina.execute();
				
			}
			
			
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("Erro ao alterar professor\n" + ex.getMessage());
		}
	}

	@Override
	public ArrayList<Professor> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Professor> al = new ArrayList<Professor>();
		Professor professor = new Professor();
		
		try {
			String sql = "SELECT * FROM professor pr INNER JOIN pessoa_fisica pf ON pr.pessoa_fisica_codigo = pf.codigo INNER JOIN pessoa p ON pf.pessoa_codigo = p.codigo WHERE (ativo = true AND (pr.codigo like '%" + valor + "%' or pf.nome like '%" + valor + "%' or pf.sobrenome like '%" + valor + "%' or pf.cpf like '%" + valor + "%' or pf.rg like '%" + valor + "%' or pf.estado_civil like '%" + valor + "%' or pf.sexo like '%" + valor + "%' or pf.data_nascimento like '%" + valor + "%' or p.telefone like '%" + valor + "%' or p.email like '%" + valor + "%'))";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				professor = getByNome(rs.getString("nome"), rs.getString("sobrenome"));
				al.add(professor);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao buscar professores\n" + ex.getMessage());
			}
		
			return al;
	}
}
