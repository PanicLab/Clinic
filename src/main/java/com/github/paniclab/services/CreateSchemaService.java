package com.github.paniclab.services;

import javax.servlet.ServletContext;
import java.nio.file.Paths;

public interface CreateSchemaService {
    String RELATIVE_SCRIPT_PATH = Paths.get("WEB-INF", "sql").toString();

    static CreateSchemaService create(ServletContext cxt) {
        String attribute = (String)cxt.getAttribute("persistence_method");
        if (attribute == null) {
            attribute = "JDBC";
        }
        PersistenceMethod METHOD = PersistenceMethod.valueOf(attribute);

        switch (METHOD) {
            case JDBC: {
                return new JDBCCreateSchemaService(cxt);
            }
            case JPA: {
                throw new InternalError("Использование JPA на данный момент не реализовано");
            }
            default: {
                throw new InternalError("Неизвестный тип перзистентности.");
            }
        }
    }

    void createSchema(ServletContext context);
}
