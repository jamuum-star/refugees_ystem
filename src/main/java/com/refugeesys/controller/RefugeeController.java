package com.refugeesys.controller;

import com.refugeesys.model.Refugee;
import com.refugeesys.model.User;
import com.refugeesys.service.RefugeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@RequestMapping("/refugees")
//public class RefugeeController {
//
//    @Autowired
//    private  RefugeeService refugeeService;
//
//    @Autowired
//    public RefugeeController(RefugeeService refugeeService) {
//        this.refugeeService = refugeeService;
//    }
//
//    @GetMapping("/register")
//    public String registerRefugeeForm(Model model) {
//        model.addAttribute("refugee", new Refugee());
//        return "RegisterRefugee";
//    }
//
//    @PostMapping("/save")
//    public String addRefugee(@ModelAttribute("refugee") Refugee refugee) {
//        refugeeService.addRefugee(refugee);
//        return "redirect:/refugees/all";
//    }
//
//    @GetMapping("/all")
//    public String getAllRefugees(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
//        List<Refugee> listRefugees = refugeeService.getAllRefugees(keyword);
//        model.addAttribute("refugees", listRefugees);
//        model.addAttribute("keyword", keyword);
//        return "RefugeeList";
//    }
//
//    @GetMapping("/{id}")
//    @ResponseBody
//    public Refugee getRefugeeById(@PathVariable int id) {
//        return refugeeService.getRefugeeById(id);
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String deleteRefugee(@PathVariable int id) {
//        refugeeService.deleteRefugee(id);
//        return "redirect:/refugees/all";
//    }
//
//    @RequestMapping("/edit/{id}")
//    public String updateRefugeeForm(@PathVariable("id") int id, Model model) {
//        Refugee refugee = refugeeService.getRefugeeById(id);
//        model.addAttribute("refugee", refugee);
//        return "EditRefugee";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateRefugee(@ModelAttribute("refugee") Refugee refugee) {
//        refugeeService.updateRefugee(refugee);
//        return "redirect:/refugees/all";
//    }
//}
//
//@Controller
//@RequestMapping("/refugees")
//public class RefugeeController {
//
//    private final RefugeeService refugeeService;
//
//    public RefugeeController(RefugeeService refugeeService) {
//        this.refugeeService = refugeeService;
//    }
//
//    @GetMapping("/register")
//    public String registerRefugeeForm(Model model) {
//        model.addAttribute("refugee", new Refugee());
//        return "RegisterRefugee";
//    }
//
//    @PostMapping("/save")
//    public String addRefugee(@ModelAttribute("refugee") Refugee refugee) {
//        refugeeService.addRefugee(refugee);
//        return "redirect:/refugees/all";
//    }
//
//
//
//    @GetMapping("/all")
//    public String getAllRefugees(Model model, @RequestParam(name = "mykeyword", required = false) String mykeyword) {
//        List<Refugee> listRefugees = refugeeService.getAllRefugees(mykeyword);
//        model.addAttribute("refugees", listRefugees);  // Changed from "user" to "users"
//        model.addAttribute("mykeyword", mykeyword);
//        return "RefugeeList";
//    }
//
//    @GetMapping("/{id}")
//    @ResponseBody
//    public Refugee getRefugeeById(@PathVariable long id) {
//        return refugeeService.getRefugeeById(id);
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String deleteRefugee(@PathVariable long id) {
//        refugeeService.deleteRefugee(id);
//        return "redirect:/refugees/all";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String updateRefugeeForm(@PathVariable("id") long id, Model model) {
//        Refugee refugee = refugeeService.getRefugeeById(id);
//        model.addAttribute("refugee", refugee);
//        return "EditRefugee";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateRefugee(@ModelAttribute("refugee") Refugee refugee) {
//        refugeeService.updateRefugee(refugee);
//        return "redirect:/refugees/all";
//    }
//}



@Controller
@RequestMapping("/refugees")
public class RefugeeController {

    private final RefugeeService refugeeService;

    @Autowired
    public RefugeeController(RefugeeService refugeeService) {
        this.refugeeService = refugeeService;
    }

    @GetMapping("/register")
    public String registerRefugeeForm(Model model) {
        model.addAttribute("refugee", new Refugee());
        return "RegisterRefugee";
    }

    @PostMapping("/save")
    public String addRefugee(@ModelAttribute("refugee") Refugee refugee) {
        refugeeService.addRefugee(refugee);
        return "redirect:/refugees/all";
    }

    @GetMapping("/all")
    public String getAllRefugees(Model model, @RequestParam(name = "mykeyword", required = false) String mykeyword) {
        List<Refugee> listRefugees = refugeeService.getAllRefugees(mykeyword);
        model.addAttribute("refugees", listRefugees);
        model.addAttribute("mykeyword", mykeyword);
        return "RefugeeList";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Refugee getRefugeeById(@PathVariable int id) {
        return refugeeService.getRefugeeById(id);
    }

    @RequestMapping("/delete/{id}")
    public String deleteRefugee(@PathVariable int id) {
        refugeeService.deleteRefugee(id);
        return "redirect:/refugees/all";
    }

    @RequestMapping("/edit/{id}")
    public String updateRefugeeForm(@PathVariable("id") int id, Model model) {
        Refugee refugee = refugeeService.getRefugeeById(id);
        model.addAttribute("refugee", refugee);
        return "EditRefugee";
    }

    @PostMapping("/edit/{id}")
    public String updateRefugee(@ModelAttribute("refugee") Refugee refugee) {
        refugeeService.updateRefugee(refugee);
        return "redirect:/refugees/all";
    }
}
