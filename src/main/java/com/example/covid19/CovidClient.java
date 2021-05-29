package com.example.covid19;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class CovidClient {
    private static String baseURI = "http://localhost:8080/api";

    public static void main(String[] args) {
        testgetAllData();
        testGetData();
        testGetAllMeans();
        testGetMean();
        testGetAllMedian();
        testGetMedian();
        testAdd();
        testUpdate();
        testDelete();
    }


    //creates client object with any filters/interceptors registered with the config object (here null)
    static WebTarget getWebTarget(String parameter) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        //returns baseURI (i.e. servlet URI) as target URI
        return client.target(baseURI + parameter);
    }

    //GET all request
    static void testgetAllData() {
        WebTarget target = getWebTarget("/getAllData");

        Response response = target.request().accept(MediaType.APPLICATION_JSON).get(Response.class);

        System.out.println(response);
    }


    static void testGetData() {
        WebTarget target = getWebTarget("/getData");
        String startDate = "2020-12-12";
        String endDate = "2020-12-13";

        Response response = target.queryParam("startDate",startDate).queryParam("endDate", endDate)
                .request().accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println(response);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////
    //MEANS
    static void testGetAllMeans() {
        WebTarget target = getWebTarget("/getAllMeans");

        Response response = target.request().accept(MediaType.APPLICATION_JSON).get(Response.class);

        System.out.println(response);
    }


    static void testGetMean() {
        WebTarget target = getWebTarget("/getMean");
        String startDate = "2020-12-12";
        String endDate = "2020-12-13";

        Response covidData = target.queryParam("startDate",startDate).queryParam("endDate", endDate)
                .request().accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println(covidData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //MEDIAN
    static void testGetAllMedian() {
        WebTarget target = getWebTarget("/getAllMedian");

        Response response = target.request().accept(MediaType.APPLICATION_JSON).get(Response.class);

        System.out.println(response);
    }


    static void testGetMedian() {
        WebTarget target = getWebTarget("/getMedian");
        String startDate = "2020-12-12";
        String endDate = "2020-12-13";

        Response covidData = target.queryParam("startDate",startDate).queryParam("endDate", endDate)
                .request().accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println(covidData);
    }

    //ADD request
    static void testAdd() {
        WebTarget target = getWebTarget("/insertData");
        CovidData covidData = new CovidData("02-16-2020", 41, 42,43);
        Response response = target.request()
                .post(Entity.entity(covidData, MediaType.APPLICATION_JSON), Response.class);

        System.out.println(response.toString());
    }

    //UPDATE request
    private static void testUpdate() {
        WebTarget target = getWebTarget("/alterData/");
        CovidData covidData = new CovidData(41, 42,43);
        String timestamp = "12-12-2020";
        Response response = target.path(timestamp).request()
                .put(Entity.entity(covidData, MediaType.APPLICATION_JSON), Response.class);
        System.out.println(response);
    }

    //DELETE request

    private static void testDelete() {
        WebTarget target = getWebTarget("");
        String timestamp = "05-05-2021";
        Response response = target.path(timestamp).request()
                .delete(Response.class);
        System.out.println(response);
    }
}
