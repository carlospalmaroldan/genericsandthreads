package generics.threads.exercises;

import java.util.ArrayList;
import java.util.List;

public class MaxDivisorConcurrentParameterizable {


   public static void main(String[] args) throws InterruptedException{
       long timestart= System.currentTimeMillis();
       int totalRange=1000000;
       int numberOfThreads=16;
       int segmentLength=totalRange/numberOfThreads;
       List<Thread> threadList=new ArrayList<>();
       Container container=new Container();
       List<Integer> segments=new ArrayList<>();
       for(int i=0;i<numberOfThreads;i++){
           int start=i*segmentLength;
           int end=(i+1)*segmentLength;
           segments.add(start);
           segments.add(end);
           threadList.add(new Thread(new Runnable() {
               @Override
               public void run() {
                   MaxDivisorsConcurrent instance=new MaxDivisorsConcurrent();
                   container.addToResponseList(instance.maxDivisorsInRangeInstance(start,end));
               }
           }));
       }
       for(int i=0;i<numberOfThreads;i++){
           threadList.get(i).start();
       }
       for(int i=0;i<numberOfThreads;i++){
           threadList.get(i).join();
       }
       System.out.println(container.maxDivisorsWinner());
       System.out.println("time with concurrency "+ (System.currentTimeMillis()-timestart));
       timestart= System.currentTimeMillis();
       System.out.println(MaxDivisors.maxDivisors(totalRange));
       System.out.println("time without concurrency "+ (System.currentTimeMillis()-timestart));
       //no por tener 8 hilos en vez de cuatro se aprecia una mejora sustancial en el tiempo de procesamiento
       //lo mismo sucede al usar 16 hilos
       //997920
       //time with concurrency 383990 about 6 minutes
       //997920
       //time without concurrency 898407 about minutes
       //we have the same behaviour even for very big inputs

       //So far threads have been using a static method, what happens if the method is called from an instance?
       //997920
       //time with concurrency 387990
       //997920
       //time without concurrency 806947 similar results when compared to using a static method
   }


}
