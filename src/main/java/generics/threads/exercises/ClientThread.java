package generics.threads.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientThread  extends Thread{
    Socket connection;
    String hostname="127.0.0.1";
    public int listeningPort ;
    public ConcurrentLinkedQueue<String> answerQueue;

    public ClientThread(int listeningPort,ConcurrentLinkedQueue<String> answerQueue){
        this.listeningPort=listeningPort;
        this.answerQueue=answerQueue;
    }

    public void run(){
        try {
            connection = new Socket(hostname, listeningPort);
            sendCommand("INDEX",connection);
            getAnswer(connection,answerQueue);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private static void sendCommand(String command,Socket connection) throws IOException{
        PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
        outgoing.println(command);
        outgoing.flush();
    }

    public static void getAnswer(Socket connection,ConcurrentLinkedQueue<String> answerQueue) throws IOException {
        String segment="initialize";
        BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((segment = incoming.readLine()) != null) {
            answerQueue.add("Thread ID- " +Thread.currentThread().getId()+" "+ segment);
        }
        incoming.close();
        connection.close();
    }
}
