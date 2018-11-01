package generics.threads.exercises;

public class MaxDivisors {
    //find the integer in the range 1 to 10000 that has the largest number of divisors
    public static int maxDivisors(int maxRange){
        int maxDivisors=0;
        int winner=0;
        for (int i=0;i<maxRange;i++){
            int half=0;
            if(i%2==0){
                half=i/2;
            }else{
                half=(i+1)/2;
            }
            int divisors=0;
            for(int j=1;j<half;j++){
                if(i%j==0){
                    divisors=divisors+1;
                }
            }
            if(divisors>=maxDivisors){
                maxDivisors=divisors;
                winner=i;
            }
        }
        return winner;
    }

    public static void main(String[] args){
        long start= System.currentTimeMillis();
        System.out.println(maxDivisors(10000));
        System.out.println("time "+ (System.currentTimeMillis()-start));
    }
}
