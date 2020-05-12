package generics.threads.exercises.queueproducerconsumer;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class LimitedQueue {

    private String[] strings = new String[10];

    int position = 0;

    public void add(String s){
        strings[position] = s;
        position++;
    }

    public String remove(){
      String output = strings[0];
      for(int i = 1; i < strings.length; i++) {
         strings[i-1]  = strings[i];
      }
      position--;
      return output;
    };

    public int size(){
        return position;
    }

    public boolean isFull(){
        return position == strings.length-1;
    }

    public boolean isEmpty(){
        return position == 0;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i< strings.length; i++){
            stringBuilder.append(strings[i]+" ");
        }
        return stringBuilder.toString();
    }

}
