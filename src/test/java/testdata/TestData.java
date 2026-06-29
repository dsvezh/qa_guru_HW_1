package testdata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TestData {

    public static final String PRACTICE_FORM_URL = "/automation-practice-form";
    public static final String TEXT_BOX_URL = "/text-box";

    public static final String SUCCESS_MODAL_TITLE = "Thanks for submitting the form";
    public static final String ERROR_BORDER_COLOR = "rgb(220, 53, 69)";
    public static final String ERROR_GENDER_COLOR = "rgba(220, 53, 69, 1)";
    public static final String FIELD_ERROR_CLASS = "field-error";

    public static final String STUDENT_NAME_LABEL = "Student Name";
    public static final String STUDENT_EMAIL_LABEL = "Student Email";
    public static final String GENDER_LABEL = "Gender";
    public static final String MOBILE_LABEL = "Mobile";
    public static final String DATE_OF_BIRTH_LABEL = "Date of Birth";
    public static final String SUBJECTS_LABEL = "Subjects";
    public static final String HOBBIES_LABEL = "Hobbies";
    public static final String PICTURE_LABEL = "Picture";
    public static final String ADDRESS_LABEL = "Address";
    public static final String STATE_AND_CITY_LABEL = "State and City";

    public static final String REG_FIRST_NAME = "Harry";
    public static final String REG_LAST_NAME = "Potter";
    public static final String REG_FULL_NAME = REG_FIRST_NAME + " " + REG_LAST_NAME;
    public static final String REG_EMAIL = "og@potter.com";
    public static final String REG_INVALID_EMAIL = "not-an-email";
    public static final String REG_MOBILE = "8800555353";
    public static final String REG_REQUIRED_MOBILE = "8005553535";
    public static final String REG_MALE_GENDER = "Male";
    public static final String REG_FEMALE_GENDER = "Female";
    public static final String REG_OTHER_GENDER = "Other";
    public static final String REG_BIRTH_DAY = "01";
    public static final String REG_BIRTH_MONTH = "January";
    public static final String REG_BIRTH_YEAR = "2000";
    public static final String REG_EXPECTED_BIRTH_DATE = REG_BIRTH_DAY + " January,2000";
    public static final String REG_EXPECTED_DEFAULT_BIRTH_DATE =
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    public static final String REG_SUBJECT_INPUT = "Math";
    public static final String REG_EXPECTED_SUBJECT = "Maths";
    public static final String REG_HOBBY_SPORTS = "Sports";
    public static final String REG_HOBBY_READING = "Reading";
    public static final String REG_HOBBY_MUSIC = "Music";
    public static final String REG_EXPECTED_HOBBIES = "Sports, Reading, Music";
    public static final String REG_PICTURE_NAME = "CatHarry.jpg";
    public static final String REG_CURRENT_ADDRESS = "221B Baker Street";
    public static final String REG_STATE = "NCR";
    public static final String REG_CITY = "Delhi";
    public static final String REG_EXPECTED_STATE_AND_CITY = REG_STATE + " " + REG_CITY;

    public static final String TEXT_BOX_FULL_NAME = "Ivanov Ivan Ivanovich";
    public static final String TEXT_BOX_EMAIL = "og@vanya.ru";
    public static final String TEXT_BOX_INVALID_EMAIL = "og-vanya.ru";
    public static final String TEXT_BOX_INVALID_EMAIL_WITH_SPACES = "og @vanya.ru";
    public static final String TEXT_BOX_CURRENT_ADDRESS = "35 Marshal Rybalko Street, Perm, Russia";
    public static final String TEXT_BOX_PERMANENT_ADDRESS = "27 Lenin Street, Moscow, Russia";
}
