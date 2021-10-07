package edu.uci.ics.KENNEH3.service.gateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uci.ics.KENNEH3.service.gateway.GatewayService;
import edu.uci.ics.KENNEH3.service.gateway.configs.IdmConfigs;
import edu.uci.ics.KENNEH3.service.gateway.logger.ServiceLogger;
import edu.uci.ics.KENNEH3.service.gateway.models.Request.SessionRequestModel;
import edu.uci.ics.KENNEH3.service.gateway.models.Response.SessionResponseModel;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;

public class Validation {
    public static SessionResponseModel checkSession(String email, String session_id){

        IdmConfigs idm = GatewayService.getIdmConfigs();
        ServiceLogger.LOGGER.info("Building Service Path");
        String servicePath = UriBuilder.fromUri(idm.getScheme() + idm.getHostName() + idm.getPath()).port(idm.getPort()).build().toString();
        ServiceLogger.LOGGER.info("Getting Endpoint Path");
        String endpointPath = idm.getSessionPath();
        SessionRequestModel requestModel = new SessionRequestModel(email, session_id);
        SessionResponseModel responseModel = null;

        // Create a new Client
        ServiceLogger.LOGGER.info("Building client...");
        Client client = ClientBuilder.newClient();
        client.register(JacksonFeature.class);

        // Create a WebTarget to send a request at
        ServiceLogger.LOGGER.info("Building WebTarget...");
        WebTarget webTarget = client.target(servicePath).path(endpointPath);

        // Create an InvocationBuilder to create the HTTP request
        ServiceLogger.LOGGER.info("Starting invocation builder...");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        // Send the request and save it to a Response
        ServiceLogger.LOGGER.info("Sending request...");
        Response response = invocationBuilder.post(Entity.entity(requestModel, MediaType.APPLICATION_JSON));
        ServiceLogger.LOGGER.info("Request sent.");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonText = response.readEntity(String.class);
            responseModel = mapper.readValue(jsonText, SessionResponseModel.class);
            ServiceLogger.LOGGER.info("Successfully mapped response to POJO.");
        } catch (IOException e) {
            ServiceLogger.LOGGER.warning("Unable to map response to POJO.");
        }
        return responseModel;
    }
}
