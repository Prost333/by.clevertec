package by.clevertec.PDF;

import by.clevertec.Entity.Entity;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import com.itextpdf.layout.properties.VerticalAlignment;
import by.clevertec.Dto.ProductDto;

import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class PDFPrint {
    LocalDateTime now=LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm");
    String formattedNow = now.format(formatter);

    public void Print(Entity entity){
        try {
            String dest = "resources/pdf/Clevertec_Template.pdf";
            String src = "resources/pdf/Clevertec_Template — копия.pdf";

            PdfReader reader = new PdfReader(src);
            PdfWriter writer = new PdfWriter(dest);

            PdfDocument pdf = new PdfDocument(reader, writer);
            Document document = new Document(pdf);

            String content = "name: "+entity.getName()+ "\n" +
            "Surname: "+entity.getSurname()+"\n"+ "actual date "+ formattedNow;
            Paragraph paragraph = new Paragraph(content);
            paragraph.setFontSize(24f);
            paragraph.setVerticalAlignment(VerticalAlignment.MIDDLE);
            // 1 - номер страницы, 36 - x координата, 36 - y координата, 200 - ширина области текста
            paragraph.setFixedPosition(1, 200, 400, 600);

            document.add(paragraph);

            document.close();
            System.out.println("PDFPrint Created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PrintProduct(ProductDto product, OutputStream outputStream){
        try {
            String src = "C:\\musikdb\\clecertec\\Clevertec_Template — копия.pdf";

            PdfReader reader = new PdfReader(src);
            PdfWriter writer = new PdfWriter(outputStream);

            PdfDocument pdf = new PdfDocument(reader, writer);
            Document document = new Document(pdf);

            // Используйте свойство product для получения данных
            String content = "name: "+ product.getName() + "\n" +
                    "Price: "+ product.getPrice() + "\n" +
                    "actual date " + formattedNow;

            Paragraph paragraph = new Paragraph(content);
            paragraph.setFontSize(24f);
            paragraph.setVerticalAlignment(VerticalAlignment.MIDDLE);
            paragraph.setFixedPosition(1, 200, 400, 600);

            document.add(paragraph);

            document.close();
            System.out.println("PDFPrint Created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
