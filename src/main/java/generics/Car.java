package generics;

public class Car implements Comparable<Car> {

    Integer price;

    public Car(Integer price){
        this.price=price;
    }

    public int compareTo(Car car){
        if(this.getPrice()>car.getPrice()){
            return 1;
        }else if(this.getPrice()<car.getPrice()){
            return -1;
        }else{
            return 0;
        }
    }

    public Integer getPrice(){
        return this.price;
    }

    public String toString(){
        return price.toString();
    }

}
