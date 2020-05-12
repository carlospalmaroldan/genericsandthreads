package generics.threads.exercises.producerconsumer;

public class MainProduceConsumer {

    public static void main(String[] args){
        //shared reference to synchronize on
        MyWaitNotify3 monitor = new MyWaitNotify3();
        Producer producer = new Producer(monitor);
        Consumer consumer = new Consumer(monitor);

        Thread thread1  = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        //ja, I fell for it, run called directly DOES NOT start different threads, just runs
        //on the main thread
        /*thread1.run();
        thread2.run();*/
        thread1.start();
        thread2.start();


    }
}
