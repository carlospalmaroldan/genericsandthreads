package generics.threads.exercises.puzzle;

import java.util.ArrayList;
import java.util.List;

public class SequentialPuzzle {

    List<Node> seen = new ArrayList<>();

    public static void main(String[] args){
        int[][] array = new int[][]{new int[]{7,2,5},new int[]{4,0,1},new int[]{3,6,8}};
        Matrix matrix = new Matrix(array);
        Node initial = new Node(matrix);
        Node goal = new Node(new Matrix(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,0}}));
        Solver solver = new Solver(initial,goal);
        List<Node> output=solver.solve();
        for(Node node: output) {
            System.out.println(node);
        }
    }







}
