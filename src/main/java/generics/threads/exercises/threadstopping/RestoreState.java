package generics.threads.exercises.threadstopping;

public class RestoreState {

    public static void main(String []args){
        whileExecutesIndefinitely();
        System.out.println("should not be here");
    }

    public static void whileExecutesIndefinitely(){
        int divisor = 0;
        int dividend = 5;
        boolean interrupted = false;
        while(true){
            try{
                int result = dividend/divisor;
            } catch(Exception e){
                interrupted = true;
                System.out.println("entered");
            }finally{
                if(interrupted)
                    System.out.println("restoring state");
                    Thread.currentThread().interrupt();
            }
        }
    }
}
