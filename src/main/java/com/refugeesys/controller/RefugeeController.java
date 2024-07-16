package com.refugeesys.controller;

import com.refugeesys.model.Refugee;
import com.refugeesys.service.RefugeeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/refugees")
public class RefugeeController {
    private final RefugeeService refugeeService;
    private final RefugeeExport refugeeExport;

    // Constructor for dependency injection
    @Autowired
    public RefugeeController(RefugeeService refugeeService, RefugeeExport refugeeExport) {
        this.refugeeService = refugeeService;
        this.refugeeExport = refugeeExport;
    }

    // Display the form to register a new refugee
    @GetMapping("/register")
    public String registerRefugeeForm(Model model) {
        model.addAttribute("refugee", new Refugee());
        return "RegisterRefugee";
    }
    // Handle the submission of the refugee registration form
    @PostMapping("/save")
    public String addRefugee(@ModelAttribute("refugee") @Valid Refugee refugee, BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "RegisterRefugee"; // Return to the form if validation fails
        }
        refugeeService.addRefugee(refugee);
        redirectAttributes.addFlashAttribute("successMessage", "Refugee added successfully.");
        return "redirect:/refugees/all";
    }
    // Display a list of all refugees, optionally filtered by a keyword
    @GetMapping("/all")
    public String getAllRefugees(Model model, @RequestParam(name = "mykeyword", required = false) String mykeyword) {
        List<Refugee> listRefugees = refugeeService.getAllRefugees(mykeyword);
        listRefugees.sort(Comparator.comparing(Refugee::getId));
        model.addAttribute("refugees", listRefugees);
        model.addAttribute("mykeyword", mykeyword);
        int refugeeCount = listRefugees.size();
        model.addAttribute("refugeeCount", refugeeCount);
        return "RefugeeList";
    }
    // Get a refugee by ID (returns JSON response)
    @GetMapping("/{id}")
    @ResponseBody
    public Refugee getRefugeeById(@PathVariable int id) {
        return refugeeService.getRefugeeById(id);
    }
    // Delete a refugee by ID and redirect to the list of all refugees
    @RequestMapping("/delete/{id}")
    public String deleteRefugee(@PathVariable int id) {
        refugeeService.deleteRefugee(id);
        return "redirect:/refugees/all";
    }
    // Display the form to edit an existing refugee
    @RequestMapping("/edit/{id}")
    public String updateRefugeeForm(@PathVariable("id") int id, Model model) {
        Refugee refugee = refugeeService.getRefugeeById(id);
        model.addAttribute("refugee", refugee);
        return "EditRefugee";
    }
    // Handle the submission of the refugee edit form
    @PostMapping("/edit/{id}")
    public String updateRefugee(@ModelAttribute("refugee") @Valid Refugee refugee, BindingResult result,
                                @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "EditRefugee"; // Return to the form if validation fails
        }
        refugee.setId(id); // Set ID from path variable
        refugeeService.updateRefugee(refugee);
        redirectAttributes.addFlashAttribute("successMessage", "Refugee updated successfully.");
        return "redirect:/refugees/all";
    }
    // Export the list of refugees to a PDF file
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=refugees.pdf");

        List<Refugee> listRefugees = refugeeService.getAllRefugees(null);
        listRefugees.sort(Comparator.comparing(Refugee::getId));

        RefugeeExport refugeeExport = new RefugeeExport(listRefugees);
        refugeeExport.export(response);
    }

    // Exception handling for not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // Replace with your error page
    }

    // Exception handling for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
        BindingResult result = ex.getBindingResult();
        model.addAttribute("errorMessage", "Validation error");
        model.addAttribute("errors", result.getAllErrors());
        return "error"; // Replace with your error page
    }
}
