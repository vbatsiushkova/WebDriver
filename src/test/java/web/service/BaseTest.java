package web.service;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static web.service.Constant.EXPECTED_COUNT_USERS;
import static web.service.Constant.EXPECTE_CONTENT_TYPE;
import static web.service.Constant.SUCCESS_RESPONSE_CODE;

/**
 * Created by Volha_Batsiushkova on 2/15/2018.
 */
public class BaseTest extends RestAssuredProperties
{
	Response rp;

	@Test
	public void verifyStatusCode()
	{
		rp = given().get().andReturn();
		int statusCode = rp.getStatusCode();
		System.out.println("Actual status code of REST request: " + statusCode);
		Assert.assertEquals(statusCode, SUCCESS_RESPONSE_CODE);
	}

	@Test
	public void verifyResponseHeaderIsExist()
	{
		rp = given().get().andReturn();
		String contentType = rp.getContentType();
		System.out.println("Actual contentType of REST request: " + contentType);
		Assert.assertTrue(contentType != null);
	}

	@Test
	public void verifyResponseHeaderContent(){
		rp = given().get().andReturn();
		String contentType = rp.getContentType();
		System.out.println("Actual contentType of REST request: " + contentType);
		Assert.assertEquals(contentType, EXPECTE_CONTENT_TYPE);
	}

	@Test
	public void verifyResponseBody(){
		rp = given().get().andReturn();
		ArrayList<Integer> ids = rp.getBody().jsonPath().get("id");
		int actualUsers = ids.size();
		System.out.println("Actual count of users from REST request: " + actualUsers);
		Assert.assertEquals(actualUsers,EXPECTED_COUNT_USERS);
	}

}
