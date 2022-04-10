package alepekhin.apigateway.common;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    /**
     * Header name for api token
     */
    public static final String X_API_KEY = "x-api-key";

    /**
     * MDC variable name for saving request id
     */
    public static final String MDC_RQID = "mdc.rqid";

    /**
     * Header name for request id sent to backend
     */
    public static final String X_REQUEST_ID = "x-request-id";

}
