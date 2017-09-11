package com.github.paniclab.producers;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static com.github.paniclab.utils.Util.print;


public class PropertyProducer {
    private static final String PROPERTY_PATH = "WEB-INF/cfg/application.properties";

    private Logger logger;
    private Properties properties;

    public PropertyProducer() {
        logger = Logger.getLogger(getClass().getSimpleName());
        logger.info("Идет поиск и загрузка файла properties...");
    }

    @PostConstruct
    private void init() {
        print("Вызывается метод init в классе PropertyProducer");
        logger = Logger.getLogger(getClass().getSimpleName());
        logger.info("Класс PropertyProducer: идет поиск и загрузка файла properties...");
        properties = new Properties();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_PATH);

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

    @Produces
    @Property
    public String produceString(final InjectionPoint ip) {
        return properties.getProperty(getKey(ip));
    }

    @Produces
    @Property
    public int produceInt(final InjectionPoint ip) {
        return Integer.parseInt(properties.getProperty(getKey(ip)));
    }

    @Produces
    @Property
    public boolean produceBoolean(final InjectionPoint ip) {
        return Boolean.parseBoolean(properties.getProperty(getKey(ip)));
    }

    private String getKey(InjectionPoint ip) {
        String result;
        final Annotated codeElement = ip.getAnnotated();
        if (codeElement.isAnnotationPresent(Property.class)) {
            if (!(codeElement.getAnnotation(Property.class).value().isEmpty())) {
                result = codeElement.getAnnotation(Property.class).value();
                logger.info("Ключ property определен как: " + result);
                return result;
            }
        }
        result = ip.getMember().getName();
        logger.info("Ключ property определен как: " + result);
        return result;
    }
}
