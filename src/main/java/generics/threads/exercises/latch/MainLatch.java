package generics.threads.exercises.latch;

import java.util.concurrent.CountDownLatch;

public class MainLatch {

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(5);

        for(int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            }).start();
        }

        long start  = System.currentTimeMillis();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("latch released");
        System.out.println(System.currentTimeMillis()-start);

    }

}
