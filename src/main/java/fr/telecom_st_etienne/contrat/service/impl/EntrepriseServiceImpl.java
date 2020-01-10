package fr.telecom_st_etienne.contrat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom_st_etienne.contrat.business.Contrat;
import fr.telecom_st_etienne.contrat.business.Entreprise;
import fr.telecom_st_etienne.contrat.dao.EntrepriseDao;
import fr.telecom_st_etienne.contrat.service.EntrepriseService;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	private EntrepriseDao entrepriseDAO;
	
	@Override
	public Entreprise ajouterEntreprise(String nom, String adresse, String siret, List<Contrat> contrats) {
		// TODO Auto-generated method stub
		Entreprise entreprise = new Entreprise();
		entreprise.setNom(nom);
		entreprise.setAdresse(adresse);
		entreprise.setSiret(siret);
		entreprise.setContrat(contrats);
		entrepriseDAO.save(entreprise);
		return entreprise;
	}

	@Override
	public List<Entreprise> recupererEntreprises() {
		// TODO Auto-generated method stub
		return entrepriseDAO.findAll();
	}

	@Override
	public Entreprise recupererEntreprise(Long id) {
		// TODO Auto-generated method stub
		return entrepriseDAO.findOne(id);
	}
	
	@Override
	public void supprimerEntreprise(Long id) {
		entrepriseDAO.delete(id);
	}

}
