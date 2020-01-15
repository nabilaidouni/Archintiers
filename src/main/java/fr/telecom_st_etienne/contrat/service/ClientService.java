package fr.telecom_st_etienne.contrat.service;

import java.util.List;

import fr.telecom_st_etienne.contrat.business.Client;
import fr.telecom_st_etienne.contrat.business.Contrat;

public interface ClientService {
	
	Client ajouterClient(Contrat contrat, String nom,String identifiant, String mdp, Boolean admin);
	
	List<Client> recupererClients();

	Client recupererClient(Long id);
	
	Client recupererClient(String identifiant);
	
	Client obtenirContrat(Client client, Contrat contrat);
	
	Client retirerContrat(Client client);
	
	void supprimerClient(Long id);
}
