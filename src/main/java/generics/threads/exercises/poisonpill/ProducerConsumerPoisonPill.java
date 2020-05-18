package generics.threads.exercises.poisonpill;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerPoisonPill {
    private static final String POISON = "POISON";
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
    private  Producer producer = new Producer();
    private  Consumer consumer = new Consumer();

    private  class Producer extends Thread{
        //the producer is the one who signals that the process must stop
        //by putting the POISON pill in the queue
        private int count = 0;

        public void run(){
            try {
                while(true) {
                    queue.put("String " + count);
                    synchronized (this){count++;}
                }
            } catch (InterruptedException e) {
                System.out.println("capture time");
                //Exit code 0 (successful termination) even though an exception is shown on the console
                //e.printStackTrace();
                System.out.println(e.getCause());

            }
            finally {
                System.out.println("entering finally");
                while(true){
                    //make sure the POISON pill is put in the queue
                    try {
                        queue.put(POISON);
                        System.out.println("POISON pill put");
                        break;
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }
    }

    private class Consumer extends Thread{
        public void run(){
                while(true){
                    try {
                         String queueContent = queue.take();
                         System.out.println(queueContent);
                        if(queueContent.equals(POISON)){
                            break;
                        }
                    } catch (InterruptedException e) {
                        //consumer only leaves when it takes the POISON pill
                        //from the queue
                        e.printStackTrace();
                    }
                }
        }
    }


    public void start(){
        producer.start();
        consumer.start();
    }

    public void stop(){
        producer.interrupt();
    }


}
