package generics.threads.exercises;

import java.util.ArrayList;
import java.util.List;

public class MaxDivisorsConcurrent {
    //find the integer in the range 1 to 10000 that has the largest number of divisors


    public static Response maxDivisorsInRange(int start,int end){
        int maxDivisors=0;
        int winner=0;
        for(int i=start;i<end;i++){
            int half=0;
            if(i%2==0){
                half=i/2;
            }else{
                half=(i+1)/2;
            }
            int divisors=0;
            for(int j=1;j<half;j++){
                if(i%j==0){
                    divisors=divisors+1;
                }
            }
            if(divisors>=maxDivisors){
                maxDivisors=divisors;
                winner=i;
            }
        }
        Response response = new Response();
        response.setNumber(winner);
        response.setDivisors(maxDivisors);
        return response;
    }

    public  Response maxDivisorsInRangeInstance(int start,int end){
        int maxDivisors=0;
        int winner=0;
        for(int i=start;i<end;i++){
            int half=0;
            if(i%2==0){
                half=i/2;
            }else{
                half=(i+1)/2;
            }
            int divisors=0;
            for(int j=1;j<half;j++){
                if(i%j==0){
                    divisors=divisors+1;
                }
            }
            if(divisors>=maxDivisors){
                maxDivisors=divisors;
                winner=i;
            }
        }
        Response response = new Response();
        response.setNumber(winner);
        response.setDivisors(maxDivisors);
        return response;
    }



    public static void main(String[] args) throws InterruptedException{
      long start= System.currentTimeMillis();
        Container container=new Container();
     /*   container.addToResponseList(maxDivisorsInRange(0,2500));
        container.addToResponseList(maxDivisorsInRange(2501,5000));
        container.addToResponseList(maxDivisorsInRange(5001,7500));
        container.addToResponseList(maxDivisorsInRange(7501,10000));*/
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
               container.addToResponseList(maxDivisorsInRange(0,2500));
            }
        });
        t1.start();
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                container.addToResponseList(maxDivisorsInRange(2501,5000));
            }
        });
        t2.start();
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                container.addToResponseList(maxDivisorsInRange(5001,7500));
            }
        });
        t3.start();
        Thread t4=new Thread(new Runnable() {
            @Override
            public void run() {
                container.addToResponseList(maxDivisorsInRange(7501,10000));
            }
        });
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println(container.maxDivisorsWinner());
        System.out.println("time with concurrency "+ (System.currentTimeMillis()-start));
        start= System.currentTimeMillis();
        System.out.println(MaxDivisors.maxDivisors(10000));
        System.out.println("time without concurrency "+ (System.currentTimeMillis()-start));
    }

}
