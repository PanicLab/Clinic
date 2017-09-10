package com.github.paniclab.Listeners;

import com.github.paniclab.producers.Property;
import com.github.paniclab.producers.PropertyProducer;
import org.hsqldb.jdbc.JDBCDataSource;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;


@WebListener
public class DatabaseInitListener implements ServletContextListener {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

/*    @Inject
    @Property("database.relative.path")
    private String url;

    @Inject
    @Property("database.user")
    private String user;

    @Inject
    @Property("database.password")
    private String password;*/


    public DatabaseInitListener() {
        super();
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("data_source", new DataSourceFactory().getDataSource(context));
        logger.info("Объект DataSource помещен в ServletContext.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
