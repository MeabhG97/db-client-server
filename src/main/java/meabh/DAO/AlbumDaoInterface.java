package meabh.DAO;

import meabh.Exceptions.DaoException;

public interface AlbumDaoInterface {
    public String getArtistAlbumsJson(int artistId) throws DaoException;
}
