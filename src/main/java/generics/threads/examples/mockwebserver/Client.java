package generics.threads.examples.mockwebserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    static String hostName="localhost";
    static int portNumber=8998;

    private static ExecutorService executor;

    public static void sendMessage(){
        try {
            Socket clientSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            out.println("hola");
            String fromServer;
            while((fromServer=in.readLine())!=null){
                System.out.println("Response From Server: " + fromServer);
            }
            System.out.println("client in thread has finished communication");
        }catch(IOException e){}
    }

    //the client creates several thread to communicate with the server, we verify that the server creates
    //a thread for each request
    public static void sendSeveralMessages(){
        executor = Executors.newCachedThreadPool(new SimpleThreadFactory());
        for(int i=0;i<3;i++) {
            executor.execute(new Runnable() {
                @Override public void run() {
                    sendMessage();
                }
            });
        }
    }


    public static void main(String[] args){
        sendSeveralMessages();

    }

}
