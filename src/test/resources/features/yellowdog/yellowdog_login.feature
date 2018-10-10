@Login
Feature: Login YellowDog app to do human-review
	 	 As an analyst I need to be able to enter my credentials and
	 	 sign in the YellowDog application in order to perform human reviews.

@ShouldPass
Scenario: Enter correct credentials on the Login page
    Given user enters "User1" as username and "Pass1" as password to sign in
    #Then  login should be successful

Scenario Outline: Enter incorrect credentials on the Login page
    Given user enters "<username>" as username and "<password>" as password to sign in
    #Then login should be unsuccessful
  	Examples: 
    | username  | password      |
    | User1     | WrongPassword |
    | WrongUser | Pass1         |
    | WrongUser | WrongPassword |