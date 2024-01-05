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

}
