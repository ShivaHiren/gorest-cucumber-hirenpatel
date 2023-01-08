Feature: Testing User creation and editing

  Scenario: Add new user
    When User sends a Post request
    Then User must get back a valid status code 201

  Scenario Outline: Create a new user & verify if the user is added
    When I create a new user by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then I verify that the user with "<id>" is created
    Examples:
      | name | email          | gender | status |
      | jhon | jhon@gmail.com | Male   | Active |

  Scenario: Verify User was added
    When User is added
    Then I verify that the user with "<id>" is created







