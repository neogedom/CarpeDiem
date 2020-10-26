package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.carpediem.modelo.Aula;
import br.com.carpediem.modelo.Disciplina;
import br.com.carpediem.modelo.Turma;

public class DAOAula extends DAO implements IDAO {

private Connection conn;
	
	public DAOAula() {
		super();
	}
	
	public Aula getByNome(String horaInicial, String horaFinal, Integer codigoTurma) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM aula WHERE hora_inicial = ? AND hora_final = ? AND turma_codigo = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, horaInicial);
			ps.setString(2, horaFinal);
			ps.setInt(3, codigoTurma);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Aula aula = new Aula();
				Turma turma = new Turma();
				DAOTurma daot = new DAOTurma();
				turma = daot.getByCodigo(rs.getInt("turma_codigo"));
				aula.setCodigoAula(rs.getInt("codigo"));
				aula.setHoraFinal(rs.getString("hora_final"));
				aula.setData(rs.getDate("data"));
				aula.setHoraInicial(rs.getString("hora_inicial"));
				aula.setTurma(turma);
				
				rs.close();
				ps.close();
				return aula;
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
			throw new Exception ("- Erro ao obter Aula por nome!\n" + ex.getMessage()); 
		}
	}
	
	public Aula getByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		
		String sql = "SELECT * FROM aula WHERE codigo = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Aula aula = new Aula();
				Turma turma = new Turma();
				DAOTurma daot = new DAOTurma();
				turma = daot.getByCodigo(rs.getInt("turma_codigo"));
				aula.setCodigoAula(rs.getInt("codigo"));
				aula.setHoraFinal(rs.getString("hora_final"));
				aula.setData(rs.getDate("data"));
				aula.setHoraInicial(rs.getString("hora_inicial"));
				aula.setTurma(turma);
				
				rs.close();
				ps.close();
				return aula;
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
			throw new Exception ("- Erro ao obter Aula por código!\n" + ex.getMessage()); 
		}
	}

	@Override
	public void cadastrar(Object param) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM aula WHERE hora_inicial = ? AND hora_final = ? AND turma_codigo = ?");
			ps.setString(1, ((Aula) param).getHoraInicial() );
			ps.setString(2, ((Aula) param).getHoraFinal());
			ps.setInt(3, ((Aula) param).getTurma().getCodigoTurma());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				Aula aula = new Aula();
				DAOAula dao = new DAOAula();
				aula = dao.getByNome(((Aula) param).getHoraInicial(), ((Aula) param).getHoraFinal(), ((Aula) param).getTurma().getCodigoTurma());
				try {
					conn.setAutoCommit(false);
					PreparedStatement psAula = conn.prepareStatement("UPDATE aula SET hora_inicial = ?, hora_final = ?, data = ?, turma_codigo = ? WHERE (codigo = ?)");
					
					
					psAula.setString(1, ((Aula) param).getHoraInicial());
					psAula.setString(2, ((Aula) param).getHoraFinal());
					psAula.setDate(3, new Date(((Aula) param).getData().getTime()));
					psAula.setInt(4, ((Aula) param).getTurma().getCodigoTurma());
					psAula.setInt(5, aula.getCodigoAula());					
					psAula.executeUpdate();
					
					conn.commit();
				}
				catch (Exception ex)
				{
					conn.rollback();
					throw new Exception ("Erro ao alterar aula\n" + ex.getMessage());
				}
			
			}
			else {
				PreparedStatement psAula = conn.prepareStatement("INSERT INTO aula (hora_inicial, hora_final, data, turma_codigo) VALUES (?, ?, ?, ?)");
				
				psAula.setString(1, ((Aula) param).getHoraInicial());
				psAula.setString(2, ((Aula) param).getHoraFinal());
				psAula.setDate(3, new Date(((Aula) param).getData().getTime()));
				psAula.setInt(4, ((Aula) param).getTurma().getCodigoTurma());
				psAula.executeUpdate();
				
				
				conn.commit();
				conn.close();
			}
		} catch (Exception ex)
		{
			conn.rollback();
			throw new Exception ("- Erro ao cadastrar Aula\n" + ex.getMessage());
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
	public ArrayList<Aula> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Aula> listarPorTurma(Integer codigoTurma) throws Exception {
		// TODO Auto-generated method stub
		conn = super.getConnection();
		ArrayList<Aula> al = new ArrayList<Aula>();
		Aula aula = new Aula();
		
		try {
			String sql = "SELECT codigo FROM aula WHERE turma_codigo = ?";
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigoTurma);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next())
			{
				aula = getByCodigo(rs.getInt("codigo"));
				al.add(aula);
			}
		
			}
	
			catch (Exception ex)
			{
				throw new Exception ("Erro ao listar aulas por turma\n" + ex.getMessage());
			}
		
			return al;
	}
	
	@Override
	public ArrayList<Aula> buscar(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
