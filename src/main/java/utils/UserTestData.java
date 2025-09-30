package utils;

import com.github.javafaker.Faker;

public class UserTestData {

    public static final String FIRST_NAME = "RK";

    public static final String LAST_NAME = "V";

    public static final String PASSWORD = "12345678"; //"P@ssword123!";

    private static final Faker faker = new Faker();

    public static String address = faker.address().fullAddress();

    private static String uniqueEmail;

    public static String generateUniqueEmail() {
        return "123@gmail.com";

         /*
        if (uniqueEmail == null)
            uniqueEmail = "rk" + System.currentTimeMillis() + "@gmail.com";

        return uniqueEmail;
          */
    }

}
