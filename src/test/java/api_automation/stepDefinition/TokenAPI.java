package api_automation.stepDefinition;


import api_automation.RequestBuilder.TokenRequestBuilder;
import api_automation.utils.FunctionLibrary;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import com.jayway.jsonpath.JsonPath;




public class TokenAPI extends FunctionLibrary {
	private String requestData;
	private Response response;
	
	@Given("User creates request data with {string} and {string}")
	public void user_creates_request_data_with_and(String userID, String password) {
		// create request data
		TokenRequestBuilder payload=new TokenRequestBuilder();
			payload.setUsernameOrEmailAddress(userID);
			payload.setPassword(password);
		// convert payload obj to String	
			requestData=convertObjectToString(payload);
			
	}

	@Given("User submits request to TOKEN api")
	public void user_submits_request_to_TOKEN_api() {
		          response =given()
				              .body(requestData)
				              .contentType(ContentType.JSON)
				              .relaxedHTTPSValidation()
				            .when()
				               .post("https://34.235.0.4/api/tokenauth/authenticate");
		response.prettyPrint();
	   
	}

	@Given("User validates if status code is {int}")
	public void user_validates_if_status_code_is(Integer statusCode) {
		// validate status code of response
		  response.then().statusCode(statusCode);
	}

	@Then("User retrieves access token from response")
	public void user_retrieves_access_token_from_response() {
		
		String token=JsonPath.read(response.asString(), "$.result.accessToken");
		System.out.println(token);
	    assertNotNull(token);
	}
	
	@Then("User validates error message {string}")
	public void user_validates_error_message(String expectedErrorMessage) {
		 String actualError=JsonPath.read(response.asString(), "$.error.validationErrors[0].message");
		 System.out.println(actualError);
	     assertEquals(expectedErrorMessage, actualError);
		 
	}
	


}
