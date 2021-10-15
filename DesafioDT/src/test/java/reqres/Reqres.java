package reqres;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Reqres {

    String uri = "https://reqres.in/api/login";


    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Create - Post
    @Test(priority = 1)
    public void loginSuccessful() throws IOException {
        String jsonBody = lerJson("db/user1.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)

        ;
    }

    @Test(priority = 2)
    public void loginUnSuccessful() throws IOException {
        String jsonBody = lerJson("db/user2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Missing password"))

        ;
    }



}
