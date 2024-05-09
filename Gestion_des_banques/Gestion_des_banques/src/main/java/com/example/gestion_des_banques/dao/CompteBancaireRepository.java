package com.example.gestion_des_banques.dao;

import com.example.gestion_des_banques.entities.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,Integer> {
}
