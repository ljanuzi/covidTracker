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


    /* NOTE:
     * ----
     * This code is "buggy" in the sense that if one calls testDelete() to delete, say, the product with ID 3,
     * and then performs a testGet() with the same product ID, an exception will be raised and execution will stop
     */
    public static void main(String[] args) {
        testList();
        testGet();
        testAdd();
        testUpdate();
        testDelete();
        testList();
    }

    //DELETE request
    /* NOTE:
     * ----
     * Line 45 issues request and gets back server response.
     * The Response.class creates an object that carries a representation of the current instantiation of the Reponse class.
     * As explained in the comments in the (server-side) ProductResource class, the Response class is not instantiated
     * directly and hence there are no named instances of it to which we can refer. Instead, instantiation is performed
     * through builders (the application of the build() method - see ProductResource class). If we want to refer to the
     * current instantiation and get a representation of it, the Response.class object must be used.
     *
     * Same note applies to all request-constructing methods below.
     */
    private static void testDelete() {
        WebTarget target = getWebTarget();
        String timestamp = "2020-12-12";
        Response response = target.path(timestamp).request()
                .delete(Response.class);
        System.out.println(response);
    }

    //UPDATE request
    private static void testUpdate() {
//        WebTarget target = getWebTarget();
//        Product product = new Product("ZenFoneX", 100f);
//        String productId = "4";
//        Response response = target.path(productId).request()
//                .put(Entity.entity(product, MediaType.APPLICATION_JSON), Response.class);
//        System.out.println(response);
    }

    //creates client object with any filters/interceptors registered with the config object (here null)
    static WebTarget getWebTarget() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        //returns baseURI (i.e. servlet URI) as target URI
        return client.target(baseURI);
    }

    //GET all request
    static void testList() {
        WebTarget target = getWebTarget();

        String response = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);

        System.out.println(response);
    }

    //GET ID request
    static void testGet() {
//        WebTarget target = getWebTarget();
//        String productId = "2";
//        Product product = target.path(productId)
//                .request().accept(MediaType.APPLICATION_JSON)
//                .get(Product.class);
//
//        System.out.println(product);
    }

    //ADD request
    static void testAdd() {
//        WebTarget target = getWebTarget();
//        Product product = new Product("ZenFoneX", 888.88f);
//        Response response = target.request()
//                .post(Entity.entity(product, MediaType.APPLICATION_JSON), Response.class);
//
//        System.out.println(response.getLocation().toString());
    }
}
