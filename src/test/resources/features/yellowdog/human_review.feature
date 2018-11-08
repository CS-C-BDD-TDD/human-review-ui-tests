@HumanReview 
Feature: Human-review features 
	As an analyst 
	I want to see a list of fields for a STIX document that need human review
	So that I can ajudicate them for dissemination

Scenario Outline: List of fields 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I look for all human review fields for a STIX document "<stixId>" 
	Then I should see a list of <number of fields> fields that need human review 
	Examples: 
		| stixId                               | number of fields | 
		| 4fa1581e-74a5-40d7-a7f2-8b3424983de4 | 4                |
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | 3                |
		
@Kiet_WIP 
Scenario: Redacting a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I readact a field with the following information: 
		| stixId                               | field name   | field value  |
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | PII presents |
	Then I should have the field displayed as follows: 
		| stixId                               | field name   | field value | status   |
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | #####       | Redacted |
		
@Kiet_WIP 
Scenario: Not PII a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I Not PII a field with the following information: 
		| stixId                               | field name   | field value  |
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | PII presents |
	Then I should have the field displayed as follows: 
		| stixId                               | field name   | field value  | status  |  
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | PII presents | Not PII |
		
@Kiet_WIP 
Scenario: Confirm Risk a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I Confirm Risk a field with the following information: 
		| stixId                               | field name   | field value  |
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | PII presents |
	Then I should have the field displayed as follows: 
		| stixId                               | field name   | field value  | status    |
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | PII presents | Confirmed |
		
@Kiet_WIP 
Scenario: Edit a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I Edit a field with the following information: 
		| stixId                               | field name   | field value  | accepted value          |
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | PII presents | *** Value is edited *** |
	Then I should have the field displayed as follows: 
		| stixId                               | field name   | field value             | status |  
		| b6ab42d9-959d-41ac-8017-752f0ec6a919 | Header Title | *** Value is edited *** | Edited |
		
