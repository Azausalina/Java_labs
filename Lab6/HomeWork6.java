import animal.*;

class HomeWork6 {

    /*
    4. * Добавить подсчет созданных котов, собак и животных.
    */
    static String animalCounter(Animal[] animals) {
      int dogCount = 0;
      int catCount = 0;
      for (Animal animal: animals) {
          if (animal.getType() == "Dog") {
              dogCount++;
          }
          else { catCount++; }
      }

      return "In set " + (dogCount + catCount) + " animals." +
              " Of which: " + dogCount + " dogs and " + catCount + " cats.";

    }


    public static void main(String[] args) {

        Animal[] animals = new Animal[5];

        animals[0] = new Cat("Barsik", "British",
                             "male", 30.2f, 2.5f, 8);
        animals[1] = new Cat("Murzik", "Mestizo",
                             "male", 28.4f, 5.5f, 24);
        animals[2] = new Cat("Masya", "Siberian",
                             "female", 35.1f, 8.1f, 36);
        animals[3] = new Dog("Sharik", "Mestizo",
                             "male", 80.2f, 32.3f, 31);
        animals[4] = new Dog("Bobik", "Layka",
                             "female", 65.1f, 20.1f, 65);

        System.out.println("Try to run 25m.:");
        System.out.println("\t" + animals[0].run(25));
        System.out.println("\t" + animals[3].run(25));

        System.out.println("Try to run 750m.:");
        System.out.println("\t" + animals[0].run(750));
        System.out.println("\t" + animals[3].run(750));

        System.out.println("Try to swim 10m.:");
        System.out.println("\t" + animals[0].swim(10));
        System.out.println("\t" + animals[3].swim(10));

        System.out.println("Try to swim 20m.:");
        System.out.println("\t" + animals[0].swim(20));
        System.out.println("\t" + animals[3].swim(20));

        System.out.println(animalCounter(animals));

        int count = 0;
        for (Animal animal: animals) {
            if (count > 0) {
                System.out.println("#####################################");
            }
            System.out.println(animal);
            count++;
        }

    }

}
