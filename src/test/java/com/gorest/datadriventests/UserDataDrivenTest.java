package com.gorest.datadriventests;

import com.gorest.gorestinfo.UsersSteps;
import com.gorest.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@Concurrent(threads = "8x")
@UseTestDataFrom("src/test/java/com/gorest/testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class UserDataDrivenTest extends TestBase {

    static String name;
    static String email;
    static String newName;
    static String gender;
    static String status;
    static int id;


    @Steps
    UsersSteps usersSteps;

    @Title("Data Driven test for creating multiple users")
    @Test
    public void createMultipleUsers() {
        usersSteps.createUser(name, email, gender, status).statusCode(201);

    }
}