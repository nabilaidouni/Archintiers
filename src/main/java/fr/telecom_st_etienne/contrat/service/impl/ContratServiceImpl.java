package fr.telecom_st_etienne.contrat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom_st_etienne.contrat.business.Client;
import fr.telecom_st_etienne.contrat.business.Contrat;
import fr.telecom_st_etienne.contrat.business.Entreprise;
import fr.telecom_st_etienne.contrat.dao.ContratDao;
import fr.telecom_st_etienne.contrat.service.ContratService;

@Service
public class ContratServiceImpl implements ContratService{

	
	@Autowired
	private ContratDao contratDAO;
	
	@Override
	public Contrat ajouterContrat(Client client, String commentaire, String lienPdf, Date dateSoumission, Entreprise entreprise) {
		// TODO Auto-generated method stub
		Contrat contrat = new Contrat();
		contrat.setClient(client);
		contrat.setCommentaire(commentaire);
		contrat.setLienPdf(lienPdf);
		contrat.setDateSoumission(dateSoumission);
		contrat.setValide(false);
		contrat.setEntreprise(entreprise);
		contratDAO.save(contrat);
		return contrat;
	}

	@Override
	public List<Contrat> recupererContrats() {
		// TODO Auto-generated method stub
		return contratDAO.findAll();
	}

	@Override
	public Contrat recupererContrat(Long id) {
		// TODO Auto-generated method stub
		return contratDAO.findOne(id);
	}
	
	@Override
	public Contrat validerContrat(Contrat contrat) {
		contrat.setValide(true);
		return contratDAO.save(contrat);
	}
	
	@Override
	public void supprimerContrat(Long id) {
		contratDAO.delete(id);
	}

}
