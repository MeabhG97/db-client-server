package meabh.DAO;

import java.util.List;
import meabh.DTO.Album;

public interface AlbumDaoInterface {
    public List<Album> getAllAlbums();
    public Album getAlbumById(int id);
    public void deleteAlbumById(int id);
    public void addAlbum(Album album);
}
