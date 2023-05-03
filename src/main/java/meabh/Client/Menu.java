package meabh.Client;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import meabh.DTO.Artist;
import meabh.Request.RequestBuilder;

public class Menu {
    private static Scanner inputScanner = new Scanner(System.in);
    private static ArrayList<String> menuOptions = new ArrayList<>();

    public Menu(){
        menuOptions.add("1. Display Artist by Id");
        menuOptions.add("2. Display All Artists");
        menuOptions.add("3. Add Artist");
        menuOptions.add("4. Delete Artist by Id");
    }
    
    public void displayOptions(){
        System.out.println("---Menu---");
        for(String opt : menuOptions){
            System.out.println(opt);
        }
    }

    public String enterInput(){
        String requestStr = "";
        boolean validOpt = false;
        while(!validOpt){
            System.out.println("Enter number corresponding to chosen option:");
            try{
                int selection = inputScanner.nextInt() - 1;
                switch(selection) {
                    case 0 -> {
                        requestStr = findArtist();
                        validOpt = true;
                    }
                    case 1 -> {
                        requestStr = findAllArtists();
                        validOpt = true;
                    }
                    case 2 -> {
                        requestStr = addArtist();
                        validOpt = true;
                    }
                    case 3 -> {
                        requestStr = deleteArtist();
                        validOpt = true;
                    }
                }
            } catch(InputMismatchException e) {
                System.out.println("Error: input was not a number");
                inputScanner.nextLine();
            } catch(IndexOutOfBoundsException e){
                System.out.println("Error: that option doesn't exists");
            }
        }
        return requestStr;
    }

    private String findArtist(){
        inputScanner.nextLine();
        System.out.println("Enter Id to find:");
        int id = inputScanner.nextInt();
        return RequestBuilder.findArtist(id);
    }

    private String findAllArtists(){
        return RequestBuilder.findAllArtists();
    }

    private String addArtist(){
        inputScanner.nextLine();

        System.out.println("Enter name");
        String name = inputScanner.nextLine();

        LocalDate date = dateInput();

        System.out.println("Enter Orgin");
        String origin = inputScanner.nextLine();

        boolean enterMembers = true;
        int i = 1;
        ArrayList<String> members = new ArrayList<>();
        while(enterMembers){
            System.out.println("Enter memeber " + i + ", Or empty input to stop:");
            i++;

            String mem = inputScanner.nextLine();
            if(mem.length() > 0){
                members.add(mem);
            }
            else{
                enterMembers = false;
            }
        }
        Artist artist = new Artist(name, date, origin, members);

        return RequestBuilder.addArtist(artist);
    }

    private LocalDate dateInput(){
        boolean validDate = false;
        LocalDate date = null;

        while(!validDate){
            System.out.println("Enter date formed (YYYY-MM-DD)");
            String dateString = inputScanner.nextLine();
            try{
                date = LocalDate.parse(dateString);
                validDate = true;
            } catch(DateTimeParseException e){
                System.out.println("Invalid Date");
            }
        }
        return date;
    }

    private String deleteArtist(){
        inputScanner.nextLine();
        System.out.println("Enter Id to delete:");
        int id = inputScanner.nextInt();
        return RequestBuilder.deleteArtist(id);
    }
}
