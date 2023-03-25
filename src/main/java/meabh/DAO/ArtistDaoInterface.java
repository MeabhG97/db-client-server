package meabh.DAO;

import java.util.List;

import meabh.DTO.Artist;

public interface ArtistDaoInterface {
    public List<Artist> findAllArtists();
    public Artist findArtistById(int id);
    public boolean deleteArtistById(int id);
}
