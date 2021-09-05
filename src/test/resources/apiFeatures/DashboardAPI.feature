Feature: Dashboard API

@dashboard @smokeTest  @regression @flag
Scenario Outline: Dashboard API - Positive Scenario for Company Account
Given I get token for "company" account when flag is "<flag>"
When I submit GET request to "dashboardURL"
And I validate if the status code is 200
Then I validate if element "<element>" exist in response

Examples:
|flag	  |element                      |
|true		|otherExpenses   							|
|false	|mealExpenses		 							|
|false	|entertainmentExpenses 				|
|false	|mealAndEntertainmentExpenses	|
|false	|travelExpenses								|
|false	|giftExpenses									|
|false	|deductibleGiftExpenses				|
|false	|taxSaving 										|
|false	|numberOfEmployees						|


@dashboard @smokeTest  @regression @flag7  
Scenario Outline: Dashboard API - Positive Scenario for Employee Account
Given I get token for "employee" account when flag is "<flag>"
When I submit GET request to "dashboardURL"
And I validate if the status code is 200
Then I validate if element "<element>" exist in response

Examples:
|flag	  |element                      |
|true		|otherExpenses   							|
|false	|mealExpenses		 							|
|false	|entertainmentExpenses 				|
|false	|mealAndEntertainmentExpenses	|
|false	|travelExpenses								|
|false	|giftExpenses									|
|false	|deductibleGiftExpenses				|
|false	|taxSaving 										|
|false	|numberOfEmployees						|






@smokeTest  @regression
Scenario:  Dashboard API - Positive Scenario
Given User get token for "company" account
When User submits GET request to "dashboardURL"
And User validates if the status code is 200
Then User validates if following elements exist in response
		|otherExpenses   							|
		|mealExpenses		 							|
		|entertainmentExpenses 				|
 		|mealAndEntertainmentExpenses	|
		|travelExpenses								|
		|giftExpenses									|
		|deductibleGiftExpenses				|
		|taxSaving 										|
		|numberOfEmployees						|








