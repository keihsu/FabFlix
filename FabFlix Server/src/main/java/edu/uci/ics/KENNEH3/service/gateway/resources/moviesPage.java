package edu.uci.ics.KENNEH3.service.gateway.resources;

import edu.uci.ics.KENNEH3.service.gateway.GatewayService;
import edu.uci.ics.KENNEH3.service.gateway.base.Result;
import edu.uci.ics.KENNEH3.service.gateway.configs.MoviesConfigs;
import edu.uci.ics.KENNEH3.service.gateway.models.Response.SessionResponseModel;
import edu.uci.ics.KENNEH3.service.gateway.threadpool.ClientRequest;
import edu.uci.ics.KENNEH3.service.gateway.threadpool.HTTPMethod;
import edu.uci.ics.KENNEH3.service.gateway.transaction.TransactionGenerator;
import edu.uci.ics.KENNEH3.service.gateway.util.Validation;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("movies")
public class moviesPage {
    @Path("search")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@Context HttpHeaders headers, @Context UriInfo uri_info, byte[] jsonBytes){
        MoviesConfigs movies = GatewayService.getMoviesConfigs();
        String uri = UriBuilder.fromUri(movies.getScheme() + movies.getHostName() + movies.getPath()).port(movies.getPort()).build().toString();
        String endpoint = movies.getSearchPath();
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

    @Path("browse/{phrase:.*}")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response browse(@Context HttpHeaders headers,
                           @PathParam("phrase") String pathString,
                           @Context UriInfo uri_info,
                           byte[] jsonBytes){
        MoviesConfigs movies = GatewayService.getMoviesConfigs();
        String uri = UriBuilder.fromUri(movies.getScheme() + movies.getHostName() + movies.getPath()).port(movies.getPort()).build().toString();
        String endpoint = movies.getBrowsePath() + pathString;
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

    @Path("get/{phrase:.*}")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response movieDetail(@Context HttpHeaders headers,
                                @PathParam("phrase") String pathString,
                                @Context UriInfo uri_info,
                                byte[] jsonBytes){
        MoviesConfigs movies = GatewayService.getMoviesConfigs();
        String uri = UriBuilder.fromUri(movies.getScheme() + movies.getHostName() + movies.getPath()).port(movies.getPort()).build().toString();
        String endpoint = movies.getGetPath() + pathString;
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

    @Path("thumbnail")
    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response thumbnail (@Context HttpHeaders headers, byte[] jsonBytes){
        MoviesConfigs movies = GatewayService.getMoviesConfigs();
        String uri = UriBuilder.fromUri(movies.getScheme() + movies.getHostName() + movies.getPath()).port(movies.getPort()).build().toString();
        String endpoint = movies.getThumbnailPath();
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

    @Path("people")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response people(@Context HttpHeaders headers,
                           @Context UriInfo uri_info,
                           byte[] jsonBytes){
        MoviesConfigs movies = GatewayService.getMoviesConfigs();
        String uri = UriBuilder.fromUri(movies.getScheme() + movies.getHostName() + movies.getPath()).port(movies.getPort()).build().toString();
        String endpoint = movies.getPeoplePath();
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

    @Path("people/search")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response peopleSearch(@Context HttpHeaders headers,
                                 @Context UriInfo uri_info,
                                 byte[] jsonBytes){
        MoviesConfigs movies = GatewayService.getMoviesConfigs();
        String uri = UriBuilder.fromUri(movies.getScheme() + movies.getHostName() + movies.getPath()).port(movies.getPort()).build().toString();
        String endpoint = movies.getPeopleSearchPath();
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

    @Path("people/get/{phrase:.*}")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response personDetail(@Context HttpHeaders headers,
                                 @PathParam("phrase") String pathString,
                                 @Context UriInfo uri_info,
                                 byte[] jsonBytes){
        MoviesConfigs movies = GatewayService.getMoviesConfigs();
        String uri = UriBuilder.fromUri(movies.getScheme() + movies.getHostName() + movies.getPath()).port(movies.getPort()).build().toString();
        String endpoint = movies.getPeopleGetPath() + pathString;
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