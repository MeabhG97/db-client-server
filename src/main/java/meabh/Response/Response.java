package meabh.Response;

public class Response {
    public enum ResponseCode{
        OK, CREATED, BAD_REQUEST, NOT_FOUND;
    }

    ResponseCode code;
    Response body;
    
    public Response(ResponseCode code, Response body) {
        this.code = code;
        this.body = body;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public Response getBody() {
        return body;
    }

    public void setBody(Response body) {
        this.body = body;
    }
    
}
