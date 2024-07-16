package com.refugeesys.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.refugeesys.model.Refugee;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Component
public class RefugeeExport {

    private final List<Refugee> refugeeList;
    private static final String LOGO_PATH = "static/image/refugee.jpg";
    // Constructor to initialize with list of refugees
    public RefugeeExport(List<Refugee> refugeeList) {
        this.refugeeList = refugeeList;
    }
    // Helper method to create a PDF cell with specified padding and alignment
    private PdfPCell createCellWithPadding(String text, Font font, float paddingTop, float paddingBottom, float paddingLeft, float paddingRight) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPaddingTop(paddingTop);
        cell.setPaddingBottom(paddingBottom);
        cell.setPaddingLeft(paddingLeft);
        cell.setPaddingRight(paddingRight);
        cell.setBorderWidth(1);
        cell.setBorderColor(Color.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
    // Method to write table headers
    private void writeTableHeader(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);

        PdfPCell headerCell;
        // Create header cells for each column
        headerCell = createCellWithPadding("ID", font, 5f, 5f, 5f, 5f);
        headerCell.setBackgroundColor(Color.GREEN);
        table.addCell(headerCell);

        headerCell = createCellWithPadding("Name", font, 5f, 5f, 5f, 5f);
        headerCell.setBackgroundColor(Color.GREEN);
        table.addCell(headerCell);

        headerCell = createCellWithPadding("Family Size", font, 5f, 5f, 5f, 5f);
        headerCell.setBackgroundColor(Color.GREEN);
        table.addCell(headerCell);

        headerCell = createCellWithPadding("Contact", font, 5f, 5f, 5f, 5f);
        headerCell.setBackgroundColor(Color.GREEN);
        table.addCell(headerCell);

        headerCell = createCellWithPadding("Address", font, 5f, 5f, 5f, 5f);
        headerCell.setBackgroundColor(Color.GREEN);
        table.addCell(headerCell);

        headerCell = createCellWithPadding("Status", font, 5f, 5f, 5f, 5f);
        headerCell.setBackgroundColor(Color.GREEN);
        table.addCell(headerCell);
    }
    // Method to write table data (refugee information)
    private void writeTableData(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        for (Refugee refugee : refugeeList) {
            // Add data cells for each refugee
            table.addCell(createCellWithPadding(String.valueOf(refugee.getId()), font, 5f, 5f, 5f, 5f));
            table.addCell(createCellWithPadding(refugee.getFullname(), font, 5f, 5f, 5f, 5f));
            table.addCell(createCellWithPadding(String.valueOf(refugee.getFamilysize()), font, 5f, 5f, 5f, 5f));
            table.addCell(createCellWithPadding(refugee.getPhone(), font, 5f, 5f, 5f, 5f));
            table.addCell(createCellWithPadding(refugee.getAddress(), font, 5f, 5f, 5f, 5f));
            table.addCell(createCellWithPadding(refugee.getStatus(), font, 5f, 5f, 5f, 5f));
        }
    }
    // Method to export PDF with refugee list
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        // Adding logo to the document
        URL url = getClass().getClassLoader().getResource(LOGO_PATH);
        if (url != null) {
            Image logo = Image.getInstance(url);
            logo.scaleToFit(100, 100); // Adjust size as needed
            logo.setAbsolutePosition(document.right() - 120, document.top() - 50);
            document.add(logo);
        }
        // Title paragraph
        Paragraph paragraph = new Paragraph("Refugee List", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);

        document.add(paragraph);
        // Create PDF table
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1f, 4f, 2f, 2f, 2f, 3f}); // Set column widths
        table.setSpacingBefore(10);
        // Write table header and data
        writeTableHeader(table);
        writeTableData(table);
        // Add table to document
        document.add(table);
        // Close the document
        document.close();
    }
}
