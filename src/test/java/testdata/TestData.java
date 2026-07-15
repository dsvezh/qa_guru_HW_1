package testdata;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class TestData {

    public static final String SUCCESS_MODAL_TITLE = "Thanks for submitting the form";
    public static final String ERROR_BORDER_COLOR = "rgb(220, 53, 69)";
    public static final String ERROR_GENDER_COLOR = "rgba(220, 53, 69, 1)";
    public static final String FIELD_ERROR_CLASS = "field-error";

    public static final String REG_MALE_GENDER = "Male";
    public static final String REG_FEMALE_GENDER = "Female";
    public static final String REG_OTHER_GENDER = "Other";
    public static final String REG_PICTURE_NAME = "CatHarry.jpg";

    private final Faker faker = new Faker(Locale.ENGLISH);
    private final Date birthDate = faker.date().birthday(18, 70);

    public final String regFirstName = faker.name().firstName();
    public final String regLastName = faker.name().lastName();
    public final String regFullName = regFirstName + " " + regLastName;
    public final String regEmail = faker.internet().emailAddress();
    public final String regInvalidEmail = faker.name().username() + "-invalid-email";
    public final String regMobile = faker.numerify("##########");
    public final String regGender = getRandomGender();
    public final String regBirthDay = formatBirthDate("dd");
    public final String regBirthMonth = formatBirthDate("MMMM");
    public final String regBirthYear = formatBirthDate("yyyy");
    public final String regExpectedBirthDate = regBirthDay + " " + regBirthMonth + "," + regBirthYear;
    public final String regExpectedDefaultBirthDate =
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    public final String regSubject = getRandomSubject();
    public final String regHobby = getRandomHobby();
    public final String regCurrentAddress = faker.address().streetAddress();
    public final String regState = getRandomState();
    public final String regCity = getRandomCity(regState);
    public final String regExpectedStateAndCity = regState + " " + regCity;

    public final String textBoxFullName = faker.name().fullName();
    public final String textBoxEmail = faker.internet().emailAddress();
    public final String textBoxInvalidEmail = faker.name().username() + "-invalid-email";
    public final String textBoxInvalidEmailWithSpaces = faker.name().username() + " @example.com";
    public final String textBoxCurrentAddress = faker.address().streetAddress();
    public final String textBoxPermanentAddress = faker.address().streetAddress();

    private String formatBirthDate(String pattern) {
        return new SimpleDateFormat(pattern, Locale.ENGLISH).format(birthDate);
    }

    public String getRandomGender() {
        return faker.options().option(REG_MALE_GENDER, REG_FEMALE_GENDER, REG_OTHER_GENDER);
    }

    public String getRandomSubject() {
        return faker.options().option(
                "Maths", "English", "Computer Science", "Chemistry", "Economics",
                "Social Studies", "Physics", "Biology", "Arts", "History",
                "Civics", "Commerce", "Hindi", "Accounting");
    }

    public String getRandomHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalArgumentException("Unknown state: " + state);
        };
    }
}
