package com.example.gestion_des_banques.dao;

import com.example.gestion_des_banques.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Page<Client> findByNomContainsOrPrenomContains(String nom, String prenom, Pageable pageable);

}
