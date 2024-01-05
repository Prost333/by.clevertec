package by.clevertec.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;


@Component
@RequiredArgsConstructor
public class DataSourceManager {

    @Value("${database.driver}")
    private String dbDriver;
    @Value("${database.url}")
    private String dbUrl;
    @Value("${database.user}")
    private String dbUser;
    @Value("${database.password}")
    private String dbPassword;
    @Value("#{T(java.lang.Integer).parseInt('${database.min}')}")
    private Integer dbMinPool;
    @Value("#{T(java.lang.Integer).parseInt('${database.max}')}")
    private Integer dbMaxPool;
    @Value("#{T(java.lang.Boolean).parseBoolean('${database.autocommit}')}")
    private Boolean dbAutoCommit;
    @Value("#{T(java.lang.Boolean).parseBoolean('${database.auto_init}')}")
    private Boolean autoInit;
    @Value("#{T(java.lang.Boolean).parseBoolean('${database.add_data}')}")
    private Boolean addData;
    @Value("${database.sql.schema}")
    private String schemaSql;
    @Value("${database.sql.data}")
    private String dataSql;
    @Value("${database.sql.drop}")
    private String dropSql;


    private HikariDataSource dataSource;

    private final DataInputStreamReader dataInputStreamReader;

    @PostConstruct
    public void init() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName(dbDriver);
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setUsername(dbUser);
        hikariConfig.setPassword(dbPassword);
        hikariConfig.setMinimumIdle(dbMinPool);
        hikariConfig.setMaximumPoolSize(dbMaxPool);
        hikariConfig.setAutoCommit(dbAutoCommit);


        dataSource = new HikariDataSource(hikariConfig);
    }


    public DataSource getDataSource() {
        return dataSource;
    }


    public void close() {

        dataSource.close();
        deregisterDriver();
    }


    public void initDataBase() {
        if (autoInit) {
            executeSqlByPath(schemaSql);
        }
        if (addData) {
            executeSqlByPath(dataSql);
        }
    }


    public void dropDataBase() {
        if (autoInit) {
            executeSqlByPath(dropSql);
        }
    }

    private void executeSqlByPath(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            executeSqlFileByInputStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void executeSqlFileByInputStream(InputStream inputStream) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = dataInputStreamReader.getString(inputStream);

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw e;
        }
    }

    private void deregisterDriver() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();

            if (driver instanceof org.postgresql.Driver) {
                try {
                    DriverManager.deregisterDriver(driver);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
