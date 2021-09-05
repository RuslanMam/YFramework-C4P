Feature: Personal info API

@personalInfo @smokeTest  @regression @flag
Scenario Outline:  Personal INFO API - Positive Scenario
Given I get token for "company" account when flag is "<flag>"
When I submit GET request to "personalInfoURL"
And I validate if the status code is 200
Then I validate if element "<element>" exist in response

Examples:
|flag			| element 						|
|true			|name   							|
|false		|surname		 			  	|
|false		|middleName 				  |
|false 		|companyName	  			|
|false		|emailAddress					|
|false		|phoneNumber					|
|false		|userName							|
|false		|address 							|
|false		|address2							|
|false		|countryState					|
|false		|city									|
|false		|postalCode						|
|false		|tenantName						|
|false		|accountType					|
|false		|birthDate						|
|false		|businessType					|
|false		|profileImageUrl			|
|false		|numberOfEmployees		|
|false		|registrationNumber		|	
|false		|approximateAGI				|
|false		|businessTypeId				|
|false		|countryStateId				|







@personalInfo @smokeTest  @regression
Scenario:  Personal INFO API - Positive Scenario
Given User get token for "company" account
When User submits GET request to "personalInfoURL"
And User validates if the status code is 200
Then User validates if following elements exist in response

		|name   							|
		|surname		 			  	|
		|middleName 				  |
 		|companyName	  			|
		|emailAddress					|
		|phoneNumber					|
		|userName							|
		|address 							|
		|address2							|
		|countryState					|
		|city									|
		|postalCode						|
		|tenantName						|
		|accountType					|
		|birthDate						|
		|businessType					|
		|profileImageUrl			|
		|numberOfEmployees		|
		|registrationNumber		|	
		|approximateAGI				|
		|businessTypeId				|
		|countryStateId				|

		
		
		