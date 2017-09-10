package com.github.paniclab.models;

import java.util.Date;

public interface Receipt {
    String getDescription();
    Doctor getDoctor();
    Patient getPatient();
    Priority getPriority();
    Date getCreationDate();
    Date getExpirationDate();

    enum Priority {
        NORMAL, CITO, STATIM
    }
}
