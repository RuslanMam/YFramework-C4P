Feature: GetMonthlyExpenses API

@monthlyExpense @smokeTest  @regression
Scenario:  Get Monthly Expenses API - Positive Scenario
Given User get token for "company" account
When User submits GET request to "monthlyExpensesURL"
And User validates if the status code is 200
Then User validates if following elements exist in response
		|months   		|
		|expenses	    |	 
		
