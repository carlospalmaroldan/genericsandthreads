package generics.threads.exercises.Barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/* Example class to see how Barrier works
 A matrix containing all 1s is passed, each worker thread
 sums the elements in a row of the matrix, while incrementing the entries
 When all the threads end the barrier sums the partial results given
 by the workers
 When the sum total is greater than 15 then the process ends
 This means that the barrier is used twice, this to illustrate its main
 advantage over a latch*/

public class Solver {
    final int N;
    final float[][] data;
    private final CyclicBarrier barrier;
    private float[] partialResults = new float[3];
    //this one has to be volatile because the merging thread is the one changing it, all the other threads
    //just read it to see if they have to keep working (workers) or waiting, when not volatile, sometimes the
    //code will hang
    private volatile boolean done=false;

    public Solver(float[][] matrix) {
        data = matrix;
        N = matrix.length;
        barrier = new CyclicBarrier(N,
            new Runnable() {
                public void run() {
                    mergeRows();
                }
            });

    }

    public void solve(){
        for (int i = 0; i < N; ++i)
            new Thread(new Worker(i)).start();
        System.out.println("preparing to wait");
        waitUntilDone();
        System.out.println("solved");
    }

    private void mergeRows(){
        float sum = 0.0f;
        for(int i = 0 ; i< partialResults.length;i++){
            sum = sum + partialResults[i];
        }
        System.out.println("sum is " +  sum);
        if(sum > 15){
            done = true;
        }
    }

    private void waitUntilDone(){
        System.out.println(done);
        while(!done){}

    }


    class Worker implements Runnable {
        int myRow;
        Worker(int row) { myRow = row; }
        public void run() {
            while (!done) {
                processRow(myRow);

                try {
                    System.out.println(Thread.currentThread().getName()+ " waiting");
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }
        private void processRow(int row){
            float sum = 0.0f;
            for(int i=0;i<data[0].length;i++){
                sum = sum + data[row][i];
                data[row][i] += 1.0f;
            }
            partialResults[row] = sum;
        }
    }
}
