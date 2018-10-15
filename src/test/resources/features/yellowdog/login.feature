@Login
Feature: Login YellowDog application
	As an analyst I need to be able to enter my credentials and
	sign in the YellowDog application in order to perform human reviews.

@SmokeTest  @ShouldPass
Scenario: Enter correct credentials on the Login page
    Given user enters "User1" as username and "Pass1" as password to sign in
    Then login should be successful

@ShouldFail
Scenario Outline: Enter incorrect credentials on the Login page
    Given user enters "<username>" as username and "<password>" as password to sign in
    Then login should be unsuccessful
  	Examples: 
    | username  | password      |
    | User1     | WrongPassword |
    | WrongUser | Pass1         |
    | WrongUser | WrongPassword |
    
@Kiet_WIP
Scenario: Enter correct credentials on the Login page (another version)
	Given I am have a valid credential as "User1" and "Pass1"
    When I access the application with that credentials
    Then I should get access to the Human Review application
    
