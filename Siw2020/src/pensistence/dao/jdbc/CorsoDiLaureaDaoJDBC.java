package pensistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CorsoDiLaurea;
import pensistence.DataSource;
import pensistence.dao.CorsoDiLaureaDao;


public class CorsoDiLaureaDaoJDBC implements CorsoDiLaureaDao {
	private DataSource dataSource;

	public CorsoDiLaureaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(CorsoDiLaurea corsoDiLaurea) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection);
			corsoDiLaurea.setId(id); 
			String insert = "insert into corsodilaurea(id, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corsoDiLaurea.getId());
			statement.setString(2, corsoDiLaurea.getNome());

			statement.executeUpdate();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch(SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			} 
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	
	

	/* 
	 * versione con Lazy Load
	 */
	public CorsoDiLaurea findByPrimaryKey(Long id) {
		CorsoDiLaurea corsoDiLaurea = null;
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from corsodilaurea where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				corsoDiLaurea = new CorsoDiLaurea();
				corsoDiLaurea.setId(result.getLong("id"));				
				corsoDiLaurea.setNome(result.getString("nome"));
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
		return corsoDiLaurea;
	}

	public List<CorsoDiLaurea> findAll() {
		Connection connection = null;
		List<CorsoDiLaurea> corsidilaurea = new ArrayList<>();
		try {			
			connection = this.dataSource.getConnection();
			CorsoDiLaurea corsoDiLaurea;
			PreparedStatement statement;
			String query = "select * from corsodilaurea";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				corsoDiLaurea = findByPrimaryKey(result.getLong("id"));
				corsidilaurea.add(corsoDiLaurea);
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
		return corsidilaurea;
	}

	public void update(CorsoDiLaurea corsodilaurea) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update corso SET nome = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, corsodilaurea.getNome());
			statement.setLong(2, corsodilaurea.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch(SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			} 
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public void delete(CorsoDiLaurea corsoDiLaurea) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete FROM corsodilaurea WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, corsoDiLaurea.getId());

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
