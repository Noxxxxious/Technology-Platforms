package lab3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        System.out.println("Insert n: ");
        String nextLine = scanner.nextLine();
        System.out.println(client.createRequest((Integer.parseInt(nextLine))));
        scanner.close();
    }

    String createRequest(int number){
        try(Socket client = new Socket("localhost", 9797);){
            try(ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(client.getInputStream())){
                String response = (String) is.readObject();
                if(!response.equals("ready")){
                    return "error! not ready.";
                }
                os.writeObject(number);
                response = (String) is.readObject();
                if(!response.equals("ready for a message")){
                    return "error! not ready to receive a message.";
                }

                int[] arr;
                for(int i = 0; i < number; i++){
                    arr = getRandArr(number);
                    os.writeObject(new Message(i, arr));
                }
                response = (String) is.readObject();
                if(!response.equals("done")){
                    return "error! not done yet.";
                }
                return "messages sent";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error!";
    }

    private int[] getRandArr(int range){
        int[] arr = new int[10];
        for(int j = 0; j < arr.length; j++){
            arr[j] = (int)(Math.random() * range) + 1;
        }
        return arr;
    }
}
