package ua.nure.degtuaryov.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.<br/>
 * 
 * @author Degtuaryow
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login.do", new LoginCommand());
		commands.put("logout.do", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("viewLoginPage", new ViewLoginPageCommand());
		commands.put("viewRegisterPage", new ViewRegisterPageCommand());
		commands.put("register", new RegisterCommand());
		commands.put("viewAllClients", new ViewClientsListCommand());
		commands.put("changeClient", new EditClientCommand());
		commands.put("deleteClient.do", new DeleteClientCommand());
		commands.put("deleteClientPage", new ViewDeleteClientPageCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}