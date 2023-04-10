package meabh.Client;

import com.google.gson.Gson;

import meabh.Request.Request;
import meabh.Request.Request.RequestType;
import meabh.DTO.Artist;

public class RequestBuilder {
    private static Gson gson = new Gson();

    public static String findArtist(int id){
        Request request = new Request(RequestType.GET, gson.toJson(id));
        return gson.toJson(request);
    }

    public static String findAllArtists(){
        Request request = new Request(RequestType.GET, "");
        return gson.toJson(request);
    }

    public static String addArtist(Artist artist){
        Request request = new Request(RequestType.POST, gson.toJson(artist));
        return gson.toJson(request);
    }

    public static String deleteArtist(int id){
        Request request = new Request(RequestType.DELETE, gson.toJson(id));
        return gson.toJson(request);
    }
}
