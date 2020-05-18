package generics.threads.exercises.poisonpill;

public class Main {
    public static void main(String[] args){
        ProducerConsumerPoisonPill producerConsumerPoisonPill = new ProducerConsumerPoisonPill();
        producerConsumerPoisonPill.start();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished waiting on the main thread");
        producerConsumerPoisonPill.stop();
    }
}
