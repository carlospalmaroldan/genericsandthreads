package generics.threads.exercises.puzzle;

public class Matrix {
    private int[][] array;

    public Matrix(){

    }

    public Matrix(int[][] array){
        this.array = array;
    }

    public int[][] getArray(){return this.array;}

    public int get(int i, int j){
        return array[i][j];
    }

    public int getRows(){
        return array.length;
    }

    public int getColumns(){
        return  array[0].length;
    }

    public Matrix copy(){
        int[][] copyArray = new int[array.length][array[0].length];
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j< array[0].length; j++){
                copyArray[i][j] = array[i][j];
            }
        }
        return new Matrix(copyArray);
    }

    public Matrix exchange(int i,int j, int newi, int newj){
        int temp = array[i][j];
        array[i][j] = array[newi][newj];
        array[newi][newj] = temp;
        return this;
    }

    public boolean equals(Object other){
        if(other == null){
            return false;
        }else if(!(other instanceof Matrix)){
            return false;
        }else{
            Matrix otherMatrix = (Matrix) other;
            int[][] otherArray = otherMatrix.getArray();
            for(int i = 0;i < array.length;i++){
                for(int j=0; j <array[0].length;j++){
                    if(otherArray[i][j] != array[i][j]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int distance(Matrix otherMatrix){
        int distance = 0;
        for(int i =0; i< array.length;i++){
            for(int j=0;j< array[0].length;j++){
                if(array[i][j] != otherMatrix.get(i,j)){
                    distance = distance + 1;
                }
            }
        }
        return distance;
    }

    public String toString(){
        StringBuilder stringBuilder  = new StringBuilder();
        for(int i =0; i < array.length;i++){
            for(int j=0; j< array[0].length; j++){
                stringBuilder.append(array[i][j] + " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
