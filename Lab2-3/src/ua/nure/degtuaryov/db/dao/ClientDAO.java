package ua.nure.degtuaryov.db.dao;

import java.util.List;

import ua.nure.degtuaryov.db.entity.Client;

/**
 * Interface dao producing implementations ClientDAO
 * 
 * @author Degtuaryow
 *
 */
public interface ClientDAO {
	
	Client findClientByLogin(String login);
	
	Client findClientByLoginPass(String login, String password);
	
	Client findClientById(long id);
	
	List<Client> findAllClients();
	
	boolean addClient(Client client);
	
	boolean removeClient(long id);
	
	boolean updateClient(Client  client);

}
