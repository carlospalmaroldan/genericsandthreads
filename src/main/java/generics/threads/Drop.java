package generics.threads;

public class Drop {

    private String message;

    private boolean empty=true;

    public synchronized String take(){
        while(empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        empty=true;

        notifyAll();
        return message;
    }

    public synchronized void put(String putMessage){
        while(!empty){
          try {
              wait();
          }catch(InterruptedException e){}
        }
        empty=false;
        message=putMessage;
        notifyAll();
    }

}
