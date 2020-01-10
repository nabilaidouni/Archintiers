package fr.telecom_st_etienne.contrat.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.telecom_st_etienne.contrat.business.Client;
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
		if(clientService.recupererClients().isEmpty()) {
			clientService.ajouterClient("Thales","thales","thales", true);
			clientService.ajouterClient("Pythagore","pythagore","pythagore", false);
			clientService.ajouterClient("Laplace","laplace","laplace", false);
			clientService.ajouterClient("Bayes","bayes","bayes", false);
		}
		if(contratService.recupererContrats().isEmpty()) {
			contratService.ajouterContrat(clientService.recupererClient(1L),"salut !","coco.pdf", new Date(), entrepriseService.recupererEntreprise(1L));
			contratService.ajouterContrat(clientService.recupererClient(2L),"salut !","caca.pdf", new Date(), entrepriseService.recupererEntreprise(1L));
			contratService.ajouterContrat(clientService.recupererClient(3L),"salut !","cucu.pdf", new Date(), entrepriseService.recupererEntreprise(1L));
			contratService.ajouterContrat(clientService.recupererClient(4L),"salut !","cici.pdf", new Date(), entrepriseService.recupererEntreprise(1L));
		}
		if(entrepriseService.recupererEntreprises().isEmpty()) {
			entrepriseService.ajouterEntreprise("Andrice","12 rue Andrice","0000001",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("Boulanger","12 rue Boulanger","0000002",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("Cabine française","12 rue Cabine francaise","0000003",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("Dell","12 rue Dell","0000004",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("EDF","12 rue EDF","0000005",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("Facebook","12 rue Facebook","0000006",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("GDF","12 rue GDF","0000007",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("Hasbro","12 rue Hasbro","0000008",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("Iris","12 rue Iris","0000009",contratService.recupererContrats());
			entrepriseService.ajouterEntreprise("Jdbc","12 rue Jdbc","0000010",contratService.recupererContrats());
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
					return new RedirectView("pageclient");
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
	public ModelAndView pageClientGet() {

		ModelAndView mav = new ModelAndView("pageclient");
		mav.addObject("clients",clientService.recupererClients());
		mav.addObject("contrats",contratService.recupererContrats());
		mav.addObject("entreprises", entrepriseService.recupererEntreprises());
		return mav;
		
	}
	
	@PostMapping("inscription")
	public RedirectView inscription(@RequestParam("NOM") String nom, @RequestParam("IDENTIFIANT") String identifiant, @RequestParam("MOT_DE_PASSE") String mdp, @RequestParam("CODE_ADMIN") String codeAdmin) {
		if(codeAdmin == "admin") {
			clientService.ajouterClient(nom, identifiant, mdp, true);
		}
		else {
			clientService.ajouterClient(nom, identifiant, mdp, false);
		}
		return new RedirectView("index");
	}
	
	@GetMapping("entreprises")
	public ModelAndView developpeurGet() {
		
		ModelAndView mav = new ModelAndView("entreprises");
		return mav;
		
	}
	
	public void initBinder() {
		
	}
	
}