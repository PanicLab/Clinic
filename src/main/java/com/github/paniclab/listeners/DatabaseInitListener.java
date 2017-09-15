package com.github.paniclab.listeners;

import com.github.paniclab.producers.AppContext;
import com.github.paniclab.producers.HSQLDB;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static com.github.paniclab.utils.Util.print;


@WebListener
public class DatabaseInitListener implements ServletContextListener {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    public DatabaseInitListener() {
        super();
    }

    @PostConstruct
    public void init() {
        print("Запускается метод init класса DatabaseInitListener...");
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
