package com.refugeesys.controller;

import com.refugeesys.model.Refugee;

import com.refugeesys.service.RefugeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;





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
        listRefugees.sort(Comparator.comparing(Refugee::getId));
        model.addAttribute("refugees", listRefugees);
        model.addAttribute("mykeyword", mykeyword);
        int refugeeCount = listRefugees.size();
        model.addAttribute("refugeeCount", refugeeCount);
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





    @GetMapping("/print/pdf")
    public void printRefugeeListAsPdf(HttpServletResponse response) throws IOException {
        List<Refugee> listRefugees = refugeeService.getAllRefugees(null);
        listRefugees.sort(Comparator.comparing(Refugee::getId));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Refugee List");
                contentStream.newLineAtOffset(0, -20);
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                int yPosition = 700;
                for (Refugee refugee : listRefugees) {
                    contentStream.showText("ID: " + refugee.getId() +
                            ", Name: " + refugee.getFullname() +
                            ", Family Size: " + refugee.getFamilysize() +
                            ", Contact: " + refugee.getPhone() +
                            ", Address: " + refugee.getAddress() +
                            ", Status: " + refugee.getStatus());
                    contentStream.newLineAtOffset(0, -15);
                    yPosition -= 15;

                    // Check if content needs to overflow to a new page
                    if (yPosition <= 50) {
                        contentStream.endText();
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);
                        contentStream.setFont(PDType1Font.HELVETICA, 12);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(100, 700);
                        yPosition = 700;
                    }
                }

                contentStream.endText();
            }

            document.save(baos);
        }

        // Set the content type and headers for the response
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=refugee_list.pdf");
        response.setContentLength(baos.size());

        // Write the PDF content to the response
        baos.writeTo(response.getOutputStream());
        baos.close();

        // Flush the response
        response.flushBuffer();
    }







}
