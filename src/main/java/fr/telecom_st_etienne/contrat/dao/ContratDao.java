package fr.telecom_st_etienne.contrat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.contrat.business.Contrat;

public interface ContratDao extends JpaRepository<Contrat, Long>{

}
