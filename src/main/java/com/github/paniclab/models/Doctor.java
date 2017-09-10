package com.github.paniclab.models;

public interface Doctor {
    String getFirstName();
    String getLastName();
    String getPatronymic();
    String getSpecialization();

    static Doctor create() {
        return new DoctorImpl();
    }
}
