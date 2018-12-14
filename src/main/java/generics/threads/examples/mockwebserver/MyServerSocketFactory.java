package generics.threads.examples.mockwebserver;

import java.io.IOException;
import java.net.ServerSocket;

public class MyServerSocketFactory {

    private static  ServerSocket serverSocket;

    public static ServerSocket getServerSocket(){
        if(serverSocket==null){
            try {
                 serverSocket = new ServerSocket();
            }catch (IOException e){

            }
            return serverSocket;
        }else{
            return serverSocket;
        }
    }
}
