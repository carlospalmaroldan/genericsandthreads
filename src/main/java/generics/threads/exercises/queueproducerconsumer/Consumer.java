package generics.threads.exercises.queueproducerconsumer;

public class Consumer implements Runnable{
    private SharedQueue sharedQueue;

    public Consumer(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    public void run(){
        while(true) {
            if (sharedQueue.getLimitedQueue().isEmpty()) {
                System.out.println("consumer waiting");
                sharedQueue.consumerWait();
            }

            System.out.println("consumer printing "+sharedQueue.getLimitedQueue().remove());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("notifying producer");
            sharedQueue.producerNotify();
        }
    }
}
