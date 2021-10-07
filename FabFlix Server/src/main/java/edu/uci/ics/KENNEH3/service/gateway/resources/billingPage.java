package edu.uci.ics.KENNEH3.service.gateway.resources;

import edu.uci.ics.KENNEH3.service.gateway.GatewayService;
import edu.uci.ics.KENNEH3.service.gateway.base.Result;
import edu.uci.ics.KENNEH3.service.gateway.configs.BillingConfigs;
import edu.uci.ics.KENNEH3.service.gateway.models.Response.SessionResponseModel;
import edu.uci.ics.KENNEH3.service.gateway.threadpool.ClientRequest;
import edu.uci.ics.KENNEH3.service.gateway.threadpool.HTTPMethod;
import edu.uci.ics.KENNEH3.service.gateway.transaction.TransactionGenerator;
import edu.uci.ics.KENNEH3.service.gateway.util.Validation;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("billing")
public class billingPage {
    @Path("cart/insert")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response cartInsert (@Context HttpHeaders headers, byte[] jsonBytes){
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getCartInsertPath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);

        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("cart/update")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response cartUpdate (@Context HttpHeaders headers, byte[] jsonBytes){
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getCartUpdatePath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("cart/delete")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response cartDelete (@Context HttpHeaders headers, byte[] jsonBytes){
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getCartDeletePath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("cart/retrieve")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response cartRetrieve (@Context HttpHeaders headers, byte[] jsonBytes) {
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getCartRetrievePath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("cart/clear")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response cartClear (@Context HttpHeaders headers, byte[] jsonBytes){
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getCartClearPath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("order/place")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response orderPlace (@Context HttpHeaders headers, byte[] jsonBytes) {
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getOrderPlacePath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("order/retrieve")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response orderRetrieve (@Context HttpHeaders headers, byte[] jsonBytes) {
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getOrderRetrievePath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("order/complete")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response orderComplete(@Context HttpHeaders headers,
                                  @Context UriInfo uri_info,
                                  byte[] jsonBytes){
        BillingConfigs billing = GatewayService.getBillingConfigs();
        String uri = UriBuilder.fromUri(billing.getScheme() + billing.getHostName() + billing.getPath()).port(billing.getPort()).build().toString();
        String endpoint = billing.getOrderCompletePath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder;

        SessionResponseModel session = Validation.checkSession(email, session_id);
        if(session == null || session.getResultCode() != Result.SESSION_ACTIVE.getResultCode()) {
            builder = Response.status(Response.Status.OK).entity(session);

            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);

            // Return the response
            return builder.build();
        }
        session_id = session.getSession_id();
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.GET, uri_info, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("email", email);
        builder.header("session_id", session_id);
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }
}