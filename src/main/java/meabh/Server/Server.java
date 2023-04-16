package meabh.Server;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.lang.Thread;

public class Server {
    public static void main(String args[]){
        Server server = new Server();
        server.start();
    }

    private void start(){
        try(ServerSocket serverSocket = new ServerSocket(8080)){
            serverSocket.setReuseAddress(true);

            while(true){
                Socket clientConnection = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientConnection);
                new Thread(serverThread).start();	
            }
            
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
