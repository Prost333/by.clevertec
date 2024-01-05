package by.clevertec.config;

import by.clevertec.servlet.GreetingServlet;
import by.clevertec.servlet.ProductController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebContextListener implements ServletContextListener {

    private static AnnotationConfigApplicationContext context;

    private DataSourceManager dataSourceManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        ProductController productController = context.getBean(ProductController.class);
        GreetingServlet greetingServlet=context.getBean(GreetingServlet.class);

        servletContext.addServlet("product", (Servlet) productController)
                .addMapping("/product");

        servletContext.addServlet("greetingServlet", (Servlet) greetingServlet).addMapping("/pdf");

        dataSourceManager = context.getBean(DataSourceManager.class);
        dataSourceManager.initDataBase();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        dataSourceManager.dropDataBase();
        dataSourceManager.close();

        context.close();
    }

    public static ApplicationContext getContext() {
        return context;
    }
}