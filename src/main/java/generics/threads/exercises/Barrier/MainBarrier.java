package generics.threads.exercises.Barrier;

import java.util.Arrays;

public class MainBarrier {
    public static void main(String[] args){
        float[][] matrix = new float[3][5];
        for(int i =0;i<matrix.length;i++) {
            Arrays.fill(matrix[i], 1.0f);
        }

        Solver solver = new Solver(matrix);
        solver.solve();


    }
}
