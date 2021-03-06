package fr.telecom_st_etienne.contrat.service;

import java.util.Date;
import java.util.List;

import fr.telecom_st_etienne.contrat.business.Client;
import fr.telecom_st_etienne.contrat.business.Contrat;
import fr.telecom_st_etienne.contrat.business.Entreprise;

public interface ContratService {
	
	Contrat ajouterContrat(String commentaire, String lienPdf, Date dateSoumission, Entreprise entreprise);
	
	List<Contrat> recupererContrats();

	Contrat recupererContrat(Long id);
	
	Contrat validerContrat(Contrat contrat);
	
	Contrat mettreAJourContrat(Contrat contrat, Entreprise entreprise, String commentaire, String lienPdf);
	
	void supprimerContrat(Contrat contrat);

}
