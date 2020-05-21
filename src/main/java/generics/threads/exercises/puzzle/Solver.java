package generics.threads.exercises.puzzle;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver {
    Node initial;
    Node goal;

    public Solver(){}

    public Solver(Node initial,Node goal){
        this.initial = initial;
        this.goal = goal;
    }

    public List<Node> solve(){
        Node end = process(initial);
        return getAncestors(end);
    }

    public List<Node> getAncestors(Node node){
        List<Node> ancestors = new ArrayList<>();
        Node parent = node.getParent();
        ancestors.add(node);
        while(parent!=null){
            ancestors.add(parent);
            parent = parent.getParent();
        }
        return ancestors;
    }


    public abstract Node process(Node node);


    public boolean contains(List<Node> seen,Node node){
        boolean output = false;
        for(Node child:seen){
            if(child.equals(node))
                output = true;
        }
        return output;
    }

}
