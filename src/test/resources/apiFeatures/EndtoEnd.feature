Feature: End to End scenario
@EndtoEnd @US123
Scenario Outline: Create expense End2End scenario
Given I get token for "company" account when flag is "<flag>"
Then User creates request body with type "Other"  and otherExpenseNameId 1 for expense API
And User submits POST request to "createExpenseURL" api
And I validate if the status code is 200
And I retrieves "id" from response
When I submit GET request to "expensesURL_ByID"
And I validate if the status code is 200
Then I submits DELETE expense request
And I validate if the status code is 200
When I submit GET request to "expensesURL_ByID"
And I validate if the status code is 404

Examples:
|flag|
|true|
