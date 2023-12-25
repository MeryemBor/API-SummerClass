package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get02  extends HerOkuAppBaseUrl {

    /*
    Given
             https://restful-booker.herokuapp.com/booking/1001
        When
             User  send GET request to the URL
       Then
             HTTP Status code should be 404
      And
              Status line should be HTTP/1.1 404 Not Found
    And
            Response body contains "Not Found"
    And
          Response body does not contain "TechProEd"
  And
         Server is "Cowboy"
     */

    //Note : Path Parameters are used to meke resource smaller
    @Test
    public void get02() {

        //1.Step: Set the URL
      //  String url = " https://restful-booker.herokuapp.com/booking/1001"; ==> Not recommended
        spec.pathParams("first", "booking","second", 12004);

        //2.Step: Set the expected data

        // 3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do assertion
        response.then().assertThat().statusCode(404). statusLine("HTTP/1.1 404 Not Found");
      // assertTrue(true) ==> Green tick               assertTrue(fakse) ==> Red cross
        assertTrue (response.asString().contains("Not Found"));//If reasponse.asString().contains ("Not Found") reurn true, you will get green tick

        //assertFalse(false) ==> Green tick                 assertFalse(true) ==> Red cross
        assertFalse(response.asString().contains("TechProEd"));//If response.asString().contains("TechProEd") returns false, you will get green tick

        //Expected Data comes from the test case, Actual data comes from the API
        assertEquals("Cowboy", response.getHeader("Server"));



    }

}
