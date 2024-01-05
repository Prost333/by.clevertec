package by.clevertec.config;

import by.clevertec.Cache.Cache;
import by.clevertec.Cache.LFUCache;
import by.clevertec.Cache.LRUCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan("by.clevertec.*")
@EnableAspectJAutoProxy
@PropertySource("classpath:application.yml")
public class AppConfiguration {

}
