package generics.threads.exercises;

import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread extends Thread{


    LinkedBlockingQueue<Task> taskQueue;

    public WorkerThread(LinkedBlockingQueue<Task> taskQueue){
        setDaemon(true); //only ends when the virtual machine is turned off
        this.taskQueue=taskQueue;
    }


    public void run(){
            while (true){
                try {
                    Runnable task = taskQueue.take(); //the magic of this method is blocking the worker until there's something in the queue
                    task.run();
                }catch(InterruptedException e){
                    System.out.println(e);
                }
        }
    }


}
