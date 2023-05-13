package server;

public class Response {
    private String response;
    private Object value;
    private String reason;

    public Response setResponse(String response) {
        this.response = response;
        return this;
    }

    public Response setValue(Object value) {
        this.value = value;
        return this;
    }

    public Response setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
