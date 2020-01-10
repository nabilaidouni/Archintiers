package fr.telecom_st_etienne.contrat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.contrat.business.Entreprise;

public interface EntrepriseDao extends JpaRepository<Entreprise, Long>{

}
