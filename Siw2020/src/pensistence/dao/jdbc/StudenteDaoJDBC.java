package pensistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.CorsoDiLaurea;
import model.Scuola;
import model.Studente;
import pensistence.DBManager;
import pensistence.DataSource;
import pensistence.dao.StudenteDao;


public class StudenteDaoJDBC implements StudenteDao {
	private DataSource dataSource;

	public StudenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Studente studente) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "insert into studente(matricola, nome, cognome,"
					+ " datanascita, scuola, corsodilaurea) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, studente.getMatricola());
			statement.setString(2, studente.getNome());
			statement.setString(3, studente.getCognome());
			statement.setString(4, studente.getDataNascita());
			statement.setLong(5, studente.getScuolaDiDiploma().getId());
//			statement.setLong(5, studente.get.getScuolaDiDiploma().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}  

	public Studente findByPrimaryKey(String matricola) {
		Connection connection = null;
		Studente studente = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from studente where matricola = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				studente = new Studente();
				studente.setMatricola(result.getString("matricola"));				
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				studente.setDataNascita(result.getString("datanascita"));
				
				Scuola scuola = DBManager.getInstance().getScuolaDAO().findByPrimaryKey(result.getLong("scuola"));
				studente.setScuolaDiDiploma(scuola);
				
				CorsoDiLaurea corsoDiLaurea = DBManager.getInstance().getCorsoDiLaureaDAO().findByPrimaryKey(result.getLong("corsodilaurea"));
				studente.setCorsoDiLaurea(corsoDiLaurea);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
		return studente;
	}
	

	public List<Studente> findAll() {
		Connection connection = null;
		List<Studente> studenti = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Studente studente;
			PreparedStatement statement;
			String query = "select * from studente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				studente = new Studente();
				studente.setMatricola(result.getString("matricola"));				
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				studente.setDataNascita(result.getString("datanascita"));
				
				Scuola scuola = DBManager.getInstance().getScuolaDAO().findByPrimaryKey(result.getLong("scuola"));
				studente.setScuolaDiDiploma(scuola);
				
				CorsoDiLaurea corsoDiLaurea = DBManager.getInstance().getCorsoDiLaureaDAO().findByPrimaryKey(result.getLong("corsodilaurea"));
				studente.setCorsoDiLaurea(corsoDiLaurea);
				
				studenti.add(studente);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return studenti;
	}

	public void update(Studente studente) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update studente SET nome = ?, cognome = ?, data_nascita = ?, scuola = ?, corsodilaurea = ? WHERE matricola=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, studente.getNome());
			statement.setString(2, studente.getCognome());
			statement.setString(3, studente.getDataNascita());			
			statement.setLong(4, studente.getScuolaDiDiploma().getId());
			statement.setLong(5, studente.getCorsoDiLaurea().getId());
			statement.setString(6, studente.getMatricola());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public void delete(Studente studente) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM studente WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, studente.getMatricola());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
}
