package com.github.paniclab.listeners;

import com.github.paniclab.producers.AppContext;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
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
    private static final String PROPERTY_PATH = "/WEB-INF/cfg/application.properties";
    private static final Properties properties = new Properties();
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

    @PostConstruct
    public void init() {
        print("Запускается метод init класса DatabaseInitListener...");
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext cxt = sce.getServletContext();

        loadAppProperties(cxt);
        DataSource dataSource = new DataSourceFactory(properties).getDataSource(cxt);
        cxt.setAttribute("data_source", dataSource);
        cxt.setAttribute("persistence_method", properties.getProperty("persistence.method"));

        logger.info("Объект DataSource помещен в ServletContext.");
    }

    private void loadAppProperties(ServletContext cxt) {
        logger.info("Идет поиск и загрузка файла properties...");
        final InputStream inputStream = cxt.getResourceAsStream(PROPERTY_PATH);

        try {
            properties.load(inputStream);
            logger.info("Файл properties загружен успешно.");
        } catch (NullPointerException e) {
            logger.severe("Файл properties не найден.");
            e.printStackTrace();
        } catch (IOException e) {
            logger.severe("Ошибка ввода-вывода при загрузке файла properties.");
            e.printStackTrace();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
