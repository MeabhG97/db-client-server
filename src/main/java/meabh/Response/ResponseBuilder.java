package meabh.Response;

import com.google.gson.Gson;

public class ResponseBuilder {
    private static Gson gson = new Gson();

    public static String response(ResponseCode code, String bodyJson){
        Response response = new Response(code, bodyJson);
        return gson.toJson(response);
    }
}
