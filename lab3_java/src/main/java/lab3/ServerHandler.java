package lab3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket(){
        return this.socket;
    }

    @Override
    public void run(){
        try(ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream())){
            os.writeObject("ready");
            Integer n = (Integer) is.readObject();
            os.writeObject("ready for a message");
            for (int i = 0; i < n; i++){
                Message in = (Message) is.readObject();
                System.out.println(Arrays.toString(in.getContent()));
            }
            os.writeObject("done");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
