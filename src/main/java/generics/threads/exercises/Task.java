package generics.threads.exercises;

import java.util.concurrent.ArrayBlockingQueue;

public class Task implements Runnable{
    int start;
    int end;
    ArrayBlockingQueue queue;

    public Task(int start,int end,ArrayBlockingQueue queue){
        this.start=start;
        this.end=end;
        this.queue=queue;
    }

    public void run() {
        MaxDivisorsConcurrent maxDivisorsConcurrent=new MaxDivisorsConcurrent();
        try {
            queue.put(maxDivisorsConcurrent.maxDivisorsInRangeInstance(start, end));
        }catch (InterruptedException e){System.out.println(e);}
    }
}
