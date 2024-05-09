package com.example.gestion_des_banques.web;


import com.example.gestion_des_banques.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.gestion_des_banques.dao.ClientRepository;

@Controller
public class ClientController {

    @Autowired ClientRepository ClientRepository;

    @GetMapping(path = "/client")
    public String findClients(Model model, @RequestParam(name="page",defaultValue= "0") int page,
                               @RequestParam(name="size", defaultValue= "4") int size,
                               @RequestParam(name="search", defaultValue= "") String keyword
    ){
        //List<Student> students = studentRepository.findAll();
        //model.addAttribute("listStudents", students);
        Page<Client> pageClient = ClientRepository.findByNomContainsOrPrenomContains(keyword, keyword, PageRequest.of(page, size));
        model.addAttribute("listClients", pageClient.getContent());
        int[] pages=new int[pageClient.getTotalPages()];
        for(int i=0;i<pages.length;i++)
            pages[i]=i;
        model.addAttribute("tabbPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", keyword);
        return "AllClients";
    }

    @GetMapping(path="/createclient")
    public String createStudent(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        return "formAddClient";

    }
    @GetMapping(path = "/editclient")
    public String editClient(Model model , int page, int size, String search, Integer id){
        Client client = ClientRepository.findById(id).orElse(null);
        if(client == null) throw new RuntimeException("Erreur");
        model.addAttribute("client", client);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);

        return "formEditClient";
    }

    @PostMapping(path = "/saveclient")
    public String saveClient(Model model, Client s,
                              @RequestParam(name="currentPage", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "3") int size,
                              @RequestParam(name="searchName", defaultValue = "") String search){
        ClientRepository.save(s);
        return "redirect:/client?page="+page+"&size="+size+"&search="+search;


    }

    @GetMapping(path="/deleteclient")
    public String deleteStudent(
            int page, int size, String search,
            @RequestParam(name="id") Integer id){
        ClientRepository.deleteById(id);

        return "redirect:/client?page="+page+"&size="+size+"&search="+search;
    }
}
