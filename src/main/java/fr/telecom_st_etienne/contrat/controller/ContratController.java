package fr.telecom_st_etienne.contrat.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fr.telecom_st_etienne.contrat.business.Client;
import fr.telecom_st_etienne.contrat.business.Contrat;
import fr.telecom_st_etienne.contrat.service.ClientService;
import fr.telecom_st_etienne.contrat.service.ContratService;
import fr.telecom_st_etienne.contrat.service.EntrepriseService;


@Controller
@RequestMapping("/")
public class ContratController {

	@Autowired
	private ClientService clientService;
	@Autowired
	private ContratService contratService;
	@Autowired
	private EntrepriseService entrepriseService;
	
	public ContratController(ClientService clientService, ContratService contratService, EntrepriseService entrepriseService) {
		super();
		this.clientService = clientService;
		this.contratService = contratService;
		this.entrepriseService = entrepriseService;
	}

	@RequestMapping(value = { "index", "/" })
	public ModelAndView accueil() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("clients",clientService.recupererClients());
		return mav;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Dans init()");
		
		if(entrepriseService.recupererEntreprises().isEmpty()) {
			entrepriseService.ajouterEntreprise("Andrice","12 rue Andrice","0000001");
			entrepriseService.ajouterEntreprise("Boulanger","12 rue Boulanger","0000002");
			entrepriseService.ajouterEntreprise("Cabine française","12 rue Cabine francaise","0000003");
			entrepriseService.ajouterEntreprise("Dell","12 rue Dell","0000004");
			entrepriseService.ajouterEntreprise("EDF","12 rue EDF","0000005");
			entrepriseService.ajouterEntreprise("Facebook","12 rue Facebook","0000006");
			entrepriseService.ajouterEntreprise("GDF","12 rue GDF","0000007");
			entrepriseService.ajouterEntreprise("Hasbro","12 rue Hasbro","0000008");
			entrepriseService.ajouterEntreprise("Iris","12 rue Iris","0000009");
			entrepriseService.ajouterEntreprise("Jdbc","12 rue Jdbc","0000010");
		}
		
		if(contratService.recupererContrats().isEmpty()) {
			contratService.ajouterContrat("Developpeur JAVA","coco.pdf", new Date(), entrepriseService.recupererEntreprise(1L));
			contratService.ajouterContrat("Developpeur C","caca.pdf", new Date(), entrepriseService.recupererEntreprise(2L));
			contratService.ajouterContrat("Developpeur Ruby","cucu.pdf", new Date(), entrepriseService.recupererEntreprise(3L));
			contratService.ajouterContrat("Developpeur Python","cici.pdf", new Date(), entrepriseService.recupererEntreprise(4L));
		}
		
		if(clientService.recupererClients().isEmpty()) {
			clientService.ajouterClient(contratService.recupererContrat(1L),"Thales","thales","thales", true);
			clientService.ajouterClient(contratService.recupererContrat(2L),"Pythagore","pythagore","pythagore", false);
			clientService.ajouterClient(contratService.recupererContrat(3L),"Laplace","laplace","laplace", false);
			clientService.ajouterClient(contratService.recupererContrat(4L),"Bayes","bayes","bayes", false);
		}
	}
	
	@PostMapping("SignIn")
	public RedirectView connexion(@RequestParam("IDENTIFIANT") String identifiant, @RequestParam("MOT_DE_PASSE") String mdp) {
		if(clientService.recupererClient(identifiant) != null) {
			if (clientService.recupererClient(identifiant).getMdp().equals(mdp)) {
				
				if(clientService.recupererClient(identifiant).getAdmin()==true) {
					return new RedirectView("pageadmin");
				}
				else {
					return new RedirectView("pageclient?IDENTIFIANT_CLIENT=" + identifiant);
				}
			}
			else {
				return new RedirectView("index");
			}
		}
		else {
			return new RedirectView("index");
		}
	}
	
	@GetMapping("pageadmin")
	public ModelAndView pageAdminGet() {

		ModelAndView mav = new ModelAndView("pageadmin");
		mav.addObject("clients",clientService.recupererClients());
		mav.addObject("contrats",contratService.recupererContrats());
		mav.addObject("entreprises", entrepriseService.recupererEntreprises());
		return mav;
		
	}
	
	@GetMapping("pageclient")
	public ModelAndView pageClientGet(@RequestParam("IDENTIFIANT_CLIENT") String identifiant) {
		
		ModelAndView mav = new ModelAndView("pageclient");
		mav.addObject("clientConnecte",clientService.recupererClient(identifiant));
		mav.addObject("clients",clientService.recupererClients());
		mav.addObject("contrats",contratService.recupererContrats());
		mav.addObject("entreprises", entrepriseService.recupererEntreprises());
		return mav;
		
	}
	
	@GetMapping("inscription")
	public ModelAndView inscriptionGet() {
		ModelAndView mav = new ModelAndView("inscription");
		mav.addObject("clients",clientService.recupererClients());
		mav.addObject("contrats",contratService.recupererContrats());
		mav.addObject("entreprises", entrepriseService.recupererEntreprises());
		return mav;
	}
	
	@PostMapping("inscriptionPost")
	public ModelAndView inscriptionPost(@RequestParam("NOM") String nom, @RequestParam("IDENTIFIANT") String identifiant, @RequestParam("MOT_DE_PASSE") String mdp, @RequestParam("CODE_ADMIN") String codeAdmin) {
		if(clientService.recupererClient(identifiant) == null) {
			if(codeAdmin.equals("admin")) {
				clientService.ajouterClient(null, nom, identifiant, mdp, true);
			}
			else {
				clientService.ajouterClient(null, nom, identifiant, mdp, false);
			}
			return new ModelAndView("redirect:/");
		}
		else {
			return new ModelAndView("redirect:/inscription");
		}
		
	}
	
	@PostMapping("validationContratPost")
	public ModelAndView validationContratPost(@RequestParam("ID_CONTRAT") Long idContrat) {
			contratService.validerContrat(contratService.recupererContrat(idContrat));
			return new ModelAndView("redirect:/pageadmin");
	}
	
	@GetMapping("contrat")
	public ModelAndView contratGet(@RequestParam("ID_CLIENT") Long idClient) {
		
		ModelAndView mav = new ModelAndView("contrat");
		mav.addObject("clientConnecte",clientService.recupererClient(idClient));
		mav.addObject("contrats",contratService.recupererContrats());
		mav.addObject("entreprises",entrepriseService.recupererEntreprises());
		return mav;
		
	}
	
	@PostMapping("newContratPost")
	public ModelAndView newContratPost(@RequestParam("IDENTIFIANT_CLIENT") String idClient, @RequestParam("ID_ENTREPRISE") Long idEntreprise, @RequestParam("COMMENTAIRE") String commentaire, @RequestParam("LIEN_PDF") String lienPdf) {
		
		clientService.obtenirContrat(clientService.recupererClient(idClient), contratService.ajouterContrat(commentaire, lienPdf, new Date(), entrepriseService.recupererEntreprise(idEntreprise)));	
		return new ModelAndView("redirect:/pageclient?IDENTIFIANT_CLIENT=" + idClient);
	}
	
	@PostMapping("majContratPost")
	public ModelAndView majContratPost(@RequestParam("IDENTIFIANT_CLIENT") String idClient, @RequestParam("ID_ENTREPRISE") Long idEntreprise, @RequestParam("COMMENTAIRE") String commentaire, @RequestParam("LIEN_PDF") String lienPdf) {
		
		majContratPut(clientService.recupererClient(idClient).getContrat().getId(),clientService.recupererClient(idClient).getContrat().getEntreprise().getId(),clientService.recupererClient(idClient).getContrat().getCommentaire(),clientService.recupererClient(idClient).getContrat().getLienPdf());
		//clientService.obtenirContrat(clientService.recupererClient(idClient), contratService.ajouterContrat(commentaire, lienPdf, new Date(), entrepriseService.recupererEntreprise(idEntreprise)));	
		return new ModelAndView("redirect:/pageclient?IDENTIFIANT_CLIENT=" + idClient);
	}
	
	@PutMapping("mettreAJourContrat")
	public void majContratPut(@RequestParam("ID_CONTRAT") Long idContrat, @RequestParam("ID_ENTREPRISE") Long idEntreprise, @RequestParam("COMMENTAIRE") String commentaire, @RequestParam("LIEN_PDF") String lienPdf) {
		contratService.mettreAJourContrat(contratService.recupererContrat(idContrat), entrepriseService.recupererEntreprise(idEntreprise), commentaire, lienPdf);
		//clientService.obtenirContrat(clientService.recupererClient(idClient), contratService.ajouterContrat(commentaire, lienPdf, new Date(), entrepriseService.recupererEntreprise(idEntreprise)));
	}
	
	@GetMapping("contratmaj")
	public ModelAndView contratMajGet(@RequestParam("ID_CLIENT") Long idClient) {
		
		ModelAndView mav = new ModelAndView("contratmaj");
		mav.addObject("clientConnecte",clientService.recupererClient(idClient));
		mav.addObject("contrats",contratService.recupererContrats());
		mav.addObject("entreprises",entrepriseService.recupererEntreprises());
		return mav;
		
	}
	
	@GetMapping("putanddelete")
	public RedirectView putAndDeleteGet(@RequestParam("ID_CONTRAT") Long idContrat, @RequestParam("IDENTIFIANT_CLIENT") String idClient, @RequestParam("METHOD_SELECTED") Long method) {
		if(method == 2) {
			contratDelete(idContrat, idClient);
			return new RedirectView("pageclient?IDENTIFIANT_CLIENT=" + idClient);
		}
		else {
			contratDelete(idContrat, idClient);
			return new RedirectView("pageclient?IDENTIFIANT_CLIENT=" + idClient);
		}
	}
	
	@DeleteMapping("contratDelete")
	public void contratDelete(@RequestParam("ID_CONTRAT") Long idContrat, @RequestParam("IDENTIFIANT_CLIENT") String idClient ) {
		clientService.retirerContrat(clientService.recupererClient(idClient));
		contratService.supprimerContrat(contratService.recupererContrat(idContrat));
		
	}
	
}