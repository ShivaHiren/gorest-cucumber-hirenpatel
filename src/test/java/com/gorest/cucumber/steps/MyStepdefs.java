package com.gorest.cucumber.steps;

import com.gorest.gorestinfo.UsersSteps;
import com.gorest.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {
    static ValidatableResponse response;

    static String name;
    static String gender;
    static String email;
    static String status;
    static int id;

    @Steps
    UsersSteps usersSteps;

    @When("^I create a new user by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationNameEmailGenderStatus(String _name, String _email, String gender, String status) {
        name = TestUtils.getRandomValue() + _name;
        email = TestUtils.getRandomValue() + _email;
        response = usersSteps.createUser(name, email, gender, status);
    }

    @Then("^I verify that the user with \"([^\"]*)\" is created$")
    public void iVerifyThatTheUserWithIsCreated(int userId) {
        HashMap<String, Object> userMap = (HashMap<String, Object>) usersSteps.getUserById(userId);
        Assert.assertThat(userMap, hasValue(userId));


    }


    @When("^User is added$")
    public void userIsAdded() {
    }

    @When("^User sends a Post request$")
    public void userSendsAPostRequest() {
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(201);

    }
}
