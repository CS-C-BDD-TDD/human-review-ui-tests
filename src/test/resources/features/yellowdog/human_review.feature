@HumanReview 
Feature: Human-review features 
	As an analyst 
	I want to see a list of fields for a STIX document that need human review
	So that I can ajudicate them for dissemination



Scenario Outline: List of fields 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I choose a STIX document "<stixId>" 
	Then I should see a list of <number of fields> fields that need human review 
	Examples: 
		| stixId                               | number of fields | 
		| 74392d85-7c05-425d-abdb-4ee360878db9 | 4                |
		| 8d1ea7e3-ddaf-4f4a-8eee-26719e39a772 | 2                |
		

Scenario: Redacting a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I readact a field with the following information: 
		| stixId                               | field name         | field value           |
		| 0cfcbdee-0e64-4e9a-a18b-a0b1dc698003 | Header Description | Confidence (08082016) |
	Then I should have the field displayed as follows: 
		| stixId                               | field name         | field value | 
		| 0cfcbdee-0e64-4e9a-a18b-a0b1dc698003 | Header Description | #####       |
		
@Kiet_WIP 
Scenario: Not PII a field 
	Given I am a regular user of the Yellow Dog Application with a valid credential 
	And I login to the website 
	When I Not PII a field with the following information: 
		| stixId                               | field name         | field value           | accepted value                          |
		| 0cfcbdee-0e64-4e9a-a18b-a0b1dc698003 | Header Description | Confidence (08082016) | *** Not a PII *** Confidence (08082016) |
	Then I should have the field displayed as follows: 
		| stixId                               | field name         | field value                             | 
		| 0cfcbdee-0e64-4e9a-a18b-a0b1dc698003 | Header Description | *** Not a PII *** Confidence (08082016) |
