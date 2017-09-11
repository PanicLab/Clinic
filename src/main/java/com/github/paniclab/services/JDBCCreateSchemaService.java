package com.github.paniclab.services;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

class JDBCCreateSchemaService implements CreateSchemaService {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());
    private Connection connection;
    private Path scriptPath;

    JDBCCreateSchemaService(ServletContext cxt) {
        logger.info("Попытка создания службы CreateSchemaService, тип перзистентности JDBC...");

        String relativeSQLScriptPath = cxt.getInitParameter("db.schema_script_path");
        scriptPath = Paths.get(cxt.getRealPath("/"), relativeSQLScriptPath);
        logger.info("Путь к SQL скриптам определен как: " + scriptPath);

        DataSource dataSource = (DataSource)cxt.getAttribute("data_source");
        try {
            connection = dataSource.getConnection();
            logger.info("Объект Connection получен успешно.");
            logger.info("СОздание службы CreateSchemaService завершилось успешно.");
        } catch (SQLException e) {
            logger.severe("Не удалось получить объект Connection. Ошибка при обращении к БД");
            logger.severe("Не удалось создать службу CreateSchemaService.");
            e.printStackTrace();
        }
    }


    @Override
    public void createSchema(ServletContext cxt) {
        logger.info("Служба CreateSchemaService пытается выполнить скрипт создания схемы БД...");
        String path = Paths.get(CreateSchemaService.RELATIVE_SCRIPT_PATH, "createSchema.sql").toString();
        InputStream inputStream = cxt.getResourceAsStream(path);
        executeScript(inputStream);
    }

    private void executeScript(InputStream stream) {
        SqlFile sqlFile;
        try {
            sqlFile = new SqlFile(new InputStreamReader(stream),
                    "init",
                    System.out,
                    "UTF-8",
                    false,
                    new File("."));
            sqlFile.setConnection(connection);
            sqlFile.execute();
            logger.info("Выполнение скрипта успешно завершено.");
        } catch (IOException e) {
            logger.severe("Не удалось выполнить скрипт создания схемы БД. Ошибка ввода/вывода.");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.severe("Не удалось выполнить скрипт создания схемы БД. Ошибка при обращении к БД.");
            e.printStackTrace();
        } catch (SqlToolError e) {
            logger.severe("Не удалось выполнить скрипт создания схемы БД. Неведомая ошибка HSQLDB.");
            e.printStackTrace();
        }
    }
}
