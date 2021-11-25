/**
* Class implement Animals
* Author: Anna Zausalina
* Date: 24.11.2021
*/

package animal;

import java.lang.Math;

public abstract class Animal implements Moveable{

    private String name;
    private String breed;
    private String sex;
    private float height;
    private float wieght;
    private int age;
    private String type;

    private int runLimit;
    private int swimLimit;

    public String getName() { return this.name; }
    public String getBreed() { return this.breed; }
    public String getSex() { return this.sex; }
    public float getHeight() { return this.height; }
    public float getWieght() { return this.wieght; }
    public int getAge() { return this.age; }
    public int getRunLimit() { return this.runLimit; }
    public int getSwimLimit() { return this.swimLimit; }
    public String getType() { return this.type; }

    public void setName(String name) { this.name = name; }
    public void setBreed(String breed) { this.breed = breed; }
    public void setSex(String sex) { this.sex = sex; }
    public void setHeight(float height) { this.height = height; }
    public void setWieght(float wieght) { this.wieght = wieght; }
    public void setAge(int age) { this.age = age; }
    public void setRunLimit(int runLimit) { this.runLimit = runLimit; }
    public void setSwimLimit(int swimLimit) { this.swimLimit = swimLimit; }

    public Animal(String name, String breed, String type, String sex,
                  float height, float wieght, int age, int runLimit, int swimLimit){
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        this.height = height;
        this.wieght = wieght;
        this.age = age;
        this.type = type;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
    }

    /*
    2. Все животные могут бежать и плыть.
    В качестве параметра каждому методу передается длина препятствия.
    Результатом выполнения действия будет печать в консоль.
    (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');

    3. У каждого животного есть ограничения на действия
    (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать,
    собака 10 м.).
    */
    public String run(int distance) {
        return this.type + " " + this.name + " ran " + Math.min(distance, runLimit) + " m.";
    }
    public String swim(int distance) {
        if (this.swimLimit > 0) {
            return this.type + " " + this.name +  " swam " + Math.min(distance, swimLimit) + " m.";
        }
        return this.type + " " + this.name + " can't swim :(";
    }

    public String toString() {

      String resultString = "Info about " + this.type + ":\n";

      resultString += "\t1.Name: " + this.name + "\n";
      resultString += "\t2.Breed: " + this.breed + "\n";
      resultString += "\t3.Sex: " + this.sex + "\n";
      resultString += "\t4.Height: " + this.height + "\n";
      resultString += "\t5.Wieght: " + this.wieght + "\n";
      resultString += "\t6.Age: " + this.age + " months\n";
      resultString += "\t7.Can run: " + this.runLimit + "m.\n";
      resultString += "\t8.Can swim: " + this.swimLimit + "m.";

      return resultString;
    }
}
