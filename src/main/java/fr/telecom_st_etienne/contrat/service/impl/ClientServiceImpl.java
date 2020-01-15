package fr.telecom_st_etienne.contrat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom_st_etienne.contrat.business.Client;
import fr.telecom_st_etienne.contrat.business.Contrat;
import fr.telecom_st_etienne.contrat.dao.ClientDao;
import fr.telecom_st_etienne.contrat.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	private ClientDao clientDAO;
	
	@Override
	public Client ajouterClient(Contrat contrat, String nom,String identifiant, String mdp, Boolean admin) {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.setContrat(contrat);
		client.setNom(nom);
		client.setIdentifiant(identifiant);
		client.setMdp(mdp);
		client.setAdmin(admin);
		clientDAO.save(client);
		return client;
	}

	@Override
	public List<Client> recupererClients() {
		// TODO Auto-generated method stub
		return clientDAO.findAll();
	}

	@Override
	public Client recupererClient(Long id) {
		// TODO Auto-generated method stub
		return clientDAO.findOne(id);
	}
	
	public Client recupererClient(String identifiant) {
		return clientDAO.findClientByIdentifiant(identifiant);
	}
	
	@Override
	public Client obtenirContrat(Client client, Contrat contrat) {
		client.setContrat(contrat);
		return clientDAO.save(client);
	}
	
	@Override
	public Client retirerContrat(Client client) {
		client.setContrat(null);
		return clientDAO.save(client);
	}
	
	@Override
	public void supprimerClient(Long id) {
		clientDAO.delete(id);
	}
	

}
