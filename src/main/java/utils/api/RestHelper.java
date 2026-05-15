package utils.api;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestHelper {
    // *************************************************** Request Builder *************************************************** //
    private static RequestSpecification request(String baseUrl) {
        return given()
                .log().all()
                .baseUri(baseUrl)
                .accept(ContentType.JSON);
    }

    private static RequestSpecification request(String baseUrl,
                                                Map<String, Object> queryParams,
                                                Map<String, Object> pathParams,
                                                Map<String, String> headers) {
        RequestSpecification request = request(baseUrl);

        if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }

        if (pathParams != null && !pathParams.isEmpty()) {
            request.pathParams(pathParams);
        }

        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        return request;
    }

    // *************************************************** GET Single Object *************************************************** //

    public static <T> T sendGetRequest(String baseUrl,
                                       String endpoint,
                                       int expectedStatusCode,
                                       Class<T> responseClass) {
        return sendGetRequest(baseUrl, endpoint, null, null, null, expectedStatusCode, responseClass);
    }

    public static <T> T sendGetRequestWithPathParams(String baseUrl,
                                                     String endpoint,
                                                     Map<String, Object> pathParams,
                                                     int expectedStatusCode,
                                                     Class<T> responseClass) {
        return sendGetRequest(baseUrl, endpoint, null, pathParams, null, expectedStatusCode, responseClass);
    }

    public static <T> T sendGetRequestWithQueryParams(String baseUrl,
                                                      String endpoint,
                                                      Map<String, Object> queryParams,
                                                      int expectedStatusCode,
                                                      Class<T> responseClass) {
        return sendGetRequest(baseUrl, endpoint, queryParams, null, null, expectedStatusCode, responseClass);
    }

    public static <T> T sendGetRequestWithHeaders(String baseUrl,
                                                  String endpoint,
                                                  Map<String, String> headers,
                                                  int expectedStatusCode,
                                                  Class<T> responseClass) {
        return sendGetRequest(baseUrl, endpoint, null, null, headers, expectedStatusCode, responseClass);
    }

    public static <T> T sendGetRequest(String baseUrl,
                                       String endpoint,
                                       Map<String, Object> queryParams,
                                       Map<String, Object> pathParams,
                                       Map<String, String> headers,
                                       int expectedStatusCode,
                                       Class<T> responseClass) {
        return request(baseUrl, queryParams, pathParams, headers)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

    // *************************************************** GET List ************************************************************** //

    public static <T> List<T> sendGetListRequest(String baseUrl,
                                                 String endpoint,
                                                 int expectedStatusCode,
                                                 Class<T> responseClass) {
        return sendGetListRequest(baseUrl, endpoint, null, null, null, expectedStatusCode, responseClass);
    }

    public static <T> List<T> sendGetListRequestWithQueryParams(String baseUrl,
                                                                String endpoint,
                                                                Map<String, Object> queryParams,
                                                                int expectedStatusCode,
                                                                Class<T> responseClass) {
        return sendGetListRequest(baseUrl, endpoint, queryParams, null, null, expectedStatusCode, responseClass);
    }

    public static <T> List<T> sendGetListRequestWithHeaders(String baseUrl,
                                                            String endpoint,
                                                            Map<String, String> headers,
                                                            int expectedStatusCode,
                                                            Class<T> responseClass) {
        return sendGetListRequest(baseUrl, endpoint, null, null, headers, expectedStatusCode, responseClass);
    }

    public static <T> List<T> sendGetListRequest(String baseUrl,
                                                 String endpoint,
                                                 Map<String, Object> queryParams,
                                                 Map<String, Object> pathParams,
                                                 Map<String, String> headers,
                                                 int expectedStatusCode,
                                                 Class<T> responseClass) {
        return request(baseUrl, queryParams, pathParams, headers)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .jsonPath()
                .getList("", responseClass);
    }

    // *************************************************** POST Single Object *************************************************** //
    public static <T> T sendPostRequest(String baseUrl,
                                        String endpoint,
                                        Object body,
                                        int expectedStatusCode,
                                        Class<T> responseClass) {
        return sendPostRequest(baseUrl, endpoint, body, null, null, expectedStatusCode, responseClass);
    }

    public static <T> T sendPostRequestWithQueryParams(String baseUrl,
                                                       String endpoint,
                                                       Object body,
                                                       Map<String, Object> queryParams,
                                                       int expectedStatusCode,
                                                       Class<T> responseClass) {
        return sendPostRequest(baseUrl, endpoint, body, queryParams, null, expectedStatusCode, responseClass);
    }

    public static <T> T sendPostRequestWithHeaders(String baseUrl,
                                                   String endpoint,
                                                   Object body,
                                                   Map<String, String> headers,
                                                   int expectedStatusCode,
                                                   Class<T> responseClass) {
        return sendPostRequest(baseUrl, endpoint, body, null, headers, expectedStatusCode, responseClass);
    }
    public static <T> T sendPostRequest(String baseUrl,
                                        String endpoint,
                                        Object body,
                                        Map<String, Object> queryParams,
                                        Map<String, String> headers,
                                        int expectedStatusCode,
                                        Class<T> responseClass) {
        return request(baseUrl, queryParams, null, headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

}