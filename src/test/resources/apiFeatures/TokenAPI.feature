Feature: Token API


@token  @smokeTest  @regression
Scenario: Get token
	Given User creates request data with "ElshanRasul" and "Elshan123"
	And User submits request to TOKEN api
	And User validates if status code is 200
	Then User retrieves access token from response
	
@token  @negative	 @smokeTest  @regression
Scenario: negative scenario: Password is missing
	Given User creates request data with "ElshanRasul" and ""
	And User submits request to TOKEN api
	And User validates if status code is 400
	Then User validates error message "The Password field is required."
	
	@token  @negative	 @smokeTest  @regression"
Scenario: negative scenario: Username is missing
	Given User creates request data with "" and "Elshan123"
	And User submits request to TOKEN api
	And User validates if status code is 400
	Then User validates error message "The UserNameOrEmailAddress field is required."
	