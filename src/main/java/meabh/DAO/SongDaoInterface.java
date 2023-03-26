package meabh.DAO;

import java.util.List;

import meabh.DTO.Song;
import meabh.Exceptions.DaoException;

public interface SongDaoInterface{
    public List<Song> getAllSongs() throws DaoException;
    public Song getSongById(int id) throws DaoException;
    public void deleteSongById(int id) throws DaoException;
    public void addSong(Song song) throws DaoException;
}