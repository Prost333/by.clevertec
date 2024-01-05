package by.clevertec.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import by.clevertec.Dto.ProductDto;
import by.clevertec.Service.ProductServiceImp;
import by.clevertec.config.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@Slf4j
@Component
@WebServlet(name = "ProductController", urlPatterns = "/product")
@NoArgsConstructor
public class ProductController extends HttpServlet {

}
