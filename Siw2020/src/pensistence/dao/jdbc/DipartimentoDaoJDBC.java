package pensistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.CorsoDiLaurea;
import model.Dipartimento;
import pensistence.DBManager;
import pensistence.DataSource;
import pensistence.dao.DipartimentoDao;

public class DipartimentoDaoJDBC implements DipartimentoDao {
	private DataSource dataSource;

	public DipartimentoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Dipartimento dipartimento) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection);
			dipartimento.setId(id);
			String insert = "insert into dipartimento(codice, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, dipartimento.getId());
			statement.setString(2, dipartimento.getNome());
			
			statement.executeUpdate();
			updateReference(dipartimento, connection);
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

	@Override
	public Dipartimento findByPrimaryKey(Long codice) {
		Connection connection = null;
		Dipartimento dipartimento = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select d.id as d_id, d.nome as d_nome "
					+ "from dipartimento d  "
					+ "where d.id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			boolean primaRiga = true;
			while (result.next()) {
				if (primaRiga) {
					dipartimento = new Dipartimento();
					dipartimento.setId(result.getLong("d_id"));				
					dipartimento.setNome(result.getString("d_nome"));
					dipartimento.setCorsiDiLaurea(corsiDiLaurea(dipartimento, connection));
					primaRiga = false;
				}
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
		return dipartimento;
	}

	@Override
	public List<Dipartimento> findAll() {
		Connection connection = null;
		List<Dipartimento> dipartimenti = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Dipartimento dipartimento;
			PreparedStatement statement;
			String query = "select * from dipartimento";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				dipartimento = new Dipartimento();
				dipartimento.setId(result.getLong("id"));				
				dipartimento.setNome(result.getString("nome"));
				dipartimento.setCorsiDiLaurea(corsiDiLaurea(dipartimento, connection));
				dipartimenti.add(dipartimento);
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
		return dipartimenti;
	}

	public void update(Dipartimento dipartimento) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update dipartimento SET nome = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, dipartimento.getNome());
			statement.setLong(2, dipartimento.getId());
			statement.executeUpdate();
			updateReference(dipartimento, connection);
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

	public void delete(Dipartimento dipartimento) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM dipartimento WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, dipartimento.getId());
			removeReference(dipartimento, connection);
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
	
	private void updateReference(Dipartimento dipartimento, Connection connection) throws SQLException {
		for (CorsoDiLaurea cdl : dipartimento.getCorsiDiLaurea()) {
			String esiste = "select id from afferisce where dipartimento=? AND corsodilaurea=?";
			PreparedStatement statement = connection.prepareStatement(esiste);
			statement.setLong(1, dipartimento.getId());
			statement.setLong(2, cdl.getId());
			ResultSet result = statement.executeQuery();
			if(!result.next()){					
				String inserisci = "insert into afferisce(id, corsodilaurea, dipartimento) values (?,?,?)";
				PreparedStatement st = connection.prepareStatement(inserisci);
				Long id = IdBroker.getId(connection);
				st.setLong(1, id);
				st.setLong(2, cdl.getId());
				st.setLong(3, cdl.getId());
				st.executeUpdate();
			}
		}
	}
	
	private void removeReference(Dipartimento dipartimento, Connection connection) throws SQLException {
		for (CorsoDiLaurea cdl : dipartimento.getCorsiDiLaurea()) {
			String esiste = "select id from afferisce where dipartimento=? AND corsodilaurea=?";
			PreparedStatement statement = connection.prepareStatement(esiste);
			statement.setLong(1, dipartimento.getId());
			statement.setLong(2, cdl.getId());
			ResultSet result = statement.executeQuery();
			if(result.next()){					
				String delete = "delete FROM afferisce WHERE id = ? ";
				PreparedStatement st = connection.prepareStatement(delete);
				st.setLong(1, result.getLong("id"));
				st.executeUpdate();
			}
		}
	}
	
	private List<CorsoDiLaurea> corsiDiLaurea(Dipartimento dip, Connection connection){
		List<CorsoDiLaurea> cdls = new ArrayList<CorsoDiLaurea>();
		PreparedStatement statement;
		String query = "select * from afferisce where dipartimento = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, dip.getId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				CorsoDiLaurea cdl = DBManager.getInstance().getCorsoDiLaureaDAO().findByPrimaryKey(result.getLong(1));
				cdls.add(cdl);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return cdls;
	}

}
