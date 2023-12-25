package base_urls;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.*;
import org.junit.Before;

public class HerOkuAppBaseUrl {
    //Create object in RequestSpecification data type
    protected RequestSpecification spec;

    //If you use @Before annotation at the top of a method , it means the method will  be execoted before every test method
    @Before
  public void setUp(){
    spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
}
}
