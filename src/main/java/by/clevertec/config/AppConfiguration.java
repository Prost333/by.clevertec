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
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        PropertySourcesPlaceholderConfigurer configure = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        Properties yamlObject = Objects.requireNonNull(yaml.getObject(), "application not found.");
        configure.setProperties(yamlObject);
        return configure;
    }

    @Bean
    public DataSource dataSource(DataSourceManager dataSourceManager) {
        return dataSourceManager.getDataSource();
    }

    @Bean
    public Cache<?, ?> cache(@Value("#{T(java.lang.Integer).parseInt('${cache.size}')}") int initialCapacity,
                             @Value("${cache.type}") String cacheType) {
        if (cacheType.equals("LRU")) {
            return new LRUCache<>(initialCapacity);
        } else {
            return new LFUCache<>(initialCapacity);
        }
    }
    @Bean
    public String stringBean() {
        return "someString";
    }
    @Bean
    public Integer intBean() {
        return 0;
    }
    @Bean
    public Boolean booleanBean() {
        return true;
    }


}
