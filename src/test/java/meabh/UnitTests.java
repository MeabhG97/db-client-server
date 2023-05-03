package meabh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import meabh.DAO.ArtistDao;
import meabh.DAO.ArtistDaoInterface;
import meabh.DTO.Artist;
import meabh.Exceptions.DaoException;

public class UnitTests {
    @Test
    public void testFindAll(){
        try{
            ArtistDaoInterface artistDao = new ArtistDao();
            List<Artist> artists = artistDao.findAllArtists();

            String expectedResult = "[Artist [id=1, name=Nightwish, formation=1996-07-06, origin=Kitee,Finland, members=[Tuomas Holopainen, Emppu Vuorinen, Jukka Nevalainen, Marco Hietala, Floor Jansen]], Artist [id=2, name=Beast in Black, formation=2015-12-08, origin=Helsinki,Finland, members=[Anton Kabanen, Yannis Papadopoulos, Kasperi Heikkinen, Mate Molnar, Atte Palokangas]], Artist [id=3, name=Apocalyptica, formation=1993-09-17, origin=Helsinki,Finland, members=[Eicca Toppinen, Perttu Kivilaakso, Paavo Lötjönen, Mikko Sirén]]]";
            String actualResult = artists.toString();
            
            assertEquals(expectedResult, actualResult);
        } catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testfindOne(){
        try{
            ArtistDaoInterface artistDao = new ArtistDao();
            Artist artist = artistDao.findArtistById(2);

            String expectedResult = "Artist [id=2, name=Beast in Black, formation=2015-12-08, origin=Helsinki,Finland, members=[Anton Kabanen, Yannis Papadopoulos, Kasperi Heikkinen, Mate Molnar, Atte Palokangas]]";
            String actualResult = artist.toString();
            
            assertEquals(expectedResult, actualResult);
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void testAdd(){
        try{
            ArtistDaoInterface artistDao = new ArtistDao();

            LocalDate date = LocalDate.of(2003, 11, 13);
            List<String> members = new ArrayList<>();
            members.add("member1");
            members.add("member2");
            members.add("member3");
            Artist artist = new Artist( "Test", date, "Location", members);

            artistDao.addArtist(artist);
            String a = artistDao.findLastAddedArtistJson();
            

            String expectedResult = "Artist [id=1351, name=Test, formation=2003-11-13, origin=Location, members=[member1, member2, member3]]";
            String actualResult = a;

            artistDao.deleteArtistById(4);
            
            assertEquals(expectedResult, actualResult);
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDelete(){
        try{
            ArtistDaoInterface artistDao = new ArtistDao();
            List<Artist> artists = artistDao.findAllArtists();

            LocalDate date = LocalDate.of(2003, 11, 13);
            List<String> members = new ArrayList<>();
            members.add("member1");
            members.add("member2");
            members.add("member3");
            Artist artist = new Artist("Test", date, "Location", members);

            artistDao.addArtist(artist);

            artistDao.deleteArtistById(4);

            String expectedResult = "[Artist [id=1, name=Nightwish, formation=1996-07-06, origin=Kitee,Finland, members=[Tuomas Holopainen, Emppu Vuorinen, Jukka Nevalainen, Marco Hietala, Floor Jansen]], Artist [id=2, name=Beast in Black, formation=2015-12-08, origin=Helsinki,Finland, members=[Anton Kabanen, Yannis Papadopoulos, Kasperi Heikkinen, Mate Molnar, Atte Palokangas]], Artist [id=3, name=Apocalyptica, formation=1993-09-17, origin=Helsinki,Finland, members=[Eicca Toppinen, Perttu Kivilaakso, Paavo Lötjönen, Mikko Sirén]]]";
            String actualResult = artists.toString();
            
            assertEquals(expectedResult, actualResult);
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }
}
