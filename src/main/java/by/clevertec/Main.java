package by.clevertec;


import by.clevertec.Dto.ProductDto;
import by.clevertec.Service.ProductService;
import by.clevertec.Service.ProductServiceImp;
import by.clevertec.config.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ProductService productService = applicationContext.getBean(ProductServiceImp.class);
        List<ProductDto> products = productService.finaAll(0, null);
        System.out.println(products);

    }
}