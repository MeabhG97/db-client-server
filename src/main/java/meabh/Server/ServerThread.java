package meabh.Server;

import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import meabh.Request.RequestHandler;

public class ServerThread implements Runnable{
    private Socket clientSocket;

    public ServerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try(PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            System.out.println("Client Connected");
            String request = clientIn.readLine();
            String response = RequestHandler.handleRequest(request);
            clientOut.print(response + "\n");
            clientOut.flush();
        } catch(IOException e){

        }
    }
}
