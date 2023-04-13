package meabh.Response;

public class Response {
    ResponseCode code;
    String body;
    
    public Response(ResponseCode code, String body) {
        this.code = code;
        this.body = body;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }   
}
