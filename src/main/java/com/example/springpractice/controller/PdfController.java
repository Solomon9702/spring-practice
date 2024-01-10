package com.example.springpractice.controller;

import com.example.springpractice.entity.Member;
import com.example.springpractice.repository.MemberRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class PdfController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/createPdf/{id}")
    public String createPdf(HttpServletResponse response, Model model, @PathVariable Long id) throws IOException {
        // PDF 문서를 브라우저에서 바로 다운로드할 수 있도록 설정
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=members.pdf");

        // PDF 문서 생성
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()));
        Document document = new Document(pdfDoc);

        Member member = memberRepository.findById(id).orElse(null);

        // 표 생성
        Table table = new Table(2);

        // 헤더 추가
        table.addCell("ID");
        table.addCell("Email");

        // 데이터 추가
        if(member != null){
            table.addCell(member.getId().toString());
            table.addCell(member.getEmail());
        } else{
            table.addCell("N/A");
            table.addCell("N/A");
        }

//        document.add(new Paragraph(member.getEmail()));

        document.add(table);

        // 문서 닫기
        document.close();

        return "redirect:/members/" + member.getId();
    }
}
