package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.com.carpediem.modelo.Aluno;
import br.com.carpediem.modelo.Boletim;
import br.com.carpediem.modelo.Falta;
import br.com.carpediem.modelo.Professor;
import br.com.carpediem.modelo.Turma;

public class DAOBoletim extends DAO implements IDAO {
	
private Connection conn;
	
	public DAOBoletim() {
		super();
	}
	
	public Boletim getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM aluno_turma WHERE (codigo = ?)";
		String sqlFaltas = "SELECT * FROM falta f INNER JOIN aula au ON f.aula_codigo = au.codigo INNER JOIN turma t ON au.turma_codigo = t.codigo WHERE (f.aluno_codigo IN (SELECT aluno_codigo FROM aluno_turma at WHERE at.codigo = ?) AND turma_codigo IN (SELECT turma_codigo FROM aluno_turma at WHERE at.codigo = ? ))";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement psFaltas = conn.prepareStatement(sqlFaltas);
			psFaltas.setInt(1, codigo);
			psFaltas.setInt(2, codigo);
			ResultSet rsFaltas = psFaltas.executeQuery();
			
			Locale.setDefault(Locale.US);
			DecimalFormat df = new DecimalFormat("0.0");

			if (rs.next())
			{
				Boletim boletim = new Boletim();
				Aluno aluno = new Aluno();
				DAOAluno daoal = new DAOAluno();
				aluno = daoal.getByCodigo(rs.getInt("aluno_codigo"));
				Turma turma = new Turma ();
				DAOTurma daot = new DAOTurma ();
				turma = daot.getByCodigo(rs.getInt("turma_codigo"));
				
				boletim.setCodigoBoletim(rs.getInt("codigo"));
				boletim.setAluno(aluno);
				boletim.setTurma(turma);
				boletim.setVA1(Float.parseFloat(df.format(rs.getDouble("va1"))));
				boletim.setVA2(Float.parseFloat(df.format(rs.getDouble("va2"))));
				boletim.setVA3(Float.parseFloat(df.format(rs.getDouble("va3"))));
				boletim.setVA4(Float.parseFloat(df.format(rs.getDouble("va4"))));
				boletim.setVA5(Float.parseFloat(df.format(rs.getDouble("va5"))));
				
				ArrayList<Falta> al = new ArrayList<Falta>();
				while (rsFaltas.next())
				{
					Falta falta = new Falta();
					DAOFalta daof = new DAOFalta();
					falta = daof.getByCodigo(rsFaltas.getInt("codigo"));
					al.add(falta);
				}
				boletim.setFaltas(al);
				
				rsFaltas.last();
				int count = rsFaltas.getRow();
				boletim.setQuantidadeFalta(count);
				
				rs.close();
				ps.close(); 
				
				return boletim;
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
			throw new Exception ("- Erro ao obter Boletim por nome!\n" + ex.getMessage()); 
		}
	}
	
	public Boletim getByNome(Integer aluno_codigo, Integer turma_codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM aluno_turma WHERE (aluno_codigo = ? AND turma_codigo = ?)";
		String sqlFaltas = "SELECT * FROM falta WHERE (aluno_codigo = ? AND aula_codigo IN (SELECT codigo FROM aula WHERE turma_codigo = ?))";
		String sqlQuantFaltas = "SELECT * FROM falta  WHERE (aluno_codigo = ? AND aula_codigo IN (SELECT codigo FROM aula WHERE turma_codigo = ?)) ";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aluno_codigo);
			ps.setInt(2, turma_codigo);
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement psFaltas = conn.prepareStatement(sqlFaltas);
			psFaltas.setInt(1, aluno_codigo);
			psFaltas.setInt(2, turma_codigo);
			ResultSet rsFaltas = psFaltas.executeQuery();
			
			PreparedStatement psQuantFaltas = conn.prepareStatement(sqlQuantFaltas);
			psQuantFaltas.setInt(1, aluno_codigo);
			psQuantFaltas.setInt(2, turma_codigo);
			ResultSet rsQuantFaltas = psQuantFaltas.executeQuery();
			
			Locale.setDefault(Locale.US);
			DecimalFormat df = new DecimalFormat("0.0");

			if (rs.next())
			{
				Boletim boletim = new Boletim();
				Aluno aluno = new Aluno();
				DAOAluno daoal = new DAOAluno();
				aluno = daoal.getByCodigo(aluno_codigo);
				Turma turma = new Turma ();
				DAOTurma daot = new DAOTurma ();
				turma = daot.getByCodigo(turma_codigo);
				
				boletim.setCodigoBoletim(rs.getInt("codigo"));
				boletim.setAluno(aluno);
				boletim.setTurma(turma);
				boletim.setVA1(Float.parseFloat(df.format(rs.getDouble("va1"))));
				boletim.setVA2(Float.parseFloat(df.format(rs.getDouble("va2"))));
				boletim.setVA3(Float.parseFloat(df.format(rs.getDouble("va3"))));
				boletim.setVA4(Float.parseFloat(df.format(rs.getDouble("va4"))));
				boletim.setVA5(Float.parseFloat(df.format(rs.getDouble("va5"))));
				
				ArrayList<Falta> al = new ArrayList<Falta>();
				while (rsFaltas.next())
				{
					Falta falta = new Falta();
					DAOFalta daof = new DAOFalta();
					falta = daof.getByCodigo(rsFaltas.getInt("codigo"));
					al.add(falta);
				}
				boletim.setFaltas(al);
				
				rsQuantFaltas.last();
				int count = rsQuantFaltas.getRow();
				boletim.setQuantidadeFalta(count);
				
				rs.close();
				ps.close(); 
				
				return boletim;
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
			throw new Exception ("- Erro ao obter Boletim por nome!\n" + ex.getMessage()); 
		}
	}

	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT codigo, aluno_codigo, turma_codigo, va1, va2, va3, va4, va5 FROM aluno_turma WHERE (aluno_codigo = ? AND turma_codigo = ?)");
			ps.setInt(1, ((Boletim) param).getAluno().getCodigoAluno());
			ps.setInt(2, ((Boletim) param).getTurma().getCodigoTurma());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Boletim boletim = new Boletim();
				DAOBoletim daob = new DAOBoletim();
				boletim = daob.getByNome(((Boletim) param).getAluno().getCodigoAluno(), ((Boletim) param).getTurma().getCodigoTurma());
				Aluno aluno = new Aluno();
				DAOAluno daoal = new DAOAluno();
				aluno = daoal.getByNome(((Boletim) param).getAluno().getNome(), ((Boletim) param).getAluno().getSobrenome());
				Turma turma = new Turma();
				DAOTurma daot = new DAOTurma();
				turma = daot.getByCodigo(((Boletim) param).getTurma().getCodigoTurma());
				try {
					conn.setAutoCommit(false);
					PreparedStatement psBoletim = conn.prepareStatement("UPDATE aluno_turma SET aluno_codigo = ?, turma_codigo = ?, va1 = ?, va2 = ?, va3 = ?, va4 = ?, va5 = ? WHERE (codigo = ?)");
					psBoletim.setInt(1, aluno.getCodigoAluno());
					psBoletim.setInt(2, turma.getCodigoTurma());
					psBoletim.setFloat(3, ((Boletim) param).getVA1());
					psBoletim.setFloat(4, ((Boletim) param).getVA2());
					psBoletim.setFloat(5, ((Boletim) param).getVA3());
					psBoletim.setFloat(6, ((Boletim) param).getVA4());
					psBoletim.setFloat(7, ((Boletim) param).getVA5());
					psBoletim.setString(8, ((Boletim) param).getStatus());
					psBoletim.setInt(9, boletim.getCodigoBoletim());
					psBoletim.executeUpdate();
					
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar boletim\n" + ex.getMessage());
				}
			
			}
			else {
				PreparedStatement psBoletim = conn.prepareStatement("INSERT INTO aluno_turma (aluno_codigo, turma_codigo, va1, va2, va3, va4, va5) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				Aluno aluno = new Aluno();
				DAOAluno daoal = new DAOAluno();
				aluno = daoal.getByNome(((Boletim) param).getAluno().getNome(), ((Boletim) param).getAluno().getSobrenome());
				Turma turma = new Turma();
				DAOTurma daot = new DAOTurma();
				turma = daot.getByCodigo(((Boletim) param).getTurma().getCodigoTurma());
				
				psBoletim.setInt(1, aluno.getCodigoAluno());
				psBoletim.setInt(2, turma.getCodigoTurma());
				psBoletim.setFloat(3, ((Boletim) param).getVA1());
				psBoletim.setFloat(4, ((Boletim) param).getVA2());
				psBoletim.setFloat(5, ((Boletim) param).getVA3());
				psBoletim.setFloat(6, ((Boletim) param).getVA4());
				psBoletim.setFloat(7, ((Boletim) param).getVA5());
				psBoletim.setString(8, ((Boletim) param).getStatus());
				psBoletim.executeUpdate();
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Boletim\n" + ex.getMessage());
		}


	}

	@Override
	public void alterar(Object param) throws Exception {
		// TODO Auto-generated method stub
		
		conn = super.getConnection();
		Boletim boletim = new Boletim();
		DAOBoletim daob = new DAOBoletim();
		boletim = daob.getByNome(((Boletim) param).getAluno().getCodigoAluno(), ((Boletim) param).getTurma().getCodigoTurma());
		Aluno aluno = new Aluno();
		DAOAluno daoal = new DAOAluno();
		aluno = daoal.getByNome(((Boletim) param).getAluno().getNome(), ((Boletim) param).getAluno().getSobrenome());
		Turma turma = new Turma();
		DAOTurma daot = new DAOTurma();
		turma = daot.getByCodigo(((Boletim) param).getTurma().getCodigoTurma());
		try {
			PreparedStatement psBoletim = conn.prepareStatement("UPDATE aluno_turma SET aluno_codigo = ?, turma_codigo = ?, va1 = ?, va2 = ?, va3 = ?, va4 = ?, va5 = ? WHERE (codigo = ?)");
			PreparedStatement psFaltaDelete = conn.prepareStatement("DELETE FROM falta WHERE aluno_codigo = ? AND aula_codigo IN (SELECT codigo FROM aula WHERE turma_codigo =?)");
			PreparedStatement psFaltas = conn.prepareStatement("INSERT INTO falta VALUES (?, ?, ?)");
			PreparedStatement psFaltasSelect = conn.prepareStatement("SELECT codigo, justificativa, aluno_codigo, aula_codigo FROM falta WHERE aluno_codigo = ? AND aula_codigo IN (SELECT codigo FROM aula WHERE turma_codigo =?)");
			
			
			psBoletim.setInt(1, aluno.getCodigoAluno());
			psBoletim.setInt(2, turma.getCodigoTurma());
			psBoletim.setFloat(3, ((Boletim) param).getVA1());
			psBoletim.setFloat(4, ((Boletim) param).getVA2());
			psBoletim.setFloat(5, ((Boletim) param).getVA3());
			psBoletim.setFloat(6, ((Boletim) param).getVA4());
			psBoletim.setFloat(7, ((Boletim) param).getVA5());
			psBoletim.setString(8, ((Boletim) param).getStatus());
			psBoletim.setInt(9, boletim.getCodigoBoletim());
			psBoletim.executeUpdate();
			
			psFaltaDelete.setInt(1, aluno.getCodigoAluno());
			psFaltaDelete.setInt(2, turma.getCodigoTurma());
			psFaltaDelete.executeUpdate();
			
			ResultSet rs;
			for (int cont = 0; cont <= (((Boletim) param).getFaltas().size() - 1); cont++)
			{
				psFaltasSelect.setInt(1, boletim.getFaltas().get(cont).getAluno().getCodigoAluno());
				psFaltasSelect.setInt(2, boletim.getFaltas().get(cont).getAula().getTurma().getCodigoTurma());
				rs = psFaltasSelect.executeQuery();
				rs.next();
				psFaltas.setString(1, rs.getString("justificativa"));
				psFaltas.setInt(2, rs.getInt("aula_codigo"));
				psFaltas.setInt(3, rs.getInt("aluno_codigo"));
				
				psFaltas.execute();
				
			}

		}
		catch (Exception ex)
		{
			throw new Exception ("Erro ao alterar boletim\n" + ex.getMessage());
		}
	
	}

	@Override
	public void deletar(Object param) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Boletim> listar() throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Boletim> al = new ArrayList<Boletim>();
		Boletim boletim = new Boletim();
		
		try {
			String sql = "SELECT * FROM aluno_turma";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					boletim = getByNome(rs.getInt("aluno_codigo"), rs.getInt("turma_codigo"));
					al.add(boletim);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar boletins\n" + ex.getMessage());
			}
		
			return al;
	}
	
	public ArrayList<Boletim> listar(Integer turma) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Boletim> al = new ArrayList<Boletim>();
		Boletim boletim = new Boletim();
		
		try {
			String sql = "SELECT * FROM aluno_turma WHERE turma_codigo = ?";
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, turma);
			ResultSet rs = ps.executeQuery();
		
				while (rs.next())
				{
					boletim = getByNome(rs.getInt("aluno_codigo"), rs.getInt("turma_codigo"));
					al.add(boletim);
				}
				
			}
	
			catch (Exception ex)
			{
				throw new Exception ("- Erro ao listar boletins\n" + ex.getMessage());
			}
		
			return al;
	}

	@Override
	public ArrayList<Boletim> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
