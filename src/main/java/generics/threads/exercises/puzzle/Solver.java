package generics.threads.exercises.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solver {
    List<Node> seen;
    Node initial;
    Node goal;

    public Solver(Node initial, Node goal){
        this.initial= initial;
        this.seen = new ArrayList<>();
        this.goal = goal;
    }


    public List<Node> solve(){
        Node end = process(initial);
        return getAncestors(end);
    }

    private List<Node> getAncestors(Node node){
        List<Node> ancestors = new ArrayList<>();
        Node parent = node.getParent();
        ancestors.add(node);
        while(parent!=null){
            ancestors.add(parent);
            parent = parent.getParent();
        }
        return ancestors;
    }

    private Node process(Node node){
        if(node.equals(goal)){
            return node;
        }
        List<Node> children = node.getChildren();

        if(!contains(seen,node)){
            seen.add(node);
        }

        List<Node> toProcess = new ArrayList<>();
        for(Node child:children){
            if(child.equals(goal)){
                return child;
            }
            if(!contains(seen,child)){
                seen.add(child);
                toProcess.add(child);
            }
        }
        Collections.sort(toProcess, new Comparator<Node>() {
            @Override public int compare(Node node1, Node node2) {
               int d1= node1.distance(goal);
               int d2= node2.distance(goal);
               return d1 - d2;
            }
        });
        for(Node child:toProcess){

             Node childResult=process(child);
             if(childResult != null){
                 return childResult;
             }
        }
      return null;
    }

    private boolean contains(List<Node> seen,Node node){
        boolean output = false;
        for(Node child:seen){
            if(child.equals(node))
                output = true;
        }
        return output;
    }

}
