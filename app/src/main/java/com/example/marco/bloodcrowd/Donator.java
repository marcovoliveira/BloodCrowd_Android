package com.example.marco.bloodcrowd;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Marco on 03/01/2018.
 */

public class Donator implements Serializable{

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("Sexo")
    private String sexo;

    @SerializedName("StreetAddress")
    private String streetAddress;

    @SerializedName("City")
    private String city;

    @SerializedName("Statefull")
    private String statefull;

    @SerializedName("Latitude")
    private String latitude;

    @SerializedName("Longitude")
    private String longitude;

    @SerializedName("ZipCode")
    private String zipCode;

    @SerializedName("EMail")
    private String eMail;

    @SerializedName("UserName")
    private String userName;

    @SerializedName("Password")
    private String password;

    @SerializedName("MothersMaiden")
    private String mothersMaiden;

    @SerializedName("BirthDay")
    private String birthDay;

    @SerializedName("Occupation")
    private String occupation;

    @SerializedName("Company")
    private String company;

    @SerializedName("Vehicle")
    private String vehicle;

    @SerializedName("BloodType")
    private String bloodType;

    @SerializedName("Guid")
    private String guid;

    @SerializedName("Number")
    private int number;

    @SerializedName("Age")
    private int age;

    @SerializedName("TelephoneNumber")
    private long telephoneNumber;

    @SerializedName("Kilograms")
    private double kilograms;

    @SerializedName("Centimeters")
    private double centimeters;

    @SerializedName("Imc")
    private double imc;


    public Donator(int age, String birthDay, String bloodType, double centimeters, String city, String company, String eMail,
                   String firstName, String lastName, String streetAddress,
                   String statefull, String latitude, String longitude, String zipCode,
                   String userName, String password, String mothersMaiden,  String occupation,
                    String vehicle, String guid, String sexo,  int number,
                   long telephoneNumber, double kilograms, double imc) {

        this.sexo = sexo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.statefull = statefull;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
        this.eMail = eMail;
        this.userName = userName;
        this.password = password;
        this.mothersMaiden = mothersMaiden;
        this.birthDay = birthDay;
        this.occupation = occupation;
        this.company = company;
        this.vehicle = vehicle;
        this.bloodType = bloodType;
        this.guid = guid;
        this.number = number;
        this.age = age;
        this.telephoneNumber = telephoneNumber;
        this.kilograms = kilograms;
        this.centimeters = centimeters;
        this.imc = imc;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatefull() {
        return statefull;
    }

    public void setStatefull(String statefull) {
        this.statefull = statefull;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMothersMaiden() {
        return mothersMaiden;
    }

    public void setMothersMaiden(String mothersMaiden) {
        this.mothersMaiden = mothersMaiden;
    }

    public String getBirthDay() {
        return birthDay.split("\\s+")[0];
    } //Remove a hora

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public double getKilograms() {return kilograms/10;} // Bug da resposta do appharbor

    public void setKilograms(double kilograms) {
        this.kilograms = kilograms;
    }

    public double getCentimeters() {
        return centimeters;
    }

    public void setCentimeters(double centimeters) {
        this.centimeters = centimeters;
    }

    public double getImc() {
        return round(imc/10, 2);
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    @Override
    public String toString() {
        return " Nome: " + firstName+ " "+ lastName + "  -  Grupo sanguineo: " + bloodType;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
