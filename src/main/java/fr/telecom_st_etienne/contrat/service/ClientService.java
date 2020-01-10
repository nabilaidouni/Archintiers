package fr.telecom_st_etienne.contrat.service;

import java.util.List;

import fr.telecom_st_etienne.contrat.business.Client;

public interface ClientService {
	
	Client ajouterClient(String nom,String identifiant, String mdp, Boolean admin);
	
	List<Client> recupererClients();

	Client recupererClient(Long id);
	
	Client recupererClient(String identifiant);
	
	void supprimerClient(Long id);
}
