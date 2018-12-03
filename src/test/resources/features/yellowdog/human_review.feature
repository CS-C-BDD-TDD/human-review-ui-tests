@HumanReview 
Feature: Human-review features 
	As an analyst 
	I want to see a list of fields for a STIX document that need human review
	So that I can ajudicate them for dissemination

Background: 
	Given I submit a STIX document to Yellow Dog "/stix_docs/identifying_threat_actor_profile.json" 
	And I am a regular user of the Yellow Dog Application with a valid credential 

@Kiet_WIP	
Scenario: List of fields 
	Given I login to the website 
	When I look for all human review fields for a STIX document 
	Then I should see 3 fields that need human review 
	
	
	
Scenario: Redacting a field 
	Given I login to the website 
	When I readact the following fields: 
		| field name |
		| name       |
	Then the modified fields should be as follows: 
		| field name | field value | status   |
		| name       | #####       | Redacted |
		
		
Scenario: Not PII a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I Not PII the following fields: 
		| field name |
		| name       |
	Then the modified fields should be as follows: 
		| field name | field value                   | status   |
		| name       | Disco Team Threat Actor Group | Not PII |
		
		
		
Scenario: Confirm Risk a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I Confirm Risk the following fields: 
		| field name |
		| name       |
	Then the modified fields should be as follows: 
		| field name | field value                   | status    |
		| name       | Disco Team Threat Actor Group | Confirmed |
		
		
Scenario: Edit a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I Edit the following fields: 
		| field name | field value          |
		| name       | *** Edited value *** |
	Then the modified fields should be as follows: 
		| field name | field value          | status |
		| name       | *** Edited value *** | Edited |
