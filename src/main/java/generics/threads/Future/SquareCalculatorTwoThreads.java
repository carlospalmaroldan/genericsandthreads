package generics.threads.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculatorTwoThreads {
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    public Future<Integer> calculate(Integer input) {
        return executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return input * input;
            }
        });
    }


}
