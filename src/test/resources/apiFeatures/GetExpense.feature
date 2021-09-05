Feature: Get expense API

@expense @smokeTest  @regression
Scenario: Get Expense -Positive Scenario

Given User generate token with "company" account
When User submits GET request to GetExpense API
And User validates status code is 200
Then User validates if expenses are present







