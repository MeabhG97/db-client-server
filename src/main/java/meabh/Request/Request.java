package meabh.Request;

public class Request {
    public enum RequestType {
        GET, POST, PUT, DELETE;
    }
    
    private RequestType type;
    private String body;

    public Request(RequestType type, String body){
        this.type = type;
        this.body = body;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
