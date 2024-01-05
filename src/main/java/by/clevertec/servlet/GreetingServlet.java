package by.clevertec.servlet;

import jakarta.servlet.annotation.WebServlet;
import by.clevertec.Dto.ProductDto;
import by.clevertec.PDF.PDFPrint;
import by.clevertec.Service.ProductService;
import by.clevertec.Service.ProductServiceImp;
import by.clevertec.config.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@Component
@WebServlet(name = "GreetingServlet", urlPatterns = "/pdf")
public class GreetingServlet  extends HttpServlet {
    private PDFPrint pdfPrint;
    private ProductService productService;
    public void init() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        pdfPrint = context.getBean(PDFPrint.class);
        productService = context.getBean(ProductServiceImp.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "inline; filename=product.pdf");
        try {
            String productId = req.getParameter("productId");

            ProductDto product = productService.getById(productId);

            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }

            pdfPrint.PrintProduct(product, resp.getOutputStream());
            resp.getOutputStream().flush();
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while creating the PDF");
        }

    }
}
