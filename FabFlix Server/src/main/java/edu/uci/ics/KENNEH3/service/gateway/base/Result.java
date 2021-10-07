package edu.uci.ics.KENNEH3.service.gateway.base;

import javax.ws.rs.core.Response;

public enum Result {
    INVALID_PRIV_RANGE(-14, "Privilege level out of valid range.", Response.Status.BAD_REQUEST),
    INVALID_TOKEN_LENGTH(-13, "Token has invalid length.", Response.Status.BAD_REQUEST),
    INVALID_EMAIL_FORMAT(-11, "Email address has invalid format.", Response.Status.BAD_REQUEST),
    INVALID_EMAIL_LENGTH(-10, "Email address has invalid length.", Response.Status.BAD_REQUEST),
    JSON_PARSE_EXCEPTION(-3, "JSON Parse Exception.", Response.Status.BAD_REQUEST),
    JSON_MAPPING_EXCEPTION(-2, "JSON Mapping Exception.", Response.Status.BAD_REQUEST),

    INTERNAL_SERVER_ERROR(-1, "Internal Server Error.", Response.Status.INTERNAL_SERVER_ERROR),

    //FOUND_USER              (10, "User found.",                             Response.Status.OK),
    //NO_USER_FOUND           (11, "Could not find user.",                    Response.Status.OK),
    USER_NOT_FOUND(14, "User not found.", Response.Status.OK),
    INVALID_QTY(33, "Quantity has invalid value.", Response.Status.OK),
    SESSION_ACTIVE(130, "Session is active.", Response.Status.OK),
    SESSION_EXPIRED(131, "Session is expired.", Response.Status.OK),
    SESSION_CLOSED(132, "Session is closed.", Response.Status.OK),
    SESSION_REVOKED(133, "Session is revoked.", Response.Status.OK),
    SESSION_NOT_FOUND(134, "Session not found.", Response.Status.OK),
    SUFFICIENT_PRIV(140, "User has sufficient privilege level.", Response.Status.OK),
    INSUFFICIENT_PRIV(141, "User has insufficient privilege level.", Response.Status.OK),
    FOUND_MOVIE(210, "Found movie(s) with search parameters.", Response.Status.OK),
    NO_MOVIES_FOUND(211, "No movies found with search parameters.", Response.Status.OK),
    FOUND_PERSON(212, "Found people with search parameters.", Response.Status.OK),
    NO_PEOPLE_FOUND(213, "No people found with search parameters.", Response.Status.OK),
    CART_DUPLICATE_INSERT(311, "Duplicate insertion.", Response.Status.OK),
    CART_ITEM_NOT_EXIST(312, "Shopping cart item does not exist.", Response.Status.OK),
    ORDER_HIST_NOT_EXIST(313, "Order history does not exist.", Response.Status.OK),
    ORDER_CREATE_FAIL(342, "Order creation failed.", Response.Status.OK),
    CART_INSERT_SUCCESS(3100, "Shopping cart item inserted successfully.", Response.Status.OK),
    CART_UPDATE_SUCCESS(3110, "Shopping cart item updated successfully.", Response.Status.OK),
    CART_DELETE_SUCCESS(3120, "Shopping cart item deleted successfully.", Response.Status.OK),
    CART_RETRIEVE_SUCCESS(3130, "Shopping cart retrieved successfully.", Response.Status.OK),
    CART_CLEAR_SUCCESS(3140, "140: Shopping cart cleared successfully.", Response.Status.OK),
    CART_FAIL(3150, "Shopping cart operation failed.", Response.Status.OK),
    ORDER_PLACE_SUCCESS(3400, "Order placed successfully.", Response.Status.OK),
    ORDER_RETRIEVE_SUCCESS(3410, "Orders retrieved successfully.", Response.Status.OK),
    ORDER_COMPLETE(3410, "Order is completed successfully.", Response.Status.OK),
    TOKEN_NOT_FOUND(3421, "Token not found.", Response.Status.OK),
    ORDER_NOT_COMPLETE(3422, "Order can not be completed.", Response.Status.OK);

    private final int resultCode;
    private final String message;
    private final Response.Status status;

    Result(int resultCode, String message, Response.Status status) {
        this.resultCode = resultCode;
        this.message = message;
        this.status = status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }

    public Response.Status getStatus() {
        return status;
    }
}
