
/**
 * File: src/main/java/com/cms/utils/ApiUtils.java
 * Description: Utility for API interactions
 */
package com.cms.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ApiUtils {
    private static final String BASE_URL = ConfigManager.getInstance().getApiBaseUrl();

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response get(String endpoint) {
        return get(endpoint, null);
    }

    public static Response get(String endpoint, Map<String, String> queryParams) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();

        if (queryParams != null) {
            request.queryParams(queryParams);
        }

        Response response = request.get(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response post(String endpoint, Object requestBody) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all();

        Response response = request.post(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response put(String endpoint, Object requestBody) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all();

        Response response = request.put(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response delete(String endpoint) {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();

        Response response = request.delete(endpoint);
        response.then().log().all();
        return response;
    }
}
