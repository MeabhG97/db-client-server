package meabh.Server;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Server {
    public static void main(String args[]){
        Server server = new Server();
        server.start();
    }

    private void start(){
        try(ServerSocket serverSocket = new ServerSocket(8080)){
            
            
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
