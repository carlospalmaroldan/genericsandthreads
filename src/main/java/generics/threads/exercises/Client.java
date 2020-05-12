package generics.threads.exercises;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int LISTENING_PORT =32010;

    public static void main(String[] args){
        String hostname="127.0.0.1";
        Socket connection;

        try{

            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("input message");
                connection=new Socket(hostname,LISTENING_PORT);
                String input =scanner.nextLine();
                sendCommand(input, connection);
                System.out.print("answer is  ");
                getAnswer(connection);
            }
        }catch(IOException e){
            System.out.println(e);
        }

    }

    private static void sendCommand(String command,Socket connection) throws IOException{
        PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
        outgoing.println(command);
        outgoing.flush();

    }

    public static void getAnswer(Socket connection) throws IOException {
        String segment="initialize";
        BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((segment = incoming.readLine()) != null) {
            System.out.println(segment);
        }
        incoming.close();
        connection.close();
    }

}
