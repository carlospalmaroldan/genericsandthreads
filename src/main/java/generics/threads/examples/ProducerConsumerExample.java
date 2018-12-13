package generics.threads.examples;

import generics.threads.examples.Consumer;
import generics.threads.examples.Drop;
import generics.threads.examples.Producer;

public class ProducerConsumerExample {
   public static void main(String[] args){
       Drop drop= new Drop();
       (new Thread(new Producer(drop))).start();
       (new Thread(new Consumer(drop))).start();
   }
}
