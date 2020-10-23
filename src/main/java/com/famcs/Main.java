package com.famcs;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String LOG_FILE_NAME = "logfile.txt";
    private static String INPUT_FILE_NAME = "input.csv";
    private static String OUTPUT_FILE_NAME = "output.csv";

    public static void main(String[] args) {

        if (args.length >= 2) {
            INPUT_FILE_NAME = args[0];
            OUTPUT_FILE_NAME = args[1];
        }

        else {
            System.out.println("...Default files are used...");
        }

        try (Scanner scanner = new Scanner(System.in);
             Scanner inputFile = new Scanner(new FileReader(INPUT_FILE_NAME));
             FileWriter outputFile = new FileWriter(OUTPUT_FILE_NAME)) {
            {
                LOGGER.setLevel(Level.FINE);
                FileHandler fh = new FileHandler(LOG_FILE_NAME, true);
                fh.setLevel(Level.FINE);
                fh.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fh);
                LOGGER.fine("Program started...\n");
            }
            List<Company> companyList = readListOfCompanies(inputFile);
            List<Company> foundCompanies = getCompaniesByRequest(scanner, companyList);
            writeFoundCompanies(outputFile, foundCompanies);
        }  catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }

    public static void showMenu() {
        System.out.println("Possible requests: ");
        System.out.println("1: Find a company by short name");
        System.out.println("2: Find companies by department of work");
        System.out.println("3: Find companies by activity kind");
        System.out.println("4: Find companies by foundation date (between date1 and date2) [dd.MM.yyyy]");
        System.out.println("5: Find companies by employees number (between num1 and num2)");
        System.out.println("0: Exit");
        System.out.println();
    }

    static List<Company> readListOfCompanies(Scanner input) throws ParseException {
        String[] args;
        List<Company> companyList = new ArrayList<>();

        while (input.hasNextLine()) {
            args = input.nextLine().split(";");
            companyList.add(new Company(args));
        }
        return companyList;
    }

    private static void writeFoundCompanies(FileWriter output, List<Company> list) throws IOException {
        if (list.isEmpty()) {
            output.write("NONE");
        }
        for (Company company : list) {
            output.write(company.toString() + System.lineSeparator());
        }
    }

    static List<Company> getCompaniesByRequest(Scanner scanner, List<Company> companyList) throws ParseException, IllegalArgumentException {
        int key;
        List<Company> result = new ArrayList<>();
        String requestData;
        while (true) {
            showMenu();
            System.out.println("Enter key: ");
            key = scanner.nextInt();
            scanner.nextLine();

            switch (Requests.RequestNumber.getTypeFromInt(key)) {
                case BY_SHORT_NAME:
                    System.out.println("Enter shortName: ");
                    String shortName = scanner.nextLine();
                    requestData = shortName;
                    result = Requests.findByShortName(companyList, shortName);
                    break;
                case BY_DEPARTMENT:
                    System.out.println("Enter sphere: ");
                    String industry = scanner.nextLine();
                    requestData = industry;
                    result = Requests.findBySphere(companyList, industry);
                    break;
                case BY_ACTIVITY:
                    System.out.println("Enter kind of activity: ");
                    String activity = scanner.nextLine();
                    requestData = activity;
                    result = Requests.findByActivity(companyList, activity);
                    break;
                case BY_FOUNDATION_DATE:
                    System.out.println("Enter 2 dates (example: 01.01.2001 20.07.2077): ");
                    requestData = scanner.nextLine();
                    String[] dates = requestData.split(" ");
                    if (dates.length != 2) {
                        throw new IllegalArgumentException("Error: two dates must be entered!");
                    }
                    Date startDate = Company.dateFormat.parse(dates[0]);
                    Date endDate = Company.dateFormat.parse(dates[1]);
                    result = Requests.findByFoundationDate(companyList, startDate, endDate);
                    break;
                case BY_EMPLOYEE_NUMBER:
                    System.out.println("Enter 2 numbers (example: 1 1000): ");
                    requestData = scanner.nextLine();
                    String[] nums = requestData.split(" ");
                    if (nums.length != 2) {
                        throw new IllegalArgumentException("Error: 2 numbers must be entered");
                    }
                    int minNumber = Integer.parseInt(nums[0]);
                    int maxNumber = Integer.parseInt(nums[1]);
                    result = Requests.findByEmployeeNumber(companyList, minNumber, maxNumber);
                    break;
                default:
                    Main.LOGGER.fine("EXIT\n");
                    return result;
            }

            System.out.println("Companies found: ");
            if (result.isEmpty()) {
                System.out.println("None\n");
            } else {
                result.forEach(System.out::println);
                System.out.println("");
            }

            Main.LOGGER.fine("Find company " + Requests.RequestNumber.getTypeFromInt(key) +
                    "::" + requestData + ", Companies found: " + result.size() + "\n");
        }
    }
}
