package edu.uci.ics.KENNEH3.service.gateway.threadpool;

import javax.ws.rs.core.UriInfo;

public class ClientRequest
{
    /* User Information */
    private String email;
    private String session_id;
    private String transaction_id;

    /* Target Service and Endpoint */
    private String URI;
    private String endpoint;
    private HTTPMethod method;
    private UriInfo uri_info;

    /*
     * So before when we wanted to get the request body
     * we would grab it as a String (String jsonText).
     *
     * The Gateway however does not need to see the body
     * but simply needs to pass it. So we save ourselves some
     * time and overhead by grabbing the request as a byte array
     * (byte[] jsonBytes).
     *
     * This way we can just act as a
     * messenger and just pass along the bytes to the target
     * service and it will do the rest.
     *
     * for example:
     *
     * where we used to do this:
     *
     *     @Path("hello")
     *     ...ect
     *     public Response hello(String jsonString) {
     *         ...ect
     *     }
     *
     * do:
     *
     *     @Path("hello")
     *     ...ect
     *     public Response hello(byte[] jsonBytes) {
     *         ...ect
     *     }
     *
     */
    private byte[] requestBytes;

    public ClientRequest(String email, String session_id, String transaction_id, String URI, String endpoint, HTTPMethod method, UriInfo uri_info, byte[] requestBytes) {
        this.email = email;
        this.session_id = session_id;
        this.transaction_id = transaction_id;
        this.URI = URI;
        this.endpoint = endpoint;
        this.method = method;
        this.requestBytes = requestBytes;
        this.uri_info = uri_info;
    }

    public String getEmail() {
        return email;
    }

    public String getSession_id() {
        return session_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getURI() {
        return URI;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public HTTPMethod getMethod() {
        return method;
    }

    public UriInfo getUri_info() {
        return uri_info;
    }

    public byte[] getRequestBytes() {
        return requestBytes;
    }
}
