package lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException{
        try(ServerSocket serverSocket = new ServerSocket(9797)){
            Socket client = serverSocket.accept();
            System.out.println("New client connected: " + client);
            Thread thread = new Thread(new ServerHandler(client));
            thread.start();
            serverSocket.close();
        } catch(IOException e){
            System.err.println(e);
        }
    }
}
