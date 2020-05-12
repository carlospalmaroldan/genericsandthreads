package generics.threads.exercises.queueproducerconsumer;

public class Producer implements Runnable{
    private SharedQueue sharedQueue;
    private int counter;

    public Producer(SharedQueue sharedQueue){
        this.sharedQueue=sharedQueue;
    }

    public void run(){
        while(true){
            if(sharedQueue.getLimitedQueue().isFull()){
                System.out.println("producer has filled the queue");
                sharedQueue.producerWait();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sharedQueue.getLimitedQueue().add("String"+counter);
            counter++;
            System.out.println(sharedQueue.toString());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("notifying consumer");
            sharedQueue.consumerNotify();
        }
    }
}
