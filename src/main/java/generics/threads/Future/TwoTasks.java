package generics.threads.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TwoTasks {

    public static void main(String[] args) throws InterruptedException,ExecutionException {
        SquareCalculator squareCalculator = new SquareCalculator();
        SquareCalculatorTwoThreads squareCalculatorTwoThreads= new SquareCalculatorTwoThreads();

        //Las dos tareas se le mandan a un executor de un solo hilo
        //son procesadas de forma serial (una despues de la otra)
        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);

        Future<Integer> future3 = squareCalculatorTwoThreads.calculate(10);
        Future<Integer> future4 = squareCalculator.calculate(100);

        while (!(future3.isDone() && future4.isDone())) {
            System.out.println(
                    String.format(
                            "future3 is %s and future4 is %s",
                            future3.isDone() ? "done" : "not done",
                            future4.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result3 = future1.get();
        Integer result4 = future2.get();

        System.out.println(result3 + " and " + result4);
    }
}
