package generics.threads.exercises.multipleproducersconsumers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainMultipleProducersConsumers {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100);

        for (int i = 1; i <= 10; i++) {
            new Thread(new Producer(blockingQueue)).start();
            new Thread(new Consumer(blockingQueue)).start();
        }


    }
}
