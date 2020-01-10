package fr.telecom_st_etienne.contrat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.contrat.business.Client;

public interface ClientDao extends JpaRepository<Client, Long>{

	Client findClientByIdentifiant(String identifiant);

}
