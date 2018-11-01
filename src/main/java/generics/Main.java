package generics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
     /*   Box<String> integerBox = new Box<>();
        integerBox.set("hola");
        integerBox.inspect(45);


        Car[] cars= {new Car(5),new Car(3)};
        Person[] persons= {new Person(1),new  Person(28)};
        System.out.println(Box.countGreaterThan(cars,new Car(1)));
        System.out.println(Box.countGreaterThan(persons,new Person(4)));
        List<Car> carslist=Arrays.asList(cars); //esto hace una copia del arreglo de carros, las operaciones se hacen sobre la lista original
        // carslist.remove(0); la lista original es inmutable en tamaño, y esto resulta en error
        List<Car> carslist2=new ArrayList<>(Arrays.asList(cars)); //creo un nuevo ArrayList, las operaciones aca no afectan el arreglo original
        carslist2.add(new Car(2));
        System.out.println(carslist2);
        //System.out.println(cars[2]); el arreglo original no fue tocado

        List<Number> numbers=new ArrayList<>();
        List<Integer> integers=new ArrayList<>();
        numbers.addAll(integers);

        List<? extends Number> numeros=new ArrayList<>();
        List<? extends Integer> enteros=new ArrayList<>();
        numeros=enteros;


        List<Box<String>> boxes=new ArrayList<>();
        BoxCollection collection=new BoxCollection();
        collection.addBox("hola",boxes);
        collection.addBox("como",boxes);
        collection.addBox("vas",boxes);
        collection.outputBoxes(boxes);

        System.out.println(Box.<Car>pick(new Car(4),new Car(5)));

        List<Integer> lista=new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        Box.process(lista);

        Box.printNumber(324L);
        Box.printNumber(2);

        Box.processWildcard(lista);*/













//        SpringApplication.run(Main.class, args);
    }
}
