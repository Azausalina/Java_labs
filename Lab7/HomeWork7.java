/**
* Java Home Work №7
* Author: Anna Zausalina
* Date: 24.11.2021
*/

class Cat {
    private String name;
    private int appetite;
    private boolean isWellFed;

    Cat(String name, int appetite, boolean isWellFed) {
        this.name = name;
        this.appetite = appetite;
        this.isWellFed = isWellFed;
    }

    /*
    2. Каждому коту нужно добавить поле сытость
    (когда создаем котов, они голодны).
    Если коту удалось покушать (хватило еды), сытость = true.

    3. Считаем, что если коту мало еды в тарелке,
    то он её просто не трогает, то есть не может
    быть наполовину сыт (это сделано для упрощения логики программы).
    */
    String eat(Plate plate){
        if (!isWellFed) {
            if (plate.decreaseFood(this.appetite)) {
                this.isWellFed = true;
                return "Cat " + this.name + " now is well-fed";
            }
            return "Cat " + this.name + " didn't eat. Not enough food on the plate";
        }

        return "Cat " + this.name + " is already well-fed";
    }

    @Override
    public String toString() {
        String resultString = "Info about cat:\n";

        resultString += "\t1.Name: " + this.name + "\n";
        resultString += "\t2.Appetite: " + this.appetite + "\n";
        resultString += "\t3.isWellFed: " + this.isWellFed;

        return resultString;
    }
}

class Plate {
    int volume; // кол-во еды в тарелке

    Plate(int volume) {
        this.volume = volume;
    }

    /*
    1. Сделать так, чтобы в тарелке с едой не могло получиться
    отрицательного количества еды (например, в миске 10 еды,
    а кот пытается покушать 15-20).
    */
    boolean decreaseFood(int volume) {
        int oldVolume = this.volume;
        this.volume = (this.volume - volume) >=0 ? this.volume - volume: this.volume;

        return oldVolume != this.volume;
    }

    /*
    5. Добавить в тарелку метод, с помощью которого
    можно было бы добавлять еду в тарелку.
    */
    String addFood(int volume) {
      this.volume += volume;

      return "Now " + this.volume + " conventional units of food on the plate.";
    }

    @Override
    public String toString() {
        String resultString = "Info about plate:\n";

        resultString += "\t1.Volume: " + this.volume + "\n";

        return resultString;
    }
}

class HomeWork7 {

    public static void main(String[] args) {

        /*
        4. Создать массив котов и тарелку с едой,
        попросить всех котов покушать из этой
        тарелки и потом вывести информацию о сытости
        котов в консоль.
        */

        Cat[] cats = new Cat[5];
        Plate plate = new Plate(0);

        System.out.println(plate.addFood(15));

        cats[0] = new Cat("Barsik", 5, false);
        cats[1] = new Cat("Murzik", 6, false);
        cats[2] = new Cat("Masya", 7, false);
        cats[3] = new Cat("Muskya", 3, false);
        cats[4] = new Cat("Milasha", 4, true);

        for (Cat cat: cats) {
            System.out.println(cat.eat(plate));
        }

        System.out.println(plate.addFood(10));

        for (Cat cat: cats) {
            System.out.println(cat.eat(plate));
        }

        for (Cat cat: cats) {
            System.out.println(cat);
        }
      }

}
