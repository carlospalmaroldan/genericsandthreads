package generics.threads.exercises.puzzle;

import org.springframework.core.PriorityOrdered;

import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelSolver  {
    Node initial;
    Node goal;
    CopyOnWriteArrayList<Node> seen;
    ExecutorService executorService;
    volatile boolean finished = false;
    PriorityBlockingQueue<Runnable> priorityQueue;
    //we create several threads that only stop processing once the goal has been
    //achieved, and when that happens this shared variable is assigned
    //return does not make any sense when using tasks running in parallel
    Node end;
    public ParallelSolver(Node initial,Node goal){
       this.initial = initial;
       this.goal= goal;
       this.seen = new CopyOnWriteArrayList<>();
       priorityQueue  = new PriorityBlockingQueue<Runnable>(10, new Comparator<Runnable>() {
           @Override public int compare(Runnable o1, Runnable o2) {
               int d1 = ((PriorityTask)o1).getNode().positionDistance();
               int d2 = ((PriorityTask)o2).getNode().positionDistance();
               return d2-d1;
           }
       });
       executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
           5,
           10000,
           TimeUnit.MILLISECONDS,
           priorityQueue);
    }

    public List<Node> solve(){
        parallelProcess(initial);
        List<Node> ancestors = new ArrayList<>();
        //wait until some task identifies that we arrived at the goal
            while(!finished){}
            ancestors = getAncestors(end);

        return ancestors;
    }

   public void parallelProcess(Node node){
       if(node.equals(goal)){
           end = node;
           finished = true;
           executorService.shutdown();
           return;
       }
       List<Node> children = node.getChildren();
       //process will be called from several different threads, we need to make
       //sure they don't clash when used the shared state that is the array of already
       //seen matrices
       seen.addIfAbsent(node);

       List<Node> toProcess = new ArrayList<>();
       for(Node child:children){
           if(child.equals(goal)){
               end = child;
               finished = true;
               executorService.shutdown();
               return;
           }

           if(!seen.contains(child)){
               seen.add(child);
               toProcess.add(child);
           }
       }

       for(Node child:toProcess){
           executorService.submit(new PriorityTask(child));
       }
   }
    //when a thread is checking if the node is among the already seen ones we don't want
    //another thread adding elements to the list
    public synchronized boolean synchronizedContains(List<Node> seen,Node node){
        boolean output = false;
        for(Node child:seen){
            if(child.equals(node))
                output = true;
        }
        return output;
    }

    public List<Node> getAncestors(Node node){
        List<Node> ancestors = new ArrayList<>();
        Node parent = node.getParent();
        ancestors.add(node);
        while(parent!=null){
            ancestors.add(parent);
            parent = parent.getParent();
        }
        return ancestors;
    }

    private synchronized void add(List<Node> seen,Node node){
        seen.add(node);
    }

    private class PriorityTask implements Runnable{
        Node node;
        public PriorityTask(Node node){
            this.node = node;
        }

        public void run()  {
                if(!finished) {

                    System.out.println("father:"+"\n"+node.getParent()+"\n"+"intermediate node"+"\n"+node+"\n"+node.positionDistance()+"\n");

                    parallelProcess(node);
                }
                System.out.println("finished runnable");
        }

        public Node getNode(){return this.node;}

    }
}
