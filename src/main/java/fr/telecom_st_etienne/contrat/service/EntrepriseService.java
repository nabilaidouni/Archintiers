package fr.telecom_st_etienne.contrat.service;

import java.util.List;

import fr.telecom_st_etienne.contrat.business.Contrat;
import fr.telecom_st_etienne.contrat.business.Entreprise;

public interface EntrepriseService {
	
	Entreprise ajouterEntreprise(String nom, String adresse, String siret, List<Contrat> contrats);
	
	List<Entreprise> recupererEntreprises();

	Entreprise recupererEntreprise(Long id);
	
	void supprimerEntreprise(Long id);
}
