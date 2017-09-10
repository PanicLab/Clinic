package com.github.paniclab.models;

class PatientImpl extends PersistableObject implements Patient {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;

    protected PatientImpl() {
        super();
    }

    protected PatientImpl(long id) {
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
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "Пациент " + lastName + " " + getInitials();
    }

    private String getInitials() {
        return firstName.charAt(0) + ". " + patronymic.charAt(0) + ".";
    }
}
