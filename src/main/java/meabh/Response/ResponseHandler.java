package meabh.Response;

import com.google.gson.Gson;

public class ResponseHandler {
    private static Gson gson = new Gson();
    
    public static void handleResponse(String responseJson){
        Response response = gson.fromJson(responseJson, Response.class);
        System.out.println(response.getCode());
        System.out.println(response.getBody());
    }
}
