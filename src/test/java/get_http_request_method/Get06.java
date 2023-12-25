package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
     /*
       Given
           https://restful-booker.herokuapp.com/booking/5
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;
           { "firstname": "Mark",
              "lastname": " Ericsson",
              "totalprice": 391,
              "depositpaid": true,
              "bookingdates": {"checkin": "2020-11-06",
                                           "checkout": "2021-03-16}
         },
         "additionalneeds": "Breakfast"
      */

    @Test
    public void get06(){
        //1.Step:Set the URL
        spec.pathParams("first","booking", "second", 11);

        //2.Step:Set the expected data

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do assertion
        //1.Way:
    /*    response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Jane"),
                       "lastname", equalTo("Doe"),
                       "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Extra pillows please"));
*/
        //2.Way: Use JasonPath. JsanPath is a class and it has many useful methods to navigate inside the Json Data
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
        //Create jsonPath object from response object
        JsonPath json = response.jsonPath();
//        assertEquals("First name is not matching","Jane", json.getString("firstname") );
//        assertEquals("Last name is not matching","Doe", json.getString("lastname") );
//        assertEquals("Total price is not matching",111, json.getInt("totalprice"));
//        assertEquals("Dposit paid value is not matching",true, json.getBoolean("depositpaid"));
//        assertEquals("Checkin date is not matching","2018-01-01", json.getString("bookingdates.checkin"));
//        assertEquals("Checkout date is not matching","2019-01-01", json.getString("bookingdates.checkout"));

//3.Way:Soft Assertion
        //i)Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();

        //ii)By using softAssert Object do  assertion
        softAssert.assertEquals(json.getString("firstname"), "Jane","First name is not matching");
        softAssert.assertEquals(json.getString("lastname"), "Doe","last name is not matching");
        softAssert.assertEquals(json.getInt("totalprice"), 111,"Total price is not matching");


        //iii)Do not forget to use assertAll(). If you do not use assertAll() you will get green everytime but it is not meaningful
        softAssert.assertAll();


    }
}
