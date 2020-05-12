package generics.threads.exercises.producerconsumer;

import generics.threads.exercises.producerconsumer.Monitor;

import java.util.ArrayList;
import java.util.List;

public class MyWaitNotify3{

    Monitor myMonitorObject = new Monitor();
    boolean wasSignalled = false;
    List<String> list = new ArrayList<>();

    public void doWait(){
        synchronized(myMonitorObject){
            while(!wasSignalled){
                try{
                    myMonitorObject.wait();
                } catch(InterruptedException e){}
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify(){
        synchronized(myMonitorObject){
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }
}

