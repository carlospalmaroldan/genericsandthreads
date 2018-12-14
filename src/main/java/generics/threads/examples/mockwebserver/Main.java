package generics.threads.examples.mockwebserver;

public class Main {

    private static MockWebServer mockWebServer=new MockWebServer();

    public static void main(String[] args){
        mockWebServer.start();
    }
}
