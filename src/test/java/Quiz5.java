import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Quiz5 {
    @Test
    public void getbook() {

        Book Book1 = RestAssured.given().when().get("https://bookstore.toolsqa.com/BookStore/v1/Book?ISBN=9781449325862").as(Book.class);

//        System.out.println(Book1);

        RestAssured.given().queryParam("ISBN",Book1.isbn, ObjectMapperType.JAXB).when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Book").then().log().all();

//        assertEquals(200, Book1.getStatusCode());

        Response rsp = RestAssured.given().when()
                .get("https://demoqa.com/BookStore/v1/Books");
        Assert.assertEquals(rsp.getStatusCode(),200);



        String Publisher = Book1.publisher;
        int Pages = Book1.pages;
        Assert.assertEquals(Publisher,"O'Reilly Media");
        Assert.assertEquals(Pages,234);

    }
}

//Tests passed