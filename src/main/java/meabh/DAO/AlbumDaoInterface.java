package meabh.DAO;

import java.util.List;

import meabh.DTO.Album;
import meabh.Exceptions.DaoException;

public interface AlbumDaoInterface {
    public List<Album> getAllAlbums() throws DaoException;
    public Album getAlbumById(int id) throws DaoException;
    public void deleteAlbumById(int id) throws DaoException;
    public void addAlbum(Album album) throws DaoException;
}
