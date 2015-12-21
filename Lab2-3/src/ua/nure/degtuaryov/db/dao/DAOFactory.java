package ua.nure.degtuaryov.db.dao;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.db.Constants;
import ua.nure.degtuaryov.web.command.NoCommand;

/**
 * Abstract factory producing implementations DAOFactory. <br/>
 * With this class switching may be accomplished with one another by DB
 * 
 * @author Degtuaryow Dima
 * 
 */
public abstract class DAOFactory {

	private static final Logger LOG = Logger.getLogger(NoCommand.class);
	
	private static DAOFactory instance;

	/**
	 * he method of obtaining the object DAOFactory. What kind of realization
	 * DAOFactory will be returned to determine the factory settings.
	 * 
	 * @return instance descendant DAOFactory, whose name is contained in the
	 *         {@link #daoFactoryFCN}
	 */
	public static synchronized DAOFactory getInstance() {
		if (instance == null) {
			Class<?> clazz;
			try {
				Class.forName(Constants.DRIVER);
				clazz = Class.forName(Constants.DAO_FACTORY);
				instance = (DAOFactory) clazz.newInstance();
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}

		}
		return instance;
	}

	/**
	 * The constructor for the class to inherit. <br/>
	 */
	protected DAOFactory() {
	}

	/**
	 * Contains the fully qualified name of the class whose object is return
	 * {@link #getInstance()}
	 */
	private static String daoFactoryFCN;

	public static String getDaoFactoryFCN() {
		return daoFactoryFCN;
	}
	
	public static void setDaoFactoryFCN(String daoFactoryFCN) {
		instance = null;
		DAOFactory.daoFactoryFCN = daoFactoryFCN;
	}

	/**
	 * DAO method of obtaining an entity ClientDAO.
	 * 
	 * @return implementation ClientDAO, which define factory settings
	 *         {@link DAOFactory}.
	 */
	public abstract ClientDAO getClientDAO();

	

}
