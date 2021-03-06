package com.github.paniclab.listeners;


import org.hsqldb.jdbc.JDBCDataSource;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.logging.Logger;


class DataSourceFactory {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    private String relative_url;
    private String user;
    private String password;


    DataSourceFactory() {}

    DataSourceFactory(Properties props) {
        relative_url = props.getProperty("database.relative.path");
        user = props.getProperty("database.user");
        password = props.getProperty("database.password");
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
