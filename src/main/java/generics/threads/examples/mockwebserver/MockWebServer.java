package generics.threads.examples.mockwebserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MockWebServer {

    private ExecutorService executor;

    private ServerSocket serverSocket= MyServerSocketFactory.getServerSocket();

    private final Set<Socket> openClientSockets =new HashSet<>();

    private int port =8998;

    public void start(){

        try {
            InetAddress inetAddress=InetAddress.getByName("localhost");
            serverSocket.bind(new InetSocketAddress(inetAddress, port));
        }catch( IOException e){
            return;
        }

        executor = Executors.newCachedThreadPool(new SimpleThreadFactory());

        executor.execute(new Runnable() {

            @Override public void run() {
                acceptConnections();
            }

            private void acceptConnections(){
                System.out.println("accepting connections");
                while (true) {
                    Socket socket;
                    try {
                        socket = serverSocket.accept();
                    }catch(IOException ex){
                        return;
                    }

                    openClientSockets.add(socket);
                    serveConnection(socket);
                }
            }
        });
    }

    private void serveConnection(Socket socket) {
        System.out.println("serveConnection");
            executor.execute(new Runnable() {
                @Override public void run() {
                    processConnection();
                }

                public void processConnection(){
                    processOneRequest();

                }

                private boolean processOneRequest(){

                    try {
                        PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in =
                            new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));

                        String userInput;
                        while((userInput=in.readLine())!=null){
                            out.println(Thread.currentThread().getName());
                            socket.close(); //It's up to the server to signal that communication has ended
                        }

                    }catch (IOException e){

                    }
                    return true;
                }
            });
    }
}
