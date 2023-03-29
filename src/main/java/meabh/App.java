package meabh;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import meabh.DAO.ArtistDao;
import meabh.DAO.ArtistDaoInterface;
import meabh.DTO.Artist;
import meabh.Exceptions.DaoException;

public class App {
    public static void main(String[] args) {
        DaoMenu();
    }

    public static void DaoMenu(){
        try(Scanner menu = new Scanner(System.in)){
        ArtistDaoInterface artistDaoInterface = new ArtistDao();

        System.out.println("1. Find All Artists");
        System.out.println("2. Find Artist By Id");
        System.out.println("3. Delete Artist By Id");
        System.out.println("4. Add new Artist");

        int opt = menu.nextInt();
            switch(opt){
                case 1 -> {
                    List<Artist> artists =  artistDaoInterface.findAllArtists();
                    System.out.println(artists.toString());
                }
                case 2 -> {
                    System.out.println("Enter Id to search");
                    int id = menu.nextInt();
                    Artist artist = artistDaoInterface.findArtistById(id);
                    System.out.println(artist);
                }
                case 3 -> {
                    System.out.println("Enter Id to delete");
                    int id = menu.nextInt();
                    artistDaoInterface.deleteArtistById(id);
                }
                case 4 -> {
                    menu.nextLine();
                    System.out.println("Enter name");
                    String name = menu.nextLine();
                    System.out.println("Enter date formed");
                    String dateString = menu.nextLine();
                    LocalDate date = LocalDate.parse(dateString);
                    System.out.println("Enter Orgin");
                    String origin = menu.nextLine();
                    boolean enterMembers = true;
                    int i = 1;
                    ArrayList<String> members = new ArrayList<>();
                    while(enterMembers){
                        System.out.println("Enter memeber " + i);
                        i++;
                        String mem = menu.nextLine();
                        if(mem.length() > 0){
                            if(members.isEmpty()){
                                members.add(mem);
                            }
                            else{
                                String m = mem;
                                System.out.println(m);
                                members.add(m);
                            }
                            System.out.println(members);
                        }
                        else{
                            enterMembers = false;
                        }
                    }
                    Artist artist = new Artist(name, date, origin, members);
                    artistDaoInterface.addArtist(artist);
                }
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }
}
