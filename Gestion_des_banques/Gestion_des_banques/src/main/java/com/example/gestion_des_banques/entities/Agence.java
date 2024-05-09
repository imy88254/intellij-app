package com.example.gestion_des_banques.entities;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String adresse;

    @ManyToOne
    private Banque banque;

    @OneToMany
    private List<Employe> employes;

    @ManyToMany
    private List<Client> clients;

    @OneToMany
    private List<CompteBancaire> comptesBancaires;
}
