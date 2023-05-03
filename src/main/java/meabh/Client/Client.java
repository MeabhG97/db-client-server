package meabh.Client;

import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import meabh.Response.ResponseHandler;

public class Client {
    public static void main(String[] args){
        Client client = new Client();
        client.start();
    }

    public void start(){
        Menu menu = new Menu();
        try(Socket socket = new Socket("localhost", 8080);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter outputStreamWriter = new PrintWriter(outputStream, true);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            
                menu.displayOptions();
                String request = menu.enterInput();
                outputStreamWriter.write(request + "\n");
                outputStreamWriter.flush();

                String response = inputStream.readLine();
                ResponseHandler.handleResponse(response);
        } catch(IOException e){
            System.out.println("IOExeption: " + e.getMessage());
        }
    }
}
