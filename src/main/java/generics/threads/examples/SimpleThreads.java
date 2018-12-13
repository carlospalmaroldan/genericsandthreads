package generics.threads.examples;

public class SimpleThreads {

    static void threadMessage(String message){
        System.out.format("%s:%s%n",Thread.currentThread().getName(),message);
    }


    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };

            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done");
            }
        }
    }

    public static void main(String args[]) throws  InterruptedException{
        long patience= 1000*60*60;

        threadMessage("Waiting for message loop thread to finish");

        Thread t= new Thread(new MessageLoop());
        t.start();
        long starTime=System.currentTimeMillis();
        while(t.isAlive()){
            threadMessage("Still waiting...");
            t.join();

            if((System.currentTimeMillis()-starTime)>patience){
                t.interrupt();
                t.join();
            }

        }
        threadMessage("Finally");
    }

}
