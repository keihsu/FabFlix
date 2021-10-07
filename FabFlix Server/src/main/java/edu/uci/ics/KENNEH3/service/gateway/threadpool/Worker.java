package edu.uci.ics.KENNEH3.service.gateway.threadpool;

import edu.uci.ics.KENNEH3.service.gateway.GatewayService;
import edu.uci.ics.KENNEH3.service.gateway.logger.ServiceLogger;
import edu.uci.ics.KENNEH3.service.gateway.util.Param;
import edu.uci.ics.KENNEH3.service.gateway.util.Util;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Worker extends Thread {
    int id;
    ThreadPool threadPool;

    private Worker(int id, ThreadPool threadPool) {
        this.id = id;
        this.threadPool = threadPool;
    }

    public static Worker CreateWorker(int id, ThreadPool threadPool) {
        return new Worker(id, threadPool);
    }

    public void process() {
        Connection con = null;
        ClientRequest request = this.threadPool.takeRequest();

        ServiceLogger.LOGGER.info("Building client...");
        Client client = ClientBuilder.newClient();
        client.register(JacksonFeature.class);

        // Create a WebTarget to send a request at
        ServiceLogger.LOGGER.info("Building WebTarget...");
        WebTarget webTarget = client.target(request.getURI()).path(request.getEndpoint());
        if(request.getUri_info() != null){
            MultivaluedMap<String, String> queryParams = request.getUri_info().getQueryParameters();
            for(String str: queryParams.keySet()){
                webTarget = webTarget.queryParam(str, queryParams.getFirst(str));
            }
            ServiceLogger.LOGGER.info("WebTarget: " + webTarget.toString());
        }

        // Create an InvocationBuilder to create the HTTP request
        ServiceLogger.LOGGER.info("Starting invocation builder...");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = null;
        if(request.getMethod() == HTTPMethod.GET){
            ServiceLogger.LOGGER.info("Sending request...");
            response = invocationBuilder.get();
            ServiceLogger.LOGGER.info("Request sent.");
        }
        else if(request.getMethod() == HTTPMethod.POST){
            ServiceLogger.LOGGER.info("Sending request...");
            response = invocationBuilder.post(Entity.entity(request.getRequestBytes(), MediaType.APPLICATION_JSON));
            ServiceLogger.LOGGER.info("Request sent.");
        }
        if(response != null){
            try {
                ServiceLogger.LOGGER.info("Inserting into database.");
                con = GatewayService.getConnectionPoolManager().requestCon();
                String query = "INSERT INTO responses\n" +
                        "VALUES(?, ?, ?, ?, ?);";
                Param [] params = new Param[5];
                params[0] = Param.create(Types.VARCHAR, request.getTransaction_id());
                params[1] = Param.create(Types.VARCHAR, request.getEmail());
                params[2] = Param.create(Types.VARCHAR, request.getSession_id());
                params[3] = Param.create(Types.LONGVARCHAR, response.readEntity(String.class));
                params[4] = Param.create(Types.INTEGER, response.getStatus());
                int rows = Util.prepareStatement(query, params, con).executeUpdate();
                ServiceLogger.LOGGER.info(rows + " rows inserted.");
            } catch (SQLException e){
                Util.serverError("SQLException was raised: " + e.getMessage());
            } finally {
                if(con != null)
                    GatewayService.getConnectionPoolManager().releaseCon(con);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            this.process();
        }
    }
}
