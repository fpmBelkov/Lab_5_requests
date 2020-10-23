package com.famcs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

class Company {

    private String name;
    private String shortName;
    private Date actualizationDate;
    private String address;
    private Date foundationDate;
    private int employeeNumber;
    private String auditor;
    private String phoneNumber;
    private String email;
    private String branch;
    private String activityType;
    private String webSite;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    Company (String name, String shortName, Date actualizationDate,
             String address, Date foundationDate, int employeeNumber,
             String auditor,String phoneNumber, String email, String branch,
             String activityType, String webSite)                             {

        this.name = name;
        this.shortName = shortName;
        this.actualizationDate = actualizationDate;
        this.address = address;
        this.foundationDate = foundationDate;
        this.employeeNumber = employeeNumber;
        this.auditor = auditor;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.branch = branch;
        this.activityType = activityType;
        this.webSite = webSite;

    }

    Company(String[] aData) throws ParseException, IllegalArgumentException {

        if (aData.length != 12) {
            throw new IllegalArgumentException("Error: wrong number of fields in the argument");
        }

        try {
            this.name = aData[0];
            this.shortName = aData[1];
            this.actualizationDate = dateFormat.parse(aData[2]);
            this.address = aData[3];
            this.foundationDate = dateFormat.parse(aData[4]);
            this.employeeNumber = Integer.parseInt(aData[5]);
            this.auditor = aData[6];
            this.phoneNumber = aData[7];
            this.email = aData[8];
            this.branch = aData[9];
            this.activityType = aData[10];
            this.webSite = aData[11];
        }

        catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Override
    public String toString() {
        return name + ";" + shortName + ";" + actualizationDate.toString() + ";"
                + address + ";" + foundationDate.toString() + ";" + employeeNumber + ";"
                + auditor + ";" + phoneNumber + ";" + email + ";" + branch + ";" + activityType + ";"
                + webSite + ";";
    }



    void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setShortName(String shortName) {
        this.shortName = shortName;
    }

    String getShortName() {
        return shortName;
    }

    void setActualizationDate(Date actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    Date getActualizationDate() {
        return actualizationDate;
    }

    void setAddress(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }

    void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    Date getFoundationDate() {
        return foundationDate;
    }

    void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    int getEmployeeNumber() {
        return employeeNumber;
    }

    void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    String getAuditor() {
        return auditor;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getEmail() {
        return email;
    }

    void setBranch(String branch) {
        this.branch = branch;
    }

    String getBranch() {
        return branch;
    }

    void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    String getActivityType() {
        return activityType;
    }

    void setWebSite (String webSite) {
        this.webSite = webSite;
    }

    String getWebSite() {
        return webSite;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Company company = (Company)obj;
        return employeeNumber == company.employeeNumber &&
                Objects.equals(name, company.name) &&
                Objects.equals(shortName, company.shortName) &&
                Objects.equals(actualizationDate, company.actualizationDate) &&
                Objects.equals(address, company.address) &&
                Objects.equals(foundationDate, company.foundationDate) &&
                Objects.equals(auditor, company.auditor) &&
                Objects.equals(phoneNumber, company.phoneNumber) &&
                Objects.equals(email, company.email) &&
                Objects.equals(branch, company.branch) &&
                Objects.equals(activityType, company.activityType) &&
                Objects.equals(webSite, company.webSite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortName, actualizationDate, address, foundationDate, employeeNumber,
                auditor, phoneNumber, email, branch, activityType, webSite);
    }


}
