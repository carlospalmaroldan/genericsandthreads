package generics.threads.exercises;

public class SynchronizedCounter {
    static  int count;
    static synchronized void inc(){
        count=count+1;
    }
    static synchronized int getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50000;i++){
                    inc();
                }
            }
        });
        t1.start();
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<80000;i++){
                    inc();
                }
            }
        });
        t2.start();
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100000;i++){
                    inc();
                }
            }
        });
        t3.start();
        Thread.sleep(3000);
        //al sincronizar el metodo de aumento logramos un resultado correcto
        System.out.println(getCount());

    }

}
