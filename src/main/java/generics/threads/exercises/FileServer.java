package generics.threads.exercises;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.text.resources.iw.FormatData_iw_IL;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileServer {
    public static final int LISTENING_PORT = 32010;


    public static void main(String[] args){
        ServerSocket listener;
        Socket connection;
        try {
            listener = new ServerSocket(LISTENING_PORT);
            while (true) {
                connection = listener.accept();
                String message= processMessage(connection);
              //  sendMessage(connection,message);
            }
        }catch (IOException  e){
            System.out.println(e);
        }
    }

    private static void sendDate(Socket client){
        try {
            Date now = new Date();
            PrintWriter outgoing;
            outgoing = new PrintWriter(client.getOutputStream());
            outgoing.println(now.toString());
            outgoing.flush();
            client.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static String processMessage(Socket connection) throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String message=bufferedReader.readLine();
        List<String> filenames=new ArrayList<>();
        if(message.equals("INDEX")){
            File file=new File("C:\\Users\\carlos.palma\\workspace\\Generics2\\files");
            File[] files=file.listFiles();
            Arrays.stream(files).map(f->f.getName()).forEach(name->filenames.add(name));
            sendMultipleMessages(connection,filenames);
        }else if(message.contains("GET")){
            String[] parts=message.split(" ");
            File file=new File(System.getProperty("user.dir")+"\\files\\"+parts[1]);
            if(file.exists()){
                sendFileContent(connection,file);
            }
        }else{
            sendMessage(connection,"ERROR");
        }
        return message ;
    }

    private static void sendMessage(Socket client,String message) throws IOException{
        PrintWriter outgoing;
        outgoing = new PrintWriter(client.getOutputStream());
        outgoing.println(message);
        outgoing.flush();
        client.close();
        //QUIEN DEBE CERRAR LA CONEXION, EL CLIENTE O EL SERVIDOR? POR QUE?

        //EN ESTE CASO SI EL SERVIDOR  CIERRA LA CONEXION EL CLIENTE  DICE SOCKET IS CLOSED LA SEGUNDA VEZ
        //QUE TRATA DE USARLO (NO PUEDE USAR EL MISMO SOCKET PARA MANDAR MENSAJE?)

        //SI EL SERVIDOR NO CIERRA EL SOCKET ENTONCES EL CLIENTE SE QUEDA COLGADO EN
        //incoming.readLine()

        //ADQUIRIMOS UN SOCKET NUEVO CADA VEZ QUE HACEMOS UNA PETICION AL SERVIDOR
    }

    private static void sendMultipleMessages(Socket client,List<String> messages) throws IOException{
        PrintWriter outgoing;
        outgoing = new PrintWriter(client.getOutputStream());
        messages.stream().forEach(m->outgoing.println(m));
        outgoing.flush();
        client.close();
    }

    private static void sendFileContent(Socket client,File file) throws IOException {
        PrintWriter outgoing;
        outgoing = new PrintWriter(client.getOutputStream());
        List<String> lines=Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);
        lines.stream().forEach(line->outgoing.println(line));
        outgoing.flush();
        client.close();
    }

}
