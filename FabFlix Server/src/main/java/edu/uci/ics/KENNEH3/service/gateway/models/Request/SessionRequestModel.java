package edu.uci.ics.KENNEH3.service.gateway.models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uci.ics.KENNEH3.service.gateway.base.RequestModel;

public class SessionRequestModel extends RequestModel {
    @JsonProperty(value = "email", required = true)
    private String email;
    @JsonProperty(value = "session_id", required = true)
    private String session_id;

    public SessionRequestModel(@JsonProperty(value = "email", required = true) String email,
                               @JsonProperty(value = "session_id", required = true) String session_id) {
        this.email = email;
        this.session_id = session_id;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("session_id")
    public String getSession_id() {
        return session_id;
    }
}
