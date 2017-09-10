package com.github.paniclab.models;

import java.util.Date;

class ReceiptImpl extends PersistableObject implements Receipt {
    private String description;
    private Doctor doctor;
    private Patient patient;
    private Priority priority;
    private Date creationDate;
    private Date expirationDate;

    protected ReceiptImpl() {
        super();
    }

    protected ReceiptImpl(long id) {
        super(id);
    }


    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    @Override
    public String toString() {
        return "Рецепт №" + getId() + " выдан врачом " + getDoctor() + " " + getCreationDate();
    }
}
