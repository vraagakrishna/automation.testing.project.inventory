package utils;

import com.github.javafaker.Faker;

import java.io.IOException;

public class UserTestData {

    private static final Faker faker = new Faker();

    public static String address = faker.address().fullAddress();

    private static String firstName;

    private static String lastName;

    private static String password;

    private static String emailPrefix;

    private static String emailDomain;

    private static String uniqueEmail;

    public UserTestData() throws IOException {
        ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();

        firstName = readDataFromExcel.getStringDataFromExcel("Login", 1, 0);
        lastName = readDataFromExcel.getStringDataFromExcel("Login", 1, 1);
        emailPrefix = readDataFromExcel.getStringDataFromExcel("Login", 1, 2);
        emailDomain = readDataFromExcel.getStringDataFromExcel("Login", 1, 3);
        password = readDataFromExcel.getStringDataFromExcel("Login", 1, 4);
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUniqueEmail() {
        generateUniqueEmail();
        return uniqueEmail;
    }

    private static void generateUniqueEmail() {
        if (uniqueEmail == null)
            uniqueEmail = emailPrefix + emailDomain;
        // uniqueEmail = emailPrefix + System.currentTimeMillis() + emailDomain;
    }

}
