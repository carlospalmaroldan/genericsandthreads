package generics.threads.exercises.queueproducerconsumer;

public class MainProducerConsumer {


    public static void main(String[] args){
        SharedQueue sharedQueue = new SharedQueue();
        Producer producer = new Producer(sharedQueue);
        Consumer consumer = new Consumer(sharedQueue);
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();

    }


}
