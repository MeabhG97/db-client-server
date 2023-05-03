package meabh.DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.google.gson.Gson;

import meabh.DTO.Song;
import meabh.Exceptions.DaoException;

public class SongDao extends MySqlDao implements SongDaoInterface{
    private static Gson gson = new Gson();

    private List<Song> getAllSongs(int albumId) throws DaoException{
        ArrayList<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM songs WHERE album_id = ?";

        try(Connection connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);){

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                if(albumId == resultSet.getInt("album_id")){
                    int id = resultSet.getInt("song_id");
                    String name = resultSet.getString("song_name");
                    int length = resultSet.getInt("length");

                    Song song = new Song(id, name, albumId, length);
                    songs.add(song);
                }
            }

        } catch(SQLException e){
            throw new DaoException(e.getMessage());
        }

        return songs;
    }

    private String songListToJSON(List<Song> songs){
        return gson.toJson(songs);
    }

    @Override
    public String getAlbumSongsJSON(int albumId) throws DaoException {
        String res = "";

        try{
            List<Song> songs = getAllSongs(albumId);
            res = songListToJSON(songs);
        } catch(DaoException e){
            System.out.println(e.getMessage());
        }

        return res;
    }
    
}
