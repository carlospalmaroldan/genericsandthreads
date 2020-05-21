package generics.threads.exercises.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Node{
    private Matrix matrix;
    private Node parent;
    private List<Node> children;
    public Node(Matrix matrix){
        this.matrix = matrix;
    }

    public Node(Matrix matrix,Node  parent){
        this.matrix = matrix;
        this.parent = parent;
    }

    public Node getParent(){
        return this.parent;
    }

    public Matrix getMatrix(){return this.matrix;}

    public List<Node> getChildren(){
        List<Node> children = new ArrayList<>();
        Matrix child  = new Matrix();
        Node childNode;
        for(int i=0;i<matrix.getRows();i++){
            for(int j=0; j<matrix.getColumns();j++){
                if(matrix.get(i,j)==0){
                    if(i+1<matrix.getRows()){
                        child = matrix.copy();
                        children.add(new Node(child.exchange(i,j,i+1,j),this));
                    }
                    if(i-1>=0){
                        child = matrix.copy();
                        children.add(new Node(child.exchange(i,j,i-1,j),this));
                    }
                    if(j+1<matrix.getColumns()){
                        child = matrix.copy();
                        children.add(new Node(child.exchange(i,j,i,j+1),this));
                    }
                    if(j-1 >= 0){
                        child = matrix.copy();
                        children.add(new Node(child.exchange(i,j,i,j-1),this));
                    }
                }
            }
        }
        return children;
    }
    public boolean equals(Object other){
        if(other == null){
            return false;
        }else if(!(other instanceof Node)) {
            return false;
        }else{
            Matrix otherMatrix = ((Node)other).getMatrix();
            return matrix.equals(otherMatrix);
        }
    }

    public int distance(Node otherNode){
        return matrix.distance(otherNode.getMatrix());
    }

    public int positionDistance(){
        return matrix.positionDistance();
    }

    public String toString(){
        return matrix.toString();
    }


}
