package meabh.DAO;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import meabh.GsonTypeAdapter.LocalDateAdapter;

import meabh.DTO.Album;
import meabh.Exceptions.DaoException;

public class AlbumDao extends MySqlDao implements AlbumDaoInterface {
    private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    private List<Album> getArtistAlbums(int artistId) throws DaoException {
        ArrayList<Album> albumList = new ArrayList<>();
        String query = "SECLET * FROM albums WHERE artist_id = ?";

        try(Connection connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setInt (1, artistId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if(artistId == resultSet.getInt("artist_id")){
                    int id = resultSet.getInt("album_id");
                    String name = resultSet.getString("album_name");
                    LocalDate date = resultSet.getDate("release_date").toLocalDate();
                    String producerString = resultSet.getString("producer");
                    List<String> producers = Arrays.asList(producerString.split(","));

                    Album album = new Album(id, name, artistId, date, producers);
                    albumList.add(album);
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return albumList;
    }

    private String albumListToJSON(List<Album> albums){
        return gson.toJson(albums);
    }

    @Override
    public String getArtistAlbumsJson(int artistId) throws DaoException {
        String res = "";

        try{
            List<Album> albums = getArtistAlbums(artistId);
            res = albumListToJSON(albums);
        } catch (DaoException e){
            System.out.println(e.getMessage());
        }

        return res;
    }
}
