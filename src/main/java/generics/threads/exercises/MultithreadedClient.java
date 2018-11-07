package generics.threads.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

public class MultithreadedClient {
    static String hostname="127.0.0.1";
    public static final int LISTENING_PORT =32010;
    private static ConcurrentLinkedQueue<String> answerQueue= new ConcurrentLinkedQueue<>();
    private static List<String> results=new ArrayList<>();

    public static void main(String[] args){
        try {
            createClients();
            showQueueContents();
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static void createClients() throws InterruptedException{
        List<ClientThread> clientThreadList=new ArrayList<>();
        IntStream.rangeClosed(1,4).forEach(i->clientThreadList.add(new ClientThread(LISTENING_PORT,answerQueue)));
        for(ClientThread clientThread:clientThreadList){
            clientThread.start();
            clientThread.join();
        }

    }

    public static void showQueueContents(){
        Iterator queueIterator=answerQueue.iterator();
        while(queueIterator.hasNext()){
            System.out.println(queueIterator.next());
        }
    }

    public static void showListContents(){
        for(String string:results){
            System.out.println(string);
        }
    }




}
