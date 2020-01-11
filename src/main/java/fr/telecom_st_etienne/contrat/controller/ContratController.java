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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
			contratService.ajouterContrat(clientService.recupererClient(1L),"Developpeur JAVA","coco.pdf", new Date(), entrepriseService.recupererEntreprise(1L));
			contratService.ajouterContrat(clientService.recupererClient(2L),"Developpeur C","caca.pdf", new Date(), entrepriseService.recupererEntreprise(2L));
			contratService.ajouterContrat(clientService.recupererClient(3L),"Developpeur Ruby","cucu.pdf", new Date(), entrepriseService.recupererEntreprise(3L));
			contratService.ajouterContrat(clientService.recupererClient(4L),"Developpeur Python","cici.pdf", new Date(), entrepriseService.recupererEntreprise(4L));
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
				clientService.ajouterClient(nom, identifiant, mdp, true);
			}
			else {
				clientService.ajouterClient(nom, identifiant, mdp, false);
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
	
	@GetMapping("entreprises")
	public ModelAndView developpeurGet() {
		
		ModelAndView mav = new ModelAndView("entreprises");
		return mav;
		
	}
	
	public void initBinder() {
		
	}
	
}