package generics.threads.exercises;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.IntStream;

public class FileServerMultiThreaded {
    public static final int LISTENING_PORT = 32010;
    private ServerSocket listener;
    private Socket connection;
    private static ConnectionHandler connectionHandler;
    private static ArrayBlockingQueue<Socket> connectionQueue;


    public static void main(String[] args){
        ServerSocket listener;
        Socket connection;
        connectionQueue= new ArrayBlockingQueue<Socket>(15);
        createWorkerThreads();
        try {
            listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Multithreaded server waiting on port "+LISTENING_PORT);
            while (true) {
                connection = listener.accept();
                connectionQueue.add(connection);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }


    private static class ConnectionHandler extends  Thread{
        ArrayBlockingQueue queue;
        public ConnectionHandler(ArrayBlockingQueue queue){
            this.queue=queue;
        }
        public void run(){
            try {
                System.out.println("Thread ID- " +Thread.currentThread().getId()+" created and listening to the queue");
                Socket connection=connectionQueue.take();
                FileServer fileServer=new FileServer();
                fileServer.processMessage(connection);
            }catch (InterruptedException | IOException e){
                System.out.println(e);
            }
        }
    }

    public static void createWorkerThreads(){
        List<ConnectionHandler> connectionHandlerList=new ArrayList<>();
        IntStream.rangeClosed(1, 4).forEach(i->connectionHandlerList.add(new ConnectionHandler(connectionQueue)));
        connectionHandlerList.stream().forEach(w->w.start());
    }
}
