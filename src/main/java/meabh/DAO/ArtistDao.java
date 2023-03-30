package meabh.DAO;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import meabh.DTO.Artist;
import meabh.Exceptions.DaoException;
import meabh.Cache.ArtistCache;

public class ArtistDao extends MySqlDao implements ArtistDaoInterface {
    private ArtistCache cache;

    public ArtistDao() throws DaoException{
        cache = new ArtistCache();
        initialiseCache();
    }

    private void initialiseCache() throws DaoException{
        List<Artist> artists = findAllArtists();
        for (Artist artist : artists) {
            cache.addId(artist.getId());
        }
    }

    private boolean isIdInCache(int id){
        if(cache.isIdInDatabase(id)){
            return true;
        }
        return false;
    }

    @Override
    public List<Artist> findAllArtists() throws DaoException {
        List<Artist> artistList = new ArrayList<>();
        String query = "SELECT * FROM artists";

        //returns empty list if database is empty
        if(cache.isEmpty()){
            return List.of();
        }

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("artist_id");
                String name = resultSet.getString("artist_name");
                LocalDate date = resultSet.getDate("formed_date").toLocalDate();
                String origin = resultSet.getString("origin");
                String memberString = resultSet.getString("members");
                List<String> members = Arrays.asList(memberString.split(","));

                Artist artist = new Artist(id, name, date, origin, members);
                artistList.add(artist);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllArtistSet()" + e.getMessage());
        }

        return artistList;
    }

    public Artist findArtistById(int id) throws DaoException{
        Artist artist = null;
        String query = "SELECT * FROM artists WHERE artist_id = ?";

        if(!isIdInCache(id)){
            throw new DaoException("Id is not is database");
        }

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setInt (1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if(id == resultSet.getInt("artist_id")){
                    String name = resultSet.getString("artist_name");
                    LocalDate date = resultSet.getDate("formed_date").toLocalDate();
                    String origin = resultSet.getString("origin");
                    String memberString = resultSet.getString("members");
                    List<String> members = Arrays.asList(memberString.split(","));

                    artist = new Artist(id, name, date, origin, members);
                }
            }
                
        } catch (SQLException e) {
            throw new DaoException("findArtistByIdSet()" + e.getMessage());
        }

        return artist;
    }

    public void deleteArtistById(int id) throws DaoException{
        String query = "DELETE FROM artists WHERE artist_id = ?";

        if(!isIdInCache(id)){
            throw new DaoException("Id is not is database");
        }

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);){

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deleteArtistByIdSet()" + e.getMessage());
        }
    }

    public void addArtist(Artist artist) throws DaoException{
        String query = "INSERT INTO artists (artist_name, formed_date, origin, members) VALUES (?, ?, ?, ?)";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setDate(2, Date.valueOf(artist.getFormation()));
            preparedStatement.setString(3, artist.getOrigin());
            preparedStatement.setString(4, artist.getMembersAsString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("addArtistSet()" + e.getMessage());
        }
    }
}
