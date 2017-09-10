package com.github.paniclab.models;

class DoctorImpl extends PersistableObject implements Doctor {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String specialization;

    protected DoctorImpl() {
        super();
    }

    protected DoctorImpl(long id) {
        super(id);
    }


    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Доктор " + lastName + " " + getInitials();
    }

    private String getInitials() {
        return firstName.charAt(0) + ". " + patronymic.charAt(0) + ".";
    }
}
