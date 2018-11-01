package generics;

import java.util.List;

public class BoxCollection {

    public <U> void addBox(U u, List<Box<U>> boxes){
        Box<U> box= new Box<>(u);

        boxes.add(box);
    }

    public <U> void outputBoxes(List<Box<U>> boxes){
        int counter=0;
        for(Box<U> box:boxes){
            U boxContents=box.get();
            System.out.println("Box # "+counter+" contains "+boxContents.toString());
            counter++;
        }
    }

}
