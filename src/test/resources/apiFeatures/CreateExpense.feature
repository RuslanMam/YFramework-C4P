Feature: Create Expense API

@createExpense1 @regression @smokeTest
Scenario Outline: Create expense
Given I get token for "company" account when flag is "<flag>"
Then User creates request body with type "Other"  and otherExpenseNameId <otherExpenseNameId> for expense API
And User submits POST request to "createExpenseURL" api
And I validate if the status code is 200
Then I validate if value in "<jsonPath>" equals to "<value>"

Examples:
		|flag          |jsonPath         	|otherExpenseNameId	 | value 						|     
		|true          |$.result.name			|  1								 | Electricity      |
		|true          |$.result.name			|  2								 | Rent				      |
		|true          |$.result.name			|  3								 | Gas				      |
		|true          |$.result.name			|  4								 | Vocation expense |



@createExpense @regression @smokeTest
Scenario Outline: Create expense

Given I get token for "company" account when flag is "<flag>"
Then User creates request body with type "Other"  and otherExpenseNameId 1 for expense API
And User submits POST request to "createExpenseURL" api
And I validate if the status code is 200
Then I validate if element "<element>" exist in response

Examples:
		|flag          |element         		 |        
		|true          |id			             |
		|false         |name								 |
    |false         |amount							 |
    |false         |expenseType					 |
    |false         |expenseDateTime			 |
    |false         |businessPurpose			 |
    |false         |natureOfGift				 |
    |false         |giftRecipient				 |
    |false         |vendorName					 |
    |false         |destinationOfTravel	 |
    |false         |company							 |
    |false         |projectName					 |
    |false         |longitude						 |
    |false         |latitude					   |
    |false         |placeName						 |
    |false         |placeAddress				 |
    |false         |placeId							 |
    |false         |placeIcon						 |
    |false         |expenseRelationshipId|
    |false         |travelExpenseTypeId	 |
    |false         |otherExpenseNameId   |
    |false         |otherRelationship    |
    |false         |receiptFile					 |
    |false         |receiptUrl 					 |
    
@createGiftExpense @regression @smokeTest
Scenario Outline: Create expense

Given I get token for "company" account when flag is "<flag>"
Then User creates request body with type "Gift"  and otherExpenseNameId 0 for expense API
And User submits POST request to "createExpenseURL" api
And I validate if the status code is 200
Then I validate if element "<element>" exist in response

Examples:
		|flag          |element         		 |        
		|true          |id			             |
		|false         |name								 |
    |false         |amount							 |
    |false         |expenseType					 |
    |false         |expenseDateTime			 |
    |false         |businessPurpose			 |
    |false         |natureOfGift				 |
    |false         |giftRecipient				 |
    |false         |vendorName					 |
    |false         |destinationOfTravel	 |
    |false         |company							 |
    |false         |projectName					 |
    |false         |longitude						 |
    |false         |latitude					   |
    |false         |placeName						 |
    |false         |placeAddress				 |
    |false         |placeId							 |
    |false         |placeIcon						 |
    |false         |expenseRelationshipId|
    |false         |travelExpenseTypeId	 |
    |false         |otherExpenseNameId   |
    |false         |otherRelationship    |
    |false         |receiptFile					 |
    |false         |receiptUrl 					 |    
    
