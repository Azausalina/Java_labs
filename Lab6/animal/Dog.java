package animal;

public class Dog extends Animal {

    public Dog(String name, String breed, String sex,
        float height, float wieght, int age){

        super(name, breed, "Dog", sex, height, wieght,
              age, 500, 10);
      }
}
