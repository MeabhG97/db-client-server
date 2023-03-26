package meabh.DAO;

import java.util.List;

import meabh.DTO.Artist;
import meabh.Exceptions.DaoException;

public interface ArtistDaoInterface {
    public List<Artist> findAllArtists() throws DaoException;
    public Artist findArtistById(int id) throws DaoException;
    public void deleteArtistById(int id) throws DaoException;
    public void addArtist(Artist artist) throws DaoException;
}
