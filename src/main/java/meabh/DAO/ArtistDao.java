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

public class ArtistDao extends MySqlDao implements ArtistDaoInterface {
    @Override
    public List<Artist> findAllArtists() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Artist> artistList = new ArrayList<>();

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM artists";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("artist_id");
                String name = resultSet.getString("artist_name");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String origin = resultSet.getString("origin");
                String memberString = resultSet.getString("members");
                List<String> members = Arrays.asList(memberString.split(","));

                Artist artist = new Artist(id, name, date, origin, members);
                artistList.add(artist);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllArtistSet()" + e.getMessage());
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    freeConnection(connection);
                }
            }catch(SQLException e){
                throw new DaoException("findAllArtists() " + e.getMessage());
            }
        }

        return artistList;
    }

    public Artist findArtistById(int id) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Artist artist = null;

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM artists WHERE artist_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, id);

            resultSet = preparedStatement.executeQuery();

            if(id == resultSet.getInt("artist_id")){
                String name = resultSet.getString("artist_name");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String origin = resultSet.getString("origin");
                String memberString = resultSet.getString("members");
                List<String> members = Arrays.asList(memberString.split(","));

                artist = new Artist(id, name, date, origin, members);
            }
        } catch (SQLException e) {
            throw new DaoException("findArtistByIdSet()" + e.getMessage());
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    freeConnection(connection);
                }
            }catch(SQLException e){
                throw new DaoException("findArtistById() " + e.getMessage());
            }
        }

        return artist;
    }

    public void deleteArtistById(int id) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM artists WHERE artist_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            throw new DaoException("deleteArtistByIdSet()" + e.getMessage());
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    freeConnection(connection);
                }
            }catch(SQLException e){
                throw new DaoException("deleteArtistById() " + e.getMessage());
            }
        }
    }

    public void addArtist(Artist artist) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();
            String query = "INSERT INTO artists (artist_name, formed_date, origin, members) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setDate(2, Date.valueOf(artist.getFormation()));
            preparedStatement.setString(3, artist.getOrigin());
            preparedStatement.setString(4, artist.getMembersAsString());
        } catch (SQLException e) {
            throw new DaoException("addArtistSet()" + e.getMessage());
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    freeConnection(connection);
                }
            }catch(SQLException e){
                throw new DaoException("addArtist() " + e.getMessage());
            }
        }
    }
}
