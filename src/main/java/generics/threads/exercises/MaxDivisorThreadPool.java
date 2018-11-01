package generics.threads.exercises;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MaxDivisorThreadPool {

    private static ConcurrentLinkedQueue<Runnable> taskQueue=new ConcurrentLinkedQueue<Runnable>();
    static int threadCount=4;
    static WorkerThread[] workers=new WorkerThread[threadCount];
    static boolean running=true;
    static int threadsFinished=0;
    static Container container=new Container();

    private static class WorkerThread extends Thread{
        public void run(){
            try {
                while (running){
                    Runnable task=taskQueue.poll();
                    if(task==null){
                        break;
                    }
                    task.run();
                }
            }finally {
                threadFinished();
            }
        }
    }

    static synchronized private void threadFinished(){
        threadsFinished++;
        if(threadsFinished==workers.length){
            running = false;
        }
    }

    public static void main(String args[]) throws InterruptedException{
        long startTime=System.currentTimeMillis();
        int totalRange=10000;
        int numberOfTasks=100;
        int segmentLength=totalRange/numberOfTasks;
        for(int i=0;i<numberOfTasks;i++){
            int start=i*segmentLength;
            int end=(i+1)*segmentLength;
            Task task=new Task(start,end);
            taskQueue.add(task);
        }

        for(int i=0;i<threadCount;i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }

        for(int i=0;i<threadCount;i++) {
            workers[i].join();
        }

        System.out.println(container.maxDivisorsWinner());
        System.out.println("Time with threadpool "+(System.currentTimeMillis()-startTime));
        startTime=System.currentTimeMillis();
        System.out.println(MaxDivisors.maxDivisors(10000));
        System.out.println("time without concurrency "+ (System.currentTimeMillis()-startTime));
        //It seems like using a thread pool instead of creating a thread for every chunk of the problem
        //improves the efficiency of the algorithm
        /*997920
        Time with threadpool 369740 roughly 6.16 minutes
       997920
        time without concurrency 852404  about 14 minutes
         about 20000 milliseconds is the difference between using a thread for each one of the tasks and using
         a thread pool. However in terms of memory it should be better to use less threads?
         */
    }



    private static class Task implements Runnable{
        int start;
        int end;

        public Task(int start,int end){
            this.start=start;
            this.end=end;
        }

        public void run(){
            MaxDivisorsConcurrent maxDivisorsConcurrent=new MaxDivisorsConcurrent();
            container.addToResponseList(maxDivisorsConcurrent.maxDivisorsInRangeInstance(start,end));
        }
    }

}
