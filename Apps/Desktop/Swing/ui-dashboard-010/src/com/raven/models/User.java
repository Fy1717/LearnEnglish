package com.raven.models;

public class User {
    private static String idNumber;
    private static String name;
    private static String surname;
    private static String birthday;
    private static String docNumber;
    private static String validDate;
    private static String gender;
    private static String nation;
    private static String city;
    private static String country;

    private static User user;

    private User() {}

    public static User getInstanceUser(){
        if(user == null)
            user = new User();

        return user;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        User.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        User.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        User.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        User.birthday = birthday;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        User.docNumber = docNumber;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        User.validDate = validDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        User.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        User.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        User.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        User.country = country;
    }
}
