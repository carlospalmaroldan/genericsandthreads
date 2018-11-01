package generics.threads.exercises;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class MaxDivisorThreadPoolTwoQueues {

    static ArrayBlockingQueue<Response> resultsQueue=new ArrayBlockingQueue<Response>(15);
    static LinkedBlockingQueue<Task> taskQueue=new LinkedBlockingQueue<Task>();
    List<WorkerThread> workers=new ArrayList<>();
    static int numberOfTasks=100;
    static long startTime=0L;
    static Container container=new Container();
    public static void main(String[] args) throws InterruptedException{

        boolean thisBatch=true;
        int counter=0;
        createWorkerThreads();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("input value");
            String input =scanner.nextLine();
            System.out.println("input is '" + input + "'");
            fillTaskQueue(Integer.parseInt(input));
            thisBatch=true;
            startTime=System.currentTimeMillis();
            while(thisBatch) {
                Response response = resultsQueue.poll();
                if(response!=null) {
                   // System.out.println("response "+counter+ " = " + response.number + " " + response.divisors);
                    counter++;
                    container.addToResponseList(response);
                    if(counter==numberOfTasks){
                        System.out.println(container.maxDivisorsWinner());
                        container.clearContainer();
                        System.out.println("elapsed time "+ (System.currentTimeMillis()-startTime));
                        counter=0;
                        thisBatch=false;
                    }
                }
            }
        }
    }

    public static void fillTaskQueue(int input){

        int segmentLength=0;
        segmentLength=input/numberOfTasks;
        for(int i=0;i<numberOfTasks;i++) {
            taskQueue.add(new Task(i*segmentLength,(i+1)*segmentLength,resultsQueue));
        }
    }

    public static void createWorkerThreads(){
        List<WorkerThread> workerThreadList=new ArrayList<>();
        IntStream.rangeClosed(1, 4).forEach(i->workerThreadList.add(new WorkerThread(taskQueue)));
        workerThreadList.stream().forEach(w->w.start());
    }

   /* input value
    10000
    input is '10000'
            9240
    elapsed time 62
    input value
    100000
    input is '100000'
            98280
    elapsed time 3827
    input value
    1000000
    input is '1000000'
            997920
    elapsed time 433871  This approach using two queues actually takes more time than using just one, what is interesting is
    that results can be consumed as soon as they arrive.
    input value*/

}
