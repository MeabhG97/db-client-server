package meabh.DAO;

import meabh.Exceptions.DaoException;

public interface SongDaoInterface{
    public String getAlbumSongsJSON(int albumId) throws DaoException;
}