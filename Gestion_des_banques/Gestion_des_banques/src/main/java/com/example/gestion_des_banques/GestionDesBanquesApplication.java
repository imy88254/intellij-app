package com.example.gestion_des_banques;
import com.example.gestion_des_banques.dao.ClientRepository;
import com.example.gestion_des_banques.dao.EmployeRepository;
import com.example.gestion_des_banques.dao.CompteBancaireRepository;
import com.example.gestion_des_banques.entities.Client;
import com.example.gestion_des_banques.entities.CompteBancaire;
import com.example.gestion_des_banques.entities.Employe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestionDesBanquesApplication {

    @Autowired EmployeRepository EmployeRepository;
    @Autowired ClientRepository ClientRepository;
    @Autowired CompteBancaireRepository CompteBancaireRepository;
    public static void main(String[] args) {
        SpringApplication.run(GestionDesBanquesApplication.class, args);
    }



    public void run(String... args) throws Exception {}

}
