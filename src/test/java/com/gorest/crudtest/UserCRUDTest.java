package com.gorest.crudtest;

import com.gorest.gorestinfo.UsersSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {

    static String name = "Jhon";
    static String newName = "Jhon Wick";
    static String gender = "male";
    static String email = TestUtils.getRandomValue() + "@gmail.com";
    static String status = "Active";
    static int id;

    @Steps
    UsersSteps usersSteps;

    @Title("This will create a User")
    @Test
    public void test001() {
        ValidatableResponse response = usersSteps.createUser(name, email, gender, status);
        response.log().all().statusCode(201);
        id = response.log().all().extract().path("id");
        System.out.println(id);

    }

    @Title("Verify if the user was added ")
    @Test
    public void test002() {
        ValidatableResponse response = usersSteps.getUserById(id);
        response.log().all().statusCode(200);

    }

    @Title("This will update user records")
    @Test
    public void test003() {
        status = "inactive";
        usersSteps.updateUser(id, name, email, gender, status);
        ValidatableResponse response = usersSteps.getUserById(id).statusCode(200);
        HashMap<String, ?> userRecord = response.extract().path("");
        Assert.assertThat(userRecord, hasValue("inactive"));
    }


    @Title("This will Delete user by ID")
    @Test
    public void test004() {
        ValidatableResponse response = usersSteps.deleteUser(id);
        response.log().all().statusCode(204);
    }
}

