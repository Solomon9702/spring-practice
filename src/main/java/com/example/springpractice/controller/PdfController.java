package com.example.springpractice.controller;

import com.example.springpractice.entity.Member;
import com.example.springpractice.repository.MemberRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class PdfController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/createPdf")
    public void createPdf(HttpServletResponse response) throws IOException {
        // PDF 문서를 브라우저에서 바로 다운로드할 수 있도록 설정
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=members.pdf");

        // PDF 문서 생성
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()));
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Hello, world!"));

        // 문서 닫기
        document.close();
    }
}
