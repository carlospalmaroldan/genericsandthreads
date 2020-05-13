package generics.threads.exercises.multipleproducersconsumers;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    public void run(){
        while(true) {
            try {
                Thread.sleep(5000);
                blockingQueue.put(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
