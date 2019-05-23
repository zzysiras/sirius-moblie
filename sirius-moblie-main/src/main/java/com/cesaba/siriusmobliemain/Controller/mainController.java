package com.cesaba.siriusmobliemain.Controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class mainController {

    @RequestMapping("/")
    public RedirectView loginPage() {
        return new RedirectView("/rolelogin.html");
    }


    @RequestMapping("/labLogin")
    public String toLabLogin(){
        return "/labLogin";
    }

    @RequestMapping("/labWeb")
    public String toWeb(){
        return "/labWeb";
    }
    /*@RequestMapping("/index")
    public String toIndex(){
        return "/index";
    }

    @RequestMapping("/header")
    public String header(){
        return "/header";
    }

    @RequestMapping("/footer")
    public String footer(){
        return "/footer";
    }

    @RequestMapping("/backstagelogin")
    public String tologin(){
        return "/backstagelogin";
    }



    @RequestMapping("/introducation")
    public String intro(){
        return "/introducation";
    }


    @RequestMapping("/labLogin-error")
    public String error(){
        return "/labLogin-error";
    }



    @RequestMapping("/login")
    public String tologin1(){
        return "/login";
    }

    @RequestMapping("/rolelogin")
    public String torolelogin(){
        return "/rolelogin";
    }

    @RequestMapping("/testlogin")
    public String test(){
        return "/testlogin";
    }

    @RequestMapping("/testlogin-error")
    public String errortest(){
        return "/testlogin-error";
    }

    @RequestMapping("/main")
    public String toWebpart(){
        return "/main";
    }

    @RequestMapping("/freemarker")
    public String testFreemarker(ModelMap modelMap){
        modelMap.addAttribute("msg", "这是freemarker测试");
        return "freemarker";
    }*/


}
