package com.github.paniclab.Listeners;

import com.github.paniclab.producers.Property;
import org.hsqldb.jdbc.JDBCDataSource;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.logging.Logger;

class DataSourceFactory {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Inject
    //@Default
    @Property("database.relative.path")
    private String relative_url;

    @Inject
    @Property("database.user")
    private String user;

    @Inject
    @Property("database.password")
    private String password;

    DataSourceFactory() {}

    @PostConstruct
    void init() {
        logger.info("Объект DataSourceFactory инициализирован.");
    }

    DataSource getDataSource(ServletContext cxt) {
        String baseDir = cxt.getRealPath("/");
        logger.info("Путь приложения определен как: " + baseDir);
        final String URL = "jdbc:hsqldb:file:" + baseDir + relative_url;
        logger.info("URL базы данных определен как: " + URL);
        JDBCDataSource ds = new JDBCDataSource();
        ds.setURL(URL);
        ds.setUser(user);
        ds.setPassword(password);
        logger.info("Объект DataSource создан успешно.");
        return ds;
    }
}
