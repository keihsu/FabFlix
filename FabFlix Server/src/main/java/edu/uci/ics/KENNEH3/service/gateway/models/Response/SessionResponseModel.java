package edu.uci.ics.KENNEH3.service.gateway.models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionResponseModel {
    @JsonProperty(value = "resultCode", required = true)
    private int resultCode;

    @JsonProperty(value = "message", required = true)
    private String message;

    @JsonProperty(value = "session_id")
    private String session_id;


    public SessionResponseModel(@JsonProperty(value = "resultCode", required = true) int resultCode,
                                @JsonProperty(value = "message", required = true) String message,
                                @JsonProperty(value = "session_id") String session_id) {
        this.resultCode = resultCode;
        this.message = message;
        this.session_id = session_id;
    }

    @JsonProperty("resultCode")
    public int getResultCode() {
        return resultCode;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("session_id")
    public String getSession_id() {
        return session_id;
    }
}
