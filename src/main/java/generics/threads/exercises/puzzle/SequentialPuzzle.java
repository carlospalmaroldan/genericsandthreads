package generics.threads.exercises.puzzle;


import java.util.List;

public class SequentialPuzzle {


    public static void main(String[] args){
        int[][] array = new int[][]{new int[]{7,2,5},new int[]{4,0,1},new int[]{3,6,8}};
        Matrix matrix = new Matrix(array);
        Node initial = new Node(matrix);
        Node goal = new Node(new Matrix(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,0}}));
       /* Solver solver = new SequentialSolver(initial,goal);
        long start = System.currentTimeMillis();
        List<Node> output=solver.solve();
        for(Node node: output) {
            System.out.println(node);
        }
        long sequentialTime = System.currentTimeMillis() - start;*/


        initial = new Node(new Matrix(new int[][]{new int[]{1,3,0},new int[]{4,2,6},new int[]{7,5,8}}));

        ParallelSolver parallelSolver = new ParallelSolver(initial,goal);
        long startParallel = System.currentTimeMillis();
        List<Node> outputParallel=parallelSolver.solve();
        for(Node node: outputParallel) {
            System.out.println(node);
        }

        System.out.println("parallel time: " +(System.currentTimeMillis() - startParallel));

    }







}
