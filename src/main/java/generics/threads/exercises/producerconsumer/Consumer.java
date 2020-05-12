package generics.threads.exercises.producerconsumer;

public class Consumer implements Runnable{
    MyWaitNotify3 monitor;

    public Consumer(MyWaitNotify3 monitor){
        this.monitor = monitor;
    }

    public void run(){
        for(int i=0;i<3;i++) {

            monitor.doWait();
            System.out.println("after waiting consumer");
            System.out.println("from consumer "+monitor.list.get(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
