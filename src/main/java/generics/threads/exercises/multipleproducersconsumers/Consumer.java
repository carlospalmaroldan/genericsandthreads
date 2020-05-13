package generics.threads.exercises.multipleproducersconsumers;


import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    public void run(){
        while(true){
            try {
                String value=blockingQueue.take();
                System.out.println(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
