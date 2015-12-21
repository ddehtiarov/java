package ua.nure.degtuaryov.db.dao.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Fields;
import ua.nure.degtuaryov.db.dao.ClientDAO;
import ua.nure.degtuaryov.db.entity.Client;
import ua.nure.degtuaryov.utils.Password;
import ua.nure.degtuaryov.web.command.NoCommand;

public class DerbyClientDAO implements ClientDAO {

	private static final String SQL_SELECT_ALL_CLIENTS = "SELECT * from CLIENT";
	private static final String SQL_SELECT_CLIENT_WITH_LOGIN_PASS = "SELECT * from CLIENT where login=? and password=?";
	private static final String SQL_SELECT_CLIENT_WITH_LOGIN = "SELECT * from CLIENT where login=?";
	private static final String SQL_INSERT_CLIENT = "INSERT INTO CLIENT VALUES (default, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_CLIENT = "UPDATE CLIENT SET login=?, password=?, email=?, firstname=?, surname=?, secondname=?,"
			+ " role_id=? WHERE id=?";
	private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT * FROM CLIENT WHERE id=?";
	private static final String SQL_DELETE_CLIENT_BY_ID = "DELETE FROM CLIENT WHERE id=?";
	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public Client findClientByLogin(String login) {
		Connection con = null;
		PreparedStatement statement = null;
		Client client = null;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_CLIENT_WITH_LOGIN);
			statement.setString(1, login);
			rs = statement.executeQuery();
			if (rs.next()) {
				client = obtainClient(rs);
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
			return null;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return client;
	}

	@Override
	public Client findClientByLoginPass(String login, String password) {
		Connection con = null;
		PreparedStatement statement = null;
		Client client = null;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_CLIENT_WITH_LOGIN_PASS);
			statement.setString(1, login.toLowerCase());
			statement.setString(2, Password.hash(password));
			rs = statement.executeQuery();
			if (rs.next()) {
				client = obtainClient(rs);
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
			return null;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return client;
	}

	@Override
	public Client findClientById(long id) {
		Connection con = null;
		PreparedStatement statement = null;
		Client client = null;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				client = obtainClient(rs);
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
			return null;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return client;
	}

	@Override
	public List<Client> findAllClients() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Client> result = new ArrayList<Client>();
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_SELECT_ALL_CLIENTS);
			rs = statement.executeQuery();
			while (rs.next()) {
				Client client = obtainClient(rs);
				result.add(client);
			}
		} catch (SQLException e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(connection, statement);
			LOG.error(e.getMessage());
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(connection, statement, rs);
		}
		return result;
	}

	@Override
	public boolean addClient(Client client) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_INSERT_CLIENT);
			int k = 1;
			statement.setString(k++, client.getLogin().toLowerCase());
			statement.setString(k++, Password.hash(client.getPassword()));
			statement.setString(k++, client.getEmail());
			statement.setString(k++, client.getFirstName());
			statement.setString(k++, client.getSurname());
			statement.setString(k++, client.getSecondName());
			statement.setLong(k++, client.getRoleId());
			statement.executeUpdate();
			statement.close();
		} catch (Exception ex) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(connection, statement);
			LOG.error(ex.getMessage());
			return false;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatment(connection, statement);
		}
		return true;
	}

	@Override
	public boolean removeClient(long id) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_DELETE_CLIENT_BY_ID);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			return false;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatment(con, statement);
		}
		return true;
	}

	@Override
	public boolean updateClient(Client client) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_UPDATE_CLIENT);
			int k = 1;
			statement.setString(k++, client.getLogin());
			if (client.getPassword().equals(findClientById(client.getId()).getPassword())) {
				statement.setString(k++, client.getPassword());
			} else {
				statement.setString(k++, Password.hash(client.getPassword()));
			}
			statement.setString(k++, client.getEmail());
			statement.setString(k++, client.getFirstName());
			statement.setString(k++, client.getSurname());
			statement.setString(k++, client.getSecondName());
			statement.setLong(k++, client.getRoleId());
			statement.setLong(k, client.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			return false;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatment(con, statement);
		}
		return true;
	}

	private Client obtainClient(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setId(resultSet.getLong(Fields.ENTITY_ID));
		client.setLogin(resultSet.getString(Fields.CLIENT_LOGIN));
		client.setPassword(resultSet.getString(Fields.CLIENT_PASSWORD));
		client.setEmail(resultSet.getString(Fields.CLIENT_EMAIL));
		client.setFirstName(resultSet.getString(Fields.CLIENT_FIRSTNAME));
		client.setSecondName(resultSet.getString(Fields.CLIENT_SECONDNAME));
		client.setSurname(resultSet.getString(Fields.CLIENT_SURNAME));
		client.setRoleId(resultSet.getLong(Fields.CLIENT_ROLE_ID));
		return client;
	}

}
