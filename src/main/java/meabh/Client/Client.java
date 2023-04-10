package meabh.Client;

import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class Client {
    public static void main(String[] args){
        Client client = new Client();
        client.start();
    }

    public void start(){
        Menu menu = new Menu();
        try{
            Socket socket = new Socket("localhost", 8080);

            menu.displayOptions();
            String request = menu.enterInput();

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter outputStreamWriter = new PrintWriter(outputStream, true);

            outputStreamWriter.write(request + "\n");
            outputStreamWriter.flush();

            Scanner inputStream = new Scanner(socket.getInputStream());

        } catch(IOException e){
            System.out.println("IOExeption: " + e.getMessage());
        }
    }
}
