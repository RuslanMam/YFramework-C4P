package api_automation.utils;



import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class FunctionLibrary extends TestBase {
	
	public Response submitGETRequest(String token, String url) {
		Response getResponse=given()
  								.header("Authorization", "Bearer "+token)
  							 .when().
  							 	get(property.getProperty(url));		
		return getResponse;
	}
	
	
	
	// this method submits token request and return Response obj
	public Response submitTokenAPIRequest(String requestData ) {
		  Response response =given()
	              .body(requestData)
	              .contentType(ContentType.JSON)
	              .relaxedHTTPSValidation()
		         .when()
		           .post(property.getProperty("tokenURL"));
		  
		  return response;
	}
	
	
	

	public String convertObjectToString(Object payload) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
		return jsonString;
	}

	
	
	

}
