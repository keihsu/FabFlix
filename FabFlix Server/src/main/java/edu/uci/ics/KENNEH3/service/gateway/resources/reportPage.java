package edu.uci.ics.KENNEH3.service.gateway.resources;

import edu.uci.ics.KENNEH3.service.gateway.GatewayService;
import edu.uci.ics.KENNEH3.service.gateway.base.Result;
import edu.uci.ics.KENNEH3.service.gateway.logger.ServiceLogger;
import edu.uci.ics.KENNEH3.service.gateway.models.Response.SessionResponseModel;
import edu.uci.ics.KENNEH3.service.gateway.util.Param;
import edu.uci.ics.KENNEH3.service.gateway.util.Util;
import edu.uci.ics.KENNEH3.service.gateway.util.Validation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Path("report")
public class reportPage {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response report(@Context HttpHeaders headers){
        // Get header strings
        // If there is no header with given key, it will be null
        ServiceLogger.LOGGER.info("Getting Headers");
        String email = headers.getHeaderString("email");
        String session_id = headers.getHeaderString("session_id");
        String transaction_id = headers.getHeaderString("transaction_id");
        Response.ResponseBuilder builder = null;

        Connection con = null;
        try {
            con = GatewayService.getConnectionPoolManager().requestCon();
            String query = "SELECT *\n" +
                    "FROM responses\n" +
                    "WHERE transaction_id = ?;";
            Param[] params = new Param[1];
            params[0] = Param.create(Types.VARCHAR, transaction_id);
            ResultSet rs = Util.prepareStatement(query, params, con).executeQuery();
            if(rs.next()){
                builder = Response.status(rs.getInt("http_status")).entity(rs.getString("response"));
                query = "DELETE FROM responses\n" +
                        "WHERE transaction_id = ?;";
                int rows = Util.prepareStatement(query, params, con).executeUpdate();
            }
            else {
                builder = Response.status(Response.Status.NO_CONTENT);
            }
            // Pass along headers
            builder.header("email", email);
            builder.header("session_id", session_id);
            builder.header("transaction_id", transaction_id);
            builder.header("request_delay", GatewayService.getThreadConfigs().getRequestDelay());

            // Return the response
            return builder.build();
        } catch (SQLException e){
        } finally {
            if(con != null)
                GatewayService.getConnectionPoolManager().releaseCon(con);
        }
        return builder.build();
    }
}
