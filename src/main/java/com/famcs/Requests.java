package com.famcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Requests {

    enum RequestNumber {
        BY_SHORT_NAME(1),
        BY_DEPARTMENT(2),
        BY_ACTIVITY(3),
        BY_FOUNDATION_DATE(4),
        BY_EMPLOYEE_NUMBER(5),
        DEFAULT(0);

        private final int value;

        RequestNumber(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }

        public static RequestNumber getTypeFromInt(int value) {
            for (RequestNumber type : values()) {
                if (type.value == value) return type;
            }
            return RequestNumber.DEFAULT;
        }
    }

    static List<Company> findByShortName(List<Company> companyList, String shortName) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getShortName().equalsIgnoreCase(shortName)) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findBySphere(List<Company> companyList, String industry) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getBranch().equalsIgnoreCase(industry)) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findByActivity(List<Company> companyList, String activity) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getActivityType().equalsIgnoreCase(activity)) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findByFoundationDate(List<Company> companyList, Date date1, Date date2) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getFoundationDate().compareTo(date1) >= 0 && company.getFoundationDate().compareTo(date2) <= 0) {
                result.add(company);
            }
        }
        return result;
    }

    static List<Company> findByEmployeeNumber(List<Company> companyList, int num1, int num2) {
        List<Company> result = new ArrayList<>();
        for (Company company : companyList) {
            if (company.getEmployeeNumber() >= num1 && company.getEmployeeNumber() <= num2) {
                result.add(company);
            }
        }
        return result;
    }
}