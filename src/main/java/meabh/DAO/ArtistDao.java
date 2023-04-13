package meabh.DAO;

import java.util.List;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import meabh.Comparators.ArtistSortByFormedDate;
import meabh.GsonTypeAdapter.LocalDateAdapter;

public class ArtistDao extends MySqlDao implements ArtistDaoInterface {
    private ArtistCache cache;
    private boolean cacheInitialised = false;
    private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    public ArtistDao() throws DaoException{
        cache = new ArtistCache();
        initialiseCache();
    }

    private void initialiseCache() throws DaoException{
        List<Artist> artists = findAllArtists();
        for (Artist artist : artists) {
            cache.addId(artist.getId());
        }
        cacheInitialised = true;
        System.out.println(cache.toString());
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
        if(cache.isEmpty() && cacheInitialised){
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

    public List<Artist> findArtistsFormedDate(LocalDate date) throws DaoException{
        List<Artist> artists = new ArrayList<>();

        if(cache.isEmpty()){
            return List.of();
        }

        try{
            artists = findAllArtists();
            ArtistSortByFormedDate comparator = new ArtistSortByFormedDate();
            Predicate<Artist> condition = artist -> (comparator.compareDate(artist, date) < 0);
            artists.removeIf(condition);
        }
        catch(DaoException e){
            System.out.println("DAO Exception filter " + e.getMessage());
        }

        return artists;
    }

    private String artistListToJSON(List<Artist> artists){
        return gson.toJson(artists);
    }

    private String artistToJSON(Artist artist){
        return gson.toJson(artist);
    }

    public String findAllArtistsJson() throws DaoException {
        if(cache.isEmpty()){
            throw new DaoException("Database Empty");
        }

        String res = "";
        try{
            List<Artist> artists = findAllArtists();
            res = artistListToJSON(artists);
        } catch (DaoException e){
            System.out.println(e.getMessage());
        }

        return res;
    }

    public String findArtistByIdJson(int id) throws DaoException{
        if(!isIdInCache(id)){
            throw new DaoException("Id is not is database");
        }

        String res = "";
        try{
            Artist artist = findArtistById(id);
            res = artistToJSON(artist);
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public String findLastAddedArtistJson() throws DaoException {
        if(cache.isEmpty()){
            throw new DaoException("Database Empty");
        }

        Artist artist = null;
        String query = "SELECT * FROM artists ORDER BY artist_id DESC LIMIT 1";

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

                artist = new Artist(id, name, date, origin, members);
            }

            return gson.toJson(artist);
        } catch(SQLException e){
            throw new DaoException(e.getMessage());
        }
    }
}
