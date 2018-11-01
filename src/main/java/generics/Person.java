package generics;



public class Person implements Comparable<Person>{
    Integer age;

    public Person(Integer age){
        this.age=age;
    }

    public int compareTo(Person person){
        return this.age-person.getAge();
    }

    public  Integer getAge(){
        return this.age;
    }
}
