package generics.threads.exercises.queueproducerconsumer;



public class SharedQueue {

    public boolean consumeData = false;
    private boolean produceData = false;

    private LimitedQueue limitedQueue = new LimitedQueue();

    public void consumerWait()  {
        this.consumeData = false;
        synchronized (this){

            while(!consumeData) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        this.consumeData = false;
    }

    public void producerWait(){
        synchronized (this){
            produceData = false;
            while(!produceData){
                try{
                    this.wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            produceData = false;
        }
    }


    public void producerNotify(){
        synchronized (this){
            this.notifyAll();
            produceData = true;
        }
    }

    public void consumerNotify(){
        synchronized (this){
            this.notify();
        }
        this.consumeData = true;
    }

    public LimitedQueue getLimitedQueue(){
        return this.limitedQueue;
    }

    public String toString(){
        return limitedQueue.toString();
    }

}
