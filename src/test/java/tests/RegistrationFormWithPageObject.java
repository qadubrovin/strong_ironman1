package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationPage;

import static com.codeborne.selenide.Condition.text;


public class RegistrationFormWithPageObject {

    StudentRegistrationPage registrationPage = new StudentRegistrationPage();

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender3 = "Other",
            mobile = faker.number().digits(10),
            subject = "English",
            hobby = "Sports",
            picturePath = "img/Screenshot_2.png",
            pictureName = "Screenshot_2.png",
            currentAddress = faker.address().fullAddress(),
            state = "Uttar Pradesh",
            city = "Agra";


    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillStudentRegistrationForm() {

        //fill form
        registrationPage.openRegistrationForm();
        registrationPage.name.setValue(firstName);
        registrationPage.lastName.setValue(lastName);
        registrationPage.userEmail.setValue(email);
        registrationPage.otherGender.click();
        registrationPage.userNumber.setValue(mobile);
        registrationPage.setDate("August", "1988");
        registrationPage.subjectInput.val(subject).pressEnter();
        registrationPage.submitButton.scrollIntoView(true);
        registrationPage.hobbySports.click();
        registrationPage.uploadPicture.uploadFromClasspath(picturePath);
        registrationPage.setStateAndCity();
        registrationPage.usersAddress.setValue(currentAddress);
        registrationPage.submitButton.click();

        //check successful popup
        registrationPage.successHeader.shouldHave(text("Thanks for submitting the form"));
        registrationPage.nameColumn.shouldHave(text(firstName + ' ' + lastName));
        registrationPage.studentEmailColumn.shouldHave(text(email));
        registrationPage.studentGenderColumn.shouldHave(text(gender3));
        registrationPage.birthdateColumn.shouldHave(text("Date of Birth 15 August,1988"));
        registrationPage.mobileColumn.shouldHave(text(mobile));
        registrationPage.subjectsColumn.parent().shouldHave(text(subject));
        registrationPage.hobbyColumn.parent().shouldHave(text(hobby));
        registrationPage.pictureColumn.parent().shouldHave(text(pictureName));
        registrationPage.addressColumn.shouldHave(text(currentAddress));
        registrationPage.stateAndCityColumn.parent().shouldHave(text(state + ' ' + city));

    }
}
