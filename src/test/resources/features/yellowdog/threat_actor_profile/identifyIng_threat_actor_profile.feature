Feature: Human-review features 
	As an analyst 
	I want to see a list of fields for a STIX document that need human review
	So that I can ajudicate them for dissemination
	
Background: 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	
@Kiet_WIP 
Scenario: Disseminating a redacted field. 
	Given I submit a STIX document to Yellow Dog "/stix_docs/identifying_threat_actor_profile.json" 
	And I login to the website 
	And I redact these fields and accept other fields as not PII: 
		| field name |
		| name       | 
	When I disseminate the document 
	Then I should receive a new document with these updated fields: 
		| field name | field value |
		| name       | #####       |
	And other fields remain the same 
	