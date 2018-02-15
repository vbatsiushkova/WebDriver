package web.service;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

import static web.service.Constant.*;

/**
 * Created by Volha_Batsiushkova on 2/15/2018.
 */
public class RestAssuredProperties
{
	@BeforeTest
	public void initTest() {
		
        String basePath = System.getProperty("server.base");
		if(basePath==null){
			basePath = BASE_PATH;
		}
		RestAssured.basePath = basePath;

		String baseHost = System.getProperty("server.host");
		if(baseHost==null){
			baseHost = URL_PLACEHOLDER;
		}
		RestAssured.baseURI = baseHost;
	}

}
