package generics;

import java.util.List;

public class Box<T> {
    private T t;


    public Box(){}

    public  Box(T t){
        this.t=t;
    }

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem){
        int count=0;
        for(T e: anArray){
            if(e.compareTo(elem)>0){
                ++count;
            }
        }
        return  count;
    }

    public static <U> U  pick(U a1, U a2){
        return a2;
    }

    public static <U extends  Number> void printNumber(U u){
        System.out.println(u);
    }

    public static <U extends  Number> void   process(List<U> list){
        for(U elem:list){
            System.out.println(elem);
        }
    }


    public static void processWildcard(List<? extends Number> list){
        for(Number elem:list){
            System.out.println(elem);
        }
    }
}
