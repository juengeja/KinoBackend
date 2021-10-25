package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.entity.Seat;
import com.example.kinoticketreservierungssystem.entity.ShowEvent;
import com.example.kinoticketreservierungssystem.entity.Ticket;
import com.example.kinoticketreservierungssystem.repository.SeatRepository;
import com.example.kinoticketreservierungssystem.repository.ShowEventRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;

@Service
public class TicketPDF {

    @Autowired
    ShowEventRepository showEventRepository;
    @Autowired
    SeatRepository seatRepository;

    private static String FILE = "src/main/resources/ticket.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 30,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL);


    public void createTicketPDF(Ticket ticket){
    try {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        addTitlePage(document, ticket);
        document.add(generateQRCodeImage(ticket.getTicketID()));
        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }}

    private Document addTitlePage(Document document, Ticket ticket)
            throws DocumentException {
        Seat seat = seatRepository.findBySeatID(ticket.getSeatInfo()).get();
        ShowEvent showEvent = showEventRepository.findByShowEventID(ticket.getShowEventInfo()).get();
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Movie Ticket", catFont));
        addEmptyLine(preface, 2);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph( showEvent.getMovieInfo().getMovieName(),
                subFont));
        addEmptyLine(preface, 2);
        preface.add(new Paragraph("Date: " + showEvent.getEventStart().toString(),
                subFont));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph("Row: " + String.valueOf(seat.getRow()),
                catFont));
        addEmptyLine(preface,2);
        preface.add(new Paragraph(
                "Number: " + String.valueOf(seat.getSeatNumber()),
                catFont));
        document.add(preface);
        // Start a new page
        return document;
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
        return ImageIO.read(bis);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}