package ua.nure.degtuaryov.db;

/**
 * Constants for the database connection.
 * 
 * @author Degtuaryow Dima
 *
 */
public class Constants {

	public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
	public static final String DB_URL_C = "jdbc:derby://localhost:1527/db/rzgi";
	public static final String DAO_FACTORY = "ua.nure.degtuaryov.db.dao.derby.DerbyDAOFactory";
	public static final String DB_DATA_SOURSE = "java:/comp/env/jdbc/db/rzgi";
	public static final String DB_LOGIN = "root";
	public static final String DB_PASSWORD = "root";

}
