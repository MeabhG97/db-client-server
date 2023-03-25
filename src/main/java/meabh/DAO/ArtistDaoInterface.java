package meabh.DAO;

import java.util.List;

import meabh.DTO.Artist;

public interface ArtistDaoInterface {
    public List<Artist> findAllArtists();
    public Artist findArtistById(int id);
    public void deleteArtistById(int id);
    public void addArtist(Artist artist);
}
