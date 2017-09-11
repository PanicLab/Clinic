package com.github.paniclab.models;


import com.github.paniclab.producers.AppContext;
import com.github.paniclab.utils.Util;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;


public class CDIExaminer {

    CDIExaminer() {}
    //TODO этот класс должен быть удален из релиза
    public void echo() {
        Util.print("Внедрение прошло успешно");
    }

    @Produces @AppContext
    public String produce() {
        return "Внедрение прошло успешно";
    }
}
