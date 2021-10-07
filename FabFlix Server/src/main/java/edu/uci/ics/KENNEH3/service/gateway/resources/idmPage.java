package edu.uci.ics.KENNEH3.service.gateway.resources;

import edu.uci.ics.KENNEH3.service.gateway.GatewayService;
import edu.uci.ics.KENNEH3.service.gateway.configs.IdmConfigs;
import edu.uci.ics.KENNEH3.service.gateway.threadpool.ClientRequest;
import edu.uci.ics.KENNEH3.service.gateway.threadpool.HTTPMethod;
import edu.uci.ics.KENNEH3.service.gateway.transaction.TransactionGenerator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;

@Path("idm")
public class idmPage {
    @Path("register")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@Context HttpHeaders headers, byte[] jsonBytes) {
        IdmConfigs idm = GatewayService.getIdmConfigs();
        String uri = UriBuilder.fromUri(idm.getScheme() + idm.getHostName() + idm.getPath()).port(idm.getPort()).build().toString();
        String endpoint = idm.getRegisterPath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        Response.ResponseBuilder builder;
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("login")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpHeaders headers, byte[] jsonBytes){
        IdmConfigs idm = GatewayService.getIdmConfigs();
        String uri = UriBuilder.fromUri(idm.getScheme() + idm.getHostName() + idm.getPath()).port(idm.getPort()).build().toString();
        String endpoint = idm.getLoginPath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);
        GatewayService.getThreadPool().putRequest(cr);
        Response.ResponseBuilder builder;
        builder = Response.status(Response.Status.NO_CONTENT);

        // Pass along headers
        builder.header("transaction_id", transaction_id);
        builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

        // Return the response
        return builder.build();
    }

    @Path("session")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response session(@Context HttpHeaders headers, byte[] jsonBytes) {
        IdmConfigs idm = GatewayService.getIdmConfigs();
        String uri = UriBuilder.fromUri(idm.getScheme() + idm.getHostName() + idm.getPath()).port(idm.getPort()).build().toString();
        String endpoint = idm.getSessionPath();
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        transaction_id = TransactionGenerator.generate();
        ClientRequest cr = new ClientRequest(email, session_id, transaction_id, uri, endpoint, HTTPMethod.POST, null, jsonBytes);

        GatewayService.getThreadPool().putRequest(cr);
        Response.ResponseBuilder builder;
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
