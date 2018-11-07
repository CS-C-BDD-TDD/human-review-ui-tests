@Authentication 
Feature: Authenticate users 
	As an analyst, 
	I want to access the Yellow Dog application using secure credential
	So that I can perform my work securely.
	
Scenario: Authenticate user to the Yellow Dog application 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	When I login to the website 
	Then I should be presented with a list of pending human review items 
	
	
Scenario Outline: Reject invalid user login attempt 
	Given I am a regular user of the Yellow Dog Application with an invalid credential as "<username>" and "<password>" 
	When I login to the website 
	Then I should be denied access. 
	Examples: 
		| username  | password      |
		| User1     | WrongPassword |
		| WrongUser | Pass1         |
		| WrongUser | WrongPassword |