package meabh.DAO;

import java.util.List;

import meabh.DTO.Song;

public interface SongDaoInterface{
    public List<Song> getAllSongs();
    public Song getSongById(int id);
    public void deleteSongById(int id);
    public void addSong(Song song);
}