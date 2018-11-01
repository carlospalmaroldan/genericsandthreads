package generics.threads.exercises;
import generics.threads.exercises.Response;
import java.util.ArrayList;
import java.util.List;

public class Container {
    List<Response> responseList=new ArrayList<>();

    public synchronized void addToResponseList(Response response){
        responseList.add(response);
    }

    public int maxDivisorsWinner(){
        int winner=0;
        int divisors=0;
        for(int i=0;i<responseList.size();i++){
            if(responseList.get(i).getDivisors()>=divisors){
                divisors=responseList.get(i).getDivisors();
                winner=responseList.get(i).getNumber();
            }
        }
        return winner;
    }

    public void clearContainer(){
        responseList.clear();
    }
}
