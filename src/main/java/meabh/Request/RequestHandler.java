package meabh.Request;

import com.google.gson.Gson;

import meabh.DAO.ArtistDao;
import meabh.DAO.ArtistDaoInterface;
import meabh.DTO.Artist;
import meabh.Exceptions.DaoException;
import meabh.Response.ResponseBuilder;
import meabh.Response.ResponseCode;

public class RequestHandler {
    private static Gson gson = new Gson();

    public static String handleRequest(String requestJson) {
        Request request = gson.fromJson(requestJson, Request.class);
        String response = "";

        switch(request.getType()){
            case GET -> {
                response = handleGet(request.getBody());
            }

            case POST -> {
                response = handlePost(request.getBody());
            }

            case DELETE -> {
                response = handleDelete(request.getBody());
            }

            default -> {
                response = ResponseBuilder.response(ResponseCode.BAD_REQUEST, "Bad Request");
            }
        }

        return response;
    }

    private static String handleGet(String requestBody){
        try{
            ArtistDaoInterface artistDao = new ArtistDao();
            if(requestBody.length() > 0){
                return ResponseBuilder.response(ResponseCode.OK, 
                    artistDao.findArtistByIdJson(gson.fromJson(requestBody, Integer.class)));
            } else {
                return ResponseBuilder.response(ResponseCode.OK, artistDao.findAllArtistsJson());
            }
        } catch(DaoException e){
            return ResponseBuilder.response(ResponseCode.NOT_FOUND, e.getMessage());
        }
    }

    private static String handlePost(String requestBody){
        Artist artist = gson.fromJson(requestBody, Artist.class);
        try{
            ArtistDaoInterface artistDao = new ArtistDao();
            artistDao.addArtist(artist);
            return ResponseBuilder.response(ResponseCode.CREATED, artistDao.findLastAddedArtistJson()); 
        } catch(DaoException e){
            return ResponseBuilder.response(ResponseCode.BAD_REQUEST, e.getMessage());
        }
    }

    private static String handleDelete(String requestBody){
        try{
            ArtistDaoInterface artistDao = new ArtistDao();
            artistDao.deleteArtistById(gson.fromJson(requestBody, Integer.class));
            return ResponseBuilder.response(ResponseCode.OK, "Deleted Artist" + requestBody);
        } catch(DaoException e){
            return ResponseBuilder.response(ResponseCode.BAD_REQUEST, e.getMessage());
        }
    }
}