package generics.threads.exercises.producerconsumer;

import generics.threads.exercises.producerconsumer.MyWaitNotify3;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable{
    MyWaitNotify3 monitor;
    List<String> source= new ArrayList<>();

    public Producer(MyWaitNotify3 monitor){
        this.monitor = monitor;
        this.source.add("hola");
        this.source.add("como");
        this.source.add("estas");
    }

    public void run(){
        for(int i = 0;i< 3; i++){
            monitor.list.add(source.get(i));

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("added");
            monitor.doNotify();


        }
    }

}
