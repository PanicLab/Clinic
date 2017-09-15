package com.github.paniclab.producers;

import org.hsqldb.jdbc.JDBCDataSource;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.logging.Logger;

import static com.github.paniclab.utils.Util.print;

@ApplicationScoped
public class DataSourceProducer {
    @Inject
    @Property("database.relative.path")
    private String relative_url;

    @Inject
    @Property("database.user")
    private String user;

    @Inject
    @Property("database.password")
    private String password;

    @Inject
    private ServletContext context;

    private DataSource dataSource;

    public DataSourceProducer() {}


    @PostConstruct
    public void init() {
        print("Вызывается метод init в классе DataSourceProducer");
    }

    @Produces
    @HSQLDB
    public DataSource produceDataSource() {
        if (dataSource == null) {
            createDataSource();
        }
        return dataSource;
    }

    private void createDataSource() {
        Logger logger = Logger.getLogger(getClass().getSimpleName());
        String baseDir = context.getRealPath("/");
        logger.info("Путь приложения определен как: " + baseDir);
        final String URL = "jdbc:hsqldb:file:" + baseDir + relative_url;
        logger.info("URL базы данных определен как: " + URL);
        JDBCDataSource ds = new JDBCDataSource();
        ds.setURL(URL);
        ds.setUser(user);
        ds.setPassword(password);
        logger.info("Объект DataSource создан успешно.");
        dataSource = ds;
    }
}
