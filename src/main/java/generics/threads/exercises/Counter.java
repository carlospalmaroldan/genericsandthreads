package generics.threads.exercises;

public class Counter {
    static  int count;
    static void inc(){
        count=count+1;
    }
    static int getCount(){
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
        //esta claro que los hilos no estan viendo los cambios que los otros hilos hacen y
        //por eso la suma no es lo que deberìa ser
        System.out.println(getCount());

    }

}
