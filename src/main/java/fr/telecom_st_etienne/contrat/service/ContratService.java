package fr.telecom_st_etienne.contrat.service;

import java.util.Date;
import java.util.List;

import fr.telecom_st_etienne.contrat.business.Client;
import fr.telecom_st_etienne.contrat.business.Contrat;
import fr.telecom_st_etienne.contrat.business.Entreprise;

public interface ContratService {
	
	Contrat ajouterContrat(Client client, String commentaire, String lienPdf, Date dateSoumission, Entreprise entreprise);
	
	List<Contrat> recupererContrats();

	Contrat recupererContrat(Long id);
	
	void supprimerContrat(Long id);

}
