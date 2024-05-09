package com.example.gestion_des_banques.web;

import com.example.gestion_des_banques.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.gestion_des_banques.dao.EmployeRepository;

@Controller
public class EmployeController {

    @Autowired EmployeRepository employeRepository;

    @GetMapping(path = "/index")
    public String findStudents(Model model, @RequestParam(name="page",defaultValue= "0") int page,
                               @RequestParam(name="size", defaultValue= "4") int size,
                               @RequestParam(name="search", defaultValue= "") String keyword
    ){
        //List<Student> students = studentRepository.findAll();
        //model.addAttribute("listStudents", students);

        Page<Employe> pageEmploye = employeRepository.findByNomContainsOrPrenomContains(keyword, keyword, PageRequest.of(page, size));
        model.addAttribute("listEmployes", pageEmploye.getContent());
        model.addAttribute("currentPage",page);
        int[] pages=new int[pageEmploye.getTotalPages()];
        for(int i=0;i<pages.length;i++)
            pages[i]=i;
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", keyword);
        return "AllEmployes";
    }

    @GetMapping(path="/create")
    public String createStudent(Model model){
        Employe employe = new Employe();
        model.addAttribute("employe", employe);
        return "formAddEmploye";

    }
    @GetMapping(path = "/edit")
    public String editStudent(Model model , int page, int size, String search, Integer id){
        Employe employe = employeRepository.findById(id).orElse(null);
        if(employe == null) throw new RuntimeException("Erreur");
        model.addAttribute("employe", employe);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);

        return "formEditEmploye";
    }

    @PostMapping(path = "/save")
    public String saveStudent(Model model, Employe s,
                              @RequestParam(name="currentPage", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "3") int size,
                              @RequestParam(name="searchName", defaultValue = "") String search){
        employeRepository.save(s);
        return "redirect:/index?page="+page+"&size="+size+"&search="+search;


    }

    @GetMapping(path="/delete")
    public String deleteStudent(
            int page, int size, String search,
            @RequestParam(name="id") Integer id){
        employeRepository.deleteById(id);

        return "redirect:/index?page="+page+"&size="+size+"&search="+search;
    }
}