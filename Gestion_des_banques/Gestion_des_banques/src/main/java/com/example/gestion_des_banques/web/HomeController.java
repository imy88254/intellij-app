package com.example.gestion_des_banques.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "AllEmployes";
    }

    @ResponseBody
    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String about(){
        return "about";
    }
}
